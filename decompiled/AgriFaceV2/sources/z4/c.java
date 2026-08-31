package z4;

import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import o4.b;
import t4.k0;
/* loaded from: classes.dex */
public final class c implements j4.a {

    /* renamed from: c  reason: collision with root package name */
    public static final b.EnumC0135b f14394c = b.EnumC0135b.f9849f;

    /* renamed from: a  reason: collision with root package name */
    private final SecretKey f14395a;

    /* renamed from: b  reason: collision with root package name */
    private final byte[] f14396b;

    private c(byte[] bArr, a5.a aVar) {
        if (!f14394c.e()) {
            throw new GeneralSecurityException("Can not use AES-GCM in FIPS-mode, as BoringCrypto module is not available.");
        }
        this.f14395a = l4.k.c(bArr);
        this.f14396b = aVar.d();
    }

    public static j4.a c(k4.q qVar) {
        if (qVar.b().c() != 12) {
            throw new GeneralSecurityException("Expected IV Size 12, got " + qVar.b().c());
        } else if (qVar.b().e() == 16) {
            return new c(qVar.e().d(j4.g.a()), qVar.c());
        } else {
            throw new GeneralSecurityException("Expected tag Size 16, got " + qVar.b().e());
        }
    }

    @Override // j4.a
    public byte[] a(byte[] bArr, byte[] bArr2) {
        if (bArr != null) {
            byte[] a8 = p.a(12);
            AlgorithmParameterSpec a9 = l4.k.a(a8);
            Cipher d8 = l4.k.d();
            d8.init(1, this.f14395a, a9);
            if (bArr2 != null && bArr2.length != 0) {
                d8.updateAAD(bArr2);
            }
            int outputSize = d8.getOutputSize(bArr.length);
            byte[] bArr3 = this.f14396b;
            if (outputSize <= (Integer.MAX_VALUE - bArr3.length) - 12) {
                byte[] copyOf = Arrays.copyOf(bArr3, bArr3.length + 12 + outputSize);
                System.arraycopy(a8, 0, copyOf, this.f14396b.length, 12);
                if (d8.doFinal(bArr, 0, bArr.length, copyOf, this.f14396b.length + 12) == outputSize) {
                    return copyOf;
                }
                throw new GeneralSecurityException("not enough data written");
            }
            throw new GeneralSecurityException("plaintext too long");
        }
        throw new NullPointerException("plaintext is null");
    }

    @Override // j4.a
    public byte[] b(byte[] bArr, byte[] bArr2) {
        if (bArr != null) {
            int length = bArr.length;
            byte[] bArr3 = this.f14396b;
            if (length >= bArr3.length + 12 + 16) {
                if (k0.e(bArr3, bArr)) {
                    AlgorithmParameterSpec b8 = l4.k.b(bArr, this.f14396b.length, 12);
                    Cipher d8 = l4.k.d();
                    d8.init(2, this.f14395a, b8);
                    if (bArr2 != null && bArr2.length != 0) {
                        d8.updateAAD(bArr2);
                    }
                    byte[] bArr4 = this.f14396b;
                    return d8.doFinal(bArr, bArr4.length + 12, (bArr.length - bArr4.length) - 12);
                }
                throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
            }
            throw new GeneralSecurityException("ciphertext too short");
        }
        throw new NullPointerException("ciphertext is null");
    }
}
