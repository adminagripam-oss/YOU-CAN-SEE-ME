import React, { createContext, useContext } from 'react';

const AppDataContext = createContext(null);

/**
 * Provides shared application state (employees, logs, actions) to all
 * route-level components without prop drilling through the router.
 */
export function AppDataProvider({ value, children }) {
  return <AppDataContext.Provider value={value}>{children}</AppDataContext.Provider>;
}

/**
 * @returns {ReturnType<typeof createContext<null>>}
 */
export function useAppData() {
  const ctx = useContext(AppDataContext);
  if (!ctx) throw new Error('useAppData must be used within AppDataProvider');
  return ctx;
}
