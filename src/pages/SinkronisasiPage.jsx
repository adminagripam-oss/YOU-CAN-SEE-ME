import React, { useState, useEffect } from 'react';
import { useAuth } from '../context/AuthContext';
import { db, getAllMasterVectors, cacheUserMasterVector, toVectorArray } from '../db';
import { supabase } from '../supabaseClient';
import { vectorChecksum } from '../biometrics';
import { Card, CardContent, CardHeader, CardTitle, CardDescription } from '@/components/ui/card';
import { Tabs, TabsContent, TabsList, TabsTrigger } from '@/components/ui/tabs';
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from '@/components/ui/table';
import { Badge } from '@/components/ui/badge';
import { Activity, Users, Database, Clock, RefreshCw, CheckCircle2, AlertTriangle, Download, ShieldCheck, Copy, RotateCcw, AlertCircle, ChevronDown, Building2, WifiOff } from 'lucide-react';
import { format } from 'date-fns';
import { id } from 'date-fns/locale';

export default function SinkronisasiPage() {
  const { user } = useAuth();
  const [attendanceQueue, setAttendanceQueue] = useState([]);
  const [employeeQueue, setEmployeeQueue] = useState([]);
  const [loading, setLoading] = useState(true);
  const [syncingEmployees, setSyncingEmployees] = useState(false);
  const [copiedId, setCopiedId] = useState(null);
  const [schemaNotice, setSchemaNotice] = useState(null);

  // Connectivity & Scoping States
  const [isOnline, setIsOnline] = useState(typeof navigator !== 'undefined' ? navigator.onLine : true);
  const [selectedKebunFilter, setSelectedKebunFilter] = useState('ALL');
  const [availableKebunList, setAvailableKebunList] = useState([]);

  // Vector Integrity Diagnostic States
  const [diagLoading, setDiagLoading] = useState(false);
  const [diagResults, setDiagResults] = useState(null);
  const [syncingVectorId, setSyncingVectorId] = useState(null);

  useEffect(() => {
    const handleOnline = () => setIsOnline(true);
    const handleOffline = () => setIsOnline(false);
    window.addEventListener('online', handleOnline);
    window.addEventListener('offline', handleOffline);
    return () => {
      window.removeEventListener('online', handleOnline);
      window.removeEventListener('offline', handleOffline);
    };
  }, []);

  const checkRegistrationSchema = async () => {
    try {
      const notices = [];
      const { error: descErr } = await supabase
        .from('master_descriptors')
        .select('vector_checksum')
        .limit(1);
      if (descErr && (descErr.code === '42703' || String(descErr.message || '').includes('vector_checksum'))) {
        notices.push('Kolom vector_checksum / template_version belum dibuat di tabel master_descriptors server Supabase (42703). Harap jalankan migrasi 20261015_alter_master_descriptors.sql.');
      }

      const { error: attErr } = await supabase
        .from('attendance_logs')
        .select('match_cosine')
        .limit(1);
      if (attErr && (attErr.code === '42703' || String(attErr.message || '').includes('match_cosine'))) {
        notices.push('Kolom telemetri (match_cosine, match_threshold, template_version, facing_mode) belum dibuat di attendance_logs server Supabase (42703). Sinkronisasi adaptif otomatis menyisihkan telemetri agar log absensi tetap tersimpan. Harap jalankan migrasi 20261015_fix_attendance_logs_columns.sql.');
      }

      setSchemaNotice(notices.length > 0 ? notices.join(' | ') : null);
    } catch (_) {}
  };

  const handleSyncEmployees = async () => {
    if (!navigator.onLine) {
      if (typeof window !== 'undefined') {
        window.dispatchEvent(new CustomEvent('agriface-toast', {
          detail: { title: 'Mode Offline', message: 'Perangkat sedang luring. Hubungkan ke internet untuk sinkronisasi pendaftaran.', type: 'error' }
        }));
      }
      return;
    }
    setSyncingEmployees(true);
    try {
      const { syncPendingEmployees } = await import('../syncEngine');
      await syncPendingEmployees();
      await loadData();
    } catch (err) {
      console.error('[SinkronisasiPage] Gagal sinkronisasi pendaftaran:', err);
    } finally {
      setSyncingEmployees(false);
    }
  };

  const copyErrorDetails = (emp) => {
    const details = JSON.stringify({
      id: emp.id,
      nik: emp.nik,
      name: emp.name,
      department: emp.department,
      step: emp.last_error_step || 'unknown',
      code: emp.last_error_code || 'RPC_OR_NETWORK_ERROR',
      message: emp.last_error_message || 'Tidak ada detail pesan error',
      attempt_count: emp.attempt_count || 1,
      last_attempt_at: emp.last_attempt_at || new Date().toISOString(),
      created_at: emp.created_at
    }, null, 2);
    navigator.clipboard?.writeText(details);
    setCopiedId(emp.id);
    setTimeout(() => setCopiedId(null), 2500);
  };

  const loadData = async () => {
    try {
      setLoading(true);
      // Fetch data from local databases
      const attData = await db.attendance_sync_queue.toArray();
      const empData = await db.employee_sync_queue.toArray();
      
      // Filter out any records that are already synced (strictly pending/unsynced only)
      let pendingAtt = (attData || []).filter(item => 
        (!item.is_synced || item.is_synced === 0) && item.status_sync_teks !== 'done'
      );
      let pendingEmp = (empData || []).filter(emp => 
        !emp.is_synced || emp.is_synced === 0
      );

      // Scoping based on user role and authorization
      if (user?.role === 'estate_admin' && user?.kebun) {
        pendingAtt = pendingAtt.filter(item => item.kebun === user.kebun || item.nama_kebun === user.kebun);
        pendingEmp = pendingEmp.filter(emp => emp.kebun === user.kebun || emp.nama_kebun === user.kebun);
      } else if (user?.role === 'regional_admin' && user?.region) {
        pendingAtt = pendingAtt.filter(item => item.region === user.region);
        pendingEmp = pendingEmp.filter(emp => emp.region === user.region);
      } else if ((user?.username === 'admin.hq' || user?.role === 'headoffice_admin') && selectedKebunFilter !== 'ALL') {
        pendingAtt = pendingAtt.filter(item => item.kebun === selectedKebunFilter || item.nama_kebun === selectedKebunFilter);
        pendingEmp = pendingEmp.filter(emp => emp.kebun === selectedKebunFilter || emp.nama_kebun === selectedKebunFilter);
      }

      // Populate availableKebunList from cached employees and queues
      try {
        const cached = await db.employees_cache.toArray();
        const kSet = new Set();
        (cached || []).forEach(c => {
          if (c.nama_kebun) kSet.add(c.nama_kebun);
          if (c.kebun) kSet.add(c.kebun);
        });
        (attData || []).forEach(a => {
          if (a.kebun) kSet.add(a.kebun);
          if (a.nama_kebun) kSet.add(a.nama_kebun);
        });
        (empData || []).forEach(e => {
          if (e.kebun) kSet.add(e.kebun);
          if (e.nama_kebun) kSet.add(e.nama_kebun);
        });
        setAvailableKebunList(Array.from(kSet).filter(Boolean).sort((a, b) => a.localeCompare(b)));
      } catch (_) {}

      // Sort newest first
      setAttendanceQueue(pendingAtt.sort((a, b) => new Date(b.timestamp) - new Date(a.timestamp)));
      setEmployeeQueue(pendingEmp.sort((a, b) => new Date(b.created_at || b.timestamp) - new Date(a.created_at || a.timestamp)));
      
      checkRegistrationSchema();
    } catch (error) {
      console.error('[SinkronisasiPage] Failed to load sync queues:', error);
    } finally {
      setLoading(false);
    }
  };

  const checkVectorIntegrity = async (overrideKebun = null) => {
    if (!navigator.onLine) {
      setDiagLoading(false);
      return;
    }

    setDiagLoading(true);
    try {
      const activeKebun = overrideKebun !== null ? overrideKebun : selectedKebunFilter;

      // 1. Fetch local cached employees to map employee metadata (name, nik, kebun)
      let cachedEmps = [];
      try {
        cachedEmps = await db.employees_cache.toArray();
      } catch (_) {}
      const empMetaMap = new Map();
      const kebunSet = new Set();
      (cachedEmps || []).forEach(e => {
        empMetaMap.set(String(e.id), e);
        if (e.nama_kebun) kebunSet.add(e.nama_kebun);
        if (e.kebun) kebunSet.add(e.kebun);
      });

      // 2. Fetch local master vectors and filter by kebun scope
      const rawLocalMasters = await getAllMasterVectors();
      const localMasters = (rawLocalMasters || []).filter(m => {
        const meta = empMetaMap.get(String(m.employee_id));
        const kbn = meta?.nama_kebun || meta?.kebun || m.nama_kebun || m.kebun;
        if (user?.role === 'estate_admin' && user?.kebun) {
          return kbn === user.kebun;
        }
        if (user?.role === 'regional_admin' && user?.region) {
          return meta?.region === user.region;
        }
        if (activeKebun && activeKebun !== 'ALL') {
          return kbn === activeKebun;
        }
        return true;
      });

      const localMap = new Map();
      for (const m of localMasters) {
        const meta = empMetaMap.get(String(m.employee_id));
        let isInvalid = false;
        let cs = m.vector_checksum;
        if (m.descriptor_json) {
          const normArr = toVectorArray(m.descriptor_json);
          if (!normArr) {
            isInvalid = true;
            console.warn(`[VectorIntegrity] Vektor master lokal karyawan #${m.employee_id} (${m.name || meta?.name}) memiliki format rusak (INVALID_FORMAT).`);
          } else if (!cs) {
            cs = vectorChecksum(normArr);
          }
        } else {
          isInvalid = true;
        }
        localMap.set(String(m.employee_id), {
          ...m,
          name: m.name || meta?.name,
          nik: m.nik || meta?.nik,
          nama_kebun: meta?.nama_kebun || meta?.kebun || m.nama_kebun || m.kebun || '-',
          computed_checksum: cs,
          isInvalidFormat: isInvalid
        });
      }

      // 3. Fetch remote master descriptors joined with employees
      let query = supabase
        .from('master_descriptors')
        .select(`
          employee_id,
          vector_checksum,
          updated_at,
          template_version,
          employees!inner (
            id,
            name,
            nik,
            nama_kebun,
            region
          )
        `);

      if (user?.role === 'estate_admin' && user?.kebun) {
        query = query.eq('employees.nama_kebun', user.kebun);
      } else if (user?.role === 'regional_admin' && user?.region) {
        query = query.eq('employees.region', user.region);
      } else if (activeKebun && activeKebun !== 'ALL') {
        query = query.eq('employees.nama_kebun', activeKebun);
      }

      const { data: remoteData, error: remoteErr } = await query;
      if (remoteErr) {
        throw remoteErr;
      }

      // Harvest kebun names from remote data as well for admin.hq selector
      (remoteData || []).forEach(r => {
        const rKbn = r.employees?.nama_kebun;
        if (rKbn) kebunSet.add(rKbn);
      });
      setAvailableKebunList(Array.from(kebunSet).filter(Boolean).sort((a, b) => a.localeCompare(b)));

      const remoteMap = new Map((remoteData || []).map(r => [String(r.employee_id), r]));
      const allIds = new Set([...localMap.keys(), ...remoteMap.keys()]);

      let matchCount = 0;
      let mismatchCount = 0;
      let localOnlyCount = 0;
      let remoteOnlyCount = 0;
      let uncomputedCount = 0;
      let invalidFormatCount = 0;
      const details = [];

      for (const empId of allIds) {
        const local = localMap.get(empId);
        const remote = remoteMap.get(empId);

        let status = 'MATCH';
        if (local?.isInvalidFormat) {
          status = 'INVALID_FORMAT';
          invalidFormatCount++;
        } else if (local && !remote) {
          status = 'LOCAL_ONLY';
          localOnlyCount++;
        } else if (!local && remote) {
          status = 'REMOTE_ONLY';
          remoteOnlyCount++;
        } else if (local && remote) {
          const localCs = local.vector_checksum || local.computed_checksum;
          const remoteCs = remote.vector_checksum;
          if (!remoteCs) {
            status = 'UNCOMPUTED';
            uncomputedCount++;
          } else if (localCs && remoteCs && localCs === remoteCs) {
            status = 'MATCH';
            matchCount++;
          } else {
            status = 'MISMATCH';
            mismatchCount++;
          }
        }

        const employeeName = remote?.employees?.name || local?.name || `Karyawan #${empId}`;
        const employeeNik = remote?.employees?.nik || local?.nik || '-';
        const employeeKebun = remote?.employees?.nama_kebun || local?.nama_kebun || '-';

        details.push({
          employee_id: empId,
          name: employeeName,
          nik: employeeNik,
          nama_kebun: employeeKebun,
          status,
          localChecksum: local?.vector_checksum || local?.computed_checksum || '-',
          remoteChecksum: remote?.vector_checksum || '-',
          remoteUpdatedAt: remote?.updated_at || null
        });
      }

      setDiagResults({
        totalLocal: localMasters.length,
        totalRemote: (remoteData || []).length,
        matchCount,
        mismatchCount,
        localOnlyCount,
        remoteOnlyCount,
        uncomputedCount,
        invalidFormatCount,
        details: details.sort((a, b) => {
          if (a.status === 'INVALID_FORMAT') return -1;
          if (b.status === 'INVALID_FORMAT') return 1;
          return (a.status === 'MATCH' ? 1 : -1);
        })
      });
    } catch (err) {
      console.error('[VectorIntegrity] Diagnostic error:', err);
    } finally {
      setDiagLoading(false);
    }
  };

  const pullRemoteVector = async (empId) => {
    setSyncingVectorId(empId);
    try {
      const { data, error } = await supabase
        .from('master_descriptors')
        .select('*')
        .eq('employee_id', empId)
        .maybeSingle();

      if (error || !data) throw error || new Error('Data tidak ditemukan');

      let calculatedChecksum = data.vector_checksum;
      const arr = toVectorArray(data.descriptor_json);
      const pullTemplateVer = data.template_version !== undefined && data.template_version !== null ? Number(data.template_version) : 1;
      if (!calculatedChecksum && arr) {
        calculatedChecksum = vectorChecksum(arr);
        await supabase
          .from('master_descriptors')
          .update({
            vector_checksum: calculatedChecksum,
            template_version: pullTemplateVer
          })
          .eq('employee_id', empId);
      }

      await cacheUserMasterVector({
        employee_id: empId,
        descriptor_json: arr || data.descriptor_json,
        geometric_descriptor_json: data.geometric_descriptor_json,
        template_version: pullTemplateVer,
        vector_checksum: calculatedChecksum,
        updated_at: data.updated_at
      });

      // Refresh diagnostic view
      await checkVectorIntegrity();
    } catch (err) {
      console.error('[VectorIntegrity] Failed to pull vector:', err);
    } finally {
      setSyncingVectorId(null);
    }
  };

  useEffect(() => {
    loadData();
    window.addEventListener('refresh_logs', loadData);
    window.addEventListener('sync_completed', loadData);
    return () => {
      window.removeEventListener('refresh_logs', loadData);
      window.removeEventListener('sync_completed', loadData);
    };
  }, []);

  const formatDate = (dateString) => {
    if (!dateString) return '-';
    try {
      return format(new Date(dateString), 'dd MMM yyyy, HH:mm', { locale: id });
    } catch {
      return dateString;
    }
  };

  const getStatusBadge = (isSynced, error) => {
    if (error) return <Badge variant="destructive" title={error}>Gagal</Badge>;
    return <Badge variant="secondary" className="bg-yellow-500/10 text-yellow-600 dark:text-yellow-400 hover:bg-yellow-500/20 border-yellow-200 dark:border-yellow-800/50">Menunggu</Badge>;
  };

  const getTypeBadge = (type) => {
    const isCheckIn = type === 'CHECK-IN' || type === 'CHECK_IN';
    return (
      <Badge variant="outline" className={isCheckIn ? 'text-blue-600 border-blue-200 bg-blue-50 dark:bg-blue-900/20 dark:text-blue-400 dark:border-blue-800' : 'text-orange-600 border-orange-200 bg-orange-50 dark:bg-orange-900/20 dark:text-orange-400 dark:border-orange-800'}>
        {isCheckIn ? 'CHECK-IN' : 'CHECK-OUT'}
      </Badge>
    );
  };

  return (
    <div className="flex flex-col h-full w-full p-0 space-y-4">
      <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-4 p-4 md:p-6 pb-0">
        <div>
          <h1 className="text-2xl font-bold tracking-tight text-foreground flex items-center gap-2">
            Sinkronisasi Data Offline
          </h1>
          <p className="text-muted-foreground mt-1">
            Pantau status pengiriman data absensi dan pendaftaran karyawan ke server pusat.
          </p>
        </div>
      </div>

      <Tabs defaultValue="attendance" className="w-full flex-1 flex flex-col">
        <div className="px-4 md:px-6">
          <TabsList className="flex h-11 w-full max-w-md items-center justify-start rounded-xl p-1 bg-muted border border-border/40">
            <TabsTrigger 
              value="attendance" 
              className="flex-1 flex items-center justify-center gap-2 h-full rounded-lg data-[state=active]:bg-white dark:data-[state=active]:bg-slate-700 data-[state=active]:text-slate-900 dark:data-[state=active]:text-slate-100 data-[state=active]:shadow-sm transition-all"
            >
              <Clock className="size-4" />
              Log Absensi
              {attendanceQueue.length > 0 && (
                <span className="ml-1.5 flex h-5 w-5 items-center justify-center rounded-full bg-indigo-500/10 text-[10px] font-medium text-indigo-600 dark:bg-indigo-500/20 dark:text-indigo-300">
                  {attendanceQueue.length}
                </span>
              )}
            </TabsTrigger>
            <TabsTrigger 
              value="employee" 
              className="flex-1 flex items-center justify-center gap-2 h-full rounded-lg data-[state=active]:bg-white dark:data-[state=active]:bg-slate-700 data-[state=active]:text-slate-900 dark:data-[state=active]:text-slate-100 data-[state=active]:shadow-sm transition-all"
            >
              <Database className="size-4" />
              Pendaftaran
              {employeeQueue.length > 0 && (
                <span className="ml-1.5 flex h-5 w-5 items-center justify-center rounded-full bg-indigo-500/10 text-[10px] font-medium text-indigo-600 dark:bg-indigo-500/20 dark:text-indigo-300">
                  {employeeQueue.length}
                </span>
              )}
            </TabsTrigger>
            <TabsTrigger 
              value="vectors" 
              onClick={() => { if (!diagResults && isOnline) checkVectorIntegrity(); }}
              className="flex-1 flex items-center justify-center gap-2 h-full rounded-lg data-[state=active]:bg-white dark:data-[state=active]:bg-slate-700 data-[state=active]:text-slate-900 dark:data-[state=active]:text-slate-100 data-[state=active]:shadow-sm transition-all"
            >
              <Activity className="size-4" />
              Integritas Vektor
            </TabsTrigger>
          </TabsList>
        </div>
        
        <TabsContent value="attendance" className="flex-1 mt-2 px-0 md:px-6 pb-6">
          <Card className="rounded-xl border shadow-sm border-t-4 border-t-indigo-500 dark:border-t-indigo-400 overflow-hidden h-full flex flex-col">
            <CardHeader className="px-4 md:px-6">
              <CardTitle>Data Absensi Offline</CardTitle>
              <CardDescription>
                Daftar absensi yang direkam saat perangkat tidak terhubung ke internet.
              </CardDescription>
            </CardHeader>
            <CardContent className="flex-1 p-0">
              {loading ? (
                <div className="text-center py-10 text-muted-foreground">Memuat data...</div>
              ) : attendanceQueue.length === 0 ? (
                <div className="flex items-center justify-center py-20 m-4 border border-dashed border-border rounded-xl bg-transparent text-muted-foreground">
                  <p className="text-sm">Tidak ada antrian absensi offline.</p>
                </div>
              ) : (
                <div className="w-full overflow-auto">
                  <Table className="compact-mobile-table w-full">
                  <TableHeader>
                    <TableRow>
                      <TableHead>Karyawan</TableHead>
                      <TableHead>Waktu</TableHead>
                      <TableHead>Tipe</TableHead>
                      <TableHead>Lokasi</TableHead>
                      <TableHead className="text-right">Status</TableHead>
                    </TableRow>
                  </TableHeader>
                  <TableBody>
                    {attendanceQueue.map((log) => (
                      <TableRow key={log.id} className="hover:bg-muted/50">
                        <TableCell>
                          <div className="font-semibold text-foreground">{log.name || 'Unknown'}</div>
                          <div className="text-xs text-muted-foreground font-medium">{log.nik || '-'}</div>
                        </TableCell>
                        <TableCell className="text-muted-foreground font-medium whitespace-nowrap">
                          {formatDate(log.timestamp)}
                        </TableCell>
                        <TableCell>
                          {getTypeBadge(log.attendance_type)}
                        </TableCell>
                        <TableCell className="text-xs text-muted-foreground max-w-[150px] md:max-w-[200px] truncate" title={log.location}>
                          {log.location || '-'}
                        </TableCell>
                        <TableCell className="text-right">
                          {getStatusBadge(log.is_synced, log.sync_notes)}
                        </TableCell>
                      </TableRow>
                    ))}
                  </TableBody>
                </Table>
                </div>
              )}
            </CardContent>
          </Card>
        </TabsContent>
        
        <TabsContent value="employee" className="flex-1 mt-2 px-0 md:px-6 pb-6">
          <Card className="rounded-xl border shadow-sm border-t-4 border-t-emerald-500 dark:border-t-emerald-400 overflow-hidden h-full flex flex-col">
            <CardHeader className="px-4 md:px-6 flex flex-col sm:flex-row sm:items-center justify-between gap-3">
              <div>
                <CardTitle>Data Pendaftaran Karyawan Offline</CardTitle>
                <CardDescription>
                  Daftar karyawan baru atau pembaruan biometrik yang tersimpan di perangkat dan siap disinkronkan.
                </CardDescription>
              </div>
              <button
                type="button"
                onClick={handleSyncEmployees}
                disabled={syncingEmployees || employeeQueue.length === 0}
                className="flex items-center gap-2 px-3.5 py-2 rounded-lg bg-emerald-600 hover:bg-emerald-700 disabled:opacity-50 text-white text-xs font-semibold shadow-sm transition-colors cursor-pointer disabled:cursor-not-allowed self-start sm:self-auto"
              >
                <RefreshCw size={14} className={syncingEmployees ? 'animate-spin' : ''} />
                <span>{syncingEmployees ? 'Menyinkronkan...' : 'Sinkronkan Pendaftaran'}</span>
              </button>
            </CardHeader>
            <CardContent className="flex-1 p-0">
              {schemaNotice && (
                <div className="mx-4 md:mx-6 mb-3 p-3 rounded-lg bg-amber-500/10 border border-amber-500/30 text-amber-800 dark:text-amber-300 text-xs flex items-start gap-2">
                  <AlertTriangle size={15} className="mt-0.5 shrink-0 text-amber-600 dark:text-amber-400" />
                  <div>
                    <div className="font-semibold">Catatan Skema Database:</div>
                    <div>{schemaNotice}</div>
                  </div>
                </div>
              )}
              {loading ? (
                <div className="text-center py-10 text-muted-foreground">Memuat data...</div>
              ) : employeeQueue.length === 0 ? (
                <div className="flex items-center justify-center py-20 m-4 border border-dashed border-border rounded-xl bg-transparent text-muted-foreground">
                  <p className="text-sm">Tidak ada antrian pendaftaran karyawan offline.</p>
                </div>
              ) : (
                <div className="w-full overflow-auto">
                  <Table className="compact-mobile-table w-full">
                  <TableHeader>
                    <TableRow>
                      <TableHead>Nama Karyawan</TableHead>
                      <TableHead>NIK / Departemen</TableHead>
                      <TableHead>Waktu Pendaftaran</TableHead>
                      <TableHead>Status &amp; Diagnostik</TableHead>
                      <TableHead className="text-right">Aksi</TableHead>
                    </TableRow>
                  </TableHeader>
                  <TableBody>
                    {employeeQueue.map((emp) => {
                      const hasError = !!(emp.last_error_message || emp.last_error_code);
                      return (
                        <TableRow key={emp.id} className="hover:bg-muted/50">
                          <TableCell>
                            <div className="font-semibold text-foreground">{emp.name || 'Unknown'}</div>
                            <div className="text-[10px] text-muted-foreground font-mono">ID: {emp.id}</div>
                          </TableCell>
                          <TableCell>
                            <div className="text-sm font-medium text-foreground">{emp.nik || '-'}</div>
                            <div className="text-xs text-muted-foreground">{emp.department || '-'}</div>
                          </TableCell>
                          <TableCell className="text-muted-foreground font-medium whitespace-nowrap text-xs">
                            {formatDate(emp.created_at || emp.timestamp)}
                          </TableCell>
                          <TableCell>
                            {hasError ? (
                              <div className="flex flex-col gap-1 items-start max-w-[320px]">
                                <Badge variant="destructive" className="font-semibold text-[10px] py-0 px-2">
                                  Gagal ({emp.last_error_step || 'RPC'})
                                </Badge>
                                <div className="text-[11px] text-red-600 dark:text-red-400 font-mono bg-red-50 dark:bg-red-950/40 p-1.5 rounded border border-red-200 dark:border-red-900/50 w-full break-words">
                                  <span className="font-bold">[{emp.last_error_code || 'ERR'}]</span> {emp.last_error_message}
                                </div>
                                <div className="text-[10px] text-muted-foreground">
                                  Percobaan: {emp.attempt_count || 1} &bull; {formatDate(emp.last_attempt_at)}
                                </div>
                              </div>
                            ) : (
                              <Badge variant="secondary" className="bg-yellow-500/10 text-yellow-600 dark:text-yellow-400 hover:bg-yellow-500/20 border-yellow-200 dark:border-yellow-800/50">
                                Menunggu
                              </Badge>
                            )}
                          </TableCell>
                          <TableCell className="text-right">
                            <div className="flex items-center justify-end gap-1.5">
                              {hasError && (
                                <button
                                  type="button"
                                  onClick={() => copyErrorDetails(emp)}
                                  title="Salin detail error Supabase"
                                  className="p-1.5 rounded border border-border hover:bg-muted text-xs text-muted-foreground hover:text-foreground flex items-center gap-1 transition-colors cursor-pointer"
                                >
                                  <Copy size={12} />
                                  <span className="text-[10px]">{copiedId === emp.id ? 'Tersalin' : 'Salin'}</span>
                                </button>
                              )}
                              <button
                                type="button"
                                onClick={handleSyncEmployees}
                                disabled={syncingEmployees}
                                title={hasError ? 'Coba sinkronkan ulang' : 'Kirim data ke server'}
                                className="p-1.5 rounded bg-emerald-50 dark:bg-emerald-950/40 border border-emerald-200 dark:border-emerald-800 text-emerald-700 dark:text-emerald-400 hover:bg-emerald-100 text-[10px] font-semibold flex items-center gap-1 transition-colors disabled:opacity-50 cursor-pointer"
                              >
                                <RotateCcw size={12} className={syncingEmployees ? 'animate-spin' : ''} />
                                <span>{hasError ? 'Ulang' : 'Kirim'}</span>
                              </button>
                            </div>
                          </TableCell>
                        </TableRow>
                      );
                    })}
                  </TableBody>
                </Table>
                </div>
              )}
            </CardContent>
          </Card>
        </TabsContent>

        {/* Vector Integrity Diagnostics Tab */}
        <TabsContent value="vectors" className="flex-1 mt-2 px-0 md:px-6 pb-6">
          <Card className="rounded-xl border shadow-sm border-t-4 border-t-cyan-500 overflow-hidden h-full flex flex-col">
            <CardHeader className="px-4 md:px-6 flex flex-col sm:flex-row sm:items-center justify-between gap-3">
              <div>
                <CardTitle className="flex items-center gap-2">
                  <ShieldCheck size={20} className="text-cyan-600" />
                  Diagnostik Integritas Vektor
                </CardTitle>
                <CardDescription>
                  {user?.role === 'estate_admin' && user?.kebun ? (
                    <span>Membandingkan sidik vektor unit <strong>{user.kebun}</strong> antara cache lokal dan cloud Supabase.</span>
                  ) : (
                    <span>Membandingkan keselarasan sidik vektor (checksum) antara cache lokal perangkat dan database pusat Supabase.</span>
                  )}
                </CardDescription>
              </div>

              <div className="flex flex-wrap items-center gap-2">
                {(user?.username === 'admin.hq' || user?.role === 'headoffice_admin') && (
                  <div className="custom-select-wrapper relative min-w-[190px]">
                    <select
                      value={selectedKebunFilter}
                      onChange={(e) => {
                        const newKbn = e.target.value;
                        setSelectedKebunFilter(newKbn);
                        if (isOnline) {
                          checkVectorIntegrity(newKbn);
                        }
                      }}
                      className="w-full appearance-none rounded-lg border border-[var(--border-color)] bg-[var(--bg-input)] text-[var(--text-main)] text-xs font-semibold py-1.5 pl-3 pr-8 focus:outline-none focus:ring-1 focus:ring-[var(--accent-primary)]"
                    >
                      <option value="ALL">Semua Kebun ({availableKebunList.length})</option>
                      {availableKebunList.map((kbn) => (
                        <option key={kbn} value={kbn}>
                          {kbn}
                        </option>
                      ))}
                    </select>
                    <ChevronDown size={14} className="absolute right-2.5 top-1/2 -translate-y-1/2 pointer-events-none text-[var(--text-muted)]" />
                  </div>
                )}

                <button
                  type="button"
                  onClick={() => checkVectorIntegrity()}
                  disabled={diagLoading || !isOnline}
                  title={!isOnline ? "Pemeriksaan memerlukan koneksi internet aktif" : "Mulai audit vektor"}
                  className="flex items-center gap-2 px-3 py-1.5 rounded-lg border border-[var(--border-color)] bg-[var(--bg-card)] text-xs font-semibold text-[var(--text-main)] hover:bg-[var(--bg-input)] transition-colors disabled:opacity-50 disabled:cursor-not-allowed"
                >
                  <RefreshCw size={14} className={diagLoading ? 'animate-spin' : ''} />
                  <span>Periksa Integritas</span>
                </button>
              </div>
            </CardHeader>
            <CardContent className="flex-1 p-4 md:p-6 pt-0 space-y-4">
              {!isOnline && (
                <div className="flex items-center gap-2 p-3 rounded-lg bg-amber-500/10 border border-amber-500/25 text-amber-700 dark:text-amber-300 text-xs font-medium">
                  <AlertTriangle size={16} className="shrink-0 text-amber-600 dark:text-amber-400" />
                  <span>Perangkat sedang luring (offline). Komparasi sidik vektor dengan server pusat memerlukan koneksi internet aktif.</span>
                </div>
              )}
              {diagLoading ? (
                <div className="text-center py-16 text-muted-foreground flex flex-col items-center gap-2">
                  <RefreshCw className="animate-spin size-6 text-cyan-600" />
                  <span className="text-xs font-medium">Memeriksa keselarasan seluruh vektor master...</span>
                </div>
              ) : !diagResults ? (
                <div className="flex flex-col items-center justify-center py-16 border border-dashed border-border rounded-xl text-muted-foreground gap-3">
                  <Activity size={32} className="opacity-40" />
                  <p className="text-sm">Klik tombol &quot;Periksa Integritas&quot; untuk memulai audit vektor.</p>
                </div>
              ) : (
                <div className="space-y-4">
                  {/* Summary Metric Cards */}
                  <div className={`grid grid-cols-2 ${diagResults.invalidFormatCount > 0 ? 'md:grid-cols-6' : 'md:grid-cols-5'} gap-3`}>
                    <div className="bg-[var(--bg-input)] p-3 rounded-lg border border-[var(--border-color)]">
                      <div className="text-[11px] font-bold uppercase text-[var(--text-muted)]">Vektor Lokal</div>
                      <div className="text-xl font-black text-[var(--text-main)]">{diagResults.totalLocal}</div>
                    </div>
                    <div className="bg-[var(--bg-input)] p-3 rounded-lg border border-[var(--border-color)]">
                      <div className="text-[11px] font-bold uppercase text-[var(--text-muted)]">Vektor Cloud</div>
                      <div className="text-xl font-black text-[var(--text-main)]">{diagResults.totalRemote}</div>
                    </div>
                    <div className="bg-[var(--bg-input)] p-3 rounded-lg border border-[var(--border-color)]">
                      <div className="text-[11px] font-bold uppercase text-emerald-600">Selaras (Match)</div>
                      <div className="text-xl font-black text-emerald-600">{diagResults.matchCount}</div>
                    </div>
                    <div className="bg-[var(--bg-input)] p-3 rounded-lg border border-[var(--border-color)]">
                      <div className="text-[11px] font-bold uppercase text-slate-500">Belum Dihitung</div>
                      <div className="text-xl font-black text-slate-600 dark:text-slate-400">{diagResults.uncomputedCount || 0}</div>
                    </div>
                    <div className="bg-[var(--bg-input)] p-3 rounded-lg border border-[var(--border-color)]">
                      <div className="text-[11px] font-bold uppercase text-amber-600">Beda Checksum</div>
                      <div className="text-xl font-black text-amber-600">{diagResults.mismatchCount + diagResults.remoteOnlyCount}</div>
                    </div>
                    {diagResults.invalidFormatCount > 0 && (
                      <div className="bg-rose-500/10 p-3 rounded-lg border border-rose-500/30">
                        <div className="text-[11px] font-bold uppercase text-rose-600">Format Rusak</div>
                        <div className="text-xl font-black text-rose-600">{diagResults.invalidFormatCount}</div>
                      </div>
                    )}
                  </div>

                  {/* Detail Table */}
                  <div className="w-full overflow-auto max-h-[50vh] border border-[var(--border-color)] rounded-lg">
                    <Table className="compact-mobile-table w-full">
                      <TableHeader>
                        <TableRow>
                          <TableHead>Karyawan</TableHead>
                          <TableHead>Kebun</TableHead>
                          <TableHead>Status Checksum</TableHead>
                          <TableHead>Checksum Lokal</TableHead>
                          <TableHead>Checksum Cloud</TableHead>
                          <TableHead className="text-right">Aksi</TableHead>
                        </TableRow>
                      </TableHeader>
                      <TableBody>
                        {diagResults.details.map((item) => {
                          const isMatch = item.status === 'MATCH';
                          return (
                            <TableRow key={item.employee_id} className="hover:bg-muted/50">
                              <TableCell>
                                <div className="font-semibold text-foreground">{item.name}</div>
                                <div className="text-xs text-muted-foreground font-mono">{item.nik} (ID: {item.employee_id})</div>
                              </TableCell>
                              <TableCell className="text-xs text-muted-foreground whitespace-nowrap font-medium">
                                {item.nama_kebun || '-'}
                              </TableCell>
                              <TableCell>
                                {item.status === 'INVALID_FORMAT' ? (
                                  <Badge variant="destructive" className="bg-rose-600 text-white gap-1" title="Format vektor di database lokal rusak atau bukan dimensi 1024">
                                    <AlertTriangle size={12} /> Format Rusak
                                  </Badge>
                                ) : isMatch ? (
                                  <Badge variant="outline" className="text-emerald-600 border-emerald-300 bg-emerald-50 dark:bg-emerald-950/20 gap-1">
                                    <CheckCircle2 size={12} /> Cocok
                                  </Badge>
                                ) : item.status === 'UNCOMPUTED' ? (
                                  <Badge variant="outline" className="text-slate-600 border-slate-300 bg-slate-50 dark:bg-slate-900/40 dark:text-slate-400 gap-1">
                                    Belum Dihitung
                                  </Badge>
                                ) : item.status === 'LOCAL_ONLY' ? (
                                  <Badge variant="secondary" className="bg-blue-500/10 text-blue-600">
                                    Hanya Lokal
                                  </Badge>
                                ) : (
                                  <Badge variant="destructive" className="gap-1">
                                    <AlertTriangle size={12} /> Beda / Perlu Sync
                                  </Badge>
                                )}
                              </TableCell>
                              <TableCell className="font-mono text-xs max-w-[160px] truncate" title={item.localChecksum}>
                                {item.localChecksum}
                              </TableCell>
                              <TableCell className="font-mono text-xs max-w-[160px] truncate" title={item.remoteChecksum}>
                                {item.remoteChecksum}
                              </TableCell>
                              <TableCell className="text-right">
                                {!isMatch && item.status !== 'LOCAL_ONLY' && (
                                  <button
                                    type="button"
                                    disabled={syncingVectorId === item.employee_id}
                                    onClick={() => pullRemoteVector(item.employee_id)}
                                    className="inline-flex items-center gap-1 px-2.5 py-1 rounded bg-[var(--accent-primary)] text-white text-[11px] font-medium hover:opacity-90"
                                  >
                                    <Download size={12} className={syncingVectorId === item.employee_id ? 'animate-spin' : ''} />
                                    <span>{item.status === 'UNCOMPUTED' ? 'Hitung & Sync' : 'Tarik Cloud'}</span>
                                  </button>
                                )}
                              </TableCell>
                            </TableRow>
                          );
                        })}
                      </TableBody>
                    </Table>
                  </div>
                </div>
              )}
            </CardContent>
          </Card>
        </TabsContent>
      </Tabs>
    </div>
  );
}

