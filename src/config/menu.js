/**
 * Single source of truth for navigation menu, breadcrumbs, and role-based route guards.
 *
 * Roles: 'estate_admin' | 'regional_admin' | 'headoffice_admin'
 * If a menu item has no `roles` array (or an empty one), it is visible to all roles.
 *
 * @typedef {{ title: string, icon: string, path?: string, roles?: string[], badgeKey?: string, children?: MenuItem[] }} MenuItem
 */

/** @type {MenuItem[]} */
export const MENU = [
  {
    title: 'Dashboard',
    icon: 'LayoutDashboard',
    path: '/dashboard',
  },
  {
    title: 'Absensi',
    icon: 'ClipboardList',
    roles: ['estate_admin', 'regional_admin', 'headoffice_admin'],
    children: [
      { title: 'Scanner Absensi',       icon: 'Camera',          path: '/absensi',         roles: ['estate_admin'] },
      { title: 'Log Absensi',           icon: 'History',         path: '/logs' },
      { title: 'Monitoring Absensi',    icon: 'Activity',        path: '/logs/monitoring' },
      { title: 'Hasil Panen',           icon: 'Package',         path: '/hasil-panen',     roles: ['estate_admin'] },
      { title: 'Pengajuan Edit/Hapus',  icon: 'FileEdit',        path: '/pengajuan',       roles: ['estate_admin'] },
    ],
  },
  {
    title: 'Data Karyawan',
    icon: 'Users',
    roles: ['estate_admin'],
    children: [
      { title: 'Daftar Karyawan',     icon: 'List',            path: '/daftar-karyawan' },
      { title: 'Mutasi Karyawan',     icon: 'ArrowLeftRight',  path: '/daftar-karyawan/mutasi' },
      { title: 'Registrasi Wajah',    icon: 'UserPlus',        path: '/karyawan' },
    ],
  },
  {
    title: 'Monitoring Region',
    icon: 'Monitor',
    roles: ['headoffice_admin'],
    children: [
      { title: 'Daftar Karyawan', icon: 'List',           path: '/daftar-karyawan' },
      { title: 'Mutasi Karyawan', icon: 'ArrowLeftRight', path: '/daftar-karyawan/mutasi' },
    ],
  },
  {
    title: 'Persetujuan',
    icon: 'CheckSquare',
    roles: ['headoffice_admin'],
    badgeKey: 'pendingRequests',
    children: [
      { title: 'Inbox Pengajuan',     icon: 'Inbox',           path: '/persetujuan/inbox',   badgeKey: 'pendingRequests' },
      { title: 'Riwayat Persetujuan', icon: 'ClipboardList',   path: '/persetujuan/riwayat' },
    ],
  },
  {
    title: 'Sinkronisasi',
    icon: 'RefreshCw',
    roles: ['estate_admin'],
    path: '/sinkronisasi',
    badgeKey: 'unsynced',
  },
];

/**
 * Filters menu items by role, pruning parent groups whose children are all filtered out.
 * @param {MenuItem[]} menu
 * @param {string} role
 * @returns {MenuItem[]}
 */
export function filterMenuByRole(menu, role) {
  return menu.reduce((acc, item) => {
    const roleAllowed = !item.roles || item.roles.length === 0 || item.roles.includes(role);
    if (!roleAllowed) return acc;

    if (item.children) {
      const filteredChildren = filterMenuByRole(item.children, role);
      if (filteredChildren.length === 0) return acc;
      return [...acc, { ...item, children: filteredChildren }];
    }

    return [...acc, item];
  }, []);
}

/**
 * Returns the breadcrumb trail from the root to the menu item matching the given pathname.
 * Returns [] when no match is found (e.g. for paths not listed in the menu).
 * @param {MenuItem[]} menu
 * @param {string} pathname
 * @returns {MenuItem[]}
 */
export function findTrail(menu, pathname) {
  for (const item of menu) {
    if (item.path === pathname) return [item];
    if (item.children) {
      const childTrail = findTrail(item.children, pathname);
      if (childTrail.length > 0) return [item, ...childTrail];
    }
  }
  return [];
}

/**
 * Returns all paths accessible to a given role.
 * @param {MenuItem[]} menu
 * @param {string} role
 * @returns {string[]}
 */
export function allowedPaths(menu, role) {
  return collectPaths(filterMenuByRole(menu, role));
}

/** @param {MenuItem[]} items @returns {string[]} */
function collectPaths(items) {
  return items.flatMap((item) => [
    ...(item.path ? [item.path] : []),
    ...(item.children ? collectPaths(item.children) : []),
  ]);
}

/**
 * Returns the first accessible path for a role, used for default post-login redirects.
 * @param {MenuItem[]} menu
 * @param {string} role
 * @returns {string|null}
 */
export function firstPath(menu, role) {
  return allowedPaths(menu, role)[0] ?? null;
}
