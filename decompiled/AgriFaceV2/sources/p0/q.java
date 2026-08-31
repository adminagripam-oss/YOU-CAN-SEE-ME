package p0;

import b0.d0;
import b0.j1;
import b0.s0;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import p0.h;
/* loaded from: classes.dex */
public class q extends j1 {

    /* renamed from: c  reason: collision with root package name */
    private final h.a f9978c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public q(d0 d0Var, h.a aVar) {
        super(d0Var);
        this.f9978c = aVar;
    }

    private int u(s0 s0Var) {
        Integer num = (Integer) s0Var.g().f(s0.f4372j, 100);
        Objects.requireNonNull(num);
        return num.intValue();
    }

    private int v(s0 s0Var) {
        Integer num = (Integer) s0Var.g().f(s0.f4371i, 0);
        Objects.requireNonNull(num);
        return num.intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ i4.d w(i4.d dVar, a0.k kVar) {
        return ((a0.k) dVar.get()).b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ i4.d x(List list, Void r42) {
        return this.f9978c.a(u((s0) list.get(0)), v((s0) list.get(0)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ i4.d y(i4.d dVar, Void r12) {
        return ((a0.k) dVar.get()).a();
    }

    @Override // b0.j1, b0.d0
    public i4.d h(final List list, int i8, int i9) {
        g1.e.b(list.size() == 1, "Only support one capture config.");
        final i4.d d8 = d(i8, i9);
        return f0.n.k(Collections.singletonList(f0.d.a(d8).f(new f0.a() { // from class: p0.n
            @Override // f0.a
            public final i4.d apply(Object obj) {
                i4.d w7;
                w7 = q.w(i4.d.this, (a0.k) obj);
                return w7;
            }
        }, e0.c.b()).f(new f0.a() { // from class: p0.o
            @Override // f0.a
            public final i4.d apply(Object obj) {
                i4.d x7;
                x7 = q.this.x(list, (Void) obj);
                return x7;
            }
        }, e0.c.b()).f(new f0.a() { // from class: p0.p
            @Override // f0.a
            public final i4.d apply(Object obj) {
                i4.d y7;
                y7 = q.y(i4.d.this, (Void) obj);
                return y7;
            }
        }, e0.c.b())));
    }
}
