package o3;

import android.os.IBinder;
import android.os.Parcel;
/* loaded from: classes.dex */
public final class g extends v3.a {
    /* JADX INFO: Access modifiers changed from: package-private */
    public g(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.moduleinstall.internal.IModuleInstallService");
    }

    public final void d0(f fVar, a aVar, h hVar) {
        Parcel d8 = d();
        v3.c.d(d8, fVar);
        v3.c.c(d8, aVar);
        v3.c.d(d8, hVar);
        e(2, d8);
    }
}
