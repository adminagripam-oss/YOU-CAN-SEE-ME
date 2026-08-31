package n3;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class i implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        PendingIntent pendingIntent = null;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            if (l3.b.i(l8) != 1) {
                l3.b.q(parcel, l8);
            } else {
                pendingIntent = (PendingIntent) l3.b.c(parcel, l8, PendingIntent.CREATOR);
            }
        }
        l3.b.h(parcel, r8);
        return new e(pendingIntent);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new e[i8];
    }
}
