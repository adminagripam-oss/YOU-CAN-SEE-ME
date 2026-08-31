package h3;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class m implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        long j8 = -1;
        int i8 = 0;
        String str = null;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            int i9 = l3.b.i(l8);
            if (i9 == 1) {
                str = l3.b.d(parcel, l8);
            } else if (i9 == 2) {
                i8 = l3.b.n(parcel, l8);
            } else if (i9 != 3) {
                l3.b.q(parcel, l8);
            } else {
                j8 = l3.b.o(parcel, l8);
            }
        }
        l3.b.h(parcel, r8);
        return new c(str, i8, j8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new c[i8];
    }
}
