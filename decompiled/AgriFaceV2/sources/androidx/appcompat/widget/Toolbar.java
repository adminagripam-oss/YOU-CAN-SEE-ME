package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.appcompat.app.a;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.view.menu.m;
import androidx.appcompat.widget.ActionMenuView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public class Toolbar extends ViewGroup implements androidx.core.view.w {
    private ColorStateList A;
    private boolean B;
    private boolean C;
    private final ArrayList D;
    private final ArrayList E;
    private final int[] F;
    final androidx.core.view.z G;
    private ArrayList H;
    h I;
    private final ActionMenuView.e J;
    private f1 K;
    private androidx.appcompat.widget.c L;
    private f M;
    private m.a N;
    g.a O;
    private boolean P;
    private OnBackInvokedCallback Q;
    private OnBackInvokedDispatcher R;
    private boolean S;
    private final Runnable T;

    /* renamed from: a  reason: collision with root package name */
    ActionMenuView f2137a;

    /* renamed from: b  reason: collision with root package name */
    private TextView f2138b;

    /* renamed from: c  reason: collision with root package name */
    private TextView f2139c;

    /* renamed from: d  reason: collision with root package name */
    private ImageButton f2140d;

    /* renamed from: e  reason: collision with root package name */
    private ImageView f2141e;

    /* renamed from: f  reason: collision with root package name */
    private Drawable f2142f;

    /* renamed from: g  reason: collision with root package name */
    private CharSequence f2143g;

    /* renamed from: h  reason: collision with root package name */
    ImageButton f2144h;

    /* renamed from: i  reason: collision with root package name */
    View f2145i;

    /* renamed from: j  reason: collision with root package name */
    private Context f2146j;

    /* renamed from: k  reason: collision with root package name */
    private int f2147k;

    /* renamed from: l  reason: collision with root package name */
    private int f2148l;

    /* renamed from: m  reason: collision with root package name */
    private int f2149m;

    /* renamed from: n  reason: collision with root package name */
    int f2150n;

    /* renamed from: o  reason: collision with root package name */
    private int f2151o;

    /* renamed from: p  reason: collision with root package name */
    private int f2152p;

    /* renamed from: q  reason: collision with root package name */
    private int f2153q;

    /* renamed from: r  reason: collision with root package name */
    private int f2154r;

    /* renamed from: s  reason: collision with root package name */
    private int f2155s;

    /* renamed from: t  reason: collision with root package name */
    private u0 f2156t;

    /* renamed from: u  reason: collision with root package name */
    private int f2157u;

    /* renamed from: v  reason: collision with root package name */
    private int f2158v;

    /* renamed from: w  reason: collision with root package name */
    private int f2159w;

    /* renamed from: x  reason: collision with root package name */
    private CharSequence f2160x;

    /* renamed from: y  reason: collision with root package name */
    private CharSequence f2161y;

    /* renamed from: z  reason: collision with root package name */
    private ColorStateList f2162z;

    /* loaded from: classes.dex */
    class a implements ActionMenuView.e {
        a() {
        }

        @Override // androidx.appcompat.widget.ActionMenuView.e
        public boolean onMenuItemClick(MenuItem menuItem) {
            if (Toolbar.this.G.j(menuItem)) {
                return true;
            }
            h hVar = Toolbar.this.I;
            if (hVar != null) {
                return hVar.onMenuItemClick(menuItem);
            }
            return false;
        }
    }

    /* loaded from: classes.dex */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Toolbar.this.Q();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c implements g.a {
        c() {
        }

        @Override // androidx.appcompat.view.menu.g.a
        public boolean a(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
            g.a aVar = Toolbar.this.O;
            return aVar != null && aVar.a(gVar, menuItem);
        }

        @Override // androidx.appcompat.view.menu.g.a
        public void b(androidx.appcompat.view.menu.g gVar) {
            if (!Toolbar.this.f2137a.H()) {
                Toolbar.this.G.k(gVar);
            }
            g.a aVar = Toolbar.this.O;
            if (aVar != null) {
                aVar.b(gVar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d implements View.OnClickListener {
        d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Toolbar.this.e();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class e {
        static OnBackInvokedDispatcher a(View view) {
            return view.findOnBackInvokedDispatcher();
        }

        static OnBackInvokedCallback b(final Runnable runnable) {
            Objects.requireNonNull(runnable);
            return new OnBackInvokedCallback() { // from class: androidx.appcompat.widget.e1
                public final void onBackInvoked() {
                    runnable.run();
                }
            };
        }

        static void c(Object obj, Object obj2) {
            ((OnBackInvokedDispatcher) obj).registerOnBackInvokedCallback(1000000, (OnBackInvokedCallback) obj2);
        }

        static void d(Object obj, Object obj2) {
            ((OnBackInvokedDispatcher) obj).unregisterOnBackInvokedCallback((OnBackInvokedCallback) obj2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class f implements androidx.appcompat.view.menu.m {

        /* renamed from: a  reason: collision with root package name */
        androidx.appcompat.view.menu.g f2167a;

        /* renamed from: b  reason: collision with root package name */
        androidx.appcompat.view.menu.i f2168b;

        f() {
        }

        @Override // androidx.appcompat.view.menu.m
        public void b(androidx.appcompat.view.menu.g gVar, boolean z7) {
        }

        @Override // androidx.appcompat.view.menu.m
        public void d(Context context, androidx.appcompat.view.menu.g gVar) {
            androidx.appcompat.view.menu.i iVar;
            androidx.appcompat.view.menu.g gVar2 = this.f2167a;
            if (gVar2 != null && (iVar = this.f2168b) != null) {
                gVar2.f(iVar);
            }
            this.f2167a = gVar;
        }

        @Override // androidx.appcompat.view.menu.m
        public boolean e(androidx.appcompat.view.menu.r rVar) {
            return false;
        }

        @Override // androidx.appcompat.view.menu.m
        public void f(boolean z7) {
            if (this.f2168b != null) {
                androidx.appcompat.view.menu.g gVar = this.f2167a;
                boolean z8 = false;
                if (gVar != null) {
                    int size = gVar.size();
                    int i8 = 0;
                    while (true) {
                        if (i8 >= size) {
                            break;
                        } else if (this.f2167a.getItem(i8) == this.f2168b) {
                            z8 = true;
                            break;
                        } else {
                            i8++;
                        }
                    }
                }
                if (z8) {
                    return;
                }
                i(this.f2167a, this.f2168b);
            }
        }

        @Override // androidx.appcompat.view.menu.m
        public boolean g() {
            return false;
        }

        @Override // androidx.appcompat.view.menu.m
        public boolean i(androidx.appcompat.view.menu.g gVar, androidx.appcompat.view.menu.i iVar) {
            View view = Toolbar.this.f2145i;
            if (view instanceof androidx.appcompat.view.c) {
                ((androidx.appcompat.view.c) view).d();
            }
            Toolbar toolbar = Toolbar.this;
            toolbar.removeView(toolbar.f2145i);
            Toolbar toolbar2 = Toolbar.this;
            toolbar2.removeView(toolbar2.f2144h);
            Toolbar toolbar3 = Toolbar.this;
            toolbar3.f2145i = null;
            toolbar3.a();
            this.f2168b = null;
            Toolbar.this.requestLayout();
            iVar.r(false);
            Toolbar.this.R();
            return true;
        }

        @Override // androidx.appcompat.view.menu.m
        public boolean j(androidx.appcompat.view.menu.g gVar, androidx.appcompat.view.menu.i iVar) {
            Toolbar.this.g();
            ViewParent parent = Toolbar.this.f2144h.getParent();
            Toolbar toolbar = Toolbar.this;
            if (parent != toolbar) {
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(toolbar.f2144h);
                }
                Toolbar toolbar2 = Toolbar.this;
                toolbar2.addView(toolbar2.f2144h);
            }
            Toolbar.this.f2145i = iVar.getActionView();
            this.f2168b = iVar;
            ViewParent parent2 = Toolbar.this.f2145i.getParent();
            Toolbar toolbar3 = Toolbar.this;
            if (parent2 != toolbar3) {
                if (parent2 instanceof ViewGroup) {
                    ((ViewGroup) parent2).removeView(toolbar3.f2145i);
                }
                g generateDefaultLayoutParams = Toolbar.this.generateDefaultLayoutParams();
                Toolbar toolbar4 = Toolbar.this;
                generateDefaultLayoutParams.f1539a = (toolbar4.f2150n & 112) | 8388611;
                generateDefaultLayoutParams.f2170b = 2;
                toolbar4.f2145i.setLayoutParams(generateDefaultLayoutParams);
                Toolbar toolbar5 = Toolbar.this;
                toolbar5.addView(toolbar5.f2145i);
            }
            Toolbar.this.I();
            Toolbar.this.requestLayout();
            iVar.r(true);
            View view = Toolbar.this.f2145i;
            if (view instanceof androidx.appcompat.view.c) {
                ((androidx.appcompat.view.c) view).c();
            }
            Toolbar.this.R();
            return true;
        }
    }

    /* loaded from: classes.dex */
    public static class g extends a.C0006a {

        /* renamed from: b  reason: collision with root package name */
        int f2170b;

        public g(int i8, int i9) {
            super(i8, i9);
            this.f2170b = 0;
            this.f1539a = 8388627;
        }

        void a(ViewGroup.MarginLayoutParams marginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) this).leftMargin = marginLayoutParams.leftMargin;
            ((ViewGroup.MarginLayoutParams) this).topMargin = marginLayoutParams.topMargin;
            ((ViewGroup.MarginLayoutParams) this).rightMargin = marginLayoutParams.rightMargin;
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = marginLayoutParams.bottomMargin;
        }

        public g(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f2170b = 0;
        }

        public g(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f2170b = 0;
        }

        public g(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f2170b = 0;
            a(marginLayoutParams);
        }

        public g(a.C0006a c0006a) {
            super(c0006a);
            this.f2170b = 0;
        }

        public g(g gVar) {
            super((a.C0006a) gVar);
            this.f2170b = 0;
            this.f2170b = gVar.f2170b;
        }
    }

    /* loaded from: classes.dex */
    public interface h {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    /* loaded from: classes.dex */
    public static class i extends m1.a {
        public static final Parcelable.Creator<i> CREATOR = new a();

        /* renamed from: c  reason: collision with root package name */
        int f2171c;

        /* renamed from: d  reason: collision with root package name */
        boolean f2172d;

        /* loaded from: classes.dex */
        class a implements Parcelable.ClassLoaderCreator {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public i createFromParcel(Parcel parcel) {
                return new i(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* renamed from: b */
            public i createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new i(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: c */
            public i[] newArray(int i8) {
                return new i[i8];
            }
        }

        public i(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f2171c = parcel.readInt();
            this.f2172d = parcel.readInt() != 0;
        }

        @Override // m1.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            super.writeToParcel(parcel, i8);
            parcel.writeInt(this.f2171c);
            parcel.writeInt(this.f2172d ? 1 : 0);
        }

        public i(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, g.a.J);
    }

    private int C(View view, int i8, int[] iArr, int i9) {
        g gVar = (g) view.getLayoutParams();
        int i10 = ((ViewGroup.MarginLayoutParams) gVar).leftMargin - iArr[0];
        int max = i8 + Math.max(0, i10);
        iArr[0] = Math.max(0, -i10);
        int q8 = q(view, i9);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max, q8, max + measuredWidth, view.getMeasuredHeight() + q8);
        return max + measuredWidth + ((ViewGroup.MarginLayoutParams) gVar).rightMargin;
    }

    private int D(View view, int i8, int[] iArr, int i9) {
        g gVar = (g) view.getLayoutParams();
        int i10 = ((ViewGroup.MarginLayoutParams) gVar).rightMargin - iArr[1];
        int max = i8 - Math.max(0, i10);
        iArr[1] = Math.max(0, -i10);
        int q8 = q(view, i9);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max - measuredWidth, q8, max, view.getMeasuredHeight() + q8);
        return max - (measuredWidth + ((ViewGroup.MarginLayoutParams) gVar).leftMargin);
    }

    private int E(View view, int i8, int i9, int i10, int i11, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i12 = marginLayoutParams.leftMargin - iArr[0];
        int i13 = marginLayoutParams.rightMargin - iArr[1];
        int max = Math.max(0, i12) + Math.max(0, i13);
        iArr[0] = Math.max(0, -i12);
        iArr[1] = Math.max(0, -i13);
        view.measure(ViewGroup.getChildMeasureSpec(i8, getPaddingLeft() + getPaddingRight() + max + i9, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i10, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i11, marginLayoutParams.height));
        return view.getMeasuredWidth() + max;
    }

    private void F(View view, int i8, int i9, int i10, int i11, int i12) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i8, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i9, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i10, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i11, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i12 >= 0) {
            if (mode != 0) {
                i12 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i12);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i12, 1073741824);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    private void G() {
        Menu menu = getMenu();
        ArrayList<MenuItem> currentMenuItems = getCurrentMenuItems();
        this.G.h(menu, getMenuInflater());
        ArrayList<MenuItem> currentMenuItems2 = getCurrentMenuItems();
        currentMenuItems2.removeAll(currentMenuItems);
        this.H = currentMenuItems2;
    }

    private void H() {
        removeCallbacks(this.T);
        post(this.T);
    }

    private boolean O() {
        if (this.P) {
            int childCount = getChildCount();
            for (int i8 = 0; i8 < childCount; i8++) {
                View childAt = getChildAt(i8);
                if (P(childAt) && childAt.getMeasuredWidth() > 0 && childAt.getMeasuredHeight() > 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean P(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    private void b(List list, int i8) {
        boolean z7 = androidx.core.view.p0.p(this) == 1;
        int childCount = getChildCount();
        int a8 = androidx.core.view.s.a(i8, androidx.core.view.p0.p(this));
        list.clear();
        if (!z7) {
            for (int i9 = 0; i9 < childCount; i9++) {
                View childAt = getChildAt(i9);
                g gVar = (g) childAt.getLayoutParams();
                if (gVar.f2170b == 0 && P(childAt) && p(gVar.f1539a) == a8) {
                    list.add(childAt);
                }
            }
            return;
        }
        for (int i10 = childCount - 1; i10 >= 0; i10--) {
            View childAt2 = getChildAt(i10);
            g gVar2 = (g) childAt2.getLayoutParams();
            if (gVar2.f2170b == 0 && P(childAt2) && p(gVar2.f1539a) == a8) {
                list.add(childAt2);
            }
        }
    }

    private void c(View view, boolean z7) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        g generateDefaultLayoutParams = layoutParams == null ? generateDefaultLayoutParams() : !checkLayoutParams(layoutParams) ? generateLayoutParams(layoutParams) : (g) layoutParams;
        generateDefaultLayoutParams.f2170b = 1;
        if (!z7 || this.f2145i == null) {
            addView(view, generateDefaultLayoutParams);
            return;
        }
        view.setLayoutParams(generateDefaultLayoutParams);
        this.E.add(view);
    }

    private ArrayList<MenuItem> getCurrentMenuItems() {
        ArrayList<MenuItem> arrayList = new ArrayList<>();
        Menu menu = getMenu();
        for (int i8 = 0; i8 < menu.size(); i8++) {
            arrayList.add(menu.getItem(i8));
        }
        return arrayList;
    }

    private MenuInflater getMenuInflater() {
        return new androidx.appcompat.view.g(getContext());
    }

    private void h() {
        if (this.f2156t == null) {
            this.f2156t = new u0();
        }
    }

    private void i() {
        if (this.f2141e == null) {
            this.f2141e = new r(getContext());
        }
    }

    private void j() {
        k();
        if (this.f2137a.L() == null) {
            androidx.appcompat.view.menu.g gVar = (androidx.appcompat.view.menu.g) this.f2137a.getMenu();
            if (this.M == null) {
                this.M = new f();
            }
            this.f2137a.setExpandedActionViewsExclusive(true);
            gVar.c(this.M, this.f2146j);
            R();
        }
    }

    private void k() {
        if (this.f2137a == null) {
            ActionMenuView actionMenuView = new ActionMenuView(getContext());
            this.f2137a = actionMenuView;
            actionMenuView.setPopupTheme(this.f2147k);
            this.f2137a.setOnMenuItemClickListener(this.J);
            this.f2137a.M(this.N, new c());
            g generateDefaultLayoutParams = generateDefaultLayoutParams();
            generateDefaultLayoutParams.f1539a = (this.f2150n & 112) | 8388613;
            this.f2137a.setLayoutParams(generateDefaultLayoutParams);
            c(this.f2137a, false);
        }
    }

    private void l() {
        if (this.f2140d == null) {
            this.f2140d = new p(getContext(), null, g.a.I);
            g generateDefaultLayoutParams = generateDefaultLayoutParams();
            generateDefaultLayoutParams.f1539a = (this.f2150n & 112) | 8388611;
            this.f2140d.setLayoutParams(generateDefaultLayoutParams);
        }
    }

    private int p(int i8) {
        int p8 = androidx.core.view.p0.p(this);
        int a8 = androidx.core.view.s.a(i8, p8) & 7;
        return (a8 == 1 || a8 == 3 || a8 == 5) ? a8 : p8 == 1 ? 5 : 3;
    }

    private int q(View view, int i8) {
        g gVar = (g) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i9 = i8 > 0 ? (measuredHeight - i8) / 2 : 0;
        int r8 = r(gVar.f1539a);
        if (r8 != 48) {
            if (r8 != 80) {
                int paddingTop = getPaddingTop();
                int paddingBottom = getPaddingBottom();
                int height = getHeight();
                int i10 = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
                int i11 = ((ViewGroup.MarginLayoutParams) gVar).topMargin;
                if (i10 < i11) {
                    i10 = i11;
                } else {
                    int i12 = (((height - paddingBottom) - measuredHeight) - i10) - paddingTop;
                    int i13 = ((ViewGroup.MarginLayoutParams) gVar).bottomMargin;
                    if (i12 < i13) {
                        i10 = Math.max(0, i10 - (i13 - i12));
                    }
                }
                return paddingTop + i10;
            }
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) gVar).bottomMargin) - i9;
        }
        return getPaddingTop() - i9;
    }

    private int r(int i8) {
        int i9 = i8 & 112;
        return (i9 == 16 || i9 == 48 || i9 == 80) ? i9 : this.f2159w & 112;
    }

    private int s(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return androidx.core.view.v.b(marginLayoutParams) + androidx.core.view.v.a(marginLayoutParams);
    }

    private int t(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    private int u(List list, int[] iArr) {
        int i8 = iArr[0];
        int i9 = iArr[1];
        int size = list.size();
        int i10 = 0;
        int i11 = 0;
        while (i10 < size) {
            View view = (View) list.get(i10);
            g gVar = (g) view.getLayoutParams();
            int i12 = ((ViewGroup.MarginLayoutParams) gVar).leftMargin - i8;
            int i13 = ((ViewGroup.MarginLayoutParams) gVar).rightMargin - i9;
            int max = Math.max(0, i12);
            int max2 = Math.max(0, i13);
            int max3 = Math.max(0, -i12);
            int max4 = Math.max(0, -i13);
            i11 += max + view.getMeasuredWidth() + max2;
            i10++;
            i9 = max4;
            i8 = max3;
        }
        return i11;
    }

    private boolean z(View view) {
        return view.getParent() == this || this.E.contains(view);
    }

    public boolean A() {
        ActionMenuView actionMenuView = this.f2137a;
        return actionMenuView != null && actionMenuView.G();
    }

    public boolean B() {
        ActionMenuView actionMenuView = this.f2137a;
        return actionMenuView != null && actionMenuView.H();
    }

    void I() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (((g) childAt.getLayoutParams()).f2170b != 2 && childAt != this.f2137a) {
                removeViewAt(childCount);
                this.E.add(childAt);
            }
        }
    }

    public void J(int i8, int i9) {
        h();
        this.f2156t.g(i8, i9);
    }

    public void K(androidx.appcompat.view.menu.g gVar, androidx.appcompat.widget.c cVar) {
        if (gVar == null && this.f2137a == null) {
            return;
        }
        k();
        androidx.appcompat.view.menu.g L = this.f2137a.L();
        if (L == gVar) {
            return;
        }
        if (L != null) {
            L.O(this.L);
            L.O(this.M);
        }
        if (this.M == null) {
            this.M = new f();
        }
        cVar.G(true);
        if (gVar != null) {
            gVar.c(cVar, this.f2146j);
            gVar.c(this.M, this.f2146j);
        } else {
            cVar.d(this.f2146j, null);
            this.M.d(this.f2146j, null);
            cVar.f(true);
            this.M.f(true);
        }
        this.f2137a.setPopupTheme(this.f2147k);
        this.f2137a.setPresenter(cVar);
        this.L = cVar;
        R();
    }

    public void L(m.a aVar, g.a aVar2) {
        this.N = aVar;
        this.O = aVar2;
        ActionMenuView actionMenuView = this.f2137a;
        if (actionMenuView != null) {
            actionMenuView.M(aVar, aVar2);
        }
    }

    public void M(Context context, int i8) {
        this.f2149m = i8;
        TextView textView = this.f2139c;
        if (textView != null) {
            textView.setTextAppearance(context, i8);
        }
    }

    public void N(Context context, int i8) {
        this.f2148l = i8;
        TextView textView = this.f2138b;
        if (textView != null) {
            textView.setTextAppearance(context, i8);
        }
    }

    public boolean Q() {
        ActionMenuView actionMenuView = this.f2137a;
        return actionMenuView != null && actionMenuView.N();
    }

    void R() {
        OnBackInvokedDispatcher onBackInvokedDispatcher;
        if (Build.VERSION.SDK_INT >= 33) {
            OnBackInvokedDispatcher a8 = e.a(this);
            boolean z7 = v() && a8 != null && androidx.core.view.p0.y(this) && this.S;
            if (z7 && this.R == null) {
                if (this.Q == null) {
                    this.Q = e.b(new Runnable() { // from class: androidx.appcompat.widget.d1
                        @Override // java.lang.Runnable
                        public final void run() {
                            Toolbar.this.e();
                        }
                    });
                }
                e.c(a8, this.Q);
            } else if (z7 || (onBackInvokedDispatcher = this.R) == null) {
                return;
            } else {
                e.d(onBackInvokedDispatcher, this.Q);
                a8 = null;
            }
            this.R = a8;
        }
    }

    void a() {
        for (int size = this.E.size() - 1; size >= 0; size--) {
            addView((View) this.E.get(size));
        }
        this.E.clear();
    }

    @Override // androidx.core.view.w
    public void addMenuProvider(androidx.core.view.b0 b0Var) {
        this.G.c(b0Var);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof g);
    }

    public boolean d() {
        ActionMenuView actionMenuView;
        return getVisibility() == 0 && (actionMenuView = this.f2137a) != null && actionMenuView.I();
    }

    public void e() {
        f fVar = this.M;
        androidx.appcompat.view.menu.i iVar = fVar == null ? null : fVar.f2168b;
        if (iVar != null) {
            iVar.collapseActionView();
        }
    }

    public void f() {
        ActionMenuView actionMenuView = this.f2137a;
        if (actionMenuView != null) {
            actionMenuView.z();
        }
    }

    void g() {
        if (this.f2144h == null) {
            p pVar = new p(getContext(), null, g.a.I);
            this.f2144h = pVar;
            pVar.setImageDrawable(this.f2142f);
            this.f2144h.setContentDescription(this.f2143g);
            g generateDefaultLayoutParams = generateDefaultLayoutParams();
            generateDefaultLayoutParams.f1539a = (this.f2150n & 112) | 8388611;
            generateDefaultLayoutParams.f2170b = 2;
            this.f2144h.setLayoutParams(generateDefaultLayoutParams);
            this.f2144h.setOnClickListener(new d());
        }
    }

    public CharSequence getCollapseContentDescription() {
        ImageButton imageButton = this.f2144h;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getCollapseIcon() {
        ImageButton imageButton = this.f2144h;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public int getContentInsetEnd() {
        u0 u0Var = this.f2156t;
        if (u0Var != null) {
            return u0Var.a();
        }
        return 0;
    }

    public int getContentInsetEndWithActions() {
        int i8 = this.f2158v;
        return i8 != Integer.MIN_VALUE ? i8 : getContentInsetEnd();
    }

    public int getContentInsetLeft() {
        u0 u0Var = this.f2156t;
        if (u0Var != null) {
            return u0Var.b();
        }
        return 0;
    }

    public int getContentInsetRight() {
        u0 u0Var = this.f2156t;
        if (u0Var != null) {
            return u0Var.c();
        }
        return 0;
    }

    public int getContentInsetStart() {
        u0 u0Var = this.f2156t;
        if (u0Var != null) {
            return u0Var.d();
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int i8 = this.f2157u;
        return i8 != Integer.MIN_VALUE ? i8 : getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        androidx.appcompat.view.menu.g L;
        ActionMenuView actionMenuView = this.f2137a;
        return actionMenuView != null && (L = actionMenuView.L()) != null && L.hasVisibleItems() ? Math.max(getContentInsetEnd(), Math.max(this.f2158v, 0)) : getContentInsetEnd();
    }

    public int getCurrentContentInsetLeft() {
        return androidx.core.view.p0.p(this) == 1 ? getCurrentContentInsetEnd() : getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        return androidx.core.view.p0.p(this) == 1 ? getCurrentContentInsetStart() : getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        return getNavigationIcon() != null ? Math.max(getContentInsetStart(), Math.max(this.f2157u, 0)) : getContentInsetStart();
    }

    public Drawable getLogo() {
        ImageView imageView = this.f2141e;
        if (imageView != null) {
            return imageView.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        ImageView imageView = this.f2141e;
        if (imageView != null) {
            return imageView.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        j();
        return this.f2137a.getMenu();
    }

    View getNavButtonView() {
        return this.f2140d;
    }

    public CharSequence getNavigationContentDescription() {
        ImageButton imageButton = this.f2140d;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getNavigationIcon() {
        ImageButton imageButton = this.f2140d;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    androidx.appcompat.widget.c getOuterActionMenuPresenter() {
        return this.L;
    }

    public Drawable getOverflowIcon() {
        j();
        return this.f2137a.getOverflowIcon();
    }

    Context getPopupContext() {
        return this.f2146j;
    }

    public int getPopupTheme() {
        return this.f2147k;
    }

    public CharSequence getSubtitle() {
        return this.f2161y;
    }

    final TextView getSubtitleTextView() {
        return this.f2139c;
    }

    public CharSequence getTitle() {
        return this.f2160x;
    }

    public int getTitleMarginBottom() {
        return this.f2155s;
    }

    public int getTitleMarginEnd() {
        return this.f2153q;
    }

    public int getTitleMarginStart() {
        return this.f2152p;
    }

    public int getTitleMarginTop() {
        return this.f2154r;
    }

    final TextView getTitleTextView() {
        return this.f2138b;
    }

    public j0 getWrapper() {
        if (this.K == null) {
            this.K = new f1(this, true);
        }
        return this.K;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* renamed from: m */
    public g generateDefaultLayoutParams() {
        return new g(-2, -2);
    }

    @Override // android.view.ViewGroup
    /* renamed from: n */
    public g generateLayoutParams(AttributeSet attributeSet) {
        return new g(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* renamed from: o */
    public g generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof g ? new g((g) layoutParams) : layoutParams instanceof a.C0006a ? new g((a.C0006a) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new g((ViewGroup.MarginLayoutParams) layoutParams) : new g(layoutParams);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        R();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.T);
        R();
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.C = false;
        }
        if (!this.C) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.C = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.C = false;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x0295 A[LOOP:0: B:109:0x0293->B:110:0x0295, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x02b7 A[LOOP:1: B:112:0x02b5->B:113:0x02b7, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x02f0 A[LOOP:2: B:121:0x02ee->B:122:0x02f0, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x021d  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onLayout(boolean r20, int r21, int r22, int r23, int r24) {
        /*
            Method dump skipped, instructions count: 773
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.Toolbar.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    protected void onMeasure(int i8, int i9) {
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int[] iArr = this.F;
        boolean b8 = i1.b(this);
        int i17 = !b8 ? 1 : 0;
        if (P(this.f2140d)) {
            F(this.f2140d, i8, 0, i9, 0, this.f2151o);
            i10 = this.f2140d.getMeasuredWidth() + s(this.f2140d);
            i11 = Math.max(0, this.f2140d.getMeasuredHeight() + t(this.f2140d));
            i12 = View.combineMeasuredStates(0, this.f2140d.getMeasuredState());
        } else {
            i10 = 0;
            i11 = 0;
            i12 = 0;
        }
        if (P(this.f2144h)) {
            F(this.f2144h, i8, 0, i9, 0, this.f2151o);
            i10 = this.f2144h.getMeasuredWidth() + s(this.f2144h);
            i11 = Math.max(i11, this.f2144h.getMeasuredHeight() + t(this.f2144h));
            i12 = View.combineMeasuredStates(i12, this.f2144h.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int max = 0 + Math.max(currentContentInsetStart, i10);
        iArr[b8 ? 1 : 0] = Math.max(0, currentContentInsetStart - i10);
        if (P(this.f2137a)) {
            F(this.f2137a, i8, max, i9, 0, this.f2151o);
            i13 = this.f2137a.getMeasuredWidth() + s(this.f2137a);
            i11 = Math.max(i11, this.f2137a.getMeasuredHeight() + t(this.f2137a));
            i12 = View.combineMeasuredStates(i12, this.f2137a.getMeasuredState());
        } else {
            i13 = 0;
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int max2 = max + Math.max(currentContentInsetEnd, i13);
        iArr[i17] = Math.max(0, currentContentInsetEnd - i13);
        if (P(this.f2145i)) {
            max2 += E(this.f2145i, i8, max2, i9, 0, iArr);
            i11 = Math.max(i11, this.f2145i.getMeasuredHeight() + t(this.f2145i));
            i12 = View.combineMeasuredStates(i12, this.f2145i.getMeasuredState());
        }
        if (P(this.f2141e)) {
            max2 += E(this.f2141e, i8, max2, i9, 0, iArr);
            i11 = Math.max(i11, this.f2141e.getMeasuredHeight() + t(this.f2141e));
            i12 = View.combineMeasuredStates(i12, this.f2141e.getMeasuredState());
        }
        int childCount = getChildCount();
        for (int i18 = 0; i18 < childCount; i18++) {
            View childAt = getChildAt(i18);
            if (((g) childAt.getLayoutParams()).f2170b == 0 && P(childAt)) {
                max2 += E(childAt, i8, max2, i9, 0, iArr);
                i11 = Math.max(i11, childAt.getMeasuredHeight() + t(childAt));
                i12 = View.combineMeasuredStates(i12, childAt.getMeasuredState());
            }
        }
        int i19 = this.f2154r + this.f2155s;
        int i20 = this.f2152p + this.f2153q;
        if (P(this.f2138b)) {
            E(this.f2138b, i8, max2 + i20, i9, i19, iArr);
            int measuredWidth = this.f2138b.getMeasuredWidth() + s(this.f2138b);
            i14 = this.f2138b.getMeasuredHeight() + t(this.f2138b);
            i15 = View.combineMeasuredStates(i12, this.f2138b.getMeasuredState());
            i16 = measuredWidth;
        } else {
            i14 = 0;
            i15 = i12;
            i16 = 0;
        }
        if (P(this.f2139c)) {
            i16 = Math.max(i16, E(this.f2139c, i8, max2 + i20, i9, i14 + i19, iArr));
            i14 += this.f2139c.getMeasuredHeight() + t(this.f2139c);
            i15 = View.combineMeasuredStates(i15, this.f2139c.getMeasuredState());
        }
        setMeasuredDimension(View.resolveSizeAndState(Math.max(max2 + i16 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i8, (-16777216) & i15), O() ? 0 : View.resolveSizeAndState(Math.max(Math.max(i11, i14) + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i9, i15 << 16));
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem findItem;
        if (!(parcelable instanceof i)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        i iVar = (i) parcelable;
        super.onRestoreInstanceState(iVar.a());
        ActionMenuView actionMenuView = this.f2137a;
        androidx.appcompat.view.menu.g L = actionMenuView != null ? actionMenuView.L() : null;
        int i8 = iVar.f2171c;
        if (i8 != 0 && this.M != null && L != null && (findItem = L.findItem(i8)) != null) {
            findItem.expandActionView();
        }
        if (iVar.f2172d) {
            H();
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i8) {
        super.onRtlPropertiesChanged(i8);
        h();
        this.f2156t.f(i8 == 1);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        androidx.appcompat.view.menu.i iVar;
        i iVar2 = new i(super.onSaveInstanceState());
        f fVar = this.M;
        if (fVar != null && (iVar = fVar.f2168b) != null) {
            iVar2.f2171c = iVar.getItemId();
        }
        iVar2.f2172d = B();
        return iVar2;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.B = false;
        }
        if (!this.B) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !onTouchEvent) {
                this.B = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.B = false;
        }
        return true;
    }

    @Override // androidx.core.view.w
    public void removeMenuProvider(androidx.core.view.b0 b0Var) {
        this.G.l(b0Var);
    }

    public void setBackInvokedCallbackEnabled(boolean z7) {
        if (this.S != z7) {
            this.S = z7;
            R();
        }
    }

    public void setCollapseContentDescription(int i8) {
        setCollapseContentDescription(i8 != 0 ? getContext().getText(i8) : null);
    }

    public void setCollapseIcon(int i8) {
        setCollapseIcon(h.a.b(getContext(), i8));
    }

    public void setCollapsible(boolean z7) {
        this.P = z7;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i8) {
        if (i8 < 0) {
            i8 = Integer.MIN_VALUE;
        }
        if (i8 != this.f2158v) {
            this.f2158v = i8;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i8) {
        if (i8 < 0) {
            i8 = Integer.MIN_VALUE;
        }
        if (i8 != this.f2157u) {
            this.f2157u = i8;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setLogo(int i8) {
        setLogo(h.a.b(getContext(), i8));
    }

    public void setLogoDescription(int i8) {
        setLogoDescription(getContext().getText(i8));
    }

    public void setNavigationContentDescription(int i8) {
        setNavigationContentDescription(i8 != 0 ? getContext().getText(i8) : null);
    }

    public void setNavigationIcon(int i8) {
        setNavigationIcon(h.a.b(getContext(), i8));
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        l();
        this.f2140d.setOnClickListener(onClickListener);
    }

    public void setOnMenuItemClickListener(h hVar) {
        this.I = hVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        j();
        this.f2137a.setOverflowIcon(drawable);
    }

    public void setPopupTheme(int i8) {
        if (this.f2147k != i8) {
            this.f2147k = i8;
            if (i8 == 0) {
                this.f2146j = getContext();
            } else {
                this.f2146j = new ContextThemeWrapper(getContext(), i8);
            }
        }
    }

    public void setSubtitle(int i8) {
        setSubtitle(getContext().getText(i8));
    }

    public void setSubtitleTextColor(int i8) {
        setSubtitleTextColor(ColorStateList.valueOf(i8));
    }

    public void setTitle(int i8) {
        setTitle(getContext().getText(i8));
    }

    public void setTitleMarginBottom(int i8) {
        this.f2155s = i8;
        requestLayout();
    }

    public void setTitleMarginEnd(int i8) {
        this.f2153q = i8;
        requestLayout();
    }

    public void setTitleMarginStart(int i8) {
        this.f2152p = i8;
        requestLayout();
    }

    public void setTitleMarginTop(int i8) {
        this.f2154r = i8;
        requestLayout();
    }

    public void setTitleTextColor(int i8) {
        setTitleTextColor(ColorStateList.valueOf(i8));
    }

    public boolean v() {
        f fVar = this.M;
        return (fVar == null || fVar.f2168b == null) ? false : true;
    }

    public boolean w() {
        ActionMenuView actionMenuView = this.f2137a;
        return actionMenuView != null && actionMenuView.F();
    }

    public void x(int i8) {
        getMenuInflater().inflate(i8, getMenu());
    }

    public void y() {
        Iterator it = this.H.iterator();
        while (it.hasNext()) {
            getMenu().removeItem(((MenuItem) it.next()).getItemId());
        }
        G();
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f2159w = 8388627;
        this.D = new ArrayList();
        this.E = new ArrayList();
        this.F = new int[2];
        this.G = new androidx.core.view.z(new Runnable() { // from class: androidx.appcompat.widget.c1
            @Override // java.lang.Runnable
            public final void run() {
                Toolbar.this.y();
            }
        });
        this.H = new ArrayList();
        this.J = new a();
        this.T = new b();
        b1 u7 = b1.u(getContext(), attributeSet, g.j.O2, i8, 0);
        androidx.core.view.p0.L(this, context, g.j.O2, attributeSet, u7.q(), i8, 0);
        this.f2148l = u7.m(g.j.f7007q3, 0);
        this.f2149m = u7.m(g.j.f6962h3, 0);
        this.f2159w = u7.k(g.j.P2, this.f2159w);
        this.f2150n = u7.k(g.j.Q2, 48);
        int d8 = u7.d(g.j.f6977k3, 0);
        d8 = u7.r(g.j.f7002p3) ? u7.d(g.j.f7002p3, d8) : d8;
        this.f2155s = d8;
        this.f2154r = d8;
        this.f2153q = d8;
        this.f2152p = d8;
        int d9 = u7.d(g.j.f6992n3, -1);
        if (d9 >= 0) {
            this.f2152p = d9;
        }
        int d10 = u7.d(g.j.f6987m3, -1);
        if (d10 >= 0) {
            this.f2153q = d10;
        }
        int d11 = u7.d(g.j.f6997o3, -1);
        if (d11 >= 0) {
            this.f2154r = d11;
        }
        int d12 = u7.d(g.j.f6982l3, -1);
        if (d12 >= 0) {
            this.f2155s = d12;
        }
        this.f2151o = u7.e(g.j.f6932b3, -1);
        int d13 = u7.d(g.j.X2, Integer.MIN_VALUE);
        int d14 = u7.d(g.j.T2, Integer.MIN_VALUE);
        int e8 = u7.e(g.j.V2, 0);
        int e9 = u7.e(g.j.W2, 0);
        h();
        this.f2156t.e(e8, e9);
        if (d13 != Integer.MIN_VALUE || d14 != Integer.MIN_VALUE) {
            this.f2156t.g(d13, d14);
        }
        this.f2157u = u7.d(g.j.Y2, Integer.MIN_VALUE);
        this.f2158v = u7.d(g.j.U2, Integer.MIN_VALUE);
        this.f2142f = u7.f(g.j.S2);
        this.f2143g = u7.o(g.j.R2);
        CharSequence o8 = u7.o(g.j.f6972j3);
        if (!TextUtils.isEmpty(o8)) {
            setTitle(o8);
        }
        CharSequence o9 = u7.o(g.j.f6957g3);
        if (!TextUtils.isEmpty(o9)) {
            setSubtitle(o9);
        }
        this.f2146j = getContext();
        setPopupTheme(u7.m(g.j.f6952f3, 0));
        Drawable f8 = u7.f(g.j.f6947e3);
        if (f8 != null) {
            setNavigationIcon(f8);
        }
        CharSequence o10 = u7.o(g.j.f6942d3);
        if (!TextUtils.isEmpty(o10)) {
            setNavigationContentDescription(o10);
        }
        Drawable f9 = u7.f(g.j.Z2);
        if (f9 != null) {
            setLogo(f9);
        }
        CharSequence o11 = u7.o(g.j.f6927a3);
        if (!TextUtils.isEmpty(o11)) {
            setLogoDescription(o11);
        }
        if (u7.r(g.j.f7012r3)) {
            setTitleTextColor(u7.c(g.j.f7012r3));
        }
        if (u7.r(g.j.f6967i3)) {
            setSubtitleTextColor(u7.c(g.j.f6967i3));
        }
        if (u7.r(g.j.f6937c3)) {
            x(u7.m(g.j.f6937c3, 0));
        }
        u7.v();
    }

    public void setCollapseContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            g();
        }
        ImageButton imageButton = this.f2144h;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setCollapseIcon(Drawable drawable) {
        if (drawable != null) {
            g();
            this.f2144h.setImageDrawable(drawable);
            return;
        }
        ImageButton imageButton = this.f2144h;
        if (imageButton != null) {
            imageButton.setImageDrawable(this.f2142f);
        }
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            i();
            if (!z(this.f2141e)) {
                c(this.f2141e, true);
            }
        } else {
            ImageView imageView = this.f2141e;
            if (imageView != null && z(imageView)) {
                removeView(this.f2141e);
                this.E.remove(this.f2141e);
            }
        }
        ImageView imageView2 = this.f2141e;
        if (imageView2 != null) {
            imageView2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            i();
        }
        ImageView imageView = this.f2141e;
        if (imageView != null) {
            imageView.setContentDescription(charSequence);
        }
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            l();
        }
        ImageButton imageButton = this.f2140d;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
            g1.a(this.f2140d, charSequence);
        }
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            l();
            if (!z(this.f2140d)) {
                c(this.f2140d, true);
            }
        } else {
            ImageButton imageButton = this.f2140d;
            if (imageButton != null && z(imageButton)) {
                removeView(this.f2140d);
                this.E.remove(this.f2140d);
            }
        }
        ImageButton imageButton2 = this.f2140d;
        if (imageButton2 != null) {
            imageButton2.setImageDrawable(drawable);
        }
    }

    public void setSubtitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.f2139c;
            if (textView != null && z(textView)) {
                removeView(this.f2139c);
                this.E.remove(this.f2139c);
            }
        } else {
            if (this.f2139c == null) {
                Context context = getContext();
                d0 d0Var = new d0(context);
                this.f2139c = d0Var;
                d0Var.setSingleLine();
                this.f2139c.setEllipsize(TextUtils.TruncateAt.END);
                int i8 = this.f2149m;
                if (i8 != 0) {
                    this.f2139c.setTextAppearance(context, i8);
                }
                ColorStateList colorStateList = this.A;
                if (colorStateList != null) {
                    this.f2139c.setTextColor(colorStateList);
                }
            }
            if (!z(this.f2139c)) {
                c(this.f2139c, true);
            }
        }
        TextView textView2 = this.f2139c;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.f2161y = charSequence;
    }

    public void setSubtitleTextColor(ColorStateList colorStateList) {
        this.A = colorStateList;
        TextView textView = this.f2139c;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.f2138b;
            if (textView != null && z(textView)) {
                removeView(this.f2138b);
                this.E.remove(this.f2138b);
            }
        } else {
            if (this.f2138b == null) {
                Context context = getContext();
                d0 d0Var = new d0(context);
                this.f2138b = d0Var;
                d0Var.setSingleLine();
                this.f2138b.setEllipsize(TextUtils.TruncateAt.END);
                int i8 = this.f2148l;
                if (i8 != 0) {
                    this.f2138b.setTextAppearance(context, i8);
                }
                ColorStateList colorStateList = this.f2162z;
                if (colorStateList != null) {
                    this.f2138b.setTextColor(colorStateList);
                }
            }
            if (!z(this.f2138b)) {
                c(this.f2138b, true);
            }
        }
        TextView textView2 = this.f2138b;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.f2160x = charSequence;
    }

    public void setTitleTextColor(ColorStateList colorStateList) {
        this.f2162z = colorStateList;
        TextView textView = this.f2138b;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }
}
