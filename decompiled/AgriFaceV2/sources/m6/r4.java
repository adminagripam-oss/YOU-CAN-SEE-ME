package m6;

import m6.t0;
/* loaded from: classes.dex */
public class r4 {

    /* renamed from: a  reason: collision with root package name */
    private final i6.b f9454a;

    /* renamed from: b  reason: collision with root package name */
    private final v4 f9455b;

    /* renamed from: c  reason: collision with root package name */
    private t0.j0 f9456c;

    public r4(i6.b bVar, v4 v4Var) {
        this.f9454a = bVar;
        this.f9455b = v4Var;
        this.f9456c = new t0.j0(bVar);
    }

    public void a(androidx.camera.core.n nVar, Long l8, Long l9, Long l10, t0.j0.a aVar) {
        if (this.f9455b.e(nVar)) {
            return;
        }
        this.f9456c.b(Long.valueOf(this.f9455b.b(nVar)), l8, l9, l10, aVar);
    }
}
