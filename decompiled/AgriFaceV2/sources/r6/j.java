package r6;

import java.io.Serializable;
/* loaded from: classes.dex */
public final class j implements Serializable {

    /* renamed from: e  reason: collision with root package name */
    private final Object f11361e;

    /* renamed from: f  reason: collision with root package name */
    private final Object f11362f;

    public j(Object obj, Object obj2) {
        this.f11361e = obj;
        this.f11362f = obj2;
    }

    public final Object a() {
        return this.f11361e;
    }

    public final Object b() {
        return this.f11362f;
    }

    public final Object c() {
        return this.f11361e;
    }

    public final Object d() {
        return this.f11362f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof j) {
            j jVar = (j) obj;
            return d7.k.a(this.f11361e, jVar.f11361e) && d7.k.a(this.f11362f, jVar.f11362f);
        }
        return false;
    }

    public int hashCode() {
        Object obj = this.f11361e;
        int hashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        Object obj2 = this.f11362f;
        return hashCode + (obj2 != null ? obj2.hashCode() : 0);
    }

    public String toString() {
        return '(' + this.f11361e + ", " + this.f11362f + ')';
    }
}
