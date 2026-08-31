package t2;

import java.util.Arrays;
import t2.l;
/* loaded from: classes.dex */
final class f extends l {

    /* renamed from: a  reason: collision with root package name */
    private final long f11721a;

    /* renamed from: b  reason: collision with root package name */
    private final Integer f11722b;

    /* renamed from: c  reason: collision with root package name */
    private final long f11723c;

    /* renamed from: d  reason: collision with root package name */
    private final byte[] f11724d;

    /* renamed from: e  reason: collision with root package name */
    private final String f11725e;

    /* renamed from: f  reason: collision with root package name */
    private final long f11726f;

    /* renamed from: g  reason: collision with root package name */
    private final o f11727g;

    /* loaded from: classes.dex */
    static final class b extends l.a {

        /* renamed from: a  reason: collision with root package name */
        private Long f11728a;

        /* renamed from: b  reason: collision with root package name */
        private Integer f11729b;

        /* renamed from: c  reason: collision with root package name */
        private Long f11730c;

        /* renamed from: d  reason: collision with root package name */
        private byte[] f11731d;

        /* renamed from: e  reason: collision with root package name */
        private String f11732e;

        /* renamed from: f  reason: collision with root package name */
        private Long f11733f;

        /* renamed from: g  reason: collision with root package name */
        private o f11734g;

        @Override // t2.l.a
        public l a() {
            String str = "";
            if (this.f11728a == null) {
                str = " eventTimeMs";
            }
            if (this.f11730c == null) {
                str = str + " eventUptimeMs";
            }
            if (this.f11733f == null) {
                str = str + " timezoneOffsetSeconds";
            }
            if (str.isEmpty()) {
                return new f(this.f11728a.longValue(), this.f11729b, this.f11730c.longValue(), this.f11731d, this.f11732e, this.f11733f.longValue(), this.f11734g);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // t2.l.a
        public l.a b(Integer num) {
            this.f11729b = num;
            return this;
        }

        @Override // t2.l.a
        public l.a c(long j8) {
            this.f11728a = Long.valueOf(j8);
            return this;
        }

        @Override // t2.l.a
        public l.a d(long j8) {
            this.f11730c = Long.valueOf(j8);
            return this;
        }

        @Override // t2.l.a
        public l.a e(o oVar) {
            this.f11734g = oVar;
            return this;
        }

        @Override // t2.l.a
        l.a f(byte[] bArr) {
            this.f11731d = bArr;
            return this;
        }

        @Override // t2.l.a
        l.a g(String str) {
            this.f11732e = str;
            return this;
        }

        @Override // t2.l.a
        public l.a h(long j8) {
            this.f11733f = Long.valueOf(j8);
            return this;
        }
    }

    private f(long j8, Integer num, long j9, byte[] bArr, String str, long j10, o oVar) {
        this.f11721a = j8;
        this.f11722b = num;
        this.f11723c = j9;
        this.f11724d = bArr;
        this.f11725e = str;
        this.f11726f = j10;
        this.f11727g = oVar;
    }

    @Override // t2.l
    public Integer b() {
        return this.f11722b;
    }

    @Override // t2.l
    public long c() {
        return this.f11721a;
    }

    @Override // t2.l
    public long d() {
        return this.f11723c;
    }

    @Override // t2.l
    public o e() {
        return this.f11727g;
    }

    public boolean equals(Object obj) {
        Integer num;
        String str;
        if (obj == this) {
            return true;
        }
        if (obj instanceof l) {
            l lVar = (l) obj;
            if (this.f11721a == lVar.c() && ((num = this.f11722b) != null ? num.equals(lVar.b()) : lVar.b() == null) && this.f11723c == lVar.d()) {
                if (Arrays.equals(this.f11724d, lVar instanceof f ? ((f) lVar).f11724d : lVar.f()) && ((str = this.f11725e) != null ? str.equals(lVar.g()) : lVar.g() == null) && this.f11726f == lVar.h()) {
                    o oVar = this.f11727g;
                    o e8 = lVar.e();
                    if (oVar == null) {
                        if (e8 == null) {
                            return true;
                        }
                    } else if (oVar.equals(e8)) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    @Override // t2.l
    public byte[] f() {
        return this.f11724d;
    }

    @Override // t2.l
    public String g() {
        return this.f11725e;
    }

    @Override // t2.l
    public long h() {
        return this.f11726f;
    }

    public int hashCode() {
        long j8 = this.f11721a;
        int i8 = (((int) (j8 ^ (j8 >>> 32))) ^ 1000003) * 1000003;
        Integer num = this.f11722b;
        int hashCode = num == null ? 0 : num.hashCode();
        long j9 = this.f11723c;
        int hashCode2 = (((((i8 ^ hashCode) * 1000003) ^ ((int) (j9 ^ (j9 >>> 32)))) * 1000003) ^ Arrays.hashCode(this.f11724d)) * 1000003;
        String str = this.f11725e;
        int hashCode3 = str == null ? 0 : str.hashCode();
        long j10 = this.f11726f;
        int i9 = (((hashCode2 ^ hashCode3) * 1000003) ^ ((int) ((j10 >>> 32) ^ j10))) * 1000003;
        o oVar = this.f11727g;
        return i9 ^ (oVar != null ? oVar.hashCode() : 0);
    }

    public String toString() {
        return "LogEvent{eventTimeMs=" + this.f11721a + ", eventCode=" + this.f11722b + ", eventUptimeMs=" + this.f11723c + ", sourceExtension=" + Arrays.toString(this.f11724d) + ", sourceExtensionJsonProto3=" + this.f11725e + ", timezoneOffsetSeconds=" + this.f11726f + ", networkConnectionInfo=" + this.f11727g + "}";
    }
}
