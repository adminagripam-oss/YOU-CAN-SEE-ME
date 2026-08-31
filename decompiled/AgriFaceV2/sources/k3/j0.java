package k3;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
/* loaded from: classes.dex */
public final class j0 extends l3.a {
    public static final Parcelable.Creator<j0> CREATOR = new k0();

    /* renamed from: a  reason: collision with root package name */
    final int f8428a;

    /* renamed from: b  reason: collision with root package name */
    private final Account f8429b;

    /* renamed from: c  reason: collision with root package name */
    private final int f8430c;

    /* renamed from: d  reason: collision with root package name */
    private final GoogleSignInAccount f8431d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j0(int i8, Account account, int i9, GoogleSignInAccount googleSignInAccount) {
        this.f8428a = i8;
        this.f8429b = account;
        this.f8430c = i9;
        this.f8431d = googleSignInAccount;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int i9 = this.f8428a;
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, i9);
        l3.c.j(parcel, 2, this.f8429b, i8, false);
        l3.c.g(parcel, 3, this.f8430c);
        l3.c.j(parcel, 4, this.f8431d, i8, false);
        l3.c.b(parcel, a8);
    }

    public j0(Account account, int i8, GoogleSignInAccount googleSignInAccount) {
        this(2, account, i8, googleSignInAccount);
    }
}
