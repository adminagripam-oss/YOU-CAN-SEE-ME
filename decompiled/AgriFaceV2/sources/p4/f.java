package p4;

import java.security.GeneralSecurityException;
import y4.j0;
/* loaded from: classes.dex */
public abstract class f {

    /* renamed from: a  reason: collision with root package name */
    public static final String f10110a = a("type.googleapis.com/google.crypto.tink.AesSivKey");

    /* renamed from: b  reason: collision with root package name */
    public static final j0 f10111b = j0.V();

    /* renamed from: c  reason: collision with root package name */
    public static final j0 f10112c = j0.V();

    static {
        try {
            b();
        } catch (GeneralSecurityException e8) {
            throw new ExceptionInInitializerError(e8);
        }
    }

    private static String a(String str) {
        return str;
    }

    public static void b() {
        j.d();
        if (n4.b.a()) {
            return;
        }
        d.f(true);
    }
}
