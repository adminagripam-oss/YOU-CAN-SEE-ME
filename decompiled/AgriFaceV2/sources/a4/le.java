package a4;

import android.os.IBinder;
import android.os.Parcel;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class le extends a {
    /* JADX INFO: Access modifiers changed from: package-private */
    public le(IBinder iBinder) {
        super(iBinder, "com.google.mlkit.vision.face.aidls.IFaceDetector");
    }

    public final void P() {
        f(2, d());
    }

    public final void c() {
        f(1, d());
    }

    public final List d0(t3.a aVar, de deVar) {
        Parcel d8 = d();
        b1.b(d8, aVar);
        b1.a(d8, deVar);
        Parcel e8 = e(3, d8);
        ArrayList createTypedArrayList = e8.createTypedArrayList(je.CREATOR);
        e8.recycle();
        return createTypedArrayList;
    }
}
