package s;

import android.hardware.camera2.CameraCharacteristics;
import s.b0;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class z implements b0.a {

    /* renamed from: a  reason: collision with root package name */
    protected final CameraCharacteristics f11524a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public z(CameraCharacteristics cameraCharacteristics) {
        this.f11524a = cameraCharacteristics;
    }

    @Override // s.b0.a
    public Object a(CameraCharacteristics.Key key) {
        return this.f11524a.get(key);
    }
}
