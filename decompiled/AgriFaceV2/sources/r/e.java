package r;

import r.j4;
/* loaded from: classes.dex */
final class e extends j4.b {

    /* renamed from: a  reason: collision with root package name */
    private final int f10441a;

    /* renamed from: b  reason: collision with root package name */
    private final int f10442b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f10443c;

    /* renamed from: d  reason: collision with root package name */
    private final boolean f10444d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(int i8, int i9, boolean z7, boolean z8) {
        this.f10441a = i8;
        this.f10442b = i9;
        this.f10443c = z7;
        this.f10444d = z8;
    }

    @Override // r.j4.b
    int a() {
        return this.f10441a;
    }

    @Override // r.j4.b
    int b() {
        return this.f10442b;
    }

    @Override // r.j4.b
    boolean c() {
        return this.f10443c;
    }

    @Override // r.j4.b
    boolean d() {
        return this.f10444d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof j4.b) {
            j4.b bVar = (j4.b) obj;
            return this.f10441a == bVar.a() && this.f10442b == bVar.b() && this.f10443c == bVar.c() && this.f10444d == bVar.d();
        }
        return false;
    }

    public int hashCode() {
        return ((((((this.f10441a ^ 1000003) * 1000003) ^ this.f10442b) * 1000003) ^ (this.f10443c ? 1231 : 1237)) * 1000003) ^ (this.f10444d ? 1231 : 1237);
    }

    public String toString() {
        return "FeatureSettings{cameraMode=" + this.f10441a + ", requiredMaxBitDepth=" + this.f10442b + ", previewStabilizationOn=" + this.f10443c + ", ultraHdrOn=" + this.f10444d + "}";
    }
}
