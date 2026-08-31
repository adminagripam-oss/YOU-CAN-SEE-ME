package r;
/* loaded from: classes.dex */
public abstract class c4 {
    public static int a(int i8) {
        if (i8 != 0) {
            if (i8 != 1) {
                if (i8 == 2) {
                    return 2;
                }
                throw new IllegalArgumentException("The given lens facing integer: " + i8 + " can not be recognized.");
            }
            return 1;
        }
        return 0;
    }
}
