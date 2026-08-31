package d7;
/* loaded from: classes.dex */
public abstract class v {
    public static Object a(Object obj, int i8) {
        if (obj != null && !c(obj, i8)) {
            f(obj, "kotlin.jvm.functions.Function" + i8);
        }
        return obj;
    }

    public static int b(Object obj) {
        if (obj instanceof h) {
            return ((h) obj).c();
        }
        if (obj instanceof c7.a) {
            return 0;
        }
        if (obj instanceof c7.l) {
            return 1;
        }
        if (obj instanceof c7.p) {
            return 2;
        }
        return obj instanceof c7.q ? 3 : -1;
    }

    public static boolean c(Object obj, int i8) {
        return (obj instanceof r6.c) && b(obj) == i8;
    }

    private static Throwable d(Throwable th) {
        return k.i(th, v.class.getName());
    }

    public static ClassCastException e(ClassCastException classCastException) {
        throw ((ClassCastException) d(classCastException));
    }

    public static void f(Object obj, String str) {
        String name = obj == null ? "null" : obj.getClass().getName();
        g(name + " cannot be cast to " + str);
    }

    public static void g(String str) {
        throw e(new ClassCastException(str));
    }
}
