package x4;

import j4.g;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import o4.b;
import v4.f;
import w4.c;
import z4.i;
import z4.r;
/* loaded from: classes.dex */
public final class a implements c {

    /* renamed from: d  reason: collision with root package name */
    public static final b.EnumC0135b f12714d = b.EnumC0135b.f9848e;

    /* renamed from: e  reason: collision with root package name */
    private static final ThreadLocal f12715e = new C0162a();

    /* renamed from: a  reason: collision with root package name */
    private final SecretKey f12716a;

    /* renamed from: b  reason: collision with root package name */
    private byte[] f12717b;

    /* renamed from: c  reason: collision with root package name */
    private byte[] f12718c;

    /* renamed from: x4.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0162a extends ThreadLocal {
        C0162a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public Cipher initialValue() {
            try {
                return (Cipher) i.f14427b.a("AES/ECB/NoPadding");
            } catch (GeneralSecurityException e8) {
                throw new IllegalStateException(e8);
            }
        }
    }

    private a(byte[] bArr) {
        r.a(bArr.length);
        this.f12716a = new SecretKeySpec(bArr, "AES");
        d();
    }

    static int b(int i8) {
        if (i8 == 0) {
            return 1;
        }
        return ((i8 - 1) / 16) + 1;
    }

    public static c c(w4.a aVar) {
        return new a(aVar.d().d(g.a()));
    }

    private void d() {
        Cipher e8 = e();
        e8.init(1, this.f12716a);
        byte[] b8 = f.b(e8.doFinal(new byte[16]));
        this.f12717b = b8;
        this.f12718c = f.b(b8);
    }

    private static Cipher e() {
        if (f12714d.e()) {
            return (Cipher) f12715e.get();
        }
        throw new GeneralSecurityException("Can not use AES-CMAC in FIPS-mode.");
    }

    private static void f(byte[] bArr, byte[] bArr2, int i8, byte[] bArr3) {
        for (int i9 = 0; i9 < 16; i9++) {
            bArr3[i9] = (byte) (bArr[i9] ^ bArr2[i9 + i8]);
        }
    }

    @Override // w4.c
    public byte[] a(byte[] bArr, int i8) {
        if (i8 <= 16) {
            Cipher e8 = e();
            e8.init(1, this.f12716a);
            int b8 = b(bArr.length);
            byte[] d8 = b8 * 16 == bArr.length ? z4.f.d(bArr, (b8 - 1) * 16, this.f12717b, 0, 16) : z4.f.e(f.a(Arrays.copyOfRange(bArr, (b8 - 1) * 16, bArr.length)), this.f12718c);
            byte[] bArr2 = new byte[16];
            byte[] bArr3 = new byte[16];
            for (int i9 = 0; i9 < b8 - 1; i9++) {
                f(bArr2, bArr, i9 * 16, bArr3);
                if (e8.doFinal(bArr3, 0, 16, bArr2) != 16) {
                    throw new IllegalStateException("Cipher didn't write full block");
                }
            }
            f(bArr2, d8, 0, bArr3);
            if (e8.doFinal(bArr3, 0, 16, bArr2) == 16) {
                return 16 == i8 ? bArr2 : Arrays.copyOf(bArr2, i8);
            }
            throw new IllegalStateException("Cipher didn't write full block");
        }
        throw new InvalidAlgorithmParameterException("outputLength too large, max is 16 bytes");
    }
}
