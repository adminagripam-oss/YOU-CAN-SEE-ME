# Git Workflow Rules  
- Always create a new branch before making code changes. Do not commit or push directly to main or dev. 

- **Offline Face Enrollment**: When implementing or modifying face registration (enrollment) features, always disable the face similarity/duplication threshold checks if the application is in Offline mode (e.g. !isOnline), allowing any face to be registered without duplication errors.

- **SQLite Connection Consistency**: Whenever initializing @capacitor-community/sqlite (Native SQLite), always run sqlite.checkConnectionsConsistency() at the start of the app lifecycle to synchronize native connections after a WebView reload (OTA updates, Live Reload). This prevents data from appearing as 'lost' due to failed, out-of-sync queries.

- **Offline ID Type Safety**: When deleting or manipulating employee records from local storage (IndexedDB/SQLite), never cast IDs to Number() blindly (e.g. Number(employeeId)). Offline-registered employees use string prefixes (e.g., off_123456 or 	mp_...), and casting them to Number results in NaN, which causes silent failures in sync deduplication and manual deletions. Always pass IDs as strings or handle them as string | number types natively.

- **Offline Data Completeness (Dual-Write)**: When performing dual-writes or caching (e.g. saving an offline record into a local queue and a local cache simultaneously), always ensure the schema matches exactly. Omitted properties like `kebun` or `nama_kebun` will cause UI filters to silently hide offline records.
- **Global UI Reactivity (Event Listeners)**: Whenever a background or cross-component action modifies offline data (e.g. `queueOfflineAttendance`), always ensure the root state manager (`App.jsx`) has a corresponding `window.addEventListener('event_name')` to immediately refetch and re-render the updated state (e.g. `fetchLogs()`).

- **UI Data Table Standards**: Always include a "No." (Number) column as the first column in any data tables (e.g., lists of logs, employees, or requests) to improve row referenceability. Set its width appropriately (e.g. 50px) and center the text.

- **Offline UI Data Grouping**: When grouping or deduplicating records in the UI (e.g., merging Check-In and Check-Out logs), always use the primary "id" or "employee_id" as the grouping key. Never rely on fields like "nik" that may be empty, "-", or duplicated during offline mode (pending sync) to avoid inadvertently merging records from different users.

- **Offline Data Completeness (Dual-Write)**: When performing dual-writes or caching (e.g. saving an offline record into a local queue and a local cache simultaneously), always ensure the schema matches exactly. Omitted properties like `kebun` or `nama_kebun` will cause UI filters to silently hide offline records.
- **Offline Data Sync Sanitization**: Before inserting offline-created rows (such as offline registered employees) into Supabase, always supply explicit fallback strings (e.g., "-" or "Tanpa Nama") for optional fields. Leaving them as `null` can silently violate `NOT NULL` constraints on the remote schema and cause synchronization failures.
- **SQLite Boolean Deserialization**: Always account for SQLite returning `1`/`0` instead of `true`/`false`. Direct comparisons like `emp.has_master_biometric === true` will fail. Check against `1` or cast properly.

- **Dashboard KPI Consistency**: When computing aggregated attendance KPIs (TK Hadir, Izin, Sakit, Mangkir) on DashboardPage.jsx, always pre-group the raw `logs` array by `employee_id` (same pattern as TabAttendanceLogs.jsx) before counting. Never call `.filter().length` directly on raw logs as it double-counts check-in/check-out pairs. Status 'Lupa Check-out' must be counted under 'TK Hadir', not 'Mangkir'.
- **Sync Engine isSyncing Guard**: The `isSyncing` flag in `syncPendingAttendanceLogs` must NEVER silently block sync on subsequent calls. If the flag is already `true` at the start of a new sync call, reset it to `false` with a console.warn before proceeding. This prevents offline logs from being permanently stuck due to a prior crash in the sync pipeline.

- **Offline Sync Display Cache Integrity**: The dual-table architecture (sync queue + display cache) requires that after a successful Supabase upload, the display cache entry in local_attendance_logs must also be marked is_synced = 1. Never require BOTH text AND photo sync to be done before setting is_synced = 1; set it as soon as text data reaches Supabase. This ensures etchLogs cleanup correctly removes ghost offline entries on next refresh.
- **Offline Sync Dedup Must Use NIK Fallback**: When deduplicating merged local+online logs in etchLogs, always build a secondary NIK-based signature (
ik_{nik}_{date}_{type}) for online logs in addition to the employee_id-based signature. For offline logs with temp employee_id (non-numeric, e.g. off_emp_xxx), match against the NIK signature. This prevents ghost offline entries from appearing alongside their synced online counterparts when the employee_id changed during sync.
- **db.attendance_logs Has No filter() Method**: The db.attendance_logs object in db.js is a custom wrapper with only: put, ulkPut, 	oArray, delete, getTodayLogs, clear. Never call .filter() directly on it. Always use .toArray() first, then apply JavaScript array .filter() on the result.

- **Offline SQLite Primary Key Retrieval**: When inserting new rows into Capacitor SQLite native tables, ALWAYS retrieve the auto-incremented primary key synchronously using `runRes.changes?.lastId` from the insert execution result. Never rely on subsequent `SELECT last_insert_rowid()` queries (which are highly prone to race conditions), and never allow a fallback to `null` to be used as a prefix (e.g., `offline_null`) for IndexedDB dual-write caches, as this will cause silent data overwriting.

- **Offline Deletion Architecture**: When handling delete operations in the React + Capacitor app, always follow the offline-first deletion pattern: 
  1. **Optimistic UI Update**: Immediately update React/Zustand state to hide the item from the user.
  2. **Soft Delete Locally**: Do not hard-delete immediately. Mark the local database (SQLite/IndexedDB) record with a flag like `syncStatus: 'PENDING_DELETE'`.
  3. **Queue Network Action**: Add the delete request (endpoint, HTTP DELETE, item ID, timestamp) to the `offline_sync_queue`.
  4. **Check & Listen**: Use Capacitor `Network.getStatus()` to trigger sync if online, or wait if offline. Use `Network.addListener` to detect connectivity changes.
  5. **Cleanup & Hard Delete**: Only permanently delete the local record and remove the queue task upon receiving a successful server response (HTTP 200/204).

- **Offline Data Integration (Native vs Web)**: Whenever implementing central data fetching logic (like `fetchEmployees` in `App.jsx`), always explicitly branch data retrieval using `Capacitor.isNativePlatform()`. In native environments, read exclusively from SQLite caches (`sqliteGetEmployeesCache`, `sqliteGetPendingEmployees`). Failure to do so will cause the React state to read from an empty/stale IndexedDB fallback, preventing the UI/Dashboard from reacting to local offline changes seamlessly.

- **Offline Deletion Tokenization (Sync Architecture)**: Differentiate deletion logic based on the origin of the entity.
  1. For entities with temporary offline IDs (`off_emp_...`), perform a true local hard-delete, as the remote database has no knowledge of them.
  2. For entities with authoritative remote IDs (UUIDs), do not hard delete locally. Instead, issue a deletion token by inserting the ID into `offline_sync_queue` and flagging the local cache with `syncStatus: 'PENDING_DELETE'`. The `syncEngine` must process these deletion tokens (Tier 1) *before* processing any new inserts/updates to prevent Unique Constraint violations.
