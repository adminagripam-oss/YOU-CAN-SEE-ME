package x6;
/* loaded from: classes.dex */
public abstract class c {
    private static final int a(int i8, int i9, int i10) {
        return c(c(i8, i10) - c(i9, i10), i10);
    }

    public static final int b(int i8, int i9, int i10) {
        if (i10 > 0) {
            return i8 >= i9 ? i9 : i9 - a(i9, i8, i10);
        } else if (i10 < 0) {
            return i8 <= i9 ? i9 : i9 + a(i8, i9, -i10);
        } else {
            throw new IllegalArgumentException("Step is zero.");
        }
    }

    private static final int c(int i8, int i9) {
        int i10 = i8 % i9;
        return i10 >= 0 ? i10 : i10 + i9;
    }
}
