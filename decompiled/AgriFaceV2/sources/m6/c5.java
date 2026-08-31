package m6;

import android.app.Activity;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;
import java.util.Objects;
import m6.t0;
/* loaded from: classes.dex */
public class c5 implements t0.u0 {

    /* renamed from: a  reason: collision with root package name */
    private final v4 f9244a;

    /* renamed from: b  reason: collision with root package name */
    private final a f9245b;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        Activity f9246a;

        private Display b(Activity activity) {
            return ((WindowManager) activity.getSystemService("window")).getDefaultDisplay();
        }

        public y.i1 a(Double d8, Double d9, Double d10, y.p pVar) {
            y.z d11 = d(Build.VERSION.SDK_INT >= 30 ? this.f9246a.getDisplay() : b(this.f9246a), pVar, 1.0f, 1.0f);
            float floatValue = d8.floatValue();
            float floatValue2 = d9.floatValue();
            return d10 == null ? d11.b(floatValue, floatValue2) : d11.c(floatValue, floatValue2, d10.floatValue());
        }

        public float c() {
            return y.j1.d();
        }

        public y.z d(Display display, y.p pVar, float f8, float f9) {
            return new y.z(display, pVar, f8, f9);
        }
    }

    public c5(v4 v4Var) {
        this(v4Var, new a());
    }

    @Override // m6.t0.u0
    public void c(Long l8, Double d8, Double d9, Double d10, Long l9) {
        a aVar = this.f9245b;
        Object h8 = this.f9244a.h(l9.longValue());
        Objects.requireNonNull(h8);
        this.f9244a.a(aVar.a(d8, d9, d10, (y.p) h8), l8.longValue());
    }

    @Override // m6.t0.u0
    public Double h() {
        return Double.valueOf(this.f9245b.c());
    }

    public void i(Activity activity) {
        this.f9245b.f9246a = activity;
    }

    c5(v4 v4Var, a aVar) {
        this.f9244a = v4Var;
        this.f9245b = aVar;
    }
}
