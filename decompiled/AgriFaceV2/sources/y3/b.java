package y3;
/* loaded from: classes.dex */
public abstract class b {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(int i8, int i9) {
        if (i9 >= 0) {
            int i10 = i8 + (i8 >> 1) + 1;
            if (i10 < i9) {
                int highestOneBit = Integer.highestOneBit(i9 - 1);
                i10 = highestOneBit + highestOneBit;
            }
            if (i10 < 0) {
                return Integer.MAX_VALUE;
            }
            return i10;
        }
        throw new AssertionError("cannot store more than MAX_VALUE elements");
    }
}
