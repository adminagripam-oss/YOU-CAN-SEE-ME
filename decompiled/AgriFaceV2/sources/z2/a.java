package z2;

import u2.m;
/* loaded from: classes.dex */
final /* synthetic */ class a implements Runnable {

    /* renamed from: e  reason: collision with root package name */
    private final c f13378e;

    /* renamed from: f  reason: collision with root package name */
    private final m f13379f;

    /* renamed from: g  reason: collision with root package name */
    private final s2.h f13380g;

    /* renamed from: h  reason: collision with root package name */
    private final u2.h f13381h;

    private a(c cVar, m mVar, s2.h hVar, u2.h hVar2) {
        this.f13378e = cVar;
        this.f13379f = mVar;
        this.f13380g = hVar;
        this.f13381h = hVar2;
    }

    public static Runnable a(c cVar, m mVar, s2.h hVar, u2.h hVar2) {
        return new a(cVar, mVar, hVar, hVar2);
    }

    @Override // java.lang.Runnable
    public void run() {
        c.c(this.f13378e, this.f13379f, this.f13380g, this.f13381h);
    }
}
