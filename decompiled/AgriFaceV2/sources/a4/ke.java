package a4;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
/* loaded from: classes.dex */
public final class ke implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        int i8 = 0;
        Rect rect = null;
        ArrayList arrayList = null;
        ArrayList arrayList2 = null;
        float f8 = 0.0f;
        float f9 = 0.0f;
        float f10 = 0.0f;
        float f11 = 0.0f;
        float f12 = 0.0f;
        float f13 = 0.0f;
        float f14 = 0.0f;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            switch (l3.b.i(l8)) {
                case 1:
                    i8 = l3.b.n(parcel, l8);
                    break;
                case 2:
                    rect = (Rect) l3.b.c(parcel, l8, Rect.CREATOR);
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
                    f12 = l3.b.k(parcel, l8);
                    break;
                case 8:
                    f13 = l3.b.k(parcel, l8);
                    break;
                case 9:
                    f14 = l3.b.k(parcel, l8);
                    break;
                case 10:
                    arrayList = l3.b.g(parcel, l8, qe.CREATOR);
                    break;
                case 11:
                    arrayList2 = l3.b.g(parcel, l8, fe.CREATOR);
                    break;
                default:
                    l3.b.q(parcel, l8);
                    break;
            }
        }
        l3.b.h(parcel, r8);
        return new je(i8, rect, f8, f9, f10, f11, f12, f13, f14, arrayList, arrayList2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new je[i8];
    }
}
