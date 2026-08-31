package t2;

import t2.o;
/* loaded from: classes.dex */
final class i extends o {

    /* renamed from: a  reason: collision with root package name */
    private final o.c f11750a;

    /* renamed from: b  reason: collision with root package name */
    private final o.b f11751b;

    /* loaded from: classes.dex */
    static final class b extends o.a {

        /* renamed from: a  reason: collision with root package name */
        private o.c f11752a;

        /* renamed from: b  reason: collision with root package name */
        private o.b f11753b;

        @Override // t2.o.a
        public o a() {
            return new i(this.f11752a, this.f11753b);
        }

        @Override // t2.o.a
        public o.a b(o.b bVar) {
            this.f11753b = bVar;
            return this;
        }

        @Override // t2.o.a
        public o.a c(o.c cVar) {
            this.f11752a = cVar;
            return this;
        }
    }

    private i(o.c cVar, o.b bVar) {
        this.f11750a = cVar;
        this.f11751b = bVar;
    }

    @Override // t2.o
    public o.b b() {
        return this.f11751b;
    }

    @Override // t2.o
    public o.c c() {
        return this.f11750a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof o) {
            o oVar = (o) obj;
            o.c cVar = this.f11750a;
            if (cVar != null ? cVar.equals(oVar.c()) : oVar.c() == null) {
                o.b bVar = this.f11751b;
                o.b b8 = oVar.b();
                if (bVar == null) {
                    if (b8 == null) {
                        return true;
                    }
                } else if (bVar.equals(b8)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        o.c cVar = this.f11750a;
        int hashCode = ((cVar == null ? 0 : cVar.hashCode()) ^ 1000003) * 1000003;
        o.b bVar = this.f11751b;
        return hashCode ^ (bVar != null ? bVar.hashCode() : 0);
    }

    public String toString() {
        return "NetworkConnectionInfo{networkType=" + this.f11750a + ", mobileSubtype=" + this.f11751b + "}";
    }
}
