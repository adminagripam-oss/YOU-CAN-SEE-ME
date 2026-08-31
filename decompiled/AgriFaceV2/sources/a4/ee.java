package a4;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class ee implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        long j8 = 0;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            int i12 = l3.b.i(l8);
            if (i12 == 1) {
                i8 = l3.b.n(parcel, l8);
            } else if (i12 == 2) {
                i9 = l3.b.n(parcel, l8);
            } else if (i12 == 3) {
                i10 = l3.b.n(parcel, l8);
            } else if (i12 == 4) {
                i11 = l3.b.n(parcel, l8);
            } else if (i12 != 5) {
                l3.b.q(parcel, l8);
            } else {
                j8 = l3.b.o(parcel, l8);
            }
        }
        l3.b.h(parcel, r8);
        return new de(i8, i9, i10, i11, j8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new de[i8];
    }
}
