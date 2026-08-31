package t2;

import t2.k;
/* loaded from: classes.dex */
final class e extends k {

    /* renamed from: a  reason: collision with root package name */
    private final k.b f11717a;

    /* renamed from: b  reason: collision with root package name */
    private final t2.a f11718b;

    /* loaded from: classes.dex */
    static final class b extends k.a {

        /* renamed from: a  reason: collision with root package name */
        private k.b f11719a;

        /* renamed from: b  reason: collision with root package name */
        private t2.a f11720b;

        @Override // t2.k.a
        public k a() {
            return new e(this.f11719a, this.f11720b);
        }

        @Override // t2.k.a
        public k.a b(t2.a aVar) {
            this.f11720b = aVar;
            return this;
        }

        @Override // t2.k.a
        public k.a c(k.b bVar) {
            this.f11719a = bVar;
            return this;
        }
    }

    private e(k.b bVar, t2.a aVar) {
        this.f11717a = bVar;
        this.f11718b = aVar;
    }

    @Override // t2.k
    public t2.a b() {
        return this.f11718b;
    }

    @Override // t2.k
    public k.b c() {
        return this.f11717a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof k) {
            k kVar = (k) obj;
            k.b bVar = this.f11717a;
            if (bVar != null ? bVar.equals(kVar.c()) : kVar.c() == null) {
                t2.a aVar = this.f11718b;
                t2.a b8 = kVar.b();
                if (aVar == null) {
                    if (b8 == null) {
                        return true;
                    }
                } else if (aVar.equals(b8)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        k.b bVar = this.f11717a;
        int hashCode = ((bVar == null ? 0 : bVar.hashCode()) ^ 1000003) * 1000003;
        t2.a aVar = this.f11718b;
        return hashCode ^ (aVar != null ? aVar.hashCode() : 0);
    }

    public String toString() {
        return "ClientInfo{clientType=" + this.f11717a + ", androidClientInfo=" + this.f11718b + "}";
    }
}
