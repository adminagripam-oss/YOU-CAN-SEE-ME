package v4;

import java.util.Arrays;
/* loaded from: classes.dex */
public abstract class f {
    public static byte[] a(byte[] bArr) {
        if (bArr.length < 16) {
            byte[] copyOf = Arrays.copyOf(bArr, 16);
            copyOf[bArr.length] = Byte.MIN_VALUE;
            return copyOf;
        }
        throw new IllegalArgumentException("x must be smaller than a block.");
    }

    public static byte[] b(byte[] bArr) {
        if (bArr.length == 16) {
            byte[] bArr2 = new byte[16];
            for (int i8 = 0; i8 < 16; i8++) {
                byte b8 = (byte) ((bArr[i8] << 1) & 254);
                bArr2[i8] = b8;
                if (i8 < 15) {
                    bArr2[i8] = (byte) (((byte) ((bArr[i8 + 1] >> 7) & 1)) | b8);
                }
            }
            bArr2[15] = (byte) (((byte) ((bArr[0] >> 7) & 135)) ^ bArr2[15]);
            return bArr2;
        }
        throw new IllegalArgumentException("value must be a block.");
    }
}
