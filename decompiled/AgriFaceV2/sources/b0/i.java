package b0;

import b0.q2;
import java.util.List;
/* loaded from: classes.dex */
final class i extends q2.f {

    /* renamed from: a  reason: collision with root package name */
    private final b1 f4226a;

    /* renamed from: b  reason: collision with root package name */
    private final List f4227b;

    /* renamed from: c  reason: collision with root package name */
    private final String f4228c;

    /* renamed from: d  reason: collision with root package name */
    private final int f4229d;

    /* renamed from: e  reason: collision with root package name */
    private final int f4230e;

    /* renamed from: f  reason: collision with root package name */
    private final y.a0 f4231f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class b extends q2.f.a {

        /* renamed from: a  reason: collision with root package name */
        private b1 f4232a;

        /* renamed from: b  reason: collision with root package name */
        private List f4233b;

        /* renamed from: c  reason: collision with root package name */
        private String f4234c;

        /* renamed from: d  reason: collision with root package name */
        private Integer f4235d;

        /* renamed from: e  reason: collision with root package name */
        private Integer f4236e;

        /* renamed from: f  reason: collision with root package name */
        private y.a0 f4237f;

        @Override // b0.q2.f.a
        public q2.f a() {
            String str = "";
            if (this.f4232a == null) {
                str = " surface";
            }
            if (this.f4233b == null) {
                str = str + " sharedSurfaces";
            }
            if (this.f4235d == null) {
                str = str + " mirrorMode";
            }
            if (this.f4236e == null) {
                str = str + " surfaceGroupId";
            }
            if (this.f4237f == null) {
                str = str + " dynamicRange";
            }
            if (str.isEmpty()) {
                return new i(this.f4232a, this.f4233b, this.f4234c, this.f4235d.intValue(), this.f4236e.intValue(), this.f4237f);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // b0.q2.f.a
        public q2.f.a b(y.a0 a0Var) {
            if (a0Var != null) {
                this.f4237f = a0Var;
                return this;
            }
            throw new NullPointerException("Null dynamicRange");
        }

        @Override // b0.q2.f.a
        public q2.f.a c(int i8) {
            this.f4235d = Integer.valueOf(i8);
            return this;
        }

        @Override // b0.q2.f.a
        public q2.f.a d(String str) {
            this.f4234c = str;
            return this;
        }

        @Override // b0.q2.f.a
        public q2.f.a e(List list) {
            if (list != null) {
                this.f4233b = list;
                return this;
            }
            throw new NullPointerException("Null sharedSurfaces");
        }

        @Override // b0.q2.f.a
        public q2.f.a f(int i8) {
            this.f4236e = Integer.valueOf(i8);
            return this;
        }

        public q2.f.a g(b1 b1Var) {
            if (b1Var != null) {
                this.f4232a = b1Var;
                return this;
            }
            throw new NullPointerException("Null surface");
        }
    }

    private i(b1 b1Var, List list, String str, int i8, int i9, y.a0 a0Var) {
        this.f4226a = b1Var;
        this.f4227b = list;
        this.f4228c = str;
        this.f4229d = i8;
        this.f4230e = i9;
        this.f4231f = a0Var;
    }

    @Override // b0.q2.f
    public y.a0 b() {
        return this.f4231f;
    }

    @Override // b0.q2.f
    public int c() {
        return this.f4229d;
    }

    @Override // b0.q2.f
    public String d() {
        return this.f4228c;
    }

    @Override // b0.q2.f
    public List e() {
        return this.f4227b;
    }

    public boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (obj instanceof q2.f) {
            q2.f fVar = (q2.f) obj;
            return this.f4226a.equals(fVar.f()) && this.f4227b.equals(fVar.e()) && ((str = this.f4228c) != null ? str.equals(fVar.d()) : fVar.d() == null) && this.f4229d == fVar.c() && this.f4230e == fVar.g() && this.f4231f.equals(fVar.b());
        }
        return false;
    }

    @Override // b0.q2.f
    public b1 f() {
        return this.f4226a;
    }

    @Override // b0.q2.f
    public int g() {
        return this.f4230e;
    }

    public int hashCode() {
        int hashCode = (((this.f4226a.hashCode() ^ 1000003) * 1000003) ^ this.f4227b.hashCode()) * 1000003;
        String str = this.f4228c;
        return ((((((hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ this.f4229d) * 1000003) ^ this.f4230e) * 1000003) ^ this.f4231f.hashCode();
    }

    public String toString() {
        return "OutputConfig{surface=" + this.f4226a + ", sharedSurfaces=" + this.f4227b + ", physicalCameraId=" + this.f4228c + ", mirrorMode=" + this.f4229d + ", surfaceGroupId=" + this.f4230e + ", dynamicRange=" + this.f4231f + "}";
    }
}
