package k3;

import android.os.Bundle;
/* loaded from: classes.dex */
public final class z0 extends n0 {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ c f8493g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z0(c cVar, int i8, Bundle bundle) {
        super(cVar, i8, null);
        this.f8493g = cVar;
    }

    @Override // k3.n0
    protected final void f(h3.a aVar) {
        if (this.f8493g.s() && c.f0(this.f8493g)) {
            c.b0(this.f8493g, 16);
            return;
        }
        this.f8493g.f8347p.b(aVar);
        this.f8493g.K(aVar);
    }

    @Override // k3.n0
    protected final boolean g() {
        this.f8493g.f8347p.b(h3.a.f7220e);
        return true;
    }
}
