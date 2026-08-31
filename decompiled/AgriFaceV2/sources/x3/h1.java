package x3;

import android.location.Location;
import android.os.Parcel;
import com.google.android.gms.common.api.Status;
/* loaded from: classes.dex */
public abstract class h1 extends d implements i1 {
    public h1() {
        super("com.google.android.gms.location.internal.ILocationStatusCallback");
    }

    @Override // x3.d
    protected final boolean d(int i8, Parcel parcel, Parcel parcel2, int i9) {
        if (i8 == 1) {
            n.d(parcel);
            x((Status) n.a(parcel, Status.CREATOR), (Location) n.a(parcel, Location.CREATOR));
            return true;
        }
        return false;
    }
}
