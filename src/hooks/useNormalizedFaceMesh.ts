import { useEffect, useRef, useCallback } from 'react';

// ─────────────────────────────────────────────────────────────────────────
// TYPES & INTERFACES
// ─────────────────────────────────────────────────────────────────────────

export type Point3D = 
  | [number, number, number?] 
  | { x: number; y: number; z?: number };

export interface BoundingBox {
  minX: number;
  minY: number;
  maxX: number;
  maxY: number;
  width: number;
  height: number;
  centerX: number;
  centerY: number;
}

export interface NormalizedLandmark {
  rawX: number;
  rawY: number;
  rawZ: number;
  localX: number; // Normalized local X (-0.5 to 0.5, origin at Bounding Box center)
  localY: number; // Normalized local Y (-0.5 to 0.5, origin at Bounding Box center)
  localZ: number; // Normalized local Z (scaled relative to face width)
}

export interface FaceMeshOutput {
  detection: any; // Raw detection result from the AI model
  smoothedMesh: Point3D[];
  normalizedMesh: NormalizedLandmark[];
  boundingBox: BoundingBox;
  ctx: CanvasRenderingContext2D;
}

export interface Config {
  videoRef: React.RefObject<HTMLVideoElement | null>;
  canvasRef: React.RefObject<HTMLCanvasElement | null>;
  active?: boolean;
  facingMode?: 'user' | 'environment';
  smoothingMethod?: 'one-euro' | 'ema' | 'none';
  emaAlpha?: number;             // alpha for EMA (0.0 to 1.0, default 0.4)
  oneEuroMinCutoff?: number;     // min cutoff freq in Hz (default 1.0)
  oneEuroBeta?: number;          // speed coefficient (default 0.007)
  oneEuroDCutoff?: number;        // cutoff freq for derivative (default 1.0)
  detectFaces: (canvas: HTMLCanvasElement, timestamp: number) => Promise<any> | any;
  onFaceProcessed?: (output: FaceMeshOutput) => void;
  onNoFace?: () => void;
  onCameraError?: (err: Error) => void;
}

// ─────────────────────────────────────────────────────────────────────────
// CONSTANTS
// ─────────────────────────────────────────────────────────────────────────

const STD_WIDTH = 640;
const STD_HEIGHT = 480;
const TARGET_ASPECT = 4 / 3;

/** Standard MediaPipe Face Mesh outer boundary contour indices (36 points) */
export const FACE_OUTER_CONTOUR_INDICES = [
  10, 338, 297, 332, 284, 251, 389, 356, 454, 323, 361, 288, 397, 365, 379, 378, 400, 377,
  152, 148, 176, 149, 150, 136, 172, 58, 132, 93, 234, 127, 162, 21, 54, 103, 67, 109
];

// ─────────────────────────────────────────────────────────────────────────
// ONE EURO FILTER MATHEMATICAL IMPLEMENTATION
// ─────────────────────────────────────────────────────────────────────────

class LowPassFilter {
  private y: number | null = null;

  public filter(value: number, alpha: number): number {
    if (this.y === null) {
      this.y = value;
    } else {
      this.y = alpha * value + (1.0 - alpha) * this.y;
    }
    return this.y;
  }

  public lastValue(): number | null {
    return this.y;
  }

  public reset(): void {
    this.y = null;
  }
}

class OneEuroFilter {
  private minCutoff: number;
  private beta: number;
  private dCutoff: number;
  private xFilter: LowPassFilter;
  private dxFilter: LowPassFilter;
  private lastTime: number | null = null;

  constructor(minCutoff = 1.0, beta = 0.007, dCutoff = 1.0) {
    this.minCutoff = minCutoff;
    this.beta = beta;
    this.dCutoff = dCutoff;
    this.xFilter = new LowPassFilter();
    this.dxFilter = new LowPassFilter();
  }

  public filter(value: number, timestamp: number): number {
    if (this.lastTime === null || timestamp === this.lastTime) {
      this.lastTime = timestamp;
      return this.xFilter.filter(value, 1.0);
    }

    const dt = (timestamp - this.lastTime) / 1000.0; // convert ms to seconds
    this.lastTime = timestamp;

    if (dt <= 0) {
      return this.xFilter.lastValue() ?? value;
    }

    const prevX = this.xFilter.lastValue() ?? value;
    const dx = (value - prevX) / dt;
    const alphaD = this.calculateAlpha(dt, this.dCutoff);
    const edx = this.dxFilter.filter(dx, alphaD);

    const cutoff = this.minCutoff + this.beta * Math.abs(edx);
    const alpha = this.calculateAlpha(dt, cutoff);

    return this.xFilter.filter(value, alpha);
  }

  private calculateAlpha(dt: number, cutoff: number): number {
    const tau = 1.0 / (2.0 * Math.PI * cutoff);
    return dt / (dt + tau);
  }

  public reset(): void {
    this.xFilter.reset();
    this.dxFilter.reset();
    this.lastTime = null;
  }
}

// ─────────────────────────────────────────────────────────────────────────
// HELPER MATH & GEOMETRY FUNCTIONS
// ─────────────────────────────────────────────────────────────────────────

/**
 * Computes source cropping coordinates to enforce a 4:3 aspect ratio 
 * without stretching, independent of original camera aspect ratio.
 */
export function computeCenterCrop(videoWidth: number, videoHeight: number) {
  const videoAspect = videoWidth / videoHeight;

  let srcW = videoWidth;
  let srcH = videoHeight;
  let srcX = 0;
  let srcY = 0;

  if (videoAspect > TARGET_ASPECT) {
    // Video is wider than 4:3 (e.g. 16:9) -> crop sides
    srcW = Math.round(videoHeight * TARGET_ASPECT);
    srcX = Math.round((videoWidth - srcW) / 2);
  } else if (videoAspect < TARGET_ASPECT) {
    // Video is taller than 4:3 -> crop top & bottom
    srcH = Math.round(videoWidth / TARGET_ASPECT);
    srcY = Math.round((videoHeight - srcH) / 2);
  }

  return { srcX, srcY, srcW, srcH };
}

/**
 * Computes the tight Bounding Box of the landmarks.
 */
export function computeBoundingBox(landmarks: Point3D[]): BoundingBox {
  let minX = Infinity;
  let minY = Infinity;
  let maxX = -Infinity;
  let maxY = -Infinity;

  for (const p of landmarks) {
    const px = Array.isArray(p) ? p[0] : p.x;
    const py = Array.isArray(p) ? p[1] : p.y;
    if (px < minX) minX = px;
    if (py < minY) minY = py;
    if (px > maxX) maxX = px;
    if (py > maxY) maxY = py;
  }

  const width = maxX - minX;
  const height = maxY - minY;

  return {
    minX,
    minY,
    maxX,
    maxY,
    width,
    height,
    centerX: minX + width / 2,
    centerY: minY + height / 2,
  };
}

/**
 * Applies standard Exponential Moving Average (EMA) to landmarks.
 */
function applyEMASmoothing(
  rawLandmarks: Point3D[],
  prevSmoothed: Point3D[] | null,
  alpha: number
): Point3D[] {
  if (!prevSmoothed || prevSmoothed.length !== rawLandmarks.length) {
    return rawLandmarks;
  }

  return rawLandmarks.map((pt, i) => {
    const prev = prevSmoothed[i];
    if (!prev) return pt;
    if (Array.isArray(pt) && Array.isArray(prev)) {
      return [
        alpha * pt[0] + (1 - alpha) * (prev[0] ?? pt[0]),
        alpha * pt[1] + (1 - alpha) * (prev[1] ?? pt[1]),
        alpha * (pt[2] ?? 0) + (1 - alpha) * (prev[2] ?? pt[2] ?? 0),
      ] as Point3D;
    } else if (!Array.isArray(pt) && !Array.isArray(prev)) {
      return {
        x: alpha * pt.x + (1 - alpha) * (prev.x ?? pt.x),
        y: alpha * pt.y + (1 - alpha) * (prev.y ?? pt.y),
        z: alpha * (pt.z ?? 0) + (1 - alpha) * (prev.z ?? pt.z ?? 0),
      } as Point3D;
    }
    return pt;
  });
}

/**
 * Normalizes coordinates relative to the face Bounding Box.
 * Origin (0,0) is centered on the bounding box.
 * Scale is normalized relative to face size (width & height), 
 * making landmarks layout device-independent.
 */
export function normalizeToLocalCoords(
  landmarks: Point3D[],
  bbox: BoundingBox
): NormalizedLandmark[] {
  const { centerX, centerY, width, height } = bbox;
  const scaleX = width > 0 ? width : 1;
  const scaleY = height > 0 ? height : 1;

  return landmarks.map((pt) => {
    const px = Array.isArray(pt) ? pt[0] : pt.x;
    const py = Array.isArray(pt) ? pt[1] : pt.y;
    const pz = Array.isArray(pt) ? (pt[2] ?? 0) : (pt.z ?? 0);

    return {
      rawX: px,
      rawY: py,
      rawZ: pz,
      localX: (px - centerX) / scaleX,
      localY: (py - centerY) / scaleY,
      localZ: pz / scaleX, // scaled relative to facial width
    };
  });
}

// ─────────────────────────────────────────────────────────────────────────
// RENDERING HELPERS
// ─────────────────────────────────────────────────────────────────────────

/**
 * High-performance 2D Canvas rendering utility for mesh nodes and contours.
 */
export function drawFaceMeshOverlay(
  ctx: CanvasRenderingContext2D,
  landmarks: Point3D[],
  options: {
    drawPoints?: boolean;
    drawBorder?: boolean;
    pointColor?: string;
    pointRadius?: number;
    borderColor?: string;
    borderWidth?: number;
  } = {}
) {
  const {
    drawPoints = true,
    drawBorder = true,
    pointColor = 'rgba(50, 200, 255, 0.8)',
    pointRadius = 2.0,
    borderColor = '#32c8ff',
    borderWidth = 2.0,
  } = options;

  if (!landmarks || landmarks.length === 0) return;

  // 1. Draw Outer Border (Contour)
  if (drawBorder && landmarks.length >= 110) {
    ctx.save();
    ctx.beginPath();
    ctx.strokeStyle = borderColor;
    ctx.lineWidth = borderWidth;
    ctx.lineCap = 'round';
    ctx.lineJoin = 'round';

    const getCoord = (idx: number): [number, number] | null => {
      const p = landmarks[idx];
      if (!p) return null;
      return Array.isArray(p) ? [p[0], p[1]] : [p.x, p.y];
    };

    let first = true;
    for (const idx of FACE_OUTER_CONTOUR_INDICES) {
      const coords = getCoord(idx);
      if (!coords) continue;
      if (first) {
        ctx.moveTo(coords[0], coords[1]);
        first = false;
      } else {
        ctx.lineTo(coords[0], coords[1]);
      }
    }
    ctx.closePath();
    ctx.stroke();
    ctx.restore();
  }

  // 2. Draw Points (Nodes)
  if (drawPoints) {
    ctx.save();
    ctx.fillStyle = pointColor;
    for (const p of landmarks) {
      if (!p) continue;
      const px = Array.isArray(p) ? p[0] : p.x;
      const py = Array.isArray(p) ? p[1] : p.y;
      ctx.beginPath();
      ctx.arc(px, py, pointRadius, 0, 2 * Math.PI);
      ctx.fill();
    }
    ctx.restore();
  }
}

// ─────────────────────────────────────────────────────────────────────────
// CUSTOM REACT HOOK
// ─────────────────────────────────────────────────────────────────────────

export function useNormalizedFaceMesh({
  videoRef,
  canvasRef,
  active = true,
  facingMode = 'user',
  smoothingMethod = 'one-euro',
  emaAlpha = 0.4,
  oneEuroMinCutoff = 1.0,
  oneEuroBeta = 0.007,
  oneEuroDCutoff = 1.0,
  detectFaces,
  onFaceProcessed,
  onNoFace,
  onCameraError,
}: Config) {
  // Loop execution & stream references
  const rafRef = useRef<number | null>(null);
  const isRunningRef = useRef<boolean>(false);
  const streamRef = useRef<MediaStream | null>(null);

  // References for keeping state between frames (avoid re-rendering)
  const smoothedMeshRef = useRef<Point3D[] | null>(null);
  const offscreenCanvasRef = useRef<HTMLCanvasElement | null>(null);

  // Persistent One Euro filters for each landmark (up to 3 coordinates each)
  const oneEuroFiltersRef = useRef<{ x: OneEuroFilter; y: OneEuroFilter; z: OneEuroFilter }[]>([]);

  // Synchronize callbacks to avoid effect restarts
  const callbacksRef = useRef({
    detectFaces,
    onFaceProcessed,
    onNoFace,
    onCameraError,
  });

  useEffect(() => {
    callbacksRef.current = {
      detectFaces,
      onFaceProcessed,
      onNoFace,
      onCameraError,
    };
  }, [detectFaces, onFaceProcessed, onNoFace, onCameraError]);

  // Main processing loop triggered per frame
  const processFrame = useCallback(async (timestamp: number) => {
    if (!isRunningRef.current) return;

    const video = videoRef.current;
    const canvas = canvasRef.current;

    if (video && canvas && video.readyState >= 2 && video.videoWidth > 0) {
      // 1. Ensure Offscreen Canvas is setup for standard 640x480 crop
      if (!offscreenCanvasRef.current) {
        offscreenCanvasRef.current = document.createElement('canvas');
      }
      const offscreen = offscreenCanvasRef.current;
      if (offscreen.width !== STD_WIDTH || offscreen.height !== STD_HEIGHT) {
        offscreen.width = STD_WIDTH;
        offscreen.height = STD_HEIGHT;
      }

      const offCtx = offscreen.getContext('2d', { willReadFrequently: true });
      if (!offCtx) return;

      // 2. Center Crop Logic
      const videoWidth = video.videoWidth;
      const videoHeight = video.videoHeight;
      const videoRatio = videoWidth / videoHeight;
      const TARGET_RATIO = STD_WIDTH / STD_HEIGHT;

      let sourceWidth, sourceHeight, sourceX, sourceY;

      if (videoRatio > TARGET_RATIO) {
        // Video is wider than 4:3 (e.g. 16:9 laptop) -> cut left and right sides
        sourceHeight = videoHeight;
        sourceWidth = sourceHeight * TARGET_RATIO;
        sourceX = (videoWidth - sourceWidth) / 2;
        sourceY = 0;
      } else {
        // Video is taller than 4:3 (e.g. portrait tablet 9:16) -> cut top and bottom sides
        sourceWidth = videoWidth;
        sourceHeight = sourceWidth / TARGET_RATIO;
        sourceX = 0;
        sourceY = (videoHeight - sourceHeight) / 2;
      }

      // Reset transform before drawing
      offCtx.setTransform(1, 0, 0, 1, 0, 0);

      // Draw standard cropped video frame
      offCtx.drawImage(
        video,
        sourceX, sourceY, sourceWidth, sourceHeight,
        0, 0, STD_WIDTH, STD_HEIGHT
      );

      // Restore transform state
      offCtx.setTransform(1, 0, 0, 1, 0, 0);

      // 3. Call injected facial model on the standardized offscreen image
      let detection: any = null;
      try {
        detection = await callbacksRef.current.detectFaces(offscreen, timestamp);
      } catch (err) {
        console.warn('[useNormalizedFaceMesh] Face detection error:', err);
      }

      // 4. Align output canvas overlay to match standard 640x480 coordinates
      if (canvas.width !== STD_WIDTH) canvas.width = STD_WIDTH;
      if (canvas.height !== STD_HEIGHT) canvas.height = STD_HEIGHT;

      const ctx = canvas.getContext('2d');

      const meshData: Point3D[] | null = detection?.mesh || detection?.meshRaw || (Array.isArray(detection) ? detection : null);

      if (!detection || !meshData || meshData.length === 0) {
        // Clear screen and reset filters if face is lost
        if (ctx) ctx.clearRect(0, 0, STD_WIDTH, STD_HEIGHT);
        smoothedMeshRef.current = null;
        oneEuroFiltersRef.current = [];
        callbacksRef.current.onNoFace?.();
      } else {
        const rawMesh: Point3D[] = meshData;
        let smoothedMesh: Point3D[];

        // 4. Apply selected smoothing filter
        if (smoothingMethod === 'one-euro') {
          // Initialize One Euro Filters if shape mismatch
          if (oneEuroFiltersRef.current.length !== rawMesh.length) {
            oneEuroFiltersRef.current = rawMesh.map(() => ({
              x: new OneEuroFilter(oneEuroMinCutoff, oneEuroBeta, oneEuroDCutoff),
              y: new OneEuroFilter(oneEuroMinCutoff, oneEuroBeta, oneEuroDCutoff),
              z: new OneEuroFilter(oneEuroMinCutoff, oneEuroBeta, oneEuroDCutoff),
            }));
          }

          smoothedMesh = rawMesh.map((pt, i) => {
            const filters = oneEuroFiltersRef.current[i];
            const px = Array.isArray(pt) ? pt[0] : pt.x;
            const py = Array.isArray(pt) ? pt[1] : pt.y;
            const pz = Array.isArray(pt) ? (pt[2] ?? 0) : (pt.z ?? 0);

            const sx = filters.x.filter(px, timestamp);
            const sy = filters.y.filter(py, timestamp);
            const sz = filters.z.filter(pz, timestamp);

            return (Array.isArray(pt) ? [sx, sy, sz] : { x: sx, y: sy, z: sz }) as Point3D;
          });
          smoothedMeshRef.current = smoothedMesh;
        } else if (smoothingMethod === 'ema') {
          smoothedMesh = applyEMASmoothing(rawMesh, smoothedMeshRef.current, emaAlpha);
          smoothedMeshRef.current = smoothedMesh;
        } else {
          smoothedMesh = rawMesh;
        }

        // 5. Compute tight bounding box
        const bbox = computeBoundingBox(smoothedMesh);

        // 6. Normalize coordinates into Bounding-Box Local Coordinates
        const normalizedMesh = normalizeToLocalCoords(smoothedMesh, bbox);

        // 7. Output result back to caller
        if (ctx) {
          callbacksRef.current.onFaceProcessed?.({
            detection,
            smoothedMesh,
            normalizedMesh,
            boundingBox: bbox,
            ctx,
          });
        }
      }
    }

    // Schedule next frame
    if (isRunningRef.current) {
      rafRef.current = requestAnimationFrame(processFrame);
    }
  }, [
    videoRef,
    canvasRef,
    smoothingMethod,
    emaAlpha,
    oneEuroMinCutoff,
    oneEuroBeta,
    oneEuroDCutoff,
  ]);

  // Handle stream initialization and cleanup lifecycle
  useEffect(() => {
    if (!active) return;

    let cancelled = false;

    async function initCamera() {
      try {
        let stream: MediaStream;
        try {
          stream = await navigator.mediaDevices.getUserMedia({
            video: {
              width: { ideal: STD_WIDTH },
              height: { ideal: STD_HEIGHT },
              facingMode: facingMode,
            },
            audio: false,
          });
        } catch (constraintErr) {
          console.warn('[useNormalizedFaceMesh] Ideal constraints failed, falling back to basic video: true', constraintErr);
          stream = await navigator.mediaDevices.getUserMedia({
            video: true,
            audio: false,
          });
        }

        if (cancelled) {
          stream.getTracks().forEach((track: MediaStreamTrack) => track.stop());
          return;
        }

        streamRef.current = stream;

        const video = videoRef.current;
        if (video) {
          video.srcObject = stream;
          // Fix for Android WebView: Explicitly set muted and playsinline on the DOM node
          video.muted = true;
          video.setAttribute('playsinline', 'true');
          
          // Play stream safely
          await video.play().catch((e) => console.warn('[useNormalizedFaceMesh] AutoPlay failed:', e));
        }

        // Initialize frame loop
        isRunningRef.current = true;
        rafRef.current = requestAnimationFrame(processFrame);
      } catch (err) {
        console.error('[useNormalizedFaceMesh] Camera initialization error:', err);
        callbacksRef.current.onCameraError?.(err as Error);
      }
    }

    initCamera();

    return () => {
      cancelled = true;
      isRunningRef.current = false;

      if (rafRef.current) {
        cancelAnimationFrame(rafRef.current);
        rafRef.current = null;
      }

      if (streamRef.current) {
        streamRef.current.getTracks().forEach((track: MediaStreamTrack) => track.stop());
        streamRef.current = null;
      }

      smoothedMeshRef.current = null;
      oneEuroFiltersRef.current = [];
    };
  }, [active, facingMode, processFrame, videoRef]);
}
