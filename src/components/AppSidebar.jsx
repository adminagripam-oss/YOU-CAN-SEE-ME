import React, { useEffect, useMemo, useRef, useState } from 'react';
import { NavLink, useLocation, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { MENU, filterMenuByRole, findTrail } from '../config/menu';
import { useBadges } from '../hooks/useBadges';
import { useOnlineStatus } from '../hooks/useOnlineStatus';
import { cleanUserName } from '../utils/displayName';
import { Collapsible, CollapsibleTrigger, CollapsibleContent } from '@/components/ui/collapsible';
import {
  Sidebar, SidebarContent, SidebarFooter, SidebarGroup, SidebarGroupContent,
  SidebarGroupLabel, SidebarHeader, SidebarMenu,
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
  DropdownMenuSeparator, DropdownMenuTrigger, DropdownMenuGroup,
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
  return (
    <span
      className="relative ml-1.5 flex size-2 shrink-0"
      role="status"
      aria-label={`${count} item menunggu`}
    >
      <span className="absolute inline-flex size-full rounded-full bg-red-500 opacity-75 motion-safe:animate-ping" />
      <span className="relative inline-flex size-2 rounded-full bg-red-500" />
    </span>
  );
}

function NavGroupItem({ item, pathname, isCollapsed, isMobile, badges, handleNavClick, open, onOpenChange }) {
  const Icon = getIcon(item.icon);
  const badgeCount = item.badgeKey ? (badges[item.badgeKey] ?? 0) : 0;
  const isChildActive = item.children.some(
    (c) => pathname === c.path || pathname.startsWith(c.path + '/')
  );

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
                <div className="relative inline-flex">
                  <Icon className="size-4 shrink-0" />
                  {badgeCount > 0 && (
                    <span
                      className="absolute -right-1 -top-1 flex size-2"
                      aria-label={`${badgeCount} menunggu`}
                    >
                      <span className="absolute inline-flex size-full rounded-full bg-red-500 opacity-75 motion-safe:animate-ping" />
                      <span className="relative inline-flex size-2 rounded-full bg-red-500" />
                    </span>
                  )}
                </div>
              </SidebarMenuButton>
            )}
          />
          <DropdownMenuContent side="right" align="start" sideOffset={8}>
            <DropdownMenuGroup>
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
            </DropdownMenuGroup>
          </DropdownMenuContent>
        </DropdownMenu>
      </SidebarMenuItem>
    );
  }

  // Expanded / mobile: inline collapsible
  return (
    <Collapsible open={open} onOpenChange={onOpenChange} className="group/collapsible">
      <SidebarMenuItem className="group-data-[collapsible=icon]:flex group-data-[collapsible=icon]:justify-center">
        <SidebarMenuButton tooltip={item.title} isActive={isChildActive} render={<CollapsibleTrigger />}>
          <Icon className="size-4 shrink-0" />
          <span>{item.title}</span>
          {badgeCount > 0 && <NavBadge count={badgeCount} />}
          <ChevronDown className="ml-auto size-4 shrink-0 text-sidebar-foreground/50 motion-safe:transition-transform motion-safe:duration-200 group-data-[state=open]/collapsible:rotate-180" />
        </SidebarMenuButton>
        <CollapsibleContent className="group-data-[collapsible=icon]:hidden overflow-hidden motion-safe:data-[state=open]:animate-collapsible-down motion-safe:data-[state=closed]:animate-collapsible-up">
          <SidebarMenuSub>
            {item.children.map((child, i) => {
              const childBadge = child.badgeKey ? (badges[child.badgeKey] ?? 0) : 0;
              const isChildActiveSub = pathname === child.path;
              return (
                <SidebarMenuSubItem
                  key={child.path}
                  className="motion-safe:animate-in motion-safe:fade-in-0 motion-safe:slide-in-from-left-1 motion-safe:duration-200 motion-safe:fill-mode-both"
                  style={{ animationDelay: `${i * 30}ms` }}
                >
                  <SidebarMenuSubButton
                    render={<NavLink to={child.path} onClick={handleNavClick} />}
                    isActive={isChildActiveSub}
                    className="relative before:absolute before:-left-[13px] before:top-1.5 before:bottom-1.5 before:w-0.5 before:rounded-full before:bg-green-700 before:origin-center before:scale-y-0 data-[active=true]:before:scale-y-100 motion-safe:before:transition-transform motion-safe:before:duration-200"
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
  const { isMobile, setOpenMobile, state, openMobile } = useSidebar();
  const navigate = useNavigate();
  const { pathname } = useLocation();
  const badges = useBadges(user?.role);
  const isOnline = useOnlineStatus();
  const isCollapsed = state === 'collapsed';

  const filteredMenu = useMemo(
    () => (user ? filterMenuByRole(MENU, user.role) : []),
    [user]
  );

  const [openGroups, setOpenGroups] = useState({});
  const isGroupOpen = (title) => openGroups[title] ?? true;
  const setGroupOpen = (title, value) => {
    setOpenGroups((prev) => ({ ...prev, [title]: value }));
  };

  const expanded = isMobile ? openMobile : state === 'expanded';
  const wasExpanded = useRef(expanded);
  useEffect(() => {
    if (expanded && !wasExpanded.current) setOpenGroups({});
    wasExpanded.current = expanded;
  }, [expanded]);

  useEffect(() => {
    filteredMenu.forEach((item) => {
      if (item.children?.some((c) => pathname === c.path || pathname.startsWith(c.path + '/'))) {
        setGroupOpen(item.title, true);
      }
    });
  }, [pathname]);

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
          open={isGroupOpen(item.title)}
          onOpenChange={(v) => setGroupOpen(item.title, v)}
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
          <div className="relative inline-flex items-center justify-center">
            <Icon className="size-4 shrink-0" />
            {badgeCount > 0 && isCollapsed && (
              <span
                className="absolute -right-1 -top-1 flex size-2"
                aria-label={`${badgeCount} menunggu`}
              >
                <span className="absolute inline-flex size-full rounded-full bg-red-500 opacity-75 motion-safe:animate-ping" />
                <span className="relative inline-flex size-2 rounded-full bg-red-500" />
              </span>
            )}
          </div>
          <span>{item.title}</span>
          {badgeCount > 0 && !isCollapsed && <NavBadge count={badgeCount} />}
        </SidebarMenuButton>
      </SidebarMenuItem>
    );
  };

  const displayName = cleanUserName(user?.name);

  return (
    <TooltipProvider>
      <Sidebar collapsible="icon">
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
