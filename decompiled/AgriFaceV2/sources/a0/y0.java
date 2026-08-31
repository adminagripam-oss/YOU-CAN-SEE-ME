package a0;

import a0.e1;
import android.util.Log;
import androidx.camera.core.e;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import y.f1;
/* loaded from: classes.dex */
public class y0 implements e.a, e1.a {

    /* renamed from: b  reason: collision with root package name */
    final x f144b;

    /* renamed from: c  reason: collision with root package name */
    y f145c;

    /* renamed from: d  reason: collision with root package name */
    private s0 f146d;

    /* renamed from: e  reason: collision with root package name */
    private final List f147e;

    /* renamed from: a  reason: collision with root package name */
    final Deque f143a = new ArrayDeque();

    /* renamed from: f  reason: collision with root package name */
    boolean f148f = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements f0.c {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ l f149a;

        a(l lVar) {
            this.f149a = lVar;
        }

        @Override // f0.c
        public void b(Throwable th) {
            y yVar;
            b c8;
            if (this.f149a.b()) {
                return;
            }
            int f8 = ((b0.s0) this.f149a.a().get(0)).f();
            if (th instanceof y.v0) {
                yVar = y0.this.f145c;
                c8 = b.c(f8, (y.v0) th);
            } else {
                yVar = y0.this.f145c;
                c8 = b.c(f8, new y.v0(2, "Failed to submit capture request", th));
            }
            yVar.j(c8);
            y0.this.f144b.c();
        }

        @Override // f0.c
        /* renamed from: c */
        public void a(Void r12) {
            y0.this.f144b.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class b {
        /* JADX INFO: Access modifiers changed from: package-private */
        public static b c(int i8, y.v0 v0Var) {
            return new g(i8, v0Var);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract y.v0 a();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract int b();
    }

    public y0(x xVar) {
        d0.q.a();
        this.f144b = xVar;
        this.f147e = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h() {
        this.f146d = null;
        g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i(s0 s0Var) {
        this.f147e.remove(s0Var);
    }

    private i4.d n(l lVar) {
        d0.q.a();
        this.f144b.b();
        i4.d a8 = this.f144b.a(lVar.a());
        f0.n.j(a8, new a(lVar), e0.c.e());
        return a8;
    }

    private void o(final s0 s0Var) {
        g1.e.l(!f());
        this.f146d = s0Var;
        s0Var.p().d(new Runnable() { // from class: a0.w0
            @Override // java.lang.Runnable
            public final void run() {
                y0.this.h();
            }
        }, e0.c.b());
        this.f147e.add(s0Var);
        s0Var.q().d(new Runnable() { // from class: a0.x0
            @Override // java.lang.Runnable
            public final void run() {
                y0.this.i(s0Var);
            }
        }, e0.c.b());
    }

    @Override // a0.e1.a
    public void a(e1 e1Var) {
        d0.q.a();
        f1.a("TakePictureManager", "Add a new request for retrying.");
        this.f143a.addFirst(e1Var);
        g();
    }

    @Override // androidx.camera.core.e.a
    public void b(androidx.camera.core.n nVar) {
        e0.c.e().execute(new Runnable() { // from class: a0.v0
            @Override // java.lang.Runnable
            public final void run() {
                y0.this.g();
            }
        });
    }

    public void e() {
        d0.q.a();
        y.v0 v0Var = new y.v0(3, "Camera is closed.", null);
        for (e1 e1Var : this.f143a) {
            e1Var.x(v0Var);
        }
        this.f143a.clear();
        for (s0 s0Var : new ArrayList(this.f147e)) {
            s0Var.m(v0Var);
        }
    }

    boolean f() {
        return this.f146d != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g() {
        d0.q.a();
        Log.d("TakePictureManager", "Issue the next TakePictureRequest.");
        if (f()) {
            Log.d("TakePictureManager", "There is already a request in-flight.");
        } else if (this.f148f) {
            Log.d("TakePictureManager", "The class is paused.");
        } else if (this.f145c.h() == 0) {
            Log.d("TakePictureManager", "Too many acquire images. Close image to be able to process next.");
        } else {
            e1 e1Var = (e1) this.f143a.poll();
            if (e1Var == null) {
                Log.d("TakePictureManager", "No new request.");
                return;
            }
            s0 s0Var = new s0(e1Var, this);
            o(s0Var);
            g1.d e8 = this.f145c.e(e1Var, s0Var, s0Var.p());
            l lVar = (l) e8.f7099a;
            Objects.requireNonNull(lVar);
            p0 p0Var = (p0) e8.f7100b;
            Objects.requireNonNull(p0Var);
            this.f145c.m(p0Var);
            s0Var.v(n(lVar));
        }
    }

    public void j(e1 e1Var) {
        d0.q.a();
        this.f143a.offer(e1Var);
        g();
    }

    public void k() {
        d0.q.a();
        this.f148f = true;
        s0 s0Var = this.f146d;
        if (s0Var != null) {
            s0Var.n();
        }
    }

    public void l() {
        d0.q.a();
        this.f148f = false;
        g();
    }

    public void m(y yVar) {
        d0.q.a();
        this.f145c = yVar;
        yVar.k(this);
    }
}
