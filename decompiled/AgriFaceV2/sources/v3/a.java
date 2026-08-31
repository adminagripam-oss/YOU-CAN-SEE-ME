package v3;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
/* loaded from: classes.dex */
public abstract class a implements IInterface {

    /* renamed from: e  reason: collision with root package name */
    private final IBinder f12351e;

    /* renamed from: f  reason: collision with root package name */
    private final String f12352f;

    /* JADX INFO: Access modifiers changed from: protected */
    public a(IBinder iBinder, String str) {
        this.f12351e = iBinder;
        this.f12352f = str;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f12351e;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Parcel d() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.f12352f);
        return obtain;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void e(int i8, Parcel parcel) {
        Parcel obtain = Parcel.obtain();
        try {
            this.f12351e.transact(i8, parcel, obtain, 0);
            obtain.readException();
        } finally {
            parcel.recycle();
            obtain.recycle();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void f(int i8, Parcel parcel) {
        try {
            this.f12351e.transact(1, parcel, null, 1);
        } finally {
            parcel.recycle();
        }
    }
}
