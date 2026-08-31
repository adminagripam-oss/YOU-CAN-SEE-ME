package a3;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final /* synthetic */ class l implements Runnable {

    /* renamed from: e  reason: collision with root package name */
    private final n f218e;

    private l(n nVar) {
        this.f218e = nVar;
    }

    public static Runnable a(n nVar) {
        return new l(nVar);
    }

    @Override // java.lang.Runnable
    public void run() {
        r0.f223d.b(m.b(this.f218e));
    }
}
