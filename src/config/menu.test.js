/**
 * Unit tests for pure helper functions in src/config/menu.js.
 * Run with: npm test
 *
 * These functions contain no React/DOM dependencies and can run in node environment.
 */

import { describe, it, expect } from 'vitest';
import {
  MENU, filterMenuByRole, findTrail, allowedPaths, firstPath,
} from './menu';

const ESTATE  = 'estate_admin';
const REGION  = 'regional_admin';
const HQ      = 'headoffice_admin';
const UNKNOWN = 'superadmin';

describe('filterMenuByRole', () => {
  it('returns Dashboard for all roles', () => {
    for (const role of [ESTATE, REGION, HQ]) {
      const result = filterMenuByRole(MENU, role);
      expect(result.some((item) => item.title === 'Dashboard')).toBe(true);
    }
  });

  it('estate_admin sees Absensi group and Data Karyawan group', () => {
    const result = filterMenuByRole(MENU, ESTATE);
    const titles = result.map((i) => i.title);
    expect(titles).toContain('Absensi');
    expect(titles).toContain('Data Karyawan');
  });

  it('regional_admin sees Absensi group but NOT Data Karyawan', () => {
    const result = filterMenuByRole(MENU, REGION);
    const titles = result.map((i) => i.title);
    expect(titles).toContain('Absensi');
    expect(titles).not.toContain('Data Karyawan');
  });

  it('headoffice_admin sees Persetujuan group but NOT Sinkronisasi or Scanner Absensi', () => {
    const result = filterMenuByRole(MENU, HQ);
    const titles = result.map((i) => i.title);
    expect(titles).toContain('Persetujuan');
    expect(titles).not.toContain('Sinkronisasi');
    // Flatten children titles to check Scanner Absensi is absent
    const childTitles = result.flatMap((i) => (i.children ?? []).map((c) => c.title));
    expect(childTitles).not.toContain('Scanner Absensi');
  });

  it('estate_admin sees Scanner Absensi as child of Absensi group', () => {
    const result = filterMenuByRole(MENU, ESTATE);
    const absensiGroup = result.find((i) => i.title === 'Absensi');
    expect(absensiGroup).toBeDefined();
    const childTitles = absensiGroup.children.map((c) => c.title);
    expect(childTitles).toContain('Scanner Absensi');
  });

  it('prunes a parent group when all its children are filtered out', () => {
    const fakeMenu = [
      {
        title: 'Group',
        icon: 'X',
        roles: ['estate_admin'],
        children: [
          { title: 'Child A', icon: 'X', path: '/a', roles: ['estate_admin'] },
        ],
      },
    ];
    expect(filterMenuByRole(fakeMenu, REGION).length).toBe(0);
  });

  it('unknown role only sees items with no role restriction', () => {
    const result = filterMenuByRole(MENU, UNKNOWN);
    result.forEach((item) => {
      expect(!item.roles || item.roles.length === 0).toBe(true);
    });
  });
});

describe('findTrail', () => {
  it('returns single-item trail for a root-level leaf', () => {
    const trail = findTrail(MENU, '/dashboard');
    expect(trail).toHaveLength(1);
    expect(trail[0].title).toBe('Dashboard');
  });

  it('returns two-item trail for a child of a group', () => {
    const trail = findTrail(MENU, '/logs');
    expect(trail).toHaveLength(2);
    expect(trail[0].title).toBe('Absensi');
    expect(trail[1].title).toBe('Log Absensi');
  });

  it('returns two-item trail for /daftar-karyawan/mutasi', () => {
    const trail = findTrail(MENU, '/daftar-karyawan/mutasi');
    expect(trail).toHaveLength(2);
    expect(trail[0].title).toBe('Data Karyawan');
    expect(trail[1].title).toBe('Mutasi Karyawan');
  });

  it('returns [] for a path not in the menu', () => {
    expect(findTrail(MENU, '/not-a-real-path')).toEqual([]);
  });
});

describe('allowedPaths', () => {
  it('estate_admin can access /absensi, /dashboard, /logs, /karyawan, /sinkronisasi', () => {
    const paths = allowedPaths(MENU, ESTATE);
    expect(paths).toContain('/dashboard');
    expect(paths).toContain('/absensi');
    expect(paths).toContain('/logs');
    expect(paths).toContain('/karyawan');
    expect(paths).toContain('/sinkronisasi');
  });

  it('regional_admin cannot access /karyawan or /sinkronisasi', () => {
    const paths = allowedPaths(MENU, REGION);
    expect(paths).not.toContain('/karyawan');
    expect(paths).not.toContain('/sinkronisasi');
  });

  it('headoffice_admin can access /persetujuan/inbox', () => {
    const paths = allowedPaths(MENU, HQ);
    expect(paths).toContain('/persetujuan/inbox');
  });

  it('headoffice_admin cannot access /sinkronisasi or /absensi', () => {
    const paths = allowedPaths(MENU, HQ);
    expect(paths).not.toContain('/sinkronisasi');
    expect(paths).not.toContain('/absensi');
  });
});

describe('firstPath', () => {
  it('returns /dashboard for every role since it has no restriction', () => {
    expect(firstPath(MENU, ESTATE)).toBe('/dashboard');
    expect(firstPath(MENU, REGION)).toBe('/dashboard');
    expect(firstPath(MENU, HQ)).toBe('/dashboard');
  });

  it('returns null for a role with no accessible paths', () => {
    const emptyMenu = [
      { title: 'Only Estate', icon: 'X', path: '/x', roles: ['estate_admin'] },
    ];
    expect(firstPath(emptyMenu, REGION)).toBeNull();
  });
});
