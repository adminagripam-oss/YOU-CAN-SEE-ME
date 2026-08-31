package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Handler;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.view.menu.m;
import androidx.appcompat.widget.q0;
import androidx.appcompat.widget.r0;
import androidx.core.view.p0;
import androidx.core.view.s;
import java.util.ArrayList;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class d extends k implements View.OnKeyListener, PopupWindow.OnDismissListener {
    private static final int B = g.g.f6892e;
    boolean A;

    /* renamed from: b  reason: collision with root package name */
    private final Context f1844b;

    /* renamed from: c  reason: collision with root package name */
    private final int f1845c;

    /* renamed from: d  reason: collision with root package name */
    private final int f1846d;

    /* renamed from: e  reason: collision with root package name */
    private final int f1847e;

    /* renamed from: f  reason: collision with root package name */
    private final boolean f1848f;

    /* renamed from: g  reason: collision with root package name */
    final Handler f1849g;

    /* renamed from: o  reason: collision with root package name */
    private View f1857o;

    /* renamed from: p  reason: collision with root package name */
    View f1858p;

    /* renamed from: r  reason: collision with root package name */
    private boolean f1860r;

    /* renamed from: s  reason: collision with root package name */
    private boolean f1861s;

    /* renamed from: t  reason: collision with root package name */
    private int f1862t;

    /* renamed from: u  reason: collision with root package name */
    private int f1863u;

    /* renamed from: w  reason: collision with root package name */
    private boolean f1865w;

    /* renamed from: x  reason: collision with root package name */
    private m.a f1866x;

    /* renamed from: y  reason: collision with root package name */
    ViewTreeObserver f1867y;

    /* renamed from: z  reason: collision with root package name */
    private PopupWindow.OnDismissListener f1868z;

    /* renamed from: h  reason: collision with root package name */
    private final List f1850h = new ArrayList();

    /* renamed from: i  reason: collision with root package name */
    final List f1851i = new ArrayList();

    /* renamed from: j  reason: collision with root package name */
    final ViewTreeObserver.OnGlobalLayoutListener f1852j = new a();

    /* renamed from: k  reason: collision with root package name */
    private final View.OnAttachStateChangeListener f1853k = new b();

    /* renamed from: l  reason: collision with root package name */
    private final q0 f1854l = new c();

    /* renamed from: m  reason: collision with root package name */
    private int f1855m = 0;

    /* renamed from: n  reason: collision with root package name */
    private int f1856n = 0;

    /* renamed from: v  reason: collision with root package name */
    private boolean f1864v = false;

    /* renamed from: q  reason: collision with root package name */
    private int f1859q = D();

    /* loaded from: classes.dex */
    class a implements ViewTreeObserver.OnGlobalLayoutListener {
        a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!d.this.c() || d.this.f1851i.size() <= 0 || ((C0008d) d.this.f1851i.get(0)).f1876a.x()) {
                return;
            }
            View view = d.this.f1858p;
            if (view == null || !view.isShown()) {
                d.this.dismiss();
                return;
            }
            for (C0008d c0008d : d.this.f1851i) {
                c0008d.f1876a.a();
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
            ViewTreeObserver viewTreeObserver = d.this.f1867y;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    d.this.f1867y = view.getViewTreeObserver();
                }
                d dVar = d.this;
                dVar.f1867y.removeGlobalOnLayoutListener(dVar.f1852j);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    /* loaded from: classes.dex */
    class c implements q0 {

        /* loaded from: classes.dex */
        class a implements Runnable {

            /* renamed from: e  reason: collision with root package name */
            final /* synthetic */ C0008d f1872e;

            /* renamed from: f  reason: collision with root package name */
            final /* synthetic */ MenuItem f1873f;

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ g f1874g;

            a(C0008d c0008d, MenuItem menuItem, g gVar) {
                this.f1872e = c0008d;
                this.f1873f = menuItem;
                this.f1874g = gVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                C0008d c0008d = this.f1872e;
                if (c0008d != null) {
                    d.this.A = true;
                    c0008d.f1877b.e(false);
                    d.this.A = false;
                }
                if (this.f1873f.isEnabled() && this.f1873f.hasSubMenu()) {
                    this.f1874g.L(this.f1873f, 4);
                }
            }
        }

        c() {
        }

        @Override // androidx.appcompat.widget.q0
        public void b(g gVar, MenuItem menuItem) {
            d.this.f1849g.removeCallbacksAndMessages(null);
            int size = d.this.f1851i.size();
            int i8 = 0;
            while (true) {
                if (i8 >= size) {
                    i8 = -1;
                    break;
                } else if (gVar == ((C0008d) d.this.f1851i.get(i8)).f1877b) {
                    break;
                } else {
                    i8++;
                }
            }
            if (i8 == -1) {
                return;
            }
            int i9 = i8 + 1;
            d.this.f1849g.postAtTime(new a(i9 < d.this.f1851i.size() ? (C0008d) d.this.f1851i.get(i9) : null, menuItem, gVar), gVar, SystemClock.uptimeMillis() + 200);
        }

        @Override // androidx.appcompat.widget.q0
        public void f(g gVar, MenuItem menuItem) {
            d.this.f1849g.removeCallbacksAndMessages(gVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: androidx.appcompat.view.menu.d$d  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0008d {

        /* renamed from: a  reason: collision with root package name */
        public final r0 f1876a;

        /* renamed from: b  reason: collision with root package name */
        public final g f1877b;

        /* renamed from: c  reason: collision with root package name */
        public final int f1878c;

        public C0008d(r0 r0Var, g gVar, int i8) {
            this.f1876a = r0Var;
            this.f1877b = gVar;
            this.f1878c = i8;
        }

        public ListView a() {
            return this.f1876a.h();
        }
    }

    public d(Context context, View view, int i8, int i9, boolean z7) {
        this.f1844b = context;
        this.f1857o = view;
        this.f1846d = i8;
        this.f1847e = i9;
        this.f1848f = z7;
        Resources resources = context.getResources();
        this.f1845c = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(g.d.f6828b));
        this.f1849g = new Handler();
    }

    private int A(g gVar) {
        int size = this.f1851i.size();
        for (int i8 = 0; i8 < size; i8++) {
            if (gVar == ((C0008d) this.f1851i.get(i8)).f1877b) {
                return i8;
            }
        }
        return -1;
    }

    private MenuItem B(g gVar, g gVar2) {
        int size = gVar.size();
        for (int i8 = 0; i8 < size; i8++) {
            MenuItem item = gVar.getItem(i8);
            if (item.hasSubMenu() && gVar2 == item.getSubMenu()) {
                return item;
            }
        }
        return null;
    }

    private View C(C0008d c0008d, g gVar) {
        f fVar;
        int i8;
        int firstVisiblePosition;
        MenuItem B2 = B(c0008d.f1877b, gVar);
        if (B2 == null) {
            return null;
        }
        ListView a8 = c0008d.a();
        ListAdapter adapter = a8.getAdapter();
        int i9 = 0;
        if (adapter instanceof HeaderViewListAdapter) {
            HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
            i8 = headerViewListAdapter.getHeadersCount();
            fVar = (f) headerViewListAdapter.getWrappedAdapter();
        } else {
            fVar = (f) adapter;
            i8 = 0;
        }
        int count = fVar.getCount();
        while (true) {
            if (i9 >= count) {
                i9 = -1;
                break;
            } else if (B2 == fVar.getItem(i9)) {
                break;
            } else {
                i9++;
            }
        }
        if (i9 != -1 && (firstVisiblePosition = (i9 + i8) - a8.getFirstVisiblePosition()) >= 0 && firstVisiblePosition < a8.getChildCount()) {
            return a8.getChildAt(firstVisiblePosition);
        }
        return null;
    }

    private int D() {
        return p0.p(this.f1857o) == 1 ? 0 : 1;
    }

    private int E(int i8) {
        List list = this.f1851i;
        ListView a8 = ((C0008d) list.get(list.size() - 1)).a();
        int[] iArr = new int[2];
        a8.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        this.f1858p.getWindowVisibleDisplayFrame(rect);
        return this.f1859q == 1 ? (iArr[0] + a8.getWidth()) + i8 > rect.right ? 0 : 1 : iArr[0] - i8 < 0 ? 1 : 0;
    }

    private void F(g gVar) {
        C0008d c0008d;
        View view;
        int i8;
        LayoutInflater from = LayoutInflater.from(this.f1844b);
        f fVar = new f(gVar, from, this.f1848f, B);
        if (!c() && this.f1864v) {
            fVar.d(true);
        } else if (c()) {
            fVar.d(k.x(gVar));
        }
        int o8 = k.o(fVar, null, this.f1844b, this.f1845c);
        r0 z7 = z();
        z7.p(fVar);
        z7.B(o8);
        z7.C(this.f1856n);
        if (this.f1851i.size() > 0) {
            List list = this.f1851i;
            c0008d = (C0008d) list.get(list.size() - 1);
            view = C(c0008d, gVar);
        } else {
            c0008d = null;
            view = null;
        }
        if (view != null) {
            z7.Q(false);
            z7.N(null);
            int E = E(o8);
            boolean z8 = E == 1;
            this.f1859q = E;
            z7.z(view);
            if ((this.f1856n & 5) == 5) {
                if (!z8) {
                    o8 = view.getWidth();
                    i8 = 0 - o8;
                }
                i8 = o8 + 0;
            } else {
                if (z8) {
                    o8 = view.getWidth();
                    i8 = o8 + 0;
                }
                i8 = 0 - o8;
            }
            z7.l(i8);
            z7.I(true);
            z7.j(0);
        } else {
            if (this.f1860r) {
                z7.l(this.f1862t);
            }
            if (this.f1861s) {
                z7.j(this.f1863u);
            }
            z7.D(n());
        }
        this.f1851i.add(new C0008d(z7, gVar, this.f1859q));
        z7.a();
        ListView h8 = z7.h();
        h8.setOnKeyListener(this);
        if (c0008d == null && this.f1865w && gVar.x() != null) {
            FrameLayout frameLayout = (FrameLayout) from.inflate(g.g.f6899l, (ViewGroup) h8, false);
            frameLayout.setEnabled(false);
            ((TextView) frameLayout.findViewById(16908310)).setText(gVar.x());
            h8.addHeaderView(frameLayout, null, false);
            z7.a();
        }
    }

    private r0 z() {
        r0 r0Var = new r0(this.f1844b, null, this.f1846d, this.f1847e);
        r0Var.P(this.f1854l);
        r0Var.H(this);
        r0Var.G(this);
        r0Var.z(this.f1857o);
        r0Var.C(this.f1856n);
        r0Var.F(true);
        r0Var.E(2);
        return r0Var;
    }

    @Override // androidx.appcompat.view.menu.p
    public void a() {
        if (c()) {
            return;
        }
        for (g gVar : this.f1850h) {
            F(gVar);
        }
        this.f1850h.clear();
        View view = this.f1857o;
        this.f1858p = view;
        if (view != null) {
            boolean z7 = this.f1867y == null;
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            this.f1867y = viewTreeObserver;
            if (z7) {
                viewTreeObserver.addOnGlobalLayoutListener(this.f1852j);
            }
            this.f1858p.addOnAttachStateChangeListener(this.f1853k);
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public void b(g gVar, boolean z7) {
        int A = A(gVar);
        if (A < 0) {
            return;
        }
        int i8 = A + 1;
        if (i8 < this.f1851i.size()) {
            ((C0008d) this.f1851i.get(i8)).f1877b.e(false);
        }
        C0008d c0008d = (C0008d) this.f1851i.remove(A);
        c0008d.f1877b.O(this);
        if (this.A) {
            c0008d.f1876a.O(null);
            c0008d.f1876a.A(0);
        }
        c0008d.f1876a.dismiss();
        int size = this.f1851i.size();
        this.f1859q = size > 0 ? ((C0008d) this.f1851i.get(size - 1)).f1878c : D();
        if (size != 0) {
            if (z7) {
                ((C0008d) this.f1851i.get(0)).f1877b.e(false);
                return;
            }
            return;
        }
        dismiss();
        m.a aVar = this.f1866x;
        if (aVar != null) {
            aVar.b(gVar, true);
        }
        ViewTreeObserver viewTreeObserver = this.f1867y;
        if (viewTreeObserver != null) {
            if (viewTreeObserver.isAlive()) {
                this.f1867y.removeGlobalOnLayoutListener(this.f1852j);
            }
            this.f1867y = null;
        }
        this.f1858p.removeOnAttachStateChangeListener(this.f1853k);
        this.f1868z.onDismiss();
    }

    @Override // androidx.appcompat.view.menu.p
    public boolean c() {
        return this.f1851i.size() > 0 && ((C0008d) this.f1851i.get(0)).f1876a.c();
    }

    @Override // androidx.appcompat.view.menu.p
    public void dismiss() {
        int size = this.f1851i.size();
        if (size > 0) {
            C0008d[] c0008dArr = (C0008d[]) this.f1851i.toArray(new C0008d[size]);
            for (int i8 = size - 1; i8 >= 0; i8--) {
                C0008d c0008d = c0008dArr[i8];
                if (c0008d.f1876a.c()) {
                    c0008d.f1876a.dismiss();
                }
            }
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean e(r rVar) {
        for (C0008d c0008d : this.f1851i) {
            if (rVar == c0008d.f1877b) {
                c0008d.a().requestFocus();
                return true;
            }
        }
        if (rVar.hasVisibleItems()) {
            l(rVar);
            m.a aVar = this.f1866x;
            if (aVar != null) {
                aVar.c(rVar);
            }
            return true;
        }
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public void f(boolean z7) {
        for (C0008d c0008d : this.f1851i) {
            k.y(c0008d.a().getAdapter()).notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean g() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.p
    public ListView h() {
        if (this.f1851i.isEmpty()) {
            return null;
        }
        List list = this.f1851i;
        return ((C0008d) list.get(list.size() - 1)).a();
    }

    @Override // androidx.appcompat.view.menu.m
    public void k(m.a aVar) {
        this.f1866x = aVar;
    }

    @Override // androidx.appcompat.view.menu.k
    public void l(g gVar) {
        gVar.c(this, this.f1844b);
        if (c()) {
            F(gVar);
        } else {
            this.f1850h.add(gVar);
        }
    }

    @Override // androidx.appcompat.view.menu.k
    protected boolean m() {
        return false;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        C0008d c0008d;
        int size = this.f1851i.size();
        int i8 = 0;
        while (true) {
            if (i8 >= size) {
                c0008d = null;
                break;
            }
            c0008d = (C0008d) this.f1851i.get(i8);
            if (!c0008d.f1876a.c()) {
                break;
            }
            i8++;
        }
        if (c0008d != null) {
            c0008d.f1877b.e(false);
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
        if (this.f1857o != view) {
            this.f1857o = view;
            this.f1856n = s.a(this.f1855m, p0.p(view));
        }
    }

    @Override // androidx.appcompat.view.menu.k
    public void r(boolean z7) {
        this.f1864v = z7;
    }

    @Override // androidx.appcompat.view.menu.k
    public void s(int i8) {
        if (this.f1855m != i8) {
            this.f1855m = i8;
            this.f1856n = s.a(i8, p0.p(this.f1857o));
        }
    }

    @Override // androidx.appcompat.view.menu.k
    public void t(int i8) {
        this.f1860r = true;
        this.f1862t = i8;
    }

    @Override // androidx.appcompat.view.menu.k
    public void u(PopupWindow.OnDismissListener onDismissListener) {
        this.f1868z = onDismissListener;
    }

    @Override // androidx.appcompat.view.menu.k
    public void v(boolean z7) {
        this.f1865w = z7;
    }

    @Override // androidx.appcompat.view.menu.k
    public void w(int i8) {
        this.f1861s = true;
        this.f1863u = i8;
    }
}
