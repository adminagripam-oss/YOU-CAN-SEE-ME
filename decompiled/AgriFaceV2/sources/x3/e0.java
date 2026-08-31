package x3;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.internal.c;
/* loaded from: classes.dex */
public final class e0 extends k3.h {
    private final b1.g I;
    private final b1.g J;
    private final b1.g K;
    private final b1.g L;

    public e0(Context context, Looper looper, k3.e eVar, j3.c cVar, j3.h hVar) {
        super(context, looper, 23, eVar, cVar, hVar);
        this.I = new b1.g();
        this.J = new b1.g();
        this.K = new b1.g();
        this.L = new b1.g();
    }

    private final boolean j0(h3.c cVar) {
        h3.c cVar2;
        h3.c[] i8 = i();
        if (i8 != null) {
            int i9 = 0;
            while (true) {
                if (i9 >= i8.length) {
                    cVar2 = null;
                    break;
                }
                cVar2 = i8[i9];
                if (cVar.a().equals(cVar2.a())) {
                    break;
                }
                i9++;
            }
            if (cVar2 != null && cVar2.b() >= cVar.b()) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // k3.c
    public final String D() {
        return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
    }

    @Override // k3.c
    protected final String E() {
        return "com.google.android.location.internal.GoogleLocationManagerService.START";
    }

    @Override // k3.c
    public final void L(int i8) {
        super.L(i8);
        synchronized (this.I) {
            this.I.clear();
        }
        synchronized (this.J) {
            this.J.clear();
        }
        synchronized (this.K) {
            this.K.clear();
        }
    }

    @Override // k3.c
    public final boolean Q() {
        return true;
    }

    @Override // k3.c, i3.a.f
    public final int g() {
        return 11717000;
    }

    public final void k0(c4.d dVar, f4.k kVar) {
        if (j0(c4.v.f4825j)) {
            ((g1) C()).F(dVar, g0.b(new x(kVar)));
        } else if (j0(c4.v.f4821f)) {
            ((g1) C()).n(dVar, new x(kVar));
        } else {
            kVar.c(((g1) C()).Q());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003d A[Catch: all -> 0x0075, TryCatch #0 {, blocks: (B:4:0x001a, B:8:0x0028, B:11:0x003d, B:13:0x0073, B:12:0x0054, B:9:0x002e), top: B:18:0x001a }] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0054 A[Catch: all -> 0x0075, TryCatch #0 {, blocks: (B:4:0x001a, B:8:0x0028, B:11:0x003d, B:13:0x0073, B:12:0x0054, B:9:0x002e), top: B:18:0x001a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void l0(x3.z r18, com.google.android.gms.location.LocationRequest r19, f4.k r20) {
        /*
            r17 = this;
            r1 = r17
            r0 = r19
            r2 = r20
            com.google.android.gms.common.api.internal.c r3 = r18.zza()
            com.google.android.gms.common.api.internal.c$a r4 = r3.b()
            java.util.Objects.requireNonNull(r4)
            h3.c r5 = c4.v.f4825j
            boolean r5 = r1.j0(r5)
            b1.g r6 = r1.J
            monitor-enter(r6)
            b1.g r7 = r1.J     // Catch: java.lang.Throwable -> L75
            java.lang.Object r7 = r7.get(r4)     // Catch: java.lang.Throwable -> L75
            x3.d0 r7 = (x3.d0) r7     // Catch: java.lang.Throwable -> L75
            r8 = 0
            if (r7 == 0) goto L2e
            if (r5 == 0) goto L28
            goto L2e
        L28:
            r7.f(r3)     // Catch: java.lang.Throwable -> L75
            r13 = r7
            r7 = r8
            goto L3b
        L2e:
            x3.d0 r3 = new x3.d0     // Catch: java.lang.Throwable -> L75
            r9 = r18
            r3.<init>(r9)     // Catch: java.lang.Throwable -> L75
            b1.g r9 = r1.J     // Catch: java.lang.Throwable -> L75
            r9.put(r4, r3)     // Catch: java.lang.Throwable -> L75
            r13 = r3
        L3b:
            if (r5 == 0) goto L54
            android.os.IInterface r3 = r17.C()     // Catch: java.lang.Throwable -> L75
            x3.g1 r3 = (x3.g1) r3     // Catch: java.lang.Throwable -> L75
            java.lang.String r4 = r4.a()     // Catch: java.lang.Throwable -> L75
            x3.g0 r4 = x3.g0.a(r7, r13, r4)     // Catch: java.lang.Throwable -> L75
            x3.w r5 = new x3.w     // Catch: java.lang.Throwable -> L75
            r5.<init>(r8, r2)     // Catch: java.lang.Throwable -> L75
            r3.A(r4, r0, r5)     // Catch: java.lang.Throwable -> L75
            goto L73
        L54:
            android.os.IInterface r3 = r17.C()     // Catch: java.lang.Throwable -> L75
            x3.g1 r3 = (x3.g1) r3     // Catch: java.lang.Throwable -> L75
            x3.i0 r11 = x3.i0.a(r8, r0)     // Catch: java.lang.Throwable -> L75
            x3.u r15 = new x3.u     // Catch: java.lang.Throwable -> L75
            r15.<init>(r2, r13)     // Catch: java.lang.Throwable -> L75
            java.lang.String r16 = r4.a()     // Catch: java.lang.Throwable -> L75
            x3.k0 r0 = new x3.k0     // Catch: java.lang.Throwable -> L75
            r10 = 1
            r12 = 0
            r14 = 0
            r9 = r0
            r9.<init>(r10, r11, r12, r13, r14, r15, r16)     // Catch: java.lang.Throwable -> L75
            r3.L(r0)     // Catch: java.lang.Throwable -> L75
        L73:
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L75
            return
        L75:
            r0 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L75
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: x3.e0.l0(x3.z, com.google.android.gms.location.LocationRequest, f4.k):void");
    }

    public final void m0(c.a aVar, boolean z7, f4.k kVar) {
        synchronized (this.J) {
            d0 d0Var = (d0) this.J.remove(aVar);
            if (d0Var == null) {
                kVar.c(Boolean.FALSE);
                return;
            }
            d0Var.d0();
            if (!z7) {
                kVar.c(Boolean.TRUE);
            } else if (j0(c4.v.f4825j)) {
                int identityHashCode = System.identityHashCode(d0Var);
                StringBuilder sb = new StringBuilder(String.valueOf(identityHashCode).length() + 18);
                sb.append("ILocationCallback@");
                sb.append(identityHashCode);
                ((g1) C()).S(g0.a(null, d0Var, sb.toString()), new w(Boolean.TRUE, kVar));
            } else {
                ((g1) C()).L(new k0(2, null, null, d0Var, null, new y(Boolean.TRUE, kVar), null));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // k3.c
    public final /* synthetic */ IInterface r(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        return queryLocalInterface instanceof g1 ? (g1) queryLocalInterface : new f1(iBinder);
    }

    @Override // k3.c
    public final h3.c[] u() {
        return c4.v.f4831p;
    }
}
