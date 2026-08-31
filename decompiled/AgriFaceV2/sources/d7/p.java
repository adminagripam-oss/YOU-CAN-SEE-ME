package d7;
/* loaded from: classes.dex */
public abstract class p extends c implements h7.g {

    /* renamed from: l  reason: collision with root package name */
    private final boolean f6482l;

    public p(Object obj, Class cls, String str, String str2, int i8) {
        super(obj, cls, str, str2, (i8 & 1) == 1);
        this.f6482l = (i8 & 2) == 2;
    }

    @Override // d7.c
    public h7.a b() {
        return this.f6482l ? this : super.b();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof p) {
            p pVar = (p) obj;
            return j().equals(pVar.j()) && i().equals(pVar.i()) && k().equals(pVar.k()) && k.a(h(), pVar.h());
        } else if (obj instanceof h7.g) {
            return obj.equals(b());
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (((j().hashCode() * 31) + i().hashCode()) * 31) + k().hashCode();
    }

    public String toString() {
        h7.a b8 = b();
        if (b8 != this) {
            return b8.toString();
        }
        return "property " + i() + " (Kotlin reflection is not available)";
    }
}
