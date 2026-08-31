package x;

import androidx.concurrent.futures.c;
import b0.d0;
import b0.u0;
import f0.n;
import java.util.concurrent.Executor;
import q.a;
import r.t;
import y.k;
/* loaded from: classes.dex */
public final class g {

    /* renamed from: c  reason: collision with root package name */
    private final t f12595c;

    /* renamed from: d  reason: collision with root package name */
    final Executor f12596d;

    /* renamed from: g  reason: collision with root package name */
    c.a f12599g;

    /* renamed from: a  reason: collision with root package name */
    private boolean f12593a = false;

    /* renamed from: b  reason: collision with root package name */
    private boolean f12594b = false;

    /* renamed from: e  reason: collision with root package name */
    final Object f12597e = new Object();

    /* renamed from: f  reason: collision with root package name */
    private a.C0140a f12598f = new a.C0140a();

    public g(t tVar, Executor executor) {
        this.f12595c = tVar;
        this.f12596d = executor;
    }

    private void h(j jVar) {
        synchronized (this.f12597e) {
            this.f12598f.b(jVar);
        }
    }

    private void k() {
        synchronized (this.f12597e) {
            this.f12598f = new a.C0140a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        c.a aVar = this.f12599g;
        if (aVar != null) {
            aVar.c(null);
            this.f12599g = null;
        }
    }

    private void m(Exception exc) {
        c.a aVar = this.f12599g;
        if (aVar != null) {
            if (exc == null) {
                exc = new Exception("Camera2CameraControl failed with unknown error.");
            }
            aVar.f(exc);
            this.f12599g = null;
        }
    }

    public static g n(k kVar) {
        d0 a8 = ((d0) kVar).a();
        g1.e.b(a8 instanceof t, "CameraControl doesn't contain Camera2 implementation.");
        return ((t) a8).E();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object q(final c.a aVar) {
        this.f12596d.execute(new Runnable() { // from class: x.b
            @Override // java.lang.Runnable
            public final void run() {
                g.this.p(aVar);
            }
        });
        return "addCaptureRequestOptions";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object s(final c.a aVar) {
        this.f12596d.execute(new Runnable() { // from class: x.e
            @Override // java.lang.Runnable
            public final void run() {
                g.this.r(aVar);
            }
        });
        return "clearCaptureRequestOptions";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: v */
    public void t(boolean z7) {
        if (this.f12593a == z7) {
            return;
        }
        this.f12593a = z7;
        if (!z7) {
            m(new k.a("The camera control has became inactive."));
        } else if (this.f12594b) {
            x();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: w */
    public void r(c.a aVar) {
        this.f12594b = true;
        m(new k.a("Camera2CameraControl was updated with new options."));
        this.f12599g = aVar;
        if (this.f12593a) {
            x();
        }
    }

    private void x() {
        this.f12595c.t0().d(new Runnable() { // from class: x.f
            @Override // java.lang.Runnable
            public final void run() {
                g.this.l();
            }
        }, this.f12596d);
        this.f12594b = false;
    }

    public i4.d g(j jVar) {
        h(jVar);
        return n.B(androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: x.a
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object q8;
                q8 = g.this.q(aVar);
                return q8;
            }
        }));
    }

    public void i(a.C0140a c0140a) {
        synchronized (this.f12597e) {
            c0140a.e(this.f12598f.c(), u0.c.ALWAYS_OVERRIDE);
        }
    }

    public i4.d j() {
        k();
        return n.B(androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: x.d
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object s8;
                s8 = g.this.s(aVar);
                return s8;
            }
        }));
    }

    public q.a o() {
        q.a a8;
        synchronized (this.f12597e) {
            a8 = this.f12598f.a();
        }
        return a8;
    }

    public void u(final boolean z7) {
        this.f12596d.execute(new Runnable() { // from class: x.c
            @Override // java.lang.Runnable
            public final void run() {
                g.this.t(z7);
            }
        });
    }
}
