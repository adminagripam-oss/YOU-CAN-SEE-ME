package x3;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
/* loaded from: classes.dex */
public abstract class a implements IInterface {

    /* renamed from: e  reason: collision with root package name */
    private final IBinder f12649e;

    /* renamed from: f  reason: collision with root package name */
    private final String f12650f;

    /* JADX INFO: Access modifiers changed from: protected */
    public a(IBinder iBinder, String str) {
        this.f12649e = iBinder;
        this.f12650f = str;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f12649e;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Parcel d() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.f12650f);
        return obtain;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Parcel e(int i8, Parcel parcel) {
        Parcel obtain = Parcel.obtain();
        try {
            try {
                this.f12649e.transact(i8, parcel, obtain, 0);
                obtain.readException();
                return obtain;
            } catch (RuntimeException e8) {
                obtain.recycle();
                throw e8;
            }
        } finally {
            parcel.recycle();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void f(int i8, Parcel parcel) {
        Parcel obtain = Parcel.obtain();
        try {
            this.f12649e.transact(i8, parcel, obtain, 0);
            obtain.readException();
        } finally {
            parcel.recycle();
            obtain.recycle();
        }
    }
}
