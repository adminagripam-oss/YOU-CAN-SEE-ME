package androidx.core.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AnimationUtils;
import android.widget.EdgeEffect;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import android.widget.ScrollView;
import androidx.core.view.c0;
import androidx.core.view.d0;
import androidx.core.view.f0;
import androidx.core.view.g0;
import androidx.core.view.o;
import androidx.core.view.p;
import androidx.core.view.p0;
import h1.r;
import h1.t;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class NestedScrollView extends FrameLayout implements f0 {
    private static final float D = (float) (Math.log(0.78d) / Math.log(0.9d));
    private static final a E = new a();
    private static final int[] F = {16843130};
    private d A;
    final c B;
    o C;

    /* renamed from: a  reason: collision with root package name */
    private final float f3200a;

    /* renamed from: b  reason: collision with root package name */
    private long f3201b;

    /* renamed from: c  reason: collision with root package name */
    private final Rect f3202c;

    /* renamed from: d  reason: collision with root package name */
    private OverScroller f3203d;

    /* renamed from: e  reason: collision with root package name */
    public EdgeEffect f3204e;

    /* renamed from: f  reason: collision with root package name */
    public EdgeEffect f3205f;

    /* renamed from: g  reason: collision with root package name */
    private int f3206g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f3207h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f3208i;

    /* renamed from: j  reason: collision with root package name */
    private View f3209j;

    /* renamed from: k  reason: collision with root package name */
    private boolean f3210k;

    /* renamed from: l  reason: collision with root package name */
    private VelocityTracker f3211l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f3212m;

    /* renamed from: n  reason: collision with root package name */
    private boolean f3213n;

    /* renamed from: o  reason: collision with root package name */
    private int f3214o;

    /* renamed from: p  reason: collision with root package name */
    private int f3215p;

    /* renamed from: q  reason: collision with root package name */
    private int f3216q;

    /* renamed from: r  reason: collision with root package name */
    private int f3217r;

    /* renamed from: s  reason: collision with root package name */
    private final int[] f3218s;

    /* renamed from: t  reason: collision with root package name */
    private final int[] f3219t;

    /* renamed from: u  reason: collision with root package name */
    private int f3220u;

    /* renamed from: v  reason: collision with root package name */
    private int f3221v;

    /* renamed from: w  reason: collision with root package name */
    private e f3222w;

    /* renamed from: x  reason: collision with root package name */
    private final g0 f3223x;

    /* renamed from: y  reason: collision with root package name */
    private final d0 f3224y;

    /* renamed from: z  reason: collision with root package name */
    private float f3225z;

    /* loaded from: classes.dex */
    static class a extends androidx.core.view.a {
        a() {
        }

        @Override // androidx.core.view.a
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            super.f(view, accessibilityEvent);
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            accessibilityEvent.setClassName(ScrollView.class.getName());
            accessibilityEvent.setScrollable(nestedScrollView.getScrollRange() > 0);
            accessibilityEvent.setScrollX(nestedScrollView.getScrollX());
            accessibilityEvent.setScrollY(nestedScrollView.getScrollY());
            t.a(accessibilityEvent, nestedScrollView.getScrollX());
            t.b(accessibilityEvent, nestedScrollView.getScrollRange());
        }

        @Override // androidx.core.view.a
        public void g(View view, r rVar) {
            int scrollRange;
            super.g(view, rVar);
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            rVar.P(ScrollView.class.getName());
            if (!nestedScrollView.isEnabled() || (scrollRange = nestedScrollView.getScrollRange()) <= 0) {
                return;
            }
            rVar.T(true);
            if (nestedScrollView.getScrollY() > 0) {
                rVar.a(r.a.f7155q);
                rVar.a(r.a.B);
            }
            if (nestedScrollView.getScrollY() < scrollRange) {
                rVar.a(r.a.f7154p);
                rVar.a(r.a.D);
            }
        }

        @Override // androidx.core.view.a
        public boolean j(View view, int i8, Bundle bundle) {
            if (super.j(view, i8, bundle)) {
                return true;
            }
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            if (nestedScrollView.isEnabled()) {
                int height = nestedScrollView.getHeight();
                Rect rect = new Rect();
                if (nestedScrollView.getMatrix().isIdentity() && nestedScrollView.getGlobalVisibleRect(rect)) {
                    height = rect.height();
                }
                if (i8 != 4096) {
                    if (i8 == 8192 || i8 == 16908344) {
                        int max = Math.max(nestedScrollView.getScrollY() - ((height - nestedScrollView.getPaddingBottom()) - nestedScrollView.getPaddingTop()), 0);
                        if (max != nestedScrollView.getScrollY()) {
                            nestedScrollView.Q(0, max, true);
                            return true;
                        }
                        return false;
                    } else if (i8 != 16908346) {
                        return false;
                    }
                }
                int min = Math.min(nestedScrollView.getScrollY() + ((height - nestedScrollView.getPaddingBottom()) - nestedScrollView.getPaddingTop()), nestedScrollView.getScrollRange());
                if (min != nestedScrollView.getScrollY()) {
                    nestedScrollView.Q(0, min, true);
                    return true;
                }
                return false;
            }
            return false;
        }
    }

    /* loaded from: classes.dex */
    static class b {
        static boolean a(ViewGroup viewGroup) {
            return viewGroup.getClipToPadding();
        }
    }

    /* loaded from: classes.dex */
    class c implements p {
        c() {
        }

        @Override // androidx.core.view.p
        public boolean a(float f8) {
            if (f8 == 0.0f) {
                return false;
            }
            c();
            NestedScrollView.this.p((int) f8);
            return true;
        }

        @Override // androidx.core.view.p
        public float b() {
            return -NestedScrollView.this.getVerticalScrollFactorCompat();
        }

        @Override // androidx.core.view.p
        public void c() {
            NestedScrollView.this.f3203d.abortAnimation();
        }
    }

    /* loaded from: classes.dex */
    public interface d {
        void a(NestedScrollView nestedScrollView, int i8, int i9, int i10, int i11);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class e extends View.BaseSavedState {
        public static final Parcelable.Creator<e> CREATOR = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f3227a;

        /* loaded from: classes.dex */
        class a implements Parcelable.Creator {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public e createFromParcel(Parcel parcel) {
                return new e(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public e[] newArray(int i8) {
                return new e[i8];
            }
        }

        e(Parcel parcel) {
            super(parcel);
            this.f3227a = parcel.readInt();
        }

        public String toString() {
            return "HorizontalScrollView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " scrollPosition=" + this.f3227a + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            super.writeToParcel(parcel, i8);
            parcel.writeInt(this.f3227a);
        }

        e(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public NestedScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c1.a.f4694c);
    }

    private boolean A(View view, int i8, int i9) {
        view.getDrawingRect(this.f3202c);
        offsetDescendantRectToMyCoords(view, this.f3202c);
        return this.f3202c.bottom + i8 >= getScrollY() && this.f3202c.top - i8 <= getScrollY() + i9;
    }

    private void B(int i8, int i9, int[] iArr) {
        int scrollY = getScrollY();
        scrollBy(0, i8);
        int scrollY2 = getScrollY() - scrollY;
        if (iArr != null) {
            iArr[1] = iArr[1] + scrollY2;
        }
        this.f3224y.e(0, scrollY2, 0, i8 - scrollY2, null, i9, iArr);
    }

    private void C(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.f3217r) {
            int i8 = actionIndex == 0 ? 1 : 0;
            this.f3206g = (int) motionEvent.getY(i8);
            this.f3217r = motionEvent.getPointerId(i8);
            VelocityTracker velocityTracker = this.f3211l;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    private void F() {
        VelocityTracker velocityTracker = this.f3211l;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.f3211l = null;
        }
    }

    private int G(int i8, float f8) {
        float d8;
        EdgeEffect edgeEffect;
        float width = f8 / getWidth();
        float height = i8 / getHeight();
        float f9 = 0.0f;
        if (androidx.core.widget.d.b(this.f3204e) != 0.0f) {
            d8 = -androidx.core.widget.d.d(this.f3204e, -height, width);
            if (androidx.core.widget.d.b(this.f3204e) == 0.0f) {
                edgeEffect = this.f3204e;
                edgeEffect.onRelease();
            }
            f9 = d8;
        } else if (androidx.core.widget.d.b(this.f3205f) != 0.0f) {
            d8 = androidx.core.widget.d.d(this.f3205f, height, 1.0f - width);
            if (androidx.core.widget.d.b(this.f3205f) == 0.0f) {
                edgeEffect = this.f3205f;
                edgeEffect.onRelease();
            }
            f9 = d8;
        }
        int round = Math.round(f9 * getHeight());
        if (round != 0) {
            invalidate();
        }
        return round;
    }

    private void H(boolean z7) {
        if (z7) {
            R(2, 1);
        } else {
            T(1);
        }
        this.f3221v = getScrollY();
        postInvalidateOnAnimation();
    }

    private boolean I(int i8, int i9, int i10) {
        int height = getHeight();
        int scrollY = getScrollY();
        int i11 = height + scrollY;
        boolean z7 = false;
        boolean z8 = i8 == 33;
        View o8 = o(z8, i9, i10);
        if (o8 == null) {
            o8 = this;
        }
        if (i9 < scrollY || i10 > i11) {
            J(z8 ? i9 - scrollY : i10 - i11, 0, 1, true);
            z7 = true;
        }
        if (o8 != findFocus()) {
            o8.requestFocus(i8);
        }
        return z7;
    }

    private int J(int i8, int i9, int i10, boolean z7) {
        int i11;
        int i12;
        EdgeEffect edgeEffect;
        VelocityTracker velocityTracker;
        if (i10 == 1) {
            R(2, i10);
        }
        boolean z8 = false;
        if (i(0, i8, this.f3219t, this.f3218s, i10)) {
            i11 = i8 - this.f3219t[1];
            i12 = this.f3218s[1] + 0;
        } else {
            i11 = i8;
            i12 = 0;
        }
        int scrollY = getScrollY();
        int scrollRange = getScrollRange();
        boolean z9 = d() && !z7;
        boolean z10 = D(0, i11, 0, scrollY, 0, scrollRange, 0, 0, true) && !s(i10);
        int scrollY2 = getScrollY() - scrollY;
        int[] iArr = this.f3219t;
        iArr[1] = 0;
        j(0, scrollY2, 0, i11 - scrollY2, this.f3218s, i10, iArr);
        int i13 = i12 + this.f3218s[1];
        int i14 = i11 - this.f3219t[1];
        int i15 = scrollY + i14;
        if (i15 < 0) {
            if (z9) {
                androidx.core.widget.d.d(this.f3204e, (-i14) / getHeight(), i9 / getWidth());
                if (!this.f3205f.isFinished()) {
                    edgeEffect = this.f3205f;
                    edgeEffect.onRelease();
                }
            }
        } else if (i15 > scrollRange && z9) {
            androidx.core.widget.d.d(this.f3205f, i14 / getHeight(), 1.0f - (i9 / getWidth()));
            if (!this.f3204e.isFinished()) {
                edgeEffect = this.f3204e;
                edgeEffect.onRelease();
            }
        }
        if (this.f3204e.isFinished() && this.f3205f.isFinished()) {
            z8 = z10;
        } else {
            postInvalidateOnAnimation();
        }
        if (z8 && i10 == 0 && (velocityTracker = this.f3211l) != null) {
            velocityTracker.clear();
        }
        if (i10 == 1) {
            T(i10);
            this.f3204e.onRelease();
            this.f3205f.onRelease();
        }
        return i13;
    }

    private void K(View view) {
        view.getDrawingRect(this.f3202c);
        offsetDescendantRectToMyCoords(view, this.f3202c);
        int g8 = g(this.f3202c);
        if (g8 != 0) {
            scrollBy(0, g8);
        }
    }

    private boolean L(Rect rect, boolean z7) {
        int g8 = g(rect);
        boolean z8 = g8 != 0;
        if (z8) {
            if (z7) {
                scrollBy(0, g8);
            } else {
                N(0, g8);
            }
        }
        return z8;
    }

    private boolean M(EdgeEffect edgeEffect, int i8) {
        if (i8 > 0) {
            return true;
        }
        return r(-i8) < androidx.core.widget.d.b(edgeEffect) * ((float) getHeight());
    }

    private void O(int i8, int i9, int i10, boolean z7) {
        if (getChildCount() == 0) {
            return;
        }
        if (AnimationUtils.currentAnimationTimeMillis() - this.f3201b > 250) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int scrollY = getScrollY();
            OverScroller overScroller = this.f3203d;
            int scrollX = getScrollX();
            overScroller.startScroll(scrollX, scrollY, 0, Math.max(0, Math.min(i9 + scrollY, Math.max(0, ((childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin) - ((getHeight() - getPaddingTop()) - getPaddingBottom())))) - scrollY, i10);
            H(z7);
        } else {
            if (!this.f3203d.isFinished()) {
                a();
            }
            scrollBy(i8, i9);
        }
        this.f3201b = AnimationUtils.currentAnimationTimeMillis();
    }

    private boolean S(MotionEvent motionEvent) {
        boolean z7;
        if (androidx.core.widget.d.b(this.f3204e) != 0.0f) {
            androidx.core.widget.d.d(this.f3204e, 0.0f, motionEvent.getX() / getWidth());
            z7 = true;
        } else {
            z7 = false;
        }
        if (androidx.core.widget.d.b(this.f3205f) != 0.0f) {
            androidx.core.widget.d.d(this.f3205f, 0.0f, 1.0f - (motionEvent.getX() / getWidth()));
            return true;
        }
        return z7;
    }

    private void a() {
        this.f3203d.abortAnimation();
        T(1);
    }

    private boolean d() {
        int overScrollMode = getOverScrollMode();
        if (overScrollMode != 0) {
            return overScrollMode == 1 && getScrollRange() > 0;
        }
        return true;
    }

    private boolean e() {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            return (childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin > (getHeight() - getPaddingTop()) - getPaddingBottom();
        }
        return false;
    }

    private static int f(int i8, int i9, int i10) {
        if (i9 >= i10 || i8 < 0) {
            return 0;
        }
        return i9 + i8 > i10 ? i10 - i9 : i8;
    }

    private void k(int i8) {
        if (i8 != 0) {
            if (this.f3213n) {
                N(0, i8);
            } else {
                scrollBy(0, i8);
            }
        }
    }

    private boolean l(int i8) {
        EdgeEffect edgeEffect;
        if (androidx.core.widget.d.b(this.f3204e) != 0.0f) {
            if (M(this.f3204e, i8)) {
                edgeEffect = this.f3204e;
                edgeEffect.onAbsorb(i8);
            } else {
                i8 = -i8;
                p(i8);
            }
        } else if (androidx.core.widget.d.b(this.f3205f) == 0.0f) {
            return false;
        } else {
            i8 = -i8;
            if (M(this.f3205f, i8)) {
                edgeEffect = this.f3205f;
                edgeEffect.onAbsorb(i8);
            }
            p(i8);
        }
        return true;
    }

    private void m() {
        this.f3217r = -1;
        this.f3210k = false;
        F();
        T(0);
        this.f3204e.onRelease();
        this.f3205f.onRelease();
    }

    private View o(boolean z7, int i8, int i9) {
        ArrayList<View> focusables = getFocusables(2);
        int size = focusables.size();
        View view = null;
        boolean z8 = false;
        for (int i10 = 0; i10 < size; i10++) {
            View view2 = focusables.get(i10);
            int top = view2.getTop();
            int bottom = view2.getBottom();
            if (i8 < bottom && top < i9) {
                boolean z9 = i8 < top && bottom < i9;
                if (view == null) {
                    view = view2;
                    z8 = z9;
                } else {
                    boolean z10 = (z7 && top < view.getTop()) || (!z7 && bottom > view.getBottom());
                    if (z8) {
                        if (z9) {
                            if (!z10) {
                            }
                            view = view2;
                        }
                    } else if (z9) {
                        view = view2;
                        z8 = true;
                    } else {
                        if (!z10) {
                        }
                        view = view2;
                    }
                }
            }
        }
        return view;
    }

    private float r(int i8) {
        double log = Math.log((Math.abs(i8) * 0.35f) / (this.f3200a * 0.015f));
        float f8 = D;
        return (float) (this.f3200a * 0.015f * Math.exp((f8 / (f8 - 1.0d)) * log));
    }

    private boolean t(int i8, int i9) {
        if (getChildCount() > 0) {
            int scrollY = getScrollY();
            View childAt = getChildAt(0);
            return i9 >= childAt.getTop() - scrollY && i9 < childAt.getBottom() - scrollY && i8 >= childAt.getLeft() && i8 < childAt.getRight();
        }
        return false;
    }

    private void u() {
        VelocityTracker velocityTracker = this.f3211l;
        if (velocityTracker == null) {
            this.f3211l = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
    }

    private void v() {
        this.f3203d = new OverScroller(getContext());
        setFocusable(true);
        setDescendantFocusability(262144);
        setWillNotDraw(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.f3214o = viewConfiguration.getScaledTouchSlop();
        this.f3215p = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f3216q = viewConfiguration.getScaledMaximumFlingVelocity();
    }

    private void w() {
        if (this.f3211l == null) {
            this.f3211l = VelocityTracker.obtain();
        }
    }

    private void x(int i8, int i9) {
        this.f3206g = i8;
        this.f3217r = i9;
        R(2, 0);
    }

    private boolean y(View view) {
        return !A(view, 0, getHeight());
    }

    private static boolean z(View view, View view2) {
        if (view == view2) {
            return true;
        }
        ViewParent parent = view.getParent();
        return (parent instanceof ViewGroup) && z((View) parent, view2);
    }

    boolean D(int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, boolean z7) {
        boolean z8;
        boolean z9;
        int overScrollMode = getOverScrollMode();
        boolean z10 = computeHorizontalScrollRange() > computeHorizontalScrollExtent();
        boolean z11 = computeVerticalScrollRange() > computeVerticalScrollExtent();
        boolean z12 = overScrollMode == 0 || (overScrollMode == 1 && z10);
        boolean z13 = overScrollMode == 0 || (overScrollMode == 1 && z11);
        int i16 = i10 + i8;
        int i17 = !z12 ? 0 : i14;
        int i18 = i11 + i9;
        int i19 = !z13 ? 0 : i15;
        int i20 = -i17;
        int i21 = i17 + i12;
        int i22 = -i19;
        int i23 = i19 + i13;
        if (i16 > i21) {
            i16 = i21;
            z8 = true;
        } else if (i16 < i20) {
            z8 = true;
            i16 = i20;
        } else {
            z8 = false;
        }
        if (i18 > i23) {
            i18 = i23;
            z9 = true;
        } else if (i18 < i22) {
            z9 = true;
            i18 = i22;
        } else {
            z9 = false;
        }
        if (z9 && !s(1)) {
            this.f3203d.springBack(i16, i18, 0, 0, 0, getScrollRange());
        }
        onOverScrolled(i16, i18, z8, z9);
        return z8 || z9;
    }

    public boolean E(int i8) {
        boolean z7 = i8 == 130;
        int height = getHeight();
        if (z7) {
            this.f3202c.top = getScrollY() + height;
            int childCount = getChildCount();
            if (childCount > 0) {
                View childAt = getChildAt(childCount - 1);
                int bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin + getPaddingBottom();
                Rect rect = this.f3202c;
                if (rect.top + height > bottom) {
                    rect.top = bottom - height;
                }
            }
        } else {
            this.f3202c.top = getScrollY() - height;
            Rect rect2 = this.f3202c;
            if (rect2.top < 0) {
                rect2.top = 0;
            }
        }
        Rect rect3 = this.f3202c;
        int i9 = rect3.top;
        int i10 = height + i9;
        rect3.bottom = i10;
        return I(i8, i9, i10);
    }

    public final void N(int i8, int i9) {
        O(i8, i9, 250, false);
    }

    void P(int i8, int i9, int i10, boolean z7) {
        O(i8 - getScrollX(), i9 - getScrollY(), i10, z7);
    }

    void Q(int i8, int i9, boolean z7) {
        P(i8, i9, 250, z7);
    }

    public boolean R(int i8, int i9) {
        return this.f3224y.q(i8, i9);
    }

    public void T(int i8) {
        this.f3224y.s(i8);
    }

    @Override // android.view.ViewGroup
    public void addView(View view) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view);
    }

    public boolean c(int i8) {
        View findFocus = findFocus();
        if (findFocus == this) {
            findFocus = null;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, i8);
        int maxScrollAmount = getMaxScrollAmount();
        if (findNextFocus == null || !A(findNextFocus, maxScrollAmount, getHeight())) {
            if (i8 == 33 && getScrollY() < maxScrollAmount) {
                maxScrollAmount = getScrollY();
            } else if (i8 == 130 && getChildCount() > 0) {
                View childAt = getChildAt(0);
                maxScrollAmount = Math.min((childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin) - ((getScrollY() + getHeight()) - getPaddingBottom()), maxScrollAmount);
            }
            if (maxScrollAmount == 0) {
                return false;
            }
            if (i8 != 130) {
                maxScrollAmount = -maxScrollAmount;
            }
            J(maxScrollAmount, 0, 1, true);
        } else {
            findNextFocus.getDrawingRect(this.f3202c);
            offsetDescendantRectToMyCoords(findNextFocus, this.f3202c);
            J(g(this.f3202c), 0, 1, true);
            findNextFocus.requestFocus(i8);
        }
        if (findFocus != null && findFocus.isFocused() && y(findFocus)) {
            int descendantFocusability = getDescendantFocusability();
            setDescendantFocusability(131072);
            requestFocus();
            setDescendantFocusability(descendantFocusability);
        }
        return true;
    }

    @Override // android.view.View
    public int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    @Override // android.view.View
    public int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    @Override // android.view.View
    public int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    @Override // android.view.View
    public void computeScroll() {
        EdgeEffect edgeEffect;
        if (this.f3203d.isFinished()) {
            return;
        }
        this.f3203d.computeScrollOffset();
        int currY = this.f3203d.getCurrY();
        int h8 = h(currY - this.f3221v);
        this.f3221v = currY;
        int[] iArr = this.f3219t;
        boolean z7 = false;
        iArr[1] = 0;
        i(0, h8, iArr, null, 1);
        int i8 = h8 - this.f3219t[1];
        int scrollRange = getScrollRange();
        if (i8 != 0) {
            int scrollY = getScrollY();
            D(0, i8, getScrollX(), scrollY, 0, scrollRange, 0, 0, false);
            int scrollY2 = getScrollY() - scrollY;
            int i9 = i8 - scrollY2;
            int[] iArr2 = this.f3219t;
            iArr2[1] = 0;
            j(0, scrollY2, 0, i9, this.f3218s, 1, iArr2);
            i8 = i9 - this.f3219t[1];
        }
        if (i8 != 0) {
            int overScrollMode = getOverScrollMode();
            if (overScrollMode == 0 || (overScrollMode == 1 && scrollRange > 0)) {
                z7 = true;
            }
            if (z7) {
                if (i8 < 0) {
                    if (this.f3204e.isFinished()) {
                        edgeEffect = this.f3204e;
                        edgeEffect.onAbsorb((int) this.f3203d.getCurrVelocity());
                    }
                } else if (this.f3205f.isFinished()) {
                    edgeEffect = this.f3205f;
                    edgeEffect.onAbsorb((int) this.f3203d.getCurrVelocity());
                }
            }
            a();
        }
        if (this.f3203d.isFinished()) {
            T(1);
        } else {
            postInvalidateOnAnimation();
        }
    }

    @Override // android.view.View
    public int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    @Override // android.view.View
    public int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    @Override // android.view.View
    public int computeVerticalScrollRange() {
        int childCount = getChildCount();
        int height = (getHeight() - getPaddingBottom()) - getPaddingTop();
        if (childCount == 0) {
            return height;
        }
        View childAt = getChildAt(0);
        int bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin;
        int scrollY = getScrollY();
        int max = Math.max(0, bottom - height);
        return scrollY < 0 ? bottom - scrollY : scrollY > max ? bottom + (scrollY - max) : bottom;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || n(keyEvent);
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f8, float f9, boolean z7) {
        return this.f3224y.a(f8, f9, z7);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f8, float f9) {
        return this.f3224y.b(f8, f9);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i8, int i9, int[] iArr, int[] iArr2) {
        return i(i8, i9, iArr, iArr2, 0);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i8, int i9, int i10, int i11, int[] iArr) {
        return this.f3224y.f(i8, i9, i10, i11, iArr);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int i8;
        super.draw(canvas);
        int scrollY = getScrollY();
        int i9 = 0;
        if (!this.f3204e.isFinished()) {
            int save = canvas.save();
            int width = getWidth();
            int height = getHeight();
            int min = Math.min(0, scrollY);
            if (b.a(this)) {
                width -= getPaddingLeft() + getPaddingRight();
                i8 = getPaddingLeft() + 0;
            } else {
                i8 = 0;
            }
            if (b.a(this)) {
                height -= getPaddingTop() + getPaddingBottom();
                min += getPaddingTop();
            }
            canvas.translate(i8, min);
            this.f3204e.setSize(width, height);
            if (this.f3204e.draw(canvas)) {
                postInvalidateOnAnimation();
            }
            canvas.restoreToCount(save);
        }
        if (this.f3205f.isFinished()) {
            return;
        }
        int save2 = canvas.save();
        int width2 = getWidth();
        int height2 = getHeight();
        int max = Math.max(getScrollRange(), scrollY) + height2;
        if (b.a(this)) {
            width2 -= getPaddingLeft() + getPaddingRight();
            i9 = 0 + getPaddingLeft();
        }
        if (b.a(this)) {
            height2 -= getPaddingTop() + getPaddingBottom();
            max -= getPaddingBottom();
        }
        canvas.translate(i9 - width2, max);
        canvas.rotate(180.0f, width2, 0.0f);
        this.f3205f.setSize(width2, height2);
        if (this.f3205f.draw(canvas)) {
            postInvalidateOnAnimation();
        }
        canvas.restoreToCount(save2);
    }

    protected int g(Rect rect) {
        if (getChildCount() == 0) {
            return 0;
        }
        int height = getHeight();
        int scrollY = getScrollY();
        int i8 = scrollY + height;
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        if (rect.top > 0) {
            scrollY += verticalFadingEdgeLength;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        int i9 = rect.bottom < (childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin ? i8 - verticalFadingEdgeLength : i8;
        int i10 = rect.bottom;
        if (i10 > i9 && rect.top > scrollY) {
            return Math.min((rect.height() > height ? rect.top - scrollY : rect.bottom - i9) + 0, (childAt.getBottom() + layoutParams.bottomMargin) - i8);
        } else if (rect.top >= scrollY || i10 >= i9) {
            return 0;
        } else {
            return Math.max(rect.height() > height ? 0 - (i9 - rect.bottom) : 0 - (scrollY - rect.top), -getScrollY());
        }
    }

    @Override // android.view.View
    protected float getBottomFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        View childAt = getChildAt(0);
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int bottom = ((childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin) - getScrollY()) - (getHeight() - getPaddingBottom());
        if (bottom < verticalFadingEdgeLength) {
            return bottom / verticalFadingEdgeLength;
        }
        return 1.0f;
    }

    public int getMaxScrollAmount() {
        return (int) (getHeight() * 0.5f);
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.f3223x.a();
    }

    int getScrollRange() {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            return Math.max(0, ((childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin) - ((getHeight() - getPaddingTop()) - getPaddingBottom()));
        }
        return 0;
    }

    @Override // android.view.View
    protected float getTopFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int scrollY = getScrollY();
        if (scrollY < verticalFadingEdgeLength) {
            return scrollY / verticalFadingEdgeLength;
        }
        return 1.0f;
    }

    float getVerticalScrollFactorCompat() {
        if (this.f3225z == 0.0f) {
            TypedValue typedValue = new TypedValue();
            Context context = getContext();
            if (!context.getTheme().resolveAttribute(16842829, typedValue, true)) {
                throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
            }
            this.f3225z = typedValue.getDimension(context.getResources().getDisplayMetrics());
        }
        return this.f3225z;
    }

    int h(int i8) {
        int height = getHeight();
        if (i8 > 0 && androidx.core.widget.d.b(this.f3204e) != 0.0f) {
            int round = Math.round(((-height) / 4.0f) * androidx.core.widget.d.d(this.f3204e, ((-i8) * 4.0f) / height, 0.5f));
            if (round != i8) {
                this.f3204e.finish();
            }
            return i8 - round;
        } else if (i8 >= 0 || androidx.core.widget.d.b(this.f3205f) == 0.0f) {
            return i8;
        } else {
            float f8 = height;
            int round2 = Math.round((f8 / 4.0f) * androidx.core.widget.d.d(this.f3205f, (i8 * 4.0f) / f8, 0.5f));
            if (round2 != i8) {
                this.f3205f.finish();
            }
            return i8 - round2;
        }
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return s(0);
    }

    public boolean i(int i8, int i9, int[] iArr, int[] iArr2, int i10) {
        return this.f3224y.d(i8, i9, iArr, iArr2, i10);
    }

    @Override // android.view.View
    public boolean isNestedScrollingEnabled() {
        return this.f3224y.m();
    }

    public void j(int i8, int i9, int i10, int i11, int[] iArr, int i12, int[] iArr2) {
        this.f3224y.e(i8, i9, i10, i11, iArr, i12, iArr2);
    }

    @Override // android.view.ViewGroup
    protected void measureChild(View view, int i8, int i9) {
        view.measure(ViewGroup.getChildMeasureSpec(i8, getPaddingLeft() + getPaddingRight(), view.getLayoutParams().width), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    @Override // android.view.ViewGroup
    protected void measureChildWithMargins(View view, int i8, int i9, int i10, int i11) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(ViewGroup.getChildMeasureSpec(i8, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i9, marginLayoutParams.width), View.MeasureSpec.makeMeasureSpec(marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, 0));
    }

    public boolean n(KeyEvent keyEvent) {
        this.f3202c.setEmpty();
        int i8 = 130;
        if (!e()) {
            if (!isFocused() || keyEvent.getKeyCode() == 4) {
                return false;
            }
            View findFocus = findFocus();
            if (findFocus == this) {
                findFocus = null;
            }
            View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, 130);
            return (findNextFocus == null || findNextFocus == this || !findNextFocus.requestFocus(130)) ? false : true;
        } else if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode != 19) {
                if (keyCode != 20) {
                    if (keyCode != 62) {
                        if (keyCode != 92) {
                            if (keyCode != 93) {
                                if (keyCode == 122) {
                                    E(33);
                                    return false;
                                } else if (keyCode != 123) {
                                    return false;
                                }
                            }
                        }
                    } else if (keyEvent.isShiftPressed()) {
                        i8 = 33;
                    }
                    E(i8);
                    return false;
                } else if (!keyEvent.isAltPressed()) {
                    return c(130);
                }
                return q(130);
            } else if (!keyEvent.isAltPressed()) {
                return c(33);
            }
            return q(33);
        } else {
            return false;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f3208i = false;
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        int i8;
        int i9;
        float f8;
        if (motionEvent.getAction() == 8 && !this.f3210k) {
            if (c0.a(motionEvent, 2)) {
                i8 = 9;
                f8 = motionEvent.getAxisValue(9);
                i9 = (int) motionEvent.getX();
            } else if (c0.a(motionEvent, 4194304)) {
                float axisValue = motionEvent.getAxisValue(26);
                i9 = getWidth() / 2;
                i8 = 26;
                f8 = axisValue;
            } else {
                i8 = 0;
                i9 = 0;
                f8 = 0.0f;
            }
            if (f8 != 0.0f) {
                J(-((int) (f8 * getVerticalScrollFactorCompat())), i9, 1, c0.a(motionEvent, 8194));
                if (i8 != 0) {
                    this.C.g(motionEvent, i8);
                }
                return true;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        boolean z7 = true;
        if (action == 2 && this.f3210k) {
            return true;
        }
        int i8 = action & 255;
        if (i8 != 0) {
            if (i8 != 1) {
                if (i8 == 2) {
                    int i9 = this.f3217r;
                    if (i9 != -1) {
                        int findPointerIndex = motionEvent.findPointerIndex(i9);
                        if (findPointerIndex == -1) {
                            Log.e("NestedScrollView", "Invalid pointerId=" + i9 + " in onInterceptTouchEvent");
                        } else {
                            int y7 = (int) motionEvent.getY(findPointerIndex);
                            if (Math.abs(y7 - this.f3206g) > this.f3214o && (2 & getNestedScrollAxes()) == 0) {
                                this.f3210k = true;
                                this.f3206g = y7;
                                w();
                                this.f3211l.addMovement(motionEvent);
                                this.f3220u = 0;
                                ViewParent parent = getParent();
                                if (parent != null) {
                                    parent.requestDisallowInterceptTouchEvent(true);
                                }
                            }
                        }
                    }
                } else if (i8 != 3) {
                    if (i8 == 6) {
                        C(motionEvent);
                    }
                }
            }
            this.f3210k = false;
            this.f3217r = -1;
            F();
            if (this.f3203d.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                postInvalidateOnAnimation();
            }
            T(0);
        } else {
            int y8 = (int) motionEvent.getY();
            if (t((int) motionEvent.getX(), y8)) {
                this.f3206g = y8;
                this.f3217r = motionEvent.getPointerId(0);
                u();
                this.f3211l.addMovement(motionEvent);
                this.f3203d.computeScrollOffset();
                if (!S(motionEvent) && this.f3203d.isFinished()) {
                    z7 = false;
                }
                this.f3210k = z7;
                R(2, 0);
            } else {
                if (!S(motionEvent) && this.f3203d.isFinished()) {
                    z7 = false;
                }
                this.f3210k = z7;
                F();
            }
        }
        return this.f3210k;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        super.onLayout(z7, i8, i9, i10, i11);
        int i12 = 0;
        this.f3207h = false;
        View view = this.f3209j;
        if (view != null && z(view, this)) {
            K(this.f3209j);
        }
        this.f3209j = null;
        if (!this.f3208i) {
            if (this.f3222w != null) {
                scrollTo(getScrollX(), this.f3222w.f3227a);
                this.f3222w = null;
            }
            if (getChildCount() > 0) {
                View childAt = getChildAt(0);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                i12 = childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            }
            int paddingTop = ((i11 - i9) - getPaddingTop()) - getPaddingBottom();
            int scrollY = getScrollY();
            int f8 = f(scrollY, paddingTop, i12);
            if (f8 != scrollY) {
                scrollTo(getScrollX(), f8);
            }
        }
        scrollTo(getScrollX(), getScrollY());
        this.f3208i = true;
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i8, int i9) {
        super.onMeasure(i8, i9);
        if (this.f3212m && View.MeasureSpec.getMode(i9) != 0 && getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int measuredHeight2 = (((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - layoutParams.topMargin) - layoutParams.bottomMargin;
            if (measuredHeight < measuredHeight2) {
                childAt.measure(ViewGroup.getChildMeasureSpec(i8, getPaddingLeft() + getPaddingRight() + layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width), View.MeasureSpec.makeMeasureSpec(measuredHeight2, 1073741824));
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View view, float f8, float f9, boolean z7) {
        if (z7) {
            return false;
        }
        dispatchNestedFling(0.0f, f9, true);
        p((int) f9);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View view, float f8, float f9) {
        return dispatchNestedPreFling(f8, f9);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View view, int i8, int i9, int[] iArr) {
        onNestedPreScroll(view, i8, i9, iArr, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View view, int i8, int i9, int i10, int i11) {
        B(i11, 0, null);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScrollAccepted(View view, View view2, int i8) {
        onNestedScrollAccepted(view, view2, i8, 0);
    }

    @Override // android.view.View
    protected void onOverScrolled(int i8, int i9, boolean z7, boolean z8) {
        super.scrollTo(i8, i9);
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int i8, Rect rect) {
        if (i8 == 2) {
            i8 = 130;
        } else if (i8 == 1) {
            i8 = 33;
        }
        FocusFinder focusFinder = FocusFinder.getInstance();
        View findNextFocus = rect == null ? focusFinder.findNextFocus(this, null, i8) : focusFinder.findNextFocusFromRect(this, rect, i8);
        if (findNextFocus == null || y(findNextFocus)) {
            return false;
        }
        return findNextFocus.requestFocus(i8, rect);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof e)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        e eVar = (e) parcelable;
        super.onRestoreInstanceState(eVar.getSuperState());
        this.f3222w = eVar;
        requestLayout();
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        e eVar = new e(super.onSaveInstanceState());
        eVar.f3227a = getScrollY();
        return eVar;
    }

    @Override // android.view.View
    protected void onScrollChanged(int i8, int i9, int i10, int i11) {
        super.onScrollChanged(i8, i9, i10, i11);
        d dVar = this.A;
        if (dVar != null) {
            dVar.a(this, i8, i9, i10, i11);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i8, int i9, int i10, int i11) {
        super.onSizeChanged(i8, i9, i10, i11);
        View findFocus = findFocus();
        if (findFocus == null || this == findFocus || !A(findFocus, 0, i11)) {
            return;
        }
        findFocus.getDrawingRect(this.f3202c);
        offsetDescendantRectToMyCoords(findFocus, this.f3202c);
        k(g(this.f3202c));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View view, View view2, int i8) {
        return onStartNestedScroll(view, view2, i8, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View view) {
        onStopNestedScroll(view, 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x006e, code lost:
        if (r12.f3203d.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange()) != false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0070, code lost:
        postInvalidateOnAnimation();
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x012a, code lost:
        if (r12.f3203d.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange()) != false) goto L29;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r13) {
        /*
            Method dump skipped, instructions count: 356
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void p(int i8) {
        if (getChildCount() > 0) {
            this.f3203d.fling(getScrollX(), getScrollY(), 0, i8, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
            H(true);
        }
    }

    public boolean q(int i8) {
        int childCount;
        boolean z7 = i8 == 130;
        int height = getHeight();
        Rect rect = this.f3202c;
        rect.top = 0;
        rect.bottom = height;
        if (z7 && (childCount = getChildCount()) > 0) {
            View childAt = getChildAt(childCount - 1);
            this.f3202c.bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin + getPaddingBottom();
            Rect rect2 = this.f3202c;
            rect2.top = rect2.bottom - height;
        }
        Rect rect3 = this.f3202c;
        return I(i8, rect3.top, rect3.bottom);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (this.f3207h) {
            this.f3209j = view2;
        } else {
            K(view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z7) {
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        return L(rect, z7);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z7) {
        if (z7) {
            F();
        }
        super.requestDisallowInterceptTouchEvent(z7);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        this.f3207h = true;
        super.requestLayout();
    }

    public boolean s(int i8) {
        return this.f3224y.l(i8);
    }

    @Override // android.view.View
    public void scrollTo(int i8, int i9) {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int f8 = f(i8, (getWidth() - getPaddingLeft()) - getPaddingRight(), childAt.getWidth() + layoutParams.leftMargin + layoutParams.rightMargin);
            int f9 = f(i9, (getHeight() - getPaddingTop()) - getPaddingBottom(), childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin);
            if (f8 == getScrollX() && f9 == getScrollY()) {
                return;
            }
            super.scrollTo(f8, f9);
        }
    }

    public void setFillViewport(boolean z7) {
        if (z7 != this.f3212m) {
            this.f3212m = z7;
            requestLayout();
        }
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z7) {
        this.f3224y.n(z7);
    }

    public void setOnScrollChangeListener(d dVar) {
        this.A = dVar;
    }

    public void setSmoothScrollingEnabled(boolean z7) {
        this.f3213n = z7;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return true;
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i8) {
        return R(i8, 0);
    }

    @Override // android.view.View
    public void stopNestedScroll() {
        T(0);
    }

    public NestedScrollView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f3202c = new Rect();
        this.f3207h = true;
        this.f3208i = false;
        this.f3209j = null;
        this.f3210k = false;
        this.f3213n = true;
        this.f3217r = -1;
        this.f3218s = new int[2];
        this.f3219t = new int[2];
        c cVar = new c();
        this.B = cVar;
        this.C = new o(getContext(), cVar);
        this.f3204e = androidx.core.widget.d.a(context, attributeSet);
        this.f3205f = androidx.core.widget.d.a(context, attributeSet);
        this.f3200a = context.getResources().getDisplayMetrics().density * 160.0f * 386.0878f * 0.84f;
        v();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, F, i8, 0);
        setFillViewport(obtainStyledAttributes.getBoolean(0, false));
        obtainStyledAttributes.recycle();
        this.f3223x = new g0(this);
        this.f3224y = new d0(this);
        setNestedScrollingEnabled(true);
        p0.N(this, E);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i8) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view, i8);
    }

    @Override // androidx.core.view.e0
    public void onNestedPreScroll(View view, int i8, int i9, int[] iArr, int i10) {
        i(i8, i9, iArr, null, i10);
    }

    @Override // androidx.core.view.e0
    public void onNestedScroll(View view, int i8, int i9, int i10, int i11, int i12) {
        B(i11, i12, null);
    }

    @Override // androidx.core.view.e0
    public void onNestedScrollAccepted(View view, View view2, int i8, int i9) {
        this.f3223x.c(view, view2, i8, i9);
        R(2, i9);
    }

    @Override // androidx.core.view.e0
    public boolean onStartNestedScroll(View view, View view2, int i8, int i9) {
        return (i8 & 2) != 0;
    }

    @Override // androidx.core.view.e0
    public void onStopNestedScroll(View view, int i8) {
        this.f3223x.e(view, i8);
        T(i8);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i8, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view, i8, layoutParams);
    }

    @Override // androidx.core.view.f0
    public void onNestedScroll(View view, int i8, int i9, int i10, int i11, int i12, int[] iArr) {
        B(i11, i12, iArr);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view, layoutParams);
    }
}
