import React, { useState, useEffect } from 'react';
import Dexie from 'dexie';
import { createClient } from '@supabase/supabase-js';

// ==========================================
// 1. Setup Supabase
// (Sesuaikan URL dan Key dengan project Supabase Anda)
// ==========================================
const supabaseUrl = import.meta.env.VITE_SUPABASE_URL || 'https://xyzcompany.supabase.co';
const supabaseKey = import.meta.env.VITE_SUPABASE_ANON_KEY || 'public-anon-key';
const supabase = createClient(supabaseUrl, supabaseKey);

// ==========================================
// 2. Setup Dexie.js (Database Lokal)
// ==========================================
const db = new Dexie('OfflineDatabase');
db.version(1).stores({
  // Skema: ++id (auto-increment), dan kolom lainnya
  orders: '++id, customer_name, item, quantity, expiresAt'
});

const OfflineOrderForm = () => {
  // State untuk melacak status jaringan
  const [isOnline, setIsOnline] = useState(navigator.onLine);
  
  // State form
  const [formData, setFormData] = useState({
    customer_name: '',
    item: '',
    quantity: ''
  });
  
  // State untuk indikator status/pesan
  const [statusMsg, setStatusMsg] = useState('');

  // Batas waktu kedaluwarsa: 24 jam (dalam milidetik)
  const EXPIRATION_TIME_MS = 24 * 60 * 60 * 1000;

  // ==========================================
  // 3. Deteksi Jaringan
  // ==========================================
  useEffect(() => {
    const handleOnline = () => setIsOnline(true);
    const handleOffline = () => setIsOnline(false);

    window.addEventListener('online', handleOnline);
    window.addEventListener('offline', handleOffline);

    // Cleanup event listener
    return () => {
      window.removeEventListener('online', handleOnline);
      window.removeEventListener('offline', handleOffline);
    };
  }, []);

  // ==========================================
  // 4. Logika Sinkronisasi (Saat Online Kembali)
  // ==========================================
  useEffect(() => {
    if (isOnline) {
      syncData();
    }
  }, [isOnline]);

  const syncData = async () => {
    try {
      const now = Date.now();
      // Ambil semua data dari IndexedDB (Dexie)
      const offlineOrders = await db.orders.toArray();
      
      if (offlineOrders.length === 0) return;
      
      setStatusMsg('Menyinkronkan data tertunda...');

      const validOrders = [];
      const idsToDelete = [];

      // Pengecekan data
      offlineOrders.forEach(order => {
        if (now < order.expiresAt) {
          // Jika belum melewati expiresAt, siapkan untuk dikirim ke Supabase
          // Kita buang properti 'id' (lokal Dexie) dan 'expiresAt' agar sesuai skema Supabase
          const { id, expiresAt, ...supabaseData } = order;
          validOrders.push(supabaseData);
        }
        // Tandai id untuk dihapus dari Dexie (baik yang dikirim maupun yang diabaikan/kedaluwarsa)
        idsToDelete.push(order.id);
      });

      // Kirim data valid ke Supabase
      if (validOrders.length > 0) {
        const { error } = await supabase.from('orders').insert(validOrders);
        if (error) throw error;
      }

      // Hapus semua data yang sudah diproses (dikirim/diabaikan) dari Dexie
      if (idsToDelete.length > 0) {
        await db.orders.bulkDelete(idsToDelete);
      }

      setStatusMsg('Sinkronisasi selesai!');
      setTimeout(() => setStatusMsg(''), 3000); // Bersihkan pesan setelah 3 detik
    } catch (error) {
      console.error('Gagal menyinkronkan data:', error);
      setStatusMsg('Gagal menyinkronkan data ke server.');
    }
  };

  // ==========================================
  // 5. Handle Perubahan Input Form
  // ==========================================
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  // ==========================================
  // 6. Logika Submit (Simpan)
  // ==========================================
  const handleSubmit = async (e) => {
    e.preventDefault();
    setStatusMsg('Memproses...');

    try {
      if (isOnline) {
        // Jika Online: Insert langsung ke Supabase
        const { error } = await supabase.from('orders').insert([formData]);
        if (error) throw error;
        
        setStatusMsg('Data berhasil disimpan langsung ke server!');
      } else {
        // Jika Offline: Hitung expiresAt dan simpan ke Dexie
        const expiresAt = Date.now() + EXPIRATION_TIME_MS;
        const offlineData = { ...formData, expiresAt };
        
        await db.orders.add(offlineData);
        setStatusMsg('Anda sedang offline. Data disimpan sementara secara lokal.');
      }
      
      // Kosongkan form setelah submit
      setFormData({ customer_name: '', item: '', quantity: '' });
    } catch (error) {
      console.error('Error saving data:', error);
      setStatusMsg('Terjadi kesalahan saat menyimpan data.');
    }
  };

  // ==========================================
  // 7. UI Sederhana
  // ==========================================
  return (
    <div style={{ maxWidth: '400px', margin: '0 auto', padding: '20px', fontFamily: 'sans-serif' }}>
      <h2>Form Input Pesanan (Offline Support)</h2>
      
      {/* Indikator Status Jaringan */}
      <div style={{ 
        padding: '10px', 
        marginBottom: '20px', 
        backgroundColor: isOnline ? '#d4edda' : '#f8d7da',
        color: isOnline ? '#155724' : '#721c24',
        borderRadius: '5px',
        textAlign: 'center'
      }}>
        Status Jaringan: <strong>{isOnline ? 'ONLINE' : 'OFFLINE'}</strong>
      </div>

      <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column', gap: '15px' }}>
        <div>
          <label style={{ display: 'block', marginBottom: '5px' }}>Nama Pelanggan:</label>
          <input 
            type="text" 
            name="customer_name" 
            value={formData.customer_name} 
            onChange={handleChange} 
            required 
            style={{ width: '100%', padding: '8px', boxSizing: 'border-box' }}
          />
        </div>
        
        <div>
          <label style={{ display: 'block', marginBottom: '5px' }}>Item/Barang:</label>
          <input 
            type="text" 
            name="item" 
            value={formData.item} 
            onChange={handleChange} 
            required 
            style={{ width: '100%', padding: '8px', boxSizing: 'border-box' }}
          />
        </div>

        <div>
          <label style={{ display: 'block', marginBottom: '5px' }}>Jumlah (Quantity):</label>
          <input 
            type="number" 
            name="quantity" 
            value={formData.quantity} 
            onChange={handleChange} 
            required 
            style={{ width: '100%', padding: '8px', boxSizing: 'border-box' }}
          />
        </div>

        <button 
          type="submit" 
          style={{ 
            padding: '12px', 
            backgroundColor: '#007bff', 
            color: 'white', 
            border: 'none', 
            borderRadius: '5px', 
            cursor: 'pointer',
            fontSize: '16px'
          }}
        >
          Kirim Data
        </button>
      </form>

      {/* Indikator Pesan Sukses / Error */}
      {statusMsg && (
        <div style={{ 
          marginTop: '20px', 
          padding: '10px', 
          backgroundColor: '#e2e3e5', 
          borderRadius: '5px',
          textAlign: 'center' 
        }}>
          {statusMsg}
        </div>
      )}
    </div>
  );
};

export default OfflineOrderForm;
