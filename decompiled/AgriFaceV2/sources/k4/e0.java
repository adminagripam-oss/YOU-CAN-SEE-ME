package k4;

import java.util.Objects;
/* loaded from: classes.dex */
public final class e0 extends c {

    /* renamed from: a  reason: collision with root package name */
    private final a f8514a;

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: b  reason: collision with root package name */
        public static final a f8515b = new a("TINK");

        /* renamed from: c  reason: collision with root package name */
        public static final a f8516c = new a("CRUNCHY");

        /* renamed from: d  reason: collision with root package name */
        public static final a f8517d = new a("NO_PREFIX");

        /* renamed from: a  reason: collision with root package name */
        private final String f8518a;

        private a(String str) {
            this.f8518a = str;
        }

        public String toString() {
            return this.f8518a;
        }
    }

    private e0(a aVar) {
        this.f8514a = aVar;
    }

    public static e0 b(a aVar) {
        return new e0(aVar);
    }

    @Override // j4.w
    public boolean a() {
        return this.f8514a != a.f8517d;
    }

    public a c() {
        return this.f8514a;
    }

    public boolean equals(Object obj) {
        return (obj instanceof e0) && ((e0) obj).c() == c();
    }

    public int hashCode() {
        return Objects.hash(e0.class, this.f8514a);
    }

    public String toString() {
        return "ChaCha20Poly1305 Parameters (variant: " + this.f8514a + ")";
    }
}
