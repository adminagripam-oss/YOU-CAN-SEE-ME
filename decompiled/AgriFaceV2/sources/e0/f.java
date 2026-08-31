package e0;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
/* loaded from: classes.dex */
final class f implements Executor {

    /* renamed from: f  reason: collision with root package name */
    private static volatile Executor f6538f;

    /* renamed from: e  reason: collision with root package name */
    private final ExecutorService f6539e = Executors.newSingleThreadExecutor(new a());

    /* loaded from: classes.dex */
    class a implements ThreadFactory {
        a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setPriority(10);
            thread.setName("CameraX-camerax_high_priority");
            return thread;
        }
    }

    f() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Executor a() {
        if (f6538f != null) {
            return f6538f;
        }
        synchronized (f.class) {
            if (f6538f == null) {
                f6538f = new f();
            }
        }
        return f6538f;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.f6539e.execute(runnable);
    }
}
