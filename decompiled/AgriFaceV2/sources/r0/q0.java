package r0;

import android.content.Context;
import android.location.Location;
import android.media.MediaMuxer;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.Pair;
import android.util.Range;
import android.util.Size;
import android.view.Surface;
import androidx.camera.video.internal.compat.quirk.DeactivateEncoderSurfaceBeforeStopEncoderQuirk;
import androidx.camera.video.internal.compat.quirk.EncoderNotUsePersistentInputSurfaceQuirk;
import androidx.concurrent.futures.c;
import b0.d2;
import b0.t2;
import b0.z2;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import r0.b2;
import r0.c1;
import r0.c2;
import r0.d2;
import r0.q0;
import r0.r;
import u0.n;
import y.g2;
import y0.l;
/* loaded from: classes.dex */
public final class q0 implements b2 {

    /* renamed from: j0  reason: collision with root package name */
    private static final Set f11119j0 = Collections.unmodifiableSet(EnumSet.of(l.PENDING_RECORDING, l.PENDING_PAUSED));

    /* renamed from: k0  reason: collision with root package name */
    private static final Set f11120k0 = Collections.unmodifiableSet(EnumSet.of(l.CONFIGURING, l.IDLING, l.RESETTING, l.STOPPING, l.ERROR));

    /* renamed from: l0  reason: collision with root package name */
    public static final y f11121l0;

    /* renamed from: m0  reason: collision with root package name */
    private static final d2 f11122m0;

    /* renamed from: n0  reason: collision with root package name */
    private static final r f11123n0;

    /* renamed from: o0  reason: collision with root package name */
    private static final Exception f11124o0;

    /* renamed from: p0  reason: collision with root package name */
    static final y0.o f11125p0;

    /* renamed from: q0  reason: collision with root package name */
    private static final Executor f11126q0;

    /* renamed from: r0  reason: collision with root package name */
    static int f11127r0;

    /* renamed from: s0  reason: collision with root package name */
    static long f11128s0;
    Surface A;
    Surface B;
    MediaMuxer C;
    final b0.b2 D;
    u0.n E;
    y0.l F;
    y0.k1 G;
    y0.l H;
    y0.k1 I;
    h J;
    Uri K;
    long L;
    long M;
    long N;
    int O;
    Range P;
    long Q;
    long R;
    long S;
    long T;
    long U;
    int V;
    Throwable W;
    y0.i X;
    final k0.c Y;
    Throwable Z;

    /* renamed from: a  reason: collision with root package name */
    private final b0.b2 f11129a;

    /* renamed from: a0  reason: collision with root package name */
    boolean f11130a0;

    /* renamed from: b  reason: collision with root package name */
    private final b0.b2 f11131b;

    /* renamed from: b0  reason: collision with root package name */
    b2.a f11132b0;

    /* renamed from: c  reason: collision with root package name */
    private final Executor f11133c;

    /* renamed from: c0  reason: collision with root package name */
    ScheduledFuture f11134c0;

    /* renamed from: d  reason: collision with root package name */
    private final Executor f11135d;

    /* renamed from: d0  reason: collision with root package name */
    private boolean f11136d0;

    /* renamed from: e  reason: collision with root package name */
    final Executor f11137e;

    /* renamed from: e0  reason: collision with root package name */
    a2 f11138e0;

    /* renamed from: f  reason: collision with root package name */
    private final y0.o f11139f;

    /* renamed from: f0  reason: collision with root package name */
    a2 f11140f0;

    /* renamed from: g  reason: collision with root package name */
    private final y0.o f11141g;

    /* renamed from: g0  reason: collision with root package name */
    double f11142g0;

    /* renamed from: h  reason: collision with root package name */
    private final Object f11143h = new Object();

    /* renamed from: h0  reason: collision with root package name */
    private boolean f11144h0;

    /* renamed from: i  reason: collision with root package name */
    private final boolean f11145i;

    /* renamed from: i0  reason: collision with root package name */
    private k f11146i0;

    /* renamed from: j  reason: collision with root package name */
    private final int f11147j;

    /* renamed from: k  reason: collision with root package name */
    private l f11148k;

    /* renamed from: l  reason: collision with root package name */
    private l f11149l;

    /* renamed from: m  reason: collision with root package name */
    int f11150m;

    /* renamed from: n  reason: collision with root package name */
    j f11151n;

    /* renamed from: o  reason: collision with root package name */
    j f11152o;

    /* renamed from: p  reason: collision with root package name */
    private long f11153p;

    /* renamed from: q  reason: collision with root package name */
    j f11154q;

    /* renamed from: r  reason: collision with root package name */
    boolean f11155r;

    /* renamed from: s  reason: collision with root package name */
    private g2.h f11156s;

    /* renamed from: t  reason: collision with root package name */
    private g2.h f11157t;

    /* renamed from: u  reason: collision with root package name */
    private t0.g f11158u;

    /* renamed from: v  reason: collision with root package name */
    final List f11159v;

    /* renamed from: w  reason: collision with root package name */
    Integer f11160w;

    /* renamed from: x  reason: collision with root package name */
    Integer f11161x;

    /* renamed from: y  reason: collision with root package name */
    g2 f11162y;

    /* renamed from: z  reason: collision with root package name */
    z2 f11163z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements f0.c {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ a2 f11164a;

        a(a2 a2Var) {
            this.f11164a = a2Var;
        }

        @Override // f0.c
        public void b(Throwable th) {
            y.f1.a("Recorder", "Error in ReadyToReleaseFuture: " + th);
        }

        @Override // f0.c
        /* renamed from: c */
        public void a(y0.l lVar) {
            y0.l lVar2;
            y.f1.a("Recorder", "VideoEncoder can be released: " + lVar);
            if (lVar == null) {
                return;
            }
            ScheduledFuture scheduledFuture = q0.this.f11134c0;
            if (scheduledFuture != null && scheduledFuture.cancel(false) && (lVar2 = q0.this.F) != null && lVar2 == lVar) {
                q0.i0(lVar2);
            }
            q0 q0Var = q0.this;
            q0Var.f11140f0 = this.f11164a;
            q0Var.G0(null);
            q0 q0Var2 = q0.this;
            q0Var2.w0(4, null, q0Var2.R());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements f0.c {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ u0.n f11166a;

        b(u0.n nVar) {
            this.f11166a = nVar;
        }

        @Override // f0.c
        public void b(Throwable th) {
            y.f1.a("Recorder", String.format("An error occurred while attempting to release audio source: 0x%x", Integer.valueOf(this.f11166a.hashCode())));
        }

        @Override // f0.c
        /* renamed from: c */
        public void a(Void r22) {
            y.f1.a("Recorder", String.format("Released audio source successfully: 0x%x", Integer.valueOf(this.f11166a.hashCode())));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c implements y0.m {

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ c.a f11168b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ j f11169c;

        c(c.a aVar, j jVar) {
            this.f11168b = aVar;
            this.f11169c = jVar;
        }

        @Override // y0.m
        public void a() {
        }

        @Override // y0.m
        public void b(y0.h hVar) {
            this.f11168b.f(hVar);
        }

        @Override // y0.m
        public void c() {
            this.f11168b.c(null);
        }

        @Override // y0.m
        public void d(y0.i iVar) {
            boolean z7;
            q0 q0Var = q0.this;
            if (q0Var.C != null) {
                try {
                    q0Var.Y0(iVar, this.f11169c);
                    if (iVar == null) {
                        return;
                    }
                } catch (Throwable th) {
                    if (iVar != null) {
                        try {
                            iVar.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } else if (q0Var.f11155r) {
                y.f1.a("Recorder", "Drop video data since recording is stopping.");
            } else {
                y0.i iVar2 = q0Var.X;
                if (iVar2 != null) {
                    iVar2.close();
                    q0.this.X = null;
                    z7 = true;
                } else {
                    z7 = false;
                }
                if (iVar.I()) {
                    q0 q0Var2 = q0.this;
                    q0Var2.X = iVar;
                    if (q0Var2.P() && q0.this.Y.isEmpty()) {
                        y.f1.a("Recorder", z7 ? "Replaced cached video keyframe with newer keyframe." : "Cached video keyframe while we wait for first audio sample before starting muxer.");
                        return;
                    }
                    y.f1.a("Recorder", "Received video keyframe. Starting muxer...");
                    q0.this.J0(this.f11169c);
                    return;
                }
                if (z7) {
                    y.f1.a("Recorder", "Dropped cached keyframe since we have new video data and have not yet received audio data.");
                }
                y.f1.a("Recorder", "Dropped video data since muxer has not yet started and data is not a keyframe.");
                q0.this.F.f();
            }
            iVar.close();
        }

        @Override // y0.m
        public void f(y0.k1 k1Var) {
            q0.this.G = k1Var;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d implements n.c {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ g1.a f11171a;

        d(g1.a aVar) {
            this.f11171a = aVar;
        }

        @Override // u0.n.c
        public void a(Throwable th) {
            y.f1.d("Recorder", "Error occurred after audio source started.", th);
            if (th instanceof u0.o) {
                this.f11171a.accept(th);
            }
        }

        @Override // u0.n.c
        public void b(boolean z7) {
            q0 q0Var = q0.this;
            if (q0Var.f11130a0 != z7) {
                q0Var.f11130a0 = z7;
                q0Var.V0();
                return;
            }
            y.f1.l("Recorder", "Audio source silenced transitions to the same state " + z7);
        }

        @Override // u0.n.c
        public void d(double d8) {
            q0.this.f11142g0 = d8;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e implements y0.m {

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ c.a f11173b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ g1.a f11174c;

        /* renamed from: d  reason: collision with root package name */
        final /* synthetic */ j f11175d;

        e(c.a aVar, g1.a aVar2, j jVar) {
            this.f11173b = aVar;
            this.f11174c = aVar2;
            this.f11175d = jVar;
        }

        @Override // y0.m
        public void a() {
        }

        @Override // y0.m
        public void b(y0.h hVar) {
            if (q0.this.Z == null) {
                this.f11174c.accept(hVar);
            }
        }

        @Override // y0.m
        public void c() {
            this.f11173b.c(null);
        }

        @Override // y0.m
        public void d(y0.i iVar) {
            String str;
            q0 q0Var = q0.this;
            if (q0Var.J == h.DISABLED) {
                iVar.close();
                throw new AssertionError("Audio is not enabled but audio encoded data is being produced.");
            }
            if (q0Var.C == null) {
                if (q0Var.f11155r) {
                    str = "Drop audio data since recording is stopping.";
                } else {
                    q0Var.Y.b(new y0.g(iVar));
                    if (q0.this.X != null) {
                        y.f1.a("Recorder", "Received audio data. Starting muxer...");
                        q0.this.J0(this.f11175d);
                    } else {
                        str = "Cached audio data while we wait for video keyframe before starting muxer.";
                    }
                }
                y.f1.a("Recorder", str);
            } else {
                try {
                    q0Var.X0(iVar, this.f11175d);
                    if (iVar == null) {
                        return;
                    }
                } catch (Throwable th) {
                    if (iVar != null) {
                        try {
                            iVar.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            }
            iVar.close();
        }

        @Override // y0.m
        public void f(y0.k1 k1Var) {
            q0.this.I = k1Var;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class f implements f0.c {
        f() {
        }

        @Override // f0.c
        public void b(Throwable th) {
            g1.e.m(q0.this.f11154q != null, "In-progress recording shouldn't be null");
            if (q0.this.f11154q.D()) {
                return;
            }
            y.f1.a("Recorder", "Encodings end with error: " + th);
            q0 q0Var = q0.this;
            q0Var.E(q0Var.C == null ? 8 : 6, th);
        }

        @Override // f0.c
        /* renamed from: c */
        public void a(List list) {
            y.f1.a("Recorder", "Encodings end successfully.");
            q0 q0Var = q0.this;
            q0Var.E(q0Var.V, q0Var.W);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class g implements d2.a {
        g() {
        }

        @Override // b0.d2.a
        public void a(Throwable th) {
            q0.this.f11131b.j(th);
        }

        @Override // b0.d2.a
        /* renamed from: c */
        public void b(Boolean bool) {
            q0.this.f11131b.k(bool);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum h {
        INITIALIZING,
        IDLING,
        DISABLED,
        ENABLED,
        ERROR_ENCODER,
        ERROR_SOURCE
    }

    /* loaded from: classes.dex */
    public static final class i {

        /* renamed from: a  reason: collision with root package name */
        private final r.a f11186a;

        /* renamed from: b  reason: collision with root package name */
        private int f11187b = 0;

        /* renamed from: c  reason: collision with root package name */
        private Executor f11188c = null;

        /* renamed from: d  reason: collision with root package name */
        private y0.o f11189d;

        /* renamed from: e  reason: collision with root package name */
        private y0.o f11190e;

        public i() {
            y0.o oVar = q0.f11125p0;
            this.f11189d = oVar;
            this.f11190e = oVar;
            this.f11186a = r.a();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void g(int i8, d2.a aVar) {
            aVar.c(new Range(Integer.valueOf(i8), Integer.valueOf(i8)));
        }

        public q0 d() {
            return new q0(this.f11188c, this.f11186a.a(), this.f11187b, this.f11189d, this.f11190e);
        }

        public i h(final int i8) {
            this.f11186a.b(new g1.a() { // from class: r0.t0
                @Override // g1.a
                public final void accept(Object obj) {
                    ((d2.a) obj).b(i8);
                }
            });
            return this;
        }

        public i i(Executor executor) {
            g1.e.k(executor, "The specified executor can't be null.");
            this.f11188c = executor;
            return this;
        }

        public i j(final y yVar) {
            g1.e.k(yVar, "The specified quality selector can't be null.");
            this.f11186a.b(new g1.a() { // from class: r0.r0
                @Override // g1.a
                public final void accept(Object obj) {
                    ((d2.a) obj).e(y.this);
                }
            });
            return this;
        }

        public i k(final int i8) {
            if (i8 > 0) {
                this.f11186a.b(new g1.a() { // from class: r0.s0
                    @Override // g1.a
                    public final void accept(Object obj) {
                        q0.i.g(i8, (d2.a) obj);
                    }
                });
                return this;
            }
            throw new IllegalArgumentException("The requested target bitrate " + i8 + " is not supported. Target bitrate must be greater than 0.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class j implements AutoCloseable {

        /* renamed from: e  reason: collision with root package name */
        private final d0.d f11191e = d0.d.b();

        /* renamed from: f  reason: collision with root package name */
        private final AtomicBoolean f11192f = new AtomicBoolean(false);

        /* renamed from: g  reason: collision with root package name */
        private final AtomicReference f11193g = new AtomicReference(null);

        /* renamed from: h  reason: collision with root package name */
        private final AtomicReference f11194h = new AtomicReference(null);

        /* renamed from: i  reason: collision with root package name */
        private final AtomicReference f11195i = new AtomicReference(new g1.a() { // from class: r0.w0
            @Override // g1.a
            public final void accept(Object obj) {
                q0.j.G((Uri) obj);
            }
        });

        /* renamed from: j  reason: collision with root package name */
        private final AtomicBoolean f11196j = new AtomicBoolean(false);

        /* renamed from: k  reason: collision with root package name */
        private final b0.b2 f11197k = b0.b2.l(Boolean.FALSE);

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class a implements c {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ Context f11198a;

            a(Context context) {
                this.f11198a = context;
            }

            @Override // r0.q0.j.c
            public u0.n a(u0.a aVar, Executor executor) {
                return new u0.n(aVar, executor, this.f11198a);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class b implements c {
            b() {
            }

            @Override // r0.q0.j.c
            public u0.n a(u0.a aVar, Executor executor) {
                return new u0.n(aVar, executor, null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public interface c {
            u0.n a(u0.a aVar, Executor executor);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public interface d {
            MediaMuxer a(int i8, g1.a aVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ MediaMuxer E(s sVar, ParcelFileDescriptor parcelFileDescriptor, int i8, g1.a aVar) {
            Uri uri = Uri.EMPTY;
            if (!(sVar instanceof q)) {
                throw new AssertionError("Invalid output options type: " + sVar.getClass().getSimpleName());
            }
            File d8 = ((q) sVar).d();
            if (!z0.d.a(d8)) {
                y.f1.l("Recorder", "Failed to create folder for " + d8.getAbsolutePath());
            }
            MediaMuxer mediaMuxer = new MediaMuxer(d8.getAbsolutePath(), i8);
            aVar.accept(Uri.fromFile(d8));
            return mediaMuxer;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void G(Uri uri) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void M(c2 c2Var) {
            q().accept(c2Var);
        }

        private void R(c2 c2Var) {
            b0.b2 b2Var;
            Boolean bool;
            if ((c2Var instanceof c2.d) || (c2Var instanceof c2.c)) {
                b2Var = this.f11197k;
                bool = Boolean.TRUE;
            } else if (!(c2Var instanceof c2.b) && !(c2Var instanceof c2.a)) {
                return;
            } else {
                b2Var = this.f11197k;
                bool = Boolean.FALSE;
            }
            b2Var.k(bool);
        }

        private void g(g1.a aVar, Uri uri) {
            if (aVar != null) {
                this.f11191e.a();
                aVar.accept(uri);
                return;
            }
            throw new AssertionError("Recording " + this + " has already been finalized");
        }

        static j j(u uVar, long j8) {
            return new r0.k(uVar.d(), uVar.c(), uVar.b(), uVar.f(), uVar.g(), j8);
        }

        void A(Context context) {
            if (this.f11192f.getAndSet(true)) {
                throw new AssertionError("Recording " + this + " has already been initialized");
            }
            final s t8 = t();
            this.f11191e.c("finalizeRecording");
            this.f11193g.set(new d() { // from class: r0.u0
                @Override // r0.q0.j.d
                public final MediaMuxer a(int i8, g1.a aVar) {
                    MediaMuxer E;
                    E = q0.j.E(s.this, r2, i8, aVar);
                    return E;
                }
            });
            if (x()) {
                if (Build.VERSION.SDK_INT >= 31) {
                    this.f11194h.set(new a(context));
                    return;
                }
                this.f11194h.set(new b());
            }
        }

        boolean C() {
            return this.f11196j.get();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract boolean D();

        u0.n P(u0.a aVar, Executor executor) {
            if (!x()) {
                throw new AssertionError("Recording does not have audio enabled. Unable to create audio source for recording " + this);
            }
            c cVar = (c) this.f11194h.getAndSet(null);
            if (cVar != null) {
                return cVar.a(aVar, executor);
            }
            throw new AssertionError("One-time audio source creation has already occurred for recording " + this);
        }

        MediaMuxer Q(int i8, g1.a aVar) {
            if (!this.f11192f.get()) {
                throw new AssertionError("Recording " + this + " has not been initialized");
            }
            d dVar = (d) this.f11193g.getAndSet(null);
            if (dVar == null) {
                throw new AssertionError("One-time media muxer creation has already occurred for recording " + this);
            }
            try {
                return dVar.a(i8, aVar);
            } catch (RuntimeException e8) {
                throw new IOException("Failed to create MediaMuxer by " + e8, e8);
            }
        }

        void S(final c2 c2Var) {
            c2.a aVar;
            if (!Objects.equals(c2Var.c(), t())) {
                throw new AssertionError("Attempted to update event listener with event from incorrect recording [Recording: " + c2Var.c() + ", Expected: " + t() + "]");
            }
            String str = "Sending VideoRecordEvent " + c2Var.getClass().getSimpleName();
            if (c2Var instanceof c2.a) {
                if (((c2.a) c2Var).k()) {
                    str = str + String.format(" [error: %s]", c2.a.h(aVar.j()));
                }
            }
            y.f1.a("Recorder", str);
            R(c2Var);
            if (n() == null || q() == null) {
                return;
            }
            try {
                n().execute(new Runnable() { // from class: r0.v0
                    @Override // java.lang.Runnable
                    public final void run() {
                        q0.j.this.M(c2Var);
                    }
                });
            } catch (RejectedExecutionException e8) {
                y.f1.d("Recorder", "The callback executor is invalid.", e8);
            }
        }

        @Override // java.lang.AutoCloseable
        public void close() {
            f(Uri.EMPTY);
        }

        void f(Uri uri) {
            if (this.f11192f.get()) {
                g((g1.a) this.f11195i.getAndSet(null), uri);
            }
        }

        protected void finalize() {
            try {
                this.f11191e.d();
                g1.a aVar = (g1.a) this.f11195i.getAndSet(null);
                if (aVar != null) {
                    g(aVar, Uri.EMPTY);
                }
            } finally {
                super.finalize();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Executor n();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract g1.a q();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract s t();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract long v();

        t2 w() {
            return this.f11197k;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract boolean x();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class k {

        /* renamed from: a  reason: collision with root package name */
        private final g2 f11201a;

        /* renamed from: b  reason: collision with root package name */
        private final z2 f11202b;

        /* renamed from: c  reason: collision with root package name */
        private final int f11203c;

        /* renamed from: d  reason: collision with root package name */
        private boolean f11204d = false;

        /* renamed from: e  reason: collision with root package name */
        private int f11205e = 0;

        /* renamed from: f  reason: collision with root package name */
        private ScheduledFuture f11206f = null;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class a implements f0.c {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ a2 f11208a;

            a(a2 a2Var) {
                this.f11208a = a2Var;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public /* synthetic */ void d() {
                if (k.this.f11204d) {
                    return;
                }
                y.f1.a("Recorder", "Retry setupVideo #" + k.this.f11205e);
                k kVar = k.this;
                kVar.l(kVar.f11201a, k.this.f11202b);
            }

            @Override // f0.c
            public void b(Throwable th) {
                y.f1.m("Recorder", "VideoEncoder Setup error: " + th, th);
                if (k.this.f11205e >= k.this.f11203c) {
                    q0.this.k0(th);
                    return;
                }
                k.e(k.this);
                k.this.f11206f = q0.D0(new Runnable() { // from class: r0.y0
                    @Override // java.lang.Runnable
                    public final void run() {
                        q0.k.a.this.d();
                    }
                }, q0.this.f11137e, q0.f11128s0, TimeUnit.MILLISECONDS);
            }

            @Override // f0.c
            /* renamed from: e */
            public void a(y0.l lVar) {
                y.f1.a("Recorder", "VideoEncoder is created. " + lVar);
                if (lVar == null) {
                    return;
                }
                g1.e.l(q0.this.f11138e0 == this.f11208a);
                g1.e.l(q0.this.F == null);
                q0.this.q0(this.f11208a);
                q0.this.j0();
            }
        }

        k(g2 g2Var, z2 z2Var, int i8) {
            this.f11201a = g2Var;
            this.f11202b = z2Var;
            this.f11203c = i8;
        }

        static /* synthetic */ int e(k kVar) {
            int i8 = kVar.f11205e;
            kVar.f11205e = i8 + 1;
            return i8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void k(g2 g2Var, z2 z2Var) {
            if (!g2Var.s() && (!q0.this.f11138e0.n(g2Var) || q0.this.R())) {
                y0.o oVar = q0.this.f11139f;
                q0 q0Var = q0.this;
                a2 a2Var = new a2(oVar, q0Var.f11137e, q0Var.f11135d);
                q0 q0Var2 = q0.this;
                i4.d i8 = a2Var.i(g2Var, z2Var, (r) q0Var2.J(q0Var2.D), q0.this.f11158u);
                q0.this.f11138e0 = a2Var;
                f0.n.j(i8, new a(a2Var), q0.this.f11137e);
                return;
            }
            y.f1.l("Recorder", "Ignore the SurfaceRequest " + g2Var + " isServiced: " + g2Var.s() + " VideoEncoderSession: " + q0.this.f11138e0 + " has been configured with a persistent in-progress recording.");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void l(final g2 g2Var, final z2 z2Var) {
            q0.this.C0().d(new Runnable() { // from class: r0.x0
                @Override // java.lang.Runnable
                public final void run() {
                    q0.k.this.k(g2Var, z2Var);
                }
            }, q0.this.f11137e);
        }

        void j() {
            if (this.f11204d) {
                return;
            }
            this.f11204d = true;
            ScheduledFuture scheduledFuture = this.f11206f;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
                this.f11206f = null;
            }
        }

        void m() {
            l(this.f11201a, this.f11202b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum l {
        CONFIGURING,
        PENDING_RECORDING,
        PENDING_PAUSED,
        IDLING,
        RECORDING,
        PAUSED,
        STOPPING,
        RESETTING,
        ERROR
    }

    static {
        v vVar = v.f11269c;
        y g8 = y.g(Arrays.asList(vVar, v.f11268b, v.f11267a), p.a(vVar));
        f11121l0 = g8;
        d2 a8 = d2.a().e(g8).b(-1).a();
        f11122m0 = a8;
        f11123n0 = r.a().e(-1).f(a8).a();
        f11124o0 = new RuntimeException("The video frame producer became inactive before any data was received.");
        f11125p0 = new y0.o() { // from class: r0.h0
            @Override // y0.o
            public final y0.l a(Executor executor, y0.n nVar) {
                return new y0.h0(executor, nVar);
            }
        };
        f11126q0 = e0.c.g(e0.c.d());
        f11127r0 = 3;
        f11128s0 = 1000L;
    }

    q0(Executor executor, r rVar, int i8, y0.o oVar, y0.o oVar2) {
        this.f11145i = androidx.camera.video.internal.compat.quirk.a.b(EncoderNotUsePersistentInputSurfaceQuirk.class) != null;
        this.f11148k = l.CONFIGURING;
        this.f11149l = null;
        this.f11150m = 0;
        this.f11151n = null;
        this.f11152o = null;
        this.f11153p = 0L;
        this.f11154q = null;
        this.f11155r = false;
        this.f11156s = null;
        this.f11157t = null;
        this.f11158u = null;
        this.f11159v = new ArrayList();
        this.f11160w = null;
        this.f11161x = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.E = null;
        this.F = null;
        this.G = null;
        this.H = null;
        this.I = null;
        this.J = h.INITIALIZING;
        this.K = Uri.EMPTY;
        this.L = 0L;
        this.M = 0L;
        this.N = Long.MAX_VALUE;
        this.O = 0;
        this.P = null;
        this.Q = Long.MAX_VALUE;
        this.R = Long.MAX_VALUE;
        this.S = Long.MAX_VALUE;
        this.T = 0L;
        this.U = 0L;
        this.V = 1;
        this.W = null;
        this.X = null;
        this.Y = new k0.a(60);
        this.Z = null;
        this.f11130a0 = false;
        this.f11132b0 = b2.a.INACTIVE;
        this.f11134c0 = null;
        this.f11136d0 = false;
        this.f11140f0 = null;
        this.f11142g0 = 0.0d;
        this.f11144h0 = false;
        this.f11146i0 = null;
        this.f11133c = executor;
        executor = executor == null ? e0.c.d() : executor;
        this.f11135d = executor;
        Executor g8 = e0.c.g(executor);
        this.f11137e = g8;
        this.D = b0.b2.l(C(rVar));
        this.f11147j = i8;
        this.f11129a = b0.b2.l(c1.d(this.f11150m, O(this.f11148k)));
        this.f11131b = b0.b2.l(Boolean.FALSE);
        this.f11139f = oVar;
        this.f11141g = oVar2;
        this.f11138e0 = new a2(oVar, g8, executor);
    }

    private void B() {
        while (!this.Y.isEmpty()) {
            this.Y.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: B0 */
    public void Y(j jVar) {
        if (this.f11154q != jVar || this.f11155r) {
            return;
        }
        if (P()) {
            this.H.start();
        }
        y0.l lVar = this.F;
        if (lVar == null) {
            this.f11144h0 = true;
            return;
        }
        lVar.start();
        j jVar2 = this.f11154q;
        jVar2.S(c2.e(jVar2.t(), I()));
    }

    private r C(r rVar) {
        r.a i8 = rVar.i();
        if (rVar.d().b() == -1) {
            i8.b(new g1.a() { // from class: r0.i0
                @Override // g1.a
                public final void accept(Object obj) {
                    q0.T((d2.a) obj);
                }
            });
        }
        return i8.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public i4.d C0() {
        y.f1.a("Recorder", "Try to safely release video encoder: " + this.F);
        return this.f11138e0.w();
    }

    private void D(g2 g2Var, z2 z2Var, boolean z7) {
        if (g2Var.s()) {
            y.f1.l("Recorder", "Ignore the SurfaceRequest since it is already served.");
            return;
        }
        g2Var.D(this.f11137e, new g2.i() { // from class: r0.n0
            @Override // y.g2.i
            public final void a(g2.h hVar) {
                q0.this.U(hVar);
            }
        });
        Size o8 = g2Var.o();
        y.a0 m8 = g2Var.m();
        d1 L = L(g2Var.k().b());
        v b8 = L.b(o8, m8);
        y.f1.a("Recorder", "Using supported quality of " + b8 + " for surface size " + o8);
        if (b8 != v.f11273g) {
            t0.g c8 = L.c(b8, m8);
            this.f11158u = c8;
            if (c8 == null) {
                throw new AssertionError("Camera advertised available quality but did not produce EncoderProfiles  for advertised quality.");
            }
        }
        k kVar = this.f11146i0;
        if (kVar != null) {
            kVar.j();
        }
        k kVar2 = new k(g2Var, z2Var, z7 ? f11127r0 : 0);
        this.f11146i0 = kVar2;
        kVar2.m();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ScheduledFuture D0(final Runnable runnable, final Executor executor, long j8, TimeUnit timeUnit) {
        return e0.c.e().schedule(new Runnable() { // from class: r0.g0
            @Override // java.lang.Runnable
            public final void run() {
                executor.execute(runnable);
            }
        }, j8, timeUnit);
    }

    private void F(j jVar, int i8, Throwable th) {
        jVar.f(Uri.EMPTY);
        jVar.S(c2.b(jVar.t(), b1.d(0L, 0L, r0.b.d(1, this.Z, 0.0d)), t.b(Uri.EMPTY), i8, th));
    }

    private List H(long j8) {
        ArrayList arrayList = new ArrayList();
        while (!this.Y.isEmpty()) {
            y0.i iVar = (y0.i) this.Y.a();
            if (iVar.y() >= j8) {
                arrayList.add(iVar);
            }
        }
        return arrayList;
    }

    private void I0(int i8) {
        if (this.f11150m == i8) {
            return;
        }
        y.f1.a("Recorder", "Transitioning streamId: " + this.f11150m + " --> " + i8);
        this.f11150m = i8;
        this.f11129a.k(c1.e(i8, O(this.f11148k), this.f11156s));
    }

    private void K0(j jVar) {
        r rVar = (r) J(this.D);
        x0.e d8 = x0.b.d(rVar, this.f11158u);
        z2 z2Var = z2.UPTIME;
        u0.a e8 = x0.b.e(d8, rVar.b());
        if (this.E != null) {
            v0();
        }
        u0.n L0 = L0(jVar, e8);
        this.E = L0;
        y.f1.a("Recorder", String.format("Set up new audio source: 0x%x", Integer.valueOf(L0.hashCode())));
        y0.l a8 = this.f11141g.a(this.f11135d, x0.b.c(d8, z2Var, e8, rVar.b()));
        this.H = a8;
        l.b c8 = a8.c();
        if (!(c8 instanceof l.a)) {
            throw new AssertionError("The EncoderInput of audio isn't a ByteBufferInput.");
        }
        this.E.M((l.a) c8);
    }

    public static d1 L(y.p pVar) {
        return M(pVar, 0);
    }

    private u0.n L0(j jVar, u0.a aVar) {
        return jVar.P(aVar, f11126q0);
    }

    public static d1 M(y.p pVar, int i8) {
        return new z0(i8, (b0.g0) pVar, y0.s1.f13176d);
    }

    private int N(h hVar) {
        int ordinal = hVar.ordinal();
        if (ordinal == 0 || ordinal == 2) {
            return 1;
        }
        if (ordinal == 3) {
            j jVar = this.f11154q;
            if (jVar == null || !jVar.C()) {
                return this.f11130a0 ? 2 : 0;
            }
            return 5;
        } else if (ordinal != 4) {
            if (ordinal == 5) {
                return 4;
            }
            throw new AssertionError("Invalid internal audio state: " + hVar);
        } else {
            return 3;
        }
    }

    private void N0(j jVar) {
        if (this.f11154q != null) {
            throw new AssertionError("Attempted to start a new recording while another was in progress.");
        }
        if (jVar.t().b() > 0) {
            this.T = Math.round(jVar.t().b() * 0.95d);
            y.f1.a("Recorder", "File size limit in bytes: " + this.T);
        } else {
            this.T = 0L;
        }
        if (jVar.t().a() > 0) {
            this.U = TimeUnit.MILLISECONDS.toNanos(jVar.t().a());
            y.f1.a("Recorder", "Duration limit in nanoseconds: " + this.U);
        } else {
            this.U = 0L;
        }
        this.f11154q = jVar;
        int ordinal = this.J.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                E0(jVar.x() ? h.ENABLED : h.DISABLED);
            } else if (ordinal == 2 || ordinal == 3 || ordinal == 4 || ordinal == 5) {
                throw new AssertionError("Incorrectly invoke startInternal in audio state " + this.J);
            }
        } else if (jVar.x()) {
            if (!Q()) {
                throw new AssertionError("The Recorder doesn't support recording with audio");
            }
            try {
                if (!this.f11154q.D() || this.H == null) {
                    K0(jVar);
                }
                E0(h.ENABLED);
            } catch (u0.o | y0.j1 e8) {
                y.f1.d("Recorder", "Unable to create audio resource with error: ", e8);
                E0(e8 instanceof y0.j1 ? h.ERROR_ENCODER : h.ERROR_SOURCE);
                this.Z = e8;
            }
        }
        U0(jVar, false);
        if (P()) {
            this.E.O(jVar.C());
            this.H.start();
        }
        this.F.start();
        j jVar2 = this.f11154q;
        jVar2.S(c2.f(jVar2.t(), I()));
    }

    private c1.a O(l lVar) {
        return (lVar == l.RECORDING || (lVar == l.STOPPING && ((DeactivateEncoderSurfaceBeforeStopEncoderQuirk) androidx.camera.video.internal.compat.quirk.a.b(DeactivateEncoderSurfaceBeforeStopEncoderQuirk.class)) == null)) ? c1.a.ACTIVE : c1.a.INACTIVE;
    }

    private void O0(j jVar, boolean z7) {
        N0(jVar);
        if (z7) {
            X(jVar);
        }
    }

    private static int R0(t0.g gVar, int i8) {
        if (gVar != null) {
            int a8 = gVar.a();
            if (a8 == 1) {
                return 2;
            }
            if (a8 == 2) {
                return 0;
            }
            if (a8 == 9) {
                return 1;
            }
        }
        return i8;
    }

    private static boolean S(a1 a1Var, j jVar) {
        return jVar != null && a1Var.f() == jVar.v();
    }

    private void S0() {
        a2 a2Var = this.f11140f0;
        if (a2Var == null) {
            C0();
            return;
        }
        g1.e.l(a2Var.m() == this.F);
        y.f1.a("Recorder", "Releasing video encoder: " + this.F);
        this.f11140f0.x();
        this.f11140f0 = null;
        this.F = null;
        this.G = null;
        G0(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void T(d2.a aVar) {
        aVar.b(f11122m0.b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void U(g2.h hVar) {
        this.f11157t = hVar;
    }

    private void U0(final j jVar, boolean z7) {
        if (!this.f11159v.isEmpty()) {
            i4.d k8 = f0.n.k(this.f11159v);
            if (!k8.isDone()) {
                k8.cancel(true);
            }
            this.f11159v.clear();
        }
        this.f11159v.add(androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: r0.p0
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object e02;
                e02 = q0.this.e0(jVar, aVar);
                return e02;
            }
        }));
        if (P() && !z7) {
            this.f11159v.add(androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: r0.a0
                @Override // androidx.concurrent.futures.c.InterfaceC0018c
                public final Object a(c.a aVar) {
                    Object g02;
                    g02 = q0.this.g0(jVar, aVar);
                    return g02;
                }
            }));
        }
        f0.n.j(f0.n.k(this.f11159v), new f(), e0.c.b());
    }

    private void W0(l lVar) {
        if (!f11119j0.contains(this.f11148k)) {
            throw new AssertionError("Can only updated non-pending state from a pending state, but state is " + this.f11148k);
        } else if (!f11120k0.contains(lVar)) {
            throw new AssertionError("Invalid state transition. State is not a valid non-pending state while in a pending state: " + lVar);
        } else if (this.f11149l != lVar) {
            this.f11149l = lVar;
            this.f11129a.k(c1.e(this.f11150m, O(lVar), this.f11156s));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a0(Uri uri) {
        this.K = uri;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b0() {
        g2 g2Var = this.f11162y;
        if (g2Var == null) {
            throw new AssertionError("surface request is required to retry initialization.");
        }
        D(g2Var, this.f11163z, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void d0(y0.l lVar) {
        y.f1.a("Recorder", "The source didn't become non-streaming before timeout. Waited 1000ms");
        if (androidx.camera.video.internal.compat.quirk.a.b(DeactivateEncoderSurfaceBeforeStopEncoderQuirk.class) != null) {
            i0(lVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object e0(j jVar, c.a aVar) {
        this.F.e(new c(aVar, jVar), this.f11137e);
        return "videoEncodingFuture";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f0(c.a aVar, Throwable th) {
        if (this.Z == null) {
            E0(th instanceof y0.h ? h.ERROR_ENCODER : h.ERROR_SOURCE);
            this.Z = th;
            V0();
            aVar.c(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object g0(j jVar, final c.a aVar) {
        g1.a aVar2 = new g1.a() { // from class: r0.b0
            @Override // g1.a
            public final void accept(Object obj) {
                q0.this.f0(aVar, (Throwable) obj);
            }
        };
        this.E.L(this.f11137e, new d(aVar2));
        this.H.e(new e(aVar, aVar2, jVar), this.f11137e);
        return "audioEncodingFuture";
    }

    private j h0(l lVar) {
        boolean z7;
        if (lVar == l.PENDING_PAUSED) {
            z7 = true;
        } else if (lVar != l.PENDING_RECORDING) {
            throw new AssertionError("makePendingRecordingActiveLocked() can only be called from a pending state.");
        } else {
            z7 = false;
        }
        if (this.f11151n == null) {
            j jVar = this.f11152o;
            if (jVar != null) {
                this.f11151n = jVar;
                jVar.w().d(e0.c.b(), new g());
                this.f11152o = null;
                H0(z7 ? l.PAUSED : l.RECORDING);
                return jVar;
            }
            throw new AssertionError("Pending recording should exist when in a PENDING state.");
        }
        throw new AssertionError("Cannot make pending recording active because another recording is already active.");
    }

    static void i0(y0.l lVar) {
        if (lVar instanceof y0.h0) {
            ((y0.h0) lVar).n0();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x006c A[Catch: all -> 0x00e3, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0007, B:7:0x0019, B:44:0x00b2, B:11:0x0026, B:13:0x002a, B:15:0x0030, B:19:0x0038, B:21:0x0043, B:22:0x004a, B:23:0x0062, B:26:0x0066, B:28:0x006c, B:29:0x007c, B:31:0x0080, B:33:0x0086, B:37:0x008e, B:38:0x0097, B:40:0x009b, B:58:0x00db, B:59:0x00e2), top: B:63:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x007c A[Catch: all -> 0x00e3, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0007, B:7:0x0019, B:44:0x00b2, B:11:0x0026, B:13:0x002a, B:15:0x0030, B:19:0x0038, B:21:0x0043, B:22:0x004a, B:23:0x0062, B:26:0x0066, B:28:0x006c, B:29:0x007c, B:31:0x0080, B:33:0x0086, B:37:0x008e, B:38:0x0097, B:40:0x009b, B:58:0x00db, B:59:0x00e2), top: B:63:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void m0(r0.q0.j r9) {
        /*
            Method dump skipped, instructions count: 248
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: r0.q0.m0(r0.q0$j):void");
    }

    private void n0() {
        boolean z7;
        g2 g2Var;
        synchronized (this.f11143h) {
            switch (this.f11148k.ordinal()) {
                case 1:
                case 2:
                    W0(l.CONFIGURING);
                    z7 = true;
                    break;
                case 4:
                case 5:
                case 8:
                    if (R()) {
                        z7 = false;
                        break;
                    }
                case 3:
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                case 7:
                    H0(l.CONFIGURING);
                    z7 = true;
                    break;
                default:
                    z7 = true;
                    break;
            }
        }
        this.f11136d0 = false;
        if (!z7 || (g2Var = this.f11162y) == null || g2Var.s()) {
            return;
        }
        D(this.f11162y, this.f11163z, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p0 */
    public void W(g2 g2Var, z2 z2Var) {
        g2 g2Var2 = this.f11162y;
        if (g2Var2 != null && !g2Var2.s()) {
            this.f11162y.F();
        }
        this.f11162y = g2Var;
        this.f11163z = z2Var;
        D(g2Var, z2Var, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s0 */
    public void X(j jVar) {
        if (this.f11154q != jVar || this.f11155r) {
            return;
        }
        if (P()) {
            this.H.a();
        }
        this.F.a();
        j jVar2 = this.f11154q;
        jVar2.S(c2.d(jVar2.t(), I()));
    }

    private u u0(Context context, s sVar) {
        g1.e.k(sVar, "The OutputOptions cannot be null.");
        return new u(context, this, sVar);
    }

    private void v0() {
        u0.n nVar = this.E;
        if (nVar == null) {
            throw new AssertionError("Cannot release null audio source.");
        }
        this.E = null;
        y.f1.a("Recorder", String.format("Releasing audio source: 0x%x", Integer.valueOf(nVar.hashCode())));
        f0.n.j(nVar.H(), new b(nVar), e0.c.b());
    }

    private void x0() {
        if (this.H != null) {
            y.f1.a("Recorder", "Releasing audio encoder.");
            this.H.release();
            this.H = null;
            this.I = null;
        }
        if (this.E != null) {
            v0();
        }
        E0(h.INITIALIZING);
        y0();
    }

    private void y0() {
        if (this.F != null) {
            y.f1.a("Recorder", "Releasing video encoder.");
            S0();
        }
        n0();
    }

    private void z0() {
        if (f11119j0.contains(this.f11148k)) {
            H0(this.f11149l);
            return;
        }
        throw new AssertionError("Cannot restore non-pending state when in state " + this.f11148k);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void A0(a1 a1Var) {
        synchronized (this.f11143h) {
            if (!S(a1Var, this.f11152o) && !S(a1Var, this.f11151n)) {
                y.f1.a("Recorder", "resume() called on a recording that is no longer active: " + a1Var.d());
                return;
            }
            int ordinal = this.f11148k.ordinal();
            if (ordinal != 0) {
                if (ordinal == 5) {
                    H0(l.RECORDING);
                    final j jVar = this.f11151n;
                    this.f11137e.execute(new Runnable() { // from class: r0.l0
                        @Override // java.lang.Runnable
                        public final void run() {
                            q0.this.Y(jVar);
                        }
                    });
                } else if (ordinal == 2) {
                    H0(l.PENDING_RECORDING);
                } else if (ordinal != 3) {
                }
                return;
            }
            throw new IllegalStateException("Called resume() from invalid state: " + this.f11148k);
        }
    }

    void E(int i8, Throwable th) {
        if (this.f11154q == null) {
            throw new AssertionError("Attempted to finalize in-progress recording, but no recording is in progress.");
        }
        MediaMuxer mediaMuxer = this.C;
        if (mediaMuxer != null) {
            try {
                mediaMuxer.stop();
                this.C.release();
            } catch (IllegalStateException e8) {
                y.f1.c("Recorder", "MediaMuxer failed to stop or release with error: " + e8.getMessage());
                if (i8 == 0) {
                    i8 = 1;
                }
            }
            this.C = null;
        } else if (i8 == 0) {
            i8 = 8;
        }
        this.f11154q.f(this.K);
        s t8 = this.f11154q.t();
        b1 I = I();
        t b8 = t.b(this.K);
        this.f11154q.S(i8 == 0 ? c2.a(t8, I, b8) : c2.b(t8, I, b8, i8, th));
        j jVar = this.f11154q;
        this.f11154q = null;
        this.f11155r = false;
        this.f11160w = null;
        this.f11161x = null;
        this.f11159v.clear();
        this.K = Uri.EMPTY;
        this.L = 0L;
        this.M = 0L;
        this.N = Long.MAX_VALUE;
        this.Q = Long.MAX_VALUE;
        this.R = Long.MAX_VALUE;
        this.S = Long.MAX_VALUE;
        this.V = 1;
        this.W = null;
        this.Z = null;
        this.f11142g0 = 0.0d;
        B();
        F0(null);
        int ordinal = this.J.ordinal();
        if (ordinal == 1) {
            throw new AssertionError("Incorrectly finalize recording when audio state is IDLING");
        }
        if (ordinal == 2 || ordinal == 3) {
            E0(h.IDLING);
            this.E.Q();
        } else if (ordinal == 4 || ordinal == 5) {
            E0(h.INITIALIZING);
        }
        m0(jVar);
    }

    void E0(h hVar) {
        y.f1.a("Recorder", "Transitioning audio state: " + this.J + " --> " + hVar);
        this.J = hVar;
    }

    void F0(g2.h hVar) {
        y.f1.a("Recorder", "Update stream transformation info: " + hVar);
        this.f11156s = hVar;
        synchronized (this.f11143h) {
            this.f11129a.k(c1.e(this.f11150m, O(this.f11148k), hVar));
        }
    }

    public int G() {
        return ((r) J(this.D)).d().b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void G0(Surface surface) {
        int hashCode;
        if (this.A == surface) {
            return;
        }
        this.A = surface;
        synchronized (this.f11143h) {
            if (surface != null) {
                try {
                    hashCode = surface.hashCode();
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                hashCode = 0;
            }
            I0(hashCode);
        }
    }

    void H0(l lVar) {
        if (this.f11148k == lVar) {
            throw new AssertionError("Attempted to transition to state " + lVar + ", but Recorder is already in state " + lVar);
        }
        y.f1.a("Recorder", "Transitioning Recorder internal state: " + this.f11148k + " --> " + lVar);
        Set set = f11119j0;
        c1.a aVar = null;
        if (set.contains(lVar)) {
            if (!set.contains(this.f11148k)) {
                if (!f11120k0.contains(this.f11148k)) {
                    throw new AssertionError("Invalid state transition. Should not be transitioning to a PENDING state from state " + this.f11148k);
                }
                l lVar2 = this.f11148k;
                this.f11149l = lVar2;
                aVar = O(lVar2);
            }
        } else if (this.f11149l != null) {
            this.f11149l = null;
        }
        this.f11148k = lVar;
        if (aVar == null) {
            aVar = O(lVar);
        }
        this.f11129a.k(c1.e(this.f11150m, aVar, this.f11156s));
    }

    b1 I() {
        return b1.d(this.M, this.L, r0.b.d(N(this.J), this.Z, this.f11142g0));
    }

    Object J(t2 t2Var) {
        try {
            return t2Var.a().get();
        } catch (InterruptedException | ExecutionException e8) {
            throw new IllegalStateException(e8);
        }
    }

    void J0(j jVar) {
        if (this.C != null) {
            throw new AssertionError("Unable to set up media muxer when one already exists.");
        }
        if (P() && this.Y.isEmpty()) {
            throw new AssertionError("Audio is enabled but no audio sample is ready. Cannot start media muxer.");
        }
        y0.i iVar = this.X;
        if (iVar == null) {
            throw new AssertionError("Media muxer cannot be started without an encoded video frame.");
        }
        try {
            this.X = null;
            List<y0.i> H = H(iVar.y());
            long size = iVar.size();
            for (y0.i iVar2 : H) {
                size += iVar2.size();
            }
            long j8 = this.T;
            if (j8 != 0 && size > j8) {
                y.f1.a("Recorder", String.format("Initial data exceeds file size limit %d > %d", Long.valueOf(size), Long.valueOf(this.T)));
                l0(jVar, 2, null);
                iVar.close();
                return;
            }
            try {
                r rVar = (r) J(this.D);
                MediaMuxer Q = jVar.Q(rVar.c() == -1 ? R0(this.f11158u, r.g(f11123n0.c())) : r.g(rVar.c()), new g1.a() { // from class: r0.z
                    @Override // g1.a
                    public final void accept(Object obj) {
                        q0.this.a0((Uri) obj);
                    }
                });
                g2.h hVar = this.f11157t;
                if (hVar != null) {
                    F0(hVar);
                    Q.setOrientationHint(hVar.b());
                }
                Location c8 = jVar.t().c();
                if (c8 != null) {
                    try {
                        Pair a8 = a1.a.a(c8.getLatitude(), c8.getLongitude());
                        Q.setLocation((float) ((Double) a8.first).doubleValue(), (float) ((Double) a8.second).doubleValue());
                    } catch (IllegalArgumentException e8) {
                        Q.release();
                        l0(jVar, 5, e8);
                        iVar.close();
                        return;
                    }
                }
                this.f11161x = Integer.valueOf(Q.addTrack(this.G.a()));
                if (P()) {
                    this.f11160w = Integer.valueOf(Q.addTrack(this.I.a()));
                }
                Q.start();
                this.C = Q;
                Y0(iVar, jVar);
                for (y0.i iVar3 : H) {
                    X0(iVar3, jVar);
                }
                iVar.close();
            } catch (IOException e9) {
                l0(jVar, 5, e9);
                iVar.close();
            }
        } catch (Throwable th) {
            if (iVar != null) {
                try {
                    iVar.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public int K() {
        return ((Integer) ((r) J(this.D)).d().c().getLower()).intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a1 M0(u uVar) {
        long j8;
        j jVar;
        int i8;
        Executor executor;
        Runnable runnable;
        j jVar2;
        g1.e.k(uVar, "The given PendingRecording cannot be null.");
        synchronized (this.f11143h) {
            j8 = this.f11153p + 1;
            this.f11153p = j8;
            jVar = null;
            i8 = 0;
            switch (this.f11148k.ordinal()) {
                case 0:
                case 3:
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                case 7:
                case 8:
                    l lVar = this.f11148k;
                    l lVar2 = l.IDLING;
                    if (lVar == lVar2) {
                        g1.e.m(this.f11151n == null && this.f11152o == null, "Expected recorder to be idle but a recording is either pending or in progress.");
                    }
                    try {
                        j j9 = j.j(uVar, j8);
                        j9.A(uVar.a());
                        this.f11152o = j9;
                        l lVar3 = this.f11148k;
                        if (lVar3 != lVar2) {
                            if (lVar3 != l.ERROR) {
                                H0(l.PENDING_RECORDING);
                                e = null;
                                break;
                            } else {
                                H0(l.PENDING_RECORDING);
                                executor = this.f11137e;
                                runnable = new Runnable() { // from class: r0.k0
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        q0.this.b0();
                                    }
                                };
                            }
                        } else {
                            H0(l.PENDING_RECORDING);
                            executor = this.f11137e;
                            runnable = new Runnable() { // from class: r0.j0
                                @Override // java.lang.Runnable
                                public final void run() {
                                    q0.this.T0();
                                }
                            };
                        }
                        executor.execute(runnable);
                        e = null;
                    } catch (IOException e8) {
                        e = e8;
                        i8 = 5;
                        break;
                    }
                    break;
                case 1:
                case 2:
                    jVar2 = (j) g1.e.j(this.f11152o);
                    jVar = jVar2;
                    e = null;
                    break;
                case 4:
                case 5:
                    jVar2 = this.f11151n;
                    jVar = jVar2;
                    e = null;
                    break;
                default:
                    e = null;
                    break;
            }
        }
        if (jVar == null) {
            if (i8 != 0) {
                y.f1.c("Recorder", "Recording was started when the Recorder had encountered error " + e);
                F(j.j(uVar, j8), i8, e);
                return a1.b(uVar, j8);
            }
            return a1.c(uVar, j8);
        }
        throw new IllegalStateException("A recording is already in progress. Previous recordings must be stopped before a new recording can be started.");
    }

    boolean P() {
        return this.J == h.ENABLED;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void P0(a1 a1Var, final int i8, final Throwable th) {
        synchronized (this.f11143h) {
            if (!S(a1Var, this.f11152o) && !S(a1Var, this.f11151n)) {
                y.f1.a("Recorder", "stop() called on a recording that is no longer active: " + a1Var.d());
                return;
            }
            j jVar = null;
            switch (this.f11148k.ordinal()) {
                case 0:
                case 3:
                    throw new IllegalStateException("Calling stop() while idling or initializing is invalid.");
                case 1:
                case 2:
                    g1.e.l(S(a1Var, this.f11152o));
                    j jVar2 = this.f11152o;
                    this.f11152o = null;
                    z0();
                    jVar = jVar2;
                    break;
                case 4:
                case 5:
                    H0(l.STOPPING);
                    final long micros = TimeUnit.NANOSECONDS.toMicros(System.nanoTime());
                    final j jVar3 = this.f11151n;
                    this.f11137e.execute(new Runnable() { // from class: r0.o0
                        @Override // java.lang.Runnable
                        public final void run() {
                            q0.this.c0(jVar3, micros, i8, th);
                        }
                    });
                    break;
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                case 7:
                    g1.e.l(S(a1Var, this.f11151n));
                    break;
            }
            if (jVar != null) {
                if (i8 == 10) {
                    y.f1.c("Recorder", "Recording was stopped due to recording being garbage collected before any valid data has been produced.");
                }
                F(jVar, 8, new RuntimeException("Recording was stopped before any data could be produced.", th));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean Q() {
        return ((r) J(this.D)).b().c() != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: Q0 */
    public void c0(j jVar, long j8, int i8, Throwable th) {
        if (this.f11154q != jVar || this.f11155r) {
            return;
        }
        this.f11155r = true;
        this.V = i8;
        this.W = th;
        if (P()) {
            B();
            this.H.b(j8);
        }
        y0.i iVar = this.X;
        if (iVar != null) {
            iVar.close();
            this.X = null;
        }
        if (this.f11132b0 != b2.a.ACTIVE_NON_STREAMING) {
            final y0.l lVar = this.F;
            this.f11134c0 = D0(new Runnable() { // from class: r0.d0
                @Override // java.lang.Runnable
                public final void run() {
                    q0.d0(y0.l.this);
                }
            }, this.f11137e, 1000L, TimeUnit.MILLISECONDS);
        } else {
            i0(this.F);
        }
        this.F.b(j8);
    }

    boolean R() {
        j jVar = this.f11154q;
        return jVar != null && jVar.D();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void T0() {
        boolean z7;
        j jVar;
        int i8;
        j jVar2;
        Exception exc;
        synchronized (this.f11143h) {
            int ordinal = this.f11148k.ordinal();
            boolean z8 = true;
            z7 = false;
            jVar = null;
            if (ordinal == 1) {
                z8 = false;
            } else if (ordinal != 2) {
                i8 = 0;
                jVar2 = null;
                exc = jVar2;
            }
            if (this.f11151n == null && !this.f11136d0) {
                if (this.f11132b0 == b2.a.INACTIVE) {
                    jVar2 = this.f11152o;
                    this.f11152o = null;
                    z0();
                    i8 = 4;
                    z7 = z8;
                    exc = f11124o0;
                } else if (this.F != null) {
                    i8 = 0;
                    z7 = z8;
                    exc = null;
                    jVar = h0(this.f11148k);
                    jVar2 = null;
                }
            }
            i8 = 0;
            jVar2 = null;
            z7 = z8;
            exc = jVar2;
        }
        if (jVar != null) {
            O0(jVar, z7);
        } else if (jVar2 != null) {
            F(jVar2, i8, exc);
        }
    }

    void V0() {
        j jVar = this.f11154q;
        if (jVar != null) {
            jVar.S(c2.g(jVar.t(), I()));
        }
    }

    void X0(y0.i iVar, j jVar) {
        long size = this.L + iVar.size();
        long j8 = this.T;
        if (j8 != 0 && size > j8) {
            y.f1.a("Recorder", String.format("Reach file size limit %d > %d", Long.valueOf(size), Long.valueOf(this.T)));
            l0(jVar, 2, null);
            return;
        }
        long y7 = iVar.y();
        long j9 = this.Q;
        if (j9 == Long.MAX_VALUE) {
            this.Q = y7;
            y.f1.a("Recorder", String.format("First audio time: %d (%s)", Long.valueOf(y7), t0.d.c(this.Q)));
        } else {
            TimeUnit timeUnit = TimeUnit.MICROSECONDS;
            long nanos = timeUnit.toNanos(y7 - Math.min(this.N, j9));
            g1.e.m(this.S != Long.MAX_VALUE, "There should be a previous data for adjusting the duration.");
            long nanos2 = nanos + timeUnit.toNanos(y7 - this.S);
            long j10 = this.U;
            if (j10 != 0 && nanos2 > j10) {
                y.f1.a("Recorder", String.format("Audio data reaches duration limit %d > %d", Long.valueOf(nanos2), Long.valueOf(this.U)));
                l0(jVar, 9, null);
                return;
            }
        }
        this.C.writeSampleData(this.f11160w.intValue(), iVar.a(), iVar.F());
        this.L = size;
        this.S = y7;
    }

    void Y0(y0.i iVar, j jVar) {
        if (this.f11161x == null) {
            throw new AssertionError("Video data comes before the track is added to MediaMuxer.");
        }
        long size = this.L + iVar.size();
        long j8 = this.T;
        long j9 = 0;
        if (j8 != 0 && size > j8) {
            y.f1.a("Recorder", String.format("Reach file size limit %d > %d", Long.valueOf(size), Long.valueOf(this.T)));
            l0(jVar, 2, null);
            return;
        }
        long y7 = iVar.y();
        long j10 = this.N;
        if (j10 == Long.MAX_VALUE) {
            this.N = y7;
            y.f1.a("Recorder", String.format("First video time: %d (%s)", Long.valueOf(y7), t0.d.c(this.N)));
        } else {
            TimeUnit timeUnit = TimeUnit.MICROSECONDS;
            long nanos = timeUnit.toNanos(y7 - Math.min(j10, this.Q));
            g1.e.m(this.R != Long.MAX_VALUE, "There should be a previous data for adjusting the duration.");
            long nanos2 = timeUnit.toNanos(y7 - this.R) + nanos;
            long j11 = this.U;
            if (j11 != 0 && nanos2 > j11) {
                y.f1.a("Recorder", String.format("Video data reaches duration limit %d > %d", Long.valueOf(nanos2), Long.valueOf(this.U)));
                l0(jVar, 9, null);
                return;
            }
            j9 = nanos;
        }
        this.C.writeSampleData(this.f11161x.intValue(), iVar.a(), iVar.F());
        this.L = size;
        this.M = j9;
        this.R = y7;
        V0();
    }

    @Override // r0.b2
    public void a(g2 g2Var) {
        b(g2Var, z2.UPTIME);
    }

    @Override // r0.b2
    public void b(final g2 g2Var, final z2 z2Var) {
        synchronized (this.f11143h) {
            y.f1.a("Recorder", "Surface is requested in state: " + this.f11148k + ", Current surface: " + this.f11150m);
            if (this.f11148k == l.ERROR) {
                H0(l.CONFIGURING);
            }
        }
        this.f11137e.execute(new Runnable() { // from class: r0.e0
            @Override // java.lang.Runnable
            public final void run() {
                q0.this.W(g2Var, z2Var);
            }
        });
    }

    @Override // r0.b2
    public b0.d2 c() {
        return this.f11129a;
    }

    @Override // r0.b2
    public b0.d2 d() {
        return this.f11131b;
    }

    @Override // r0.b2
    public d1 e(y.p pVar) {
        return M(pVar, this.f11147j);
    }

    @Override // r0.b2
    public void f(final b2.a aVar) {
        this.f11137e.execute(new Runnable() { // from class: r0.c0
            @Override // java.lang.Runnable
            public final void run() {
                q0.this.V(aVar);
            }
        });
    }

    @Override // r0.b2
    public b0.d2 g() {
        return this.D;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005d A[Catch: all -> 0x00bd, TryCatch #0 {, blocks: (B:4:0x0003, B:5:0x000c, B:30:0x0085, B:7:0x0011, B:8:0x001a, B:11:0x0020, B:12:0x0027, B:15:0x002b, B:16:0x0039, B:17:0x0051, B:20:0x0055, B:23:0x005d, B:25:0x0063, B:26:0x006f, B:27:0x007b), top: B:45:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void j0() {
        /*
            Method dump skipped, instructions count: 214
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: r0.q0.j0():void");
    }

    void k0(Throwable th) {
        j jVar;
        synchronized (this.f11143h) {
            jVar = null;
            switch (this.f11148k.ordinal()) {
                case 1:
                case 2:
                    j jVar2 = this.f11152o;
                    this.f11152o = null;
                    jVar = jVar2;
                case 0:
                    I0(-1);
                    H0(l.ERROR);
                    break;
                case 3:
                case 4:
                case 5:
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                case 7:
                    throw new AssertionError("Encountered encoder setup error while in unexpected state " + this.f11148k + ": " + th);
            }
        }
        if (jVar != null) {
            F(jVar, 7, th);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x001d  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x001e A[Catch: all -> 0x004c, TryCatch #0 {, blocks: (B:6:0x0007, B:7:0x000e, B:17:0x003f, B:9:0x0012, B:10:0x0019, B:13:0x001e, B:14:0x0025, B:15:0x0026, B:16:0x003e), top: B:26:0x0007 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void l0(r0.q0.j r10, int r11, java.lang.Throwable r12) {
        /*
            r9 = this;
            r0.q0$j r0 = r9.f11154q
            if (r10 != r0) goto L4f
            java.lang.Object r0 = r9.f11143h
            monitor-enter(r0)
            r0.q0$l r1 = r9.f11148k     // Catch: java.lang.Throwable -> L4c
            int r1 = r1.ordinal()     // Catch: java.lang.Throwable -> L4c
            r2 = 0
            switch(r1) {
                case 0: goto L26;
                case 1: goto L19;
                case 2: goto L19;
                case 3: goto L26;
                case 4: goto L12;
                case 5: goto L12;
                case 6: goto L19;
                case 7: goto L19;
                case 8: goto L26;
                default: goto L11;
            }     // Catch: java.lang.Throwable -> L4c
        L11:
            goto L3f
        L12:
            r0.q0$l r1 = r0.q0.l.STOPPING     // Catch: java.lang.Throwable -> L4c
            r9.H0(r1)     // Catch: java.lang.Throwable -> L4c
            r1 = 1
            r2 = r1
        L19:
            r0.q0$j r1 = r9.f11151n     // Catch: java.lang.Throwable -> L4c
            if (r10 != r1) goto L1e
            goto L3f
        L1e:
            java.lang.AssertionError r10 = new java.lang.AssertionError     // Catch: java.lang.Throwable -> L4c
            java.lang.String r11 = "Internal error occurred for recording but it is not the active recording."
            r10.<init>(r11)     // Catch: java.lang.Throwable -> L4c
            throw r10     // Catch: java.lang.Throwable -> L4c
        L26:
            java.lang.AssertionError r10 = new java.lang.AssertionError     // Catch: java.lang.Throwable -> L4c
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4c
            r11.<init>()     // Catch: java.lang.Throwable -> L4c
            java.lang.String r12 = "In-progress recording error occurred while in unexpected state: "
            r11.append(r12)     // Catch: java.lang.Throwable -> L4c
            r0.q0$l r12 = r9.f11148k     // Catch: java.lang.Throwable -> L4c
            r11.append(r12)     // Catch: java.lang.Throwable -> L4c
            java.lang.String r11 = r11.toString()     // Catch: java.lang.Throwable -> L4c
            r10.<init>(r11)     // Catch: java.lang.Throwable -> L4c
            throw r10     // Catch: java.lang.Throwable -> L4c
        L3f:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L4c
            if (r2 == 0) goto L4b
            r5 = -1
            r3 = r9
            r4 = r10
            r7 = r11
            r8 = r12
            r3.c0(r4, r5, r7, r8)
        L4b:
            return
        L4c:
            r10 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L4c
            throw r10
        L4f:
            java.lang.AssertionError r10 = new java.lang.AssertionError
            java.lang.String r11 = "Internal error occurred on recording that is not the current in-progress recording."
            r10.<init>(r11)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: r0.q0.l0(r0.q0$j, int, java.lang.Throwable):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: o0 */
    public void V(b2.a aVar) {
        ScheduledFuture scheduledFuture;
        y0.l lVar;
        b2.a aVar2 = this.f11132b0;
        this.f11132b0 = aVar;
        if (aVar2 == aVar) {
            y.f1.a("Recorder", "Video source transitions to the same state: " + aVar);
            return;
        }
        y.f1.a("Recorder", "Video source has transitioned to state: " + aVar);
        if (aVar != b2.a.INACTIVE) {
            if (aVar != b2.a.ACTIVE_NON_STREAMING || (scheduledFuture = this.f11134c0) == null || !scheduledFuture.cancel(false) || (lVar = this.F) == null) {
                return;
            }
            i0(lVar);
        } else if (this.B == null) {
            k kVar = this.f11146i0;
            if (kVar != null) {
                kVar.j();
                this.f11146i0 = null;
            }
            w0(4, null, false);
        } else {
            this.f11136d0 = true;
            j jVar = this.f11154q;
            if (jVar == null || jVar.D()) {
                return;
            }
            l0(this.f11154q, 4, null);
        }
    }

    void q0(a2 a2Var) {
        y0.l m8 = a2Var.m();
        this.F = m8;
        this.P = ((y0.q1) m8.h()).c();
        this.O = this.F.g();
        Surface k8 = a2Var.k();
        this.B = k8;
        G0(k8);
        a2Var.v(this.f11137e, new l.c.a() { // from class: r0.f0
            @Override // y0.l.c.a
            public final void a(Surface surface) {
                q0.this.G0(surface);
            }
        });
        f0.n.j(a2Var.l(), new a(a2Var), this.f11137e);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void r0(a1 a1Var) {
        synchronized (this.f11143h) {
            if (!S(a1Var, this.f11152o) && !S(a1Var, this.f11151n)) {
                y.f1.a("Recorder", "pause() called on a recording that is no longer active: " + a1Var.d());
                return;
            }
            int ordinal = this.f11148k.ordinal();
            if (ordinal != 0) {
                if (ordinal == 1) {
                    H0(l.PENDING_PAUSED);
                } else if (ordinal != 3) {
                    if (ordinal == 4) {
                        H0(l.PAUSED);
                        final j jVar = this.f11151n;
                        this.f11137e.execute(new Runnable() { // from class: r0.m0
                            @Override // java.lang.Runnable
                            public final void run() {
                                q0.this.X(jVar);
                            }
                        });
                    }
                }
                return;
            }
            throw new IllegalStateException("Called pause() from invalid state: " + this.f11148k);
        }
    }

    public u t0(Context context, q qVar) {
        return u0(context, qVar);
    }

    void w0(int i8, Throwable th, boolean z7) {
        boolean z8;
        boolean z9;
        synchronized (this.f11143h) {
            z8 = true;
            z9 = false;
            switch (this.f11148k.ordinal()) {
                case 0:
                case 3:
                case 8:
                    break;
                case 1:
                case 2:
                    W0(l.RESETTING);
                    break;
                case 4:
                case 5:
                    g1.e.m(this.f11154q != null, "In-progress recording shouldn't be null when in state " + this.f11148k);
                    if (this.f11151n != this.f11154q) {
                        throw new AssertionError("In-progress recording does not match the active recording. Unable to reset encoder.");
                    }
                    if (!R()) {
                        H0(l.RESETTING);
                        z9 = true;
                        z8 = false;
                        break;
                    } else {
                        break;
                    }
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                    H0(l.RESETTING);
                    z8 = false;
                    break;
                case 7:
                default:
                    z8 = false;
                    break;
            }
        }
        if (!z8) {
            if (z9) {
                c0(this.f11154q, -1L, i8, th);
            }
        } else if (z7) {
            y0();
        } else {
            x0();
        }
    }
}
