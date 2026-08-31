package x3;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
/* loaded from: classes.dex */
public final class y0 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        ArrayList arrayList = null;
        f0 f0Var = null;
        int i8 = 0;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            int i9 = l3.b.i(l8);
            if (i9 == 1) {
                i8 = l3.b.n(parcel, l8);
            } else if (i9 == 3) {
                str = l3.b.d(parcel, l8);
            } else if (i9 == 4) {
                str2 = l3.b.d(parcel, l8);
            } else if (i9 == 6) {
                str3 = l3.b.d(parcel, l8);
            } else if (i9 == 7) {
                f0Var = (f0) l3.b.c(parcel, l8, f0.CREATOR);
            } else if (i9 != 8) {
                l3.b.q(parcel, l8);
            } else {
                arrayList = l3.b.g(parcel, l8, h3.c.CREATOR);
            }
        }
        l3.b.h(parcel, r8);
        return new f0(i8, str, str2, str3, arrayList, f0Var);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new f0[i8];
    }
}
