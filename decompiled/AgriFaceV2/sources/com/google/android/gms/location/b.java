package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.WorkSource;
import x3.f0;
/* loaded from: classes.dex */
public final class b implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        WorkSource workSource = new WorkSource();
        f0 f0Var = null;
        boolean z7 = false;
        int i8 = 0;
        int i9 = 0;
        boolean z8 = false;
        long j8 = -1;
        float f8 = 0.0f;
        int i10 = Integer.MAX_VALUE;
        long j9 = Long.MAX_VALUE;
        long j10 = Long.MAX_VALUE;
        long j11 = 0;
        long j12 = 600000;
        long j13 = 3600000;
        int i11 = 102;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            switch (l3.b.i(l8)) {
                case 1:
                    i11 = l3.b.n(parcel, l8);
                    break;
                case 2:
                    j13 = l3.b.o(parcel, l8);
                    break;
                case 3:
                    j12 = l3.b.o(parcel, l8);
                    break;
                case 4:
                case 14:
                default:
                    l3.b.q(parcel, l8);
                    break;
                case 5:
                    j9 = l3.b.o(parcel, l8);
                    break;
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                    i10 = l3.b.n(parcel, l8);
                    break;
                case 7:
                    f8 = l3.b.k(parcel, l8);
                    break;
                case 8:
                    j11 = l3.b.o(parcel, l8);
                    break;
                case 9:
                    z7 = l3.b.j(parcel, l8);
                    break;
                case 10:
                    j10 = l3.b.o(parcel, l8);
                    break;
                case 11:
                    j8 = l3.b.o(parcel, l8);
                    break;
                case 12:
                    i8 = l3.b.n(parcel, l8);
                    break;
                case 13:
                    i9 = l3.b.n(parcel, l8);
                    break;
                case 15:
                    z8 = l3.b.j(parcel, l8);
                    break;
                case 16:
                    workSource = (WorkSource) l3.b.c(parcel, l8, WorkSource.CREATOR);
                    break;
                case 17:
                    f0Var = (f0) l3.b.c(parcel, l8, f0.CREATOR);
                    break;
            }
        }
        l3.b.h(parcel, r8);
        return new LocationRequest(i11, j13, j12, j11, j9, j10, i10, f8, z7, j8, i8, i9, z8, workSource, f0Var);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new LocationRequest[i8];
    }
}
