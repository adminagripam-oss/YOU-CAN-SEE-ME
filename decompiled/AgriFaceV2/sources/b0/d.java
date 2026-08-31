package b0;

import b0.u0;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class d extends u0.a {

    /* renamed from: a  reason: collision with root package name */
    private final String f4160a;

    /* renamed from: b  reason: collision with root package name */
    private final Class f4161b;

    /* renamed from: c  reason: collision with root package name */
    private final Object f4162c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(String str, Class cls, Object obj) {
        if (str == null) {
            throw new NullPointerException("Null id");
        }
        this.f4160a = str;
        if (cls == null) {
            throw new NullPointerException("Null valueClass");
        }
        this.f4161b = cls;
        this.f4162c = obj;
    }

    @Override // b0.u0.a
    public String c() {
        return this.f4160a;
    }

    @Override // b0.u0.a
    public Object d() {
        return this.f4162c;
    }

    @Override // b0.u0.a
    public Class e() {
        return this.f4161b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof u0.a) {
            u0.a aVar = (u0.a) obj;
            if (this.f4160a.equals(aVar.c()) && this.f4161b.equals(aVar.e())) {
                Object obj2 = this.f4162c;
                Object d8 = aVar.d();
                if (obj2 == null) {
                    if (d8 == null) {
                        return true;
                    }
                } else if (obj2.equals(d8)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((this.f4160a.hashCode() ^ 1000003) * 1000003) ^ this.f4161b.hashCode()) * 1000003;
        Object obj = this.f4162c;
        return hashCode ^ (obj == null ? 0 : obj.hashCode());
    }

    public String toString() {
        return "Option{id=" + this.f4160a + ", valueClass=" + this.f4161b + ", token=" + this.f4162c + "}";
    }
}
