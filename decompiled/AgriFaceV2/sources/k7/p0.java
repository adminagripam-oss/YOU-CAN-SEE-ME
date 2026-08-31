package k7;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class p0 implements a1 {

    /* renamed from: e  reason: collision with root package name */
    private final boolean f8775e;

    public p0(boolean z7) {
        this.f8775e = z7;
    }

    @Override // k7.a1
    public boolean b() {
        return this.f8775e;
    }

    @Override // k7.a1
    public p1 h() {
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empty{");
        sb.append(b() ? "Active" : "New");
        sb.append('}');
        return sb.toString();
    }
}
