package v;

import androidx.camera.camera2.internal.compat.quirk.ConfigureSurfaceToSecondarySessionFailQuirk;
import androidx.camera.camera2.internal.compat.quirk.PreviewOrientationIncorrectQuirk;
import androidx.camera.camera2.internal.compat.quirk.TextureViewIsClosedQuirk;
import b0.b1;
import b0.l2;
import java.util.Iterator;
import java.util.List;
import y.f1;
/* loaded from: classes.dex */
public class i {

    /* renamed from: a  reason: collision with root package name */
    private final boolean f12293a;

    /* renamed from: b  reason: collision with root package name */
    private final boolean f12294b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f12295c;

    public i(l2 l2Var, l2 l2Var2) {
        this.f12293a = l2Var2.a(TextureViewIsClosedQuirk.class);
        this.f12294b = l2Var.a(PreviewOrientationIncorrectQuirk.class);
        this.f12295c = l2Var.a(ConfigureSurfaceToSecondarySessionFailQuirk.class);
    }

    public void a(List list) {
        if (!b() || list == null) {
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((b1) it.next()).d();
        }
        f1.a("ForceCloseDeferrableSurface", "deferrableSurface closed");
    }

    public boolean b() {
        return this.f12293a || this.f12294b || this.f12295c;
    }
}
