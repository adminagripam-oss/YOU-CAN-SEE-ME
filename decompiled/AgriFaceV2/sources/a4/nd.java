package a4;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class nd implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        int i8 = 0;
        float f8 = 0.0f;
        float f9 = 0.0f;
        int i9 = 0;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            int i10 = l3.b.i(l8);
            if (i10 == 1) {
                i8 = l3.b.n(parcel, l8);
            } else if (i10 == 2) {
                f8 = l3.b.k(parcel, l8);
            } else if (i10 == 3) {
                f9 = l3.b.k(parcel, l8);
            } else if (i10 != 4) {
                l3.b.q(parcel, l8);
            } else {
                i9 = l3.b.n(parcel, l8);
            }
        }
        l3.b.h(parcel, r8);
        return new mc(i8, f8, f9, i9);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new mc[i8];
    }
}
