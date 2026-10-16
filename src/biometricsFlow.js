import { BIOMETRIC_CONFIG, toVectorArray, vectorChecksum } from './biometrics';

/**
 * Menentukan tipe absensi (CHECK-IN atau CHECK-OUT) berdasarkan riwayat log hari ini.
 * 
 * Aturan Bisnis:
 * - Jika sudah ada check-in hari ini dan belum ada check-out: tipe absensi berikutnya adalah CHECK-OUT.
 * - Jika belum ada log atau sudah ada check-in dan check-out lengkap: tipe absensi adalah CHECK-IN.
 * 
 * @param {Array} todayLogs Array log absensi hari berjalan untuk karyawan yang bersangkutan
 * @returns {'CHECK-IN' | 'CHECK-OUT'}
 */
export function determineAttendanceType(todayLogs = []) {
  if (!Array.isArray(todayLogs) || todayLogs.length === 0) {
    return 'CHECK-IN';
  }

  const hasCheckedIn = todayLogs.some(
    log => (log.attendance_type === 'CHECK-IN' || log.attendance_type === 'CHECK_IN')
  );
  const hasCheckedOut = todayLogs.some(
    log => (log.attendance_type === 'CHECK-OUT' || log.attendance_type === 'CHECK_OUT')
  );

  if (hasCheckedIn && !hasCheckedOut) {
    return 'CHECK-OUT';
  }

  return 'CHECK-IN';
}

/**
 * Memvalidasi keabsahan vektor embedding biometrik 1024-D.
 * 
 * @param {any} rawVector
 * @returns {{ valid: boolean, errorReason: string | null, vector: number[] | null }}
 */
export function validateBiometricVector(rawVector) {
  const vector = toVectorArray(rawVector);
  if (!vector || !Array.isArray(vector)) {
    return { valid: false, errorReason: 'CORRUPT_VECTOR', vector: null };
  }

  if (vector.length !== 1024) {
    return { valid: false, errorReason: 'CORRUPT_VECTOR', vector: null };
  }

  let hasNonZero = false;
  for (let i = 0; i < vector.length; i++) {
    const val = vector[i];
    if (typeof val !== 'number' || Number.isNaN(val) || !Number.isFinite(val)) {
      return { valid: false, errorReason: 'CORRUPT_VECTOR', vector: null };
    }
    if (val !== 0) {
      hasNonZero = true;
    }
  }

  if (!hasNonZero) {
    return { valid: false, errorReason: 'CORRUPT_VECTOR', vector: null };
  }

  return { valid: true, errorReason: null, vector };
}

/**
 * Menentukan sumber vektor embedding biometrik yang tersedia untuk pemindaian.
 * 
 * @param {string | number} empId
 * @param {Map | object} localCache
 * @param {boolean} isOnline
 * @returns {{ vector: number[] | null, source: 'local_cache' | 'supabase_cloud' | null, checksum: string | null, template_version: number | null, status: 'READY' | 'NO_VECTOR' | 'CORRUPT_VECTOR' }}
 */
export function resolveVectorSource(empId, localCache, isOnline = false) {
  let cached = null;
  const idStr = String(empId);

  if (localCache instanceof Map) {
    cached = localCache.get(idStr) || localCache.get(Number(empId));
  } else if (localCache && typeof localCache === 'object') {
    cached = localCache[idStr] || localCache[Number(empId)];
  }

  if (cached) {
    const rawVec = cached.descriptor_json || cached.descriptor || cached.face_vector;
    if (rawVec) {
      const validation = validateBiometricVector(rawVec);
      if (!validation.valid) {
        return {
          vector: null,
          source: 'local_cache',
          checksum: cached.vector_checksum || null,
          template_version: (cached.template_version !== undefined && cached.template_version !== null && cached.template_version !== '') ? Number(cached.template_version) : null,
          status: 'CORRUPT_VECTOR'
        };
      }
      return {
        vector: validation.vector,
        source: 'local_cache',
        checksum: cached.vector_checksum || vectorChecksum(validation.vector),
        template_version: (cached.template_version !== undefined && cached.template_version !== null && cached.template_version !== '') ? Number(cached.template_version) : null,
        status: 'READY'
      };
    }
  }

  if (isOnline) {
    return {
      vector: null,
      source: 'supabase_cloud',
      checksum: null,
      template_version: null,
      status: 'NO_VECTOR'
    };
  }

  return {
    vector: null,
    source: null,
    checksum: null,
    template_version: null,
    status: 'NO_VECTOR'
  };
}

/**
 * Mengklasifikasikan alasan hasil pemindaian wajah secara seragam untuk telemetri & UI.
 * 
 * @param {object} params
 * @returns {'OK' | 'NO_VECTOR' | 'CORRUPT_VECTOR' | 'LOW_SCORE' | 'LIVENESS_FAIL' | 'NO_FACE' | 'CANCELLED'}
 */
export function determineFailureReason({
  matched = false,
  medianCosine = null,
  threshold = BIOMETRIC_CONFIG.MATCH_COSINE_THRESHOLD,
  framesEvaluated = 0,
  vectorStatus = 'READY',
  livenessPassed = true,
  cancelled = false
} = {}) {
  if (cancelled) return 'CANCELLED';
  if (vectorStatus === 'NO_VECTOR') return 'NO_VECTOR';
  if (vectorStatus === 'CORRUPT_VECTOR') return 'CORRUPT_VECTOR';
  if (framesEvaluated === 0) return 'NO_FACE';
  if (!livenessPassed) return 'LIVENESS_FAIL';
  if (!matched || (medianCosine != null && medianCosine < threshold)) return 'LOW_SCORE';
  return 'OK';
}

/**
 * Memeriksa apakah vektor lokal perlu diperbarui (rescan download) dari server.
 * 
 * @param {object} params
 * @param {string | null} params.localChecksum
 * @param {string | null} params.serverChecksum
 * @param {any} params.localVector
 * @returns {boolean}
 */
export function determineNeedsDownload({ localChecksum, serverChecksum, localVector }) {
  if (!localVector) return true;
  if (!serverChecksum) return false;
  if (!localChecksum) return true;
  return localChecksum !== serverChecksum;
}
