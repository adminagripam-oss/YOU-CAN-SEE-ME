package j7;
/* loaded from: classes.dex */
abstract class b extends a {
    public static final boolean d(char c8, char c9, boolean z7) {
        if (c8 == c9) {
            return true;
        }
        if (z7) {
            char upperCase = Character.toUpperCase(c8);
            char upperCase2 = Character.toUpperCase(c9);
            return upperCase == upperCase2 || Character.toLowerCase(upperCase) == Character.toLowerCase(upperCase2);
        }
        return false;
    }
}
