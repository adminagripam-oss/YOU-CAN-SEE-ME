package l4;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Provider;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import o4.b;
/* loaded from: classes.dex */
public final class w implements j4.a {

    /* renamed from: d  reason: collision with root package name */
    private static final b.EnumC0135b f9048d = b.EnumC0135b.f9848e;

    /* renamed from: e  reason: collision with root package name */
    private static final byte[] f9049e = z4.k.a("808182838485868788898a8b8c8d8e8f909192939495969798999a9b9c9d9e9f");

    /* renamed from: f  reason: collision with root package name */
    private static final byte[] f9050f = z4.k.a("070000004041424344454647");

    /* renamed from: g  reason: collision with root package name */
    private static final byte[] f9051g = z4.k.a("a0784d7a4716f3feb4f64e7f4b39bf04");

    /* renamed from: a  reason: collision with root package name */
    private final SecretKey f9052a;

    /* renamed from: b  reason: collision with root package name */
    private final byte[] f9053b;

    /* renamed from: c  reason: collision with root package name */
    private final Provider f9054c;

    private w(byte[] bArr, byte[] bArr2, Provider provider) {
        if (!f9048d.e()) {
            throw new GeneralSecurityException("Can not use ChaCha20Poly1305 in FIPS-mode.");
        }
        if (bArr.length != 32) {
            throw new InvalidKeyException("The key length in bytes must be 32.");
        }
        this.f9052a = new SecretKeySpec(bArr, "ChaCha20");
        this.f9053b = bArr2;
        this.f9054c = provider;
    }

    public static j4.a c(k4.a0 a0Var) {
        return new w(a0Var.e().d(j4.g.a()), a0Var.c().d(), e().getProvider());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Cipher d(Provider provider) {
        return Cipher.getInstance("ChaCha20-Poly1305", provider);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Cipher e() {
        Cipher cipher = (Cipher) z4.i.f14427b.a("ChaCha20-Poly1305");
        if (g(cipher)) {
            return cipher;
        }
        throw new GeneralSecurityException("JCE does not support algorithm: ChaCha20-Poly1305");
    }

    public static boolean f() {
        try {
            e();
            return true;
        } catch (GeneralSecurityException unused) {
            return false;
        }
    }

    private static boolean g(Cipher cipher) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(f9050f);
            byte[] bArr = f9049e;
            cipher.init(2, new SecretKeySpec(bArr, "ChaCha20"), ivParameterSpec);
            byte[] bArr2 = f9051g;
            if (cipher.doFinal(bArr2).length != 0) {
                return false;
            }
            cipher.init(2, new SecretKeySpec(bArr, "ChaCha20"), ivParameterSpec);
            return cipher.doFinal(bArr2).length == 0;
        } catch (GeneralSecurityException unused) {
            return false;
        }
    }

    @Override // j4.a
    public byte[] a(byte[] bArr, byte[] bArr2) {
        if (bArr != null) {
            byte[] a8 = z4.p.a(12);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(a8);
            Cipher d8 = d(this.f9054c);
            d8.init(1, this.f9052a, ivParameterSpec);
            if (bArr2 != null && bArr2.length != 0) {
                d8.updateAAD(bArr2);
            }
            int outputSize = d8.getOutputSize(bArr.length);
            byte[] bArr3 = this.f9053b;
            if (outputSize <= (Integer.MAX_VALUE - bArr3.length) - 12) {
                byte[] copyOf = Arrays.copyOf(bArr3, bArr3.length + 12 + outputSize);
                System.arraycopy(a8, 0, copyOf, this.f9053b.length, 12);
                if (d8.doFinal(bArr, 0, bArr.length, copyOf, this.f9053b.length + 12) == outputSize) {
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
            byte[] bArr3 = this.f9053b;
            if (length >= bArr3.length + 12 + 16) {
                if (t4.k0.e(bArr3, bArr)) {
                    byte[] bArr4 = new byte[12];
                    System.arraycopy(bArr, this.f9053b.length, bArr4, 0, 12);
                    IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr4);
                    Cipher d8 = d(this.f9054c);
                    d8.init(2, this.f9052a, ivParameterSpec);
                    if (bArr2 != null && bArr2.length != 0) {
                        d8.updateAAD(bArr2);
                    }
                    byte[] bArr5 = this.f9053b;
                    return d8.doFinal(bArr, bArr5.length + 12, (bArr.length - bArr5.length) - 12);
                }
                throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
            }
            throw new GeneralSecurityException("ciphertext too short");
        }
        throw new NullPointerException("ciphertext is null");
    }
}
