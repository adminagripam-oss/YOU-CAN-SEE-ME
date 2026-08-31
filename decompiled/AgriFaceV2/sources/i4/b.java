package i4;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
/* loaded from: classes.dex */
public abstract class b extends c {

    /* loaded from: classes.dex */
    private static final class a implements Runnable {

        /* renamed from: e  reason: collision with root package name */
        final Future f7508e;

        /* renamed from: f  reason: collision with root package name */
        final i4.a f7509f;

        a(Future future, i4.a aVar) {
            this.f7508e = future;
            this.f7509f = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f7509f.a(b.b(this.f7508e));
            } catch (ExecutionException e8) {
                this.f7509f.b(e8.getCause());
            } catch (Throwable th) {
                this.f7509f.b(th);
            }
        }

        public String toString() {
            return h4.a.a(this).c(this.f7509f).toString();
        }
    }

    public static void a(d dVar, i4.a aVar, Executor executor) {
        h4.b.a(aVar);
        dVar.d(new a(dVar, aVar), executor);
    }

    public static Object b(Future future) {
        h4.b.b(future.isDone(), "Future was expected to be done: %s", future);
        return e.a(future);
    }
}
