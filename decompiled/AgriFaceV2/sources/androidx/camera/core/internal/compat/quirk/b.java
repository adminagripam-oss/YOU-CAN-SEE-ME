package androidx.camera.core.internal.compat.quirk;

import b0.i2;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public abstract class b {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static List a(i2 i2Var) {
        ArrayList arrayList = new ArrayList();
        if (i2Var.a(ImageCaptureRotationOptionQuirk.class, ImageCaptureRotationOptionQuirk.k())) {
            arrayList.add(new ImageCaptureRotationOptionQuirk());
        }
        if (i2Var.a(SurfaceOrderQuirk.class, SurfaceOrderQuirk.f())) {
            arrayList.add(new SurfaceOrderQuirk());
        }
        if (i2Var.a(CaptureFailedRetryQuirk.class, CaptureFailedRetryQuirk.g())) {
            arrayList.add(new CaptureFailedRetryQuirk());
        }
        if (i2Var.a(LowMemoryQuirk.class, LowMemoryQuirk.f())) {
            arrayList.add(new LowMemoryQuirk());
        }
        if (i2Var.a(LargeJpegImageQuirk.class, LargeJpegImageQuirk.i())) {
            arrayList.add(new LargeJpegImageQuirk());
        }
        if (i2Var.a(IncorrectJpegMetadataQuirk.class, IncorrectJpegMetadataQuirk.j())) {
            arrayList.add(new IncorrectJpegMetadataQuirk());
        }
        return arrayList;
    }
}
