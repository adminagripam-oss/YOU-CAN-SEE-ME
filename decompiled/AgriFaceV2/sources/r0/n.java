package r0;

import android.util.Range;
import r0.d2;
/* loaded from: classes.dex */
final class n extends d2 {

    /* renamed from: d  reason: collision with root package name */
    private final y f11095d;

    /* renamed from: e  reason: collision with root package name */
    private final Range f11096e;

    /* renamed from: f  reason: collision with root package name */
    private final Range f11097f;

    /* renamed from: g  reason: collision with root package name */
    private final int f11098g;

    /* loaded from: classes.dex */
    static final class b extends d2.a {

        /* renamed from: a  reason: collision with root package name */
        private y f11099a;

        /* renamed from: b  reason: collision with root package name */
        private Range f11100b;

        /* renamed from: c  reason: collision with root package name */
        private Range f11101c;

        /* renamed from: d  reason: collision with root package name */
        private Integer f11102d;

        /* JADX INFO: Access modifiers changed from: package-private */
        public b() {
        }

        private b(d2 d2Var) {
            this.f11099a = d2Var.e();
            this.f11100b = d2Var.d();
            this.f11101c = d2Var.c();
            this.f11102d = Integer.valueOf(d2Var.b());
        }

        @Override // r0.d2.a
        public d2 a() {
            String str = "";
            if (this.f11099a == null) {
                str = " qualitySelector";
            }
            if (this.f11100b == null) {
                str = str + " frameRate";
            }
            if (this.f11101c == null) {
                str = str + " bitrate";
            }
            if (this.f11102d == null) {
                str = str + " aspectRatio";
            }
            if (str.isEmpty()) {
                return new n(this.f11099a, this.f11100b, this.f11101c, this.f11102d.intValue());
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // r0.d2.a
        d2.a b(int i8) {
            this.f11102d = Integer.valueOf(i8);
            return this;
        }

        @Override // r0.d2.a
        public d2.a c(Range range) {
            if (range != null) {
                this.f11101c = range;
                return this;
            }
            throw new NullPointerException("Null bitrate");
        }

        @Override // r0.d2.a
        public d2.a d(Range range) {
            if (range != null) {
                this.f11100b = range;
                return this;
            }
            throw new NullPointerException("Null frameRate");
        }

        @Override // r0.d2.a
        public d2.a e(y yVar) {
            if (yVar != null) {
                this.f11099a = yVar;
                return this;
            }
            throw new NullPointerException("Null qualitySelector");
        }
    }

    private n(y yVar, Range range, Range range2, int i8) {
        this.f11095d = yVar;
        this.f11096e = range;
        this.f11097f = range2;
        this.f11098g = i8;
    }

    @Override // r0.d2
    int b() {
        return this.f11098g;
    }

    @Override // r0.d2
    public Range c() {
        return this.f11097f;
    }

    @Override // r0.d2
    public Range d() {
        return this.f11096e;
    }

    @Override // r0.d2
    public y e() {
        return this.f11095d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof d2) {
            d2 d2Var = (d2) obj;
            return this.f11095d.equals(d2Var.e()) && this.f11096e.equals(d2Var.d()) && this.f11097f.equals(d2Var.c()) && this.f11098g == d2Var.b();
        }
        return false;
    }

    @Override // r0.d2
    public d2.a f() {
        return new b(this);
    }

    public int hashCode() {
        return ((((((this.f11095d.hashCode() ^ 1000003) * 1000003) ^ this.f11096e.hashCode()) * 1000003) ^ this.f11097f.hashCode()) * 1000003) ^ this.f11098g;
    }

    public String toString() {
        return "VideoSpec{qualitySelector=" + this.f11095d + ", frameRate=" + this.f11096e + ", bitrate=" + this.f11097f + ", aspectRatio=" + this.f11098g + "}";
    }
}
