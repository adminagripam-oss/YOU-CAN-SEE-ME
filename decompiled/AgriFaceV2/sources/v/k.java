package v;

import android.util.Size;
import androidx.camera.camera2.internal.compat.quirk.ExtraCroppingQuirk;
import b0.w2;
/* loaded from: classes.dex */
public class k {

    /* renamed from: a  reason: collision with root package name */
    private final ExtraCroppingQuirk f12296a;

    public k() {
        this((ExtraCroppingQuirk) androidx.camera.camera2.internal.compat.quirk.b.b(ExtraCroppingQuirk.class));
    }

    public Size a(Size size) {
        Size f8;
        ExtraCroppingQuirk extraCroppingQuirk = this.f12296a;
        if (extraCroppingQuirk == null || (f8 = extraCroppingQuirk.f(w2.b.PRIV)) == null) {
            return size;
        }
        return f8.getWidth() * f8.getHeight() > size.getWidth() * size.getHeight() ? f8 : size;
    }

    k(ExtraCroppingQuirk extraCroppingQuirk) {
        this.f12296a = extraCroppingQuirk;
    }
}
