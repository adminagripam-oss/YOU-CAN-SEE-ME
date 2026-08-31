package u0;

import java.nio.ByteBuffer;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;
import u0.p;
import y.f1;
/* loaded from: classes.dex */
public class c0 implements p {

    /* renamed from: g  reason: collision with root package name */
    private final p f11931g;

    /* renamed from: h  reason: collision with root package name */
    private final int f11932h;

    /* renamed from: i  reason: collision with root package name */
    private final int f11933i;

    /* renamed from: j  reason: collision with root package name */
    private final int f11934j;

    /* renamed from: l  reason: collision with root package name */
    private int f11936l;

    /* renamed from: a  reason: collision with root package name */
    private final AtomicBoolean f11925a = new AtomicBoolean(false);

    /* renamed from: b  reason: collision with root package name */
    private final AtomicBoolean f11926b = new AtomicBoolean(false);

    /* renamed from: c  reason: collision with root package name */
    private final Queue f11927c = new ConcurrentLinkedQueue();

    /* renamed from: d  reason: collision with root package name */
    private final Executor f11928d = e0.c.g(e0.c.a());

    /* renamed from: e  reason: collision with root package name */
    private final Object f11929e = new Object();

    /* renamed from: f  reason: collision with root package name */
    private a f11930f = null;

    /* renamed from: k  reason: collision with root package name */
    private final AtomicBoolean f11935k = new AtomicBoolean(false);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final int f11937a;

        /* renamed from: b  reason: collision with root package name */
        private final int f11938b;

        /* renamed from: c  reason: collision with root package name */
        private final ByteBuffer f11939c;

        /* renamed from: d  reason: collision with root package name */
        private long f11940d;

        a(ByteBuffer byteBuffer, p.c cVar, int i8, int i9) {
            byteBuffer.rewind();
            int limit = byteBuffer.limit() - byteBuffer.position();
            if (limit == cVar.a()) {
                this.f11937a = i8;
                this.f11938b = i9;
                this.f11939c = byteBuffer;
                this.f11940d = cVar.b();
                return;
            }
            throw new IllegalStateException("Byte buffer size is not match with packet info: " + limit + " != " + cVar.a());
        }

        public int a() {
            return this.f11939c.remaining();
        }

        public p.c b(ByteBuffer byteBuffer) {
            int remaining;
            ByteBuffer byteBuffer2;
            long j8 = this.f11940d;
            int position = this.f11939c.position();
            int position2 = byteBuffer.position();
            if (this.f11939c.remaining() > byteBuffer.remaining()) {
                remaining = byteBuffer.remaining();
                this.f11940d += t.d(t.g(remaining, this.f11937a), this.f11938b);
                byteBuffer2 = this.f11939c.duplicate();
                byteBuffer2.position(position).limit(position + remaining);
            } else {
                remaining = this.f11939c.remaining();
                byteBuffer2 = this.f11939c;
            }
            byteBuffer.put(byteBuffer2).limit(position2 + remaining).position(position2);
            this.f11939c.position(position + remaining);
            return p.c.c(remaining, j8);
        }
    }

    public c0(p pVar, u0.a aVar) {
        this.f11931g = pVar;
        int d8 = aVar.d();
        this.f11932h = d8;
        int f8 = aVar.f();
        this.f11933i = f8;
        g1.e.b(((long) d8) > 0, "mBytesPerFrame must be greater than 0.");
        g1.e.b(((long) f8) > 0, "mSampleRate must be greater than 0.");
        this.f11934j = 500;
        this.f11936l = d8 * 1024;
    }

    private void i() {
        g1.e.m(!this.f11926b.get(), "AudioStream has been released.");
    }

    private void j() {
        g1.e.m(this.f11925a.get(), "AudioStream has not been started.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (this.f11935k.get()) {
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(this.f11936l);
            a aVar = new a(allocateDirect, this.f11931g.a(allocateDirect), this.f11932h, this.f11933i);
            int i8 = this.f11934j;
            synchronized (this.f11929e) {
                this.f11927c.offer(aVar);
                while (this.f11927c.size() > i8) {
                    this.f11927c.poll();
                    f1.l("BufferedAudioStream", "Drop audio data due to full of queue.");
                }
            }
            if (this.f11935k.get()) {
                this.f11928d.execute(new Runnable() { // from class: u0.b0
                    @Override // java.lang.Runnable
                    public final void run() {
                        c0.this.k();
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l() {
        this.f11935k.set(false);
        this.f11931g.release();
        synchronized (this.f11929e) {
            this.f11930f = null;
            this.f11927c.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m(p.a aVar, Executor executor) {
        this.f11931g.b(aVar, executor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n() {
        try {
            this.f11931g.start();
            q();
        } catch (p.b e8) {
            throw new RuntimeException(e8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o() {
        this.f11935k.set(false);
        this.f11931g.stop();
        synchronized (this.f11929e) {
            this.f11930f = null;
            this.f11927c.clear();
        }
    }

    private void q() {
        if (this.f11935k.getAndSet(true)) {
            return;
        }
        k();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r */
    public void p(int i8) {
        int i9 = this.f11936l;
        if (i9 == i8) {
            return;
        }
        int i10 = this.f11932h;
        this.f11936l = (i8 / i10) * i10;
        f1.a("BufferedAudioStream", "Update buffer size from " + i9 + " to " + this.f11936l);
    }

    private void s(final int i8) {
        this.f11928d.execute(new Runnable() { // from class: u0.x
            @Override // java.lang.Runnable
            public final void run() {
                c0.this.p(i8);
            }
        });
    }

    @Override // u0.p
    public p.c a(ByteBuffer byteBuffer) {
        boolean z7;
        i();
        j();
        s(byteBuffer.remaining());
        p.c c8 = p.c.c(0, 0L);
        do {
            synchronized (this.f11929e) {
                a aVar = this.f11930f;
                this.f11930f = null;
                if (aVar == null) {
                    aVar = (a) this.f11927c.poll();
                }
                if (aVar != null) {
                    c8 = aVar.b(byteBuffer);
                    if (aVar.a() > 0) {
                        this.f11930f = aVar;
                    }
                }
            }
            z7 = c8.a() <= 0 && this.f11925a.get() && !this.f11926b.get();
            if (z7) {
                try {
                    Thread.sleep(1L);
                } catch (InterruptedException e8) {
                    f1.m("BufferedAudioStream", "Interruption while waiting for audio data", e8);
                }
            }
        } while (z7);
        return c8;
    }

    @Override // u0.p
    public void b(final p.a aVar, final Executor executor) {
        boolean z7 = true;
        g1.e.m(!this.f11925a.get(), "AudioStream can not be started when setCallback.");
        i();
        if (aVar != null && executor == null) {
            z7 = false;
        }
        g1.e.b(z7, "executor can't be null with non-null callback.");
        this.f11928d.execute(new Runnable() { // from class: u0.w
            @Override // java.lang.Runnable
            public final void run() {
                c0.this.m(aVar, executor);
            }
        });
    }

    @Override // u0.p
    public void release() {
        if (this.f11926b.getAndSet(true)) {
            return;
        }
        this.f11928d.execute(new Runnable() { // from class: u0.a0
            @Override // java.lang.Runnable
            public final void run() {
                c0.this.l();
            }
        });
    }

    @Override // u0.p
    public void start() {
        i();
        if (this.f11925a.getAndSet(true)) {
            return;
        }
        FutureTask futureTask = new FutureTask(new Runnable() { // from class: u0.y
            @Override // java.lang.Runnable
            public final void run() {
                c0.this.n();
            }
        }, null);
        this.f11928d.execute(futureTask);
        try {
            futureTask.get();
        } catch (InterruptedException | ExecutionException e8) {
            this.f11925a.set(false);
            throw new p.b(e8);
        }
    }

    @Override // u0.p
    public void stop() {
        i();
        if (this.f11925a.getAndSet(false)) {
            this.f11928d.execute(new Runnable() { // from class: u0.z
                @Override // java.lang.Runnable
                public final void run() {
                    c0.this.o();
                }
            });
        }
    }
}
