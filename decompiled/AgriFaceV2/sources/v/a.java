package v;

import android.hardware.camera2.CaptureRequest;
import android.util.Range;
import androidx.camera.camera2.internal.compat.quirk.AeFpsRangeLegacyQuirk;
import b0.l2;
import b0.u0;
import q.a;
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private final Range f12284a;

    public a(l2 l2Var) {
        AeFpsRangeLegacyQuirk aeFpsRangeLegacyQuirk = (AeFpsRangeLegacyQuirk) l2Var.b(AeFpsRangeLegacyQuirk.class);
        this.f12284a = aeFpsRangeLegacyQuirk == null ? null : aeFpsRangeLegacyQuirk.g();
    }

    public void a(a.C0140a c0140a) {
        Range range = this.f12284a;
        if (range != null) {
            c0140a.g(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, range, u0.c.REQUIRED);
        }
    }
}
