import React from 'react';
import { FaceFrameOverlay } from '../FaceFrameTemplate';

export default function FaceScanSurface({
  videoRef,
  canvasRef,
  canvasSize = { width: 640, height: 480 },
  facingMode = 'user',
  mode = 'checkin',
  frameStateRef,
  modelsLoaded = true,
  modelStatusText = 'Memuat AI Model...',
  extraOverlay = null,
}) {
  const w = canvasSize?.width || 640;
  const h = canvasSize?.height || 480;
  const isUserFacing = facingMode === 'user';
  const mirrorStyle = { transform: isUserFacing ? 'scaleX(-1)' : 'scaleX(1)' };

  return (
    <div
      className="scan-surface"
      style={{
        aspectRatio: `${w} / ${h}`,
      }}
    >
      <video
        ref={videoRef}
        autoPlay
        muted
        playsInline
        style={mirrorStyle}
      />
      <canvas
        ref={canvasRef}
        className="overlay-canvas"
        style={mirrorStyle}
      />
      <FaceFrameOverlay
        mode={mode}
        stateRef={frameStateRef}
        width={w}
        height={h}
      />
      {extraOverlay}
      {!modelsLoaded && (
        <div className="loading-overlay">
          <div className="spinner" />
          <span>{modelStatusText}</span>
        </div>
      )}
    </div>
  );
}
