package v6;

import java.io.Serializable;
import r6.k;
import r6.l;
/* loaded from: classes.dex */
public abstract class a implements t6.d, e, Serializable {

    /* renamed from: e  reason: collision with root package name */
    private final t6.d f12431e;

    public a(t6.d dVar) {
        this.f12431e = dVar;
    }

    public t6.d b(Object obj, t6.d dVar) {
        d7.k.e(dVar, "completion");
        throw new UnsupportedOperationException("create(Any?;Continuation) has not been overridden");
    }

    @Override // v6.e
    public e e() {
        t6.d dVar = this.f12431e;
        if (dVar instanceof e) {
            return (e) dVar;
        }
        return null;
    }

    public final t6.d h() {
        return this.f12431e;
    }

    @Override // t6.d
    public final void i(Object obj) {
        Object l8;
        Object c8;
        t6.d dVar = this;
        while (true) {
            h.b(dVar);
            a aVar = (a) dVar;
            t6.d dVar2 = aVar.f12431e;
            d7.k.b(dVar2);
            try {
                l8 = aVar.l(obj);
                c8 = u6.d.c();
            } catch (Throwable th) {
                k.a aVar2 = r6.k.f11363e;
                obj = r6.k.a(l.a(th));
            }
            if (l8 == c8) {
                return;
            }
            obj = r6.k.a(l8);
            aVar.m();
            if (!(dVar2 instanceof a)) {
                dVar2.i(obj);
                return;
            }
            dVar = dVar2;
        }
    }

    public StackTraceElement j() {
        return g.d(this);
    }

    protected abstract Object l(Object obj);

    protected void m() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Continuation at ");
        Object j8 = j();
        if (j8 == null) {
            j8 = getClass().getName();
        }
        sb.append(j8);
        return sb.toString();
    }
}
