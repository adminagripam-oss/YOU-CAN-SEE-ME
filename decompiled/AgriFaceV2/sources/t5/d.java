package t5;

import e6.a;
import i6.j;
/* loaded from: classes.dex */
public class d implements e6.a {

    /* renamed from: a  reason: collision with root package name */
    private j f11906a;

    @Override // e6.a
    public void onAttachedToEngine(a.b bVar) {
        j jVar = new j(bVar.b(), "google_mlkit_face_detector");
        this.f11906a = jVar;
        jVar.e(new c(bVar.a()));
    }

    @Override // e6.a
    public void onDetachedFromEngine(a.b bVar) {
        this.f11906a.e(null);
    }
}
