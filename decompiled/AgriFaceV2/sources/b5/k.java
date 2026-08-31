package b5;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final /* synthetic */ class k implements Runnable {

    /* renamed from: e  reason: collision with root package name */
    private final v f4653e;

    /* renamed from: f  reason: collision with root package name */
    private final h5.b f4654f;

    private k(v vVar, h5.b bVar) {
        this.f4653e = vVar;
        this.f4654f = bVar;
    }

    public static Runnable a(v vVar, h5.b bVar) {
        return new k(vVar, bVar);
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f4653e.a(this.f4654f);
    }
}
