package c4;
/* loaded from: classes.dex */
public abstract class t {
    public static int a(int i8) {
        boolean z7 = true;
        if (i8 != 100 && i8 != 102 && i8 != 104) {
            if (i8 == 105) {
                i8 = 105;
            } else {
                z7 = false;
            }
        }
        k3.q.c(z7, "priority %d must be a Priority.PRIORITY_* constant", Integer.valueOf(i8));
        return i8;
    }

    public static String b(int i8) {
        if (i8 != 100) {
            if (i8 != 102) {
                if (i8 != 104) {
                    if (i8 == 105) {
                        return "PASSIVE";
                    }
                    throw new IllegalArgumentException();
                }
                return "LOW_POWER";
            }
            return "BALANCED_POWER_ACCURACY";
        }
        return "HIGH_ACCURACY";
    }
}
