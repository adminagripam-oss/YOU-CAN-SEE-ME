// Membersihkan akhiran status seperti "(Offline)" / "(Online)" dari nama,
// termasuk untuk sesi lama yang sudah tersimpan di storage.
export const cleanUserName = (name = '') =>
  String(name)
    .replace(/\s*[\(\[]\s*(offline|online)\s*[\)\]]\s*$/i, '')
    .trim();
