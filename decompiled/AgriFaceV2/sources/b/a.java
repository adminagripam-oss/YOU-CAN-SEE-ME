package b;

import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public interface a extends IInterface {

    /* renamed from: a  reason: collision with root package name */
    public static final String f4122a = "android$support$customtabs$ICustomTabsCallback".replace('$', '.');

    /* renamed from: b.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static abstract class AbstractBinderC0056a extends Binder implements a {
        public AbstractBinderC0056a() {
            attachInterface(this, a.f4122a);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i8, Parcel parcel, Parcel parcel2, int i9) {
            String str = a.f4122a;
            if (i8 >= 1 && i8 <= 16777215) {
                parcel.enforceInterface(str);
            }
            if (i8 == 1598968902) {
                parcel2.writeString(str);
                return true;
            }
            switch (i8) {
                case 2:
                    G(parcel.readInt(), (Bundle) b.c(parcel, Bundle.CREATOR));
                    break;
                case 3:
                    v(parcel.readString(), (Bundle) b.c(parcel, Bundle.CREATOR));
                    break;
                case 4:
                    T((Bundle) b.c(parcel, Bundle.CREATOR));
                    parcel2.writeNoException();
                    break;
                case 5:
                    R(parcel.readString(), (Bundle) b.c(parcel, Bundle.CREATOR));
                    parcel2.writeNoException();
                    break;
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                    W(parcel.readInt(), (Uri) b.c(parcel, Uri.CREATOR), parcel.readInt() != 0, (Bundle) b.c(parcel, Bundle.CREATOR));
                    break;
                case 7:
                    Bundle J = J(parcel.readString(), (Bundle) b.c(parcel, Bundle.CREATOR));
                    parcel2.writeNoException();
                    b.d(parcel2, J, 1);
                    break;
                case 8:
                    i(parcel.readInt(), parcel.readInt(), (Bundle) b.c(parcel, Bundle.CREATOR));
                    break;
                case 9:
                    C((Bundle) b.c(parcel, Bundle.CREATOR));
                    break;
                case 10:
                    z(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), (Bundle) b.c(parcel, Bundle.CREATOR));
                    break;
                case 11:
                    X((Bundle) b.c(parcel, Bundle.CREATOR));
                    break;
                case 12:
                    a0((Bundle) b.c(parcel, Bundle.CREATOR));
                    break;
                default:
                    return super.onTransact(i8, parcel, parcel2, i9);
            }
            return true;
        }
    }

    /* loaded from: classes.dex */
    public static class b {
        /* JADX INFO: Access modifiers changed from: private */
        public static Object c(Parcel parcel, Parcelable.Creator creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void d(Parcel parcel, Parcelable parcelable, int i8) {
            if (parcelable == null) {
                parcel.writeInt(0);
                return;
            }
            parcel.writeInt(1);
            parcelable.writeToParcel(parcel, i8);
        }
    }

    void C(Bundle bundle);

    void G(int i8, Bundle bundle);

    Bundle J(String str, Bundle bundle);

    void R(String str, Bundle bundle);

    void T(Bundle bundle);

    void W(int i8, Uri uri, boolean z7, Bundle bundle);

    void X(Bundle bundle);

    void a0(Bundle bundle);

    void i(int i8, int i9, Bundle bundle);

    void v(String str, Bundle bundle);

    void z(int i8, int i9, int i10, int i11, int i12, Bundle bundle);
}
