package c4;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class p implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        boolean z7 = false;
        boolean z8 = false;
        boolean z9 = false;
        boolean z10 = false;
        boolean z11 = false;
        boolean z12 = false;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            switch (l3.b.i(l8)) {
                case 1:
                    z7 = l3.b.j(parcel, l8);
                    break;
                case 2:
                    z8 = l3.b.j(parcel, l8);
                    break;
                case 3:
                    z9 = l3.b.j(parcel, l8);
                    break;
                case 4:
                    z10 = l3.b.j(parcel, l8);
                    break;
                case 5:
                    z11 = l3.b.j(parcel, l8);
                    break;
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                    z12 = l3.b.j(parcel, l8);
                    break;
                default:
                    l3.b.q(parcel, l8);
                    break;
            }
        }
        l3.b.h(parcel, r8);
        return new j(z7, z8, z9, z10, z11, z12);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new j[i8];
    }
}
