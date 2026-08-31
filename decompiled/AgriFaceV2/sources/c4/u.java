package c4;
/* loaded from: classes.dex */
public abstract class u {
    public static int a(int i8) {
        boolean z7 = true;
        if (i8 != 0 && i8 != 1) {
            if (i8 == 2) {
                i8 = 2;
            } else {
                z7 = false;
            }
        }
        k3.q.c(z7, "throttle behavior %d must be a ThrottleBehavior.THROTTLE_* constant", Integer.valueOf(i8));
        return i8;
    }

    public static String b(int i8) {
        if (i8 != 0) {
            if (i8 != 1) {
                if (i8 == 2) {
                    return "THROTTLE_NEVER";
                }
                throw new IllegalArgumentException();
            }
            return "THROTTLE_ALWAYS";
        }
        return "THROTTLE_BACKGROUND";
    }
}
