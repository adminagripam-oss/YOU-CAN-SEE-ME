package m3;

import android.os.IBinder;
import android.os.Parcel;
import k3.t;
/* loaded from: classes.dex */
public final class a extends v3.a {
    /* JADX INFO: Access modifiers changed from: package-private */
    public a(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.service.IClientTelemetryService");
    }

    public final void d0(t tVar) {
        Parcel d8 = d();
        v3.c.c(d8, tVar);
        f(1, d8);
    }
}
