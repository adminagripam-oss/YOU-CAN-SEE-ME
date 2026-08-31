package e4;

import android.os.Parcel;
import android.os.Parcelable;
import k3.l0;
/* loaded from: classes.dex */
public final class m implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        h3.a aVar = null;
        int i8 = 0;
        l0 l0Var = null;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            int i9 = l3.b.i(l8);
            if (i9 == 1) {
                i8 = l3.b.n(parcel, l8);
            } else if (i9 == 2) {
                aVar = (h3.a) l3.b.c(parcel, l8, h3.a.CREATOR);
            } else if (i9 != 3) {
                l3.b.q(parcel, l8);
            } else {
                l0Var = (l0) l3.b.c(parcel, l8, l0.CREATOR);
            }
        }
        l3.b.h(parcel, r8);
        return new l(i8, aVar, l0Var);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new l[i8];
    }
}
