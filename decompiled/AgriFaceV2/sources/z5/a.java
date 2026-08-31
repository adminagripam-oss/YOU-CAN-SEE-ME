package z5;

import c6.d;
import io.flutter.embedding.engine.FlutterJNI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
/* loaded from: classes.dex */
public final class a {

    /* renamed from: d  reason: collision with root package name */
    private static a f14455d;

    /* renamed from: e  reason: collision with root package name */
    private static boolean f14456e;

    /* renamed from: a  reason: collision with root package name */
    private d f14457a;

    /* renamed from: b  reason: collision with root package name */
    private FlutterJNI.c f14458b;

    /* renamed from: c  reason: collision with root package name */
    private ExecutorService f14459c;

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private d f14460a;

        /* renamed from: b  reason: collision with root package name */
        private FlutterJNI.c f14461b;

        /* renamed from: c  reason: collision with root package name */
        private ExecutorService f14462c;

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: z5.a$b$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class ThreadFactoryC0173a implements ThreadFactory {

            /* renamed from: a  reason: collision with root package name */
            private int f14463a;

            private ThreadFactoryC0173a() {
                this.f14463a = 0;
            }

            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                StringBuilder sb = new StringBuilder();
                sb.append("flutter-worker-");
                int i8 = this.f14463a;
                this.f14463a = i8 + 1;
                sb.append(i8);
                thread.setName(sb.toString());
                return thread;
            }
        }

        private void b() {
            if (this.f14461b == null) {
                this.f14461b = new FlutterJNI.c();
            }
            if (this.f14462c == null) {
                this.f14462c = Executors.newCachedThreadPool(new ThreadFactoryC0173a());
            }
            if (this.f14460a == null) {
                this.f14460a = new d(this.f14461b.a(), this.f14462c);
            }
        }

        public a a() {
            b();
            return new a(this.f14460a, null, this.f14461b, this.f14462c);
        }
    }

    private a(d dVar, b6.a aVar, FlutterJNI.c cVar, ExecutorService executorService) {
        this.f14457a = dVar;
        this.f14458b = cVar;
        this.f14459c = executorService;
    }

    public static a e() {
        f14456e = true;
        if (f14455d == null) {
            f14455d = new b().a();
        }
        return f14455d;
    }

    public b6.a a() {
        return null;
    }

    public ExecutorService b() {
        return this.f14459c;
    }

    public d c() {
        return this.f14457a;
    }

    public FlutterJNI.c d() {
        return this.f14458b;
    }
}
