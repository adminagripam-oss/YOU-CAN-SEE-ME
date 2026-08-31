package t4;

import java.security.GeneralSecurityException;
import y4.y;
/* loaded from: classes.dex */
public final class i extends j4.h {

    /* renamed from: a  reason: collision with root package name */
    private final d0 f11851a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f11852a;

        /* renamed from: b  reason: collision with root package name */
        static final /* synthetic */ int[] f11853b;

        static {
            int[] iArr = new int[y.c.values().length];
            f11853b = iArr;
            try {
                iArr[y.c.SYMMETRIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f11853b[y.c.ASYMMETRIC_PRIVATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[y4.i0.values().length];
            f11852a = iArr2;
            try {
                iArr2[y4.i0.TINK.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f11852a[y4.i0.LEGACY.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f11852a[y4.i0.RAW.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f11852a[y4.i0.CRUNCHY.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* loaded from: classes.dex */
    private static class b extends j4.w {

        /* renamed from: a  reason: collision with root package name */
        private final String f11854a;

        /* renamed from: b  reason: collision with root package name */
        private final y4.i0 f11855b;

        private b(String str, y4.i0 i0Var) {
            this.f11854a = str;
            this.f11855b = i0Var;
        }

        private static String b(y4.i0 i0Var) {
            int i8 = a.f11852a[i0Var.ordinal()];
            return i8 != 1 ? i8 != 2 ? i8 != 3 ? i8 != 4 ? "UNKNOWN" : "CRUNCHY" : "RAW" : "LEGACY" : "TINK";
        }

        @Override // j4.w
        public boolean a() {
            return this.f11855b != y4.i0.RAW;
        }

        public String toString() {
            return String.format("(typeUrl=%s, outputPrefixType=%s)", this.f11854a, b(this.f11855b));
        }

        /* synthetic */ b(String str, y4.i0 i0Var, a aVar) {
            this(str, i0Var);
        }
    }

    public i(d0 d0Var, j4.y yVar) {
        f(d0Var, yVar);
        this.f11851a = d0Var;
    }

    private static a5.a c(d0 d0Var) {
        if (d0Var.e().equals(y4.i0.RAW)) {
            return a5.a.a(new byte[0]);
        }
        if (d0Var.e().equals(y4.i0.TINK)) {
            return v.b(d0Var.c().intValue());
        }
        if (d0Var.e().equals(y4.i0.LEGACY) || d0Var.e().equals(y4.i0.CRUNCHY)) {
            return v.a(d0Var.c().intValue());
        }
        throw new GeneralSecurityException("Unknown output prefix type");
    }

    private static void f(d0 d0Var, j4.y yVar) {
        int i8 = a.f11853b[d0Var.d().ordinal()];
        if (i8 == 1 || i8 == 2) {
            j4.y.b(yVar);
        }
    }

    @Override // j4.h
    public Integer a() {
        return this.f11851a.c();
    }

    @Override // j4.h
    public j4.w b() {
        return new b(this.f11851a.f(), this.f11851a.e(), null);
    }

    public a5.a d() {
        return c(this.f11851a);
    }

    public d0 e(j4.y yVar) {
        f(this.f11851a, yVar);
        return this.f11851a;
    }
}
