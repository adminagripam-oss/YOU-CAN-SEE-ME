package v;

import androidx.camera.camera2.internal.compat.quirk.TorchFlashRequiredFor3aUpdateQuirk;
import b0.l2;
import y.f1;
/* loaded from: classes.dex */
public class a0 {

    /* renamed from: a  reason: collision with root package name */
    private final TorchFlashRequiredFor3aUpdateQuirk f12285a;

    public a0(l2 l2Var) {
        this.f12285a = (TorchFlashRequiredFor3aUpdateQuirk) l2Var.b(TorchFlashRequiredFor3aUpdateQuirk.class);
    }

    public boolean a() {
        TorchFlashRequiredFor3aUpdateQuirk torchFlashRequiredFor3aUpdateQuirk = this.f12285a;
        boolean z7 = torchFlashRequiredFor3aUpdateQuirk != null && torchFlashRequiredFor3aUpdateQuirk.i();
        f1.a("UseFlashModeTorchFor3aUpdate", "shouldUseFlashModeTorch: " + z7);
        return z7;
    }
}
