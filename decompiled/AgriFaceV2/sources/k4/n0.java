package k4;

import java.util.Objects;
/* loaded from: classes.dex */
public final class n0 extends c {

    /* renamed from: a  reason: collision with root package name */
    private final String f8592a;

    /* renamed from: b  reason: collision with root package name */
    private final a f8593b;

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: b  reason: collision with root package name */
        public static final a f8594b = new a("TINK");

        /* renamed from: c  reason: collision with root package name */
        public static final a f8595c = new a("NO_PREFIX");

        /* renamed from: a  reason: collision with root package name */
        private final String f8596a;

        private a(String str) {
            this.f8596a = str;
        }

        public String toString() {
            return this.f8596a;
        }
    }

    private n0(String str, a aVar) {
        this.f8592a = str;
        this.f8593b = aVar;
    }

    public static n0 b(String str, a aVar) {
        return new n0(str, aVar);
    }

    @Override // j4.w
    public boolean a() {
        return this.f8593b != a.f8595c;
    }

    public String c() {
        return this.f8592a;
    }

    public a d() {
        return this.f8593b;
    }

    public boolean equals(Object obj) {
        if (obj instanceof n0) {
            n0 n0Var = (n0) obj;
            return n0Var.f8592a.equals(this.f8592a) && n0Var.f8593b.equals(this.f8593b);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(n0.class, this.f8592a, this.f8593b);
    }

    public String toString() {
        return "LegacyKmsAead Parameters (keyUri: " + this.f8592a + ", variant: " + this.f8593b + ")";
    }
}
