package k3;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
/* loaded from: classes.dex */
public final class k0 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int r8 = l3.b.r(parcel);
        Account account = null;
        int i8 = 0;
        int i9 = 0;
        GoogleSignInAccount googleSignInAccount = null;
        while (parcel.dataPosition() < r8) {
            int l8 = l3.b.l(parcel);
            int i10 = l3.b.i(l8);
            if (i10 == 1) {
                i8 = l3.b.n(parcel, l8);
            } else if (i10 == 2) {
                account = (Account) l3.b.c(parcel, l8, Account.CREATOR);
            } else if (i10 == 3) {
                i9 = l3.b.n(parcel, l8);
            } else if (i10 != 4) {
                l3.b.q(parcel, l8);
            } else {
                googleSignInAccount = (GoogleSignInAccount) l3.b.c(parcel, l8, GoogleSignInAccount.CREATOR);
            }
        }
        l3.b.h(parcel, r8);
        return new j0(i8, account, i9, googleSignInAccount);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i8) {
        return new j0[i8];
    }
}
