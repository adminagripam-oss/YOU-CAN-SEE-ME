package u4;

import java.security.GeneralSecurityException;
import y4.j0;
/* loaded from: classes.dex */
public abstract class o {

    /* renamed from: a  reason: collision with root package name */
    public static final String f12155a = b("type.googleapis.com/google.crypto.tink.HmacKey");

    /* renamed from: b  reason: collision with root package name */
    public static final j0 f12156b;

    /* renamed from: c  reason: collision with root package name */
    public static final j0 f12157c;

    /* renamed from: d  reason: collision with root package name */
    public static final j0 f12158d;

    static {
        j0 V = j0.V();
        f12156b = V;
        f12157c = V;
        f12158d = V;
        try {
            a();
        } catch (GeneralSecurityException e8) {
            throw new ExceptionInInitializerError(e8);
        }
    }

    public static void a() {
        c();
    }

    private static String b(String str) {
        return str;
    }

    public static void c() {
        s.d();
        h.d();
        m.c(true);
        if (n4.b.a()) {
            return;
        }
        e.h(true);
    }
}
