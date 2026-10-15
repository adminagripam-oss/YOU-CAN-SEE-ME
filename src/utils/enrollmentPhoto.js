/**
 * Helper to crop, resize, and optimize employee face enrollment photos
 * for fast offline storage and remote Supabase Storage sync.
 */

/**
 * Pure function: Computes a cropped bounding box with margin,
 * strictly clamped within canvas boundaries.
 * 
 * @param {Array|Object} boundingBox [x, y, w, h] or {x, y, width, height}
 * @param {number} canvasW Canvas width
 * @param {number} canvasH Canvas height
 * @param {number} margin Expansion margin ratio (default 0.25 = 25%)
 * @returns {{ x: number, y: number, width: number, height: number }}
 */
export function computeCropRect(boundingBox, canvasW, canvasH, margin = 0.25) {
  const cW = Math.max(0, canvasW || 0);
  const cH = Math.max(0, canvasH || 0);

  if (cW === 0 || cH === 0) {
    return { x: 0, y: 0, width: 0, height: 0 };
  }

  let bx = 0, by = 0, bw = cW, bh = cH;

  if (boundingBox) {
    if (Array.isArray(boundingBox) && boundingBox.length >= 4) {
      [bx, by, bw, bh] = boundingBox;
    } else if (typeof boundingBox === 'object' && boundingBox.width && boundingBox.height) {
      bx = boundingBox.x || 0;
      by = boundingBox.y || 0;
      bw = boundingBox.width;
      bh = boundingBox.height;
    }
  }

  // Margin expansion (preserve ears, hair, chin context)
  const marginX = bw * margin;
  const marginY = bh * margin;

  const rawX1 = bx - marginX;
  const rawY1 = by - marginY;
  const rawX2 = bx + bw + marginX;
  const rawY2 = by + bh + marginY;

  // Clamped coordinates to canvas boundaries
  const cropX = Math.max(0, Math.min(cW, rawX1));
  const cropY = Math.max(0, Math.min(cH, rawY1));
  const cropX2 = Math.max(0, Math.min(cW, rawX2));
  const cropY2 = Math.max(0, Math.min(cH, rawY2));

  const rx = Math.round(cropX);
  const ry = Math.round(cropY);
  const rx2 = Math.round(cropX2);
  const ry2 = Math.round(cropY2);

  return {
    x: rx,
    y: ry,
    width: Math.max(0, rx2 - rx),
    height: Math.max(0, ry2 - ry)
  };
}

/**
 * Captures enrollment photo thumbnail (max 240px) without flipping.
 * Camera mirroring is strictly handled via CSS on display elements.
 */
export function captureEnrollmentPhoto(videoOrCanvas, boundingBox) {
  if (!videoOrCanvas) return null;

  try {
    const isVideo = typeof HTMLVideoElement !== 'undefined' && videoOrCanvas instanceof HTMLVideoElement;
    const srcW = isVideo ? (videoOrCanvas.videoWidth || 640) : (videoOrCanvas.width || 640);
    const srcH = isVideo ? (videoOrCanvas.videoHeight || 480) : (videoOrCanvas.height || 480);

    if (srcW === 0 || srcH === 0) return null;

    const crop = computeCropRect(boundingBox, srcW, srcH, 0.25);
    if (crop.width === 0 || crop.height === 0) return null;

    // Target thumbnail max dimension 240px
    const maxDim = 240;
    const scale = Math.min(maxDim / crop.width, maxDim / crop.height, 1.0);
    const dstW = Math.round(crop.width * scale);
    const dstH = Math.round(crop.height * scale);

    if (typeof document === 'undefined') return null;
    const canvas = document.createElement('canvas');
    canvas.width = dstW;
    canvas.height = dstH;
    const ctx = canvas.getContext('2d');
    if (!ctx) return null;

    // No flip! Photos must be stored in their natural physical orientation.
    ctx.drawImage(
      videoOrCanvas,
      crop.x, crop.y, crop.width, crop.height,
      0, 0, dstW, dstH
    );

    const base64 = canvas.toDataURL('image/jpeg', 0.82);
    return {
      base64,
      width: dstW,
      height: dstH
    };
  } catch (err) {
    console.warn('[captureEnrollmentPhoto] Error generating thumbnail:', err);
    return null;
  }
}
