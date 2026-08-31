package z4;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import k4.p1;
import l4.j0;
import t4.k0;
/* loaded from: classes.dex */
public final class s implements j4.a {

    /* renamed from: a  reason: collision with root package name */
    private final j0 f14453a;

    /* renamed from: b  reason: collision with root package name */
    private final byte[] f14454b;

    private s(byte[] bArr, byte[] bArr2) {
        this.f14453a = new j0(bArr);
        this.f14454b = bArr2;
    }

    public static j4.a c(p1 p1Var) {
        return new s(p1Var.e().d(j4.g.a()), p1Var.c().d());
    }

    private byte[] d(byte[] bArr, byte[] bArr2) {
        if (bArr.length >= 40) {
            byte[] copyOf = Arrays.copyOf(bArr, 24);
            return this.f14453a.a(ByteBuffer.wrap(bArr, 24, bArr.length - 24), copyOf, bArr2);
        }
        throw new GeneralSecurityException("ciphertext too short");
    }

    private byte[] e(byte[] bArr, byte[] bArr2) {
        ByteBuffer allocate = ByteBuffer.allocate(bArr.length + 24 + 16);
        byte[] a8 = p.a(24);
        allocate.put(a8);
        this.f14453a.b(allocate, a8, bArr, bArr2);
        return allocate.array();
    }

    @Override // j4.a
    public byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] e8 = e(bArr, bArr2);
        byte[] bArr3 = this.f14454b;
        return bArr3.length == 0 ? e8 : f.a(bArr3, e8);
    }

    @Override // j4.a
    public byte[] b(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = this.f14454b;
        if (bArr3.length == 0) {
            return d(bArr, bArr2);
        }
        if (k0.e(bArr3, bArr)) {
            return d(Arrays.copyOfRange(bArr, this.f14454b.length, bArr.length), bArr2);
        }
        throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
    }
}
