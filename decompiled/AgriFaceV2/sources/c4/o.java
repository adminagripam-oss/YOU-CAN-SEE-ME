package c4;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
/* loaded from: classes.dex */
public final class o implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        Status status = null;
        j jVar = null;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            int i8 = l3.b.i(l8);
            if (i8 == 1) {
                status = (Status) l3.b.c(parcel, l8, Status.CREATOR);
            } else if (i8 != 2) {
                l3.b.q(parcel, l8);
            } else {
                jVar = (j) l3.b.c(parcel, l8, j.CREATOR);
            }
        }
        l3.b.h(parcel, r8);
        return new i(status, jVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new i[i8];
    }
}
