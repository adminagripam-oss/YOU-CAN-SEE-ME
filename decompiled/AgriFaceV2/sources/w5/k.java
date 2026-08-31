package w5;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.os.Build;
import android.util.Log;
import i6.j;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class k {

    /* renamed from: n  reason: collision with root package name */
    private static Boolean f12525n;

    /* renamed from: a  reason: collision with root package name */
    final boolean f12526a;

    /* renamed from: b  reason: collision with root package name */
    final String f12527b;

    /* renamed from: c  reason: collision with root package name */
    final int f12528c;

    /* renamed from: d  reason: collision with root package name */
    final int f12529d;

    /* renamed from: e  reason: collision with root package name */
    final Context f12530e;

    /* renamed from: h  reason: collision with root package name */
    public p f12533h;

    /* renamed from: i  reason: collision with root package name */
    SQLiteDatabase f12534i;

    /* renamed from: l  reason: collision with root package name */
    private Integer f12537l;

    /* renamed from: f  reason: collision with root package name */
    final List f12531f = new ArrayList();

    /* renamed from: g  reason: collision with root package name */
    final Map f12532g = new HashMap();

    /* renamed from: j  reason: collision with root package name */
    private int f12535j = 0;

    /* renamed from: k  reason: collision with root package name */
    private int f12536k = 0;

    /* renamed from: m  reason: collision with root package name */
    private int f12538m = 0;

    /* loaded from: classes.dex */
    class a implements DatabaseErrorHandler {
        a() {
        }

        @Override // android.database.DatabaseErrorHandler
        public void onCorruption(SQLiteDatabase sQLiteDatabase) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public k(Context context, String str, int i8, boolean z7, int i9) {
        this.f12530e = context;
        this.f12527b = str;
        this.f12526a = z7;
        this.f12528c = i8;
        this.f12529d = i9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Cursor G(e0 e0Var, SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
        e0Var.a(sQLiteQuery);
        return new SQLiteCursor(sQLiteCursorDriver, str, sQLiteQuery);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void H(y5.e eVar) {
        Boolean d8 = eVar.d();
        boolean z7 = Boolean.TRUE.equals(d8) && eVar.f();
        if (z7) {
            int i8 = this.f12536k + 1;
            this.f12536k = i8;
            this.f12537l = Integer.valueOf(i8);
        }
        if (!w(eVar)) {
            if (z7) {
                this.f12537l = null;
            }
        } else if (z7) {
            HashMap hashMap = new HashMap();
            hashMap.put("transactionId", this.f12537l);
            eVar.success(hashMap);
        } else {
            if (Boolean.FALSE.equals(d8)) {
                this.f12537l = null;
            }
            eVar.success(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Q() {
        while (!this.f12531f.isEmpty() && this.f12537l == null) {
            ((y5.g) this.f12531f.get(0)).a();
            this.f12531f.remove(0);
        }
    }

    private void S(y5.e eVar, Runnable runnable) {
        Integer e8 = eVar.e();
        Integer num = this.f12537l;
        if (num == null) {
            runnable.run();
        } else if (e8 == null || !(e8.equals(num) || e8.intValue() == -1)) {
            this.f12531f.add(new y5.g(eVar, runnable));
        } else {
            runnable.run();
            if (this.f12537l != null || this.f12531f.isEmpty()) {
                return;
            }
            this.f12533h.d(this, new Runnable() { // from class: w5.j
                @Override // java.lang.Runnable
                public final void run() {
                    k.this.Q();
                }
            });
        }
    }

    protected static boolean i(Context context, String str, boolean z7) {
        ApplicationInfo y7;
        PackageManager.ApplicationInfoFlags of;
        try {
            String packageName = context.getPackageName();
            if (Build.VERSION.SDK_INT >= 33) {
                PackageManager packageManager = context.getPackageManager();
                of = PackageManager.ApplicationInfoFlags.of(128L);
                y7 = packageManager.getApplicationInfo(packageName, of);
            } else {
                y7 = y(context, packageName, 128);
            }
            return y7.metaData.getBoolean(str, z7);
        } catch (Exception e8) {
            e8.printStackTrace();
            return false;
        }
    }

    protected static boolean j(Context context) {
        return i(context, "com.tekartik.sqflite.wal_enabled", false);
    }

    private void l(int i8) {
        u uVar = (u) this.f12532g.get(Integer.valueOf(i8));
        if (uVar != null) {
            m(uVar);
        }
    }

    private void m(u uVar) {
        try {
            int i8 = uVar.f12565a;
            if (s.c(this.f12529d)) {
                Log.d("Sqflite", A() + "closing cursor " + i8);
            }
            this.f12532g.remove(Integer.valueOf(i8));
            uVar.f12567c.close();
        } catch (Exception unused) {
        }
    }

    private Map n(Cursor cursor, Integer num) {
        HashMap hashMap = null;
        int i8 = 0;
        ArrayList arrayList = null;
        while (cursor.moveToNext()) {
            if (hashMap == null) {
                ArrayList arrayList2 = new ArrayList();
                HashMap hashMap2 = new HashMap();
                i8 = cursor.getColumnCount();
                hashMap2.put("columns", Arrays.asList(cursor.getColumnNames()));
                hashMap2.put("rows", arrayList2);
                arrayList = arrayList2;
                hashMap = hashMap2;
            }
            arrayList.add(f0.a(cursor, i8));
            if (num != null && arrayList.size() >= num.intValue()) {
                break;
            }
        }
        return hashMap == null ? new HashMap() : hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void o(String str) {
        SQLiteDatabase.deleteDatabase(new File(str));
    }

    private boolean p(y5.e eVar) {
        if (w(eVar)) {
            eVar.success(null);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00cf  */
    /* renamed from: q */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean I(y5.e r10) {
        /*
            r9 = this;
            boolean r0 = r9.w(r10)
            r1 = 0
            if (r0 != 0) goto L8
            return r1
        L8:
            boolean r0 = r10.c()
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L14
            r10.success(r2)
            return r3
        L14:
            java.lang.String r0 = "SELECT changes(), last_insert_rowid()"
            android.database.sqlite.SQLiteDatabase r4 = r9.C()     // Catch: java.lang.Throwable -> Lbc java.lang.Exception -> Lbe
            android.database.Cursor r0 = r4.rawQuery(r0, r2)     // Catch: java.lang.Throwable -> Lbc java.lang.Exception -> Lbe
            java.lang.String r4 = "Sqflite"
            if (r0 == 0) goto L9b
            int r5 = r0.getCount()     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            if (r5 <= 0) goto L9b
            boolean r5 = r0.moveToFirst()     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            if (r5 == 0) goto L9b
            int r5 = r0.getInt(r1)     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            if (r5 != 0) goto L67
            int r5 = r9.f12529d     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            boolean r5 = w5.s.b(r5)     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            if (r5 == 0) goto L60
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            r5.<init>()     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            java.lang.String r6 = r9.A()     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            r5.append(r6)     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            java.lang.String r6 = "no changes (id was "
            r5.append(r6)     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            long r6 = r0.getLong(r3)     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            r5.append(r6)     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            java.lang.String r6 = ")"
            r5.append(r6)     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            android.util.Log.d(r4, r5)     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
        L60:
            r10.success(r2)     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            r0.close()
            return r3
        L67:
            long r5 = r0.getLong(r3)     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            int r2 = r9.f12529d     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            boolean r2 = w5.s.b(r2)     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            if (r2 == 0) goto L8e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            r2.<init>()     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            java.lang.String r7 = r9.A()     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            r2.append(r7)     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            java.lang.String r7 = "inserted "
            r2.append(r7)     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            r2.append(r5)     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            android.util.Log.d(r4, r2)     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
        L8e:
            java.lang.Long r2 = java.lang.Long.valueOf(r5)     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            r10.success(r2)     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            r0.close()
            return r3
        L99:
            r2 = move-exception
            goto Lc2
        L9b:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            r5.<init>()     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            java.lang.String r6 = r9.A()     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            r5.append(r6)     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            java.lang.String r6 = "fail to read changes for Insert"
            r5.append(r6)     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            android.util.Log.e(r4, r5)     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            r10.success(r2)     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lcb
            if (r0 == 0) goto Lbb
            r0.close()
        Lbb:
            return r3
        Lbc:
            r10 = move-exception
            goto Lcd
        Lbe:
            r0 = move-exception
            r8 = r2
            r2 = r0
            r0 = r8
        Lc2:
            r9.D(r2, r10)     // Catch: java.lang.Throwable -> Lcb
            if (r0 == 0) goto Lca
            r0.close()
        Lca:
            return r1
        Lcb:
            r10 = move-exception
            r2 = r0
        Lcd:
            if (r2 == 0) goto Ld2
            r2.close()
        Ld2:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: w5.k.I(y5.e):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [y5.f, y5.e] */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.Object, w5.e0] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v4, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v6, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r9v0, types: [w5.k] */
    /* renamed from: r */
    public boolean J(y5.e eVar) {
        Integer num = (Integer) eVar.a("cursorPageSize");
        final ?? b8 = eVar.b();
        if (s.b(this.f12529d)) {
            Log.d("Sqflite", A() + b8);
        }
        u uVar = null;
        try {
            try {
                b8 = z().rawQueryWithFactory(new SQLiteDatabase.CursorFactory() { // from class: w5.h
                    @Override // android.database.sqlite.SQLiteDatabase.CursorFactory
                    public final Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
                        Cursor G;
                        G = k.G(e0.this, sQLiteDatabase, sQLiteCursorDriver, str, sQLiteQuery);
                        return G;
                    }
                }, b8.c(), w5.a.f12477a, null);
                try {
                    Map n8 = n(b8, num);
                    if ((num == null || b8.isLast() || b8.isAfterLast()) ? false : true) {
                        int i8 = this.f12538m + 1;
                        this.f12538m = i8;
                        n8.put("cursorId", Integer.valueOf(i8));
                        u uVar2 = new u(i8, num.intValue(), b8);
                        try {
                            this.f12532g.put(Integer.valueOf(i8), uVar2);
                            uVar = uVar2;
                        } catch (Exception e8) {
                            e = e8;
                            uVar = uVar2;
                            D(e, eVar);
                            if (uVar != null) {
                                m(uVar);
                            }
                            if (uVar == null && b8 != 0) {
                                b8.close();
                            }
                            return false;
                        } catch (Throwable th) {
                            th = th;
                            uVar = uVar2;
                            if (uVar == null && b8 != 0) {
                                b8.close();
                            }
                            throw th;
                        }
                    }
                    eVar.success(n8);
                    if (uVar == null && b8 != 0) {
                        b8.close();
                    }
                    return true;
                } catch (Exception e9) {
                    e = e9;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e10) {
            e = e10;
            b8 = 0;
        } catch (Throwable th3) {
            th = th3;
            b8 = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s */
    public boolean K(y5.e eVar) {
        boolean z7;
        int intValue = ((Integer) eVar.a("cursorId")).intValue();
        boolean equals = Boolean.TRUE.equals(eVar.a("cancel"));
        if (s.c(this.f12529d)) {
            StringBuilder sb = new StringBuilder();
            sb.append(A());
            sb.append("cursor ");
            sb.append(intValue);
            sb.append(equals ? " cancel" : " next");
            Log.d("Sqflite", sb.toString());
        }
        u uVar = null;
        if (equals) {
            l(intValue);
            eVar.success(null);
            return true;
        }
        u uVar2 = (u) this.f12532g.get(Integer.valueOf(intValue));
        boolean z8 = false;
        try {
            if (uVar2 == null) {
                throw new IllegalStateException("Cursor " + intValue + " not found");
            }
            Cursor cursor = uVar2.f12567c;
            Map n8 = n(cursor, Integer.valueOf(uVar2.f12566b));
            z7 = (cursor.isLast() || cursor.isAfterLast()) ? false : true;
            if (z7) {
                try {
                    try {
                        n8.put("cursorId", Integer.valueOf(intValue));
                    } catch (Exception e8) {
                        e = e8;
                        D(e, eVar);
                        if (uVar2 != null) {
                            m(uVar2);
                        } else {
                            uVar = uVar2;
                        }
                        if (!z7 && uVar != null) {
                            m(uVar);
                        }
                        return false;
                    }
                } catch (Throwable th) {
                    th = th;
                    z8 = z7;
                    if (!z8 && uVar2 != null) {
                        m(uVar2);
                    }
                    throw th;
                }
            }
            eVar.success(n8);
            if (!z7) {
                m(uVar2);
            }
            return true;
        } catch (Exception e9) {
            e = e9;
            z7 = false;
        } catch (Throwable th2) {
            th = th2;
            if (!z8) {
                m(uVar2);
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: t */
    public boolean L(y5.e eVar) {
        if (!w(eVar)) {
            return false;
        }
        Cursor cursor = null;
        if (eVar.c()) {
            eVar.success(null);
            return true;
        }
        try {
            try {
                Cursor rawQuery = C().rawQuery("SELECT changes()", null);
                if (rawQuery != null) {
                    try {
                        if (rawQuery.getCount() > 0 && rawQuery.moveToFirst()) {
                            int i8 = rawQuery.getInt(0);
                            if (s.b(this.f12529d)) {
                                Log.d("Sqflite", A() + "changed " + i8);
                            }
                            eVar.success(Integer.valueOf(i8));
                            rawQuery.close();
                            return true;
                        }
                    } catch (Exception e8) {
                        e = e8;
                        cursor = rawQuery;
                        D(e, eVar);
                        if (cursor != null) {
                            cursor.close();
                        }
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        cursor = rawQuery;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
                Log.e("Sqflite", A() + "fail to read changes for Update/Delete");
                eVar.success(null);
                if (rawQuery != null) {
                    rawQuery.close();
                }
                return true;
            } catch (Exception e9) {
                e = e9;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private boolean w(y5.e eVar) {
        e0 b8 = eVar.b();
        if (s.b(this.f12529d)) {
            Log.d("Sqflite", A() + b8);
        }
        Boolean d8 = eVar.d();
        try {
            C().execSQL(b8.c(), b8.d());
            u(d8);
            return true;
        } catch (Exception e8) {
            D(e8, eVar);
            return false;
        }
    }

    public static boolean x(String str) {
        try {
            return new File(str).exists();
        } catch (Exception unused) {
            return false;
        }
    }

    static ApplicationInfo y(Context context, String str, int i8) {
        return context.getPackageManager().getApplicationInfo(str, i8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String A() {
        return "[" + B() + "] ";
    }

    String B() {
        Thread currentThread = Thread.currentThread();
        return this.f12528c + "," + currentThread.getName() + "(" + currentThread.getId() + ")";
    }

    public SQLiteDatabase C() {
        return this.f12534i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void D(Exception exc, y5.e eVar) {
        String message;
        Map a8;
        if (exc instanceof SQLiteCantOpenDatabaseException) {
            message = "open_failed " + this.f12527b;
            a8 = null;
        } else {
            boolean z7 = exc instanceof SQLException;
            message = exc.getMessage();
            a8 = y5.h.a(eVar);
        }
        eVar.error("sqlite_error", message, a8);
    }

    public void E(final y5.e eVar) {
        S(eVar, new Runnable() { // from class: w5.f
            @Override // java.lang.Runnable
            public final void run() {
                k.this.I(eVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean F() {
        return this.f12535j > 0;
    }

    public void M() {
        if (f12525n == null) {
            Boolean valueOf = Boolean.valueOf(j(this.f12530e));
            f12525n = valueOf;
            if (valueOf.booleanValue() && s.c(this.f12529d)) {
                Log.d("Sqflite", A() + "[sqflite] WAL enabled");
            }
        }
        this.f12534i = SQLiteDatabase.openDatabase(this.f12527b, null, f12525n.booleanValue() ? 805306368 : 268435456);
    }

    public void N() {
        this.f12534i = SQLiteDatabase.openDatabase(this.f12527b, null, 1, new a());
    }

    public void O(final y5.e eVar) {
        S(eVar, new Runnable() { // from class: w5.g
            @Override // java.lang.Runnable
            public final void run() {
                k.this.J(eVar);
            }
        });
    }

    public void P(final y5.e eVar) {
        S(eVar, new Runnable() { // from class: w5.d
            @Override // java.lang.Runnable
            public final void run() {
                k.this.K(eVar);
            }
        });
    }

    public void R(final y5.e eVar) {
        S(eVar, new Runnable() { // from class: w5.i
            @Override // java.lang.Runnable
            public final void run() {
                k.this.L(eVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h(i6.i iVar, j.d dVar) {
        y5.d dVar2 = new y5.d(iVar, dVar);
        boolean c8 = dVar2.c();
        boolean j8 = dVar2.j();
        ArrayList arrayList = new ArrayList();
        for (Map map : (List) dVar2.a("operations")) {
            y5.c cVar = new y5.c(map, c8);
            String g8 = cVar.g();
            g8.hashCode();
            char c9 = 65535;
            switch (g8.hashCode()) {
                case -1319569547:
                    if (g8.equals("execute")) {
                        c9 = 0;
                        break;
                    }
                    break;
                case -1183792455:
                    if (g8.equals("insert")) {
                        c9 = 1;
                        break;
                    }
                    break;
                case -838846263:
                    if (g8.equals("update")) {
                        c9 = 2;
                        break;
                    }
                    break;
                case 107944136:
                    if (g8.equals("query")) {
                        c9 = 3;
                        break;
                    }
                    break;
            }
            switch (c9) {
                case 0:
                    if (p(cVar)) {
                        cVar.r(arrayList);
                    } else {
                        if (!j8) {
                            cVar.p(dVar);
                            return;
                        }
                        cVar.q(arrayList);
                    }
                case 1:
                    if (I(cVar)) {
                        cVar.r(arrayList);
                    } else {
                        if (!j8) {
                            cVar.p(dVar);
                            return;
                        }
                        cVar.q(arrayList);
                    }
                case 2:
                    if (L(cVar)) {
                        cVar.r(arrayList);
                    } else {
                        if (!j8) {
                            cVar.p(dVar);
                            return;
                        }
                        cVar.q(arrayList);
                    }
                case 3:
                    if (J(cVar)) {
                        cVar.r(arrayList);
                    } else {
                        if (!j8) {
                            cVar.p(dVar);
                            return;
                        }
                        cVar.q(arrayList);
                    }
                default:
                    dVar.error("bad_param", "Batch method '" + g8 + "' not supported", null);
                    return;
            }
        }
        if (c8) {
            dVar.success(null);
        } else {
            dVar.success(arrayList);
        }
    }

    public void k() {
        if (!this.f12532g.isEmpty() && s.b(this.f12529d)) {
            Log.d("Sqflite", A() + this.f12532g.size() + " cursor(s) are left opened");
        }
        this.f12534i.close();
    }

    synchronized void u(Boolean bool) {
        if (Boolean.TRUE.equals(bool)) {
            this.f12535j++;
        } else if (Boolean.FALSE.equals(bool)) {
            this.f12535j--;
        }
    }

    public void v(final y5.e eVar) {
        S(eVar, new Runnable() { // from class: w5.e
            @Override // java.lang.Runnable
            public final void run() {
                k.this.H(eVar);
            }
        });
    }

    public SQLiteDatabase z() {
        return this.f12534i;
    }
}
