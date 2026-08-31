package androidx.appcompat.app;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.appcompat.app.h;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.view.menu.m;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.f1;
import androidx.appcompat.widget.j0;
import androidx.core.view.p0;
import java.util.ArrayList;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class x extends androidx.appcompat.app.a {

    /* renamed from: a  reason: collision with root package name */
    final j0 f1687a;

    /* renamed from: b  reason: collision with root package name */
    final Window.Callback f1688b;

    /* renamed from: c  reason: collision with root package name */
    final h.g f1689c;

    /* renamed from: d  reason: collision with root package name */
    boolean f1690d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f1691e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f1692f;

    /* renamed from: g  reason: collision with root package name */
    private ArrayList f1693g = new ArrayList();

    /* renamed from: h  reason: collision with root package name */
    private final Runnable f1694h = new a();

    /* renamed from: i  reason: collision with root package name */
    private final Toolbar.h f1695i;

    /* loaded from: classes.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            x.this.C();
        }
    }

    /* loaded from: classes.dex */
    class b implements Toolbar.h {
        b() {
        }

        @Override // androidx.appcompat.widget.Toolbar.h
        public boolean onMenuItemClick(MenuItem menuItem) {
            return x.this.f1688b.onMenuItemSelected(0, menuItem);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class c implements m.a {

        /* renamed from: a  reason: collision with root package name */
        private boolean f1698a;

        c() {
        }

        @Override // androidx.appcompat.view.menu.m.a
        public void b(androidx.appcompat.view.menu.g gVar, boolean z7) {
            if (this.f1698a) {
                return;
            }
            this.f1698a = true;
            x.this.f1687a.i();
            x.this.f1688b.onPanelClosed(108, gVar);
            this.f1698a = false;
        }

        @Override // androidx.appcompat.view.menu.m.a
        public boolean c(androidx.appcompat.view.menu.g gVar) {
            x.this.f1688b.onMenuOpened(108, gVar);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class d implements g.a {
        d() {
        }

        @Override // androidx.appcompat.view.menu.g.a
        public boolean a(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
            return false;
        }

        @Override // androidx.appcompat.view.menu.g.a
        public void b(androidx.appcompat.view.menu.g gVar) {
            if (x.this.f1687a.b()) {
                x.this.f1688b.onPanelClosed(108, gVar);
            } else if (x.this.f1688b.onPreparePanel(0, null, gVar)) {
                x.this.f1688b.onMenuOpened(108, gVar);
            }
        }
    }

    /* loaded from: classes.dex */
    private class e implements h.g {
        e() {
        }

        @Override // androidx.appcompat.app.h.g
        public boolean a(int i8) {
            if (i8 == 0) {
                x xVar = x.this;
                if (xVar.f1690d) {
                    return false;
                }
                xVar.f1687a.c();
                x.this.f1690d = true;
                return false;
            }
            return false;
        }

        @Override // androidx.appcompat.app.h.g
        public View onCreatePanelView(int i8) {
            if (i8 == 0) {
                return new View(x.this.f1687a.getContext());
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public x(Toolbar toolbar, CharSequence charSequence, Window.Callback callback) {
        b bVar = new b();
        this.f1695i = bVar;
        g1.e.j(toolbar);
        f1 f1Var = new f1(toolbar, false);
        this.f1687a = f1Var;
        this.f1688b = (Window.Callback) g1.e.j(callback);
        f1Var.setWindowCallback(callback);
        toolbar.setOnMenuItemClickListener(bVar);
        f1Var.setWindowTitle(charSequence);
        this.f1689c = new e();
    }

    private Menu B() {
        if (!this.f1691e) {
            this.f1687a.j(new c(), new d());
            this.f1691e = true;
        }
        return this.f1687a.s();
    }

    void C() {
        Menu B = B();
        androidx.appcompat.view.menu.g gVar = B instanceof androidx.appcompat.view.menu.g ? (androidx.appcompat.view.menu.g) B : null;
        if (gVar != null) {
            gVar.e0();
        }
        try {
            B.clear();
            if (!this.f1688b.onCreatePanelMenu(0, B) || !this.f1688b.onPreparePanel(0, null, B)) {
                B.clear();
            }
        } finally {
            if (gVar != null) {
                gVar.d0();
            }
        }
    }

    public void D(int i8, int i9) {
        this.f1687a.q((i8 & i9) | ((~i9) & this.f1687a.r()));
    }

    @Override // androidx.appcompat.app.a
    public boolean g() {
        return this.f1687a.f();
    }

    @Override // androidx.appcompat.app.a
    public boolean h() {
        if (this.f1687a.p()) {
            this.f1687a.collapseActionView();
            return true;
        }
        return false;
    }

    @Override // androidx.appcompat.app.a
    public void i(boolean z7) {
        if (z7 == this.f1692f) {
            return;
        }
        this.f1692f = z7;
        if (this.f1693g.size() <= 0) {
            return;
        }
        w.a(this.f1693g.get(0));
        throw null;
    }

    @Override // androidx.appcompat.app.a
    public int j() {
        return this.f1687a.r();
    }

    @Override // androidx.appcompat.app.a
    public Context k() {
        return this.f1687a.getContext();
    }

    @Override // androidx.appcompat.app.a
    public void l() {
        this.f1687a.k(8);
    }

    @Override // androidx.appcompat.app.a
    public boolean m() {
        this.f1687a.m().removeCallbacks(this.f1694h);
        p0.I(this.f1687a.m(), this.f1694h);
        return true;
    }

    @Override // androidx.appcompat.app.a
    public boolean n() {
        return this.f1687a.o() == 0;
    }

    @Override // androidx.appcompat.app.a
    public void o(Configuration configuration) {
        super.o(configuration);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.appcompat.app.a
    public void p() {
        this.f1687a.m().removeCallbacks(this.f1694h);
    }

    @Override // androidx.appcompat.app.a
    public boolean q(int i8, KeyEvent keyEvent) {
        Menu B = B();
        if (B != null) {
            B.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
            return B.performShortcut(i8, keyEvent, 0);
        }
        return false;
    }

    @Override // androidx.appcompat.app.a
    public boolean r(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            s();
        }
        return true;
    }

    @Override // androidx.appcompat.app.a
    public boolean s() {
        return this.f1687a.g();
    }

    @Override // androidx.appcompat.app.a
    public void t(Drawable drawable) {
        this.f1687a.d(drawable);
    }

    @Override // androidx.appcompat.app.a
    public void u(boolean z7) {
    }

    @Override // androidx.appcompat.app.a
    public void v(boolean z7) {
        D(z7 ? 8 : 0, 8);
    }

    @Override // androidx.appcompat.app.a
    public void w(boolean z7) {
    }

    @Override // androidx.appcompat.app.a
    public void x(CharSequence charSequence) {
        this.f1687a.setTitle(charSequence);
    }

    @Override // androidx.appcompat.app.a
    public void y(CharSequence charSequence) {
        this.f1687a.setWindowTitle(charSequence);
    }

    @Override // androidx.appcompat.app.a
    public void z() {
        this.f1687a.k(0);
    }
}
