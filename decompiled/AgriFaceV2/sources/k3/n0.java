package k3;

import android.app.PendingIntent;
import android.os.Bundle;
/* loaded from: classes.dex */
abstract class n0 extends v0 {

    /* renamed from: d  reason: collision with root package name */
    public final int f8451d;

    /* renamed from: e  reason: collision with root package name */
    public final Bundle f8452e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ c f8453f;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n0(c cVar, int i8, Bundle bundle) {
        super(cVar, Boolean.TRUE);
        this.f8453f = cVar;
        this.f8451d = i8;
        this.f8452e = bundle;
    }

    @Override // k3.v0
    protected final /* bridge */ /* synthetic */ void a(Object obj) {
        if (this.f8451d != 0) {
            this.f8453f.g0(1, null);
            Bundle bundle = this.f8452e;
            f(new h3.a(this.f8451d, bundle != null ? (PendingIntent) bundle.getParcelable("pendingIntent") : null));
        } else if (g()) {
        } else {
            this.f8453f.g0(1, null);
            f(new h3.a(8, null));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // k3.v0
    public final void b() {
    }

    protected abstract void f(h3.a aVar);

    protected abstract boolean g();
}
