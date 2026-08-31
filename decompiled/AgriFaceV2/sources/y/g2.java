package y;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Range;
import android.util.Size;
import android.view.Surface;
import androidx.concurrent.futures.c;
import b0.b1;
import b0.u2;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import y.g2;
/* loaded from: classes.dex */
public final class g2 {

    /* renamed from: p  reason: collision with root package name */
    public static final Range f12794p = u2.f4433a;

    /* renamed from: a  reason: collision with root package name */
    private final Object f12795a = new Object();

    /* renamed from: b  reason: collision with root package name */
    private final Size f12796b;

    /* renamed from: c  reason: collision with root package name */
    private final a0 f12797c;

    /* renamed from: d  reason: collision with root package name */
    private final Range f12798d;

    /* renamed from: e  reason: collision with root package name */
    private final b0.h0 f12799e;

    /* renamed from: f  reason: collision with root package name */
    private final boolean f12800f;

    /* renamed from: g  reason: collision with root package name */
    final i4.d f12801g;

    /* renamed from: h  reason: collision with root package name */
    private final c.a f12802h;

    /* renamed from: i  reason: collision with root package name */
    private final i4.d f12803i;

    /* renamed from: j  reason: collision with root package name */
    private final c.a f12804j;

    /* renamed from: k  reason: collision with root package name */
    private final c.a f12805k;

    /* renamed from: l  reason: collision with root package name */
    private final b0.b1 f12806l;

    /* renamed from: m  reason: collision with root package name */
    private h f12807m;

    /* renamed from: n  reason: collision with root package name */
    private i f12808n;

    /* renamed from: o  reason: collision with root package name */
    private Executor f12809o;

    /* loaded from: classes.dex */
    class a implements f0.c {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ c.a f12810a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ i4.d f12811b;

        a(c.a aVar, i4.d dVar) {
            this.f12810a = aVar;
            this.f12811b = dVar;
        }

        @Override // f0.c
        public void b(Throwable th) {
            g1.e.l(th instanceof f ? this.f12811b.cancel(false) : this.f12810a.c(null));
        }

        @Override // f0.c
        /* renamed from: c */
        public void a(Void r22) {
            g1.e.l(this.f12810a.c(null));
        }
    }

    /* loaded from: classes.dex */
    class b extends b0.b1 {
        b(Size size, int i8) {
            super(size, i8);
        }

        @Override // b0.b1
        protected i4.d r() {
            return g2.this.f12801g;
        }
    }

    /* loaded from: classes.dex */
    class c implements f0.c {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ i4.d f12814a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ c.a f12815b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f12816c;

        c(i4.d dVar, c.a aVar, String str) {
            this.f12814a = dVar;
            this.f12815b = aVar;
            this.f12816c = str;
        }

        @Override // f0.c
        public void b(Throwable th) {
            if (!(th instanceof CancellationException)) {
                this.f12815b.c(null);
                return;
            }
            c.a aVar = this.f12815b;
            g1.e.l(aVar.f(new f(this.f12816c + " cancelled.", th)));
        }

        @Override // f0.c
        /* renamed from: c */
        public void a(Surface surface) {
            f0.n.C(this.f12814a, this.f12815b);
        }
    }

    /* loaded from: classes.dex */
    class d implements f0.c {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ g1.a f12818a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ Surface f12819b;

        d(g1.a aVar, Surface surface) {
            this.f12818a = aVar;
            this.f12819b = surface;
        }

        @Override // f0.c
        public void b(Throwable th) {
            g1.e.m(th instanceof f, "Camera surface session should only fail with request cancellation. Instead failed due to:\n" + th);
            this.f12818a.accept(g.c(1, this.f12819b));
        }

        @Override // f0.c
        /* renamed from: c */
        public void a(Void r32) {
            this.f12818a.accept(g.c(0, this.f12819b));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e implements f0.c {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Runnable f12821a;

        e(Runnable runnable) {
            this.f12821a = runnable;
        }

        @Override // f0.c
        public void b(Throwable th) {
        }

        @Override // f0.c
        /* renamed from: c */
        public void a(Void r12) {
            this.f12821a.run();
        }
    }

    /* loaded from: classes.dex */
    private static final class f extends RuntimeException {
        f(String str, Throwable th) {
            super(str, th);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class g {
        static g c(int i8, Surface surface) {
            return new y.h(i8, surface);
        }

        public abstract int a();

        public abstract Surface b();
    }

    /* loaded from: classes.dex */
    public static abstract class h {
        public static h g(Rect rect, int i8, int i9, boolean z7, Matrix matrix, boolean z8) {
            return new y.i(rect, i8, i9, z7, matrix, z8);
        }

        public abstract Rect a();

        public abstract int b();

        public abstract Matrix c();

        public abstract int d();

        public abstract boolean e();

        public abstract boolean f();
    }

    /* loaded from: classes.dex */
    public interface i {
        void a(h hVar);
    }

    public g2(Size size, b0.h0 h0Var, boolean z7, a0 a0Var, Range range, Runnable runnable) {
        this.f12796b = size;
        this.f12799e = h0Var;
        this.f12800f = z7;
        this.f12797c = a0Var;
        this.f12798d = range;
        final String str = "SurfaceRequest[size: " + size + ", id: " + hashCode() + "]";
        final AtomicReference atomicReference = new AtomicReference(null);
        i4.d a8 = androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: y.x1
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object u7;
                u7 = g2.u(atomicReference, str, aVar);
                return u7;
            }
        });
        c.a aVar = (c.a) g1.e.j((c.a) atomicReference.get());
        this.f12805k = aVar;
        final AtomicReference atomicReference2 = new AtomicReference(null);
        i4.d a9 = androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: y.y1
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar2) {
                Object v7;
                v7 = g2.v(atomicReference2, str, aVar2);
                return v7;
            }
        });
        this.f12803i = a9;
        f0.n.j(a9, new a(aVar, a8), e0.c.b());
        final AtomicReference atomicReference3 = new AtomicReference(null);
        i4.d a10 = androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: y.z1
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar2) {
                Object w7;
                w7 = g2.w(atomicReference3, str, aVar2);
                return w7;
            }
        });
        this.f12801g = a10;
        this.f12802h = (c.a) g1.e.j((c.a) atomicReference3.get());
        b bVar = new b(size, 34);
        this.f12806l = bVar;
        i4.d k8 = bVar.k();
        f0.n.j(a10, new c(k8, (c.a) g1.e.j((c.a) atomicReference2.get()), str), e0.c.b());
        k8.d(new Runnable() { // from class: y.a2
            @Override // java.lang.Runnable
            public final void run() {
                g2.this.x();
            }
        }, e0.c.b());
        this.f12804j = p(e0.c.b(), runnable);
    }

    private c.a p(Executor executor, Runnable runnable) {
        final AtomicReference atomicReference = new AtomicReference(null);
        f0.n.j(androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: y.d2
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object t8;
                t8 = g2.this.t(atomicReference, aVar);
                return t8;
            }
        }), new e(runnable), executor);
        return (c.a) g1.e.j((c.a) atomicReference.get());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object t(AtomicReference atomicReference, c.a aVar) {
        atomicReference.set(aVar);
        return "SurfaceRequest-surface-recreation(" + hashCode() + ")";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object u(AtomicReference atomicReference, String str, c.a aVar) {
        atomicReference.set(aVar);
        return str + "-cancellation";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object v(AtomicReference atomicReference, String str, c.a aVar) {
        atomicReference.set(aVar);
        return str + "-status";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object w(AtomicReference atomicReference, String str, c.a aVar) {
        atomicReference.set(aVar);
        return str + "-Surface";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void x() {
        this.f12801g.cancel(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void y(g1.a aVar, Surface surface) {
        aVar.accept(g.c(3, surface));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void z(g1.a aVar, Surface surface) {
        aVar.accept(g.c(4, surface));
    }

    public void C(final Surface surface, Executor executor, final g1.a aVar) {
        if (this.f12802h.c(surface) || this.f12801g.isCancelled()) {
            f0.n.j(this.f12803i, new d(aVar, surface), executor);
            return;
        }
        g1.e.l(this.f12801g.isDone());
        try {
            this.f12801g.get();
            executor.execute(new Runnable() { // from class: y.e2
                @Override // java.lang.Runnable
                public final void run() {
                    g2.y(g1.a.this, surface);
                }
            });
        } catch (InterruptedException | ExecutionException unused) {
            executor.execute(new Runnable() { // from class: y.f2
                @Override // java.lang.Runnable
                public final void run() {
                    g2.z(g1.a.this, surface);
                }
            });
        }
    }

    public void D(Executor executor, final i iVar) {
        final h hVar;
        synchronized (this.f12795a) {
            this.f12808n = iVar;
            this.f12809o = executor;
            hVar = this.f12807m;
        }
        if (hVar != null) {
            executor.execute(new Runnable() { // from class: y.b2
                @Override // java.lang.Runnable
                public final void run() {
                    g2.i.this.a(hVar);
                }
            });
        }
    }

    public void E(final h hVar) {
        final i iVar;
        Executor executor;
        synchronized (this.f12795a) {
            this.f12807m = hVar;
            iVar = this.f12808n;
            executor = this.f12809o;
        }
        if (iVar == null || executor == null) {
            return;
        }
        executor.execute(new Runnable() { // from class: y.c2
            @Override // java.lang.Runnable
            public final void run() {
                g2.i.this.a(hVar);
            }
        });
    }

    public boolean F() {
        return this.f12802h.f(new b1.b("Surface request will not complete."));
    }

    public void j() {
        synchronized (this.f12795a) {
            this.f12808n = null;
            this.f12809o = null;
        }
    }

    public b0.h0 k() {
        return this.f12799e;
    }

    public b0.b1 l() {
        return this.f12806l;
    }

    public a0 m() {
        return this.f12797c;
    }

    public Range n() {
        return this.f12798d;
    }

    public Size o() {
        return this.f12796b;
    }

    public boolean q() {
        F();
        return this.f12804j.c(null);
    }

    public boolean r() {
        return this.f12800f;
    }

    public boolean s() {
        return this.f12801g.isDone();
    }
}
