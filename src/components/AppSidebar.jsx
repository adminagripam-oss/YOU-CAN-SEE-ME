import React, { useEffect, useMemo, useState } from 'react';
import { NavLink, useLocation, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { MENU, filterMenuByRole, findTrail } from '../config/menu';
import { useBadges } from '../hooks/useBadges';
import { useOnlineStatus } from '../hooks/useOnlineStatus';
import { cleanUserName } from '../utils/displayName';
import { Collapsible, CollapsibleTrigger, CollapsibleContent } from '@/components/ui/collapsible';
import {
  Sidebar, SidebarContent, SidebarFooter, SidebarGroup, SidebarGroupContent,
  SidebarGroupLabel, SidebarHeader, SidebarMenu, SidebarMenuBadge,
  SidebarMenuButton, SidebarMenuItem, SidebarMenuSub, SidebarMenuSubButton,
  SidebarMenuSubItem, SidebarRail, useSidebar,
} from '@/components/ui/sidebar';
import { Separator } from '@/components/ui/separator';
import { TooltipProvider, Tooltip, TooltipContent, TooltipTrigger } from '@/components/ui/tooltip';
import { Button } from './ui/button';
import {
  Dialog, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle,
} from './ui/dialog';
import {
  DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuLabel,
  DropdownMenuSeparator, DropdownMenuTrigger,
} from './ui/dropdown-menu';
import {
  Activity, ArrowLeftRight, Camera, CheckSquare, ChevronDown, Circle,
  ClipboardList, FileEdit, History, Inbox, LayoutDashboard, List,
  LogOut, Monitor, Package, RefreshCw, Users, UserPlus,
} from 'lucide-react';

const ICON_MAP = {
  Activity, ArrowLeftRight, Camera, CheckSquare, ClipboardList, FileEdit,
  History, Inbox, LayoutDashboard, List, Monitor, Package, RefreshCw, Users, UserPlus,
};

function getIcon(name) {
  const Icon = ICON_MAP[name];
  if (!Icon) {
    if (import.meta.env.DEV) console.warn(`[AppSidebar] Icon not found: "${name}". Falling back to Circle.`);
    return Circle;
  }
  return Icon;
}

function getInitials(name = '') {
  return name.trim().split(/\s+/).slice(0, 2).map((w) => w[0]).join('').toUpperCase() || '?';
}

function NavBadge({ count }) {
  if (!count || count <= 0) return null;
  const label = count > 99 ? '99+' : String(count);
  return (
    <SidebarMenuBadge aria-label={`${count} notifikasi`}>
      {label}
    </SidebarMenuBadge>
  );
}

function NavGroupItem({ item, pathname, isCollapsed, isMobile, badges, handleNavClick }) {
  const Icon = getIcon(item.icon);
  const badgeCount = item.badgeKey ? (badges[item.badgeKey] ?? 0) : 0;
  const isChildActive = item.children.some(
    (c) => pathname === c.path || pathname.startsWith(c.path + '/')
  );

  const [open, setOpen] = useState(isChildActive);

  useEffect(() => {
    if (isChildActive) {
      setOpen(true);
    }
  }, [isChildActive]);

  // Collapsed desktop mode: flyout DropdownMenu
  if (isCollapsed && !isMobile) {
    return (
      <SidebarMenuItem key={item.title} className="group-data-[collapsible=icon]:flex group-data-[collapsible=icon]:justify-center">
        <DropdownMenu>
          <DropdownMenuTrigger
            render={(
              <SidebarMenuButton
                tooltip={item.title}
                className="relative"
                aria-label={item.title}
              >
                <Icon className="size-4 shrink-0" />
                {badgeCount > 0 && (
                  <span
                    className="absolute top-1 right-1 size-2 rounded-full bg-red-500"
                    aria-hidden="true"
                  />
                )}
              </SidebarMenuButton>
            )}
          />
          <DropdownMenuContent side="right" align="start" sideOffset={8}>
            <DropdownMenuLabel>{item.title}</DropdownMenuLabel>
            <DropdownMenuSeparator />
            {item.children.map((child) => (
              <DropdownMenuItem
                key={child.path}
                render={<NavLink to={child.path} onClick={handleNavClick} />}
                className={pathname === child.path ? 'bg-accent font-medium' : ''}
              >
                {child.title}
              </DropdownMenuItem>
            ))}
          </DropdownMenuContent>
        </DropdownMenu>
      </SidebarMenuItem>
    );
  }

  // Expanded / mobile: inline collapsible
  return (
    <Collapsible open={open} onOpenChange={setOpen} className="group/collapsible">
      <SidebarMenuItem className="group-data-[collapsible=icon]:flex group-data-[collapsible=icon]:justify-center">
        <CollapsibleTrigger asChild>
          <SidebarMenuButton tooltip={item.title} isActive={isChildActive}>
            <Icon className="size-4 shrink-0" />
            <span>{item.title}</span>
            {badgeCount > 0 && <NavBadge count={badgeCount} />}
            <ChevronDown className="ml-auto size-4 shrink-0 text-sidebar-foreground/50 transition-transform duration-150 group-data-[state=open]/collapsible:rotate-180" />
          </SidebarMenuButton>
        </CollapsibleTrigger>
        <CollapsibleContent className="group-data-[collapsible=icon]:hidden">
          <SidebarMenuSub>
            {item.children.map((child) => {
              const childBadge = child.badgeKey ? (badges[child.badgeKey] ?? 0) : 0;
              const isChildActiveSub = pathname === child.path;
              return (
                <SidebarMenuSubItem key={child.path}>
                  <SidebarMenuSubButton
                    render={<NavLink to={child.path} onClick={handleNavClick} />}
                    isActive={isChildActiveSub}
                    style={isChildActiveSub ? {
                      backgroundColor: 'var(--sidebar-active-sub-bg)',
                      color: 'var(--sidebar-active-sub-fg)',
                      fontWeight: 500,
                    } : undefined}
                  >
                    <span>{child.title}</span>
                    {childBadge > 0 && <NavBadge count={childBadge} />}
                  </SidebarMenuSubButton>
                </SidebarMenuSubItem>
              );
            })}
          </SidebarMenuSub>
        </CollapsibleContent>
      </SidebarMenuItem>
    </Collapsible>
  );
}

export function AppSidebar() {
  const { user, logout } = useAuth();
  const { isMobile, setOpenMobile, state } = useSidebar();
  const navigate = useNavigate();
  const { pathname } = useLocation();
  const badges = useBadges(user?.role);
  const isOnline = useOnlineStatus();
  const isCollapsed = state === 'collapsed';

  const filteredMenu = useMemo(
    () => (user ? filterMenuByRole(MENU, user.role) : []),
    [user]
  );

  const [forceLogoutOpen, setForceLogoutOpen] = useState(false);
  const [logoutBlockedMsg, setLogoutBlockedMsg] = useState('');
  const [isLoggingOut, setIsLoggingOut] = useState(false);

  const handleNavClick = () => {
    if (isMobile) setOpenMobile(false);
  };

  const handleLogout = async () => {
    if (isMobile) setOpenMobile(false);
    setIsLoggingOut(true);
    const res = await logout(false);
    setIsLoggingOut(false);
    if (res?.blocked) {
      setLogoutBlockedMsg(res.message);
      setForceLogoutOpen(true);
    } else {
      navigate('/login');
    }
  };

  const handleForceLogout = async () => {
    setIsLoggingOut(true);
    await logout(true);
    setIsLoggingOut(false);
    setForceLogoutOpen(false);
  };

  const renderItem = (item) => {
    const Icon = getIcon(item.icon);
    const badgeCount = item.badgeKey ? (badges[item.badgeKey] ?? 0) : 0;

    if (item.children) {
      return (
        <NavGroupItem
          key={item.title}
          item={item}
          pathname={pathname}
          isCollapsed={isCollapsed}
          isMobile={isMobile}
          badges={badges}
          handleNavClick={handleNavClick}
        />
      );
    }

    // Leaf item: single NavLink
    return (
      <SidebarMenuItem key={item.path} className="group-data-[collapsible=icon]:flex group-data-[collapsible=icon]:justify-center">
        <SidebarMenuButton
          render={<NavLink to={item.path} onClick={handleNavClick} />}
          isActive={pathname === item.path}
          tooltip={item.title}
          className="relative"
        >
          <Icon className="size-4 shrink-0" />
          <span>{item.title}</span>
          {badgeCount > 0 && !isCollapsed && <NavBadge count={badgeCount} />}
          {badgeCount > 0 && isCollapsed && (
            <span
              className="absolute top-1 right-1 size-2 rounded-full bg-red-500"
              aria-hidden="true"
            />
          )}
        </SidebarMenuButton>
      </SidebarMenuItem>
    );
  };

  const displayName = cleanUserName(user?.name);

  return (
    <TooltipProvider>
      <Sidebar collapsible="icon" style={{ '--sidebar-width': '18rem' }}>
        <SidebarHeader>
          <div className="flex items-center gap-2.5 px-2 py-2 group-data-[collapsible=icon]:justify-center group-data-[collapsible=icon]:px-0">
            <div className="flex size-8 shrink-0 items-center justify-center rounded-lg overflow-hidden">
              <img
                src="/agrinas-logo.png"
                alt="AgriFace logo"
                className="size-8 object-contain"
              />
            </div>
            <div className="flex flex-col min-w-0 group-data-[collapsible=icon]:hidden">
              <span className="text-sm font-semibold leading-tight text-sidebar-foreground truncate">
                AgriFace
              </span>
              <span className="text-xs leading-tight text-sidebar-foreground/60 truncate">
                1-to-1 Biometric Engine
              </span>
            </div>
          </div>
        </SidebarHeader>

        <SidebarContent>
          <SidebarGroup>
            <SidebarGroupLabel className="group-data-[collapsible=icon]:hidden">
              Navigasi
            </SidebarGroupLabel>
            <SidebarGroupContent>
              <SidebarMenu aria-label="Menu utama">
                {filteredMenu.map(renderItem)}
              </SidebarMenu>
            </SidebarGroupContent>
          </SidebarGroup>
        </SidebarContent>

        <SidebarFooter>
          {user && (
            <>
              <Separator className="my-0 group-data-[collapsible=icon]:hidden" />

              {/* Expanded: full user card */}
              <div className="flex items-center gap-2.5 px-2 py-2 group-data-[collapsible=icon]:hidden">
                <div className="relative shrink-0">
                  <div
                    className="flex size-8 items-center justify-center rounded-full text-white text-xs font-semibold"
                    style={{ backgroundColor: '#14532d' }}
                    aria-hidden="true"
                  >
                    {getInitials(displayName)}
                  </div>
                  <span
                    className={`absolute -bottom-0.5 -right-0.5 size-2.5 rounded-full border-2 border-white ${isOnline ? 'bg-emerald-500' : 'bg-amber-400'}`}
                    aria-label={isOnline ? 'Online' : 'Offline'}
                  />
                </div>
                <div className="flex flex-col min-w-0">
                  <span
                    className="text-sm font-medium text-sidebar-foreground truncate leading-tight"
                    title={displayName}
                  >
                    {displayName}
                  </span>
                  <span className="text-xs text-sidebar-foreground/60 truncate leading-tight">
                    {user.kebun || 'Head Office'}
                  </span>
                </div>
              </div>

              {/* Collapsed: avatar only with status dot, wrapped in Tooltip */}
              <div className="hidden group-data-[collapsible=icon]:flex justify-center group-data-[collapsible=icon]:px-0 py-2">
                <Tooltip>
                  <TooltipTrigger render={<button type="button" className="relative cursor-default" />}>
                    <div
                      className="flex size-8 items-center justify-center rounded-full text-white text-xs font-semibold"
                      style={{ backgroundColor: '#14532d' }}
                    >
                      {getInitials(displayName)}
                    </div>
                    <span
                      className={`absolute -bottom-0.5 -right-0.5 size-2.5 rounded-full border-2 border-white ${isOnline ? 'bg-emerald-500' : 'bg-amber-400'}`}
                    />
                  </TooltipTrigger>
                  <TooltipContent side="right">
                    <p className="font-medium">{displayName}</p>
                    <p className="text-xs opacity-70">{user.kebun || 'Head Office'}</p>
                    <p className="text-xs mt-0.5">
                      {isOnline ? '🟢 Online' : '🟡 Offline'}
                    </p>
                  </TooltipContent>
                </Tooltip>
              </div>
            </>
          )}

          <SidebarMenu>
            <SidebarMenuItem className="group-data-[collapsible=icon]:flex group-data-[collapsible=icon]:justify-center">
              <SidebarMenuButton
                tooltip="Logout"
                onClick={handleLogout}
                disabled={isLoggingOut}
                className="text-red-700 hover:text-red-700 hover:bg-red-50 dark:text-red-400 dark:hover:bg-red-950/30"
                style={{ minHeight: '44px' }}
              >
                <LogOut className="size-4 shrink-0 text-red-700 dark:text-red-400" />
                <span>{isLoggingOut ? 'Keluar...' : 'Logout'}</span>
              </SidebarMenuButton>
            </SidebarMenuItem>
          </SidebarMenu>
        </SidebarFooter>

        <SidebarRail />
      </Sidebar>

      <Dialog
        open={forceLogoutOpen}
        onOpenChange={(open) => { if (!open && !isLoggingOut) setForceLogoutOpen(false); }}
      >
        <DialogContent style={{ maxWidth: '420px' }}>
          <DialogHeader>
            <DialogTitle>Peringatan: Data Belum Tersinkronisasi</DialogTitle>
            <DialogDescription style={{ marginTop: '0.5rem' }}>
              {logoutBlockedMsg}
            </DialogDescription>
          </DialogHeader>
          <p style={{ fontSize: '0.85rem', color: '#ef4444', fontWeight: 600, marginTop: '0.25rem' }}>
            Data offline akan terhapus permanen jika logout paksa dilanjutkan.
          </p>
          <DialogFooter style={{ marginTop: '1rem', gap: '8px', display: 'flex', justifyContent: 'flex-end' }}>
            <Button
              variant="outline"
              onClick={() => setForceLogoutOpen(false)}
              disabled={isLoggingOut}
              style={{ minHeight: '44px' }}
            >
              Batal
            </Button>
            <Button
              variant="destructive"
              onClick={handleForceLogout}
              disabled={isLoggingOut}
              style={{ minHeight: '44px' }}
            >
              {isLoggingOut ? 'Keluar...' : 'Logout Paksa'}
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </TooltipProvider>
  );
}
