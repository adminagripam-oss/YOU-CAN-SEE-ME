package g1;
/* loaded from: classes.dex */
public abstract class g {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f7101a = new Object();

    /* renamed from: b  reason: collision with root package name */
    private static char[] f7102b = new char[24];

    private static int a(int i8, int i9, boolean z7, int i10) {
        if (i8 > 99 || (z7 && i10 >= 3)) {
            return i9 + 3;
        }
        if (i8 > 9 || (z7 && i10 >= 2)) {
            return i9 + 2;
        }
        if (z7 || i8 > 0) {
            return i9 + 1;
        }
        return 0;
    }

    public static void b(long j8, StringBuilder sb) {
        synchronized (f7101a) {
            sb.append(f7102b, 0, c(j8, 0));
        }
    }

    private static int c(long j8, int i8) {
        char c8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        long j9 = j8;
        if (f7102b.length < i8) {
            f7102b = new char[i8];
        }
        char[] cArr = f7102b;
        int i14 = (j9 > 0L ? 1 : (j9 == 0L ? 0 : -1));
        if (i14 == 0) {
            int i15 = i8 - 1;
            while (i15 > 0) {
                cArr[0] = ' ';
            }
            cArr[0] = '0';
            return 1;
        }
        if (i14 > 0) {
            c8 = '+';
        } else {
            j9 = -j9;
            c8 = '-';
        }
        int i16 = (int) (j9 % 1000);
        int floor = (int) Math.floor(j9 / 1000);
        if (floor > 86400) {
            i9 = floor / 86400;
            floor -= 86400 * i9;
        } else {
            i9 = 0;
        }
        if (floor > 3600) {
            i10 = floor / 3600;
            floor -= i10 * 3600;
        } else {
            i10 = 0;
        }
        if (floor > 60) {
            int i17 = floor / 60;
            i11 = floor - (i17 * 60);
            i12 = i17;
        } else {
            i11 = floor;
            i12 = 0;
        }
        if (i8 != 0) {
            int a8 = a(i9, 1, false, 0);
            int a9 = a8 + a(i10, 1, a8 > 0, 2);
            int a10 = a9 + a(i12, 1, a9 > 0, 2);
            int a11 = a10 + a(i11, 1, a10 > 0, 2);
            i13 = 0;
            for (int a12 = a11 + a(i16, 2, true, a11 > 0 ? 3 : 0) + 1; a12 < i8; a12++) {
                cArr[i13] = ' ';
                i13++;
            }
        } else {
            i13 = 0;
        }
        cArr[i13] = c8;
        int i18 = i13 + 1;
        boolean z7 = i8 != 0;
        int d8 = d(cArr, i9, 'd', i18, false, 0);
        int d9 = d(cArr, i10, 'h', d8, d8 != i18, z7 ? 2 : 0);
        int d10 = d(cArr, i12, 'm', d9, d9 != i18, z7 ? 2 : 0);
        int d11 = d(cArr, i11, 's', d10, d10 != i18, z7 ? 2 : 0);
        int d12 = d(cArr, i16, 'm', d11, true, (!z7 || d11 == i18) ? 0 : 3);
        cArr[d12] = 's';
        return d12 + 1;
    }

    private static int d(char[] cArr, int i8, char c8, int i9, boolean z7, int i10) {
        int i11;
        if (z7 || i8 > 0) {
            if ((!z7 || i10 < 3) && i8 <= 99) {
                i11 = i9;
            } else {
                int i12 = i8 / 100;
                cArr[i9] = (char) (i12 + 48);
                i11 = i9 + 1;
                i8 -= i12 * 100;
            }
            if ((z7 && i10 >= 2) || i8 > 9 || i9 != i11) {
                int i13 = i8 / 10;
                cArr[i11] = (char) (i13 + 48);
                i11++;
                i8 -= i13 * 10;
            }
            cArr[i11] = (char) (i8 + 48);
            int i14 = i11 + 1;
            cArr[i14] = c8;
            return i14 + 1;
        }
        return i9;
    }
}
