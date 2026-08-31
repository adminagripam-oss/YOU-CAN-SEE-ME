package u0;

import u0.a;
/* loaded from: classes.dex */
final class u extends u0.a {

    /* renamed from: b  reason: collision with root package name */
    private final int f12015b;

    /* renamed from: c  reason: collision with root package name */
    private final int f12016c;

    /* renamed from: d  reason: collision with root package name */
    private final int f12017d;

    /* renamed from: e  reason: collision with root package name */
    private final int f12018e;

    /* loaded from: classes.dex */
    static final class b extends a.AbstractC0155a {

        /* renamed from: a  reason: collision with root package name */
        private Integer f12019a;

        /* renamed from: b  reason: collision with root package name */
        private Integer f12020b;

        /* renamed from: c  reason: collision with root package name */
        private Integer f12021c;

        /* renamed from: d  reason: collision with root package name */
        private Integer f12022d;

        @Override // u0.a.AbstractC0155a
        u0.a a() {
            String str = "";
            if (this.f12019a == null) {
                str = " audioSource";
            }
            if (this.f12020b == null) {
                str = str + " sampleRate";
            }
            if (this.f12021c == null) {
                str = str + " channelCount";
            }
            if (this.f12022d == null) {
                str = str + " audioFormat";
            }
            if (str.isEmpty()) {
                return new u(this.f12019a.intValue(), this.f12020b.intValue(), this.f12021c.intValue(), this.f12022d.intValue());
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // u0.a.AbstractC0155a
        public a.AbstractC0155a c(int i8) {
            this.f12022d = Integer.valueOf(i8);
            return this;
        }

        @Override // u0.a.AbstractC0155a
        public a.AbstractC0155a d(int i8) {
            this.f12019a = Integer.valueOf(i8);
            return this;
        }

        @Override // u0.a.AbstractC0155a
        public a.AbstractC0155a e(int i8) {
            this.f12021c = Integer.valueOf(i8);
            return this;
        }

        @Override // u0.a.AbstractC0155a
        public a.AbstractC0155a f(int i8) {
            this.f12020b = Integer.valueOf(i8);
            return this;
        }
    }

    private u(int i8, int i9, int i10, int i11) {
        this.f12015b = i8;
        this.f12016c = i9;
        this.f12017d = i10;
        this.f12018e = i11;
    }

    @Override // u0.a
    public int b() {
        return this.f12018e;
    }

    @Override // u0.a
    public int c() {
        return this.f12015b;
    }

    @Override // u0.a
    public int e() {
        return this.f12017d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof u0.a) {
            u0.a aVar = (u0.a) obj;
            return this.f12015b == aVar.c() && this.f12016c == aVar.f() && this.f12017d == aVar.e() && this.f12018e == aVar.b();
        }
        return false;
    }

    @Override // u0.a
    public int f() {
        return this.f12016c;
    }

    public int hashCode() {
        return ((((((this.f12015b ^ 1000003) * 1000003) ^ this.f12016c) * 1000003) ^ this.f12017d) * 1000003) ^ this.f12018e;
    }

    public String toString() {
        return "AudioSettings{audioSource=" + this.f12015b + ", sampleRate=" + this.f12016c + ", channelCount=" + this.f12017d + ", audioFormat=" + this.f12018e + "}";
    }
}
