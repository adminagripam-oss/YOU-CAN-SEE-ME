package w3;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
/* loaded from: classes.dex */
public abstract class a implements IInterface {

    /* renamed from: e  reason: collision with root package name */
    private final IBinder f12469e;

    /* renamed from: f  reason: collision with root package name */
    private final String f12470f;

    /* JADX INFO: Access modifiers changed from: protected */
    public a(IBinder iBinder, String str) {
        this.f12469e = iBinder;
        this.f12470f = str;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f12469e;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Parcel d(int i8, Parcel parcel) {
        Parcel obtain = Parcel.obtain();
        try {
            try {
                this.f12469e.transact(i8, parcel, obtain, 0);
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
    public final Parcel e() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.f12470f);
        return obtain;
    }
}
