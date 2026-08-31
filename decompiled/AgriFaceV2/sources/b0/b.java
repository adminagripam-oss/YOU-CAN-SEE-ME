package b0;

import android.util.Range;
import android.util.Size;
import java.util.List;
/* loaded from: classes.dex */
final class b extends a {

    /* renamed from: a  reason: collision with root package name */
    private final w2 f4130a;

    /* renamed from: b  reason: collision with root package name */
    private final int f4131b;

    /* renamed from: c  reason: collision with root package name */
    private final Size f4132c;

    /* renamed from: d  reason: collision with root package name */
    private final y.a0 f4133d;

    /* renamed from: e  reason: collision with root package name */
    private final List f4134e;

    /* renamed from: f  reason: collision with root package name */
    private final u0 f4135f;

    /* renamed from: g  reason: collision with root package name */
    private final Range f4136g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(w2 w2Var, int i8, Size size, y.a0 a0Var, List list, u0 u0Var, Range range) {
        if (w2Var == null) {
            throw new NullPointerException("Null surfaceConfig");
        }
        this.f4130a = w2Var;
        this.f4131b = i8;
        if (size == null) {
            throw new NullPointerException("Null size");
        }
        this.f4132c = size;
        if (a0Var == null) {
            throw new NullPointerException("Null dynamicRange");
        }
        this.f4133d = a0Var;
        if (list == null) {
            throw new NullPointerException("Null captureTypes");
        }
        this.f4134e = list;
        this.f4135f = u0Var;
        this.f4136g = range;
    }

    @Override // b0.a
    public List b() {
        return this.f4134e;
    }

    @Override // b0.a
    public y.a0 c() {
        return this.f4133d;
    }

    @Override // b0.a
    public int d() {
        return this.f4131b;
    }

    @Override // b0.a
    public u0 e() {
        return this.f4135f;
    }

    public boolean equals(Object obj) {
        u0 u0Var;
        if (obj == this) {
            return true;
        }
        if (obj instanceof a) {
            a aVar = (a) obj;
            if (this.f4130a.equals(aVar.g()) && this.f4131b == aVar.d() && this.f4132c.equals(aVar.f()) && this.f4133d.equals(aVar.c()) && this.f4134e.equals(aVar.b()) && ((u0Var = this.f4135f) != null ? u0Var.equals(aVar.e()) : aVar.e() == null)) {
                Range range = this.f4136g;
                Range h8 = aVar.h();
                if (range == null) {
                    if (h8 == null) {
                        return true;
                    }
                } else if (range.equals(h8)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // b0.a
    public Size f() {
        return this.f4132c;
    }

    @Override // b0.a
    public w2 g() {
        return this.f4130a;
    }

    @Override // b0.a
    public Range h() {
        return this.f4136g;
    }

    public int hashCode() {
        int hashCode = (((((((((this.f4130a.hashCode() ^ 1000003) * 1000003) ^ this.f4131b) * 1000003) ^ this.f4132c.hashCode()) * 1000003) ^ this.f4133d.hashCode()) * 1000003) ^ this.f4134e.hashCode()) * 1000003;
        u0 u0Var = this.f4135f;
        int hashCode2 = (hashCode ^ (u0Var == null ? 0 : u0Var.hashCode())) * 1000003;
        Range range = this.f4136g;
        return hashCode2 ^ (range != null ? range.hashCode() : 0);
    }

    public String toString() {
        return "AttachedSurfaceInfo{surfaceConfig=" + this.f4130a + ", imageFormat=" + this.f4131b + ", size=" + this.f4132c + ", dynamicRange=" + this.f4133d + ", captureTypes=" + this.f4134e + ", implementationOptions=" + this.f4135f + ", targetFrameRate=" + this.f4136g + "}";
    }
}
