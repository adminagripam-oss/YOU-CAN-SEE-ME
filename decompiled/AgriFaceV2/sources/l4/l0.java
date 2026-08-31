package l4;

import java.security.GeneralSecurityException;
import java.util.Arrays;
/* loaded from: classes.dex */
public abstract class l0 {
    public static byte[] a(byte[] bArr, byte[] bArr2) {
        if (bArr.length == 32) {
            int i8 = 0;
            long c8 = c(bArr, 0, 0) & 67108863;
            int i9 = 3;
            long c9 = c(bArr, 3, 2) & 67108611;
            long c10 = c(bArr, 6, 4) & 67092735;
            long c11 = c(bArr, 9, 6) & 66076671;
            long c12 = c(bArr, 12, 8) & 1048575;
            long j8 = c9 * 5;
            long j9 = c10 * 5;
            long j10 = c11 * 5;
            long j11 = c12 * 5;
            byte[] bArr3 = new byte[17];
            long j12 = 0;
            int i10 = 0;
            long j13 = 0;
            long j14 = 0;
            long j15 = 0;
            long j16 = 0;
            while (i10 < bArr2.length) {
                b(bArr3, bArr2, i10);
                long c13 = j16 + c(bArr3, i8, i8);
                long c14 = j12 + c(bArr3, i9, 2);
                long c15 = j13 + c(bArr3, 6, 4);
                long c16 = j14 + c(bArr3, 9, 6);
                long c17 = j15 + (c(bArr3, 12, 8) | (bArr3[16] << 24));
                long j17 = (c13 * c8) + (c14 * j11) + (c15 * j10) + (c16 * j9) + (c17 * j8);
                long j18 = (c13 * c9) + (c14 * c8) + (c15 * j11) + (c16 * j10) + (c17 * j9) + (j17 >> 26);
                long j19 = (c13 * c10) + (c14 * c9) + (c15 * c8) + (c16 * j11) + (c17 * j10) + (j18 >> 26);
                long j20 = (c13 * c11) + (c14 * c10) + (c15 * c9) + (c16 * c8) + (c17 * j11) + (j19 >> 26);
                long j21 = (c13 * c12) + (c14 * c11) + (c15 * c10) + (c16 * c9) + (c17 * c8) + (j20 >> 26);
                long j22 = (j17 & 67108863) + ((j21 >> 26) * 5);
                j12 = (j18 & 67108863) + (j22 >> 26);
                i10 += 16;
                j13 = j19 & 67108863;
                j14 = j20 & 67108863;
                j15 = j21 & 67108863;
                i9 = 3;
                j16 = j22 & 67108863;
                i8 = 0;
            }
            long j23 = j13 + (j12 >> 26);
            long j24 = j23 & 67108863;
            long j25 = j14 + (j23 >> 26);
            long j26 = j25 & 67108863;
            long j27 = j15 + (j25 >> 26);
            long j28 = j27 & 67108863;
            long j29 = j16 + ((j27 >> 26) * 5);
            long j30 = j29 & 67108863;
            long j31 = (j12 & 67108863) + (j29 >> 26);
            long j32 = j30 + 5;
            long j33 = j32 & 67108863;
            long j34 = (j32 >> 26) + j31;
            long j35 = j24 + (j34 >> 26);
            long j36 = j26 + (j35 >> 26);
            long j37 = (j28 + (j36 >> 26)) - 67108864;
            long j38 = j37 >> 63;
            long j39 = j30 & j38;
            long j40 = j31 & j38;
            long j41 = j24 & j38;
            long j42 = j26 & j38;
            long j43 = j28 & j38;
            long j44 = ~j38;
            long j45 = (j34 & 67108863 & j44) | j40;
            long j46 = (j35 & 67108863 & j44) | j41;
            long j47 = (j36 & 67108863 & j44) | j42;
            long d8 = ((j39 | (j33 & j44) | (j45 << 26)) & 4294967295L) + d(bArr, 16);
            long j48 = d8 & 4294967295L;
            long d9 = (((j45 >> 6) | (j46 << 20)) & 4294967295L) + d(bArr, 20) + (d8 >> 32);
            long d10 = (((j46 >> 12) | (j47 << 14)) & 4294967295L) + d(bArr, 24) + (d9 >> 32);
            byte[] bArr4 = new byte[16];
            e(bArr4, j48, 0);
            e(bArr4, d9 & 4294967295L, 4);
            e(bArr4, d10 & 4294967295L, 8);
            e(bArr4, ((((j47 >> 18) | (((j37 & j44) | j43) << 8)) & 4294967295L) + d(bArr, 28) + (d10 >> 32)) & 4294967295L, 12);
            return bArr4;
        }
        throw new IllegalArgumentException("The key length in bytes must be 32.");
    }

    private static void b(byte[] bArr, byte[] bArr2, int i8) {
        int min = Math.min(16, bArr2.length - i8);
        System.arraycopy(bArr2, i8, bArr, 0, min);
        bArr[min] = 1;
        if (min != 16) {
            Arrays.fill(bArr, min + 1, bArr.length, (byte) 0);
        }
    }

    private static long c(byte[] bArr, int i8, int i9) {
        return (d(bArr, i8) >> i9) & 67108863;
    }

    private static long d(byte[] bArr, int i8) {
        return (((bArr[i8 + 3] & 255) << 24) | (bArr[i8] & 255) | ((bArr[i8 + 1] & 255) << 8) | ((bArr[i8 + 2] & 255) << 16)) & 4294967295L;
    }

    private static void e(byte[] bArr, long j8, int i8) {
        int i9 = 0;
        while (i9 < 4) {
            bArr[i8 + i9] = (byte) (255 & j8);
            i9++;
            j8 >>= 8;
        }
    }

    public static void f(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (!z4.f.b(a(bArr, bArr2), bArr3)) {
            throw new GeneralSecurityException("invalid MAC");
        }
    }
}
