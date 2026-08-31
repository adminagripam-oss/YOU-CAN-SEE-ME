package com.google.android.gms.internal.mlkit_vision_face_bundled;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class mi implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        float f8 = 0.0f;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        boolean z7 = false;
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
                    i10 = l3.b.n(parcel, l8);
                    break;
                case 4:
                    i11 = l3.b.n(parcel, l8);
                    break;
                case 5:
                    z7 = l3.b.j(parcel, l8);
                    break;
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                    f8 = l3.b.k(parcel, l8);
                    break;
                default:
                    l3.b.q(parcel, l8);
                    break;
            }
        }
        l3.b.h(parcel, r8);
        return new li(i8, i9, i10, i11, z7, f8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new li[i8];
    }
}
