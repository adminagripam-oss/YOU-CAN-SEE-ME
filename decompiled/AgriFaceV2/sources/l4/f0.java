package l4;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
/* loaded from: classes.dex */
abstract class f0 {

    /* renamed from: a  reason: collision with root package name */
    int[] f8994a;

    /* renamed from: b  reason: collision with root package name */
    private final int f8995b;

    public f0(byte[] bArr, int i8) {
        if (bArr.length != 32) {
            throw new InvalidKeyException("The key length in bytes must be 32.");
        }
        this.f8994a = c0.h(bArr);
        this.f8995b = i8;
    }

    private void f(byte[] bArr, ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        if (bArr.length != e()) {
            throw new GeneralSecurityException("The nonce length (in bytes) must be " + e());
        }
        int remaining = byteBuffer2.remaining();
        int i8 = (remaining / 64) + 1;
        for (int i9 = 0; i9 < i8; i9++) {
            ByteBuffer a8 = a(bArr, this.f8995b + i9);
            if (i9 == i8 - 1) {
                z4.f.c(byteBuffer, byteBuffer2, a8, remaining % 64);
            } else {
                z4.f.c(byteBuffer, byteBuffer2, a8, 64);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteBuffer a(byte[] bArr, int i8) {
        int[] b8 = b(c0.h(bArr), i8);
        int[] iArr = (int[]) b8.clone();
        c0.f(iArr);
        for (int i9 = 0; i9 < b8.length; i9++) {
            b8[i9] = b8[i9] + iArr[i9];
        }
        ByteBuffer order = ByteBuffer.allocate(64).order(ByteOrder.LITTLE_ENDIAN);
        order.asIntBuffer().put(b8, 0, 16);
        return order;
    }

    abstract int[] b(int[] iArr, int i8);

    public byte[] c(byte[] bArr, ByteBuffer byteBuffer) {
        ByteBuffer allocate = ByteBuffer.allocate(byteBuffer.remaining());
        f(bArr, allocate, byteBuffer);
        return allocate.array();
    }

    public void d(ByteBuffer byteBuffer, byte[] bArr, byte[] bArr2) {
        if (byteBuffer.remaining() < bArr2.length) {
            throw new IllegalArgumentException("Given ByteBuffer output is too small");
        }
        f(bArr, byteBuffer, ByteBuffer.wrap(bArr2));
    }

    abstract int e();
}
