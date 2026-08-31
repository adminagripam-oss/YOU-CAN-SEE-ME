package d7;

import java.util.Arrays;
/* loaded from: classes.dex */
public abstract class k {
    public static boolean a(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    public static void b(Object obj) {
        if (obj == null) {
            j();
        }
    }

    public static void c(Object obj, String str) {
        if (obj == null) {
            k(str);
        }
    }

    public static void d(Object obj, String str) {
        if (obj != null) {
            return;
        }
        throw ((NullPointerException) h(new NullPointerException(str + " must not be null")));
    }

    public static void e(Object obj, String str) {
        if (obj == null) {
            l(str);
        }
    }

    public static int f(int i8, int i9) {
        if (i8 < i9) {
            return -1;
        }
        return i8 == i9 ? 0 : 1;
    }

    private static String g(String str) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String name = k.class.getName();
        int i8 = 0;
        while (!stackTrace[i8].getClassName().equals(name)) {
            i8++;
        }
        while (stackTrace[i8].getClassName().equals(name)) {
            i8++;
        }
        StackTraceElement stackTraceElement = stackTrace[i8];
        String className = stackTraceElement.getClassName();
        String methodName = stackTraceElement.getMethodName();
        return "Parameter specified as non-null is null: method " + className + "." + methodName + ", parameter " + str;
    }

    private static Throwable h(Throwable th) {
        return i(th, k.class.getName());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Throwable i(Throwable th, String str) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        int length = stackTrace.length;
        int i8 = -1;
        for (int i9 = 0; i9 < length; i9++) {
            if (str.equals(stackTrace[i9].getClassName())) {
                i8 = i9;
            }
        }
        th.setStackTrace((StackTraceElement[]) Arrays.copyOfRange(stackTrace, i8 + 1, length));
        return th;
    }

    public static void j() {
        throw ((NullPointerException) h(new NullPointerException()));
    }

    public static void k(String str) {
        throw ((NullPointerException) h(new NullPointerException(str)));
    }

    private static void l(String str) {
        throw ((NullPointerException) h(new NullPointerException(g(str))));
    }

    public static void m(String str) {
        throw ((r6.q) h(new r6.q(str)));
    }

    public static void n(String str) {
        m("lateinit property " + str + " has not been initialized");
    }
}
