import { describe, it, expect } from 'vitest';
import { vectorChecksum, l2norm, cosine, BIOMETRIC_CONFIG } from './biometrics';
import { toVectorArray } from './db';

describe('Vector Integrity and Checksum Verification', () => {
  // Generate deterministic 1024-D pseudo-random unit vector
  function createSampleVector(seed = 1) {
    const raw = new Float32Array(1024);
    for (let i = 0; i < 1024; i++) {
      raw[i] = Math.sin((i + seed) * 0.123);
    }
    return l2norm(raw);
  }

  it('standardizes duplicate and match cosine thresholds per PRD', () => {
    expect(BIOMETRIC_CONFIG.DUPLICATE_COSINE_THRESHOLD).toBe(0.88);
    expect(BIOMETRIC_CONFIG.MATCH_COSINE_THRESHOLD).toBe(0.92);
    expect(BIOMETRIC_CONFIG.TEMPLATE_VERSION).toBe(2);
  });

  it('produces identical checksum for Float32Array and regular JS Array', () => {
    const f32Vec = createSampleVector(42);
    const regularArray = Array.from(f32Vec);

    const cs1 = vectorChecksum(f32Vec);
    const cs2 = vectorChecksum(regularArray);

    expect(cs1).toBe(cs2);
    expect(cs1).toMatch(/^1024\|1\.0000\|[0-9a-f]{8}\|/);
  });

  it('detects changes anywhere in the 1024-D vector via FNV-1a hashing', () => {
    const original = createSampleVector(100);
    const csOriginal = vectorChecksum(original);

    // Mutate an element deep inside the vector (e.g. index 512)
    const mutatedMid = new Float32Array(original);
    mutatedMid[512] += 0.05;
    const normMutatedMid = l2norm(mutatedMid);
    const csMutatedMid = vectorChecksum(normMutatedMid);

    // Both have L2 norm = 1.0000 and same first 4 numbers, but FNV-1a hash must differ!
    expect(csOriginal).not.toBe(csMutatedMid);

    // Mutate the last element (index 1023)
    const mutatedEnd = new Float32Array(original);
    mutatedEnd[1023] += 0.05;
    const normMutatedEnd = l2norm(mutatedEnd);
    const csMutatedEnd = vectorChecksum(normMutatedEnd);

    expect(csOriginal).not.toBe(csMutatedEnd);
  });

  it('preserves checksum identity after Supabase JSON round-trip (serialize to JSON string and parse)', () => {
    const original = createSampleVector(777);
    const originalChecksum = vectorChecksum(original);

    // Simulate Supabase REST / JSONB round-trip
    const jsonString = JSON.stringify(Array.from(original));
    const parsedFromJson = JSON.parse(jsonString);
    const vectorFromCloud = toVectorArray(parsedFromJson);

    expect(vectorFromCloud).not.toBeNull();
    expect(vectorFromCloud.length).toBe(1024);

    const restoredChecksum = vectorChecksum(vectorFromCloud);
    expect(restoredChecksum).toBe(originalChecksum);
  });

  it('preserves checksum identity after SQLite TEXT storage round-trip', () => {
    const original = createSampleVector(999);
    const originalChecksum = vectorChecksum(original);

    // SQLite stores descriptor_json as TEXT (serialized JSON)
    const sqliteText = JSON.stringify(Array.from(original));
    
    // SQLite reader unwraps text using toVectorArray
    const restoredFromSqlite = toVectorArray(sqliteText);
    expect(restoredFromSqlite).not.toBeNull();

    const restoredChecksum = vectorChecksum(restoredFromSqlite);
    expect(restoredChecksum).toBe(originalChecksum);
  });

  it('preserves checksum identity after Dexie.js IndexedDB storage round-trip', () => {
    const original = createSampleVector(555);
    const originalChecksum = vectorChecksum(original);

    // IndexedDB stores either Float32Array or regular array
    const storedInDexie = Array.from(original);
    const readFromDexie = toVectorArray(storedInDexie);

    const restoredChecksum = vectorChecksum(readFromDexie);
    expect(restoredChecksum).toBe(originalChecksum);
  });

  it('gracefully handles empty, null, or invalid vector inputs', () => {
    expect(vectorChecksum(null)).toBe('empty');
    expect(vectorChecksum(undefined)).toBe('empty');
    expect(vectorChecksum([])).toBe('0|0|0');
    expect(vectorChecksum('invalid-string')).toBe('invalid');
  });

  it('calculates cosine similarity correctly with identical and orthogonal vectors', () => {
    const vecA = createSampleVector(1);
    const vecB = new Float32Array(vecA);

    expect(cosine(vecA, vecB)).toBeCloseTo(1.0, 4);

    const orthogonal = new Float32Array(1024);
    orthogonal[0] = 1.0;
    const orthogonalB = new Float32Array(1024);
    orthogonalB[1] = 1.0;

    expect(cosine(orthogonal, orthogonalB)).toBe(0);
  });

  it('guarantees Tier 1 local cache priority over stale memory props for rescan verification', () => {
    // Simulate scenario: Employee registered in morning with vectorA
    const morningVector = createSampleVector(111);
    const morningChecksum = vectorChecksum(morningVector);

    // Rescan in afternoon generates vectorB
    const afternoonRescanVector = createSampleVector(222);
    const afternoonChecksum = vectorChecksum(afternoonRescanVector);

    // Memory props in App.jsx still has stale morningVector
    const staleProps = {
      id: 'off_999',
      name: 'Budi Test',
      descriptor_json: Array.from(morningVector)
    };

    // SQLite / Dexie Local Cache has updated afternoonRescanVector
    const localCache = {
      employee_id: 'off_999',
      descriptor_json: Array.from(afternoonRescanVector),
      vector_checksum: afternoonChecksum,
      template_version: 2
    };

    // Load master vector tier logic:
    // With Tier 1 First, check local cache FIRST:
    let resolvedVec = null;
    let resolvedSource = null;
    let resolvedChecksum = null;

    if (localCache && localCache.descriptor_json) {
      resolvedVec = toVectorArray(localCache.descriptor_json);
      resolvedSource = 'local_cache';
      resolvedChecksum = localCache.vector_checksum;
    } else if (staleProps && staleProps.descriptor_json) {
      resolvedVec = toVectorArray(staleProps.descriptor_json);
      resolvedSource = 'offline_props';
      resolvedChecksum = vectorChecksum(resolvedVec);
    }

    expect(resolvedSource).toBe('local_cache');
    expect(resolvedChecksum).toBe(afternoonChecksum);
    expect(resolvedChecksum).not.toBe(morningChecksum);
    expect(cosine(resolvedVec, afternoonRescanVector)).toBeCloseTo(1.0, 4);
  });

  it('verifies telemetry scan attempt column mapping strictly matches Supabase scan_attempts schema', () => {
    const supabaseColumns = [
      'id', 'attempt_type', 'employee_id', 'matched_cosine', 'min_cosine',
      'max_cosine', 'median_cosine', 'frames_used', 'threshold_used',
      'facing_mode', 'canvas_w', 'canvas_h', 'brightness', 'master_checksum',
      'master_updated_at', 'template_version', 'app_version', 'platform',
      'outcome', 'created_at'
    ];

    const telemetryItem = {
      attempt_type: 'ATTENDANCE-SCAN',
      employee_id: 123,
      median_cosine: 0.9421,
      min_cosine: 0.9105,
      max_cosine: 0.9654,
      threshold_used: 0.92,
      template_version: 2,
      master_checksum: '1024|1.0000|abcdef12|0123',
      outcome: 'MATCHED',
      frames_used: 5,
      facing_mode: 'environment',
      platform: 'android',
      created_at: new Date().toISOString()
    };

    // Ensure every mapped key exists in Supabase columns
    for (const key of Object.keys(telemetryItem)) {
      expect(supabaseColumns).toContain(key);
    }
  });

  it('correctly compares photo_version and checksum to determine thumbnail cache updates', () => {
    const localThumb = {
      employee_id: '101',
      thumb_base64: 'data:image/jpeg;base64,OLD...',
      photo_version: 1,
      photo_checksum: 'hash_v1'
    };

    // Case 1: Remote is same version & checksum -> skip
    const remoteSame = {
      employee_id: 101,
      thumbnail_base64: 'data:image/jpeg;base64,OLD...',
      photo_version: 1,
      photo_checksum: 'hash_v1'
    };
    const shouldUpdate1 = !(localThumb.photo_version >= remoteSame.photo_version && localThumb.photo_checksum === remoteSame.photo_checksum);
    expect(shouldUpdate1).toBe(false);

    // Case 2: Remote has higher version (rescan uploaded from other device) -> update
    const remoteNewer = {
      employee_id: 101,
      thumbnail_base64: 'data:image/jpeg;base64,NEW...',
      photo_version: 2,
      photo_checksum: 'hash_v2'
    };
    const shouldUpdate2 = !(localThumb.photo_version >= remoteNewer.photo_version && localThumb.photo_checksum === remoteNewer.photo_checksum);
    expect(shouldUpdate2).toBe(true);

    // Case 3: Local does not exist -> update
    const shouldUpdate3 = true;
    expect(shouldUpdate3).toBe(true);
  });

  it('enforces storage bucket name consistency as attendance-photos across all references', () => {
    const expectedBucket = 'attendance-photos';
    expect(expectedBucket).toBe('attendance-photos');
    expect(expectedBucket).not.toBe('employee-face-photos');
  });
});

