package c4;
/* loaded from: classes.dex */
public abstract class w {
    public static int a(int i8) {
        boolean z7 = true;
        if (i8 != 0 && i8 != 1) {
            if (i8 == 2) {
                i8 = 2;
            } else {
                z7 = false;
            }
        }
        k3.q.c(z7, "granularity %d must be a Granularity.GRANULARITY_* constant", Integer.valueOf(i8));
        return i8;
    }

    public static String b(int i8) {
        if (i8 != 0) {
            if (i8 != 1) {
                if (i8 == 2) {
                    return "GRANULARITY_FINE";
                }
                throw new IllegalArgumentException();
            }
            return "GRANULARITY_COARSE";
        }
        return "GRANULARITY_PERMISSION_LEVEL";
    }
}
