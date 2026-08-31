package k7;

import java.util.concurrent.CancellationException;
import r6.k;
/* loaded from: classes.dex */
public abstract class l0 extends r7.h {

    /* renamed from: g  reason: collision with root package name */
    public int f8748g;

    public l0(int i8) {
        this.f8748g = i8;
    }

    public abstract void b(Object obj, Throwable th);

    public abstract t6.d c();

    public Throwable d(Object obj) {
        u uVar = obj instanceof u ? (u) obj : null;
        if (uVar != null) {
            return uVar.f8789a;
        }
        return null;
    }

    public Object f(Object obj) {
        return obj;
    }

    public final void g(Throwable th, Throwable th2) {
        if (th == null && th2 == null) {
            return;
        }
        if (th != null && th2 != null) {
            r6.b.a(th, th2);
        }
        if (th == null) {
            th = th2;
        }
        d7.k.b(th);
        c0.a(c().getContext(), new g0("Fatal exception in coroutines machinery for " + this + ". Please read KDoc to 'handleFatalException' method and report this incident to maintainers", th));
    }

    public abstract Object h();

    @Override // java.lang.Runnable
    public final void run() {
        Object a8;
        Object a9;
        Object a10;
        r7.i iVar = this.f11411f;
        try {
            t6.d c8 = c();
            d7.k.c(c8, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T of kotlinx.coroutines.DispatchedTask>");
            p7.j jVar = (p7.j) c8;
            t6.d dVar = jVar.f10153i;
            Object obj = jVar.f10155k;
            t6.g context = dVar.getContext();
            Object c9 = p7.l0.c(context, obj);
            if (c9 != p7.l0.f10160a) {
                z.g(dVar, context, c9);
            }
            t6.g context2 = dVar.getContext();
            Object h8 = h();
            Throwable d8 = d(h8);
            e1 e1Var = (d8 == null && m0.b(this.f8748g)) ? (e1) context2.c(e1.f8727b) : null;
            if (e1Var != null && !e1Var.b()) {
                CancellationException E = e1Var.E();
                b(h8, E);
                k.a aVar = r6.k.f11363e;
                a9 = r6.k.a(r6.l.a(E));
            } else if (d8 != null) {
                k.a aVar2 = r6.k.f11363e;
                a9 = r6.k.a(r6.l.a(d8));
            } else {
                k.a aVar3 = r6.k.f11363e;
                a9 = r6.k.a(f(h8));
            }
            dVar.i(a9);
            r6.r rVar = r6.r.f11372a;
            p7.l0.a(context, c9);
            try {
                iVar.a();
                a10 = r6.k.a(r6.r.f11372a);
            } catch (Throwable th) {
                k.a aVar4 = r6.k.f11363e;
                a10 = r6.k.a(r6.l.a(th));
            }
            g(null, r6.k.b(a10));
        } catch (Throwable th2) {
            try {
                k.a aVar5 = r6.k.f11363e;
                iVar.a();
                a8 = r6.k.a(r6.r.f11372a);
            } catch (Throwable th3) {
                k.a aVar6 = r6.k.f11363e;
                a8 = r6.k.a(r6.l.a(th3));
            }
            g(th2, r6.k.b(a8));
        }
    }
}
