package g0;

import b0.m2;
import b0.u0;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public interface n extends m2 {
    public static final u0.a H = u0.a.a("camerax.core.thread.backgroundExecutor", Executor.class);

    default Executor Q(Executor executor) {
        return (Executor) f(H, executor);
    }
}
