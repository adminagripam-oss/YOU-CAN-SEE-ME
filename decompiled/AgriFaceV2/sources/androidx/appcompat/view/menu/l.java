package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import androidx.appcompat.view.menu.m;
import androidx.core.view.p0;
import androidx.core.view.s;
/* loaded from: classes.dex */
public class l {

    /* renamed from: a  reason: collision with root package name */
    private final Context f1965a;

    /* renamed from: b  reason: collision with root package name */
    private final g f1966b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f1967c;

    /* renamed from: d  reason: collision with root package name */
    private final int f1968d;

    /* renamed from: e  reason: collision with root package name */
    private final int f1969e;

    /* renamed from: f  reason: collision with root package name */
    private View f1970f;

    /* renamed from: g  reason: collision with root package name */
    private int f1971g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f1972h;

    /* renamed from: i  reason: collision with root package name */
    private m.a f1973i;

    /* renamed from: j  reason: collision with root package name */
    private k f1974j;

    /* renamed from: k  reason: collision with root package name */
    private PopupWindow.OnDismissListener f1975k;

    /* renamed from: l  reason: collision with root package name */
    private final PopupWindow.OnDismissListener f1976l;

    /* loaded from: classes.dex */
    class a implements PopupWindow.OnDismissListener {
        a() {
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            l.this.e();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b {
        static void a(Display display, Point point) {
            display.getRealSize(point);
        }
    }

    public l(Context context, g gVar, View view, boolean z7, int i8) {
        this(context, gVar, view, z7, i8, 0);
    }

    private k a() {
        Display defaultDisplay = ((WindowManager) this.f1965a.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        b.a(defaultDisplay, point);
        k dVar = Math.min(point.x, point.y) >= this.f1965a.getResources().getDimensionPixelSize(g.d.f6827a) ? new d(this.f1965a, this.f1970f, this.f1968d, this.f1969e, this.f1967c) : new q(this.f1965a, this.f1966b, this.f1970f, this.f1968d, this.f1969e, this.f1967c);
        dVar.l(this.f1966b);
        dVar.u(this.f1976l);
        dVar.p(this.f1970f);
        dVar.k(this.f1973i);
        dVar.r(this.f1972h);
        dVar.s(this.f1971g);
        return dVar;
    }

    private void l(int i8, int i9, boolean z7, boolean z8) {
        k c8 = c();
        c8.v(z8);
        if (z7) {
            if ((s.a(this.f1971g, p0.p(this.f1970f)) & 7) == 5) {
                i8 -= this.f1970f.getWidth();
            }
            c8.t(i8);
            c8.w(i9);
            int i10 = (int) ((this.f1965a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            c8.q(new Rect(i8 - i10, i9 - i10, i8 + i10, i9 + i10));
        }
        c8.a();
    }

    public void b() {
        if (d()) {
            this.f1974j.dismiss();
        }
    }

    public k c() {
        if (this.f1974j == null) {
            this.f1974j = a();
        }
        return this.f1974j;
    }

    public boolean d() {
        k kVar = this.f1974j;
        return kVar != null && kVar.c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void e() {
        this.f1974j = null;
        PopupWindow.OnDismissListener onDismissListener = this.f1975k;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public void f(View view) {
        this.f1970f = view;
    }

    public void g(boolean z7) {
        this.f1972h = z7;
        k kVar = this.f1974j;
        if (kVar != null) {
            kVar.r(z7);
        }
    }

    public void h(int i8) {
        this.f1971g = i8;
    }

    public void i(PopupWindow.OnDismissListener onDismissListener) {
        this.f1975k = onDismissListener;
    }

    public void j(m.a aVar) {
        this.f1973i = aVar;
        k kVar = this.f1974j;
        if (kVar != null) {
            kVar.k(aVar);
        }
    }

    public void k() {
        if (!m()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public boolean m() {
        if (d()) {
            return true;
        }
        if (this.f1970f == null) {
            return false;
        }
        l(0, 0, false, false);
        return true;
    }

    public boolean n(int i8, int i9) {
        if (d()) {
            return true;
        }
        if (this.f1970f == null) {
            return false;
        }
        l(i8, i9, true, true);
        return true;
    }

    public l(Context context, g gVar, View view, boolean z7, int i8, int i9) {
        this.f1971g = 8388611;
        this.f1976l = new a();
        this.f1965a = context;
        this.f1966b = gVar;
        this.f1970f = view;
        this.f1967c = z7;
        this.f1968d = i8;
        this.f1969e = i9;
    }
}
