package m5;

import java.io.Closeable;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes.dex */
final class h0 implements Closeable {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ n f9151e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ h0(n nVar, g0 g0Var) {
        AtomicReference atomicReference;
        this.f9151e = nVar;
        atomicReference = nVar.f9191d;
        k3.q.i(((Thread) atomicReference.getAndSet(Thread.currentThread())) == null);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        AtomicReference atomicReference;
        atomicReference = this.f9151e.f9191d;
        atomicReference.set(null);
        this.f9151e.d();
    }
}
