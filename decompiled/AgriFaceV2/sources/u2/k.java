package u2;

import java.util.concurrent.Executor;
/* loaded from: classes.dex */
class k implements Executor {

    /* renamed from: e  reason: collision with root package name */
    private final Executor f12077e;

    /* loaded from: classes.dex */
    static class a implements Runnable {

        /* renamed from: e  reason: collision with root package name */
        private final Runnable f12078e;

        a(Runnable runnable) {
            this.f12078e = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f12078e.run();
            } catch (Exception e8) {
                x2.a.c("Executor", "Background execution failure.", e8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public k(Executor executor) {
        this.f12077e = executor;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.f12077e.execute(new a(runnable));
    }
}
