export const BIOMETRIC_CONFIG = {
  MATCH_COSINE_THRESHOLD: 0.90, // Sementara 0.90, harus ditentukan dari data kalibrasi (Fase 5)
  ENROLL_SAMPLE_COUNT: 7,
  ENROLL_MIN_INTERVAL_MS: 150,
  SCAN_SAMPLE_COUNT: 5,
  QUALITY_MIN_SCORE: 0.8,
  QUALITY_MAX_ANGLE: 0.15, // radians
  DUPLICATE_COSINE_THRESHOLD: 0.93,
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

const COSINE_FLOOR = 0.70;
const COSINE_CEIL = 0.98;

export function displayScore(cosSim) {
  if (cosSim <= COSINE_FLOOR) return 0;
  if (cosSim >= COSINE_CEIL) return 100;
  const normalized = (cosSim - COSINE_FLOOR) / (COSINE_CEIL - COSINE_FLOOR);
  return parseFloat((Math.pow(normalized, 1.8) * 100).toFixed(1));
}

export function median(arr) {
  if (!arr || arr.length === 0) return 0;
  const sorted = [...arr].sort((a, b) => a - b);
  const mid = Math.floor(sorted.length / 2);
  if (sorted.length % 2 === 0) {
    return (sorted[mid - 1] + sorted[mid]) / 2;
  }
  return sorted[mid];
}
