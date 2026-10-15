import { describe, it, expect } from 'vitest';
import {
  BIOMETRIC_CONFIG,
  medianVectorPerDimension,
  createEnrollmentCollector,
  selectEnrollmentFrames,
  l2norm,
  cosine
} from './biometrics';

function createDummyVector(seed = 1) {
  const arr = new Float32Array(1024);
  for (let i = 0; i < 1024; i++) {
    arr[i] = Math.sin((i + seed) * 0.05);
  }
  return l2norm(arr);
}

describe('Tugas 1: Multi-Pose Enrollment (v2 Resilient)', () => {
  describe('medianVectorPerDimension', () => {
    it('returns null for empty array', () => {
      expect(medianVectorPerDimension([])).toBeNull();
      expect(medianVectorPerDimension(null)).toBeNull();
    });

    it('returns normalized L2 vector (norm = 1.0)', () => {
      const v1 = createDummyVector(1);
      const v2 = createDummyVector(2);
      const v3 = createDummyVector(3);
      const res = medianVectorPerDimension([v1, v2, v3]);
      expect(res).not.toBeNull();
      expect(res.length).toBe(1024);

      let sq = 0;
      for (let i = 0; i < 1024; i++) sq += res[i] * res[i];
      expect(Math.sqrt(sq)).toBeCloseTo(1.0, 4);
    });

    it('filters out localized outlier spikes via dimension-wise median', () => {
      const v1 = [1, 2, 3];
      const v2 = [1, 2, 3];
      const v3 = [1, 999, 3]; // outlier in dimension 1
      const res = medianVectorPerDimension([v1, v2, v3]);
      const expectedNormed = l2norm([1, 2, 3]);
      expect(res[0]).toBeCloseTo(expectedNormed[0], 4);
      expect(res[1]).toBeCloseTo(expectedNormed[1], 4);
      expect(res[2]).toBeCloseTo(expectedNormed[2], 4);
    });
  });

  describe('selectEnrollmentFrames State Machine & Timing', () => {
    it('rejects frames with gap < 300ms', () => {
      const collector = createEnrollmentCollector();
      const baseVec = createDummyVector(10);
      const detection = {
        embedding: baseVec,
        score: 0.95,
        rotation: { angle: { yaw: 0.02, pitch: 0.02, roll: 0.01 } }
      };

      // Frame 1 at t = 1000
      const res1 = selectEnrollmentFrames(collector, detection, 1000);
      expect(res1.status).toBe('collecting');
      expect(collector.frames.length).toBe(1);

      // Frame 2 at t = 1200 (< 300ms gap) -> rejected
      const res2 = selectEnrollmentFrames(collector, detection, 1200);
      expect(res2.status).toBe('rejected');
      expect(res2.reason).toBe('too_close');
      expect(collector.frames.length).toBe(1);

      // Frame 3 at t = 1350 (>= 300ms gap) -> accepted
      const res3 = selectEnrollmentFrames(collector, detection, 1350);
      expect(res3.status).toBe('collecting');
      expect(collector.frames.length).toBe(2);
    });

    it('pauses when face is lost without resetting existing frames', () => {
      const collector = createEnrollmentCollector();
      const baseVec = createDummyVector(10);
      const detection = {
        embedding: baseVec,
        score: 0.95,
        rotation: { angle: { yaw: 0.02, pitch: 0.02, roll: 0.01 } }
      };

      // Frame 1 accepted
      selectEnrollmentFrames(collector, detection, 1000);
      expect(collector.frames.length).toBe(1);

      // Face lost (null detection)
      const pausedRes = selectEnrollmentFrames(collector, null, 1400);
      expect(pausedRes.status).toBe('paused');
      expect(pausedRes.reason).toBe('face_lost');
      expect(collector.isPaused).toBe(true);
      expect(collector.frames.length).toBe(1); // Not reset!

      // Face reappears after 2 seconds
      const resumeRes = selectEnrollmentFrames(collector, detection, 3400);
      expect(resumeRes.status).toBe('collecting');
      expect(collector.isPaused).toBe(false);
      expect(collector.frames.length).toBe(2);
    });

    it('reproduces and passes realistic 4 FPS stream without big turns (completes with template v1)', () => {
      const collector = createEnrollmentCollector('multi_pose');
      const baseVec = createDummyVector(50);

      let time = 1000;
      let lastRes = null;
      // Simulate realistic stream: only frontal yaw (0.02 - 0.08 rad), no turns >= 0.12 rad
      for (let i = 0; i < 8; i++) {
        const vec = new Float32Array(baseVec);
        vec[i % 50] += 0.01;
        const det = {
          embedding: l2norm(vec),
          score: 0.92,
          rotation: { angle: { yaw: 0.04, pitch: 0.02, roll: 0.01 } }
        };
        lastRes = selectEnrollmentFrames(collector, det, time);
        time += 350; // 350ms gap
      }

      // Must succeed without getting blocked by missing turn!
      expect(lastRes.status).toBe('complete');
      expect(lastRes.template_version).toBe(1); // Frontal template
      expect(lastRes.consistency).toBeGreaterThanOrEqual(0.90);
      expect(collector.frames.length).toBe(8);
    });

    it('accepts opportunistic turned frames (yaw 0.12 - 0.30 rad) and produces template v2', () => {
      const collector = createEnrollmentCollector('multi_pose');
      const baseVec = createDummyVector(80);

      let time = 1000;
      let lastRes = null;
      for (let i = 0; i < 8; i++) {
        const vec = new Float32Array(baseVec);
        vec[i % 50] += 0.01;
        // Frames 3 and 4 have turned yaw (~0.18 rad = ~10 deg)
        const isTurned = i === 3 || i === 4;
        const det = {
          embedding: l2norm(vec),
          score: 0.92,
          rotation: { angle: { yaw: isTurned ? 0.18 : 0.03, pitch: 0.02, roll: 0.01 } }
        };
        lastRes = selectEnrollmentFrames(collector, det, time);
        time += 350;
      }

      expect(lastRes.status).toBe('complete');
      expect(lastRes.template_version).toBe(2); // Multi-pose template
      expect(collector.turnedCount).toBe(2);
      expect(collector.frontalCount).toBe(6);
    });

    it('supports frontal_only mode completing in 6 frames', () => {
      const collector = createEnrollmentCollector('frontal_only');
      const baseVec = createDummyVector(30);

      let time = 1000;
      let lastRes = null;
      for (let i = 0; i < 6; i++) {
        const vec = new Float32Array(baseVec);
        vec[i % 50] += 0.01;
        const det = {
          embedding: l2norm(vec),
          score: 0.92,
          rotation: { angle: { yaw: 0.03, pitch: 0.02, roll: 0.01 } }
        };
        lastRes = selectEnrollmentFrames(collector, det, time);
        time += 350;
      }

      expect(lastRes.status).toBe('complete');
      expect(lastRes.template_version).toBe(1);
      expect(collector.frames.length).toBe(6);
    });

    it('rejects turned angles in frontal_only mode', () => {
      const collector = createEnrollmentCollector('frontal_only');
      const baseVec = createDummyVector(30);
      const turnedDet = {
        embedding: baseVec,
        score: 0.92,
        rotation: { angle: { yaw: 0.22, pitch: 0.02, roll: 0.01 } }
      };

      const res = selectEnrollmentFrames(collector, turnedDet, 1000);
      expect(res.status).toBe('rejected');
      expect(res.reason).toBe('angle_out_of_bounds');
      expect(collector.frames.length).toBe(0);
    });

    it('times out when active duration exceeds 12 seconds with specific message', () => {
      const collector = createEnrollmentCollector('multi_pose');
      const baseVec = createDummyVector(10);
      const det = {
        embedding: baseVec,
        score: 0.95,
        rotation: { angle: { yaw: 0.02, pitch: 0.02, roll: 0.01 } }
      };

      // Frame 1 at t = 1000
      selectEnrollmentFrames(collector, det, 1000);
      expect(collector.frames.length).toBe(1);

      // Frame 2 at t = 14000 (diff = 13000ms > 12000ms max)
      const res = selectEnrollmentFrames(collector, det, 14000);
      expect(res.status).toBe('timeout');
      expect(res.reason).toBe('insufficient_frames');
      expect(res.message).toContain('Waktu habis');
      expect(res.framesCount).toBe(1);
    });
  });
});
