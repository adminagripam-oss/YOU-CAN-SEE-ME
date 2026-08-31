package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.os.Parcelable;
/* loaded from: classes.dex */
public class IconCompatParcelizer {
    public static IconCompat read(androidx.versionedparcelable.a aVar) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.f2978a = aVar.p(iconCompat.f2978a, 1);
        iconCompat.f2980c = aVar.j(iconCompat.f2980c, 2);
        iconCompat.f2981d = aVar.r(iconCompat.f2981d, 3);
        iconCompat.f2982e = aVar.p(iconCompat.f2982e, 4);
        iconCompat.f2983f = aVar.p(iconCompat.f2983f, 5);
        iconCompat.f2984g = (ColorStateList) aVar.r(iconCompat.f2984g, 6);
        iconCompat.f2986i = aVar.t(iconCompat.f2986i, 7);
        iconCompat.f2987j = aVar.t(iconCompat.f2987j, 8);
        iconCompat.g();
        return iconCompat;
    }

    public static void write(IconCompat iconCompat, androidx.versionedparcelable.a aVar) {
        aVar.x(true, true);
        iconCompat.h(aVar.f());
        int i8 = iconCompat.f2978a;
        if (-1 != i8) {
            aVar.F(i8, 1);
        }
        byte[] bArr = iconCompat.f2980c;
        if (bArr != null) {
            aVar.B(bArr, 2);
        }
        Parcelable parcelable = iconCompat.f2981d;
        if (parcelable != null) {
            aVar.H(parcelable, 3);
        }
        int i9 = iconCompat.f2982e;
        if (i9 != 0) {
            aVar.F(i9, 4);
        }
        int i10 = iconCompat.f2983f;
        if (i10 != 0) {
            aVar.F(i10, 5);
        }
        ColorStateList colorStateList = iconCompat.f2984g;
        if (colorStateList != null) {
            aVar.H(colorStateList, 6);
        }
        String str = iconCompat.f2986i;
        if (str != null) {
            aVar.J(str, 7);
        }
        String str2 = iconCompat.f2987j;
        if (str2 != null) {
            aVar.J(str2, 8);
        }
    }
}
