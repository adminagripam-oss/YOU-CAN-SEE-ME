package y0;

import android.util.Size;
import b0.z2;
import y0.o1;
/* loaded from: classes.dex */
final class d extends o1 {

    /* renamed from: a  reason: collision with root package name */
    private final String f13025a;

    /* renamed from: b  reason: collision with root package name */
    private final int f13026b;

    /* renamed from: c  reason: collision with root package name */
    private final z2 f13027c;

    /* renamed from: d  reason: collision with root package name */
    private final Size f13028d;

    /* renamed from: e  reason: collision with root package name */
    private final int f13029e;

    /* renamed from: f  reason: collision with root package name */
    private final p1 f13030f;

    /* renamed from: g  reason: collision with root package name */
    private final int f13031g;

    /* renamed from: h  reason: collision with root package name */
    private final int f13032h;

    /* renamed from: i  reason: collision with root package name */
    private final int f13033i;

    /* loaded from: classes.dex */
    static final class b extends o1.a {

        /* renamed from: a  reason: collision with root package name */
        private String f13034a;

        /* renamed from: b  reason: collision with root package name */
        private Integer f13035b;

        /* renamed from: c  reason: collision with root package name */
        private z2 f13036c;

        /* renamed from: d  reason: collision with root package name */
        private Size f13037d;

        /* renamed from: e  reason: collision with root package name */
        private Integer f13038e;

        /* renamed from: f  reason: collision with root package name */
        private p1 f13039f;

        /* renamed from: g  reason: collision with root package name */
        private Integer f13040g;

        /* renamed from: h  reason: collision with root package name */
        private Integer f13041h;

        /* renamed from: i  reason: collision with root package name */
        private Integer f13042i;

        @Override // y0.o1.a
        public o1 a() {
            String str = "";
            if (this.f13034a == null) {
                str = " mimeType";
            }
            if (this.f13035b == null) {
                str = str + " profile";
            }
            if (this.f13036c == null) {
                str = str + " inputTimebase";
            }
            if (this.f13037d == null) {
                str = str + " resolution";
            }
            if (this.f13038e == null) {
                str = str + " colorFormat";
            }
            if (this.f13039f == null) {
                str = str + " dataSpace";
            }
            if (this.f13040g == null) {
                str = str + " frameRate";
            }
            if (this.f13041h == null) {
                str = str + " IFrameInterval";
            }
            if (this.f13042i == null) {
                str = str + " bitrate";
            }
            if (str.isEmpty()) {
                return new d(this.f13034a, this.f13035b.intValue(), this.f13036c, this.f13037d, this.f13038e.intValue(), this.f13039f, this.f13040g.intValue(), this.f13041h.intValue(), this.f13042i.intValue());
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // y0.o1.a
        public o1.a b(int i8) {
            this.f13042i = Integer.valueOf(i8);
            return this;
        }

        @Override // y0.o1.a
        public o1.a c(int i8) {
            this.f13038e = Integer.valueOf(i8);
            return this;
        }

        @Override // y0.o1.a
        public o1.a d(p1 p1Var) {
            if (p1Var != null) {
                this.f13039f = p1Var;
                return this;
            }
            throw new NullPointerException("Null dataSpace");
        }

        @Override // y0.o1.a
        public o1.a e(int i8) {
            this.f13040g = Integer.valueOf(i8);
            return this;
        }

        @Override // y0.o1.a
        public o1.a f(int i8) {
            this.f13041h = Integer.valueOf(i8);
            return this;
        }

        @Override // y0.o1.a
        public o1.a g(z2 z2Var) {
            if (z2Var != null) {
                this.f13036c = z2Var;
                return this;
            }
            throw new NullPointerException("Null inputTimebase");
        }

        @Override // y0.o1.a
        public o1.a h(String str) {
            if (str != null) {
                this.f13034a = str;
                return this;
            }
            throw new NullPointerException("Null mimeType");
        }

        @Override // y0.o1.a
        public o1.a i(int i8) {
            this.f13035b = Integer.valueOf(i8);
            return this;
        }

        @Override // y0.o1.a
        public o1.a j(Size size) {
            if (size != null) {
                this.f13037d = size;
                return this;
            }
            throw new NullPointerException("Null resolution");
        }
    }

    private d(String str, int i8, z2 z2Var, Size size, int i9, p1 p1Var, int i10, int i11, int i12) {
        this.f13025a = str;
        this.f13026b = i8;
        this.f13027c = z2Var;
        this.f13028d = size;
        this.f13029e = i9;
        this.f13030f = p1Var;
        this.f13031g = i10;
        this.f13032h = i11;
        this.f13033i = i12;
    }

    @Override // y0.o1, y0.n
    public z2 a() {
        return this.f13027c;
    }

    @Override // y0.o1, y0.n
    public String c() {
        return this.f13025a;
    }

    @Override // y0.o1
    public int e() {
        return this.f13033i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof o1) {
            o1 o1Var = (o1) obj;
            return this.f13025a.equals(o1Var.c()) && this.f13026b == o1Var.j() && this.f13027c.equals(o1Var.a()) && this.f13028d.equals(o1Var.k()) && this.f13029e == o1Var.f() && this.f13030f.equals(o1Var.g()) && this.f13031g == o1Var.h() && this.f13032h == o1Var.i() && this.f13033i == o1Var.e();
        }
        return false;
    }

    @Override // y0.o1
    public int f() {
        return this.f13029e;
    }

    @Override // y0.o1
    public p1 g() {
        return this.f13030f;
    }

    @Override // y0.o1
    public int h() {
        return this.f13031g;
    }

    public int hashCode() {
        return ((((((((((((((((this.f13025a.hashCode() ^ 1000003) * 1000003) ^ this.f13026b) * 1000003) ^ this.f13027c.hashCode()) * 1000003) ^ this.f13028d.hashCode()) * 1000003) ^ this.f13029e) * 1000003) ^ this.f13030f.hashCode()) * 1000003) ^ this.f13031g) * 1000003) ^ this.f13032h) * 1000003) ^ this.f13033i;
    }

    @Override // y0.o1
    public int i() {
        return this.f13032h;
    }

    @Override // y0.o1
    public int j() {
        return this.f13026b;
    }

    @Override // y0.o1
    public Size k() {
        return this.f13028d;
    }

    public String toString() {
        return "VideoEncoderConfig{mimeType=" + this.f13025a + ", profile=" + this.f13026b + ", inputTimebase=" + this.f13027c + ", resolution=" + this.f13028d + ", colorFormat=" + this.f13029e + ", dataSpace=" + this.f13030f + ", frameRate=" + this.f13031g + ", IFrameInterval=" + this.f13032h + ", bitrate=" + this.f13033i + "}";
    }
}
