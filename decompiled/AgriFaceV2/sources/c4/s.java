package c4;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class s implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        long j8 = -1;
        long j9 = -1;
        int i8 = 1;
        int i9 = 1;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            int i10 = l3.b.i(l8);
            if (i10 == 1) {
                i8 = l3.b.n(parcel, l8);
            } else if (i10 == 2) {
                i9 = l3.b.n(parcel, l8);
            } else if (i10 == 3) {
                j8 = l3.b.o(parcel, l8);
            } else if (i10 != 4) {
                l3.b.q(parcel, l8);
            } else {
                j9 = l3.b.o(parcel, l8);
            }
        }
        l3.b.h(parcel, r8);
        return new r(i8, i9, j8, j9);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new r[i8];
    }
}
