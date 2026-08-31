package androidx.core.os;

import android.os.Handler;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
/* loaded from: classes.dex */
public abstract class f {

    /* loaded from: classes.dex */
    private static class a implements Executor {

        /* renamed from: e  reason: collision with root package name */
        private final Handler f3048e;

        a(Handler handler) {
            this.f3048e = (Handler) g1.e.j(handler);
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            if (this.f3048e.post((Runnable) g1.e.j(runnable))) {
                return;
            }
            throw new RejectedExecutionException(this.f3048e + " is shutting down");
        }
    }

    public static Executor a(Handler handler) {
        return new a(handler);
    }
}
