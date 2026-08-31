package j7;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class k extends j {
    public static Long b(String str) {
        d7.k.e(str, "<this>");
        return c(str, 10);
    }

    public static final Long c(String str, int i8) {
        boolean z7;
        d7.k.e(str, "<this>");
        a.a(i8);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        int i9 = 0;
        char charAt = str.charAt(0);
        long j8 = -9223372036854775807L;
        if (d7.k.f(charAt, 48) < 0) {
            z7 = true;
            if (length == 1) {
                return null;
            }
            if (charAt == '-') {
                j8 = Long.MIN_VALUE;
                i9 = 1;
            } else if (charAt != '+') {
                return null;
            } else {
                z7 = false;
                i9 = 1;
            }
        } else {
            z7 = false;
        }
        long j9 = -256204778801521550L;
        long j10 = 0;
        long j11 = -256204778801521550L;
        while (i9 < length) {
            int b8 = a.b(str.charAt(i9), i8);
            if (b8 < 0) {
                return null;
            }
            if (j10 < j11) {
                if (j11 == j9) {
                    j11 = j8 / i8;
                    if (j10 < j11) {
                    }
                }
                return null;
            }
            long j12 = j10 * i8;
            long j13 = b8;
            if (j12 < j8 + j13) {
                return null;
            }
            j10 = j12 - j13;
            i9++;
            j9 = -256204778801521550L;
        }
        return z7 ? Long.valueOf(j10) : Long.valueOf(-j10);
    }
}
