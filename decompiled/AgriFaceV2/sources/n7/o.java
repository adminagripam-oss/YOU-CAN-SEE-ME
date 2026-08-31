package n7;

import p7.h0;
/* loaded from: classes.dex */
public abstract class o {

    /* renamed from: a  reason: collision with root package name */
    private static final h0 f9792a = new h0("NONE");

    /* renamed from: b  reason: collision with root package name */
    private static final h0 f9793b = new h0("PENDING");

    public static final j a(Object obj) {
        if (obj == null) {
            obj = o7.i.f9899a;
        }
        return new n(obj);
    }

    public static final c d(m mVar, t6.g gVar, int i8, m7.a aVar) {
        boolean z7 = false;
        if (i8 >= 0 && i8 < 2) {
            z7 = true;
        }
        return ((z7 || i8 == -2) && aVar == m7.a.DROP_OLDEST) ? mVar : l.a(mVar, gVar, i8, aVar);
    }
}
