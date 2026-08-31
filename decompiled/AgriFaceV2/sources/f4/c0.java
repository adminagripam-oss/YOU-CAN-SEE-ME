package f4;

import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class c0 extends j {

    /* renamed from: a  reason: collision with root package name */
    private final Object f6737a = new Object();

    /* renamed from: b  reason: collision with root package name */
    private final y f6738b = new y();

    /* renamed from: c  reason: collision with root package name */
    private boolean f6739c;

    /* renamed from: d  reason: collision with root package name */
    private volatile boolean f6740d;

    /* renamed from: e  reason: collision with root package name */
    private Object f6741e;

    /* renamed from: f  reason: collision with root package name */
    private Exception f6742f;

    private final void r() {
        k3.q.j(this.f6739c, "Task is not yet complete");
    }

    private final void s() {
        if (this.f6740d) {
            throw new CancellationException("Task is already canceled.");
        }
    }

    private final void t() {
        if (this.f6739c) {
            throw d.a(this);
        }
    }

    private final void u() {
        synchronized (this.f6737a) {
            if (this.f6739c) {
                this.f6738b.b(this);
            }
        }
    }

    @Override // f4.j
    public final j a(e eVar) {
        this.f6738b.a(new s(l.f6744a, eVar));
        u();
        return this;
    }

    @Override // f4.j
    public final j b(Executor executor, e eVar) {
        this.f6738b.a(new s(executor, eVar));
        u();
        return this;
    }

    @Override // f4.j
    public final j c(f fVar) {
        k(l.f6744a, fVar);
        return this;
    }

    @Override // f4.j
    public final j d(g gVar) {
        l(l.f6744a, gVar);
        return this;
    }

    @Override // f4.j
    public final j e(Executor executor, c cVar) {
        c0 c0Var = new c0();
        this.f6738b.a(new q(executor, cVar, c0Var));
        u();
        return c0Var;
    }

    @Override // f4.j
    public final Exception f() {
        Exception exc;
        synchronized (this.f6737a) {
            exc = this.f6742f;
        }
        return exc;
    }

    @Override // f4.j
    public final Object g() {
        Object obj;
        synchronized (this.f6737a) {
            r();
            s();
            Exception exc = this.f6742f;
            if (exc != null) {
                throw new i(exc);
            }
            obj = this.f6741e;
        }
        return obj;
    }

    @Override // f4.j
    public final boolean h() {
        return this.f6740d;
    }

    @Override // f4.j
    public final boolean i() {
        boolean z7;
        synchronized (this.f6737a) {
            z7 = this.f6739c;
        }
        return z7;
    }

    @Override // f4.j
    public final boolean j() {
        boolean z7;
        synchronized (this.f6737a) {
            z7 = false;
            if (this.f6739c && !this.f6740d && this.f6742f == null) {
                z7 = true;
            }
        }
        return z7;
    }

    public final j k(Executor executor, f fVar) {
        this.f6738b.a(new u(executor, fVar));
        u();
        return this;
    }

    public final j l(Executor executor, g gVar) {
        this.f6738b.a(new w(executor, gVar));
        u();
        return this;
    }

    public final void m(Exception exc) {
        k3.q.h(exc, "Exception must not be null");
        synchronized (this.f6737a) {
            t();
            this.f6739c = true;
            this.f6742f = exc;
        }
        this.f6738b.b(this);
    }

    public final void n(Object obj) {
        synchronized (this.f6737a) {
            t();
            this.f6739c = true;
            this.f6741e = obj;
        }
        this.f6738b.b(this);
    }

    public final boolean o() {
        synchronized (this.f6737a) {
            if (this.f6739c) {
                return false;
            }
            this.f6739c = true;
            this.f6740d = true;
            this.f6738b.b(this);
            return true;
        }
    }

    public final boolean p(Exception exc) {
        k3.q.h(exc, "Exception must not be null");
        synchronized (this.f6737a) {
            if (this.f6739c) {
                return false;
            }
            this.f6739c = true;
            this.f6742f = exc;
            this.f6738b.b(this);
            return true;
        }
    }

    public final boolean q(Object obj) {
        synchronized (this.f6737a) {
            if (this.f6739c) {
                return false;
            }
            this.f6739c = true;
            this.f6741e = obj;
            this.f6738b.b(this);
            return true;
        }
    }
}
