package r;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public final class e5 {

    /* renamed from: a  reason: collision with root package name */
    private final Executor f10461a;

    /* renamed from: b  reason: collision with root package name */
    private final AtomicInteger f10462b;

    public e5(Executor executor) {
        d7.k.e(executor, "executor");
        this.f10461a = executor;
        this.f10462b = new AtomicInteger(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void d(e5 e5Var) {
        d7.k.e(e5Var, "this$0");
        int decrementAndGet = e5Var.f10462b.decrementAndGet();
        if (decrementAndGet >= 0) {
            y.f1.a("VideoUsageControl", "decrementUsage: mVideoUsage = " + decrementAndGet);
            return;
        }
        y.f1.l("VideoUsageControl", "decrementUsage: mVideoUsage = " + decrementAndGet + ", which is less than 0!");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void g(e5 e5Var) {
        d7.k.e(e5Var, "this$0");
        int incrementAndGet = e5Var.f10462b.incrementAndGet();
        y.f1.a("VideoUsageControl", "incrementUsage: mVideoUsage = " + incrementAndGet);
    }

    public final void c() {
        this.f10461a.execute(new Runnable() { // from class: r.d5
            @Override // java.lang.Runnable
            public final void run() {
                e5.d(e5.this);
            }
        });
    }

    public final int e() {
        return this.f10462b.get();
    }

    public final void f() {
        this.f10461a.execute(new Runnable() { // from class: r.c5
            @Override // java.lang.Runnable
            public final void run() {
                e5.g(e5.this);
            }
        });
    }

    public final void h() {
        this.f10462b.set(0);
        y.f1.a("VideoUsageControl", "resetDirectly: mVideoUsage reset!");
    }
}
