package t4;

import java.util.Objects;
/* loaded from: classes.dex */
public final class j extends j4.w {

    /* renamed from: a  reason: collision with root package name */
    private final e0 f11868a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f11869a;

        static {
            int[] iArr = new int[y4.i0.values().length];
            f11869a = iArr;
            try {
                iArr[y4.i0.TINK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f11869a[y4.i0.LEGACY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f11869a[y4.i0.RAW.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f11869a[y4.i0.CRUNCHY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public j(e0 e0Var) {
        this.f11868a = e0Var;
    }

    private static String c(y4.i0 i0Var) {
        int i8 = a.f11869a[i0Var.ordinal()];
        return i8 != 1 ? i8 != 2 ? i8 != 3 ? i8 != 4 ? "UNKNOWN" : "CRUNCHY" : "RAW" : "LEGACY" : "TINK";
    }

    @Override // j4.w
    public boolean a() {
        return this.f11868a.d().Z() != y4.i0.RAW;
    }

    public e0 b() {
        return this.f11868a;
    }

    public boolean equals(Object obj) {
        if (obj instanceof j) {
            e0 e0Var = ((j) obj).f11868a;
            return this.f11868a.d().Z().equals(e0Var.d().Z()) && this.f11868a.d().a0().equals(e0Var.d().a0()) && this.f11868a.d().b0().equals(e0Var.d().b0());
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.f11868a.d(), this.f11868a.a());
    }

    public String toString() {
        return String.format("(typeUrl=%s, outputPrefixType=%s)", this.f11868a.d().a0(), c(this.f11868a.d().Z()));
    }
}
