package p7;
/* loaded from: classes.dex */
public abstract class z {

    /* loaded from: classes.dex */
    static final class a extends d7.l implements c7.l {

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ c7.l f10202f;

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Object f10203g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ t6.g f10204h;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(c7.l lVar, Object obj, t6.g gVar) {
            super(1);
            this.f10202f = lVar;
            this.f10203g = obj;
            this.f10204h = gVar;
        }

        public final void b(Throwable th) {
            z.b(this.f10202f, this.f10203g, this.f10204h);
        }

        @Override // c7.l
        public /* bridge */ /* synthetic */ Object g(Object obj) {
            b((Throwable) obj);
            return r6.r.f11372a;
        }
    }

    public static final c7.l a(c7.l lVar, Object obj, t6.g gVar) {
        return new a(lVar, obj, gVar);
    }

    public static final void b(c7.l lVar, Object obj, t6.g gVar) {
        q0 c8 = c(lVar, obj, null);
        if (c8 != null) {
            k7.c0.a(gVar, c8);
        }
    }

    public static final q0 c(c7.l lVar, Object obj, q0 q0Var) {
        try {
            lVar.g(obj);
        } catch (Throwable th) {
            if (q0Var == null || q0Var.getCause() == th) {
                return new q0("Exception in undelivered element handler for " + obj, th);
            }
            r6.b.a(q0Var, th);
        }
        return q0Var;
    }

    public static /* synthetic */ q0 d(c7.l lVar, Object obj, q0 q0Var, int i8, Object obj2) {
        if ((i8 & 2) != 0) {
            q0Var = null;
        }
        return c(lVar, obj, q0Var);
    }
}
