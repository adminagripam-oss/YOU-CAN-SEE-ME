package com.google.android.gms.location;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
/* loaded from: classes.dex */
public final class c implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        List list = LocationResult.f6020b;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            if (l3.b.i(l8) != 1) {
                l3.b.q(parcel, l8);
            } else {
                list = l3.b.g(parcel, l8, Location.CREATOR);
            }
        }
        l3.b.h(parcel, r8);
        return new LocationResult(list);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new LocationResult[i8];
    }
}
