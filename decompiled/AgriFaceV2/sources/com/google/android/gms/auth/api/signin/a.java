package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import java.util.ArrayList;
import l3.b;
/* loaded from: classes.dex */
public final class a implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = b.r(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        Uri uri = null;
        String str5 = null;
        String str6 = null;
        ArrayList arrayList = null;
        String str7 = null;
        String str8 = null;
        long j8 = 0;
        int i8 = 0;
        while (parcel.dataPosition() < r8) {
            int l8 = b.l(parcel);
            switch (b.i(l8)) {
                case 1:
                    i8 = b.n(parcel, l8);
                    break;
                case 2:
                    str = b.d(parcel, l8);
                    break;
                case 3:
                    str2 = b.d(parcel, l8);
                    break;
                case 4:
                    str3 = b.d(parcel, l8);
                    break;
                case 5:
                    str4 = b.d(parcel, l8);
                    break;
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                    uri = (Uri) b.c(parcel, l8, Uri.CREATOR);
                    break;
                case 7:
                    str5 = b.d(parcel, l8);
                    break;
                case 8:
                    j8 = b.o(parcel, l8);
                    break;
                case 9:
                    str6 = b.d(parcel, l8);
                    break;
                case 10:
                    arrayList = b.g(parcel, l8, Scope.CREATOR);
                    break;
                case 11:
                    str7 = b.d(parcel, l8);
                    break;
                case 12:
                    str8 = b.d(parcel, l8);
                    break;
                default:
                    b.q(parcel, l8);
                    break;
            }
        }
        b.h(parcel, r8);
        return new GoogleSignInAccount(i8, str, str2, str3, str4, uri, str5, j8, str6, arrayList, str7, str8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new GoogleSignInAccount[i8];
    }
}
