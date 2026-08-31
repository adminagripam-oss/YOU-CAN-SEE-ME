package r0;

import java.util.List;
import r0.v;
/* loaded from: classes.dex */
final class j extends v.b {

    /* renamed from: j  reason: collision with root package name */
    private final int f11065j;

    /* renamed from: k  reason: collision with root package name */
    private final String f11066k;

    /* renamed from: l  reason: collision with root package name */
    private final List f11067l;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(int i8, String str, List list) {
        this.f11065j = i8;
        if (str == null) {
            throw new NullPointerException("Null name");
        }
        this.f11066k = str;
        if (list == null) {
            throw new NullPointerException("Null typicalSizes");
        }
        this.f11067l = list;
    }

    @Override // r0.v.b
    public String c() {
        return this.f11066k;
    }

    @Override // r0.v.b
    public List d() {
        return this.f11067l;
    }

    @Override // r0.v.b
    public int e() {
        return this.f11065j;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof v.b) {
            v.b bVar = (v.b) obj;
            return this.f11065j == bVar.e() && this.f11066k.equals(bVar.c()) && this.f11067l.equals(bVar.d());
        }
        return false;
    }

    public int hashCode() {
        return ((((this.f11065j ^ 1000003) * 1000003) ^ this.f11066k.hashCode()) * 1000003) ^ this.f11067l.hashCode();
    }

    public String toString() {
        return "ConstantQuality{value=" + this.f11065j + ", name=" + this.f11066k + ", typicalSizes=" + this.f11067l + "}";
    }
}
