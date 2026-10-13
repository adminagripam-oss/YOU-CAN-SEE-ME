export const BIOMETRIC_CONFIG = {
  MATCH_COSINE_THRESHOLD: 0.92,
  ENROLL_SAMPLE_COUNT: 7,
  ENROLL_MIN_INTERVAL_MS: 150,
  SCAN_SAMPLE_COUNT: 5,
  QUALITY_MIN_SCORE: 0.8,
  QUALITY_MAX_ANGLE: 0.15,
  DUPLICATE_COSINE_THRESHOLD: 0.93,
  ENROLL_MIN_CONSISTENCY: 0.90,
  TEMPLATE_VERSION: 1
};

export function toF32(v) {
  if (v instanceof Float32Array) return v;
  if (Array.isArray(v)) return new Float32Array(v);
  throw new Error("Invalid vector type");
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
 * Builds a master template from multiple enrollment embeddings.
 * Removes the single worst outlier (lowest cosine to centroid) when count > 3,
 * then returns an L2-normalized average vector plus a consistency score.
 */
export function buildTemplateDetailed(embeddings) {
  if (!embeddings || embeddings.length === 0) return null;
  const dim = embeddings[0].length;

  // Pass 1: rough centroid to identify the outlier
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

  // Pass 2: L2-normalized average of remaining samples
  const avg = new Float32Array(dim);
  for (const { vec } of kept) {
    const normed = l2norm(vec);
    for (let i = 0; i < dim; i++) avg[i] += normed[i];
  }
  for (let i = 0; i < dim; i++) avg[i] /= kept.length;

  return { vector: l2norm(avg), consistency: minSim };
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
