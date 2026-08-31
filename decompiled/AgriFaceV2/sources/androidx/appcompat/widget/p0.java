package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public abstract class p0 implements androidx.appcompat.view.menu.p {
    private static Method G;
    private static Method H;
    private Runnable A;
    final Handler B;
    private final Rect C;
    private Rect D;
    private boolean E;
    PopupWindow F;

    /* renamed from: a  reason: collision with root package name */
    private Context f2425a;

    /* renamed from: b  reason: collision with root package name */
    private ListAdapter f2426b;

    /* renamed from: c  reason: collision with root package name */
    l0 f2427c;

    /* renamed from: d  reason: collision with root package name */
    private int f2428d;

    /* renamed from: e  reason: collision with root package name */
    private int f2429e;

    /* renamed from: f  reason: collision with root package name */
    private int f2430f;

    /* renamed from: g  reason: collision with root package name */
    private int f2431g;

    /* renamed from: h  reason: collision with root package name */
    private int f2432h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f2433i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f2434j;

    /* renamed from: k  reason: collision with root package name */
    private boolean f2435k;

    /* renamed from: l  reason: collision with root package name */
    private int f2436l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f2437m;

    /* renamed from: n  reason: collision with root package name */
    private boolean f2438n;

    /* renamed from: o  reason: collision with root package name */
    int f2439o;

    /* renamed from: p  reason: collision with root package name */
    private View f2440p;

    /* renamed from: q  reason: collision with root package name */
    private int f2441q;

    /* renamed from: r  reason: collision with root package name */
    private DataSetObserver f2442r;

    /* renamed from: s  reason: collision with root package name */
    private View f2443s;

    /* renamed from: t  reason: collision with root package name */
    private Drawable f2444t;

    /* renamed from: u  reason: collision with root package name */
    private AdapterView.OnItemClickListener f2445u;

    /* renamed from: v  reason: collision with root package name */
    private AdapterView.OnItemSelectedListener f2446v;

    /* renamed from: w  reason: collision with root package name */
    final i f2447w;

    /* renamed from: x  reason: collision with root package name */
    private final h f2448x;

    /* renamed from: y  reason: collision with root package name */
    private final g f2449y;

    /* renamed from: z  reason: collision with root package name */
    private final e f2450z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            View t8 = p0.this.t();
            if (t8 == null || t8.getWindowToken() == null) {
                return;
            }
            p0.this.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements AdapterView.OnItemSelectedListener {
        b() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView adapterView, View view, int i8, long j8) {
            l0 l0Var;
            if (i8 == -1 || (l0Var = p0.this.f2427c) == null) {
                return;
            }
            l0Var.setListSelectionHidden(false);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView adapterView) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class c {
        static int a(PopupWindow popupWindow, View view, int i8, boolean z7) {
            return popupWindow.getMaxAvailableHeight(view, i8, z7);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class d {
        static void a(PopupWindow popupWindow, Rect rect) {
            popupWindow.setEpicenterBounds(rect);
        }

        static void b(PopupWindow popupWindow, boolean z7) {
            popupWindow.setIsClippedToScreen(z7);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            p0.this.r();
        }
    }

    /* loaded from: classes.dex */
    private class f extends DataSetObserver {
        f() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            if (p0.this.c()) {
                p0.this.a();
            }
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            p0.this.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class g implements AbsListView.OnScrollListener {
        g() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i8, int i9, int i10) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i8) {
            if (i8 != 1 || p0.this.w() || p0.this.F.getContentView() == null) {
                return;
            }
            p0 p0Var = p0.this;
            p0Var.B.removeCallbacks(p0Var.f2447w);
            p0.this.f2447w.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class h implements View.OnTouchListener {
        h() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            PopupWindow popupWindow;
            int action = motionEvent.getAction();
            int x7 = (int) motionEvent.getX();
            int y7 = (int) motionEvent.getY();
            if (action == 0 && (popupWindow = p0.this.F) != null && popupWindow.isShowing() && x7 >= 0 && x7 < p0.this.F.getWidth() && y7 >= 0 && y7 < p0.this.F.getHeight()) {
                p0 p0Var = p0.this;
                p0Var.B.postDelayed(p0Var.f2447w, 250L);
                return false;
            } else if (action == 1) {
                p0 p0Var2 = p0.this;
                p0Var2.B.removeCallbacks(p0Var2.f2447w);
                return false;
            } else {
                return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class i implements Runnable {
        i() {
        }

        @Override // java.lang.Runnable
        public void run() {
            l0 l0Var = p0.this.f2427c;
            if (l0Var == null || !androidx.core.view.p0.y(l0Var) || p0.this.f2427c.getCount() <= p0.this.f2427c.getChildCount()) {
                return;
            }
            int childCount = p0.this.f2427c.getChildCount();
            p0 p0Var = p0.this;
            if (childCount <= p0Var.f2439o) {
                p0Var.F.setInputMethodMode(2);
                p0.this.a();
            }
        }
    }

    static {
        if (Build.VERSION.SDK_INT <= 28) {
            try {
                G = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException unused) {
                Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
            try {
                H = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            } catch (NoSuchMethodException unused2) {
                Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
            }
        }
    }

    public p0(Context context, AttributeSet attributeSet, int i8) {
        this(context, attributeSet, i8, 0);
    }

    private void J(boolean z7) {
        if (Build.VERSION.SDK_INT > 28) {
            d.b(this.F, z7);
            return;
        }
        Method method = G;
        if (method != null) {
            try {
                method.invoke(this.F, Boolean.valueOf(z7));
            } catch (Exception unused) {
                Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0150  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int q() {
        /*
            Method dump skipped, instructions count: 355
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.p0.q():int");
    }

    private int u(View view, int i8, boolean z7) {
        return c.a(this.F, view, i8, z7);
    }

    private void y() {
        View view = this.f2440p;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.f2440p);
            }
        }
    }

    public void A(int i8) {
        this.F.setAnimationStyle(i8);
    }

    public void B(int i8) {
        Drawable background = this.F.getBackground();
        if (background == null) {
            M(i8);
            return;
        }
        background.getPadding(this.C);
        Rect rect = this.C;
        this.f2429e = rect.left + rect.right + i8;
    }

    public void C(int i8) {
        this.f2436l = i8;
    }

    public void D(Rect rect) {
        this.D = rect != null ? new Rect(rect) : null;
    }

    public void E(int i8) {
        this.F.setInputMethodMode(i8);
    }

    public void F(boolean z7) {
        this.E = z7;
        this.F.setFocusable(z7);
    }

    public void G(PopupWindow.OnDismissListener onDismissListener) {
        this.F.setOnDismissListener(onDismissListener);
    }

    public void H(AdapterView.OnItemClickListener onItemClickListener) {
        this.f2445u = onItemClickListener;
    }

    public void I(boolean z7) {
        this.f2435k = true;
        this.f2434j = z7;
    }

    public void K(int i8) {
        this.f2441q = i8;
    }

    public void L(int i8) {
        l0 l0Var = this.f2427c;
        if (!c() || l0Var == null) {
            return;
        }
        l0Var.setListSelectionHidden(false);
        l0Var.setSelection(i8);
        if (l0Var.getChoiceMode() != 0) {
            l0Var.setItemChecked(i8, true);
        }
    }

    public void M(int i8) {
        this.f2429e = i8;
    }

    @Override // androidx.appcompat.view.menu.p
    public void a() {
        int q8 = q();
        boolean w7 = w();
        androidx.core.widget.h.b(this.F, this.f2432h);
        boolean z7 = true;
        if (this.F.isShowing()) {
            if (androidx.core.view.p0.y(t())) {
                int i8 = this.f2429e;
                if (i8 == -1) {
                    i8 = -1;
                } else if (i8 == -2) {
                    i8 = t().getWidth();
                }
                int i9 = this.f2428d;
                if (i9 == -1) {
                    if (!w7) {
                        q8 = -1;
                    }
                    if (w7) {
                        this.F.setWidth(this.f2429e == -1 ? -1 : 0);
                        this.F.setHeight(0);
                    } else {
                        this.F.setWidth(this.f2429e == -1 ? -1 : 0);
                        this.F.setHeight(-1);
                    }
                } else if (i9 != -2) {
                    q8 = i9;
                }
                PopupWindow popupWindow = this.F;
                if (this.f2438n || this.f2437m) {
                    z7 = false;
                }
                popupWindow.setOutsideTouchable(z7);
                this.F.update(t(), this.f2430f, this.f2431g, i8 < 0 ? -1 : i8, q8 < 0 ? -1 : q8);
                return;
            }
            return;
        }
        int i10 = this.f2429e;
        if (i10 == -1) {
            i10 = -1;
        } else if (i10 == -2) {
            i10 = t().getWidth();
        }
        int i11 = this.f2428d;
        if (i11 == -1) {
            q8 = -1;
        } else if (i11 != -2) {
            q8 = i11;
        }
        this.F.setWidth(i10);
        this.F.setHeight(q8);
        J(true);
        this.F.setOutsideTouchable((this.f2438n || this.f2437m) ? false : true);
        this.F.setTouchInterceptor(this.f2448x);
        if (this.f2435k) {
            androidx.core.widget.h.a(this.F, this.f2434j);
        }
        if (Build.VERSION.SDK_INT <= 28) {
            Method method = H;
            if (method != null) {
                try {
                    method.invoke(this.F, this.D);
                } catch (Exception e8) {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e8);
                }
            }
        } else {
            d.a(this.F, this.D);
        }
        androidx.core.widget.h.c(this.F, t(), this.f2430f, this.f2431g, this.f2436l);
        this.f2427c.setSelection(-1);
        if (!this.E || this.f2427c.isInTouchMode()) {
            r();
        }
        if (this.E) {
            return;
        }
        this.B.post(this.f2450z);
    }

    @Override // androidx.appcompat.view.menu.p
    public boolean c() {
        return this.F.isShowing();
    }

    public void d(Drawable drawable) {
        this.F.setBackgroundDrawable(drawable);
    }

    @Override // androidx.appcompat.view.menu.p
    public void dismiss() {
        this.F.dismiss();
        y();
        this.F.setContentView(null);
        this.f2427c = null;
        this.B.removeCallbacks(this.f2447w);
    }

    public int e() {
        return this.f2430f;
    }

    public Drawable g() {
        return this.F.getBackground();
    }

    @Override // androidx.appcompat.view.menu.p
    public ListView h() {
        return this.f2427c;
    }

    public void j(int i8) {
        this.f2431g = i8;
        this.f2433i = true;
    }

    public void l(int i8) {
        this.f2430f = i8;
    }

    public int n() {
        if (this.f2433i) {
            return this.f2431g;
        }
        return 0;
    }

    public void p(ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.f2442r;
        if (dataSetObserver == null) {
            this.f2442r = new f();
        } else {
            ListAdapter listAdapter2 = this.f2426b;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f2426b = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.f2442r);
        }
        l0 l0Var = this.f2427c;
        if (l0Var != null) {
            l0Var.setAdapter(this.f2426b);
        }
    }

    public void r() {
        l0 l0Var = this.f2427c;
        if (l0Var != null) {
            l0Var.setListSelectionHidden(true);
            l0Var.requestLayout();
        }
    }

    l0 s(Context context, boolean z7) {
        return new l0(context, z7);
    }

    public View t() {
        return this.f2443s;
    }

    public int v() {
        return this.f2429e;
    }

    public boolean w() {
        return this.F.getInputMethodMode() == 2;
    }

    public boolean x() {
        return this.E;
    }

    public void z(View view) {
        this.f2443s = view;
    }

    public p0(Context context, AttributeSet attributeSet, int i8, int i9) {
        this.f2428d = -2;
        this.f2429e = -2;
        this.f2432h = 1002;
        this.f2436l = 0;
        this.f2437m = false;
        this.f2438n = false;
        this.f2439o = Integer.MAX_VALUE;
        this.f2441q = 0;
        this.f2447w = new i();
        this.f2448x = new h();
        this.f2449y = new g();
        this.f2450z = new e();
        this.C = new Rect();
        this.f2425a = context;
        this.B = new Handler(context.getMainLooper());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, g.j.f6980l1, i8, i9);
        this.f2430f = obtainStyledAttributes.getDimensionPixelOffset(g.j.f6985m1, 0);
        int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(g.j.f6990n1, 0);
        this.f2431g = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.f2433i = true;
        }
        obtainStyledAttributes.recycle();
        t tVar = new t(context, attributeSet, i8, i9);
        this.F = tVar;
        tVar.setInputMethodMode(1);
    }
}
