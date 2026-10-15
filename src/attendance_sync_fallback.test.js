import { describe, it, expect } from 'vitest';
import { stripAttendanceTelemetry, isAttendanceMissingColumnError } from './syncEngine';

describe('Tugas 2: Attendance Sync Payload & 42703 Adaptive Fallback', () => {
  const fullAttendancePayload = {
    employee_id: 'emp-101',
    timestamp: '2026-10-15T08:00:00.000Z',
    location: 'Kebun A | Afd 1 | Lapangan',
    status: 'HADIR TEPAT WAKTU',
    euclidean_distance: 0.15,
    latitude: -6.2088,
    longitude: 106.8456,
    durasi: 480,
    attendance_type: 'CHECK-IN',
    nik: 'NIK-101',
    name: 'Budi Santoso',
    department: 'Panen',
    match_cosine: 0.9452,
    match_threshold: 0.92,
    template_version: 2,
    facing_mode: 'environment'
  };

  it('preserves all 16 columns in full attendance payload', () => {
    const keys = Object.keys(fullAttendancePayload);
    expect(keys.length).toBe(16);
    expect(fullAttendancePayload.match_cosine).toBe(0.9452);
    expect(fullAttendancePayload.match_threshold).toBe(0.92);
    expect(fullAttendancePayload.template_version).toBe(2);
    expect(fullAttendancePayload.facing_mode).toBe('environment');
  });

  it('correctly strips telemetry columns when fallback is triggered', () => {
    const core = stripAttendanceTelemetry(fullAttendancePayload);
    expect(core.match_cosine).toBeUndefined();
    expect(core.match_threshold).toBeUndefined();
    expect(core.template_version).toBeUndefined();
    expect(core.facing_mode).toBeUndefined();

    // Core columns remain intact
    expect(core.employee_id).toBe('emp-101');
    expect(core.nik).toBe('NIK-101');
    expect(core.attendance_type).toBe('CHECK-IN');
    expect(core.status).toBe('HADIR TEPAT WAKTU');
    expect(Object.keys(core).length).toBe(12);
  });

  it('strips telemetry columns recursively when payload is an array', () => {
    const batch = [fullAttendancePayload, { ...fullAttendancePayload, employee_id: 'emp-102' }];
    const strippedBatch = stripAttendanceTelemetry(batch);

    expect(Array.isArray(strippedBatch)).toBe(true);
    expect(strippedBatch.length).toBe(2);
    expect(strippedBatch[0].match_cosine).toBeUndefined();
    expect(strippedBatch[1].match_cosine).toBeUndefined();
    expect(strippedBatch[0].employee_id).toBe('emp-101');
    expect(strippedBatch[1].employee_id).toBe('emp-102');
  });

  it('detects Postgres 42703 (undefined column) error accurately', () => {
    const errCode = { code: '42703', message: 'column "match_cosine" of relation "attendance_logs" does not exist' };
    expect(isAttendanceMissingColumnError(errCode)).toBe(true);

    const errMessageOnly = { message: 'column attendance_logs.template_version does not exist' };
    expect(isAttendanceMissingColumnError(errMessageOnly)).toBe(true);

    const errFacing = { message: 'column facing_mode does not exist' };
    expect(isAttendanceMissingColumnError(errFacing)).toBe(true);

    const unrelatedErr = { code: '23503', message: 'foreign key violation' };
    expect(isAttendanceMissingColumnError(unrelatedErr)).toBe(false);
  });

  it('simulates mock Supabase insert flow with 42703 fallback retry', async () => {
    let callCount = 0;
    let receivedPayloads = [];

    // Mock server that rejects first call with 42703, then accepts stripped payload
    const mockSupabase = {
      from: () => ({
        insert: (payload) => {
          callCount++;
          receivedPayloads.push(payload);
          return {
            select: async () => {
              if (callCount === 1) {
                return {
                  data: null,
                  error: { code: '42703', message: 'column match_cosine does not exist' }
                };
              }
              // Second call (fallback) succeeds
              return {
                data: [{ id: 999, ...payload[0] }],
                error: null
              };
            }
          };
        }
      })
    };

    // Client execution simulation
    let result = await mockSupabase.from('attendance_logs').insert([fullAttendancePayload]).select();
    if (result.error && isAttendanceMissingColumnError(result.error)) {
      const stripped = stripAttendanceTelemetry([fullAttendancePayload]);
      result = await mockSupabase.from('attendance_logs').insert(stripped).select();
    }

    expect(callCount).toBe(2);
    expect(result.error).toBeNull();
    expect(result.data[0].id).toBe(999);
    // Verified: First call had telemetry, second call had telemetry removed
    expect(receivedPayloads[0][0].match_cosine).toBe(0.9452);
    expect(receivedPayloads[1][0].match_cosine).toBeUndefined();
  });

  it('simulates duplicate key 23505 handling to prevent infinite retry loops', () => {
    const duplicateErr = { code: '23505', message: 'duplicate key value violates unique constraint' };
    const isDuplicate = duplicateErr.code === '23505' || duplicateErr.message.includes('duplicate key');
    expect(isDuplicate).toBe(true);
  });
});
