package c4;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.location.LocationRequest;
import java.util.ArrayList;
/* loaded from: classes.dex */
public final class n implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        boolean z7 = false;
        ArrayList arrayList = null;
        boolean z8 = false;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            int i8 = l3.b.i(l8);
            if (i8 == 1) {
                arrayList = l3.b.g(parcel, l8, LocationRequest.CREATOR);
            } else if (i8 == 2) {
                z7 = l3.b.j(parcel, l8);
            } else if (i8 != 3) {
                l3.b.q(parcel, l8);
            } else {
                z8 = l3.b.j(parcel, l8);
            }
        }
        l3.b.h(parcel, r8);
        return new g(arrayList, z7, z8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new g[i8];
    }
}
