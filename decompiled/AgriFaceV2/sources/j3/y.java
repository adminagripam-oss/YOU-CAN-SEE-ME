package j3;
/* loaded from: classes.dex */
final class y implements Runnable {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ e4.l f8243e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ a0 f8244f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public y(a0 a0Var, e4.l lVar) {
        this.f8244f = a0Var;
        this.f8243e = lVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        a0.f0(this.f8244f, this.f8243e);
    }
}
