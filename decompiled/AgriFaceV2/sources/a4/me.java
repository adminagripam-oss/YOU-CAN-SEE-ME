package a4;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
/* loaded from: classes.dex */
public final class me extends a implements pe {
    /* JADX INFO: Access modifiers changed from: package-private */
    public me(IBinder iBinder) {
        super(iBinder, "com.google.mlkit.vision.face.aidls.IFaceDetectorCreator");
    }

    @Override // a4.pe
    public final le N(t3.a aVar, he heVar) {
        le leVar;
        Parcel d8 = d();
        b1.b(d8, aVar);
        b1.a(d8, heVar);
        Parcel e8 = e(1, d8);
        IBinder readStrongBinder = e8.readStrongBinder();
        if (readStrongBinder == null) {
            leVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.mlkit.vision.face.aidls.IFaceDetector");
            leVar = queryLocalInterface instanceof le ? (le) queryLocalInterface : new le(readStrongBinder);
        }
        e8.recycle();
        return leVar;
    }
}
