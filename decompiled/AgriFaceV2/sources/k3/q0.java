package k3;

import android.os.IBinder;
import android.os.Parcel;
/* loaded from: classes.dex */
final class q0 implements m {

    /* renamed from: e  reason: collision with root package name */
    private final IBinder f8465e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public q0(IBinder iBinder) {
        this.f8465e = iBinder;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f8465e;
    }

    @Override // k3.m
    public final void j(l lVar, g gVar) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
            obtain.writeStrongBinder(lVar != null ? lVar.asBinder() : null);
            if (gVar != null) {
                obtain.writeInt(1);
                d1.a(gVar, obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            this.f8465e.transact(46, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
