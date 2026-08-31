package a4;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class f5 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        int i8 = 0;
        int i9 = 0;
        float f8 = 0.0f;
        float f9 = 0.0f;
        float f10 = 0.0f;
        float f11 = 0.0f;
        float f12 = 0.0f;
        float f13 = 0.0f;
        float f14 = 0.0f;
        float f15 = Float.MAX_VALUE;
        float f16 = Float.MAX_VALUE;
        float f17 = Float.MAX_VALUE;
        mc[] mcVarArr = null;
        c2[] c2VarArr = null;
        float f18 = -1.0f;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            switch (l3.b.i(l8)) {
                case 1:
                    i8 = l3.b.n(parcel, l8);
                    break;
                case 2:
                    i9 = l3.b.n(parcel, l8);
                    break;
                case 3:
                    f8 = l3.b.k(parcel, l8);
                    break;
                case 4:
                    f9 = l3.b.k(parcel, l8);
                    break;
                case 5:
                    f10 = l3.b.k(parcel, l8);
                    break;
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                    f11 = l3.b.k(parcel, l8);
                    break;
                case 7:
                    f15 = l3.b.k(parcel, l8);
                    break;
                case 8:
                    f16 = l3.b.k(parcel, l8);
                    break;
                case 9:
                    mcVarArr = (mc[]) l3.b.f(parcel, l8, mc.CREATOR);
                    break;
                case 10:
                    f12 = l3.b.k(parcel, l8);
                    break;
                case 11:
                    f13 = l3.b.k(parcel, l8);
                    break;
                case 12:
                    f14 = l3.b.k(parcel, l8);
                    break;
                case 13:
                    c2VarArr = (c2[]) l3.b.f(parcel, l8, c2.CREATOR);
                    break;
                case 14:
                    f17 = l3.b.k(parcel, l8);
                    break;
                case 15:
                    f18 = l3.b.k(parcel, l8);
                    break;
                default:
                    l3.b.q(parcel, l8);
                    break;
            }
        }
        l3.b.h(parcel, r8);
        return new e4(i8, i9, f8, f9, f10, f11, f15, f16, f17, mcVarArr, f12, f13, f14, c2VarArr, f18);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new e4[i8];
    }
}
