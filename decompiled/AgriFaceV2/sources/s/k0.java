package s;

import android.hardware.camera2.CameraDevice;
import android.os.Handler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import s.c0;
import y.f1;
/* loaded from: classes.dex */
abstract class k0 implements c0.a {

    /* renamed from: a  reason: collision with root package name */
    final CameraDevice f11465a;

    /* renamed from: b  reason: collision with root package name */
    final Object f11466b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        final Handler f11467a;

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(Handler handler) {
            this.f11467a = handler;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public k0(CameraDevice cameraDevice, Object obj) {
        this.f11465a = (CameraDevice) g1.e.j(cameraDevice);
        this.f11466b = obj;
    }

    private static void b(CameraDevice cameraDevice, List list) {
        String id = cameraDevice.getId();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String c8 = ((t.k) it.next()).c();
            if (c8 != null && !c8.isEmpty()) {
                f1.l("CameraDeviceCompat", "Camera " + id + ": Camera doesn't support physicalCameraId " + c8 + ". Ignoring.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void c(CameraDevice cameraDevice, t.q qVar) {
        g1.e.j(cameraDevice);
        g1.e.j(qVar);
        g1.e.j(qVar.e());
        List c8 = qVar.c();
        if (c8 == null) {
            throw new IllegalArgumentException("Invalid output configurations");
        }
        if (qVar.a() == null) {
            throw new IllegalArgumentException("Invalid executor");
        }
        b(cameraDevice, c8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List d(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((t.k) it.next()).d());
        }
        return arrayList;
    }
}
