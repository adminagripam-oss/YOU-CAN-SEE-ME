package u0;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import u0.p;
import y.f1;
/* loaded from: classes.dex */
public class e0 implements p {

    /* renamed from: a  reason: collision with root package name */
    private final AtomicBoolean f11946a = new AtomicBoolean(false);

    /* renamed from: b  reason: collision with root package name */
    private final AtomicBoolean f11947b = new AtomicBoolean(false);

    /* renamed from: c  reason: collision with root package name */
    private final int f11948c;

    /* renamed from: d  reason: collision with root package name */
    private final int f11949d;

    /* renamed from: e  reason: collision with root package name */
    private byte[] f11950e;

    /* renamed from: f  reason: collision with root package name */
    private long f11951f;

    /* renamed from: g  reason: collision with root package name */
    private p.a f11952g;

    /* renamed from: h  reason: collision with root package name */
    private Executor f11953h;

    public e0(a aVar) {
        this.f11948c = aVar.d();
        this.f11949d = aVar.f();
    }

    private static void d(long j8) {
        long g8 = j8 - g();
        if (g8 > 0) {
            try {
                Thread.sleep(TimeUnit.NANOSECONDS.toMillis(g8));
            } catch (InterruptedException e8) {
                f1.m("SilentAudioStream", "Ignore interruption", e8);
            }
        }
    }

    private void e() {
        g1.e.m(!this.f11947b.get(), "AudioStream has been released.");
    }

    private void f() {
        g1.e.m(this.f11946a.get(), "AudioStream has not been started.");
    }

    private static long g() {
        return System.nanoTime();
    }

    private void i() {
        final p.a aVar = this.f11952g;
        Executor executor = this.f11953h;
        if (aVar == null || executor == null) {
            return;
        }
        executor.execute(new Runnable() { // from class: u0.d0
            @Override // java.lang.Runnable
            public final void run() {
                p.a.this.b(true);
            }
        });
    }

    private void j(ByteBuffer byteBuffer, int i8) {
        g1.e.l(i8 <= byteBuffer.remaining());
        byte[] bArr = this.f11950e;
        if (bArr == null || bArr.length < i8) {
            this.f11950e = new byte[i8];
        }
        int position = byteBuffer.position();
        byteBuffer.put(this.f11950e, 0, i8).limit(i8 + position).position(position);
    }

    @Override // u0.p
    public p.c a(ByteBuffer byteBuffer) {
        e();
        f();
        long g8 = t.g(byteBuffer.remaining(), this.f11948c);
        int e8 = (int) t.e(g8, this.f11948c);
        if (e8 <= 0) {
            return p.c.c(0, this.f11951f);
        }
        long d8 = this.f11951f + t.d(g8, this.f11949d);
        d(d8);
        j(byteBuffer, e8);
        p.c c8 = p.c.c(e8, this.f11951f);
        this.f11951f = d8;
        return c8;
    }

    @Override // u0.p
    public void b(p.a aVar, Executor executor) {
        boolean z7 = true;
        g1.e.m(!this.f11946a.get(), "AudioStream can not be started when setCallback.");
        e();
        if (aVar != null && executor == null) {
            z7 = false;
        }
        g1.e.b(z7, "executor can't be null with non-null callback.");
        this.f11952g = aVar;
        this.f11953h = executor;
    }

    @Override // u0.p
    public void release() {
        this.f11947b.getAndSet(true);
    }

    @Override // u0.p
    public void start() {
        e();
        if (this.f11946a.getAndSet(true)) {
            return;
        }
        this.f11951f = g();
        i();
    }

    @Override // u0.p
    public void stop() {
        e();
        this.f11946a.set(false);
    }
}
