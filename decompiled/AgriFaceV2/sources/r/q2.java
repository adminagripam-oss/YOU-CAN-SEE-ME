package r;

import android.hardware.camera2.CameraCaptureSession;
import b0.o;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
abstract class q2 {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static CameraCaptureSession.CaptureCallback a(b0.n nVar) {
        if (nVar == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        b(nVar, arrayList);
        return arrayList.size() == 1 ? (CameraCaptureSession.CaptureCallback) arrayList.get(0) : s0.a(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(b0.n nVar, List list) {
        if (nVar instanceof o.a) {
            for (b0.n nVar2 : ((o.a) nVar).e()) {
                b(nVar2, list);
            }
        } else if (nVar instanceof p2) {
            list.add(((p2) nVar).f());
        } else {
            list.add(new o2(nVar));
        }
    }
}
