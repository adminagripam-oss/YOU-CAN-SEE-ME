package k7;

import t6.g;
/* loaded from: classes.dex */
public abstract class z {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class a extends d7.l implements c7.p {

        /* renamed from: f  reason: collision with root package name */
        public static final a f8801f = new a();

        a() {
            super(2);
        }

        @Override // c7.p
        /* renamed from: b */
        public final t6.g f(t6.g gVar, g.b bVar) {
            return gVar.M(bVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class b extends d7.l implements c7.p {

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ d7.r f8802f;

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ boolean f8803g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(d7.r rVar, boolean z7) {
            super(2);
            this.f8802f = rVar;
            this.f8803g = z7;
        }

        @Override // c7.p
        /* renamed from: b */
        public final t6.g f(t6.g gVar, g.b bVar) {
            return gVar.M(bVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class c extends d7.l implements c7.p {

        /* renamed from: f  reason: collision with root package name */
        public static final c f8804f = new c();

        c() {
            super(2);
        }

        public final Boolean b(boolean z7, g.b bVar) {
            return Boolean.valueOf(z7);
        }

        @Override // c7.p
        public /* bridge */ /* synthetic */ Object f(Object obj, Object obj2) {
            return b(((Boolean) obj).booleanValue(), (g.b) obj2);
        }
    }

    private static final t6.g a(t6.g gVar, t6.g gVar2, boolean z7) {
        boolean c8 = c(gVar);
        boolean c9 = c(gVar2);
        if (c8 || c9) {
            d7.r rVar = new d7.r();
            rVar.f6484e = gVar2;
            t6.h hVar = t6.h.f11916e;
            t6.g gVar3 = (t6.g) gVar.g(hVar, new b(rVar, z7));
            if (c9) {
                rVar.f6484e = ((t6.g) rVar.f6484e).g(hVar, a.f8801f);
            }
            return gVar3.M((t6.g) rVar.f6484e);
        }
        return gVar.M(gVar2);
    }

    public static final String b(t6.g gVar) {
        return null;
    }

    private static final boolean c(t6.g gVar) {
        return ((Boolean) gVar.g(Boolean.FALSE, c.f8804f)).booleanValue();
    }

    public static final t6.g d(d0 d0Var, t6.g gVar) {
        t6.g a8 = a(d0Var.h(), gVar, true);
        return (a8 == n0.a() || a8.c(t6.e.f11913d) != null) ? a8 : a8.M(n0.a());
    }

    public static final t6.g e(t6.g gVar, t6.g gVar2) {
        return !c(gVar2) ? gVar.M(gVar2) : a(gVar, gVar2, false);
    }

    public static final x1 f(v6.e eVar) {
        do {
            eVar = eVar.e();
        } while (eVar != null);
        return null;
    }

    public static final x1 g(t6.d dVar, t6.g gVar, Object obj) {
        if (dVar instanceof v6.e) {
            if (gVar.c(y1.f8800e) != null) {
                f((v6.e) dVar);
                return null;
            }
            return null;
        }
        return null;
    }
}
