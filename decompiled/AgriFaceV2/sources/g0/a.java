package g0;

import b0.l1;
import g0.f;
/* loaded from: classes.dex */
final class a extends f.b {

    /* renamed from: a  reason: collision with root package name */
    private final String f7053a;

    /* renamed from: b  reason: collision with root package name */
    private final l1 f7054b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(String str, l1 l1Var) {
        if (str == null) {
            throw new NullPointerException("Null cameraIdString");
        }
        this.f7053a = str;
        if (l1Var == null) {
            throw new NullPointerException("Null cameraConfigId");
        }
        this.f7054b = l1Var;
    }

    @Override // g0.f.b
    public l1 b() {
        return this.f7054b;
    }

    @Override // g0.f.b
    public String c() {
        return this.f7053a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof f.b) {
            f.b bVar = (f.b) obj;
            return this.f7053a.equals(bVar.c()) && this.f7054b.equals(bVar.b());
        }
        return false;
    }

    public int hashCode() {
        return ((this.f7053a.hashCode() ^ 1000003) * 1000003) ^ this.f7054b.hashCode();
    }

    public String toString() {
        return "CameraId{cameraIdString=" + this.f7053a + ", cameraConfigId=" + this.f7054b + "}";
    }
}
