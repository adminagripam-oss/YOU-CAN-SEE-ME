package b5;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final /* synthetic */ class j implements Runnable {

    /* renamed from: e  reason: collision with root package name */
    private final z f4651e;

    /* renamed from: f  reason: collision with root package name */
    private final h5.b f4652f;

    private j(z zVar, h5.b bVar) {
        this.f4651e = zVar;
        this.f4652f = bVar;
    }

    public static Runnable a(z zVar, h5.b bVar) {
        return new j(zVar, bVar);
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f4651e.d(this.f4652f);
    }
}
