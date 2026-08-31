package e4;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class c implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        Intent intent = null;
        int i8 = 0;
        int i9 = 0;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            int i10 = l3.b.i(l8);
            if (i10 == 1) {
                i8 = l3.b.n(parcel, l8);
            } else if (i10 == 2) {
                i9 = l3.b.n(parcel, l8);
            } else if (i10 != 3) {
                l3.b.q(parcel, l8);
            } else {
                intent = (Intent) l3.b.c(parcel, l8, Intent.CREATOR);
            }
        }
        l3.b.h(parcel, r8);
        return new b(i8, i9, intent);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new b[i8];
    }
}
