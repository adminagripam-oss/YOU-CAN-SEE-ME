package f4;
/* loaded from: classes.dex */
final class t implements Runnable {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ j f6758e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ u f6759f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public t(u uVar, j jVar) {
        this.f6759f = uVar;
        this.f6758e = jVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        f fVar;
        f fVar2;
        obj = this.f6759f.f6761b;
        synchronized (obj) {
            u uVar = this.f6759f;
            fVar = uVar.f6762c;
            if (fVar != null) {
                fVar2 = uVar.f6762c;
                fVar2.a((Exception) k3.q.g(this.f6758e.f()));
            }
        }
    }
}
