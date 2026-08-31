package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.view.menu.m;
import androidx.appcompat.widget.r0;
import androidx.core.view.p0;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class q extends k implements PopupWindow.OnDismissListener, View.OnKeyListener {

    /* renamed from: v  reason: collision with root package name */
    private static final int f1979v = g.g.f6900m;

    /* renamed from: b  reason: collision with root package name */
    private final Context f1980b;

    /* renamed from: c  reason: collision with root package name */
    private final g f1981c;

    /* renamed from: d  reason: collision with root package name */
    private final f f1982d;

    /* renamed from: e  reason: collision with root package name */
    private final boolean f1983e;

    /* renamed from: f  reason: collision with root package name */
    private final int f1984f;

    /* renamed from: g  reason: collision with root package name */
    private final int f1985g;

    /* renamed from: h  reason: collision with root package name */
    private final int f1986h;

    /* renamed from: i  reason: collision with root package name */
    final r0 f1987i;

    /* renamed from: l  reason: collision with root package name */
    private PopupWindow.OnDismissListener f1990l;

    /* renamed from: m  reason: collision with root package name */
    private View f1991m;

    /* renamed from: n  reason: collision with root package name */
    View f1992n;

    /* renamed from: o  reason: collision with root package name */
    private m.a f1993o;

    /* renamed from: p  reason: collision with root package name */
    ViewTreeObserver f1994p;

    /* renamed from: q  reason: collision with root package name */
    private boolean f1995q;

    /* renamed from: r  reason: collision with root package name */
    private boolean f1996r;

    /* renamed from: s  reason: collision with root package name */
    private int f1997s;

    /* renamed from: u  reason: collision with root package name */
    private boolean f1999u;

    /* renamed from: j  reason: collision with root package name */
    final ViewTreeObserver.OnGlobalLayoutListener f1988j = new a();

    /* renamed from: k  reason: collision with root package name */
    private final View.OnAttachStateChangeListener f1989k = new b();

    /* renamed from: t  reason: collision with root package name */
    private int f1998t = 0;

    /* loaded from: classes.dex */
    class a implements ViewTreeObserver.OnGlobalLayoutListener {
        a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!q.this.c() || q.this.f1987i.x()) {
                return;
            }
            View view = q.this.f1992n;
            if (view == null || !view.isShown()) {
                q.this.dismiss();
            } else {
                q.this.f1987i.a();
            }
        }
    }

    /* loaded from: classes.dex */
    class b implements View.OnAttachStateChangeListener {
        b() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            ViewTreeObserver viewTreeObserver = q.this.f1994p;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    q.this.f1994p = view.getViewTreeObserver();
                }
                q qVar = q.this;
                qVar.f1994p.removeGlobalOnLayoutListener(qVar.f1988j);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    public q(Context context, g gVar, View view, int i8, int i9, boolean z7) {
        this.f1980b = context;
        this.f1981c = gVar;
        this.f1983e = z7;
        this.f1982d = new f(gVar, LayoutInflater.from(context), z7, f1979v);
        this.f1985g = i8;
        this.f1986h = i9;
        Resources resources = context.getResources();
        this.f1984f = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(g.d.f6828b));
        this.f1991m = view;
        this.f1987i = new r0(context, null, i8, i9);
        gVar.c(this, context);
    }

    private boolean z() {
        View view;
        if (c()) {
            return true;
        }
        if (this.f1995q || (view = this.f1991m) == null) {
            return false;
        }
        this.f1992n = view;
        this.f1987i.G(this);
        this.f1987i.H(this);
        this.f1987i.F(true);
        View view2 = this.f1992n;
        boolean z7 = this.f1994p == null;
        ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
        this.f1994p = viewTreeObserver;
        if (z7) {
            viewTreeObserver.addOnGlobalLayoutListener(this.f1988j);
        }
        view2.addOnAttachStateChangeListener(this.f1989k);
        this.f1987i.z(view2);
        this.f1987i.C(this.f1998t);
        if (!this.f1996r) {
            this.f1997s = k.o(this.f1982d, null, this.f1980b, this.f1984f);
            this.f1996r = true;
        }
        this.f1987i.B(this.f1997s);
        this.f1987i.E(2);
        this.f1987i.D(n());
        this.f1987i.a();
        ListView h8 = this.f1987i.h();
        h8.setOnKeyListener(this);
        if (this.f1999u && this.f1981c.x() != null) {
            FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.f1980b).inflate(g.g.f6899l, (ViewGroup) h8, false);
            TextView textView = (TextView) frameLayout.findViewById(16908310);
            if (textView != null) {
                textView.setText(this.f1981c.x());
            }
            frameLayout.setEnabled(false);
            h8.addHeaderView(frameLayout, null, false);
        }
        this.f1987i.p(this.f1982d);
        this.f1987i.a();
        return true;
    }

    @Override // androidx.appcompat.view.menu.p
    public void a() {
        if (!z()) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public void b(g gVar, boolean z7) {
        if (gVar != this.f1981c) {
            return;
        }
        dismiss();
        m.a aVar = this.f1993o;
        if (aVar != null) {
            aVar.b(gVar, z7);
        }
    }

    @Override // androidx.appcompat.view.menu.p
    public boolean c() {
        return !this.f1995q && this.f1987i.c();
    }

    @Override // androidx.appcompat.view.menu.p
    public void dismiss() {
        if (c()) {
            this.f1987i.dismiss();
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean e(r rVar) {
        if (rVar.hasVisibleItems()) {
            l lVar = new l(this.f1980b, rVar, this.f1992n, this.f1983e, this.f1985g, this.f1986h);
            lVar.j(this.f1993o);
            lVar.g(k.x(rVar));
            lVar.i(this.f1990l);
            this.f1990l = null;
            this.f1981c.e(false);
            int e8 = this.f1987i.e();
            int n8 = this.f1987i.n();
            if ((Gravity.getAbsoluteGravity(this.f1998t, p0.p(this.f1991m)) & 7) == 5) {
                e8 += this.f1991m.getWidth();
            }
            if (lVar.n(e8, n8)) {
                m.a aVar = this.f1993o;
                if (aVar != null) {
                    aVar.c(rVar);
                    return true;
                }
                return true;
            }
        }
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public void f(boolean z7) {
        this.f1996r = false;
        f fVar = this.f1982d;
        if (fVar != null) {
            fVar.notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean g() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.p
    public ListView h() {
        return this.f1987i.h();
    }

    @Override // androidx.appcompat.view.menu.m
    public void k(m.a aVar) {
        this.f1993o = aVar;
    }

    @Override // androidx.appcompat.view.menu.k
    public void l(g gVar) {
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        this.f1995q = true;
        this.f1981c.close();
        ViewTreeObserver viewTreeObserver = this.f1994p;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.f1994p = this.f1992n.getViewTreeObserver();
            }
            this.f1994p.removeGlobalOnLayoutListener(this.f1988j);
            this.f1994p = null;
        }
        this.f1992n.removeOnAttachStateChangeListener(this.f1989k);
        PopupWindow.OnDismissListener onDismissListener = this.f1990l;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i8, KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1 && i8 == 82) {
            dismiss();
            return true;
        }
        return false;
    }

    @Override // androidx.appcompat.view.menu.k
    public void p(View view) {
        this.f1991m = view;
    }

    @Override // androidx.appcompat.view.menu.k
    public void r(boolean z7) {
        this.f1982d.d(z7);
    }

    @Override // androidx.appcompat.view.menu.k
    public void s(int i8) {
        this.f1998t = i8;
    }

    @Override // androidx.appcompat.view.menu.k
    public void t(int i8) {
        this.f1987i.l(i8);
    }

    @Override // androidx.appcompat.view.menu.k
    public void u(PopupWindow.OnDismissListener onDismissListener) {
        this.f1990l = onDismissListener;
    }

    @Override // androidx.appcompat.view.menu.k
    public void v(boolean z7) {
        this.f1999u = z7;
    }

    @Override // androidx.appcompat.view.menu.k
    public void w(int i8) {
        this.f1987i.j(i8);
    }
}
