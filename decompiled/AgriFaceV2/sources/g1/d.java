package g1;
/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    public final Object f7099a;

    /* renamed from: b  reason: collision with root package name */
    public final Object f7100b;

    public d(Object obj, Object obj2) {
        this.f7099a = obj;
        this.f7100b = obj2;
    }

    public static d a(Object obj, Object obj2) {
        return new d(obj, obj2);
    }

    public boolean equals(Object obj) {
        if (obj instanceof d) {
            d dVar = (d) obj;
            return c.a(dVar.f7099a, this.f7099a) && c.a(dVar.f7100b, this.f7100b);
        }
        return false;
    }

    public int hashCode() {
        Object obj = this.f7099a;
        int hashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.f7100b;
        return hashCode ^ (obj2 != null ? obj2.hashCode() : 0);
    }

    public String toString() {
        return "Pair{" + this.f7099a + " " + this.f7100b + "}";
    }
}
