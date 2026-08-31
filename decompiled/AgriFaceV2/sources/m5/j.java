package m5;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public class j extends y3.u {

    /* renamed from: f  reason: collision with root package name */
    private static final ThreadLocal f9155f = new ThreadLocal();

    /* renamed from: e  reason: collision with root package name */
    private final ThreadPoolExecutor f9156e;

    public j() {
        final ThreadFactory defaultThreadFactory = Executors.defaultThreadFactory();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(availableProcessors, availableProcessors, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactory() { // from class: m5.v
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(final Runnable runnable) {
                return defaultThreadFactory.newThread(new Runnable() { // from class: m5.w
                    @Override // java.lang.Runnable
                    public final void run() {
                        j.g(runnable);
                    }
                });
            }
        });
        this.f9156e = threadPoolExecutor;
        threadPoolExecutor.allowCoreThreadTimeOut(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void g(Runnable runnable) {
        f9155f.set(new ArrayDeque());
        runnable.run();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void j(Deque deque, Runnable runnable) {
        k3.q.g(deque);
        deque.add(runnable);
        if (deque.size() <= 1) {
            do {
                runnable.run();
                deque.removeFirst();
                runnable = (Runnable) deque.peekFirst();
            } while (runnable != null);
        }
    }

    @Override // y3.x0
    protected final /* synthetic */ Object b() {
        return this.f9156e;
    }

    @Override // y3.u
    protected final ExecutorService c() {
        return this.f9156e;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(final Runnable runnable) {
        Deque deque = (Deque) f9155f.get();
        if (deque == null || deque.size() > 1) {
            this.f9156e.execute(new Runnable() { // from class: m5.u
                @Override // java.lang.Runnable
                public final void run() {
                    j.j((Deque) j.f9155f.get(), runnable);
                }
            });
        } else {
            j(deque, runnable);
        }
    }
}
