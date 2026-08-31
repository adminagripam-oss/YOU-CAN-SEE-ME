package p7;

import k7.u1;
import t6.g;
/* loaded from: classes.dex */
public abstract class l0 {

    /* renamed from: a  reason: collision with root package name */
    public static final h0 f10160a = new h0("NO_THREAD_ELEMENTS");

    /* renamed from: b  reason: collision with root package name */
    private static final c7.p f10161b = a.f10164f;

    /* renamed from: c  reason: collision with root package name */
    private static final c7.p f10162c = b.f10165f;

    /* renamed from: d  reason: collision with root package name */
    private static final c7.p f10163d = c.f10166f;

    /* loaded from: classes.dex */
    static final class a extends d7.l implements c7.p {

        /* renamed from: f  reason: collision with root package name */
        public static final a f10164f = new a();

        a() {
            super(2);
        }

        @Override // c7.p
        /* renamed from: b */
        public final Object f(Object obj, g.b bVar) {
            if (bVar instanceof u1) {
                Integer num = obj instanceof Integer ? (Integer) obj : null;
                int intValue = num != null ? num.intValue() : 1;
                return intValue == 0 ? bVar : Integer.valueOf(intValue + 1);
            }
            return obj;
        }
    }

    /* loaded from: classes.dex */
    static final class b extends d7.l implements c7.p {

        /* renamed from: f  reason: collision with root package name */
        public static final b f10165f = new b();

        b() {
            super(2);
        }

        @Override // c7.p
        /* renamed from: b */
        public final u1 f(u1 u1Var, g.b bVar) {
            if (u1Var != null) {
                return u1Var;
            }
            if (bVar instanceof u1) {
                return (u1) bVar;
            }
            return null;
        }
    }

    /* loaded from: classes.dex */
    static final class c extends d7.l implements c7.p {

        /* renamed from: f  reason: collision with root package name */
        public static final c f10166f = new c();

        c() {
            super(2);
        }

        @Override // c7.p
        /* renamed from: b */
        public final p0 f(p0 p0Var, g.b bVar) {
            if (bVar instanceof u1) {
                u1 u1Var = (u1) bVar;
                p0Var.a(u1Var, u1Var.x(p0Var.f10176a));
            }
            return p0Var;
        }
    }

    public static final void a(t6.g gVar, Object obj) {
        if (obj == f10160a) {
            return;
        }
        if (obj instanceof p0) {
            ((p0) obj).b(gVar);
            return;
        }
        Object g8 = gVar.g(null, f10162c);
        d7.k.c(g8, "null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
        ((u1) g8).n(gVar, obj);
    }

    public static final Object b(t6.g gVar) {
        Object g8 = gVar.g(0, f10161b);
        d7.k.b(g8);
        return g8;
    }

    public static final Object c(t6.g gVar, Object obj) {
        if (obj == null) {
            obj = b(gVar);
        }
        if (obj == 0) {
            return f10160a;
        }
        if (obj instanceof Integer) {
            return gVar.g(new p0(gVar, ((Number) obj).intValue()), f10163d);
        }
        d7.k.c(obj, "null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
        return ((u1) obj).x(gVar);
    }
}
