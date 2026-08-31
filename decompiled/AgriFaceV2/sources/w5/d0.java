package w5;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import e6.a;
import i6.j;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class d0 implements e6.a, j.c {

    /* renamed from: h  reason: collision with root package name */
    static String f12499h;

    /* renamed from: l  reason: collision with root package name */
    private static p f12503l;

    /* renamed from: a  reason: collision with root package name */
    private Context f12504a;

    /* renamed from: b  reason: collision with root package name */
    private i6.j f12505b;

    /* renamed from: c  reason: collision with root package name */
    static final Map f12494c = new HashMap();

    /* renamed from: d  reason: collision with root package name */
    static final Map f12495d = new HashMap();

    /* renamed from: e  reason: collision with root package name */
    private static final Object f12496e = new Object();

    /* renamed from: f  reason: collision with root package name */
    private static final Object f12497f = new Object();

    /* renamed from: g  reason: collision with root package name */
    static int f12498g = 0;

    /* renamed from: i  reason: collision with root package name */
    private static int f12500i = 0;

    /* renamed from: j  reason: collision with root package name */
    private static int f12501j = 1;

    /* renamed from: k  reason: collision with root package name */
    private static int f12502k = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements Runnable {

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ k f12506e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ j.d f12507f;

        a(k kVar, j.d dVar) {
            this.f12506e = kVar;
            this.f12507f = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (d0.f12497f) {
                d0.this.l(this.f12506e);
            }
            this.f12507f.success(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements Runnable {

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ k f12509e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ String f12510f;

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ j.d f12511g;

        b(k kVar, String str, j.d dVar) {
            this.f12509e = kVar;
            this.f12510f = str;
            this.f12511g = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (d0.f12497f) {
                k kVar = this.f12509e;
                if (kVar != null) {
                    d0.this.l(kVar);
                }
                try {
                    if (s.c(d0.f12498g)) {
                        Log.d("Sqflite", "delete database " + this.f12510f);
                    }
                    k.o(this.f12510f);
                } catch (Exception e8) {
                    Log.e("Sqflite", "error " + e8 + " while closing database " + d0.f12502k);
                }
            }
            this.f12511g.success(null);
        }
    }

    private void A(i6.i iVar, j.d dVar) {
        int intValue = ((Integer) iVar.a("id")).intValue();
        k n8 = n(iVar, dVar);
        if (n8 == null) {
            return;
        }
        if (s.b(n8.f12529d)) {
            Log.d("Sqflite", n8.A() + "closing " + intValue + " " + n8.f12527b);
        }
        String str = n8.f12527b;
        synchronized (f12496e) {
            f12495d.remove(Integer.valueOf(intValue));
            if (n8.f12526a) {
                f12494c.remove(str);
            }
        }
        f12503l.d(n8, new a(n8, dVar));
    }

    private void B(i6.i iVar, j.d dVar) {
        dVar.success(Boolean.valueOf(k.x((String) iVar.a("path"))));
    }

    private void C(i6.i iVar, j.d dVar) {
        HashMap hashMap = new HashMap();
        if ("get".equals((String) iVar.a("cmd"))) {
            int i8 = f12498g;
            if (i8 > 0) {
                hashMap.put("logLevel", Integer.valueOf(i8));
            }
            Map map = f12495d;
            if (!map.isEmpty()) {
                HashMap hashMap2 = new HashMap();
                for (Map.Entry entry : map.entrySet()) {
                    k kVar = (k) entry.getValue();
                    HashMap hashMap3 = new HashMap();
                    hashMap3.put("path", kVar.f12527b);
                    hashMap3.put("singleInstance", Boolean.valueOf(kVar.f12526a));
                    int i9 = kVar.f12529d;
                    if (i9 > 0) {
                        hashMap3.put("logLevel", Integer.valueOf(i9));
                    }
                    hashMap2.put(((Integer) entry.getKey()).toString(), hashMap3);
                }
                hashMap.put("databases", hashMap2);
            }
        }
        dVar.success(hashMap);
    }

    private void D(i6.i iVar, j.d dVar) {
        x5.a.f12722a = Boolean.TRUE.equals(iVar.b());
        x5.a.f12724c = x5.a.f12723b && x5.a.f12722a;
        if (!x5.a.f12722a) {
            f12498g = 0;
        } else if (x5.a.f12724c) {
            f12498g = 2;
        } else if (x5.a.f12722a) {
            f12498g = 1;
        }
        dVar.success(null);
    }

    private void E(i6.i iVar, j.d dVar) {
        k kVar;
        String str = (String) iVar.a("path");
        synchronized (f12496e) {
            if (s.c(f12498g)) {
                Log.d("Sqflite", "Look for " + str + " in " + f12494c.keySet());
            }
            Map map = f12494c;
            Integer num = (Integer) map.get(str);
            if (num != null) {
                Map map2 = f12495d;
                kVar = (k) map2.get(num);
                if (kVar != null && kVar.f12534i.isOpen()) {
                    if (s.c(f12498g)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(kVar.A());
                        sb.append("found single instance ");
                        sb.append(kVar.F() ? "(in transaction) " : "");
                        sb.append(num);
                        sb.append(" ");
                        sb.append(str);
                        Log.d("Sqflite", sb.toString());
                    }
                    map2.remove(num);
                    map.remove(str);
                }
            }
            kVar = null;
        }
        b bVar = new b(kVar, str, dVar);
        p pVar = f12503l;
        if (pVar != null) {
            pVar.d(kVar, bVar);
        } else {
            bVar.run();
        }
    }

    private void F(final i6.i iVar, final j.d dVar) {
        final k n8 = n(iVar, dVar);
        if (n8 == null) {
            return;
        }
        f12503l.d(n8, new Runnable() { // from class: w5.y
            @Override // java.lang.Runnable
            public final void run() {
                d0.q(i6.i.this, dVar, n8);
            }
        });
    }

    private void H(final i6.i iVar, final j.d dVar) {
        final k n8 = n(iVar, dVar);
        if (n8 == null) {
            return;
        }
        f12503l.d(n8, new Runnable() { // from class: w5.v
            @Override // java.lang.Runnable
            public final void run() {
                d0.r(i6.i.this, dVar, n8);
            }
        });
    }

    private void I(final i6.i iVar, final j.d dVar) {
        final int i8;
        k kVar;
        final String str = (String) iVar.a("path");
        final Boolean bool = (Boolean) iVar.a("readOnly");
        final boolean o8 = o(str);
        boolean z7 = (Boolean.FALSE.equals(iVar.a("singleInstance")) || o8) ? false : true;
        if (z7) {
            synchronized (f12496e) {
                if (s.c(f12498g)) {
                    Log.d("Sqflite", "Look for " + str + " in " + f12494c.keySet());
                }
                Integer num = (Integer) f12494c.get(str);
                if (num != null && (kVar = (k) f12495d.get(num)) != null) {
                    if (kVar.f12534i.isOpen()) {
                        if (s.c(f12498g)) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(kVar.A());
                            sb.append("re-opened single instance ");
                            sb.append(kVar.F() ? "(in transaction) " : "");
                            sb.append(num);
                            sb.append(" ");
                            sb.append(str);
                            Log.d("Sqflite", sb.toString());
                        }
                        dVar.success(x(num.intValue(), true, kVar.F()));
                        return;
                    } else if (s.c(f12498g)) {
                        Log.d("Sqflite", kVar.A() + "single instance database of " + str + " not opened");
                    }
                }
            }
        }
        Object obj = f12496e;
        synchronized (obj) {
            i8 = f12502k + 1;
            f12502k = i8;
        }
        final k kVar2 = new k(this.f12504a, str, i8, z7, f12498g);
        synchronized (obj) {
            if (f12503l == null) {
                p a8 = p.a("Sqflite", f12501j, f12500i);
                f12503l = a8;
                a8.start();
                if (s.b(kVar2.f12529d)) {
                    Log.d("Sqflite", kVar2.A() + "starting worker pool with priority " + f12500i);
                }
            }
            kVar2.f12533h = f12503l;
            if (s.b(kVar2.f12529d)) {
                Log.d("Sqflite", kVar2.A() + "opened " + i8 + " " + str);
            }
            final boolean z8 = z7;
            f12503l.d(kVar2, new Runnable() { // from class: w5.a0
                @Override // java.lang.Runnable
                public final void run() {
                    d0.s(o8, str, dVar, bool, kVar2, iVar, z8, i8);
                }
            });
        }
    }

    private void K(final i6.i iVar, final j.d dVar) {
        final k n8 = n(iVar, dVar);
        if (n8 == null) {
            return;
        }
        f12503l.d(n8, new Runnable() { // from class: w5.z
            @Override // java.lang.Runnable
            public final void run() {
                d0.t(i6.i.this, dVar, n8);
            }
        });
    }

    private void L(final i6.i iVar, final j.d dVar) {
        final k n8 = n(iVar, dVar);
        if (n8 == null) {
            return;
        }
        f12503l.d(n8, new Runnable() { // from class: w5.w
            @Override // java.lang.Runnable
            public final void run() {
                d0.u(i6.i.this, dVar, n8);
            }
        });
    }

    private void M(final i6.i iVar, final j.d dVar) {
        final k n8 = n(iVar, dVar);
        if (n8 == null) {
            return;
        }
        f12503l.d(n8, new Runnable() { // from class: w5.b0
            @Override // java.lang.Runnable
            public final void run() {
                d0.v(i6.i.this, n8, dVar);
            }
        });
    }

    private void N(final i6.i iVar, final j.d dVar) {
        final k n8 = n(iVar, dVar);
        if (n8 == null) {
            return;
        }
        f12503l.d(n8, new Runnable() { // from class: w5.x
            @Override // java.lang.Runnable
            public final void run() {
                d0.w(i6.i.this, dVar, n8);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l(k kVar) {
        try {
            if (s.b(kVar.f12529d)) {
                Log.d("Sqflite", kVar.A() + "closing database ");
            }
            kVar.k();
        } catch (Exception e8) {
            Log.e("Sqflite", "error " + e8 + " while closing database " + f12502k);
        }
        synchronized (f12496e) {
            if (f12495d.isEmpty() && f12503l != null) {
                if (s.b(kVar.f12529d)) {
                    Log.d("Sqflite", kVar.A() + "stopping thread");
                }
                f12503l.b();
                f12503l = null;
            }
        }
    }

    private k m(int i8) {
        return (k) f12495d.get(Integer.valueOf(i8));
    }

    private k n(i6.i iVar, j.d dVar) {
        int intValue = ((Integer) iVar.a("id")).intValue();
        k m8 = m(intValue);
        if (m8 != null) {
            return m8;
        }
        dVar.error("sqlite_error", "database_closed " + intValue, null);
        return null;
    }

    static boolean o(String str) {
        return str == null || str.equals(":memory:");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void q(i6.i iVar, j.d dVar, k kVar) {
        kVar.v(new y5.d(iVar, dVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void r(i6.i iVar, j.d dVar, k kVar) {
        kVar.E(new y5.d(iVar, dVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void s(boolean z7, String str, j.d dVar, Boolean bool, k kVar, i6.i iVar, boolean z8, int i8) {
        synchronized (f12497f) {
            if (!z7) {
                try {
                    File file = new File(new File(str).getParent());
                    if (!file.exists() && !file.mkdirs() && !file.exists()) {
                        dVar.error("sqlite_error", "open_failed " + str, null);
                        return;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            try {
                if (Boolean.TRUE.equals(bool)) {
                    kVar.N();
                } else {
                    kVar.M();
                }
                synchronized (f12496e) {
                    if (z8) {
                        f12494c.put(str, Integer.valueOf(i8));
                    }
                    f12495d.put(Integer.valueOf(i8), kVar);
                }
                if (s.b(kVar.f12529d)) {
                    Log.d("Sqflite", kVar.A() + "opened " + i8 + " " + str);
                }
                dVar.success(x(i8, false, false));
            } catch (Exception e8) {
                kVar.D(e8, new y5.d(iVar, dVar));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void t(i6.i iVar, j.d dVar, k kVar) {
        kVar.O(new y5.d(iVar, dVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void u(i6.i iVar, j.d dVar, k kVar) {
        kVar.P(new y5.d(iVar, dVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void v(i6.i iVar, k kVar, j.d dVar) {
        try {
            kVar.f12534i.setLocale(f0.d((String) iVar.a("locale")));
            dVar.success(null);
        } catch (Exception e8) {
            dVar.error("sqlite_error", "Error calling setLocale: " + e8.getMessage(), null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void w(i6.i iVar, j.d dVar, k kVar) {
        kVar.R(new y5.d(iVar, dVar));
    }

    static Map x(int i8, boolean z7, boolean z8) {
        HashMap hashMap = new HashMap();
        hashMap.put("id", Integer.valueOf(i8));
        if (z7) {
            hashMap.put("recovered", Boolean.TRUE);
        }
        if (z8) {
            hashMap.put("recoveredInTransaction", Boolean.TRUE);
        }
        return hashMap;
    }

    private void y(Context context, i6.b bVar) {
        this.f12504a = context;
        i6.j jVar = new i6.j(bVar, "com.tekartik.sqflite", i6.o.f7550b, bVar.c());
        this.f12505b = jVar;
        jVar.e(this);
    }

    private void z(final i6.i iVar, final j.d dVar) {
        final k n8 = n(iVar, dVar);
        if (n8 == null) {
            return;
        }
        f12503l.d(n8, new Runnable() { // from class: w5.c0
            @Override // java.lang.Runnable
            public final void run() {
                k.this.h(iVar, dVar);
            }
        });
    }

    void G(i6.i iVar, j.d dVar) {
        if (f12499h == null) {
            f12499h = this.f12504a.getDatabasePath("tekartik_sqflite.db").getParent();
        }
        dVar.success(f12499h);
    }

    void J(i6.i iVar, j.d dVar) {
        Object a8 = iVar.a("androidThreadPriority");
        if (a8 != null) {
            f12500i = ((Integer) a8).intValue();
        }
        Object a9 = iVar.a("androidThreadCount");
        if (a9 != null && !a9.equals(Integer.valueOf(f12501j))) {
            f12501j = ((Integer) a9).intValue();
            p pVar = f12503l;
            if (pVar != null) {
                pVar.b();
                f12503l = null;
            }
        }
        Integer a10 = s.a(iVar);
        if (a10 != null) {
            f12498g = a10.intValue();
        }
        dVar.success(null);
    }

    @Override // e6.a
    public void onAttachedToEngine(a.b bVar) {
        y(bVar.a(), bVar.b());
    }

    @Override // e6.a
    public void onDetachedFromEngine(a.b bVar) {
        this.f12504a = null;
        this.f12505b.e(null);
        this.f12505b = null;
    }

    @Override // i6.j.c
    public void onMethodCall(i6.i iVar, j.d dVar) {
        String str = iVar.f7535a;
        str.hashCode();
        char c8 = 65535;
        switch (str.hashCode()) {
            case -1319569547:
                if (str.equals("execute")) {
                    c8 = 0;
                    break;
                }
                break;
            case -1253581933:
                if (str.equals("closeDatabase")) {
                    c8 = 1;
                    break;
                }
                break;
            case -1249474914:
                if (str.equals("options")) {
                    c8 = 2;
                    break;
                }
                break;
            case -1183792455:
                if (str.equals("insert")) {
                    c8 = 3;
                    break;
                }
                break;
            case -838846263:
                if (str.equals("update")) {
                    c8 = 4;
                    break;
                }
                break;
            case -396289107:
                if (str.equals("androidSetLocale")) {
                    c8 = 5;
                    break;
                }
                break;
            case -263511994:
                if (str.equals("deleteDatabase")) {
                    c8 = 6;
                    break;
                }
                break;
            case -198450538:
                if (str.equals("debugMode")) {
                    c8 = 7;
                    break;
                }
                break;
            case -17190427:
                if (str.equals("openDatabase")) {
                    c8 = '\b';
                    break;
                }
                break;
            case 93509434:
                if (str.equals("batch")) {
                    c8 = '\t';
                    break;
                }
                break;
            case 95458899:
                if (str.equals("debug")) {
                    c8 = '\n';
                    break;
                }
                break;
            case 107944136:
                if (str.equals("query")) {
                    c8 = 11;
                    break;
                }
                break;
            case 956410295:
                if (str.equals("databaseExists")) {
                    c8 = '\f';
                    break;
                }
                break;
            case 1193546321:
                if (str.equals("queryCursorNext")) {
                    c8 = '\r';
                    break;
                }
                break;
            case 1385449135:
                if (str.equals("getPlatformVersion")) {
                    c8 = 14;
                    break;
                }
                break;
            case 1863829223:
                if (str.equals("getDatabasesPath")) {
                    c8 = 15;
                    break;
                }
                break;
        }
        switch (c8) {
            case 0:
                F(iVar, dVar);
                return;
            case 1:
                A(iVar, dVar);
                return;
            case 2:
                J(iVar, dVar);
                return;
            case 3:
                H(iVar, dVar);
                return;
            case 4:
                N(iVar, dVar);
                return;
            case 5:
                M(iVar, dVar);
                return;
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                E(iVar, dVar);
                return;
            case 7:
                D(iVar, dVar);
                return;
            case '\b':
                I(iVar, dVar);
                return;
            case '\t':
                z(iVar, dVar);
                return;
            case '\n':
                C(iVar, dVar);
                return;
            case 11:
                K(iVar, dVar);
                return;
            case '\f':
                B(iVar, dVar);
                return;
            case '\r':
                L(iVar, dVar);
                return;
            case 14:
                dVar.success("Android " + Build.VERSION.RELEASE);
                return;
            case 15:
                G(iVar, dVar);
                return;
            default:
                dVar.notImplemented();
                return;
        }
    }
}
