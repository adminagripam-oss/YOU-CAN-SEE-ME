package p0;

import b0.a2;
import b0.f2;
import b0.g3;
import b0.h3;
import b0.z1;
import java.util.UUID;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class i implements g3.a {

    /* renamed from: a  reason: collision with root package name */
    private final a2 f9953a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i() {
        this(a2.d0());
    }

    @Override // b0.g3.a
    /* renamed from: a */
    public j d() {
        return new j(f2.b0(this.f9953a));
    }

    public i b(h3.b bVar) {
        c().Y(g3.B, bVar);
        return this;
    }

    @Override // y.c0
    public z1 c() {
        return this.f9953a;
    }

    public i e(Class cls) {
        c().Y(g0.m.G, cls);
        if (c().f(g0.m.F, null) == null) {
            f(cls.getCanonicalName() + "-" + UUID.randomUUID());
        }
        return this;
    }

    public i f(String str) {
        c().Y(g0.m.F, str);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(a2 a2Var) {
        this.f9953a = a2Var;
        Class cls = (Class) a2Var.f(g0.m.G, null);
        if (cls == null || cls.equals(h.class)) {
            b(h3.b.STREAM_SHARING);
            e(h.class);
            return;
        }
        throw new IllegalArgumentException("Invalid target class configuration for " + this + ": " + cls);
    }
}
