package e4;

import android.os.Parcel;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Status;
/* loaded from: classes.dex */
public abstract class e extends v3.b implements f {
    public e() {
        super("com.google.android.gms.signin.internal.ISignInCallbacks");
    }

    @Override // v3.b
    protected final boolean d0(int i8, Parcel parcel, Parcel parcel2, int i9) {
        switch (i8) {
            case 3:
                h3.a aVar = (h3.a) v3.c.a(parcel, h3.a.CREATOR);
                b bVar = (b) v3.c.a(parcel, b.CREATOR);
                v3.c.b(parcel);
                break;
            case 4:
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                Status status = (Status) v3.c.a(parcel, Status.CREATOR);
                v3.c.b(parcel);
                break;
            case 5:
            default:
                return false;
            case 7:
                Status status2 = (Status) v3.c.a(parcel, Status.CREATOR);
                GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) v3.c.a(parcel, GoogleSignInAccount.CREATOR);
                v3.c.b(parcel);
                break;
            case 8:
                v3.c.b(parcel);
                g((l) v3.c.a(parcel, l.CREATOR));
                break;
            case 9:
                h hVar = (h) v3.c.a(parcel, h.CREATOR);
                v3.c.b(parcel);
                break;
        }
        parcel2.writeNoException();
        return true;
    }
}
