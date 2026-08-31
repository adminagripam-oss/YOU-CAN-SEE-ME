package m6;

import java.util.Objects;
import m6.t0;
/* loaded from: classes.dex */
public class e6 implements t0.k1 {

    /* renamed from: a  reason: collision with root package name */
    private final i6.b f9273a;

    /* renamed from: b  reason: collision with root package name */
    private final v4 f9274b;

    public e6(i6.b bVar, v4 v4Var) {
        this.f9273a = bVar;
        this.f9274b = v4Var;
    }

    private r0.a1 o(Long l8) {
        Object h8 = this.f9274b.h(l8.longValue());
        Objects.requireNonNull(h8);
        return (r0.a1) h8;
    }

    @Override // m6.t0.k1
    public void b(Long l8) {
        o(l8).close();
    }

    @Override // m6.t0.k1
    public void c(Long l8) {
        o(l8).n();
    }

    @Override // m6.t0.k1
    public void g(Long l8) {
        o(l8).g();
    }

    @Override // m6.t0.k1
    public void j(Long l8) {
        o(l8).j();
    }
}
