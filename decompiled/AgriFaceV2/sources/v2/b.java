package v2;

import v2.g;
/* loaded from: classes.dex */
final class b extends g {

    /* renamed from: a  reason: collision with root package name */
    private final g.a f12328a;

    /* renamed from: b  reason: collision with root package name */
    private final long f12329b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(g.a aVar, long j8) {
        if (aVar == null) {
            throw new NullPointerException("Null status");
        }
        this.f12328a = aVar;
        this.f12329b = j8;
    }

    @Override // v2.g
    public long b() {
        return this.f12329b;
    }

    @Override // v2.g
    public g.a c() {
        return this.f12328a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof g) {
            g gVar = (g) obj;
            return this.f12328a.equals(gVar.c()) && this.f12329b == gVar.b();
        }
        return false;
    }

    public int hashCode() {
        long j8 = this.f12329b;
        return ((this.f12328a.hashCode() ^ 1000003) * 1000003) ^ ((int) (j8 ^ (j8 >>> 32)));
    }

    public String toString() {
        return "BackendResponse{status=" + this.f12328a + ", nextRequestWaitMillis=" + this.f12329b + "}";
    }
}
