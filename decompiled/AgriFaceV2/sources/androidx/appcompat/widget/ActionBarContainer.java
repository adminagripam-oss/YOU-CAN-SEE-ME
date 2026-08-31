package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
/* loaded from: classes.dex */
public class ActionBarContainer extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private boolean f2002a;

    /* renamed from: b  reason: collision with root package name */
    private View f2003b;

    /* renamed from: c  reason: collision with root package name */
    private View f2004c;

    /* renamed from: d  reason: collision with root package name */
    private View f2005d;

    /* renamed from: e  reason: collision with root package name */
    Drawable f2006e;

    /* renamed from: f  reason: collision with root package name */
    Drawable f2007f;

    /* renamed from: g  reason: collision with root package name */
    Drawable f2008g;

    /* renamed from: h  reason: collision with root package name */
    boolean f2009h;

    /* renamed from: i  reason: collision with root package name */
    boolean f2010i;

    /* renamed from: j  reason: collision with root package name */
    private int f2011j;

    /* loaded from: classes.dex */
    private static class a {
        public static void a(ActionBarContainer actionBarContainer) {
            actionBarContainer.invalidateOutline();
        }
    }

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        androidx.core.view.p0.Q(this, new b(this));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, g.j.f6923a);
        this.f2006e = obtainStyledAttributes.getDrawable(g.j.f6928b);
        this.f2007f = obtainStyledAttributes.getDrawable(g.j.f6938d);
        this.f2011j = obtainStyledAttributes.getDimensionPixelSize(g.j.f6968j, -1);
        boolean z7 = true;
        if (getId() == g.f.G) {
            this.f2009h = true;
            this.f2008g = obtainStyledAttributes.getDrawable(g.j.f6933c);
        }
        obtainStyledAttributes.recycle();
        if (!this.f2009h ? this.f2006e != null || this.f2007f != null : this.f2008g != null) {
            z7 = false;
        }
        setWillNotDraw(z7);
    }

    private int a(View view) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        return view.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    private boolean b(View view) {
        return view == null || view.getVisibility() == 8 || view.getMeasuredHeight() == 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f2006e;
        if (drawable != null && drawable.isStateful()) {
            this.f2006e.setState(getDrawableState());
        }
        Drawable drawable2 = this.f2007f;
        if (drawable2 != null && drawable2.isStateful()) {
            this.f2007f.setState(getDrawableState());
        }
        Drawable drawable3 = this.f2008g;
        if (drawable3 == null || !drawable3.isStateful()) {
            return;
        }
        this.f2008g.setState(getDrawableState());
    }

    public View getTabContainer() {
        return this.f2003b;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f2006e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.f2007f;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        Drawable drawable3 = this.f2008g;
        if (drawable3 != null) {
            drawable3.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f2004c = findViewById(g.f.f6862a);
        this.f2005d = findViewById(g.f.f6867f);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f2002a || super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        Drawable drawable;
        Drawable drawable2;
        int left;
        int top;
        int right;
        View view;
        super.onLayout(z7, i8, i9, i10, i11);
        View view2 = this.f2003b;
        boolean z8 = true;
        boolean z9 = false;
        boolean z10 = (view2 == null || view2.getVisibility() == 8) ? false : true;
        if (view2 != null && view2.getVisibility() != 8) {
            int measuredHeight = getMeasuredHeight();
            int i12 = ((FrameLayout.LayoutParams) view2.getLayoutParams()).bottomMargin;
            view2.layout(i8, (measuredHeight - view2.getMeasuredHeight()) - i12, i10, measuredHeight - i12);
        }
        if (this.f2009h) {
            Drawable drawable3 = this.f2008g;
            if (drawable3 != null) {
                drawable3.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            }
            z8 = z9;
        } else {
            if (this.f2006e != null) {
                if (this.f2004c.getVisibility() == 0) {
                    drawable2 = this.f2006e;
                    left = this.f2004c.getLeft();
                    top = this.f2004c.getTop();
                    right = this.f2004c.getRight();
                    view = this.f2004c;
                } else {
                    View view3 = this.f2005d;
                    if (view3 == null || view3.getVisibility() != 0) {
                        this.f2006e.setBounds(0, 0, 0, 0);
                        z9 = true;
                    } else {
                        drawable2 = this.f2006e;
                        left = this.f2005d.getLeft();
                        top = this.f2005d.getTop();
                        right = this.f2005d.getRight();
                        view = this.f2005d;
                    }
                }
                drawable2.setBounds(left, top, right, view.getBottom());
                z9 = true;
            }
            this.f2010i = z10;
            if (z10 && (drawable = this.f2007f) != null) {
                drawable.setBounds(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
            }
            z8 = z9;
        }
        if (z8) {
            invalidate();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005a  */
    @Override // android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMeasure(int r4, int r5) {
        /*
            r3 = this;
            android.view.View r0 = r3.f2004c
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r0 != 0) goto L1c
            int r0 = android.view.View.MeasureSpec.getMode(r5)
            if (r0 != r1) goto L1c
            int r0 = r3.f2011j
            if (r0 < 0) goto L1c
            int r5 = android.view.View.MeasureSpec.getSize(r5)
            int r5 = java.lang.Math.min(r0, r5)
            int r5 = android.view.View.MeasureSpec.makeMeasureSpec(r5, r1)
        L1c:
            super.onMeasure(r4, r5)
            android.view.View r4 = r3.f2004c
            if (r4 != 0) goto L24
            return
        L24:
            int r4 = android.view.View.MeasureSpec.getMode(r5)
            android.view.View r0 = r3.f2003b
            if (r0 == 0) goto L6f
            int r0 = r0.getVisibility()
            r2 = 8
            if (r0 == r2) goto L6f
            r0 = 1073741824(0x40000000, float:2.0)
            if (r4 == r0) goto L6f
            android.view.View r0 = r3.f2004c
            boolean r0 = r3.b(r0)
            if (r0 != 0) goto L47
            android.view.View r0 = r3.f2004c
        L42:
            int r0 = r3.a(r0)
            goto L53
        L47:
            android.view.View r0 = r3.f2005d
            boolean r0 = r3.b(r0)
            if (r0 != 0) goto L52
            android.view.View r0 = r3.f2005d
            goto L42
        L52:
            r0 = 0
        L53:
            if (r4 != r1) goto L5a
            int r4 = android.view.View.MeasureSpec.getSize(r5)
            goto L5d
        L5a:
            r4 = 2147483647(0x7fffffff, float:NaN)
        L5d:
            int r5 = r3.getMeasuredWidth()
            android.view.View r1 = r3.f2003b
            int r1 = r3.a(r1)
            int r0 = r0 + r1
            int r4 = java.lang.Math.min(r0, r4)
            r3.setMeasuredDimension(r5, r4)
        L6f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActionBarContainer.onMeasure(int, int):void");
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public void setPrimaryBackground(Drawable drawable) {
        Drawable drawable2 = this.f2006e;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.f2006e);
        }
        this.f2006e = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            View view = this.f2004c;
            if (view != null) {
                this.f2006e.setBounds(view.getLeft(), this.f2004c.getTop(), this.f2004c.getRight(), this.f2004c.getBottom());
            }
        }
        boolean z7 = true;
        if (!this.f2009h ? this.f2006e != null || this.f2007f != null : this.f2008g != null) {
            z7 = false;
        }
        setWillNotDraw(z7);
        invalidate();
        a.a(this);
    }

    public void setSplitBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.f2008g;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.f2008g);
        }
        this.f2008g = drawable;
        boolean z7 = false;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.f2009h && (drawable2 = this.f2008g) != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            }
        }
        if (!this.f2009h ? !(this.f2006e != null || this.f2007f != null) : this.f2008g == null) {
            z7 = true;
        }
        setWillNotDraw(z7);
        invalidate();
        a.a(this);
    }

    public void setStackedBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.f2007f;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.f2007f);
        }
        this.f2007f = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.f2010i && (drawable2 = this.f2007f) != null) {
                drawable2.setBounds(this.f2003b.getLeft(), this.f2003b.getTop(), this.f2003b.getRight(), this.f2003b.getBottom());
            }
        }
        boolean z7 = true;
        if (!this.f2009h ? this.f2006e != null || this.f2007f != null : this.f2008g != null) {
            z7 = false;
        }
        setWillNotDraw(z7);
        invalidate();
        a.a(this);
    }

    public void setTabContainer(v0 v0Var) {
        View view = this.f2003b;
        if (view != null) {
            removeView(view);
        }
        this.f2003b = v0Var;
    }

    public void setTransitioning(boolean z7) {
        this.f2002a = z7;
        setDescendantFocusability(z7 ? 393216 : 262144);
    }

    @Override // android.view.View
    public void setVisibility(int i8) {
        super.setVisibility(i8);
        boolean z7 = i8 == 0;
        Drawable drawable = this.f2006e;
        if (drawable != null) {
            drawable.setVisible(z7, false);
        }
        Drawable drawable2 = this.f2007f;
        if (drawable2 != null) {
            drawable2.setVisible(z7, false);
        }
        Drawable drawable3 = this.f2008g;
        if (drawable3 != null) {
            drawable3.setVisible(z7, false);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return (drawable == this.f2006e && !this.f2009h) || (drawable == this.f2007f && this.f2010i) || ((drawable == this.f2008g && this.f2009h) || super.verifyDrawable(drawable));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i8) {
        if (i8 != 0) {
            return super.startActionModeForChild(view, callback, i8);
        }
        return null;
    }
}
