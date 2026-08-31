package y;

import android.os.Handler;
import b0.e0;
import b0.f0;
import b0.h3;
import b0.u0;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public final class x implements g0.m {
    static final u0.a J = u0.a.a("camerax.core.appConfig.cameraFactoryProvider", f0.a.class);
    static final u0.a K = u0.a.a("camerax.core.appConfig.deviceSurfaceManagerProvider", e0.a.class);
    static final u0.a L = u0.a.a("camerax.core.appConfig.useCaseConfigFactoryProvider", h3.c.class);
    static final u0.a M = u0.a.a("camerax.core.appConfig.cameraExecutor", Executor.class);
    static final u0.a N = u0.a.a("camerax.core.appConfig.schedulerHandler", Handler.class);
    static final u0.a O = u0.a.a("camerax.core.appConfig.minimumLoggingLevel", Integer.TYPE);
    static final u0.a P = u0.a.a("camerax.core.appConfig.availableCamerasLimiter", q.class);
    static final u0.a Q = u0.a.a("camerax.core.appConfig.cameraOpenRetryMaxTimeoutInMillisWhileResuming", Long.TYPE);
    static final u0.a R = u0.a.a("camerax.core.appConfig.cameraProviderInitRetryPolicy", s1.class);
    static final u0.a S = u0.a.a("camerax.core.appConfig.quirksSettings", b0.i2.class);
    private final b0.f2 I;

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private final b0.a2 f12987a;

        public a() {
            this(b0.a2.d0());
        }

        private b0.z1 b() {
            return this.f12987a;
        }

        public x a() {
            return new x(b0.f2.b0(this.f12987a));
        }

        public a c(f0.a aVar) {
            b().Y(x.J, aVar);
            return this;
        }

        public a d(e0.a aVar) {
            b().Y(x.K, aVar);
            return this;
        }

        public a e(Class cls) {
            b().Y(g0.m.G, cls);
            if (b().f(g0.m.F, null) == null) {
                f(cls.getCanonicalName() + "-" + UUID.randomUUID());
            }
            return this;
        }

        public a f(String str) {
            b().Y(g0.m.F, str);
            return this;
        }

        public a g(h3.c cVar) {
            b().Y(x.L, cVar);
            return this;
        }

        private a(b0.a2 a2Var) {
            this.f12987a = a2Var;
            Class cls = (Class) a2Var.f(g0.m.G, null);
            if (cls == null || cls.equals(w.class)) {
                e(w.class);
                return;
            }
            throw new IllegalArgumentException("Invalid target class configuration for " + this + ": " + cls);
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        x getCameraXConfig();
    }

    x(b0.f2 f2Var) {
        this.I = f2Var;
    }

    public q Z(q qVar) {
        return (q) this.I.f(P, qVar);
    }

    public Executor a0(Executor executor) {
        return (Executor) this.I.f(M, executor);
    }

    public f0.a b0(f0.a aVar) {
        return (f0.a) this.I.f(J, aVar);
    }

    public long c0() {
        return ((Long) this.I.f(Q, -1L)).longValue();
    }

    public s1 d0() {
        s1 s1Var = (s1) this.I.f(R, s1.f12903b);
        Objects.requireNonNull(s1Var);
        return s1Var;
    }

    public e0.a e0(e0.a aVar) {
        return (e0.a) this.I.f(K, aVar);
    }

    public b0.i2 f0() {
        return (b0.i2) this.I.f(S, null);
    }

    public Handler g0(Handler handler) {
        return (Handler) this.I.f(N, handler);
    }

    public h3.c h0(h3.c cVar) {
        return (h3.c) this.I.f(L, cVar);
    }

    @Override // b0.m2
    public b0.u0 z() {
        return this.I;
    }
}
