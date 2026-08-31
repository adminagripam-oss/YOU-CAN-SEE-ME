package a0;

import androidx.camera.core.ImageProcessingUtil;
import java.util.Objects;
/* loaded from: classes.dex */
public class b0 implements l0.y {
    @Override // l0.y
    /* renamed from: a */
    public l0.z apply(l0.z zVar) {
        androidx.camera.core.q qVar = new androidx.camera.core.q(androidx.camera.core.o.a(zVar.h().getWidth(), zVar.h().getHeight(), 256, 2));
        androidx.camera.core.n e8 = ImageProcessingUtil.e(qVar, (byte[]) zVar.c());
        qVar.k();
        Objects.requireNonNull(e8);
        d0.g d8 = zVar.d();
        Objects.requireNonNull(d8);
        return l0.z.k(e8, d8, zVar.b(), zVar.f(), zVar.g(), zVar.a());
    }
}
