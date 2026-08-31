package y;

import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class n implements Executor {

    /* renamed from: g  reason: collision with root package name */
    private static final ThreadFactory f12861g = new a();

    /* renamed from: e  reason: collision with root package name */
    private final Object f12862e = new Object();

    /* renamed from: f  reason: collision with root package name */
    private ThreadPoolExecutor f12863f = b();

    /* loaded from: classes.dex */
    class a implements ThreadFactory {

        /* renamed from: a  reason: collision with root package name */
        private final AtomicInteger f12864a = new AtomicInteger(0);

        a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName(String.format(Locale.US, "CameraX-core_camera_%d", Integer.valueOf(this.f12864a.getAndIncrement())));
            return thread;
        }
    }

    private static ThreadPoolExecutor b() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), f12861g);
        threadPoolExecutor.setRejectedExecutionHandler(new RejectedExecutionHandler() { // from class: y.m
            @Override // java.util.concurrent.RejectedExecutionHandler
            public final void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor2) {
                f1.c("CameraExecutor", "A rejected execution occurred in CameraExecutor!");
            }
        });
        return threadPoolExecutor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(b0.f0 f0Var) {
        ThreadPoolExecutor threadPoolExecutor;
        g1.e.j(f0Var);
        synchronized (this.f12862e) {
            if (this.f12863f.isShutdown()) {
                this.f12863f = b();
            }
            threadPoolExecutor = this.f12863f;
        }
        int max = Math.max(1, f0Var.c().size());
        threadPoolExecutor.setMaximumPoolSize(max);
        threadPoolExecutor.setCorePoolSize(max);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        g1.e.j(runnable);
        synchronized (this.f12862e) {
            this.f12863f.execute(runnable);
        }
    }
}
