package b0;

import android.util.Size;
import android.view.Surface;
/* loaded from: classes.dex */
public final class r1 extends b1 {

    /* renamed from: o  reason: collision with root package name */
    private final Surface f4364o;

    public r1(Surface surface) {
        this.f4364o = surface;
    }

    @Override // b0.b1
    public i4.d r() {
        return f0.n.p(this.f4364o);
    }

    public r1(Surface surface, Size size, int i8) {
        super(size, i8);
        this.f4364o = surface;
    }
}
