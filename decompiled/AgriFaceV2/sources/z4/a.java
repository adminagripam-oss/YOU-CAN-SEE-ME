package z4;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import o4.b;
/* loaded from: classes.dex */
public final class a implements l {

    /* renamed from: d  reason: collision with root package name */
    public static final b.EnumC0135b f14383d = b.EnumC0135b.f9849f;

    /* renamed from: e  reason: collision with root package name */
    private static final ThreadLocal f14384e = new C0171a();

    /* renamed from: a  reason: collision with root package name */
    private final SecretKeySpec f14385a;

    /* renamed from: b  reason: collision with root package name */
    private final int f14386b;

    /* renamed from: c  reason: collision with root package name */
    private final int f14387c;

    /* renamed from: z4.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0171a extends ThreadLocal {
        C0171a() {
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

    public a(byte[] bArr, int i8) {
        if (!f14383d.e()) {
            throw new GeneralSecurityException("Can not use AES-CTR in FIPS-mode, as BoringCrypto module is not available.");
        }
        r.a(bArr.length);
        this.f14385a = new SecretKeySpec(bArr, "AES");
        int blockSize = ((Cipher) f14384e.get()).getBlockSize();
        this.f14387c = blockSize;
        if (i8 < 12 || i8 > blockSize) {
            throw new GeneralSecurityException("invalid IV size");
        }
        this.f14386b = i8;
    }

    private void c(byte[] bArr, int i8, int i9, byte[] bArr2, int i10, byte[] bArr3, boolean z7) {
        Cipher cipher = (Cipher) f14384e.get();
        byte[] bArr4 = new byte[this.f14387c];
        System.arraycopy(bArr3, 0, bArr4, 0, this.f14386b);
        cipher.init(z7 ? 1 : 2, this.f14385a, new IvParameterSpec(bArr4));
        if (cipher.doFinal(bArr, i8, i9, bArr2, i10) != i9) {
            throw new GeneralSecurityException("stored output's length does not match input's length");
        }
    }

    @Override // z4.l
    public byte[] a(byte[] bArr) {
        int length = bArr.length;
        int i8 = this.f14386b;
        if (length > Integer.MAX_VALUE - i8) {
            throw new GeneralSecurityException("plaintext length can not exceed " + (Integer.MAX_VALUE - this.f14386b));
        }
        byte[] bArr2 = new byte[bArr.length + i8];
        byte[] a8 = p.a(i8);
        System.arraycopy(a8, 0, bArr2, 0, this.f14386b);
        c(bArr, 0, bArr.length, bArr2, this.f14386b, a8, true);
        return bArr2;
    }

    @Override // z4.l
    public byte[] b(byte[] bArr) {
        int length = bArr.length;
        int i8 = this.f14386b;
        if (length >= i8) {
            byte[] bArr2 = new byte[i8];
            System.arraycopy(bArr, 0, bArr2, 0, i8);
            int length2 = bArr.length;
            int i9 = this.f14386b;
            byte[] bArr3 = new byte[length2 - i9];
            c(bArr, i9, bArr.length - i9, bArr3, 0, bArr2, false);
            return bArr3;
        }
        throw new GeneralSecurityException("ciphertext too short");
    }
}
