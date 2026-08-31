package m6;

import java.util.Objects;
import m6.t0;
/* loaded from: classes.dex */
public class u implements t0.j {

    /* renamed from: a  reason: collision with root package name */
    private final i6.b f9584a;

    /* renamed from: b  reason: collision with root package name */
    private final v4 f9585b;

    public u(i6.b bVar, v4 v4Var) {
        this.f9584a = bVar;
        this.f9585b = v4Var;
    }

    private y.j k(Long l8) {
        Object h8 = this.f9585b.h(l8.longValue());
        Objects.requireNonNull(h8);
        return (y.j) h8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void l(Void r02) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void m(Void r02) {
    }

    @Override // m6.t0.j
    public Long c(Long l8) {
        y.k a8 = k(l8).a();
        new o(this.f9584a, this.f9585b).e(a8, new t0.g.a() { // from class: m6.t
            @Override // m6.t0.g.a
            public final void a(Object obj) {
                u.l((Void) obj);
            }
        });
        return this.f9585b.g(a8);
    }

    @Override // m6.t0.j
    public Long d(Long l8) {
        y.p b8 = k(l8).b();
        new v(this.f9584a, this.f9585b).e(b8, new t0.k.a() { // from class: m6.s
            @Override // m6.t0.k.a
            public final void a(Object obj) {
                u.m((Void) obj);
            }
        });
        return this.f9585b.g(b8);
    }
}
