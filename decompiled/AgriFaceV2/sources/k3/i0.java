package k3;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class i0 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        int i8 = -1;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        String str = null;
        String str2 = null;
        long j8 = 0;
        long j9 = 0;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            switch (l3.b.i(l8)) {
                case 1:
                    i9 = l3.b.n(parcel, l8);
                    break;
                case 2:
                    i10 = l3.b.n(parcel, l8);
                    break;
                case 3:
                    i11 = l3.b.n(parcel, l8);
                    break;
                case 4:
                    j8 = l3.b.o(parcel, l8);
                    break;
                case 5:
                    j9 = l3.b.o(parcel, l8);
                    break;
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                    str = l3.b.d(parcel, l8);
                    break;
                case 7:
                    str2 = l3.b.d(parcel, l8);
                    break;
                case 8:
                    i12 = l3.b.n(parcel, l8);
                    break;
                case 9:
                    i8 = l3.b.n(parcel, l8);
                    break;
                default:
                    l3.b.q(parcel, l8);
                    break;
            }
        }
        l3.b.h(parcel, r8);
        return new o(i9, i10, i11, j8, j9, str, str2, i12, i8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new o[i8];
    }
}
