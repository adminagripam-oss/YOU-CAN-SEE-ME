package b3;

import b3.d;
/* loaded from: classes.dex */
final class a extends d {

    /* renamed from: b  reason: collision with root package name */
    private final long f4554b;

    /* renamed from: c  reason: collision with root package name */
    private final int f4555c;

    /* renamed from: d  reason: collision with root package name */
    private final int f4556d;

    /* renamed from: e  reason: collision with root package name */
    private final long f4557e;

    /* renamed from: f  reason: collision with root package name */
    private final int f4558f;

    /* loaded from: classes.dex */
    static final class b extends d.a {

        /* renamed from: a  reason: collision with root package name */
        private Long f4559a;

        /* renamed from: b  reason: collision with root package name */
        private Integer f4560b;

        /* renamed from: c  reason: collision with root package name */
        private Integer f4561c;

        /* renamed from: d  reason: collision with root package name */
        private Long f4562d;

        /* renamed from: e  reason: collision with root package name */
        private Integer f4563e;

        @Override // b3.d.a
        d a() {
            String str = "";
            if (this.f4559a == null) {
                str = " maxStorageSizeInBytes";
            }
            if (this.f4560b == null) {
                str = str + " loadBatchSize";
            }
            if (this.f4561c == null) {
                str = str + " criticalSectionEnterTimeoutMs";
            }
            if (this.f4562d == null) {
                str = str + " eventCleanUpAge";
            }
            if (this.f4563e == null) {
                str = str + " maxBlobByteSizePerRow";
            }
            if (str.isEmpty()) {
                return new a(this.f4559a.longValue(), this.f4560b.intValue(), this.f4561c.intValue(), this.f4562d.longValue(), this.f4563e.intValue());
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // b3.d.a
        d.a b(int i8) {
            this.f4561c = Integer.valueOf(i8);
            return this;
        }

        @Override // b3.d.a
        d.a c(long j8) {
            this.f4562d = Long.valueOf(j8);
            return this;
        }

        @Override // b3.d.a
        d.a d(int i8) {
            this.f4560b = Integer.valueOf(i8);
            return this;
        }

        @Override // b3.d.a
        d.a e(int i8) {
            this.f4563e = Integer.valueOf(i8);
            return this;
        }

        @Override // b3.d.a
        d.a f(long j8) {
            this.f4559a = Long.valueOf(j8);
            return this;
        }
    }

    private a(long j8, int i8, int i9, long j9, int i10) {
        this.f4554b = j8;
        this.f4555c = i8;
        this.f4556d = i9;
        this.f4557e = j9;
        this.f4558f = i10;
    }

    @Override // b3.d
    int b() {
        return this.f4556d;
    }

    @Override // b3.d
    long c() {
        return this.f4557e;
    }

    @Override // b3.d
    int d() {
        return this.f4555c;
    }

    @Override // b3.d
    int e() {
        return this.f4558f;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof d) {
            d dVar = (d) obj;
            return this.f4554b == dVar.f() && this.f4555c == dVar.d() && this.f4556d == dVar.b() && this.f4557e == dVar.c() && this.f4558f == dVar.e();
        }
        return false;
    }

    @Override // b3.d
    long f() {
        return this.f4554b;
    }

    public int hashCode() {
        long j8 = this.f4554b;
        long j9 = this.f4557e;
        return ((((((((((int) (j8 ^ (j8 >>> 32))) ^ 1000003) * 1000003) ^ this.f4555c) * 1000003) ^ this.f4556d) * 1000003) ^ ((int) ((j9 >>> 32) ^ j9))) * 1000003) ^ this.f4558f;
    }

    public String toString() {
        return "EventStoreConfig{maxStorageSizeInBytes=" + this.f4554b + ", loadBatchSize=" + this.f4555c + ", criticalSectionEnterTimeoutMs=" + this.f4556d + ", eventCleanUpAge=" + this.f4557e + ", maxBlobByteSizePerRow=" + this.f4558f + "}";
    }
}
