import React, { useState, useEffect, useRef } from 'react';
import { API_BASE_URL } from '../config';
import { db, getCachedUserMasterVector, cacheUserMasterVector, cacheGeometricVector, queueOfflineAttendance } from '../db';
import { supabase } from '../supabaseClient';

// ═══════════════════════════════════════════════════════════════════════════
// GEOMETRIC FEATURE ENGINE  —  3D Facial Mesh + Coordinate Distance Matching
// ═══════════════════════════════════════════════════════════════════════════

/** Euclidean distance between two {x,y} points */
const pd = (a, b) => Math.sqrt((a.x - b.x) ** 2 + (a.y - b.y) ** 2);

/** Centroid of an array of {x,y} points */
const cen = (arr) => ({
  x: arr.reduce((s, p) => s + p.x, 0) / arr.length,
  y: arr.reduce((s, p) => s + p.y, 0) / arr.length,
});

/**
 * Extract 40-d Geometric Feature Vector (GFV) from 68 face-api landmark points.
 * All distances are normalized by Inter-Pupillary Distance (IPD) → scale invariant.
 *
 * Landmark index reference (68-point model):
 *   0-16: jaw  |  17-21: L brow  |  22-26: R brow
 *   27-30: nose bridge  |  31-35: nose bottom
 *   36-41: L eye  |  42-47: R eye
 *   48-59: outer mouth  |  60-67: inner mouth
 */
function extractGFV(landmarks) {
  try {
    const pts = landmarks.positions;
    if (!pts || pts.length < 68) return null;

    const lEye  = cen(pts.slice(36, 42));
    const rEye  = cen(pts.slice(42, 48));
    const lBrow = cen(pts.slice(17, 22));
    const rBrow = cen(pts.slice(22, 27));
    const midEye = cen([lEye, rEye]);
    const ipd    = pd(lEye, rEye);
    if (ipd < 5) return null;                    // face too small / too far

    const n  = (v) => parseFloat((v / ipd).toFixed(6));   // normalize by IPD
    const ns = (v) => parseFloat(v.toFixed(6));            // already ratio

    const noseTip = pts[30];
    const noseBtm = pts[33];
    const chin    = pts[8];

    return [
      // ── Eyes (0–5) ──────────────────────────────────────────────────────
      n(pd(pts[36], pts[39])),   // 0  L eye width
      n(pd(pts[42], pts[45])),   // 1  R eye width
      n(pd(pts[37], pts[41])),   // 2  L eye height (outer)
      n(pd(pts[43], pts[47])),   // 3  R eye height (outer)
      n(pd(pts[38], pts[40])),   // 4  L eye height (inner)
      n(pd(pts[44], pts[46])),   // 5  R eye height (inner)
      // ── Eyebrows (6–9) ──────────────────────────────────────────────────
      n(pd(pts[17], pts[21])),   // 6  L eyebrow width
      n(pd(pts[22], pts[26])),   // 7  R eyebrow width
      n(pd(lBrow, lEye)),        // 8  L eyebrow-to-eye gap
      n(pd(rBrow, rEye)),        // 9  R eyebrow-to-eye gap
      // ── Nose (10–13) ────────────────────────────────────────────────────
      n(pd(pts[31], pts[35])),   // 10 nose width
      n(pd(pts[27], pts[30])),   // 11 nose bridge length
      n(pd(noseTip, noseBtm)),   // 12 nose tip → nose bottom center
      n(pd(noseTip, chin)),      // 13 nose tip → chin
      // ── Mouth (14–17) ───────────────────────────────────────────────────
      n(pd(pts[48], pts[54])),   // 14 mouth width
      n(pd(pts[51], pts[57])),   // 15 mouth height
      n(pd(pts[51], pts[62])),   // 16 upper lip thickness
      n(pd(pts[57], pts[66])),   // 17 lower lip thickness
      // ── Inter-feature coordinate distances (18–22) ───────────────────────
      n(pd(lEye, noseTip)),      // 18 L eye → nose tip
      n(pd(rEye, noseTip)),      // 19 R eye → nose tip
      n(pd(midEye, pts[51])),    // 20 eye midpoint → upper lip
      n(pd(noseTip, pts[51])),   // 21 nose tip → upper lip
      n(pd(midEye, chin)),       // 22 eye midpoint → chin
      // ── Jaw segment distances (23–31) ───────────────────────────────────
      n(pd(pts[0],  pts[16])),   // 23 face width (jaw corners)
      n(pd(pts[4],  pts[12])),   // 24 cheek width
      n(pd(pts[2],  pts[14])),   // 25 jaw intermediate width
      n(pd(pts[0],  pts[8])),    // 26 L jaw height
      n(pd(pts[16], pts[8])),    // 27 R jaw height
      n(pd(pts[0],  pts[4])),    // 28 L jaw segment
      n(pd(pts[4],  pts[8])),    // 29 L-mid jaw segment
      n(pd(pts[8],  pts[12])),   // 30 R-mid jaw segment
      n(pd(pts[12], pts[16])),   // 31 R jaw segment
      // ── Symmetry offsets / signed (32–35) ───────────────────────────────
      ns((lEye.x    - midEye.x) / ipd),  // 32 L eye horiz offset
      ns((rEye.x    - midEye.x) / ipd),  // 33 R eye horiz offset
      ns((noseTip.x - midEye.x) / ipd),  // 34 nose horiz offset
      ns((pts[57].x - midEye.x) / ipd),  // 35 mouth horiz offset
      // ── Scale-invariant facial ratios (36–39) ───────────────────────────
      ns(pd(midEye, chin)        / pd(pts[0], pts[16])),    // 36 face aspect ratio
      ns(pd(pts[27], noseTip)    / pd(noseTip, chin)),       // 37 upper/lower face split
      ns(pd(pts[48], pts[54])    / pd(pts[0], pts[16])),     // 38 mouth / face-width ratio
      ns(pd(pts[17], pts[26])    / pd(pts[0], pts[16])),     // 39 brow span / face-width ratio
    ];
  } catch {
    return null;
  }
}

/**
 * Weighted Euclidean distance between two GFVs.
 * Eye / jaw / ratio features receive higher weights (most identity-distinctive).
 */
const GFV_WEIGHTS = [
  2.5, 2.5, 2.0, 2.0, 1.5, 1.5,   // eyes (0-5)
  1.5, 1.5, 1.2, 1.2,               // eyebrows (6-9)
  2.0, 2.0, 1.5, 1.8,               // nose (10-13)
  2.0, 1.8, 1.2, 1.2,               // mouth (14-17)
  1.8, 1.8, 1.5, 1.5, 1.5,          // inter-feature (18-22)
  2.2, 2.0, 1.8, 1.8, 1.8, 1.5, 1.5, 1.5, 1.5,  // jaw (23-31)
  0.8, 0.8, 0.8, 0.8,               // symmetry offsets (32-35) — pose-variable
  3.0, 2.5, 2.5, 2.0,               // ratios (36-39) — most pose-invariant
];

/**
 * COSINE SIMILARITY MATH FORMULA
 *
 * CosineSimilarity(A, B) = ( A • B ) / ( ||A|| * ||B|| )
 *
 * Measures angle similarity between MediaPipe 3D Landmark & GFV Feature Vectors.
 * Returns score in range [0.0, 1.0] (0% to 100%).
 * Threshold >= 0.85 (85%) for positive biometric verification.
 */
function cosineSimilarity(vecA, vecB) {
  if (!vecA || !vecB || vecA.length !== vecB.length || vecA.length === 0) return 0;
  let dot = 0, normA = 0, normB = 0;
  for (let i = 0; i < vecA.length; i++) {
    const a = vecA[i], b = vecB[i];
    dot   += a * b;
    normA += a * a;
    normB += b * b;
  }
  if (normA === 0 || normB === 0) return 0;
  return Math.max(0, Math.min(1.0, dot / (Math.sqrt(normA) * Math.sqrt(normB))));
}

/** Cosine Similarity score [0..1] -> Match Percentage [0..100%] */
const cosineToMatchPct = (cosSim) => parseFloat((cosSim * 100).toFixed(1));

/** Weighted Euclidean distance fallback */
function geometricDistance(gfvA, gfvB) {
  if (!gfvA || !gfvB || gfvA.length !== gfvB.length) return 999;
  let sum = 0;
  for (let i = 0; i < gfvA.length; i++) {
    const diff = gfvA[i] - gfvB[i];
    sum += diff * diff;
  }
  return Math.sqrt(sum);
}

// ═══════════════════════════════════════════════════════════════════════════
// 3D TRIANGULATED MESH RENDERER
// ═══════════════════════════════════════════════════════════════════════════

/**
 * Complete Delaunay-style triangulation over the 68-point face model.
 * Covers: forehead, temples, cheeks, nose, eyes, eyebrows, mouth, chin, jaw.
 *
 * 68-point model layout:
 *   0-16 : jaw line  (0=L-ear → 8=chin → 16=R-ear)
 *   17-21: L eyebrow  |  22-26: R eyebrow
 *   27-30: nose bridge (27=top, 30=tip)
 *   31-35: nose base (31=L-wing, 33=tip-bottom, 35=R-wing)
 *   36-41: L eye  |  42-47: R eye
 *   48-59: outer mouth ring  |  60-67: inner mouth
 */
const FACE_TRIANGLES = [
  // ── FOREHEAD / TOP (eyebrow-to-eyebrow band) ─────────────────────────
  [17, 18, 27], [18, 19, 27], [19, 20, 27], [20, 21, 27],
  [21, 22, 27], [22, 23, 27], [23, 24, 27], [24, 25, 27], [25, 26, 27],

  // ── LEFT TEMPLE (jaw corner 0 → brow 17 → eye 36) ────────────────────
  [0, 17, 36], [0,  1, 36],

  // ── RIGHT TEMPLE (jaw corner 16 → brow 26 → eye 45) ─────────────────
  [16, 26, 45], [15, 16, 45],

  // ── LEFT EYEBROW → EYE BAND ───────────────────────────────────────────
  [17, 18, 36], [18, 37, 36], [18, 19, 37],
  [19, 38, 37], [19, 20, 38], [20, 39, 38],
  [20, 21, 39],

  // ── RIGHT EYEBROW → EYE BAND ──────────────────────────────────────────
  [26, 25, 45], [25, 44, 45], [25, 24, 44],
  [24, 43, 44], [24, 23, 43], [23, 42, 43],
  [22, 23, 42],

  // ── LEFT EYE INTERIOR ─────────────────────────────────────────────────
  [36, 37, 41], [37, 38, 40], [38, 39, 40], [39, 40, 41],

  // ── RIGHT EYE INTERIOR ────────────────────────────────────────────────
  [42, 43, 47], [43, 44, 46], [44, 45, 46], [45, 46, 47],

  // ── GLABELLA (between eyes + nose bridge top) ─────────────────────────
  [21, 27, 39], [22, 27, 42], [21, 22, 27],
  [39, 27, 40], [42, 27, 43],

  // ── NOSE BRIDGE (eye level → nose tip) ────────────────────────────────
  [27, 28, 39], [28, 29, 39], [29, 30, 31],
  [27, 28, 42], [28, 29, 42], [29, 30, 35],
  [28, 39, 42],

  // ── NOSE TIP + ALA ────────────────────────────────────────────────────
  [30, 31, 32], [30, 32, 33], [30, 33, 34], [30, 34, 35],

  // ── LEFT CHEEK UPPER (eye → nose bridge → jaw) ────────────────────────
  [1,  2, 36], [2,  3, 41], [3,  4, 41],
  [36, 41, 31], [41, 40, 31],
  [2, 36, 41],

  // ── RIGHT CHEEK UPPER (eye → nose bridge → jaw) ───────────────────────
  [14, 15, 45], [13, 14, 46], [12, 13, 46],
  [45, 46, 35], [46, 47, 35],
  [14, 45, 46],

  // ── LEFT CHEEK LOWER (jaw → nose ala → mouth corner) ─────────────────
  [3,  4, 31], [4,  5, 31], [5,  6, 31],
  [6,  7, 48], [5, 48, 31],
  [31, 40, 41], [40, 48, 41],

  // ── RIGHT CHEEK LOWER (jaw → nose ala → mouth corner) ────────────────
  [12, 11, 35], [11, 10, 35], [10,  9, 35],
  [9,  8, 54], [10, 54, 35],
  [35, 46, 47], [46, 54, 47],

  // ── UPPER LIP REGION (nose → mouth) ───────────────────────────────────
  [31, 48, 49], [31, 49, 50], [31, 50, 51],
  [35, 54, 53], [35, 53, 52], [35, 52, 51],
  [30, 31, 51], [30, 35, 51],
  [30, 33, 51],

  // ── OUTER MOUTH RING ──────────────────────────────────────────────────
  [48, 49, 60], [49, 50, 61], [50, 51, 62],
  [51, 52, 63], [52, 53, 64], [53, 54, 65],
  [54, 55, 65], [55, 56, 66], [56, 57, 67],
  [57, 58, 67], [58, 59, 60], [59, 48, 60],

  // ── INNER MOUTH ───────────────────────────────────────────────────────
  [60, 61, 62], [60, 62, 63], [60, 63, 64],
  [60, 64, 65], [60, 65, 66], [60, 66, 67],

  // ── LOWER FACE LEFT (mouth corner → jaw → chin) ───────────────────────
  [6,  7, 59], [7,  8, 58], [8,  57, 58],
  [6, 48, 59], [48, 59, 6],

  // ── LOWER FACE RIGHT (mouth corner → jaw → chin) ─────────────────────
  [9, 10, 55], [9, 56, 55], [8,  9, 56],
  [9, 54, 10], [54, 10, 11],

  // ── CHIN FILL ─────────────────────────────────────────────────────────
  [7, 8, 57], [8, 9, 57],
  [57, 60, 59], [57, 67, 60],
  [56, 57, 67], [65, 66, 56], [55, 65, 56],
  [54, 65, 55], [11, 54, 55],
];

/**
 * Estimate simulated Z depth [0..1] for a landmark.
 * Ellipsoidal model: centre = near (Z=1), edges = far (Z=0).
 */
function estimateZ(pt, cx, cy, hw, hh) {
  const nx = (pt.x - cx) / (hw || 1);
  const ny = (pt.y - cy) / (hh || 1);
  return Math.sqrt(Math.max(0, 1 - nx * nx * 0.6 - ny * ny * 0.4));
}

/**
 * Draw scanner corner brackets (like a face-scan UI overlay).
 */
function drawScannerCorners(ctx, minX, minY, maxX, maxY, R, G, B, matched) {
  const pad = 10;
  const x0 = minX - pad, y0 = minY - pad;
  const x1 = maxX + pad, y1 = maxY + pad;
  const len = Math.min((x1 - x0), (y1 - y0)) * 0.14;
  const lw  = 2.5;
  const alpha = matched ? 0.95 : 0.75;

  ctx.save();
  ctx.strokeStyle = `rgba(${R},${G},${B},${alpha})`;
  ctx.lineWidth   = lw;
  ctx.lineCap     = 'square';

  const corners = [
    [x0, y0,  len,  0,  0,  len],   // top-left
    [x1, y0, -len,  0,  0,  len],   // top-right
    [x0, y1,  len,  0,  0, -len],   // bottom-left
    [x1, y1, -len,  0,  0, -len],   // bottom-right
  ];
  for (const [ox, oy, hx, hy, vx, vy] of corners) {
    ctx.beginPath(); ctx.moveTo(ox + hx, oy); ctx.lineTo(ox, oy); ctx.lineTo(ox, oy + vy); ctx.stroke();
  }
  ctx.restore();
}

/**
 * Full 3D Geometric Mesh Renderer.
 *
 * Matching the reference image:
 *  • Triangulated mesh conforming to all 68 landmark positions
 *  • Depth-shaded semi-transparent triangle fills
 *  • Wireframe edges with depth-brightness
 *  • Hollow circle nodes at each landmark
 *  • Glowing nose-tip anchor
 *  • Scanner corner brackets
 *  • Measurement dashed lines (IPD, NW, MW)
 *
 * @param {CanvasRenderingContext2D} ctx  Already translated+scaled for mirroring
 * @param {Array}   pts          68 {x,y} landmark points
 * @param {boolean} livenessDone
 * @param {boolean} matchDone
 */
/**
 * NODE-ONLY BIOMETRIC OVERLAY (Metode Node Tanpa Mesh)
 *
 * Visualizes 68 pure facial landmark nodes, glowing pupil anchors, mouth corners,
 * jaw contours, and measurement lines — strictly without filled mesh triangles.
 *
 * @param {CanvasRenderingContext2D} ctx  Already translated+scaled for mirroring
 * @param {Array}   pts          68 {x,y} landmark points
 * @param {boolean} livenessDone
 * @param {boolean} matchDone
 */
function drawGeometricMesh(ctx, pts, livenessDone, matchDone) {
  if (!pts || pts.length < 68) return;

  const xs = pts.map(p => p.x), ys = pts.map(p => p.y);
  const minX = Math.min(...xs), maxX = Math.max(...xs);
  const minY = Math.min(...ys), maxY = Math.max(...ys);

  const [R, G, B] = matchDone
    ? [16,  185, 129]   // ✓ matched — emerald green
    : livenessDone
      ? [59,  130, 246]  // liveness done — blue
      : [56,  189, 248]; // idle — sky blue

  // 1. Scanner Corner Brackets
  drawScannerCorners(ctx, minX, minY, maxX, maxY, R, G, B, matchDone);

  // 2. Facial Contour Lines (Subtle Connecting Lines)
  const drawContourPath = (indices, closed = false) => {
    ctx.beginPath();
    indices.forEach((idx, i) => {
      const p = pts[idx];
      if (!p) return;
      if (i === 0) ctx.moveTo(p.x, p.y);
      else ctx.lineTo(p.x, p.y);
    });
    if (closed) ctx.closePath();
    ctx.strokeStyle = `rgba(${R},${G},${B},0.35)`;
    ctx.lineWidth   = 0.9;
    ctx.stroke();
  };

  drawContourPath([0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16]); // Rahang
  drawContourPath([17,18,19,20,21]); // Alis Kiri
  drawContourPath([22,23,24,25,26]); // Alis Kanan
  drawContourPath([27,28,29,30]);    // Batang Hidung
  drawContourPath([31,32,33,34,35], true); // Bawah Hidung
  drawContourPath([36,37,38,39,40,41], true); // Mata Kiri
  drawContourPath([42,43,44,45,46,47], true); // Mata Kanan
  drawContourPath([48,49,50,51,52,53,54,55,56,57,58,59], true); // Bibir Luar

  // 3. Clean Single Facial Landmark Nodes (Dots)
  for (let i = 0; i < 68; i++) {
    const p = pts[i];
    if (!p) continue;
    ctx.beginPath();
    ctx.arc(p.x, p.y, 1.8, 0, 2 * Math.PI);
    ctx.fillStyle = `rgba(${R}, ${G}, ${B}, 0.85)`;
    ctx.fill();
  }
}

/** Helper: Draw glowing iris circle + pupil dot for Bola Mata */
function _drawIrisPupil(ctx, pupilPt, R, G, B) {
  if (!pupilPt) return;
  ctx.save();
  // Iris ring
  ctx.beginPath();
  ctx.arc(pupilPt.x, pupilPt.y, 5.5, 0, 2 * Math.PI);
  ctx.strokeStyle = `rgba(${R},${G},${B},0.95)`;
  ctx.lineWidth   = 1.5;
  ctx.stroke();
  // Pupil center dot
  ctx.beginPath();
  ctx.arc(pupilPt.x, pupilPt.y, 2.2, 0, 2 * Math.PI);
  ctx.fillStyle = '#ffffff';
  ctx.fill();
  ctx.restore();
}

/** Helper: Draw crosshairs on Sudut Bibir */
function _drawCrosshair(ctx, x, y, R, G, B) {
  ctx.save();
  ctx.strokeStyle = `rgba(${R},${G},${B},0.95)`;
  ctx.lineWidth   = 1.2;
  const sz = 4;
  ctx.beginPath();
  ctx.moveTo(x - sz, y); ctx.lineTo(x + sz, y);
  ctx.moveTo(x, y - sz); ctx.lineTo(x, y + sz);
  ctx.stroke();
  ctx.restore();
}

function _measureLine(ctx, a, b, label, R, G, B) {
  if (!a || !b) return;
  ctx.save();
  ctx.setLineDash([4, 4]);
  ctx.beginPath();
  ctx.moveTo(a.x, a.y); ctx.lineTo(b.x, b.y);
  ctx.strokeStyle = `rgba(${R},${G},${B},0.55)`;
  ctx.lineWidth   = 0.85;
  ctx.stroke();
  ctx.setLineDash([]);
  ctx.font        = 'bold 8px monospace';
  ctx.fillStyle   = `rgba(${R},${G},${B},0.90)`;
  ctx.shadowColor = 'rgba(0,0,0,0.8)';
  ctx.shadowBlur  = 3;
  ctx.fillText(label, (a.x + b.x) / 2 + 2, (a.y + b.y) / 2 - 4);
  ctx.restore();
}


// ═══════════════════════════════════════════════════════════════════════════
// HELPERS
// ═══════════════════════════════════════════════════════════════════════════

function formatDuration(secs) {
  const h = Math.floor(secs / 3600), m = Math.floor((secs % 3600) / 60), s = Math.floor(secs % 60);
  return `${String(h).padStart(2,'0')}:${String(m).padStart(2,'0')}:${String(s).padStart(2,'0')}`;
}
function fmtTime(ts) {
  if (!ts) return '—';
  return new Date(ts).toLocaleTimeString('id-ID', { hour: '2-digit', minute: '2-digit' }) + ' WIB';
}

function captureVideoFrameBase64(videoEl) {
  if (!videoEl || videoEl.readyState < 2) return null;
  try {
    const canvas = document.createElement('canvas');
    canvas.width  = videoEl.videoWidth  || 640;
    canvas.height = videoEl.videoHeight || 480;
    const ctx = canvas.getContext('2d');
    ctx.translate(canvas.width, 0);
    ctx.scale(-1, 1);
    ctx.drawImage(videoEl, 0, 0, canvas.width, canvas.height);
    return canvas.toDataURL('image/jpeg', 0.85);
  } catch (e) {
    console.warn('[FRAME CAPTURE ERROR]:', e);
    return null;
  }
}

// ═══════════════════════════════════════════════════════════════════════════
// COMPONENT — AgriFace Biometric Attendance with EAR Blink Liveness
// ═══════════════════════════════════════════════════════════════════════════

/**
 * EYE ASPECT RATIO (EAR) MATH FORMULA & MEDIA PIPE LANDMARK MAPPING
 *
 *  EAR = ( ||p2 - p6|| + ||p3 - p5|| ) / ( 2 * ||p1 - p4|| )
 *
 * MediaPipe 468/478 Eye Landmarks:
 *  - Left Eye:  p1=33  (outer corner), p4=133 (inner corner)
 *               p2=160 (upper1),       p6=144 (lower1)
 *               p3=158 (upper2),       p5=153 (lower2)
 *  - Right Eye: p1=263 (outer corner), p4=362 (inner corner)
 *               p2=385 (upper1),       p6=380 (lower1)
 *               p3=387 (upper2),       p5=373 (lower2)
 */

function calculateEAR(pts, eye) {
  if (!pts || pts.length < 68) return 0.3;
  const { p1, p4, p2, p6, p3, p5 } = eye;
  const pt1 = pts[p1], pt4 = pts[p4], pt2 = pts[p2], pt6 = pts[p6], pt3 = pts[p3], pt5 = pts[p5];
  if (!pt1 || !pt4 || !pt2 || !pt6 || !pt3 || !pt5) return 0.3;

  const d = (a, b) => Math.hypot(a.x - b.x, a.y - b.y);
  const vert1 = d(pt2, pt6);
  const vert2 = d(pt3, pt5);
  const horiz = d(pt1, pt4);

  if (horiz === 0) return 0.3;
  return (vert1 + vert2) / (2.0 * horiz);
}

const MP_LEFT_EYE  = { p1: 33,  p4: 133, p2: 160, p6: 144, p3: 158, p5: 153 };
const MP_RIGHT_EYE = { p1: 263, p4: 362, p2: 385, p6: 380, p3: 387, p5: 373 };

const FA_LEFT_EYE  = { p1: 36,  p4: 39,  p2: 37,  p6: 41,  p3: 38,  p5: 40 };
const FA_RIGHT_EYE = { p1: 42,  p4: 45,  p2: 43,  p6: 47,  p3: 44,  p5: 46 };

export default function TabFaceVerification({
  employees, modelsLoaded, modelStatusText, showToast, currentUser, onVerificationSuccess,
}) {
  const [selectedEmployeeId, setSelectedEmployeeId] = useState('');
  const [nikInput, setNikInput]             = useState('');
  const [selectedStatus, setSelectedStatus] = useState('Hadir');

  // Liveness States (Blink & Head Turn)
  const [isLiveHuman, setIsLiveHuman]           = useState(false);
  const [livenessStatusMsg, setLivenessStatusMsg] = useState('Harap posisikan wajah Anda di tengah layar');
  const [currentEAR, setCurrentEAR]             = useState(0.3);
  const [capturedBase64Image, setCapturedBase64Image] = useState(null);

  const [headTurnState, setHeadTurnState]       = useState({ left: false, right: false });
  const [livenessVerified, setLivenessVerified] = useState(false);

  // Geometric 1-to-1 Match States
  const [matchRate, setMatchRate] = useState(0);
  const [isMatched, setIsMatched] = useState(false);
  const [gfvMode, setGfvMode]     = useState(false);

  // Camera settings
  const [facingMode, setFacingMode] = useState('user'); // 'user' = depan, 'environment' = belakang

  // Attendance & Timer States
  const [attendanceStatus, setAttendanceStatus] = useState({
    checkedIn: false, checkInTime: null, checkOutTime: null, loaded: false,
  });
  const [elapsedSeconds, setElapsedSeconds] = useState(0);
  const [isSubmitting, setIsSubmitting]     = useState(false);
  const [lastResultMsg, setLastResultMsg]   = useState(null);

  // Refs
  const videoRef             = useRef(null);
  const canvasRef            = useRef(null);
  const currentDescRef       = useRef(null);
  const currentGFVRef        = useRef(null);
  const streamRef            = useRef(null);
  const headTurnRef          = useRef({ left: false, right: false });
  const livenessVerifiedRef  = useRef(false);
  const eyeClosedRef         = useRef(false); // for blink detection transition
  const masterGFVRef         = useRef(null);
  const masterVectorRef      = useRef(null);
  const matchRateRef         = useRef(0);
  const isMatchedRef         = useRef(false);
  const timerRef             = useRef(null);

  const selectedEmployee = employees.find((e) => String(e.id) === String(selectedEmployeeId));

  // Auto-select logged-in user & fetch live attendance status
  useEffect(() => {
    if (currentUser && employees.length > 0) {
      const empId = currentUser.employee_id || currentUser.id;
      const emp = employees.find(
        (e) => String(e.id) === String(empId) || String(e.nik) === String(currentUser.nik)
      );
      if (emp) {
        setSelectedEmployeeId(String(emp.id));
        setNikInput(emp.nik || '');
        fetchAttendanceStatus(emp.id);
        loadMasterVectors(emp.id);
      }
    }
  }, [currentUser, employees]);

  // Live work-duration timer
  useEffect(() => {
    if (timerRef.current) clearInterval(timerRef.current);
    if (attendanceStatus.checkedIn && attendanceStatus.checkInTime && !attendanceStatus.checkOutTime) {
      const upd = () => setElapsedSeconds(
        Math.max(0, Math.floor((Date.now() - new Date(attendanceStatus.checkInTime)) / 1000))
      );
      upd();
      timerRef.current = setInterval(upd, 1000);
    } else setElapsedSeconds(0);
    return () => { if (timerRef.current) clearInterval(timerRef.current); };
  }, [attendanceStatus.checkedIn, attendanceStatus.checkInTime, attendanceStatus.checkOutTime]);

  // Select employee
  const handleEmployeeSelect = async (e) => {
    const empId = e.target.value;
    setSelectedEmployeeId(empId);
    headTurnRef.current        = { left: false, right: false };
    livenessVerifiedRef.current = false;
    masterGFVRef.current       = null;
    masterVectorRef.current    = null;
    matchRateRef.current       = 0;
    isMatchedRef.current       = false;
    setHeadTurnState({ left: false, right: false });
    setLivenessVerified(false); setMatchRate(0); setIsMatched(false);
    setGfvMode(false); setLastResultMsg(null);

    const emp = employees.find((it) => String(it.id) === String(empId));
    if (emp) {
      setNikInput(emp.nik || '');
      await fetchAttendanceStatus(emp.id);
      await loadMasterVectors(emp.id);
    } else {
      setNikInput('');
      setAttendanceStatus({ checkedIn: false, checkInTime: null, checkOutTime: null, loaded: false });
    }
  };

  // Load stored GFV + 128-d for selected employee (4-Tier Fetch: IndexedDB -> Server API -> Supabase Direct -> Props)
  const loadMasterVectors = async (empId) => {
    if (!empId || empId === '' || empId === 'null' || empId === 'undefined') return;
    try {
      let gfv = null;
      let vec = null;

      // Tier 1: Local IndexedDB Cache
      const cached = await getCachedUserMasterVector(empId);
      if (cached) {
        gfv = cached.geometric_descriptor_json;
        vec = cached.descriptor_json;
      }

      // Tier 2: Fetch from Express Backend API Endpoint
      if (!gfv && !vec) {
        try {
          const res = await fetch(`${API_BASE_URL}/api/biometrics/master/${empId}`);
          if (res.ok) {
            const data = await res.json();
            if (data.success) {
              gfv = data.geometric_descriptor_json;
              vec = data.descriptor_json;
            }
          }
        } catch (e) {
          console.warn('[LOAD MASTER API WARN]:', e.message);
        }
      }

      // Tier 3: Fetch directly from Supabase Cloud Database
      if (!gfv && !vec) {
        try {
          const { data: masterData } = await supabase
            .from('master_descriptors')
            .select('descriptor_json')
            .eq('employee_id', empId)
            .maybeSingle();

          const { data: empData } = await supabase
            .from('employees')
            .select('descriptor_json, geometric_descriptor_json')
            .eq('id', empId)
            .maybeSingle();

          gfv = empData?.geometric_descriptor_json || null;
          vec = masterData?.descriptor_json || empData?.descriptor_json || null;
        } catch (e) {
          console.warn('[LOAD MASTER SUPABASE WARN]:', e.message);
        }
      }

      // Tier 4: Fallback to employees list from props
      if (!gfv && !vec) {
        const empObj = employees.find((it) => String(it.id) === String(empId));
        gfv = empObj?.geometric_descriptor_json;
        vec = empObj?.descriptor_json || empObj?.facial_descriptor;
      }

      // Parse & Store in Refs
      if (gfv) {
        if (typeof gfv === 'string') {
          try { gfv = JSON.parse(gfv); } catch {}
        }
        if (Array.isArray(gfv)) {
          masterGFVRef.current = gfv;
          setGfvMode(true);
        } else {
          masterGFVRef.current = null;
          setGfvMode(false);
        }
      } else {
        masterGFVRef.current = null;
        setGfvMode(false);
      }

      if (vec) {
        if (typeof vec === 'string') {
          try { vec = JSON.parse(vec); } catch {}
        }
        if (Array.isArray(vec) || vec instanceof Float32Array || typeof vec === 'object') {
          masterVectorRef.current = Array.from(Object.values(vec));
        } else {
          masterVectorRef.current = null;
        }
      } else {
        masterVectorRef.current = null;
      }

      // Auto-cache into IndexedDB if retrieved from Cloud/API
      if ((gfv || vec) && !cached) {
        const empObj = employees.find((it) => String(it.id) === String(empId));
        await cacheUserMasterVector({
          employee_id: empId,
          nik: empObj?.nik || '',
          name: empObj?.name || '',
          department: empObj?.department || '',
          descriptor_json: masterVectorRef.current,
          geometric_descriptor_json: masterGFVRef.current,
        });
      }

      console.log(`[LOAD MASTER VECTORS OK] Employee ${empId} | GFV: ${masterGFVRef.current ? masterGFVRef.current.length + '-d' : 'NO'} | 128-d: ${masterVectorRef.current ? 'YES' : 'NO'}`);
    } catch (err) {
      console.error('[LOAD MASTER VECTORS ERROR]:', err);
      masterGFVRef.current = null;
      masterVectorRef.current = null;
    }
  };

  // Fetch attendance status (Multi-Tier: Express API -> Direct Supabase -> Dexie.js Queue + State Guard)
  const fetchAttendanceStatus = async (empId) => {
    if (!empId || empId === '' || empId === 'null' || empId === 'undefined') return;
    let statusData = null;

    // 1. Tier 1: Express REST API
    try {
      const res = await fetch(`${API_BASE_URL}/api/attendance/status/${empId}`);
      if (res.ok) {
        const data = await res.json();
        if (data?.success) statusData = data;
      }
    } catch (err) {
      console.warn('[FETCH ATTENDANCE STATUS API WARN - FALLBACK TO SUPABASE/DEXIE]:', err.message);
    }

    // 2. Tier 2: Direct Supabase Cloud Query Fallback
    if (!statusData) {
      try {
        const { data: logs, error } = await supabase
          .from('attendance_logs')
          .select('*')
          .eq('employee_id', parseInt(empId))
          .order('timestamp', { ascending: false });

        if (!error && logs) {
          const now = new Date();
          const isSameDay = (d1, d2) => {
            if (!d1 || !d2) return false;
            const date1 = new Date(d1);
            const date2 = new Date(d2);
            return (
              date1.getFullYear() === date2.getFullYear() &&
              date1.getMonth() === date2.getMonth() &&
              date1.getDate() === date2.getDate()
            );
          };

          const todayLogs = logs.filter((l) => {
            const ts = l.timestamp || l.created_at;
            return ts && isSameDay(ts, now);
          });

          const successLogs = todayLogs.filter((l) => !l.status?.includes('GAGAL') && !l.status?.includes('REJECT'));
          const checkIns = successLogs.filter((l) => {
            const t = (l.attendance_type || '').toUpperCase();
            const loc = (l.location || '').toUpperCase();
            const st = (l.status || '').toUpperCase();
            const isOut = t.includes('CHECK_OUT') || t.includes('CHECK-OUT') || loc.includes('CHECK_OUT') || loc.includes('CHECK-OUT') || st.includes('CHECK_OUT') || st.includes('CHECK-OUT');
            const isIn = t.includes('CHECK_IN') || t.includes('CHECK-IN') || loc.includes('CHECK_IN') || loc.includes('CHECK-IN') || st.includes('HADIR') || st.includes('CHECK_IN') || st.includes('CHECK-IN');
            return isIn && !isOut;
          });

          const checkOuts = successLogs.filter((l) => {
            const t = (l.attendance_type || '').toUpperCase();
            const loc = (l.location || '').toUpperCase();
            const st = (l.status || '').toUpperCase();
            return (
              t.includes('CHECK_OUT') ||
              t.includes('CHECK-OUT') ||
              loc.includes('CHECK_OUT') ||
              loc.includes('CHECK-OUT') ||
              st.includes('CHECK_OUT') ||
              st.includes('CHECK-OUT')
            );
          });

          const lastCheckIn = checkIns[0] || null;
          const lastCheckOut = checkOuts[0] || null;
          const isCheckedIn =
            !!lastCheckIn &&
            (!lastCheckOut ||
              new Date(lastCheckIn.timestamp || lastCheckIn.created_at) >
                new Date(lastCheckOut.timestamp || lastCheckOut.created_at));

          statusData = {
            hasCheckedIn: !!lastCheckIn,
            hasCheckedOut: !!lastCheckOut,
            checked_in: isCheckedIn,
            check_in_time: lastCheckIn ? lastCheckIn.timestamp || lastCheckIn.created_at : null,
            check_out_time: lastCheckOut ? lastCheckOut.timestamp || lastCheckOut.created_at : null,
          };
        }
      } catch (err) {
        console.warn('[FETCH ATTENDANCE STATUS SUPABASE WARN]:', err.message);
      }
    }

    // Determine base statuses
    let hasCheckedIn = statusData?.hasCheckedIn ?? statusData?.checked_in ?? false;
    let hasCheckedOut = statusData?.hasCheckedOut ?? false;
    let checkInTime = statusData?.check_in_time || null;
    let checkOutTime = statusData?.check_out_time || null;

    // 3. Tier 3: Dexie.js (IndexedDB) Sync Queue Inspection (Offline Override Rule)
    try {
      const now = new Date();
      const isSameDay = (d1, d2) => {
        if (!d1 || !d2) return false;
        const date1 = new Date(d1);
        const date2 = new Date(d2);
        return (
          date1.getFullYear() === date2.getFullYear() &&
          date1.getMonth() === date2.getMonth() &&
          date1.getDate() === date2.getDate()
        );
      };

      const queuedLogs = await db.attendance_sync_queue.toArray();
      const localEmpLogs = queuedLogs.filter((item) => {
        const matchEmp = String(item.employee_id) === String(empId);
        const ts = item.timestamp || item.created_at;
        return matchEmp && ts && isSameDay(ts, now);
      });

      const localCheckIn = localEmpLogs.find((item) => {
        const type = (item.attendance_type || item.status || '').toUpperCase();
        return type.includes('CHECK_IN') || type.includes('CHECK-IN') || type.includes('HADIR');
      });

      const localCheckOut = localEmpLogs.find((item) => {
        const type = (item.attendance_type || item.status || '').toUpperCase();
        return type.includes('CHECK_OUT') || type.includes('CHECK-OUT');
      });

      if (localCheckIn) {
        hasCheckedIn = true;
        if (!checkInTime) checkInTime = localCheckIn.timestamp || localCheckIn.created_at;
      }

      if (localCheckOut) {
        hasCheckedOut = true;
        hasCheckedIn = true;
        if (!checkOutTime) checkOutTime = localCheckOut.timestamp || localCheckOut.created_at;
      }
    } catch (dexieErr) {
      console.warn('[DEXIE CHECK QUEUE WARN]:', dexieErr.message);
    }

    // 4. State Guard: Prevent state revert if state is already checkedIn
    setAttendanceStatus((prev) => {
      const isCurrentlyCheckedIn = prev.checkedIn && !prev.checkOutTime;
      const targetCheckedIn = isCurrentlyCheckedIn
        ? true
        : ((hasCheckedIn || prev.checkedIn) && !hasCheckedOut && !checkOutTime && !prev.checkOutTime);

      return {
        checkedIn: targetCheckedIn,
        checkInTime: checkInTime || prev.checkInTime,
        checkOutTime: checkOutTime || prev.checkOutTime,
        loaded: true,
      };
    });
  };

  // ── Camera + Geometric Detection Loop ──────────────────────────────────
  useEffect(() => {
    let intervalId = null;
    async function startCamera() {
      try {
        const stream = await navigator.mediaDevices.getUserMedia({
          video: { 
            width: { ideal: 640 }, 
            height: { ideal: 720 }, 
            facingMode: facingMode 
          }, 
          audio: false,
        });
        if (videoRef.current) { videoRef.current.srcObject = stream; streamRef.current = stream; }

        intervalId = setInterval(async () => {
          if (!videoRef.current || videoRef.current.readyState !== 4 || !window.faceapi || !modelsLoaded) return;

          const opts = window.faceapi.nets.tinyFaceDetector?.isLoaded
            ? new window.faceapi.TinyFaceDetectorOptions({ inputSize: 224, scoreThreshold: 0.5 })
            : new window.faceapi.SsdMobilenetv1Options({ minConfidence: 0.5 });

          const detection = await window.faceapi
            .detectSingleFace(videoRef.current, opts)
            .withFaceLandmarks()
            .withFaceDescriptor();

          const W = videoRef.current?.clientWidth  || 640;
          const H = videoRef.current?.clientHeight || 480;
          const ctx = canvasRef.current?.getContext('2d');
          if (!ctx) return;

          if (!detection) {
            currentDescRef.current = null; currentGFVRef.current = null;
            setLivenessStatusMsg('Harap posisikan wajah Anda di tengah layar');
            ctx.clearRect(0, 0, W, H);
            return;
          }

          // Store live descriptors
          currentDescRef.current = Array.from(detection.descriptor);
          const liveGFV = extractGFV(detection.landmarks);
          currentGFVRef.current = liveGFV;

          // ── EYE ASPECT RATIO (EAR) BLINK DETECTION (ANTI-SPOOFING) ─────────
          const landmarksPos = detection.landmarks.positions;
          const leftEAR  = calculateEAR(landmarksPos, FA_LEFT_EYE);
          const rightEAR = calculateEAR(landmarksPos, FA_RIGHT_EYE);
          const avgEAR   = (leftEAR + rightEAR) / 2.0;
          setCurrentEAR(parseFloat(avgEAR.toFixed(3)));

          if (!livenessVerifiedRef.current) {
            setLivenessStatusMsg('Silakan kedipkan mata Anda untuk verifikasi');

            // Blink transition: EAR < 0.21 (Closed) -> EAR > 0.24 (Reopened)
            if (avgEAR < 0.21) {
              eyeClosedRef.current = true;
            } else if (eyeClosedRef.current && avgEAR > 0.24) {
              // BLINK DETECTED & VERIFIED!
              eyeClosedRef.current = false;
              livenessVerifiedRef.current = true;
              setLivenessVerified(true);
              setIsLiveHuman(true);
              setLivenessStatusMsg('Liveness Terverifikasi!');

              const base64Str = captureVideoFrameBase64(videoRef.current);
              if (base64Str) setCapturedBase64Image(base64Str);
            }

            // Backup 3D Head Rotation (Toleh Kiri & Kanan)
            const jaw  = detection.landmarks.getJawOutline();
            const nose = detection.landmarks.getNose();
            if (jaw?.length >= 17 && nose?.length >= 4) {
              const dL = Math.abs(nose[3].x - jaw[0].x);
              const dR = Math.abs(jaw[16].x  - nose[3].x);
              const tw = dL + dR;
              if (tw > 0) {
                const yaw = (dL - dR) / tw;
                if (yaw >  0.20) headTurnRef.current.left  = true;
                if (yaw < -0.20) headTurnRef.current.right = true;
              }
            }
            const nL = headTurnRef.current.left, nR = headTurnRef.current.right;
            setHeadTurnState({ left: nL, right: nR });
            if (nL && nR && !livenessVerifiedRef.current) {
              livenessVerifiedRef.current = true;
              setLivenessVerified(true);
              setIsLiveHuman(true);
              setLivenessStatusMsg('Liveness Terverifikasi!');
              const base64Str = captureVideoFrameBase64(videoRef.current);
              if (base64Str) setCapturedBase64Image(base64Str);
            }
          }

          // ── Step 2: Geometric 1-to-1 Match via Cosine Similarity ───────────
          if (liveGFV || currentDescRef.current) {
            let pct = 0, matched = false;

            // Case A: 40-d GFV Cosine Match
            if (masterGFVRef.current && Array.isArray(masterGFVRef.current) && liveGFV && masterGFVRef.current.length === liveGFV.length) {
              const cosSim = cosineSimilarity(liveGFV, masterGFVRef.current);
              pct     = cosineToMatchPct(cosSim);
              matched = cosSim >= 0.85; // Match threshold 85% (0.85)
            }
            // Case B: 128-d Face-API Vector Cosine Match
            else if (masterVectorRef.current && currentDescRef.current && masterVectorRef.current.length === 128 && currentDescRef.current.length === 128) {
              const cosSim = cosineSimilarity(currentDescRef.current, masterVectorRef.current);
              pct     = cosineToMatchPct(cosSim);
              matched = cosSim >= 0.80; // Match threshold 80% for 128-d cosine similarity

              // Auto-cache GFV for future scans if 128-d match passes
              if (matched && liveGFV && !masterGFVRef.current && selectedEmployeeId) {
                masterGFVRef.current = liveGFV;
                setGfvMode(true);
                cacheGeometricVector(selectedEmployeeId, liveGFV).catch(() => {});
              }
            }

            matchRateRef.current = pct; isMatchedRef.current = matched;
            setMatchRate(pct); setIsMatched(matched);
          }

          // ── Draw Biometric Node Overlay ─────────────────────────────────
          window.faceapi.matchDimensions(canvasRef.current, { width: W, height: H });
          const resized = window.faceapi.resizeResults(detection, { width: W, height: H });

          ctx.clearRect(0, 0, W, H);

          // Draw landmark nodes directly.
          // CSS 'transform: scaleX(-1)' on canvas.overlay-canvas handles GPU video mirroring.
          drawGeometricMesh(
            ctx,
            resized.landmarks.positions,
            livenessVerifiedRef.current,
            isMatchedRef.current
          );

          // Match% label — Un-flip text so it reads normally under CSS scaleX(-1)
          const box    = resized.detection.box;
        }, 200);
      } catch (err) { console.error('[CAMERA ERROR]:', err); }
    }

    startCamera();
    return () => {
      if (intervalId) clearInterval(intervalId);
      if (streamRef.current) streamRef.current.getTracks().forEach(t => t.stop());
    };
  }, [modelsLoaded, selectedEmployeeId, facingMode]);

  // ── Submit Attendance (Transactional async/await) ──────────────────────
  const handleVerifySubmit = async (attendanceType = 'CHECK_IN') => {
    if (!selectedEmployeeId) {
      showToast('Pilih Karyawan', 'Harap pilih karyawan terlebih dahulu!', 'error');
      return;
    }

    const targetEmp = selectedEmployee || {
      id: selectedEmployeeId,
      nik: nikInput,
      name: currentUser?.name || 'Karyawan',
      department: currentUser?.department || 'Umum',
    };

    // 1. Hitung & Validasi Biometrik Euclidean Distance
    let euclideanDist = 0.1;
    if (attendanceType === 'CHECK_IN' && selectedStatus === 'Hadir') {
      if (!currentDescRef.current) {
        showToast('Deteksi Gagal', 'Wajah belum terdeteksi!', 'error');
        return;
      }
      if (!livenessVerifiedRef.current) {
        showToast('Liveness Ditolak', 'Harap lakukan verifikasi kedip mata!', 'error');
        return;
      }
      if (!isMatchedRef.current) {
        showToast('Wajah Tidak Cocok', `Skor ${matchRateRef.current.toFixed(1)}% — tidak sesuai data karyawan!`, 'error');
        return;
      }
      if (matchRateRef.current > 0) {
        euclideanDist = parseFloat((1.0 - (matchRateRef.current / 100)).toFixed(4));
      }
    }

    setIsSubmitting(true);
    setLastResultMsg(null);

    const typeLabel = selectedStatus !== 'Hadir'
      ? selectedStatus.toUpperCase()
      : (attendanceType === 'CHECK_OUT' ? 'CHECK-OUT' : 'CHECK-IN');

    let locationStr = 'HP Mobile', userLat = null, userLng = null;
    if (navigator.geolocation) {
      try {
        const pos = await new Promise((res, rej) =>
          navigator.geolocation.getCurrentPosition(res, rej, { timeout: 3000 })
        );
        userLat = pos.coords.latitude;
        userLng = pos.coords.longitude;
        locationStr = `GPS (${userLat.toFixed(4)}, ${userLng.toFixed(4)})`;
      } catch {
        locationStr = 'HP Mobile (GPS)';
      }
    }

    // 2. Lakukan Operasi Database (API / Supabase Direct / Dexie.js Offline)
    let isSuccess = false;
    let successMsg = '';
    let recordTimestamp = new Date().toISOString();

    if (navigator.onLine) {
      try {
        const payload = {
          employee_id: parseInt(selectedEmployeeId),
          nik: (targetEmp.nik || nikInput).trim() || null,
          scan_descriptor: currentDescRef.current || [],
          location: `${locationStr} - GeoMesh Scanner`,
          attendance_type: attendanceType,
          status: selectedStatus === 'Hadir' ? 'Hadir (Verified)' : selectedStatus,
        };

        const res = await fetch(`${API_BASE_URL}/api/attendance/verify`, {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(payload),
        });

        const data = await res.json();
        if (res.ok && data.success) {
          isSuccess = true;
          successMsg = data.message || `Absensi ${typeLabel} berhasil dicatat!`;
          recordTimestamp = data.timestamp || recordTimestamp;
        } else {
          throw new Error(data.message || `API Gagal mencatat ${typeLabel}`);
        }
      } catch (apiErr) {
        console.warn('[ONLINE API WARN – FALLBACK TO SUPABASE/DEXIE]:', apiErr.message);

        // Fallback A: Direct Supabase Cloud Insert
        try {
          const logPayload = {
            employee_id: parseInt(selectedEmployeeId),
            location: `${locationStr} - GeoMesh Scanner [Supabase Direct] [${attendanceType}]`,
            status: selectedStatus === 'Hadir' ? 'Hadir (Verified)' : selectedStatus,
            euclidean_distance: euclideanDist,
            timestamp: recordTimestamp,
          };
          const { error: sbErr } = await supabase.from('attendance_logs').insert(logPayload);
          if (!sbErr) {
            isSuccess = true;
            successMsg = `Absensi ${typeLabel} berhasil dicatat di Supabase Cloud!`;
          } else {
            throw new Error(sbErr.message);
          }
        } catch (sbEx) {
          console.warn('[SUPABASE DIRECT WARN – FALLBACK TO DEXIE QUEUE]:', sbEx.message);
        }
      }
    }

    // Fallback B: Offline Dexie.js Sync Queue
    if (!isSuccess) {
      try {
        await queueOfflineAttendance({
          employee_id: targetEmp.id,
          nik: targetEmp.nik,
          name: targetEmp.name,
          department: targetEmp.department,
          timestamp: recordTimestamp,
          location: `${locationStr} [OFFLINE DEXIE]`,
          lat: userLat,
          lng: userLng,
          status: selectedStatus === 'Hadir' ? 'Hadir (Verified) [OFFLINE]' : selectedStatus,
          attendance_type: attendanceType,
          euclidean_distance: euclideanDist,
        });

        isSuccess = true;
        successMsg = `Absensi ${typeLabel} berhasil disimpan di penyimpanan offline (Dexie.js)!`;
      } catch (dexieErr) {
        console.error('[DEXIE QUEUE ERROR]:', dexieErr);
      }
    }

    // 3. JANGAN update Toast/State sebelum Database Benar-Benar Sukses
    if (isSuccess) {
      showToast('Absensi Berhasil', successMsg, 'success');

      setAttendanceStatus((prev) => ({
        checkedIn: attendanceType === 'CHECK_IN' ? true : (attendanceType === 'CHECK_OUT' ? false : prev.checkedIn),
        checkInTime: attendanceType === 'CHECK_IN' ? recordTimestamp : prev.checkInTime,
        checkOutTime: attendanceType === 'CHECK_OUT' ? recordTimestamp : prev.checkOutTime,
        loaded: true,
      }));

      setTimeout(async () => {
        await fetchAttendanceStatus(selectedEmployeeId);
      }, 300);

      if (onVerificationSuccess) onVerificationSuccess();
    } else {
      // Jika Catch Error: Tampilkan Toast Gagal & Biarkan Mode Tombol Tetap (State Tidak Berubah)
      showToast('Absensi Gagal', `Gagal memproses absensi ${typeLabel}. Silakan coba lagi.`, 'error');
    }

    setIsSubmitting(false);
  };

  // Computed helpers
  const isHadir    = selectedStatus === 'Hadir';
  const step1Done  = livenessVerified;
  const step2Done  = isMatched;
  const canCheckIn = !isHadir || (step1Done && step2Done && currentDescRef.current);

  const workDuration = (() => {
    if (attendanceStatus.checkInTime && attendanceStatus.checkOutTime) {
      const s = Math.floor((new Date(attendanceStatus.checkOutTime) - new Date(attendanceStatus.checkInTime)) / 1000);
      return s > 0 ? formatDuration(s) : '—';
    }
    return null;
  })();



  return (
    <div className="glass-card">
      <div className="card-title">
        <i className="fa-solid fa-camera"></i> Absensi Biometrik Wajah
      </div>

      <div className="grid-2">
        {/* ── LEFT PANEL ──────────────────────────────────────────── */}
        <div>
          {/* Employee Select */}
          <div className="form-group">
            <label htmlFor="verify-emp-select">Pilih Karyawan</label>
            <select
              id="verify-emp-select"
              value={selectedEmployeeId}
              onChange={handleEmployeeSelect}
            >
              <option value="">-- Pilih Karyawan Absen --</option>
              {employees.map((emp) => (
                <option key={emp.id} value={emp.id}>
                  {emp.nik} — {emp.name} ({emp.department})
                </option>
              ))}
            </select>
          </div>

          {/* Status Dropdown */}
          <div className="form-group">
            <label htmlFor="verify-status-select">Keterangan</label>
            <select
              id="verify-status-select"
              value={selectedStatus}
              onChange={(e) => setSelectedStatus(e.target.value)}
            >
              <option value="Hadir">🟢 Hadir (Verified Wajah)</option>
              <option value="Izin">🔵 Izin</option>
              <option value="Sakit">🟡 Sakit</option>
              <option value="Mangkir">🔴 Mangkir</option>
            </select>
          </div>

          {/* Live Camera */}
          <div className="form-group">
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '8px' }}>
              <label style={{ margin: 0 }}>Kamera Scanner</label>
              <button 
                type="button" 
                onClick={() => setFacingMode(prev => prev === 'user' ? 'environment' : 'user')}
                style={{ 
                  padding: '6px 14px', 
                  borderRadius: '20px', 
                  border: 'none', 
                  background: 'var(--accent-primary)', 
                  color: '#fff', 
                  fontSize: '0.8rem', 
                  fontWeight: 'bold',
                  cursor: 'pointer',
                  display: 'flex',
                  alignItems: 'center',
                  gap: '6px',
                  boxShadow: '0 2px 6px rgba(99,102,241,0.4)'
                }}
              >
                <i className="fa-solid fa-camera-rotate"></i> Ganti Kamera
              </button>
            </div>
            
            <div 
              className="webcam-wrapper" 
              style={{ 
                height: window.innerWidth < 768 ? '65vh' : 'auto', 
                aspectRatio: window.innerWidth < 768 ? '3/4' : '4/3' 
              }}
            >
              <video 
                ref={videoRef} 
                autoPlay 
                muted 
                playsInline
                style={{ transform: facingMode === 'user' ? 'scaleX(-1)' : 'scaleX(1)' }}
              ></video>
              <canvas 
                ref={canvasRef} 
                className="overlay-canvas"
                style={{ transform: facingMode === 'user' ? 'scaleX(-1)' : 'scaleX(1)' }}
              ></canvas>

              {/* Top Floating Badge Prompt */}
              {isHadir && (
                <div
                  style={{
                    position: 'absolute',
                    top: '10px',
                    left: '50%',
                    transform: 'translateX(-50%)',
                    zIndex: 10,
                    padding: '6px 16px',
                    borderRadius: '20px',
                    background: isLiveHuman && isMatched
                      ? 'rgba(16, 185, 129, 0.94)'
                      : isLiveHuman
                        ? 'rgba(59, 130, 246, 0.92)'
                        : 'rgba(15, 23, 42, 0.88)',
                    color: '#ffffff',
                    fontSize: '0.8rem',
                    fontWeight: 800,
                    pointerEvents: 'none',
                    whiteSpace: 'nowrap',
                    boxShadow: '0 4px 12px rgba(0,0,0,0.4)',
                    backdropFilter: 'blur(4px)',
                    display: 'flex',
                    alignItems: 'center',
                    gap: '6px',
                  }}
                >
                  {isLiveHuman && isMatched ? (
                    <>✓ SIAP ABSEN — {matchRate.toFixed(1)}% Match</>
                  ) : isLiveHuman ? (
                    <>✓ Liveness Terverifikasi! · Mencocokkan...</>
                  ) : (
                    <>{livenessStatusMsg}</>
                  )}
                </div>
              )}

              {!modelsLoaded && (
                <div className="loading-overlay">
                  <div className="spinner"></div>
                  <span>{modelStatusText}</span>
                </div>
              )}
            </div>
          </div>

          {/* ── Liveness & Match Status Chips ──────────────────────── */}
          {isHadir && (
            <div
              style={{
                marginBottom: '10px',
                padding: '8px 12px',
                borderRadius: '8px',
                background: 'var(--bg-primary)',
                border: '1px solid var(--border-color)',
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'space-between',
                gap: '8px',
                fontSize: '0.75rem',
                fontWeight: 700,
              }}
            >
              {/* Liveness & EAR chips */}
              <div style={{ display: 'flex', gap: '8px', alignItems: 'center', flexWrap: 'wrap' }}>
                <span>
                  🛡️ Anti-Spoofing:{' '}
                  <strong style={{ color: isLiveHuman ? '#15803d' : '#f59e0b' }}>
                    {isLiveHuman ? '✓ Human Live' : 'Kedip Mata'}
                  </strong>
                </span>
                <span style={{ color: 'var(--border-color)' }}>|</span>
                <span>
                  👁️ EAR:{' '}
                  <strong style={{ color: currentEAR < 0.21 ? '#dc2626' : '#15803d' }}>
                    {currentEAR} {currentEAR < 0.21 ? '(Kedip)' : '(Buka)'}
                  </strong>
                </span>
              </div>

              {/* Match rate */}
              <div
                style={{
                  fontWeight: 800,
                  color: isMatched ? '#15803d' : matchRate > 60 ? '#f59e0b' : 'var(--text-muted)',
                }}
              >
                🎯 {matchRate.toFixed(1)}% {isMatched ? '✓ COCOK' : 'Match'}
              </div>
            </div>
          )}

          {/* ── Captured Frame Thumbnail Preview (Base64) ──────────────── */}
          {capturedBase64Image && (
            <div
              style={{
                marginBottom: '10px',
                padding: '6px 10px',
                borderRadius: '8px',
                background: 'rgba(16, 185, 129, 0.08)',
                border: '1px solid rgba(16, 185, 129, 0.3)',
                display: 'flex',
                alignItems: 'center',
                gap: '10px',
              }}
            >
              <img
                src={capturedBase64Image}
                alt="Captured Live Frame"
                style={{
                  width: '44px',
                  height: '34px',
                  objectFit: 'cover',
                  borderRadius: '4px',
                  border: '1px solid #10b981',
                }}
              />
              <div style={{ flex: 1, fontSize: '0.72rem', color: '#15803d', fontWeight: 700 }}>
                📷 Frame Wajah Terverifikasi (Base64 Ready)
                <div style={{ fontSize: '0.65rem', fontWeight: 500, color: 'var(--text-muted)' }}>
                  Siap dikirim ke database Supabase backend
                </div>
              </div>
            </div>
          )}

          {/* ── ATTENDANCE BUTTON ──────────────────────────────────── */}
          <div style={{ marginTop: '8px', display: 'flex', gap: '8px', flexDirection: 'column' }}>
            {attendanceStatus.checkInTime && attendanceStatus.checkOutTime ? (
              /* ALREADY COMPLETED BOTH CHECK-IN & CHECK-OUT */
              <button
                type="button"
                className="btn"
                disabled
                style={{
                  padding: '13px',
                  fontSize: '0.9rem',
                  fontWeight: 800,
                  background: 'var(--bg-primary)',
                  color: 'var(--text-muted)',
                  border: '1px solid var(--border-color)',
                  borderRadius: '8px',
                  cursor: 'not-allowed',
                }}
              >
                <i className="fa-solid fa-lock"></i> Absensi Selesai Hari Ini
              </button>
            ) : (attendanceStatus.checkedIn || attendanceStatus.checkInTime) ? (
              /* CHECK OUT BUTTON — shown after successful check-in */
              <button
                type="button"
                className="btn"
                disabled={isSubmitting}
                onClick={() => handleVerifySubmit('CHECK_OUT')}
                style={{
                  padding: '13px',
                  fontSize: '0.9rem',
                  fontWeight: 800,
                  background: 'linear-gradient(135deg, #dc2626, #b91c1c)',
                  color: '#fff',
                  border: 'none',
                  borderRadius: '8px',
                  cursor: 'pointer',
                }}
              >
                {isSubmitting ? (
                  <><i className="fa-solid fa-spinner fa-spin"></i> Memproses...</>
                ) : (
                  <><i className="fa-solid fa-right-from-bracket"></i> LOG OUT (CHECK-OUT)</>
                )}
              </button>
            ) : (
              /* CHECK IN BUTTON */
              <button
                type="button"
                className="btn btn-primary"
                disabled={
                  isSubmitting ||
                  !selectedEmployeeId ||
                  (isHadir && (!canCheckIn || isSubmitting))
                }
                onClick={() => handleVerifySubmit('CHECK_IN')}
                style={{ padding: '13px', fontSize: '0.9rem', fontWeight: 800 }}
              >
                {isSubmitting ? (
                  <><i className="fa-solid fa-spinner fa-spin"></i> Memproses...</>
                ) : (
                  <><i className="fa-solid fa-circle-check"></i> PROSES ABSENSI (CHECK-IN)</>
                )}
              </button>
            )}
          </div>
        </div>

        {/* ── RIGHT PANEL ─────────────────────────────────────────────── */}
        <div>
          <div className="card-title" style={{ fontSize: '0.95rem' }}>
            <i className="fa-solid fa-user-check"></i> Status Absensi Karyawan
          </div>

          {/* Selected Employee Card */}
          {selectedEmployee ? (
            <div
              style={{
                marginBottom: '12px',
                padding: '12px',
                borderRadius: '8px',
                background: 'var(--bg-primary)',
                border: '1px solid var(--border-color)',
                display: 'flex',
                alignItems: 'center',
                gap: '12px',
              }}
            >
              <div
                style={{
                  width: '40px',
                  height: '40px',
                  borderRadius: '50%',
                  background: 'var(--accent-primary)',
                  color: '#fff',
                  display: 'flex',
                  alignItems: 'center',
                  justifyContent: 'center',
                  fontWeight: 800,
                  fontSize: '1rem',
                  flexShrink: 0,
                }}
              >
                {selectedEmployee.name?.charAt(0) || 'K'}
              </div>
              <div style={{ flex: 1 }}>
                <div style={{ fontSize: '0.92rem', fontWeight: 800, color: 'var(--text-main)' }}>
                  {selectedEmployee.name}
                </div>
                <div style={{ fontSize: '0.75rem', color: 'var(--text-muted)', fontWeight: 600 }}>
                  NIK: {selectedEmployee.nik} · {selectedEmployee.department}
                </div>
              </div>
              {isHadir && (
                <div
                  style={{
                    fontSize: '0.85rem',
                    fontWeight: 800,
                    color: isMatched ? '#15803d' : matchRate > 50 ? '#f59e0b' : 'var(--text-muted)',
                  }}
                >
                  {matchRate.toFixed(1)}%
                </div>
              )}
            </div>
          ) : (
            <div
              style={{
                marginBottom: '12px',
                padding: '12px',
                borderRadius: '6px',
                background: 'var(--bg-primary)',
                border: '1px dashed var(--border-color)',
                fontSize: '0.78rem',
                color: 'var(--text-muted)',
                fontWeight: 600,
              }}
            >
              Pilih karyawan di sebelah kiri untuk memulai absensi.
            </div>
          )}

          {/* ── ATTENDANCE LOG CARD ──────────────────────────────────── */}
          {attendanceStatus.loaded && selectedEmployee && (
            <div
              style={{
                padding: '14px',
                borderRadius: '10px',
                background: 'var(--bg-primary)',
                border: `1px solid ${attendanceStatus.checkedIn ? '#15803d' : 'var(--border-color)'}`,
                fontSize: '0.82rem',
                fontWeight: 600,
              }}
            >
              {/* Header */}
              <div
                style={{
                  display: 'flex',
                  justifyContent: 'space-between',
                  alignItems: 'center',
                  marginBottom: '10px',
                  paddingBottom: '8px',
                  borderBottom: '1px solid var(--border-color)',
                }}
              >
                <span style={{ fontWeight: 800, fontSize: '0.88rem', color: 'var(--text-main)' }}>
                  📋 Log Absensi Hari Ini
                </span>
                <span
                  style={{
                    padding: '3px 10px',
                    borderRadius: '12px',
                    fontWeight: 800,
                    fontSize: '0.73rem',
                    background: attendanceStatus.checkOutTime
                      ? 'rgba(107,114,128,0.15)'
                      : attendanceStatus.checkedIn
                        ? 'rgba(21,128,61,0.15)'
                        : 'rgba(234,179,8,0.15)',
                    color: attendanceStatus.checkOutTime
                      ? '#6b7280'
                      : attendanceStatus.checkedIn
                        ? '#15803d'
                        : '#a16207',
                  }}
                >
                  {attendanceStatus.checkOutTime
                    ? '✓ Selesai'
                    : attendanceStatus.checkedIn
                      ? '● Sedang Bekerja'
                      : '○ Belum Check-In'}
                </span>
              </div>

              {/* Check-In Row */}
              <div
                style={{
                  display: 'flex',
                  justifyContent: 'space-between',
                  alignItems: 'center',
                  marginBottom: '6px',
                }}
              >
                <span style={{ color: 'var(--text-muted)' }}>
                  <i className="fa-solid fa-arrow-right-to-bracket"></i> Check-In
                </span>
                <strong style={{ color: attendanceStatus.checkInTime ? '#15803d' : 'var(--text-muted)' }}>
                  {attendanceStatus.checkInTime ? fmtTime(attendanceStatus.checkInTime) : '—'}
                </strong>
              </div>

              {/* Check-Out Row */}
              <div
                style={{
                  display: 'flex',
                  justifyContent: 'space-between',
                  alignItems: 'center',
                  marginBottom: '8px',
                }}
              >
                <span style={{ color: 'var(--text-muted)' }}>
                  <i className="fa-solid fa-arrow-right-from-bracket"></i> Check-Out
                </span>
                <strong style={{ color: attendanceStatus.checkOutTime ? '#dc2626' : 'var(--text-muted)' }}>
                  {attendanceStatus.checkOutTime ? fmtTime(attendanceStatus.checkOutTime) : '—'}
                </strong>
              </div>

              {/* Duration Row */}
              <div
                style={{
                  display: 'flex',
                  justifyContent: 'space-between',
                  alignItems: 'center',
                  paddingTop: '8px',
                  borderTop: '1px solid var(--border-color)',
                }}
              >
                <span style={{ color: 'var(--text-muted)' }}>
                  <i className="fa-solid fa-clock"></i> Durasi Kerja
                </span>
                <strong
                  style={{
                    color: workDuration ? '#15803d' : attendanceStatus.checkedIn ? '#f59e0b' : 'var(--text-muted)',
                    fontFamily: 'monospace',
                    fontSize: '0.92rem',
                  }}
                >
                  {workDuration
                    ? workDuration
                    : attendanceStatus.checkedIn
                      ? formatDuration(elapsedSeconds)
                      : '—'}
                </strong>
              </div>
            </div>
          )}

          {/* ── VERIFICATION FLOW GUIDE ──────────────────────────────── */}
          {isHadir && selectedEmployee && !attendanceStatus.checkOutTime && (
            <div
              style={{
                marginTop: '12px',
                padding: '12px',
                borderRadius: '8px',
                background: 'var(--bg-primary)',
                border: '1px solid var(--border-color)',
                fontSize: '0.78rem',
              }}
            >
              <div style={{ fontWeight: 800, marginBottom: '8px', color: 'var(--text-main)' }}>
                Alur Verifikasi Biometrik
              </div>
              {[
                {
                  step: '1',
                  label: 'Pilih Karyawan',
                  done: !!selectedEmployee,
                  active: !selectedEmployee,
                },
                {
                  step: '2',
                  label: 'Deteksi Kehidupan 3D (Toleh Kiri & Kanan)',
                  done: livenessVerified,
                  active: !!selectedEmployee && !livenessVerified,
                },
                {
                  step: '3',
                  label: `Cocokkan Wajah (${matchRate.toFixed(1)}%)`,
                  done: isMatched,
                  active: livenessVerified && !isMatched,
                },
                {
                  step: '4',
                  label: attendanceStatus.checkedIn ? 'Status: Sedang Bekerja / Check-Out' : 'Proses Check-In',
                  done: !!attendanceStatus.checkOutTime || !!attendanceStatus.checkedIn,
                  active: livenessVerified && isMatched && !attendanceStatus.checkOutTime,
                },
              ].map(({ step, label, done, active }) => (
                <div
                  key={step}
                  style={{
                    display: 'flex',
                    alignItems: 'center',
                    gap: '8px',
                    marginBottom: '5px',
                    opacity: done ? 1 : active ? 1 : 0.45,
                  }}
                >
                  <div
                    style={{
                      width: '20px',
                      height: '20px',
                      borderRadius: '50%',
                      display: 'flex',
                      alignItems: 'center',
                      justifyContent: 'center',
                      fontWeight: 800,
                      fontSize: '0.7rem',
                      flexShrink: 0,
                      background: done ? '#15803d' : active ? 'var(--accent-primary)' : 'var(--border-color)',
                      color: '#fff',
                    }}
                  >
                    {done ? '✓' : step}
                  </div>
                  <span
                    style={{
                      fontWeight: done ? 700 : 600,
                      color: done ? '#15803d' : active ? 'var(--text-main)' : 'var(--text-muted)',
                    }}
                  >
                    {label}
                  </span>
                </div>
              ))}
            </div>
          )}
        </div>
      </div>
    </div>
  );
}
