package r;

import android.util.Size;
import java.util.List;
import r.l0;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class d extends l0.k {

    /* renamed from: a  reason: collision with root package name */
    private final String f10418a;

    /* renamed from: b  reason: collision with root package name */
    private final Class f10419b;

    /* renamed from: c  reason: collision with root package name */
    private final b0.q2 f10420c;

    /* renamed from: d  reason: collision with root package name */
    private final b0.g3 f10421d;

    /* renamed from: e  reason: collision with root package name */
    private final Size f10422e;

    /* renamed from: f  reason: collision with root package name */
    private final b0.u2 f10423f;

    /* renamed from: g  reason: collision with root package name */
    private final List f10424g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(String str, Class cls, b0.q2 q2Var, b0.g3 g3Var, Size size, b0.u2 u2Var, List list) {
        if (str == null) {
            throw new NullPointerException("Null useCaseId");
        }
        this.f10418a = str;
        if (cls == null) {
            throw new NullPointerException("Null useCaseType");
        }
        this.f10419b = cls;
        if (q2Var == null) {
            throw new NullPointerException("Null sessionConfig");
        }
        this.f10420c = q2Var;
        if (g3Var == null) {
            throw new NullPointerException("Null useCaseConfig");
        }
        this.f10421d = g3Var;
        this.f10422e = size;
        this.f10423f = u2Var;
        this.f10424g = list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // r.l0.k
    public List c() {
        return this.f10424g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // r.l0.k
    public b0.q2 d() {
        return this.f10420c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // r.l0.k
    public b0.u2 e() {
        return this.f10423f;
    }

    public boolean equals(Object obj) {
        Size size;
        b0.u2 u2Var;
        if (obj == this) {
            return true;
        }
        if (obj instanceof l0.k) {
            l0.k kVar = (l0.k) obj;
            if (this.f10418a.equals(kVar.h()) && this.f10419b.equals(kVar.i()) && this.f10420c.equals(kVar.d()) && this.f10421d.equals(kVar.g()) && ((size = this.f10422e) != null ? size.equals(kVar.f()) : kVar.f() == null) && ((u2Var = this.f10423f) != null ? u2Var.equals(kVar.e()) : kVar.e() == null)) {
                List list = this.f10424g;
                List c8 = kVar.c();
                if (list == null) {
                    if (c8 == null) {
                        return true;
                    }
                } else if (list.equals(c8)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // r.l0.k
    public Size f() {
        return this.f10422e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // r.l0.k
    public b0.g3 g() {
        return this.f10421d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // r.l0.k
    public String h() {
        return this.f10418a;
    }

    public int hashCode() {
        int hashCode = (((((((this.f10418a.hashCode() ^ 1000003) * 1000003) ^ this.f10419b.hashCode()) * 1000003) ^ this.f10420c.hashCode()) * 1000003) ^ this.f10421d.hashCode()) * 1000003;
        Size size = this.f10422e;
        int hashCode2 = (hashCode ^ (size == null ? 0 : size.hashCode())) * 1000003;
        b0.u2 u2Var = this.f10423f;
        int hashCode3 = (hashCode2 ^ (u2Var == null ? 0 : u2Var.hashCode())) * 1000003;
        List list = this.f10424g;
        return hashCode3 ^ (list != null ? list.hashCode() : 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // r.l0.k
    public Class i() {
        return this.f10419b;
    }

    public String toString() {
        return "UseCaseInfo{useCaseId=" + this.f10418a + ", useCaseType=" + this.f10419b + ", sessionConfig=" + this.f10420c + ", useCaseConfig=" + this.f10421d + ", surfaceResolution=" + this.f10422e + ", streamSpec=" + this.f10423f + ", captureTypes=" + this.f10424g + "}";
    }
}
