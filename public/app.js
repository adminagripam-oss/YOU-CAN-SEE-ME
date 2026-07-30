/**
 * Sistem Absensi Biometrik Wajah 1-to-1 Verification Engine
 * Client-side Controller (app.js)
 */

// Robust API Base URL Fallback to prevent "Failed to fetch" when opened via file://
const API_BASE_URL = (window.location.origin && window.location.origin !== 'null' && !window.location.origin.startsWith('file'))
  ? ''
  : 'http://localhost:8080';

let modelsLoaded = false;
let currentVerifyDescriptor = null;
let currentEmpFormDescriptor = null;
let empFormMode = 'camera'; // 'camera' or 'file'

// CDN Model URI for face-api.js
const MODEL_URL = 'https://cdn.jsdelivr.net/npm/@vladmandic/face-api/model';

// DOM Initialization
document.addEventListener('DOMContentLoaded', () => {
  initTabs();
  initApp();
});

/**
 * Tab Navigation Setup
 */
function initTabs() {
  const tabButtons = document.querySelectorAll('.tab-btn');
  const tabContents = document.querySelectorAll('.tab-content');

  tabButtons.forEach(btn => {
    btn.addEventListener('click', () => {
      const targetTab = btn.getAttribute('data-tab');

      tabButtons.forEach(b => b.classList.remove('active'));
      tabContents.forEach(c => c.classList.remove('active'));

      btn.classList.add('active');
      const targetEl = document.getElementById(targetTab);
      if (targetEl) targetEl.classList.add('active');

      // Trigger webcams according to tab
      if (targetTab === 'tab-verify') {
        startCamera('verify-video', 'verify');
      } else if (targetTab === 'tab-employees') {
        loadEmployeeList();
        if (empFormMode === 'camera') {
          startCamera('emp-reg-video', 'employee-form');
        }
      } else if (targetTab === 'tab-logs') {
        loadAttendanceLogs();
      }
    });
  });
}

/**
 * Initialize Application: Load AI Models & Employees
 */
async function initApp() {
  try {
    const loaderText = document.getElementById('model-status-text');
    if (loaderText) loaderText.textContent = 'Memuat Model AI Biometrik (SsdMobilenetv1, Landmark68, Recognition)...';

    // Load face-api.js Neural Networks
    await Promise.all([
      faceapi.nets.ssdMobilenetv1.loadFromUri(MODEL_URL),
      faceapi.nets.faceLandmark68Net.loadFromUri(MODEL_URL),
      faceapi.nets.faceRecognitionNet.loadFromUri(MODEL_URL)
    ]);

    modelsLoaded = true;
    console.log('[FACE-API] Models loaded successfully!');

    const verifyLoader = document.getElementById('verify-loader');
    if (verifyLoader) verifyLoader.style.display = 'none';

    // Load initial dropdown & employee table
    await loadEmployeeList();

    // Start verification tab camera by default
    startCamera('verify-video', 'verify');
    setupEventListeners();

  } catch (error) {
    console.error('[ERROR] Gagal memuat model face-api.js:', error);
    const loaderText = document.getElementById('model-status-text');
    if (loaderText) {
      loaderText.textContent = 'Error: Gagal memuat model biometrik dari CDN. Pastikan koneksi internet aktif!';
      loaderText.style.color = '#ef4444';
    }
  }
}

/**
 * Start Webcam Stream & Attach Real-Time Face Detection Loop
 */
async function startCamera(videoId, mode) {
  if (!modelsLoaded) return;

  const video = document.getElementById(videoId);
  if (!video) return;

  // Stop previous stream if active
  if (video.srcObject) {
    video.srcObject.getTracks().forEach(track => track.stop());
  }

  try {
    const stream = await navigator.mediaDevices.getUserMedia({
      video: { width: { ideal: 640 }, height: { ideal: 480 }, facingMode: 'user' },
      audio: false
    });

    video.srcObject = stream;

    video.onloadedmetadata = () => {
      video.play();
      if (mode === 'verify') {
        startDetectionLoop(video, 'verify-canvas', 'verify');
      } else if (mode === 'employee-form') {
        startDetectionLoop(video, 'emp-reg-canvas', 'employee-form');
      }
    };
  } catch (err) {
    console.error(`[WEBCAM ERROR] Gagal mengakses kamera (${videoId}):`, err);
  }
}

/**
 * Real-time Face Detection Loop & Vector Extraction
 */
function startDetectionLoop(video, canvasId, mode) {
  const canvas = document.getElementById(canvasId);
  if (!canvas) return;

  const displaySize = { width: video.videoWidth || 640, height: video.videoHeight || 480 };
  faceapi.matchDimensions(canvas, displaySize);

  const btnVerify = document.getElementById('btn-do-verify');
  const faceDetectBadge = document.getElementById('face-detect-badge');
  const empFaceStatus = document.getElementById('emp-face-status');

  const intervalId = setInterval(async () => {
    if (!video.srcObject || video.paused || video.ended) {
      clearInterval(intervalId);
      return;
    }

    // Detect Single Face with Landmarks & 128-float Descriptor
    const detection = await faceapi
      .detectSingleFace(video, new faceapi.SsdMobilenetv1Options({ minConfidence: 0.5 }))
      .withFaceLandmarks()
      .withFaceDescriptor();

    const ctx = canvas.getContext('2d');
    ctx.clearRect(0, 0, canvas.width, canvas.height);

    if (detection) {
      const resizedDetection = faceapi.resizeResults(detection, displaySize);

      // Draw Bounding Box & Landmarks
      faceapi.draw.drawDetections(canvas, resizedDetection);
      faceapi.draw.drawFaceLandmarks(canvas, resizedDetection);

      // Extract Float32Array (128 dimensions)
      const descriptor = Array.from(detection.descriptor);

      if (mode === 'verify') {
        currentVerifyDescriptor = descriptor;
        const btnCheckIn = document.getElementById('btn-do-checkin');
        const btnCheckOut = document.getElementById('btn-do-checkout');
        if (btnCheckIn) btnCheckIn.disabled = false;
        if (btnCheckOut) btnCheckOut.disabled = false;

        if (faceDetectBadge) {
          faceDetectBadge.textContent = 'Wajah Terdeteksi (Siap Absen)';
          faceDetectBadge.style.color = 'var(--accent-success)';
        }
      } else if (mode === 'employee-form' && empFormMode === 'camera') {
        currentEmpFormDescriptor = descriptor;
        if (empFaceStatus) {
          empFaceStatus.textContent = 'Wajah Terdeteksi (Ready Master)';
          empFaceStatus.style.color = 'var(--accent-success)';
        }
      }
    } else {
      if (mode === 'verify') {
        currentVerifyDescriptor = null;
        const btnCheckIn = document.getElementById('btn-do-checkin');
        const btnCheckOut = document.getElementById('btn-do-checkout');
        if (btnCheckIn) btnCheckIn.disabled = true;
        if (btnCheckOut) btnCheckOut.disabled = true;

        if (faceDetectBadge) {
          faceDetectBadge.textContent = 'Wajah Tidak Terdeteksi';
          faceDetectBadge.style.color = 'var(--accent-error)';
        }
      } else if (mode === 'employee-form' && empFormMode === 'camera') {
        currentEmpFormDescriptor = null;
        if (empFaceStatus) {
          empFaceStatus.textContent = 'Menunggu Wajah di Kamera...';
          empFaceStatus.style.color = 'var(--accent-warning)';
        }
      }
    }
  }, 200);
}

/**
 * Setup Event Listeners
 */
function setupEventListeners() {
  // Sync Employee Selection in Verification Tab
  const selectVerify = document.getElementById('verify-employee-select');
  const inputNik = document.getElementById('verify-nik-input');

  if (selectVerify) {
    selectVerify.addEventListener('change', async () => {
      const selectedOpt = selectVerify.options[selectVerify.selectedIndex];
      if (selectedOpt && selectedOpt.value) {
        inputNik.value = selectedOpt.getAttribute('data-nik') || '';
        // Check today's attendance status for this employee
        await checkTodayAttendanceStatus(selectedOpt.value);
      } else {
        // Reset to default when no employee selected
        resetAttendanceButtons();
      }
    });
  }

  // Dual Action Buttons: Check-In and Check-Out
  const btnCheckIn = document.getElementById('btn-do-checkin');
  const btnCheckOut = document.getElementById('btn-do-checkout');

  if (btnCheckIn) {
    btnCheckIn.addEventListener('click', () => perform1to1Verification('CHECK_IN'));
  }
  if (btnCheckOut) {
    btnCheckOut.addEventListener('click', () => perform1to1Verification('CHECK_OUT'));
  }

  // Employee Form Camera vs File Mode Switch
  const btnModeCamera = document.getElementById('btn-mode-camera');
  const btnModeFile = document.getElementById('btn-mode-file');
  const empCameraWrapper = document.getElementById('emp-camera-wrapper');
  const empFileWrapper = document.getElementById('emp-file-wrapper');

  if (btnModeCamera && btnModeFile) {
    btnModeCamera.addEventListener('click', () => {
      empFormMode = 'camera';
      btnModeCamera.classList.add('btn-primary');
      btnModeFile.classList.remove('btn-primary');
      btnModeFile.style.background = 'rgba(255,255,255,0.1)';

      empCameraWrapper.style.display = 'block';
      empFileWrapper.style.display = 'none';

      startCamera('emp-reg-video', 'employee-form');
    });

    btnModeFile.addEventListener('click', () => {
      empFormMode = 'file';
      btnModeFile.classList.add('btn-primary');
      btnModeFile.style.background = '';
      btnModeCamera.classList.remove('btn-primary');
      btnModeCamera.style.background = 'rgba(255,255,255,0.1)';

      empCameraWrapper.style.display = 'none';
      empFileWrapper.style.display = 'block';

      // Stop camera stream
      const video = document.getElementById('emp-reg-video');
      if (video && video.srcObject) {
        video.srcObject.getTracks().forEach(t => t.stop());
      }
    });
  }

  // Photo File Upload Event Handler
  const empPhotoInput = document.getElementById('emp-photo-input');
  if (empPhotoInput) {
    empPhotoInput.addEventListener('change', handlePhotoFileUpload);
  }

  // Form Add Employee Submit
  const formAddEmp = document.getElementById('form-add-employee');
  if (formAddEmp) {
    formAddEmp.addEventListener('submit', handleAddEmployeeWithFaceSubmit);
  }

  // Refresh Logs & Clear All Logs Buttons
  const btnRefreshLogs = document.getElementById('btn-refresh-logs');
  if (btnRefreshLogs) {
    btnRefreshLogs.addEventListener('click', loadAttendanceLogs);
  }

  const btnClearAllLogs = document.getElementById('btn-clear-all-logs');
  if (btnClearAllLogs) {
    btnClearAllLogs.addEventListener('click', confirmClearAllAttendanceLogs);
  }

  // Edit Employee Form Modal Listeners
  const formEditEmp = document.getElementById('form-edit-employee');
  if (formEditEmp) {
    formEditEmp.addEventListener('submit', handleEditEmployeeSubmit);
  }

  const btnCloseEdit = document.getElementById('btn-close-edit-modal');
  const btnCancelEdit = document.getElementById('btn-cancel-edit');
  if (btnCloseEdit) btnCloseEdit.addEventListener('click', closeEditEmpModal);
  if (btnCancelEdit) btnCancelEdit.addEventListener('click', closeEditEmpModal);
}

/**
 * Open Modal to Edit Employee Data
 */
function openEditEmpModal(id, nik, name, dept) {
  document.getElementById('edit-emp-id').value = id;
  document.getElementById('edit-emp-nik').value = nik;
  document.getElementById('edit-emp-name').value = name;
  document.getElementById('edit-emp-dept').value = dept;

  const msgBox = document.getElementById('edit-form-msg');
  if (msgBox) msgBox.style.display = 'none';

  const modal = document.getElementById('modal-edit-emp');
  if (modal) modal.style.display = 'flex';
}

/**
 * Close Modal Edit Employee Data
 */
function closeEditEmpModal() {
  const modal = document.getElementById('modal-edit-emp');
  if (modal) modal.style.display = 'none';
}

/**
 * Handle Edit Employee Form Submit (PUT Request)
 */
async function handleEditEmployeeSubmit(e) {
  e.preventDefault();
  const id = document.getElementById('edit-emp-id').value;
  const nik = document.getElementById('edit-emp-nik').value.trim();
  const name = document.getElementById('edit-emp-name').value.trim();
  const dept = document.getElementById('edit-emp-dept').value.trim();
  const msgBox = document.getElementById('edit-form-msg');
  const btnSave = document.getElementById('btn-save-edit');

  btnSave.disabled = true;
  btnSave.innerHTML = '<i class="fa-solid fa-spinner fa-spin"></i> Menyimpan...';

  try {
    const res = await fetch(`${API_BASE_URL}/api/employees/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ nik, name, department: dept })
    });

    const data = await res.json();
    msgBox.className = 'result-box ' + (data.success ? 'success' : 'error');
    msgBox.textContent = data.message;
    msgBox.style.display = 'block';

    if (data.success) {
      await loadEmployeeList();
      setTimeout(() => closeEditEmpModal(), 1200);
    }
  } catch (err) {
    msgBox.className = 'result-box error';
    msgBox.textContent = 'Error: ' + err.message;
    msgBox.style.display = 'block';
  } finally {
    btnSave.disabled = false;
    btnSave.innerHTML = '<i class="fa-solid fa-floppy-disk"></i> Simpan Perubahan';
  }
}

/**
 * Custom Shadcn UI Style Toast Alert
 */
function showShadcnToast(title, description, type = 'info') {
  const container = document.getElementById('custom-toast-container');
  if (!container) return;

  const iconClass = type === 'success' ? 'fa-circle-check' : (type === 'error' ? 'fa-circle-xmark' : 'fa-circle-info');

  const toast = document.createElement('div');
  toast.className = `shadcn-alert ${type}`;
  toast.innerHTML = `
    <div class="alert-icon"><i class="fa-solid ${iconClass}"></i></div>
    <div class="alert-content">
      <div class="alert-title">${title}</div>
      <div class="alert-description">${description}</div>
    </div>
  `;

  container.appendChild(toast);

  setTimeout(() => {
    toast.style.opacity = '0';
    toast.style.transform = 'translateX(50px)';
    setTimeout(() => toast.remove(), 300);
  }, 4000);
}

/**
 * Custom Shadcn UI Style Confirm Modal
 */
let pendingConfirmCallback = null;

function showShadcnConfirm(title, message, onConfirm) {
  const modal = document.getElementById('modal-confirm');
  const titleEl = document.getElementById('confirm-title');
  const msgEl = document.getElementById('confirm-message');
  const btnAction = document.getElementById('btn-confirm-action');
  const btnCancel = document.getElementById('btn-confirm-cancel');

  if (!modal) return;

  if (titleEl) titleEl.textContent = title;
  if (msgEl) msgEl.textContent = message;

  pendingConfirmCallback = onConfirm;

  modal.style.display = 'flex';

  const cleanup = () => {
    modal.style.display = 'none';
    btnAction.removeEventListener('click', handleAction);
    btnCancel.removeEventListener('click', cleanup);
  };

  const handleAction = async () => {
    cleanup();
    if (pendingConfirmCallback) await pendingConfirmCallback();
  };

  btnAction.addEventListener('click', handleAction);
  btnCancel.addEventListener('click', cleanup);
}

/**
 * Confirm and Delete Employee from Supabase (Using Custom Shadcn Modal)
 */
function confirmDeleteEmployee(id, name) {
  showShadcnConfirm(
    `Hapus Karyawan "${name}"?`,
    `Apakah Anda yakin ingin menghapus karyawan "${name}" dari database? Semua data biometrik master dan log absensi karyawan ini akan ikut terhapus secara permanen.`,
    async () => {
      try {
        const res = await fetch(`${API_BASE_URL}/api/employees/${id}`, {
          method: 'DELETE'
        });

        const data = await res.json();
        if (data.success) {
          showShadcnToast('Penghapusan Berhasil', `Karyawan "${name}" telah berhasil dihapus dari database .`, 'success');
          await loadEmployeeList();
          loadAttendanceLogs();
        } else {
          showShadcnToast('Gagal Menghapus', data.message, 'error');
        }
      } catch (err) {
        console.error('[DELETE ERROR]:', err);
        showShadcnToast('Error Sistem', err.message, 'error');
      }
    }
  );
}

/**
 * Confirm and Delete Single Attendance Log Entry (Session) from Supabase
 */
function confirmDeleteSingleAttendanceLog(logId, empName, timeStr) {
  showShadcnConfirm(
    `Hapus Session Log Absensi?`,
    `Apakah Anda yakin ingin menghapus catatan log absensi untuk "${empName}" (${timeStr}) dari database ?`,
    async () => {
      try {
        const res = await fetch(`${API_BASE_URL}/api/attendance/logs/${logId}`, {
          method: 'DELETE'
        });

        const data = await res.json();
        if (data.success) {
          showShadcnToast('Log Dihapus', data.message, 'success');
          loadAttendanceLogs();
          // Re-check status for currently selected employee
          const selVal = document.getElementById('verify-employee-select')?.value;
          if (selVal) await checkTodayAttendanceStatus(selVal);
        } else {
          showShadcnToast('Gagal Menghapus Log', data.message, 'error');
        }
      } catch (err) {
        console.error('[DELETE LOG ERROR]:', err);
        showShadcnToast('Error Sistem', err.message, 'error');
      }
    }
  );
}

/**
 * Confirm and Delete ALL Attendance Logs from Supabase
 */
function confirmClearAllAttendanceLogs() {
  showShadcnConfirm(
    `Hapus SELURUH Riwayat Log Absensi?`,
    `PERINGATAN HAPUS TOTAL: Apakah Anda benar-benar yakin ingin menghapus SELURUH riwayat log absensi di database ? Tindakan ini tidak dapat dibatalkan!`,
    async () => {
      try {
        const res = await fetch(`${API_BASE_URL}/api/attendance/logs`, {
          method: 'DELETE'
        });

        const data = await res.json();
        if (data.success) {
          showShadcnToast('Seluruh Log Berhasil Dihapus', data.message, 'success');
          loadAttendanceLogs();
          // Reset employee status buttons
          const selVal = document.getElementById('verify-employee-select')?.value;
          if (selVal) await checkTodayAttendanceStatus(selVal);
        } else {
          showShadcnToast('Gagal Menghapus Log', data.message, 'error');
        }
      } catch (err) {
        console.error('[CLEAR ALL LOGS ERROR]:', err);
        showShadcnToast('Error Sistem', err.message, 'error');
      }
    }
  );
}

/**
 * Handle Photo File Upload & Extract Master Descriptor
 */
async function handlePhotoFileUpload(e) {
  const file = e.target.files[0];
  const empFaceStatus = document.getElementById('emp-face-status');
  const previewImg = document.getElementById('emp-photo-preview');
  const filenameTxt = document.getElementById('emp-photo-filename');

  if (!file) return;

  if (filenameTxt) filenameTxt.textContent = `File: ${file.name} (${(file.size / 1024).toFixed(1)} KB)`;
  if (empFaceStatus) {
    empFaceStatus.textContent = 'Mengekstrak biometrik dari foto...';
    empFaceStatus.style.color = 'var(--accent-warning)';
  }

  try {
    // Convert file to HTMLImageElement
    const img = await faceapi.bufferToImage(file);
    if (previewImg) {
      previewImg.src = img.src;
      previewImg.style.display = 'inline-block';
    }

    // Detect Single Face Descriptor from Image File
    const detection = await faceapi
      .detectSingleFace(img, new faceapi.SsdMobilenetv1Options({ minConfidence: 0.5 }))
      .withFaceLandmarks()
      .withFaceDescriptor();

    if (detection) {
      currentEmpFormDescriptor = Array.from(detection.descriptor);
      if (empFaceStatus) {
        empFaceStatus.textContent = 'Wajah Berhasil Diekstrak (128-float)!';
        empFaceStatus.style.color = 'var(--accent-success)';
      }
    } else {
      currentEmpFormDescriptor = null;
      if (empFaceStatus) {
        empFaceStatus.textContent = 'Gagal: Wajah tidak terdeteksi pada foto!';
        empFaceStatus.style.color = 'var(--accent-error)';
      }
      alert('Wajah tidak terdeteksi pada file foto yang diunggah. Pastikan foto jelas dan menampilkan 1 wajah.');
    }
  } catch (err) {
    console.error('[PHOTO UPLOAD ERROR]:', err);
    if (empFaceStatus) {
      empFaceStatus.textContent = 'Error membaca foto: ' + err.message;
      empFaceStatus.style.color = 'var(--accent-error)';
    }
  }
}

/**
 * Fetch & Populate Employee Dropdowns and Table from Supabase
 */
async function loadEmployeeList() {
  try {
    const res = await fetch(`${API_BASE_URL}/api/employees`);
    const data = await res.json();

    if (!data.success) return;

    const employees = data.data;

    // Populate Verification Dropdown
    const selectVerify = document.getElementById('verify-employee-select');
    if (selectVerify) {
      selectVerify.innerHTML = '<option value="">-- Pilih Karyawan --</option>';
      employees.forEach(emp => {
        const hasMasterStr = emp.has_master_biometric ? ' (Siap)' : ' (Belum)';
        const option = document.createElement('option');
        option.value = emp.id;
        option.setAttribute('data-nik', emp.nik);
        option.textContent = `${emp.nik} - ${emp.name}${hasMasterStr}`;
        selectVerify.appendChild(option);
      });
    }

    // Populate Employee Table
    const tableBody = document.getElementById('employee-table-body');
    if (tableBody) {
      if (employees.length === 0) {
        tableBody.innerHTML = '<tr><td colspan="5" style="text-align:center;">Belum ada data karyawan.</td></tr>';
      } else {
        tableBody.innerHTML = employees.map(emp => {
          const safeNik = emp.nik.replace(/'/g, "\\'");
          const safeName = emp.name.replace(/'/g, "\\'");
          const safeDept = emp.department.replace(/'/g, "\\'");
          return `
            <tr>
              <td><strong>${emp.nik}</strong></td>
              <td>${emp.name}</td>
              <td>${emp.department}</td>
              <td>
                ${emp.has_master_biometric
              ? '<span class="status-badge success"><i class="fa-solid fa-check-circle"></i> Siap</span>'
              : '<span class="status-badge fail"><i class="fa-solid fa-triangle-exclamation"></i> Belum</span>'}
              </td>
              <td>
                <button type="button" class="btn-action edit" onclick="openEditEmpModal(${emp.id}, '${safeNik}', '${safeName}', '${safeDept}')">
                  <i class="fa-solid fa-pen-to-square"></i> Edit
                </button>
                <button type="button" class="btn-action delete" onclick="confirmDeleteEmployee(${emp.id}, '${safeName}')">
                  <i class="fa-solid fa-trash"></i> Hapus
                </button>
              </td>
            </tr>
          `;
        }).join('');
      }
    }

  } catch (err) {
    console.error('[LOAD EMPLOYEES ERROR]:', err);
  }
}

/**
 * Reset attendance buttons to default (Check-In visible, Check-Out hidden)
 */
function resetAttendanceButtons() {
  const btnCheckIn = document.getElementById('btn-do-checkin');
  const btnCheckOut = document.getElementById('btn-do-checkout');
  const statusBar = document.getElementById('attendance-status-bar');
  const statusLabel = document.getElementById('attendance-status-label');
  const checkinTimeRow = document.getElementById('checkin-time-row');

  if (btnCheckIn) { btnCheckIn.style.display = 'block'; btnCheckIn.disabled = true; }
  if (btnCheckOut) { btnCheckOut.style.display = 'none'; btnCheckOut.disabled = true; }
  if (statusBar) statusBar.style.display = 'none';
  if (statusLabel) statusLabel.textContent = '—';
  if (checkinTimeRow) checkinTimeRow.style.display = 'none';
}

/**
 * Check today's attendance status for a specific employee
 * Shows Check-In button if not yet checked in, Check-Out if already checked in
 */
async function checkTodayAttendanceStatus(employeeId) {
  const btnCheckIn = document.getElementById('btn-do-checkin');
  const btnCheckOut = document.getElementById('btn-do-checkout');
  const statusBar = document.getElementById('attendance-status-bar');
  const statusLabel = document.getElementById('attendance-status-label');
  const checkinTimeRow = document.getElementById('checkin-time-row');
  const checkinTimeDisplay = document.getElementById('checkin-time-display');

  try {
    const res = await fetch(`${API_BASE_URL}/api/attendance/status/${employeeId}`);
    const data = await res.json();

    if (!data.success) return;

    const isCheckedIn = data.checked_in;

    // Show status bar
    if (statusBar) statusBar.style.display = 'block';

    if (isCheckedIn) {
      // Employee has checked in but NOT checked out yet — show Check-Out button
      if (btnCheckIn) { btnCheckIn.style.display = 'none'; btnCheckIn.disabled = true; }
      if (btnCheckOut) { btnCheckOut.style.display = 'block'; btnCheckOut.disabled = !currentVerifyDescriptor; }

      if (statusLabel) {
        statusLabel.innerHTML = `<span class="status-badge success"><i class="fa-solid fa-right-to-bracket"></i> Sudah Check-In</span>`;
      }
      if (checkinTimeRow && data.check_in_time) {
        checkinTimeRow.style.display = 'block';
        const t = new Date(data.check_in_time);
        if (checkinTimeDisplay) checkinTimeDisplay.textContent = t.toLocaleTimeString('id-ID', { hour: '2-digit', minute: '2-digit', second: '2-digit' });
      }
    } else {
      // Employee has NOT checked in (or already checked out) — show Check-In button
      if (btnCheckIn) { btnCheckIn.style.display = 'block'; btnCheckIn.disabled = !currentVerifyDescriptor; }
      if (btnCheckOut) { btnCheckOut.style.display = 'none'; btnCheckOut.disabled = true; }

      if (statusLabel) {
        statusLabel.innerHTML = `<span class="status-badge fail"><i class="fa-solid fa-circle-xmark"></i> Belum Check-In Hari Ini</span>`;
      }
      if (checkinTimeRow) checkinTimeRow.style.display = 'none';
    }

  } catch (err) {
    console.error('[STATUS CHECK ERROR]:', err);
    resetAttendanceButtons();
  }
}

/**
 * Execute 1-to-1 Face Verification Request to Backend
 */
/**
 * Execute 1-to-1 Face Verification Request to Backend (Check-In / Check-Out)
 */
async function perform1to1Verification(attendanceType = 'CHECK_IN') {
  const selectVerify = document.getElementById('verify-employee-select');
  const inputNik = document.getElementById('verify-nik-input');
  const resultBox = document.getElementById('verify-result');
  const btnCheckIn = document.getElementById('btn-do-checkin');
  const btnCheckOut = document.getElementById('btn-do-checkout');

  const employeeId = selectVerify.value;
  const nik = inputNik.value.trim();

  // Error Handling: Missing Employee ID / NIK
  if (!employeeId && !nik) {
    showShadcnToast('Peringatan Absensi', 'Mohon pilih karyawan dari dropdown atau masukkan NIK!', 'error');
    return;
  }

  // Error Handling: Missing Face Detection
  if (!currentVerifyDescriptor || currentVerifyDescriptor.length !== 128) {
    showShadcnToast('Deteksi Wajah Gagal', 'Wajah tidak terdeteksi oleh kamera! Pastikan wajah Anda terlihat jelas.', 'error');
    return;
  }

  if (btnCheckIn) btnCheckIn.disabled = true;
  if (btnCheckOut) btnCheckOut.disabled = true;

  try {
    const payload = {
      employee_id: employeeId ? parseInt(employeeId) : null,
      nik: nik || null,
      scan_descriptor: currentVerifyDescriptor,
      location: 'Kantor Pusat - Lobby Absensi',
      attendance_type: attendanceType
    };

    const response = await fetch(`${API_BASE_URL}/api/attendance/verify`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(payload)
    });

    const data = await response.json();
    const isSuccess = data.success;
    const typeLabel = attendanceType === 'CHECK_OUT' ? 'CHECK-OUT' : 'CHECK-IN';

    // 1. Fill Metrics Info at TOP (sesuai instruksi user)
    const metricDist = document.getElementById('metric-distance');
    const metricThresh = document.getElementById('metric-threshold');
    const metricType = document.getElementById('metric-type');
    const metricTime = document.getElementById('metric-time');

    if (data.metrics) {
      if (metricDist) metricDist.textContent = data.metrics.euclidean_distance.toFixed(4);
      if (metricThresh) metricThresh.textContent = data.metrics.threshold.toFixed(4);
      if (metricType) metricType.textContent = typeLabel;
      if (metricTime) metricTime.textContent = new Date().toLocaleTimeString('id-ID');
    }

    // 2. Render Shadcn UI Alert Component
    resultBox.className = 'shadcn-alert ' + (isSuccess ? 'success' : 'error');
    resultBox.style.display = 'flex';

    const iconEl = document.getElementById('result-icon');
    const titleEl = document.getElementById('result-title');
    const msgEl = document.getElementById('result-message');

    if (iconEl) {
      iconEl.innerHTML = isSuccess
        ? `<i class="fa-solid fa-circle-check" style="color: #34d399;"></i>`
        : `<i class="fa-solid fa-circle-xmark" style="color: #f87171;"></i>`;
    }

    if (titleEl) {
      titleEl.textContent = isSuccess ? `VERIFIKASI BERHASIL (${typeLabel})` : `VERIFIKASI GAGAL (${typeLabel})`;
    }

    if (msgEl) {
      msgEl.textContent = data.message;
    }

    // Floating Toast Notification
    showShadcnToast(
      isSuccess ? `Absensi ${typeLabel} Berhasil` : `Absensi ${typeLabel} Gagal`,
      data.message,
      isSuccess ? 'success' : 'error'
    );

    // Refresh Log History
    loadAttendanceLogs();

    // Re-check attendance status to flip Check-In ↔ Check-Out button
    if (isSuccess) {
      const selVal = document.getElementById('verify-employee-select')?.value;
      if (selVal) await checkTodayAttendanceStatus(selVal);
    }

  } catch (error) {
    console.error('[VERIFY ERROR]:', error);
    resultBox.className = 'shadcn-alert error';
    resultBox.style.display = 'flex';

    const iconEl = document.getElementById('result-icon');
    const titleEl = document.getElementById('result-title');
    const msgEl = document.getElementById('result-message');

    if (iconEl) iconEl.innerHTML = `<i class="fa-solid fa-circle-xmark" style="color: #f87171;"></i>`;
    if (titleEl) titleEl.textContent = 'ERROR SISTEM';
    if (msgEl) msgEl.textContent = 'Terjadi kesalahan koneksi ke server API: ' + error.message;

  } finally {
    // Do NOT blindly re-enable both — let checkTodayAttendanceStatus manage visibility
    // Only re-enable if face is still detected
    if (currentVerifyDescriptor && currentVerifyDescriptor.length === 128) {
      const selVal = document.getElementById('verify-employee-select')?.value;
      if (!selVal) {
        // No employee selected — reset to default
        if (btnCheckIn) btnCheckIn.disabled = false;
      }
    }
  }
}

/**
 * Handle Add Employee Form Submit & Register Master Face Descriptor Together
 */
async function handleAddEmployeeWithFaceSubmit(e) {
  e.preventDefault();
  const nik = document.getElementById('emp-nik').value.trim();
  const name = document.getElementById('emp-name').value.trim();
  const dept = document.getElementById('emp-dept').value.trim();
  const msgBox = document.getElementById('emp-form-msg');
  const btnSubmit = document.getElementById('btn-submit-emp');

  btnSubmit.disabled = true;
  btnSubmit.innerHTML = '<i class="fa-solid fa-spinner fa-spin"></i> Menyimpan Karyawan & Biometrik...';

  try {
    // 1. Add Employee to Supabase Database
    const resEmp = await fetch(`${API_BASE_URL}/api/employees`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ nik, name, department: dept })
    });

    const dataEmp = await resEmp.json();

    if (!dataEmp.success) {
      msgBox.className = 'result-box error';
      msgBox.textContent = 'Gagal menyimpan karyawan: ' + dataEmp.message;
      return;
    }

    const newEmpId = dataEmp.data.id;
    let masterMsg = '';

    // 2. Register Master Biometric Descriptor if face captured/uploaded
    if (currentEmpFormDescriptor && currentEmpFormDescriptor.length === 128) {
      const resMaster = await fetch(`${API_BASE_URL}/api/biometrics/register`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          employee_id: newEmpId,
          descriptor: currentEmpFormDescriptor
        })
      });
      const dataMaster = await resMaster.json();
      if (dataMaster.success) {
        masterMsg = ' dan Master Biometrik Wajah berhasil disimpan';
      }
    } else {
      masterMsg = ' (Catatan: Foto/wajah belum terdeteksi saat pendaftaran).';
    }

    msgBox.className = 'result-box success';
    msgBox.innerHTML = `✅ <strong>Berhasil!</strong> Karyawan <strong>${name} (${nik})</strong>${masterMsg}`;

    // Reset Form
    document.getElementById('form-add-employee').reset();
    currentEmpFormDescriptor = null;

    const previewImg = document.getElementById('emp-photo-preview');
    if (previewImg) previewImg.style.display = 'none';

    await loadEmployeeList();

  } catch (err) {
    console.error('[ADD EMP ERROR]:', err);
    msgBox.className = 'result-box error';
    msgBox.textContent = 'Terjadi kesalahan: ' + err.message;
  } finally {
    btnSubmit.disabled = false;
    btnSubmit.innerHTML = '<i class="fa-solid fa-floppy-disk"></i> Simpan Karyawan & Master Biometrik';
  }
}

/**
 * Fetch & Render Attendance Logs History Table (shadcn UI Table style)
 */
async function loadAttendanceLogs() {
  const tableBody = document.getElementById('logs-table-body');
  const footer = document.getElementById('logs-table-footer');
  const totalCount = document.getElementById('logs-total-count');
  if (!tableBody) return;

  try {
    const res = await fetch(`${API_BASE_URL}/api/attendance/logs`);
    const data = await res.json();

    if (!data.success || data.data.length === 0) {
      tableBody.innerHTML = `
        <tr class="shadcn-tr-empty">
          <td colspan="8">
            <div style="display:flex; flex-direction:column; align-items:center; gap:8px; padding: 2.5rem 0; color: var(--text-muted);">
              <i class="fa-regular fa-clock" style="font-size: 2rem; opacity:0.35;"></i>
              <span style="font-size:0.9rem;">Belum ada log absensi yang tercatat.</span>
            </div>
          </td>
        </tr>`;
      if (footer) footer.style.display = 'none';
      return;
    }

    tableBody.innerHTML = data.data.map(log => {
      const isSuccess = log.status.includes('BERHASIL');
      const badgeClass = isSuccess ? 'success' : 'fail';

      // Timestamp: split date and time
      const tsDate = new Date(log.timestamp);
      const datePart = tsDate.toLocaleDateString('id-ID', { day: '2-digit', month: 'short', year: 'numeric' });
      const timePart = tsDate.toLocaleTimeString('id-ID', { hour: '2-digit', minute: '2-digit', second: '2-digit' });

      // Tipe badge
      const isCheckOut = (log.attendance_type === 'CHECK-OUT') || log.status.includes('CHECK-OUT') || (log.location && log.location.includes('CHECK-OUT'));
      const typeBadge = isCheckOut
        ? `<span class="status-badge" style="background: rgba(245,158,11,0.18); color: #fbbf24; border: 1px solid rgba(245,158,11,0.35); gap: 4px;"><i class="fa-solid fa-right-from-bracket"></i> OUT</span>`
        : `<span class="status-badge success" style="gap:4px;"><i class="fa-solid fa-right-to-bracket"></i> IN</span>`;

      // Durasi badge — only for CHECK-OUT rows that have a pairing
      let durasiCell = `<span style="color: var(--text-muted); font-size: 0.8rem;">—</span>`;
      if (isCheckOut && log.durasi) {
        durasiCell = `<span class="durasi-badge"><i class="fa-regular fa-clock"></i> ${log.durasi}</span>`;
      } else if (!isCheckOut && isSuccess) {
        durasiCell = `<span class="durasi-badge checkin"><i class="fa-solid fa-right-to-bracket"></i> Sedang Bekerja</span>`;
      }

      // Location — strip [CHECK-IN] / [CHECK-OUT] suffix for cleaner display
      const cleanLocation = (log.location || '').replace(/\s*\[CHECK-(IN|OUT)\]/g, '');
      const safeName = (log.name || 'Karyawan').replace(/'/g, "\\'");

      return `
        <tr>
          <td>
            <div class="time-cell">
              <span class="date-part">${datePart}</span>
              <span class="time-part">${timePart}</span>
            </div>
          </td>
          <td>${typeBadge}</td>
          <td style="font-weight: 600; color: #e2e8f0;">${log.nik}</td>
          <td>${log.name}</td>
          <td style="color: var(--text-muted); font-size:0.82rem;">${log.department}</td>
          <td>${durasiCell}</td>
          <td><span class="status-badge ${badgeClass}" style="font-size:0.72rem;">${isSuccess ? 'BERHASIL' : 'GAGAL'}</span></td>
          <td style="color: var(--text-muted); font-size: 0.8rem;">${cleanLocation}</td>
          <td>
            <button type="button" class="btn-action delete" style="padding: 4px 8px; font-size: 0.75rem;" onclick="confirmDeleteSingleAttendanceLog(${log.id}, '${safeName}', '${timePart}')">
              <i class="fa-solid fa-trash"></i> Hapus
            </button>
          </td>
        </tr>
      `;
    }).join('');

    // Update footer count
    if (footer) footer.style.display = '';
    if (totalCount) totalCount.textContent = data.data.length;

  } catch (err) {
    console.error('[LOAD LOGS ERROR]:', err);
  }
}
