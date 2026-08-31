package m6;

import android.app.Activity;
import android.content.Context;
import e6.a;
import io.flutter.view.TextureRegistry;
import m6.t0;
import m6.v4;
/* loaded from: classes.dex */
public final class n implements e6.a, f6.a {

    /* renamed from: a  reason: collision with root package name */
    private v4 f9369a;

    /* renamed from: b  reason: collision with root package name */
    private a.b f9370b;

    /* renamed from: c  reason: collision with root package name */
    public o5 f9371c;

    /* renamed from: d  reason: collision with root package name */
    public c6 f9372d;

    /* renamed from: e  reason: collision with root package name */
    public n6 f9373e;

    /* renamed from: f  reason: collision with root package name */
    public p4 f9374f;

    /* renamed from: g  reason: collision with root package name */
    public q4 f9375g;

    /* renamed from: h  reason: collision with root package name */
    public p f9376h;

    /* renamed from: i  reason: collision with root package name */
    public k6 f9377i;

    /* renamed from: j  reason: collision with root package name */
    public c5 f9378j;

    /* renamed from: k  reason: collision with root package name */
    public f f9379k;

    /* renamed from: l  reason: collision with root package name */
    public m0 f9380l;

    /* renamed from: m  reason: collision with root package name */
    public y5 f9381m;

    /* renamed from: n  reason: collision with root package name */
    public a5 f9382n;

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void d(Void r02) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void e(i6.b bVar, long j8) {
        new t0.m0(bVar).b(Long.valueOf(j8), new t0.m0.a() { // from class: m6.m
            @Override // m6.t0.m0.a
            public final void a(Object obj) {
                n.d((Void) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f() {
        this.f9369a.d();
    }

    public void g(final i6.b bVar, Context context, TextureRegistry textureRegistry) {
        this.f9369a = v4.f(new v4.a() { // from class: m6.k
            @Override // m6.v4.a
            public final void a(long j8) {
                n.e(i6.b.this, j8);
            }
        });
        t0.l0.b(bVar, new t0.l0() { // from class: m6.l
            @Override // m6.t0.l0
            public final void clear() {
                n.this.f();
            }
        });
        t0.j.e(bVar, new u(bVar, this.f9369a));
        t0.l.h(bVar, new z(bVar, this.f9369a));
        t0.n.d(bVar, new c0(bVar, this.f9369a));
        t0.n0.d(bVar, new w4(this.f9369a));
        y5 y5Var = new y5(bVar, this.f9369a, context);
        this.f9381m = y5Var;
        t0.f1.t(bVar, y5Var);
        k6 k6Var = new k6(bVar, this.f9369a, context);
        this.f9377i = k6Var;
        t0.t1.g(bVar, k6Var);
        m0 m0Var = new m0(bVar, this.f9369a);
        this.f9380l = m0Var;
        t0.x.f(bVar, m0Var);
        t0.c1.i(bVar, new s5(bVar, this.f9369a, textureRegistry));
        q4 q4Var = new q4(bVar, this.f9369a, context);
        this.f9375g = q4Var;
        t0.i0.k(bVar, q4Var);
        t0.j.e(bVar, new u(bVar, this.f9369a));
        a5 a5Var = new a5(bVar, this.f9369a);
        this.f9382n = a5Var;
        t0.q0.i(bVar, a5Var);
        t0.x0.d(bVar, new h5(bVar, this.f9369a));
        p4 p4Var = new p4(bVar, this.f9369a, context);
        this.f9374f = p4Var;
        t0.h0.h(bVar, p4Var);
        t0.b.d(bVar, new d(bVar, this.f9369a));
        t0.k0.c(bVar, new t4(bVar, this.f9369a));
        t0.k1.l(bVar, new e6(bVar, this.f9369a));
        c6 c6Var = new c6(bVar, this.f9369a, context);
        this.f9372d = c6Var;
        t0.i1.g(bVar, c6Var);
        o5 o5Var = new o5(bVar, this.f9369a, context);
        this.f9371c = o5Var;
        t0.a1.c(bVar, o5Var);
        n6 n6Var = new n6(bVar, this.f9369a);
        this.f9373e = n6Var;
        t0.w1.j(bVar, n6Var);
        t0.o1.e(bVar, new g6(this.f9369a));
        t0.p1.d(bVar, new h6(this.f9369a));
        t0.c.d(bVar, new e(this.f9369a));
        t0.b0.e(bVar, new o0(this.f9369a));
        t0.g1.g(bVar, new a6(this.f9369a));
        p pVar = new p(bVar, this.f9369a, context);
        this.f9376h = pVar;
        t0.h.c(bVar, pVar);
        f fVar = new f(this.f9369a, context);
        this.f9379k = fVar;
        t0.d.f(bVar, fVar);
        t0.u.d(bVar, new h0(this.f9369a));
        t0.d0.c(bVar, new p0(this.f9369a));
        t0.g0.b(bVar, new r0(this.f9369a));
        c5 c5Var = new c5(this.f9369a);
        this.f9378j = c5Var;
        t0.u0.f(bVar, c5Var);
        t0.l1.d(bVar, new f6(this.f9369a));
        t0.f.n(bVar, new i(bVar, this.f9369a));
    }

    public void h(Activity activity) {
        k6 k6Var = this.f9377i;
        if (k6Var != null) {
            k6Var.n(activity);
        }
        m0 m0Var = this.f9380l;
        if (m0Var != null) {
            m0Var.t(activity);
        }
        c5 c5Var = this.f9378j;
        if (c5Var != null) {
            c5Var.i(activity);
        }
    }

    public void i(Context context) {
        y5 y5Var = this.f9381m;
        if (y5Var != null) {
            y5Var.C(context);
        }
        c6 c6Var = this.f9372d;
        if (c6Var != null) {
            c6Var.s(context);
        }
        o5 o5Var = this.f9371c;
        if (o5Var != null) {
            o5Var.s(context);
        }
        k6 k6Var = this.f9377i;
        if (k6Var != null) {
            k6Var.o(context);
        }
        q4 q4Var = this.f9375g;
        if (q4Var != null) {
            q4Var.q(context);
        }
        p4 p4Var = this.f9374f;
        if (p4Var != null) {
            p4Var.p(context);
        }
        p pVar = this.f9376h;
        if (pVar != null) {
            pVar.s(context);
        }
        f fVar = this.f9379k;
        if (fVar != null) {
            fVar.j(context);
        }
    }

    public void j(Activity activity) {
        z5 z5Var;
        y5 y5Var;
        if (activity == null) {
            y5Var = this.f9381m;
            z5Var = null;
        } else if (activity instanceof androidx.lifecycle.m) {
            androidx.lifecycle.m mVar = (androidx.lifecycle.m) activity;
            this.f9381m.D(mVar);
            this.f9382n.s(mVar);
            return;
        } else {
            z5Var = new z5(activity);
            y5Var = this.f9381m;
        }
        y5Var.D(z5Var);
        this.f9382n.s(z5Var);
    }

    @Override // f6.a
    public void onAttachedToActivity(f6.c cVar) {
        Activity activity = cVar.getActivity();
        g(this.f9370b.b(), activity, this.f9370b.e());
        j(activity);
        h(activity);
        this.f9377i.p(new j(cVar));
    }

    @Override // e6.a
    public void onAttachedToEngine(a.b bVar) {
        this.f9370b = bVar;
    }

    @Override // f6.a
    public void onDetachedFromActivity() {
        i(this.f9370b.a());
        j(null);
        h(null);
        this.f9377i.p(null);
    }

    @Override // f6.a
    public void onDetachedFromActivityForConfigChanges() {
        i(this.f9370b.a());
        j(null);
        h(null);
        this.f9377i.p(null);
    }

    @Override // e6.a
    public void onDetachedFromEngine(a.b bVar) {
        v4 v4Var = this.f9369a;
        if (v4Var != null) {
            v4Var.n();
        }
    }

    @Override // f6.a
    public void onReattachedToActivityForConfigChanges(f6.c cVar) {
        Activity activity = cVar.getActivity();
        i(activity);
        j(activity);
        h(activity);
        this.f9377i.p(new j(cVar));
    }
}
