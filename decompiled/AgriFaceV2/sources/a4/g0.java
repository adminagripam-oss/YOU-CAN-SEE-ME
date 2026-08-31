package a4;
/* loaded from: classes.dex */
abstract class g0 {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(int i8) {
        return (i8 < 32 ? 4 : 2) * (i8 + 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b(Object obj, Object obj2, int i8, Object obj3, int[] iArr, Object[] objArr, Object[] objArr2) {
        int i9;
        int i10;
        int a8 = h0.a(obj);
        int i11 = a8 & i8;
        int c8 = c(obj3, i11);
        if (c8 != 0) {
            int i12 = ~i8;
            int i13 = a8 & i12;
            int i14 = -1;
            while (true) {
                i9 = c8 - 1;
                i10 = iArr[i9];
                if ((i10 & i12) != i13 || !ze.a(obj, objArr[i9]) || (objArr2 != null && !ze.a(obj2, objArr2[i9]))) {
                    int i15 = i10 & i8;
                    if (i15 == 0) {
                        break;
                    }
                    i14 = i9;
                    c8 = i15;
                }
            }
            int i16 = i10 & i8;
            if (i14 == -1) {
                e(obj3, i11, i16);
            } else {
                iArr[i14] = (i16 & i8) | (iArr[i14] & i12);
            }
            return i9;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int c(Object obj, int i8) {
        return obj instanceof byte[] ? ((byte[]) obj)[i8] & 255 : obj instanceof short[] ? (char) ((short[]) obj)[i8] : ((int[]) obj)[i8];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object d(int i8) {
        if (i8 >= 2 && i8 <= 1073741824 && Integer.highestOneBit(i8) == i8) {
            return i8 <= 256 ? new byte[i8] : i8 <= 65536 ? new short[i8] : new int[i8];
        }
        throw new IllegalArgumentException("must be power of 2 between 2^1 and 2^30: " + i8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void e(Object obj, int i8, int i9) {
        if (obj instanceof byte[]) {
            ((byte[]) obj)[i8] = (byte) i9;
        } else if (obj instanceof short[]) {
            ((short[]) obj)[i8] = (short) i9;
        } else {
            ((int[]) obj)[i8] = i9;
        }
    }
}
