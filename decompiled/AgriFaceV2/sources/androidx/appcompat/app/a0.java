package androidx.appcompat.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.appcompat.view.b;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.j0;
import androidx.core.view.a1;
import androidx.core.view.p0;
import androidx.core.view.x0;
import androidx.core.view.y0;
import androidx.core.view.z0;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class a0 extends androidx.appcompat.app.a implements ActionBarOverlayLayout.d {
    private static final Interpolator D = new AccelerateInterpolator();
    private static final Interpolator E = new DecelerateInterpolator();

    /* renamed from: a  reason: collision with root package name */
    Context f1540a;

    /* renamed from: b  reason: collision with root package name */
    private Context f1541b;

    /* renamed from: c  reason: collision with root package name */
    private Activity f1542c;

    /* renamed from: d  reason: collision with root package name */
    ActionBarOverlayLayout f1543d;

    /* renamed from: e  reason: collision with root package name */
    ActionBarContainer f1544e;

    /* renamed from: f  reason: collision with root package name */
    j0 f1545f;

    /* renamed from: g  reason: collision with root package name */
    ActionBarContextView f1546g;

    /* renamed from: h  reason: collision with root package name */
    View f1547h;

    /* renamed from: k  reason: collision with root package name */
    private boolean f1550k;

    /* renamed from: l  reason: collision with root package name */
    d f1551l;

    /* renamed from: m  reason: collision with root package name */
    androidx.appcompat.view.b f1552m;

    /* renamed from: n  reason: collision with root package name */
    b.a f1553n;

    /* renamed from: o  reason: collision with root package name */
    private boolean f1554o;

    /* renamed from: q  reason: collision with root package name */
    private boolean f1556q;

    /* renamed from: t  reason: collision with root package name */
    boolean f1559t;

    /* renamed from: u  reason: collision with root package name */
    boolean f1560u;

    /* renamed from: v  reason: collision with root package name */
    private boolean f1561v;

    /* renamed from: x  reason: collision with root package name */
    androidx.appcompat.view.h f1563x;

    /* renamed from: y  reason: collision with root package name */
    private boolean f1564y;

    /* renamed from: z  reason: collision with root package name */
    boolean f1565z;

    /* renamed from: i  reason: collision with root package name */
    private ArrayList f1548i = new ArrayList();

    /* renamed from: j  reason: collision with root package name */
    private int f1549j = -1;

    /* renamed from: p  reason: collision with root package name */
    private ArrayList f1555p = new ArrayList();

    /* renamed from: r  reason: collision with root package name */
    private int f1557r = 0;

    /* renamed from: s  reason: collision with root package name */
    boolean f1558s = true;

    /* renamed from: w  reason: collision with root package name */
    private boolean f1562w = true;
    final y0 A = new a();
    final y0 B = new b();
    final a1 C = new c();

    /* loaded from: classes.dex */
    class a extends z0 {
        a() {
        }

        @Override // androidx.core.view.y0
        public void b(View view) {
            View view2;
            a0 a0Var = a0.this;
            if (a0Var.f1558s && (view2 = a0Var.f1547h) != null) {
                view2.setTranslationY(0.0f);
                a0.this.f1544e.setTranslationY(0.0f);
            }
            a0.this.f1544e.setVisibility(8);
            a0.this.f1544e.setTransitioning(false);
            a0 a0Var2 = a0.this;
            a0Var2.f1563x = null;
            a0Var2.D();
            ActionBarOverlayLayout actionBarOverlayLayout = a0.this.f1543d;
            if (actionBarOverlayLayout != null) {
                p0.K(actionBarOverlayLayout);
            }
        }
    }

    /* loaded from: classes.dex */
    class b extends z0 {
        b() {
        }

        @Override // androidx.core.view.y0
        public void b(View view) {
            a0 a0Var = a0.this;
            a0Var.f1563x = null;
            a0Var.f1544e.requestLayout();
        }
    }

    /* loaded from: classes.dex */
    class c implements a1 {
        c() {
        }

        @Override // androidx.core.view.a1
        public void a(View view) {
            ((View) a0.this.f1544e.getParent()).invalidate();
        }
    }

    /* loaded from: classes.dex */
    public class d extends androidx.appcompat.view.b implements g.a {

        /* renamed from: c  reason: collision with root package name */
        private final Context f1569c;

        /* renamed from: d  reason: collision with root package name */
        private final androidx.appcompat.view.menu.g f1570d;

        /* renamed from: e  reason: collision with root package name */
        private b.a f1571e;

        /* renamed from: f  reason: collision with root package name */
        private WeakReference f1572f;

        public d(Context context, b.a aVar) {
            this.f1569c = context;
            this.f1571e = aVar;
            androidx.appcompat.view.menu.g S = new androidx.appcompat.view.menu.g(context).S(1);
            this.f1570d = S;
            S.R(this);
        }

        @Override // androidx.appcompat.view.menu.g.a
        public boolean a(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
            b.a aVar = this.f1571e;
            if (aVar != null) {
                return aVar.c(this, menuItem);
            }
            return false;
        }

        @Override // androidx.appcompat.view.menu.g.a
        public void b(androidx.appcompat.view.menu.g gVar) {
            if (this.f1571e == null) {
                return;
            }
            k();
            a0.this.f1546g.l();
        }

        @Override // androidx.appcompat.view.b
        public void c() {
            a0 a0Var = a0.this;
            if (a0Var.f1551l != this) {
                return;
            }
            if (a0.C(a0Var.f1559t, a0Var.f1560u, false)) {
                this.f1571e.b(this);
            } else {
                a0 a0Var2 = a0.this;
                a0Var2.f1552m = this;
                a0Var2.f1553n = this.f1571e;
            }
            this.f1571e = null;
            a0.this.B(false);
            a0.this.f1546g.g();
            a0 a0Var3 = a0.this;
            a0Var3.f1543d.setHideOnContentScrollEnabled(a0Var3.f1565z);
            a0.this.f1551l = null;
        }

        @Override // androidx.appcompat.view.b
        public View d() {
            WeakReference weakReference = this.f1572f;
            if (weakReference != null) {
                return (View) weakReference.get();
            }
            return null;
        }

        @Override // androidx.appcompat.view.b
        public Menu e() {
            return this.f1570d;
        }

        @Override // androidx.appcompat.view.b
        public MenuInflater f() {
            return new androidx.appcompat.view.g(this.f1569c);
        }

        @Override // androidx.appcompat.view.b
        public CharSequence g() {
            return a0.this.f1546g.getSubtitle();
        }

        @Override // androidx.appcompat.view.b
        public CharSequence i() {
            return a0.this.f1546g.getTitle();
        }

        @Override // androidx.appcompat.view.b
        public void k() {
            if (a0.this.f1551l != this) {
                return;
            }
            this.f1570d.e0();
            try {
                this.f1571e.a(this, this.f1570d);
            } finally {
                this.f1570d.d0();
            }
        }

        @Override // androidx.appcompat.view.b
        public boolean l() {
            return a0.this.f1546g.j();
        }

        @Override // androidx.appcompat.view.b
        public void m(View view) {
            a0.this.f1546g.setCustomView(view);
            this.f1572f = new WeakReference(view);
        }

        @Override // androidx.appcompat.view.b
        public void n(int i8) {
            o(a0.this.f1540a.getResources().getString(i8));
        }

        @Override // androidx.appcompat.view.b
        public void o(CharSequence charSequence) {
            a0.this.f1546g.setSubtitle(charSequence);
        }

        @Override // androidx.appcompat.view.b
        public void q(int i8) {
            r(a0.this.f1540a.getResources().getString(i8));
        }

        @Override // androidx.appcompat.view.b
        public void r(CharSequence charSequence) {
            a0.this.f1546g.setTitle(charSequence);
        }

        @Override // androidx.appcompat.view.b
        public void s(boolean z7) {
            super.s(z7);
            a0.this.f1546g.setTitleOptional(z7);
        }

        public boolean t() {
            this.f1570d.e0();
            try {
                return this.f1571e.d(this, this.f1570d);
            } finally {
                this.f1570d.d0();
            }
        }
    }

    public a0(Activity activity, boolean z7) {
        this.f1542c = activity;
        View decorView = activity.getWindow().getDecorView();
        L(decorView);
        if (z7) {
            return;
        }
        this.f1547h = decorView.findViewById(16908290);
    }

    static boolean C(boolean z7, boolean z8, boolean z9) {
        if (z9) {
            return true;
        }
        return (z7 || z8) ? false : true;
    }

    private j0 G(View view) {
        if (view instanceof j0) {
            return (j0) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can't make a decor toolbar out of ");
        sb.append(view != null ? view.getClass().getSimpleName() : "null");
        throw new IllegalStateException(sb.toString());
    }

    private void K() {
        if (this.f1561v) {
            this.f1561v = false;
            ActionBarOverlayLayout actionBarOverlayLayout = this.f1543d;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setShowingForActionMode(false);
            }
            U(false);
        }
    }

    private void L(View view) {
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(g.f.f6877p);
        this.f1543d = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        this.f1545f = G(view.findViewById(g.f.f6862a));
        this.f1546g = (ActionBarContextView) view.findViewById(g.f.f6867f);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(g.f.f6864c);
        this.f1544e = actionBarContainer;
        j0 j0Var = this.f1545f;
        if (j0Var == null || this.f1546g == null || actionBarContainer == null) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with a compatible window decor layout");
        }
        this.f1540a = j0Var.getContext();
        boolean z7 = (this.f1545f.r() & 4) != 0;
        if (z7) {
            this.f1550k = true;
        }
        androidx.appcompat.view.a b8 = androidx.appcompat.view.a.b(this.f1540a);
        R(b8.a() || z7);
        P(b8.e());
        TypedArray obtainStyledAttributes = this.f1540a.obtainStyledAttributes(null, g.j.f6923a, g.a.f6794c, 0);
        if (obtainStyledAttributes.getBoolean(g.j.f6973k, false)) {
            Q(true);
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(g.j.f6963i, 0);
        if (dimensionPixelSize != 0) {
            O(dimensionPixelSize);
        }
        obtainStyledAttributes.recycle();
    }

    private void P(boolean z7) {
        this.f1556q = z7;
        if (z7) {
            this.f1544e.setTabContainer(null);
            this.f1545f.l(null);
        } else {
            this.f1545f.l(null);
            this.f1544e.setTabContainer(null);
        }
        boolean z8 = true;
        boolean z9 = J() == 2;
        this.f1545f.y(!this.f1556q && z9);
        ActionBarOverlayLayout actionBarOverlayLayout = this.f1543d;
        if (this.f1556q || !z9) {
            z8 = false;
        }
        actionBarOverlayLayout.setHasNonEmbeddedTabs(z8);
    }

    private boolean S() {
        return p0.z(this.f1544e);
    }

    private void T() {
        if (this.f1561v) {
            return;
        }
        this.f1561v = true;
        ActionBarOverlayLayout actionBarOverlayLayout = this.f1543d;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setShowingForActionMode(true);
        }
        U(false);
    }

    private void U(boolean z7) {
        if (C(this.f1559t, this.f1560u, this.f1561v)) {
            if (this.f1562w) {
                return;
            }
            this.f1562w = true;
            F(z7);
        } else if (this.f1562w) {
            this.f1562w = false;
            E(z7);
        }
    }

    @Override // androidx.appcompat.app.a
    public androidx.appcompat.view.b A(b.a aVar) {
        d dVar = this.f1551l;
        if (dVar != null) {
            dVar.c();
        }
        this.f1543d.setHideOnContentScrollEnabled(false);
        this.f1546g.k();
        d dVar2 = new d(this.f1546g.getContext(), aVar);
        if (dVar2.t()) {
            this.f1551l = dVar2;
            dVar2.k();
            this.f1546g.h(dVar2);
            B(true);
            return dVar2;
        }
        return null;
    }

    public void B(boolean z7) {
        x0 v7;
        x0 f8;
        if (z7) {
            T();
        } else {
            K();
        }
        if (!S()) {
            if (z7) {
                this.f1545f.k(4);
                this.f1546g.setVisibility(0);
                return;
            }
            this.f1545f.k(0);
            this.f1546g.setVisibility(8);
            return;
        }
        if (z7) {
            f8 = this.f1545f.v(4, 100L);
            v7 = this.f1546g.f(0, 200L);
        } else {
            v7 = this.f1545f.v(0, 200L);
            f8 = this.f1546g.f(8, 100L);
        }
        androidx.appcompat.view.h hVar = new androidx.appcompat.view.h();
        hVar.d(f8, v7);
        hVar.h();
    }

    void D() {
        b.a aVar = this.f1553n;
        if (aVar != null) {
            aVar.b(this.f1552m);
            this.f1552m = null;
            this.f1553n = null;
        }
    }

    public void E(boolean z7) {
        View view;
        int[] iArr;
        androidx.appcompat.view.h hVar = this.f1563x;
        if (hVar != null) {
            hVar.a();
        }
        if (this.f1557r != 0 || (!this.f1564y && !z7)) {
            this.A.b(null);
            return;
        }
        this.f1544e.setAlpha(1.0f);
        this.f1544e.setTransitioning(true);
        androidx.appcompat.view.h hVar2 = new androidx.appcompat.view.h();
        float f8 = -this.f1544e.getHeight();
        if (z7) {
            this.f1544e.getLocationInWindow(new int[]{0, 0});
            f8 -= iArr[1];
        }
        x0 m8 = p0.c(this.f1544e).m(f8);
        m8.k(this.C);
        hVar2.c(m8);
        if (this.f1558s && (view = this.f1547h) != null) {
            hVar2.c(p0.c(view).m(f8));
        }
        hVar2.f(D);
        hVar2.e(250L);
        hVar2.g(this.A);
        this.f1563x = hVar2;
        hVar2.h();
    }

    public void F(boolean z7) {
        View view;
        View view2;
        int[] iArr;
        androidx.appcompat.view.h hVar = this.f1563x;
        if (hVar != null) {
            hVar.a();
        }
        this.f1544e.setVisibility(0);
        if (this.f1557r == 0 && (this.f1564y || z7)) {
            this.f1544e.setTranslationY(0.0f);
            float f8 = -this.f1544e.getHeight();
            if (z7) {
                this.f1544e.getLocationInWindow(new int[]{0, 0});
                f8 -= iArr[1];
            }
            this.f1544e.setTranslationY(f8);
            androidx.appcompat.view.h hVar2 = new androidx.appcompat.view.h();
            x0 m8 = p0.c(this.f1544e).m(0.0f);
            m8.k(this.C);
            hVar2.c(m8);
            if (this.f1558s && (view2 = this.f1547h) != null) {
                view2.setTranslationY(f8);
                hVar2.c(p0.c(this.f1547h).m(0.0f));
            }
            hVar2.f(E);
            hVar2.e(250L);
            hVar2.g(this.B);
            this.f1563x = hVar2;
            hVar2.h();
        } else {
            this.f1544e.setAlpha(1.0f);
            this.f1544e.setTranslationY(0.0f);
            if (this.f1558s && (view = this.f1547h) != null) {
                view.setTranslationY(0.0f);
            }
            this.B.b(null);
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.f1543d;
        if (actionBarOverlayLayout != null) {
            p0.K(actionBarOverlayLayout);
        }
    }

    public int H() {
        return this.f1544e.getHeight();
    }

    public int I() {
        return this.f1543d.getActionBarHideOffset();
    }

    public int J() {
        return this.f1545f.u();
    }

    public void M(boolean z7) {
        N(z7 ? 4 : 0, 4);
    }

    public void N(int i8, int i9) {
        int r8 = this.f1545f.r();
        if ((i9 & 4) != 0) {
            this.f1550k = true;
        }
        this.f1545f.q((i8 & i9) | ((~i9) & r8));
    }

    public void O(float f8) {
        p0.T(this.f1544e, f8);
    }

    public void Q(boolean z7) {
        if (z7 && !this.f1543d.q()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        }
        this.f1565z = z7;
        this.f1543d.setHideOnContentScrollEnabled(z7);
    }

    public void R(boolean z7) {
        this.f1545f.n(z7);
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void a() {
        if (this.f1560u) {
            this.f1560u = false;
            U(true);
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void b() {
        androidx.appcompat.view.h hVar = this.f1563x;
        if (hVar != null) {
            hVar.a();
            this.f1563x = null;
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void c(int i8) {
        this.f1557r = i8;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void d() {
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void e(boolean z7) {
        this.f1558s = z7;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void f() {
        if (this.f1560u) {
            return;
        }
        this.f1560u = true;
        U(true);
    }

    @Override // androidx.appcompat.app.a
    public boolean h() {
        j0 j0Var = this.f1545f;
        if (j0Var == null || !j0Var.p()) {
            return false;
        }
        this.f1545f.collapseActionView();
        return true;
    }

    @Override // androidx.appcompat.app.a
    public void i(boolean z7) {
        if (z7 == this.f1554o) {
            return;
        }
        this.f1554o = z7;
        if (this.f1555p.size() <= 0) {
            return;
        }
        w.a(this.f1555p.get(0));
        throw null;
    }

    @Override // androidx.appcompat.app.a
    public int j() {
        return this.f1545f.r();
    }

    @Override // androidx.appcompat.app.a
    public Context k() {
        if (this.f1541b == null) {
            TypedValue typedValue = new TypedValue();
            this.f1540a.getTheme().resolveAttribute(g.a.f6796e, typedValue, true);
            int i8 = typedValue.resourceId;
            if (i8 != 0) {
                this.f1541b = new ContextThemeWrapper(this.f1540a, i8);
            } else {
                this.f1541b = this.f1540a;
            }
        }
        return this.f1541b;
    }

    @Override // androidx.appcompat.app.a
    public void l() {
        if (this.f1559t) {
            return;
        }
        this.f1559t = true;
        U(false);
    }

    @Override // androidx.appcompat.app.a
    public boolean n() {
        int H = H();
        return this.f1562w && (H == 0 || I() < H);
    }

    @Override // androidx.appcompat.app.a
    public void o(Configuration configuration) {
        P(androidx.appcompat.view.a.b(this.f1540a).e());
    }

    @Override // androidx.appcompat.app.a
    public boolean q(int i8, KeyEvent keyEvent) {
        Menu e8;
        d dVar = this.f1551l;
        if (dVar == null || (e8 = dVar.e()) == null) {
            return false;
        }
        e8.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return e8.performShortcut(i8, keyEvent, 0);
    }

    @Override // androidx.appcompat.app.a
    public void t(Drawable drawable) {
        this.f1544e.setPrimaryBackground(drawable);
    }

    @Override // androidx.appcompat.app.a
    public void u(boolean z7) {
        if (this.f1550k) {
            return;
        }
        M(z7);
    }

    @Override // androidx.appcompat.app.a
    public void v(boolean z7) {
        N(z7 ? 8 : 0, 8);
    }

    @Override // androidx.appcompat.app.a
    public void w(boolean z7) {
        androidx.appcompat.view.h hVar;
        this.f1564y = z7;
        if (z7 || (hVar = this.f1563x) == null) {
            return;
        }
        hVar.a();
    }

    @Override // androidx.appcompat.app.a
    public void x(CharSequence charSequence) {
        this.f1545f.setTitle(charSequence);
    }

    @Override // androidx.appcompat.app.a
    public void y(CharSequence charSequence) {
        this.f1545f.setWindowTitle(charSequence);
    }

    @Override // androidx.appcompat.app.a
    public void z() {
        if (this.f1559t) {
            this.f1559t = false;
            U(false);
        }
    }

    public a0(Dialog dialog) {
        L(dialog.getWindow().getDecorView());
    }
}
