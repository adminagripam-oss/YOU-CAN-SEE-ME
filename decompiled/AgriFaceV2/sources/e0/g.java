package e0;

import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
final class g implements Executor {

    /* renamed from: f  reason: collision with root package name */
    private static volatile Executor f6541f;

    /* renamed from: e  reason: collision with root package name */
    private final ExecutorService f6542e = Executors.newFixedThreadPool(2, new a());

    /* loaded from: classes.dex */
    class a implements ThreadFactory {

        /* renamed from: a  reason: collision with root package name */
        private final AtomicInteger f6543a = new AtomicInteger(0);

        a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName(String.format(Locale.US, "CameraX-camerax_io_%d", Integer.valueOf(this.f6543a.getAndIncrement())));
            return thread;
        }
    }

    g() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Executor a() {
        if (f6541f != null) {
            return f6541f;
        }
        synchronized (g.class) {
            if (f6541f == null) {
                f6541f = new g();
            }
        }
        return f6541f;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.f6542e.execute(runnable);
    }
}
