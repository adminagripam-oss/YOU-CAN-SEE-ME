package d7;
/* loaded from: classes.dex */
public final class m implements d {

    /* renamed from: a  reason: collision with root package name */
    private final Class f6480a;

    /* renamed from: b  reason: collision with root package name */
    private final String f6481b;

    public m(Class cls, String str) {
        k.e(cls, "jClass");
        k.e(str, "moduleName");
        this.f6480a = cls;
        this.f6481b = str;
    }

    @Override // d7.d
    public Class d() {
        return this.f6480a;
    }

    public boolean equals(Object obj) {
        return (obj instanceof m) && k.a(d(), ((m) obj).d());
    }

    public int hashCode() {
        return d().hashCode();
    }

    public String toString() {
        return d().toString() + " (Kotlin reflection is not available)";
    }
}
