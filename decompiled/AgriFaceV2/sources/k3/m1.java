package k3;

import android.accounts.Account;
import android.os.IBinder;
import android.os.Parcel;
/* loaded from: classes.dex */
public final class m1 extends w3.a implements k {
    /* JADX INFO: Access modifiers changed from: package-private */
    public m1(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.IAccountAccessor");
    }

    @Override // k3.k
    public final Account zzb() {
        Parcel d8 = d(2, e());
        Account account = (Account) w3.c.a(d8, Account.CREATOR);
        d8.recycle();
        return account;
    }
}
