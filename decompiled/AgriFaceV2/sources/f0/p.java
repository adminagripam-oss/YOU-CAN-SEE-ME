package f0;

import androidx.concurrent.futures.c;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class p implements i4.d {

    /* renamed from: e  reason: collision with root package name */
    List f6666e;

    /* renamed from: f  reason: collision with root package name */
    List f6667f;

    /* renamed from: g  reason: collision with root package name */
    private final boolean f6668g;

    /* renamed from: h  reason: collision with root package name */
    private final AtomicInteger f6669h;

    /* renamed from: i  reason: collision with root package name */
    private final i4.d f6670i = androidx.concurrent.futures.c.a(new a());

    /* renamed from: j  reason: collision with root package name */
    c.a f6671j;

    /* loaded from: classes.dex */
    class a implements c.InterfaceC0018c {
        a() {
        }

        @Override // androidx.concurrent.futures.c.InterfaceC0018c
        public Object a(c.a aVar) {
            g1.e.m(p.this.f6671j == null, "The result can only set once!");
            p.this.f6671j = aVar;
            return "ListFuture[" + this + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            p pVar = p.this;
            pVar.f6667f = null;
            pVar.f6666e = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c implements Runnable {

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ int f6674e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ i4.d f6675f;

        c(int i8, i4.d dVar) {
            this.f6674e = i8;
            this.f6675f = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            p.this.f(this.f6674e, this.f6675f);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public p(List list, boolean z7, Executor executor) {
        this.f6666e = (List) g1.e.j(list);
        this.f6667f = new ArrayList(list.size());
        this.f6668g = z7;
        this.f6669h = new AtomicInteger(list.size());
        e(executor);
    }

    private void a() {
        List<i4.d> list = this.f6666e;
        if (list == null || isDone()) {
            return;
        }
        for (i4.d dVar : list) {
            while (!dVar.isDone()) {
                try {
                    dVar.get();
                } catch (Error e8) {
                    throw e8;
                } catch (InterruptedException e9) {
                    throw e9;
                } catch (Throwable unused) {
                    if (this.f6668g) {
                        return;
                    }
                }
            }
        }
    }

    private void e(Executor executor) {
        d(new b(), e0.c.b());
        if (this.f6666e.isEmpty()) {
            this.f6671j.c(new ArrayList(this.f6667f));
            return;
        }
        for (int i8 = 0; i8 < this.f6666e.size(); i8++) {
            this.f6667f.add(null);
        }
        List list = this.f6666e;
        for (int i9 = 0; i9 < list.size(); i9++) {
            i4.d dVar = (i4.d) list.get(i9);
            dVar.d(new c(i9, dVar), executor);
        }
    }

    @Override // java.util.concurrent.Future
    /* renamed from: b */
    public List get() {
        a();
        return (List) this.f6670i.get();
    }

    @Override // java.util.concurrent.Future
    /* renamed from: c */
    public List get(long j8, TimeUnit timeUnit) {
        return (List) this.f6670i.get(j8, timeUnit);
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z7) {
        List<i4.d> list = this.f6666e;
        if (list != null) {
            for (i4.d dVar : list) {
                dVar.cancel(z7);
            }
        }
        return this.f6670i.cancel(z7);
    }

    @Override // i4.d
    public void d(Runnable runnable, Executor executor) {
        this.f6670i.d(runnable, executor);
    }

    void f(int i8, Future future) {
        c.a aVar;
        ArrayList arrayList;
        int decrementAndGet;
        List list = this.f6667f;
        if (isDone() || list == null) {
            g1.e.m(this.f6668g, "Future was done before all dependencies completed");
            return;
        }
        try {
            try {
                try {
                    try {
                        g1.e.m(future.isDone(), "Tried to set value from future which is not done");
                        list.set(i8, n.m(future));
                        decrementAndGet = this.f6669h.decrementAndGet();
                        g1.e.m(decrementAndGet >= 0, "Less than 0 remaining futures");
                    } catch (ExecutionException e8) {
                        if (this.f6668g) {
                            this.f6671j.f(e8.getCause());
                        }
                        int decrementAndGet2 = this.f6669h.decrementAndGet();
                        g1.e.m(decrementAndGet2 >= 0, "Less than 0 remaining futures");
                        if (decrementAndGet2 != 0) {
                            return;
                        }
                        List list2 = this.f6667f;
                        if (list2 != null) {
                            aVar = this.f6671j;
                            arrayList = new ArrayList(list2);
                        }
                    }
                } catch (RuntimeException e9) {
                    if (this.f6668g) {
                        this.f6671j.f(e9);
                    }
                    int decrementAndGet3 = this.f6669h.decrementAndGet();
                    g1.e.m(decrementAndGet3 >= 0, "Less than 0 remaining futures");
                    if (decrementAndGet3 != 0) {
                        return;
                    }
                    List list3 = this.f6667f;
                    if (list3 != null) {
                        aVar = this.f6671j;
                        arrayList = new ArrayList(list3);
                    }
                }
            } catch (Error e10) {
                this.f6671j.f(e10);
                int decrementAndGet4 = this.f6669h.decrementAndGet();
                g1.e.m(decrementAndGet4 >= 0, "Less than 0 remaining futures");
                if (decrementAndGet4 != 0) {
                    return;
                }
                List list4 = this.f6667f;
                if (list4 != null) {
                    aVar = this.f6671j;
                    arrayList = new ArrayList(list4);
                }
            } catch (CancellationException unused) {
                if (this.f6668g) {
                    cancel(false);
                }
                int decrementAndGet5 = this.f6669h.decrementAndGet();
                g1.e.m(decrementAndGet5 >= 0, "Less than 0 remaining futures");
                if (decrementAndGet5 != 0) {
                    return;
                }
                List list5 = this.f6667f;
                if (list5 != null) {
                    aVar = this.f6671j;
                    arrayList = new ArrayList(list5);
                }
            }
            if (decrementAndGet == 0) {
                List list6 = this.f6667f;
                if (list6 != null) {
                    aVar = this.f6671j;
                    arrayList = new ArrayList(list6);
                    aVar.c(arrayList);
                    return;
                }
                g1.e.l(isDone());
            }
        } catch (Throwable th) {
            int decrementAndGet6 = this.f6669h.decrementAndGet();
            g1.e.m(decrementAndGet6 >= 0, "Less than 0 remaining futures");
            if (decrementAndGet6 == 0) {
                List list7 = this.f6667f;
                if (list7 != null) {
                    this.f6671j.c(new ArrayList(list7));
                } else {
                    g1.e.l(isDone());
                }
            }
            throw th;
        }
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.f6670i.isCancelled();
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return this.f6670i.isDone();
    }
}
