package y;

import b0.a3;
import b0.k0;
import b0.p2;
import y.s1;
/* loaded from: classes.dex */
public interface s1 {

    /* renamed from: a  reason: collision with root package name */
    public static final s1 f12902a = new s1() { // from class: y.r1
        @Override // y.s1
        public final s1.c b(s1.b bVar) {
            s1.c e8;
            e8 = s1.e(bVar);
            return e8;
        }
    };

    /* renamed from: b  reason: collision with root package name */
    public static final s1 f12903b = new k0.b(d());

    /* renamed from: c  reason: collision with root package name */
    public static final s1 f12904c = new b0.k0(d());

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private final s1 f12905a;

        /* renamed from: b  reason: collision with root package name */
        private long f12906b;

        public a(s1 s1Var) {
            this.f12905a = s1Var;
            this.f12906b = s1Var.f();
        }

        public s1 a() {
            s1 s1Var = this.f12905a;
            return s1Var instanceof p2 ? ((p2) s1Var).c(this.f12906b) : new a3(this.f12906b, this.f12905a);
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        Throwable a();

        int b();

        long c();
    }

    /* loaded from: classes.dex */
    public static final class c {

        /* renamed from: d  reason: collision with root package name */
        public static final c f12907d = new c(false, 0);

        /* renamed from: e  reason: collision with root package name */
        public static final c f12908e = new c(true);

        /* renamed from: f  reason: collision with root package name */
        public static final c f12909f = new c(true, 100);

        /* renamed from: g  reason: collision with root package name */
        public static c f12910g = new c(false, 0, true);

        /* renamed from: a  reason: collision with root package name */
        private final long f12911a;

        /* renamed from: b  reason: collision with root package name */
        private final boolean f12912b;

        /* renamed from: c  reason: collision with root package name */
        private final boolean f12913c;

        private c(boolean z7) {
            this(z7, a());
        }

        public static long a() {
            return 500L;
        }

        public long b() {
            return this.f12911a;
        }

        public boolean c() {
            return this.f12913c;
        }

        public boolean d() {
            return this.f12912b;
        }

        private c(boolean z7, long j8) {
            this(z7, j8, false);
        }

        private c(boolean z7, long j8, boolean z8) {
            this.f12912b = z7;
            this.f12911a = j8;
            if (z8) {
                g1.e.b(!z7, "shouldRetry must be false when completeWithoutFailure is set to true");
            }
            this.f12913c = z8;
        }
    }

    static long d() {
        return 6000L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    static /* synthetic */ c e(b bVar) {
        return c.f12907d;
    }

    c b(b bVar);

    default long f() {
        return 0L;
    }
}
