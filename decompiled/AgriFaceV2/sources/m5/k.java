package m5;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public abstract class k {

    /* renamed from: b  reason: collision with root package name */
    private final AtomicInteger f9158b = new AtomicInteger(0);

    /* renamed from: c  reason: collision with root package name */
    private final AtomicBoolean f9159c = new AtomicBoolean(false);

    /* renamed from: a  reason: collision with root package name */
    protected final n f9157a = new n();

    public f4.j a(final Executor executor, final Callable callable, final f4.a aVar) {
        k3.q.i(this.f9158b.get() > 0);
        if (aVar.a()) {
            return f4.m.a();
        }
        final f4.b bVar = new f4.b();
        final f4.k kVar = new f4.k(bVar.b());
        this.f9157a.a(new Executor() { // from class: m5.y
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                try {
                    executor.execute(runnable);
                } catch (RuntimeException e8) {
                    if (aVar.a()) {
                        bVar.a();
                    } else {
                        kVar.b(e8);
                    }
                    throw e8;
                }
            }
        }, new Runnable() { // from class: m5.z
            @Override // java.lang.Runnable
            public final void run() {
                k.this.g(aVar, bVar, callable, kVar);
            }
        });
        return kVar.a();
    }

    public abstract void b();

    public void c() {
        this.f9158b.incrementAndGet();
    }

    protected abstract void d();

    public void e(Executor executor) {
        f(executor);
    }

    public f4.j f(Executor executor) {
        k3.q.i(this.f9158b.get() > 0);
        final f4.k kVar = new f4.k();
        this.f9157a.a(executor, new Runnable() { // from class: m5.x
            @Override // java.lang.Runnable
            public final void run() {
                k.this.h(kVar);
            }
        });
        return kVar.a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void g(f4.a aVar, f4.b bVar, Callable callable, f4.k kVar) {
        try {
            if (aVar.a()) {
                bVar.a();
                return;
            }
            try {
                if (!this.f9159c.get()) {
                    b();
                    this.f9159c.set(true);
                }
                if (aVar.a()) {
                    bVar.a();
                    return;
                }
                Object call = callable.call();
                if (aVar.a()) {
                    bVar.a();
                } else {
                    kVar.c(call);
                }
            } catch (RuntimeException e8) {
                throw new i5.a("Internal error has occurred when executing ML Kit tasks", 13, e8);
            }
        } catch (Exception e9) {
            if (aVar.a()) {
                bVar.a();
            } else {
                kVar.b(e9);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void h(f4.k kVar) {
        int decrementAndGet = this.f9158b.decrementAndGet();
        k3.q.i(decrementAndGet >= 0);
        if (decrementAndGet == 0) {
            d();
            this.f9159c.set(false);
        }
        y3.x.a();
        kVar.c(null);
    }
}
