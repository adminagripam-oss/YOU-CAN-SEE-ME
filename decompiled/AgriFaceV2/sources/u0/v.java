package u0;

import u0.p;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class v extends p.c {

    /* renamed from: a  reason: collision with root package name */
    private final int f12023a;

    /* renamed from: b  reason: collision with root package name */
    private final long f12024b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public v(int i8, long j8) {
        this.f12023a = i8;
        this.f12024b = j8;
    }

    @Override // u0.p.c
    public int a() {
        return this.f12023a;
    }

    @Override // u0.p.c
    public long b() {
        return this.f12024b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof p.c) {
            p.c cVar = (p.c) obj;
            return this.f12023a == cVar.a() && this.f12024b == cVar.b();
        }
        return false;
    }

    public int hashCode() {
        long j8 = this.f12024b;
        return ((this.f12023a ^ 1000003) * 1000003) ^ ((int) (j8 ^ (j8 >>> 32)));
    }

    public String toString() {
        return "PacketInfo{sizeInBytes=" + this.f12023a + ", timestampNs=" + this.f12024b + "}";
    }
}
