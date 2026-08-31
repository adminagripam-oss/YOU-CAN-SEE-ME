package t;

import android.hardware.camera2.params.OutputConfiguration;
import android.view.Surface;
/* loaded from: classes.dex */
abstract class l extends p {
    /* JADX INFO: Access modifiers changed from: package-private */
    public l(Object obj) {
        super(obj);
    }

    @Override // t.k.a
    public abstract Object f();

    @Override // t.k.a
    public Surface getSurface() {
        return ((OutputConfiguration) f()).getSurface();
    }
}
