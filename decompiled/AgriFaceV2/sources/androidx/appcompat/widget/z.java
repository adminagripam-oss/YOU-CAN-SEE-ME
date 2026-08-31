package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;
/* loaded from: classes.dex */
class z extends u {

    /* renamed from: d  reason: collision with root package name */
    private final SeekBar f2537d;

    /* renamed from: e  reason: collision with root package name */
    private Drawable f2538e;

    /* renamed from: f  reason: collision with root package name */
    private ColorStateList f2539f;

    /* renamed from: g  reason: collision with root package name */
    private PorterDuff.Mode f2540g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f2541h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f2542i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public z(SeekBar seekBar) {
        super(seekBar);
        this.f2539f = null;
        this.f2540g = null;
        this.f2541h = false;
        this.f2542i = false;
        this.f2537d = seekBar;
    }

    private void f() {
        Drawable drawable = this.f2538e;
        if (drawable != null) {
            if (this.f2541h || this.f2542i) {
                Drawable i8 = androidx.core.graphics.drawable.a.i(drawable.mutate());
                this.f2538e = i8;
                if (this.f2541h) {
                    androidx.core.graphics.drawable.a.g(i8, this.f2539f);
                }
                if (this.f2542i) {
                    androidx.core.graphics.drawable.a.h(this.f2538e, this.f2540g);
                }
                if (this.f2538e.isStateful()) {
                    this.f2538e.setState(this.f2537d.getDrawableState());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.appcompat.widget.u
    public void c(AttributeSet attributeSet, int i8) {
        super.c(attributeSet, i8);
        b1 u7 = b1.u(this.f2537d.getContext(), attributeSet, g.j.T, i8, 0);
        SeekBar seekBar = this.f2537d;
        androidx.core.view.p0.L(seekBar, seekBar.getContext(), g.j.T, attributeSet, u7.q(), i8, 0);
        Drawable g8 = u7.g(g.j.U);
        if (g8 != null) {
            this.f2537d.setThumb(g8);
        }
        j(u7.f(g.j.V));
        if (u7.r(g.j.X)) {
            this.f2540g = k0.d(u7.j(g.j.X, -1), this.f2540g);
            this.f2542i = true;
        }
        if (u7.r(g.j.W)) {
            this.f2539f = u7.c(g.j.W);
            this.f2541h = true;
        }
        u7.v();
        f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(Canvas canvas) {
        if (this.f2538e != null) {
            int max = this.f2537d.getMax();
            if (max > 1) {
                int intrinsicWidth = this.f2538e.getIntrinsicWidth();
                int intrinsicHeight = this.f2538e.getIntrinsicHeight();
                int i8 = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i9 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.f2538e.setBounds(-i8, -i9, i8, i9);
                float width = ((this.f2537d.getWidth() - this.f2537d.getPaddingLeft()) - this.f2537d.getPaddingRight()) / max;
                int save = canvas.save();
                canvas.translate(this.f2537d.getPaddingLeft(), this.f2537d.getHeight() / 2);
                for (int i10 = 0; i10 <= max; i10++) {
                    this.f2538e.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(save);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h() {
        Drawable drawable = this.f2538e;
        if (drawable != null && drawable.isStateful() && drawable.setState(this.f2537d.getDrawableState())) {
            this.f2537d.invalidateDrawable(drawable);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i() {
        Drawable drawable = this.f2538e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    void j(Drawable drawable) {
        Drawable drawable2 = this.f2538e;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f2538e = drawable;
        if (drawable != null) {
            drawable.setCallback(this.f2537d);
            androidx.core.graphics.drawable.a.e(drawable, androidx.core.view.p0.p(this.f2537d));
            if (drawable.isStateful()) {
                drawable.setState(this.f2537d.getDrawableState());
            }
            f();
        }
        this.f2537d.invalidate();
    }
}
