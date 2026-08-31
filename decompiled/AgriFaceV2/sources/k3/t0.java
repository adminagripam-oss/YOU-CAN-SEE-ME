package k3;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class t0 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        int i8 = 0;
        boolean z7 = false;
        boolean z8 = false;
        int i9 = 0;
        int i10 = 0;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            int i11 = l3.b.i(l8);
            if (i11 == 1) {
                i8 = l3.b.n(parcel, l8);
            } else if (i11 == 2) {
                z7 = l3.b.j(parcel, l8);
            } else if (i11 == 3) {
                z8 = l3.b.j(parcel, l8);
            } else if (i11 == 4) {
                i9 = l3.b.n(parcel, l8);
            } else if (i11 != 5) {
                l3.b.q(parcel, l8);
            } else {
                i10 = l3.b.n(parcel, l8);
            }
        }
        l3.b.h(parcel, r8);
        return new s(i8, z7, z8, i9, i10);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new s[i8];
    }
}
