export const BIOMETRIC_CONFIG = {
  MATCH_COSINE_THRESHOLD: 0.92,
  ENROLL_SAMPLE_COUNT: 8,
  ENROLL_TARGET_FRAMES: 8,
  ENROLL_MIN_FRONTAL_FRAMES: 6,
  ENROLL_MIN_INTERVAL_MS: 300,
  ENROLL_MIN_FRAME_GAP_MS: 300,
  ENROLL_MAX_DURATION_MS: 12000,
  SCAN_SAMPLE_COUNT: 5,
  QUALITY_MIN_SCORE: 0.8,
  QUALITY_MAX_ANGLE: 0.15,
  TURNED_YAW_MIN: 0.12,
  TURNED_YAW_MAX: 0.30,
  QUALITY_MAX_YAW_TURNED: 0.30,
  QUALITY_MAX_PITCH_TURNED: 0.20,
  DUPLICATE_COSINE_THRESHOLD: 0.88,
  ENROLL_MIN_CONSISTENCY: 0.90,
  TEMPLATE_VERSION: 2
};

export function toF32(v) {
  if (v instanceof Float32Array) return v;
  if (Array.isArray(v)) return new Float32Array(v);
  throw new Error("Invalid vector type");
}

/**
 * Menghasilkan sidik vektor ringkas dan deterministik.
 * Menggabungkan panjang vektor, norma L2, hash FNV-1a atas seluruh elemen, dan 4 angka pertama.
 */
export function vectorChecksum(vec) {
  if (!vec) return 'empty';
  let arr;
  try {
    arr = toF32(vec);
  } catch (_) {
    return 'invalid';
  }
  const len = arr.length;
  if (len === 0) return '0|0|0';

  let sqSum = 0;
  let hash = 0x811c9dc5;
  for (let i = 0; i < len; i++) {
    const val = arr[i];
    sqSum += val * val;
    const intVal = Math.round(val * 10000);
    hash ^= (intVal & 0xff);
    hash = Math.imul(hash, 0x01000193);
    hash ^= ((intVal >> 8) & 0xff);
    hash = Math.imul(hash, 0x01000193);
    hash ^= ((intVal >> 16) & 0xff);
    hash = Math.imul(hash, 0x01000193);
    hash ^= ((intVal >> 24) & 0xff);
    hash = Math.imul(hash, 0x01000193);
  }
  const fnvHex = (hash >>> 0).toString(16).padStart(8, '0');
  const norm = Math.sqrt(sqSum).toFixed(4);
  const first4 = [];
  for (let i = 0; i < Math.min(4, len); i++) {
    first4.push(arr[i].toFixed(4));
  }
  return `${len}|${norm}|${fnvHex}|${first4.join(',')}`;
}

export function l2norm(v) {
  const vec = toF32(v);
  let sqSum = 0;
  for (let i = 0; i < vec.length; i++) {
    sqSum += vec[i] * vec[i];
  }
  const norm = Math.sqrt(sqSum);
  if (norm === 0) return vec.slice();

  const normalized = new Float32Array(vec.length);
  for (let i = 0; i < vec.length; i++) {
    normalized[i] = vec[i] / norm;
  }
  return normalized;
}

export function cosine(a, b) {
  const vecA = toF32(a);
  const vecB = toF32(b);
  if (vecA.length !== vecB.length) throw new Error("Dimension mismatch");
  if (vecA.length === 0) return 0;

  let dot = 0;
  let normA = 0;
  let normB = 0;
  for (let i = 0; i < vecA.length; i++) {
    dot += vecA[i] * vecB[i];
    normA += vecA[i] * vecA[i];
    normB += vecB[i] * vecB[i];
  }
  if (normA === 0 || normB === 0) return 0;
  return Math.max(0, Math.min(1.0, dot / (Math.sqrt(normA) * Math.sqrt(normB))));
}

/**
 * Computes the dimension-wise median vector from an array of embeddings,
 * then returns the L2-normalized vector.
 */
export function medianVectorPerDimension(vectors) {
  if (!vectors || vectors.length === 0) return null;
  const count = vectors.length;
  const dim = vectors[0].length;
  const result = new Float32Array(dim);

  const col = new Float32Array(count);
  for (let d = 0; d < dim; d++) {
    for (let i = 0; i < count; i++) {
      col[i] = vectors[i][d];
    }
    col.sort();
    const mid = Math.floor(count / 2);
    if (count % 2 === 0) {
      result[d] = (col[mid - 1] + col[mid]) / 2;
    } else {
      result[d] = col[mid];
    }
  }

  return l2norm(result);
}

/**
 * Creates a fresh state container for multi-pose enrollment collection.
 */
export function createEnrollmentCollector(mode = 'multi_pose') {
  return {
    frames: [],
    lastSampleTime: 0,
    startTime: 0,
    elapsedActiveMs: 0,
    lastActiveTime: 0,
    isPaused: false,
    mode, // 'multi_pose' | 'frontal_only'
    frontalCount: 0,
    turnedCount: 0,
    targetFrames: mode === 'frontal_only' ? 6 : 8,
    minFrontalFrames: 6
  };
}

/**
 * Pure evaluation function for collecting multi-pose enrollment frames.
 * Returns updated collector and status.
 */
export function selectEnrollmentFrames(collector, detection, nowMs, config = BIOMETRIC_CONFIG) {
  if (!collector) return { status: 'error', reason: 'no_collector' };

  // 1. Check if face is lost / misaligned (pause state)
  if (!detection) {
    if (collector.startTime > 0 && !collector.isPaused) {
      collector.isPaused = true;
    }
    return {
      status: 'paused',
      reason: 'face_lost',
      message: 'Arahkan wajah ke dalam bingkai...',
      frameCount: collector.frames.length,
      targetFrames: collector.targetFrames,
      collector
    };
  }

  // 2. Face is present: resume from pause if needed
  if (collector.isPaused) {
    collector.isPaused = false;
    collector.lastActiveTime = nowMs;
  }

  // 3. Track active elapsed time
  if (collector.startTime === 0) {
    collector.startTime = nowMs;
    collector.lastActiveTime = nowMs;
    collector.elapsedActiveMs = 0;
  } else {
    collector.elapsedActiveMs += Math.max(0, nowMs - (collector.lastActiveTime || nowMs));
    collector.lastActiveTime = nowMs;
  }

  // 4. Check overall enrollment timeout (12 seconds)
  if (collector.elapsedActiveMs > config.ENROLL_MAX_DURATION_MS) {
    // If we already have at least 6 frontal frames, we can complete with frontal fallback
    if (collector.frontalCount >= config.ENROLL_MIN_FRONTAL_FRAMES) {
      const vector = medianVectorPerDimension(collector.frames.map(f => f.embedding));
      const minSim = Math.min(...collector.frames.map(f => cosine(f.embedding, vector)));
      return {
        status: 'complete',
        vector,
        consistency: minSim,
        template_version: collector.turnedCount > 0 ? 2 : 1,
        frames: collector.frames,
        collector
      };
    }

    return {
      status: 'timeout',
      reason: 'insufficient_frames',
      message: `Waktu habis: baru ${collector.frames.length}/${collector.targetFrames} frame terkumpul.`,
      framesCount: collector.frames.length,
      collector
    };
  }

  // 5. Check minimum frame gap (300ms)
  if (collector.lastSampleTime > 0 && (nowMs - collector.lastSampleTime < config.ENROLL_MIN_FRAME_GAP_MS)) {
    return {
      status: 'rejected',
      reason: 'too_close',
      gap: nowMs - collector.lastSampleTime,
      collector
    };
  }

  // 6. Quality gating on detection
  if (!detection || !detection.embedding || detection.embedding.length !== 1024) {
    return { status: 'rejected', reason: 'invalid_embedding', collector };
  }
  if (typeof detection.score === 'number' && detection.score < config.QUALITY_MIN_SCORE) {
    return {
      status: 'rejected',
      reason: 'low_score',
      score: detection.score,
      message: 'Kualitas frame terlalu rendah atau buram',
      collector
    };
  }

  const pitch = Math.abs(detection.rotation?.angle?.pitch || 0);
  const yaw = Math.abs(detection.rotation?.angle?.yaw || 0);
  const roll = Math.abs(detection.rotation?.angle?.roll || 0);

  // Reject extreme pitch or roll
  if (pitch > config.QUALITY_MAX_PITCH_TURNED || roll > config.QUALITY_MAX_ANGLE) {
    return {
      status: 'rejected',
      reason: 'angle_out_of_bounds',
      pitch,
      roll,
      message: 'Posisi kepala terlalu miring vertikal, tegakkan wajah',
      collector
    };
  }

  // Opportunistic turned yaw evaluation
  const isFrontal = yaw <= config.QUALITY_MAX_ANGLE;
  const isTurned = !isFrontal && yaw >= config.TURNED_YAW_MIN && yaw <= config.TURNED_YAW_MAX;

  let frameType = 'frontal';
  if (collector.mode === 'frontal_only') {
    if (!isFrontal) {
      return {
        status: 'rejected',
        reason: 'angle_out_of_bounds',
        yaw,
        message: 'Tatap lurus ke kamera',
        collector
      };
    }
    frameType = 'frontal';
  } else {
    // Multi-pose mode: accept frontal or opportunistic turned
    if (isFrontal) {
      frameType = 'frontal';
    } else if (isTurned) {
      frameType = 'turned';
    } else {
      return {
        status: 'rejected',
        reason: 'angle_out_of_bounds',
        yaw,
        message: 'Sudut belokan terlalu jauh (maks 20°), tatap lebih ke depan',
        collector
      };
    }
  }

  // 7. Accept frame: Record frame and timestamp
  collector.lastSampleTime = nowMs;

  const frameData = {
    embedding: Array.from(detection.embedding),
    timestamp: nowMs,
    type: frameType,
    yaw,
    pitch
  };
  collector.frames.push(frameData);
  if (frameType === 'turned') {
    collector.turnedCount++;
  } else {
    collector.frontalCount++;
  }

  // 8. Check completion
  const targetMet = collector.mode === 'frontal_only'
    ? collector.frames.length >= collector.targetFrames
    : collector.frames.length >= collector.targetFrames;

  if (targetMet) {
    const vector = medianVectorPerDimension(collector.frames.map(f => f.embedding));
    const minSim = Math.min(...collector.frames.map(f => cosine(f.embedding, vector)));

    if (minSim < config.ENROLL_MIN_CONSISTENCY) {
      return {
        status: 'low_consistency',
        consistency: minSim,
        message: `Konsistensi rendah: ${(minSim * 100).toFixed(1)}% < 90%. Ulangi dengan cahaya lebih merata.`,
        collector
      };
    }

    const template_version = (collector.turnedCount > 0 && collector.mode !== 'frontal_only') ? 2 : 1;

    return {
      status: 'complete',
      vector,
      consistency: minSim,
      template_version,
      frames: collector.frames,
      collector
    };
  }

  // 9. Prompt guidance for user
  let promptText = `Mengambil sampel ${collector.frames.length}/${collector.targetFrames} (Tatap lurus ke kamera)`;
  if (collector.mode === 'multi_pose') {
    if (collector.frontalCount >= 3 && collector.turnedCount < 2) {
      promptText = `Mengambil sampel ${collector.frames.length}/${collector.targetFrames} (Boleh putar kepala sedikit ke samping)`;
    } else {
      promptText = `Mengambil sampel ${collector.frames.length}/${collector.targetFrames} (Tatap lurus ke kamera)`;
    }
  }

  return {
    status: 'collecting',
    progress: collector.frames.length / collector.targetFrames,
    frameCount: collector.frames.length,
    targetFrames: collector.targetFrames,
    promptText,
    collector
  };
}

/**
 * Builds a master template from multiple enrollment embeddings.
 * For v2 multi-pose (>=8 frames), aggregates using dimension-wise median + L2 normalization.
 * For legacy / fewer frames, drops single worst outlier and calculates L2-normalized mean.
 */
export function buildTemplateDetailed(embeddings, version = 2) {
  if (!embeddings || embeddings.length === 0) return null;
  const dim = embeddings[0].length;

  if (version === 2 || embeddings.length >= 8) {
    const medianVec = medianVectorPerDimension(embeddings);
    const minSim = Math.min(...embeddings.map(vec => cosine(vec, medianVec)));
    return { vector: medianVec, consistency: minSim, template_version: 2 };
  }

  // Legacy Pass 1: rough centroid to identify the outlier
  const centroid = new Array(dim).fill(0);
  for (const vec of embeddings) {
    for (let i = 0; i < dim; i++) centroid[i] += vec[i];
  }
  for (let i = 0; i < dim; i++) centroid[i] /= embeddings.length;

  // Rank each sample by its cosine similarity to the centroid
  const ranked = embeddings.map(vec => ({ vec, sim: cosine(vec, centroid) }));

  // Drop the single worst sample when we have enough
  let kept = ranked;
  if (ranked.length > 3) {
    kept = [...ranked].sort((a, b) => b.sim - a.sim);
    kept.pop();
  }

  // Consistency = cosine of the worst sample that was still kept
  const minSim = Math.min(...kept.map(d => d.sim));

  // Legacy Pass 2: L2-normalized average of remaining samples
  const avg = new Float32Array(dim);
  for (const { vec } of kept) {
    const normed = l2norm(vec);
    for (let i = 0; i < dim; i++) avg[i] += normed[i];
  }
  for (let i = 0; i < dim; i++) avg[i] /= kept.length;

  return { vector: l2norm(avg), consistency: minSim, template_version: 1 };
}

/**
 * Legacy builder kept for backward compatibility.
 * Prefer buildTemplateDetailed for new enrollment flows.
 */
export function buildTemplate(embeddings) {
  if (!embeddings || embeddings.length === 0) throw new Error("No embeddings provided");
  const dim = embeddings[0].length;
  const avg = new Float32Array(dim);

  for (const emb of embeddings) {
    const normEmb = l2norm(emb);
    for (let i = 0; i < dim; i++) {
      avg[i] += normEmb[i];
    }
  }

  for (let i = 0; i < dim; i++) {
    avg[i] /= embeddings.length;
  }

  return l2norm(avg);
}

export function validateVector(v) {
  if (!v) return false;
  if (v.length !== 1024) return false;
  for (let i = 0; i < v.length; i++) {
    if (!Number.isFinite(v[i])) return false;
  }
  return true;
}

export function isGoodFrame(face) {
  if (!face || !face.embedding) return false;
  if (face.embedding.length !== 1024) return false;
  if (typeof face.score === 'number' && face.score < BIOMETRIC_CONFIG.QUALITY_MIN_SCORE) return false;

  const pitch = face.rotation?.angle?.pitch || 0;
  const yaw = face.rotation?.angle?.yaw || 0;
  const roll = face.rotation?.angle?.roll || 0;

  if (Math.abs(pitch) > BIOMETRIC_CONFIG.QUALITY_MAX_ANGLE) return false;
  if (Math.abs(yaw) > BIOMETRIC_CONFIG.QUALITY_MAX_ANGLE) return false;
  if (Math.abs(roll) > BIOMETRIC_CONFIG.QUALITY_MAX_ANGLE) return false;

  return true;
}

/**
 * Piecewise linear display score.
 * Forces the match threshold (0.92) to render as exactly 82% on screen,
 * giving users a clear pass/fail boundary.
 *
 * Below threshold: linear ramp 0% at floor (0.70) → displayTarget% at threshold.
 * Above threshold: linear ramp displayTarget% at threshold → 100% at ceiling (0.98).
 */
function makeAlignedDisplay(thresholdCos, displayTarget) {
  const FLOOR = 0.70;
  const CEIL = 0.98;
  return function(cosSim) {
    if (cosSim <= FLOOR) return 0;
    if (cosSim >= CEIL) return 100;
    if (cosSim < thresholdCos) {
      return ((cosSim - FLOOR) / (thresholdCos - FLOOR)) * displayTarget;
    }
    return displayTarget + ((cosSim - thresholdCos) / (CEIL - thresholdCos)) * (100 - displayTarget);
  };
}

export const displayScore = makeAlignedDisplay(BIOMETRIC_CONFIG.MATCH_COSINE_THRESHOLD, 82);

export function median(arr) {
  if (!arr || arr.length === 0) return 0;
  const sorted = [...arr].sort((a, b) => a - b);
  const mid = Math.floor(sorted.length / 2);
  if (sorted.length % 2 === 0) {
    return (sorted[mid - 1] + sorted[mid]) / 2;
  }
  return sorted[mid];
}
