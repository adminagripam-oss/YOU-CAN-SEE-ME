package h2;
/* loaded from: classes.dex */
public interface c extends h2.a {

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: b  reason: collision with root package name */
        public static final C0090a f7171b = new C0090a(null);

        /* renamed from: c  reason: collision with root package name */
        public static final a f7172c = new a("NONE");

        /* renamed from: d  reason: collision with root package name */
        public static final a f7173d = new a("FULL");

        /* renamed from: a  reason: collision with root package name */
        private final String f7174a;

        /* renamed from: h2.c$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0090a {
            private C0090a() {
            }

            public /* synthetic */ C0090a(d7.g gVar) {
                this();
            }
        }

        private a(String str) {
            this.f7174a = str;
        }

        public String toString() {
            return this.f7174a;
        }
    }

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: b  reason: collision with root package name */
        public static final a f7175b = new a(null);

        /* renamed from: c  reason: collision with root package name */
        public static final b f7176c = new b("FLAT");

        /* renamed from: d  reason: collision with root package name */
        public static final b f7177d = new b("HALF_OPENED");

        /* renamed from: a  reason: collision with root package name */
        private final String f7178a;

        /* loaded from: classes.dex */
        public static final class a {
            private a() {
            }

            public /* synthetic */ a(d7.g gVar) {
                this();
            }
        }

        private b(String str) {
            this.f7178a = str;
        }

        public String toString() {
            return this.f7178a;
        }
    }

    b b();

    a c();
}
