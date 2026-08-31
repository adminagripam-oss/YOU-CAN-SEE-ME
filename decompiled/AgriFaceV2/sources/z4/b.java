package z4;

import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.AEADBadTagException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import o4.b;
import t4.k0;
/* loaded from: classes.dex */
public final class b implements j4.a {

    /* renamed from: e  reason: collision with root package name */
    public static final b.EnumC0135b f14388e = b.EnumC0135b.f9848e;

    /* renamed from: f  reason: collision with root package name */
    private static final ThreadLocal f14389f = new a();

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f14390a;

    /* renamed from: b  reason: collision with root package name */
    private final w4.c f14391b;

    /* renamed from: c  reason: collision with root package name */
    private final SecretKeySpec f14392c;

    /* renamed from: d  reason: collision with root package name */
    private final int f14393d;

    /* loaded from: classes.dex */
    class a extends ThreadLocal {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public Cipher initialValue() {
            try {
                return (Cipher) i.f14427b.a("AES/CTR/NOPADDING");
            } catch (GeneralSecurityException e8) {
                throw new IllegalStateException(e8);
            }
        }
    }

    private b(byte[] bArr, int i8, byte[] bArr2) {
        if (!f14388e.e()) {
            throw new GeneralSecurityException("Can not use AES-EAX in FIPS-mode.");
        }
        if (i8 != 12 && i8 != 16) {
            throw new IllegalArgumentException("IV size should be either 12 or 16 bytes");
        }
        this.f14393d = i8;
        r.a(bArr.length);
        this.f14392c = new SecretKeySpec(bArr, "AES");
        this.f14391b = d(bArr);
        this.f14390a = bArr2;
    }

    public static j4.a c(k4.l lVar) {
        if (f14388e.e()) {
            if (lVar.b().e() == 16) {
                return new b(lVar.e().d(j4.g.a()), lVar.b().c(), lVar.c().d());
            }
            throw new GeneralSecurityException("AesEaxJce only supports 16 byte tag size, not " + lVar.b().e());
        }
        throw new GeneralSecurityException("Can not use AES-EAX in FIPS-mode.");
    }

    private static w4.c d(byte[] bArr) {
        return m.b(w4.a.c(w4.b.b(bArr.length), a5.b.a(bArr, j4.g.a())));
    }

    private byte[] e(int i8, byte[] bArr, int i9, int i10) {
        byte[] bArr2 = new byte[i10 + 16];
        bArr2[15] = (byte) i8;
        System.arraycopy(bArr, i9, bArr2, 16, i10);
        return this.f14391b.a(bArr2, 16);
    }

    @Override // j4.a
    public byte[] a(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        byte[] bArr3 = this.f14390a;
        int length2 = Integer.MAX_VALUE - bArr3.length;
        int i8 = this.f14393d;
        if (length <= (length2 - i8) - 16) {
            byte[] copyOf = Arrays.copyOf(bArr3, bArr3.length + i8 + bArr.length + 16);
            byte[] a8 = p.a(this.f14393d);
            System.arraycopy(a8, 0, copyOf, this.f14390a.length, this.f14393d);
            byte[] e8 = e(0, a8, 0, a8.length);
            if (bArr2 == null) {
                bArr2 = new byte[0];
            }
            byte[] e9 = e(1, bArr2, 0, bArr2.length);
            Cipher cipher = (Cipher) f14389f.get();
            cipher.init(1, this.f14392c, new IvParameterSpec(e8));
            cipher.doFinal(bArr, 0, bArr.length, copyOf, this.f14390a.length + this.f14393d);
            byte[] e10 = e(2, copyOf, this.f14390a.length + this.f14393d, bArr.length);
            int length3 = this.f14390a.length + bArr.length + this.f14393d;
            for (int i9 = 0; i9 < 16; i9++) {
                copyOf[length3 + i9] = (byte) ((e9[i9] ^ e8[i9]) ^ e10[i9]);
            }
            return copyOf;
        }
        throw new GeneralSecurityException("plaintext too long");
    }

    @Override // j4.a
    public byte[] b(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        byte[] bArr3 = this.f14390a;
        int length2 = ((length - bArr3.length) - this.f14393d) - 16;
        if (length2 >= 0) {
            if (k0.e(bArr3, bArr)) {
                byte[] e8 = e(0, bArr, this.f14390a.length, this.f14393d);
                if (bArr2 == null) {
                    bArr2 = new byte[0];
                }
                byte[] e9 = e(1, bArr2, 0, bArr2.length);
                byte[] e10 = e(2, bArr, this.f14390a.length + this.f14393d, length2);
                int length3 = bArr.length - 16;
                byte b8 = 0;
                for (int i8 = 0; i8 < 16; i8++) {
                    b8 = (byte) (b8 | (((bArr[length3 + i8] ^ e9[i8]) ^ e8[i8]) ^ e10[i8]));
                }
                if (b8 == 0) {
                    Cipher cipher = (Cipher) f14389f.get();
                    cipher.init(1, this.f14392c, new IvParameterSpec(e8));
                    return cipher.doFinal(bArr, this.f14390a.length + this.f14393d, length2);
                }
                throw new AEADBadTagException("tag mismatch");
            }
            throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
        }
        throw new GeneralSecurityException("ciphertext too short");
    }
}
