package androidx.core.os;

import android.os.Build;
import android.os.Trace;
import android.util.Log;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public abstract class l {

    /* renamed from: a  reason: collision with root package name */
    private static long f3053a;

    /* renamed from: b  reason: collision with root package name */
    private static Method f3054b;

    /* renamed from: c  reason: collision with root package name */
    private static Method f3055c;

    /* renamed from: d  reason: collision with root package name */
    private static Method f3056d;

    /* renamed from: e  reason: collision with root package name */
    private static Method f3057e;

    static {
        if (Build.VERSION.SDK_INT < 29) {
            try {
                f3053a = Trace.class.getField("TRACE_TAG_APP").getLong(null);
                Class cls = Long.TYPE;
                f3054b = Trace.class.getMethod("isTagEnabled", cls);
                Class cls2 = Integer.TYPE;
                f3055c = Trace.class.getMethod("asyncTraceBegin", cls, String.class, cls2);
                f3056d = Trace.class.getMethod("asyncTraceEnd", cls, String.class, cls2);
                f3057e = Trace.class.getMethod("traceCounter", cls, String.class, cls2);
            } catch (Exception e8) {
                Log.i("TraceCompat", "Unable to initialize via reflection.", e8);
            }
        }
    }

    public static void a(String str) {
        Trace.beginSection(str);
    }

    public static void b() {
        Trace.endSection();
    }
}
