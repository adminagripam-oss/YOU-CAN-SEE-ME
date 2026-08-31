package m0;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Size;
import b0.h0;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import l0.l0;
import l0.p0;
import l0.u0;
import y.f1;
import y.v1;
/* loaded from: classes.dex */
public class r {

    /* renamed from: a  reason: collision with root package name */
    final p0 f9121a;

    /* renamed from: b  reason: collision with root package name */
    final h0 f9122b;

    /* renamed from: c  reason: collision with root package name */
    final h0 f9123c;

    /* renamed from: d  reason: collision with root package name */
    private c f9124d;

    /* renamed from: e  reason: collision with root package name */
    private b f9125e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements f0.c {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ l0 f9126a;

        a(l0 l0Var) {
            this.f9126a = l0Var;
        }

        @Override // f0.c
        public void b(Throwable th) {
            if (this.f9126a.t() == 2 && (th instanceof CancellationException)) {
                f1.a("DualSurfaceProcessorNode", "Downstream VideoCapture failed to provide Surface.");
                return;
            }
            f1.m("DualSurfaceProcessorNode", "Downstream node failed to provide Surface. Target: " + u0.a(this.f9126a.t()), th);
        }

        @Override // f0.c
        /* renamed from: c */
        public void a(v1 v1Var) {
            g1.e.j(v1Var);
            r.this.f9121a.a(v1Var);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class b {
        public static b d(l0 l0Var, l0 l0Var2, List list) {
            return new m0.b(l0Var, l0Var2, list);
        }

        public abstract List a();

        public abstract l0 b();

        public abstract l0 c();
    }

    /* loaded from: classes.dex */
    public static class c extends HashMap {
    }

    public r(h0 h0Var, h0 h0Var2, p0 p0Var) {
        this.f9122b = h0Var;
        this.f9123c = h0Var2;
        this.f9121a = p0Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void e(h0 h0Var, h0 h0Var2, l0 l0Var, l0 l0Var2, Map.Entry entry) {
        l0 l0Var3 = (l0) entry.getValue();
        Size e8 = l0Var.s().e();
        Rect a8 = ((d) entry.getKey()).a().a();
        if (!l0Var.u()) {
            h0Var = null;
        }
        v1.a f8 = v1.a.f(e8, a8, h0Var, ((d) entry.getKey()).a().c(), ((d) entry.getKey()).a().g());
        Size e9 = l0Var2.s().e();
        Rect a9 = ((d) entry.getKey()).b().a();
        if (!l0Var2.u()) {
            h0Var2 = null;
        }
        f0.n.j(l0Var3.j(((d) entry.getKey()).a().b(), f8, v1.a.f(e9, a9, h0Var2, ((d) entry.getKey()).b().c(), ((d) entry.getKey()).b().g())), new a(l0Var3), e0.c.e());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d() {
        c cVar = this.f9124d;
        if (cVar != null) {
            for (V v7 : cVar.values()) {
                v7.i();
            }
        }
    }

    private void g(final h0 h0Var, final h0 h0Var2, final l0 l0Var, final l0 l0Var2, Map map) {
        for (final Map.Entry entry : map.entrySet()) {
            e(h0Var, h0Var2, l0Var, l0Var2, entry);
            ((l0) entry.getValue()).e(new Runnable() { // from class: m0.q
                @Override // java.lang.Runnable
                public final void run() {
                    r.this.e(h0Var, h0Var2, l0Var, l0Var2, entry);
                }
            });
        }
    }

    private void h(h0 h0Var, l0 l0Var, Map map, boolean z7) {
        this.f9121a.b(l0Var.l(h0Var, z7));
    }

    private l0 j(l0 l0Var, n0.f fVar) {
        Rect a8 = fVar.a();
        int c8 = fVar.c();
        boolean g8 = fVar.g();
        Matrix matrix = new Matrix();
        g1.e.a(d0.r.i(d0.r.e(a8, c8), fVar.d()));
        Rect p8 = d0.r.p(fVar.d());
        return new l0(fVar.e(), fVar.b(), l0Var.s().g().e(fVar.d()).a(), matrix, false, p8, l0Var.q() - c8, -1, l0Var.w() != g8);
    }

    public void f() {
        this.f9121a.release();
        d0.q.d(new Runnable() { // from class: m0.p
            @Override // java.lang.Runnable
            public final void run() {
                r.this.d();
            }
        });
    }

    public c i(b bVar) {
        d0.q.a();
        this.f9125e = bVar;
        this.f9124d = new c();
        l0 b8 = this.f9125e.b();
        l0 c8 = this.f9125e.c();
        for (d dVar : this.f9125e.a()) {
            this.f9124d.put(dVar, j(b8, dVar.a()));
        }
        h(this.f9122b, b8, this.f9124d, true);
        h(this.f9123c, c8, this.f9124d, false);
        g(this.f9122b, this.f9123c, b8, c8, this.f9124d);
        return this.f9124d;
    }
}
