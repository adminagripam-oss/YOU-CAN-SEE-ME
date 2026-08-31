package x3;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class h0 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        IBinder iBinder = null;
        IBinder iBinder2 = null;
        PendingIntent pendingIntent = null;
        String str = null;
        int i8 = 0;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            int i9 = l3.b.i(l8);
            if (i9 == 1) {
                i8 = l3.b.n(parcel, l8);
            } else if (i9 == 2) {
                iBinder = l3.b.m(parcel, l8);
            } else if (i9 == 3) {
                iBinder2 = l3.b.m(parcel, l8);
            } else if (i9 == 4) {
                pendingIntent = (PendingIntent) l3.b.c(parcel, l8, PendingIntent.CREATOR);
            } else if (i9 != 6) {
                l3.b.q(parcel, l8);
            } else {
                str = l3.b.d(parcel, l8);
            }
        }
        l3.b.h(parcel, r8);
        return new g0(i8, iBinder, iBinder2, pendingIntent, str);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new g0[i8];
    }
}
