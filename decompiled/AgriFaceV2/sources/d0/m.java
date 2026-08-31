package d0;

import android.os.Handler;
import android.os.Looper;
/* loaded from: classes.dex */
public abstract class m {

    /* renamed from: a  reason: collision with root package name */
    private static volatile Handler f6418a;

    public static Handler a() {
        if (f6418a != null) {
            return f6418a;
        }
        synchronized (m.class) {
            if (f6418a == null) {
                f6418a = androidx.core.os.g.a(Looper.getMainLooper());
            }
        }
        return f6418a;
    }
}
