package z4;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.util.Arrays;
import javax.crypto.AEADBadTagException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import o4.b;
import t4.k0;
/* loaded from: classes.dex */
public final class d implements j4.f, r4.a {

    /* renamed from: d  reason: collision with root package name */
    public static final b.EnumC0135b f14397d = b.EnumC0135b.f9848e;

    /* renamed from: e  reason: collision with root package name */
    private static final byte[] f14398e = new byte[16];

    /* renamed from: f  reason: collision with root package name */
    private static final byte[] f14399f = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};

    /* renamed from: g  reason: collision with root package name */
    private static final ThreadLocal f14400g = new a();

    /* renamed from: a  reason: collision with root package name */
    private final w4.c f14401a;

    /* renamed from: b  reason: collision with root package name */
    private final byte[] f14402b;

    /* renamed from: c  reason: collision with root package name */
    private final byte[] f14403c;

    /* loaded from: classes.dex */
    class a extends ThreadLocal {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public Cipher initialValue() {
            try {
                return (Cipher) i.f14427b.a("AES/CTR/NoPadding");
            } catch (GeneralSecurityException e8) {
                throw new IllegalStateException(e8);
            }
        }
    }

    private d(byte[] bArr, a5.a aVar) {
        if (!f14397d.e()) {
            throw new GeneralSecurityException("Can not use AES-SIV in FIPS-mode.");
        }
        if (bArr.length == 32 || bArr.length == 64) {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, bArr.length / 2);
            this.f14402b = Arrays.copyOfRange(bArr, bArr.length / 2, bArr.length);
            this.f14401a = d(copyOfRange);
            this.f14403c = aVar.d();
            return;
        }
        throw new InvalidKeyException("invalid key size: " + bArr.length + " bytes; key must have 32 or 64 bytes");
    }

    public static r4.a c(p4.a aVar) {
        return new d(aVar.e().d(j4.g.a()), aVar.c());
    }

    private static w4.c d(byte[] bArr) {
        return m.b(w4.a.c(w4.b.b(bArr.length), a5.b.a(bArr, j4.g.a())));
    }

    private byte[] e(byte[] bArr, byte[]... bArr2) {
        h(bArr2.length);
        int length = bArr.length;
        byte[] bArr3 = this.f14403c;
        if (length >= bArr3.length + 16) {
            if (k0.e(bArr3, bArr)) {
                Cipher cipher = (Cipher) f14400g.get();
                byte[] bArr4 = this.f14403c;
                byte[] copyOfRange = Arrays.copyOfRange(bArr, bArr4.length, bArr4.length + 16);
                byte[] bArr5 = (byte[]) copyOfRange.clone();
                bArr5[8] = (byte) (bArr5[8] & Byte.MAX_VALUE);
                bArr5[12] = (byte) (bArr5[12] & Byte.MAX_VALUE);
                cipher.init(2, new SecretKeySpec(this.f14402b, "AES"), new IvParameterSpec(bArr5));
                int length2 = this.f14403c.length + 16;
                int length3 = bArr.length - length2;
                byte[] doFinal = cipher.doFinal(bArr, length2, length3);
                if (length3 == 0 && doFinal == null && q.a()) {
                    doFinal = new byte[0];
                }
                byte[][] bArr6 = (byte[][]) Arrays.copyOf(bArr2, bArr2.length + 1);
                bArr6[bArr2.length] = doFinal;
                if (f.b(copyOfRange, g(bArr6))) {
                    return doFinal;
                }
                throw new AEADBadTagException("Integrity check failed.");
            }
            throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
        }
        throw new GeneralSecurityException("Ciphertext too short.");
    }

    private byte[] f(byte[] bArr, byte[]... bArr2) {
        h(bArr2.length);
        if (bArr.length <= (Integer.MAX_VALUE - this.f14403c.length) - 16) {
            Cipher cipher = (Cipher) f14400g.get();
            byte[][] bArr3 = (byte[][]) Arrays.copyOf(bArr2, bArr2.length + 1);
            bArr3[bArr2.length] = bArr;
            byte[] g8 = g(bArr3);
            byte[] bArr4 = (byte[]) g8.clone();
            bArr4[8] = (byte) (bArr4[8] & Byte.MAX_VALUE);
            bArr4[12] = (byte) (bArr4[12] & Byte.MAX_VALUE);
            cipher.init(1, new SecretKeySpec(this.f14402b, "AES"), new IvParameterSpec(bArr4));
            byte[] bArr5 = this.f14403c;
            byte[] copyOf = Arrays.copyOf(bArr5, bArr5.length + g8.length + bArr.length);
            System.arraycopy(g8, 0, copyOf, this.f14403c.length, g8.length);
            if (cipher.doFinal(bArr, 0, bArr.length, copyOf, this.f14403c.length + g8.length) == bArr.length) {
                return copyOf;
            }
            throw new GeneralSecurityException("not enough data written");
        }
        throw new GeneralSecurityException("plaintext too long");
    }

    private byte[] g(byte[]... bArr) {
        if (bArr.length == 0) {
            return this.f14401a.a(f14399f, 16);
        }
        byte[] a8 = this.f14401a.a(f14398e, 16);
        for (int i8 = 0; i8 < bArr.length - 1; i8++) {
            byte[] bArr2 = bArr[i8];
            if (bArr2 == null) {
                bArr2 = new byte[0];
            }
            a8 = f.e(v4.f.b(a8), this.f14401a.a(bArr2, 16));
        }
        byte[] bArr3 = bArr[bArr.length - 1];
        return this.f14401a.a(bArr3.length >= 16 ? f.f(bArr3, a8) : f.e(v4.f.a(bArr3), v4.f.b(a8)), 16);
    }

    private void h(int i8) {
        if (i8 <= 126) {
            return;
        }
        throw new GeneralSecurityException("Too many associated datas: " + i8 + " > 126");
    }

    @Override // j4.f
    public byte[] a(byte[] bArr, byte[] bArr2) {
        return f(bArr, bArr2);
    }

    @Override // j4.f
    public byte[] b(byte[] bArr, byte[] bArr2) {
        return e(bArr, bArr2);
    }
}
