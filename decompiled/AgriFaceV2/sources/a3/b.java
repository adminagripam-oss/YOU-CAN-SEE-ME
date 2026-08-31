package a3;

import a3.d;
import java.util.Set;
/* loaded from: classes.dex */
final class b extends d.b {

    /* renamed from: a  reason: collision with root package name */
    private final long f174a;

    /* renamed from: b  reason: collision with root package name */
    private final long f175b;

    /* renamed from: c  reason: collision with root package name */
    private final Set f176c;

    /* renamed from: a3.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static final class C0000b extends d.b.a {

        /* renamed from: a  reason: collision with root package name */
        private Long f177a;

        /* renamed from: b  reason: collision with root package name */
        private Long f178b;

        /* renamed from: c  reason: collision with root package name */
        private Set f179c;

        @Override // a3.d.b.a
        public d.b a() {
            String str = "";
            if (this.f177a == null) {
                str = " delta";
            }
            if (this.f178b == null) {
                str = str + " maxAllowedDelay";
            }
            if (this.f179c == null) {
                str = str + " flags";
            }
            if (str.isEmpty()) {
                return new b(this.f177a.longValue(), this.f178b.longValue(), this.f179c);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // a3.d.b.a
        public d.b.a b(long j8) {
            this.f177a = Long.valueOf(j8);
            return this;
        }

        @Override // a3.d.b.a
        public d.b.a c(Set set) {
            if (set != null) {
                this.f179c = set;
                return this;
            }
            throw new NullPointerException("Null flags");
        }

        @Override // a3.d.b.a
        public d.b.a d(long j8) {
            this.f178b = Long.valueOf(j8);
            return this;
        }
    }

    private b(long j8, long j9, Set set) {
        this.f174a = j8;
        this.f175b = j9;
        this.f176c = set;
    }

    @Override // a3.d.b
    long b() {
        return this.f174a;
    }

    @Override // a3.d.b
    Set c() {
        return this.f176c;
    }

    @Override // a3.d.b
    long d() {
        return this.f175b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof d.b) {
            d.b bVar = (d.b) obj;
            return this.f174a == bVar.b() && this.f175b == bVar.d() && this.f176c.equals(bVar.c());
        }
        return false;
    }

    public int hashCode() {
        long j8 = this.f174a;
        long j9 = this.f175b;
        return ((((((int) (j8 ^ (j8 >>> 32))) ^ 1000003) * 1000003) ^ ((int) ((j9 >>> 32) ^ j9))) * 1000003) ^ this.f176c.hashCode();
    }

    public String toString() {
        return "ConfigValue{delta=" + this.f174a + ", maxAllowedDelay=" + this.f175b + ", flags=" + this.f176c + "}";
    }
}
