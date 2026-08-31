package b0;

import b0.d2;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public final class j2 {

    /* renamed from: b  reason: collision with root package name */
    public static final i2 f4252b = i2.b();

    /* renamed from: c  reason: collision with root package name */
    private static final j2 f4253c = new j2();

    /* renamed from: a  reason: collision with root package name */
    private final b2 f4254a = b2.l(f4252b);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a implements d2.a {

        /* renamed from: a  reason: collision with root package name */
        private final g1.a f4255a;

        a(g1.a aVar) {
            this.f4255a = aVar;
        }

        @Override // b0.d2.a
        public void a(Throwable th) {
            y.f1.d("ObserverToConsumerAdapter", "Unexpected error in Observable", th);
        }

        @Override // b0.d2.a
        public void b(Object obj) {
            this.f4255a.accept(obj);
        }
    }

    public static j2 b() {
        return f4253c;
    }

    public i2 a() {
        try {
            return (i2) this.f4254a.a().get();
        } catch (InterruptedException | ExecutionException e8) {
            throw new AssertionError("Unexpected error in QuirkSettings StateObservable", e8);
        }
    }

    public void c(Executor executor, g1.a aVar) {
        this.f4254a.d(executor, new a(aVar));
    }

    public void d(i2 i2Var) {
        this.f4254a.k(i2Var);
    }
}
