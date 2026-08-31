package y0;

import b0.z2;
import y0.a;
/* loaded from: classes.dex */
final class c extends y0.a {

    /* renamed from: a  reason: collision with root package name */
    private final String f13011a;

    /* renamed from: b  reason: collision with root package name */
    private final int f13012b;

    /* renamed from: c  reason: collision with root package name */
    private final z2 f13013c;

    /* renamed from: d  reason: collision with root package name */
    private final int f13014d;

    /* renamed from: e  reason: collision with root package name */
    private final int f13015e;

    /* renamed from: f  reason: collision with root package name */
    private final int f13016f;

    /* loaded from: classes.dex */
    static final class b extends a.AbstractC0165a {

        /* renamed from: a  reason: collision with root package name */
        private String f13017a;

        /* renamed from: b  reason: collision with root package name */
        private Integer f13018b;

        /* renamed from: c  reason: collision with root package name */
        private z2 f13019c;

        /* renamed from: d  reason: collision with root package name */
        private Integer f13020d;

        /* renamed from: e  reason: collision with root package name */
        private Integer f13021e;

        /* renamed from: f  reason: collision with root package name */
        private Integer f13022f;

        @Override // y0.a.AbstractC0165a
        y0.a a() {
            String str = "";
            if (this.f13017a == null) {
                str = " mimeType";
            }
            if (this.f13018b == null) {
                str = str + " profile";
            }
            if (this.f13019c == null) {
                str = str + " inputTimebase";
            }
            if (this.f13020d == null) {
                str = str + " bitrate";
            }
            if (this.f13021e == null) {
                str = str + " sampleRate";
            }
            if (this.f13022f == null) {
                str = str + " channelCount";
            }
            if (str.isEmpty()) {
                return new c(this.f13017a, this.f13018b.intValue(), this.f13019c, this.f13020d.intValue(), this.f13021e.intValue(), this.f13022f.intValue());
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // y0.a.AbstractC0165a
        public a.AbstractC0165a c(int i8) {
            this.f13020d = Integer.valueOf(i8);
            return this;
        }

        @Override // y0.a.AbstractC0165a
        public a.AbstractC0165a d(int i8) {
            this.f13022f = Integer.valueOf(i8);
            return this;
        }

        @Override // y0.a.AbstractC0165a
        public a.AbstractC0165a e(z2 z2Var) {
            if (z2Var != null) {
                this.f13019c = z2Var;
                return this;
            }
            throw new NullPointerException("Null inputTimebase");
        }

        @Override // y0.a.AbstractC0165a
        public a.AbstractC0165a f(String str) {
            if (str != null) {
                this.f13017a = str;
                return this;
            }
            throw new NullPointerException("Null mimeType");
        }

        @Override // y0.a.AbstractC0165a
        public a.AbstractC0165a g(int i8) {
            this.f13018b = Integer.valueOf(i8);
            return this;
        }

        @Override // y0.a.AbstractC0165a
        public a.AbstractC0165a h(int i8) {
            this.f13021e = Integer.valueOf(i8);
            return this;
        }
    }

    private c(String str, int i8, z2 z2Var, int i9, int i10, int i11) {
        this.f13011a = str;
        this.f13012b = i8;
        this.f13013c = z2Var;
        this.f13014d = i9;
        this.f13015e = i10;
        this.f13016f = i11;
    }

    @Override // y0.a, y0.n
    public z2 a() {
        return this.f13013c;
    }

    @Override // y0.a, y0.n
    public String c() {
        return this.f13011a;
    }

    @Override // y0.a
    public int e() {
        return this.f13014d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof y0.a) {
            y0.a aVar = (y0.a) obj;
            return this.f13011a.equals(aVar.c()) && this.f13012b == aVar.g() && this.f13013c.equals(aVar.a()) && this.f13014d == aVar.e() && this.f13015e == aVar.h() && this.f13016f == aVar.f();
        }
        return false;
    }

    @Override // y0.a
    public int f() {
        return this.f13016f;
    }

    @Override // y0.a
    public int g() {
        return this.f13012b;
    }

    @Override // y0.a
    public int h() {
        return this.f13015e;
    }

    public int hashCode() {
        return ((((((((((this.f13011a.hashCode() ^ 1000003) * 1000003) ^ this.f13012b) * 1000003) ^ this.f13013c.hashCode()) * 1000003) ^ this.f13014d) * 1000003) ^ this.f13015e) * 1000003) ^ this.f13016f;
    }

    public String toString() {
        return "AudioEncoderConfig{mimeType=" + this.f13011a + ", profile=" + this.f13012b + ", inputTimebase=" + this.f13013c + ", bitrate=" + this.f13014d + ", sampleRate=" + this.f13015e + ", channelCount=" + this.f13016f + "}";
    }
}
