package x3;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
/* loaded from: classes.dex */
public final class b1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        Status status = null;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            if (l3.b.i(l8) != 1) {
                l3.b.q(parcel, l8);
            } else {
                status = (Status) l3.b.c(parcel, l8, Status.CREATOR);
            }
        }
        l3.b.h(parcel, r8);
        return new a1(status);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new a1[i8];
    }
}
