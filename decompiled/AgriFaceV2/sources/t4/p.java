package t4;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public final class p {

    /* renamed from: b  reason: collision with root package name */
    private static final p f11875b = new p();

    /* renamed from: a  reason: collision with root package name */
    private final Map f11876a = new HashMap();

    /* loaded from: classes.dex */
    public interface a {
    }

    public static p b() {
        return f11875b;
    }

    public synchronized void a(a aVar, Class cls) {
        a aVar2 = (a) this.f11876a.get(cls);
        if (aVar2 != null && !aVar2.equals(aVar)) {
            throw new GeneralSecurityException("Different key creator for parameters class already inserted");
        }
        this.f11876a.put(cls, aVar);
    }
}
