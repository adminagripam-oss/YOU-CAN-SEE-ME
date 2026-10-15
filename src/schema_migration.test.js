import { describe, it, expect } from 'vitest';
import fs from 'fs';
import path from 'path';

describe('A8: Schema Migrations Integrity', () => {
  it('ensures Dexie database versions are declared in strictly ascending order', () => {
    const dbPath = path.resolve(__dirname, 'db.js');
    const content = fs.readFileSync(dbPath, 'utf8');

    const regex = /dexieDb\.version\((\d+)\)/g;
    const versions = [];
    let match;
    while ((match = regex.exec(content)) !== null) {
      versions.push(parseInt(match[1], 10));
    }

    expect(versions.length).toBeGreaterThan(0);

    // Verify versions are strictly ascending
    for (let i = 1; i < versions.length; i++) {
      expect(versions[i]).toBeGreaterThan(versions[i - 1]);
    }

    // Verify latest version is at least 10 and includes employee_face_thumbs
    expect(versions[versions.length - 1]).toBe(10);
    expect(content).toContain("employee_face_thumbs: 'employee_id, captured_at, is_synced'");
    expect(content).toContain("scan_attempts_queue: '++id, employee_id, nik, outcome, is_synced, created_at'");
  });

  it('ensures SQLite service executes idempotent migrations with column existence guards', () => {
    const sqlitePath = path.resolve(__dirname, 'services/sqliteService.ts');
    const content = fs.readFileSync(sqlitePath, 'utf8');

    expect(content).toContain('CREATE TABLE IF NOT EXISTS local_employee_face_thumbs');
    expect(content).toContain('ALTER TABLE local_master_descriptors ADD COLUMN vector_checksum TEXT');
    expect(content).toContain('ALTER TABLE local_master_descriptors ADD COLUMN template_version INTEGER');
  });
});
