package k4;

import java.util.Objects;
/* loaded from: classes.dex */
public final class t1 extends c {

    /* renamed from: a  reason: collision with root package name */
    private final a f8648a;

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: b  reason: collision with root package name */
        public static final a f8649b = new a("TINK");

        /* renamed from: c  reason: collision with root package name */
        public static final a f8650c = new a("CRUNCHY");

        /* renamed from: d  reason: collision with root package name */
        public static final a f8651d = new a("NO_PREFIX");

        /* renamed from: a  reason: collision with root package name */
        private final String f8652a;

        private a(String str) {
            this.f8652a = str;
        }

        public String toString() {
            return this.f8652a;
        }
    }

    private t1(a aVar) {
        this.f8648a = aVar;
    }

    public static t1 b(a aVar) {
        return new t1(aVar);
    }

    @Override // j4.w
    public boolean a() {
        return this.f8648a != a.f8651d;
    }

    public a c() {
        return this.f8648a;
    }

    public boolean equals(Object obj) {
        return (obj instanceof t1) && ((t1) obj).c() == c();
    }

    public int hashCode() {
        return Objects.hash(t1.class, this.f8648a);
    }

    public String toString() {
        return "XChaCha20Poly1305 Parameters (variant: " + this.f8648a + ")";
    }
}
