package m6;

import m6.t0;
/* loaded from: classes.dex */
public class g extends t0.e {

    /* renamed from: b  reason: collision with root package name */
    private final v4 f9297b;

    public g(i6.b bVar, v4 v4Var) {
        super(bVar);
        this.f9297b = v4Var;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(x.h hVar, t0.e.a aVar) {
        if (this.f9297b.e(hVar)) {
            return;
        }
        b(Long.valueOf(this.f9297b.b(hVar)), aVar);
    }
}
