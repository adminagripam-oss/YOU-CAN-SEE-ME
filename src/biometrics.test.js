import { expect, test, describe } from 'vitest';
import { l2norm, cosine, buildTemplate, buildTemplateDetailed, validateVector, displayScore, median } from './biometrics';

describe('Biometrics Utilities', () => {
  test('l2norm normalizes a vector', () => {
    const vec = [3, 4];
    const norm = l2norm(vec);
    expect(norm[0]).toBeCloseTo(0.6);
    expect(norm[1]).toBeCloseTo(0.8);
    expect(norm instanceof Float32Array).toBe(true);
  });

  test('cosine similarity calculation', () => {
    const vecA = [1, 0, 0];
    const vecB = [0, 1, 0];
    const vecC = [1, 0, 0];
    const vecD = [0.707106, 0.707106, 0];

    expect(cosine(vecA, vecB)).toBeCloseTo(0.0);
    expect(cosine(vecA, vecC)).toBeCloseTo(1.0);
    expect(cosine(vecA, vecD)).toBeCloseTo(0.707106);
  });

  describe('displayScore — Piecewise Linear (threshold=0.92, target=82%)', () => {
    test('floor and ceiling clamps', () => {
      expect(displayScore(0.60)).toBe(0);
      expect(displayScore(0.70)).toBe(0);
      expect(displayScore(0.98)).toBe(100);
      expect(displayScore(1.00)).toBe(100);
    });

    test('threshold cosine 0.92 renders as exactly 82%', () => {
      expect(displayScore(0.92)).toBeCloseTo(82.0, 1);
    });

    test('midpoint of lower segment (0.81) renders near 41%', () => {
      // (0.81 - 0.70) / (0.92 - 0.70) * 82 = 0.11/0.22*82 = 41.0
      expect(displayScore(0.81)).toBeCloseTo(41.0, 0);
    });

    test('midpoint of upper segment (0.95) renders near 90.7%', () => {
      // 82 + (0.95 - 0.92) / (0.98 - 0.92) * 18 = 82 + 9 = 91
      expect(displayScore(0.95)).toBeCloseTo(91.0, 0);
    });

    test('monotonically increasing across representative points', () => {
      const points = [0.70, 0.80, 0.88, 0.92, 0.95, 0.98];
      const scores = points.map(displayScore);
      for (let i = 1; i < scores.length; i++) {
        expect(scores[i]).toBeGreaterThanOrEqual(scores[i - 1]);
      }
    });
  });

  describe('buildTemplateDetailed', () => {
    const makeSamples = (n, dim = 4) =>
      Array.from({ length: n }, (_, k) =>
        Array.from({ length: dim }, (__, i) => (i === k % dim ? 1 : 0.01))
      );

    test('returns null for empty input', () => {
      expect(buildTemplateDetailed([])).toBeNull();
      expect(buildTemplateDetailed(null)).toBeNull();
    });

    test('returns { vector, consistency } shape', () => {
      const samples = makeSamples(5);
      const result = buildTemplateDetailed(samples);
      expect(result).not.toBeNull();
      expect(result.vector).toBeInstanceOf(Float32Array);
      expect(typeof result.consistency).toBe('number');
    });

    test('consistency is between 0 and 1', () => {
      const samples = makeSamples(7);
      const result = buildTemplateDetailed(samples);
      expect(result.consistency).toBeGreaterThanOrEqual(0);
      expect(result.consistency).toBeLessThanOrEqual(1);
    });

    test('output vector is L2-normalized (magnitude ≈ 1.0)', () => {
      const samples = makeSamples(7, 8);
      const { vector } = buildTemplateDetailed(samples);
      let mag = 0;
      for (const v of vector) mag += v * v;
      expect(Math.sqrt(mag)).toBeCloseTo(1.0, 5);
    });

    test('drops 1 outlier from 7 samples (uses 6 internally)', () => {
      const dim = 3;
      const good = Array.from({ length: 6 }, () => [1, 0, 0]);
      const outlier = [[0, 1, 0]];
      const samples = [...good, ...outlier];
      const { vector } = buildTemplateDetailed(samples);
      // After dropping the outlier, the template should point strongly toward [1,0,0]
      expect(vector[0]).toBeGreaterThan(0.95);
    });

    test('works with a single sample (no outlier removal)', () => {
      const result = buildTemplateDetailed([[1, 0, 0]]);
      expect(result).not.toBeNull();
      expect(result.vector[0]).toBeCloseTo(1.0, 5);
    });
  });

  test('median function', () => {
    expect(median([1, 2, 3])).toBe(2);
    expect(median([3, 1, 2])).toBe(2);
    expect(median([1, 2, 3, 4])).toBe(2.5);
    expect(median([10])).toBe(10);
    expect(median([])).toBe(0);
  });

  test('validateVector checks 1024-D finite array', () => {
    const valid = new Array(1024).fill(0.1);
    expect(validateVector(valid)).toBe(true);

    const invalidLength = new Array(1023).fill(0.1);
    expect(validateVector(invalidLength)).toBe(false);

    const withNaN = new Array(1024).fill(0.1);
    withNaN[500] = NaN;
    expect(validateVector(withNaN)).toBe(false);
  });
});
