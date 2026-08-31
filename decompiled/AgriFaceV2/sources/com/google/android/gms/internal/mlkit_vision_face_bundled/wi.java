package com.google.android.gms.internal.mlkit_vision_face_bundled;

import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class wi implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        PointF pointF = null;
        int i8 = 0;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            int i9 = l3.b.i(l8);
            if (i9 == 1) {
                i8 = l3.b.n(parcel, l8);
            } else if (i9 != 2) {
                l3.b.q(parcel, l8);
            } else {
                pointF = (PointF) l3.b.c(parcel, l8, PointF.CREATOR);
            }
        }
        l3.b.h(parcel, r8);
        return new vi(i8, pointF);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new vi[i8];
    }
}
