package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
/* loaded from: classes.dex */
public class q {

    /* renamed from: a  reason: collision with root package name */
    private final ImageView f2458a;

    /* renamed from: b  reason: collision with root package name */
    private z0 f2459b;

    /* renamed from: c  reason: collision with root package name */
    private z0 f2460c;

    /* renamed from: d  reason: collision with root package name */
    private z0 f2461d;

    /* renamed from: e  reason: collision with root package name */
    private int f2462e = 0;

    public q(ImageView imageView) {
        this.f2458a = imageView;
    }

    private boolean a(Drawable drawable) {
        if (this.f2461d == null) {
            this.f2461d = new z0();
        }
        z0 z0Var = this.f2461d;
        z0Var.a();
        ColorStateList a8 = androidx.core.widget.e.a(this.f2458a);
        if (a8 != null) {
            z0Var.f2546d = true;
            z0Var.f2543a = a8;
        }
        PorterDuff.Mode b8 = androidx.core.widget.e.b(this.f2458a);
        if (b8 != null) {
            z0Var.f2545c = true;
            z0Var.f2544b = b8;
        }
        if (z0Var.f2546d || z0Var.f2545c) {
            k.i(drawable, z0Var, this.f2458a.getDrawableState());
            return true;
        }
        return false;
    }

    private boolean l() {
        return this.f2459b != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        if (this.f2458a.getDrawable() != null) {
            this.f2458a.getDrawable().setLevel(this.f2462e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c() {
        Drawable drawable = this.f2458a.getDrawable();
        if (drawable != null) {
            k0.b(drawable);
        }
        if (drawable != null) {
            if (l() && a(drawable)) {
                return;
            }
            z0 z0Var = this.f2460c;
            if (z0Var != null) {
                k.i(drawable, z0Var, this.f2458a.getDrawableState());
                return;
            }
            z0 z0Var2 = this.f2459b;
            if (z0Var2 != null) {
                k.i(drawable, z0Var2, this.f2458a.getDrawableState());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ColorStateList d() {
        z0 z0Var = this.f2460c;
        if (z0Var != null) {
            return z0Var.f2543a;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PorterDuff.Mode e() {
        z0 z0Var = this.f2460c;
        if (z0Var != null) {
            return z0Var.f2544b;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean f() {
        return !(this.f2458a.getBackground() instanceof RippleDrawable);
    }

    public void g(AttributeSet attributeSet, int i8) {
        int m8;
        b1 u7 = b1.u(this.f2458a.getContext(), attributeSet, g.j.P, i8, 0);
        ImageView imageView = this.f2458a;
        androidx.core.view.p0.L(imageView, imageView.getContext(), g.j.P, attributeSet, u7.q(), i8, 0);
        try {
            Drawable drawable = this.f2458a.getDrawable();
            if (drawable == null && (m8 = u7.m(g.j.Q, -1)) != -1 && (drawable = h.a.b(this.f2458a.getContext(), m8)) != null) {
                this.f2458a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                k0.b(drawable);
            }
            if (u7.r(g.j.R)) {
                androidx.core.widget.e.c(this.f2458a, u7.c(g.j.R));
            }
            if (u7.r(g.j.S)) {
                androidx.core.widget.e.d(this.f2458a, k0.d(u7.j(g.j.S, -1), null));
            }
        } finally {
            u7.v();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h(Drawable drawable) {
        this.f2462e = drawable.getLevel();
    }

    public void i(int i8) {
        if (i8 != 0) {
            Drawable b8 = h.a.b(this.f2458a.getContext(), i8);
            if (b8 != null) {
                k0.b(b8);
            }
            this.f2458a.setImageDrawable(b8);
        } else {
            this.f2458a.setImageDrawable(null);
        }
        c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(ColorStateList colorStateList) {
        if (this.f2460c == null) {
            this.f2460c = new z0();
        }
        z0 z0Var = this.f2460c;
        z0Var.f2543a = colorStateList;
        z0Var.f2546d = true;
        c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k(PorterDuff.Mode mode) {
        if (this.f2460c == null) {
            this.f2460c = new z0();
        }
        z0 z0Var = this.f2460c;
        z0Var.f2544b = mode;
        z0Var.f2545c = true;
        c();
    }
}
