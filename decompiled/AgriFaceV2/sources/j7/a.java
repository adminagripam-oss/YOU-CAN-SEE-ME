package j7;
/* loaded from: classes.dex */
abstract class a {
    public static final int a(int i8) {
        if (new g7.c(2, 36).l(i8)) {
            return i8;
        }
        throw new IllegalArgumentException("radix " + i8 + " was not in valid range " + new g7.c(2, 36));
    }

    public static final int b(char c8, int i8) {
        return Character.digit((int) c8, i8);
    }

    public static final boolean c(char c8) {
        return Character.isWhitespace(c8) || Character.isSpaceChar(c8);
    }
}
