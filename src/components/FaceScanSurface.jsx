import React, { useRef, useState, useEffect } from 'react';
import { FaceFrameOverlay } from '../FaceFrameTemplate';

export default function FaceScanSurface({
  videoRef,
  canvasRef,
  canvasSize = { width: 640, height: 480 },
  facingMode = 'environment',
  mode = 'checkin',
  frameStateRef,
  modelsLoaded = true,
  modelStatusText = 'Memuat AI Model...',
  extraOverlay = null,
}) {
  const containerRef = useRef(null);
  const [boxSize, setBoxSize] = useState({ width: 0, height: 0 });
  const lastWarnRef = useRef(0);

  const targetW = canvasSize?.width || 640;
  const targetH = canvasSize?.height || 480;
  const isUserFacing = facingMode === 'user';
  const mirrorStyle = { transform: isUserFacing ? 'scaleX(-1)' : 'scaleX(1)' };

  useEffect(() => {
    const el = containerRef.current;
    if (!el) return;

    const updateSize = (entries) => {
      const entry = entries[0];
      if (!entry) return;
      const { width: stageW, height: stageH } = entry.contentRect;
      if (stageW <= 0 || stageH <= 0) return;

      const scale = Math.min(stageW / targetW, stageH / targetH);
      const boxW = Math.floor(targetW * scale);
      const boxH = Math.floor(targetH * scale);

      setBoxSize(prev => {
        if (prev.width === boxW && prev.height === boxH) return prev;
        return { width: boxW, height: boxH };
      });

      if (import.meta.env?.DEV) {
        const now = Date.now();
        if (now - lastWarnRef.current > 1000) {
          const boxRatio = boxW / boxH;
          const internalRatio = targetW / targetH;
          if (Math.abs(boxRatio - internalRatio) / internalRatio > 0.01) {
            console.warn('[SCAN] Rasio kotak != rasio kanvas, mesh akan terdistorsi', {
              box: [boxW, boxH],
              internal: [targetW, targetH],
              boxRatio,
              internalRatio,
            });
            lastWarnRef.current = now;
          }
        }
      }
    };

    const ro = new ResizeObserver(updateSize);
    ro.observe(el);

    return () => ro.disconnect();
  }, [targetW, targetH]);

  return (
    <div
      ref={containerRef}
      style={{
        width: '100%',
        height: '100%',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        overflow: 'hidden',
      }}
    >
      <div
        className="scan-surface"
        style={{
          width: boxSize.width ? `${boxSize.width}px` : '100%',
          height: boxSize.height ? `${boxSize.height}px` : '100%',
          aspectRatio: `${targetW} / ${targetH}`,
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
          width={targetW}
          height={targetH}
        />
        {extraOverlay}
        {!modelsLoaded && (
          <div className="loading-overlay">
            <div className="spinner" />
            <span>{modelStatusText}</span>
          </div>
        )}
      </div>
    </div>
  );
}
