package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import c4.r;
/* loaded from: classes.dex */
public final class a implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        boolean z7 = false;
        r[] rVarArr = null;
        long j8 = 0;
        int i8 = 1;
        int i9 = 1;
        int i10 = 1000;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            switch (l3.b.i(l8)) {
                case 1:
                    i8 = l3.b.n(parcel, l8);
                    break;
                case 2:
                    i9 = l3.b.n(parcel, l8);
                    break;
                case 3:
                    j8 = l3.b.o(parcel, l8);
                    break;
                case 4:
                    i10 = l3.b.n(parcel, l8);
                    break;
                case 5:
                    rVarArr = (r[]) l3.b.f(parcel, l8, r.CREATOR);
                    break;
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                    z7 = l3.b.j(parcel, l8);
                    break;
                default:
                    l3.b.q(parcel, l8);
                    break;
            }
        }
        l3.b.h(parcel, r8);
        return new LocationAvailability(i10, i8, i9, j8, rVarArr, z7);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new LocationAvailability[i8];
    }
}
