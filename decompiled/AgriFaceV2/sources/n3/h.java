package n3;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class h implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        boolean z7 = false;
        int i8 = 0;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            int i9 = l3.b.i(l8);
            if (i9 == 1) {
                z7 = l3.b.j(parcel, l8);
            } else if (i9 != 2) {
                l3.b.q(parcel, l8);
            } else {
                i8 = l3.b.n(parcel, l8);
            }
        }
        l3.b.h(parcel, r8);
        return new b(z7, i8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new b[i8];
    }
}
