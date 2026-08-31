package p7;

import r6.k;
/* loaded from: classes.dex */
public abstract class g0 {

    /* renamed from: a  reason: collision with root package name */
    private static final StackTraceElement f10146a = new a.a().a();

    /* renamed from: b  reason: collision with root package name */
    private static final String f10147b;

    /* renamed from: c  reason: collision with root package name */
    private static final String f10148c;

    static {
        Object a8;
        Object a9;
        try {
            k.a aVar = r6.k.f11363e;
            a8 = r6.k.a(v6.a.class.getCanonicalName());
        } catch (Throwable th) {
            k.a aVar2 = r6.k.f11363e;
            a8 = r6.k.a(r6.l.a(th));
        }
        if (r6.k.b(a8) != null) {
            a8 = "kotlin.coroutines.jvm.internal.BaseContinuationImpl";
        }
        f10147b = (String) a8;
        try {
            a9 = r6.k.a(g0.class.getCanonicalName());
        } catch (Throwable th2) {
            k.a aVar3 = r6.k.f11363e;
            a9 = r6.k.a(r6.l.a(th2));
        }
        if (r6.k.b(a9) != null) {
            a9 = "kotlinx.coroutines.internal.StackTraceRecoveryKt";
        }
        f10148c = (String) a9;
    }

    public static final Throwable a(Throwable th) {
        return th;
    }
}
