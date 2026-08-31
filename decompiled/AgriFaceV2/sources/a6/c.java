package a6;

import a6.c;
import i6.b;
import io.flutter.embedding.engine.FlutterJNI;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class c implements i6.b, a6.f {

    /* renamed from: a  reason: collision with root package name */
    private final FlutterJNI f1372a;

    /* renamed from: b  reason: collision with root package name */
    private final Map f1373b;

    /* renamed from: c  reason: collision with root package name */
    private Map f1374c;

    /* renamed from: d  reason: collision with root package name */
    private final Object f1375d;

    /* renamed from: e  reason: collision with root package name */
    private final AtomicBoolean f1376e;

    /* renamed from: f  reason: collision with root package name */
    private final Map f1377f;

    /* renamed from: g  reason: collision with root package name */
    private int f1378g;

    /* renamed from: h  reason: collision with root package name */
    private final d f1379h;

    /* renamed from: i  reason: collision with root package name */
    private WeakHashMap f1380i;

    /* renamed from: j  reason: collision with root package name */
    private i f1381j;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public final ByteBuffer f1382a;

        /* renamed from: b  reason: collision with root package name */
        int f1383b;

        /* renamed from: c  reason: collision with root package name */
        long f1384c;

        b(ByteBuffer byteBuffer, int i8, long j8) {
            this.f1382a = byteBuffer;
            this.f1383b = i8;
            this.f1384c = j8;
        }
    }

    /* renamed from: a6.c$c  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static class C0002c implements d {

        /* renamed from: a  reason: collision with root package name */
        private final ExecutorService f1385a;

        C0002c(ExecutorService executorService) {
            this.f1385a = executorService;
        }

        @Override // a6.c.d
        public void a(Runnable runnable) {
            this.f1385a.execute(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface d {
        void a(Runnable runnable);
    }

    /* loaded from: classes.dex */
    private static class e implements i {

        /* renamed from: a  reason: collision with root package name */
        ExecutorService f1386a = z5.a.e().b();

        e() {
        }

        @Override // a6.c.i
        public d a(b.d dVar) {
            return dVar.a() ? new h(this.f1386a) : new C0002c(this.f1386a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class f {

        /* renamed from: a  reason: collision with root package name */
        public final b.a f1387a;

        /* renamed from: b  reason: collision with root package name */
        public final d f1388b;

        f(b.a aVar, d dVar) {
            this.f1387a = aVar;
            this.f1388b = dVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class g implements b.InterfaceC0103b {

        /* renamed from: a  reason: collision with root package name */
        private final FlutterJNI f1389a;

        /* renamed from: b  reason: collision with root package name */
        private final int f1390b;

        /* renamed from: c  reason: collision with root package name */
        private final AtomicBoolean f1391c = new AtomicBoolean(false);

        g(FlutterJNI flutterJNI, int i8) {
            this.f1389a = flutterJNI;
            this.f1390b = i8;
        }

        @Override // i6.b.InterfaceC0103b
        public void a(ByteBuffer byteBuffer) {
            if (this.f1391c.getAndSet(true)) {
                throw new IllegalStateException("Reply already submitted");
            }
            if (byteBuffer == null) {
                this.f1389a.invokePlatformMessageEmptyResponseCallback(this.f1390b);
            } else {
                this.f1389a.invokePlatformMessageResponseCallback(this.f1390b, byteBuffer, byteBuffer.position());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class h implements d {

        /* renamed from: a  reason: collision with root package name */
        private final ExecutorService f1392a;

        /* renamed from: b  reason: collision with root package name */
        private final ConcurrentLinkedQueue f1393b = new ConcurrentLinkedQueue();

        /* renamed from: c  reason: collision with root package name */
        private final AtomicBoolean f1394c = new AtomicBoolean(false);

        h(ExecutorService executorService) {
            this.f1392a = executorService;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public void f() {
            if (this.f1394c.compareAndSet(false, true)) {
                try {
                    Runnable runnable = (Runnable) this.f1393b.poll();
                    if (runnable != null) {
                        runnable.run();
                    }
                } finally {
                    this.f1394c.set(false);
                    if (!this.f1393b.isEmpty()) {
                        this.f1392a.execute(new Runnable() { // from class: a6.e
                            @Override // java.lang.Runnable
                            public final void run() {
                                c.h.this.f();
                            }
                        });
                    }
                }
            }
        }

        @Override // a6.c.d
        public void a(Runnable runnable) {
            this.f1393b.add(runnable);
            this.f1392a.execute(new Runnable() { // from class: a6.d
                @Override // java.lang.Runnable
                public final void run() {
                    c.h.this.e();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface i {
        d a(b.d dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class j implements b.c {
        private j() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(FlutterJNI flutterJNI) {
        this(flutterJNI, new e());
    }

    private void j(final String str, final f fVar, final ByteBuffer byteBuffer, final int i8, final long j8) {
        d dVar = fVar != null ? fVar.f1388b : null;
        p6.e.c("PlatformChannel ScheduleHandler on " + str, i8);
        Runnable runnable = new Runnable() { // from class: a6.b
            @Override // java.lang.Runnable
            public final void run() {
                c.this.m(str, i8, fVar, byteBuffer, j8);
            }
        };
        if (dVar == null) {
            dVar = this.f1379h;
        }
        dVar.a(runnable);
    }

    private static void k(Error error) {
        Thread currentThread = Thread.currentThread();
        if (currentThread.getUncaughtExceptionHandler() == null) {
            throw error;
        }
        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, error);
    }

    private void l(f fVar, ByteBuffer byteBuffer, int i8) {
        if (fVar != null) {
            try {
                z5.b.f("DartMessenger", "Deferring to registered handler to process message.");
                fVar.f1387a.a(byteBuffer, new g(this.f1372a, i8));
                return;
            } catch (Error e8) {
                k(e8);
                return;
            } catch (Exception e9) {
                z5.b.c("DartMessenger", "Uncaught exception in binary message listener", e9);
            }
        } else {
            z5.b.f("DartMessenger", "No registered handler for message. Responding to Dart with empty reply message.");
        }
        this.f1372a.invokePlatformMessageEmptyResponseCallback(i8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m(String str, int i8, f fVar, ByteBuffer byteBuffer, long j8) {
        p6.e.g("PlatformChannel ScheduleHandler on " + str, i8);
        try {
            p6.e j9 = p6.e.j("DartMessenger#handleMessageFromDart on " + str);
            l(fVar, byteBuffer, i8);
            if (byteBuffer != null && byteBuffer.isDirect()) {
                byteBuffer.limit(0);
            }
            if (j9 != null) {
                j9.close();
            }
        } finally {
            this.f1372a.cleanupMessageData(j8);
        }
    }

    @Override // i6.b
    public b.c a(b.d dVar) {
        d a8 = this.f1381j.a(dVar);
        j jVar = new j();
        this.f1380i.put(jVar, a8);
        return jVar;
    }

    @Override // i6.b
    public void b(String str, b.a aVar, b.c cVar) {
        d dVar;
        if (aVar == null) {
            z5.b.f("DartMessenger", "Removing handler for channel '" + str + "'");
            synchronized (this.f1375d) {
                this.f1373b.remove(str);
            }
            return;
        }
        if (cVar != null) {
            dVar = (d) this.f1380i.get(cVar);
            if (dVar == null) {
                throw new IllegalArgumentException("Unrecognized TaskQueue, use BinaryMessenger to create your TaskQueue (ex makeBackgroundTaskQueue).");
            }
        } else {
            dVar = null;
        }
        z5.b.f("DartMessenger", "Setting handler for channel '" + str + "'");
        synchronized (this.f1375d) {
            this.f1373b.put(str, new f(aVar, dVar));
            List<b> list = (List) this.f1374c.remove(str);
            if (list == null) {
                return;
            }
            for (b bVar : list) {
                j(str, (f) this.f1373b.get(str), bVar.f1382a, bVar.f1383b, bVar.f1384c);
            }
        }
    }

    @Override // i6.b
    public void d(String str, b.a aVar) {
        b(str, aVar, null);
    }

    @Override // i6.b
    public void e(String str, ByteBuffer byteBuffer) {
        z5.b.f("DartMessenger", "Sending message over channel '" + str + "'");
        h(str, byteBuffer, null);
    }

    @Override // a6.f
    public void f(int i8, ByteBuffer byteBuffer) {
        z5.b.f("DartMessenger", "Received message reply from Dart.");
        b.InterfaceC0103b interfaceC0103b = (b.InterfaceC0103b) this.f1377f.remove(Integer.valueOf(i8));
        if (interfaceC0103b != null) {
            try {
                z5.b.f("DartMessenger", "Invoking registered callback for reply from Dart.");
                interfaceC0103b.a(byteBuffer);
                if (byteBuffer == null || !byteBuffer.isDirect()) {
                    return;
                }
                byteBuffer.limit(0);
            } catch (Error e8) {
                k(e8);
            } catch (Exception e9) {
                z5.b.c("DartMessenger", "Uncaught exception in binary message reply handler", e9);
            }
        }
    }

    @Override // a6.f
    public void g(String str, ByteBuffer byteBuffer, int i8, long j8) {
        f fVar;
        boolean z7;
        z5.b.f("DartMessenger", "Received message from Dart over channel '" + str + "'");
        synchronized (this.f1375d) {
            fVar = (f) this.f1373b.get(str);
            z7 = this.f1376e.get() && fVar == null;
            if (z7) {
                if (!this.f1374c.containsKey(str)) {
                    this.f1374c.put(str, new LinkedList());
                }
                ((List) this.f1374c.get(str)).add(new b(byteBuffer, i8, j8));
            }
        }
        if (z7) {
            return;
        }
        j(str, fVar, byteBuffer, i8, j8);
    }

    @Override // i6.b
    public void h(String str, ByteBuffer byteBuffer, b.InterfaceC0103b interfaceC0103b) {
        p6.e j8 = p6.e.j("DartMessenger#send on " + str);
        try {
            z5.b.f("DartMessenger", "Sending message with callback over channel '" + str + "'");
            int i8 = this.f1378g;
            this.f1378g = i8 + 1;
            if (interfaceC0103b != null) {
                this.f1377f.put(Integer.valueOf(i8), interfaceC0103b);
            }
            if (byteBuffer == null) {
                this.f1372a.dispatchEmptyPlatformMessage(str, i8);
            } else {
                this.f1372a.dispatchPlatformMessage(str, byteBuffer, byteBuffer.position(), i8);
            }
            if (j8 != null) {
                j8.close();
            }
        } catch (Throwable th) {
            if (j8 != null) {
                try {
                    j8.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    c(FlutterJNI flutterJNI, i iVar) {
        this.f1373b = new HashMap();
        this.f1374c = new HashMap();
        this.f1375d = new Object();
        this.f1376e = new AtomicBoolean(false);
        this.f1377f = new HashMap();
        this.f1378g = 1;
        this.f1379h = new a6.g();
        this.f1380i = new WeakHashMap();
        this.f1372a = flutterJNI;
        this.f1381j = iVar;
    }
}
