package r6;

import r6.k;
/* loaded from: classes.dex */
public abstract class l {
    public static final Object a(Throwable th) {
        d7.k.e(th, "exception");
        return new k.b(th);
    }

    public static final void b(Object obj) {
        if (obj instanceof k.b) {
            throw ((k.b) obj).f11364e;
        }
    }
}
