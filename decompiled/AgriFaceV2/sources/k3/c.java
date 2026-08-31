package k3;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Scope;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public abstract class c {

    /* renamed from: a  reason: collision with root package name */
    private int f8332a;

    /* renamed from: b  reason: collision with root package name */
    private long f8333b;

    /* renamed from: c  reason: collision with root package name */
    private long f8334c;

    /* renamed from: d  reason: collision with root package name */
    private int f8335d;

    /* renamed from: e  reason: collision with root package name */
    private long f8336e;

    /* renamed from: g  reason: collision with root package name */
    l1 f8338g;

    /* renamed from: h  reason: collision with root package name */
    private final Context f8339h;

    /* renamed from: i  reason: collision with root package name */
    private final Looper f8340i;

    /* renamed from: j  reason: collision with root package name */
    private final i f8341j;

    /* renamed from: k  reason: collision with root package name */
    private final h3.e f8342k;

    /* renamed from: l  reason: collision with root package name */
    final Handler f8343l;

    /* renamed from: o  reason: collision with root package name */
    private m f8346o;

    /* renamed from: p  reason: collision with root package name */
    protected InterfaceC0115c f8347p;

    /* renamed from: q  reason: collision with root package name */
    private IInterface f8348q;

    /* renamed from: s  reason: collision with root package name */
    private x0 f8350s;

    /* renamed from: u  reason: collision with root package name */
    private final a f8352u;

    /* renamed from: v  reason: collision with root package name */
    private final b f8353v;

    /* renamed from: w  reason: collision with root package name */
    private final int f8354w;

    /* renamed from: x  reason: collision with root package name */
    private final String f8355x;

    /* renamed from: y  reason: collision with root package name */
    private volatile String f8356y;
    private static final h3.c[] E = new h3.c[0];
    public static final String[] D = {"service_esmobile", "service_googleme"};

    /* renamed from: f  reason: collision with root package name */
    private volatile String f8337f = null;

    /* renamed from: m  reason: collision with root package name */
    private final Object f8344m = new Object();

    /* renamed from: n  reason: collision with root package name */
    private final Object f8345n = new Object();

    /* renamed from: r  reason: collision with root package name */
    private final ArrayList f8349r = new ArrayList();

    /* renamed from: t  reason: collision with root package name */
    private int f8351t = 1;

    /* renamed from: z  reason: collision with root package name */
    private h3.a f8357z = null;
    private boolean A = false;
    private volatile a1 B = null;
    protected AtomicInteger C = new AtomicInteger(0);

    /* loaded from: classes.dex */
    public interface a {
        void d(int i8);

        void f(Bundle bundle);
    }

    /* loaded from: classes.dex */
    public interface b {
        void e(h3.a aVar);
    }

    /* renamed from: k3.c$c  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0115c {
        void b(h3.a aVar);
    }

    /* loaded from: classes.dex */
    protected class d implements InterfaceC0115c {
        public d() {
        }

        @Override // k3.c.InterfaceC0115c
        public final void b(h3.a aVar) {
            if (aVar.e()) {
                c cVar = c.this;
                cVar.j(null, cVar.B());
            } else if (c.this.f8353v != null) {
                c.this.f8353v.e(aVar);
            }
        }
    }

    /* loaded from: classes.dex */
    public interface e {
        void a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public c(Context context, Looper looper, i iVar, h3.e eVar, int i8, a aVar, b bVar, String str) {
        q.h(context, "Context must not be null");
        this.f8339h = context;
        q.h(looper, "Looper must not be null");
        this.f8340i = looper;
        q.h(iVar, "Supervisor must not be null");
        this.f8341j = iVar;
        q.h(eVar, "API availability must not be null");
        this.f8342k = eVar;
        this.f8343l = new u0(this, looper);
        this.f8354w = i8;
        this.f8352u = aVar;
        this.f8353v = bVar;
        this.f8355x = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void a0(c cVar, a1 a1Var) {
        cVar.B = a1Var;
        if (cVar.Q()) {
            f fVar = a1Var.f8329d;
            r.b().c(fVar == null ? null : fVar.f());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void b0(c cVar, int i8) {
        int i9;
        int i10;
        synchronized (cVar.f8344m) {
            i9 = cVar.f8351t;
        }
        if (i9 == 3) {
            cVar.A = true;
            i10 = 5;
        } else {
            i10 = 4;
        }
        Handler handler = cVar.f8343l;
        handler.sendMessage(handler.obtainMessage(i10, cVar.C.get(), 16));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ boolean e0(c cVar, int i8, int i9, IInterface iInterface) {
        synchronized (cVar.f8344m) {
            if (cVar.f8351t != i8) {
                return false;
            }
            cVar.g0(i9, iInterface);
            return true;
        }
    }

    /*  JADX ERROR: NullPointerException in pass: RegionMakerVisitor
        java.lang.NullPointerException: Cannot read field "wordsInUse" because "set" is null
        	at java.base/java.util.BitSet.or(BitSet.java:943)
        	at jadx.core.utils.BlockUtils.getPathCross(BlockUtils.java:732)
        	at jadx.core.utils.BlockUtils.getPathCross(BlockUtils.java:811)
        	at jadx.core.dex.visitors.regions.IfMakerHelper.restructureIf(IfMakerHelper.java:88)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:706)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:155)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:730)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:155)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    static /* bridge */ /* synthetic */ boolean f0(k3.c r2) {
        /*
            boolean r0 = r2.A
            r1 = 0
            if (r0 == 0) goto L6
            goto L24
        L6:
            java.lang.String r0 = r2.D()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L11
            goto L24
        L11:
            java.lang.String r0 = r2.A()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L1c
            goto L24
        L1c:
            java.lang.String r2 = r2.D()     // Catch: java.lang.ClassNotFoundException -> L24
            java.lang.Class.forName(r2)     // Catch: java.lang.ClassNotFoundException -> L24
            r1 = 1
        L24:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: k3.c.f0(k3.c):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void g0(int i8, IInterface iInterface) {
        l1 l1Var;
        q.a((i8 == 4) == (iInterface != null));
        synchronized (this.f8344m) {
            this.f8351t = i8;
            this.f8348q = iInterface;
            if (i8 == 1) {
                x0 x0Var = this.f8350s;
                if (x0Var != null) {
                    i iVar = this.f8341j;
                    String b8 = this.f8338g.b();
                    q.g(b8);
                    iVar.d(b8, this.f8338g.a(), 4225, x0Var, V(), this.f8338g.c());
                    this.f8350s = null;
                }
            } else if (i8 == 2 || i8 == 3) {
                x0 x0Var2 = this.f8350s;
                if (x0Var2 != null && (l1Var = this.f8338g) != null) {
                    String b9 = l1Var.b();
                    String a8 = l1Var.a();
                    Log.e("GmsClient", "Calling connect() while still connected, missing disconnect() for " + b9 + " on " + a8);
                    i iVar2 = this.f8341j;
                    String b10 = this.f8338g.b();
                    q.g(b10);
                    iVar2.d(b10, this.f8338g.a(), 4225, x0Var2, V(), this.f8338g.c());
                    this.C.incrementAndGet();
                }
                x0 x0Var3 = new x0(this, this.C.get());
                this.f8350s = x0Var3;
                l1 l1Var2 = (this.f8351t != 3 || A() == null) ? new l1(F(), E(), false, 4225, H()) : new l1(x().getPackageName(), A(), true, 4225, false);
                this.f8338g = l1Var2;
                if (l1Var2.c() && g() < 17895000) {
                    throw new IllegalStateException("Internal Error, the minimum apk version of this BaseGmsClient is too low to support dynamic lookup. Start service action: ".concat(String.valueOf(this.f8338g.b())));
                }
                i iVar3 = this.f8341j;
                String b11 = this.f8338g.b();
                q.g(b11);
                if (!iVar3.e(new e1(b11, this.f8338g.a(), 4225, this.f8338g.c()), x0Var3, V(), v())) {
                    String b12 = this.f8338g.b();
                    String a9 = this.f8338g.a();
                    Log.w("GmsClient", "unable to connect to service: " + b12 + " on " + a9);
                    c0(16, null, this.C.get());
                }
            } else if (i8 == 4) {
                q.g(iInterface);
                J(iInterface);
            }
        }
    }

    protected String A() {
        return null;
    }

    protected abstract Set B();

    public final IInterface C() {
        IInterface iInterface;
        synchronized (this.f8344m) {
            if (this.f8351t == 5) {
                throw new DeadObjectException();
            }
            q();
            iInterface = this.f8348q;
            q.h(iInterface, "Client is connected but service is null");
        }
        return iInterface;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract String D();

    protected abstract String E();

    protected String F() {
        return "com.google.android.gms";
    }

    public f G() {
        a1 a1Var = this.B;
        if (a1Var == null) {
            return null;
        }
        return a1Var.f8329d;
    }

    protected boolean H() {
        return g() >= 211700000;
    }

    public boolean I() {
        return this.B != null;
    }

    protected void J(IInterface iInterface) {
        this.f8334c = System.currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void K(h3.a aVar) {
        this.f8335d = aVar.a();
        this.f8336e = System.currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void L(int i8) {
        this.f8332a = i8;
        this.f8333b = System.currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void M(int i8, IBinder iBinder, Bundle bundle, int i9) {
        this.f8343l.sendMessage(this.f8343l.obtainMessage(1, i9, -1, new y0(this, i8, iBinder, bundle)));
    }

    public boolean N() {
        return false;
    }

    public void O(String str) {
        this.f8356y = str;
    }

    public void P(int i8) {
        this.f8343l.sendMessage(this.f8343l.obtainMessage(6, this.C.get(), i8));
    }

    public boolean Q() {
        return false;
    }

    protected final String V() {
        String str = this.f8355x;
        return str == null ? this.f8339h.getClass().getName() : str;
    }

    public boolean a() {
        boolean z7;
        synchronized (this.f8344m) {
            z7 = this.f8351t == 4;
        }
        return z7;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void c0(int i8, Bundle bundle, int i9) {
        this.f8343l.sendMessage(this.f8343l.obtainMessage(7, i9, -1, new z0(this, i8, null)));
    }

    public void d(String str) {
        this.f8337f = str;
        n();
    }

    public boolean e() {
        return true;
    }

    public void f(e eVar) {
        eVar.a();
    }

    public abstract int g();

    public boolean h() {
        boolean z7;
        synchronized (this.f8344m) {
            int i8 = this.f8351t;
            z7 = true;
            if (i8 != 2 && i8 != 3) {
                z7 = false;
            }
        }
        return z7;
    }

    public final h3.c[] i() {
        a1 a1Var = this.B;
        if (a1Var == null) {
            return null;
        }
        return a1Var.f8327b;
    }

    public void j(k kVar, Set set) {
        Bundle z7 = z();
        String str = this.f8356y;
        int i8 = h3.e.f7235a;
        Scope[] scopeArr = g.f8394o;
        Bundle bundle = new Bundle();
        int i9 = this.f8354w;
        h3.c[] cVarArr = g.f8395p;
        g gVar = new g(6, i9, i8, null, null, scopeArr, bundle, null, cVarArr, cVarArr, true, 0, false, str);
        gVar.f8399d = this.f8339h.getPackageName();
        gVar.f8402g = z7;
        if (set != null) {
            gVar.f8401f = (Scope[]) set.toArray(new Scope[0]);
        }
        if (o()) {
            Account t8 = t();
            if (t8 == null) {
                t8 = new Account("<<default account>>", "com.google");
            }
            gVar.f8403h = t8;
            if (kVar != null) {
                gVar.f8400e = kVar.asBinder();
            }
        } else if (N()) {
            gVar.f8403h = t();
        }
        gVar.f8404i = E;
        gVar.f8405j = u();
        if (Q()) {
            gVar.f8408m = true;
        }
        try {
            synchronized (this.f8345n) {
                m mVar = this.f8346o;
                if (mVar != null) {
                    mVar.j(new w0(this, this.C.get()), gVar);
                } else {
                    Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                }
            }
        } catch (DeadObjectException e8) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e8);
            P(3);
        } catch (RemoteException e9) {
            e = e9;
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            M(8, null, null, this.C.get());
        } catch (SecurityException e10) {
            throw e10;
        } catch (RuntimeException e11) {
            e = e11;
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            M(8, null, null, this.C.get());
        }
    }

    public String k() {
        l1 l1Var;
        if (!a() || (l1Var = this.f8338g) == null) {
            throw new RuntimeException("Failed to connect when checking package");
        }
        return l1Var.a();
    }

    public void l(InterfaceC0115c interfaceC0115c) {
        q.h(interfaceC0115c, "Connection progress callbacks cannot be null.");
        this.f8347p = interfaceC0115c;
        g0(2, null);
    }

    public String m() {
        return this.f8337f;
    }

    public void n() {
        this.C.incrementAndGet();
        synchronized (this.f8349r) {
            int size = this.f8349r.size();
            for (int i8 = 0; i8 < size; i8++) {
                ((v0) this.f8349r.get(i8)).d();
            }
            this.f8349r.clear();
        }
        synchronized (this.f8345n) {
            this.f8346o = null;
        }
        g0(1, null);
    }

    public boolean o() {
        return false;
    }

    protected final void q() {
        if (!a()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract IInterface r(IBinder iBinder);

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean s() {
        return false;
    }

    public abstract Account t();

    public h3.c[] u() {
        return E;
    }

    protected abstract Executor v();

    public Bundle w() {
        return null;
    }

    public final Context x() {
        return this.f8339h;
    }

    public int y() {
        return this.f8354w;
    }

    protected Bundle z() {
        return new Bundle();
    }
}
