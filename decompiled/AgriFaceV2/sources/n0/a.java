package n0;

import n0.e;
/* loaded from: classes.dex */
final class a extends e {

    /* renamed from: a  reason: collision with root package name */
    private final String f9699a;

    /* renamed from: b  reason: collision with root package name */
    private final String f9700b;

    /* renamed from: c  reason: collision with root package name */
    private final String f9701c;

    /* renamed from: d  reason: collision with root package name */
    private final String f9702d;

    /* loaded from: classes.dex */
    static final class b extends e.a {

        /* renamed from: a  reason: collision with root package name */
        private String f9703a;

        /* renamed from: b  reason: collision with root package name */
        private String f9704b;

        /* renamed from: c  reason: collision with root package name */
        private String f9705c;

        /* renamed from: d  reason: collision with root package name */
        private String f9706d;

        @Override // n0.e.a
        public e a() {
            String str = "";
            if (this.f9703a == null) {
                str = " glVersion";
            }
            if (this.f9704b == null) {
                str = str + " eglVersion";
            }
            if (this.f9705c == null) {
                str = str + " glExtensions";
            }
            if (this.f9706d == null) {
                str = str + " eglExtensions";
            }
            if (str.isEmpty()) {
                return new a(this.f9703a, this.f9704b, this.f9705c, this.f9706d);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // n0.e.a
        public e.a b(String str) {
            if (str != null) {
                this.f9706d = str;
                return this;
            }
            throw new NullPointerException("Null eglExtensions");
        }

        @Override // n0.e.a
        public e.a c(String str) {
            if (str != null) {
                this.f9704b = str;
                return this;
            }
            throw new NullPointerException("Null eglVersion");
        }

        @Override // n0.e.a
        public e.a d(String str) {
            if (str != null) {
                this.f9705c = str;
                return this;
            }
            throw new NullPointerException("Null glExtensions");
        }

        @Override // n0.e.a
        public e.a e(String str) {
            if (str != null) {
                this.f9703a = str;
                return this;
            }
            throw new NullPointerException("Null glVersion");
        }
    }

    private a(String str, String str2, String str3, String str4) {
        this.f9699a = str;
        this.f9700b = str2;
        this.f9701c = str3;
        this.f9702d = str4;
    }

    @Override // n0.e
    public String b() {
        return this.f9702d;
    }

    @Override // n0.e
    public String c() {
        return this.f9700b;
    }

    @Override // n0.e
    public String d() {
        return this.f9701c;
    }

    @Override // n0.e
    public String e() {
        return this.f9699a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof e) {
            e eVar = (e) obj;
            return this.f9699a.equals(eVar.e()) && this.f9700b.equals(eVar.c()) && this.f9701c.equals(eVar.d()) && this.f9702d.equals(eVar.b());
        }
        return false;
    }

    public int hashCode() {
        return ((((((this.f9699a.hashCode() ^ 1000003) * 1000003) ^ this.f9700b.hashCode()) * 1000003) ^ this.f9701c.hashCode()) * 1000003) ^ this.f9702d.hashCode();
    }

    public String toString() {
        return "GraphicDeviceInfo{glVersion=" + this.f9699a + ", eglVersion=" + this.f9700b + ", glExtensions=" + this.f9701c + ", eglExtensions=" + this.f9702d + "}";
    }
}
