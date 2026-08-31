package e0;

import android.os.Process;
import e0.b;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class b implements Executor {

    /* renamed from: f  reason: collision with root package name */
    private static volatile Executor f6521f;

    /* renamed from: e  reason: collision with root package name */
    private final ExecutorService f6522e = Executors.newFixedThreadPool(2, new a());

    /* loaded from: classes.dex */
    class a implements ThreadFactory {

        /* renamed from: a  reason: collision with root package name */
        private final AtomicInteger f6523a = new AtomicInteger(0);

        a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void b(Runnable runnable) {
            Process.setThreadPriority(-16);
            runnable.run();
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(final Runnable runnable) {
            Thread thread = new Thread(new Runnable() { // from class: e0.a
                @Override // java.lang.Runnable
                public final void run() {
                    b.a.b(runnable);
                }
            });
            thread.setName(String.format(Locale.US, "CameraX-camerax_audio_%d", Integer.valueOf(this.f6523a.getAndIncrement())));
            return thread;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Executor a() {
        if (f6521f != null) {
            return f6521f;
        }
        synchronized (b.class) {
            if (f6521f == null) {
                f6521f = new b();
            }
        }
        return f6521f;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.f6522e.execute(runnable);
    }
}
