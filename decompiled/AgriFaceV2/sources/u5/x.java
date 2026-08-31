package u5;

import android.content.SharedPreferences;
import android.util.Log;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class x {
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01b1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(android.content.SharedPreferences r9, android.content.SharedPreferences r10, android.content.SharedPreferences r11, u5.y r12, u5.s r13, java.lang.String r14) {
        /*
            Method dump skipped, instructions count: 467
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: u5.x.a(android.content.SharedPreferences, android.content.SharedPreferences, android.content.SharedPreferences, u5.y, u5.s, java.lang.String):void");
    }

    public static void b(SharedPreferences sharedPreferences, SharedPreferences sharedPreferences2, y yVar, s sVar, String str) {
        a(sharedPreferences, sharedPreferences2, null, yVar, sVar, str);
    }

    public static void c(SharedPreferences sharedPreferences, SharedPreferences sharedPreferences2, SharedPreferences sharedPreferences3, y yVar, s sVar, String str) {
        e(sharedPreferences, sharedPreferences2, sharedPreferences3, str);
        yVar.b().remove("FlutterSecureStorageBackupStatus").commit();
        Log.d("MigrationBackup", "Backup deleted and status key removed");
    }

    public static void d(SharedPreferences sharedPreferences, SharedPreferences sharedPreferences2, y yVar, s sVar, String str) {
        c(sharedPreferences, sharedPreferences2, null, yVar, sVar, str);
    }

    private static void e(SharedPreferences sharedPreferences, SharedPreferences sharedPreferences2, SharedPreferences sharedPreferences3, String str) {
        int i8;
        int i9 = 0;
        if (sharedPreferences3 != null) {
            SharedPreferences.Editor edit = sharedPreferences3.edit();
            i8 = 0;
            for (Map.Entry<String, ?> entry : sharedPreferences3.getAll().entrySet()) {
                String key = entry.getKey();
                if (key.endsWith("_BACKUP") && key.contains(str)) {
                    edit.remove(key);
                    i8++;
                }
            }
            edit.commit();
        } else {
            i8 = 0;
        }
        SharedPreferences.Editor edit2 = sharedPreferences.edit();
        int i10 = 0;
        for (Map.Entry<String, ?> entry2 : sharedPreferences.getAll().entrySet()) {
            String key2 = entry2.getKey();
            if (key2.endsWith("_BACKUP") && key2.contains(str)) {
                edit2.remove(key2);
                i10++;
            }
        }
        edit2.commit();
        SharedPreferences.Editor edit3 = sharedPreferences2.edit();
        for (Map.Entry<String, ?> entry3 : sharedPreferences2.getAll().entrySet()) {
            String key3 = entry3.getKey();
            if (key3.endsWith("_BACKUP")) {
                edit3.remove(key3);
                i9++;
            }
        }
        edit3.commit();
        if (i10 > 0 || i9 > 0 || i8 > 0) {
            Log.d("MigrationBackup", "Deleted " + i10 + " data _BACKUP entries, " + i9 + " key _BACKUP entries, " + i8 + " ESP _BACKUP entries");
        }
    }

    public static void f(y yVar, String str) {
        SharedPreferences.Editor b8 = yVar.b();
        int i8 = 0;
        for (Map.Entry entry : yVar.c().entrySet()) {
            String str2 = (String) entry.getKey();
            if (str2.endsWith("_MIGRATED") && str2.contains(str)) {
                b8.remove(str2);
                i8++;
            }
        }
        b8.commit();
        if (i8 > 0) {
            Log.d("MigrationBackup", "Deleted " + i8 + " _MIGRATED marker entries from configSource");
        }
    }

    public static void g(SharedPreferences sharedPreferences, SharedPreferences sharedPreferences2, y yVar, String str) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
            String key = entry.getKey();
            if (!key.endsWith("_BACKUP") && key.contains(str)) {
                if (yVar != null) {
                    if (yVar.a(key + "_MIGRATED")) {
                        Log.d("MigrationBackup", "Preserving already-migrated key in dataSource: " + key);
                        i10++;
                    }
                }
                edit.remove(key);
                i9++;
            }
        }
        edit.commit();
        if (yVar == null || !i(yVar, str)) {
            SharedPreferences.Editor edit2 = sharedPreferences2.edit();
            for (Map.Entry<String, ?> entry2 : sharedPreferences2.getAll().entrySet()) {
                String key2 = entry2.getKey();
                if (!key2.endsWith("_BACKUP")) {
                    edit2.remove(key2);
                    i8++;
                }
            }
            edit2.commit();
        } else {
            Log.d("MigrationBackup", "Preserving keyStorage originals (new wrapped AES key) — already-migrated keys exist");
        }
        Log.d("MigrationBackup", "Deleted " + i9 + " original data entries (preserved " + i10 + " already-migrated), " + i8 + " original key entries");
    }

    public static String h(y yVar, s sVar) {
        return yVar.f("FlutterSecureStorageBackupStatus", null);
    }

    public static boolean i(y yVar, String str) {
        for (Map.Entry entry : yVar.c().entrySet()) {
            String str2 = (String) entry.getKey();
            if (str2.endsWith("_MIGRATED") && str2.contains(str)) {
                return true;
            }
        }
        return false;
    }

    public static void j(y yVar, s sVar, String str) {
        if (sVar.v()) {
            yVar.b().putString("FlutterSecureStorageBackupStatus", str).commit();
        }
    }
}
