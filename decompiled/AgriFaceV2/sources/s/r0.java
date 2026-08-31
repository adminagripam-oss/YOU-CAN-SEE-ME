package s;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class r0 extends q0 {
    /* JADX INFO: Access modifiers changed from: package-private */
    public r0(Context context) {
        super(context);
    }

    @Override // s.s0, s.o0.b
    public Set b() {
        try {
            return this.f11504a.getConcurrentCameraIds();
        } catch (CameraAccessException e8) {
            throw g.e(e8);
        }
    }
}
