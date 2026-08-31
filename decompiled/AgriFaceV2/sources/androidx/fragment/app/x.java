package androidx.fragment.app;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import androidx.core.view.b1;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class x extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private final List f3689a;

    /* renamed from: b  reason: collision with root package name */
    private final List f3690b;

    /* renamed from: c  reason: collision with root package name */
    private View.OnApplyWindowInsetsListener f3691c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f3692d;

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public static final a f3693a = new a();

        private a() {
        }

        public final WindowInsets a(View.OnApplyWindowInsetsListener onApplyWindowInsetsListener, View view, WindowInsets windowInsets) {
            d7.k.e(onApplyWindowInsetsListener, "onApplyWindowInsetsListener");
            d7.k.e(view, "v");
            d7.k.e(windowInsets, "insets");
            WindowInsets onApplyWindowInsets = onApplyWindowInsetsListener.onApplyWindowInsets(view, windowInsets);
            d7.k.d(onApplyWindowInsets, "onApplyWindowInsetsListe…lyWindowInsets(v, insets)");
            return onApplyWindowInsets;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(Context context, AttributeSet attributeSet, i0 i0Var) {
        super(context, attributeSet);
        String str;
        d7.k.e(context, "context");
        d7.k.e(attributeSet, "attrs");
        d7.k.e(i0Var, "fm");
        this.f3689a = new ArrayList();
        this.f3690b = new ArrayList();
        this.f3692d = true;
        String classAttribute = attributeSet.getClassAttribute();
        int[] iArr = r1.c.f11317e;
        d7.k.d(iArr, "FragmentContainerView");
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, 0, 0);
        classAttribute = classAttribute == null ? obtainStyledAttributes.getString(r1.c.f11318f) : classAttribute;
        String string = obtainStyledAttributes.getString(r1.c.f11319g);
        obtainStyledAttributes.recycle();
        int id = getId();
        p h02 = i0Var.h0(id);
        if (classAttribute != null && h02 == null) {
            if (id == -1) {
                if (string != null) {
                    str = " with tag " + string;
                } else {
                    str = "";
                }
                throw new IllegalStateException("FragmentContainerView must have an android:id to add Fragment " + classAttribute + str);
            }
            p a8 = i0Var.t0().a(context.getClassLoader(), classAttribute);
            d7.k.d(a8, "fm.fragmentFactory.insta…ontext.classLoader, name)");
            a8.f3582y = id;
            a8.f3583z = id;
            a8.A = string;
            a8.f3578u = i0Var;
            a8.f3579v = i0Var.v0();
            a8.v0(context, attributeSet, null);
            i0Var.n().m(true).c(this, a8, string).i();
        }
        i0Var.Z0(this);
    }

    private final void a(View view) {
        if (this.f3690b.contains(view)) {
            this.f3689a.add(view);
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i8, ViewGroup.LayoutParams layoutParams) {
        d7.k.e(view, "child");
        if (i0.C0(view) != null) {
            super.addView(view, i8, layoutParams);
            return;
        }
        throw new IllegalStateException(("Views added to a FragmentContainerView must be associated with a Fragment. View " + view + " is not associated with a Fragment.").toString());
    }

    @Override // android.view.ViewGroup, android.view.View
    public WindowInsets dispatchApplyWindowInsets(WindowInsets windowInsets) {
        b1 F;
        d7.k.e(windowInsets, "insets");
        b1 u7 = b1.u(windowInsets);
        d7.k.d(u7, "toWindowInsetsCompat(insets)");
        View.OnApplyWindowInsetsListener onApplyWindowInsetsListener = this.f3691c;
        if (onApplyWindowInsetsListener != null) {
            a aVar = a.f3693a;
            d7.k.b(onApplyWindowInsetsListener);
            F = b1.u(aVar.a(onApplyWindowInsetsListener, this, windowInsets));
        } else {
            F = androidx.core.view.p0.F(this, u7);
        }
        d7.k.d(F, "if (applyWindowInsetsLis…, insetsCompat)\n        }");
        if (!F.n()) {
            int childCount = getChildCount();
            for (int i8 = 0; i8 < childCount; i8++) {
                androidx.core.view.p0.e(getChildAt(i8), F);
            }
        }
        return windowInsets;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        d7.k.e(canvas, "canvas");
        if (this.f3692d) {
            for (View view : this.f3689a) {
                super.drawChild(canvas, view, getDrawingTime());
            }
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View view, long j8) {
        d7.k.e(canvas, "canvas");
        d7.k.e(view, "child");
        if (this.f3692d && (!this.f3689a.isEmpty()) && this.f3689a.contains(view)) {
            return false;
        }
        return super.drawChild(canvas, view, j8);
    }

    @Override // android.view.ViewGroup
    public void endViewTransition(View view) {
        d7.k.e(view, "view");
        this.f3690b.remove(view);
        if (this.f3689a.remove(view)) {
            this.f3692d = true;
        }
        super.endViewTransition(view);
    }

    public final <F extends p> F getFragment() {
        return (F) i0.k0(this).h0(getId());
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        d7.k.e(windowInsets, "insets");
        return windowInsets;
    }

    @Override // android.view.ViewGroup
    public void removeAllViewsInLayout() {
        int childCount = getChildCount();
        while (true) {
            childCount--;
            if (-1 >= childCount) {
                super.removeAllViewsInLayout();
                return;
            }
            View childAt = getChildAt(childCount);
            d7.k.d(childAt, "view");
            a(childAt);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        d7.k.e(view, "view");
        a(view);
        super.removeView(view);
    }

    @Override // android.view.ViewGroup
    public void removeViewAt(int i8) {
        View childAt = getChildAt(i8);
        d7.k.d(childAt, "view");
        a(childAt);
        super.removeViewAt(i8);
    }

    @Override // android.view.ViewGroup
    public void removeViewInLayout(View view) {
        d7.k.e(view, "view");
        a(view);
        super.removeViewInLayout(view);
    }

    @Override // android.view.ViewGroup
    public void removeViews(int i8, int i9) {
        int i10 = i8 + i9;
        for (int i11 = i8; i11 < i10; i11++) {
            View childAt = getChildAt(i11);
            d7.k.d(childAt, "view");
            a(childAt);
        }
        super.removeViews(i8, i9);
    }

    @Override // android.view.ViewGroup
    public void removeViewsInLayout(int i8, int i9) {
        int i10 = i8 + i9;
        for (int i11 = i8; i11 < i10; i11++) {
            View childAt = getChildAt(i11);
            d7.k.d(childAt, "view");
            a(childAt);
        }
        super.removeViewsInLayout(i8, i9);
    }

    public final void setDrawDisappearingViewsLast(boolean z7) {
        this.f3692d = z7;
    }

    @Override // android.view.ViewGroup
    public void setLayoutTransition(LayoutTransition layoutTransition) {
        throw new UnsupportedOperationException("FragmentContainerView does not support Layout Transitions or animateLayoutChanges=\"true\".");
    }

    @Override // android.view.View
    public void setOnApplyWindowInsetsListener(View.OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        d7.k.e(onApplyWindowInsetsListener, "listener");
        this.f3691c = onApplyWindowInsetsListener;
    }

    @Override // android.view.ViewGroup
    public void startViewTransition(View view) {
        d7.k.e(view, "view");
        if (view.getParent() == this) {
            this.f3690b.add(view);
        }
        super.startViewTransition(view);
    }
}
