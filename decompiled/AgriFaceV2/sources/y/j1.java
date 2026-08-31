package y;

import android.graphics.PointF;
import android.util.Rational;
/* loaded from: classes.dex */
public abstract class j1 {

    /* renamed from: a  reason: collision with root package name */
    private Rational f12855a;

    public j1() {
        this(null);
    }

    public static float d() {
        return 0.15f;
    }

    protected abstract PointF a(float f8, float f9);

    public final i1 b(float f8, float f9) {
        return c(f8, f9, d());
    }

    public final i1 c(float f8, float f9, float f10) {
        PointF a8 = a(f8, f9);
        return new i1(a8.x, a8.y, f10, this.f12855a);
    }

    public j1(Rational rational) {
        this.f12855a = rational;
    }
}
