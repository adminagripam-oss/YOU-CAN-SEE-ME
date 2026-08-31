package x3;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.location.LocationRequest;
import java.util.ArrayList;
/* loaded from: classes.dex */
public final class j0 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        long j8 = Long.MAX_VALUE;
        LocationRequest locationRequest = null;
        ArrayList arrayList = null;
        String str = null;
        boolean z7 = false;
        boolean z8 = false;
        boolean z9 = false;
        boolean z10 = false;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            int i8 = l3.b.i(l8);
            if (i8 == 1) {
                locationRequest = (LocationRequest) l3.b.c(parcel, l8, LocationRequest.CREATOR);
            } else if (i8 == 5) {
                arrayList = l3.b.g(parcel, l8, k3.d.CREATOR);
            } else if (i8 == 8) {
                z7 = l3.b.j(parcel, l8);
            } else if (i8 != 9) {
                switch (i8) {
                    case 11:
                        z9 = l3.b.j(parcel, l8);
                        continue;
                    case 12:
                        z10 = l3.b.j(parcel, l8);
                        continue;
                    case 13:
                        str = l3.b.d(parcel, l8);
                        continue;
                    case 14:
                        j8 = l3.b.o(parcel, l8);
                        continue;
                    default:
                        l3.b.q(parcel, l8);
                        continue;
                }
            } else {
                z8 = l3.b.j(parcel, l8);
            }
        }
        l3.b.h(parcel, r8);
        return new i0(locationRequest, arrayList, z7, z8, z9, z10, str, j8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new i0[i8];
    }
}
