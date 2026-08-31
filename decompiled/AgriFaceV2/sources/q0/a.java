package q0;

import androidx.lifecycle.m;
import g0.f;
import q0.c;
/* loaded from: classes.dex */
final class a extends c.a {

    /* renamed from: a  reason: collision with root package name */
    private final m f10206a;

    /* renamed from: b  reason: collision with root package name */
    private final f.b f10207b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(m mVar, f.b bVar) {
        if (mVar == null) {
            throw new NullPointerException("Null lifecycleOwner");
        }
        this.f10206a = mVar;
        if (bVar == null) {
            throw new NullPointerException("Null cameraId");
        }
        this.f10207b = bVar;
    }

    @Override // q0.c.a
    public f.b b() {
        return this.f10207b;
    }

    @Override // q0.c.a
    public m c() {
        return this.f10206a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof c.a) {
            c.a aVar = (c.a) obj;
            return this.f10206a.equals(aVar.c()) && this.f10207b.equals(aVar.b());
        }
        return false;
    }

    public int hashCode() {
        return ((this.f10206a.hashCode() ^ 1000003) * 1000003) ^ this.f10207b.hashCode();
    }

    public String toString() {
        return "Key{lifecycleOwner=" + this.f10206a + ", cameraId=" + this.f10207b + "}";
    }
}
