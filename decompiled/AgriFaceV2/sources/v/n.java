package v;

import androidx.camera.camera2.internal.compat.quirk.AutoFlashUnderExposedQuirk;
import b0.l2;
/* loaded from: classes.dex */
public class n {

    /* renamed from: a  reason: collision with root package name */
    private final boolean f12301a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f12302b = false;

    public n(l2 l2Var) {
        this.f12301a = l2Var.b(AutoFlashUnderExposedQuirk.class) != null;
    }

    public void a() {
        this.f12302b = false;
    }

    public void b() {
        this.f12302b = true;
    }

    public boolean c(int i8) {
        return this.f12302b && i8 == 0 && this.f12301a;
    }
}
