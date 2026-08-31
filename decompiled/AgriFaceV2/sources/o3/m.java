package o3;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
/* loaded from: classes.dex */
public final class m extends k3.h {
    /* JADX INFO: Access modifiers changed from: protected */
    public m(Context context, Looper looper, k3.e eVar, j3.c cVar, j3.h hVar) {
        super(context, looper, 308, eVar, cVar, hVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // k3.c
    public final String D() {
        return "com.google.android.gms.common.moduleinstall.internal.IModuleInstallService";
    }

    @Override // k3.c
    protected final String E() {
        return "com.google.android.gms.chimera.container.moduleinstall.ModuleInstallService.START";
    }

    @Override // k3.c
    protected final boolean H() {
        return true;
    }

    @Override // k3.c
    public final boolean Q() {
        return true;
    }

    @Override // k3.c, i3.a.f
    public final int g() {
        return 17895000;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // k3.c
    public final /* synthetic */ IInterface r(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.moduleinstall.internal.IModuleInstallService");
        return queryLocalInterface instanceof g ? (g) queryLocalInterface : new g(iBinder);
    }

    @Override // k3.c
    public final h3.c[] u() {
        return v3.i.f12359b;
    }
}
