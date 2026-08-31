package androidx.camera.core;

import androidx.camera.core.e;
import androidx.camera.core.k;
import b0.q1;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class k extends i {

    /* renamed from: t  reason: collision with root package name */
    final Executor f2737t;

    /* renamed from: u  reason: collision with root package name */
    private final Object f2738u = new Object();

    /* renamed from: v  reason: collision with root package name */
    n f2739v;

    /* renamed from: w  reason: collision with root package name */
    private b f2740w;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements f0.c {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ b f2741a;

        a(b bVar) {
            this.f2741a = bVar;
        }

        @Override // f0.c
        public void b(Throwable th) {
            this.f2741a.close();
        }

        @Override // f0.c
        /* renamed from: c */
        public void a(Void r12) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b extends e {

        /* renamed from: h  reason: collision with root package name */
        final WeakReference f2743h;

        b(n nVar, k kVar) {
            super(nVar);
            this.f2743h = new WeakReference(kVar);
            b(new e.a() { // from class: androidx.camera.core.l
                @Override // androidx.camera.core.e.a
                public final void b(n nVar2) {
                    k.b.this.j(nVar2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void j(n nVar) {
            final k kVar = (k) this.f2743h.get();
            if (kVar != null) {
                kVar.f2737t.execute(new Runnable() { // from class: androidx.camera.core.m
                    @Override // java.lang.Runnable
                    public final void run() {
                        k.this.z();
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public k(Executor executor) {
        this.f2737t = executor;
    }

    @Override // androidx.camera.core.i
    n d(q1 q1Var) {
        return q1Var.acquireLatestImage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.camera.core.i
    public void g() {
        synchronized (this.f2738u) {
            n nVar = this.f2739v;
            if (nVar != null) {
                nVar.close();
                this.f2739v = null;
            }
        }
    }

    @Override // androidx.camera.core.i
    void o(n nVar) {
        synchronized (this.f2738u) {
            if (!this.f2725s) {
                nVar.close();
            } else if (this.f2740w == null) {
                b bVar = new b(nVar, this);
                this.f2740w = bVar;
                f0.n.j(e(bVar), new a(bVar), e0.c.b());
            } else {
                if (nVar.p().c() <= this.f2740w.p().c()) {
                    nVar.close();
                } else {
                    n nVar2 = this.f2739v;
                    if (nVar2 != null) {
                        nVar2.close();
                    }
                    this.f2739v = nVar;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void z() {
        synchronized (this.f2738u) {
            this.f2740w = null;
            n nVar = this.f2739v;
            if (nVar != null) {
                this.f2739v = null;
                o(nVar);
            }
        }
    }
}
