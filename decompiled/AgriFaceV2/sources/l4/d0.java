package l4;

import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import o4.b;
/* loaded from: classes.dex */
public final class d0 {

    /* renamed from: b  reason: collision with root package name */
    public static final b.EnumC0135b f8985b = b.EnumC0135b.f9849f;

    /* renamed from: a  reason: collision with root package name */
    private final SecretKey f8986a;

    public d0(byte[] bArr) {
        if (!f8985b.e()) {
            throw new GeneralSecurityException("Can not use AES-GCM in FIPS-mode, as BoringCrypto module is not available.");
        }
        this.f8986a = k.c(bArr);
    }

    public byte[] a(byte[] bArr, byte[] bArr2, int i8, byte[] bArr3) {
        if (bArr.length == 12) {
            if (bArr2.length >= i8 + 16) {
                AlgorithmParameterSpec a8 = k.a(bArr);
                Cipher d8 = k.d();
                d8.init(2, this.f8986a, a8);
                if (bArr3 != null && bArr3.length != 0) {
                    d8.updateAAD(bArr3);
                }
                return d8.doFinal(bArr2, i8, bArr2.length - i8);
            }
            throw new GeneralSecurityException("ciphertext too short");
        }
        throw new GeneralSecurityException("iv is wrong size");
    }

    public byte[] b(byte[] bArr, byte[] bArr2, int i8, byte[] bArr3) {
        if (bArr.length == 12) {
            AlgorithmParameterSpec a8 = k.a(bArr);
            Cipher d8 = k.d();
            d8.init(1, this.f8986a, a8);
            if (bArr3 != null && bArr3.length != 0) {
                d8.updateAAD(bArr3);
            }
            int outputSize = d8.getOutputSize(bArr2.length);
            if (outputSize <= Integer.MAX_VALUE - i8) {
                byte[] bArr4 = new byte[i8 + outputSize];
                if (d8.doFinal(bArr2, 0, bArr2.length, bArr4, i8) == outputSize) {
                    return bArr4;
                }
                throw new GeneralSecurityException("not enough data written");
            }
            throw new GeneralSecurityException("plaintext too long");
        }
        throw new GeneralSecurityException("iv is wrong size");
    }
}
