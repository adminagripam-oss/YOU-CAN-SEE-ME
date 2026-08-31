package a0;

import a0.a0;
import y.u0;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class d extends a0.a {

    /* renamed from: a  reason: collision with root package name */
    private final l0.z f19a;

    /* renamed from: b  reason: collision with root package name */
    private final u0.g f20b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(l0.z zVar, u0.g gVar) {
        if (zVar == null) {
            throw new NullPointerException("Null packet");
        }
        this.f19a = zVar;
        if (gVar == null) {
            throw new NullPointerException("Null outputFileOptions");
        }
        this.f20b = gVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // a0.a0.a
    public u0.g a() {
        return this.f20b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // a0.a0.a
    public l0.z b() {
        return this.f19a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof a0.a) {
            a0.a aVar = (a0.a) obj;
            return this.f19a.equals(aVar.b()) && this.f20b.equals(aVar.a());
        }
        return false;
    }

    public int hashCode() {
        return ((this.f19a.hashCode() ^ 1000003) * 1000003) ^ this.f20b.hashCode();
    }

    public String toString() {
        return "In{packet=" + this.f19a + ", outputFileOptions=" + this.f20b + "}";
    }
}
