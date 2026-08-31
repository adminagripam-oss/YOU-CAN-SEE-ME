package f0;

import androidx.concurrent.futures.c;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public class d implements i4.d {

    /* renamed from: e  reason: collision with root package name */
    private final i4.d f6633e;

    /* renamed from: f  reason: collision with root package name */
    c.a f6634f;

    /* loaded from: classes.dex */
    class a implements c.InterfaceC0018c {
        a() {
        }

        @Override // androidx.concurrent.futures.c.InterfaceC0018c
        public Object a(c.a aVar) {
            g1.e.m(d.this.f6634f == null, "The result can only set once!");
            d.this.f6634f = aVar;
            return "FutureChain[" + d.this + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d() {
        this.f6633e = androidx.concurrent.futures.c.a(new a());
    }

    public static d a(i4.d dVar) {
        return dVar instanceof d ? (d) dVar : new d(dVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b(Object obj) {
        c.a aVar = this.f6634f;
        if (aVar != null) {
            return aVar.c(obj);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c(Throwable th) {
        c.a aVar = this.f6634f;
        if (aVar != null) {
            return aVar.f(th);
        }
        return false;
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z7) {
        return this.f6633e.cancel(z7);
    }

    @Override // i4.d
    public void d(Runnable runnable, Executor executor) {
        this.f6633e.d(runnable, executor);
    }

    public final d e(m.a aVar, Executor executor) {
        return (d) n.G(this, aVar, executor);
    }

    public final d f(f0.a aVar, Executor executor) {
        return (d) n.H(this, aVar, executor);
    }

    @Override // java.util.concurrent.Future
    public Object get() {
        return this.f6633e.get();
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.f6633e.isCancelled();
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return this.f6633e.isDone();
    }

    d(i4.d dVar) {
        this.f6633e = (i4.d) g1.e.j(dVar);
    }

    @Override // java.util.concurrent.Future
    public Object get(long j8, TimeUnit timeUnit) {
        return this.f6633e.get(j8, timeUnit);
    }
}
