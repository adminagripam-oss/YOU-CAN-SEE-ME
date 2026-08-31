package k3;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class b1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        Bundle bundle = null;
        f fVar = null;
        int i8 = 0;
        h3.c[] cVarArr = null;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            int i9 = l3.b.i(l8);
            if (i9 == 1) {
                bundle = l3.b.a(parcel, l8);
            } else if (i9 == 2) {
                cVarArr = (h3.c[]) l3.b.f(parcel, l8, h3.c.CREATOR);
            } else if (i9 == 3) {
                i8 = l3.b.n(parcel, l8);
            } else if (i9 != 4) {
                l3.b.q(parcel, l8);
            } else {
                fVar = (f) l3.b.c(parcel, l8, f.CREATOR);
            }
        }
        l3.b.h(parcel, r8);
        return new a1(bundle, cVarArr, i8, fVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new a1[i8];
    }
}
