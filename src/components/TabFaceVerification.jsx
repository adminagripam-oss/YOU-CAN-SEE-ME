import React, { useState, useEffect, useRef, useCallback } from 'react';
import { useNormalizedFaceMesh } from '../hooks/useNormalizedFaceMesh';
import { API_BASE_URL, fetchWithTimeout } from '../config';

import { db, getCachedUserMasterVector, cacheUserMasterVector, cacheGeometricVector, queueOfflineAttendance } from '../db';
import { supabase } from '../supabaseClient';
import { human } from '../humanSingleton'; // Singleton — SATU instance Human untuk seluruh app
import { CheckCircle, Mail, Power, XCircle, ChevronDown, MapPin, Navigation } from 'lucide-react';


// ═══════════════════════════════════════════════════════════════════════════
// GEOFENCING CONFIGURATION (Dipindahkan dari server.js)
// ═══════════════════════════════════════════════════════════════════════════
const OFFICE_LAT = -6.200000;      // ← GANTI dengan Lintang kantor/afdeling Anda
const OFFICE_LON = 106.816600;     // ← GANTI dengan Bujur kantor/afdeling Anda
const MAX_RADIUS_METERS = 100000;  // Radius maksimal dalam meter (100km)

function calculateHaversineDistance(lat1, lon1, lat2, lon2) {
  const R = 6371000; // Radius bumi dalam meter
  const dLat = (lat2 - lat1) * Math.PI / 180;
  const dLon = (lon2 - lon1) * Math.PI / 180;
  const a =
    Math.sin(dLat / 2) * Math.sin(dLat / 2) +
    Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
    Math.sin(dLon / 2) * Math.sin(dLon / 2);
  const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
  return R * c; // Jarak dalam satuan meter
}

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

    const lEye = cen(pts.slice(36, 42));
    const rEye = cen(pts.slice(42, 48));
    const lBrow = cen(pts.slice(17, 22));
    const rBrow = cen(pts.slice(22, 27));
    const midEye = cen([lEye, rEye]);
    const ipd = pd(lEye, rEye);
    if (ipd < 5) return null;                    // face too small / too far

    const n = (v) => parseFloat((v / ipd).toFixed(6));   // normalize by IPD
    const ns = (v) => parseFloat(v.toFixed(6));            // already ratio

    const noseTip = pts[30];
    const noseBtm = pts[33];
    const chin = pts[8];

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
      n(pd(pts[0], pts[16])),   // 23 face width (jaw corners)
      n(pd(pts[4], pts[12])),   // 24 cheek width
      n(pd(pts[2], pts[14])),   // 25 jaw intermediate width
      n(pd(pts[0], pts[8])),    // 26 L jaw height
      n(pd(pts[16], pts[8])),    // 27 R jaw height
      n(pd(pts[0], pts[4])),    // 28 L jaw segment
      n(pd(pts[4], pts[8])),    // 29 L-mid jaw segment
      n(pd(pts[8], pts[12])),   // 30 R-mid jaw segment
      n(pd(pts[12], pts[16])),   // 31 R jaw segment
      // ── Symmetry offsets / signed (32–35) ───────────────────────────────
      ns((lEye.x - midEye.x) / ipd),  // 32 L eye horiz offset
      ns((rEye.x - midEye.x) / ipd),  // 33 R eye horiz offset
      ns((noseTip.x - midEye.x) / ipd),  // 34 nose horiz offset
      ns((pts[57].x - midEye.x) / ipd),  // 35 mouth horiz offset
      // ── Scale-invariant facial ratios (36–39) ───────────────────────────
      ns(pd(midEye, chin) / pd(pts[0], pts[16])),    // 36 face aspect ratio
      ns(pd(pts[27], noseTip) / pd(noseTip, chin)),       // 37 upper/lower face split
      ns(pd(pts[48], pts[54]) / pd(pts[0], pts[16])),     // 38 mouth / face-width ratio
      ns(pd(pts[17], pts[26]) / pd(pts[0], pts[16])),     // 39 brow span / face-width ratio
    ];
  } catch {
    return null;
  }
}
// ═══════════════════════════════════════════════════════════════════════════
// NEW HELPERS (Lighting & Audio)
// ═══════════════════════════════════════════════════════════════════════════
const offscreenCanvas = document.createElement('canvas');
offscreenCanvas.width = 32;
offscreenCanvas.height = 32;
const offscreenCtx = offscreenCanvas.getContext('2d', { willReadFrequently: true });

function checkLightingQuality(videoEl) {
  if (!videoEl || videoEl.readyState < 2 || videoEl.videoWidth === 0) return "";
  try {
    offscreenCtx.drawImage(videoEl, 0, 0, 32, 32);
    const imgData = offscreenCtx.getImageData(0, 0, 32, 32).data;
    let sum = 0;
    for (let i = 0; i < imgData.length; i += 4) {
      sum += (0.299 * imgData[i] + 0.587 * imgData[i + 1] + 0.114 * imgData[i + 2]);
    }
    const avg = sum / (32 * 32);
    if (avg < 40) return "Area Terlalu Gelap";
    if (avg > 240) return "Terlalu Silau / Backlight";
    return "";
  } catch (e) {
    return "";
  }
}

function playBeepSound() {
  try {
    const AudioContext = window.AudioContext || window.webkitAudioContext;
    if (!AudioContext) return;
    const ctx = new AudioContext();
    const osc = ctx.createOscillator();
    const gainNode = ctx.createGain();
    osc.type = 'sine';
    osc.frequency.setValueAtTime(880, ctx.currentTime);
    gainNode.gain.setValueAtTime(0.1, ctx.currentTime);
    osc.connect(gainNode);
    gainNode.connect(ctx.destination);
    osc.start();
    osc.stop(ctx.currentTime + 0.1);
  } catch (e) { }
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
    dot += a * b;
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
  [0, 17, 36], [0, 1, 36],

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
  [1, 2, 36], [2, 3, 41], [3, 4, 41],
  [36, 41, 31], [41, 40, 31],
  [2, 36, 41],

  // ── RIGHT CHEEK UPPER (eye → nose bridge → jaw) ───────────────────────
  [14, 15, 45], [13, 14, 46], [12, 13, 46],
  [45, 46, 35], [46, 47, 35],
  [14, 45, 46],

  // ── LEFT CHEEK LOWER (jaw → nose ala → mouth corner) ─────────────────
  [3, 4, 31], [4, 5, 31], [5, 6, 31],
  [6, 7, 48], [5, 48, 31],
  [31, 40, 41], [40, 48, 41],

  // ── RIGHT CHEEK LOWER (jaw → nose ala → mouth corner) ────────────────
  [12, 11, 35], [11, 10, 35], [10, 9, 35],
  [9, 8, 54], [10, 54, 35],
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
  [6, 7, 59], [7, 8, 58], [8, 57, 58],
  [6, 48, 59], [48, 59, 6],

  // ── LOWER FACE RIGHT (mouth corner → jaw → chin) ─────────────────────
  [9, 10, 55], [9, 56, 55], [8, 9, 56],
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
  const lw = 2.5;
  const alpha = matched ? 0.95 : 0.75;

  ctx.save();
  ctx.strokeStyle = `rgba(${R},${G},${B},${alpha})`;
  ctx.lineWidth = lw;
  ctx.lineCap = 'square';

  const corners = [
    [x0, y0, len, 0, 0, len],   // top-left
    [x1, y0, -len, 0, 0, len],   // top-right
    [x0, y1, len, 0, 0, -len],   // bottom-left
    [x1, y1, -len, 0, 0, -len],   // bottom-right
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
 * @param {number}  detectionScore
 */
function drawGeometricMesh(ctx, pts, livenessDone, detectionScore) {
  // Guard: pastikan poin tersedia
  if (!pts || pts.length < 50) return;

  const getX = (p) => (Array.isArray(p) ? (p[0] ?? 0) : (p?.x ?? 0));
  const getY = (p) => (Array.isArray(p) ? (p[1] ?? 0) : (p?.y ?? 0));

  const xs = pts.map(p => getX(p));
  const ys = pts.map(p => getY(p));
  const minX = Math.min(...xs), maxX = Math.max(...xs);
  const minY = Math.min(...ys), maxY = Math.max(...ys);

  let R, G, B;
  if (livenessDone) {
    [R, G, B] = [50, 255, 50];
  } else if (detectionScore >= 0.60) {
    [R, G, B] = [255, 200, 50];
  } else {
    [R, G, B] = [255, 50, 50];
  }

  if (!isNaN(minX) && !isNaN(minY) && !isNaN(maxX) && !isNaN(maxY)) {
    drawScannerCorners(ctx, minX, minY, maxX, maxY, R, G, B, livenessDone);
  }

  // Perbesar ukuran titik (radius dari 1.2 menjadi 2.5) dan buat lebih tegas (opacity 0.85)
  ctx.fillStyle = `rgba(${R}, ${G}, ${B}, 0.85)`;
  for (let i = 0; i < pts.length; i++) {
    const p = pts[i];
    if (!p) continue;
    const px = getX(p);
    const py = getY(p);
    ctx.beginPath();
    ctx.arc(px, py, 2.5, 0, 2 * Math.PI);
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
  ctx.lineWidth = 1.5;
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
  ctx.lineWidth = 1.2;
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
  ctx.lineWidth = 0.85;
  ctx.stroke();
  ctx.setLineDash([]);
  ctx.font = 'bold 8px monospace';
  ctx.fillStyle = `rgba(${R},${G},${B},0.90)`;
  ctx.shadowColor = 'rgba(0,0,0,0.8)';
  ctx.shadowBlur = 3;
  ctx.fillText(label, (a.x + b.x) / 2 + 2, (a.y + b.y) / 2 - 4);
  ctx.restore();
}


// ═══════════════════════════════════════════════════════════════════════════
// HELPERS
// ═══════════════════════════════════════════════════════════════════════════

function formatDuration(secs) {
  const h = Math.floor(secs / 3600), m = Math.floor((secs % 3600) / 60), s = Math.floor(secs % 60);
  return `${String(h).padStart(2, '0')}:${String(m).padStart(2, '0')}:${String(s).padStart(2, '0')}`;
}
function fmtTime(ts) {
  if (!ts) return '—';
  return new Date(ts).toLocaleTimeString('id-ID', { hour: '2-digit', minute: '2-digit' }) + ' WIB';
}

function captureVideoFrameBase64(videoEl) {
  if (!videoEl || videoEl.readyState < 2) return null;
  try {
    const canvas = document.createElement('canvas');
    canvas.width = videoEl.videoWidth || 640;
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

function calculateEAR(pts, eyePoints) {
  // Iris dinonaktifkan → mesh punya 468 poin (bukan 478). Guard disesuaikan.
  if (!pts || pts.length < 468) return 0.3;
  const [p1, p4, p2, p3, p6, p5] = eyePoints;
  const pt1 = pts[p1], pt4 = pts[p4], pt2 = pts[p2], pt6 = pts[p6], pt3 = pts[p3], pt5 = pts[p5];
  if (!pt1 || !pt4 || !pt2 || !pt6 || !pt3 || !pt5) return 0.3;

  const d = (a, b) => Math.hypot(a[0] - b[0], a[1] - b[1]);
  const vert1 = d(pt2, pt6);
  const vert2 = d(pt3, pt5);
  const horiz = d(pt1, pt4);

  if (horiz === 0) return 0.3;
  return (vert1 + vert2) / (2.0 * horiz);
}

const MP_LEFT_EYE = [33, 133, 160, 158, 144, 153];
const MP_RIGHT_EYE = [263, 362, 385, 387, 380, 373];

export default function TabFaceVerification({
  employees, modelsLoaded, modelStatusText, showToast, currentUser, onVerificationSuccess,
  gpsPermission = 'granted', liveCoords = null,
}) {
  const [selectedEmployeeId, setSelectedEmployeeId] = useState('');
  const [nikInput, setNikInput] = useState('');
  const [selectedStatus, setSelectedStatus] = useState('Hadir');
  const [isStatusDropdownOpen, setIsStatusDropdownOpen] = useState(false);

  // Liveness States (Blink & Head Turn)
  const [isLiveHuman, setIsLiveHuman] = useState(false);
  const [livenessStatusMsg, setLivenessStatusMsg] = useState('Harap posisikan wajah Anda di tengah layar');
  const [currentEAR, setCurrentEAR] = useState(0.3);
  const [capturedBase64Image, setCapturedBase64Image] = useState(null);

  const [headTurnState, setHeadTurnState] = useState({ left: false, right: false });
  const [livenessVerified, setLivenessVerified] = useState(false);
  const [livenessChallenge, setLivenessChallenge] = useState('');

  // Geometric 1-to-1 Match States
  const [matchRate, setMatchRate] = useState(0);
  const [isMatched, setIsMatched] = useState(false);
  const [gfvMode, setGfvMode] = useState(false);

  // Camera settings
  const [facingMode, setFacingMode] = useState('user'); // 'user' = depan, 'environment' = belakang
  const [lightingWarning, setLightingWarning] = useState('');

  // Attendance & Timer States
  const [attendanceStatus, setAttendanceStatus] = useState({
    checkedIn: false, checkInTime: null, checkOutTime: null, loaded: false,
  });
  const [elapsedSeconds, setElapsedSeconds] = useState(0);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [lastResultMsg, setLastResultMsg] = useState(null);

  // Refs
  const videoRef = useRef(null);
  const canvasRef = useRef(null);
  const currentDescRef = useRef(null);
  const currentGFVRef = useRef(null);
  const streamRef = useRef(null);
  const headTurnRef = useRef({ left: false, right: false });
  const livenessVerifiedRef = useRef(false);
  const eyeClosedRef = useRef(false); // for blink detection transition
  const masterGFVRef = useRef(null);
  const masterVectorRef = useRef(null);
  const matchRateRef = useRef(0);
  const isMatchedRef = useRef(false);
  const [isStable, setIsStable] = useState(false);
  const isStableRef = useRef(false);
  const timerRef = useRef(null);
  const hasBeepedRef = useRef(false);
  const selectedEmployeeIdRef = useRef('');
  const livenessChallengeRef = useRef('');
  const scoreHistoryRef = useRef([]);
  const [debugAiInfo, setDebugAiInfo] = useState({ backend: 'webgl', faces: 0, nodes: 0 });

  // ── Mobile Performance Optimization Refs ─────────────────────────────────
  // [TASK 3] Riwayat timestamp tiap frame untuk menghitung FPS aktual
  const frameTimesRef = useRef([]);
  // [TASK 3] Window adaptif saat ini (3 = device lambat, 5 = device cepat)
  const adaptiveWindowRef = useRef(5);
  // [TASK 5] Waktu mulai inferensi per-frame (dev instrumentation)
  const inferenceStartRef = useRef(0);
  // [TASK 5] Waktu inferensi terakhir dalam ms (untuk display di log)
  const lastInferenceTimeRef = useRef(0);
  // [TASK 5] Timestamp awal scan dimulai (untuk total time to matched)
  const matchStartTimeRef = useRef(0);
  // [TASK 5] Flag apakah timer match sudah dimulai
  const matchTimerStartedRef = useRef(false);
  // [TASK 5] Throttle ref untuk perf log (terpisah dari diagLogThrottleRef)
  const perfLogThrottleRef = useRef(0);

  // Auto-submit and stale closure prevention refs
  const hasAutoSubmittedRef = useRef(false);
  const selectedStatusRef = useRef(selectedStatus);
  const attendanceStatusRef = useRef(attendanceStatus);
  const handleVerifySubmitRef = useRef(null);

  useEffect(() => {
    selectedStatusRef.current = selectedStatus;
  }, [selectedStatus]);

  useEffect(() => {
    attendanceStatusRef.current = attendanceStatus;
  }, [attendanceStatus]);

  useEffect(() => {
    handleVerifySubmitRef.current = handleVerifySubmit;
  }); // runs on every render to capture latest handler closure

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
    selectedEmployeeIdRef.current = empId; // Simpan ke ref agar detection loop membaca versi terbaru tanpa restart

    // Pick random challenge
    const challenges = ['BLINK', 'TURN_LEFT', 'TURN_RIGHT'];
    const randomChallenge = challenges[Math.floor(Math.random() * challenges.length)];
    setLivenessChallenge(randomChallenge);
    livenessChallengeRef.current = randomChallenge;

    headTurnRef.current = { left: false, right: false };
    eyeClosedRef.current = false;
    livenessVerifiedRef.current = false;
    hasBeepedRef.current = false; // Reset beep state
    hasAutoSubmittedRef.current = false; // Reset auto-submit state
    masterGFVRef.current = null;
    masterVectorRef.current = null;
    matchRateRef.current = 0;
    isMatchedRef.current = false;
    scoreHistoryRef.current = [];
    // Reset mobile perf refs on employee switch
    frameTimesRef.current = [];
    adaptiveWindowRef.current = 5;
    matchTimerStartedRef.current = false;
    matchStartTimeRef.current = 0;
    lastDetectTimeRef.current = 0;
    lastDetectResultRef.current = null;
    isStableRef.current = false;
    setIsStable(false);
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

  // Load stored vector for selected employee (4-Tier Fetch: IndexedDB -> Server API -> Supabase Direct -> Props)
  const loadMasterVectors = async (empId) => {
    if (!empId || empId === '' || empId === 'null' || empId === 'undefined') return;

    // Pastikan empId selalu integer untuk kompatibilitas query Supabase (INTEGER column)
    const empIdInt = parseInt(empId, 10);
    if (isNaN(empIdInt)) {
      console.warn('[LOAD MASTER VECTORS] empId tidak valid:', empId);
      return;
    }

    try {
      let vec = null;

      // Helper to validate vector length
      const isValidVector = (v) => {
        if (!v) return false;
        let parsed = v;
        while (typeof parsed === 'string') {
          try { parsed = JSON.parse(parsed); } catch { break; }
        }
        return Array.isArray(parsed) && parsed.length === 1024;
      };

      // ── Tier 1: Local IndexedDB Cache ─────────────────────────────────
      const cached = await getCachedUserMasterVector(empIdInt);
      if (cached) {
        const tempVec = cached.descriptor_json || cached.face_vector;
        if (isValidVector(tempVec)) {
          vec = tempVec;
          console.log('[LOAD MASTER T1-INDEXEDDB] Cache hit valid 1024-dim, employee:', empIdInt);
        } else {
          console.warn('[LOAD MASTER T1-INDEXEDDB] Cache hit invalid (panjang bukan 1024), lanjut ke Supabase, employee:', empIdInt);
        }
      }

      // ── Tier 2: Supabase Direct — tabel master_descriptors ───────────
      if (!vec) {
        try {
          const { data: masterData, error: masterErr } = await supabase
            .from('master_descriptors')
            .select('descriptor_json')
            .eq('employee_id', empIdInt)
            .maybeSingle();

          if (masterErr) {
            console.warn('[LOAD MASTER T2-SUPABASE WARN]:', masterErr.message);
          } else if (masterData?.descriptor_json) {
            const tempVec = masterData.descriptor_json;
            if (isValidVector(tempVec)) {
              vec = tempVec;
              console.log('[LOAD MASTER T2-SUPABASE] Vektor valid ditemukan di master_descriptors');
            } else {
              console.warn('[LOAD MASTER T2-SUPABASE] Vektor di master_descriptors tidak valid (panjang bukan 1024)');
            }
          } else {
            console.warn('[LOAD MASTER T2-SUPABASE] Tidak ada data untuk employee_id:', empIdInt);
          }
        } catch (e) {
          console.warn('[LOAD MASTER T2-SUPABASE WARN]:', e.message);
        }
      }

      // ── Tier 4: Fallback ke props employees (kolom legacy) ────────────
      if (!vec) {
        const empObj = employees.find((it) => String(it.id) === String(empIdInt));
        const tempVec = empObj?.face_vector || empObj?.descriptor_json || empObj?.facial_descriptor;
        if (isValidVector(tempVec)) {
          vec = tempVec;
          console.log('[LOAD MASTER T4-PROPS] Vektor ditemukan valid di employees props');
        } else {
          console.warn('[LOAD MASTER T4-PROPS] Tidak ada vektor valid 1024-dim di props. Employee ID:', empIdInt);
        }
      }

      // Parse & Store in Refs
      masterGFVRef.current = null; // No longer used
      setGfvMode(false);

      if (vec) {
        let parsedVec = vec;

        // Recursive unwrap: tangani kasus double/triple-stringified dari DB
        // Contoh: '"[1,2,3]"' → '[1,2,3]' → [1,2,3]
        while (typeof parsedVec === 'string') {
          try {
            parsedVec = JSON.parse(parsedVec);
          } catch (e) {
            // Bukan JSON valid — hentikan loop, biarkan validasi di bawah menanganinya
            break;
          }
        }

        // Validasi tipe data ketat sebelum disimpan ke ref
        if (Array.isArray(parsedVec)) {
          // Kasus ideal: sudah berupa Array angka
          masterVectorRef.current = parsedVec;
        } else if (parsedVec instanceof Float32Array) {
          // Kasus Float32Array (format TensorFlow.js)
          masterVectorRef.current = Array.from(parsedVec);
        } else if (typeof parsedVec === 'object' && parsedVec !== null) {
          // Kasus object {0: val, 1: val, ...} (serialisasi non-array)
          masterVectorRef.current = Array.from(Object.values(parsedVec));
        } else {
          // Gagal parse — jangan isi ref dengan data rusak
          masterVectorRef.current = null;
          console.warn('[LOAD MASTER VECTORS] Vektor tidak valid setelah unwrap:', typeof parsedVec);
        }
      } else {
        masterVectorRef.current = null;
      }

      // Auto-cache into IndexedDB/SQLite if retrieved from Cloud/API
      if (vec && !cached) {
        const empObj = employees.find((it) => String(it.id) === String(empId));
        await cacheUserMasterVector({
          employee_id: empId,
          nik: empObj?.nik || '',
          name: empObj?.name || '',
          department: empObj?.department || '',
          afdeling: empObj?.afdeling || null,
          nama_kebun: empObj?.nama_kebun || null,
          status_tk: empObj?.status_tk || null,
          jabatan: empObj?.jabatan || null,
          status_perkawinan: empObj?.status_perkawinan || null,
          descriptor_json: masterVectorRef.current,
        });
      }

      console.log(`[LOAD MASTER VECTORS OK] Employee ${empId} | Vector: ${masterVectorRef.current ? masterVectorRef.current.length + '-dim' : 'NO'}`);
    } catch (err) {
      console.error('[LOAD MASTER VECTORS ERROR]:', err);
      masterVectorRef.current = null;
    }
  };

  // Fetch attendance status (100% Local Database Backed - SQLite on Android, IndexedDB on Web)
  const fetchAttendanceStatus = async (empId) => {
    if (!empId || empId === '' || empId === 'null' || empId === 'undefined') return;

    let hasCheckedIn = false;
    let hasCheckedOut = false;
    let checkInTime = null;
    let checkOutTime = null;

    try {
      const getLocalDateString = (d) => {
        const year = d.getFullYear();
        const month = String(d.getMonth() + 1).padStart(2, '0');
        const day = String(d.getDate()).padStart(2, '0');
        return `${year}-${month}-${day}`;
      };
      const todayStr = getLocalDateString(new Date());
      const todayLogs = await db.attendance_logs.getTodayLogs(empId, todayStr);

      const checkIns = todayLogs.filter(log => log.attendance_type === 'CHECK-IN');
      const checkOuts = todayLogs.filter(log => log.attendance_type === 'CHECK-OUT');

      const lastCheckIn = checkIns[checkIns.length - 1] || null;
      const lastCheckOut = checkOuts[checkOuts.length - 1] || null;

      hasCheckedIn = checkIns.length > 0;
      hasCheckedOut = checkOuts.length > 0;
      checkInTime = lastCheckIn ? lastCheckIn.timestamp : null;
      checkOutTime = lastCheckOut ? lastCheckOut.timestamp : null;

      console.log(`[Local Database] Loaded today status for employee ${empId}:`, {
        hasCheckedIn,
        hasCheckedOut,
        checkInTime,
        checkOutTime
      });
    } catch (err) {
      console.warn('[Local Database] Failed to fetch employee attendance status:', err);
    }

    setAttendanceStatus((prev) => {
      const isCurrentlyCheckedIn = prev.checkedIn && !prev.checkOutTime;
      const targetCheckedIn = isCurrentlyCheckedIn
        ? !hasCheckedOut
        : (hasCheckedIn && !hasCheckedOut);

      return {
        checkedIn: targetCheckedIn,
        checkInTime: checkInTime || prev.checkInTime,
        checkOutTime: checkOutTime || prev.checkOutTime,
        loaded: true,
      };
    });
  };

  // ── Camera + Geometric Detection Loop (via useNormalizedFaceMesh hook) ──
  //
  // Hook ini menggantikan startCamera() + detectionLoop() yang lama.
  // Tanggung jawab hook:
  //   • getUserMedia (constraint 640×480)
  //   • Center-Crop 4:3 ke offscreen canvas → eliminasi distorsi device
  //   • EMA Smoothing per-koordinat landmark (meredam jitter kamera)
  //   • Normalisasi Bounding Box → localX/localY/localZ (device-independent)
  // Tanggung jawab onFaceProcessed callback (di sini):
  //   • Lighting check, EAR liveness, cosine match, draw overlay

  // Throttle ref untuk diagnostic log (log max 1x per 3 detik, hindari spam)
  const diagLogThrottleRef = useRef(0);

  // ── Time-based Frame Throttling (mobile GPU optimization) ─────────────────
  // lastDetectTimeRef  : timestamp (ms) terakhir human.detect() benar-benar dijalankan
  // lastDetectResultRef: hasil deteksi wajah terakhir, dipakai sebagai "cache"
  //                       selama window throttle belum lewat, sehingga overlay
  //                       (node/border wajah) tidak pernah menerima null →
  //                       tidak ada kedipan/hilang, hanya update yang lebih jarang.
  const lastDetectTimeRef = useRef(0);
  const lastDetectResultRef = useRef(null);
  const DETECT_INTERVAL_MS = 150; // ~6-7 FPS, cukup untuk liveness & matching di HP/tablet

  // ── [TASK 1] Reusable downscale canvas ─────────────────────────────────────
  // Dibuat SEKALI (bukan tiap frame) lalu dipakai ulang via drawImage + clearRect.
  // document.createElement('canvas') per-frame sangat mahal (alokasi DOM + GPU
  // backing store tiap panggilan) dan jadi sumber GC pressure di HP/tablet.
  const downscaleCanvasRef = useRef(null);
  const downscaleCtxRef = useRef(null);
  const DOWNSCALE_WIDTH = 320;
  const DOWNSCALE_HEIGHT = 240;



  /**
   * Injected ke hook sebagai interface ke model AI (@vladmandic/human).
   * Menerima canvas 640×480 yang sudah ter-crop — bukan video mentah.
   * Mengembalikan detection result dari human.detect().
   *
   * [TASK 1] Downscale Input Canvas (640×480 → 320×240):
   * Canvas offscreen dibuat SEKALI via downscaleCanvasRef (lazy-init) dan
   * dipakai ulang tiap frame — menghindari document.createElement per-frame
   * yang menyebabkan GC pressure di HP/tablet.
   * Koordinat landmark di-scale balik ke 640×480 sebelum dikembalikan ke hook.
   *
   * Time-based Frame Throttling (150ms):
   * human.detect() hanya dijalankan maksimal setiap DETECT_INTERVAL_MS.
   * Di luar window itu, callback TIDAK mengembalikan null, melainkan
   * lastDetectResultRef.current — overlay tetap stabil, tidak berkedip.
   */
  const detectFacesCallback = useCallback(async (croppedCanvas) => {
    if (!modelsLoaded) return null;

    const now = performance.now();
    const elapsed = now - lastDetectTimeRef.current;

    if (elapsed < DETECT_INTERVAL_MS) {
      return lastDetectResultRef.current;
    }
    lastDetectTimeRef.current = now;

    const shouldExtractEmbedding = livenessVerifiedRef.current && isStableRef.current;
    if (human.config?.face?.description) {
      human.config.face.description.enabled = shouldExtractEmbedding;
    }

    // Gunakan croppedCanvas untuk kompatibilitas stabil di WebView Android (menghindari WebGL context loss)
    const result = await human.detect(croppedCanvas);
    const face = result?.face?.[0] ?? null;

    lastDetectResultRef.current = face;
    return face;
  }, [modelsLoaded]);

  /**
   * Dipanggil hook setiap frame saat wajah berhasil diproses.
   * Semua logika biometrik (EAR, cosine, draw) tetap di sini agar
   * tidak ada perubahan pada flow submit & state management.
   */
  const onFaceProcessed = useCallback(({ detection, smoothedMesh, normalizedMesh, boundingBox, ctx }) => {
    setDebugAiInfo({
      backend: human.tf?.getBackend?.() || 'webgl',
      faces: 1,
      nodes: smoothedMesh?.length || 0,
    });
    // ── Lighting Check ────────────────────────────────────────────────────
    const lightingStatus = checkLightingQuality(videoRef.current);
    setLightingWarning(lightingStatus);

    // ── [TASK 3] Rekam timestamp frame untuk kalkulasi FPS adaptif ────────
    const frameNow = performance.now();
    frameTimesRef.current.push(frameNow);
    // Simpan max 12 frame terakhir saja (hemat memori)
    if (frameTimesRef.current.length > 12) frameTimesRef.current.shift();
    // Hitung rata-rata durasi antar frame (ms) dari riwayat yang ada
    let avgFrameDuration = 200; // default: asumsi lambat (device belum terukur)
    if (frameTimesRef.current.length >= 3) {
      const oldest = frameTimesRef.current[0];
      const newest = frameTimesRef.current[frameTimesRef.current.length - 1];
      avgFrameDuration = (newest - oldest) / (frameTimesRef.current.length - 1);
    }
    // Tentukan ukuran window adaptif berdasarkan kecepatan device
    // Device lambat (>200ms/frame ≈ <5 FPS): window=3 → matched lebih cepat
    // Device sedang (70-200ms/frame ≈ 5-14 FPS): window=4
    // Device cepat (<70ms/frame ≈ >14 FPS): window=5 → lebih stabil
    let newWindow;
    if (avgFrameDuration > 200) newWindow = 3;
    else if (avgFrameDuration > 70) newWindow = 4;
    else newWindow = 5;
    adaptiveWindowRef.current = newWindow;

    // ── Simpan embedding ke ref (untuk submit) ────────────────────────────
    if (detection.embedding && detection.embedding.length > 0) {
      currentDescRef.current = Array.from(detection.embedding);
      // [TASK 5] Dev: mulai timer 'time to matched' saat embedding pertama tersedia
      if (import.meta.env.DEV && !matchTimerStartedRef.current) {
        matchTimerStartedRef.current = true;
        matchStartTimeRef.current = performance.now();
      }
    } else {
      // Embedding null/undefined: terjadi saat model description nonaktif
      // dalam alur bertahap atau GPU throttle.
      if (!isMatchedRef.current && !isStableRef.current) {
        currentDescRef.current = null;
      }
    }

    // ── EYE ASPECT RATIO (EAR) Blink Detection (Anti-Spoofing) ───────────
    // smoothedMesh sudah di-smooth EMA oleh hook; gunakan untuk EAR agar stabil
    const leftEAR = calculateEAR(smoothedMesh, MP_LEFT_EYE);
    const rightEAR = calculateEAR(smoothedMesh, MP_RIGHT_EYE);
    const avgEAR = (leftEAR + rightEAR) / 2.0;
    setCurrentEAR(parseFloat(avgEAR.toFixed(3)));

    if (!livenessVerifiedRef.current) {
      let passed = false;
      const challenge = livenessChallengeRef.current;

      if (challenge === 'BLINK') {
        setLivenessStatusMsg('Tantangan Keamanan: Kedipkan / Tutup Mata Sejenak');
        if (avgEAR < 0.23) {
          eyeClosedRef.current = true;
        } else if (eyeClosedRef.current && avgEAR > 0.25) {
          passed = true;
        }
      } else if (challenge === 'TURN_LEFT' || challenge === 'TURN_RIGHT') {
        setLivenessStatusMsg(
          challenge === 'TURN_LEFT'
            ? 'Tantangan Keamanan: Tolehkan Kepala ke KIRI'
            : 'Tantangan Keamanan: Tolehkan Kepala ke KANAN'
        );
        const yaw = detection.rotation?.angle?.yaw || 0;
        if (challenge === 'TURN_LEFT' && yaw > 0.15) passed = true;
        if (challenge === 'TURN_RIGHT' && yaw < -0.15) passed = true;
      }

      if (passed) {
        livenessVerifiedRef.current = true;
        setLivenessVerified(true);
        setIsLiveHuman(true);
        setLivenessStatusMsg('Liveness Terverifikasi!');
        const base64Str = captureVideoFrameBase64(videoRef.current);
        if (base64Str) setCapturedBase64Image(base64Str);
      }
    }

    // ── Phase 2: Stabilization & Facing Forward Check ──────────────────────
    if (livenessVerifiedRef.current) {
      const pitch = detection.rotation?.angle?.pitch || 0;
      const yaw = detection.rotation?.angle?.yaw || 0;
      const roll = detection.rotation?.angle?.roll || 0;

      // Syarat stabil: nilai rotasi harus mendekati 0 (antara -0.15 hingga 0.15)
      const isFacingForward = Math.abs(pitch) <= 0.15 && Math.abs(yaw) <= 0.15 && Math.abs(roll) <= 0.15;

      if (isFacingForward) {
        if (!isStableRef.current) {
          isStableRef.current = true;
          setIsStable(true);
          setLivenessStatusMsg('Mencocokkan Wajah...');
        }
      } else {
        if (isStableRef.current) {
          isStableRef.current = false;
          setIsStable(false);
          scoreHistoryRef.current = []; // Reset moving average history
          setMatchRate(0);
        }
        if (!isMatchedRef.current) {
          setLivenessStatusMsg('Tatap lurus ke kamera dan diam sejenak...');
        }
      }
    }

    // ── 1-to-1 Match via Cosine Similarity ───────────────────────────────
    // Skip calculations if already matched or if attendance is complete for today
    const currentStatus = attendanceStatusRef.current;
    if (currentStatus.checkInTime && currentStatus.checkOutTime) {
      setLivenessStatusMsg('✓ Absensi Hari Ini Sudah Lengkap');
      setMatchRate(0);
      setIsMatched(false);
      isMatchedRef.current = false;
      return;
    }

    if (currentDescRef.current && !isMatchedRef.current) {
      let rawPct = 0;
      const threshold = 55.0;

      // ── DIAGNOSTIC LOG (throttled: max 1x per 3 detik) ─────────────────
      // Buka DevTools Console → tab Console untuk membaca log ini.
      // Hapus blok ini setelah masalah terselesaikan.
      const now = Date.now();
      if (now - diagLogThrottleRef.current > 3000) {
        diagLogThrottleRef.current = now;
        console.group('🔍 [FACE MATCH DIAGNOSTIC]');
        console.log('currentDesc (embedding) → panjang:', currentDescRef.current?.length ?? 'NULL');
        console.log('masterVector            → panjang:', masterVectorRef.current?.length ?? 'NULL — Pastikan karyawan sudah didaftarkan biometriknya!');
        
        if (currentDescRef.current && masterVectorRef.current) {
          if (currentDescRef.current.length !== masterVectorRef.current.length) {
            console.warn('❌ DIMENSI TIDAK COCOK:', currentDescRef.current.length, '≠', masterVectorRef.current.length);
          } else {
            const normA = Math.sqrt(currentDescRef.current.reduce((s, v) => s + v * v, 0));
            const normB = Math.sqrt(masterVectorRef.current.reduce((s, v) => s + v * v, 0));
            console.log('📈 Vector Norms -> currentDesc:', normA.toFixed(4), '| masterVector:', normB.toFixed(4));
            console.log('🔢 currentDesc (first 5):', JSON.stringify(currentDescRef.current.slice(0, 5)));
            console.log('🔢 masterVector (first 5):', JSON.stringify(masterVectorRef.current.slice(0, 5)));
            
            // TF.js configuration diagnostics
            const tfBackend = human.tf?.getBackend?.() || 'unknown';
            const forceF16 = human.tf?.env?.()?.get?.('WEBGL_FORCE_F16_TEXTURES');
            console.log('⚙️ TF.js Env -> Backend:', tfBackend, '| FORCE_F16:', forceF16 ?? 'undefined');
            
            const testSim = cosineSimilarity(currentDescRef.current, masterVectorRef.current);
            console.log('✅ Cosine Similarity RAW:', (testSim * 100).toFixed(2) + '%');
          }
        }
        console.groupEnd();
      }
      // ── END DIAGNOSTIC LOG ───────────────────────────────────────────────

      if (masterVectorRef.current && currentDescRef.current.length === masterVectorRef.current.length) {
        const cosSim = cosineSimilarity(currentDescRef.current, masterVectorRef.current);
        rawPct = cosineToMatchPct(cosSim);
      }

      // [TASK 3] Score history smoothing — window ADAPTIF (3-5 frame)
      // Window ditentukan oleh adaptiveWindowRef berdasarkan FPS aktual device.
      // Device lambat (HP entry-level): window=3 → matched ~800ms lebih cepat.
      // Device cepat (laptop/HP flagship): window=5 → stabilitas lebih tinggi.
      const currentWindow = adaptiveWindowRef.current;
      scoreHistoryRef.current.push(rawPct);
      if (scoreHistoryRef.current.length > currentWindow) scoreHistoryRef.current.shift();
      const avgPct = scoreHistoryRef.current.reduce((a, b) => a + b, 0) / scoreHistoryRef.current.length;

      const matched = avgPct >= threshold;
      matchRateRef.current = avgPct;
      isMatchedRef.current = matched;
      setMatchRate(avgPct);
      setIsMatched(matched);

      if (matched) {
        if (!hasBeepedRef.current) {
          hasBeepedRef.current = true;
          playBeepSound();
          // [TASK 5] Dev: log total time dari embedding pertama → matched
          if (import.meta.env.DEV && matchStartTimeRef.current > 0) {
            const totalMs = Math.round(performance.now() - matchStartTimeRef.current);
            console.log(
              `🏁 [PERF] Time-to-Matched: ${totalMs}ms | ` +
              `Avg frame: ${Math.round(avgFrameDuration)}ms | ` +
              `Window: ${currentWindow} | Score: ${avgPct.toFixed(1)}%`
            );
          }
        }

        // Auto-submit once to prevent duplicate check-in/out calls
        if (selectedStatusRef.current === 'Hadir' && !hasAutoSubmittedRef.current) {
          hasAutoSubmittedRef.current = true;
          const currentStatus = attendanceStatusRef.current;
          const attendanceType = (currentStatus.checkedIn || currentStatus.checkInTime) ? 'CHECK_OUT' : 'CHECK_IN';

          if (handleVerifySubmitRef.current) {
            handleVerifySubmitRef.current(attendanceType);
          }
        }
      }

      // [TASK 5] Dev-only performance log (throttled, max 1x per 3 detik)
      if (import.meta.env.DEV) {
        const perfNow = Date.now();
        if (perfNow - perfLogThrottleRef.current > 3000) {
          perfLogThrottleRef.current = perfNow;
          const effectiveFPS = avgFrameDuration > 0 ? (1000 / avgFrameDuration).toFixed(1) : '?';
          console.log(
            `📊 [PERF] Inference: ${lastInferenceTimeRef.current}ms | ` +
            `FPS: ${effectiveFPS} | ` +
            `Window: ${currentWindow} (${avgFrameDuration > 200 ? 'SLOW' : avgFrameDuration > 70 ? 'MID' : 'FAST'}) | ` +
            `Score: ${avgPct.toFixed(1)}%`
          );
        }
      }
    }

    // ── Draw Biometric Node Overlay ───────────────────────────────────────
    // Gunakan smoothedMesh (koordinat piksel canvas 640×480, sudah di-smooth EMA)
    // agar visualisasi konsisten di semua device
    ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);
    drawGeometricMesh(
      ctx,
      smoothedMesh,
      livenessVerifiedRef.current,
      detection.score
    );
  }, []);

  /** Callback saat tidak ada wajah terdeteksi di frame */
  const onNoFace = useCallback(() => {
    currentDescRef.current = null;
    setLivenessStatusMsg('Harap posisikan wajah Anda di tengah layar');
    setDebugAiInfo({
      backend: human.tf?.getBackend?.() || 'webgl',
      faces: 0,
      nodes: 0,
    });
  }, []);

  /** Callback jika kamera gagal dibuka */
  const onCameraError = useCallback((err) => {
    setLivenessStatusMsg('❌ Gagal membuka kamera. Pastikan izin kamera diaktifkan.');
    console.error('[Kamera Error]:', err);
  }, []);

  // Panggil hook — kamera + rAF loop dikelola di sini
  useNormalizedFaceMesh({
    videoRef,
    canvasRef,
    active: modelsLoaded,      // Hanya mulai setelah model AI selesai dimuat
    facingMode,
    smoothAlpha: 0.35,         // EMA alpha: lebih kecil = lebih smooth tapi sedikit lag
    detectFaces: detectFacesCallback,
    onFaceProcessed,
    onNoFace,
    onCameraError,
  });

  // ── Submit Attendance (Transactional async/await) ──────────────────────
  const handleVerifySubmit = async (attendanceType = 'CHECK_IN') => {
    if (!selectedEmployeeId) {
      showToast('Pilih Karyawan', 'Harap pilih karyawan terlebih dahulu!', 'error');
      return;
    }

    if (attendanceStatus.checkInTime && attendanceStatus.checkOutTime) {
      showToast('Absensi Lengkap', 'Karyawan ini sudah melakukan Check-In dan Check-Out hari ini.', 'warning');
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
    // Wajibkan scan wajah baik saat CHECK-IN maupun CHECK-OUT untuk status Hadir
    if ((attendanceType === 'CHECK_IN' || attendanceType === 'CHECK_OUT') && selectedStatus === 'Hadir') {
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

    // Format attendance_type dengan DASH agar konsisten dengan logika deteksi
    // di TabAttendanceLogs.jsx (isCheckOut = log.attendance_type === 'CHECK-OUT')
    const attendanceTypeDash = attendanceType === 'CHECK_OUT' ? 'CHECK-OUT' : 'CHECK-IN';

    // ── GPS Geolocation Enforcements ────────────────────────────────────
    let locationStr = 'HP Mobile';
    let userLat = null;
    let userLng = null;
    let userAccuracy = null;
    const isAbsenHadir = (selectedStatus === 'Hadir');

    if (isAbsenHadir) {
      if (!navigator.geolocation) {
        showToast('GPS Tidak Didukung', 'Perangkat Anda tidak mendukung pelacakan lokasi GPS.', 'error');
        setIsSubmitting(false);
        return;
      }

      setLivenessStatusMsg('🌐 Sedang mendapatkan lokasi GPS...');
      showToast('Melacak Lokasi', 'Mencari sinyal GPS dengan akurasi tinggi...', 'info');

      try {
        let pos = null;
        try {
          // Coba 1: High Accuracy, timeout 10 detik, maximumAge 30 detik
          pos = await new Promise((resolve, reject) => {
            navigator.geolocation.getCurrentPosition(resolve, reject, {
              enableHighAccuracy: true,
              timeout: 10000,
              maximumAge: 30000
            });
          });
        } catch (err1) {
          console.warn('[FRONTEND GPS WARNING] High accuracy failed, retrying with low accuracy...', err1?.message || err1);
          // Coba 2: Low Accuracy, timeout 10 detik, maximumAge 5 menit
          pos = await new Promise((resolve, reject) => {
            navigator.geolocation.getCurrentPosition(resolve, reject, {
              enableHighAccuracy: false,
              timeout: 10000,
              maximumAge: 300000
            });
          });
        }

        userLat = pos.coords.latitude;
        userLng = pos.coords.longitude;
        userAccuracy = pos.coords.accuracy;

        console.log(`[FRONTEND GPS] Lat: ${userLat}, Lng: ${userLng}, Accuracy: ${userAccuracy}m`);

        // Jika akurasi sangat buruk (> 150m) dan bukan simulasi fallback
        if (userAccuracy > 150) {
          showToast(
            'Akurasi GPS Kurang Baik',
            `Akurasi GPS Anda (${userAccuracy.toFixed(1)}m) agak rendah. Menggunakan lokasi saat ini.`,
            'info'
          );
        }

        // ── Validasi Geofencing ──
        const distanceToOffice = calculateHaversineDistance(userLat, userLng, OFFICE_LAT, OFFICE_LON);
        console.log(`[FRONTEND GEOFENCE] Jarak ke Kantor: ${distanceToOffice.toFixed(2)}m (Batas: ${MAX_RADIUS_METERS}m)`);

        if (distanceToOffice > MAX_RADIUS_METERS) {
          showToast(
            'Absensi Ditolak',
            `Anda berada di luar area jangkauan kantor (Jarak: ${distanceToOffice.toFixed(1)}m, Batas: ${MAX_RADIUS_METERS}m).`,
            'error'
          );
          setIsSubmitting(false);
          setLivenessStatusMsg('Harap posisikan wajah Anda di tengah layar');
          return;
        }

        locationStr = `GPS (${userLat.toFixed(4)}, ${userLng.toFixed(4)}) [Jarak: ${distanceToOffice.toFixed(0)}m]`;
      } catch (gpsError) {
        console.error('[FRONTEND GPS ERROR - FALLBACK APPLIED]:', gpsError);
        
        // Menerapkan fallback lokasi default kantor agar pengetesan indoor / perangkat tanpa GPS tidak terblokir
        userLat = OFFICE_LAT;
        userLng = OFFICE_LON;
        userAccuracy = 999;
        
        showToast(
          'GPS Menggunakan Default',
          'Sinyal GPS lemah atau timeout. Menggunakan koordinat kantor default untuk memproses absensi.',
          'warning'
        );
        
        locationStr = `GPS Fallback (${userLat.toFixed(4)}, ${userLng.toFixed(4)}) [Jarak: 0m]`;
      }
    }


    // 2. Lakukan Operasi Database (API / Supabase Direct / Dexie.js Offline)
    let isSuccess = false;
    let successMsg = '';
    let recordTimestamp = new Date().toISOString();

    // ── Hitung Durasi Kerja (detik) saat CHECK-OUT ────────────────────────
    // Durasi = selisih waktu antara checkInTime dan waktu CHECK-OUT sekarang.
    // Disimpan dalam satuan detik (integer) agar mudah diformat di LogsPage.
    let durasiDetik = null;
    if (attendanceType === 'CHECK_OUT' && attendanceStatus.checkInTime) {
      const checkInMs = new Date(attendanceStatus.checkInTime).getTime();
      const checkOutMs = new Date(recordTimestamp).getTime();
      const selisihMs = checkOutMs - checkInMs;
      if (selisihMs > 0) {
        durasiDetik = Math.round(selisihMs / 1000); // konversi ms → detik
      }
    }

    if (navigator.onLine) {
      try {
        const apiPayload = {
          employee_id: parseInt(selectedEmployeeId),
          nik: (targetEmp.nik || nikInput).trim() || null,
          name: targetEmp.name || null,
          department: targetEmp.department || null,
          scan_descriptor: currentDescRef.current, // Send face embedding to the server
          location: `${locationStr} - GeoMesh Scanner`,
          attendance_type: attendanceTypeDash,
          status: selectedStatus,
          ...(durasiDetik !== null && { durasi: durasiDetik }),
          latitude: userLat,
          longitude: userLng,
        };

        // Call the serverless / Express API first (Tier 1)
        const response = await fetchWithTimeout(`${API_BASE_URL}/api/attendance/verify`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(apiPayload),
          timeout: 4000 // 4 seconds timeout for API
        });

        const resData = await response.json();

        if (response.ok && resData.success) {
          isSuccess = true;
          successMsg = resData.message || `Absensi ${typeLabel} berhasil dicatat di Server!`;
          if (resData.timestamp) {
            recordTimestamp = resData.timestamp;
          }
        } else {
          throw new Error(resData.message || 'API verification failed');
        }
      } catch (apiErr) {
        console.warn('[SERVER API WARN – FALLBACK TO DIRECT SUPABASE]:', apiErr.message);

        // Tier 2: Direct Supabase Cloud insert fallback
        try {
          const logPayload = {
            employee_id: parseInt(selectedEmployeeId),
            nik: (targetEmp.nik || nikInput).trim() || null,
            name: targetEmp.name || null,
            department: targetEmp.department || null,
            location: `${locationStr} - GeoMesh Scanner (Direct Fallback)`,
            attendance_type: attendanceTypeDash,
            status: selectedStatus === 'Hadir' ? 'Hadir (Verified)' : selectedStatus,
            euclidean_distance: euclideanDist,
            timestamp: recordTimestamp,
            ...(durasiDetik !== null && { durasi: durasiDetik }),
            latitude: userLat,
            longitude: userLng,
          };

          const { error: sbErr } = await supabase.from('attendance_logs').insert(logPayload);
          if (!sbErr) {
            isSuccess = true;
            successMsg = `Absensi ${typeLabel} berhasil dicatat langsung (Fallback)!`;
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
          afdeling: targetEmp.afdeling || null,
          timestamp: recordTimestamp,
          location: `${locationStr} [OFFLINE DEXIE]`,
          lat: userLat,
          lng: userLng,
          attendance_type: attendanceTypeDash,   // 'CHECK-IN' atau 'CHECK-OUT'
          status: selectedStatus === 'Hadir' ? 'Hadir (Verified) [OFFLINE]' : selectedStatus,
          euclidean_distance: euclideanDist,
          // Sertakan durasi kerja (detik) jika ini adalah CHECK-OUT
          ...(durasiDetik !== null && { durasi: durasiDetik }),
        });

        isSuccess = true;
        successMsg = `Absensi ${typeLabel} berhasil disimpan di penyimpanan offline!`;
      } catch (dexieErr) {
        console.error('[DEXIE QUEUE ERROR]:', dexieErr);
      }
    }

    // 3. JANGAN update Toast/State sebelum Database Benar-Benar Sukses
    if (isSuccess) {
      showToast('Absensi Berhasil', successMsg, 'success');

      // Dual-write online log locally for immediate state update
      if (navigator.onLine) {
        try {
          const onlineLogRecord = {
            id: 'online_' + new Date(recordTimestamp).getTime(),
            employee_id: targetEmp.id,
            nik: targetEmp.nik || nikInput,
            name: targetEmp.name,
            department: targetEmp.department,
            afdeling: targetEmp.afdeling || null,
            timestamp: recordTimestamp,
            location: `${locationStr} - GeoMesh Scanner`,
            lat: userLat,
            lng: userLng,
            status: selectedStatus === 'Hadir' ? 'Hadir (Verified)' : selectedStatus,
            attendance_type: attendanceTypeDash,
            euclidean_distance: euclideanDist,
            is_synced: true,
            created_at: recordTimestamp
          };
          await db.attendance_logs.put(onlineLogRecord);
          console.log('[Local Database] Saved online log locally:', onlineLogRecord);
        } catch (dbErr) {
          console.warn('[Local Database] Failed to write online log locally:', dbErr);
        }
      }

      setAttendanceStatus((prev) => ({
        checkedIn: attendanceType === 'CHECK_IN' ? true : (attendanceType === 'CHECK_OUT' ? false : prev.checkedIn),
        checkInTime: attendanceType === 'CHECK_IN' ? recordTimestamp : prev.checkInTime,
        checkOutTime: attendanceType === 'CHECK_OUT' ? recordTimestamp : prev.checkOutTime,
        loaded: true,
      }));

      setTimeout(async () => {
        await fetchAttendanceStatus(selectedEmployeeId);
      }, 300);

      // Reset sesi/pilihan karyawan setelah 2.5 detik agar mesin siap untuk karyawan selanjutnya
      setTimeout(() => {
        setSelectedEmployeeId('');
        setNikInput('');
        setAttendanceStatus({ checkedIn: false, checkInTime: null, checkOutTime: null, loaded: false });
        hasAutoSubmittedRef.current = false;
      }, 2500);

      if (onVerificationSuccess) onVerificationSuccess();
    } else {
      // Jika Catch Error: Tampilkan Toast Gagal & Biarkan Mode Tombol Tetap (State Tidak Berubah)
      showToast('Absensi Gagal', `Gagal memproses absensi ${typeLabel}. Silakan coba lagi.`, 'error');
      hasAutoSubmittedRef.current = false;
    }

    setIsSubmitting(false);
  };

  // Computed helpers
  const isHadir = selectedStatus === 'Hadir';
  const step1Done = livenessVerified;
  const step2Done = isMatched;
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
          <div className="form-group" style={{ position: 'relative' }}>
            <label>Keterangan</label>
            <div
              onClick={() => setIsStatusDropdownOpen(!isStatusDropdownOpen)}
              style={{
                display: 'flex', alignItems: 'center', justifyContent: 'space-between',
                padding: '10px 14px', borderRadius: '8px', border: '1px solid var(--border-color)',
                background: 'var(--bg-input)', cursor: 'pointer', color: 'inherit'
              }}
            >
              <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
                {selectedStatus === 'Hadir' && <><CheckCircle size={18} color="#10b981" /> Hadir (Verified Wajah)</>}
                {selectedStatus === 'Izin' && <><Mail size={18} color="#38bdf8" /> Izin</>}
                {selectedStatus === 'Sakit' && <><Power size={18} color="#f59e0b" /> Sakit</>}
                {selectedStatus === 'Mangkir' && <><XCircle size={18} color="#ef4444" /> Mangkir</>}
              </div>
              <ChevronDown size={18} color="var(--text-muted)" />
            </div>

            {isStatusDropdownOpen && (
              <div style={{
                position: 'absolute', top: '100%', left: 0, right: 0,
                background: 'var(--bg-card)', border: '1px solid var(--border-color)',
                borderRadius: '8px', marginTop: '4px', zIndex: 10,
                boxShadow: 'var(--shadow-md)', overflow: 'hidden'
              }}>
                {[
                  { value: 'Hadir', label: 'Hadir (Verified Wajah)', icon: <CheckCircle size={18} color="#10b981" /> },
                  { value: 'Izin', label: 'Izin', icon: <Mail size={18} color="#38bdf8" /> },
                  { value: 'Sakit', label: 'Sakit', icon: <Power size={18} color="#f59e0b" /> },
                  { value: 'Mangkir', label: 'Mangkir', icon: <XCircle size={18} color="#ef4444" /> }
                ].map(opt => (
                  <div
                    key={opt.value}
                    onClick={() => { setSelectedStatus(opt.value); setIsStatusDropdownOpen(false); }}
                    style={{
                      display: 'flex', alignItems: 'center', gap: '8px',
                      padding: '10px 14px', cursor: 'pointer',
                      background: selectedStatus === opt.value ? 'rgba(99,102,241,0.1)' : 'transparent',
                      borderBottom: '1px solid rgba(255,255,255,0.05)'
                    }}
                    onMouseEnter={(e) => e.currentTarget.style.background = 'rgba(255,255,255,0.05)'}
                    onMouseLeave={(e) => e.currentTarget.style.background = selectedStatus === opt.value ? 'rgba(99,102,241,0.1)' : 'transparent'}
                  >
                    {opt.icon} {opt.label}
                  </div>
                ))}
              </div>
            )}
          </div>

          {/* Live Camera */}
          {isHadir && (
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

              {/* Real-Time On-Screen AI Diagnostic Bar (Moved Above Camera) */}
              <div
                style={{
                  background: 'rgba(15, 23, 42, 0.06)',
                  border: '1px solid var(--border-color)',
                  borderRadius: '8px',
                  padding: '6px 12px',
                  marginBottom: '12px',
                  fontSize: '0.72rem',
                  fontFamily: 'monospace',
                  display: 'flex',
                  alignItems: 'center',
                  gap: '8px',
                  color: 'var(--text-main)',
                  width: 'fit-content'
                }}
              >
                <span style={{ display: 'inline-block', width: '8px', height: '8px', borderRadius: '50%', background: debugAiInfo.faces > 0 ? '#10b981' : '#f59e0b' }}></span>
                <strong>AI Status:</strong>
                <span>Backend: {debugAiInfo.backend}</span>
                <span>|</span>
                <span>Wajah: {debugAiInfo.faces}</span>
                <span>|</span>
                <span>Mesh: {debugAiInfo.nodes} pts</span>
              </div>

              <div
                className="webcam-wrapper"
                style={{
                  height: 'auto',
                  aspectRatio: '4/3'
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

                {/* ── GPS Granted Badge (koordinat live di bawah kamera) ───── */}
                {gpsPermission === 'granted' && liveCoords && (
                  <div
                    style={{
                      position: 'absolute',
                      bottom: '10px',
                      left: '50%',
                      transform: 'translateX(-50%)',
                      zIndex: 15,
                      padding: '5px 14px',
                      borderRadius: '20px',
                      background: 'rgba(16, 185, 129, 0.88)',
                      color: '#fff',
                      fontSize: '0.72rem',
                      fontWeight: 700,
                      display: 'flex',
                      alignItems: 'center',
                      gap: '6px',
                      backdropFilter: 'blur(4px)',
                      whiteSpace: 'nowrap',
                    }}
                  >
                    <Navigation size={13} />
                    {liveCoords.lat.toFixed(5)}, {liveCoords.lng.toFixed(5)}
                    <span style={{ opacity: 0.8 }}>±{liveCoords.accuracy.toFixed(0)}m</span>
                  </div>
                )}

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
                          ? isStable
                            ? 'rgba(59, 130, 246, 0.92)'
                            : 'rgba(245, 158, 11, 0.92)' // Yellow/orange alert background when face needs to stabilize
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
                      isStable ? (
                        <>✓ Liveness Terverifikasi! · Mencocokkan...</>
                      ) : (
                        <>{livenessStatusMsg}</> // Will be "Tatap lurus ke kamera dan diam sejenak..."
                      )
                    ) : (
                      <>{livenessStatusMsg}</>
                    )}
                  </div>
                )}

                {/* Lighting Warning Badge */}
                {lightingWarning && (
                  <div
                    style={{
                      position: 'absolute',
                      top: '50px',
                      left: '50%',
                      transform: 'translateX(-50%)',
                      zIndex: 10,
                      padding: '4px 12px',
                      borderRadius: '12px',
                      background: 'rgba(239, 68, 68, 0.9)', // Red warning
                      color: '#ffffff',
                      fontSize: '0.75rem',
                      fontWeight: 700,
                      pointerEvents: 'none',
                      whiteSpace: 'nowrap',
                      boxShadow: '0 2px 8px rgba(0,0,0,0.3)',
                      display: 'flex',
                      alignItems: 'center',
                      gap: '4px',
                    }}
                  >
                    <i className="fa-solid fa-triangle-exclamation"></i> {lightingWarning}
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
                  Log Absensi Hari Ini
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
