package androidx.appcompat.view;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
/* loaded from: classes.dex */
public abstract class b {

    /* renamed from: a  reason: collision with root package name */
    private Object f1713a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f1714b;

    /* loaded from: classes.dex */
    public interface a {
        boolean a(b bVar, Menu menu);

        void b(b bVar);

        boolean c(b bVar, MenuItem menuItem);

        boolean d(b bVar, Menu menu);
    }

    public abstract void c();

    public abstract View d();

    public abstract Menu e();

    public abstract MenuInflater f();

    public abstract CharSequence g();

    public Object h() {
        return this.f1713a;
    }

    public abstract CharSequence i();

    public boolean j() {
        return this.f1714b;
    }

    public abstract void k();

    public abstract boolean l();

    public abstract void m(View view);

    public abstract void n(int i8);

    public abstract void o(CharSequence charSequence);

    public void p(Object obj) {
        this.f1713a = obj;
    }

    public abstract void q(int i8);

    public abstract void r(CharSequence charSequence);

    public void s(boolean z7) {
        this.f1714b = z7;
    }
}
