package c4;

import android.os.IBinder;
import android.os.IInterface;
/* loaded from: classes.dex */
public abstract class b0 extends x3.d implements c0 {
    public static c0 e(IBinder iBinder) {
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.ILocationListener");
        return queryLocalInterface instanceof c0 ? (c0) queryLocalInterface : new a0(iBinder);
    }
}
