package m6;

import java.util.Objects;
import m6.t0;
/* loaded from: classes.dex */
public class n6 implements t0.w1 {

    /* renamed from: a  reason: collision with root package name */
    private final i6.b f9388a;

    /* renamed from: b  reason: collision with root package name */
    private final v4 f9389b;

    public n6(i6.b bVar, v4 v4Var) {
        this.f9388a = bVar;
        this.f9389b = v4Var;
    }

    private r0.q1 n(Long l8) {
        r0.q1 q1Var = (r0.q1) this.f9389b.h(l8.longValue());
        Objects.requireNonNull(q1Var);
        return q1Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void o(Void r02) {
    }

    @Override // m6.t0.w1
    public void b(Long l8, Long l9) {
        n(l8).X0(l9.intValue());
    }

    @Override // m6.t0.w1
    public Long c(Long l8) {
        Long g8 = this.f9389b.g((r0.q0) n(l8).F0());
        Objects.requireNonNull(g8);
        return g8;
    }

    @Override // m6.t0.w1
    public Long k(Long l8) {
        Object h8 = this.f9389b.h(l8.longValue());
        Objects.requireNonNull(h8);
        r0.q1 g12 = r0.q1.g1((r0.q0) h8);
        m(this.f9388a, this.f9389b).e(g12, new t0.v1.a() { // from class: m6.m6
            @Override // m6.t0.v1.a
            public final void a(Object obj) {
                n6.o((Void) obj);
            }
        });
        Long g8 = this.f9389b.g(g12);
        Objects.requireNonNull(g8);
        return g8;
    }

    public l6 m(i6.b bVar, v4 v4Var) {
        return new l6(bVar, v4Var);
    }
}
