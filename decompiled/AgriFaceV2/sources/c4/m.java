package c4;

import android.os.Parcel;
import android.os.Parcelable;
import x3.f0;
/* loaded from: classes.dex */
public final class m implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        f0 f0Var = null;
        int i8 = 0;
        boolean z7 = false;
        long j8 = Long.MAX_VALUE;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            int i9 = l3.b.i(l8);
            if (i9 == 1) {
                j8 = l3.b.o(parcel, l8);
            } else if (i9 == 2) {
                i8 = l3.b.n(parcel, l8);
            } else if (i9 == 3) {
                z7 = l3.b.j(parcel, l8);
            } else if (i9 != 5) {
                l3.b.q(parcel, l8);
            } else {
                f0Var = (f0) l3.b.c(parcel, l8, f0.CREATOR);
            }
        }
        l3.b.h(parcel, r8);
        return new d(j8, i8, z7, f0Var);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new d[i8];
    }
}
