package a4;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
/* loaded from: classes.dex */
public final class j9 extends a implements lb {
    /* JADX INFO: Access modifiers changed from: package-private */
    public j9(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.face.internal.client.INativeFaceDetectorCreator");
    }

    @Override // a4.lb
    public final i8 p(t3.a aVar, g6 g6Var) {
        i8 i8Var;
        Parcel d8 = d();
        b1.b(d8, aVar);
        b1.a(d8, g6Var);
        Parcel e8 = e(1, d8);
        IBinder readStrongBinder = e8.readStrongBinder();
        if (readStrongBinder == null) {
            i8Var = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.vision.face.internal.client.INativeFaceDetector");
            i8Var = queryLocalInterface instanceof i8 ? (i8) queryLocalInterface : new i8(readStrongBinder);
        }
        e8.recycle();
        return i8Var;
    }
}
