package e4;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
/* loaded from: classes.dex */
public final class i implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        ArrayList arrayList = null;
        String str = null;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            int i8 = l3.b.i(l8);
            if (i8 == 1) {
                arrayList = l3.b.e(parcel, l8);
            } else if (i8 != 2) {
                l3.b.q(parcel, l8);
            } else {
                str = l3.b.d(parcel, l8);
            }
        }
        l3.b.h(parcel, r8);
        return new h(arrayList, str);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new h[i8];
    }
}
