package k7;
/* loaded from: classes.dex */
public final class v {

    /* renamed from: a  reason: collision with root package name */
    public final Object f8792a;

    /* renamed from: b  reason: collision with root package name */
    public final c7.l f8793b;

    public v(Object obj, c7.l lVar) {
        this.f8792a = obj;
        this.f8793b = lVar;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof v) {
            v vVar = (v) obj;
            return d7.k.a(this.f8792a, vVar.f8792a) && d7.k.a(this.f8793b, vVar.f8793b);
        }
        return false;
    }

    public int hashCode() {
        Object obj = this.f8792a;
        return ((obj == null ? 0 : obj.hashCode()) * 31) + this.f8793b.hashCode();
    }

    public String toString() {
        return "CompletedWithCancellation(result=" + this.f8792a + ", onCancellation=" + this.f8793b + ')';
    }
}
