package y;

import y.r;
/* loaded from: classes.dex */
final class b extends r {

    /* renamed from: a  reason: collision with root package name */
    private final r.b f12740a;

    /* renamed from: b  reason: collision with root package name */
    private final r.a f12741b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(r.b bVar, r.a aVar) {
        if (bVar == null) {
            throw new NullPointerException("Null type");
        }
        this.f12740a = bVar;
        this.f12741b = aVar;
    }

    @Override // y.r
    public r.a c() {
        return this.f12741b;
    }

    @Override // y.r
    public r.b d() {
        return this.f12740a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof r) {
            r rVar = (r) obj;
            if (this.f12740a.equals(rVar.d())) {
                r.a aVar = this.f12741b;
                r.a c8 = rVar.c();
                if (aVar == null) {
                    if (c8 == null) {
                        return true;
                    }
                } else if (aVar.equals(c8)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (this.f12740a.hashCode() ^ 1000003) * 1000003;
        r.a aVar = this.f12741b;
        return hashCode ^ (aVar == null ? 0 : aVar.hashCode());
    }

    public String toString() {
        return "CameraState{type=" + this.f12740a + ", error=" + this.f12741b + "}";
    }
}
