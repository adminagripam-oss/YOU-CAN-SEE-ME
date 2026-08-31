package a3;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final /* synthetic */ class e implements Runnable {

    /* renamed from: e  reason: collision with root package name */
    private final j f189e;

    /* renamed from: f  reason: collision with root package name */
    private final u2.m f190f;

    /* renamed from: g  reason: collision with root package name */
    private final int f191g;

    /* renamed from: h  reason: collision with root package name */
    private final Runnable f192h;

    private e(j jVar, u2.m mVar, int i8, Runnable runnable) {
        this.f189e = jVar;
        this.f190f = mVar;
        this.f191g = i8;
        this.f192h = runnable;
    }

    public static Runnable a(j jVar, u2.m mVar, int i8, Runnable runnable) {
        return new e(jVar, mVar, i8, runnable);
    }

    @Override // java.lang.Runnable
    public void run() {
        j.e(this.f189e, this.f190f, this.f191g, this.f192h);
    }
}
