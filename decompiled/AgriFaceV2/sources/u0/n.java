package u0;

import android.content.Context;
import androidx.concurrent.futures.c;
import b0.d2;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import t0.c;
import u0.n;
import u0.p;
import y.f1;
import y0.g1;
/* loaded from: classes.dex */
public final class n {

    /* renamed from: a  reason: collision with root package name */
    final Executor f11967a;

    /* renamed from: b  reason: collision with root package name */
    final AtomicReference f11968b;

    /* renamed from: c  reason: collision with root package name */
    final AtomicBoolean f11969c;

    /* renamed from: d  reason: collision with root package name */
    final p f11970d;

    /* renamed from: e  reason: collision with root package name */
    final e0 f11971e;

    /* renamed from: f  reason: collision with root package name */
    private final long f11972f;

    /* renamed from: g  reason: collision with root package name */
    e f11973g;

    /* renamed from: h  reason: collision with root package name */
    c.a f11974h;

    /* renamed from: i  reason: collision with root package name */
    boolean f11975i;

    /* renamed from: j  reason: collision with root package name */
    Executor f11976j;

    /* renamed from: k  reason: collision with root package name */
    c f11977k;

    /* renamed from: l  reason: collision with root package name */
    t0.c f11978l;

    /* renamed from: m  reason: collision with root package name */
    private f0.c f11979m;

    /* renamed from: n  reason: collision with root package name */
    private d2.a f11980n;

    /* renamed from: o  reason: collision with root package name */
    boolean f11981o;

    /* renamed from: p  reason: collision with root package name */
    private long f11982p;

    /* renamed from: q  reason: collision with root package name */
    boolean f11983q;

    /* renamed from: r  reason: collision with root package name */
    boolean f11984r;

    /* renamed from: s  reason: collision with root package name */
    private byte[] f11985s;

    /* renamed from: t  reason: collision with root package name */
    double f11986t;

    /* renamed from: u  reason: collision with root package name */
    long f11987u;

    /* renamed from: v  reason: collision with root package name */
    private final int f11988v;

    /* renamed from: w  reason: collision with root package name */
    public final int f11989w;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements d2.a {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ t0.c f11990a;

        a(t0.c cVar) {
            this.f11990a = cVar;
        }

        @Override // b0.d2.a
        public void a(Throwable th) {
            n nVar = n.this;
            if (nVar.f11978l == this.f11990a) {
                nVar.C(th);
            }
        }

        @Override // b0.d2.a
        /* renamed from: c */
        public void b(c.a aVar) {
            Objects.requireNonNull(aVar);
            if (n.this.f11978l == this.f11990a) {
                f1.a("AudioSource", "Receive BufferProvider state change: " + n.this.f11974h + " to " + aVar);
                n nVar = n.this;
                if (nVar.f11974h != aVar) {
                    nVar.f11974h = aVar;
                    nVar.S();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements f0.c {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ t0.c f11992a;

        b(t0.c cVar) {
            this.f11992a = cVar;
        }

        @Override // f0.c
        public void b(Throwable th) {
            if (n.this.f11978l != this.f11992a) {
                return;
            }
            f1.a("AudioSource", "Unable to get input buffer, the BufferProvider could be transitioning to INACTIVE state.");
            if (th instanceof IllegalStateException) {
                return;
            }
            n.this.C(th);
        }

        @Override // f0.c
        /* renamed from: c */
        public void a(g1 g1Var) {
            n nVar = n.this;
            if (!nVar.f11975i || nVar.f11978l != this.f11992a) {
                g1Var.cancel();
                return;
            }
            if (nVar.f11981o && nVar.p()) {
                n.this.J();
            }
            p m8 = n.this.m();
            ByteBuffer a8 = g1Var.a();
            p.c a9 = m8.a(a8);
            if (a9.a() > 0) {
                n nVar2 = n.this;
                if (nVar2.f11984r) {
                    nVar2.F(a8, a9.a());
                }
                if (n.this.f11976j != null) {
                    long b8 = a9.b();
                    n nVar3 = n.this;
                    if (b8 - nVar3.f11987u >= 200) {
                        nVar3.f11987u = a9.b();
                        n.this.G(a8);
                    }
                }
                a8.limit(a8.position() + a9.a());
                g1Var.e(TimeUnit.NANOSECONDS.toMicros(a9.b()));
                g1Var.c();
            } else {
                f1.l("AudioSource", "Unable to read data from AudioStream.");
                g1Var.cancel();
            }
            n.this.K();
        }
    }

    /* loaded from: classes.dex */
    public interface c {
        void a(Throwable th);

        void b(boolean z7);

        default void c(boolean z7) {
        }

        void d(double d8);
    }

    /* loaded from: classes.dex */
    class d implements p.a {
        d() {
        }

        @Override // u0.p.a
        public void b(boolean z7) {
            n nVar = n.this;
            nVar.f11983q = z7;
            if (nVar.f11973g == e.STARTED) {
                nVar.D();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum e {
        CONFIGURED,
        STARTED,
        RELEASED
    }

    public n(u0.a aVar, Executor executor, Context context) {
        this(aVar, executor, context, new q() { // from class: u0.g
            @Override // u0.q
            public final p a(a aVar2, Context context2) {
                return new s(aVar2, context2);
            }
        }, 3000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void A() {
        int ordinal = this.f11973g.ordinal();
        if (ordinal == 1) {
            N(e.CONFIGURED);
            S();
        } else if (ordinal != 2) {
        } else {
            f1.l("AudioSource", "AudioSource is released. Calling stop() is a no-op.");
        }
    }

    private void I(t0.c cVar) {
        t0.c cVar2 = this.f11978l;
        if (cVar2 != null) {
            d2.a aVar = this.f11980n;
            Objects.requireNonNull(aVar);
            cVar2.b(aVar);
            this.f11978l = null;
            this.f11980n = null;
            this.f11979m = null;
            this.f11974h = c.a.INACTIVE;
            S();
        }
        if (cVar != null) {
            this.f11978l = cVar;
            this.f11980n = new a(cVar);
            this.f11979m = new b(cVar);
            c.a l8 = l(cVar);
            if (l8 != null) {
                this.f11974h = l8;
                S();
            }
            this.f11978l.d(this.f11967a, this.f11980n);
        }
    }

    private void P() {
        if (this.f11975i) {
            return;
        }
        try {
            f1.a("AudioSource", "startSendingAudio");
            this.f11970d.start();
            this.f11981o = false;
        } catch (p.b e8) {
            f1.m("AudioSource", "Failed to start AudioStream", e8);
            this.f11981o = true;
            this.f11971e.start();
            this.f11982p = n();
            D();
        }
        this.f11975i = true;
        K();
    }

    private void R() {
        if (this.f11975i) {
            this.f11975i = false;
            f1.a("AudioSource", "stopSendingAudio");
            this.f11970d.stop();
        }
    }

    private static c.a l(t0.c cVar) {
        try {
            i4.d a8 = cVar.a();
            if (a8.isDone()) {
                return (c.a) a8.get();
            }
            return null;
        } catch (InterruptedException | ExecutionException unused) {
            return null;
        }
    }

    private static long n() {
        return System.nanoTime();
    }

    public static boolean o(int i8, int i9, int i10) {
        return s.l(i8, i9, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void q(boolean z7) {
        int ordinal = this.f11973g.ordinal();
        if (ordinal != 0 && ordinal != 1) {
            if (ordinal == 2) {
                throw new AssertionError("AudioSource is released");
            }
        } else if (this.f11984r == z7) {
        } else {
            this.f11984r = z7;
            if (this.f11973g == e.STARTED) {
                D();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void u(c cVar) {
        cVar.d(this.f11986t);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void v(c.a aVar) {
        try {
            int ordinal = this.f11973g.ordinal();
            if (ordinal == 0 || ordinal == 1) {
                I(null);
                this.f11971e.release();
                this.f11970d.release();
                R();
                N(e.RELEASED);
            }
            aVar.c(null);
        } catch (Throwable th) {
            aVar.f(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object w(final c.a aVar) {
        this.f11967a.execute(new Runnable() { // from class: u0.k
            @Override // java.lang.Runnable
            public final void run() {
                n.this.v(aVar);
            }
        });
        return "AudioSource-release";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void x(Executor executor, c cVar) {
        int ordinal = this.f11973g.ordinal();
        if (ordinal == 0) {
            this.f11976j = executor;
            this.f11977k = cVar;
        } else if (ordinal == 1 || ordinal == 2) {
            throw new AssertionError("The audio recording callback must be registered before the audio source is started.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void y(t0.c cVar) {
        int ordinal = this.f11973g.ordinal();
        if (ordinal != 0 && ordinal != 1) {
            if (ordinal == 2) {
                throw new AssertionError("AudioSource is released");
            }
        } else if (this.f11978l != cVar) {
            I(cVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void z(boolean z7) {
        int ordinal = this.f11973g.ordinal();
        if (ordinal != 0) {
            if (ordinal == 2) {
                throw new AssertionError("AudioSource is released");
            }
            return;
        }
        this.f11968b.set(null);
        this.f11969c.set(false);
        N(e.STARTED);
        B(z7);
        S();
    }

    public void B(final boolean z7) {
        this.f11967a.execute(new Runnable() { // from class: u0.c
            @Override // java.lang.Runnable
            public final void run() {
                n.this.q(z7);
            }
        });
    }

    void C(final Throwable th) {
        Executor executor = this.f11976j;
        final c cVar = this.f11977k;
        if (executor == null || cVar == null) {
            return;
        }
        executor.execute(new Runnable() { // from class: u0.d
            @Override // java.lang.Runnable
            public final void run() {
                n.c.this.a(th);
            }
        });
    }

    void D() {
        Executor executor = this.f11976j;
        final c cVar = this.f11977k;
        if (executor == null || cVar == null) {
            return;
        }
        final boolean z7 = this.f11984r || this.f11981o || this.f11983q;
        if (Objects.equals(this.f11968b.getAndSet(Boolean.valueOf(z7)), Boolean.valueOf(z7))) {
            return;
        }
        executor.execute(new Runnable() { // from class: u0.m
            @Override // java.lang.Runnable
            public final void run() {
                n.c.this.b(z7);
            }
        });
    }

    void E(final boolean z7) {
        Executor executor = this.f11976j;
        final c cVar = this.f11977k;
        if (executor == null || cVar == null || this.f11969c.getAndSet(z7) == z7) {
            return;
        }
        executor.execute(new Runnable() { // from class: u0.i
            @Override // java.lang.Runnable
            public final void run() {
                n.c.this.c(z7);
            }
        });
    }

    void F(ByteBuffer byteBuffer, int i8) {
        byte[] bArr = this.f11985s;
        if (bArr == null || bArr.length < i8) {
            this.f11985s = new byte[i8];
        }
        int position = byteBuffer.position();
        byteBuffer.put(this.f11985s, 0, i8);
        byteBuffer.limit(byteBuffer.position()).position(position);
    }

    void G(ByteBuffer byteBuffer) {
        Executor executor = this.f11976j;
        final c cVar = this.f11977k;
        if (this.f11988v == 2) {
            ShortBuffer asShortBuffer = byteBuffer.asShortBuffer();
            double d8 = 0.0d;
            while (asShortBuffer.hasRemaining()) {
                d8 = Math.max(d8, Math.abs((int) asShortBuffer.get()));
            }
            this.f11986t = d8 / 32767.0d;
            if (executor == null || cVar == null) {
                return;
            }
            executor.execute(new Runnable() { // from class: u0.l
                @Override // java.lang.Runnable
                public final void run() {
                    n.this.u(cVar);
                }
            });
        }
    }

    public i4.d H() {
        return androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: u0.f
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object w7;
                w7 = n.this.w(aVar);
                return w7;
            }
        });
    }

    void J() {
        g1.e.l(this.f11981o);
        try {
            this.f11970d.start();
            f1.a("AudioSource", "Retry start AudioStream succeed");
            this.f11971e.stop();
            this.f11981o = false;
        } catch (p.b e8) {
            f1.m("AudioSource", "Retry start AudioStream failed", e8);
            this.f11982p = n();
        }
    }

    void K() {
        t0.c cVar = this.f11978l;
        Objects.requireNonNull(cVar);
        i4.d c8 = cVar.c();
        f0.c cVar2 = this.f11979m;
        Objects.requireNonNull(cVar2);
        f0.n.j(c8, cVar2, this.f11967a);
    }

    public void L(final Executor executor, final c cVar) {
        this.f11967a.execute(new Runnable() { // from class: u0.j
            @Override // java.lang.Runnable
            public final void run() {
                n.this.x(executor, cVar);
            }
        });
    }

    public void M(final t0.c cVar) {
        this.f11967a.execute(new Runnable() { // from class: u0.b
            @Override // java.lang.Runnable
            public final void run() {
                n.this.y(cVar);
            }
        });
    }

    void N(e eVar) {
        f1.a("AudioSource", "Transitioning internal state: " + this.f11973g + " --> " + eVar);
        this.f11973g = eVar;
    }

    public void O(final boolean z7) {
        this.f11967a.execute(new Runnable() { // from class: u0.e
            @Override // java.lang.Runnable
            public final void run() {
                n.this.z(z7);
            }
        });
    }

    public void Q() {
        this.f11967a.execute(new Runnable() { // from class: u0.h
            @Override // java.lang.Runnable
            public final void run() {
                n.this.A();
            }
        });
    }

    void S() {
        if (this.f11973g == e.STARTED) {
            boolean z7 = this.f11974h == c.a.ACTIVE;
            E(!z7);
            if (z7) {
                P();
                return;
            }
        }
        R();
    }

    p m() {
        return this.f11981o ? this.f11971e : this.f11970d;
    }

    boolean p() {
        g1.e.l(this.f11982p > 0);
        return n() - this.f11982p >= this.f11972f;
    }

    n(u0.a aVar, Executor executor, Context context, q qVar, long j8) {
        this.f11968b = new AtomicReference(null);
        this.f11969c = new AtomicBoolean(false);
        this.f11973g = e.CONFIGURED;
        this.f11974h = c.a.INACTIVE;
        this.f11987u = 0L;
        Executor g8 = e0.c.g(executor);
        this.f11967a = g8;
        this.f11972f = TimeUnit.MILLISECONDS.toNanos(j8);
        try {
            c0 c0Var = new c0(qVar.a(aVar, context), aVar);
            this.f11970d = c0Var;
            c0Var.b(new d(), g8);
            this.f11971e = new e0(aVar);
            this.f11988v = aVar.b();
            this.f11989w = aVar.c();
        } catch (IllegalArgumentException | p.b e8) {
            throw new o("Unable to create AudioStream", e8);
        }
    }
}
