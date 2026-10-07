import React, { useState, useEffect } from 'react';
import { useAuth } from '../context/AuthContext';
import { db } from '../db';
import { Card, CardContent, CardHeader, CardTitle, CardDescription } from '@/components/ui/card';
import { Tabs, TabsContent, TabsList, TabsTrigger } from '@/components/ui/tabs';
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from '@/components/ui/table';
import { Badge } from '@/components/ui/badge';
import { Activity, Users, Database, Clock, RefreshCw } from 'lucide-react';
import { format } from 'date-fns';
import { id } from 'date-fns/locale';

export default function SinkronisasiPage() {
  const { user } = useAuth();
  const [attendanceQueue, setAttendanceQueue] = useState([]);
  const [employeeQueue, setEmployeeQueue] = useState([]);
  const [loading, setLoading] = useState(true);

  const loadData = async () => {
    try {
      setLoading(true);
      // Fetch data from local databases
      const attData = await db.attendance_sync_queue.toArray();
      const empData = await db.employee_sync_queue.toArray();
      
      // Sort newest first
      setAttendanceQueue(attData.sort((a, b) => new Date(b.timestamp) - new Date(a.timestamp)));
      setEmployeeQueue(empData.sort((a, b) => new Date(b.created_at || b.timestamp) - new Date(a.created_at || a.timestamp)));
    } catch (error) {
      console.error('[SinkronisasiPage] Failed to load sync queues:', error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadData();
    // Re-load when refresh_logs event occurs (e.g. after sync or new offline attendance)
    window.addEventListener('refresh_logs', loadData);
    return () => window.removeEventListener('refresh_logs', loadData);
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
    if (isSynced) return <Badge variant="default" className="bg-green-600 hover:bg-green-700 text-white">Terkirim</Badge>;
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
          <TabsList className="flex h-11 w-full max-w-sm items-center justify-start rounded-xl p-1 bg-muted border border-border/40">
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
            <CardHeader className="px-4 md:px-6">
              <CardTitle>Data Pendaftaran Karyawan Offline</CardTitle>
              <CardDescription>
                Daftar karyawan baru yang didaftarkan secara offline dan belum tersinkronisasi.
              </CardDescription>
            </CardHeader>
            <CardContent className="flex-1 p-0">
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
                      <TableHead className="text-right">Status</TableHead>
                    </TableRow>
                  </TableHeader>
                  <TableBody>
                    {employeeQueue.map((emp) => (
                      <TableRow key={emp.id} className="hover:bg-muted/50">
                        <TableCell className="font-semibold text-foreground">
                          {emp.name || 'Unknown'}
                        </TableCell>
                        <TableCell>
                          <div className="text-sm font-medium text-foreground">{emp.nik || '-'}</div>
                          <div className="text-xs text-muted-foreground">{emp.department || '-'}</div>
                        </TableCell>
                        <TableCell className="text-muted-foreground font-medium whitespace-nowrap">
                          {formatDate(emp.created_at || emp.timestamp)}
                        </TableCell>
                        <TableCell className="text-right">
                          {getStatusBadge(emp.is_synced, emp.sync_notes)}
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
      </Tabs>
    </div>
  );
}
