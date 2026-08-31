package p7;

import java.util.List;
import k7.o1;
/* loaded from: classes.dex */
public abstract class x {

    /* renamed from: a  reason: collision with root package name */
    private static final boolean f10199a = false;

    private static final y a(Throwable th, String str) {
        if (f10199a) {
            return new y(th, str);
        }
        if (th != null) {
            throw th;
        }
        d();
        throw new r6.d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ y b(Throwable th, String str, int i8, Object obj) {
        if ((i8 & 1) != 0) {
            th = null;
        }
        if ((i8 & 2) != 0) {
            str = null;
        }
        return a(th, str);
    }

    public static final boolean c(o1 o1Var) {
        return o1Var.S() instanceof y;
    }

    public static final Void d() {
        throw new IllegalStateException("Module with the Main dispatcher is missing. Add dependency providing the Main dispatcher, e.g. 'kotlinx-coroutines-android' and ensure it has the same version as 'kotlinx-coroutines-core'");
    }

    public static final o1 e(v vVar, List list) {
        try {
            return vVar.b(list);
        } catch (Throwable th) {
            return a(th, vVar.a());
        }
    }
}
