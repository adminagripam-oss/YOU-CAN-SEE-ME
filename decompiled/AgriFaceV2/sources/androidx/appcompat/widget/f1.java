package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.view.menu.m;
/* loaded from: classes.dex */
public class f1 implements j0 {

    /* renamed from: a  reason: collision with root package name */
    Toolbar f2304a;

    /* renamed from: b  reason: collision with root package name */
    private int f2305b;

    /* renamed from: c  reason: collision with root package name */
    private View f2306c;

    /* renamed from: d  reason: collision with root package name */
    private View f2307d;

    /* renamed from: e  reason: collision with root package name */
    private Drawable f2308e;

    /* renamed from: f  reason: collision with root package name */
    private Drawable f2309f;

    /* renamed from: g  reason: collision with root package name */
    private Drawable f2310g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f2311h;

    /* renamed from: i  reason: collision with root package name */
    CharSequence f2312i;

    /* renamed from: j  reason: collision with root package name */
    private CharSequence f2313j;

    /* renamed from: k  reason: collision with root package name */
    private CharSequence f2314k;

    /* renamed from: l  reason: collision with root package name */
    Window.Callback f2315l;

    /* renamed from: m  reason: collision with root package name */
    boolean f2316m;

    /* renamed from: n  reason: collision with root package name */
    private c f2317n;

    /* renamed from: o  reason: collision with root package name */
    private int f2318o;

    /* renamed from: p  reason: collision with root package name */
    private int f2319p;

    /* renamed from: q  reason: collision with root package name */
    private Drawable f2320q;

    /* loaded from: classes.dex */
    class a implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        final androidx.appcompat.view.menu.a f2321a;

        a() {
            this.f2321a = new androidx.appcompat.view.menu.a(f1.this.f2304a.getContext(), 0, 16908332, 0, 0, f1.this.f2312i);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            f1 f1Var = f1.this;
            Window.Callback callback = f1Var.f2315l;
            if (callback == null || !f1Var.f2316m) {
                return;
            }
            callback.onMenuItemSelected(0, this.f2321a);
        }
    }

    /* loaded from: classes.dex */
    class b extends androidx.core.view.z0 {

        /* renamed from: a  reason: collision with root package name */
        private boolean f2323a = false;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ int f2324b;

        b(int i8) {
            this.f2324b = i8;
        }

        @Override // androidx.core.view.z0, androidx.core.view.y0
        public void a(View view) {
            this.f2323a = true;
        }

        @Override // androidx.core.view.y0
        public void b(View view) {
            if (this.f2323a) {
                return;
            }
            f1.this.f2304a.setVisibility(this.f2324b);
        }

        @Override // androidx.core.view.z0, androidx.core.view.y0
        public void c(View view) {
            f1.this.f2304a.setVisibility(0);
        }
    }

    public f1(Toolbar toolbar, boolean z7) {
        this(toolbar, z7, g.h.f6907a, g.e.f6849n);
    }

    private void H(CharSequence charSequence) {
        this.f2312i = charSequence;
        if ((this.f2305b & 8) != 0) {
            this.f2304a.setTitle(charSequence);
            if (this.f2311h) {
                androidx.core.view.p0.P(this.f2304a.getRootView(), charSequence);
            }
        }
    }

    private void I() {
        if ((this.f2305b & 4) != 0) {
            if (TextUtils.isEmpty(this.f2314k)) {
                this.f2304a.setNavigationContentDescription(this.f2319p);
            } else {
                this.f2304a.setNavigationContentDescription(this.f2314k);
            }
        }
    }

    private void J() {
        Toolbar toolbar;
        Drawable drawable;
        if ((this.f2305b & 4) != 0) {
            toolbar = this.f2304a;
            drawable = this.f2310g;
            if (drawable == null) {
                drawable = this.f2320q;
            }
        } else {
            toolbar = this.f2304a;
            drawable = null;
        }
        toolbar.setNavigationIcon(drawable);
    }

    private void K() {
        Drawable drawable;
        int i8 = this.f2305b;
        if ((i8 & 2) == 0) {
            drawable = null;
        } else if ((i8 & 1) == 0 || (drawable = this.f2309f) == null) {
            drawable = this.f2308e;
        }
        this.f2304a.setLogo(drawable);
    }

    private int z() {
        if (this.f2304a.getNavigationIcon() != null) {
            this.f2320q = this.f2304a.getNavigationIcon();
            return 15;
        }
        return 11;
    }

    public void A(View view) {
        View view2 = this.f2307d;
        if (view2 != null && (this.f2305b & 16) != 0) {
            this.f2304a.removeView(view2);
        }
        this.f2307d = view;
        if (view == null || (this.f2305b & 16) == 0) {
            return;
        }
        this.f2304a.addView(view);
    }

    public void B(int i8) {
        if (i8 == this.f2319p) {
            return;
        }
        this.f2319p = i8;
        if (TextUtils.isEmpty(this.f2304a.getNavigationContentDescription())) {
            D(this.f2319p);
        }
    }

    public void C(Drawable drawable) {
        this.f2309f = drawable;
        K();
    }

    public void D(int i8) {
        E(i8 == 0 ? null : getContext().getString(i8));
    }

    public void E(CharSequence charSequence) {
        this.f2314k = charSequence;
        I();
    }

    public void F(Drawable drawable) {
        this.f2310g = drawable;
        J();
    }

    public void G(CharSequence charSequence) {
        this.f2313j = charSequence;
        if ((this.f2305b & 8) != 0) {
            this.f2304a.setSubtitle(charSequence);
        }
    }

    @Override // androidx.appcompat.widget.j0
    public void a(Menu menu, m.a aVar) {
        if (this.f2317n == null) {
            c cVar = new c(this.f2304a.getContext());
            this.f2317n = cVar;
            cVar.p(g.f.f6868g);
        }
        this.f2317n.k(aVar);
        this.f2304a.K((androidx.appcompat.view.menu.g) menu, this.f2317n);
    }

    @Override // androidx.appcompat.widget.j0
    public boolean b() {
        return this.f2304a.B();
    }

    @Override // androidx.appcompat.widget.j0
    public void c() {
        this.f2316m = true;
    }

    @Override // androidx.appcompat.widget.j0
    public void collapseActionView() {
        this.f2304a.e();
    }

    @Override // androidx.appcompat.widget.j0
    public void d(Drawable drawable) {
        androidx.core.view.p0.Q(this.f2304a, drawable);
    }

    @Override // androidx.appcompat.widget.j0
    public boolean e() {
        return this.f2304a.A();
    }

    @Override // androidx.appcompat.widget.j0
    public boolean f() {
        return this.f2304a.w();
    }

    @Override // androidx.appcompat.widget.j0
    public boolean g() {
        return this.f2304a.Q();
    }

    @Override // androidx.appcompat.widget.j0
    public Context getContext() {
        return this.f2304a.getContext();
    }

    @Override // androidx.appcompat.widget.j0
    public CharSequence getTitle() {
        return this.f2304a.getTitle();
    }

    @Override // androidx.appcompat.widget.j0
    public boolean h() {
        return this.f2304a.d();
    }

    @Override // androidx.appcompat.widget.j0
    public void i() {
        this.f2304a.f();
    }

    @Override // androidx.appcompat.widget.j0
    public void j(m.a aVar, g.a aVar2) {
        this.f2304a.L(aVar, aVar2);
    }

    @Override // androidx.appcompat.widget.j0
    public void k(int i8) {
        this.f2304a.setVisibility(i8);
    }

    @Override // androidx.appcompat.widget.j0
    public void l(v0 v0Var) {
        View view = this.f2306c;
        if (view != null) {
            ViewParent parent = view.getParent();
            Toolbar toolbar = this.f2304a;
            if (parent == toolbar) {
                toolbar.removeView(this.f2306c);
            }
        }
        this.f2306c = v0Var;
    }

    @Override // androidx.appcompat.widget.j0
    public ViewGroup m() {
        return this.f2304a;
    }

    @Override // androidx.appcompat.widget.j0
    public void n(boolean z7) {
    }

    @Override // androidx.appcompat.widget.j0
    public int o() {
        return this.f2304a.getVisibility();
    }

    @Override // androidx.appcompat.widget.j0
    public boolean p() {
        return this.f2304a.v();
    }

    @Override // androidx.appcompat.widget.j0
    public void q(int i8) {
        View view;
        CharSequence charSequence;
        Toolbar toolbar;
        int i9 = this.f2305b ^ i8;
        this.f2305b = i8;
        if (i9 != 0) {
            if ((i9 & 4) != 0) {
                if ((i8 & 4) != 0) {
                    I();
                }
                J();
            }
            if ((i9 & 3) != 0) {
                K();
            }
            if ((i9 & 8) != 0) {
                if ((i8 & 8) != 0) {
                    this.f2304a.setTitle(this.f2312i);
                    toolbar = this.f2304a;
                    charSequence = this.f2313j;
                } else {
                    charSequence = null;
                    this.f2304a.setTitle((CharSequence) null);
                    toolbar = this.f2304a;
                }
                toolbar.setSubtitle(charSequence);
            }
            if ((i9 & 16) == 0 || (view = this.f2307d) == null) {
                return;
            }
            if ((i8 & 16) != 0) {
                this.f2304a.addView(view);
            } else {
                this.f2304a.removeView(view);
            }
        }
    }

    @Override // androidx.appcompat.widget.j0
    public int r() {
        return this.f2305b;
    }

    @Override // androidx.appcompat.widget.j0
    public Menu s() {
        return this.f2304a.getMenu();
    }

    @Override // androidx.appcompat.widget.j0
    public void setIcon(int i8) {
        setIcon(i8 != 0 ? h.a.b(getContext(), i8) : null);
    }

    @Override // androidx.appcompat.widget.j0
    public void setTitle(CharSequence charSequence) {
        this.f2311h = true;
        H(charSequence);
    }

    @Override // androidx.appcompat.widget.j0
    public void setWindowCallback(Window.Callback callback) {
        this.f2315l = callback;
    }

    @Override // androidx.appcompat.widget.j0
    public void setWindowTitle(CharSequence charSequence) {
        if (this.f2311h) {
            return;
        }
        H(charSequence);
    }

    @Override // androidx.appcompat.widget.j0
    public void t(int i8) {
        C(i8 != 0 ? h.a.b(getContext(), i8) : null);
    }

    @Override // androidx.appcompat.widget.j0
    public int u() {
        return this.f2318o;
    }

    @Override // androidx.appcompat.widget.j0
    public androidx.core.view.x0 v(int i8, long j8) {
        return androidx.core.view.p0.c(this.f2304a).b(i8 == 0 ? 1.0f : 0.0f).f(j8).h(new b(i8));
    }

    @Override // androidx.appcompat.widget.j0
    public void w() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // androidx.appcompat.widget.j0
    public void x() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // androidx.appcompat.widget.j0
    public void y(boolean z7) {
        this.f2304a.setCollapsible(z7);
    }

    public f1(Toolbar toolbar, boolean z7, int i8, int i9) {
        Drawable drawable;
        this.f2318o = 0;
        this.f2319p = 0;
        this.f2304a = toolbar;
        this.f2312i = toolbar.getTitle();
        this.f2313j = toolbar.getSubtitle();
        this.f2311h = this.f2312i != null;
        this.f2310g = toolbar.getNavigationIcon();
        b1 u7 = b1.u(toolbar.getContext(), null, g.j.f6923a, g.a.f6794c, 0);
        this.f2320q = u7.f(g.j.f6978l);
        if (z7) {
            CharSequence o8 = u7.o(g.j.f7008r);
            if (!TextUtils.isEmpty(o8)) {
                setTitle(o8);
            }
            CharSequence o9 = u7.o(g.j.f6998p);
            if (!TextUtils.isEmpty(o9)) {
                G(o9);
            }
            Drawable f8 = u7.f(g.j.f6988n);
            if (f8 != null) {
                C(f8);
            }
            Drawable f9 = u7.f(g.j.f6983m);
            if (f9 != null) {
                setIcon(f9);
            }
            if (this.f2310g == null && (drawable = this.f2320q) != null) {
                F(drawable);
            }
            q(u7.j(g.j.f6958h, 0));
            int m8 = u7.m(g.j.f6953g, 0);
            if (m8 != 0) {
                A(LayoutInflater.from(this.f2304a.getContext()).inflate(m8, (ViewGroup) this.f2304a, false));
                q(this.f2305b | 16);
            }
            int l8 = u7.l(g.j.f6968j, 0);
            if (l8 > 0) {
                ViewGroup.LayoutParams layoutParams = this.f2304a.getLayoutParams();
                layoutParams.height = l8;
                this.f2304a.setLayoutParams(layoutParams);
            }
            int d8 = u7.d(g.j.f6948f, -1);
            int d9 = u7.d(g.j.f6943e, -1);
            if (d8 >= 0 || d9 >= 0) {
                this.f2304a.J(Math.max(d8, 0), Math.max(d9, 0));
            }
            int m9 = u7.m(g.j.f7013s, 0);
            if (m9 != 0) {
                Toolbar toolbar2 = this.f2304a;
                toolbar2.N(toolbar2.getContext(), m9);
            }
            int m10 = u7.m(g.j.f7003q, 0);
            if (m10 != 0) {
                Toolbar toolbar3 = this.f2304a;
                toolbar3.M(toolbar3.getContext(), m10);
            }
            int m11 = u7.m(g.j.f6993o, 0);
            if (m11 != 0) {
                this.f2304a.setPopupTheme(m11);
            }
        } else {
            this.f2305b = z();
        }
        u7.v();
        B(i8);
        this.f2314k = this.f2304a.getNavigationContentDescription();
        this.f2304a.setNavigationOnClickListener(new a());
    }

    @Override // androidx.appcompat.widget.j0
    public void setIcon(Drawable drawable) {
        this.f2308e = drawable;
        K();
    }
}
