package h2;

import android.app.Activity;
import c7.p;
import h2.i;
import k7.n0;
import r6.r;
/* loaded from: classes.dex */
public final class i implements f {

    /* renamed from: b  reason: collision with root package name */
    private final l f7202b;

    /* renamed from: c  reason: collision with root package name */
    private final i2.a f7203c;

    /* loaded from: classes.dex */
    static final class a extends v6.k implements p {

        /* renamed from: i  reason: collision with root package name */
        int f7204i;

        /* renamed from: j  reason: collision with root package name */
        private /* synthetic */ Object f7205j;

        /* renamed from: l  reason: collision with root package name */
        final /* synthetic */ Activity f7207l;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: h2.i$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0092a extends d7.l implements c7.a {

            /* renamed from: f  reason: collision with root package name */
            final /* synthetic */ i f7208f;

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ g1.a f7209g;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0092a(i iVar, g1.a aVar) {
                super(0);
                this.f7208f = iVar;
                this.f7209g = aVar;
            }

            @Override // c7.a
            public /* bridge */ /* synthetic */ Object a() {
                b();
                return r.f11372a;
            }

            public final void b() {
                this.f7208f.f7203c.a(this.f7209g);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Activity activity, t6.d dVar) {
            super(2, dVar);
            this.f7207l = activity;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void r(m7.r rVar, j jVar) {
            rVar.l(jVar);
        }

        @Override // v6.a
        public final t6.d b(Object obj, t6.d dVar) {
            a aVar = new a(this.f7207l, dVar);
            aVar.f7205j = obj;
            return aVar;
        }

        @Override // v6.a
        public final Object l(Object obj) {
            Object c8;
            c8 = u6.d.c();
            int i8 = this.f7204i;
            if (i8 == 0) {
                r6.l.b(obj);
                final m7.r rVar = (m7.r) this.f7205j;
                g1.a aVar = new g1.a() { // from class: h2.h
                    @Override // g1.a
                    public final void accept(Object obj2) {
                        i.a.r(m7.r.this, (j) obj2);
                    }
                };
                i.this.f7203c.b(this.f7207l, new androidx.profileinstaller.g(), aVar);
                C0092a c0092a = new C0092a(i.this, aVar);
                this.f7204i = 1;
                if (m7.p.a(rVar, c0092a, this) == c8) {
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
        /* renamed from: q */
        public final Object f(m7.r rVar, t6.d dVar) {
            return ((a) b(rVar, dVar)).l(r.f11372a);
        }
    }

    public i(l lVar, i2.a aVar) {
        d7.k.e(lVar, "windowMetricsCalculator");
        d7.k.e(aVar, "windowBackend");
        this.f7202b = lVar;
        this.f7203c = aVar;
    }

    @Override // h2.f
    public n7.c a(Activity activity) {
        d7.k.e(activity, "activity");
        return n7.e.d(n7.e.a(new a(activity, null)), n0.c());
    }
}
