package f4;
/* loaded from: classes.dex */
final class v implements Runnable {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ j f6763e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ w f6764f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public v(w wVar, j jVar) {
        this.f6764f = wVar;
        this.f6763e = jVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        g gVar;
        g gVar2;
        obj = this.f6764f.f6766b;
        synchronized (obj) {
            w wVar = this.f6764f;
            gVar = wVar.f6767c;
            if (gVar != null) {
                gVar2 = wVar.f6767c;
                gVar2.a(this.f6763e.g());
            }
        }
    }
}
