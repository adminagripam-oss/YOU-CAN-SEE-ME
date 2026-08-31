package f4;
/* loaded from: classes.dex */
final class r implements Runnable {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ j f6753e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ s f6754f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public r(s sVar, j jVar) {
        this.f6754f = sVar;
        this.f6753e = jVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        e eVar;
        e eVar2;
        obj = this.f6754f.f6756b;
        synchronized (obj) {
            s sVar = this.f6754f;
            eVar = sVar.f6757c;
            if (eVar != null) {
                eVar2 = sVar.f6757c;
                eVar2.a(this.f6753e);
            }
        }
    }
}
