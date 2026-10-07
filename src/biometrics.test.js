import { expect, test, describe } from 'vitest';
import { l2norm, cosine, buildTemplate, validateVector, displayScore, median } from './biometrics';

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

  test('displayScore matches required calibrations', () => {
    // Requirements from FASE 2:
    // displayScore(0.951) ≈ 82.0
    // displayScore(0.92) ≈ 64.8
    expect(displayScore(0.951)).toBeCloseTo(82.0, 0);
    expect(displayScore(0.92)).toBeCloseTo(64.8, 0);
    
    // Bounds check
    expect(displayScore(0.60)).toBe(0); // below floor
    expect(displayScore(0.99)).toBe(100); // above ceil
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
