package a3;

import java.util.Map;
/* loaded from: classes.dex */
final class a extends d {

    /* renamed from: a  reason: collision with root package name */
    private final d3.a f172a;

    /* renamed from: b  reason: collision with root package name */
    private final Map f173b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(d3.a aVar, Map map) {
        if (aVar == null) {
            throw new NullPointerException("Null clock");
        }
        this.f172a = aVar;
        if (map == null) {
            throw new NullPointerException("Null values");
        }
        this.f173b = map;
    }

    @Override // a3.d
    d3.a e() {
        return this.f172a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof d) {
            d dVar = (d) obj;
            return this.f172a.equals(dVar.e()) && this.f173b.equals(dVar.h());
        }
        return false;
    }

    @Override // a3.d
    Map h() {
        return this.f173b;
    }

    public int hashCode() {
        return ((this.f172a.hashCode() ^ 1000003) * 1000003) ^ this.f173b.hashCode();
    }

    public String toString() {
        return "SchedulerConfig{clock=" + this.f172a + ", values=" + this.f173b + "}";
    }
}
