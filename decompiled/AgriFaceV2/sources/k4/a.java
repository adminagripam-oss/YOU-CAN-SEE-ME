package k4;

import java.security.GeneralSecurityException;
/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8494a = b("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey");

    /* renamed from: b  reason: collision with root package name */
    public static final String f8495b = b("type.googleapis.com/google.crypto.tink.AesGcmKey");

    /* renamed from: c  reason: collision with root package name */
    public static final String f8496c = b("type.googleapis.com/google.crypto.tink.AesGcmSivKey");

    /* renamed from: d  reason: collision with root package name */
    public static final String f8497d = b("type.googleapis.com/google.crypto.tink.AesEaxKey");

    /* renamed from: e  reason: collision with root package name */
    public static final String f8498e = b("type.googleapis.com/google.crypto.tink.KmsAeadKey");

    /* renamed from: f  reason: collision with root package name */
    public static final String f8499f = b("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey");

    /* renamed from: g  reason: collision with root package name */
    public static final String f8500g = b("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key");

    /* renamed from: h  reason: collision with root package name */
    public static final String f8501h = b("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key");

    /* renamed from: i  reason: collision with root package name */
    public static final y4.j0 f8502i;

    /* renamed from: j  reason: collision with root package name */
    public static final y4.j0 f8503j;

    /* renamed from: k  reason: collision with root package name */
    public static final y4.j0 f8504k;

    static {
        y4.j0 V = y4.j0.V();
        f8502i = V;
        f8503j = V;
        f8504k = V;
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
        e.d();
        u4.o.c();
        j.d(true);
        t.e(true);
        if (n4.b.a()) {
            return;
        }
        o.e(true);
        y.d(true);
        d0.f(true);
        h0.f(true);
        l0.f(true);
        s1.f(true);
        n1.d(true);
    }
}
