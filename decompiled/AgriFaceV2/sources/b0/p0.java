package b0;

import android.os.Handler;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public abstract class p0 {
    public static p0 a(Executor executor, Handler handler) {
        return new c(executor, handler);
    }

    public abstract Executor b();

    public abstract Handler c();
}
