package l4;

import java.nio.ByteBuffer;
/* loaded from: classes.dex */
public class e0 extends f0 {
    public e0(byte[] bArr, int i8) {
        super(bArr, i8);
    }

    @Override // l4.f0
    public int[] b(int[] iArr, int i8) {
        if (iArr.length == e() / 4) {
            int[] iArr2 = new int[16];
            c0.e(iArr2, this.f8994a);
            iArr2[12] = i8;
            System.arraycopy(iArr, 0, iArr2, 13, iArr.length);
            return iArr2;
        }
        throw new IllegalArgumentException(String.format("ChaCha20 uses 96-bit nonces, but got a %d-bit nonce", Integer.valueOf(iArr.length * 32)));
    }

    @Override // l4.f0
    public /* bridge */ /* synthetic */ byte[] c(byte[] bArr, ByteBuffer byteBuffer) {
        return super.c(bArr, byteBuffer);
    }

    @Override // l4.f0
    public /* bridge */ /* synthetic */ void d(ByteBuffer byteBuffer, byte[] bArr, byte[] bArr2) {
        super.d(byteBuffer, bArr, bArr2);
    }

    @Override // l4.f0
    public int e() {
        return 12;
    }
}
