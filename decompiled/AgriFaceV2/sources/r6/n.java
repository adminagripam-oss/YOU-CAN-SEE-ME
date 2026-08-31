package r6;

import java.io.Serializable;
/* loaded from: classes.dex */
public final class n implements Serializable {

    /* renamed from: e  reason: collision with root package name */
    private final Object f11368e;

    /* renamed from: f  reason: collision with root package name */
    private final Object f11369f;

    /* renamed from: g  reason: collision with root package name */
    private final Object f11370g;

    public n(Object obj, Object obj2, Object obj3) {
        this.f11368e = obj;
        this.f11369f = obj2;
        this.f11370g = obj3;
    }

    public final Object a() {
        return this.f11368e;
    }

    public final Object b() {
        return this.f11369f;
    }

    public final Object c() {
        return this.f11370g;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof n) {
            n nVar = (n) obj;
            return d7.k.a(this.f11368e, nVar.f11368e) && d7.k.a(this.f11369f, nVar.f11369f) && d7.k.a(this.f11370g, nVar.f11370g);
        }
        return false;
    }

    public int hashCode() {
        Object obj = this.f11368e;
        int hashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        Object obj2 = this.f11369f;
        int hashCode2 = (hashCode + (obj2 == null ? 0 : obj2.hashCode())) * 31;
        Object obj3 = this.f11370g;
        return hashCode2 + (obj3 != null ? obj3.hashCode() : 0);
    }

    public String toString() {
        return '(' + this.f11368e + ", " + this.f11369f + ", " + this.f11370g + ')';
    }
}
