package b0;

import android.util.Range;
import android.util.Size;
import b0.u2;
/* loaded from: classes.dex */
final class k extends u2 {

    /* renamed from: b  reason: collision with root package name */
    private final Size f4256b;

    /* renamed from: c  reason: collision with root package name */
    private final y.a0 f4257c;

    /* renamed from: d  reason: collision with root package name */
    private final Range f4258d;

    /* renamed from: e  reason: collision with root package name */
    private final u0 f4259e;

    /* renamed from: f  reason: collision with root package name */
    private final boolean f4260f;

    /* loaded from: classes.dex */
    static final class b extends u2.a {

        /* renamed from: a  reason: collision with root package name */
        private Size f4261a;

        /* renamed from: b  reason: collision with root package name */
        private y.a0 f4262b;

        /* renamed from: c  reason: collision with root package name */
        private Range f4263c;

        /* renamed from: d  reason: collision with root package name */
        private u0 f4264d;

        /* renamed from: e  reason: collision with root package name */
        private Boolean f4265e;

        /* JADX INFO: Access modifiers changed from: package-private */
        public b() {
        }

        private b(u2 u2Var) {
            this.f4261a = u2Var.e();
            this.f4262b = u2Var.b();
            this.f4263c = u2Var.c();
            this.f4264d = u2Var.d();
            this.f4265e = Boolean.valueOf(u2Var.f());
        }

        @Override // b0.u2.a
        public u2 a() {
            String str = "";
            if (this.f4261a == null) {
                str = " resolution";
            }
            if (this.f4262b == null) {
                str = str + " dynamicRange";
            }
            if (this.f4263c == null) {
                str = str + " expectedFrameRateRange";
            }
            if (this.f4265e == null) {
                str = str + " zslDisabled";
            }
            if (str.isEmpty()) {
                return new k(this.f4261a, this.f4262b, this.f4263c, this.f4264d, this.f4265e.booleanValue());
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // b0.u2.a
        public u2.a b(y.a0 a0Var) {
            if (a0Var != null) {
                this.f4262b = a0Var;
                return this;
            }
            throw new NullPointerException("Null dynamicRange");
        }

        @Override // b0.u2.a
        public u2.a c(Range range) {
            if (range != null) {
                this.f4263c = range;
                return this;
            }
            throw new NullPointerException("Null expectedFrameRateRange");
        }

        @Override // b0.u2.a
        public u2.a d(u0 u0Var) {
            this.f4264d = u0Var;
            return this;
        }

        @Override // b0.u2.a
        public u2.a e(Size size) {
            if (size != null) {
                this.f4261a = size;
                return this;
            }
            throw new NullPointerException("Null resolution");
        }

        @Override // b0.u2.a
        public u2.a f(boolean z7) {
            this.f4265e = Boolean.valueOf(z7);
            return this;
        }
    }

    private k(Size size, y.a0 a0Var, Range range, u0 u0Var, boolean z7) {
        this.f4256b = size;
        this.f4257c = a0Var;
        this.f4258d = range;
        this.f4259e = u0Var;
        this.f4260f = z7;
    }

    @Override // b0.u2
    public y.a0 b() {
        return this.f4257c;
    }

    @Override // b0.u2
    public Range c() {
        return this.f4258d;
    }

    @Override // b0.u2
    public u0 d() {
        return this.f4259e;
    }

    @Override // b0.u2
    public Size e() {
        return this.f4256b;
    }

    public boolean equals(Object obj) {
        u0 u0Var;
        if (obj == this) {
            return true;
        }
        if (obj instanceof u2) {
            u2 u2Var = (u2) obj;
            return this.f4256b.equals(u2Var.e()) && this.f4257c.equals(u2Var.b()) && this.f4258d.equals(u2Var.c()) && ((u0Var = this.f4259e) != null ? u0Var.equals(u2Var.d()) : u2Var.d() == null) && this.f4260f == u2Var.f();
        }
        return false;
    }

    @Override // b0.u2
    public boolean f() {
        return this.f4260f;
    }

    @Override // b0.u2
    public u2.a g() {
        return new b(this);
    }

    public int hashCode() {
        int hashCode = (((((this.f4256b.hashCode() ^ 1000003) * 1000003) ^ this.f4257c.hashCode()) * 1000003) ^ this.f4258d.hashCode()) * 1000003;
        u0 u0Var = this.f4259e;
        return ((hashCode ^ (u0Var == null ? 0 : u0Var.hashCode())) * 1000003) ^ (this.f4260f ? 1231 : 1237);
    }

    public String toString() {
        return "StreamSpec{resolution=" + this.f4256b + ", dynamicRange=" + this.f4257c + ", expectedFrameRateRange=" + this.f4258d + ", implementationOptions=" + this.f4259e + ", zslDisabled=" + this.f4260f + "}";
    }
}
