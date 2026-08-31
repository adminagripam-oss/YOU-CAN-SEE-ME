package z4;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import k4.a0;
import l4.g0;
import t4.k0;
/* loaded from: classes.dex */
public final class g implements j4.a {

    /* renamed from: a  reason: collision with root package name */
    private final g0 f14421a;

    /* renamed from: b  reason: collision with root package name */
    private final byte[] f14422b;

    private g(byte[] bArr, byte[] bArr2) {
        this.f14421a = new g0(bArr);
        this.f14422b = bArr2;
    }

    public static j4.a c(a0 a0Var) {
        return new g(a0Var.e().d(j4.g.a()), a0Var.c().d());
    }

    private byte[] d(byte[] bArr, byte[] bArr2) {
        if (bArr.length >= 28) {
            byte[] copyOf = Arrays.copyOf(bArr, 12);
            return this.f14421a.a(ByteBuffer.wrap(bArr, 12, bArr.length - 12), copyOf, bArr2);
        }
        throw new GeneralSecurityException("ciphertext too short");
    }

    private byte[] e(byte[] bArr, byte[] bArr2) {
        ByteBuffer allocate = ByteBuffer.allocate(bArr.length + 12 + 16);
        byte[] a8 = p.a(12);
        allocate.put(a8);
        this.f14421a.b(allocate, a8, bArr, bArr2);
        return allocate.array();
    }

    @Override // j4.a
    public byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] e8 = e(bArr, bArr2);
        byte[] bArr3 = this.f14422b;
        return bArr3.length == 0 ? e8 : f.a(bArr3, e8);
    }

    @Override // j4.a
    public byte[] b(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = this.f14422b;
        if (bArr3.length == 0) {
            return d(bArr, bArr2);
        }
        if (k0.e(bArr3, bArr)) {
            return d(Arrays.copyOfRange(bArr, this.f14422b.length, bArr.length), bArr2);
        }
        throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
    }
}
