const __vite__mapDeps=(i,m=__vite__mapDeps,d=(m.f||(m.f=["assets/web-C1P6YYig.js","assets/dist-Cnp6P8yH.js"])))=>i.map(i=>d[i]);
import{t as e}from"./preload-helper-Czpn1I53.js";import{i as t,t as n}from"./dist-Cnp6P8yH.js";var r=Object.create,i=Object.defineProperty,a=Object.getOwnPropertyDescriptor,o=Object.getOwnPropertyNames,s=Object.getPrototypeOf,c=Object.prototype.hasOwnProperty,l=(e,t)=>()=>(t||(e((t={exports:{}}).exports,t),e=null),t.exports),u=(e,t)=>{let n={};for(var r in e)i(n,r,{get:e[r],enumerable:!0});return t||i(n,Symbol.toStringTag,{value:`Module`}),n},d=(e,t,n,r)=>{if(t&&typeof t==`object`||typeof t==`function`)for(var s=o(t),l=0,u=s.length,d;l<u;l++)d=s[l],!c.call(e,d)&&d!==n&&i(e,d,{get:(e=>t[e]).bind(null,d),enumerable:!(r=a(t,d))||r.enumerable});return e},f=(e,t,n)=>(n=e==null?{}:r(s(e)),d(t||!e||!e.__esModule?i(n,`default`,{value:e,enumerable:!0}):n,e)),p=class{constructor(e){this.sqlite=e,this._connectionDict=new Map}async initWebStore(){try{return await this.sqlite.initWebStore(),Promise.resolve()}catch(e){return Promise.reject(e)}}async saveToStore(e){try{return await this.sqlite.saveToStore({database:e}),Promise.resolve()}catch(e){return Promise.reject(e)}}async saveToLocalDisk(e){try{return await this.sqlite.saveToLocalDisk({database:e}),Promise.resolve()}catch(e){return Promise.reject(e)}}async getFromLocalDiskToStore(e){let t=e??!0;try{return await this.sqlite.getFromLocalDiskToStore({overwrite:t}),Promise.resolve()}catch(e){return Promise.reject(e)}}async echo(e){try{let t=await this.sqlite.echo({value:e});return Promise.resolve(t)}catch(e){return Promise.reject(e)}}async isSecretStored(){try{let e=await this.sqlite.isSecretStored();return Promise.resolve(e)}catch(e){return Promise.reject(e)}}async setEncryptionSecret(e){try{return await this.sqlite.setEncryptionSecret({passphrase:e}),Promise.resolve()}catch(e){return Promise.reject(e)}}async changeEncryptionSecret(e,t){try{return await this.sqlite.changeEncryptionSecret({passphrase:e,oldpassphrase:t}),Promise.resolve()}catch(e){return Promise.reject(e)}}async clearEncryptionSecret(){try{return await this.sqlite.clearEncryptionSecret(),Promise.resolve()}catch(e){return Promise.reject(e)}}async checkEncryptionSecret(e){try{let t=await this.sqlite.checkEncryptionSecret({passphrase:e});return Promise.resolve(t)}catch(e){return Promise.reject(e)}}async addUpgradeStatement(e,t){try{return e.endsWith(`.db`)&&(e=e.slice(0,-3)),await this.sqlite.addUpgradeStatement({database:e,upgrade:t}),Promise.resolve()}catch(e){return Promise.reject(e)}}async createConnection(e,t,n,r,i){try{e.endsWith(`.db`)&&(e=e.slice(0,-3)),await this.sqlite.createConnection({database:e,encrypted:t,mode:n,version:r,readonly:i});let a=new m(e,i,this.sqlite),o=i?`RO_${e}`:`RW_${e}`;return this._connectionDict.set(o,a),Promise.resolve(a)}catch(e){return Promise.reject(e)}}async closeConnection(e,t){try{e.endsWith(`.db`)&&(e=e.slice(0,-3)),await this.sqlite.closeConnection({database:e,readonly:t});let n=t?`RO_${e}`:`RW_${e}`;return this._connectionDict.delete(n),Promise.resolve()}catch(e){return Promise.reject(e)}}async isConnection(e,t){let n={};e.endsWith(`.db`)&&(e=e.slice(0,-3));let r=t?`RO_${e}`:`RW_${e}`;return n.result=this._connectionDict.has(r),Promise.resolve(n)}async retrieveConnection(e,t){e.endsWith(`.db`)&&(e=e.slice(0,-3));let n=t?`RO_${e}`:`RW_${e}`;if(this._connectionDict.has(n)){let t=this._connectionDict.get(n);return t===void 0?Promise.reject(`Connection ${e} is undefined`):Promise.resolve(t)}else return Promise.reject(`Connection ${e} does not exist`)}async getNCDatabasePath(e,t){try{let n=await this.sqlite.getNCDatabasePath({path:e,database:t});return Promise.resolve(n)}catch(e){return Promise.reject(e)}}async createNCConnection(e,t){try{await this.sqlite.createNCConnection({databasePath:e,version:t});let n=new m(e,!0,this.sqlite),r=`RO_${e})`;return this._connectionDict.set(r,n),Promise.resolve(n)}catch(e){return Promise.reject(e)}}async closeNCConnection(e){try{await this.sqlite.closeNCConnection({databasePath:e});let t=`RO_${e})`;return this._connectionDict.delete(t),Promise.resolve()}catch(e){return Promise.reject(e)}}async isNCConnection(e){let t={},n=`RO_${e})`;return t.result=this._connectionDict.has(n),Promise.resolve(t)}async retrieveNCConnection(e){if(this._connectionDict.has(e)){let t=`RO_${e})`,n=this._connectionDict.get(t);return n===void 0?Promise.reject(`Connection ${e} is undefined`):Promise.resolve(n)}else return Promise.reject(`Connection ${e} does not exist`)}async isNCDatabase(e){try{let t=await this.sqlite.isNCDatabase({databasePath:e});return Promise.resolve(t)}catch(e){return Promise.reject(e)}}async retrieveAllConnections(){return this._connectionDict}async closeAllConnections(){let e=new Map;try{for(let t of this._connectionDict.keys()){let n=t.substring(3),r=t.substring(0,3)===`RO_`;await this.sqlite.closeConnection({database:n,readonly:r}),e.set(t,null)}for(let t of e.keys())this._connectionDict.delete(t);return Promise.resolve()}catch(e){return Promise.reject(e)}}async checkConnectionsConsistency(){try{let e=[...this._connectionDict.keys()],t=[],n=[];for(let r of e)t.push(r.substring(0,2)),n.push(r.substring(3));let r=await this.sqlite.checkConnectionsConsistency({dbNames:n,openModes:t});return r.result||(this._connectionDict=new Map),Promise.resolve(r)}catch(e){return this._connectionDict=new Map,Promise.reject(e)}}async importFromJson(e){try{let t=await this.sqlite.importFromJson({jsonstring:e});return Promise.resolve(t)}catch(e){return Promise.reject(e)}}async isJsonValid(e){try{let t=await this.sqlite.isJsonValid({jsonstring:e});return Promise.resolve(t)}catch(e){return Promise.reject(e)}}async copyFromAssets(e){let t=e??!0;try{return await this.sqlite.copyFromAssets({overwrite:t}),Promise.resolve()}catch(e){return Promise.reject(e)}}async getFromHTTPRequest(e,t){let n=t??!0;try{return await this.sqlite.getFromHTTPRequest({url:e,overwrite:n}),Promise.resolve()}catch(e){return Promise.reject(e)}}async isDatabaseEncrypted(e){e.endsWith(`.db`)&&(e=e.slice(0,-3));try{let t=await this.sqlite.isDatabaseEncrypted({database:e});return Promise.resolve(t)}catch(e){return Promise.reject(e)}}async isInConfigEncryption(){try{let e=await this.sqlite.isInConfigEncryption();return Promise.resolve(e)}catch(e){return Promise.reject(e)}}async isInConfigBiometricAuth(){try{let e=await this.sqlite.isInConfigBiometricAuth();return Promise.resolve(e)}catch(e){return Promise.reject(e)}}async isDatabase(e){e.endsWith(`.db`)&&(e=e.slice(0,-3));try{let t=await this.sqlite.isDatabase({database:e});return Promise.resolve(t)}catch(e){return Promise.reject(e)}}async getDatabaseList(){try{let e=(await this.sqlite.getDatabaseList()).values;e.sort();let t={values:e};return Promise.resolve(t)}catch(e){return Promise.reject(e)}}async getMigratableDbList(e){let t=e||`default`;try{let e=await this.sqlite.getMigratableDbList({folderPath:t});return Promise.resolve(e)}catch(e){return Promise.reject(e)}}async addSQLiteSuffix(e,t){let n=e||`default`,r=t||[];try{let e=await this.sqlite.addSQLiteSuffix({folderPath:n,dbNameList:r});return Promise.resolve(e)}catch(e){return Promise.reject(e)}}async deleteOldDatabases(e,t){let n=e||`default`,r=t||[];try{let e=await this.sqlite.deleteOldDatabases({folderPath:n,dbNameList:r});return Promise.resolve(e)}catch(e){return Promise.reject(e)}}async moveDatabasesAndAddSuffix(e,t){let n=e||`default`,r=t||[];return this.sqlite.moveDatabasesAndAddSuffix({folderPath:n,dbNameList:r})}},m=class{constructor(e,t,n){this.dbName=e,this.readonly=t,this.sqlite=n}getConnectionDBName(){return this.dbName}getConnectionReadOnly(){return this.readonly}async open(){try{return await this.sqlite.open({database:this.dbName,readonly:this.readonly}),Promise.resolve()}catch(e){return Promise.reject(e)}}async close(){try{return await this.sqlite.close({database:this.dbName,readonly:this.readonly}),Promise.resolve()}catch(e){return Promise.reject(e)}}async beginTransaction(){try{let e=await this.sqlite.beginTransaction({database:this.dbName});return Promise.resolve(e)}catch(e){return Promise.reject(e)}}async commitTransaction(){try{let e=await this.sqlite.commitTransaction({database:this.dbName});return Promise.resolve(e)}catch(e){return Promise.reject(e)}}async rollbackTransaction(){try{let e=await this.sqlite.rollbackTransaction({database:this.dbName});return Promise.resolve(e)}catch(e){return Promise.reject(e)}}async isTransactionActive(){try{let e=await this.sqlite.isTransactionActive({database:this.dbName});return Promise.resolve(e)}catch(e){return Promise.reject(e)}}async loadExtension(e){try{return await this.sqlite.loadExtension({database:this.dbName,path:e,readonly:this.readonly}),Promise.resolve()}catch(e){return Promise.reject(e)}}async enableLoadExtension(e){try{return await this.sqlite.enableLoadExtension({database:this.dbName,toggle:e,readonly:this.readonly}),Promise.resolve()}catch(e){return Promise.reject(e)}}async getUrl(){try{let e=await this.sqlite.getUrl({database:this.dbName,readonly:this.readonly});return Promise.resolve(e)}catch(e){return Promise.reject(e)}}async getVersion(){try{let e=await this.sqlite.getVersion({database:this.dbName,readonly:this.readonly});return Promise.resolve(e)}catch(e){return Promise.reject(e)}}async getTableList(){try{let e=await this.sqlite.getTableList({database:this.dbName,readonly:this.readonly});return Promise.resolve(e)}catch(e){return Promise.reject(e)}}async execute(e,t=!0,n=!0){try{if(this.readonly)return Promise.reject(`not allowed in read-only mode`);{let r=await this.sqlite.execute({database:this.dbName,statements:e,transaction:t,readonly:!1,isSQL92:n});return Promise.resolve(r)}}catch(e){return Promise.reject(e)}}async query(e,t,n=!0){let r;try{return r=t&&t.length>0?await this.sqlite.query({database:this.dbName,statement:e,values:t,readonly:this.readonly,isSQL92:!0}):await this.sqlite.query({database:this.dbName,statement:e,values:[],readonly:this.readonly,isSQL92:n}),r=await this.reorderRows(r),Promise.resolve(r)}catch(e){return Promise.reject(e)}}async run(e,t,n=!0,r=`no`,i=!0){let a;try{return this.readonly?Promise.reject(`not allowed in read-only mode`):(a=t&&t.length>0?await this.sqlite.run({database:this.dbName,statement:e,values:t,transaction:n,readonly:!1,returnMode:r,isSQL92:!0}):await this.sqlite.run({database:this.dbName,statement:e,values:[],transaction:n,readonly:!1,returnMode:r,isSQL92:i}),a.changes=await this.reorderRows(a.changes),Promise.resolve(a))}catch(e){return Promise.reject(e)}}async executeSet(e,t=!0,n=`no`,r=!0){let i;try{return this.readonly?Promise.reject(`not allowed in read-only mode`):(i=await this.sqlite.executeSet({database:this.dbName,set:e,transaction:t,readonly:!1,returnMode:n,isSQL92:r}),i.changes=await this.reorderRows(i.changes),Promise.resolve(i))}catch(e){return Promise.reject(e)}}async isExists(){try{let e=await this.sqlite.isDBExists({database:this.dbName,readonly:this.readonly});return Promise.resolve(e)}catch(e){return Promise.reject(e)}}async isTable(e){try{let t=await this.sqlite.isTableExists({database:this.dbName,table:e,readonly:this.readonly});return Promise.resolve(t)}catch(e){return Promise.reject(e)}}async isDBOpen(){try{let e=await this.sqlite.isDBOpen({database:this.dbName,readonly:this.readonly});return Promise.resolve(e)}catch(e){return Promise.reject(e)}}async delete(){try{return this.readonly?Promise.reject(`not allowed in read-only mode`):(await this.sqlite.deleteDatabase({database:this.dbName,readonly:!1}),Promise.resolve())}catch(e){return Promise.reject(e)}}async createSyncTable(){try{if(this.readonly)return Promise.reject(`not allowed in read-only mode`);{let e=await this.sqlite.createSyncTable({database:this.dbName,readonly:!1});return Promise.resolve(e)}}catch(e){return Promise.reject(e)}}async setSyncDate(e){try{return this.readonly?Promise.reject(`not allowed in read-only mode`):(await this.sqlite.setSyncDate({database:this.dbName,syncdate:e,readonly:!1}),Promise.resolve())}catch(e){return Promise.reject(e)}}async getSyncDate(){try{let e=await this.sqlite.getSyncDate({database:this.dbName,readonly:this.readonly}),t=``;return e.syncDate>0&&(t=new Date(e.syncDate*1e3).toISOString()),Promise.resolve(t)}catch(e){return Promise.reject(e)}}async exportToJson(e,t=!1){try{let n=await this.sqlite.exportToJson({database:this.dbName,jsonexportmode:e,readonly:this.readonly,encrypted:t});return Promise.resolve(n)}catch(e){return Promise.reject(e)}}async deleteExportedRows(){try{return this.readonly?Promise.reject(`not allowed in read-only mode`):(await this.sqlite.deleteExportedRows({database:this.dbName,readonly:!1}),Promise.resolve())}catch(e){return Promise.reject(e)}}async executeTransaction(e,t=!0){let n=0,r=!1;if(this.readonly)return Promise.reject(`not allowed in read-only mode`);if(await this.sqlite.beginTransaction({database:this.dbName}),r=await this.sqlite.isTransactionActive({database:this.dbName}),!r)return Promise.reject(`After Begin Transaction, no transaction active`);try{for(let r of e){if(typeof r!=`object`||!(`statement`in r))throw Error(`Error a task.statement must be provided`);if(`values`in r&&r.values&&r.values.length>0){let e=r.statement.toUpperCase().includes(`RETURNING`)?`all`:`no`,i=await this.sqlite.run({database:this.dbName,statement:r.statement,values:r.values,transaction:!1,readonly:!1,returnMode:e,isSQL92:t});if(i.changes.changes<0)throw Error(`Error in transaction method run `);n+=i.changes.changes}else{let e=await this.sqlite.execute({database:this.dbName,statements:r.statement,transaction:!1,readonly:!1});if(e.changes.changes<0)throw Error(`Error in transaction method execute `);n+=e.changes.changes}}let r=await this.sqlite.commitTransaction({database:this.dbName});n+=r.changes.changes;let i={changes:{changes:n}};return Promise.resolve(i)}catch(e){let t=e.message?e.message:e;return await this.sqlite.rollbackTransaction({database:this.dbName}),Promise.reject(t)}}async reorderRows(e){let t=e;if(e?.values&&typeof e.values[0]==`object`&&Object.keys(e.values[0]).includes(`ios_columns`)){let n=e.values[0].ios_columns,r=[];for(let t=1;t<e.values.length;t++){let i=e.values[t],a={};for(let e of n)a[e]=i[e];r.push(a)}t.values=r}return Promise.resolve(t)}},ee=t(`CapacitorSQLite`,{web:()=>e(()=>import(`./web-C1P6YYig.js`).then(e=>new e.CapacitorSQLiteWeb),__vite__mapDeps([0,1])),electron:()=>window.CapacitorCustomPlatform.plugins.CapacitorSQLite}),te=u({initSQLite:()=>b,sqliteBulkPutEmployeesCache:()=>D,sqliteBulkSaveAttendanceLogs:()=>F,sqliteCacheTodayAttendance:()=>j,sqliteCacheUserMasterVector:()=>x,sqliteClearAttendanceLogs:()=>z,sqliteClearAttendanceRequests:()=>J,sqliteClearEmployeesCache:()=>E,sqliteClearTodayAttendanceCache:()=>N,sqliteDeleteAttendanceRequest:()=>q,sqliteGetAdmin:()=>V,sqliteGetAllMasterVectors:()=>k,sqliteGetAttendanceLogs:()=>I,sqliteGetAttendanceRequests:()=>K,sqliteGetCachedUserMasterVector:()=>S,sqliteGetEmployeeDeletes:()=>X,sqliteGetEmployeesCache:()=>O,sqliteGetPendingEmployees:()=>U,sqliteGetTodayAttendance:()=>M,sqliteGetTodayAttendanceLogs:()=>L,sqliteGetUnsyncedLogs:()=>w,sqliteHardDeleteAttendanceLog:()=>R,sqliteHardDeleteEmployeeBiometrics:()=>A,sqliteQueueEmployeeDelete:()=>Y,sqliteQueueOfflineAttendance:()=>C,sqliteRemoveEmployeeDelete:()=>Z,sqliteRemovePendingEmployee:()=>W,sqliteRemoveSyncedLogs:()=>T,sqliteSaveAdmin:()=>B,sqliteSaveAttendanceLog:()=>P,sqliteSaveAttendanceRequest:()=>G,sqliteSavePendingEmployee:()=>H,sqliteSoftDeleteAttendanceLog:()=>$,sqliteSoftDeleteEmployee:()=>Q,sqliteUpdatePendingAttendanceEmployeeId:()=>ie,sqliteUpdateSyncStatus:()=>re}),h=new p(ee),g=null,_=null,v=null,ne=new Promise((e,t)=>{_=e,v=t});async function y(){if(!n.isNativePlatform())return!1;try{return await ne,!!g}catch{return!1}}async function b(){if(!n.isNativePlatform()){console.log(`[SQLite Service] Web platform detected. Skipping SQLite initialization.`),_?.();return}try{console.log(`[SQLite Service] Initializing SQLite connection for native APK...`);try{await h.checkConnectionsConsistency().catch(()=>console.warn(`[SQLite] Consistency check warning`))}catch(e){console.warn(`[SQLite] checkConnectionsConsistency err:`,e)}if(g=(await h.isConnection(`AgriFaceLocalDB`,!1)).result?await h.retrieveConnection(`AgriFaceLocalDB`,!1):await h.createConnection(`AgriFaceLocalDB`,!1,`no-encryption`,1,!1),!g)throw Error(`Gagal membuat koneksi SQLite database.`);await g.open(),console.log(`[SQLite Service] Database connection opened successfully.`),await g.execute(`
      CREATE TABLE IF NOT EXISTS local_employees (
        id TEXT PRIMARY KEY,
        nik TEXT NOT NULL,
        name TEXT NOT NULL,
        department TEXT,
        afdeling TEXT,
        nama_kebun TEXT,
        status_tk TEXT,
        jabatan TEXT,
        status_perkawinan TEXT,
        has_master_biometric INTEGER DEFAULT 0,
        region TEXT,
        is_synced INTEGER DEFAULT 1,
        syncStatus TEXT DEFAULT NULL
      );

      CREATE TABLE IF NOT EXISTS local_master_descriptors (
        employee_id TEXT PRIMARY KEY,
        descriptor_json TEXT,
        geometric_descriptor_json TEXT,
        updated_at TEXT
      );

      CREATE TABLE IF NOT EXISTS local_attendance_queue (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        employee_id TEXT NOT NULL,
        nik TEXT,
        name TEXT,
        department TEXT,
        timestamp TEXT,
        location TEXT,
        lat REAL,
        lng REAL,
        status TEXT,
        attendance_type TEXT,
        euclidean_distance REAL,
        path_foto_lokal TEXT,
        path_foto_storage TEXT,
        status_sync_teks TEXT DEFAULT 'pending',
        status_sync_foto TEXT DEFAULT 'pending',
        is_synced INTEGER DEFAULT 0,
        created_at TEXT,
        kebun TEXT,
        durasi INTEGER
      );

      CREATE TABLE IF NOT EXISTS local_today_attendance_cache (
        employee_id TEXT PRIMARY KEY,
        has_checked_in INTEGER DEFAULT 0,
        has_checked_out INTEGER DEFAULT 0,
        checked_in INTEGER DEFAULT 0,
        check_in_time TEXT,
        check_out_time TEXT,
        cached_date TEXT
      );

      CREATE TABLE IF NOT EXISTS local_attendance_logs (
        id TEXT PRIMARY KEY,
        employee_id TEXT NOT NULL,
        nik TEXT,
        name TEXT,
        department TEXT,
        afdeling TEXT,
        timestamp TEXT,
        location TEXT,
        lat REAL,
        lng REAL,
        status TEXT,
        attendance_type TEXT,
        euclidean_distance REAL,
        path_foto_lokal TEXT,
        path_foto_storage TEXT,
        status_sync_teks TEXT DEFAULT 'pending',
        status_sync_foto TEXT DEFAULT 'pending',
        is_synced INTEGER DEFAULT 0,
        created_at TEXT,
        kebun TEXT,
        durasi INTEGER,
        syncStatus TEXT DEFAULT NULL
      );

      CREATE TABLE IF NOT EXISTS local_admins (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        username TEXT UNIQUE NOT NULL,
        password_hash TEXT NOT NULL,
        role TEXT,
        region TEXT,
        kebun TEXT,
        name TEXT,
        nik TEXT,
        last_login TEXT
      );

      CREATE TABLE IF NOT EXISTS local_employee_sync_queue (
        id TEXT PRIMARY KEY,
        nik TEXT,
        name TEXT,
        department TEXT,
        afdeling TEXT,
        nama_kebun TEXT,
        status_tk TEXT,
        jabatan TEXT,
        status_perkawinan TEXT,
        has_master_biometric INTEGER DEFAULT 0,
        region TEXT,
        descriptor_json TEXT,
        geometric_descriptor_json TEXT,
        is_synced INTEGER DEFAULT 0,
        created_at TEXT
      );

      CREATE TABLE IF NOT EXISTS local_attendance_requests (
        id TEXT PRIMARY KEY,
        request_type TEXT,
        log_id TEXT,
        nik TEXT,
        name TEXT,
        nama_kebun TEXT,
        requested_by TEXT,
        requested_at TEXT,
        status TEXT,
        old_value TEXT,
        new_value TEXT,
        is_synced INTEGER DEFAULT 0
      );

      CREATE TABLE IF NOT EXISTS local_employee_delete_queue (
        id TEXT PRIMARY KEY
      );
    `);try{let e=(await g.query(`PRAGMA table_info(local_employees);`)).values?.find(e=>e.name===`id`);e&&e.type&&e.type.toUpperCase().includes(`INT`)&&(console.log(`[SQLite Service] Migrating local_employees id column from INTEGER to TEXT...`),await g.execute(`
          CREATE TABLE IF NOT EXISTS local_employees_v2 (
            id TEXT PRIMARY KEY,
            nik TEXT NOT NULL,
            name TEXT NOT NULL,
            department TEXT,
            afdeling TEXT,
            nama_kebun TEXT,
            status_tk TEXT,
            jabatan TEXT,
            status_perkawinan TEXT,
            has_master_biometric INTEGER DEFAULT 0,
            region TEXT,
            is_synced INTEGER DEFAULT 1
          );
          INSERT OR IGNORE INTO local_employees_v2 (id, nik, name, department, afdeling, nama_kebun, status_tk, jabatan, status_perkawinan, has_master_biometric)
            SELECT CAST(id AS TEXT), nik, name, department, afdeling, nama_kebun, status_tk, jabatan, status_perkawinan, has_master_biometric FROM local_employees;
          DROP TABLE local_employees;
          ALTER TABLE local_employees_v2 RENAME TO local_employees;
        `),console.log(`[SQLite Service] Migrated local_employees to TEXT primary key.`))}catch(e){console.warn(`[SQLite Service] Migration local_employees warning:`,e)}try{let e=(await g.query(`PRAGMA table_info(local_master_descriptors);`)).values?.find(e=>e.name===`employee_id`);e&&e.type&&e.type.toUpperCase().includes(`INT`)&&(console.log(`[SQLite Service] Migrating local_master_descriptors employee_id column from INTEGER to TEXT...`),await g.execute(`
          CREATE TABLE IF NOT EXISTS local_master_descriptors_v2 (
            employee_id TEXT PRIMARY KEY,
            descriptor_json TEXT,
            geometric_descriptor_json TEXT,
            updated_at TEXT
          );
          INSERT OR IGNORE INTO local_master_descriptors_v2 (employee_id, descriptor_json, geometric_descriptor_json, updated_at)
            SELECT CAST(employee_id AS TEXT), descriptor_json, geometric_descriptor_json, updated_at FROM local_master_descriptors;
          DROP TABLE local_master_descriptors;
          ALTER TABLE local_master_descriptors_v2 RENAME TO local_master_descriptors;
        `),console.log(`[SQLite Service] Migrated local_master_descriptors to TEXT primary key.`))}catch(e){console.warn(`[SQLite Service] Migration local_master_descriptors warning:`,e)}try{let e=(await g.query(`PRAGMA table_info(local_today_attendance_cache);`)).values?.find(e=>e.name===`employee_id`);e&&e.type&&e.type.toUpperCase().includes(`INT`)&&(console.log(`[SQLite Service] Migrating local_today_attendance_cache employee_id column from INTEGER to TEXT...`),await g.execute(`
          CREATE TABLE IF NOT EXISTS local_today_attendance_cache_v2 (
            employee_id TEXT PRIMARY KEY,
            has_checked_in INTEGER DEFAULT 0,
            has_checked_out INTEGER DEFAULT 0,
            checked_in INTEGER DEFAULT 0,
            check_in_time TEXT,
            check_out_time TEXT,
            cached_date TEXT
          );
          INSERT OR IGNORE INTO local_today_attendance_cache_v2 (employee_id, has_checked_in, has_checked_out, checked_in, check_in_time, check_out_time, cached_date)
            SELECT CAST(employee_id AS TEXT), has_checked_in, has_checked_out, checked_in, check_in_time, check_out_time, cached_date FROM local_today_attendance_cache;
          DROP TABLE local_today_attendance_cache;
          ALTER TABLE local_today_attendance_cache_v2 RENAME TO local_today_attendance_cache;
        `),console.log(`[SQLite Service] Migrated local_today_attendance_cache to TEXT primary key.`))}catch(e){console.warn(`[SQLite Service] Migration local_today_attendance_cache warning:`,e)}try{let e=(await g.query(`PRAGMA table_info(local_employees);`)).values?.some(e=>e.name===`region`),t=await g.query(`PRAGMA index_list(local_employees);`),n=!1;if(t.values){for(let e of t.values)if(e.unique===1){let t=await g.query(`PRAGMA index_info('${e.name}');`);if(t.values&&t.values.some(e=>e.name===`nik`)){n=!0;break}}}(n||!e)&&(console.log(`[SQLite Service] Migrating local_employees to remove UNIQUE nik and ensure all columns exist...`),await g.execute(`
          CREATE TABLE IF NOT EXISTS local_employees_v3 (
            id TEXT PRIMARY KEY,
            nik TEXT NOT NULL,
            name TEXT NOT NULL,
            department TEXT,
            afdeling TEXT,
            nama_kebun TEXT,
            status_tk TEXT,
            jabatan TEXT,
            status_perkawinan TEXT,
            has_master_biometric INTEGER DEFAULT 0,
            region TEXT,
            is_synced INTEGER DEFAULT 1
          );
        `),e?await g.execute(`
            INSERT OR IGNORE INTO local_employees_v3 (id, nik, name, department, afdeling, nama_kebun, status_tk, jabatan, status_perkawinan, has_master_biometric, region, is_synced)
              SELECT id, nik, name, department, afdeling, nama_kebun, status_tk, jabatan, status_perkawinan, has_master_biometric, region, is_synced FROM local_employees;
          `):await g.execute(`
            INSERT OR IGNORE INTO local_employees_v3 (id, nik, name, department, afdeling, nama_kebun, status_tk, jabatan, status_perkawinan, has_master_biometric)
              SELECT id, nik, name, department, afdeling, nama_kebun, status_tk, jabatan, status_perkawinan, has_master_biometric FROM local_employees;
          `),await g.execute(`
          DROP TABLE local_employees;
          ALTER TABLE local_employees_v3 RENAME TO local_employees;
        `),console.log(`[SQLite Service] Migrated local_employees to remove UNIQUE nik successfully.`))}catch(e){console.warn(`[SQLite Service] Migration local_employees v3 warning:`,e)}try{(await g.query(`PRAGMA table_info(local_attendance_queue);`)).values?.some(e=>e.name===`durasi`)||(console.log(`[SQLite Service] Adding missing durasi column to local_attendance_queue...`),await g.execute(`ALTER TABLE local_attendance_queue ADD COLUMN durasi INTEGER;`))}catch(e){console.warn(`[SQLite Service] Migration local_attendance_queue add durasi warning:`,e)}try{(await g.query(`PRAGMA table_info(local_attendance_logs);`)).values?.some(e=>e.name===`durasi`)||(console.log(`[SQLite Service] Adding missing durasi column to local_attendance_logs...`),await g.execute(`ALTER TABLE local_attendance_logs ADD COLUMN durasi INTEGER;`))}catch(e){console.warn(`[SQLite Service] Migration local_attendance_logs add durasi warning:`,e)}try{let e=await g.query(`PRAGMA table_info(local_employee_sync_queue);`),t=e.values?.some(e=>e.name===`has_master_biometric`),n=e.values?.some(e=>e.name===`region`);t||(console.log(`[SQLite Service] Adding missing has_master_biometric column to local_employee_sync_queue...`),await g.execute(`ALTER TABLE local_employee_sync_queue ADD COLUMN has_master_biometric INTEGER DEFAULT 0;`)),n||(console.log(`[SQLite Service] Adding missing region column to local_employee_sync_queue...`),await g.execute(`ALTER TABLE local_employee_sync_queue ADD COLUMN region TEXT;`))}catch(e){console.warn(`[SQLite Service] Migration local_employee_sync_queue add columns warning:`,e)}try{let e=(await g.query(`PRAGMA table_info(local_attendance_queue);`)).values?.find(e=>e.name===`employee_id`);e&&e.type&&e.type.toUpperCase().includes(`INT`)&&(console.log(`[SQLite Service] Migrating local_attendance_queue employee_id column from INTEGER to TEXT...`),await g.execute(`
          CREATE TABLE IF NOT EXISTS local_attendance_queue_v2 (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            employee_id TEXT NOT NULL,
            nik TEXT,
            name TEXT,
            department TEXT,
            timestamp TEXT,
            location TEXT,
            lat REAL,
            lng REAL,
            status TEXT,
            attendance_type TEXT,
            euclidean_distance REAL,
            is_synced INTEGER DEFAULT 0,
            created_at TEXT,
            kebun TEXT
          );
          INSERT OR IGNORE INTO local_attendance_queue_v2 (id, employee_id, nik, name, department, timestamp, location, lat, lng, status, attendance_type, euclidean_distance, is_synced, created_at)
            SELECT id, CAST(employee_id AS TEXT), nik, name, department, timestamp, location, lat, lng, status, attendance_type, euclidean_distance, is_synced, created_at FROM local_attendance_queue;
          DROP TABLE local_attendance_queue;
          ALTER TABLE local_attendance_queue_v2 RENAME TO local_attendance_queue;
        `),console.log(`[SQLite Service] Migrated local_attendance_queue to TEXT employee_id.`))}catch(e){console.warn(`[SQLite Service] Migration local_attendance_queue warning:`,e)}try{let e=(await g.query(`PRAGMA table_info(local_attendance_logs);`)).values?.find(e=>e.name===`employee_id`);e&&e.type&&e.type.toUpperCase().includes(`INT`)&&(console.log(`[SQLite Service] Migrating local_attendance_logs employee_id column from INTEGER to TEXT...`),await g.execute(`
          CREATE TABLE IF NOT EXISTS local_attendance_logs_v2 (
            id TEXT PRIMARY KEY,
            employee_id TEXT NOT NULL,
            nik TEXT,
            name TEXT,
            department TEXT,
            afdeling TEXT,
            timestamp TEXT,
            location TEXT,
            lat REAL,
            lng REAL,
            status TEXT,
            attendance_type TEXT,
            euclidean_distance REAL,
            is_synced INTEGER DEFAULT 0,
            created_at TEXT,
            kebun TEXT
          );
          INSERT OR IGNORE INTO local_attendance_logs_v2 (id, employee_id, nik, name, department, timestamp, location, lat, lng, status, attendance_type, euclidean_distance, is_synced, created_at)
            SELECT id, CAST(employee_id AS TEXT), nik, name, department, timestamp, location, lat, lng, status, attendance_type, euclidean_distance, is_synced, created_at FROM local_attendance_logs;
          DROP TABLE local_attendance_logs;
          ALTER TABLE local_attendance_logs_v2 RENAME TO local_attendance_logs;
        `),console.log(`[SQLite Service] Migrated local_attendance_logs to TEXT employee_id.`))}catch(e){console.warn(`[SQLite Service] Migration local_attendance_logs warning:`,e)}try{await g.execute(`ALTER TABLE local_attendance_queue ADD COLUMN afdeling TEXT;`),console.log(`[SQLite Service] Migrated local_attendance_queue: added afdeling column`)}catch{}try{await g.execute(`ALTER TABLE local_attendance_queue ADD COLUMN kebun TEXT;`),console.log(`[SQLite Service] Migrated local_attendance_queue: added kebun column`)}catch{}try{await g.execute(`ALTER TABLE local_attendance_logs ADD COLUMN afdeling TEXT;`),console.log(`[SQLite Service] Migrated local_attendance_logs: added afdeling column`)}catch{}try{await g.execute(`ALTER TABLE local_attendance_logs ADD COLUMN kebun TEXT;`),console.log(`[SQLite Service] Migrated local_attendance_logs: added kebun column`)}catch{}try{await g.execute(`ALTER TABLE local_employees ADD COLUMN region TEXT;`),console.log(`[SQLite Service] Migrated local_employees: added region column`)}catch{}try{await g.execute(`ALTER TABLE local_employees ADD COLUMN is_synced INTEGER DEFAULT 1;`),console.log(`[SQLite Service] Migrated local_employees: added is_synced column`)}catch{}try{await g.execute(`ALTER TABLE local_admins ADD COLUMN nik TEXT;`),console.log(`[SQLite Service] Migrated local_admins: added nik column`)}catch{}try{await g.execute(`ALTER TABLE local_attendance_queue ADD COLUMN path_foto_lokal TEXT;`)}catch{}try{await g.execute(`ALTER TABLE local_attendance_queue ADD COLUMN path_foto_storage TEXT;`)}catch{}try{await g.execute(`ALTER TABLE local_attendance_queue ADD COLUMN status_sync_teks TEXT DEFAULT 'pending';`)}catch{}try{await g.execute(`ALTER TABLE local_attendance_queue ADD COLUMN status_sync_foto TEXT DEFAULT 'pending';`)}catch{}try{await g.execute(`ALTER TABLE local_attendance_logs ADD COLUMN path_foto_lokal TEXT;`)}catch{}try{await g.execute(`ALTER TABLE local_attendance_logs ADD COLUMN path_foto_storage TEXT;`)}catch{}try{await g.execute(`ALTER TABLE local_attendance_logs ADD COLUMN status_sync_teks TEXT DEFAULT 'pending';`)}catch{}try{await g.execute(`ALTER TABLE local_attendance_logs ADD COLUMN status_sync_foto TEXT DEFAULT 'pending';`)}catch{}try{await g.execute(`ALTER TABLE local_attendance_queue ADD COLUMN sync_notes TEXT;`)}catch{}try{await g.execute(`ALTER TABLE local_attendance_logs ADD COLUMN sync_notes TEXT;`)}catch{}try{await g.execute(`ALTER TABLE local_employees ADD COLUMN syncStatus TEXT DEFAULT NULL;`)}catch{}try{await g.execute(`ALTER TABLE local_attendance_logs ADD COLUMN syncStatus TEXT DEFAULT NULL;`)}catch{}console.log(`[SQLite Service] SQLite tables verified and ready.`),_?.()}catch(e){console.error(`[SQLite Service Init Error]:`,e?.message||e),v?.(e)}}async function x(e){if(!g&&!await y()){console.error(`[SQLite Service sqliteCacheUserMasterVector] dbConnection null even after wait. Skipping cache.`);return}try{let t=e.employee_id||e.id;if(!t)return;let n=String(t),r=e.descriptor_json||e.descriptor||e.face_vector||null,i=null;if(r){let e=r;for(;typeof e==`string`;)try{e=JSON.parse(e)}catch{break}let t=null;if(Array.isArray(e))t=e;else if(e instanceof Float32Array||e instanceof Float64Array||ArrayBuffer.isView(e))t=Array.from(e);else if(typeof e==`object`&&e){let n=Object.values(e);n.length>0&&(t=Array.from(n))}t&&t.length>0&&(i=JSON.stringify(t.map(e=>Number(e)||0)))}let a=e.geometric_descriptor_json||null,o=a?JSON.stringify(a):null;await g.run(`INSERT OR REPLACE INTO local_employees (id, nik, name, department, afdeling, nama_kebun, status_tk, jabatan, status_perkawinan, has_master_biometric, region, is_synced)
       VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)`,[n,e.nik,e.name,e.department||e.jabatan||null,e.afdeling||null,e.nama_kebun||e.kebun||null,e.status_tk||null,e.jabatan||null,e.status_perkawinan||null,+!!i,e.region||null,1]),await g.run(`INSERT OR REPLACE INTO local_master_descriptors (employee_id, descriptor_json, geometric_descriptor_json, updated_at)
       VALUES (?, ?, ?, ?)`,[n,i,o,new Date().toISOString()]),console.log(`[SQLite Service] Cached master for ${e.name} | GFV: ${o?`YES`:`NO`}`)}catch(e){console.error(`[SQLite Service cacheUserMasterVector Error]:`,e?.message||e)}}async function S(e){if(!g&&!await y())return null;try{let t=String(e),n=await g.query(`SELECT md.*, e.nik, e.name, e.department, e.afdeling, e.nama_kebun, e.status_tk, e.jabatan, e.status_perkawinan
       FROM local_master_descriptors md
       LEFT JOIN local_employees e ON CAST(md.employee_id AS TEXT) = CAST(e.id AS TEXT)
       WHERE CAST(md.employee_id AS TEXT) = ? OR md.employee_id = ? OR CAST(e.nik AS TEXT) = ?`,[t,e,t]);if(!n.values||n.values.length===0||!n.values[0].descriptor_json){let n=await g.query(`SELECT * FROM local_employee_sync_queue WHERE CAST(id AS TEXT) = ? OR id = ? OR CAST(nik AS TEXT) = ? LIMIT 1`,[t,e,t]);if(n.values&&n.values.length>0){let e=n.values[0];if(e.descriptor_json)return{employee_id:e.id,nik:e.nik,name:e.name,department:e.department,afdeling:e.afdeling,nama_kebun:e.nama_kebun,status_tk:e.status_tk,jabatan:e.jabatan,status_perkawinan:e.status_perkawinan,descriptor_json:typeof e.descriptor_json==`string`?JSON.parse(e.descriptor_json):e.descriptor_json,face_vector:typeof e.descriptor_json==`string`?JSON.parse(e.descriptor_json):e.descriptor_json,geometric_descriptor_json:e.geometric_descriptor_json?typeof e.geometric_descriptor_json==`string`?JSON.parse(e.geometric_descriptor_json):e.geometric_descriptor_json:null,updated_at:e.created_at}}return null}let r=n.values[0];return{employee_id:r.employee_id,nik:r.nik,name:r.name,department:r.department,afdeling:r.afdeling,nama_kebun:r.nama_kebun,status_tk:r.status_tk,jabatan:r.jabatan,status_perkawinan:r.status_perkawinan,descriptor_json:r.descriptor_json?typeof r.descriptor_json==`string`?JSON.parse(r.descriptor_json):r.descriptor_json:null,face_vector:r.descriptor_json?typeof r.descriptor_json==`string`?JSON.parse(r.descriptor_json):r.descriptor_json:null,geometric_descriptor_json:r.geometric_descriptor_json?typeof r.geometric_descriptor_json==`string`?JSON.parse(r.geometric_descriptor_json):r.geometric_descriptor_json:null,updated_at:r.updated_at}}catch(e){return console.error(`[SQLite Service getCachedUserMasterVector Error]:`,e?.message||e),null}}async function C(e){if(!g&&!await y())throw Error(`Database SQLite tidak terhubung.`);try{let t=new Date().toISOString(),n=e.timestamp||t,r=e.status_sync_teks||`pending`,i=e.status_sync_foto||`pending`,a=+(r===`done`&&i===`done`),o=await g.run(`INSERT INTO local_attendance_queue (
        employee_id, nik, name, department, afdeling, kebun, timestamp, location, lat, lng, status, attendance_type, euclidean_distance, path_foto_lokal, path_foto_storage, status_sync_teks, status_sync_foto, is_synced, created_at, durasi
       ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)`,[String(e.employee_id),e.nik||null,e.name||null,e.department||null,e.afdeling||null,e.kebun||null,n,e.location||`HP Mobile (Offline)`,e.lat!==void 0&&e.lat!==null?Number(e.lat):null,e.lng!==void 0&&e.lng!==null?Number(e.lng):null,e.status||null,e.attendance_type||`CHECK_IN`,e.euclidean_distance!==void 0&&e.euclidean_distance!==null?Number(e.euclidean_distance):null,e.path_foto_lokal||null,e.path_foto_storage||null,r,i,a,t,e.durasi!==void 0&&e.durasi!==null?Number(e.durasi):null]),s=o.lastId??o.changes?.lastId;s??=`temp_`+Date.now()+`_`+Math.floor(Math.random()*1e3);let c={...e,id:s,timestamp:n,path_foto_lokal:e.path_foto_lokal||null,path_foto_storage:e.path_foto_storage||null,status_sync_teks:r,status_sync_foto:i,is_synced:a===1,created_at:t};return console.log(`[SQLite Service Queue] Queued offline attendance with ID: ${s}`),c}catch(e){throw console.error(`[SQLite Service sqliteQueueOfflineAttendance Error]:`,e?.message||e),e}}async function w(){if(!g&&!await y())return[];try{return(await g.query(`SELECT * FROM local_attendance_queue WHERE is_synced = 0`)).values||[]}catch(e){return console.error(`[SQLite Service sqliteGetUnsyncedLogs Error]:`,e?.message||e),[]}}async function T(e){if(!(!e||e.length===0)&&!(!g&&!await y()))try{let t=e.map(e=>({statement:`DELETE FROM local_attendance_queue WHERE id = ?`,values:[e]}));await g.executeSet(t),console.log(`[SQLite Service] Successfully removed ${e.length} synced logs from queue.`)}catch(e){console.error(`[SQLite Service sqliteRemoveSyncedLogs Error]:`,e?.message||e)}}async function E(e){if(g)try{e&&e.kebun?(await g.run(`DELETE FROM local_master_descriptors WHERE employee_id IN (SELECT id FROM local_employees WHERE nama_kebun = ?)`,[e.kebun]),await g.run(`DELETE FROM local_employees WHERE nama_kebun = ?`,[e.kebun]),console.log(`[SQLite Service] Cleared local employees & descriptors cache for kebun: ${e.kebun}`)):e&&e.region?(await g.run(`DELETE FROM local_master_descriptors WHERE employee_id IN (SELECT id FROM local_employees WHERE region = ?)`,[e.region]),await g.run(`DELETE FROM local_employees WHERE region = ?`,[e.region]),console.log(`[SQLite Service] Cleared local employees & descriptors cache for region: ${e.region}`)):(await g.execute(`DELETE FROM local_master_descriptors`),await g.execute(`DELETE FROM local_employees`),console.log(`[SQLite Service] Cleared all local employees & descriptors cache.`))}catch(e){console.error(`[SQLite Service sqliteClearEmployeesCache Error]:`,e?.message||e)}}async function D(e){if(!(!g||!e||e.length===0))try{let t=[];e.forEach(e=>{if(t.push({statement:`INSERT OR REPLACE INTO local_employees (id, nik, name, department, afdeling, nama_kebun, status_tk, jabatan, status_perkawinan, has_master_biometric, region, is_synced)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)`,values:[String(e.id),e.nik,e.name,e.department||e.jabatan||null,e.afdeling||null,e.nama_kebun||e.kebun||null,e.status_tk||null,e.jabatan||null,e.status_perkawinan||null,+!!e.has_master_biometric,e.region||null,e.is_synced===void 0?1:+!!e.is_synced]}),e.descriptor_json||e.geometric_descriptor_json||e.face_vector||e.facial_descriptor){let n=e.descriptor_json||e.face_vector||e.facial_descriptor,r=e.geometric_descriptor_json;t.push({statement:`INSERT OR REPLACE INTO local_master_descriptors (employee_id, descriptor_json, geometric_descriptor_json, updated_at)
                      VALUES (?, ?, ?, ?)`,values:[String(e.id),n?typeof n==`string`?n:JSON.stringify(n):null,r?typeof r==`string`?r:JSON.stringify(r):null,new Date().toISOString()]})}else e.has_master_biometric||t.push({statement:`DELETE FROM local_master_descriptors WHERE employee_id = ?`,values:[String(e.id)]})}),await g.executeSet(t),console.log(`[SQLite Service] Bulk put ${e.length} employees (and their descriptors) into cache.`)}catch(e){console.error(`[SQLite Service sqliteBulkPutEmployeesCache Error]:`,e?.message||e)}}async function O(){if(!g)return[];try{return((await g.query(`
      SELECT e.*, md.descriptor_json 
      FROM local_employees e
      LEFT JOIN local_master_descriptors md ON e.id = md.employee_id
    `)).values||[]).map(e=>{let t=null;if(e.descriptor_json)try{t=typeof e.descriptor_json==`string`?JSON.parse(e.descriptor_json):e.descriptor_json}catch{}return{...e,kebun:e.nama_kebun,has_master_biometric:e.has_master_biometric==1||e.has_master_biometric===`true`||!!t,descriptor_json:t}})}catch(e){return console.error(`[SQLite Service sqliteGetEmployeesCache Error]:`,e?.message||e),[]}}async function k(){if(!g)return[];try{return((await g.query(`SELECT md.employee_id, md.descriptor_json, e.nik, e.name
       FROM local_master_descriptors md
       LEFT JOIN local_employees e ON md.employee_id = e.id
       WHERE md.descriptor_json IS NOT NULL`)).values||[]).map(e=>{let t=null;if(e.descriptor_json)try{t=typeof e.descriptor_json==`string`?JSON.parse(e.descriptor_json):e.descriptor_json}catch{}return{employee_id:e.employee_id,nik:e.nik,name:e.name,descriptor_json:t}})}catch(e){return console.error(`[SQLite Service sqliteGetAllMasterVectors Error]:`,e?.message||e),[]}}async function A(e){if(g)try{let t=String(e),n=[{statement:`DELETE FROM local_master_descriptors WHERE employee_id = ?`,values:[t]},{statement:`DELETE FROM local_employees WHERE id = ?`,values:[t]},{statement:`DELETE FROM local_attendance_logs WHERE employee_id = ?`,values:[t]},{statement:`DELETE FROM local_attendance_queue WHERE employee_id = ?`,values:[t]}];await g.executeSet(n),console.log(`[SQLite Service] Successfully deleted local master biometrics and cache for employee ID: ${e}`)}catch(e){console.error(`[SQLite Service sqliteDeleteEmployeeBiometrics Error]:`,e?.message||e)}}async function j(e,t){if(g)try{await g.run(`DELETE FROM local_today_attendance_cache WHERE cached_date != ?`,[t]);let n=[],r=Object.keys(e);for(let i=0;i<r.length;i++){let a=r[i],o=e[a];o&&n.push({statement:`INSERT OR REPLACE INTO local_today_attendance_cache 
                    (employee_id, has_checked_in, has_checked_out, checked_in, check_in_time, check_out_time, cached_date)
                    VALUES (?, ?, ?, ?, ?, ?, ?)`,values:[String(a),+!!o.hasCheckedIn,+!!o.hasCheckedOut,+!!o.checked_in,o.check_in_time||null,o.check_out_time||null,t]})}n.length>0&&await g.executeSet(n),console.log(`[SQLite Service] Cached today's attendance status for ${n.length} employees in local database`)}catch(e){throw console.error(`[SQLite Service sqliteCacheTodayAttendance Error]:`,e?.message||e,e?.stack||``),e}}async function M(e,t){if(!g)return null;try{let n=(await g.query(`SELECT * FROM local_today_attendance_cache WHERE employee_id = ? AND cached_date = ?`,[String(e),t])).values||[];if(n.length>0){let e=n[0];return{hasCheckedIn:e.has_checked_in===1,hasCheckedOut:e.has_checked_out===1,checked_in:e.checked_in===1,check_in_time:e.check_in_time,check_out_time:e.check_out_time}}return null}catch(e){return console.error(`[SQLite Service sqliteGetTodayAttendance Error]:`,e?.message||e),null}}async function N(){if(g)try{await g.run(`DELETE FROM local_today_attendance_cache`,[]),console.log(`[SQLite Service] Cleared local today attendance status cache`)}catch(e){console.error(`[SQLite Service sqliteClearTodayAttendanceCache Error]:`,e?.message||e)}}async function P(e){if(!(!g&&!await y()))try{let t=e.status_sync_teks||(e.is_synced?`done`:`pending`),n=e.status_sync_foto||(e.is_synced?`done`:`pending`),r=t===`done`&&n===`done`?1:+!!e.is_synced;await g.run(`INSERT OR REPLACE INTO local_attendance_logs 
      (id, employee_id, nik, name, department, afdeling, kebun, timestamp, location, lat, lng, status, attendance_type, euclidean_distance, path_foto_lokal, path_foto_storage, status_sync_teks, status_sync_foto, is_synced, created_at, durasi)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)`,[String(e.id),String(e.employee_id),e.nik||null,e.name||null,e.department||null,e.afdeling||null,e.kebun||e.nama_kebun||null,e.timestamp||null,e.location||null,e.lat!==void 0&&e.lat!==null?Number(e.lat):null,e.lng!==void 0&&e.lng!==null?Number(e.lng):null,e.status||null,e.attendance_type||null,e.euclidean_distance!==void 0&&e.euclidean_distance!==null?Number(e.euclidean_distance):null,e.path_foto_lokal||null,e.path_foto_storage||null,t,n,r,e.created_at||null,e.durasi!==void 0&&e.durasi!==null?Number(e.durasi):null])}catch(e){console.error(`[SQLite Service sqliteSaveAttendanceLog Error]:`,e?.message||e,e?.stack||``)}}async function F(e){if(!(!g&&!await y()))try{let t=e.map(e=>{let t=e.status_sync_teks||(e.is_synced?`done`:`pending`),n=e.status_sync_foto||(e.is_synced?`done`:`pending`),r=t===`done`&&n===`done`?1:+!!e.is_synced;return{statement:`INSERT OR REPLACE INTO local_attendance_logs 
          (id, employee_id, nik, name, department, afdeling, kebun, timestamp, location, lat, lng, status, attendance_type, euclidean_distance, path_foto_lokal, path_foto_storage, status_sync_teks, status_sync_foto, is_synced, created_at, durasi)
          VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)`,values:[String(e.id),String(e.employee_id),e.nik||null,e.name||null,e.department||null,e.afdeling||null,e.kebun||e.nama_kebun||null,e.timestamp||null,e.location||null,e.lat!==void 0&&e.lat!==null?Number(e.lat):null,e.lng!==void 0&&e.lng!==null?Number(e.lng):null,e.status||null,e.attendance_type||null,e.euclidean_distance!==void 0&&e.euclidean_distance!==null?Number(e.euclidean_distance):null,e.path_foto_lokal||null,e.path_foto_storage||null,t,n,r,e.created_at||null,e.durasi!==void 0&&e.durasi!==null?Number(e.durasi):null]}});t.length>0&&await g.executeSet(t),console.log(`[SQLite Service] Bulk saved ${e.length} attendance logs to local SQLite`)}catch(e){console.error(`[SQLite Service sqliteBulkSaveAttendanceLogs Error]:`,e?.message||e,e?.stack||``)}}async function re(e,t,n,r){if(!(!g&&!await y()))try{let i=String(e),a=i.startsWith(`offline_`)?i.replace(`offline_`,``):i,o=parseInt(a,10),s=[],c=[];if(t&&(s.push(`status_sync_teks = ?`),c.push(t)),n&&(s.push(`status_sync_foto = ?`),c.push(n)),r!=null&&(s.push(`sync_notes = ?`),c.push(r)),(t===`done`||t===`done`&&n===`done`)&&s.push(`is_synced = 1`),s.length===0)return;let l=s.join(`, `);isNaN(o)||await g.run(`UPDATE local_attendance_queue SET ${l} WHERE id = ?`,[...c,o]),await g.run(`UPDATE local_attendance_logs SET ${l} WHERE id = ? OR id = ?`,[...c,i,`offline_${o}`]),console.log(`[SQLite Service] Updated sync status for ID: ${e} | Teks: ${t||`-`} | Foto: ${n||`-`} | Notes: ${r||`-`}`)}catch(e){console.error(`[SQLite Service sqliteUpdateSyncStatus Error]:`,e?.message||e)}}async function I(){if(!g&&!await y())return[];try{return((await g.query(`SELECT * FROM local_attendance_logs ORDER BY timestamp DESC`)).values||[]).map(e=>({id:e.id,employee_id:e.employee_id,nik:e.nik,name:e.name,department:e.department,afdeling:e.afdeling,kebun:e.kebun,nama_kebun:e.kebun,timestamp:e.timestamp,location:e.location,lat:e.lat,lng:e.lng,status:e.status,attendance_type:e.attendance_type,euclidean_distance:e.euclidean_distance,path_foto_lokal:e.path_foto_lokal,path_foto_storage:e.path_foto_storage,status_sync_teks:e.status_sync_teks,status_sync_foto:e.status_sync_foto,is_synced:e.is_synced===1,created_at:e.created_at,durasi:e.durasi}))}catch(e){return console.error(`[SQLite Service sqliteGetAttendanceLogs Error]:`,e?.message||e,e?.stack||``),[]}}async function L(e,t){if(!g&&!await y())return[];try{let n=String(e);return((await g.query(`SELECT * FROM local_attendance_logs 
       WHERE (CAST(employee_id AS TEXT) = ? OR employee_id = ?) AND substr(timestamp, 1, 10) = ? 
       ORDER BY timestamp ASC`,[n,e,t])).values||[]).map(e=>({id:e.id,employee_id:e.employee_id,nik:e.nik,name:e.name,department:e.department,afdeling:e.afdeling,kebun:e.kebun,nama_kebun:e.kebun,timestamp:e.timestamp,location:e.location,lat:e.lat,lng:e.lng,status:e.status,attendance_type:e.attendance_type,euclidean_distance:e.euclidean_distance,is_synced:e.is_synced===1,created_at:e.created_at}))}catch(e){return console.error(`[SQLite Service sqliteGetTodayAttendanceLogs Error]:`,e?.message||e,e?.stack||``),[]}}async function R(e){if(!(!g&&!await y()))try{await g.run(`DELETE FROM local_attendance_logs WHERE id = ?`,[e]),console.log(`[SQLite Service] Deleted local attendance log #${e}`)}catch(e){console.error(`[SQLite Service sqliteDeleteAttendanceLog Error]:`,e?.message||e,e?.stack||``)}}async function z(){if(!(!g&&!await y()))try{await g.execute(`DELETE FROM local_attendance_logs`),console.log(`[SQLite Service] Cleared local attendance logs table.`)}catch(e){console.error(`[SQLite Service sqliteClearAttendanceLogs Error]:`,e?.message||e,e?.stack||``)}}async function B(e){if(g)try{let t=[e.username,e.password_hash,e.role,e.region,e.kebun,e.name,e.nik||null,new Date().toISOString()];await g.run(`
      INSERT OR REPLACE INTO local_admins (username, password_hash, role, region, kebun, name, nik, last_login)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?);
    `,t),console.log(`[SQLite Service] Cached offline credentials with NIK for admin: ${e.username}`)}catch(e){console.error(`[SQLite Service sqliteSaveAdmin Error]:`,e?.message||e)}}async function V(e){if(!g)return null;try{let t=await g.query(`SELECT * FROM local_admins WHERE username = ? LIMIT 1;`,[e]);if(t.values&&t.values.length>0)return t.values[0]}catch(e){console.error(`[SQLite Service sqliteGetAdmin Error]:`,e?.message||e)}return null}async function H(e){if(!g&&!await y()){console.error(`[SQLite Service sqliteSavePendingEmployee] dbConnection null even after wait. Skipping save.`);return}try{let t=e.descriptor_json?typeof e.descriptor_json==`string`?e.descriptor_json:JSON.stringify(e.descriptor_json):null,n=e.geometric_descriptor_json?typeof e.geometric_descriptor_json==`string`?e.geometric_descriptor_json:JSON.stringify(e.geometric_descriptor_json):null,r=[String(e.id),e.nik,e.name,e.department||e.jabatan||null,e.afdeling||null,e.nama_kebun||null,e.status_tk||null,e.jabatan||null,e.status_perkawinan||null,+!!e.has_master_biometric,e.region||null,t,n,e.created_at||new Date().toISOString()];await g.run(`
      INSERT OR REPLACE INTO local_employee_sync_queue 
      (id, nik, name, department, afdeling, nama_kebun, status_tk, jabatan, status_perkawinan, has_master_biometric, region, descriptor_json, geometric_descriptor_json, is_synced, created_at)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, ?);
    `,r);let i=[String(e.id),e.nik,e.name,e.department||e.jabatan||null,e.afdeling||null,e.nama_kebun||e.kebun||null,e.status_tk||null,e.jabatan||null,e.status_perkawinan||null,+!!e.has_master_biometric,e.region||null,0];if(await g.run(`
      INSERT OR REPLACE INTO local_employees 
      (id, nik, name, department, afdeling, nama_kebun, status_tk, jabatan, status_perkawinan, has_master_biometric, region, is_synced)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
    `,i),t||n){let r=[String(e.id),t,n,new Date().toISOString()];await g.run(`
        INSERT OR REPLACE INTO local_master_descriptors 
        (employee_id, descriptor_json, geometric_descriptor_json, updated_at)
        VALUES (?, ?, ?, ?)
      `,r)}console.log(`[SQLite Service] Saved pending offline employee: ${e.name} (${e.id}) into queue and cache.`)}catch(e){console.error(`[SQLite Service sqliteSavePendingEmployee Error]:`,e?.message||e)}}async function U(){if(!g&&!await y())return[];try{return(await g.query(`SELECT * FROM local_employee_sync_queue WHERE is_synced = 0`)).values||[]}catch(e){return console.error(`[SQLite Service sqliteGetPendingEmployees Error]:`,e?.message||e),[]}}async function W(e){if(!(!g&&!await y()))try{await g.run(`DELETE FROM local_employee_sync_queue WHERE id = ?`,[String(e)]),console.log(`[SQLite Service] Removed pending employee from queue: ${e}`)}catch(e){console.error(`[SQLite Service sqliteRemovePendingEmployee Error]:`,e?.message||e)}}async function ie(e,t){if(!(!g&&!await y()))try{await g.run(`UPDATE local_attendance_queue SET employee_id = ? WHERE employee_id = ?`,[String(t),String(e)]),await g.run(`UPDATE local_attendance_logs SET employee_id = ? WHERE employee_id = ?`,[String(t),String(e)]),console.log(`[SQLite Service] Updated pending attendance employee_id from ${e} to ${t}`)}catch(e){console.error(`[SQLite Service sqliteUpdatePendingAttendanceEmployeeId Error]:`,e?.message||e)}}async function G(e){if(!(!g&&!await y()))try{let t=e.old_value?JSON.stringify(e.old_value):null,n=e.new_value?JSON.stringify(e.new_value):null,r=+!!e.is_synced,i=[String(e.id),e.request_type||``,String(e.log_id||``),e.nik||null,e.name||null,e.nama_kebun||null,e.requested_by||``,e.requested_at||new Date().toISOString(),e.status||`PENDING`,t,n,r];await g.run(`
      INSERT OR REPLACE INTO local_attendance_requests 
      (id, request_type, log_id, nik, name, nama_kebun, requested_by, requested_at, status, old_value, new_value, is_synced)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    `,i),console.log(`[SQLite Service] Saved attendance request: ${e.id} (Type: ${e.request_type})`)}catch(e){console.error(`[SQLite Service sqliteSaveAttendanceRequest Error]:`,e?.message||e)}}async function K(){if(!g&&!await y())return[];try{return((await g.query(`SELECT * FROM local_attendance_requests`)).values||[]).map(e=>({...e,old_value:e.old_value?JSON.parse(e.old_value):null,new_value:e.new_value?JSON.parse(e.new_value):null,is_synced:e.is_synced===1||e.is_synced===!0}))}catch(e){return console.error(`[SQLite Service sqliteGetAttendanceRequests Error]:`,e?.message||e),[]}}async function q(e){if(!(!g&&!await y()))try{await g.run(`DELETE FROM local_attendance_requests WHERE id = ?`,[String(e)]),console.log(`[SQLite Service] Deleted attendance request: ${e}`)}catch(e){console.error(`[SQLite Service sqliteDeleteAttendanceRequest Error]:`,e?.message||e)}}async function J(){if(!(!g&&!await y()))try{await g.execute(`DELETE FROM local_attendance_requests`),console.log(`[SQLite Service] Cleared all attendance requests.`)}catch(e){console.error(`[SQLite Service sqliteClearAttendanceRequests Error]:`,e?.message||e)}}async function Y(e){if(!(!g&&!await y()))try{await g.run(`INSERT OR REPLACE INTO local_employee_delete_queue (id) VALUES (?)`,[String(e)]),console.log(`[SQLite Service] Queued employee delete for ID: ${e}`)}catch(e){console.error(`[SQLite Service sqliteQueueEmployeeDelete Error]:`,e?.message||e)}}async function X(){if(!g&&!await y())return[];try{return((await g.query(`SELECT id FROM local_employee_delete_queue`)).values||[]).map(e=>e.id)}catch(e){return console.error(`[SQLite Service sqliteGetEmployeeDeletes Error]:`,e?.message||e),[]}}async function Z(e){if(!(!g&&!await y()))try{await g.run(`DELETE FROM local_employee_delete_queue WHERE id = ?`,[String(e)]),console.log(`[SQLite Service] Removed employee delete from queue: ${e}`)}catch(e){console.error(`[SQLite Service sqliteRemoveEmployeeDelete Error]:`,e?.message||e)}}async function Q(e){if(!(!g&&!await y()))try{await g.run(`UPDATE local_employees SET syncStatus = 'PENDING_DELETE' WHERE id = ?`,[String(e)]),console.log(`[SQLite Service] Soft deleted employee: ${e}`)}catch(e){console.error(`[SQLite Service sqliteSoftDeleteEmployee Error]:`,e?.message||e)}}async function $(e){if(!(!g&&!await y()))try{await g.run(`UPDATE local_attendance_logs SET syncStatus = 'PENDING_DELETE' WHERE id = ?`,[String(e)]),console.log(`[SQLite Service] Soft deleted attendance log: ${e}`)}catch(e){console.error(`[SQLite Service sqliteSoftDeleteAttendanceLog Error]:`,e?.message||e)}}export{P as A,A as C,W as D,Z as E,Q as F,l as I,u as L,H as M,te as N,T as O,$ as P,f as R,R as S,C as T,O as _,x as a,L as b,E as c,V as d,k as f,X as g,S as h,j as i,G as j,B as k,N as l,K as m,D as n,z as o,I as p,F as r,J as s,b as t,q as u,U as v,Y as w,w as x,M as y};