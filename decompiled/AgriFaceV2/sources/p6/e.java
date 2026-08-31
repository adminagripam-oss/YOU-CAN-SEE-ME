package p6;
/* loaded from: classes.dex */
public final class e implements AutoCloseable {
    private e(String str) {
        b(str);
    }

    public static void b(String str) {
        z1.a.c(d(str));
    }

    public static void c(String str, int i8) {
        z1.a.a(d(str), i8);
    }

    private static String d(String str) {
        if (str.length() < 124) {
            return str;
        }
        return str.substring(0, 124) + "...";
    }

    public static void f() {
        z1.a.f();
    }

    public static void g(String str, int i8) {
        z1.a.d(d(str), i8);
    }

    public static e j(String str) {
        return new e(str);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        f();
    }
}
