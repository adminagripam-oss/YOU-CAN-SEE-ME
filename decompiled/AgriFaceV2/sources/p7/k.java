package p7;

import java.util.concurrent.CancellationException;
import k7.e1;
import k7.v1;
import r6.k;
/* loaded from: classes.dex */
public abstract class k {

    /* renamed from: a */
    private static final h0 f10157a = new h0("UNDEFINED");

    /* renamed from: b */
    public static final h0 f10158b = new h0("REUSABLE_CLAIMED");

    public static final void b(t6.d dVar, Object obj, c7.l lVar) {
        boolean z7;
        if (!(dVar instanceof j)) {
            dVar.i(obj);
            return;
        }
        j jVar = (j) dVar;
        Object b8 = k7.y.b(obj, lVar);
        if (jVar.f10152h.Q(jVar.getContext())) {
            jVar.f10154j = b8;
            jVar.f8748g = 1;
            jVar.f10152h.P(jVar.getContext(), jVar);
            return;
        }
        k7.q0 a8 = v1.f8794a.a();
        if (a8.Y()) {
            jVar.f10154j = b8;
            jVar.f8748g = 1;
            a8.U(jVar);
            return;
        }
        a8.W(true);
        try {
            e1 e1Var = (e1) jVar.getContext().c(e1.f8727b);
            if (e1Var == null || e1Var.b()) {
                z7 = false;
            } else {
                CancellationException E = e1Var.E();
                jVar.b(b8, E);
                k.a aVar = r6.k.f11363e;
                jVar.i(r6.k.a(r6.l.a(E)));
                z7 = true;
            }
            if (!z7) {
                t6.d dVar2 = jVar.f10153i;
                Object obj2 = jVar.f10155k;
                t6.g context = dVar2.getContext();
                Object c8 = l0.c(context, obj2);
                if (c8 != l0.f10160a) {
                    k7.z.g(dVar2, context, c8);
                }
                jVar.f10153i.i(obj);
                r6.r rVar = r6.r.f11372a;
                l0.a(context, c8);
            }
            do {
            } while (a8.a0());
        } finally {
            try {
            } finally {
            }
        }
    }

    public static /* synthetic */ void c(t6.d dVar, Object obj, c7.l lVar, int i8, Object obj2) {
        if ((i8 & 2) != 0) {
            lVar = null;
        }
        b(dVar, obj, lVar);
    }
}
