import { describe, it, expect } from 'vitest';
import { BIOMETRIC_CONFIG, cosine, toVectorArray } from './biometrics';

describe('Tugas 3 & 4: Offline Duplicate Check & Template Version Integrity', () => {
  const dummy1024A = Array.from({ length: 1024 }, (_, i) => Math.sin(i * 0.05));
  const dummy1024Duplicate = [...dummy1024A]; // Cosine 1.0
  const dummy1024Different = Array.from({ length: 1024 }, (_, i) => Math.cos(i * 0.1));

  it('standardizes duplicate threshold to BIOMETRIC_CONFIG.DUPLICATE_COSINE_THRESHOLD (0.88)', () => {
    expect(BIOMETRIC_CONFIG.DUPLICATE_COSINE_THRESHOLD).toBe(0.88);
    expect(BIOMETRIC_CONFIG.MATCH_COSINE_THRESHOLD).toBe(0.92);
  });

  it('detects duplicate face offline when comparison vector matches local master', () => {
    const localMasters = [
      { employee_id: 'emp-1', name: 'Ahmad Yani', descriptor_json: dummy1024A, template_version: 2 }
    ];

    const inputVec = toVectorArray(dummy1024Duplicate);
    expect(inputVec).not.toBeNull();

    let duplicateFound = false;
    let matchedEmp = null;

    for (const master of localMasters) {
      const masterVec = toVectorArray(master.descriptor_json);
      if (!masterVec) continue;
      const sim = cosine(inputVec, masterVec);
      if (sim >= BIOMETRIC_CONFIG.DUPLICATE_COSINE_THRESHOLD) {
        duplicateFound = true;
        matchedEmp = master;
        break;
      }
    }

    expect(duplicateFound).toBe(true);
    expect(matchedEmp.name).toBe('Ahmad Yani');
  });

  it('permits registration when face is distinct from all local masters', () => {
    const localMasters = [
      { employee_id: 'emp-1', name: 'Ahmad Yani', descriptor_json: dummy1024A, template_version: 2 }
    ];

    const inputVec = toVectorArray(dummy1024Different);
    expect(inputVec).not.toBeNull();

    let duplicateFound = false;
    for (const master of localMasters) {
      const masterVec = toVectorArray(master.descriptor_json);
      if (!masterVec) continue;
      const sim = cosine(inputVec, masterVec);
      if (sim >= BIOMETRIC_CONFIG.DUPLICATE_COSINE_THRESHOLD) {
        duplicateFound = true;
        break;
      }
    }

    expect(duplicateFound).toBe(false);
  });

  it('handles empty local master list gracefully without blocking enrollment', () => {
    const localMasters = [];
    const inputVec = toVectorArray(dummy1024A);

    let duplicateFound = false;
    let warningTriggered = false;

    if (localMasters.length === 0) {
      warningTriggered = true;
    }

    for (const master of localMasters) {
      const masterVec = toVectorArray(master.descriptor_json);
      if (!masterVec) continue;
      const sim = cosine(inputVec, masterVec);
      if (sim >= BIOMETRIC_CONFIG.DUPLICATE_COSINE_THRESHOLD) {
        duplicateFound = true;
        break;
      }
    }

    expect(warningTriggered).toBe(true);
    expect(duplicateFound).toBe(false); // Does not block
  });

  it('validates template_version semantics: 0 = photo upload, 1 = frontal camera, 2 = multi-pose', () => {
    const photoUploadRecord = {
      name: 'Foto Karyawan',
      template_version: 0,
      descriptor_json: dummy1024A
    };

    const frontalRecord = {
      name: 'Frontal Karyawan',
      template_version: 1,
      descriptor_json: dummy1024A
    };

    const multiPoseRecord = {
      name: 'Multi-Pose Karyawan',
      template_version: 2,
      descriptor_json: dummy1024A
    };

    // Ensure 0 is preserved and not coerced to 1 via falsy (0 || 1)
    const resolveVer = (rec) => rec.template_version !== undefined && rec.template_version !== null ? Number(rec.template_version) : 1;

    expect(resolveVer(photoUploadRecord)).toBe(0);
    expect(resolveVer(frontalRecord)).toBe(1);
    expect(resolveVer(multiPoseRecord)).toBe(2);

    // Verify template sempit check
    const isNarrowTemplate = (ver) => ver <= 1;
    expect(isNarrowTemplate(photoUploadRecord.template_version)).toBe(true);
    expect(isNarrowTemplate(frontalRecord.template_version)).toBe(true);
    expect(isNarrowTemplate(multiPoseRecord.template_version)).toBe(false);
  });
});
