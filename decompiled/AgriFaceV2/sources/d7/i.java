package d7;
/* loaded from: classes.dex */
public abstract class i extends c implements h, h7.e {

    /* renamed from: l  reason: collision with root package name */
    private final int f6477l;

    /* renamed from: m  reason: collision with root package name */
    private final int f6478m;

    public i(int i8, Object obj, Class cls, String str, String str2, int i9) {
        super(obj, cls, str, str2, (i9 & 1) == 1);
        this.f6477l = i8;
        this.f6478m = i9 >> 1;
    }

    @Override // d7.h
    public int c() {
        return this.f6477l;
    }

    @Override // d7.c
    protected h7.a e() {
        return s.a(this);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof i) {
            i iVar = (i) obj;
            return i().equals(iVar.i()) && k().equals(iVar.k()) && this.f6478m == iVar.f6478m && this.f6477l == iVar.f6477l && k.a(h(), iVar.h()) && k.a(j(), iVar.j());
        } else if (obj instanceof h7.e) {
            return obj.equals(b());
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (((j() == null ? 0 : j().hashCode() * 31) + i().hashCode()) * 31) + k().hashCode();
    }

    public String toString() {
        h7.a b8 = b();
        if (b8 != this) {
            return b8.toString();
        }
        if ("<init>".equals(i())) {
            return "constructor (Kotlin reflection is not available)";
        }
        return "function " + i() + " (Kotlin reflection is not available)";
    }
}
