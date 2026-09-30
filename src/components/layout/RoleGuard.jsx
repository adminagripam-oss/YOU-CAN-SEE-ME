import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { useAuth } from '../../context/AuthContext';
import { firstPath, MENU } from '../../config/menu';

/**
 * Client-side UX guard. Redirects users whose role is not in `allowedRoles`
 * to their role's default page.
 *
 * Data security is enforced at the server level by Supabase RLS — this guard
 * is for navigation UX only.
 *
 * @param {{ allowedRoles?: string[] }} props
 */
export default function RoleGuard({ allowedRoles }) {
  const { user } = useAuth();

  if (!user) return <Navigate to="/login" replace />;

  if (allowedRoles && !allowedRoles.includes(user.role)) {
    return <Navigate to={firstPath(MENU, user.role) ?? '/dashboard'} replace />;
  }

  return <Outlet />;
}
