import { useEffect, useRef, useState } from 'react';
import { getUnsyncedDataSummary } from '../db';
import { supabase } from '../supabaseClient';

const POLL_MS = 2500;

/**
 * Polls badge counts every 2.5 s. Safe for offline use — silently retains
 * the last known value when a query fails (network down, DB unavailable, etc.).
 *
 * @param {string|undefined} role - The current user's role string
 * @returns {{ unsynced: number, pendingRequests: number }}
 */
export function useBadges(role) {
  const [unsynced, setUnsynced] = useState(0);
  const [pendingRequests, setPendingRequests] = useState(0);
  const alive = useRef(true);

  useEffect(() => {
    alive.current = true;

    const poll = async () => {
      // Local DB read — always works offline
      try {
        const summary = await getUnsyncedDataSummary();
        if (alive.current) setUnsynced(summary.total ?? 0);
      } catch {
        // Retain last value; swallow error silently
      }

      // Supabase count — only relevant for HQ, requires network
      if (role === 'headoffice_admin') {
        try {
          const { count } = await supabase
            .from('attendance_requests')
            .select('id', { count: 'exact', head: true })
            .eq('status', 'PENDING');
          if (alive.current) setPendingRequests(count ?? 0);
        } catch {
          // Retain last value
        }
      }
    };

    poll();
    const intervalId = setInterval(poll, POLL_MS);

    return () => {
      alive.current = false;
      clearInterval(intervalId);
    };
  }, [role]);

  return { unsynced, pendingRequests };
}
