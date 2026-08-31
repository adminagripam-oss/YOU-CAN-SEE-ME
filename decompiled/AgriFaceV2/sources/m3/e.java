package m3;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import k3.h;
import k3.w;
/* loaded from: classes.dex */
public final class e extends h {
    private final w I;

    public e(Context context, Looper looper, k3.e eVar, w wVar, j3.c cVar, j3.h hVar) {
        super(context, looper, 270, eVar, cVar, hVar);
        this.I = wVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // k3.c
    public final String D() {
        return "com.google.android.gms.common.internal.service.IClientTelemetryService";
    }

    @Override // k3.c
    protected final String E() {
        return "com.google.android.gms.common.telemetry.service.START";
    }

    @Override // k3.c
    protected final boolean H() {
        return true;
    }

    @Override // k3.c, i3.a.f
    public final int g() {
        return 203400000;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // k3.c
    public final /* synthetic */ IInterface r(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.service.IClientTelemetryService");
        return queryLocalInterface instanceof a ? (a) queryLocalInterface : new a(iBinder);
    }

    @Override // k3.c
    public final h3.c[] u() {
        return v3.d.f12355b;
    }

    @Override // k3.c
    protected final Bundle z() {
        return this.I.b();
    }
}
