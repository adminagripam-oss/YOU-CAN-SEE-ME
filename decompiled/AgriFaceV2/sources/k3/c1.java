package k3;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class c1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        s sVar = null;
        int[] iArr = null;
        int[] iArr2 = null;
        boolean z7 = false;
        boolean z8 = false;
        int i8 = 0;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            switch (l3.b.i(l8)) {
                case 1:
                    sVar = (s) l3.b.c(parcel, l8, s.CREATOR);
                    break;
                case 2:
                    z7 = l3.b.j(parcel, l8);
                    break;
                case 3:
                    z8 = l3.b.j(parcel, l8);
                    break;
                case 4:
                    iArr = l3.b.b(parcel, l8);
                    break;
                case 5:
                    i8 = l3.b.n(parcel, l8);
                    break;
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                    iArr2 = l3.b.b(parcel, l8);
                    break;
                default:
                    l3.b.q(parcel, l8);
                    break;
            }
        }
        l3.b.h(parcel, r8);
        return new f(sVar, z7, z8, iArr, i8, iArr2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new f[i8];
    }
}
