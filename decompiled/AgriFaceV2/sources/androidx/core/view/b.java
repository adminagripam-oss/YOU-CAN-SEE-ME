package androidx.core.view;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
/* loaded from: classes.dex */
public abstract class b {

    /* renamed from: a  reason: collision with root package name */
    private final Context f3079a;

    /* renamed from: b  reason: collision with root package name */
    private a f3080b;

    /* renamed from: c  reason: collision with root package name */
    private InterfaceC0032b f3081c;

    /* loaded from: classes.dex */
    public interface a {
    }

    /* renamed from: androidx.core.view.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0032b {
        void onActionProviderVisibilityChanged(boolean z7);
    }

    public b(Context context) {
        this.f3079a = context;
    }

    public abstract boolean a();

    public abstract boolean b();

    public abstract View c(MenuItem menuItem);

    public abstract boolean d();

    public abstract void e(SubMenu subMenu);

    public abstract boolean f();

    public void g() {
        this.f3081c = null;
        this.f3080b = null;
    }

    public void h(a aVar) {
        this.f3080b = aVar;
    }

    public abstract void i(InterfaceC0032b interfaceC0032b);
}
