package q7;

import c7.p;
import d7.v;
import k7.m1;
import k7.u;
import p7.d0;
import p7.l0;
import r6.k;
import r6.l;
import t6.d;
import t6.g;
import v6.h;
/* loaded from: classes.dex */
public abstract class b {
    public static final void a(p pVar, Object obj, d dVar) {
        Object a8;
        Object c8;
        d a9 = h.a(dVar);
        try {
            g context = dVar.getContext();
            Object c9 = l0.c(context, null);
            a8 = ((p) v.a(pVar, 2)).f(obj, a9);
            l0.a(context, c9);
            c8 = u6.d.c();
            if (a8 == c8) {
                return;
            }
        } catch (Throwable th) {
            k.a aVar = k.f11363e;
            a8 = l.a(th);
        }
        a9.i(k.a(a8));
    }

    public static final Object b(d0 d0Var, Object obj, p pVar) {
        Object uVar;
        Object c8;
        Object g02;
        Object c9;
        try {
            uVar = ((p) v.a(pVar, 2)).f(obj, d0Var);
        } catch (Throwable th) {
            uVar = new u(th, false, 2, null);
        }
        c8 = u6.d.c();
        if (uVar == c8 || (g02 = d0Var.g0(uVar)) == m1.f8762b) {
            c9 = u6.d.c();
            return c9;
        } else if (g02 instanceof u) {
            throw ((u) g02).f8789a;
        } else {
            return m1.h(g02);
        }
    }
}
