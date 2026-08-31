package l4;

import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
/* loaded from: classes.dex */
public final class q implements j4.a {

    /* renamed from: d  reason: collision with root package name */
    private static final byte[] f9024d = z4.k.a("7a806c");

    /* renamed from: e  reason: collision with root package name */
    private static final byte[] f9025e = z4.k.a("46bb91c3c5");

    /* renamed from: f  reason: collision with root package name */
    private static final byte[] f9026f = z4.k.a("36864200e0eaf5284d884a0e77d31646");

    /* renamed from: g  reason: collision with root package name */
    private static final byte[] f9027g = z4.k.a("bae8e37fc83441b16034566b");

    /* renamed from: h  reason: collision with root package name */
    private static final byte[] f9028h = z4.k.a("af60eb711bd85bc1e4d3e0a462e074eea428a8");

    /* renamed from: a  reason: collision with root package name */
    private final a f9029a;

    /* renamed from: b  reason: collision with root package name */
    private final SecretKey f9030b;

    /* renamed from: c  reason: collision with root package name */
    private final byte[] f9031c;

    /* loaded from: classes.dex */
    public interface a {
        Object get();
    }

    private q(byte[] bArr, byte[] bArr2, a aVar) {
        this.f9031c = bArr2;
        z4.r.a(bArr.length);
        this.f9030b = new SecretKeySpec(bArr, "AES");
        this.f9029a = aVar;
    }

    public static j4.a c(k4.v vVar, a aVar) {
        if (f((Cipher) aVar.get())) {
            return new q(vVar.e().d(j4.g.a()), vVar.c().d(), aVar);
        }
        throw new IllegalStateException("Cipher does not implement AES GCM SIV.");
    }

    private static AlgorithmParameterSpec d(byte[] bArr) {
        return e(bArr, 0, bArr.length);
    }

    private static AlgorithmParameterSpec e(byte[] bArr, int i8, int i9) {
        return new GCMParameterSpec(128, bArr, i8, i9);
    }

    public static boolean f(Cipher cipher) {
        try {
            cipher.init(2, new SecretKeySpec(f9026f, "AES"), d(f9027g));
            cipher.updateAAD(f9025e);
            byte[] bArr = f9028h;
            return z4.f.b(cipher.doFinal(bArr, 0, bArr.length), f9024d);
        } catch (GeneralSecurityException unused) {
            return false;
        }
    }

    @Override // j4.a
    public byte[] a(byte[] bArr, byte[] bArr2) {
        Cipher cipher = (Cipher) this.f9029a.get();
        int length = bArr.length;
        byte[] bArr3 = this.f9031c;
        if (length <= 2147483619 - bArr3.length) {
            byte[] copyOf = Arrays.copyOf(bArr3, bArr3.length + 12 + bArr.length + 16);
            byte[] a8 = z4.p.a(12);
            System.arraycopy(a8, 0, copyOf, this.f9031c.length, 12);
            cipher.init(1, this.f9030b, d(a8));
            if (bArr2 != null && bArr2.length != 0) {
                cipher.updateAAD(bArr2);
            }
            int doFinal = cipher.doFinal(bArr, 0, bArr.length, copyOf, this.f9031c.length + 12);
            if (doFinal == bArr.length + 16) {
                return copyOf;
            }
            throw new GeneralSecurityException(String.format("encryption failed; AES-GCM-SIV tag must be %s bytes, but got only %s bytes", 16, Integer.valueOf(doFinal - bArr.length)));
        }
        throw new GeneralSecurityException("plaintext too long");
    }

    @Override // j4.a
    public byte[] b(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        byte[] bArr3 = this.f9031c;
        if (length >= bArr3.length + 12 + 16) {
            if (t4.k0.e(bArr3, bArr)) {
                Cipher cipher = (Cipher) this.f9029a.get();
                cipher.init(2, this.f9030b, e(bArr, this.f9031c.length, 12));
                if (bArr2 != null && bArr2.length != 0) {
                    cipher.updateAAD(bArr2);
                }
                byte[] bArr4 = this.f9031c;
                return cipher.doFinal(bArr, bArr4.length + 12, (bArr.length - bArr4.length) - 12);
            }
            throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
        }
        throw new GeneralSecurityException("ciphertext too short");
    }
}
