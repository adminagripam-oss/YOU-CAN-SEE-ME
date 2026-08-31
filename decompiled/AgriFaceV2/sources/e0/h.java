package e0;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ScheduledExecutorService;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class h {

    /* renamed from: a  reason: collision with root package name */
    private static volatile ScheduledExecutorService f6545a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ScheduledExecutorService a() {
        if (f6545a != null) {
            return f6545a;
        }
        synchronized (h.class) {
            if (f6545a == null) {
                f6545a = new e(new Handler(Looper.getMainLooper()));
            }
        }
        return f6545a;
    }
}
