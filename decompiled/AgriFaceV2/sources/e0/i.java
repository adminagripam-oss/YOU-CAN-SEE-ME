package e0;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class i implements Executor {

    /* renamed from: f  reason: collision with root package name */
    private final Executor f6547f;

    /* renamed from: e  reason: collision with root package name */
    final Deque f6546e = new ArrayDeque();

    /* renamed from: g  reason: collision with root package name */
    private final b f6548g = new b();

    /* renamed from: h  reason: collision with root package name */
    c f6549h = c.IDLE;

    /* renamed from: i  reason: collision with root package name */
    long f6550i = 0;

    /* loaded from: classes.dex */
    class a implements Runnable {

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ Runnable f6551e;

        a(Runnable runnable) {
            this.f6551e = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f6551e.run();
        }
    }

    /* loaded from: classes.dex */
    final class b implements Runnable {
        b() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:17:0x0039, code lost:
            if (r1 == false) goto L35;
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x003b, code lost:
            java.lang.Thread.currentThread().interrupt();
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x0042, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:22:0x0048, code lost:
            r1 = r1 | java.lang.Thread.interrupted();
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x0049, code lost:
            r3.run();
         */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x004d, code lost:
            r2 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x004e, code lost:
            y.f1.d("SequentialExecutor", "Exception while executing runnable " + r3, r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:?, code lost:
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void a() {
            /*
                r9 = this;
                r0 = 0
                r1 = r0
            L2:
                e0.i r2 = e0.i.this     // Catch: java.lang.Throwable -> L68
                java.util.Deque r2 = r2.f6546e     // Catch: java.lang.Throwable -> L68
                monitor-enter(r2)     // Catch: java.lang.Throwable -> L68
                if (r0 != 0) goto L26
                e0.i r0 = e0.i.this     // Catch: java.lang.Throwable -> L65
                e0.i$c r3 = r0.f6549h     // Catch: java.lang.Throwable -> L65
                e0.i$c r4 = e0.i.c.RUNNING     // Catch: java.lang.Throwable -> L65
                if (r3 != r4) goto L1c
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L65
                if (r1 == 0) goto L1b
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
            L1b:
                return
            L1c:
                long r5 = r0.f6550i     // Catch: java.lang.Throwable -> L65
                r7 = 1
                long r5 = r5 + r7
                r0.f6550i = r5     // Catch: java.lang.Throwable -> L65
                r0.f6549h = r4     // Catch: java.lang.Throwable -> L65
                r0 = 1
            L26:
                e0.i r3 = e0.i.this     // Catch: java.lang.Throwable -> L65
                java.util.Deque r3 = r3.f6546e     // Catch: java.lang.Throwable -> L65
                java.lang.Object r3 = r3.poll()     // Catch: java.lang.Throwable -> L65
                java.lang.Runnable r3 = (java.lang.Runnable) r3     // Catch: java.lang.Throwable -> L65
                if (r3 != 0) goto L43
                e0.i r0 = e0.i.this     // Catch: java.lang.Throwable -> L65
                e0.i$c r3 = e0.i.c.IDLE     // Catch: java.lang.Throwable -> L65
                r0.f6549h = r3     // Catch: java.lang.Throwable -> L65
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L65
                if (r1 == 0) goto L42
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
            L42:
                return
            L43:
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L65
                boolean r2 = java.lang.Thread.interrupted()     // Catch: java.lang.Throwable -> L68
                r1 = r1 | r2
                r3.run()     // Catch: java.lang.RuntimeException -> L4d java.lang.Throwable -> L68
                goto L2
            L4d:
                r2 = move-exception
                java.lang.String r4 = "SequentialExecutor"
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L68
                r5.<init>()     // Catch: java.lang.Throwable -> L68
                java.lang.String r6 = "Exception while executing runnable "
                r5.append(r6)     // Catch: java.lang.Throwable -> L68
                r5.append(r3)     // Catch: java.lang.Throwable -> L68
                java.lang.String r3 = r5.toString()     // Catch: java.lang.Throwable -> L68
                y.f1.d(r4, r3, r2)     // Catch: java.lang.Throwable -> L68
                goto L2
            L65:
                r0 = move-exception
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L65
                throw r0     // Catch: java.lang.Throwable -> L68
            L68:
                r0 = move-exception
                if (r1 == 0) goto L72
                java.lang.Thread r1 = java.lang.Thread.currentThread()
                r1.interrupt()
            L72:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: e0.i.b.a():void");
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                a();
            } catch (Error e8) {
                synchronized (i.this.f6546e) {
                    i.this.f6549h = c.IDLE;
                    throw e8;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum c {
        IDLE,
        QUEUING,
        QUEUED,
        RUNNING
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(Executor executor) {
        this.f6547f = (Executor) g1.e.j(executor);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        c cVar;
        g1.e.j(runnable);
        synchronized (this.f6546e) {
            c cVar2 = this.f6549h;
            if (cVar2 != c.RUNNING && cVar2 != (cVar = c.QUEUED)) {
                long j8 = this.f6550i;
                a aVar = new a(runnable);
                this.f6546e.add(aVar);
                c cVar3 = c.QUEUING;
                this.f6549h = cVar3;
                try {
                    this.f6547f.execute(this.f6548g);
                    if (this.f6549h != cVar3) {
                        return;
                    }
                    synchronized (this.f6546e) {
                        if (this.f6550i == j8 && this.f6549h == cVar3) {
                            this.f6549h = cVar;
                        }
                    }
                    return;
                } catch (Error | RuntimeException e8) {
                    synchronized (this.f6546e) {
                        c cVar4 = this.f6549h;
                        if ((cVar4 != c.IDLE && cVar4 != c.QUEUING) || !this.f6546e.removeLastOccurrence(aVar)) {
                            r0 = false;
                        }
                        if (!(e8 instanceof RejectedExecutionException) || r0) {
                            throw e8;
                        }
                    }
                    return;
                }
            }
            this.f6546e.add(runnable);
        }
    }
}
