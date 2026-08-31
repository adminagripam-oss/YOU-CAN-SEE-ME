package m5;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes.dex */
public class n {

    /* renamed from: b  reason: collision with root package name */
    private boolean f9189b;

    /* renamed from: a  reason: collision with root package name */
    private final Object f9188a = new Object();

    /* renamed from: c  reason: collision with root package name */
    private final Queue f9190c = new ArrayDeque();

    /* renamed from: d  reason: collision with root package name */
    private final AtomicReference f9191d = new AtomicReference();

    /* JADX INFO: Access modifiers changed from: private */
    public final void d() {
        synchronized (this.f9188a) {
            if (this.f9190c.isEmpty()) {
                this.f9189b = false;
                return;
            }
            f0 f0Var = (f0) this.f9190c.remove();
            e(f0Var.f9146a, f0Var.f9147b);
        }
    }

    private final void e(Executor executor, final Runnable runnable) {
        try {
            executor.execute(new Runnable() { // from class: m5.d0
                @Override // java.lang.Runnable
                public final void run() {
                    h0 h0Var = new h0(n.this, null);
                    try {
                        runnable.run();
                        h0Var.close();
                    } catch (Throwable th) {
                        try {
                            h0Var.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                }
            });
        } catch (RejectedExecutionException unused) {
            d();
        }
    }

    public void a(Executor executor, Runnable runnable) {
        synchronized (this.f9188a) {
            if (this.f9189b) {
                this.f9190c.add(new f0(executor, runnable, null));
                return;
            }
            this.f9189b = true;
            e(executor, runnable);
        }
    }
}
