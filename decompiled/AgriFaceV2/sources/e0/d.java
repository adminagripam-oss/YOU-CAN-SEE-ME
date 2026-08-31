package e0;

import java.util.concurrent.Executor;
/* loaded from: classes.dex */
final class d implements Executor {

    /* renamed from: e  reason: collision with root package name */
    private static volatile d f6525e;

    d() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Executor a() {
        if (f6525e != null) {
            return f6525e;
        }
        synchronized (d.class) {
            if (f6525e == null) {
                f6525e = new d();
            }
        }
        return f6525e;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        runnable.run();
    }
}
