package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
/* loaded from: classes.dex */
public abstract class o0 extends ViewGroup {

    /* renamed from: a  reason: collision with root package name */
    private boolean f2407a;

    /* renamed from: b  reason: collision with root package name */
    private int f2408b;

    /* renamed from: c  reason: collision with root package name */
    private int f2409c;

    /* renamed from: d  reason: collision with root package name */
    private int f2410d;

    /* renamed from: e  reason: collision with root package name */
    private int f2411e;

    /* renamed from: f  reason: collision with root package name */
    private int f2412f;

    /* renamed from: g  reason: collision with root package name */
    private float f2413g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f2414h;

    /* renamed from: i  reason: collision with root package name */
    private int[] f2415i;

    /* renamed from: j  reason: collision with root package name */
    private int[] f2416j;

    /* renamed from: k  reason: collision with root package name */
    private Drawable f2417k;

    /* renamed from: l  reason: collision with root package name */
    private int f2418l;

    /* renamed from: m  reason: collision with root package name */
    private int f2419m;

    /* renamed from: n  reason: collision with root package name */
    private int f2420n;

    /* renamed from: o  reason: collision with root package name */
    private int f2421o;

    /* loaded from: classes.dex */
    public static class a extends LinearLayout.LayoutParams {
        public a(int i8, int i9) {
            super(i8, i9);
        }

        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public o0(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void i(int i8, int i9) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i10 = 0; i10 < i8; i10++) {
            View q8 = q(i10);
            if (q8.getVisibility() != 8) {
                a aVar = (a) q8.getLayoutParams();
                if (((LinearLayout.LayoutParams) aVar).height == -1) {
                    int i11 = ((LinearLayout.LayoutParams) aVar).width;
                    ((LinearLayout.LayoutParams) aVar).width = q8.getMeasuredWidth();
                    measureChildWithMargins(q8, i9, 0, makeMeasureSpec, 0);
                    ((LinearLayout.LayoutParams) aVar).width = i11;
                }
            }
        }
    }

    private void j(int i8, int i9) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i10 = 0; i10 < i8; i10++) {
            View q8 = q(i10);
            if (q8.getVisibility() != 8) {
                a aVar = (a) q8.getLayoutParams();
                if (((LinearLayout.LayoutParams) aVar).width == -1) {
                    int i11 = ((LinearLayout.LayoutParams) aVar).height;
                    ((LinearLayout.LayoutParams) aVar).height = q8.getMeasuredHeight();
                    measureChildWithMargins(q8, makeMeasureSpec, 0, i9, 0);
                    ((LinearLayout.LayoutParams) aVar).height = i11;
                }
            }
        }
    }

    private void y(View view, int i8, int i9, int i10, int i11) {
        view.layout(i8, i9, i10 + i8, i11 + i9);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof a;
    }

    void e(Canvas canvas) {
        int right;
        int left;
        int i8;
        int virtualChildCount = getVirtualChildCount();
        boolean b8 = i1.b(this);
        for (int i9 = 0; i9 < virtualChildCount; i9++) {
            View q8 = q(i9);
            if (q8 != null && q8.getVisibility() != 8 && r(i9)) {
                a aVar = (a) q8.getLayoutParams();
                h(canvas, b8 ? q8.getRight() + ((LinearLayout.LayoutParams) aVar).rightMargin : (q8.getLeft() - ((LinearLayout.LayoutParams) aVar).leftMargin) - this.f2418l);
            }
        }
        if (r(virtualChildCount)) {
            View q9 = q(virtualChildCount - 1);
            if (q9 != null) {
                a aVar2 = (a) q9.getLayoutParams();
                if (b8) {
                    left = q9.getLeft();
                    i8 = ((LinearLayout.LayoutParams) aVar2).leftMargin;
                    right = (left - i8) - this.f2418l;
                } else {
                    right = q9.getRight() + ((LinearLayout.LayoutParams) aVar2).rightMargin;
                }
            } else if (b8) {
                right = getPaddingLeft();
            } else {
                left = getWidth();
                i8 = getPaddingRight();
                right = (left - i8) - this.f2418l;
            }
            h(canvas, right);
        }
    }

    void f(Canvas canvas) {
        int virtualChildCount = getVirtualChildCount();
        for (int i8 = 0; i8 < virtualChildCount; i8++) {
            View q8 = q(i8);
            if (q8 != null && q8.getVisibility() != 8 && r(i8)) {
                g(canvas, (q8.getTop() - ((LinearLayout.LayoutParams) ((a) q8.getLayoutParams())).topMargin) - this.f2419m);
            }
        }
        if (r(virtualChildCount)) {
            View q9 = q(virtualChildCount - 1);
            g(canvas, q9 == null ? (getHeight() - getPaddingBottom()) - this.f2419m : q9.getBottom() + ((LinearLayout.LayoutParams) ((a) q9.getLayoutParams())).bottomMargin);
        }
    }

    void g(Canvas canvas, int i8) {
        this.f2417k.setBounds(getPaddingLeft() + this.f2421o, i8, (getWidth() - getPaddingRight()) - this.f2421o, this.f2419m + i8);
        this.f2417k.draw(canvas);
    }

    @Override // android.view.View
    public int getBaseline() {
        int i8;
        if (this.f2408b < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i9 = this.f2408b;
        if (childCount > i9) {
            View childAt = getChildAt(i9);
            int baseline = childAt.getBaseline();
            if (baseline == -1) {
                if (this.f2408b == 0) {
                    return -1;
                }
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
            }
            int i10 = this.f2409c;
            if (this.f2410d == 1 && (i8 = this.f2411e & 112) != 48) {
                if (i8 == 16) {
                    i10 += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.f2412f) / 2;
                } else if (i8 == 80) {
                    i10 = ((getBottom() - getTop()) - getPaddingBottom()) - this.f2412f;
                }
            }
            return i10 + ((LinearLayout.LayoutParams) ((a) childAt.getLayoutParams())).topMargin + baseline;
        }
        throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
    }

    public int getBaselineAlignedChildIndex() {
        return this.f2408b;
    }

    public Drawable getDividerDrawable() {
        return this.f2417k;
    }

    public int getDividerPadding() {
        return this.f2421o;
    }

    public int getDividerWidth() {
        return this.f2418l;
    }

    public int getGravity() {
        return this.f2411e;
    }

    public int getOrientation() {
        return this.f2410d;
    }

    public int getShowDividers() {
        return this.f2420n;
    }

    int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.f2413g;
    }

    void h(Canvas canvas, int i8) {
        this.f2417k.setBounds(i8, getPaddingTop() + this.f2421o, this.f2418l + i8, (getHeight() - getPaddingBottom()) - this.f2421o);
        this.f2417k.draw(canvas);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* renamed from: k */
    public a generateDefaultLayoutParams() {
        int i8 = this.f2410d;
        if (i8 == 0) {
            return new a(-2, -2);
        }
        if (i8 == 1) {
            return new a(-1, -2);
        }
        return null;
    }

    @Override // android.view.ViewGroup
    /* renamed from: l */
    public a generateLayoutParams(AttributeSet attributeSet) {
        return new a(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* renamed from: m */
    public a generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new a(layoutParams);
    }

    int n(View view, int i8) {
        return 0;
    }

    int o(View view) {
        return 0;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.f2417k == null) {
            return;
        }
        if (this.f2410d == 1) {
            f(canvas);
        } else {
            e(canvas);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        if (this.f2410d == 1) {
            t(i8, i9, i10, i11);
        } else {
            s(i8, i9, i10, i11);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onMeasure(int i8, int i9) {
        if (this.f2410d == 1) {
            x(i8, i9);
        } else {
            v(i8, i9);
        }
    }

    int p(View view) {
        return 0;
    }

    View q(int i8) {
        return getChildAt(i8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean r(int i8) {
        if (i8 == 0) {
            return (this.f2420n & 1) != 0;
        } else if (i8 == getChildCount()) {
            return (this.f2420n & 4) != 0;
        } else if ((this.f2420n & 2) != 0) {
            for (int i9 = i8 - 1; i9 >= 0; i9--) {
                if (getChildAt(i9).getVisibility() != 8) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ff  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void s(int r25, int r26, int r27, int r28) {
        /*
            Method dump skipped, instructions count: 330
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.o0.s(int, int, int, int):void");
    }

    public void setBaselineAligned(boolean z7) {
        this.f2407a = z7;
    }

    public void setBaselineAlignedChildIndex(int i8) {
        if (i8 >= 0 && i8 < getChildCount()) {
            this.f2408b = i8;
            return;
        }
        throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable == this.f2417k) {
            return;
        }
        this.f2417k = drawable;
        if (drawable != null) {
            this.f2418l = drawable.getIntrinsicWidth();
            this.f2419m = drawable.getIntrinsicHeight();
        } else {
            this.f2418l = 0;
            this.f2419m = 0;
        }
        setWillNotDraw(drawable == null);
        requestLayout();
    }

    public void setDividerPadding(int i8) {
        this.f2421o = i8;
    }

    public void setGravity(int i8) {
        if (this.f2411e != i8) {
            if ((8388615 & i8) == 0) {
                i8 |= 8388611;
            }
            if ((i8 & 112) == 0) {
                i8 |= 48;
            }
            this.f2411e = i8;
            requestLayout();
        }
    }

    public void setHorizontalGravity(int i8) {
        int i9 = i8 & 8388615;
        int i10 = this.f2411e;
        if ((8388615 & i10) != i9) {
            this.f2411e = i9 | ((-8388616) & i10);
            requestLayout();
        }
    }

    public void setMeasureWithLargestChildEnabled(boolean z7) {
        this.f2414h = z7;
    }

    public void setOrientation(int i8) {
        if (this.f2410d != i8) {
            this.f2410d = i8;
            requestLayout();
        }
    }

    public void setShowDividers(int i8) {
        if (i8 != this.f2420n) {
            requestLayout();
        }
        this.f2420n = i8;
    }

    public void setVerticalGravity(int i8) {
        int i9 = i8 & 112;
        int i10 = this.f2411e;
        if ((i10 & 112) != i9) {
            this.f2411e = i9 | (i10 & (-113));
            requestLayout();
        }
    }

    public void setWeightSum(float f8) {
        this.f2413g = Math.max(0.0f, f8);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x009d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void t(int r18, int r19, int r20, int r21) {
        /*
            r17 = this;
            r6 = r17
            int r7 = r17.getPaddingLeft()
            int r0 = r20 - r18
            int r1 = r17.getPaddingRight()
            int r8 = r0 - r1
            int r0 = r0 - r7
            int r1 = r17.getPaddingRight()
            int r9 = r0 - r1
            int r10 = r17.getVirtualChildCount()
            int r0 = r6.f2411e
            r1 = r0 & 112(0x70, float:1.57E-43)
            r2 = 8388615(0x800007, float:1.1754953E-38)
            r11 = r0 & r2
            r0 = 16
            if (r1 == r0) goto L3b
            r0 = 80
            if (r1 == r0) goto L2f
            int r0 = r17.getPaddingTop()
            goto L47
        L2f:
            int r0 = r17.getPaddingTop()
            int r0 = r0 + r21
            int r0 = r0 - r19
            int r1 = r6.f2412f
            int r0 = r0 - r1
            goto L47
        L3b:
            int r0 = r17.getPaddingTop()
            int r1 = r21 - r19
            int r2 = r6.f2412f
            int r1 = r1 - r2
            int r1 = r1 / 2
            int r0 = r0 + r1
        L47:
            r1 = 0
            r12 = r1
        L49:
            if (r12 >= r10) goto Lc8
            android.view.View r13 = r6.q(r12)
            r14 = 1
            if (r13 != 0) goto L59
            int r1 = r6.w(r12)
            int r0 = r0 + r1
            goto Lc5
        L59:
            int r1 = r13.getVisibility()
            r2 = 8
            if (r1 == r2) goto Lc5
            int r4 = r13.getMeasuredWidth()
            int r15 = r13.getMeasuredHeight()
            android.view.ViewGroup$LayoutParams r1 = r13.getLayoutParams()
            r5 = r1
            androidx.appcompat.widget.o0$a r5 = (androidx.appcompat.widget.o0.a) r5
            int r1 = r5.gravity
            if (r1 >= 0) goto L75
            r1 = r11
        L75:
            int r2 = androidx.core.view.p0.p(r17)
            int r1 = androidx.core.view.s.a(r1, r2)
            r1 = r1 & 7
            if (r1 == r14) goto L8b
            r2 = 5
            if (r1 == r2) goto L88
            int r1 = r5.leftMargin
            int r1 = r1 + r7
            goto L96
        L88:
            int r1 = r8 - r4
            goto L93
        L8b:
            int r1 = r9 - r4
            int r1 = r1 / 2
            int r1 = r1 + r7
            int r2 = r5.leftMargin
            int r1 = r1 + r2
        L93:
            int r2 = r5.rightMargin
            int r1 = r1 - r2
        L96:
            r2 = r1
            boolean r1 = r6.r(r12)
            if (r1 == 0) goto La0
            int r1 = r6.f2419m
            int r0 = r0 + r1
        La0:
            int r1 = r5.topMargin
            int r16 = r0 + r1
            int r0 = r6.o(r13)
            int r3 = r16 + r0
            r0 = r17
            r1 = r13
            r14 = r5
            r5 = r15
            r0.y(r1, r2, r3, r4, r5)
            int r0 = r14.bottomMargin
            int r15 = r15 + r0
            int r0 = r6.p(r13)
            int r15 = r15 + r0
            int r16 = r16 + r15
            int r0 = r6.n(r13, r12)
            int r12 = r12 + r0
            r0 = r16
            r1 = 1
            goto Lc6
        Lc5:
            r1 = r14
        Lc6:
            int r12 = r12 + r1
            goto L49
        Lc8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.o0.t(int, int, int, int):void");
    }

    void u(View view, int i8, int i9, int i10, int i11, int i12) {
        measureChildWithMargins(view, i9, i10, i11, i12);
    }

    /* JADX WARN: Code restructure failed: missing block: B:164:0x03a4, code lost:
        if (r8 > 0) goto L176;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x03af, code lost:
        if (r8 < 0) goto L175;
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x03b1, code lost:
        r8 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:170:0x03b2, code lost:
        r14.measure(android.view.View.MeasureSpec.makeMeasureSpec(r8, r3), r0);
        r9 = android.view.View.combineMeasuredStates(r9, r14.getMeasuredState() & (-16777216));
        r0 = r0;
        r3 = r2;
     */
    /* JADX WARN: Removed duplicated region for block: B:198:0x043f  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01d2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void v(int r39, int r40) {
        /*
            Method dump skipped, instructions count: 1272
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.o0.v(int, int):void");
    }

    int w(int i8) {
        return 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:131:0x02cf, code lost:
        if (r15 > 0) goto L150;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x02da, code lost:
        if (r15 < 0) goto L149;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x02dc, code lost:
        r15 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x02dd, code lost:
        r13.measure(r0, android.view.View.MeasureSpec.makeMeasureSpec(r15, r10));
        r1 = android.view.View.combineMeasuredStates(r1, r13.getMeasuredState() & (-256));
        r0 = r0;
     */
    /* JADX WARN: Removed duplicated region for block: B:148:0x031b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void x(int r34, int r35) {
        /*
            Method dump skipped, instructions count: 904
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.o0.x(int, int):void");
    }

    public o0(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f2407a = true;
        this.f2408b = -1;
        this.f2409c = 0;
        this.f2411e = 8388659;
        b1 u7 = b1.u(context, attributeSet, g.j.f6925a1, i8, 0);
        androidx.core.view.p0.L(this, context, g.j.f6925a1, attributeSet, u7.q(), i8, 0);
        int j8 = u7.j(g.j.f6935c1, -1);
        if (j8 >= 0) {
            setOrientation(j8);
        }
        int j9 = u7.j(g.j.f6930b1, -1);
        if (j9 >= 0) {
            setGravity(j9);
        }
        boolean a8 = u7.a(g.j.f6940d1, true);
        if (!a8) {
            setBaselineAligned(a8);
        }
        this.f2413g = u7.h(g.j.f6950f1, -1.0f);
        this.f2408b = u7.j(g.j.f6945e1, -1);
        this.f2414h = u7.a(g.j.f6965i1, false);
        setDividerDrawable(u7.f(g.j.f6955g1));
        this.f2420n = u7.j(g.j.f6970j1, 0);
        this.f2421o = u7.e(g.j.f6960h1, 0);
        u7.v();
    }
}
