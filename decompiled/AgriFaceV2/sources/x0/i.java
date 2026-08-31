package x0;

import b0.h1;
import x0.n;
/* loaded from: classes.dex */
final class i extends n {

    /* renamed from: a  reason: collision with root package name */
    private final String f12625a;

    /* renamed from: b  reason: collision with root package name */
    private final int f12626b;

    /* renamed from: c  reason: collision with root package name */
    private final h1.c f12627c;

    /* loaded from: classes.dex */
    static final class b extends n.a {

        /* renamed from: a  reason: collision with root package name */
        private String f12628a;

        /* renamed from: b  reason: collision with root package name */
        private Integer f12629b;

        /* renamed from: c  reason: collision with root package name */
        private h1.c f12630c;

        @Override // x0.n.a
        public n b() {
            String str = "";
            if (this.f12628a == null) {
                str = " mimeType";
            }
            if (this.f12629b == null) {
                str = str + " profile";
            }
            if (str.isEmpty()) {
                return new i(this.f12628a, this.f12629b.intValue(), this.f12630c);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // x0.n.a
        public n.a c(h1.c cVar) {
            this.f12630c = cVar;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public n.a d(String str) {
            if (str != null) {
                this.f12628a = str;
                return this;
            }
            throw new NullPointerException("Null mimeType");
        }

        @Override // x0.j.a
        /* renamed from: e */
        public n.a a(int i8) {
            this.f12629b = Integer.valueOf(i8);
            return this;
        }
    }

    private i(String str, int i8, h1.c cVar) {
        this.f12625a = str;
        this.f12626b = i8;
        this.f12627c = cVar;
    }

    @Override // x0.j
    public String a() {
        return this.f12625a;
    }

    @Override // x0.j
    public int b() {
        return this.f12626b;
    }

    @Override // x0.n
    public h1.c d() {
        return this.f12627c;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof n) {
            n nVar = (n) obj;
            if (this.f12625a.equals(nVar.a()) && this.f12626b == nVar.b()) {
                h1.c cVar = this.f12627c;
                h1.c d8 = nVar.d();
                if (cVar == null) {
                    if (d8 == null) {
                        return true;
                    }
                } else if (cVar.equals(d8)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((this.f12625a.hashCode() ^ 1000003) * 1000003) ^ this.f12626b) * 1000003;
        h1.c cVar = this.f12627c;
        return hashCode ^ (cVar == null ? 0 : cVar.hashCode());
    }

    public String toString() {
        return "VideoMimeInfo{mimeType=" + this.f12625a + ", profile=" + this.f12626b + ", compatibleVideoProfile=" + this.f12627c + "}";
    }
}
