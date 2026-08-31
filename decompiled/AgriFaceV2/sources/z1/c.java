package z1;

import android.os.Trace;
/* loaded from: classes.dex */
abstract class c {
    public static void a(String str, int i8) {
        Trace.beginAsyncSection(str, i8);
    }

    public static void b(String str, int i8) {
        Trace.endAsyncSection(str, i8);
    }

    public static boolean c() {
        return Trace.isEnabled();
    }

    public static void d(String str, int i8) {
        Trace.setCounter(str, i8);
    }
}
