package a4;

import android.os.IBinder;
import android.os.IInterface;
/* loaded from: classes.dex */
public abstract class oe extends b0 implements pe {
    public static pe d(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.mlkit.vision.face.aidls.IFaceDetectorCreator");
        return queryLocalInterface instanceof pe ? (pe) queryLocalInterface : new me(iBinder);
    }
}
