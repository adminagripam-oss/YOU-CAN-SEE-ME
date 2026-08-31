package s;

import android.hardware.camera2.params.StreamConfigurationMap;
import android.util.Size;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class u0 extends v0 {
    /* JADX INFO: Access modifiers changed from: package-private */
    public u0(StreamConfigurationMap streamConfigurationMap) {
        super(streamConfigurationMap);
    }

    @Override // s.t0.a
    public Size[] c(int i8) {
        return this.f11520a.getOutputSizes(i8);
    }
}
