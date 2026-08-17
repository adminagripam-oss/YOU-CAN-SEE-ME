import React, { useRef, useState, useCallback, useEffect } from 'react';
import { 
  useNormalizedFaceMesh, 
  drawFaceMeshOverlay,
  Point3D, 
  NormalizedLandmark, 
  BoundingBox, 
  FACE_OUTER_CONTOUR_INDICES 
} from '../hooks/useNormalizedFaceMesh';

// ─────────────────────────────────────────────────────────────────────────
// TYPES & INTERFACES FOR THE COMPONENT
// ─────────────────────────────────────────────────────────────────────────

export interface FaceMeshScannerProps {
  /** Optional custom face detection callback. If omitted, a high-fidelity mock generator is used. */
  detectFaces?: (canvas: HTMLCanvasElement, timestamp: number) => Promise<any> | any;
  /** Callback emitted when face processing completes. */
  onFaceOutput?: (data: {
    boundingBox: BoundingBox;
    normalizedMesh: NormalizedLandmark[];
    smoothedMesh: Point3D[];
  }) => void;
  /** Callback for status/error updates. */
  onStatusChange?: (status: string) => void;
}

export const FaceMeshScanner = ({
  detectFaces,
  onFaceOutput,
  onStatusChange,
}: FaceMeshScannerProps) => {
  // HTML Refs
  const videoRef = useRef<HTMLVideoElement | null>(null);
  const canvasRef = useRef<HTMLCanvasElement | null>(null);

  // Component Configurations
  const [active, setActive] = useState<boolean>(true);
  const [facingMode, setFacingMode] = useState<'user' | 'environment'>('user');
  const [smoothingMethod, setSmoothingMethod] = useState<'one-euro' | 'ema' | 'none'>('one-euro');
  
  // Filter tuning states
  const [emaAlpha, setEmaAlpha] = useState<number>(0.35);
  const [oneEuroMinCutoff, setOneEuroMinCutoff] = useState<number>(1.2);
  const [oneEuroBeta, setOneEuroBeta] = useState<number>(0.008);
  
  // Visual options
  const [drawPoints, setDrawPoints] = useState<boolean>(true);
  const [drawBorder, setDrawBorder] = useState<boolean>(true);
  
  // Live analytics states
  const [fps, setFps] = useState<number>(0);
  const [currentFace, setCurrentFace] = useState<{
    bbox: BoundingBox;
    landmarks: NormalizedLandmark[];
    rawPoints: Point3D[];
  } | null>(null);
  const [cameraStatus, setCameraStatus] = useState<string>('Initializing camera...');
  const [jitterFactor, setJitterFactor] = useState<number>(0);

  // Time & FPS calculation helpers
  const lastTimeRef = useRef<number>(0);
  const frameCountRef = useRef<number>(0);
  const fpsIntervalRef = useRef<any>(null);

  // Sync status changes
  useEffect(() => {
    onStatusChange?.(cameraStatus);
  }, [cameraStatus, onStatusChange]);

  // Update FPS every second
  useEffect(() => {
    fpsIntervalRef.current = setInterval(() => {
      setFps(frameCountRef.current);
      frameCountRef.current = 0;
    }, 1000);

    return () => {
      if (fpsIntervalRef.current) clearInterval(fpsIntervalRef.current);
    };
  }, []);

  // ─────────────────────────────────────────────────────────────────────────
  // DETECTOR LOGIC (Custom or Mock)
  // ─────────────────────────────────────────────────────────────────────────

  // High-fidelity simulated face generator for immediate offline testing/viewing
  const mockDetectFaces = useCallback((canvas: HTMLCanvasElement, timestamp: number) => {
    const t = timestamp / 1000.0;
    
    // Simulate circular head movement and tilt
    const baseCenterX = 320 + Math.sin(t * 1.5) * 35;
    const baseCenterY = 240 + Math.cos(t * 1.2) * 20;
    const scale = 1.0 + Math.sin(t * 0.8) * 0.08; // Simulate distance variance

    const faceW = 180 * scale;
    const faceH = 220 * scale;

    const mesh: Point3D[] = [];
    const totalPoints = 468;

    // We generate pseudo-random but cohesive 3D coordinates representing a facial topology
    for (let i = 0; i < totalPoints; i++) {
      // Standardize coordinates by mapping indices to spatial regions
      let angle = (i / totalPoints) * Math.PI * 2;
      let rx = Math.sin(angle);
      let ry = Math.cos(angle);
      let rz = Math.sin(angle * 2) * 0.5; // Depth representation

      // Add regional perturbations (distinguish nose, eyes, mouth)
      if (i === 4) { // Nose tip
        rx = 0; ry = 0; rz = 1.0;
      } else if (i === 152) { // Chin
        rx = 0; ry = 1.0; rz = -0.3;
      } else if (i === 10) { // Forehead center
        rx = 0; ry = -1.0; rz = -0.3;
      } else if (i === 133 || i === 362) { // Left/Right inner eye corners
        rx = i === 133 ? -0.35 : 0.35;
        ry = -0.2;
        rz = 0.2;
      } else if (i === 33 || i === 263) { // Left/Right outer eye corners
        rx = i === 33 ? -0.55 : 0.55;
        ry = -0.2;
        rz = 0.1;
      }

      // Add high-frequency simulated noise/jitter (more evident on camera)
      const noiseX = (Math.sin(timestamp * 0.2 + i) * 1.5);
      const noiseY = (Math.cos(timestamp * 0.25 + i) * 1.5);

      mesh.push({
        x: baseCenterX + rx * faceW + noiseX,
        y: baseCenterY + ry * faceH + noiseY,
        z: rz * 80 + (Math.sin(timestamp * 0.3 + i) * 1.0),
      });
    }

    return {
      mesh,
      score: 0.98,
      rotation: { yaw: Math.sin(t) * 15, pitch: Math.cos(t) * 10, roll: Math.sin(t * 0.5) * 5 },
    };
  }, []);

  const handleDetectFaces = useCallback(async (canvas: HTMLCanvasElement, timestamp: number) => {
    frameCountRef.current += 1;
    if (detectFaces) {
      return await detectFaces(canvas, timestamp);
    }
    // Fallback to high quality mock
    return mockDetectFaces(canvas, timestamp);
  }, [detectFaces, mockDetectFaces]);

  // ─────────────────────────────────────────────────────────────────────────
  // HOOK HANDLERS
  // ─────────────────────────────────────────────────────────────────────────

  const onFaceProcessed = useCallback((output: any) => {
    setCameraStatus('Scanning Active');
    
    // Draw visual feedback overlay
    const { ctx, smoothedMesh, normalizedMesh, boundingBox, detection } = output;
    
    // Clear canvas
    ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);

    // Call draw helper from hook
    drawFaceMeshOverlay(ctx, smoothedMesh, {
      drawPoints,
      drawBorder,
      pointColor: 'rgba(45, 212, 191, 0.75)', // Teal glowing nodes
      pointRadius: 2.2,
      borderColor: '#2dd4bf', // Glowing boundary line
      borderWidth: 2.5,
    });

    // Draw Bounding Box overlay
    ctx.save();
    ctx.strokeStyle = 'rgba(255, 255, 255, 0.25)';
    ctx.lineWidth = 1;
    ctx.setLineDash([4, 4]);
    ctx.strokeRect(boundingBox.minX, boundingBox.minY, boundingBox.width, boundingBox.height);
    
    // Center indicator
    ctx.fillStyle = '#f43f5e'; // Rose center dot
    ctx.beginPath();
    ctx.arc(boundingBox.centerX, boundingBox.centerY, 4, 0, 2 * Math.PI);
    ctx.fill();
    ctx.restore();

    // Calculate jitter score (variance of raw vs smoothed coordinates for node 4)
    if (detection?.mesh?.[4] && smoothedMesh[4]) {
      const rawNose = detection.mesh[4];
      const smoothNose = smoothedMesh[4];
      const rx = Array.isArray(rawNose) ? rawNose[0] : rawNose.x;
      const ry = Array.isArray(rawNose) ? rawNose[1] : rawNose.y;
      const sx = Array.isArray(smoothNose) ? smoothNose[0] : smoothNose.x;
      const sy = Array.isArray(smoothNose) ? smoothNose[1] : smoothNose.y;
      const dist = Math.sqrt(Math.pow(rx - sx, 2) + Math.pow(ry - sy, 2));
      setJitterFactor(dist);
    }

    // Update state variables for analytics display
    setCurrentFace({
      bbox: boundingBox,
      landmarks: normalizedMesh,
      rawPoints: smoothedMesh,
    });

    onFaceOutput?.({
      boundingBox,
      normalizedMesh,
      smoothedMesh,
    });
  }, [drawPoints, drawBorder, onFaceOutput]);

  const onNoFace = useCallback(() => {
    setCameraStatus('No Face Detected');
    setCurrentFace(null);
  }, []);

  const onCameraError = useCallback((err: Error) => {
    setCameraStatus(`Camera Error: ${err.message}`);
  }, []);

  // Hook activation
  useNormalizedFaceMesh({
    videoRef,
    canvasRef,
    active,
    facingMode,
    smoothingMethod,
    emaAlpha,
    oneEuroMinCutoff,
    oneEuroBeta,
    oneEuroDCutoff: 1.0,
    detectFaces: handleDetectFaces,
    onFaceProcessed,
    onNoFace,
    onCameraError,
  });

  // Extract critical landmarks for displaying on-screen stats
  const getNoseNode = () => {
    if (!currentFace) return null;
    return currentFace.landmarks[4];
  };

  const getChinNode = () => {
    if (!currentFace) return null;
    return currentFace.landmarks[152];
  };

  const nose = getNoseNode();
  const chin = getChinNode();

  return (
    <div className="face-scanner-wrap">
      {/* Vanilla CSS styles scoped to component */}
      <style dangerouslySetInnerHTML={{ __html: `
        .face-scanner-wrap {
          display: flex;
          flex-direction: row;
          gap: 24px;
          background: rgba(15, 23, 42, 0.45);
          backdrop-filter: blur(16px) saturate(120%);
          -webkit-backdrop-filter: blur(16px) saturate(120%);
          border: 1px solid rgba(255, 255, 255, 0.08);
          border-radius: 20px;
          padding: 24px;
          color: #f1f5f9;
          font-family: 'Inter', system-ui, -apple-system, sans-serif;
          max-width: 1040px;
          margin: 0 auto;
          box-shadow: 0 20px 40px rgba(0, 0, 0, 0.35);
        }

        .viewport-section {
          flex: 1.2;
          position: relative;
          display: flex;
          flex-direction: column;
          align-items: center;
        }

        .canvas-container {
          position: relative;
          width: 100%;
          max-width: 640px;
          aspect-ratio: 4/3;
          border-radius: 16px;
          overflow: hidden;
          background: #090d16;
          border: 2px solid rgba(255, 255, 255, 0.06);
          box-shadow: inset 0 0 20px rgba(0, 0, 0, 0.6);
        }

        .scanner-video {
          position: absolute;
          top: 0;
          left: 0;
          width: 100%;
          height: 100%;
          object-fit: cover;
          transform: scaleX(-1); /* Default mirror */
          opacity: 0.55;
        }
        
        .scanner-video.environment {
          transform: scaleX(1);
        }

        .scanner-canvas {
          position: absolute;
          top: 0;
          left: 0;
          width: 100%;
          height: 100%;
          z-index: 10;
          transform: scaleX(-1);
        }
        
        .scanner-canvas.environment {
          transform: scaleX(1);
        }

        /* Scanner Scan Line Animation */
        .scan-line {
          position: absolute;
          left: 0;
          right: 0;
          height: 3px;
          background: linear-gradient(90deg, rgba(45,212,191,0) 0%, rgba(45,212,191,0.85) 50%, rgba(45,212,191,0) 100%);
          box-shadow: 0 0 15px #2dd4bf;
          z-index: 12;
          animation: scanVertical 3.5s infinite ease-in-out;
          pointer-events: none;
        }

        @keyframes scanVertical {
          0% { top: 0%; opacity: 0; }
          10% { opacity: 0.8; }
          90% { opacity: 0.8; }
          100% { top: 100%; opacity: 0; }
        }

        .status-badge {
          position: absolute;
          top: 16px;
          left: 16px;
          z-index: 20;
          padding: 6px 14px;
          border-radius: 50px;
          font-size: 0.75rem;
          font-weight: 600;
          backdrop-filter: blur(12px);
          display: flex;
          align-items: center;
          gap: 6px;
          border: 1px solid rgba(255, 255, 255, 0.1);
        }

        .status-dot {
          width: 8px;
          height: 8px;
          border-radius: 50%;
        }

        .status-green { background: rgba(16, 185, 129, 0.2); color: #34d399; border-color: rgba(52, 211, 153, 0.2); }
        .status-green .status-dot { background: #10b981; box-shadow: 0 0 8px #10b981; }

        .status-yellow { background: rgba(245, 158, 11, 0.2); color: #fbbf24; border-color: rgba(251, 191, 36, 0.2); }
        .status-yellow .status-dot { background: #f59e0b; box-shadow: 0 0 8px #f59e0b; }

        .status-red { background: rgba(239, 68, 68, 0.2); color: #f87171; border-color: rgba(248, 113, 113, 0.2); }
        .status-red .status-dot { background: #ef4444; box-shadow: 0 0 8px #ef4444; }

        .fps-badge {
          position: absolute;
          top: 16px;
          right: 16px;
          z-index: 20;
          padding: 6px 12px;
          border-radius: 8px;
          font-size: 0.75rem;
          background: rgba(15, 23, 42, 0.75);
          color: #94a3b8;
          border: 1px solid rgba(255, 255, 255, 0.08);
          font-weight: bold;
        }

        .panel-section {
          flex: 0.8;
          display: flex;
          flex-direction: column;
          gap: 20px;
          min-width: 320px;
        }

        .panel-block {
          background: rgba(30, 41, 59, 0.35);
          border: 1px solid rgba(255, 255, 255, 0.05);
          border-radius: 14px;
          padding: 16px;
        }

        .panel-title {
          font-size: 0.85rem;
          text-transform: uppercase;
          letter-spacing: 0.1em;
          color: #94a3b8;
          margin-bottom: 12px;
          font-weight: 700;
          border-left: 3px solid #2dd4bf;
          padding-left: 8px;
        }

        .control-group {
          margin-bottom: 14px;
        }

        .control-group:last-child {
          margin-bottom: 0;
        }

        .control-label {
          display: flex;
          justify-content: space-between;
          font-size: 0.75rem;
          color: #cbd5e1;
          margin-bottom: 6px;
        }

        .control-value {
          color: #2dd4bf;
          font-weight: bold;
        }

        .control-input {
          width: 100%;
          height: 6px;
          border-radius: 3px;
          background: rgba(255, 255, 255, 0.1);
          outline: none;
          accent-color: #2dd4bf;
          -webkit-appearance: none;
        }

        .control-input::-webkit-slider-thumb {
          -webkit-appearance: none;
          appearance: none;
          width: 14px;
          height: 14px;
          border-radius: 50%;
          background: #2dd4bf;
          cursor: pointer;
          box-shadow: 0 0 6px #2dd4bf;
        }

        .select-field {
          width: 100%;
          background: #0f172a;
          color: #f1f5f9;
          border: 1px solid rgba(255, 255, 255, 0.15);
          border-radius: 8px;
          padding: 8px 12px;
          font-size: 0.85rem;
          outline: none;
          cursor: pointer;
        }

        .checkbox-group {
          display: flex;
          gap: 16px;
          margin-top: 10px;
        }

        .checkbox-label {
          display: flex;
          align-items: center;
          gap: 6px;
          font-size: 0.75rem;
          color: #cbd5e1;
          cursor: pointer;
        }

        .checkbox-input {
          accent-color: #2dd4bf;
          width: 14px;
          height: 14px;
          cursor: pointer;
        }

        .stats-grid {
          display: grid;
          grid-template-columns: repeat(2, 1fr);
          gap: 10px;
        }

        .stat-card {
          background: rgba(15, 23, 42, 0.4);
          border-radius: 8px;
          padding: 10px;
          border: 1px solid rgba(255, 255, 255, 0.04);
        }

        .stat-label {
          font-size: 0.65rem;
          color: #94a3b8;
          text-transform: uppercase;
        }

        .stat-val {
          font-size: 0.9rem;
          font-weight: 700;
          color: #f1f5f9;
          margin-top: 3px;
        }

        .landmark-stats {
          font-size: 0.7rem;
          color: #94a3b8;
          margin-top: 6px;
          line-height: 1.4;
          font-family: monospace;
          background: rgba(0, 0, 0, 0.2);
          padding: 8px;
          border-radius: 6px;
        }

        .btn-toggle {
          width: 100%;
          background: linear-gradient(135deg, #0d9488 0%, #0f766e 100%);
          color: white;
          border: none;
          padding: 10px 14px;
          border-radius: 8px;
          font-weight: 600;
          font-size: 0.85rem;
          cursor: pointer;
          transition: filter 0.2s;
          display: flex;
          justify-content: center;
          align-items: center;
          gap: 8px;
          box-shadow: 0 4px 12px rgba(13, 148, 136, 0.35);
        }

        .btn-toggle:hover {
          filter: brightness(1.1);
        }
        
        .btn-toggle.inactive {
          background: linear-gradient(135deg, #475569 0%, #334155 100%);
          box-shadow: none;
        }

        .mock-indicator {
          margin-top: 10px;
          font-size: 0.7rem;
          color: #fbbf24;
          text-align: center;
          background: rgba(245, 158, 11, 0.1);
          border: 1px solid rgba(245, 158, 11, 0.2);
          padding: 6px;
          border-radius: 6px;
        }
      ` }} />

      {/* VIEWPORT AREA */}
      <div className="viewport-section">
        <div className={`status-badge ${
          cameraStatus === 'Scanning Active' 
            ? 'status-green' 
            : cameraStatus === 'No Face Detected' 
              ? 'status-yellow' 
              : 'status-red'
        }`}>
          <span className="status-dot"></span>
          <span>{cameraStatus}</span>
        </div>

        <div className="fps-badge">
          FPS: {fps}
        </div>

        <div className="canvas-container">
          {/* Scan overlay line animation */}
          {cameraStatus === 'Scanning Active' && <div className="scan-line" />}
          
          <video
            ref={videoRef}
            className={`scanner-video ${facingMode}`}
            playsInline
            muted
          />
          <canvas
            ref={canvasRef}
            className={`scanner-canvas ${facingMode}`}
          />
        </div>
        
        {!detectFaces && (
          <div className="mock-indicator">
            ⚠️ Physical AI detector not connected. Simulating live high-fidelity Face Mesh movements.
          </div>
        )}
      </div>

      {/* ANALYTICS & CONTROL PANEL */}
      <div className="panel-section">
        {/* State Toggle */}
        <button 
          className={`btn-toggle ${!active ? 'inactive' : ''}`}
          onClick={() => setActive(!active)}
        >
          {active ? (
            <>
              <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5"><rect x="6" y="4" width="4" height="16" rx="1"/><rect x="14" y="4" width="4" height="16" rx="1"/></svg>
              PAUSE SCANNER
            </>
          ) : (
            <>
              <svg width="14" height="14" viewBox="0 0 24 24" fill="currentColor"><path d="M8 5v14l11-7z"/></svg>
              RESUME SCANNER
            </>
          )}
        </button>

        {/* Camera Config */}
        <div className="panel-block">
          <div className="panel-title">Camera Configuration</div>
          <div className="control-group">
            <label className="control-label">Camera Mode</label>
            <select 
              className="select-field"
              value={facingMode} 
              onChange={(e: React.ChangeEvent<HTMLSelectElement>) => setFacingMode(e.target.value as any)}
            >
              <option value="user">Front Facing (Selfie - Mirrored)</option>
              <option value="environment">Back Facing (Environment - Direct)</option>
            </select>
          </div>
        </div>

        {/* Smoothing Filters */}
        <div className="panel-block">
          <div className="panel-title">Smoothing Filters</div>
          <div className="control-group">
            <label className="control-label">Algorithm</label>
            <select 
              className="select-field"
              value={smoothingMethod} 
              onChange={(e: React.ChangeEvent<HTMLSelectElement>) => setSmoothingMethod(e.target.value as any)}
            >
              <option value="one-euro">One Euro Filter (Adaptive)</option>
              <option value="ema">Exponential Moving Average (EMA)</option>
              <option value="none">No Filter (Raw Jittery Coordinates)</option>
            </select>
          </div>

          {smoothingMethod === 'ema' && (
            <div className="control-group">
              <div className="control-label">
                <span>EMA Alpha (Weight of new frame)</span>
                <span className="control-value">{emaAlpha.toFixed(2)}</span>
              </div>
              <input 
                type="range" 
                className="control-input" 
                min="0.05" 
                max="1.0" 
                step="0.05"
                value={emaAlpha} 
                onChange={(e: React.ChangeEvent<HTMLInputElement>) => setEmaAlpha(parseFloat(e.target.value))}
              />
              <div style={{ fontSize: '0.6rem', color: '#64748b', marginTop: '4px' }}>
                Lower values = smoother coordinates but higher tracking delay/lag.
              </div>
            </div>
          )}

          {smoothingMethod === 'one-euro' && (
            <>
              <div className="control-group">
                <div className="control-label">
                  <span>Min Cutoff (Hz)</span>
                  <span className="control-value">{oneEuroMinCutoff.toFixed(1)} Hz</span>
                </div>
                <input 
                  type="range" 
                  className="control-input" 
                  min="0.1" 
                  max="5.0" 
                  step="0.1"
                  value={oneEuroMinCutoff} 
                  onChange={(e: React.ChangeEvent<HTMLInputElement>) => setOneEuroMinCutoff(parseFloat(e.target.value))}
                />
                <div style={{ fontSize: '0.6rem', color: '#64748b', marginTop: '4px' }}>
                  Lower values reduce jitter dramatically when the face remains static.
                </div>
              </div>

              <div className="control-group">
                <div className="control-label">
                  <span>Speed Coefficient (Beta)</span>
                  <span className="control-value">{oneEuroBeta.toFixed(4)}</span>
                </div>
                <input 
                  type="range" 
                  className="control-input" 
                  min="0.001" 
                  max="0.05" 
                  step="0.001"
                  value={oneEuroBeta} 
                  onChange={(e: React.ChangeEvent<HTMLInputElement>) => setOneEuroBeta(parseFloat(e.target.value))}
                />
                <div style={{ fontSize: '0.6rem', color: '#64748b', marginTop: '4px' }}>
                  Higher values reduce lag during rapid head gestures/movement.
                </div>
              </div>
            </>
          )}

          <div className="checkbox-group">
            <label className="checkbox-label">
              <input 
                type="checkbox" 
                className="checkbox-input"
                checked={drawPoints} 
                onChange={(e: React.ChangeEvent<HTMLInputElement>) => setDrawPoints(e.target.checked)} 
              />
              Render Nodes ({468})
            </label>
            <label className="checkbox-label">
              <input 
                type="checkbox" 
                className="checkbox-input"
                checked={drawBorder} 
                onChange={(e: React.ChangeEvent<HTMLInputElement>) => setDrawBorder(e.target.checked)} 
              />
              Render Border Contour
            </label>
          </div>
        </div>

        {/* Analytics Display */}
        <div className="panel-block">
          <div className="panel-title">Normalized Analytics</div>
          
          <div className="stats-grid">
            <div className="stat-card">
              <div className="stat-label">Origin Offset (C.x)</div>
              <div className="stat-val" style={{ color: '#2dd4bf' }}>
                {currentFace ? currentFace.bbox.centerX.toFixed(1) : '-'}
              </div>
            </div>
            <div className="stat-card">
              <div className="stat-label">Origin Offset (C.y)</div>
              <div className="stat-val" style={{ color: '#2dd4bf' }}>
                {currentFace ? currentFace.bbox.centerY.toFixed(1) : '-'}
              </div>
            </div>
            <div className="stat-card">
              <div className="stat-label">Face Bounding Box</div>
              <div className="stat-val">
                {currentFace ? `${Math.round(currentFace.bbox.width)}×${Math.round(currentFace.bbox.height)}` : '-'}
              </div>
            </div>
            <div className="stat-card">
              <div className="stat-label">Smooth Jitter Dev.</div>
              <div className="stat-val" style={{ color: jitterFactor > 2 ? '#ef4444' : '#10b981' }}>
                {currentFace ? `${jitterFactor.toFixed(3)}px` : '-'}
              </div>
            </div>
          </div>

          <div className="landmark-stats">
            <strong>Nose Tip (Index 4):</strong><br />
            - Raw (px): {currentFace && nose ? `X:${nose.rawX.toFixed(1)}, Y:${nose.rawY.toFixed(1)}, Z:${nose.rawZ.toFixed(1)}` : 'N/A'}<br />
            - Local (0..1): {currentFace && nose ? `X:${nose.localX.toFixed(4)}, Y:${nose.localY.toFixed(4)}, Z:${nose.localZ.toFixed(4)}` : 'N/A'}
          </div>

          <div className="landmark-stats">
            <strong>Chin Point (Index 152):</strong><br />
            - Raw (px): {currentFace && chin ? `X:${chin.rawX.toFixed(1)}, Y:${chin.rawY.toFixed(1)}, Z:${chin.rawZ.toFixed(1)}` : 'N/A'}<br />
            - Local (0..1): {currentFace && chin ? `X:${chin.localX.toFixed(4)}, Y:${chin.localY.toFixed(4)}, Z:${chin.localZ.toFixed(4)}` : 'N/A'}
          </div>
          
          <div style={{ fontSize: '0.6rem', color: '#64748b', marginTop: '10px', textAlign: 'center' }}>
            Note: Local Coordinates are relative to bounding box center (0,0) and normalized ($-0.5$ to $+0.5$).
          </div>
        </div>
      </div>
    </div>
  );
};
