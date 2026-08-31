package t4;

import java.util.concurrent.atomic.AtomicReference;
import t4.g;
import t4.i0;
import t4.j0;
/* loaded from: classes.dex */
public final class u {

    /* renamed from: b  reason: collision with root package name */
    private static final u f11881b = (u) j0.a(new j0.a() { // from class: t4.s
        @Override // t4.j0.a
        public final Object get() {
            u b8;
            b8 = u.b();
            return b8;
        }
    });

    /* renamed from: a  reason: collision with root package name */
    private final AtomicReference f11882a = new AtomicReference(new i0.b().e());

    /* JADX INFO: Access modifiers changed from: private */
    public static u b() {
        u uVar = new u();
        uVar.k(g.a(new g.b() { // from class: t4.t
            @Override // t4.g.b
            public final h0 a(j4.h hVar, j4.y yVar) {
                return ((i) hVar).e(yVar);
            }
        }, i.class, d0.class));
        return uVar;
    }

    public static u c() {
        return f11881b;
    }

    public boolean d(h0 h0Var) {
        return ((i0) this.f11882a.get()).e(h0Var);
    }

    public boolean e(h0 h0Var) {
        return ((i0) this.f11882a.get()).f(h0Var);
    }

    public j4.h f(h0 h0Var, j4.y yVar) {
        return ((i0) this.f11882a.get()).g(h0Var, yVar);
    }

    public j4.h g(d0 d0Var, j4.y yVar) {
        return !d(d0Var) ? new i(d0Var, yVar) : f(d0Var, yVar);
    }

    public j4.w h(h0 h0Var) {
        return ((i0) this.f11882a.get()).h(h0Var);
    }

    public j4.w i(e0 e0Var) {
        return !e(e0Var) ? new j(e0Var) : h(e0Var);
    }

    public synchronized void j(f fVar) {
        this.f11882a.set(new i0.b((i0) this.f11882a.get()).f(fVar).e());
    }

    public synchronized void k(g gVar) {
        this.f11882a.set(new i0.b((i0) this.f11882a.get()).g(gVar).e());
    }

    public synchronized void l(w wVar) {
        this.f11882a.set(new i0.b((i0) this.f11882a.get()).h(wVar).e());
    }

    public synchronized void m(x xVar) {
        this.f11882a.set(new i0.b((i0) this.f11882a.get()).i(xVar).e());
    }

    public h0 n(j4.h hVar, Class cls, j4.y yVar) {
        return ((i0) this.f11882a.get()).i(hVar, cls, yVar);
    }

    public h0 o(j4.w wVar, Class cls) {
        return ((i0) this.f11882a.get()).j(wVar, cls);
    }
}
