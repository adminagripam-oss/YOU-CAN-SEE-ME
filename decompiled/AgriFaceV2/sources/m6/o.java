package m6;

import m6.t0;
/* loaded from: classes.dex */
public class o extends t0.g {

    /* renamed from: b  reason: collision with root package name */
    private final v4 f9390b;

    public o(i6.b bVar, v4 v4Var) {
        super(bVar);
        this.f9390b = v4Var;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(y.k kVar, t0.g.a aVar) {
        if (this.f9390b.e(kVar)) {
            return;
        }
        b(Long.valueOf(this.f9390b.b(kVar)), aVar);
    }
}
