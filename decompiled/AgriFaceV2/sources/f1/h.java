package f1;

import android.os.Handler;
import android.os.Process;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes.dex */
abstract class h {

    /* loaded from: classes.dex */
    private static class a implements ThreadFactory {

        /* renamed from: a  reason: collision with root package name */
        private String f6716a;

        /* renamed from: b  reason: collision with root package name */
        private int f6717b;

        /* renamed from: f1.h$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        private static class C0086a extends Thread {

            /* renamed from: e  reason: collision with root package name */
            private final int f6718e;

            C0086a(Runnable runnable, String str, int i8) {
                super(runnable, str);
                this.f6718e = i8;
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Process.setThreadPriority(this.f6718e);
                super.run();
            }
        }

        a(String str, int i8) {
            this.f6716a = str;
            this.f6717b = i8;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new C0086a(runnable, this.f6716a, this.f6717b);
        }
    }

    /* loaded from: classes.dex */
    private static class b implements Runnable {

        /* renamed from: e  reason: collision with root package name */
        private Callable f6719e;

        /* renamed from: f  reason: collision with root package name */
        private g1.a f6720f;

        /* renamed from: g  reason: collision with root package name */
        private Handler f6721g;

        /* loaded from: classes.dex */
        class a implements Runnable {

            /* renamed from: e  reason: collision with root package name */
            final /* synthetic */ g1.a f6722e;

            /* renamed from: f  reason: collision with root package name */
            final /* synthetic */ Object f6723f;

            a(g1.a aVar, Object obj) {
                this.f6722e = aVar;
                this.f6723f = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f6722e.accept(this.f6723f);
            }
        }

        b(Handler handler, Callable callable, g1.a aVar) {
            this.f6719e = callable;
            this.f6720f = aVar;
            this.f6721g = handler;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object obj;
            try {
                obj = this.f6719e.call();
            } catch (Exception unused) {
                obj = null;
            }
            this.f6721g.post(new a(this.f6720f, obj));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ThreadPoolExecutor a(String str, int i8, int i9) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, i9, TimeUnit.MILLISECONDS, new LinkedBlockingDeque(), new a(str, i8));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return threadPoolExecutor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(Executor executor, Callable callable, g1.a aVar) {
        executor.execute(new b(f1.b.a(), callable, aVar));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object c(ExecutorService executorService, Callable callable, int i8) {
        try {
            return executorService.submit(callable).get(i8, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e8) {
            throw e8;
        } catch (ExecutionException e9) {
            throw new RuntimeException(e9);
        } catch (TimeoutException unused) {
            throw new InterruptedException("timeout");
        }
    }
}
