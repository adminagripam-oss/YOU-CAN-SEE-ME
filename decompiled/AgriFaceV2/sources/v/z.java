package v;

import android.hardware.camera2.CaptureRequest;
import androidx.camera.camera2.internal.compat.quirk.TorchIsClosedAfterImageCapturingQuirk;
import b0.b1;
import b0.s0;
import java.util.Iterator;
import java.util.List;
import q.a;
/* loaded from: classes.dex */
public class z {

    /* renamed from: a  reason: collision with root package name */
    private final boolean f12320a;

    public z() {
        this.f12320a = androidx.camera.camera2.internal.compat.quirk.b.b(TorchIsClosedAfterImageCapturingQuirk.class) != null;
    }

    public s0 a(s0 s0Var) {
        s0.a aVar = new s0.a();
        aVar.v(s0Var.k());
        for (b1 b1Var : s0Var.i()) {
            aVar.f(b1Var);
        }
        aVar.e(s0Var.g());
        a.C0140a c0140a = new a.C0140a();
        c0140a.f(CaptureRequest.FLASH_MODE, 0);
        aVar.e(c0140a.a());
        return aVar.h();
    }

    public boolean b(List list, boolean z7) {
        if (this.f12320a && z7) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Integer num = (Integer) ((CaptureRequest) it.next()).get(CaptureRequest.FLASH_MODE);
                if (num != null && num.intValue() == 2) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
