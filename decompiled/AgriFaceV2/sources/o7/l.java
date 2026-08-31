package o7;

import c7.p;
import p7.l0;
import r6.r;
/* loaded from: classes.dex */
final class l implements n7.d {

    /* renamed from: a  reason: collision with root package name */
    private final t6.g f9905a;

    /* renamed from: b  reason: collision with root package name */
    private final Object f9906b;

    /* renamed from: c  reason: collision with root package name */
    private final p f9907c;

    /* loaded from: classes.dex */
    static final class a extends v6.k implements p {

        /* renamed from: i  reason: collision with root package name */
        int f9908i;

        /* renamed from: j  reason: collision with root package name */
        /* synthetic */ Object f9909j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ n7.d f9910k;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(n7.d dVar, t6.d dVar2) {
            super(2, dVar2);
            this.f9910k = dVar;
        }

        @Override // v6.a
        public final t6.d b(Object obj, t6.d dVar) {
            a aVar = new a(this.f9910k, dVar);
            aVar.f9909j = obj;
            return aVar;
        }

        @Override // v6.a
        public final Object l(Object obj) {
            Object c8;
            c8 = u6.d.c();
            int i8 = this.f9908i;
            if (i8 == 0) {
                r6.l.b(obj);
                Object obj2 = this.f9909j;
                n7.d dVar = this.f9910k;
                this.f9908i = 1;
                if (dVar.c(obj2, this) == c8) {
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
        public final Object f(Object obj, t6.d dVar) {
            return ((a) b(obj, dVar)).l(r.f11372a);
        }
    }

    public l(n7.d dVar, t6.g gVar) {
        this.f9905a = gVar;
        this.f9906b = l0.b(gVar);
        this.f9907c = new a(dVar, null);
    }

    @Override // n7.d
    public Object c(Object obj, t6.d dVar) {
        Object c8;
        Object b8 = e.b(this.f9905a, obj, this.f9906b, this.f9907c, dVar);
        c8 = u6.d.c();
        return b8 == c8 ? b8 : r.f11372a;
    }
}
