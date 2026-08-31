package androidx.fragment.app;

import android.os.Bundle;
import android.view.View;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes.dex */
public final class c0 {

    /* renamed from: a  reason: collision with root package name */
    private final i0 f3378a;

    /* renamed from: b  reason: collision with root package name */
    private final CopyOnWriteArrayList f3379b;

    public c0(i0 i0Var) {
        d7.k.e(i0Var, "fragmentManager");
        this.f3378a = i0Var;
        this.f3379b = new CopyOnWriteArrayList();
    }

    public final void a(p pVar, Bundle bundle, boolean z7) {
        d7.k.e(pVar, "f");
        p y02 = this.f3378a.y0();
        if (y02 != null) {
            i0 C = y02.C();
            d7.k.d(C, "parent.getParentFragmentManager()");
            C.x0().a(pVar, bundle, true);
        }
        Iterator it = this.f3379b.iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.w.a(it.next());
            throw null;
        }
    }

    public final void b(p pVar, boolean z7) {
        d7.k.e(pVar, "f");
        this.f3378a.v0().f();
        p y02 = this.f3378a.y0();
        if (y02 != null) {
            i0 C = y02.C();
            d7.k.d(C, "parent.getParentFragmentManager()");
            C.x0().b(pVar, true);
        }
        Iterator it = this.f3379b.iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.w.a(it.next());
            throw null;
        }
    }

    public final void c(p pVar, Bundle bundle, boolean z7) {
        d7.k.e(pVar, "f");
        p y02 = this.f3378a.y0();
        if (y02 != null) {
            i0 C = y02.C();
            d7.k.d(C, "parent.getParentFragmentManager()");
            C.x0().c(pVar, bundle, true);
        }
        Iterator it = this.f3379b.iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.w.a(it.next());
            throw null;
        }
    }

    public final void d(p pVar, boolean z7) {
        d7.k.e(pVar, "f");
        p y02 = this.f3378a.y0();
        if (y02 != null) {
            i0 C = y02.C();
            d7.k.d(C, "parent.getParentFragmentManager()");
            C.x0().d(pVar, true);
        }
        Iterator it = this.f3379b.iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.w.a(it.next());
            throw null;
        }
    }

    public final void e(p pVar, boolean z7) {
        d7.k.e(pVar, "f");
        p y02 = this.f3378a.y0();
        if (y02 != null) {
            i0 C = y02.C();
            d7.k.d(C, "parent.getParentFragmentManager()");
            C.x0().e(pVar, true);
        }
        Iterator it = this.f3379b.iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.w.a(it.next());
            throw null;
        }
    }

    public final void f(p pVar, boolean z7) {
        d7.k.e(pVar, "f");
        p y02 = this.f3378a.y0();
        if (y02 != null) {
            i0 C = y02.C();
            d7.k.d(C, "parent.getParentFragmentManager()");
            C.x0().f(pVar, true);
        }
        Iterator it = this.f3379b.iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.w.a(it.next());
            throw null;
        }
    }

    public final void g(p pVar, boolean z7) {
        d7.k.e(pVar, "f");
        this.f3378a.v0().f();
        p y02 = this.f3378a.y0();
        if (y02 != null) {
            i0 C = y02.C();
            d7.k.d(C, "parent.getParentFragmentManager()");
            C.x0().g(pVar, true);
        }
        Iterator it = this.f3379b.iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.w.a(it.next());
            throw null;
        }
    }

    public final void h(p pVar, Bundle bundle, boolean z7) {
        d7.k.e(pVar, "f");
        p y02 = this.f3378a.y0();
        if (y02 != null) {
            i0 C = y02.C();
            d7.k.d(C, "parent.getParentFragmentManager()");
            C.x0().h(pVar, bundle, true);
        }
        Iterator it = this.f3379b.iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.w.a(it.next());
            throw null;
        }
    }

    public final void i(p pVar, boolean z7) {
        d7.k.e(pVar, "f");
        p y02 = this.f3378a.y0();
        if (y02 != null) {
            i0 C = y02.C();
            d7.k.d(C, "parent.getParentFragmentManager()");
            C.x0().i(pVar, true);
        }
        Iterator it = this.f3379b.iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.w.a(it.next());
            throw null;
        }
    }

    public final void j(p pVar, Bundle bundle, boolean z7) {
        d7.k.e(pVar, "f");
        d7.k.e(bundle, "outState");
        p y02 = this.f3378a.y0();
        if (y02 != null) {
            i0 C = y02.C();
            d7.k.d(C, "parent.getParentFragmentManager()");
            C.x0().j(pVar, bundle, true);
        }
        Iterator it = this.f3379b.iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.w.a(it.next());
            throw null;
        }
    }

    public final void k(p pVar, boolean z7) {
        d7.k.e(pVar, "f");
        p y02 = this.f3378a.y0();
        if (y02 != null) {
            i0 C = y02.C();
            d7.k.d(C, "parent.getParentFragmentManager()");
            C.x0().k(pVar, true);
        }
        Iterator it = this.f3379b.iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.w.a(it.next());
            throw null;
        }
    }

    public final void l(p pVar, boolean z7) {
        d7.k.e(pVar, "f");
        p y02 = this.f3378a.y0();
        if (y02 != null) {
            i0 C = y02.C();
            d7.k.d(C, "parent.getParentFragmentManager()");
            C.x0().l(pVar, true);
        }
        Iterator it = this.f3379b.iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.w.a(it.next());
            throw null;
        }
    }

    public final void m(p pVar, View view, Bundle bundle, boolean z7) {
        d7.k.e(pVar, "f");
        d7.k.e(view, "v");
        p y02 = this.f3378a.y0();
        if (y02 != null) {
            i0 C = y02.C();
            d7.k.d(C, "parent.getParentFragmentManager()");
            C.x0().m(pVar, view, bundle, true);
        }
        Iterator it = this.f3379b.iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.w.a(it.next());
            throw null;
        }
    }

    public final void n(p pVar, boolean z7) {
        d7.k.e(pVar, "f");
        p y02 = this.f3378a.y0();
        if (y02 != null) {
            i0 C = y02.C();
            d7.k.d(C, "parent.getParentFragmentManager()");
            C.x0().n(pVar, true);
        }
        Iterator it = this.f3379b.iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.w.a(it.next());
            throw null;
        }
    }
}
