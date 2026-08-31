package r;

import android.hardware.camera2.CameraDevice;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public abstract class j2 {

    /* loaded from: classes.dex */
    private static final class a extends CameraDevice.StateCallback {

        /* renamed from: a  reason: collision with root package name */
        private final List f10538a = new ArrayList();

        a(List list) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                CameraDevice.StateCallback stateCallback = (CameraDevice.StateCallback) it.next();
                if (!(stateCallback instanceof b)) {
                    this.f10538a.add(stateCallback);
                }
            }
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onClosed(CameraDevice cameraDevice) {
            for (CameraDevice.StateCallback stateCallback : this.f10538a) {
                stateCallback.onClosed(cameraDevice);
            }
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onDisconnected(CameraDevice cameraDevice) {
            for (CameraDevice.StateCallback stateCallback : this.f10538a) {
                stateCallback.onDisconnected(cameraDevice);
            }
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onError(CameraDevice cameraDevice, int i8) {
            for (CameraDevice.StateCallback stateCallback : this.f10538a) {
                stateCallback.onError(cameraDevice, i8);
            }
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onOpened(CameraDevice cameraDevice) {
            for (CameraDevice.StateCallback stateCallback : this.f10538a) {
                stateCallback.onOpened(cameraDevice);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class b extends CameraDevice.StateCallback {
        b() {
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onClosed(CameraDevice cameraDevice) {
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onDisconnected(CameraDevice cameraDevice) {
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onError(CameraDevice cameraDevice, int i8) {
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onOpened(CameraDevice cameraDevice) {
        }
    }

    public static CameraDevice.StateCallback a(List list) {
        return list.isEmpty() ? b() : list.size() == 1 ? (CameraDevice.StateCallback) list.get(0) : new a(list);
    }

    public static CameraDevice.StateCallback b() {
        return new b();
    }
}
