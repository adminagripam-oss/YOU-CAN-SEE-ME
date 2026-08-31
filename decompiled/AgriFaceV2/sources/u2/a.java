package u2;

import java.util.Map;
import u2.h;
/* loaded from: classes.dex */
final class a extends h {

    /* renamed from: a  reason: collision with root package name */
    private final String f12033a;

    /* renamed from: b  reason: collision with root package name */
    private final Integer f12034b;

    /* renamed from: c  reason: collision with root package name */
    private final g f12035c;

    /* renamed from: d  reason: collision with root package name */
    private final long f12036d;

    /* renamed from: e  reason: collision with root package name */
    private final long f12037e;

    /* renamed from: f  reason: collision with root package name */
    private final Map f12038f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class b extends h.a {

        /* renamed from: a  reason: collision with root package name */
        private String f12039a;

        /* renamed from: b  reason: collision with root package name */
        private Integer f12040b;

        /* renamed from: c  reason: collision with root package name */
        private g f12041c;

        /* renamed from: d  reason: collision with root package name */
        private Long f12042d;

        /* renamed from: e  reason: collision with root package name */
        private Long f12043e;

        /* renamed from: f  reason: collision with root package name */
        private Map f12044f;

        @Override // u2.h.a
        public h d() {
            String str = "";
            if (this.f12039a == null) {
                str = " transportName";
            }
            if (this.f12041c == null) {
                str = str + " encodedPayload";
            }
            if (this.f12042d == null) {
                str = str + " eventMillis";
            }
            if (this.f12043e == null) {
                str = str + " uptimeMillis";
            }
            if (this.f12044f == null) {
                str = str + " autoMetadata";
            }
            if (str.isEmpty()) {
                return new a(this.f12039a, this.f12040b, this.f12041c, this.f12042d.longValue(), this.f12043e.longValue(), this.f12044f);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // u2.h.a
        protected Map e() {
            Map map = this.f12044f;
            if (map != null) {
                return map;
            }
            throw new IllegalStateException("Property \"autoMetadata\" has not been set");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // u2.h.a
        public h.a f(Map map) {
            if (map != null) {
                this.f12044f = map;
                return this;
            }
            throw new NullPointerException("Null autoMetadata");
        }

        @Override // u2.h.a
        public h.a g(Integer num) {
            this.f12040b = num;
            return this;
        }

        @Override // u2.h.a
        public h.a h(g gVar) {
            if (gVar != null) {
                this.f12041c = gVar;
                return this;
            }
            throw new NullPointerException("Null encodedPayload");
        }

        @Override // u2.h.a
        public h.a i(long j8) {
            this.f12042d = Long.valueOf(j8);
            return this;
        }

        @Override // u2.h.a
        public h.a j(String str) {
            if (str != null) {
                this.f12039a = str;
                return this;
            }
            throw new NullPointerException("Null transportName");
        }

        @Override // u2.h.a
        public h.a k(long j8) {
            this.f12043e = Long.valueOf(j8);
            return this;
        }
    }

    private a(String str, Integer num, g gVar, long j8, long j9, Map map) {
        this.f12033a = str;
        this.f12034b = num;
        this.f12035c = gVar;
        this.f12036d = j8;
        this.f12037e = j9;
        this.f12038f = map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // u2.h
    public Map c() {
        return this.f12038f;
    }

    @Override // u2.h
    public Integer d() {
        return this.f12034b;
    }

    @Override // u2.h
    public g e() {
        return this.f12035c;
    }

    public boolean equals(Object obj) {
        Integer num;
        if (obj == this) {
            return true;
        }
        if (obj instanceof h) {
            h hVar = (h) obj;
            return this.f12033a.equals(hVar.j()) && ((num = this.f12034b) != null ? num.equals(hVar.d()) : hVar.d() == null) && this.f12035c.equals(hVar.e()) && this.f12036d == hVar.f() && this.f12037e == hVar.k() && this.f12038f.equals(hVar.c());
        }
        return false;
    }

    @Override // u2.h
    public long f() {
        return this.f12036d;
    }

    public int hashCode() {
        int hashCode = (this.f12033a.hashCode() ^ 1000003) * 1000003;
        Integer num = this.f12034b;
        int hashCode2 = num == null ? 0 : num.hashCode();
        long j8 = this.f12036d;
        long j9 = this.f12037e;
        return ((((((((hashCode ^ hashCode2) * 1000003) ^ this.f12035c.hashCode()) * 1000003) ^ ((int) (j8 ^ (j8 >>> 32)))) * 1000003) ^ ((int) (j9 ^ (j9 >>> 32)))) * 1000003) ^ this.f12038f.hashCode();
    }

    @Override // u2.h
    public String j() {
        return this.f12033a;
    }

    @Override // u2.h
    public long k() {
        return this.f12037e;
    }

    public String toString() {
        return "EventInternal{transportName=" + this.f12033a + ", code=" + this.f12034b + ", encodedPayload=" + this.f12035c + ", eventMillis=" + this.f12036d + ", uptimeMillis=" + this.f12037e + ", autoMetadata=" + this.f12038f + "}";
    }
}
