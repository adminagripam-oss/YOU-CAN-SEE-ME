package k7;

import r6.k;
/* loaded from: classes.dex */
public abstract class m0 {
    public static final void a(l0 l0Var, int i8) {
        t6.d c8 = l0Var.c();
        boolean z7 = i8 == 4;
        if (z7 || !(c8 instanceof p7.j) || b(i8) != b(l0Var.f8748g)) {
            d(l0Var, c8, z7);
            return;
        }
        a0 a0Var = ((p7.j) c8).f10152h;
        t6.g context = c8.getContext();
        if (a0Var.Q(context)) {
            a0Var.P(context, l0Var);
        } else {
            e(l0Var);
        }
    }

    public static final boolean b(int i8) {
        return i8 == 1 || i8 == 2;
    }

    public static final boolean c(int i8) {
        return i8 == 2;
    }

    public static final void d(l0 l0Var, t6.d dVar, boolean z7) {
        Object f8;
        Object h8 = l0Var.h();
        Throwable d8 = l0Var.d(h8);
        if (d8 != null) {
            k.a aVar = r6.k.f11363e;
            f8 = r6.l.a(d8);
        } else {
            k.a aVar2 = r6.k.f11363e;
            f8 = l0Var.f(h8);
        }
        Object a8 = r6.k.a(f8);
        if (!z7) {
            dVar.i(a8);
            return;
        }
        d7.k.c(dVar, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T of kotlinx.coroutines.DispatchedTaskKt.resume>");
        p7.j jVar = (p7.j) dVar;
        t6.d dVar2 = jVar.f10153i;
        Object obj = jVar.f10155k;
        t6.g context = dVar2.getContext();
        Object c8 = p7.l0.c(context, obj);
        if (c8 != p7.l0.f10160a) {
            z.g(dVar2, context, c8);
        }
        try {
            jVar.f10153i.i(a8);
            r6.r rVar = r6.r.f11372a;
        } finally {
            p7.l0.a(context, c8);
        }
    }

    private static final void e(l0 l0Var) {
        q0 a8 = v1.f8794a.a();
        if (a8.Y()) {
            a8.U(l0Var);
            return;
        }
        a8.W(true);
        try {
            d(l0Var, l0Var.c(), true);
            do {
            } while (a8.a0());
        } finally {
            try {
            } finally {
            }
        }
    }
}
