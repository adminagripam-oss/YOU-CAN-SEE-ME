package y;

import android.view.Surface;
import y.g2;
/* loaded from: classes.dex */
final class h extends g2.g {

    /* renamed from: a  reason: collision with root package name */
    private final int f12823a;

    /* renamed from: b  reason: collision with root package name */
    private final Surface f12824b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(int i8, Surface surface) {
        this.f12823a = i8;
        if (surface == null) {
            throw new NullPointerException("Null surface");
        }
        this.f12824b = surface;
    }

    @Override // y.g2.g
    public int a() {
        return this.f12823a;
    }

    @Override // y.g2.g
    public Surface b() {
        return this.f12824b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof g2.g) {
            g2.g gVar = (g2.g) obj;
            return this.f12823a == gVar.a() && this.f12824b.equals(gVar.b());
        }
        return false;
    }

    public int hashCode() {
        return ((this.f12823a ^ 1000003) * 1000003) ^ this.f12824b.hashCode();
    }

    public String toString() {
        return "Result{resultCode=" + this.f12823a + ", surface=" + this.f12824b + "}";
    }
}
