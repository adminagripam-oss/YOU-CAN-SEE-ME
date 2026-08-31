package h3;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class l implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        PendingIntent pendingIntent = null;
        int i8 = 0;
        int i9 = 0;
        String str = null;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            int i10 = l3.b.i(l8);
            if (i10 == 1) {
                i8 = l3.b.n(parcel, l8);
            } else if (i10 == 2) {
                i9 = l3.b.n(parcel, l8);
            } else if (i10 == 3) {
                pendingIntent = (PendingIntent) l3.b.c(parcel, l8, PendingIntent.CREATOR);
            } else if (i10 != 4) {
                l3.b.q(parcel, l8);
            } else {
                str = l3.b.d(parcel, l8);
            }
        }
        l3.b.h(parcel, r8);
        return new a(i8, i9, pendingIntent, str);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new a[i8];
    }
}
