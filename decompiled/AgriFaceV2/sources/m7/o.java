package m7;

import k7.z1;
import m7.h;
import p7.h0;
import p7.q0;
import p7.z;
/* loaded from: classes.dex */
public class o extends b {

    /* renamed from: q  reason: collision with root package name */
    private final int f9689q;

    /* renamed from: r  reason: collision with root package name */
    private final a f9690r;

    public o(int i8, a aVar, c7.l lVar) {
        super(i8, lVar);
        this.f9689q = i8;
        this.f9690r = aVar;
        if (!(aVar != a.SUSPEND)) {
            throw new IllegalArgumentException(("This implementation does not support suspension for senders, use " + d7.s.b(b.class).c() + " instead").toString());
        }
        if (i8 >= 1) {
            return;
        }
        throw new IllegalArgumentException(("Buffered channel capacity must be at least 1, but " + i8 + " was specified").toString());
    }

    static /* synthetic */ Object B0(o oVar, Object obj, t6.d dVar) {
        q0 d8;
        Object E0 = oVar.E0(obj, true);
        if (E0 instanceof h.a) {
            h.c(E0);
            c7.l lVar = oVar.f9652f;
            if (lVar == null || (d8 = z.d(lVar, obj, null, 2, null)) == null) {
                throw oVar.K();
            }
            r6.b.a(d8, oVar.K());
            throw d8;
        }
        return r6.r.f11372a;
    }

    private final Object C0(Object obj, boolean z7) {
        c7.l lVar;
        q0 d8;
        Object l8 = super.l(obj);
        if (h.e(l8) || h.d(l8)) {
            return l8;
        }
        if (!z7 || (lVar = this.f9652f) == null || (d8 = z.d(lVar, obj, null, 2, null)) == null) {
            return h.f9684a.c(r6.r.f11372a);
        }
        throw d8;
    }

    private final Object D0(Object obj) {
        j jVar;
        h0 h0Var = c.f9663d;
        j jVar2 = (j) b.f9646l.get(this);
        while (true) {
            long andIncrement = b.f9642h.getAndIncrement(this);
            long j8 = andIncrement & 1152921504606846975L;
            boolean U = U(andIncrement);
            int i8 = c.f9661b;
            long j9 = j8 / i8;
            int i9 = (int) (j8 % i8);
            if (jVar2.f10143g != j9) {
                j F = F(j9, jVar2);
                if (F != null) {
                    jVar = F;
                } else if (U) {
                    return h.f9684a.a(K());
                }
            } else {
                jVar = jVar2;
            }
            int w02 = w0(jVar, i9, obj, j8, h0Var, U);
            if (w02 == 0) {
                jVar.b();
                return h.f9684a.c(r6.r.f11372a);
            } else if (w02 == 1) {
                return h.f9684a.c(r6.r.f11372a);
            } else {
                if (w02 == 2) {
                    if (U) {
                        jVar.p();
                        return h.f9684a.a(K());
                    }
                    z1 z1Var = h0Var instanceof z1 ? (z1) h0Var : null;
                    if (z1Var != null) {
                        i0(z1Var, jVar, i9);
                    }
                    B((jVar.f10143g * i8) + i9);
                    return h.f9684a.c(r6.r.f11372a);
                } else if (w02 == 3) {
                    throw new IllegalStateException("unexpected".toString());
                } else {
                    if (w02 == 4) {
                        if (j8 < J()) {
                            jVar.b();
                        }
                        return h.f9684a.a(K());
                    }
                    if (w02 == 5) {
                        jVar.b();
                    }
                    jVar2 = jVar;
                }
            }
        }
    }

    private final Object E0(Object obj, boolean z7) {
        return this.f9690r == a.DROP_LATEST ? C0(obj, z7) : D0(obj);
    }

    @Override // m7.b
    protected boolean V() {
        return this.f9690r == a.DROP_OLDEST;
    }

    @Override // m7.b, m7.t
    public Object l(Object obj) {
        return E0(obj, false);
    }

    @Override // m7.b, m7.t
    public Object m(Object obj, t6.d dVar) {
        return B0(this, obj, dVar);
    }
}
