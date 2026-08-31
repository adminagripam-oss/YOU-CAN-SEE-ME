package l4;

import java.nio.ByteBuffer;
/* loaded from: classes.dex */
public class i0 extends f0 {
    public i0(byte[] bArr, int i8) {
        super(bArr, i8);
    }

    @Override // l4.f0
    int[] b(int[] iArr, int i8) {
        if (iArr.length == e() / 4) {
            int[] iArr2 = new int[16];
            c0.e(iArr2, c0.b(this.f8994a, iArr));
            iArr2[12] = i8;
            iArr2[13] = 0;
            iArr2[14] = iArr[4];
            iArr2[15] = iArr[5];
            return iArr2;
        }
        throw new IllegalArgumentException(String.format("XChaCha20 uses 192-bit nonces, but got a %d-bit nonce", Integer.valueOf(iArr.length * 32)));
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
    int e() {
        return 24;
    }
}
