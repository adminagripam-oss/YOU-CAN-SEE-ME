package s5;

import e6.a;
import i6.i;
import i6.j;
/* loaded from: classes.dex */
public class a implements e6.a, j.c {

    /* renamed from: a  reason: collision with root package name */
    private j f11584a;

    @Override // e6.a
    public void onAttachedToEngine(a.b bVar) {
        j jVar = new j(bVar.b(), "google_mlkit_commons");
        this.f11584a = jVar;
        jVar.e(this);
    }

    @Override // e6.a
    public void onDetachedFromEngine(a.b bVar) {
        this.f11584a.e(null);
    }

    @Override // i6.j.c
    public void onMethodCall(i iVar, j.d dVar) {
        dVar.notImplemented();
    }
}
