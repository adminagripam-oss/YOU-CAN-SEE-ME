const fs = require('fs');
const path = require('path');

const targetFile = path.join(__dirname, 'src', 'components', 'TabFaceVerification.jsx');
let content = fs.readFileSync(targetFile, 'utf8');

// 1. IMPORT
content = content.replace(
  "import { supabase } from '../supabaseClient';",
  "import { supabase } from '../supabaseClient';\nimport { Human } from '@vladmandic/human';\n\nconst humanConfig = {\n  modelBasePath: 'https://cdn.jsdelivr.net/npm/@vladmandic/human/models',\n  face: { enabled: true, mesh: true, iris: true, description: true },\n  body: { enabled: false },\n  hand: { enabled: false },\n  object: { enabled: false },\n  gesture: { enabled: false },\n};\nconst human = new Human(humanConfig);"
);

// 2. drawGeometricMesh
const drawMeshRegex = /function drawGeometricMesh\(ctx, pts, livenessDone, detectionScore\) \{[\s\S]*?\}\n\n\/\*\* Helper: Draw glowing iris circle/m;
content = content.replace(drawMeshRegex, `function drawGeometricMesh(ctx, pts, livenessDone, detectionScore) {
  if (!pts || pts.length < 478) return;

  const xs = pts.map(p => p[0]), ys = pts.map(p => p[1]);
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

  drawScannerCorners(ctx, minX, minY, maxX, maxY, R, G, B, livenessDone);

  ctx.fillStyle = \`rgba(\${R}, \${G}, \${B}, 0.6)\`;
  for (let i = 0; i < pts.length; i++) {
    const p = pts[i];
    if (!p) continue;
    ctx.beginPath();
    ctx.arc(p[0], p[1], 1.2, 0, 2 * Math.PI);
    ctx.fill();
  }
}

/** Helper: Draw glowing iris circle`);

// 3. calculateEAR
const calcEarRegex = /function calculateEAR\(pts, eye\) \{[\s\S]*?const FA_RIGHT_EYE = \{ p1: 42,  p4: 45,  p2: 43,  p6: 47,  p3: 44,  p5: 46 \};/m;
content = content.replace(calcEarRegex, `function calculateEAR(pts, eyePoints) {
  if (!pts || pts.length < 478) return 0.3;
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

const MP_LEFT_EYE  = [130, 133, 243, 27, 23, 119];
const MP_RIGHT_EYE = [359, 362, 255, 254, 339, 253];`);

// 4. detectionLoop window.faceapi
const detectRegex = /if \(!isDetecting && videoRef\.current && videoRef\.current\.readyState === 4 && window\.faceapi && modelsLoaded\) \{[\s\S]*?isDetecting = false;\n          \}/m;

const detectReplacement = `if (!isDetecting && videoRef.current && videoRef.current.readyState === 4 && modelsLoaded) {
            isDetecting = true;
            
            try {
              const lightingStatus = checkLightingQuality(videoRef.current);
              setLightingWarning(lightingStatus);

              // ── Human Detection ─────────────────────────────────────────────
              const result = await human.detect(videoRef.current);
              const detection = result && result.face && result.face.length > 0 ? result.face[0] : null;

              const videoWidth = videoRef.current?.videoWidth || 640;
              const videoHeight = videoRef.current?.videoHeight || 480;
              
              if (canvasRef.current) {
                canvasRef.current.width = videoWidth;
                canvasRef.current.height = videoHeight;
              }

              const ctx = canvasRef.current?.getContext('2d');
              
              if (ctx) {
                ctx.clearRect(0, 0, videoWidth, videoHeight);

                if (!detection) {
                  currentDescRef.current = null;
                  setLivenessStatusMsg('Harap posisikan wajah Anda di tengah layar');
                } else {
                  currentDescRef.current = Array.from(detection.embedding);

                  // ── EYE ASPECT RATIO (EAR) BLINK DETECTION (ANTI-SPOOFING) ─────────
                  const landmarksPos = detection.mesh; // 478 points
                  const leftEAR  = calculateEAR(landmarksPos, MP_LEFT_EYE);
                  const rightEAR = calculateEAR(landmarksPos, MP_RIGHT_EYE);
                  const avgEAR   = (leftEAR + rightEAR) / 2.0;
                  setCurrentEAR(parseFloat(avgEAR.toFixed(3)));

                  if (!livenessVerifiedRef.current) {
                    let passed = false;
                    const challenge = livenessChallengeRef.current;
                    
                    if (challenge === 'BLINK') {
                      setLivenessStatusMsg('Tantangan Keamanan: Kedipkan Mata Anda');
                      if (avgEAR < 0.21) {
                        eyeClosedRef.current = true;
                      } else if (eyeClosedRef.current && avgEAR > 0.24) {
                        passed = true;
                      }
                    } else if (challenge === 'TURN_LEFT' || challenge === 'TURN_RIGHT') {
                      setLivenessStatusMsg(challenge === 'TURN_LEFT' ? 'Tantangan Keamanan: Tolehkan Kepala ke KIRI' : 'Tantangan Keamanan: Tolehkan Kepala ke KANAN');
                      const yaw = detection.rotation?.angle?.yaw || 0;
                      
                      if (challenge === 'TURN_LEFT' && yaw > 0.30) passed = true;
                      if (challenge === 'TURN_RIGHT' && yaw < -0.30) passed = true;
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

                  // ── Step 2: 1-to-1 Match via Cosine Similarity ───────────
                  if (currentDescRef.current) {
                    let rawPct = 0;
                    let threshold = 60.0; // MediaPipe model threshold

                    if (masterVectorRef.current && currentDescRef.current.length === masterVectorRef.current.length) {
                      const cosSim = cosineSimilarity(currentDescRef.current, masterVectorRef.current);
                      rawPct = cosineToMatchPct(cosSim);
                    }

                    // --- SMOOTHING LOGIC ---
                    scoreHistoryRef.current.push(rawPct);
                    if (scoreHistoryRef.current.length > 5) {
                      scoreHistoryRef.current.shift();
                    }
                    const avgPct = scoreHistoryRef.current.reduce((a, b) => a + b, 0) / scoreHistoryRef.current.length;
                    
                    const matched = avgPct >= threshold;

                    matchRateRef.current = avgPct; isMatchedRef.current = matched;
                    setMatchRate(avgPct); setIsMatched(matched);

                    if (matched && !hasBeepedRef.current) {
                      hasBeepedRef.current = true;
                      playBeepSound();
                    }
                  }

                  // ── Draw Biometric Node Overlay ─────────────────────────────────
                  drawGeometricMesh(
                    ctx,
                    detection.mesh,
                    livenessVerifiedRef.current,
                    detection.score
                  );
                }
              }
            } catch (err) {
              console.warn("Detection loop error:", err);
            }
            
            isDetecting = false;
          }`;

content = content.replace(detectRegex, detectReplacement);

fs.writeFileSync(targetFile, content);
console.log('Refactoring applied successfully.');
