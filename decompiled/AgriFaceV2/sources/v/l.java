package v;

import android.graphics.PointF;
import androidx.camera.camera2.internal.compat.quirk.AfRegionFlipHorizontallyQuirk;
import b0.l2;
import y.i1;
/* loaded from: classes.dex */
public class l {

    /* renamed from: a  reason: collision with root package name */
    private final l2 f12297a;

    public l(l2 l2Var) {
        this.f12297a = l2Var;
    }

    public PointF a(i1 i1Var, int i8) {
        return (i8 == 1 && this.f12297a.a(AfRegionFlipHorizontallyQuirk.class)) ? new PointF(1.0f - i1Var.c(), i1Var.d()) : new PointF(i1Var.c(), i1Var.d());
    }
}
