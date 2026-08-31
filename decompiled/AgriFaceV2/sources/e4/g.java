package e4;

import android.os.IBinder;
import android.os.Parcel;
/* loaded from: classes.dex */
public final class g extends v3.a {
    /* JADX INFO: Access modifiers changed from: package-private */
    public g(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.signin.internal.ISignInService");
    }

    public final void d0(j jVar, f fVar) {
        Parcel d8 = d();
        v3.c.c(d8, jVar);
        v3.c.d(d8, fVar);
        e(12, d8);
    }
}
