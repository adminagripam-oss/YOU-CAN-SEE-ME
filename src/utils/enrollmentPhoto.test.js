import { describe, it, expect } from 'vitest';
import { computeCropRect } from './enrollmentPhoto';

describe('computeCropRect', () => {
  it('applies 25% margin correctly in center', () => {
    // 640x480 canvas, face box at (200, 150) with width 100, height 120
    // marginX = 25, marginY = 30
    // cropX = 200 - 25 = 175, cropY = 150 - 30 = 120
    // cropW = 100 + 50 = 150, cropH = 120 + 60 = 180
    const rect = computeCropRect([200, 150, 100, 120], 640, 480, 0.25);
    expect(rect).toEqual({
      x: 175,
      y: 120,
      width: 150,
      height: 180
    });
  });

  it('clamps correctly when face box touches top-left edges (landscape 640x480)', () => {
    // Face box at (10, 10) with width 100, height 100
    // rawX1 = 10 - 25 = -15 -> clamped to 0
    // rawY1 = 10 - 25 = -15 -> clamped to 0
    // rawX2 = 10 + 100 + 25 = 135
    // rawY2 = 10 + 100 + 25 = 135
    // cropW = 135 - 0 = 135, cropH = 135 - 0 = 135
    const rect = computeCropRect([10, 10, 100, 100], 640, 480, 0.25);
    expect(rect).toEqual({
      x: 0,
      y: 0,
      width: 135,
      height: 135
    });
  });

  it('clamps correctly when face box touches bottom-right edges (portrait 480x640)', () => {
    // Portrait canvas 480x640
    // Face box at (400, 560) with width 70, height 70
    // rawX1 = 400 - 17.5 = 382.5 -> 383
    // rawY1 = 560 - 17.5 = 542.5 -> 543
    // rawX2 = 400 + 70 + 17.5 = 487.5 -> clamped to 480
    // rawY2 = 560 + 70 + 17.5 = 647.5 -> clamped to 640
    const rect = computeCropRect([400, 560, 70, 70], 480, 640, 0.25);
    expect(rect.x).toBe(383);
    expect(rect.y).toBe(543);
    expect(rect.x + rect.width).toBeLessThanOrEqual(480);
    expect(rect.y + rect.height).toBeLessThanOrEqual(640);
  });

  it('handles box larger than canvas by clamping to full canvas dimensions', () => {
    const rect = computeCropRect([-50, -50, 800, 700], 640, 480, 0.25);
    expect(rect).toEqual({
      x: 0,
      y: 0,
      width: 640,
      height: 480
    });
  });

  it('supports object format { x, y, width, height }', () => {
    const rect = computeCropRect({ x: 100, y: 100, width: 80, height: 80 }, 640, 480, 0.25);
    expect(rect).toEqual({
      x: 80,
      y: 80,
      width: 120,
      height: 120
    });
  });

  it('returns zero rect for empty or zero canvas', () => {
    expect(computeCropRect([10, 10, 50, 50], 0, 0)).toEqual({
      x: 0,
      y: 0,
      width: 0,
      height: 0
    });
  });
});
