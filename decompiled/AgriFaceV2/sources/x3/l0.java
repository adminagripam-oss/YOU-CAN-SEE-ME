package x3;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class l0 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        i0 i0Var = null;
        IBinder iBinder = null;
        IBinder iBinder2 = null;
        PendingIntent pendingIntent = null;
        IBinder iBinder3 = null;
        String str = null;
        int i8 = 1;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            switch (l3.b.i(l8)) {
                case 1:
                    i8 = l3.b.n(parcel, l8);
                    break;
                case 2:
                    i0Var = (i0) l3.b.c(parcel, l8, i0.CREATOR);
                    break;
                case 3:
                    iBinder = l3.b.m(parcel, l8);
                    break;
                case 4:
                    pendingIntent = (PendingIntent) l3.b.c(parcel, l8, PendingIntent.CREATOR);
                    break;
                case 5:
                    iBinder2 = l3.b.m(parcel, l8);
                    break;
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                    iBinder3 = l3.b.m(parcel, l8);
                    break;
                case 7:
                default:
                    l3.b.q(parcel, l8);
                    break;
                case 8:
                    str = l3.b.d(parcel, l8);
                    break;
            }
        }
        l3.b.h(parcel, r8);
        return new k0(i8, i0Var, iBinder, iBinder2, pendingIntent, iBinder3, str);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new k0[i8];
    }
}
