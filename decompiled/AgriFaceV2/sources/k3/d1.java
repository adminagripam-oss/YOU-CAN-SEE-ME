package k3;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
/* loaded from: classes.dex */
public final class d1 implements Parcelable.Creator {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(g gVar, Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, gVar.f8396a);
        l3.c.g(parcel, 2, gVar.f8397b);
        l3.c.g(parcel, 3, gVar.f8398c);
        l3.c.k(parcel, 4, gVar.f8399d, false);
        l3.c.f(parcel, 5, gVar.f8400e, false);
        l3.c.m(parcel, 6, gVar.f8401f, i8, false);
        l3.c.d(parcel, 7, gVar.f8402g, false);
        l3.c.j(parcel, 8, gVar.f8403h, i8, false);
        l3.c.m(parcel, 10, gVar.f8404i, i8, false);
        l3.c.m(parcel, 11, gVar.f8405j, i8, false);
        l3.c.c(parcel, 12, gVar.f8406k);
        l3.c.g(parcel, 13, gVar.f8407l);
        l3.c.c(parcel, 14, gVar.f8408m);
        l3.c.k(parcel, 15, gVar.a(), false);
        l3.c.b(parcel, a8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        Scope[] scopeArr = g.f8394o;
        Bundle bundle = new Bundle();
        h3.c[] cVarArr = g.f8395p;
        h3.c[] cVarArr2 = cVarArr;
        String str = null;
        IBinder iBinder = null;
        Account account = null;
        String str2 = null;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        boolean z7 = false;
        int i11 = 0;
        boolean z8 = false;
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
                    str = l3.b.d(parcel, l8);
                    break;
                case 5:
                    iBinder = l3.b.m(parcel, l8);
                    break;
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                    scopeArr = (Scope[]) l3.b.f(parcel, l8, Scope.CREATOR);
                    break;
                case 7:
                    bundle = l3.b.a(parcel, l8);
                    break;
                case 8:
                    account = (Account) l3.b.c(parcel, l8, Account.CREATOR);
                    break;
                case 9:
                default:
                    l3.b.q(parcel, l8);
                    break;
                case 10:
                    cVarArr = (h3.c[]) l3.b.f(parcel, l8, h3.c.CREATOR);
                    break;
                case 11:
                    cVarArr2 = (h3.c[]) l3.b.f(parcel, l8, h3.c.CREATOR);
                    break;
                case 12:
                    z7 = l3.b.j(parcel, l8);
                    break;
                case 13:
                    i11 = l3.b.n(parcel, l8);
                    break;
                case 14:
                    z8 = l3.b.j(parcel, l8);
                    break;
                case 15:
                    str2 = l3.b.d(parcel, l8);
                    break;
            }
        }
        l3.b.h(parcel, r8);
        return new g(i8, i9, i10, str, iBinder, scopeArr, bundle, account, cVarArr, cVarArr2, z7, i11, z8, str2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new g[i8];
    }
}
