package androidx.appcompat.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
/* loaded from: classes.dex */
public class ActionBarContextView extends androidx.appcompat.widget.a {

    /* renamed from: i  reason: collision with root package name */
    private CharSequence f2012i;

    /* renamed from: j  reason: collision with root package name */
    private CharSequence f2013j;

    /* renamed from: k  reason: collision with root package name */
    private View f2014k;

    /* renamed from: l  reason: collision with root package name */
    private View f2015l;

    /* renamed from: m  reason: collision with root package name */
    private View f2016m;

    /* renamed from: n  reason: collision with root package name */
    private LinearLayout f2017n;

    /* renamed from: o  reason: collision with root package name */
    private TextView f2018o;

    /* renamed from: p  reason: collision with root package name */
    private TextView f2019p;

    /* renamed from: q  reason: collision with root package name */
    private int f2020q;

    /* renamed from: r  reason: collision with root package name */
    private int f2021r;

    /* renamed from: s  reason: collision with root package name */
    private boolean f2022s;

    /* renamed from: t  reason: collision with root package name */
    private int f2023t;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ androidx.appcompat.view.b f2024a;

        a(androidx.appcompat.view.b bVar) {
            this.f2024a = bVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f2024a.c();
        }
    }

    public ActionBarContextView(Context context) {
        this(context, null);
    }

    private void i() {
        if (this.f2017n == null) {
            LayoutInflater.from(getContext()).inflate(g.g.f6888a, this);
            LinearLayout linearLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.f2017n = linearLayout;
            this.f2018o = (TextView) linearLayout.findViewById(g.f.f6866e);
            this.f2019p = (TextView) this.f2017n.findViewById(g.f.f6865d);
            if (this.f2020q != 0) {
                this.f2018o.setTextAppearance(getContext(), this.f2020q);
            }
            if (this.f2021r != 0) {
                this.f2019p.setTextAppearance(getContext(), this.f2021r);
            }
        }
        this.f2018o.setText(this.f2012i);
        this.f2019p.setText(this.f2013j);
        boolean z7 = !TextUtils.isEmpty(this.f2012i);
        boolean z8 = !TextUtils.isEmpty(this.f2013j);
        int i8 = 0;
        this.f2019p.setVisibility(z8 ? 0 : 8);
        LinearLayout linearLayout2 = this.f2017n;
        if (!z7 && !z8) {
            i8 = 8;
        }
        linearLayout2.setVisibility(i8);
        if (this.f2017n.getParent() == null) {
            addView(this.f2017n);
        }
    }

    @Override // androidx.appcompat.widget.a
    public /* bridge */ /* synthetic */ androidx.core.view.x0 f(int i8, long j8) {
        return super.f(i8, j8);
    }

    public void g() {
        if (this.f2014k == null) {
            k();
        }
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    @Override // androidx.appcompat.widget.a
    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    @Override // androidx.appcompat.widget.a
    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    public CharSequence getSubtitle() {
        return this.f2013j;
    }

    public CharSequence getTitle() {
        return this.f2012i;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void h(androidx.appcompat.view.b r4) {
        /*
            r3 = this;
            android.view.View r0 = r3.f2014k
            if (r0 != 0) goto L19
            android.content.Context r0 = r3.getContext()
            android.view.LayoutInflater r0 = android.view.LayoutInflater.from(r0)
            int r1 = r3.f2023t
            r2 = 0
            android.view.View r0 = r0.inflate(r1, r3, r2)
            r3.f2014k = r0
        L15:
            r3.addView(r0)
            goto L22
        L19:
            android.view.ViewParent r0 = r0.getParent()
            if (r0 != 0) goto L22
            android.view.View r0 = r3.f2014k
            goto L15
        L22:
            android.view.View r0 = r3.f2014k
            int r1 = g.f.f6870i
            android.view.View r0 = r0.findViewById(r1)
            r3.f2015l = r0
            androidx.appcompat.widget.ActionBarContextView$a r1 = new androidx.appcompat.widget.ActionBarContextView$a
            r1.<init>(r4)
            r0.setOnClickListener(r1)
            android.view.Menu r4 = r4.e()
            androidx.appcompat.view.menu.g r4 = (androidx.appcompat.view.menu.g) r4
            androidx.appcompat.widget.c r0 = r3.f2180d
            if (r0 == 0) goto L41
            r0.y()
        L41:
            androidx.appcompat.widget.c r0 = new androidx.appcompat.widget.c
            android.content.Context r1 = r3.getContext()
            r0.<init>(r1)
            r3.f2180d = r0
            r1 = 1
            r0.J(r1)
            android.view.ViewGroup$LayoutParams r0 = new android.view.ViewGroup$LayoutParams
            r1 = -2
            r2 = -1
            r0.<init>(r1, r2)
            androidx.appcompat.widget.c r1 = r3.f2180d
            android.content.Context r2 = r3.f2178b
            r4.c(r1, r2)
            androidx.appcompat.widget.c r4 = r3.f2180d
            androidx.appcompat.view.menu.n r4 = r4.o(r3)
            androidx.appcompat.widget.ActionMenuView r4 = (androidx.appcompat.widget.ActionMenuView) r4
            r3.f2179c = r4
            r1 = 0
            androidx.core.view.p0.Q(r4, r1)
            androidx.appcompat.widget.ActionMenuView r4 = r3.f2179c
            r3.addView(r4, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActionBarContextView.h(androidx.appcompat.view.b):void");
    }

    public boolean j() {
        return this.f2022s;
    }

    public void k() {
        removeAllViews();
        this.f2016m = null;
        this.f2179c = null;
        this.f2180d = null;
        View view = this.f2015l;
        if (view != null) {
            view.setOnClickListener(null);
        }
    }

    public boolean l() {
        c cVar = this.f2180d;
        if (cVar != null) {
            return cVar.K();
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c cVar = this.f2180d;
        if (cVar != null) {
            cVar.B();
            this.f2180d.C();
        }
    }

    @Override // androidx.appcompat.widget.a, android.view.View
    public /* bridge */ /* synthetic */ boolean onHoverEvent(MotionEvent motionEvent) {
        return super.onHoverEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        boolean b8 = i1.b(this);
        int paddingRight = b8 ? (i10 - i8) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i11 - i9) - getPaddingTop()) - getPaddingBottom();
        View view = this.f2014k;
        if (view != null && view.getVisibility() != 8) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f2014k.getLayoutParams();
            int i12 = b8 ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            int i13 = b8 ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            int d8 = androidx.appcompat.widget.a.d(paddingRight, i12, b8);
            paddingRight = androidx.appcompat.widget.a.d(d8 + e(this.f2014k, d8, paddingTop, paddingTop2, b8), i13, b8);
        }
        int i14 = paddingRight;
        LinearLayout linearLayout = this.f2017n;
        if (linearLayout != null && this.f2016m == null && linearLayout.getVisibility() != 8) {
            i14 += e(this.f2017n, i14, paddingTop, paddingTop2, b8);
        }
        int i15 = i14;
        View view2 = this.f2016m;
        if (view2 != null) {
            e(view2, i15, paddingTop, paddingTop2, b8);
        }
        int paddingLeft = b8 ? getPaddingLeft() : (i10 - i8) - getPaddingRight();
        ActionMenuView actionMenuView = this.f2179c;
        if (actionMenuView != null) {
            e(actionMenuView, paddingLeft, paddingTop, paddingTop2, !b8);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i8, int i9) {
        if (View.MeasureSpec.getMode(i8) != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with android:layout_width=\"match_parent\" (or fill_parent)");
        } else if (View.MeasureSpec.getMode(i9) == 0) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with android:layout_height=\"wrap_content\"");
        } else {
            int size = View.MeasureSpec.getSize(i8);
            int i10 = this.f2181e;
            if (i10 <= 0) {
                i10 = View.MeasureSpec.getSize(i9);
            }
            int paddingTop = getPaddingTop() + getPaddingBottom();
            int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
            int i11 = i10 - paddingTop;
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i11, Integer.MIN_VALUE);
            View view = this.f2014k;
            if (view != null) {
                int c8 = c(view, paddingLeft, makeMeasureSpec, 0);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f2014k.getLayoutParams();
                paddingLeft = c8 - (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
            }
            ActionMenuView actionMenuView = this.f2179c;
            if (actionMenuView != null && actionMenuView.getParent() == this) {
                paddingLeft = c(this.f2179c, paddingLeft, makeMeasureSpec, 0);
            }
            LinearLayout linearLayout = this.f2017n;
            if (linearLayout != null && this.f2016m == null) {
                if (this.f2022s) {
                    this.f2017n.measure(View.MeasureSpec.makeMeasureSpec(0, 0), makeMeasureSpec);
                    int measuredWidth = this.f2017n.getMeasuredWidth();
                    boolean z7 = measuredWidth <= paddingLeft;
                    if (z7) {
                        paddingLeft -= measuredWidth;
                    }
                    this.f2017n.setVisibility(z7 ? 0 : 8);
                } else {
                    paddingLeft = c(linearLayout, paddingLeft, makeMeasureSpec, 0);
                }
            }
            View view2 = this.f2016m;
            if (view2 != null) {
                ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                int i12 = layoutParams.width;
                int i13 = i12 != -2 ? 1073741824 : Integer.MIN_VALUE;
                if (i12 >= 0) {
                    paddingLeft = Math.min(i12, paddingLeft);
                }
                int i14 = layoutParams.height;
                int i15 = i14 == -2 ? Integer.MIN_VALUE : 1073741824;
                if (i14 >= 0) {
                    i11 = Math.min(i14, i11);
                }
                this.f2016m.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i13), View.MeasureSpec.makeMeasureSpec(i11, i15));
            }
            if (this.f2181e > 0) {
                setMeasuredDimension(size, i10);
                return;
            }
            int childCount = getChildCount();
            int i16 = 0;
            for (int i17 = 0; i17 < childCount; i17++) {
                int measuredHeight = getChildAt(i17).getMeasuredHeight() + paddingTop;
                if (measuredHeight > i16) {
                    i16 = measuredHeight;
                }
            }
            setMeasuredDimension(size, i16);
        }
    }

    @Override // androidx.appcompat.widget.a, android.view.View
    public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    @Override // androidx.appcompat.widget.a
    public void setContentHeight(int i8) {
        this.f2181e = i8;
    }

    public void setCustomView(View view) {
        LinearLayout linearLayout;
        View view2 = this.f2016m;
        if (view2 != null) {
            removeView(view2);
        }
        this.f2016m = view;
        if (view != null && (linearLayout = this.f2017n) != null) {
            removeView(linearLayout);
            this.f2017n = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.f2013j = charSequence;
        i();
    }

    public void setTitle(CharSequence charSequence) {
        this.f2012i = charSequence;
        i();
        androidx.core.view.p0.P(this, charSequence);
    }

    public void setTitleOptional(boolean z7) {
        if (z7 != this.f2022s) {
            requestLayout();
        }
        this.f2022s = z7;
    }

    @Override // androidx.appcompat.widget.a, android.view.View
    public /* bridge */ /* synthetic */ void setVisibility(int i8) {
        super.setVisibility(i8);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, g.a.f6798g);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        b1 u7 = b1.u(context, attributeSet, g.j.f7043y, i8, 0);
        androidx.core.view.p0.Q(this, u7.f(g.j.f7048z));
        this.f2020q = u7.m(g.j.D, 0);
        this.f2021r = u7.m(g.j.C, 0);
        this.f2181e = u7.l(g.j.B, 0);
        this.f2023t = u7.m(g.j.A, g.g.f6891d);
        u7.v();
    }
}
