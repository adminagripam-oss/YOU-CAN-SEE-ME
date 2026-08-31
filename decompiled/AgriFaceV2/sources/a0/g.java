package a0;

import a0.y0;
/* loaded from: classes.dex */
final class g extends y0.b {

    /* renamed from: a  reason: collision with root package name */
    private final int f34a;

    /* renamed from: b  reason: collision with root package name */
    private final y.v0 f35b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(int i8, y.v0 v0Var) {
        this.f34a = i8;
        if (v0Var == null) {
            throw new NullPointerException("Null imageCaptureException");
        }
        this.f35b = v0Var;
    }

    @Override // a0.y0.b
    y.v0 a() {
        return this.f35b;
    }

    @Override // a0.y0.b
    int b() {
        return this.f34a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof y0.b) {
            y0.b bVar = (y0.b) obj;
            return this.f34a == bVar.b() && this.f35b.equals(bVar.a());
        }
        return false;
    }

    public int hashCode() {
        return ((this.f34a ^ 1000003) * 1000003) ^ this.f35b.hashCode();
    }

    public String toString() {
        return "CaptureError{requestId=" + this.f34a + ", imageCaptureException=" + this.f35b + "}";
    }
}
