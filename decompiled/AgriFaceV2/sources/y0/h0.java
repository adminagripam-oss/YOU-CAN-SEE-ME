package y0;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Range;
import android.view.Surface;
import androidx.camera.video.internal.compat.quirk.AudioEncoderIgnoresInputTimestampQuirk;
import androidx.camera.video.internal.compat.quirk.CameraUseInconsistentTimebaseQuirk;
import androidx.camera.video.internal.compat.quirk.CodecStuckOnFlushQuirk;
import androidx.camera.video.internal.compat.quirk.EncoderNotUsePersistentInputSurfaceQuirk;
import androidx.camera.video.internal.compat.quirk.SignalEosOutputBufferNotComeQuirk;
import androidx.camera.video.internal.compat.quirk.StopCodecAfterSurfaceRemovalCrashMediaServerQuirk;
import androidx.camera.video.internal.compat.quirk.VideoEncoderSuspendDoesNotIncludeSuspendTimeQuirk;
import androidx.concurrent.futures.c;
import b0.d2;
import b0.z2;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import t0.c;
import y0.h0;
import y0.l;
/* loaded from: classes.dex */
public class h0 implements l {
    private static final Range E = Range.create(Long.MAX_VALUE, Long.MAX_VALUE);
    private Future D;

    /* renamed from: a  reason: collision with root package name */
    final String f13065a;

    /* renamed from: c  reason: collision with root package name */
    final boolean f13067c;

    /* renamed from: d  reason: collision with root package name */
    private final MediaFormat f13068d;

    /* renamed from: e  reason: collision with root package name */
    final MediaCodec f13069e;

    /* renamed from: f  reason: collision with root package name */
    final l.b f13070f;

    /* renamed from: g  reason: collision with root package name */
    private final e1 f13071g;

    /* renamed from: h  reason: collision with root package name */
    final Executor f13072h;

    /* renamed from: i  reason: collision with root package name */
    private final i4.d f13073i;

    /* renamed from: j  reason: collision with root package name */
    private final c.a f13074j;

    /* renamed from: p  reason: collision with root package name */
    final z2 f13080p;

    /* renamed from: t  reason: collision with root package name */
    d f13084t;

    /* renamed from: b  reason: collision with root package name */
    final Object f13066b = new Object();

    /* renamed from: k  reason: collision with root package name */
    final Queue f13075k = new ArrayDeque();

    /* renamed from: l  reason: collision with root package name */
    private final Queue f13076l = new ArrayDeque();

    /* renamed from: m  reason: collision with root package name */
    private final Set f13077m = new HashSet();

    /* renamed from: n  reason: collision with root package name */
    final Set f13078n = new HashSet();

    /* renamed from: o  reason: collision with root package name */
    final Deque f13079o = new ArrayDeque();

    /* renamed from: q  reason: collision with root package name */
    final n1 f13081q = new m1();

    /* renamed from: r  reason: collision with root package name */
    m f13082r = m.f13151a;

    /* renamed from: s  reason: collision with root package name */
    Executor f13083s = e0.c.b();

    /* renamed from: u  reason: collision with root package name */
    Range f13085u = E;

    /* renamed from: v  reason: collision with root package name */
    long f13086v = 0;

    /* renamed from: w  reason: collision with root package name */
    boolean f13087w = false;

    /* renamed from: x  reason: collision with root package name */
    Long f13088x = null;

    /* renamed from: y  reason: collision with root package name */
    Future f13089y = null;

    /* renamed from: z  reason: collision with root package name */
    private e f13090z = null;
    private boolean A = false;
    private boolean B = false;
    boolean C = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements f0.c {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: y0.h0$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class C0166a implements f0.c {
            C0166a() {
            }

            @Override // f0.c
            public void b(Throwable th) {
                if (th instanceof MediaCodec.CodecException) {
                    h0.this.I((MediaCodec.CodecException) th);
                } else {
                    h0.this.H(0, th.getMessage(), th);
                }
            }

            @Override // f0.c
            /* renamed from: c */
            public void a(Void r12) {
            }
        }

        a() {
        }

        @Override // f0.c
        public void b(Throwable th) {
            h0.this.H(0, "Unable to acquire InputBuffer.", th);
        }

        @Override // f0.c
        /* renamed from: c */
        public void a(g1 g1Var) {
            g1Var.e(h0.this.F());
            g1Var.b(true);
            g1Var.c();
            f0.n.j(g1Var.d(), new C0166a(), h0.this.f13072h);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b {
        static Surface a() {
            return MediaCodec.createPersistentInputSurface();
        }

        static void b(MediaCodec mediaCodec, Surface surface) {
            mediaCodec.setInputSurface(surface);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c implements l.a {

        /* renamed from: a  reason: collision with root package name */
        private final Map f13093a = new LinkedHashMap();

        /* renamed from: b  reason: collision with root package name */
        private c.a f13094b = c.a.INACTIVE;

        /* renamed from: c  reason: collision with root package name */
        private final List f13095c = new ArrayList();

        c() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: p */
        public void q(i4.d dVar) {
            if (dVar.cancel(true)) {
                return;
            }
            g1.e.l(dVar.isDone());
            try {
                ((g1) dVar.get()).cancel();
            } catch (InterruptedException | CancellationException | ExecutionException e8) {
                String str = h0.this.f13065a;
                y.f1.l(str, "Unable to cancel the input buffer: " + e8);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void r(i4.d dVar) {
            this.f13095c.remove(dVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void s(c.a aVar) {
            IllegalStateException illegalStateException;
            c.a aVar2 = this.f13094b;
            if (aVar2 == c.a.ACTIVE) {
                final i4.d C = h0.this.C();
                f0.n.C(C, aVar);
                aVar.a(new Runnable() { // from class: y0.i0
                    @Override // java.lang.Runnable
                    public final void run() {
                        h0.c.this.q(C);
                    }
                }, e0.c.b());
                this.f13095c.add(C);
                C.d(new Runnable() { // from class: y0.j0
                    @Override // java.lang.Runnable
                    public final void run() {
                        h0.c.this.r(C);
                    }
                }, h0.this.f13072h);
                return;
            }
            if (aVar2 == c.a.INACTIVE) {
                illegalStateException = new IllegalStateException("BufferProvider is not active.");
            } else {
                illegalStateException = new IllegalStateException("Unknown state: " + this.f13094b);
            }
            aVar.f(illegalStateException);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object t(final c.a aVar) {
            h0.this.f13072h.execute(new Runnable() { // from class: y0.q0
                @Override // java.lang.Runnable
                public final void run() {
                    h0.c.this.s(aVar);
                }
            });
            return "acquireBuffer";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void v(final d2.a aVar, Executor executor) {
            this.f13093a.put((d2.a) g1.e.j(aVar), (Executor) g1.e.j(executor));
            final c.a aVar2 = this.f13094b;
            executor.execute(new Runnable() { // from class: y0.r0
                @Override // java.lang.Runnable
                public final void run() {
                    d2.a.this.b(aVar2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void w(c.a aVar) {
            aVar.c(this.f13094b);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object x(final c.a aVar) {
            h0.this.f13072h.execute(new Runnable() { // from class: y0.n0
                @Override // java.lang.Runnable
                public final void run() {
                    h0.c.this.w(aVar);
                }
            });
            return "fetchData";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void y(d2.a aVar) {
            this.f13093a.remove(g1.e.j(aVar));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void z(Map.Entry entry, c.a aVar) {
            ((d2.a) entry.getKey()).b(aVar);
        }

        void A(boolean z7) {
            final c.a aVar = z7 ? c.a.ACTIVE : c.a.INACTIVE;
            if (this.f13094b == aVar) {
                return;
            }
            this.f13094b = aVar;
            if (aVar == c.a.INACTIVE) {
                for (i4.d dVar : this.f13095c) {
                    dVar.cancel(true);
                }
                this.f13095c.clear();
            }
            for (final Map.Entry entry : this.f13093a.entrySet()) {
                try {
                    ((Executor) entry.getValue()).execute(new Runnable() { // from class: y0.k0
                        @Override // java.lang.Runnable
                        public final void run() {
                            h0.c.z(entry, aVar);
                        }
                    });
                } catch (RejectedExecutionException e8) {
                    y.f1.d(h0.this.f13065a, "Unable to post to the supplied executor.", e8);
                }
            }
        }

        @Override // b0.d2
        public i4.d a() {
            return androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: y0.l0
                @Override // androidx.concurrent.futures.c.InterfaceC0018c
                public final Object a(c.a aVar) {
                    Object x7;
                    x7 = h0.c.this.x(aVar);
                    return x7;
                }
            });
        }

        @Override // b0.d2
        public void b(final d2.a aVar) {
            h0.this.f13072h.execute(new Runnable() { // from class: y0.o0
                @Override // java.lang.Runnable
                public final void run() {
                    h0.c.this.y(aVar);
                }
            });
        }

        @Override // t0.c
        public i4.d c() {
            return androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: y0.m0
                @Override // androidx.concurrent.futures.c.InterfaceC0018c
                public final Object a(c.a aVar) {
                    Object t8;
                    t8 = h0.c.this.t(aVar);
                    return t8;
                }
            });
        }

        @Override // b0.d2
        public void d(final Executor executor, final d2.a aVar) {
            h0.this.f13072h.execute(new Runnable() { // from class: y0.p0
                @Override // java.lang.Runnable
                public final void run() {
                    h0.c.this.v(aVar, executor);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum d {
        CONFIGURED,
        STARTED,
        PAUSED,
        STOPPING,
        PENDING_START,
        PENDING_START_PAUSED,
        PENDING_RELEASE,
        ERROR,
        RELEASED
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e extends MediaCodec.Callback {

        /* renamed from: a  reason: collision with root package name */
        private final a1.f f13107a;

        /* renamed from: b  reason: collision with root package name */
        private boolean f13108b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f13109c = false;

        /* renamed from: d  reason: collision with root package name */
        private boolean f13110d = false;

        /* renamed from: e  reason: collision with root package name */
        private boolean f13111e = false;

        /* renamed from: f  reason: collision with root package name */
        private long f13112f = 0;

        /* renamed from: g  reason: collision with root package name */
        private long f13113g = 0;

        /* renamed from: h  reason: collision with root package name */
        private boolean f13114h = false;

        /* renamed from: i  reason: collision with root package name */
        private boolean f13115i = false;

        /* renamed from: j  reason: collision with root package name */
        private boolean f13116j = false;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class a implements f0.c {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ k f13118a;

            a(k kVar) {
                this.f13118a = kVar;
            }

            @Override // f0.c
            public void b(Throwable th) {
                h0.this.f13078n.remove(this.f13118a);
                if (th instanceof MediaCodec.CodecException) {
                    h0.this.I((MediaCodec.CodecException) th);
                } else {
                    h0.this.H(0, th.getMessage(), th);
                }
            }

            @Override // f0.c
            /* renamed from: c */
            public void a(Void r22) {
                h0.this.f13078n.remove(this.f13118a);
            }
        }

        e() {
            this.f13108b = true;
            this.f13107a = h0.this.f13067c ? new a1.f(h0.this.f13081q, h0.this.f13080p, (CameraUseInconsistentTimebaseQuirk) androidx.camera.video.internal.compat.quirk.a.b(CameraUseInconsistentTimebaseQuirk.class)) : null;
            CodecStuckOnFlushQuirk codecStuckOnFlushQuirk = (CodecStuckOnFlushQuirk) androidx.camera.video.internal.compat.quirk.a.b(CodecStuckOnFlushQuirk.class);
            if (codecStuckOnFlushQuirk == null || !codecStuckOnFlushQuirk.g(h0.this.f13068d.getString("mime"))) {
                return;
            }
            this.f13108b = false;
        }

        private boolean i(MediaCodec.BufferInfo bufferInfo) {
            if (this.f13111e) {
                y.f1.a(h0.this.f13065a, "Drop buffer by already reach end of stream.");
                return false;
            } else if (bufferInfo.size <= 0) {
                y.f1.a(h0.this.f13065a, "Drop buffer by invalid buffer size.");
                return false;
            } else if ((bufferInfo.flags & 2) != 0) {
                y.f1.a(h0.this.f13065a, "Drop buffer by codec config.");
                return false;
            } else {
                a1.f fVar = this.f13107a;
                if (fVar != null) {
                    bufferInfo.presentationTimeUs = fVar.b(bufferInfo.presentationTimeUs);
                }
                long j8 = bufferInfo.presentationTimeUs;
                if (j8 <= this.f13112f) {
                    y.f1.a(h0.this.f13065a, "Drop buffer by out of order buffer from MediaCodec.");
                    return false;
                }
                this.f13112f = j8;
                if (!h0.this.f13085u.contains((Range) Long.valueOf(j8))) {
                    y.f1.a(h0.this.f13065a, "Drop buffer by not in start-stop range.");
                    h0 h0Var = h0.this;
                    if (h0Var.f13087w && bufferInfo.presentationTimeUs >= ((Long) h0Var.f13085u.getUpper()).longValue()) {
                        Future future = h0.this.f13089y;
                        if (future != null) {
                            future.cancel(true);
                        }
                        h0.this.f13088x = Long.valueOf(bufferInfo.presentationTimeUs);
                        h0.this.l0();
                        h0.this.f13087w = false;
                    }
                    return false;
                } else if (x(bufferInfo)) {
                    y.f1.a(h0.this.f13065a, "Drop buffer by pause.");
                    return false;
                } else if (h0.this.G(bufferInfo) <= this.f13113g) {
                    y.f1.a(h0.this.f13065a, "Drop buffer by adjusted time is less than the last sent time.");
                    if (h0.this.f13067c && h0.N(bufferInfo)) {
                        this.f13115i = true;
                    }
                    return false;
                } else {
                    if (!this.f13110d && !this.f13115i && h0.this.f13067c) {
                        this.f13115i = true;
                    }
                    if (this.f13115i) {
                        if (!h0.N(bufferInfo)) {
                            y.f1.a(h0.this.f13065a, "Drop buffer by not a key frame.");
                            h0.this.h0();
                            return false;
                        }
                        this.f13115i = false;
                    }
                    return true;
                }
            }
        }

        private boolean j(MediaCodec.BufferInfo bufferInfo) {
            return h0.K(bufferInfo) || (this.f13108b && k(bufferInfo));
        }

        private boolean k(MediaCodec.BufferInfo bufferInfo) {
            h0 h0Var = h0.this;
            return h0Var.C && bufferInfo.presentationTimeUs > ((Long) h0Var.f13085u.getUpper()).longValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void l(MediaCodec.CodecException codecException) {
            switch (h0.this.f13084t.ordinal()) {
                case 0:
                case 7:
                case 8:
                    return;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                    h0.this.I(codecException);
                    return;
                default:
                    throw new IllegalStateException("Unknown state: " + h0.this.f13084t);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void m(int i8) {
            if (this.f13116j) {
                y.f1.l(h0.this.f13065a, "Receives input frame after codec is reset.");
                return;
            }
            switch (h0.this.f13084t.ordinal()) {
                case 0:
                case 7:
                case 8:
                    return;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                    h0.this.f13075k.offer(Integer.valueOf(i8));
                    h0.this.e0();
                    return;
                default:
                    throw new IllegalStateException("Unknown state: " + h0.this.f13084t);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void n(MediaCodec.BufferInfo bufferInfo, MediaCodec mediaCodec, int i8) {
            final m mVar;
            Executor executor;
            if (this.f13116j) {
                y.f1.l(h0.this.f13065a, "Receives frame after codec is reset.");
                return;
            }
            switch (h0.this.f13084t.ordinal()) {
                case 0:
                case 7:
                case 8:
                    return;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                    synchronized (h0.this.f13066b) {
                        h0 h0Var = h0.this;
                        mVar = h0Var.f13082r;
                        executor = h0Var.f13083s;
                    }
                    if (!this.f13109c) {
                        this.f13109c = true;
                        try {
                            Objects.requireNonNull(mVar);
                            executor.execute(new Runnable() { // from class: y0.z0
                                @Override // java.lang.Runnable
                                public final void run() {
                                    m.this.a();
                                }
                            });
                        } catch (RejectedExecutionException e8) {
                            y.f1.d(h0.this.f13065a, "Unable to post to the supplied executor.", e8);
                        }
                    }
                    if (i(bufferInfo)) {
                        if (!this.f13110d) {
                            this.f13110d = true;
                            String str = h0.this.f13065a;
                            y.f1.a(str, "data timestampUs = " + bufferInfo.presentationTimeUs + ", data timebase = " + h0.this.f13080p + ", current system uptimeMs = " + SystemClock.uptimeMillis() + ", current system realtimeMs = " + SystemClock.elapsedRealtime());
                        }
                        MediaCodec.BufferInfo u7 = u(bufferInfo);
                        this.f13113g = u7.presentationTimeUs;
                        try {
                            v(new k(mediaCodec, i8, u7), mVar, executor);
                        } catch (MediaCodec.CodecException e9) {
                            h0.this.I(e9);
                            return;
                        }
                    } else {
                        try {
                            h0.this.f13069e.releaseOutputBuffer(i8, false);
                        } catch (MediaCodec.CodecException e10) {
                            h0.this.I(e10);
                            return;
                        }
                    }
                    if (this.f13111e || !j(bufferInfo)) {
                        return;
                    }
                    t();
                    return;
                default:
                    throw new IllegalStateException("Unknown state: " + h0.this.f13084t);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ MediaFormat o(MediaFormat mediaFormat) {
            return mediaFormat;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void p(m mVar, final MediaFormat mediaFormat) {
            mVar.f(new k1() { // from class: y0.c1
                @Override // y0.k1
                public final MediaFormat a() {
                    MediaFormat o8;
                    o8 = h0.e.o(mediaFormat);
                    return o8;
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void q(final MediaFormat mediaFormat) {
            final m mVar;
            Executor executor;
            if (this.f13116j) {
                y.f1.l(h0.this.f13065a, "Receives onOutputFormatChanged after codec is reset.");
                return;
            }
            switch (h0.this.f13084t.ordinal()) {
                case 0:
                case 7:
                case 8:
                    return;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                    synchronized (h0.this.f13066b) {
                        h0 h0Var = h0.this;
                        mVar = h0Var.f13082r;
                        executor = h0Var.f13083s;
                    }
                    try {
                        executor.execute(new Runnable() { // from class: y0.y0
                            @Override // java.lang.Runnable
                            public final void run() {
                                h0.e.p(m.this, mediaFormat);
                            }
                        });
                        return;
                    } catch (RejectedExecutionException e8) {
                        y.f1.d(h0.this.f13065a, "Unable to post to the supplied executor.", e8);
                        return;
                    }
                default:
                    throw new IllegalStateException("Unknown state: " + h0.this.f13084t);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void r(Executor executor, final m mVar) {
            if (h0.this.f13084t == d.ERROR) {
                return;
            }
            try {
                Objects.requireNonNull(mVar);
                executor.execute(new Runnable() { // from class: y0.s0
                    @Override // java.lang.Runnable
                    public final void run() {
                        m.this.c();
                    }
                });
            } catch (RejectedExecutionException e8) {
                y.f1.d(h0.this.f13065a, "Unable to post to the supplied executor.", e8);
            }
        }

        private MediaCodec.BufferInfo u(MediaCodec.BufferInfo bufferInfo) {
            long G = h0.this.G(bufferInfo);
            if (bufferInfo.presentationTimeUs == G) {
                return bufferInfo;
            }
            g1.e.l(G > this.f13113g);
            MediaCodec.BufferInfo bufferInfo2 = new MediaCodec.BufferInfo();
            bufferInfo2.set(bufferInfo.offset, bufferInfo.size, G, bufferInfo.flags);
            return bufferInfo2;
        }

        private void v(final k kVar, final m mVar, Executor executor) {
            h0.this.f13078n.add(kVar);
            f0.n.j(kVar.c(), new a(kVar), h0.this.f13072h);
            try {
                executor.execute(new Runnable() { // from class: y0.t0
                    @Override // java.lang.Runnable
                    public final void run() {
                        m.this.d(kVar);
                    }
                });
            } catch (RejectedExecutionException e8) {
                y.f1.d(h0.this.f13065a, "Unable to post to the supplied executor.", e8);
                kVar.close();
            }
        }

        private boolean x(MediaCodec.BufferInfo bufferInfo) {
            Executor executor;
            final m mVar;
            h0.this.p0(bufferInfo.presentationTimeUs);
            boolean M = h0.this.M(bufferInfo.presentationTimeUs);
            boolean z7 = this.f13114h;
            if (!z7 && M) {
                y.f1.a(h0.this.f13065a, "Switch to pause state");
                this.f13114h = true;
                synchronized (h0.this.f13066b) {
                    h0 h0Var = h0.this;
                    executor = h0Var.f13083s;
                    mVar = h0Var.f13082r;
                }
                Objects.requireNonNull(mVar);
                executor.execute(new Runnable() { // from class: y0.a1
                    @Override // java.lang.Runnable
                    public final void run() {
                        m.this.e();
                    }
                });
                h0 h0Var2 = h0.this;
                if (h0Var2.f13084t == d.PAUSED && ((h0Var2.f13067c || androidx.camera.video.internal.compat.quirk.a.b(AudioEncoderIgnoresInputTimestampQuirk.class) == null) && (!h0.this.f13067c || androidx.camera.video.internal.compat.quirk.a.b(VideoEncoderSuspendDoesNotIncludeSuspendTimeQuirk.class) == null))) {
                    l.b bVar = h0.this.f13070f;
                    if (bVar instanceof c) {
                        ((c) bVar).A(false);
                    }
                    h0.this.j0(true);
                }
                h0.this.f13088x = Long.valueOf(bufferInfo.presentationTimeUs);
                h0 h0Var3 = h0.this;
                if (h0Var3.f13087w) {
                    Future future = h0Var3.f13089y;
                    if (future != null) {
                        future.cancel(true);
                    }
                    h0.this.l0();
                    h0.this.f13087w = false;
                }
            } else if (z7 && !M) {
                y.f1.a(h0.this.f13065a, "Switch to resume state");
                this.f13114h = false;
                if (h0.this.f13067c && !h0.N(bufferInfo)) {
                    this.f13115i = true;
                }
            }
            return this.f13114h;
        }

        @Override // android.media.MediaCodec.Callback
        public void onError(MediaCodec mediaCodec, final MediaCodec.CodecException codecException) {
            h0.this.f13072h.execute(new Runnable() { // from class: y0.u0
                @Override // java.lang.Runnable
                public final void run() {
                    h0.e.this.l(codecException);
                }
            });
        }

        @Override // android.media.MediaCodec.Callback
        public void onInputBufferAvailable(MediaCodec mediaCodec, final int i8) {
            h0.this.f13072h.execute(new Runnable() { // from class: y0.w0
                @Override // java.lang.Runnable
                public final void run() {
                    h0.e.this.m(i8);
                }
            });
        }

        @Override // android.media.MediaCodec.Callback
        public void onOutputBufferAvailable(final MediaCodec mediaCodec, final int i8, final MediaCodec.BufferInfo bufferInfo) {
            h0.this.f13072h.execute(new Runnable() { // from class: y0.v0
                @Override // java.lang.Runnable
                public final void run() {
                    h0.e.this.n(bufferInfo, mediaCodec, i8);
                }
            });
        }

        @Override // android.media.MediaCodec.Callback
        public void onOutputFormatChanged(MediaCodec mediaCodec, final MediaFormat mediaFormat) {
            h0.this.f13072h.execute(new Runnable() { // from class: y0.x0
                @Override // java.lang.Runnable
                public final void run() {
                    h0.e.this.q(mediaFormat);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void t() {
            h0 h0Var;
            final m mVar;
            final Executor executor;
            if (this.f13111e) {
                return;
            }
            this.f13111e = true;
            if (h0.this.D != null) {
                h0.this.D.cancel(false);
                h0.this.D = null;
            }
            synchronized (h0.this.f13066b) {
                h0Var = h0.this;
                mVar = h0Var.f13082r;
                executor = h0Var.f13083s;
            }
            h0Var.o0(new Runnable() { // from class: y0.b1
                @Override // java.lang.Runnable
                public final void run() {
                    h0.e.this.r(executor, mVar);
                }
            });
        }

        void w() {
            this.f13116j = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class f implements l.c {

        /* renamed from: b  reason: collision with root package name */
        private Surface f13121b;

        /* renamed from: d  reason: collision with root package name */
        private l.c.a f13123d;

        /* renamed from: e  reason: collision with root package name */
        private Executor f13124e;

        /* renamed from: a  reason: collision with root package name */
        private final Object f13120a = new Object();

        /* renamed from: c  reason: collision with root package name */
        private final Set f13122c = new HashSet();

        f() {
        }

        private void c(Executor executor, final l.c.a aVar, final Surface surface) {
            try {
                executor.execute(new Runnable() { // from class: y0.d1
                    @Override // java.lang.Runnable
                    public final void run() {
                        l.c.a.this.a(surface);
                    }
                });
            } catch (RejectedExecutionException e8) {
                y.f1.d(h0.this.f13065a, "Unable to post to the supplied executor.", e8);
            }
        }

        void d() {
            Surface surface;
            HashSet<Surface> hashSet;
            synchronized (this.f13120a) {
                surface = this.f13121b;
                this.f13121b = null;
                hashSet = new HashSet(this.f13122c);
                this.f13122c.clear();
            }
            if (surface != null) {
                surface.release();
            }
            for (Surface surface2 : hashSet) {
                surface2.release();
            }
        }

        @Override // y0.l.c
        public void e(Executor executor, l.c.a aVar) {
            Surface surface;
            synchronized (this.f13120a) {
                this.f13123d = (l.c.a) g1.e.j(aVar);
                this.f13124e = (Executor) g1.e.j(executor);
                surface = this.f13121b;
            }
            if (surface != null) {
                c(executor, aVar, surface);
            }
        }

        void f() {
            Surface createInputSurface;
            l.c.a aVar;
            Executor executor;
            EncoderNotUsePersistentInputSurfaceQuirk encoderNotUsePersistentInputSurfaceQuirk = (EncoderNotUsePersistentInputSurfaceQuirk) androidx.camera.video.internal.compat.quirk.a.b(EncoderNotUsePersistentInputSurfaceQuirk.class);
            synchronized (this.f13120a) {
                if (encoderNotUsePersistentInputSurfaceQuirk == null) {
                    if (this.f13121b == null) {
                        createInputSurface = b.a();
                        this.f13121b = createInputSurface;
                    } else {
                        createInputSurface = null;
                    }
                    b.b(h0.this.f13069e, this.f13121b);
                } else {
                    Surface surface = this.f13121b;
                    if (surface != null) {
                        this.f13122c.add(surface);
                    }
                    createInputSurface = h0.this.f13069e.createInputSurface();
                    this.f13121b = createInputSurface;
                }
                aVar = this.f13123d;
                executor = this.f13124e;
            }
            if (createInputSurface == null || aVar == null || executor == null) {
                return;
            }
            c(executor, aVar, createInputSurface);
        }
    }

    public h0(Executor executor, n nVar) {
        e1 e1Var;
        g1.e.j(executor);
        g1.e.j(nVar);
        MediaCodec b8 = z0.a.b(nVar);
        this.f13069e = b8;
        MediaCodecInfo codecInfo = b8.getCodecInfo();
        this.f13072h = e0.c.g(executor);
        MediaFormat b9 = nVar.b();
        this.f13068d = b9;
        z2 a8 = nVar.a();
        this.f13080p = a8;
        if (nVar instanceof y0.a) {
            this.f13065a = "AudioEncoder";
            this.f13067c = false;
            this.f13070f = new c();
            e1Var = new y0.b(codecInfo, nVar.c());
        } else if (!(nVar instanceof o1)) {
            throw new j1("Unknown encoder config type");
        } else {
            this.f13065a = "VideoEncoder";
            this.f13067c = true;
            this.f13070f = new f();
            s1 s1Var = new s1(codecInfo, nVar.c());
            E(s1Var, b9);
            e1Var = s1Var;
        }
        this.f13071g = e1Var;
        String str = this.f13065a;
        y.f1.a(str, "mInputTimebase = " + a8);
        String str2 = this.f13065a;
        y.f1.a(str2, "mMediaFormat = " + b9);
        try {
            i0();
            final AtomicReference atomicReference = new AtomicReference();
            this.f13073i = f0.n.B(androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: y0.f0
                @Override // androidx.concurrent.futures.c.InterfaceC0018c
                public final Object a(c.a aVar) {
                    Object T;
                    T = h0.T(atomicReference, aVar);
                    return T;
                }
            }));
            this.f13074j = (c.a) g1.e.j((c.a) atomicReference.get());
            k0(d.CONFIGURED);
        } catch (MediaCodec.CodecException e8) {
            throw new j1(e8);
        }
    }

    private void D() {
        if (androidx.camera.video.internal.compat.quirk.a.b(SignalEosOutputBufferNotComeQuirk.class) != null) {
            final e eVar = this.f13090z;
            final Executor executor = this.f13072h;
            Future future = this.D;
            if (future != null) {
                future.cancel(false);
            }
            this.D = e0.c.e().schedule(new Runnable() { // from class: y0.a0
                @Override // java.lang.Runnable
                public final void run() {
                    h0.Q(executor, eVar);
                }
            }, 1000L, TimeUnit.MILLISECONDS);
        }
    }

    private void E(q1 q1Var, MediaFormat mediaFormat) {
        g1.e.l(this.f13067c);
        if (mediaFormat.containsKey("bitrate")) {
            int integer = mediaFormat.getInteger("bitrate");
            int intValue = ((Integer) q1Var.c().clamp(Integer.valueOf(integer))).intValue();
            if (integer != intValue) {
                mediaFormat.setInteger("bitrate", intValue);
                String str = this.f13065a;
                y.f1.a(str, "updated bitrate from " + integer + " to " + intValue);
            }
        }
    }

    static boolean K(MediaCodec.BufferInfo bufferInfo) {
        return (bufferInfo.flags & 4) != 0;
    }

    private boolean L() {
        return androidx.camera.video.internal.compat.quirk.a.b(StopCodecAfterSurfaceRemovalCrashMediaServerQuirk.class) != null;
    }

    static boolean N(MediaCodec.BufferInfo bufferInfo) {
        return (bufferInfo.flags & 1) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object O(AtomicReference atomicReference, c.a aVar) {
        atomicReference.set(aVar);
        return "acquireInputBuffer";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void P(c.a aVar) {
        this.f13076l.remove(aVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void Q(Executor executor, final e eVar) {
        Objects.requireNonNull(eVar);
        executor.execute(new Runnable() { // from class: y0.c0
            @Override // java.lang.Runnable
            public final void run() {
                h0.e.this.t();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void S(i1 i1Var) {
        this.f13077m.remove(i1Var);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object T(AtomicReference atomicReference, c.a aVar) {
        atomicReference.set(aVar);
        return "mReleasedFuture";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void U(m mVar, int i8, String str, Throwable th) {
        mVar.b(new h(i8, str, th));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void V(long j8) {
        d dVar;
        switch (this.f13084t.ordinal()) {
            case 0:
            case 2:
            case 3:
            case 5:
            case 7:
                return;
            case 1:
                String str = this.f13065a;
                y.f1.a(str, "Pause on " + t0.d.c(j8));
                this.f13079o.addLast(Range.create(Long.valueOf(j8), Long.MAX_VALUE));
                dVar = d.PAUSED;
                break;
            case 4:
                dVar = d.PENDING_START_PAUSED;
                break;
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
            case 8:
                throw new IllegalStateException("Encoder is released");
            default:
                throw new IllegalStateException("Unknown state: " + this.f13084t);
        }
        k0(dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void W() {
        switch (this.f13084t.ordinal()) {
            case 0:
            case 1:
            case 2:
            case 7:
                g0();
                return;
            case 3:
            case 4:
            case 5:
                k0(d.PENDING_RELEASE);
                return;
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
            case 8:
                return;
            default:
                throw new IllegalStateException("Unknown state: " + this.f13084t);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void X() {
        int ordinal = this.f13084t.ordinal();
        if (ordinal == 1) {
            h0();
        } else if (ordinal == 6 || ordinal == 8) {
            throw new IllegalStateException("Encoder is released");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void Y() {
        this.B = true;
        if (this.A) {
            this.f13069e.stop();
            i0();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void Z(long j8) {
        d dVar;
        switch (this.f13084t.ordinal()) {
            case 0:
                this.f13088x = null;
                String str = this.f13065a;
                y.f1.a(str, "Start on " + t0.d.c(j8));
                try {
                    if (this.A) {
                        i0();
                    }
                    this.f13085u = Range.create(Long.valueOf(j8), Long.MAX_VALUE);
                    this.f13069e.start();
                    l.b bVar = this.f13070f;
                    if (bVar instanceof c) {
                        ((c) bVar).A(true);
                    }
                    dVar = d.STARTED;
                    break;
                } catch (MediaCodec.CodecException e8) {
                    I(e8);
                    return;
                }
            case 1:
            case 4:
            case 7:
                return;
            case 2:
                this.f13088x = null;
                Range range = (Range) this.f13079o.removeLast();
                g1.e.m(range != null && ((Long) range.getUpper()).longValue() == Long.MAX_VALUE, "There should be a \"pause\" before \"resume\"");
                long longValue = ((Long) range.getLower()).longValue();
                this.f13079o.addLast(Range.create(Long.valueOf(longValue), Long.valueOf(j8)));
                String str2 = this.f13065a;
                y.f1.a(str2, "Resume on " + t0.d.c(j8) + "\nPaused duration = " + t0.d.c(j8 - longValue));
                if ((this.f13067c || androidx.camera.video.internal.compat.quirk.a.b(AudioEncoderIgnoresInputTimestampQuirk.class) == null) && (!this.f13067c || androidx.camera.video.internal.compat.quirk.a.b(VideoEncoderSuspendDoesNotIncludeSuspendTimeQuirk.class) == null)) {
                    j0(false);
                    l.b bVar2 = this.f13070f;
                    if (bVar2 instanceof c) {
                        ((c) bVar2).A(true);
                    }
                }
                if (this.f13067c) {
                    h0();
                }
                dVar = d.STARTED;
                break;
            case 3:
            case 5:
                dVar = d.PENDING_START;
                break;
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
            case 8:
                throw new IllegalStateException("Encoder is released");
            default:
                throw new IllegalStateException("Unknown state: " + this.f13084t);
        }
        k0(dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a0() {
        if (this.f13087w) {
            y.f1.l(this.f13065a, "The data didn't reach the expected timestamp before timeout, stop the codec.");
            this.f13088x = null;
            l0();
            this.f13087w = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b0() {
        this.f13072h.execute(new Runnable() { // from class: y0.z
            @Override // java.lang.Runnable
            public final void run() {
                h0.this.a0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00af  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ void c0(long r6, long r8) {
        /*
            r5 = this;
            y0.h0$d r0 = r5.f13084t
            int r0 = r0.ordinal()
            switch(r0) {
                case 0: goto Lbf;
                case 1: goto L31;
                case 2: goto L31;
                case 3: goto Lbf;
                case 4: goto L2a;
                case 5: goto L2a;
                case 6: goto L22;
                case 7: goto Lbf;
                case 8: goto L22;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "Unknown state: "
            r7.append(r8)
            y0.h0$d r8 = r5.f13084t
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        L22:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "Encoder is released"
            r6.<init>(r7)
            throw r6
        L2a:
            y0.h0$d r6 = y0.h0.d.CONFIGURED
            r5.k0(r6)
            goto Lbf
        L31:
            y0.h0$d r0 = r5.f13084t
            y0.h0$d r1 = y0.h0.d.STOPPING
            r5.k0(r1)
            android.util.Range r1 = r5.f13085u
            java.lang.Comparable r1 = r1.getLower()
            java.lang.Long r1 = (java.lang.Long) r1
            long r1 = r1.longValue()
            r3 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 == 0) goto Lb7
            r3 = -1
            int r3 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r3 != 0) goto L54
            goto L5f
        L54:
            int r3 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r3 >= 0) goto L60
            java.lang.String r6 = r5.f13065a
            java.lang.String r7 = "The expected stop time is less than the start time. Use current time as stop time."
            y.f1.l(r6, r7)
        L5f:
            r6 = r8
        L60:
            int r8 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r8 < 0) goto Laf
            java.lang.Long r8 = java.lang.Long.valueOf(r1)
            java.lang.Long r9 = java.lang.Long.valueOf(r6)
            android.util.Range r8 = android.util.Range.create(r8, r9)
            r5.f13085u = r8
            java.lang.String r8 = r5.f13065a
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r1 = "Stop on "
            r9.append(r1)
            java.lang.String r6 = t0.d.c(r6)
            r9.append(r6)
            java.lang.String r6 = r9.toString()
            y.f1.a(r8, r6)
            y0.h0$d r6 = y0.h0.d.PAUSED
            if (r0 != r6) goto L98
            java.lang.Long r6 = r5.f13088x
            if (r6 == 0) goto L98
            r5.l0()
            goto Lbf
        L98:
            r6 = 1
            r5.f13087w = r6
            java.util.concurrent.ScheduledExecutorService r6 = e0.c.e()
            y0.x r7 = new y0.x
            r7.<init>()
            r8 = 1000(0x3e8, double:4.94E-321)
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.MILLISECONDS
            java.util.concurrent.ScheduledFuture r6 = r6.schedule(r7, r8, r0)
            r5.f13089y = r6
            goto Lbf
        Laf:
            java.lang.AssertionError r6 = new java.lang.AssertionError
            java.lang.String r7 = "The start time should be before the stop time."
            r6.<init>(r7)
            throw r6
        Lb7:
            java.lang.AssertionError r6 = new java.lang.AssertionError
            java.lang.String r7 = "There should be a \"start\" before \"stop\""
            r6.<init>(r7)
            throw r6
        Lbf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: y0.h0.c0(long, long):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d0(List list, Runnable runnable) {
        if (this.f13084t != d.ERROR) {
            if (!list.isEmpty()) {
                y.f1.a(this.f13065a, "encoded data and input buffers are returned");
            }
            if (!(this.f13070f instanceof f) || this.B || L()) {
                this.f13069e.stop();
            } else {
                this.f13069e.flush();
                this.A = true;
            }
        }
        if (runnable != null) {
            runnable.run();
        }
        J();
    }

    private void g0() {
        if (this.A) {
            this.f13069e.stop();
            this.A = false;
        }
        this.f13069e.release();
        l.b bVar = this.f13070f;
        if (bVar instanceof f) {
            ((f) bVar).d();
        }
        k0(d.RELEASED);
        this.f13074j.c(null);
    }

    private void i0() {
        this.f13085u = E;
        this.f13086v = 0L;
        this.f13079o.clear();
        this.f13075k.clear();
        for (c.a aVar : this.f13076l) {
            aVar.d();
        }
        this.f13076l.clear();
        this.f13069e.reset();
        this.A = false;
        this.B = false;
        this.C = false;
        this.f13087w = false;
        Future future = this.f13089y;
        if (future != null) {
            future.cancel(true);
            this.f13089y = null;
        }
        Future future2 = this.D;
        if (future2 != null) {
            future2.cancel(false);
            this.D = null;
        }
        e eVar = this.f13090z;
        if (eVar != null) {
            eVar.w();
        }
        e eVar2 = new e();
        this.f13090z = eVar2;
        this.f13069e.setCallback(eVar2);
        this.f13069e.configure(this.f13068d, (Surface) null, (MediaCrypto) null, 1);
        l.b bVar = this.f13070f;
        if (bVar instanceof f) {
            ((f) bVar).f();
        }
    }

    private void k0(d dVar) {
        if (this.f13084t == dVar) {
            return;
        }
        String str = this.f13065a;
        y.f1.a(str, "Transitioning encoder internal state: " + this.f13084t + " --> " + dVar);
        this.f13084t = dVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m0() {
        f0.n.j(C(), new a(), this.f13072h);
    }

    i4.d C() {
        IllegalStateException illegalStateException;
        switch (this.f13084t.ordinal()) {
            case 0:
                illegalStateException = new IllegalStateException("Encoder is not started yet.");
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                final AtomicReference atomicReference = new AtomicReference();
                i4.d a8 = androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: y0.p
                    @Override // androidx.concurrent.futures.c.InterfaceC0018c
                    public final Object a(c.a aVar) {
                        Object O;
                        O = h0.O(atomicReference, aVar);
                        return O;
                    }
                });
                final c.a aVar = (c.a) g1.e.j((c.a) atomicReference.get());
                this.f13076l.offer(aVar);
                aVar.a(new Runnable() { // from class: y0.y
                    @Override // java.lang.Runnable
                    public final void run() {
                        h0.this.P(aVar);
                    }
                }, this.f13072h);
                e0();
                return a8;
            case 7:
                illegalStateException = new IllegalStateException("Encoder is in error state.");
                break;
            case 8:
                illegalStateException = new IllegalStateException("Encoder is released.");
                break;
            default:
                throw new IllegalStateException("Unknown state: " + this.f13084t);
        }
        return f0.n.n(illegalStateException);
    }

    long F() {
        return this.f13081q.a();
    }

    long G(MediaCodec.BufferInfo bufferInfo) {
        long j8 = this.f13086v;
        return j8 > 0 ? bufferInfo.presentationTimeUs - j8 : bufferInfo.presentationTimeUs;
    }

    void H(final int i8, final String str, final Throwable th) {
        switch (this.f13084t.ordinal()) {
            case 0:
                R(i8, str, th);
                i0();
                return;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                k0(d.ERROR);
                o0(new Runnable() { // from class: y0.g0
                    @Override // java.lang.Runnable
                    public final void run() {
                        h0.this.R(i8, str, th);
                    }
                });
                return;
            case 7:
                String str2 = this.f13065a;
                y.f1.m(str2, "Get more than one error: " + str + "(" + i8 + ")", th);
                return;
            default:
                return;
        }
    }

    void I(MediaCodec.CodecException codecException) {
        H(1, codecException.getMessage(), codecException);
    }

    void J() {
        d dVar = this.f13084t;
        if (dVar == d.PENDING_RELEASE) {
            g0();
            return;
        }
        if (!this.A) {
            i0();
        }
        k0(d.CONFIGURED);
        if (dVar == d.PENDING_START || dVar == d.PENDING_START_PAUSED) {
            start();
            if (dVar == d.PENDING_START_PAUSED) {
                a();
            }
        }
    }

    boolean M(long j8) {
        for (Range range : this.f13079o) {
            if (range.contains((Range) Long.valueOf(j8))) {
                return true;
            }
            if (j8 < ((Long) range.getLower()).longValue()) {
                break;
            }
        }
        return false;
    }

    @Override // y0.l
    public void a() {
        final long F = F();
        this.f13072h.execute(new Runnable() { // from class: y0.d0
            @Override // java.lang.Runnable
            public final void run() {
                h0.this.V(F);
            }
        });
    }

    @Override // y0.l
    public void b(final long j8) {
        final long F = F();
        this.f13072h.execute(new Runnable() { // from class: y0.r
            @Override // java.lang.Runnable
            public final void run() {
                h0.this.c0(j8, F);
            }
        });
    }

    @Override // y0.l
    public l.b c() {
        return this.f13070f;
    }

    @Override // y0.l
    public i4.d d() {
        return this.f13073i;
    }

    @Override // y0.l
    public void e(m mVar, Executor executor) {
        synchronized (this.f13066b) {
            this.f13082r = mVar;
            this.f13083s = executor;
        }
    }

    void e0() {
        while (!this.f13076l.isEmpty() && !this.f13075k.isEmpty()) {
            c.a aVar = (c.a) this.f13076l.poll();
            Objects.requireNonNull(aVar);
            Integer num = (Integer) this.f13075k.poll();
            Objects.requireNonNull(num);
            try {
                final i1 i1Var = new i1(this.f13069e, num.intValue());
                if (aVar.c(i1Var)) {
                    this.f13077m.add(i1Var);
                    i1Var.d().d(new Runnable() { // from class: y0.s
                        @Override // java.lang.Runnable
                        public final void run() {
                            h0.this.S(i1Var);
                        }
                    }, this.f13072h);
                } else {
                    i1Var.cancel();
                }
            } catch (MediaCodec.CodecException e8) {
                I(e8);
                return;
            }
        }
    }

    @Override // y0.l
    public void f() {
        this.f13072h.execute(new Runnable() { // from class: y0.b0
            @Override // java.lang.Runnable
            public final void run() {
                h0.this.X();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f0 */
    public void R(final int i8, final String str, final Throwable th) {
        final m mVar;
        Executor executor;
        synchronized (this.f13066b) {
            mVar = this.f13082r;
            executor = this.f13083s;
        }
        try {
            executor.execute(new Runnable() { // from class: y0.v
                @Override // java.lang.Runnable
                public final void run() {
                    h0.U(m.this, i8, str, th);
                }
            });
        } catch (RejectedExecutionException e8) {
            y.f1.d(this.f13065a, "Unable to post to the supplied executor.", e8);
        }
    }

    @Override // y0.l
    public int g() {
        if (this.f13068d.containsKey("bitrate")) {
            return this.f13068d.getInteger("bitrate");
        }
        return 0;
    }

    @Override // y0.l
    public e1 h() {
        return this.f13071g;
    }

    void h0() {
        Bundle bundle = new Bundle();
        bundle.putInt("request-sync", 0);
        this.f13069e.setParameters(bundle);
    }

    void j0(boolean z7) {
        Bundle bundle = new Bundle();
        bundle.putInt("drop-input-frames", z7 ? 1 : 0);
        this.f13069e.setParameters(bundle);
    }

    void l0() {
        y.f1.a(this.f13065a, "signalCodecStop");
        l.b bVar = this.f13070f;
        if (bVar instanceof c) {
            ((c) bVar).A(false);
            ArrayList arrayList = new ArrayList();
            for (g1 g1Var : this.f13077m) {
                arrayList.add(g1Var.d());
            }
            f0.n.F(arrayList).d(new Runnable() { // from class: y0.w
                @Override // java.lang.Runnable
                public final void run() {
                    h0.this.m0();
                }
            }, this.f13072h);
        } else if (bVar instanceof f) {
            try {
                D();
                this.f13069e.signalEndOfInputStream();
                this.C = true;
            } catch (MediaCodec.CodecException e8) {
                I(e8);
            }
        }
    }

    public void n0() {
        this.f13072h.execute(new Runnable() { // from class: y0.u
            @Override // java.lang.Runnable
            public final void run() {
                h0.this.Y();
            }
        });
    }

    void o0(final Runnable runnable) {
        y.f1.a(this.f13065a, "stopMediaCodec");
        final ArrayList arrayList = new ArrayList();
        for (k kVar : this.f13078n) {
            arrayList.add(kVar.c());
        }
        for (g1 g1Var : this.f13077m) {
            arrayList.add(g1Var.d());
        }
        if (!arrayList.isEmpty()) {
            String str = this.f13065a;
            y.f1.a(str, "Waiting for resources to return. encoded data = " + this.f13078n.size() + ", input buffers = " + this.f13077m.size());
        }
        f0.n.F(arrayList).d(new Runnable() { // from class: y0.t
            @Override // java.lang.Runnable
            public final void run() {
                h0.this.d0(arrayList, runnable);
            }
        }, this.f13072h);
    }

    void p0(long j8) {
        while (!this.f13079o.isEmpty()) {
            Range range = (Range) this.f13079o.getFirst();
            if (j8 <= ((Long) range.getUpper()).longValue()) {
                return;
            }
            this.f13079o.removeFirst();
            this.f13086v += ((Long) range.getUpper()).longValue() - ((Long) range.getLower()).longValue();
            y.f1.a(this.f13065a, "Total paused duration = " + t0.d.c(this.f13086v));
        }
    }

    @Override // y0.l
    public void release() {
        this.f13072h.execute(new Runnable() { // from class: y0.q
            @Override // java.lang.Runnable
            public final void run() {
                h0.this.W();
            }
        });
    }

    @Override // y0.l
    public void start() {
        final long F = F();
        this.f13072h.execute(new Runnable() { // from class: y0.e0
            @Override // java.lang.Runnable
            public final void run() {
                h0.this.Z(F);
            }
        });
    }
}
