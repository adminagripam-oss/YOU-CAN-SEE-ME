package k3;

import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
/* loaded from: classes.dex */
public final class w0 extends p0 {

    /* renamed from: e  reason: collision with root package name */
    private c f8487e;

    /* renamed from: f  reason: collision with root package name */
    private final int f8488f;

    public w0(c cVar, int i8) {
        this.f8487e = cVar;
        this.f8488f = i8;
    }

    @Override // k3.l
    public final void E(int i8, IBinder iBinder, a1 a1Var) {
        c cVar = this.f8487e;
        q.h(cVar, "onPostInitCompleteWithConnectionInfo can be called only once per call togetRemoteService");
        q.g(a1Var);
        c.a0(cVar, a1Var);
        c0(i8, iBinder, a1Var.f8326a);
    }

    @Override // k3.l
    public final void I(int i8, Bundle bundle) {
        Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
    }

    @Override // k3.l
    public final void c0(int i8, IBinder iBinder, Bundle bundle) {
        q.h(this.f8487e, "onPostInitComplete can be called only once per call to getRemoteService");
        this.f8487e.M(i8, iBinder, bundle, this.f8488f);
        this.f8487e = null;
    }
}
