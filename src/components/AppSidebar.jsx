import React from 'react';
import { NavLink, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import {
  Sidebar,
  SidebarContent,
  SidebarFooter,
  SidebarGroup,
  SidebarGroupContent,
  SidebarGroupLabel,
  SidebarHeader,
  SidebarMenu,
  SidebarMenuButton,
  SidebarMenuItem,
  SidebarRail,
  useSidebar,
} from '@/components/ui/sidebar';
import {
  LayoutDashboard,
  Camera,
  UserPlus,
  Users,
  History,
  LogOut,
} from 'lucide-react';

const navItems = [
  { label: 'Dashboard',       to: '/dashboard',       icon: LayoutDashboard, roles: ['all'] },
  { label: 'Scanner Absensi', to: '/absensi',          icon: Camera,          roles: ['estate_admin'] },
  { label: 'Input Karyawan',  to: '/karyawan',         icon: UserPlus,        roles: ['estate_admin'] },
  { label: 'Daftar Karyawan', to: '/daftar-karyawan',  icon: Users,           roles: ['all'] },
  { label: 'Log Absensi',     to: '/logs',             icon: History,         roles: ['all'] },
];

export function AppSidebar() {
  const { user, isAuthenticated, logout } = useAuth();
  const { isMobile, setOpenMobile, state } = useSidebar();
  const navigate = useNavigate();

  const isCollapsed = state === 'collapsed' && !isMobile;
  const isEstateAdmin = !user || user.role === 'estate_admin';

  const visibleItems = navItems.filter((item) => {
    if (!isAuthenticated) return false;
    if (item.roles.includes('all')) return true;
    if (item.roles.includes('estate_admin') && isEstateAdmin) return true;
    return false;
  });

  const handleNavClick = () => {
    if (isMobile) setOpenMobile(false);
  };

  const handleLogout = async () => {
    if (isMobile) setOpenMobile(false);
    const res = await logout(false);
    if (res && res.blocked) {
      const force = window.confirm(
        res.message +
          '\n\nPERINGATAN: Apakah Anda yakin ingin melakukan Logout Paksa? (Data offline yang belum tersinkron akan TERHAPUS permanen dan tidak bisa dikembalikan).'
      );
      if (force) await logout(true);
    } else {
      navigate('/login');
    }
  };

  return (
    <Sidebar collapsible="icon" className="app-sidebar-reui">
      {/* ── Header: Logo & Brand ── */}
      <SidebarHeader className="sidebar-header-reui">
        <div className={`sidebar-brand-row ${isCollapsed ? 'sidebar-brand-row--collapsed' : ''}`}>
          {/* Logo Icon — always visible */}
          <div className={`sidebar-logo-box ${isCollapsed ? 'sidebar-logo-box--collapsed' : ''}`}>
            <img src="/agrinas-logo.png" alt="AgriFace" className="sidebar-logo-img" />
          </div>
          {/* Brand Text — smooth fade + shrink on collapse */}
          <div className={`sidebar-collapsible-text ${isCollapsed ? 'sidebar-collapsible-text--hidden' : ''}`}>
            <span className="sidebar-brand-name">AgriFace</span>
            <span className="sidebar-brand-sub">1-to-1 Biometric Engine</span>
          </div>
        </div>
      </SidebarHeader>

      {/* ── Content: Main Navigation ── */}
      <SidebarContent className="sidebar-content-reui">
        <SidebarGroup>
          {/* Group label — smooth fade on collapse */}
          <SidebarGroupLabel className="sidebar-group-label-reui">
            <span className={`sidebar-collapsible-text ${isCollapsed ? 'sidebar-collapsible-text--hidden' : ''}`}>
              Navigasi
            </span>
          </SidebarGroupLabel>
          <SidebarGroupContent>
            <SidebarMenu>
              {visibleItems.map((item) => (
                <SidebarMenuItem key={item.to}>
                  <NavLink
                    to={item.to}
                    onClick={handleNavClick}
                    className="sidebar-navlink-wrapper"
                  >
                    {({ isActive }) => (
                      <SidebarMenuButton
                        isActive={isActive}
                        tooltip={item.label}
                        className={`sidebar-nav-btn ${isActive ? 'sidebar-nav-btn--active' : ''} ${isCollapsed ? 'sidebar-nav-btn--collapsed' : ''}`}
                      >
                        <div className="sidebar-icon-container">
                          <item.icon className="sidebar-nav-icon" />
                        </div>
                        {/* Label — smooth fade + shrink */}
                        <span className={`sidebar-nav-label sidebar-collapsible-text ${isCollapsed ? 'sidebar-collapsible-text--hidden' : ''}`}>
                          {item.label}
                        </span>
                      </SidebarMenuButton>
                    )}
                  </NavLink>
                </SidebarMenuItem>
              ))}
            </SidebarMenu>
          </SidebarGroupContent>
        </SidebarGroup>
      </SidebarContent>

      {/* ── Footer: User + Logout ── */}
      <SidebarFooter className="sidebar-footer-reui">
        {/* User info row — smooth fade on collapse */}
        {user && (
          <div className={`sidebar-user-row sidebar-collapsible-block ${isCollapsed ? 'sidebar-collapsible-block--hidden' : ''}`}>
            <div className="sidebar-user-avatar">
              <img src="/agrinas-logo.png" alt="Avatar" className="sidebar-user-avatar-img" />
            </div>
            <div className="sidebar-user-info">
              <span className="sidebar-user-name">{user.name}</span>
              <span className="sidebar-user-role">{user.kebun || 'Head Office'}</span>
            </div>
          </div>
        )}

        {/* Separator — fades on collapse */}
        <div className={`sidebar-footer-sep sidebar-collapsible-block ${isCollapsed ? 'sidebar-collapsible-block--hidden' : ''}`} />

        {/* Logout button */}
        <SidebarMenu>
          <SidebarMenuItem>
            <SidebarMenuButton
              tooltip="Logout"
              onClick={handleLogout}
              className={`sidebar-logout-btn-reui ${isCollapsed ? 'sidebar-nav-btn--collapsed' : ''}`}
            >
              <div className="sidebar-icon-container">
                <LogOut className="sidebar-nav-icon" />
              </div>
              <span className={`sidebar-nav-label sidebar-collapsible-text ${isCollapsed ? 'sidebar-collapsible-text--hidden' : ''}`}>
                Logout
              </span>
            </SidebarMenuButton>
          </SidebarMenuItem>
        </SidebarMenu>
      </SidebarFooter>

      <SidebarRail />
    </Sidebar>
  );
}
