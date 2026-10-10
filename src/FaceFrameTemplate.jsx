// FaceFrameTemplate.jsx  (taruh di src/components/)
// Satu bingkai wajah persegi untuk: registrasi, scan ulang, check-in, check-out.
// Disesuaikan dengan TabFaceVerification + useNormalizedFaceMesh:
//  - ruang kanvas 640x480 (hasil Center Crop 4:3), boundingBox = {minX,minY,maxX,maxY,width,height}
//  - dibaca lewat ref per frame -> TIDAK ada setState per frame / re-render
//  - kanvas terpisah dan TIDAK di-mirror, jadi label teks tidak terbalik di kamera depan
//    (bingkai simetris, jadi tidak perlu di-mirror)

import { useEffect, useRef } from 'react';

// Fallback untuk WebView/browser lama (Android WebView, Safari < 16) yang belum punya ctx.roundRect
if (typeof CanvasRenderingContext2D !== 'undefined' && !CanvasRenderingContext2D.prototype.roundRect) {
  CanvasRenderingContext2D.prototype.roundRect = function roundRect(x, y, w, h, r = 0) {
    const rad = Math.max(0, Math.min(Array.isArray(r) ? r[0] : r, w / 2, h / 2));
    this.moveTo(x + rad, y);
    this.arcTo(x + w, y, x + w, y + h, rad);
    this.arcTo(x + w, y + h, x, y + h, rad);
    this.arcTo(x, y + h, x, y, rad);
    this.arcTo(x, y, x + w, y, rad);
    this.closePath();
  };
}

export const FRAME_MODES = {
  register: { label: 'REGISTRASI', accent: '#3b82f6' },
  rescan:   { label: 'SCAN ULANG', accent: '#a855f7' },
  checkin:  { label: 'CHECK-IN',   accent: '#22c55e' },
  checkout: { label: 'CHECK-OUT',  accent: '#f59e0b' },
};

// idle       : belum ada wajah            -> warna aksen mode
// misaligned : wajah ada tapi belum pas   -> merah
// scanning   : pas, sedang mencocokkan    -> kuning (+ progress bar)
// success    : lulus / tersimpan          -> hijau (+ centang)
// error      : gagal                      -> merah
export const FRAME_COLORS = {
  idle: null,
  misaligned: '#ef4444',
  scanning: '#facc15',
  success: '#22c55e',
  error: '#ef4444',
};

// Teks selaras dengan pesan yang sudah dipakai di TabFaceVerification
export const FRAME_HINTS = {
  'no-face': 'Harap posisikan wajah Anda di tengah layar',
  'off-center': 'Pusatkan wajah di dalam bingkai',
  'too-far': 'Wajah Terlalu Jauh',
  'too-close': 'Wajah Terlalu Dekat',
};

export const FRAME_W = 640;
export const FRAME_H = 480;

// Persegi di tengah kanvas, sedikit ke atas agar dagu tidak terpotong.
// sizeRatio 0.72 -> ~345px pada kanvas 480px: cukup untuk kepala + rambut,
// dan mesh wajah (~190-210px pada screenshot Anda) mengisi sekitar 55-60% bingkai.
export function getFrameRect(width = FRAME_W, height = FRAME_H, { sizeRatio = 0.72, offsetY = -0.02 } = {}) {
  const size = Math.min(width, height) * sizeRatio;
  return { x: (width - size) / 2, y: (height - size) / 2 + height * offsetY, size };
}

// Terima boundingBox objek {minX,minY,width,height} (dari hook) atau array [x,y,w,h].
// boundingBox dari hook dihitung dari landmark mesh (lebih sempit dari kepala utuh).
// Batas ukuran: minFill 0.40 ~ 138px (sama dgn batas lama 140px "Terlalu Jauh"),
// maxFill 0.95 ~ 328px pada bingkai 345px. Sesuaikan setelah uji di lapangan.
export function evaluateFaceInFrame(box, rect, { minFill = 0.40, maxFill = 0.95 } = {}) {
  if (!box) return { ok: false, reason: 'no-face' };
  let x, y, w, h;
  if (Array.isArray(box)) [x, y, w, h] = box;
  else { x = box.minX; y = box.minY; w = box.width; h = box.height; }
  if (![x, y, w, h].every(Number.isFinite) || w <= 0 || h <= 0) return { ok: false, reason: 'no-face' };

  const cx = x + w / 2, cy = y + h / 2;
  const inside = cx > rect.x && cx < rect.x + rect.size && cy > rect.y && cy < rect.y + rect.size;
  if (!inside) return { ok: false, reason: 'off-center' };

  const fill = Math.max(w, h) / rect.size;
  if (fill < minFill) return { ok: false, reason: 'too-far' };
  if (fill > maxFill) return { ok: false, reason: 'too-close' };

  const tol = rect.size * 0.08;
  const fullyIn = x > rect.x - tol && y > rect.y - tol &&
    x + w < rect.x + rect.size + tol && y + h < rect.y + rect.size + tol;
  if (!fullyIn) return { ok: false, reason: 'off-center' };
  return { ok: true, reason: 'ok' };
}

export function drawFaceFrame(ctx, width, height, opts = {}) {
  const { mode = 'checkin', state = 'idle', progress = 0, showLabel = true, dimOutside = true, time = 0 } = opts;
  const cfg = FRAME_MODES[mode] || FRAME_MODES.checkin;
  const color = FRAME_COLORS[state] || cfg.accent;
  const { x, y, size } = getFrameRect(width, height);

  ctx.save();

  if (dimOutside) {
    ctx.fillStyle = 'rgba(0,0,0,0.40)';
    ctx.beginPath();
    ctx.rect(0, 0, width, height);
    ctx.rect(x, y, size, size);
    ctx.fill('evenodd');
  }

  ctx.strokeStyle = color + '66';
  ctx.lineWidth = 1.5;
  ctx.strokeRect(x, y, size, size);

  // Siluet kepala + bahu sebagai panduan penempatan (hanya sebelum wajah pas)
  if (state === 'idle' || state === 'misaligned') {
    const hx = x + size / 2;
    const hy = y + size * 0.44;
    ctx.save();
    ctx.setLineDash([8, 8]);
    ctx.strokeStyle = color + '99';
    ctx.lineWidth = 2;
    ctx.beginPath();
    ctx.ellipse(hx, hy, size * 0.27, size * 0.36, 0, 0, Math.PI * 2);
    ctx.stroke();
    ctx.beginPath();
    ctx.moveTo(x + size * 0.12, y + size);
    ctx.quadraticCurveTo(x + size * 0.14, y + size * 0.84, hx, y + size * 0.82);
    ctx.quadraticCurveTo(x + size * 0.86, y + size * 0.84, x + size * 0.88, y + size);
    ctx.stroke();
    ctx.restore();
  }

  const arm = size * 0.16, r = size * 0.04;
  ctx.strokeStyle = color;
  ctx.lineWidth = Math.max(4, size * 0.014);
  ctx.lineCap = 'round';
  ctx.lineJoin = 'round';
  const corner = (cx, cy, dx, dy) => {
    ctx.beginPath();
    ctx.moveTo(cx + dx * arm, cy);
    ctx.lineTo(cx + dx * r, cy);
    ctx.quadraticCurveTo(cx, cy, cx, cy + dy * r);
    ctx.lineTo(cx, cy + dy * arm);
    ctx.stroke();
  };
  corner(x, y, 1, 1);
  corner(x + size, y, -1, 1);
  corner(x, y + size, 1, -1);
  corner(x + size, y + size, -1, -1);

  if (state === 'scanning') {
    const t = (time % 1600) / 1600;
    const ly = y + size * (0.5 + 0.5 * Math.sin(t * Math.PI * 2));
    const g = ctx.createLinearGradient(x, ly, x + size, ly);
    g.addColorStop(0, color + '00');
    g.addColorStop(0.5, color + 'cc');
    g.addColorStop(1, color + '00');
    ctx.strokeStyle = g;
    ctx.lineWidth = 2;
    ctx.beginPath();
    ctx.moveTo(x, ly);
    ctx.lineTo(x + size, ly);
    ctx.stroke();

    // Progress pengumpulan sampel pencocokan (0..1), di bawah bingkai
    const bw = size * 0.5, bh = 5, bx = x + (size - bw) / 2, by = y + size + 12;
    ctx.fillStyle = 'rgba(255,255,255,0.25)';
    ctx.beginPath(); ctx.roundRect(bx, by, bw, bh, bh / 2); ctx.fill();
    const p = Math.max(0, Math.min(1, progress));
    if (p > 0) {
      ctx.fillStyle = color;
      ctx.beginPath(); ctx.roundRect(bx, by, bw * p, bh, bh / 2); ctx.fill();
    }
  }

  if (state === 'success') {
    ctx.strokeStyle = color;
    ctx.lineWidth = 8;
    ctx.beginPath();
    ctx.moveTo(width / 2 - 26, y + size - 34);
    ctx.lineTo(width / 2 - 6, y + size - 14);
    ctx.lineTo(width / 2 + 30, y + size - 58);
    ctx.stroke();
  }

  if (showLabel) {
    const fs = Math.max(12, size * 0.04);
    ctx.font = `600 ${fs}px system-ui, sans-serif`;
    const tw = ctx.measureText(cfg.label).width + fs * 1.2;
    const th = fs * 1.6;
    const ly = Math.max(4, y - th - 8);
    ctx.fillStyle = color;
    ctx.beginPath(); ctx.roundRect(width / 2 - tw / 2, ly, tw, th, th / 2); ctx.fill();
    ctx.fillStyle = '#0b0b0b';
    ctx.textAlign = 'center';
    ctx.textBaseline = 'middle';
    ctx.fillText(cfg.label, width / 2, ly + th / 2 + 1);
  }

  ctx.restore();
}

// Overlay kanvas di atas <video>/<canvas> mesh. Status dibaca dari stateRef tiap frame:
//   stateRef.current = { state: 'scanning', progress: 0.6 }
// Prop `state`/`progress` hanya dipakai bila stateRef tidak diberikan (mis. halaman registrasi sederhana).
export function FaceFrameOverlay({
  mode = 'checkin',
  state = 'idle',
  progress = 0,
  stateRef = null,
  showLabel = true,
  width = FRAME_W,
  height = FRAME_H,
  className = '',
}) {
  const canvasRef = useRef(null);
  const propsRef = useRef({ mode, state, progress, showLabel });
  propsRef.current = { mode, state, progress, showLabel };

  useEffect(() => {
    const canvas = canvasRef.current;
    if (!canvas) return undefined;
    const ctx = canvas.getContext('2d');
    let raf;
    const loop = (t) => {
      const p = propsRef.current;
      const live = stateRef?.current;
      ctx.clearRect(0, 0, width, height);
      drawFaceFrame(ctx, width, height, {
        mode: p.mode,
        state: live?.state ?? p.state,
        progress: live?.progress ?? p.progress,
        showLabel: p.showLabel,
        time: t,
      });
      raf = requestAnimationFrame(loop);
    };
    raf = requestAnimationFrame(loop);
    return () => cancelAnimationFrame(raf);
  }, [width, height, stateRef]);

  return (
    <canvas
      ref={canvasRef}
      width={width}
      height={height}
      className={className}
      style={{ position: 'absolute', inset: 0, width: '100%', height: '100%', pointerEvents: 'none', zIndex: 5 }}
    />
  );
}
