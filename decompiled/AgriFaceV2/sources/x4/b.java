package x4;

import j4.g;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.Provider;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import o4.b;
import w4.c;
/* loaded from: classes.dex */
public final class b implements c {

    /* renamed from: c  reason: collision with root package name */
    private static final b.EnumC0135b f12719c = b.EnumC0135b.f9848e;

    /* renamed from: a  reason: collision with root package name */
    private final Key f12720a;

    /* renamed from: b  reason: collision with root package name */
    private final Provider f12721b;

    private b(byte[] bArr, Provider provider) {
        if (!f12719c.e()) {
            throw new GeneralSecurityException("Cannot use AES-CMAC in FIPS-mode, as BoringCrypto module is not available");
        }
        this.f12720a = new SecretKeySpec(bArr, "AES");
        this.f12721b = provider;
    }

    public static c b(w4.a aVar) {
        Provider a8 = t4.b.a();
        if (a8 != null) {
            Mac.getInstance("AESCMAC", a8);
            return new b(aVar.d().d(g.a()), a8);
        }
        throw new GeneralSecurityException("Conscrypt not available");
    }

    @Override // w4.c
    public byte[] a(byte[] bArr, int i8) {
        if (i8 <= 16) {
            Mac mac = Mac.getInstance("AESCMAC", this.f12721b);
            mac.init(this.f12720a);
            byte[] doFinal = mac.doFinal(bArr);
            return i8 == doFinal.length ? doFinal : Arrays.copyOf(doFinal, i8);
        }
        throw new InvalidAlgorithmParameterException("outputLength must not be larger than 16");
    }
}
