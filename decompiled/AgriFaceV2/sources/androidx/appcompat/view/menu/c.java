package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class c {

    /* renamed from: a  reason: collision with root package name */
    final Context f1841a;

    /* renamed from: b  reason: collision with root package name */
    private b1.g f1842b;

    /* renamed from: c  reason: collision with root package name */
    private b1.g f1843c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(Context context) {
        this.f1841a = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final MenuItem c(MenuItem menuItem) {
        if (menuItem instanceof d1.b) {
            d1.b bVar = (d1.b) menuItem;
            if (this.f1842b == null) {
                this.f1842b = new b1.g();
            }
            MenuItem menuItem2 = (MenuItem) this.f1842b.get(bVar);
            if (menuItem2 == null) {
                j jVar = new j(this.f1841a, bVar);
                this.f1842b.put(bVar, jVar);
                return jVar;
            }
            return menuItem2;
        }
        return menuItem;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final SubMenu d(SubMenu subMenu) {
        return subMenu;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void e() {
        b1.g gVar = this.f1842b;
        if (gVar != null) {
            gVar.clear();
        }
        b1.g gVar2 = this.f1843c;
        if (gVar2 != null) {
            gVar2.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void f(int i8) {
        if (this.f1842b == null) {
            return;
        }
        int i9 = 0;
        while (i9 < this.f1842b.size()) {
            if (((d1.b) this.f1842b.i(i9)).getGroupId() == i8) {
                this.f1842b.j(i9);
                i9--;
            }
            i9++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void g(int i8) {
        if (this.f1842b == null) {
            return;
        }
        for (int i9 = 0; i9 < this.f1842b.size(); i9++) {
            if (((d1.b) this.f1842b.i(i9)).getItemId() == i8) {
                this.f1842b.j(i9);
                return;
            }
        }
    }
}
