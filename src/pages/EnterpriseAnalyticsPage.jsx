import React, { useState } from 'react';

export default function EnterpriseAnalyticsPage({ employees = [], logs = [] }) {
  const [selectedTimeframe, setSelectedTimeframe] = useState('12M');
  const [searchQuery, setSearchQuery] = useState('');
  const [statusFilter, setStatusFilter] = useState('ALL');
  const [activeTab, setActiveTab] = useState('overview');

  // Dynamic Metrics from Database
  const totalEmployeesCount = employees.length;
  const verifiedLogsCount = logs.filter(
    (l) => l.status && (l.status.includes('BERHASIL') || l.status.includes('Hadir') || l.status.includes('Verified'))
  ).length;
  const attendancePercentage =
    totalEmployeesCount > 0 ? ((verifiedLogsCount / totalEmployeesCount) * 100).toFixed(1).replace('.', ',') + '%' : '0%';

  // Dynamically convert database attendance logs into table transactions
  const transactions = logs.map((log, index) => {
    const emp = employees.find((e) => String(e.id) === String(log.employee_id) || String(e.nik) === String(log.nik));
    const name = log.name || emp?.name || `Karyawan #${log.employee_id}`;
    const nik = log.nik || emp?.nik || `ID-${log.employee_id}`;
    const dateStr = log.timestamp
      ? new Date(log.timestamp).toLocaleString('id-ID', {
          day: '2-digit',
          month: 'short',
          year: 'numeric',
          hour: '2-digit',
          minute: '2-digit'
        })
      : '-';
    const isSuccess =
      log.status && (log.status.includes('BERHASIL') || log.status.includes('Hadir') || log.status.includes('Verified'));

    return {
      id: `LOG-${log.id || index + 1}`,
      user: {
        name,
        email: `${nik.toLowerCase()}@kebun.co.id`,
        nik,
        avatar: `https://api.dicebear.com/7.x/initials/svg?seed=${encodeURIComponent(name)}`
      },
      category: emp?.department || log.department || 'Operasional Kebun',
      date: dateStr,
      amount: log.euclidean_distance !== undefined && log.euclidean_distance !== null ? `Dist: ${log.euclidean_distance}` : '-',
      method: log.attendance_type || 'Biometric 1-to-1',
      status: isSuccess ? 'Completed' : 'Failed'
    };
  });

  const filteredTransactions = transactions.filter((tx) => {
    const matchesSearch =
      tx.user.name.toLowerCase().includes(searchQuery.toLowerCase()) ||
      tx.user.nik.toLowerCase().includes(searchQuery.toLowerCase()) ||
      tx.id.toLowerCase().includes(searchQuery.toLowerCase());
    const matchesStatus = statusFilter === 'ALL' || tx.status.toUpperCase() === statusFilter;
    return matchesSearch && matchesStatus;
  });

  return (
    <div className="bg-slate-900 min-h-screen text-slate-50 font-sans flex flex-col md:flex-row antialiased selection:bg-blue-500 selection:text-white">
      {/* 1. SIDEBAR NAVIGATION */}
      <aside className="w-full md:w-64 bg-slate-950/60 backdrop-blur-xl border-r border-slate-800 flex flex-col shrink-0">
        {/* Brand Logo Header */}
        <div className="h-16 px-6 flex items-center justify-between border-b border-slate-800/80">
          <div className="flex items-center gap-3">
            <div className="w-9 h-9 rounded-xl bg-blue-600 flex items-center justify-center text-white shadow-lg shadow-blue-500/25">
              <i className="fa-solid fa-chart-line text-lg"></i>
            </div>
            <div>
              <span className="font-extrabold text-lg tracking-tight text-slate-100 block leading-none">
                Agri<span className="text-blue-500">Face</span>
              </span>
              <span className="text-[10px] font-semibold text-slate-400 tracking-wider uppercase">Analytics Suite</span>
            </div>
          </div>
        </div>

        {/* Sidebar Nav Links */}
        <nav className="p-4 space-y-1.5 flex-1 overflow-y-auto">
          <div className="px-3 py-2 text-[11px] font-bold text-slate-500 uppercase tracking-wider">Main Menu</div>

          <button
            onClick={() => setActiveTab('overview')}
            className={`w-full flex items-center gap-3 px-3.5 py-2.5 rounded-xl text-sm font-semibold transition-all ${
              activeTab === 'overview'
                ? 'bg-blue-600/20 text-blue-400 border-r-4 border-blue-500 shadow-sm'
                : 'text-slate-400 hover:text-slate-200 hover:bg-slate-800/60'
            }`}
          >
            <i className="fa-solid fa-grid-2 text-base w-5 text-center"></i>
            <span>Executive Dashboard</span>
          </button>

          <button
            onClick={() => setActiveTab('analytics')}
            className={`w-full flex items-center gap-3 px-3.5 py-2.5 rounded-xl text-sm font-semibold transition-all ${
              activeTab === 'analytics'
                ? 'bg-blue-600/20 text-blue-400 border-r-4 border-blue-500 shadow-sm'
                : 'text-slate-400 hover:text-slate-200 hover:bg-slate-800/60'
            }`}
          >
            <i className="fa-solid fa-chart-pie text-base w-5 text-center"></i>
            <span>Real-Time Metrics</span>
          </button>

          <button
            onClick={() => setActiveTab('transactions')}
            className={`w-full flex items-center gap-3 px-3.5 py-2.5 rounded-xl text-sm font-semibold transition-all ${
              activeTab === 'transactions'
                ? 'bg-blue-600/20 text-blue-400 border-r-4 border-blue-500 shadow-sm'
                : 'text-slate-400 hover:text-slate-200 hover:bg-slate-800/60'
            }`}
          >
            <i className="fa-solid fa-receipt text-base w-5 text-center"></i>
            <span>Transactions Ledger</span>
          </button>

          <button
            onClick={() => setActiveTab('users')}
            className={`w-full flex items-center gap-3 px-3.5 py-2.5 rounded-xl text-sm font-semibold transition-all ${
              activeTab === 'users'
                ? 'bg-blue-600/20 text-blue-400 border-r-4 border-blue-500 shadow-sm'
                : 'text-slate-400 hover:text-slate-200 hover:bg-slate-800/60'
            }`}
          >
            <i className="fa-solid fa-users text-base w-5 text-center"></i>
            <span>User Intelligence</span>
          </button>

          <div className="pt-6 px-3 py-2 text-[11px] font-bold text-slate-500 uppercase tracking-wider">System</div>

          <button
            onClick={() => setActiveTab('settings')}
            className={`w-full flex items-center gap-3 px-3.5 py-2.5 rounded-xl text-sm font-semibold transition-all ${
              activeTab === 'settings'
                ? 'bg-blue-600/20 text-blue-400 border-r-4 border-blue-500 shadow-sm'
                : 'text-slate-400 hover:text-slate-200 hover:bg-slate-800/60'
            }`}
          >
            <i className="fa-solid fa-gear text-base w-5 text-center"></i>
            <span>System Settings</span>
          </button>
        </nav>

        {/* Sidebar Footer Status */}
        <div className="p-4 border-t border-slate-800/80">
          <div className="bg-slate-900/90 border border-slate-800 p-3 rounded-xl flex items-center gap-3">
            <div className="relative">
              <div className="w-2.5 h-2.5 rounded-full bg-emerald-500 animate-ping absolute"></div>
              <div className="w-2.5 h-2.5 rounded-full bg-emerald-500 relative"></div>
            </div>
            <div>
              <div className="text-xs font-semibold text-slate-200">Supabase Cloud Live</div>
              <div className="text-[10px] text-slate-400">Latency: 18ms • Port 8080</div>
            </div>
          </div>
        </div>
      </aside>

      {/* MAIN CONTENT AREA */}
      <div className="flex-1 flex flex-col min-w-0 overflow-y-auto">
        {/* 2. TOP HEADER */}
        <header className="h-16 px-6 bg-slate-900/90 backdrop-blur-md border-b border-slate-800 flex items-center justify-between sticky top-0 z-30">
          {/* Search Bar */}
          <div className="relative w-64 md:w-96">
            <i className="fa-solid fa-magnifying-glass absolute left-3.5 top-1/2 -translate-y-1/2 text-slate-400 text-sm"></i>
            <input
              type="text"
              placeholder="Cari transaksi, karyawan, NIK, atau laporan..."
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
              className="w-full bg-slate-800/90 border border-slate-700/80 rounded-xl pl-10 pr-4 py-2 text-sm text-slate-50 placeholder-slate-400 focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500 shadow-inner transition-all"
            />
          </div>

          {/* Right Action Icons & User Avatar */}
          <div className="flex items-center gap-4">
            <button
              type="button"
              className="relative p-2 text-slate-400 hover:text-slate-200 hover:bg-slate-800 rounded-xl transition-all"
              title="Notifikasi Sistem"
            >
              <i className="fa-solid fa-bell text-lg"></i>
              <span className="absolute top-1.5 right-1.5 w-2 h-2 bg-blue-500 rounded-full"></span>
            </button>

            <div className="h-6 w-[1px] bg-slate-800"></div>

            {/* User Profile */}
            <div className="flex items-center gap-3 cursor-pointer p-1.5 hover:bg-slate-800/60 rounded-xl transition-all">
              <img
                src="https://images.unsplash.com/photo-1534528741775-53994a69daeb?w=100&auto=format&fit=crop&q=80"
                alt="Alex Rivera"
                className="w-9 h-9 rounded-full object-cover ring-2 ring-blue-500/40"
              />
              <div className="hidden sm:block text-left">
                <div className="text-sm font-semibold text-slate-50 leading-tight">Alex Rivera</div>
                <div className="text-[11px] text-slate-400">Chief Analytics Officer</div>
              </div>
              <i className="fa-solid fa-chevron-down text-xs text-slate-400 hidden sm:block"></i>
            </div>
          </div>
        </header>

        {/* BODY CONTAINER */}
        <main className="p-6 space-y-6 flex-1">
          {/* Section Header Title */}
          <div className="flex flex-col md:flex-row md:items-center justify-between gap-4">
            <div>
              <h1 className="text-2xl font-extrabold text-slate-50 tracking-tight">AgriFace Dashboard Monitoring Pekerjaan Kebun</h1>
              <p className="text-sm text-slate-400 mt-1">Monitoring KPI Produktivitas Panen Pemanen, Kehadiran, &amp; Luasan Area Afdeling.</p>
            </div>

            {/* Timeframe Filter Buttons */}
            <div className="flex items-center bg-slate-800 p-1 rounded-xl border border-slate-700/60 self-start">
              {['7D', '30D', '12M'].map((tf) => (
                <button
                  key={tf}
                  onClick={() => setSelectedTimeframe(tf)}
                  className={`px-3 py-1.5 text-xs font-bold rounded-lg transition-all ${
                    selectedTimeframe === tf
                      ? 'bg-blue-600 text-white shadow-md'
                      : 'text-slate-400 hover:text-slate-200'
                  }`}
                >
                  {tf}
                </button>
              ))}
            </div>
          </div>

          {/* 3. METRIC CARDS (CLEAN NON-REDUNDANT UNIT BADGE DESIGN) */}
          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-5 gap-4">
            {/* Card 1: Protas */}
            <div className="bg-slate-800 border border-slate-700/60 p-5 rounded-2xl shadow-lg hover:border-slate-600 transition-all flex flex-col justify-between">
              <div className="flex items-center justify-between">
                <span className="text-base font-bold text-slate-400">Protas</span>
                <span className="text-xs font-medium text-slate-400 bg-slate-700/80 px-2.5 py-1 rounded-lg border border-slate-600/40">
                  Ton/Ha
                </span>
              </div>
              <div className="mt-4 text-3.5xl font-black text-slate-50 tracking-tight">
                3,87
              </div>
            </div>

            {/* Card 2: Output Panen (Kg/HK) */}
            <div className="bg-slate-800 border border-slate-700/60 p-5 rounded-2xl shadow-lg hover:border-slate-600 transition-all flex flex-col justify-between">
              <div className="flex items-center justify-between">
                <span className="text-base font-bold text-slate-400">Output Panen</span>
                <span className="text-xs font-medium text-slate-400 bg-slate-700/80 px-2.5 py-1 rounded-lg border border-slate-600/40">
                  Kg/HK
                </span>
              </div>
              <div className="mt-4 text-3.5xl font-black text-slate-50 tracking-tight">
                1,245.0
              </div>
            </div>

            {/* Card 3: Output Area (Ha/HK) */}
            <div className="bg-slate-800 border border-slate-700/60 p-5 rounded-2xl shadow-lg hover:border-slate-600 transition-all flex flex-col justify-between">
              <div className="flex items-center justify-between">
                <span className="text-base font-bold text-slate-400">Output Area</span>
                <span className="text-xs font-medium text-slate-400 bg-slate-700/80 px-2.5 py-1 rounded-lg border border-slate-600/40">
                  Ha/HK
                </span>
              </div>
              <div className="mt-4 text-3.5xl font-black text-slate-50 tracking-tight">
                2,45
              </div>
            </div>

            {/* Card 4: Persentase Kehadiran Pemanen */}
            <div className="bg-slate-800 border border-slate-700/60 p-5 rounded-2xl shadow-lg hover:border-slate-600 transition-all flex flex-col justify-between">
              <div className="flex items-center justify-between">
                <span className="text-base font-bold text-slate-400">Kehadiran</span>
                <span className="text-xs font-medium text-emerald-400 bg-emerald-500/10 px-2.5 py-1 rounded-lg border border-emerald-500/20">
                  % Absensi
                </span>
              </div>
              <div className="mt-4 text-3.5xl font-black text-emerald-400 tracking-tight">
                {attendancePercentage}
              </div>
            </div>

            {/* Card 5: Total Pemanen */}
            <div className="bg-slate-800 border border-slate-700/60 p-5 rounded-2xl shadow-lg hover:border-slate-600 transition-all flex flex-col justify-between">
              <div className="flex items-center justify-between">
                <span className="text-base font-bold text-slate-400">Total Pekerja</span>
                <span className="text-xs font-medium text-slate-400 bg-slate-700/80 px-2.5 py-1 rounded-lg border border-slate-600/40">
                  Orang
                </span>
              </div>
              <div className="mt-4 text-3.5xl font-black text-slate-50 tracking-tight">
                {totalEmployeesCount.toLocaleString('id-ID')}
              </div>
            </div>
          </div>

          {/* 4. CHART SECTION (GRID LAYOUT) */}
          <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
            {/* Line Chart Utama (2 Columns) */}
            <div className="lg:col-span-2 bg-slate-800 border border-slate-700/60 p-6 rounded-2xl shadow-lg flex flex-col justify-between">
              <div className="flex items-center justify-between mb-4">
                <div>
                  <h3 className="text-base font-extrabold text-slate-50">Tren Pendapatan &amp; Pertumbuhan</h3>
                  <p className="text-xs text-slate-400">Grafik pergerakan omzet tahunan dalam skala 12 bulan.</p>
                </div>
                <div className="flex items-center gap-2">
                  <span className="w-3 h-3 rounded-full bg-blue-500 inline-block"></span>
                  <span className="text-xs text-slate-400">2026 Actual</span>
                </div>
              </div>

              {/* Custom SVG Line Chart */}
              <div className="relative w-full h-64 mt-4">
                <svg className="w-full h-full overflow-visible" viewBox="0 0 500 200" preserveAspectRatio="none">
                  {/* Grid Lines */}
                  <line x1="0" y1="40" x2="500" y2="40" stroke="#334155" strokeDasharray="4 4" strokeWidth="1" />
                  <line x1="0" y1="90" x2="500" y2="90" stroke="#334155" strokeDasharray="4 4" strokeWidth="1" />
                  <line x1="0" y1="140" x2="500" y2="140" stroke="#334155" strokeDasharray="4 4" strokeWidth="1" />
                  <line x1="0" y1="190" x2="500" y2="190" stroke="#334155" strokeWidth="1" />

                  {/* Gradient Area Fill */}
                  <defs>
                    <linearGradient id="chartGradient" x1="0" y1="0" x2="0" y2="1">
                      <stop offset="0%" stopColor="#3b82f6" stopOpacity="0.45" />
                      <stop offset="100%" stopColor="#3b82f6" stopOpacity="0.0" />
                    </linearGradient>
                  </defs>

                  <path
                    d="M 0,160 Q 50,140 100,100 T 200,80 T 300,120 T 400,40 T 500,60 L 500,190 L 0,190 Z"
                    fill="url(#chartGradient)"
                  />

                  {/* Smooth Line Curve */}
                  <path
                    d="M 0,160 Q 50,140 100,100 T 200,80 T 300,120 T 400,40 T 500,60"
                    fill="none"
                    stroke="#3b82f6"
                    strokeWidth="3.5"
                    strokeLinecap="round"
                  />

                  {/* Glowing Data Dots */}
                  <circle cx="100" cy="100" r="5" fill="#3b82f6" stroke="#0f172a" strokeWidth="2" />
                  <circle cx="200" cy="80" r="5" fill="#3b82f6" stroke="#0f172a" strokeWidth="2" />
                  <circle cx="300" cy="120" r="5" fill="#3b82f6" stroke="#0f172a" strokeWidth="2" />
                  <circle cx="400" cy="40" r="6" fill="#60a5fa" stroke="#0f172a" strokeWidth="2" className="animate-pulse" />
                  <circle cx="500" cy="60" r="5" fill="#3b82f6" stroke="#0f172a" strokeWidth="2" />
                </svg>
              </div>

              {/* Month Labels */}
              <div className="flex justify-between text-xs text-slate-400 font-semibold pt-4 border-t border-slate-700/60 mt-2">
                <span>Jan</span>
                <span>Mar</span>
                <span>Mei</span>
                <span>Jul</span>
                <span>Sep</span>
                <span>Nov</span>
                <span>Des</span>
              </div>
            </div>

            {/* Donut Chart Sekunder (1 Column) */}
            <div className="bg-slate-800 border border-slate-700/60 p-6 rounded-2xl shadow-lg flex flex-col justify-between">
              <div>
                <h3 className="text-base font-extrabold text-slate-50">Kanal Lalu Lintas</h3>
                <p className="text-xs text-slate-400">Distribusi sumber kedatangan pengguna.</p>
              </div>

              {/* SVG Donut Ring */}
              <div className="relative flex items-center justify-center my-6">
                <svg className="w-44 h-44 -rotate-90 transform" viewBox="0 0 36 36">
                  {/* Segment 1: Direct (42%) */}
                  <circle cx="18" cy="18" r="15.915" fill="none" stroke="#3b82f6" strokeWidth="3.8" strokeDasharray="42 58" strokeDashoffset="0" />
                  {/* Segment 2: Organic (28%) */}
                  <circle cx="18" cy="18" r="15.915" fill="none" stroke="#10b981" strokeWidth="3.8" strokeDasharray="28 72" strokeDashoffset="-42" />
                  {/* Segment 3: Referral (18%) */}
                  <circle cx="18" cy="18" r="15.915" fill="none" stroke="#8b5cf6" strokeWidth="3.8" strokeDasharray="18 82" strokeDashoffset="-70" />
                  {/* Segment 4: Social (12%) */}
                  <circle cx="18" cy="18" r="15.915" fill="none" stroke="#f59e0b" strokeWidth="3.8" strokeDasharray="12 88" strokeDashoffset="-88" />
                </svg>
                <div className="absolute flex flex-col items-center justify-center text-center">
                  <span className="text-xl font-extrabold text-slate-50">100%</span>
                  <span className="text-[10px] uppercase font-bold text-slate-400">Total Trafik</span>
                </div>
              </div>

              {/* Legend List */}
              <div className="space-y-2.5 text-xs font-semibold">
                <div className="flex items-center justify-between text-slate-300">
                  <div className="flex items-center gap-2">
                    <span className="w-2.5 h-2.5 rounded-full bg-blue-500"></span>
                    <span>Direct Traffic</span>
                  </div>
                  <span className="text-slate-400 font-bold">42%</span>
                </div>
                <div className="flex items-center justify-between text-slate-300">
                  <div className="flex items-center gap-2">
                    <span className="w-2.5 h-2.5 rounded-full bg-emerald-500"></span>
                    <span>Organic Search</span>
                  </div>
                  <span className="text-slate-400 font-bold">28%</span>
                </div>
                <div className="flex items-center justify-between text-slate-300">
                  <div className="flex items-center gap-2">
                    <span className="w-2.5 h-2.5 rounded-full bg-purple-500"></span>
                    <span>Referral / Partner</span>
                  </div>
                  <span className="text-slate-400 font-bold">18%</span>
                </div>
                <div className="flex items-center justify-between text-slate-300">
                  <div className="flex items-center gap-2">
                    <span className="w-2.5 h-2.5 rounded-full bg-amber-500"></span>
                    <span>Social Media</span>
                  </div>
                  <span className="text-slate-400 font-bold">12%</span>
                </div>
              </div>
            </div>
          </div>

          {/* 5. DATA TABLE (ANALYST VIEW) */}
          <div className="bg-slate-800 border border-slate-700/60 rounded-2xl shadow-lg overflow-hidden">
            {/* Table Header Filter & Title */}
            <div className="p-5 border-b border-slate-700/60 flex flex-col md:flex-row md:items-center justify-between gap-4">
              <div>
                <h3 className="text-base font-extrabold text-slate-50">Tabel Transaksi Terbaru</h3>
                <p className="text-xs text-slate-400 mt-0.5">Analisis histori log transaksi &amp; validasi otentikasi pengguna.</p>
              </div>

              <div className="flex items-center gap-3">
                {/* Status Filter */}
                <select
                  value={statusFilter}
                  onChange={(e) => setStatusFilter(e.target.value)}
                  className="bg-slate-900 border border-slate-700 text-xs font-bold text-slate-200 rounded-xl px-3 py-2 focus:outline-none focus:border-blue-500"
                >
                  <option value="ALL">Semua Status</option>
                  <option value="COMPLETED">Completed</option>
                  <option value="PENDING">Pending</option>
                  <option value="FAILED">Failed</option>
                </select>

                <button
                  type="button"
                  className="bg-blue-600 hover:bg-blue-500 text-white text-xs font-bold px-3.5 py-2 rounded-xl transition-all shadow-md flex items-center gap-2"
                >
                  <i className="fa-solid fa-download"></i> Export CSV
                </button>
              </div>
            </div>

            {/* Responsive Table */}
            <div className="overflow-x-auto">
              <table className="w-full text-left text-sm border-collapse">
                <thead>
                  <tr className="bg-slate-900/50 text-slate-400 uppercase text-[11px] font-bold tracking-wider border-b border-slate-700/60">
                    <th className="py-3.5 px-5">ID Transaksi</th>
                    <th className="py-3.5 px-5">Pengguna / NIK</th>
                    <th className="py-3.5 px-5">Kategori</th>
                    <th className="py-3.5 px-5">Waktu</th>
                    <th className="py-3.5 px-5">Jumlah</th>
                    <th className="py-3.5 px-5">Otensitas</th>
                    <th className="py-3.5 px-5">Status</th>
                  </tr>
                </thead>
                <tbody className="divide-y divide-slate-700/40 text-slate-200 font-medium">
                  {filteredTransactions.map((tx) => (
                    <tr
                      key={tx.id}
                      className="hover:bg-slate-700/50 transition-colors duration-150 cursor-pointer"
                    >
                      <td className="py-4 px-5 font-mono text-xs font-bold text-blue-400">{tx.id}</td>
                      <td className="py-4 px-5">
                        <div className="flex items-center gap-3">
                          <img src={tx.user.avatar} alt={tx.user.name} className="w-8 h-8 rounded-full object-cover ring-1 ring-slate-700" />
                          <div>
                            <div className="text-xs font-bold text-slate-100">{tx.user.name}</div>
                            <div className="text-[10px] text-slate-400 font-mono">{tx.user.nik}</div>
                          </div>
                        </div>
                      </td>
                      <td className="py-4 px-5 text-xs text-slate-300">{tx.category}</td>
                      <td className="py-4 px-5 text-xs text-slate-400">{tx.date}</td>
                      <td className="py-4 px-5 text-xs font-bold text-slate-50">{tx.amount}</td>
                      <td className="py-4 px-5 text-xs text-slate-400">
                        <span className="inline-flex items-center gap-1.5 bg-slate-900/80 px-2.5 py-1 rounded-lg border border-slate-700 text-[11px]">
                          <i className="fa-solid fa-fingerprint text-blue-400"></i> {tx.method}
                        </span>
                      </td>
                      <td className="py-4 px-5">
                        {tx.status === 'Completed' && (
                          <span className="bg-emerald-500/10 text-emerald-400 border border-emerald-500/20 text-xs font-bold px-2.5 py-1 rounded-full inline-flex items-center gap-1.5">
                            <span className="w-1.5 h-1.5 rounded-full bg-emerald-400"></span> Completed
                          </span>
                        )}
                        {tx.status === 'Pending' && (
                          <span className="bg-amber-500/10 text-amber-400 border border-amber-500/20 text-xs font-bold px-2.5 py-1 rounded-full inline-flex items-center gap-1.5">
                            <span className="w-1.5 h-1.5 rounded-full bg-amber-400"></span> Pending
                          </span>
                        )}
                        {tx.status === 'Failed' && (
                          <span className="bg-rose-500/10 text-rose-400 border border-rose-500/20 text-xs font-bold px-2.5 py-1 rounded-full inline-flex items-center gap-1.5">
                            <span className="w-1.5 h-1.5 rounded-full bg-rose-400"></span> Failed
                          </span>
                        )}
                      </td>
                    </tr>
                  ))}
                  {filteredTransactions.length === 0 && (
                    <tr>
                      <td colSpan="7" className="py-8 text-center text-slate-400 text-xs font-medium">
                        Tidak ada transaksi yang cocok dengan pencarian "{searchQuery}".
                      </td>
                    </tr>
                  )}
                </tbody>
              </table>
            </div>

            {/* Table Footer Pagination */}
            <div className="p-4 border-t border-slate-700/60 flex items-center justify-between text-xs text-slate-400">
              <div>Menampilkan 1-{filteredTransactions.length} dari {transactions.length} transaksi</div>
              <div className="flex items-center gap-2">
                <button className="px-3 py-1.5 rounded-lg border border-slate-700 bg-slate-900 text-slate-400 hover:text-slate-200 disabled:opacity-50" disabled>
                  Sebelumnya
                </button>
                <button className="px-3 py-1.5 rounded-lg border border-slate-700 bg-slate-900 text-slate-200 hover:bg-slate-700">
                  Berikutnya
                </button>
              </div>
            </div>
          </div>
        </main>
      </div>
    </div>
  );
}
