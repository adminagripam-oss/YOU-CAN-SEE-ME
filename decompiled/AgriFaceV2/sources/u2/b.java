package u2;

import u2.l;
/* loaded from: classes.dex */
final class b extends l {

    /* renamed from: a  reason: collision with root package name */
    private final m f12045a;

    /* renamed from: b  reason: collision with root package name */
    private final String f12046b;

    /* renamed from: c  reason: collision with root package name */
    private final s2.c f12047c;

    /* renamed from: d  reason: collision with root package name */
    private final s2.e f12048d;

    /* renamed from: e  reason: collision with root package name */
    private final s2.b f12049e;

    /* renamed from: u2.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static final class C0157b extends l.a {

        /* renamed from: a  reason: collision with root package name */
        private m f12050a;

        /* renamed from: b  reason: collision with root package name */
        private String f12051b;

        /* renamed from: c  reason: collision with root package name */
        private s2.c f12052c;

        /* renamed from: d  reason: collision with root package name */
        private s2.e f12053d;

        /* renamed from: e  reason: collision with root package name */
        private s2.b f12054e;

        @Override // u2.l.a
        public l a() {
            String str = "";
            if (this.f12050a == null) {
                str = " transportContext";
            }
            if (this.f12051b == null) {
                str = str + " transportName";
            }
            if (this.f12052c == null) {
                str = str + " event";
            }
            if (this.f12053d == null) {
                str = str + " transformer";
            }
            if (this.f12054e == null) {
                str = str + " encoding";
            }
            if (str.isEmpty()) {
                return new b(this.f12050a, this.f12051b, this.f12052c, this.f12053d, this.f12054e);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // u2.l.a
        l.a b(s2.b bVar) {
            if (bVar != null) {
                this.f12054e = bVar;
                return this;
            }
            throw new NullPointerException("Null encoding");
        }

        @Override // u2.l.a
        l.a c(s2.c cVar) {
            if (cVar != null) {
                this.f12052c = cVar;
                return this;
            }
            throw new NullPointerException("Null event");
        }

        @Override // u2.l.a
        l.a d(s2.e eVar) {
            if (eVar != null) {
                this.f12053d = eVar;
                return this;
            }
            throw new NullPointerException("Null transformer");
        }

        @Override // u2.l.a
        public l.a e(m mVar) {
            if (mVar != null) {
                this.f12050a = mVar;
                return this;
            }
            throw new NullPointerException("Null transportContext");
        }

        @Override // u2.l.a
        public l.a f(String str) {
            if (str != null) {
                this.f12051b = str;
                return this;
            }
            throw new NullPointerException("Null transportName");
        }
    }

    private b(m mVar, String str, s2.c cVar, s2.e eVar, s2.b bVar) {
        this.f12045a = mVar;
        this.f12046b = str;
        this.f12047c = cVar;
        this.f12048d = eVar;
        this.f12049e = bVar;
    }

    @Override // u2.l
    public s2.b b() {
        return this.f12049e;
    }

    @Override // u2.l
    s2.c c() {
        return this.f12047c;
    }

    @Override // u2.l
    s2.e e() {
        return this.f12048d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof l) {
            l lVar = (l) obj;
            return this.f12045a.equals(lVar.f()) && this.f12046b.equals(lVar.g()) && this.f12047c.equals(lVar.c()) && this.f12048d.equals(lVar.e()) && this.f12049e.equals(lVar.b());
        }
        return false;
    }

    @Override // u2.l
    public m f() {
        return this.f12045a;
    }

    @Override // u2.l
    public String g() {
        return this.f12046b;
    }

    public int hashCode() {
        return ((((((((this.f12045a.hashCode() ^ 1000003) * 1000003) ^ this.f12046b.hashCode()) * 1000003) ^ this.f12047c.hashCode()) * 1000003) ^ this.f12048d.hashCode()) * 1000003) ^ this.f12049e.hashCode();
    }

    public String toString() {
        return "SendRequest{transportContext=" + this.f12045a + ", transportName=" + this.f12046b + ", event=" + this.f12047c + ", transformer=" + this.f12048d + ", encoding=" + this.f12049e + "}";
    }
}
