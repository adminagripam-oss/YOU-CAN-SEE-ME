package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.view.menu.m;
import androidx.appcompat.widget.o0;
/* loaded from: classes.dex */
public class ActionMenuView extends o0 implements g.b, androidx.appcompat.view.menu.n {
    e A;

    /* renamed from: p  reason: collision with root package name */
    private androidx.appcompat.view.menu.g f2055p;

    /* renamed from: q  reason: collision with root package name */
    private Context f2056q;

    /* renamed from: r  reason: collision with root package name */
    private int f2057r;

    /* renamed from: s  reason: collision with root package name */
    private boolean f2058s;

    /* renamed from: t  reason: collision with root package name */
    private androidx.appcompat.widget.c f2059t;

    /* renamed from: u  reason: collision with root package name */
    private m.a f2060u;

    /* renamed from: v  reason: collision with root package name */
    g.a f2061v;

    /* renamed from: w  reason: collision with root package name */
    private boolean f2062w;

    /* renamed from: x  reason: collision with root package name */
    private int f2063x;

    /* renamed from: y  reason: collision with root package name */
    private int f2064y;

    /* renamed from: z  reason: collision with root package name */
    private int f2065z;

    /* loaded from: classes.dex */
    public interface a {
        boolean b();

        boolean c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b implements m.a {
        b() {
        }

        @Override // androidx.appcompat.view.menu.m.a
        public void b(androidx.appcompat.view.menu.g gVar, boolean z7) {
        }

        @Override // androidx.appcompat.view.menu.m.a
        public boolean c(androidx.appcompat.view.menu.g gVar) {
            return false;
        }
    }

    /* loaded from: classes.dex */
    public static class c extends o0.a {

        /* renamed from: a  reason: collision with root package name */
        public boolean f2066a;

        /* renamed from: b  reason: collision with root package name */
        public int f2067b;

        /* renamed from: c  reason: collision with root package name */
        public int f2068c;

        /* renamed from: d  reason: collision with root package name */
        public boolean f2069d;

        /* renamed from: e  reason: collision with root package name */
        public boolean f2070e;

        /* renamed from: f  reason: collision with root package name */
        boolean f2071f;

        public c(int i8, int i9) {
            super(i8, i9);
            this.f2066a = false;
        }

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public c(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public c(c cVar) {
            super(cVar);
            this.f2066a = cVar.f2066a;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class d implements g.a {
        d() {
        }

        @Override // androidx.appcompat.view.menu.g.a
        public boolean a(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
            e eVar = ActionMenuView.this.A;
            return eVar != null && eVar.onMenuItemClick(menuItem);
        }

        @Override // androidx.appcompat.view.menu.g.a
        public void b(androidx.appcompat.view.menu.g gVar) {
            g.a aVar = ActionMenuView.this.f2061v;
            if (aVar != null) {
                aVar.b(gVar);
            }
        }
    }

    /* loaded from: classes.dex */
    public interface e {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public ActionMenuView(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int J(View view, int i8, int i9, int i10, int i11) {
        int i12;
        c cVar = (c) view.getLayoutParams();
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i10) - i11, View.MeasureSpec.getMode(i10));
        ActionMenuItemView actionMenuItemView = view instanceof ActionMenuItemView ? (ActionMenuItemView) view : null;
        boolean z7 = true;
        boolean z8 = actionMenuItemView != null && actionMenuItemView.r();
        if (i9 > 0) {
            i12 = 2;
            if (!z8 || i9 >= 2) {
                view.measure(View.MeasureSpec.makeMeasureSpec(i9 * i8, Integer.MIN_VALUE), makeMeasureSpec);
                int measuredWidth = view.getMeasuredWidth();
                int i13 = measuredWidth / i8;
                if (measuredWidth % i8 != 0) {
                    i13++;
                }
                if (!z8 || i13 >= 2) {
                    i12 = i13;
                }
                if (!cVar.f2066a || !z8) {
                    z7 = false;
                }
                cVar.f2069d = z7;
                cVar.f2067b = i12;
                view.measure(View.MeasureSpec.makeMeasureSpec(i8 * i12, 1073741824), makeMeasureSpec);
                return i12;
            }
        }
        i12 = 0;
        if (!cVar.f2066a) {
        }
        z7 = false;
        cVar.f2069d = z7;
        cVar.f2067b = i12;
        view.measure(View.MeasureSpec.makeMeasureSpec(i8 * i12, 1073741824), makeMeasureSpec);
        return i12;
    }

    /* JADX WARN: Type inference failed for: r14v10 */
    /* JADX WARN: Type inference failed for: r14v11, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r14v14 */
    private void K(int i8, int i9) {
        int i10;
        int i11;
        boolean z7;
        int i12;
        int i13;
        boolean z8;
        boolean z9;
        int i14;
        ?? r14;
        int mode = View.MeasureSpec.getMode(i9);
        int size = View.MeasureSpec.getSize(i8);
        int size2 = View.MeasureSpec.getSize(i9);
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i9, paddingTop, -2);
        int i15 = size - paddingLeft;
        int i16 = this.f2064y;
        int i17 = i15 / i16;
        int i18 = i15 % i16;
        if (i17 == 0) {
            setMeasuredDimension(i15, 0);
            return;
        }
        int i19 = i16 + (i18 / i17);
        int childCount = getChildCount();
        int i20 = 0;
        int i21 = 0;
        boolean z10 = false;
        int i22 = 0;
        int i23 = 0;
        int i24 = 0;
        long j8 = 0;
        while (i21 < childCount) {
            View childAt = getChildAt(i21);
            int i25 = size2;
            if (childAt.getVisibility() != 8) {
                boolean z11 = childAt instanceof ActionMenuItemView;
                int i26 = i22 + 1;
                if (z11) {
                    int i27 = this.f2065z;
                    i14 = i26;
                    r14 = 0;
                    childAt.setPadding(i27, 0, i27, 0);
                } else {
                    i14 = i26;
                    r14 = 0;
                }
                c cVar = (c) childAt.getLayoutParams();
                cVar.f2071f = r14;
                cVar.f2068c = r14;
                cVar.f2067b = r14;
                cVar.f2069d = r14;
                ((LinearLayout.LayoutParams) cVar).leftMargin = r14;
                ((LinearLayout.LayoutParams) cVar).rightMargin = r14;
                cVar.f2070e = z11 && ((ActionMenuItemView) childAt).r();
                int J = J(childAt, i19, cVar.f2066a ? 1 : i17, childMeasureSpec, paddingTop);
                i23 = Math.max(i23, J);
                if (cVar.f2069d) {
                    i24++;
                }
                if (cVar.f2066a) {
                    z10 = true;
                }
                i17 -= J;
                i20 = Math.max(i20, childAt.getMeasuredHeight());
                if (J == 1) {
                    j8 |= 1 << i21;
                    i20 = i20;
                }
                i22 = i14;
            }
            i21++;
            size2 = i25;
        }
        int i28 = size2;
        boolean z12 = z10 && i22 == 2;
        boolean z13 = false;
        while (i24 > 0 && i17 > 0) {
            int i29 = Integer.MAX_VALUE;
            int i30 = 0;
            int i31 = 0;
            long j9 = 0;
            while (i31 < childCount) {
                boolean z14 = z13;
                c cVar2 = (c) getChildAt(i31).getLayoutParams();
                int i32 = i20;
                if (cVar2.f2069d) {
                    int i33 = cVar2.f2067b;
                    if (i33 < i29) {
                        j9 = 1 << i31;
                        i29 = i33;
                        i30 = 1;
                    } else if (i33 == i29) {
                        i30++;
                        j9 |= 1 << i31;
                    }
                }
                i31++;
                i20 = i32;
                z13 = z14;
            }
            z7 = z13;
            i12 = i20;
            j8 |= j9;
            if (i30 > i17) {
                i10 = mode;
                i11 = i15;
                break;
            }
            int i34 = i29 + 1;
            int i35 = 0;
            while (i35 < childCount) {
                View childAt2 = getChildAt(i35);
                c cVar3 = (c) childAt2.getLayoutParams();
                int i36 = i15;
                int i37 = mode;
                long j10 = 1 << i35;
                if ((j9 & j10) == 0) {
                    if (cVar3.f2067b == i34) {
                        j8 |= j10;
                    }
                    z9 = z12;
                } else {
                    if (z12 && cVar3.f2070e && i17 == 1) {
                        int i38 = this.f2065z;
                        z9 = z12;
                        childAt2.setPadding(i38 + i19, 0, i38, 0);
                    } else {
                        z9 = z12;
                    }
                    cVar3.f2067b++;
                    cVar3.f2071f = true;
                    i17--;
                }
                i35++;
                mode = i37;
                i15 = i36;
                z12 = z9;
            }
            i20 = i12;
            z13 = true;
        }
        i10 = mode;
        i11 = i15;
        z7 = z13;
        i12 = i20;
        boolean z15 = !z10 && i22 == 1;
        if (i17 <= 0 || j8 == 0 || (i17 >= i22 - 1 && !z15 && i23 <= 1)) {
            i13 = 0;
            z8 = z7;
        } else {
            float bitCount = Long.bitCount(j8);
            if (z15) {
                i13 = 0;
            } else {
                i13 = 0;
                if ((j8 & 1) != 0 && !((c) getChildAt(0).getLayoutParams()).f2070e) {
                    bitCount -= 0.5f;
                }
                int i39 = childCount - 1;
                if ((j8 & (1 << i39)) != 0 && !((c) getChildAt(i39).getLayoutParams()).f2070e) {
                    bitCount -= 0.5f;
                }
            }
            int i40 = bitCount > 0.0f ? (int) ((i17 * i19) / bitCount) : i13;
            z8 = z7;
            for (int i41 = i13; i41 < childCount; i41++) {
                if ((j8 & (1 << i41)) != 0) {
                    View childAt3 = getChildAt(i41);
                    c cVar4 = (c) childAt3.getLayoutParams();
                    if (childAt3 instanceof ActionMenuItemView) {
                        cVar4.f2068c = i40;
                        cVar4.f2071f = true;
                        if (i41 == 0 && !cVar4.f2070e) {
                            ((LinearLayout.LayoutParams) cVar4).leftMargin = (-i40) / 2;
                        }
                        z8 = true;
                    } else if (cVar4.f2066a) {
                        cVar4.f2068c = i40;
                        cVar4.f2071f = true;
                        ((LinearLayout.LayoutParams) cVar4).rightMargin = (-i40) / 2;
                        z8 = true;
                    } else {
                        if (i41 != 0) {
                            ((LinearLayout.LayoutParams) cVar4).leftMargin = i40 / 2;
                        }
                        if (i41 != childCount - 1) {
                            ((LinearLayout.LayoutParams) cVar4).rightMargin = i40 / 2;
                        }
                    }
                }
            }
        }
        if (z8) {
            for (int i42 = i13; i42 < childCount; i42++) {
                View childAt4 = getChildAt(i42);
                c cVar5 = (c) childAt4.getLayoutParams();
                if (cVar5.f2071f) {
                    childAt4.measure(View.MeasureSpec.makeMeasureSpec((cVar5.f2067b * i19) + cVar5.f2068c, 1073741824), childMeasureSpec);
                }
            }
        }
        setMeasuredDimension(i11, i10 != 1073741824 ? i12 : i28);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.o0
    /* renamed from: A */
    public c k() {
        c cVar = new c(-2, -2);
        ((LinearLayout.LayoutParams) cVar).gravity = 16;
        return cVar;
    }

    @Override // androidx.appcompat.widget.o0
    /* renamed from: B */
    public c l(AttributeSet attributeSet) {
        return new c(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.o0
    /* renamed from: C */
    public c m(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams != null) {
            c cVar = layoutParams instanceof c ? new c((c) layoutParams) : new c(layoutParams);
            if (((LinearLayout.LayoutParams) cVar).gravity <= 0) {
                ((LinearLayout.LayoutParams) cVar).gravity = 16;
            }
            return cVar;
        }
        return k();
    }

    public c D() {
        c k8 = k();
        k8.f2066a = true;
        return k8;
    }

    protected boolean E(int i8) {
        boolean z7 = false;
        if (i8 == 0) {
            return false;
        }
        View childAt = getChildAt(i8 - 1);
        View childAt2 = getChildAt(i8);
        if (i8 < getChildCount() && (childAt instanceof a)) {
            z7 = false | ((a) childAt).b();
        }
        return (i8 <= 0 || !(childAt2 instanceof a)) ? z7 : z7 | ((a) childAt2).c();
    }

    public boolean F() {
        androidx.appcompat.widget.c cVar = this.f2059t;
        return cVar != null && cVar.B();
    }

    public boolean G() {
        androidx.appcompat.widget.c cVar = this.f2059t;
        return cVar != null && cVar.D();
    }

    public boolean H() {
        androidx.appcompat.widget.c cVar = this.f2059t;
        return cVar != null && cVar.E();
    }

    public boolean I() {
        return this.f2058s;
    }

    public androidx.appcompat.view.menu.g L() {
        return this.f2055p;
    }

    public void M(m.a aVar, g.a aVar2) {
        this.f2060u = aVar;
        this.f2061v = aVar2;
    }

    public boolean N() {
        androidx.appcompat.widget.c cVar = this.f2059t;
        return cVar != null && cVar.K();
    }

    @Override // androidx.appcompat.view.menu.g.b
    public boolean a(androidx.appcompat.view.menu.i iVar) {
        return this.f2055p.L(iVar, 0);
    }

    @Override // androidx.appcompat.view.menu.n
    public void b(androidx.appcompat.view.menu.g gVar) {
        this.f2055p = gVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.o0, android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof c;
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    public Menu getMenu() {
        if (this.f2055p == null) {
            Context context = getContext();
            androidx.appcompat.view.menu.g gVar = new androidx.appcompat.view.menu.g(context);
            this.f2055p = gVar;
            gVar.R(new d());
            androidx.appcompat.widget.c cVar = new androidx.appcompat.widget.c(context);
            this.f2059t = cVar;
            cVar.J(true);
            androidx.appcompat.widget.c cVar2 = this.f2059t;
            m.a aVar = this.f2060u;
            if (aVar == null) {
                aVar = new b();
            }
            cVar2.k(aVar);
            this.f2055p.c(this.f2059t, this.f2056q);
            this.f2059t.H(this);
        }
        return this.f2055p;
    }

    public Drawable getOverflowIcon() {
        getMenu();
        return this.f2059t.A();
    }

    public int getPopupTheme() {
        return this.f2057r;
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        androidx.appcompat.widget.c cVar = this.f2059t;
        if (cVar != null) {
            cVar.f(false);
            if (this.f2059t.E()) {
                this.f2059t.B();
                this.f2059t.K();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        z();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.o0, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        int width;
        int i12;
        if (!this.f2062w) {
            super.onLayout(z7, i8, i9, i10, i11);
            return;
        }
        int childCount = getChildCount();
        int i13 = (i11 - i9) / 2;
        int dividerWidth = getDividerWidth();
        int i14 = i10 - i8;
        int paddingRight = (i14 - getPaddingRight()) - getPaddingLeft();
        boolean b8 = i1.b(this);
        int i15 = 0;
        int i16 = 0;
        for (int i17 = 0; i17 < childCount; i17++) {
            View childAt = getChildAt(i17);
            if (childAt.getVisibility() != 8) {
                c cVar = (c) childAt.getLayoutParams();
                if (cVar.f2066a) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (E(i17)) {
                        measuredWidth += dividerWidth;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (b8) {
                        i12 = getPaddingLeft() + ((LinearLayout.LayoutParams) cVar).leftMargin;
                        width = i12 + measuredWidth;
                    } else {
                        width = (getWidth() - getPaddingRight()) - ((LinearLayout.LayoutParams) cVar).rightMargin;
                        i12 = width - measuredWidth;
                    }
                    int i18 = i13 - (measuredHeight / 2);
                    childAt.layout(i12, i18, width, measuredHeight + i18);
                    paddingRight -= measuredWidth;
                    i15 = 1;
                } else {
                    paddingRight -= (childAt.getMeasuredWidth() + ((LinearLayout.LayoutParams) cVar).leftMargin) + ((LinearLayout.LayoutParams) cVar).rightMargin;
                    E(i17);
                    i16++;
                }
            }
        }
        if (childCount == 1 && i15 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int i19 = (i14 / 2) - (measuredWidth2 / 2);
            int i20 = i13 - (measuredHeight2 / 2);
            childAt2.layout(i19, i20, measuredWidth2 + i19, measuredHeight2 + i20);
            return;
        }
        int i21 = i16 - (i15 ^ 1);
        int max = Math.max(0, i21 > 0 ? paddingRight / i21 : 0);
        if (b8) {
            int width2 = getWidth() - getPaddingRight();
            for (int i22 = 0; i22 < childCount; i22++) {
                View childAt3 = getChildAt(i22);
                c cVar2 = (c) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !cVar2.f2066a) {
                    int i23 = width2 - ((LinearLayout.LayoutParams) cVar2).rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i24 = i13 - (measuredHeight3 / 2);
                    childAt3.layout(i23 - measuredWidth3, i24, i23, measuredHeight3 + i24);
                    width2 = i23 - ((measuredWidth3 + ((LinearLayout.LayoutParams) cVar2).leftMargin) + max);
                }
            }
            return;
        }
        int paddingLeft = getPaddingLeft();
        for (int i25 = 0; i25 < childCount; i25++) {
            View childAt4 = getChildAt(i25);
            c cVar3 = (c) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !cVar3.f2066a) {
                int i26 = paddingLeft + ((LinearLayout.LayoutParams) cVar3).leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i27 = i13 - (measuredHeight4 / 2);
                childAt4.layout(i26, i27, i26 + measuredWidth4, measuredHeight4 + i27);
                paddingLeft = i26 + measuredWidth4 + ((LinearLayout.LayoutParams) cVar3).rightMargin + max;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.o0, android.view.View
    public void onMeasure(int i8, int i9) {
        androidx.appcompat.view.menu.g gVar;
        boolean z7 = this.f2062w;
        boolean z8 = View.MeasureSpec.getMode(i8) == 1073741824;
        this.f2062w = z8;
        if (z7 != z8) {
            this.f2063x = 0;
        }
        int size = View.MeasureSpec.getSize(i8);
        if (this.f2062w && (gVar = this.f2055p) != null && size != this.f2063x) {
            this.f2063x = size;
            gVar.K(true);
        }
        int childCount = getChildCount();
        if (this.f2062w && childCount > 0) {
            K(i8, i9);
            return;
        }
        for (int i10 = 0; i10 < childCount; i10++) {
            c cVar = (c) getChildAt(i10).getLayoutParams();
            ((LinearLayout.LayoutParams) cVar).rightMargin = 0;
            ((LinearLayout.LayoutParams) cVar).leftMargin = 0;
        }
        super.onMeasure(i8, i9);
    }

    public void setExpandedActionViewsExclusive(boolean z7) {
        this.f2059t.G(z7);
    }

    public void setOnMenuItemClickListener(e eVar) {
        this.A = eVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        getMenu();
        this.f2059t.I(drawable);
    }

    public void setOverflowReserved(boolean z7) {
        this.f2058s = z7;
    }

    public void setPopupTheme(int i8) {
        if (this.f2057r != i8) {
            this.f2057r = i8;
            if (i8 == 0) {
                this.f2056q = getContext();
            } else {
                this.f2056q = new ContextThemeWrapper(getContext(), i8);
            }
        }
    }

    public void setPresenter(androidx.appcompat.widget.c cVar) {
        this.f2059t = cVar;
        cVar.H(this);
    }

    public void z() {
        androidx.appcompat.widget.c cVar = this.f2059t;
        if (cVar != null) {
            cVar.y();
        }
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned(false);
        float f8 = context.getResources().getDisplayMetrics().density;
        this.f2064y = (int) (56.0f * f8);
        this.f2065z = (int) (f8 * 4.0f);
        this.f2056q = context;
        this.f2057r = 0;
    }
}
