package b0;

import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class i1 {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f4238a = new Object();

    /* renamed from: b  reason: collision with root package name */
    private static final Map f4239b = new HashMap();

    public static b0 a(Object obj) {
        b0 b0Var;
        synchronized (f4238a) {
            b0Var = (b0) f4239b.get(obj);
        }
        return b0Var == null ? b0.f4137a : b0Var;
    }
}
