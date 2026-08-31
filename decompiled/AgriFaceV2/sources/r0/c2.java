package r0;
/* loaded from: classes.dex */
public abstract class c2 {

    /* renamed from: a  reason: collision with root package name */
    private final s f11022a;

    /* renamed from: b  reason: collision with root package name */
    private final b1 f11023b;

    /* loaded from: classes.dex */
    public static final class a extends c2 {

        /* renamed from: c  reason: collision with root package name */
        private final t f11024c;

        /* renamed from: d  reason: collision with root package name */
        private final int f11025d;

        /* renamed from: e  reason: collision with root package name */
        private final Throwable f11026e;

        a(s sVar, b1 b1Var, t tVar, int i8, Throwable th) {
            super(sVar, b1Var);
            this.f11024c = tVar;
            this.f11025d = i8;
            this.f11026e = th;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static String h(int i8) {
            switch (i8) {
                case 0:
                    return "ERROR_NONE";
                case 1:
                    return "ERROR_UNKNOWN";
                case 2:
                    return "ERROR_FILE_SIZE_LIMIT_REACHED";
                case 3:
                    return "ERROR_INSUFFICIENT_STORAGE";
                case 4:
                    return "ERROR_SOURCE_INACTIVE";
                case 5:
                    return "ERROR_INVALID_OUTPUT_OPTIONS";
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                    return "ERROR_ENCODING_FAILED";
                case 7:
                    return "ERROR_RECORDER_ERROR";
                case 8:
                    return "ERROR_NO_VALID_DATA";
                case 9:
                    return "ERROR_DURATION_LIMIT_REACHED";
                case 10:
                    return "ERROR_RECORDING_GARBAGE_COLLECTED";
                default:
                    return "Unknown(" + i8 + ")";
            }
        }

        public Throwable i() {
            return this.f11026e;
        }

        public int j() {
            return this.f11025d;
        }

        public boolean k() {
            return this.f11025d != 0;
        }
    }

    /* loaded from: classes.dex */
    public static final class b extends c2 {
        b(s sVar, b1 b1Var) {
            super(sVar, b1Var);
        }
    }

    /* loaded from: classes.dex */
    public static final class c extends c2 {
        c(s sVar, b1 b1Var) {
            super(sVar, b1Var);
        }
    }

    /* loaded from: classes.dex */
    public static final class d extends c2 {
        d(s sVar, b1 b1Var) {
            super(sVar, b1Var);
        }
    }

    /* loaded from: classes.dex */
    public static final class e extends c2 {
        e(s sVar, b1 b1Var) {
            super(sVar, b1Var);
        }
    }

    c2(s sVar, b1 b1Var) {
        this.f11022a = (s) g1.e.j(sVar);
        this.f11023b = (b1) g1.e.j(b1Var);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static a a(s sVar, b1 b1Var, t tVar) {
        return new a(sVar, b1Var, tVar, 0, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static a b(s sVar, b1 b1Var, t tVar, int i8, Throwable th) {
        g1.e.b(i8 != 0, "An error type is required.");
        return new a(sVar, b1Var, tVar, i8, th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b d(s sVar, b1 b1Var) {
        return new b(sVar, b1Var);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static c e(s sVar, b1 b1Var) {
        return new c(sVar, b1Var);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static d f(s sVar, b1 b1Var) {
        return new d(sVar, b1Var);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static e g(s sVar, b1 b1Var) {
        return new e(sVar, b1Var);
    }

    public s c() {
        return this.f11022a;
    }
}
