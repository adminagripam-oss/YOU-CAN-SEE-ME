package androidx.window.layout.adapter.sidecar;

import android.os.IBinder;
import androidx.window.sidecar.SidecarDeviceState;
import androidx.window.sidecar.SidecarInterface;
import androidx.window.sidecar.SidecarWindowLayoutInfo;
import java.util.Map;
import java.util.WeakHashMap;
/* loaded from: classes.dex */
public class DistinctElementSidecarCallback implements SidecarInterface.SidecarCallback {

    /* renamed from: b  reason: collision with root package name */
    private SidecarDeviceState f4096b;

    /* renamed from: d  reason: collision with root package name */
    private final k2.a f4098d;

    /* renamed from: e  reason: collision with root package name */
    private final SidecarInterface.SidecarCallback f4099e;

    /* renamed from: a  reason: collision with root package name */
    private final Object f4095a = new Object();

    /* renamed from: c  reason: collision with root package name */
    private final Map f4097c = new WeakHashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public DistinctElementSidecarCallback(k2.a aVar, SidecarInterface.SidecarCallback sidecarCallback) {
        this.f4098d = aVar;
        this.f4099e = sidecarCallback;
    }

    public void onDeviceStateChanged(SidecarDeviceState sidecarDeviceState) {
        if (sidecarDeviceState == null) {
            return;
        }
        synchronized (this.f4095a) {
            if (this.f4098d.a(this.f4096b, sidecarDeviceState)) {
                return;
            }
            this.f4096b = sidecarDeviceState;
            this.f4099e.onDeviceStateChanged(sidecarDeviceState);
        }
    }

    public void onWindowLayoutChanged(IBinder iBinder, SidecarWindowLayoutInfo sidecarWindowLayoutInfo) {
        synchronized (this.f4095a) {
            if (this.f4098d.d((SidecarWindowLayoutInfo) this.f4097c.get(iBinder), sidecarWindowLayoutInfo)) {
                return;
            }
            this.f4097c.put(iBinder, sidecarWindowLayoutInfo);
            this.f4099e.onWindowLayoutChanged(iBinder, sidecarWindowLayoutInfo);
        }
    }
}
