package b0;

import android.util.Size;
import java.util.Map;
/* loaded from: classes.dex */
final class m extends x2 {

    /* renamed from: a  reason: collision with root package name */
    private final Size f4278a;

    /* renamed from: b  reason: collision with root package name */
    private final Map f4279b;

    /* renamed from: c  reason: collision with root package name */
    private final Size f4280c;

    /* renamed from: d  reason: collision with root package name */
    private final Map f4281d;

    /* renamed from: e  reason: collision with root package name */
    private final Size f4282e;

    /* renamed from: f  reason: collision with root package name */
    private final Map f4283f;

    /* renamed from: g  reason: collision with root package name */
    private final Map f4284g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(Size size, Map map, Size size2, Map map2, Size size3, Map map3, Map map4) {
        if (size == null) {
            throw new NullPointerException("Null analysisSize");
        }
        this.f4278a = size;
        if (map == null) {
            throw new NullPointerException("Null s720pSizeMap");
        }
        this.f4279b = map;
        if (size2 == null) {
            throw new NullPointerException("Null previewSize");
        }
        this.f4280c = size2;
        if (map2 == null) {
            throw new NullPointerException("Null s1440pSizeMap");
        }
        this.f4281d = map2;
        if (size3 == null) {
            throw new NullPointerException("Null recordSize");
        }
        this.f4282e = size3;
        if (map3 == null) {
            throw new NullPointerException("Null maximumSizeMap");
        }
        this.f4283f = map3;
        if (map4 == null) {
            throw new NullPointerException("Null ultraMaximumSizeMap");
        }
        this.f4284g = map4;
    }

    @Override // b0.x2
    public Size b() {
        return this.f4278a;
    }

    @Override // b0.x2
    public Map d() {
        return this.f4283f;
    }

    @Override // b0.x2
    public Size e() {
        return this.f4280c;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof x2) {
            x2 x2Var = (x2) obj;
            return this.f4278a.equals(x2Var.b()) && this.f4279b.equals(x2Var.j()) && this.f4280c.equals(x2Var.e()) && this.f4281d.equals(x2Var.h()) && this.f4282e.equals(x2Var.f()) && this.f4283f.equals(x2Var.d()) && this.f4284g.equals(x2Var.l());
        }
        return false;
    }

    @Override // b0.x2
    public Size f() {
        return this.f4282e;
    }

    @Override // b0.x2
    public Map h() {
        return this.f4281d;
    }

    public int hashCode() {
        return ((((((((((((this.f4278a.hashCode() ^ 1000003) * 1000003) ^ this.f4279b.hashCode()) * 1000003) ^ this.f4280c.hashCode()) * 1000003) ^ this.f4281d.hashCode()) * 1000003) ^ this.f4282e.hashCode()) * 1000003) ^ this.f4283f.hashCode()) * 1000003) ^ this.f4284g.hashCode();
    }

    @Override // b0.x2
    public Map j() {
        return this.f4279b;
    }

    @Override // b0.x2
    public Map l() {
        return this.f4284g;
    }

    public String toString() {
        return "SurfaceSizeDefinition{analysisSize=" + this.f4278a + ", s720pSizeMap=" + this.f4279b + ", previewSize=" + this.f4280c + ", s1440pSizeMap=" + this.f4281d + ", recordSize=" + this.f4282e + ", maximumSizeMap=" + this.f4283f + ", ultraMaximumSizeMap=" + this.f4284g + "}";
    }
}
