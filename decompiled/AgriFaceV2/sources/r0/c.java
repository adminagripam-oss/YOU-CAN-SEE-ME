package r0;

import android.util.Range;
import r0.a;
/* loaded from: classes.dex */
final class c extends r0.a {

    /* renamed from: d  reason: collision with root package name */
    private final Range f11004d;

    /* renamed from: e  reason: collision with root package name */
    private final int f11005e;

    /* renamed from: f  reason: collision with root package name */
    private final int f11006f;

    /* renamed from: g  reason: collision with root package name */
    private final Range f11007g;

    /* renamed from: h  reason: collision with root package name */
    private final int f11008h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class b extends a.AbstractC0144a {

        /* renamed from: a  reason: collision with root package name */
        private Range f11009a;

        /* renamed from: b  reason: collision with root package name */
        private Integer f11010b;

        /* renamed from: c  reason: collision with root package name */
        private Integer f11011c;

        /* renamed from: d  reason: collision with root package name */
        private Range f11012d;

        /* renamed from: e  reason: collision with root package name */
        private Integer f11013e;

        @Override // r0.a.AbstractC0144a
        public r0.a a() {
            String str = "";
            if (this.f11009a == null) {
                str = " bitrate";
            }
            if (this.f11010b == null) {
                str = str + " sourceFormat";
            }
            if (this.f11011c == null) {
                str = str + " source";
            }
            if (this.f11012d == null) {
                str = str + " sampleRate";
            }
            if (this.f11013e == null) {
                str = str + " channelCount";
            }
            if (str.isEmpty()) {
                return new c(this.f11009a, this.f11010b.intValue(), this.f11011c.intValue(), this.f11012d, this.f11013e.intValue());
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // r0.a.AbstractC0144a
        public a.AbstractC0144a b(Range range) {
            if (range != null) {
                this.f11009a = range;
                return this;
            }
            throw new NullPointerException("Null bitrate");
        }

        @Override // r0.a.AbstractC0144a
        public a.AbstractC0144a c(int i8) {
            this.f11013e = Integer.valueOf(i8);
            return this;
        }

        @Override // r0.a.AbstractC0144a
        public a.AbstractC0144a d(Range range) {
            if (range != null) {
                this.f11012d = range;
                return this;
            }
            throw new NullPointerException("Null sampleRate");
        }

        @Override // r0.a.AbstractC0144a
        public a.AbstractC0144a e(int i8) {
            this.f11011c = Integer.valueOf(i8);
            return this;
        }

        public a.AbstractC0144a f(int i8) {
            this.f11010b = Integer.valueOf(i8);
            return this;
        }
    }

    private c(Range range, int i8, int i9, Range range2, int i10) {
        this.f11004d = range;
        this.f11005e = i8;
        this.f11006f = i9;
        this.f11007g = range2;
        this.f11008h = i10;
    }

    @Override // r0.a
    public Range b() {
        return this.f11004d;
    }

    @Override // r0.a
    public int c() {
        return this.f11008h;
    }

    @Override // r0.a
    public Range d() {
        return this.f11007g;
    }

    @Override // r0.a
    public int e() {
        return this.f11006f;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof r0.a) {
            r0.a aVar = (r0.a) obj;
            return this.f11004d.equals(aVar.b()) && this.f11005e == aVar.f() && this.f11006f == aVar.e() && this.f11007g.equals(aVar.d()) && this.f11008h == aVar.c();
        }
        return false;
    }

    @Override // r0.a
    public int f() {
        return this.f11005e;
    }

    public int hashCode() {
        return ((((((((this.f11004d.hashCode() ^ 1000003) * 1000003) ^ this.f11005e) * 1000003) ^ this.f11006f) * 1000003) ^ this.f11007g.hashCode()) * 1000003) ^ this.f11008h;
    }

    public String toString() {
        return "AudioSpec{bitrate=" + this.f11004d + ", sourceFormat=" + this.f11005e + ", source=" + this.f11006f + ", sampleRate=" + this.f11007g + ", channelCount=" + this.f11008h + "}";
    }
}
