package a4;

import android.os.IBinder;
import android.os.IInterface;
/* loaded from: classes.dex */
public abstract class ka extends b0 implements lb {
    public static lb d(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.vision.face.internal.client.INativeFaceDetectorCreator");
        return queryLocalInterface instanceof lb ? (lb) queryLocalInterface : new j9(iBinder);
    }
}
