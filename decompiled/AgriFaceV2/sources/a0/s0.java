package a0;

import a0.e1;
import android.graphics.Bitmap;
import androidx.concurrent.futures.c;
import java.util.Objects;
import y.u0;
/* loaded from: classes.dex */
public class s0 implements u0 {

    /* renamed from: a  reason: collision with root package name */
    private final e1 f96a;

    /* renamed from: b  reason: collision with root package name */
    private final e1.a f97b;

    /* renamed from: e  reason: collision with root package name */
    private c.a f100e;

    /* renamed from: f  reason: collision with root package name */
    private c.a f101f;

    /* renamed from: i  reason: collision with root package name */
    private i4.d f104i;

    /* renamed from: g  reason: collision with root package name */
    private boolean f102g = false;

    /* renamed from: h  reason: collision with root package name */
    private boolean f103h = false;

    /* renamed from: c  reason: collision with root package name */
    private final i4.d f98c = androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: a0.q0
        @Override // androidx.concurrent.futures.c.InterfaceC0018c
        public final Object a(c.a aVar) {
            Object r8;
            r8 = s0.this.r(aVar);
            return r8;
        }
    });

    /* renamed from: d  reason: collision with root package name */
    private final i4.d f99d = androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: a0.r0
        @Override // androidx.concurrent.futures.c.InterfaceC0018c
        public final Object a(c.a aVar) {
            Object s8;
            s8 = s0.this.s(aVar);
            return s8;
        }
    });

    /* JADX INFO: Access modifiers changed from: package-private */
    public s0(e1 e1Var, e1.a aVar) {
        this.f96a = e1Var;
        this.f97b = aVar;
    }

    private void l(y.v0 v0Var) {
        d0.q.a();
        this.f102g = true;
        i4.d dVar = this.f104i;
        Objects.requireNonNull(dVar);
        dVar.cancel(true);
        this.f100e.f(v0Var);
        this.f101f.c(null);
    }

    private void o() {
        g1.e.m(this.f98c.isDone(), "onImageCaptured() must be called before onFinalResult()");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object r(c.a aVar) {
        this.f100e = aVar;
        return "CaptureCompleteFuture";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object s(c.a aVar) {
        this.f101f = aVar;
        return "RequestCompleteFuture";
    }

    private void t() {
        g1.e.m(!this.f99d.isDone(), "The callback can only complete once.");
        this.f101f.c(null);
    }

    private void u(y.v0 v0Var) {
        d0.q.a();
        this.f96a.x(v0Var);
    }

    @Override // a0.u0
    public void a(Bitmap bitmap) {
        d0.q.a();
        if (this.f102g) {
            return;
        }
        this.f96a.y(bitmap);
    }

    @Override // a0.u0
    public void b(int i8) {
        d0.q.a();
        if (this.f102g) {
            return;
        }
        this.f96a.w(i8);
    }

    @Override // a0.u0
    public void c() {
        d0.q.a();
        if (this.f102g || this.f103h) {
            return;
        }
        this.f103h = true;
        this.f96a.j();
        u0.f l8 = this.f96a.l();
        if (l8 != null) {
            l8.c();
        }
    }

    @Override // a0.u0
    public void d() {
        d0.q.a();
        if (this.f102g) {
            return;
        }
        if (!this.f103h) {
            c();
        }
        this.f100e.c(null);
    }

    @Override // a0.u0
    public boolean e() {
        return this.f102g;
    }

    @Override // a0.u0
    public void f(y.v0 v0Var) {
        d0.q.a();
        if (this.f102g) {
            return;
        }
        o();
        t();
        u(v0Var);
    }

    @Override // a0.u0
    public void g(u0.h hVar) {
        d0.q.a();
        if (this.f102g) {
            return;
        }
        o();
        t();
        this.f96a.A(hVar);
    }

    @Override // a0.u0
    public void h(y.v0 v0Var) {
        d0.q.a();
        if (this.f102g) {
            return;
        }
        boolean f8 = this.f96a.f();
        if (!f8) {
            u(v0Var);
        }
        t();
        this.f100e.f(v0Var);
        if (f8) {
            this.f97b.a(this.f96a);
        }
    }

    @Override // a0.u0
    public void i(androidx.camera.core.n nVar) {
        d0.q.a();
        if (this.f102g) {
            nVar.close();
            return;
        }
        o();
        t();
        this.f96a.z(nVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m(y.v0 v0Var) {
        d0.q.a();
        if (this.f99d.isDone()) {
            return;
        }
        l(v0Var);
        u(v0Var);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n() {
        d0.q.a();
        if (this.f99d.isDone()) {
            return;
        }
        l(new y.v0(3, "The request is aborted silently and retried.", null));
        this.f97b.a(this.f96a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public i4.d p() {
        d0.q.a();
        return this.f98c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public i4.d q() {
        d0.q.a();
        return this.f99d;
    }

    public void v(i4.d dVar) {
        d0.q.a();
        g1.e.m(this.f104i == null, "CaptureRequestFuture can only be set once.");
        this.f104i = dVar;
    }
}
