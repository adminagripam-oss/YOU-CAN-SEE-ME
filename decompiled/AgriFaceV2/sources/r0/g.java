package r0;

import r0.r;
/* loaded from: classes.dex */
final class g extends r {

    /* renamed from: a  reason: collision with root package name */
    private final d2 f11049a;

    /* renamed from: b  reason: collision with root package name */
    private final r0.a f11050b;

    /* renamed from: c  reason: collision with root package name */
    private final int f11051c;

    /* loaded from: classes.dex */
    static final class b extends r.a {

        /* renamed from: a  reason: collision with root package name */
        private d2 f11052a;

        /* renamed from: b  reason: collision with root package name */
        private r0.a f11053b;

        /* renamed from: c  reason: collision with root package name */
        private Integer f11054c;

        /* JADX INFO: Access modifiers changed from: package-private */
        public b() {
        }

        private b(r rVar) {
            this.f11052a = rVar.d();
            this.f11053b = rVar.b();
            this.f11054c = Integer.valueOf(rVar.c());
        }

        @Override // r0.r.a
        public r a() {
            String str = "";
            if (this.f11052a == null) {
                str = " videoSpec";
            }
            if (this.f11053b == null) {
                str = str + " audioSpec";
            }
            if (this.f11054c == null) {
                str = str + " outputFormat";
            }
            if (str.isEmpty()) {
                return new g(this.f11052a, this.f11053b, this.f11054c.intValue());
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // r0.r.a
        d2 c() {
            d2 d2Var = this.f11052a;
            if (d2Var != null) {
                return d2Var;
            }
            throw new IllegalStateException("Property \"videoSpec\" has not been set");
        }

        @Override // r0.r.a
        public r.a d(r0.a aVar) {
            if (aVar != null) {
                this.f11053b = aVar;
                return this;
            }
            throw new NullPointerException("Null audioSpec");
        }

        @Override // r0.r.a
        public r.a e(int i8) {
            this.f11054c = Integer.valueOf(i8);
            return this;
        }

        @Override // r0.r.a
        public r.a f(d2 d2Var) {
            if (d2Var != null) {
                this.f11052a = d2Var;
                return this;
            }
            throw new NullPointerException("Null videoSpec");
        }
    }

    private g(d2 d2Var, r0.a aVar, int i8) {
        this.f11049a = d2Var;
        this.f11050b = aVar;
        this.f11051c = i8;
    }

    @Override // r0.r
    public r0.a b() {
        return this.f11050b;
    }

    @Override // r0.r
    public int c() {
        return this.f11051c;
    }

    @Override // r0.r
    public d2 d() {
        return this.f11049a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof r) {
            r rVar = (r) obj;
            return this.f11049a.equals(rVar.d()) && this.f11050b.equals(rVar.b()) && this.f11051c == rVar.c();
        }
        return false;
    }

    public int hashCode() {
        return ((((this.f11049a.hashCode() ^ 1000003) * 1000003) ^ this.f11050b.hashCode()) * 1000003) ^ this.f11051c;
    }

    @Override // r0.r
    public r.a i() {
        return new b(this);
    }

    public String toString() {
        return "MediaSpec{videoSpec=" + this.f11049a + ", audioSpec=" + this.f11050b + ", outputFormat=" + this.f11051c + "}";
    }
}
