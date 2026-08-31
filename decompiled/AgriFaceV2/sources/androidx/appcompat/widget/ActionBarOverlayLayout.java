package androidx.appcompat.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.OverScroller;
import androidx.appcompat.view.menu.m;
import androidx.core.view.b1;
/* loaded from: classes.dex */
public class ActionBarOverlayLayout extends ViewGroup implements i0, androidx.core.view.e0, androidx.core.view.f0 {
    static final int[] F = {g.a.f6793b, 16842841};
    ViewPropertyAnimator A;
    final AnimatorListenerAdapter B;
    private final Runnable C;
    private final Runnable D;
    private final androidx.core.view.g0 E;

    /* renamed from: a  reason: collision with root package name */
    private int f2026a;

    /* renamed from: b  reason: collision with root package name */
    private int f2027b;

    /* renamed from: c  reason: collision with root package name */
    private ContentFrameLayout f2028c;

    /* renamed from: d  reason: collision with root package name */
    ActionBarContainer f2029d;

    /* renamed from: e  reason: collision with root package name */
    private j0 f2030e;

    /* renamed from: f  reason: collision with root package name */
    private Drawable f2031f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f2032g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f2033h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f2034i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f2035j;

    /* renamed from: k  reason: collision with root package name */
    boolean f2036k;

    /* renamed from: l  reason: collision with root package name */
    private int f2037l;

    /* renamed from: m  reason: collision with root package name */
    private int f2038m;

    /* renamed from: n  reason: collision with root package name */
    private final Rect f2039n;

    /* renamed from: o  reason: collision with root package name */
    private final Rect f2040o;

    /* renamed from: p  reason: collision with root package name */
    private final Rect f2041p;

    /* renamed from: q  reason: collision with root package name */
    private final Rect f2042q;

    /* renamed from: r  reason: collision with root package name */
    private final Rect f2043r;

    /* renamed from: s  reason: collision with root package name */
    private final Rect f2044s;

    /* renamed from: t  reason: collision with root package name */
    private final Rect f2045t;

    /* renamed from: u  reason: collision with root package name */
    private androidx.core.view.b1 f2046u;

    /* renamed from: v  reason: collision with root package name */
    private androidx.core.view.b1 f2047v;

    /* renamed from: w  reason: collision with root package name */
    private androidx.core.view.b1 f2048w;

    /* renamed from: x  reason: collision with root package name */
    private androidx.core.view.b1 f2049x;

    /* renamed from: y  reason: collision with root package name */
    private d f2050y;

    /* renamed from: z  reason: collision with root package name */
    private OverScroller f2051z;

    /* loaded from: classes.dex */
    class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.A = null;
            actionBarOverlayLayout.f2036k = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.A = null;
            actionBarOverlayLayout.f2036k = false;
        }
    }

    /* loaded from: classes.dex */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionBarOverlayLayout.this.o();
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.A = actionBarOverlayLayout.f2029d.animate().translationY(0.0f).setListener(ActionBarOverlayLayout.this.B);
        }
    }

    /* loaded from: classes.dex */
    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionBarOverlayLayout.this.o();
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.A = actionBarOverlayLayout.f2029d.animate().translationY(-ActionBarOverlayLayout.this.f2029d.getHeight()).setListener(ActionBarOverlayLayout.this.B);
        }
    }

    /* loaded from: classes.dex */
    public interface d {
        void a();

        void b();

        void c(int i8);

        void d();

        void e(boolean z7);

        void f();
    }

    /* loaded from: classes.dex */
    public static class e extends ViewGroup.MarginLayoutParams {
        public e(int i8, int i9) {
            super(i8, i9);
        }

        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2027b = 0;
        this.f2039n = new Rect();
        this.f2040o = new Rect();
        this.f2041p = new Rect();
        this.f2042q = new Rect();
        this.f2043r = new Rect();
        this.f2044s = new Rect();
        this.f2045t = new Rect();
        androidx.core.view.b1 b1Var = androidx.core.view.b1.f3082b;
        this.f2046u = b1Var;
        this.f2047v = b1Var;
        this.f2048w = b1Var;
        this.f2049x = b1Var;
        this.B = new a();
        this.C = new b();
        this.D = new c();
        p(context);
        this.E = new androidx.core.view.g0(this);
    }

    private void d() {
        o();
        this.D.run();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean k(android.view.View r3, android.graphics.Rect r4, boolean r5, boolean r6, boolean r7, boolean r8) {
        /*
            r2 = this;
            android.view.ViewGroup$LayoutParams r3 = r3.getLayoutParams()
            androidx.appcompat.widget.ActionBarOverlayLayout$e r3 = (androidx.appcompat.widget.ActionBarOverlayLayout.e) r3
            r0 = 1
            if (r5 == 0) goto L13
            int r5 = r3.leftMargin
            int r1 = r4.left
            if (r5 == r1) goto L13
            r3.leftMargin = r1
            r5 = r0
            goto L14
        L13:
            r5 = 0
        L14:
            if (r6 == 0) goto L1f
            int r6 = r3.topMargin
            int r1 = r4.top
            if (r6 == r1) goto L1f
            r3.topMargin = r1
            r5 = r0
        L1f:
            if (r8 == 0) goto L2a
            int r6 = r3.rightMargin
            int r8 = r4.right
            if (r6 == r8) goto L2a
            r3.rightMargin = r8
            r5 = r0
        L2a:
            if (r7 == 0) goto L35
            int r6 = r3.bottomMargin
            int r4 = r4.bottom
            if (r6 == r4) goto L35
            r3.bottomMargin = r4
            goto L36
        L35:
            r0 = r5
        L36:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActionBarOverlayLayout.k(android.view.View, android.graphics.Rect, boolean, boolean, boolean, boolean):boolean");
    }

    private j0 n(View view) {
        if (view instanceof j0) {
            return (j0) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        throw new IllegalStateException("Can't make a decor toolbar out of " + view.getClass().getSimpleName());
    }

    private void p(Context context) {
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(F);
        this.f2026a = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = obtainStyledAttributes.getDrawable(1);
        this.f2031f = drawable;
        setWillNotDraw(drawable == null);
        obtainStyledAttributes.recycle();
        this.f2032g = context.getApplicationInfo().targetSdkVersion < 19;
        this.f2051z = new OverScroller(context);
    }

    private void r() {
        o();
        postDelayed(this.D, 600L);
    }

    private void s() {
        o();
        postDelayed(this.C, 600L);
    }

    private void u() {
        o();
        this.C.run();
    }

    private boolean v(float f8) {
        this.f2051z.fling(0, 0, 0, (int) f8, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return this.f2051z.getFinalY() > this.f2029d.getHeight();
    }

    @Override // androidx.appcompat.widget.i0
    public void a(Menu menu, m.a aVar) {
        t();
        this.f2030e.a(menu, aVar);
    }

    @Override // androidx.appcompat.widget.i0
    public boolean b() {
        t();
        return this.f2030e.b();
    }

    @Override // androidx.appcompat.widget.i0
    public void c() {
        t();
        this.f2030e.c();
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof e;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.f2031f == null || this.f2032g) {
            return;
        }
        int bottom = this.f2029d.getVisibility() == 0 ? (int) (this.f2029d.getBottom() + this.f2029d.getTranslationY() + 0.5f) : 0;
        this.f2031f.setBounds(0, bottom, getWidth(), this.f2031f.getIntrinsicHeight() + bottom);
        this.f2031f.draw(canvas);
    }

    @Override // androidx.appcompat.widget.i0
    public boolean e() {
        t();
        return this.f2030e.e();
    }

    @Override // androidx.appcompat.widget.i0
    public boolean f() {
        t();
        return this.f2030e.f();
    }

    @Override // android.view.View
    protected boolean fitSystemWindows(Rect rect) {
        return super.fitSystemWindows(rect);
    }

    @Override // androidx.appcompat.widget.i0
    public boolean g() {
        t();
        return this.f2030e.g();
    }

    public int getActionBarHideOffset() {
        ActionBarContainer actionBarContainer = this.f2029d;
        if (actionBarContainer != null) {
            return -((int) actionBarContainer.getTranslationY());
        }
        return 0;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.E.a();
    }

    public CharSequence getTitle() {
        t();
        return this.f2030e.getTitle();
    }

    @Override // androidx.appcompat.widget.i0
    public boolean h() {
        t();
        return this.f2030e.h();
    }

    @Override // androidx.appcompat.widget.i0
    public void i(int i8) {
        t();
        if (i8 == 2) {
            this.f2030e.w();
        } else if (i8 == 5) {
            this.f2030e.x();
        } else if (i8 != 109) {
        } else {
            setOverlayMode(true);
        }
    }

    @Override // androidx.appcompat.widget.i0
    public void j() {
        t();
        this.f2030e.i();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* renamed from: l */
    public e generateDefaultLayoutParams() {
        return new e(-1, -1);
    }

    @Override // android.view.ViewGroup
    /* renamed from: m */
    public e generateLayoutParams(AttributeSet attributeSet) {
        return new e(getContext(), attributeSet);
    }

    void o() {
        removeCallbacks(this.C);
        removeCallbacks(this.D);
        ViewPropertyAnimator viewPropertyAnimator = this.A;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        t();
        androidx.core.view.b1 v7 = androidx.core.view.b1.v(windowInsets, this);
        boolean k8 = k(this.f2029d, new Rect(v7.i(), v7.k(), v7.j(), v7.h()), true, true, false, true);
        androidx.core.view.p0.d(this, v7, this.f2039n);
        Rect rect = this.f2039n;
        androidx.core.view.b1 l8 = v7.l(rect.left, rect.top, rect.right, rect.bottom);
        this.f2046u = l8;
        boolean z7 = true;
        if (!this.f2047v.equals(l8)) {
            this.f2047v = this.f2046u;
            k8 = true;
        }
        if (this.f2040o.equals(this.f2039n)) {
            z7 = k8;
        } else {
            this.f2040o.set(this.f2039n);
        }
        if (z7) {
            requestLayout();
        }
        return v7.a().c().b().t();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        p(getContext());
        androidx.core.view.p0.K(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        o();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt = getChildAt(i12);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i13 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin + paddingLeft;
                int i14 = ((ViewGroup.MarginLayoutParams) eVar).topMargin + paddingTop;
                childAt.layout(i13, i14, measuredWidth + i13, measuredHeight + i14);
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i8, int i9) {
        int measuredHeight;
        androidx.core.view.b1 a8;
        t();
        measureChildWithMargins(this.f2029d, i8, 0, i9, 0);
        e eVar = (e) this.f2029d.getLayoutParams();
        int max = Math.max(0, this.f2029d.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) eVar).leftMargin + ((ViewGroup.MarginLayoutParams) eVar).rightMargin);
        int max2 = Math.max(0, this.f2029d.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar).topMargin + ((ViewGroup.MarginLayoutParams) eVar).bottomMargin);
        int combineMeasuredStates = View.combineMeasuredStates(0, this.f2029d.getMeasuredState());
        boolean z7 = (androidx.core.view.p0.v(this) & 256) != 0;
        if (z7) {
            measuredHeight = this.f2026a;
            if (this.f2034i && this.f2029d.getTabContainer() != null) {
                measuredHeight += this.f2026a;
            }
        } else {
            measuredHeight = this.f2029d.getVisibility() != 8 ? this.f2029d.getMeasuredHeight() : 0;
        }
        this.f2041p.set(this.f2039n);
        androidx.core.view.b1 b1Var = this.f2046u;
        this.f2048w = b1Var;
        if (this.f2033h || z7) {
            a8 = new b1.b(this.f2048w).c(androidx.core.graphics.f.b(b1Var.i(), this.f2048w.k() + measuredHeight, this.f2048w.j(), this.f2048w.h() + 0)).a();
        } else {
            Rect rect = this.f2041p;
            rect.top += measuredHeight;
            rect.bottom += 0;
            a8 = b1Var.l(0, measuredHeight, 0, 0);
        }
        this.f2048w = a8;
        k(this.f2028c, this.f2041p, true, true, true, true);
        if (!this.f2049x.equals(this.f2048w)) {
            androidx.core.view.b1 b1Var2 = this.f2048w;
            this.f2049x = b1Var2;
            androidx.core.view.p0.e(this.f2028c, b1Var2);
        }
        measureChildWithMargins(this.f2028c, i8, 0, i9, 0);
        e eVar2 = (e) this.f2028c.getLayoutParams();
        int max3 = Math.max(max, this.f2028c.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) eVar2).leftMargin + ((ViewGroup.MarginLayoutParams) eVar2).rightMargin);
        int max4 = Math.max(max2, this.f2028c.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar2).topMargin + ((ViewGroup.MarginLayoutParams) eVar2).bottomMargin);
        int combineMeasuredStates2 = View.combineMeasuredStates(combineMeasuredStates, this.f2028c.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(max3 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i8, combineMeasuredStates2), View.resolveSizeAndState(Math.max(max4 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i9, combineMeasuredStates2 << 16));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View view, float f8, float f9, boolean z7) {
        if (this.f2035j && z7) {
            if (v(f9)) {
                d();
            } else {
                u();
            }
            this.f2036k = true;
            return true;
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View view, float f8, float f9) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View view, int i8, int i9, int[] iArr) {
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View view, int i8, int i9, int i10, int i11) {
        int i12 = this.f2037l + i9;
        this.f2037l = i12;
        setActionBarHideOffset(i12);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScrollAccepted(View view, View view2, int i8) {
        this.E.b(view, view2, i8);
        this.f2037l = getActionBarHideOffset();
        o();
        d dVar = this.f2050y;
        if (dVar != null) {
            dVar.b();
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View view, View view2, int i8) {
        if ((i8 & 2) == 0 || this.f2029d.getVisibility() != 0) {
            return false;
        }
        return this.f2035j;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View view) {
        if (this.f2035j && !this.f2036k) {
            if (this.f2037l <= this.f2029d.getHeight()) {
                s();
            } else {
                r();
            }
        }
        d dVar = this.f2050y;
        if (dVar != null) {
            dVar.d();
        }
    }

    @Override // android.view.View
    public void onWindowSystemUiVisibilityChanged(int i8) {
        super.onWindowSystemUiVisibilityChanged(i8);
        t();
        int i9 = this.f2038m ^ i8;
        this.f2038m = i8;
        boolean z7 = (i8 & 4) == 0;
        boolean z8 = (i8 & 256) != 0;
        d dVar = this.f2050y;
        if (dVar != null) {
            dVar.e(!z8);
            if (z7 || !z8) {
                this.f2050y.a();
            } else {
                this.f2050y.f();
            }
        }
        if ((i9 & 256) == 0 || this.f2050y == null) {
            return;
        }
        androidx.core.view.p0.K(this);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i8) {
        super.onWindowVisibilityChanged(i8);
        this.f2027b = i8;
        d dVar = this.f2050y;
        if (dVar != null) {
            dVar.c(i8);
        }
    }

    public boolean q() {
        return this.f2033h;
    }

    public void setActionBarHideOffset(int i8) {
        o();
        this.f2029d.setTranslationY(-Math.max(0, Math.min(i8, this.f2029d.getHeight())));
    }

    public void setActionBarVisibilityCallback(d dVar) {
        this.f2050y = dVar;
        if (getWindowToken() != null) {
            this.f2050y.c(this.f2027b);
            int i8 = this.f2038m;
            if (i8 != 0) {
                onWindowSystemUiVisibilityChanged(i8);
                androidx.core.view.p0.K(this);
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean z7) {
        this.f2034i = z7;
    }

    public void setHideOnContentScrollEnabled(boolean z7) {
        if (z7 != this.f2035j) {
            this.f2035j = z7;
            if (z7) {
                return;
            }
            o();
            setActionBarHideOffset(0);
        }
    }

    public void setIcon(int i8) {
        t();
        this.f2030e.setIcon(i8);
    }

    public void setLogo(int i8) {
        t();
        this.f2030e.t(i8);
    }

    public void setOverlayMode(boolean z7) {
        this.f2033h = z7;
        this.f2032g = z7 && getContext().getApplicationInfo().targetSdkVersion < 19;
    }

    public void setShowingForActionMode(boolean z7) {
    }

    public void setUiOptions(int i8) {
    }

    @Override // androidx.appcompat.widget.i0
    public void setWindowCallback(Window.Callback callback) {
        t();
        this.f2030e.setWindowCallback(callback);
    }

    @Override // androidx.appcompat.widget.i0
    public void setWindowTitle(CharSequence charSequence) {
        t();
        this.f2030e.setWindowTitle(charSequence);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    void t() {
        if (this.f2028c == null) {
            this.f2028c = (ContentFrameLayout) findViewById(g.f.f6863b);
            this.f2029d = (ActionBarContainer) findViewById(g.f.f6864c);
            this.f2030e = n(findViewById(g.f.f6862a));
        }
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new e(layoutParams);
    }

    @Override // androidx.core.view.e0
    public void onNestedPreScroll(View view, int i8, int i9, int[] iArr, int i10) {
        if (i10 == 0) {
            onNestedPreScroll(view, i8, i9, iArr);
        }
    }

    @Override // androidx.core.view.e0
    public void onNestedScroll(View view, int i8, int i9, int i10, int i11, int i12) {
        if (i12 == 0) {
            onNestedScroll(view, i8, i9, i10, i11);
        }
    }

    @Override // androidx.core.view.e0
    public void onNestedScrollAccepted(View view, View view2, int i8, int i9) {
        if (i9 == 0) {
            onNestedScrollAccepted(view, view2, i8);
        }
    }

    @Override // androidx.core.view.e0
    public boolean onStartNestedScroll(View view, View view2, int i8, int i9) {
        return i9 == 0 && onStartNestedScroll(view, view2, i8);
    }

    @Override // androidx.core.view.e0
    public void onStopNestedScroll(View view, int i8) {
        if (i8 == 0) {
            onStopNestedScroll(view);
        }
    }

    public void setIcon(Drawable drawable) {
        t();
        this.f2030e.setIcon(drawable);
    }

    @Override // androidx.core.view.f0
    public void onNestedScroll(View view, int i8, int i9, int i10, int i11, int i12, int[] iArr) {
        onNestedScroll(view, i8, i9, i10, i11, i12);
    }
}
