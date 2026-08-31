package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
/* loaded from: classes.dex */
class e {

    /* renamed from: a  reason: collision with root package name */
    private final View f2280a;

    /* renamed from: d  reason: collision with root package name */
    private z0 f2283d;

    /* renamed from: e  reason: collision with root package name */
    private z0 f2284e;

    /* renamed from: f  reason: collision with root package name */
    private z0 f2285f;

    /* renamed from: c  reason: collision with root package name */
    private int f2282c = -1;

    /* renamed from: b  reason: collision with root package name */
    private final k f2281b = k.b();

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(View view) {
        this.f2280a = view;
    }

    private boolean a(Drawable drawable) {
        if (this.f2285f == null) {
            this.f2285f = new z0();
        }
        z0 z0Var = this.f2285f;
        z0Var.a();
        ColorStateList m8 = androidx.core.view.p0.m(this.f2280a);
        if (m8 != null) {
            z0Var.f2546d = true;
            z0Var.f2543a = m8;
        }
        PorterDuff.Mode n8 = androidx.core.view.p0.n(this.f2280a);
        if (n8 != null) {
            z0Var.f2545c = true;
            z0Var.f2544b = n8;
        }
        if (z0Var.f2546d || z0Var.f2545c) {
            k.i(drawable, z0Var, this.f2280a.getDrawableState());
            return true;
        }
        return false;
    }

    private boolean k() {
        return this.f2283d != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        Drawable background = this.f2280a.getBackground();
        if (background != null) {
            if (k() && a(background)) {
                return;
            }
            z0 z0Var = this.f2284e;
            if (z0Var != null) {
                k.i(background, z0Var, this.f2280a.getDrawableState());
                return;
            }
            z0 z0Var2 = this.f2283d;
            if (z0Var2 != null) {
                k.i(background, z0Var2, this.f2280a.getDrawableState());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ColorStateList c() {
        z0 z0Var = this.f2284e;
        if (z0Var != null) {
            return z0Var.f2543a;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PorterDuff.Mode d() {
        z0 z0Var = this.f2284e;
        if (z0Var != null) {
            return z0Var.f2544b;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(AttributeSet attributeSet, int i8) {
        b1 u7 = b1.u(this.f2280a.getContext(), attributeSet, g.j.f7032v3, i8, 0);
        View view = this.f2280a;
        androidx.core.view.p0.L(view, view.getContext(), g.j.f7032v3, attributeSet, u7.q(), i8, 0);
        try {
            if (u7.r(g.j.f7037w3)) {
                this.f2282c = u7.m(g.j.f7037w3, -1);
                ColorStateList f8 = this.f2281b.f(this.f2280a.getContext(), this.f2282c);
                if (f8 != null) {
                    h(f8);
                }
            }
            if (u7.r(g.j.f7042x3)) {
                androidx.core.view.p0.R(this.f2280a, u7.c(g.j.f7042x3));
            }
            if (u7.r(g.j.f7047y3)) {
                androidx.core.view.p0.S(this.f2280a, k0.d(u7.j(g.j.f7047y3, -1), null));
            }
        } finally {
            u7.v();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(Drawable drawable) {
        this.f2282c = -1;
        h(null);
        b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(int i8) {
        this.f2282c = i8;
        k kVar = this.f2281b;
        h(kVar != null ? kVar.f(this.f2280a.getContext(), i8) : null);
        b();
    }

    void h(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.f2283d == null) {
                this.f2283d = new z0();
            }
            z0 z0Var = this.f2283d;
            z0Var.f2543a = colorStateList;
            z0Var.f2546d = true;
        } else {
            this.f2283d = null;
        }
        b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i(ColorStateList colorStateList) {
        if (this.f2284e == null) {
            this.f2284e = new z0();
        }
        z0 z0Var = this.f2284e;
        z0Var.f2543a = colorStateList;
        z0Var.f2546d = true;
        b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(PorterDuff.Mode mode) {
        if (this.f2284e == null) {
            this.f2284e = new z0();
        }
        z0 z0Var = this.f2284e;
        z0Var.f2544b = mode;
        z0Var.f2545c = true;
        b();
    }
}
