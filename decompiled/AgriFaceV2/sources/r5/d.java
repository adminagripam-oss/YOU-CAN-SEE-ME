package r5;

import k3.q;
/* loaded from: classes.dex */
public final class d {

    /* renamed from: a  reason: collision with root package name */
    private final f f11331a;

    /* renamed from: b  reason: collision with root package name */
    private final m5.d f11332b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(f fVar, m5.d dVar) {
        this.f11331a = fVar;
        this.f11332b = dVar;
    }

    public final a a(q5.e eVar) {
        q.h(eVar, "You must provide a valid FaceDetectorOptions.");
        return new a((i) this.f11331a.b(eVar), this.f11332b, eVar, null);
    }
}
