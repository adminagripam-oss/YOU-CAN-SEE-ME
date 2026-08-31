package m6;

import android.content.Context;
import java.util.Objects;
import java.util.concurrent.Executor;
import m6.t0;
import r0.c2;
/* loaded from: classes.dex */
public class o5 implements t0.a1 {

    /* renamed from: a  reason: collision with root package name */
    private final i6.b f9398a;

    /* renamed from: b  reason: collision with root package name */
    private final v4 f9399b;

    /* renamed from: c  reason: collision with root package name */
    private Context f9400c;

    /* renamed from: d  reason: collision with root package name */
    public g0 f9401d;

    /* renamed from: e  reason: collision with root package name */
    i5 f9402e;

    /* renamed from: f  reason: collision with root package name */
    i6 f9403f;

    /* renamed from: g  reason: collision with root package name */
    d6 f9404g;

    public o5(i6.b bVar, v4 v4Var, Context context) {
        g0 g0Var = new g0();
        this.f9401d = g0Var;
        this.f9398a = bVar;
        this.f9399b = v4Var;
        this.f9400c = context;
        this.f9403f = g0Var.i(bVar);
        this.f9404g = new d6(bVar, v4Var);
        this.f9402e = new i5(bVar, v4Var);
    }

    private r0.u l(Long l8) {
        Object h8 = this.f9399b.h(l8.longValue());
        Objects.requireNonNull(h8);
        return (r0.u) h8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void n(Void r02) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void o(Void r02) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void p(Void r02) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void r(Void r02) {
    }

    @Override // m6.t0.a1
    public Long b(Long l8) {
        r0.a1 h8 = l(l8).h(k(), new g1.a() { // from class: m6.j5
            @Override // g1.a
            public final void accept(Object obj) {
                o5.this.q((r0.c2) obj);
            }
        });
        this.f9404g.e(h8, new t0.j1.a() { // from class: m6.k5
            @Override // m6.t0.j1.a
            public final void a(Object obj) {
                o5.r((Void) obj);
            }
        });
        Long g8 = this.f9399b.g(h8);
        Objects.requireNonNull(g8);
        return g8;
    }

    public Executor k() {
        Context context = this.f9400c;
        if (context != null) {
            return androidx.core.content.a.g(context);
        }
        throw new IllegalStateException("Context must be set to get an executor to start recording.");
    }

    /* renamed from: m */
    public void q(r0.c2 c2Var) {
        String str;
        if (c2Var instanceof c2.d) {
            this.f9402e.j(new t0.y0.a() { // from class: m6.l5
                @Override // m6.t0.y0.a
                public final void a(Object obj) {
                    o5.n((Void) obj);
                }
            });
        } else if (c2Var instanceof c2.a) {
            this.f9402e.i(new t0.y0.a() { // from class: m6.m5
                @Override // m6.t0.y0.a
                public final void a(Object obj) {
                    o5.o((Void) obj);
                }
            });
            c2.a aVar = (c2.a) c2Var;
            if (aVar.k()) {
                if (aVar.i() != null) {
                    str = aVar.i().toString();
                } else {
                    str = "Error code " + aVar.j() + ": An error occurred while recording video.";
                }
                this.f9403f.e(str, new t0.s1.a() { // from class: m6.n5
                    @Override // m6.t0.s1.a
                    public final void a(Object obj) {
                        o5.p((Void) obj);
                    }
                });
            }
        }
    }

    public void s(Context context) {
        this.f9400c = context;
    }
}
