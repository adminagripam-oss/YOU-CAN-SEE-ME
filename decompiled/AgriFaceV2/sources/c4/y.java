package c4;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationResult;
/* loaded from: classes.dex */
public abstract class y extends x3.d implements z {
    public y() {
        super("com.google.android.gms.location.ILocationCallback");
    }

    public static z e(IBinder iBinder) {
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.ILocationCallback");
        return queryLocalInterface instanceof z ? (z) queryLocalInterface : new x(iBinder);
    }

    @Override // x3.d
    protected final boolean d(int i8, Parcel parcel, Parcel parcel2, int i9) {
        if (i8 == 1) {
            x3.n.d(parcel);
            r((LocationResult) x3.n.a(parcel, LocationResult.CREATOR));
        } else if (i8 == 2) {
            x3.n.d(parcel);
            K((LocationAvailability) x3.n.a(parcel, LocationAvailability.CREATOR));
        } else if (i8 != 3) {
            return false;
        } else {
            P();
        }
        return true;
    }
}
