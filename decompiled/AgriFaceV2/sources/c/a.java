package c;

import android.app.Notification;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public interface a extends IInterface {

    /* renamed from: d  reason: collision with root package name */
    public static final String f4690d = "android$support$v4$app$INotificationSideChannel".replace('$', '.');

    /* renamed from: c.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static abstract class AbstractBinderC0061a extends Binder implements a {

        /* renamed from: c.a$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        private static class C0062a implements a {

            /* renamed from: e  reason: collision with root package name */
            private IBinder f4691e;

            C0062a(IBinder iBinder) {
                this.f4691e = iBinder;
            }

            @Override // c.a
            public void V(String str, int i8, String str2, Notification notification) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(a.f4690d);
                    obtain.writeString(str);
                    obtain.writeInt(i8);
                    obtain.writeString(str2);
                    b.b(obtain, notification, 0);
                    this.f4691e.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f4691e;
            }
        }

        public static a d(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(a.f4690d);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof a)) ? new C0062a(iBinder) : (a) queryLocalInterface;
        }
    }

    /* loaded from: classes.dex */
    public static class b {
        /* JADX INFO: Access modifiers changed from: private */
        public static void b(Parcel parcel, Parcelable parcelable, int i8) {
            if (parcelable == null) {
                parcel.writeInt(0);
                return;
            }
            parcel.writeInt(1);
            parcelable.writeToParcel(parcel, i8);
        }
    }

    void V(String str, int i8, String str2, Notification notification);
}
