package l4;

import java.security.GeneralSecurityException;
import java.util.Arrays;
import k4.k1;
/* loaded from: classes.dex */
public final class n0 implements j4.a {

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f9015a;

    /* renamed from: b  reason: collision with root package name */
    private final int f9016b;

    /* renamed from: c  reason: collision with root package name */
    private final w4.c f9017c;

    private n0(byte[] bArr, a5.a aVar, int i8) {
        this.f9017c = d(bArr);
        this.f9015a = aVar.d();
        this.f9016b = i8;
    }

    public static j4.a c(k1 k1Var) {
        if (k1Var.b().c() < 8 || k1Var.b().c() > 12) {
            throw new GeneralSecurityException("invalid salt size");
        }
        return new n0(k1Var.e().d(j4.g.a()), k1Var.c(), k1Var.b().c());
    }

    private static w4.c d(byte[] bArr) {
        return z4.m.b(w4.a.c(w4.b.b(bArr.length), a5.b.a(bArr, j4.g.a())));
    }

    private byte[] e(byte[] bArr) {
        byte[] bArr2 = {0, 1, 88, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        byte[] bArr3 = {0, 2, 88, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        if (bArr.length > 12 || bArr.length < 8) {
            throw new GeneralSecurityException("invalid salt size");
        }
        System.arraycopy(bArr, 0, bArr2, 4, bArr.length);
        System.arraycopy(bArr, 0, bArr3, 4, bArr.length);
        byte[] bArr4 = new byte[32];
        System.arraycopy(this.f9017c.a(bArr2, 16), 0, bArr4, 0, 16);
        System.arraycopy(this.f9017c.a(bArr3, 16), 0, bArr4, 16, 16);
        return bArr4;
    }

    @Override // j4.a
    public byte[] a(byte[] bArr, byte[] bArr2) {
        if (bArr != null) {
            byte[] a8 = z4.p.a(this.f9016b + 12);
            byte[] copyOf = Arrays.copyOf(a8, this.f9016b);
            int i8 = this.f9016b;
            byte[] copyOfRange = Arrays.copyOfRange(a8, i8, i8 + 12);
            byte[] b8 = new d0(e(copyOf)).b(copyOfRange, bArr, this.f9015a.length + this.f9016b + copyOfRange.length, bArr2);
            byte[] bArr3 = this.f9015a;
            System.arraycopy(bArr3, 0, b8, 0, bArr3.length);
            System.arraycopy(a8, 0, b8, this.f9015a.length, a8.length);
            return b8;
        }
        throw new NullPointerException("plaintext is null");
    }

    @Override // j4.a
    public byte[] b(byte[] bArr, byte[] bArr2) {
        if (bArr != null) {
            int length = bArr.length;
            byte[] bArr3 = this.f9015a;
            if (length >= bArr3.length + this.f9016b + 12 + 16) {
                if (t4.k0.e(bArr3, bArr)) {
                    int length2 = this.f9015a.length + this.f9016b;
                    int i8 = length2 + 12;
                    return new d0(e(Arrays.copyOfRange(bArr, this.f9015a.length, length2))).a(Arrays.copyOfRange(bArr, length2, i8), bArr, i8, bArr2);
                }
                throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
            }
            throw new GeneralSecurityException("ciphertext too short");
        }
        throw new NullPointerException("ciphertext is null");
    }
}
