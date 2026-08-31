package k;

import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class c extends e {

    /* renamed from: c  reason: collision with root package name */
    private static volatile c f8284c;

    /* renamed from: d  reason: collision with root package name */
    private static final Executor f8285d = new Executor() { // from class: k.a
        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            c.g(runnable);
        }
    };

    /* renamed from: e  reason: collision with root package name */
    private static final Executor f8286e = new Executor() { // from class: k.b
        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            c.h(runnable);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private e f8287a;

    /* renamed from: b  reason: collision with root package name */
    private final e f8288b;

    private c() {
        d dVar = new d();
        this.f8288b = dVar;
        this.f8287a = dVar;
    }

    public static c f() {
        if (f8284c != null) {
            return f8284c;
        }
        synchronized (c.class) {
            if (f8284c == null) {
                f8284c = new c();
            }
        }
        return f8284c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void g(Runnable runnable) {
        f().c(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void h(Runnable runnable) {
        f().a(runnable);
    }

    @Override // k.e
    public void a(Runnable runnable) {
        this.f8287a.a(runnable);
    }

    @Override // k.e
    public boolean b() {
        return this.f8287a.b();
    }

    @Override // k.e
    public void c(Runnable runnable) {
        this.f8287a.c(runnable);
    }
}
