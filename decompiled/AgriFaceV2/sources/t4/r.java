package t4;

import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes.dex */
public final class r {

    /* renamed from: b  reason: collision with root package name */
    private static r f11879b = new r();

    /* renamed from: a  reason: collision with root package name */
    private final AtomicReference f11880a = new AtomicReference(b0.d().d());

    r() {
    }

    public static r b() {
        return f11879b;
    }

    public Object a(j4.h hVar, Class cls) {
        return ((b0) this.f11880a.get()).f(hVar, cls);
    }

    public synchronized void c(z zVar) {
        this.f11880a.set(b0.e((b0) this.f11880a.get()).e(zVar).d());
    }

    public synchronized void d(c0 c0Var) {
        this.f11880a.set(b0.e((b0) this.f11880a.get()).f(c0Var).d());
    }

    public Object e(j4.o oVar, Class cls) {
        return ((b0) this.f11880a.get()).i(oVar, cls);
    }
}
