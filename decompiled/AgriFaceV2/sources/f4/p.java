package f4;
/* loaded from: classes.dex */
final class p implements Runnable {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ j f6748e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ q f6749f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public p(q qVar, j jVar) {
        this.f6749f = qVar;
        this.f6748e = jVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        c0 c0Var;
        c0 c0Var2;
        c0 c0Var3;
        c cVar;
        c0 c0Var4;
        c0 c0Var5;
        if (this.f6748e.h()) {
            c0Var5 = this.f6749f.f6752c;
            c0Var5.o();
            return;
        }
        try {
            cVar = this.f6749f.f6751b;
            Object a8 = cVar.a(this.f6748e);
            c0Var4 = this.f6749f.f6752c;
            c0Var4.n(a8);
        } catch (i e8) {
            if (e8.getCause() instanceof Exception) {
                c0Var3 = this.f6749f.f6752c;
                c0Var3.m((Exception) e8.getCause());
                return;
            }
            c0Var2 = this.f6749f.f6752c;
            c0Var2.m(e8);
        } catch (Exception e9) {
            c0Var = this.f6749f.f6752c;
            c0Var.m(e9);
        }
    }
}
