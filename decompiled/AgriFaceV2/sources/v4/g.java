package v4;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import o4.b;
/* loaded from: classes.dex */
public final class g implements u4.g {

    /* renamed from: e  reason: collision with root package name */
    private static final b.EnumC0135b f12366e = b.EnumC0135b.f9848e;

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f12367a;

    /* renamed from: b  reason: collision with root package name */
    private final u4.f f12368b;

    /* renamed from: c  reason: collision with root package name */
    private final SecretKeySpec f12369c;

    /* renamed from: d  reason: collision with root package name */
    private final Provider f12370d;

    private g(u4.a aVar, Provider provider) {
        if (provider == null) {
            throw new IllegalArgumentException("conscrypt is null");
        }
        if (!f12366e.e()) {
            throw new GeneralSecurityException("Cannot use AES-CMAC in FIPS-mode.");
        }
        try {
            Mac.getInstance("AESCMAC", provider);
            this.f12370d = provider;
            this.f12367a = aVar.c().d();
            this.f12368b = aVar.b();
            this.f12369c = b(aVar);
        } catch (NoSuchAlgorithmException e8) {
            throw new GeneralSecurityException("AES-CMAC not available.", e8);
        }
    }

    public static u4.g a(u4.a aVar, Provider provider) {
        return new g(aVar, provider);
    }

    private static SecretKeySpec b(u4.a aVar) {
        return new SecretKeySpec(aVar.e().d(j4.g.a()), "AES");
    }
}
