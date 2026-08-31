package t2;

import java.util.List;
import t2.m;
/* loaded from: classes.dex */
final class g extends m {

    /* renamed from: a  reason: collision with root package name */
    private final long f11735a;

    /* renamed from: b  reason: collision with root package name */
    private final long f11736b;

    /* renamed from: c  reason: collision with root package name */
    private final k f11737c;

    /* renamed from: d  reason: collision with root package name */
    private final Integer f11738d;

    /* renamed from: e  reason: collision with root package name */
    private final String f11739e;

    /* renamed from: f  reason: collision with root package name */
    private final List f11740f;

    /* renamed from: g  reason: collision with root package name */
    private final p f11741g;

    /* loaded from: classes.dex */
    static final class b extends m.a {

        /* renamed from: a  reason: collision with root package name */
        private Long f11742a;

        /* renamed from: b  reason: collision with root package name */
        private Long f11743b;

        /* renamed from: c  reason: collision with root package name */
        private k f11744c;

        /* renamed from: d  reason: collision with root package name */
        private Integer f11745d;

        /* renamed from: e  reason: collision with root package name */
        private String f11746e;

        /* renamed from: f  reason: collision with root package name */
        private List f11747f;

        /* renamed from: g  reason: collision with root package name */
        private p f11748g;

        @Override // t2.m.a
        public m a() {
            String str = "";
            if (this.f11742a == null) {
                str = " requestTimeMs";
            }
            if (this.f11743b == null) {
                str = str + " requestUptimeMs";
            }
            if (str.isEmpty()) {
                return new g(this.f11742a.longValue(), this.f11743b.longValue(), this.f11744c, this.f11745d, this.f11746e, this.f11747f, this.f11748g);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // t2.m.a
        public m.a b(k kVar) {
            this.f11744c = kVar;
            return this;
        }

        @Override // t2.m.a
        public m.a c(List list) {
            this.f11747f = list;
            return this;
        }

        @Override // t2.m.a
        m.a d(Integer num) {
            this.f11745d = num;
            return this;
        }

        @Override // t2.m.a
        m.a e(String str) {
            this.f11746e = str;
            return this;
        }

        @Override // t2.m.a
        public m.a f(p pVar) {
            this.f11748g = pVar;
            return this;
        }

        @Override // t2.m.a
        public m.a g(long j8) {
            this.f11742a = Long.valueOf(j8);
            return this;
        }

        @Override // t2.m.a
        public m.a h(long j8) {
            this.f11743b = Long.valueOf(j8);
            return this;
        }
    }

    private g(long j8, long j9, k kVar, Integer num, String str, List list, p pVar) {
        this.f11735a = j8;
        this.f11736b = j9;
        this.f11737c = kVar;
        this.f11738d = num;
        this.f11739e = str;
        this.f11740f = list;
        this.f11741g = pVar;
    }

    @Override // t2.m
    public k b() {
        return this.f11737c;
    }

    @Override // t2.m
    public List c() {
        return this.f11740f;
    }

    @Override // t2.m
    public Integer d() {
        return this.f11738d;
    }

    @Override // t2.m
    public String e() {
        return this.f11739e;
    }

    public boolean equals(Object obj) {
        k kVar;
        Integer num;
        String str;
        List list;
        if (obj == this) {
            return true;
        }
        if (obj instanceof m) {
            m mVar = (m) obj;
            if (this.f11735a == mVar.g() && this.f11736b == mVar.h() && ((kVar = this.f11737c) != null ? kVar.equals(mVar.b()) : mVar.b() == null) && ((num = this.f11738d) != null ? num.equals(mVar.d()) : mVar.d() == null) && ((str = this.f11739e) != null ? str.equals(mVar.e()) : mVar.e() == null) && ((list = this.f11740f) != null ? list.equals(mVar.c()) : mVar.c() == null)) {
                p pVar = this.f11741g;
                p f8 = mVar.f();
                if (pVar == null) {
                    if (f8 == null) {
                        return true;
                    }
                } else if (pVar.equals(f8)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // t2.m
    public p f() {
        return this.f11741g;
    }

    @Override // t2.m
    public long g() {
        return this.f11735a;
    }

    @Override // t2.m
    public long h() {
        return this.f11736b;
    }

    public int hashCode() {
        long j8 = this.f11735a;
        long j9 = this.f11736b;
        int i8 = (((((int) (j8 ^ (j8 >>> 32))) ^ 1000003) * 1000003) ^ ((int) ((j9 >>> 32) ^ j9))) * 1000003;
        k kVar = this.f11737c;
        int hashCode = (i8 ^ (kVar == null ? 0 : kVar.hashCode())) * 1000003;
        Integer num = this.f11738d;
        int hashCode2 = (hashCode ^ (num == null ? 0 : num.hashCode())) * 1000003;
        String str = this.f11739e;
        int hashCode3 = (hashCode2 ^ (str == null ? 0 : str.hashCode())) * 1000003;
        List list = this.f11740f;
        int hashCode4 = (hashCode3 ^ (list == null ? 0 : list.hashCode())) * 1000003;
        p pVar = this.f11741g;
        return hashCode4 ^ (pVar != null ? pVar.hashCode() : 0);
    }

    public String toString() {
        return "LogRequest{requestTimeMs=" + this.f11735a + ", requestUptimeMs=" + this.f11736b + ", clientInfo=" + this.f11737c + ", logSource=" + this.f11738d + ", logSourceName=" + this.f11739e + ", logEvents=" + this.f11740f + ", qosTier=" + this.f11741g + "}";
    }
}
