package w5;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class m {

    /* renamed from: a  reason: collision with root package name */
    private final l f12540a;

    /* renamed from: b  reason: collision with root package name */
    final Runnable f12541b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(l lVar, Runnable runnable) {
        this.f12540a = lVar;
        this.f12541b = runnable;
    }

    public Integer a() {
        l lVar = this.f12540a;
        if (lVar != null) {
            return Integer.valueOf(lVar.a());
        }
        return null;
    }

    public boolean b() {
        l lVar = this.f12540a;
        return lVar != null && lVar.b();
    }
}
