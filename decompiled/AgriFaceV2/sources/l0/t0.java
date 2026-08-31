package l0;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import y.f1;
import y.g2;
import y.v1;
/* loaded from: classes.dex */
public class t0 {

    /* renamed from: a  reason: collision with root package name */
    final p0 f8937a;

    /* renamed from: b  reason: collision with root package name */
    final b0.h0 f8938b;

    /* renamed from: c  reason: collision with root package name */
    private c f8939c;

    /* renamed from: d  reason: collision with root package name */
    private b f8940d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements f0.c {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ l0 f8941a;

        a(l0 l0Var) {
            this.f8941a = l0Var;
        }

        @Override // f0.c
        public void b(Throwable th) {
            if (this.f8941a.t() == 2 && (th instanceof CancellationException)) {
                f1.a("SurfaceProcessorNode", "Downstream VideoCapture failed to provide Surface.");
                return;
            }
            f1.m("SurfaceProcessorNode", "Downstream node failed to provide Surface. Target: " + u0.a(this.f8941a.t()), th);
        }

        @Override // f0.c
        /* renamed from: c */
        public void a(v1 v1Var) {
            g1.e.j(v1Var);
            t0.this.f8937a.a(v1Var);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class b {
        public static b c(l0 l0Var, List list) {
            return new l0.c(l0Var, list);
        }

        public abstract List a();

        public abstract l0 b();
    }

    /* loaded from: classes.dex */
    public static class c extends HashMap {
    }

    public t0(b0.h0 h0Var, p0 p0Var) {
        this.f8938b = h0Var;
        this.f8937a = p0Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void g(l0 l0Var, Map.Entry entry) {
        l0 l0Var2 = (l0) entry.getValue();
        f0.n.j(l0Var2.j(((n0.f) entry.getKey()).b(), v1.a.f(l0Var.s().e(), ((n0.f) entry.getKey()).a(), l0Var.u() ? this.f8938b : null, ((n0.f) entry.getKey()).c(), ((n0.f) entry.getKey()).g()), null), new a(l0Var2), e0.c.e());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f() {
        c cVar = this.f8939c;
        if (cVar != null) {
            for (V v7 : cVar.values()) {
                v7.i();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void h(Map map, g2.h hVar) {
        for (Map.Entry entry : map.entrySet()) {
            int b8 = hVar.b() - ((n0.f) entry.getKey()).c();
            if (((n0.f) entry.getKey()).g()) {
                b8 = -b8;
            }
            ((l0) entry.getValue()).D(d0.r.u(b8), -1);
        }
    }

    private void j(final l0 l0Var, Map map) {
        for (final Map.Entry entry : map.entrySet()) {
            g(l0Var, entry);
            ((l0) entry.getValue()).e(new Runnable() { // from class: l0.r0
                @Override // java.lang.Runnable
                public final void run() {
                    t0.this.g(l0Var, entry);
                }
            });
        }
    }

    private void k(l0 l0Var) {
        this.f8937a.b(l0Var.k(this.f8938b));
    }

    private l0 n(l0 l0Var, n0.f fVar) {
        Rect p8;
        Rect a8 = fVar.a();
        int c8 = fVar.c();
        boolean g8 = fVar.g();
        Matrix matrix = new Matrix(l0Var.r());
        Matrix d8 = d0.r.d(new RectF(a8), d0.r.r(fVar.d()), c8, g8);
        matrix.postConcat(d8);
        g1.e.a(d0.r.i(d0.r.e(a8, c8), fVar.d()));
        if (fVar.k()) {
            g1.e.b(fVar.a().contains(l0Var.n()), String.format("Output crop rect %s must contain input crop rect %s", fVar.a(), l0Var.n()));
            p8 = new Rect();
            RectF rectF = new RectF(l0Var.n());
            d8.mapRect(rectF);
            rectF.round(p8);
        } else {
            p8 = d0.r.p(fVar.d());
        }
        Rect rect = p8;
        return new l0(fVar.e(), fVar.b(), l0Var.s().g().e(fVar.d()).a(), matrix, false, rect, l0Var.q() - c8, -1, l0Var.w() != g8);
    }

    public p0 e() {
        return this.f8937a;
    }

    public void i() {
        this.f8937a.release();
        d0.q.d(new Runnable() { // from class: l0.s0
            @Override // java.lang.Runnable
            public final void run() {
                t0.this.f();
            }
        });
    }

    void l(l0 l0Var, final Map map) {
        l0Var.f(new g1.a() { // from class: l0.q0
            @Override // g1.a
            public final void accept(Object obj) {
                t0.h(map, (g2.h) obj);
            }
        });
    }

    public c m(b bVar) {
        d0.q.a();
        this.f8940d = bVar;
        this.f8939c = new c();
        l0 b8 = bVar.b();
        for (n0.f fVar : bVar.a()) {
            this.f8939c.put(fVar, n(b8, fVar));
        }
        k(b8);
        j(b8, this.f8939c);
        l(b8, this.f8939c);
        return this.f8939c;
    }
}
