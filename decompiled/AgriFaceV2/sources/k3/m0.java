package k3;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class m0 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        int i8 = 0;
        boolean z7 = false;
        boolean z8 = false;
        IBinder iBinder = null;
        h3.a aVar = null;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            int i9 = l3.b.i(l8);
            if (i9 == 1) {
                i8 = l3.b.n(parcel, l8);
            } else if (i9 == 2) {
                iBinder = l3.b.m(parcel, l8);
            } else if (i9 == 3) {
                aVar = (h3.a) l3.b.c(parcel, l8, h3.a.CREATOR);
            } else if (i9 == 4) {
                z7 = l3.b.j(parcel, l8);
            } else if (i9 != 5) {
                l3.b.q(parcel, l8);
            } else {
                z8 = l3.b.j(parcel, l8);
            }
        }
        l3.b.h(parcel, r8);
        return new l0(i8, iBinder, aVar, z7, z8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new l0[i8];
    }
}
