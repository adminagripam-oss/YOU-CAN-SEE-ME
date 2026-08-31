package o7;

import c7.p;
import k7.z;
import r6.r;
import t6.e;
/* loaded from: classes.dex */
public abstract class f extends d {

    /* renamed from: d  reason: collision with root package name */
    protected final n7.c f9895d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class a extends v6.k implements p {

        /* renamed from: i  reason: collision with root package name */
        int f9896i;

        /* renamed from: j  reason: collision with root package name */
        /* synthetic */ Object f9897j;

        a(t6.d dVar) {
            super(2, dVar);
        }

        @Override // v6.a
        public final t6.d b(Object obj, t6.d dVar) {
            a aVar = new a(dVar);
            aVar.f9897j = obj;
            return aVar;
        }

        @Override // v6.a
        public final Object l(Object obj) {
            Object c8;
            c8 = u6.d.c();
            int i8 = this.f9896i;
            if (i8 == 0) {
                r6.l.b(obj);
                f fVar = f.this;
                this.f9896i = 1;
                if (fVar.m((n7.d) this.f9897j, this) == c8) {
                    return c8;
                }
            } else if (i8 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                r6.l.b(obj);
            }
            return r.f11372a;
        }

        @Override // c7.p
        /* renamed from: o */
        public final Object f(n7.d dVar, t6.d dVar2) {
            return ((a) b(dVar, dVar2)).l(r.f11372a);
        }
    }

    public f(n7.c cVar, t6.g gVar, int i8, m7.a aVar) {
        super(gVar, i8, aVar);
        this.f9895d = cVar;
    }

    static /* synthetic */ Object j(f fVar, n7.d dVar, t6.d dVar2) {
        Object c8;
        Object c9;
        Object c10;
        if (fVar.f9886b == -3) {
            t6.g context = dVar2.getContext();
            t6.g e8 = z.e(context, fVar.f9885a);
            if (d7.k.a(e8, context)) {
                Object m8 = fVar.m(dVar, dVar2);
                c10 = u6.d.c();
                return m8 == c10 ? m8 : r.f11372a;
            }
            e.b bVar = t6.e.f11913d;
            if (d7.k.a(e8.c(bVar), context.c(bVar))) {
                Object l8 = fVar.l(dVar, e8, dVar2);
                c9 = u6.d.c();
                return l8 == c9 ? l8 : r.f11372a;
            }
        }
        Object a8 = super.a(dVar, dVar2);
        c8 = u6.d.c();
        return a8 == c8 ? a8 : r.f11372a;
    }

    static /* synthetic */ Object k(f fVar, m7.r rVar, t6.d dVar) {
        Object c8;
        Object m8 = fVar.m(new j(rVar), dVar);
        c8 = u6.d.c();
        return m8 == c8 ? m8 : r.f11372a;
    }

    private final Object l(n7.d dVar, t6.g gVar, t6.d dVar2) {
        Object c8;
        Object c9 = e.c(gVar, e.a(dVar, dVar2.getContext()), null, new a(null), dVar2, 4, null);
        c8 = u6.d.c();
        return c9 == c8 ? c9 : r.f11372a;
    }

    @Override // o7.d, n7.c
    public Object a(n7.d dVar, t6.d dVar2) {
        return j(this, dVar, dVar2);
    }

    @Override // o7.d
    protected Object e(m7.r rVar, t6.d dVar) {
        return k(this, rVar, dVar);
    }

    protected abstract Object m(n7.d dVar, t6.d dVar2);

    @Override // o7.d
    public String toString() {
        return this.f9895d + " -> " + super.toString();
    }
}
