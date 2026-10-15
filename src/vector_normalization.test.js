import { describe, it, expect } from 'vitest';
import { toVectorArray, vectorChecksum } from './biometrics';

describe('Tugas 1: Universal Vector Normalization (toVectorArray & vectorChecksum)', () => {
  const dummy1024 = Array.from({ length: 1024 }, (_, i) => Math.sin(i * 0.05));
  const f32Vector = new Float32Array(dummy1024);

  it('normalizes standard 1024-D JS Array', () => {
    const result = toVectorArray(dummy1024);
    expect(result).not.toBeNull();
    expect(result.length).toBe(1024);
    expect(Array.isArray(result)).toBe(true);
    expect(result[0]).toBeCloseTo(dummy1024[0]);
  });

  it('normalizes Float32Array into regular 1024-D Array', () => {
    const result = toVectorArray(f32Vector);
    expect(result).not.toBeNull();
    expect(result.length).toBe(1024);
    expect(Array.isArray(result)).toBe(true);
    expect(result[10]).toBeCloseTo(f32Vector[10]);
  });

  it('parses single-encoded JSON string correctly (as seen in Supabase 30 records)', () => {
    const jsonStr = JSON.stringify(dummy1024);
    const result = toVectorArray(jsonStr);
    expect(result).not.toBeNull();
    expect(result.length).toBe(1024);
    expect(result[5]).toBeCloseTo(dummy1024[5]);
  });

  it('parses double-encoded JSON string correctly', () => {
    const doubleEncoded = JSON.stringify(JSON.stringify(dummy1024));
    const result = toVectorArray(doubleEncoded);
    expect(result).not.toBeNull();
    expect(result.length).toBe(1024);
    expect(result[5]).toBeCloseTo(dummy1024[5]);
  });

  it('normalizes numeric-indexed object {"0": val, "1": val, ...}', () => {
    const objIndexed = {};
    for (let i = 0; i < 1024; i++) {
      objIndexed[String(i)] = dummy1024[i];
    }
    const result = toVectorArray(objIndexed);
    expect(result).not.toBeNull();
    expect(result.length).toBe(1024);
    expect(result[100]).toBeCloseTo(dummy1024[100]);
  });

  it('rejects vectors with wrong dimension (e.g. 512 or 1025) and returns null', () => {
    const shortVec = Array.from({ length: 512 }, () => 0.1);
    const longVec = Array.from({ length: 1025 }, () => 0.1);

    expect(toVectorArray(shortVec)).toBeNull();
    expect(toVectorArray(longVec)).toBeNull();
    expect(toVectorArray(JSON.stringify(shortVec))).toBeNull();
  });

  it('rejects vectors containing NaN or Infinity and returns null', () => {
    const nanVec = [...dummy1024];
    nanVec[50] = NaN;
    expect(toVectorArray(nanVec)).toBeNull();

    const infVec = [...dummy1024];
    infVec[100] = Infinity;
    expect(toVectorArray(infVec)).toBeNull();
  });

  it('rejects null, undefined, empty string, and boolean inputs', () => {
    expect(toVectorArray(null)).toBeNull();
    expect(toVectorArray(undefined)).toBeNull();
    expect(toVectorArray('')).toBeNull();
    expect(toVectorArray(true)).toBeNull();
  });

  it('produces identical vectorChecksum across Array, Float32Array, JSON string, double-encoded string, and numeric object', () => {
    const cRaw = vectorChecksum(dummy1024);
    const cF32 = vectorChecksum(f32Vector);
    const cJson = vectorChecksum(JSON.stringify(dummy1024));
    const cDouble = vectorChecksum(JSON.stringify(JSON.stringify(dummy1024)));

    const objIndexed = {};
    for (let i = 0; i < 1024; i++) {
      objIndexed[String(i)] = dummy1024[i];
    }
    const cObj = vectorChecksum(objIndexed);

    expect(cRaw).toBe(cF32);
    expect(cJson).toBe(cRaw);
    expect(cDouble).toBe(cRaw);
    expect(cObj).toBe(cRaw);
  });
});
