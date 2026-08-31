package b0;

import b0.h0;
import b0.o0;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import z.a;
/* loaded from: classes.dex */
public final class o0 implements a.InterfaceC0170a {

    /* renamed from: a  reason: collision with root package name */
    private final StringBuilder f4289a = new StringBuilder();

    /* renamed from: b  reason: collision with root package name */
    private final Object f4290b;

    /* renamed from: c  reason: collision with root package name */
    private int f4291c;

    /* renamed from: d  reason: collision with root package name */
    private final z.a f4292d;

    /* renamed from: e  reason: collision with root package name */
    private final Map f4293e;

    /* renamed from: f  reason: collision with root package name */
    private int f4294f;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private h0.a f4295a;

        /* renamed from: b  reason: collision with root package name */
        private final Executor f4296b;

        /* renamed from: c  reason: collision with root package name */
        private final b f4297c;

        /* renamed from: d  reason: collision with root package name */
        private final c f4298d;

        a(h0.a aVar, Executor executor, b bVar, c cVar) {
            this.f4295a = aVar;
            this.f4296b = executor;
            this.f4297c = bVar;
            this.f4298d = cVar;
        }

        h0.a a() {
            return this.f4295a;
        }

        void b() {
            try {
                Executor executor = this.f4296b;
                final b bVar = this.f4297c;
                Objects.requireNonNull(bVar);
                executor.execute(new Runnable() { // from class: b0.n0
                    @Override // java.lang.Runnable
                    public final void run() {
                        o0.b.this.a();
                    }
                });
            } catch (RejectedExecutionException e8) {
                y.f1.d("CameraStateRegistry", "Unable to notify camera to configure.", e8);
            }
        }

        void c() {
            try {
                Executor executor = this.f4296b;
                final c cVar = this.f4298d;
                Objects.requireNonNull(cVar);
                executor.execute(new Runnable() { // from class: b0.m0
                    @Override // java.lang.Runnable
                    public final void run() {
                        o0.c.this.a();
                    }
                });
            } catch (RejectedExecutionException e8) {
                y.f1.d("CameraStateRegistry", "Unable to notify camera to open.", e8);
            }
        }

        h0.a d(h0.a aVar) {
            h0.a aVar2 = this.f4295a;
            this.f4295a = aVar;
            return aVar2;
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        void a();
    }

    /* loaded from: classes.dex */
    public interface c {
        void a();
    }

    public o0(z.a aVar, int i8) {
        Object obj = new Object();
        this.f4290b = obj;
        this.f4293e = new HashMap();
        this.f4291c = i8;
        synchronized (obj) {
            this.f4292d = aVar;
            this.f4294f = this.f4291c;
        }
    }

    private a b(String str) {
        for (y.j jVar : this.f4293e.keySet()) {
            if (str.equals(((g0) jVar.b()).g())) {
                return (a) this.f4293e.get(jVar);
            }
        }
        return null;
    }

    private static boolean d(h0.a aVar) {
        return aVar != null && aVar.e();
    }

    private void f() {
        if (y.f1.f("CameraStateRegistry")) {
            this.f4289a.setLength(0);
            this.f4289a.append("Recalculating open cameras:\n");
            this.f4289a.append(String.format(Locale.US, "%-45s%-22s\n", "Camera", "State"));
            this.f4289a.append("-------------------------------------------------------------------\n");
        }
        int i8 = 0;
        for (Map.Entry entry : this.f4293e.entrySet()) {
            if (y.f1.f("CameraStateRegistry")) {
                this.f4289a.append(String.format(Locale.US, "%-45s%-22s\n", ((y.j) entry.getKey()).toString(), ((a) entry.getValue()).a() != null ? ((a) entry.getValue()).a().toString() : "UNKNOWN"));
            }
            if (d(((a) entry.getValue()).a())) {
                i8++;
            }
        }
        if (y.f1.f("CameraStateRegistry")) {
            this.f4289a.append("-------------------------------------------------------------------\n");
            this.f4289a.append(String.format(Locale.US, "Open count: %d (Max allowed: %d)", Integer.valueOf(i8), Integer.valueOf(this.f4291c)));
            y.f1.a("CameraStateRegistry", this.f4289a.toString());
        }
        this.f4294f = Math.max(this.f4291c - i8, 0);
    }

    private static void h(y.j jVar, h0.a aVar) {
        if (z1.a.h()) {
            z1.a.j("CX:State[" + jVar + "]", aVar.ordinal());
        }
    }

    private h0.a k(y.j jVar) {
        a aVar = (a) this.f4293e.remove(jVar);
        if (aVar != null) {
            f();
            return aVar.a();
        }
        return null;
    }

    private h0.a l(y.j jVar, h0.a aVar) {
        h0.a d8 = ((a) g1.e.k((a) this.f4293e.get(jVar), "Cannot update state of camera which has not yet been registered. Register with CameraStateRegistry.registerCamera()")).d(aVar);
        h0.a aVar2 = h0.a.OPENING;
        if (aVar == aVar2) {
            g1.e.m(d(aVar) || d8 == aVar2, "Cannot mark camera as opening until camera was successful at calling CameraStateRegistry.tryOpenCamera()");
        }
        if (d8 != aVar) {
            h(jVar, aVar);
            f();
        }
        return d8;
    }

    @Override // z.a.InterfaceC0170a
    public void a(int i8, int i9) {
        synchronized (this.f4290b) {
            boolean z7 = true;
            this.f4291c = i9 == 2 ? 2 : 1;
            boolean z8 = i8 != 2 && i9 == 2;
            if (i8 != 2 || i9 == 2) {
                z7 = false;
            }
            if (z8 || z7) {
                f();
            }
        }
    }

    public boolean c() {
        synchronized (this.f4290b) {
            for (Map.Entry entry : this.f4293e.entrySet()) {
                if (((a) entry.getValue()).a() == h0.a.CLOSING) {
                    return true;
                }
            }
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void e(y.j r7, b0.h0.a r8, boolean r9) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.f4290b
            monitor-enter(r0)
            int r1 = r6.f4294f     // Catch: java.lang.Throwable -> Lbc
            b0.h0$a r2 = b0.h0.a.RELEASED     // Catch: java.lang.Throwable -> Lbc
            if (r8 != r2) goto Le
            b0.h0$a r2 = r6.k(r7)     // Catch: java.lang.Throwable -> Lbc
            goto L12
        Le:
            b0.h0$a r2 = r6.l(r7, r8)     // Catch: java.lang.Throwable -> Lbc
        L12:
            if (r2 != r8) goto L16
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lbc
            return
        L16:
            z.a r2 = r6.f4292d     // Catch: java.lang.Throwable -> Lbc
            int r2 = r2.a()     // Catch: java.lang.Throwable -> Lbc
            r3 = 2
            r4 = 0
            if (r2 != r3) goto L3b
            b0.h0$a r2 = b0.h0.a.CONFIGURED     // Catch: java.lang.Throwable -> Lbc
            if (r8 != r2) goto L3b
            y.p r2 = r7.b()     // Catch: java.lang.Throwable -> Lbc
            b0.g0 r2 = (b0.g0) r2     // Catch: java.lang.Throwable -> Lbc
            java.lang.String r2 = r2.g()     // Catch: java.lang.Throwable -> Lbc
            z.a r3 = r6.f4292d     // Catch: java.lang.Throwable -> Lbc
            java.lang.String r2 = r3.b(r2)     // Catch: java.lang.Throwable -> Lbc
            if (r2 == 0) goto L3b
            b0.o0$a r2 = r6.b(r2)     // Catch: java.lang.Throwable -> Lbc
            goto L3c
        L3b:
            r2 = r4
        L3c:
            r3 = 1
            if (r1 >= r3) goto L7c
            int r1 = r6.f4294f     // Catch: java.lang.Throwable -> Lbc
            if (r1 <= 0) goto L7c
            java.util.HashMap r4 = new java.util.HashMap     // Catch: java.lang.Throwable -> Lbc
            r4.<init>()     // Catch: java.lang.Throwable -> Lbc
            java.util.Map r8 = r6.f4293e     // Catch: java.lang.Throwable -> Lbc
            java.util.Set r8 = r8.entrySet()     // Catch: java.lang.Throwable -> Lbc
            java.util.Iterator r8 = r8.iterator()     // Catch: java.lang.Throwable -> Lbc
        L52:
            boolean r1 = r8.hasNext()     // Catch: java.lang.Throwable -> Lbc
            if (r1 == 0) goto L94
            java.lang.Object r1 = r8.next()     // Catch: java.lang.Throwable -> Lbc
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch: java.lang.Throwable -> Lbc
            java.lang.Object r3 = r1.getValue()     // Catch: java.lang.Throwable -> Lbc
            b0.o0$a r3 = (b0.o0.a) r3     // Catch: java.lang.Throwable -> Lbc
            b0.h0$a r3 = r3.a()     // Catch: java.lang.Throwable -> Lbc
            b0.h0$a r5 = b0.h0.a.PENDING_OPEN     // Catch: java.lang.Throwable -> Lbc
            if (r3 != r5) goto L52
            java.lang.Object r3 = r1.getKey()     // Catch: java.lang.Throwable -> Lbc
            y.j r3 = (y.j) r3     // Catch: java.lang.Throwable -> Lbc
            java.lang.Object r1 = r1.getValue()     // Catch: java.lang.Throwable -> Lbc
            b0.o0$a r1 = (b0.o0.a) r1     // Catch: java.lang.Throwable -> Lbc
            r4.put(r3, r1)     // Catch: java.lang.Throwable -> Lbc
            goto L52
        L7c:
            b0.h0$a r1 = b0.h0.a.PENDING_OPEN     // Catch: java.lang.Throwable -> Lbc
            if (r8 != r1) goto L94
            int r8 = r6.f4294f     // Catch: java.lang.Throwable -> Lbc
            if (r8 <= 0) goto L94
            java.util.HashMap r4 = new java.util.HashMap     // Catch: java.lang.Throwable -> Lbc
            r4.<init>()     // Catch: java.lang.Throwable -> Lbc
            java.util.Map r8 = r6.f4293e     // Catch: java.lang.Throwable -> Lbc
            java.lang.Object r8 = r8.get(r7)     // Catch: java.lang.Throwable -> Lbc
            b0.o0$a r8 = (b0.o0.a) r8     // Catch: java.lang.Throwable -> Lbc
            r4.put(r7, r8)     // Catch: java.lang.Throwable -> Lbc
        L94:
            if (r4 == 0) goto L9b
            if (r9 != 0) goto L9b
            r4.remove(r7)     // Catch: java.lang.Throwable -> Lbc
        L9b:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lbc
            if (r4 == 0) goto Lb6
            java.util.Collection r7 = r4.values()
            java.util.Iterator r7 = r7.iterator()
        La6:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto Lb6
            java.lang.Object r8 = r7.next()
            b0.o0$a r8 = (b0.o0.a) r8
            r8.c()
            goto La6
        Lb6:
            if (r2 == 0) goto Lbb
            r2.b()
        Lbb:
            return
        Lbc:
            r7 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lbc
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: b0.o0.e(y.j, b0.h0$a, boolean):void");
    }

    public void g(y.j jVar, Executor executor, b bVar, c cVar) {
        synchronized (this.f4290b) {
            boolean z7 = !this.f4293e.containsKey(jVar);
            g1.e.m(z7, "Camera is already registered: " + jVar);
            this.f4293e.put(jVar, new a(null, executor, bVar, cVar));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0074 A[Catch: all -> 0x009e, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x001d, B:7:0x0052, B:9:0x0056, B:14:0x006c, B:16:0x0074, B:20:0x0083, B:22:0x0099, B:23:0x009c, B:13:0x0063), top: B:28:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0099 A[Catch: all -> 0x009e, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x001d, B:7:0x0052, B:9:0x0056, B:14:0x006c, B:16:0x0074, B:20:0x0083, B:22:0x0099, B:23:0x009c, B:13:0x0063), top: B:28:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean i(y.j r11) {
        /*
            r10 = this;
            java.lang.Object r0 = r10.f4290b
            monitor-enter(r0)
            java.util.Map r1 = r10.f4293e     // Catch: java.lang.Throwable -> L9e
            java.lang.Object r1 = r1.get(r11)     // Catch: java.lang.Throwable -> L9e
            b0.o0$a r1 = (b0.o0.a) r1     // Catch: java.lang.Throwable -> L9e
            java.lang.String r2 = "Camera must first be registered with registerCamera()"
            java.lang.Object r1 = g1.e.k(r1, r2)     // Catch: java.lang.Throwable -> L9e
            b0.o0$a r1 = (b0.o0.a) r1     // Catch: java.lang.Throwable -> L9e
            java.lang.String r2 = "CameraStateRegistry"
            boolean r2 = y.f1.f(r2)     // Catch: java.lang.Throwable -> L9e
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L52
            java.lang.StringBuilder r2 = r10.f4289a     // Catch: java.lang.Throwable -> L9e
            r2.setLength(r4)     // Catch: java.lang.Throwable -> L9e
            java.lang.StringBuilder r2 = r10.f4289a     // Catch: java.lang.Throwable -> L9e
            java.util.Locale r5 = java.util.Locale.US     // Catch: java.lang.Throwable -> L9e
            java.lang.String r6 = "tryOpenCamera(%s) [Available Cameras: %d, Already Open: %b (Previous state: %s)]"
            r7 = 4
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch: java.lang.Throwable -> L9e
            r7[r4] = r11     // Catch: java.lang.Throwable -> L9e
            int r8 = r10.f4294f     // Catch: java.lang.Throwable -> L9e
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch: java.lang.Throwable -> L9e
            r7[r3] = r8     // Catch: java.lang.Throwable -> L9e
            b0.h0$a r8 = r1.a()     // Catch: java.lang.Throwable -> L9e
            boolean r8 = d(r8)     // Catch: java.lang.Throwable -> L9e
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r8)     // Catch: java.lang.Throwable -> L9e
            r9 = 2
            r7[r9] = r8     // Catch: java.lang.Throwable -> L9e
            b0.h0$a r8 = r1.a()     // Catch: java.lang.Throwable -> L9e
            r9 = 3
            r7[r9] = r8     // Catch: java.lang.Throwable -> L9e
            java.lang.String r5 = java.lang.String.format(r5, r6, r7)     // Catch: java.lang.Throwable -> L9e
            r2.append(r5)     // Catch: java.lang.Throwable -> L9e
        L52:
            int r2 = r10.f4294f     // Catch: java.lang.Throwable -> L9e
            if (r2 > 0) goto L63
            b0.h0$a r2 = r1.a()     // Catch: java.lang.Throwable -> L9e
            boolean r2 = d(r2)     // Catch: java.lang.Throwable -> L9e
            if (r2 == 0) goto L61
            goto L63
        L61:
            r11 = r4
            goto L6c
        L63:
            b0.h0$a r2 = b0.h0.a.OPENING     // Catch: java.lang.Throwable -> L9e
            r1.d(r2)     // Catch: java.lang.Throwable -> L9e
            h(r11, r2)     // Catch: java.lang.Throwable -> L9e
            r11 = r3
        L6c:
            java.lang.String r1 = "CameraStateRegistry"
            boolean r1 = y.f1.f(r1)     // Catch: java.lang.Throwable -> L9e
            if (r1 == 0) goto L97
            java.lang.StringBuilder r1 = r10.f4289a     // Catch: java.lang.Throwable -> L9e
            java.util.Locale r2 = java.util.Locale.US     // Catch: java.lang.Throwable -> L9e
            java.lang.String r5 = " --> %s"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L9e
            if (r11 == 0) goto L81
            java.lang.String r6 = "SUCCESS"
            goto L83
        L81:
            java.lang.String r6 = "FAIL"
        L83:
            r3[r4] = r6     // Catch: java.lang.Throwable -> L9e
            java.lang.String r2 = java.lang.String.format(r2, r5, r3)     // Catch: java.lang.Throwable -> L9e
            r1.append(r2)     // Catch: java.lang.Throwable -> L9e
            java.lang.String r1 = "CameraStateRegistry"
            java.lang.StringBuilder r2 = r10.f4289a     // Catch: java.lang.Throwable -> L9e
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L9e
            y.f1.a(r1, r2)     // Catch: java.lang.Throwable -> L9e
        L97:
            if (r11 == 0) goto L9c
            r10.f()     // Catch: java.lang.Throwable -> L9e
        L9c:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L9e
            return r11
        L9e:
            r11 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L9e
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: b0.o0.i(y.j):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0053 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean j(java.lang.String r6, java.lang.String r7) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.f4290b
            monitor-enter(r0)
            z.a r1 = r5.f4292d     // Catch: java.lang.Throwable -> L59
            int r1 = r1.a()     // Catch: java.lang.Throwable -> L59
            r2 = 2
            r3 = 1
            if (r1 == r2) goto Lf
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L59
            return r3
        Lf:
            b0.o0$a r6 = r5.b(r6)     // Catch: java.lang.Throwable -> L59
            r1 = 0
            if (r6 == 0) goto L1b
            b0.h0$a r6 = r6.a()     // Catch: java.lang.Throwable -> L59
            goto L1c
        L1b:
            r6 = r1
        L1c:
            if (r7 == 0) goto L23
            b0.o0$a r7 = r5.b(r7)     // Catch: java.lang.Throwable -> L59
            goto L24
        L23:
            r7 = r1
        L24:
            if (r7 == 0) goto L2a
            b0.h0$a r1 = r7.a()     // Catch: java.lang.Throwable -> L59
        L2a:
            b0.h0$a r7 = b0.h0.a.OPEN     // Catch: java.lang.Throwable -> L59
            boolean r2 = r7.equals(r6)     // Catch: java.lang.Throwable -> L59
            r4 = 0
            if (r2 != 0) goto L3e
            b0.h0$a r2 = b0.h0.a.CONFIGURED     // Catch: java.lang.Throwable -> L59
            boolean r6 = r2.equals(r6)     // Catch: java.lang.Throwable -> L59
            if (r6 == 0) goto L3c
            goto L3e
        L3c:
            r6 = r4
            goto L3f
        L3e:
            r6 = r3
        L3f:
            boolean r7 = r7.equals(r1)     // Catch: java.lang.Throwable -> L59
            if (r7 != 0) goto L50
            b0.h0$a r7 = b0.h0.a.CONFIGURED     // Catch: java.lang.Throwable -> L59
            boolean r7 = r7.equals(r1)     // Catch: java.lang.Throwable -> L59
            if (r7 == 0) goto L4e
            goto L50
        L4e:
            r7 = r4
            goto L51
        L50:
            r7 = r3
        L51:
            if (r6 == 0) goto L56
            if (r7 == 0) goto L56
            goto L57
        L56:
            r3 = r4
        L57:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L59
            return r3
        L59:
            r6 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L59
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: b0.o0.j(java.lang.String, java.lang.String):boolean");
    }
}
