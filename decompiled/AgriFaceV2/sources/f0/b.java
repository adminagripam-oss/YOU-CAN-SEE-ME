package f0;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class b extends d implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    private f0.a f6626g;

    /* renamed from: h  reason: collision with root package name */
    private final BlockingQueue f6627h = new LinkedBlockingQueue(1);

    /* renamed from: i  reason: collision with root package name */
    private final CountDownLatch f6628i = new CountDownLatch(1);

    /* renamed from: j  reason: collision with root package name */
    private i4.d f6629j;

    /* renamed from: k  reason: collision with root package name */
    volatile i4.d f6630k;

    /* loaded from: classes.dex */
    class a implements Runnable {

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ i4.d f6631e;

        a(i4.d dVar) {
            this.f6631e = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                try {
                    b.this.b(n.m(this.f6631e));
                } catch (CancellationException unused) {
                    b.this.cancel(false);
                    b.this.f6630k = null;
                    return;
                } catch (ExecutionException e8) {
                    b.this.c(e8.getCause());
                }
                b.this.f6630k = null;
            } catch (Throwable th) {
                b.this.f6630k = null;
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(f0.a aVar, i4.d dVar) {
        this.f6626g = (f0.a) g1.e.j(aVar);
        this.f6629j = (i4.d) g1.e.j(dVar);
    }

    private void g(Future future, boolean z7) {
        if (future != null) {
            future.cancel(z7);
        }
    }

    private void h(BlockingQueue blockingQueue, Object obj) {
        boolean z7 = false;
        while (true) {
            try {
                blockingQueue.put(obj);
                break;
            } catch (InterruptedException unused) {
                z7 = true;
            } catch (Throwable th) {
                if (z7) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z7) {
            Thread.currentThread().interrupt();
        }
    }

    private Object i(BlockingQueue blockingQueue) {
        Object take;
        boolean z7 = false;
        while (true) {
            try {
                take = blockingQueue.take();
                break;
            } catch (InterruptedException unused) {
                z7 = true;
            } catch (Throwable th) {
                if (z7) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z7) {
            Thread.currentThread().interrupt();
        }
        return take;
    }

    @Override // f0.d, java.util.concurrent.Future
    public boolean cancel(boolean z7) {
        if (super.cancel(z7)) {
            h(this.f6627h, Boolean.valueOf(z7));
            g(this.f6629j, z7);
            g(this.f6630k, z7);
            return true;
        }
        return false;
    }

    @Override // f0.d, java.util.concurrent.Future
    public Object get() {
        if (!isDone()) {
            i4.d dVar = this.f6629j;
            if (dVar != null) {
                dVar.get();
            }
            this.f6628i.await();
            i4.d dVar2 = this.f6630k;
            if (dVar2 != null) {
                dVar2.get();
            }
        }
        return super.get();
    }

    @Override // java.lang.Runnable
    public void run() {
        i4.d apply;
        try {
            try {
                try {
                    apply = this.f6626g.apply(n.m(this.f6629j));
                    this.f6630k = apply;
                } catch (Throwable th) {
                    this.f6626g = null;
                    this.f6629j = null;
                    this.f6628i.countDown();
                    throw th;
                }
            } catch (CancellationException unused) {
                cancel(false);
            } catch (ExecutionException e8) {
                c(e8.getCause());
            }
        } catch (Error e9) {
            e = e9;
            c(e);
            this.f6626g = null;
            this.f6629j = null;
            this.f6628i.countDown();
            return;
        } catch (UndeclaredThrowableException e10) {
            e = e10.getCause();
            c(e);
            this.f6626g = null;
            this.f6629j = null;
            this.f6628i.countDown();
            return;
        } catch (Exception e11) {
            e = e11;
            c(e);
            this.f6626g = null;
            this.f6629j = null;
            this.f6628i.countDown();
            return;
        }
        if (!isCancelled()) {
            apply.d(new a(apply), e0.c.b());
            this.f6626g = null;
            this.f6629j = null;
            this.f6628i.countDown();
            return;
        }
        apply.cancel(((Boolean) i(this.f6627h)).booleanValue());
        this.f6630k = null;
        this.f6626g = null;
        this.f6629j = null;
        this.f6628i.countDown();
    }

    @Override // f0.d, java.util.concurrent.Future
    public Object get(long j8, TimeUnit timeUnit) {
        if (!isDone()) {
            TimeUnit timeUnit2 = TimeUnit.NANOSECONDS;
            if (timeUnit != timeUnit2) {
                j8 = timeUnit2.convert(j8, timeUnit);
                timeUnit = timeUnit2;
            }
            i4.d dVar = this.f6629j;
            if (dVar != null) {
                long nanoTime = System.nanoTime();
                dVar.get(j8, timeUnit);
                j8 -= Math.max(0L, System.nanoTime() - nanoTime);
            }
            long nanoTime2 = System.nanoTime();
            if (!this.f6628i.await(j8, timeUnit)) {
                throw new TimeoutException();
            }
            j8 -= Math.max(0L, System.nanoTime() - nanoTime2);
            i4.d dVar2 = this.f6630k;
            if (dVar2 != null) {
                dVar2.get(j8, timeUnit);
            }
        }
        return super.get(j8, timeUnit);
    }
}
