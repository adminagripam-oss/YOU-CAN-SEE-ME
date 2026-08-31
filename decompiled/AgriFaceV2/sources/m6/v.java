package m6;

import m6.t0;
/* loaded from: classes.dex */
public class v extends t0.k {

    /* renamed from: b  reason: collision with root package name */
    private final v4 f9591b;

    public v(i6.b bVar, v4 v4Var) {
        super(bVar);
        this.f9591b = v4Var;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(y.p pVar, t0.k.a aVar) {
        if (this.f9591b.e(pVar)) {
            return;
        }
        b(Long.valueOf(this.f9591b.b(pVar)), aVar);
    }
}
