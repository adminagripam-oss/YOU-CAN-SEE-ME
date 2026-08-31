package l4;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.Arrays;
/* loaded from: classes.dex */
abstract class c0 {

    /* renamed from: a  reason: collision with root package name */
    private static final int[] f8984a = h(new byte[]{101, 120, 112, 97, 110, 100, 32, 51, 50, 45, 98, 121, 116, 101, 32, 107});

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] a(byte[] bArr, byte[] bArr2) {
        return g(b(h(bArr), h(bArr2)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int[] b(int[] iArr, int[] iArr2) {
        e(r0, iArr);
        int[] iArr3 = {0, 0, 0, 0, iArr3[12], iArr3[13], iArr3[14], iArr3[15], 0, 0, 0, 0, iArr2[0], iArr2[1], iArr2[2], iArr2[3]};
        f(iArr3);
        return Arrays.copyOf(iArr3, 8);
    }

    static void c(int[] iArr, int i8, int i9, int i10, int i11) {
        int i12 = iArr[i8] + iArr[i9];
        iArr[i8] = i12;
        int d8 = d(i12 ^ iArr[i11], 16);
        iArr[i11] = d8;
        int i13 = iArr[i10] + d8;
        iArr[i10] = i13;
        int d9 = d(iArr[i9] ^ i13, 12);
        iArr[i9] = d9;
        int i14 = iArr[i8] + d9;
        iArr[i8] = i14;
        int d10 = d(iArr[i11] ^ i14, 8);
        iArr[i11] = d10;
        int i15 = iArr[i10] + d10;
        iArr[i10] = i15;
        iArr[i9] = d(iArr[i9] ^ i15, 7);
    }

    private static int d(int i8, int i9) {
        return (i8 >>> (-i9)) | (i8 << i9);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void e(int[] iArr, int[] iArr2) {
        int[] iArr3 = f8984a;
        System.arraycopy(iArr3, 0, iArr, 0, iArr3.length);
        System.arraycopy(iArr2, 0, iArr, iArr3.length, 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void f(int[] iArr) {
        for (int i8 = 0; i8 < 10; i8++) {
            c(iArr, 0, 4, 8, 12);
            c(iArr, 1, 5, 9, 13);
            c(iArr, 2, 6, 10, 14);
            c(iArr, 3, 7, 11, 15);
            c(iArr, 0, 5, 10, 15);
            c(iArr, 1, 6, 11, 12);
            c(iArr, 2, 7, 8, 13);
            c(iArr, 3, 4, 9, 14);
        }
    }

    static byte[] g(int[] iArr) {
        ByteBuffer order = ByteBuffer.allocate(iArr.length * 4).order(ByteOrder.LITTLE_ENDIAN);
        order.asIntBuffer().put(iArr);
        return order.array();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int[] h(byte[] bArr) {
        if (bArr.length % 4 == 0) {
            IntBuffer asIntBuffer = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).asIntBuffer();
            int[] iArr = new int[asIntBuffer.remaining()];
            asIntBuffer.get(iArr);
            return iArr;
        }
        throw new IllegalArgumentException("invalid input length");
    }
}
