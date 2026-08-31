package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class b implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        String str = null;
        h3.a aVar = null;
        int i8 = 0;
        PendingIntent pendingIntent = null;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            int i9 = l3.b.i(l8);
            if (i9 == 1) {
                i8 = l3.b.n(parcel, l8);
            } else if (i9 == 2) {
                str = l3.b.d(parcel, l8);
            } else if (i9 == 3) {
                pendingIntent = (PendingIntent) l3.b.c(parcel, l8, PendingIntent.CREATOR);
            } else if (i9 != 4) {
                l3.b.q(parcel, l8);
            } else {
                aVar = (h3.a) l3.b.c(parcel, l8, h3.a.CREATOR);
            }
        }
        l3.b.h(parcel, r8);
        return new Status(i8, str, pendingIntent, aVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new Status[i8];
    }
}
