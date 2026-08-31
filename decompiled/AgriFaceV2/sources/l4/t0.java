package l4;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Provider;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import k4.p1;
import o4.b;
/* loaded from: classes.dex */
public final class t0 implements j4.a {

    /* renamed from: d  reason: collision with root package name */
    private static final b.EnumC0135b f9038d = b.EnumC0135b.f9848e;

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f9039a;

    /* renamed from: b  reason: collision with root package name */
    private final byte[] f9040b;

    /* renamed from: c  reason: collision with root package name */
    private final Provider f9041c;

    private t0(byte[] bArr, byte[] bArr2, Provider provider) {
        if (!f9038d.e()) {
            throw new GeneralSecurityException("Can not use ChaCha20Poly1305 in FIPS-mode.");
        }
        if (bArr.length != 32) {
            throw new InvalidKeyException("The key length in bytes must be 32.");
        }
        this.f9039a = bArr;
        this.f9040b = bArr2;
        this.f9041c = provider;
    }

    public static j4.a c(p1 p1Var) {
        return new t0(p1Var.e().d(j4.g.a()), p1Var.c().d(), w.e().getProvider());
    }

    static byte[] d(byte[] bArr) {
        byte[] bArr2 = new byte[12];
        System.arraycopy(bArr, 16, bArr2, 4, 8);
        return bArr2;
    }

    public static boolean e() {
        return w.f();
    }

    @Override // j4.a
    public byte[] a(byte[] bArr, byte[] bArr2) {
        if (bArr != null) {
            byte[] a8 = z4.p.a(24);
            SecretKeySpec secretKeySpec = new SecretKeySpec(c0.a(this.f9039a, a8), "ChaCha20");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(d(a8));
            Cipher d8 = w.d(this.f9041c);
            d8.init(1, secretKeySpec, ivParameterSpec);
            if (bArr2 != null && bArr2.length != 0) {
                d8.updateAAD(bArr2);
            }
            int outputSize = d8.getOutputSize(bArr.length);
            byte[] bArr3 = this.f9040b;
            if (outputSize <= (Integer.MAX_VALUE - bArr3.length) - 24) {
                byte[] copyOf = Arrays.copyOf(bArr3, bArr3.length + 24 + outputSize);
                System.arraycopy(a8, 0, copyOf, this.f9040b.length, 24);
                if (d8.doFinal(bArr, 0, bArr.length, copyOf, this.f9040b.length + 24) == outputSize) {
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
            byte[] bArr3 = this.f9040b;
            if (length >= bArr3.length + 24 + 16) {
                if (t4.k0.e(bArr3, bArr)) {
                    byte[] bArr4 = new byte[24];
                    System.arraycopy(bArr, this.f9040b.length, bArr4, 0, 24);
                    SecretKeySpec secretKeySpec = new SecretKeySpec(c0.a(this.f9039a, bArr4), "ChaCha20");
                    IvParameterSpec ivParameterSpec = new IvParameterSpec(d(bArr4));
                    Cipher d8 = w.d(this.f9041c);
                    d8.init(2, secretKeySpec, ivParameterSpec);
                    if (bArr2 != null && bArr2.length != 0) {
                        d8.updateAAD(bArr2);
                    }
                    byte[] bArr5 = this.f9040b;
                    return d8.doFinal(bArr, bArr5.length + 24, (bArr.length - bArr5.length) - 24);
                }
                throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
            }
            throw new GeneralSecurityException("ciphertext too short");
        }
        throw new NullPointerException("ciphertext is null");
    }
}
