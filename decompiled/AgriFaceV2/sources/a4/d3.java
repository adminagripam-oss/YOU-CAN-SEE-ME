package a4;

import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class d3 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        PointF[] pointFArr = null;
        int i8 = 0;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            int i9 = l3.b.i(l8);
            if (i9 == 2) {
                pointFArr = (PointF[]) l3.b.f(parcel, l8, PointF.CREATOR);
            } else if (i9 != 3) {
                l3.b.q(parcel, l8);
            } else {
                i8 = l3.b.n(parcel, l8);
            }
        }
        l3.b.h(parcel, r8);
        return new c2(pointFArr, i8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new c2[i8];
    }
}
