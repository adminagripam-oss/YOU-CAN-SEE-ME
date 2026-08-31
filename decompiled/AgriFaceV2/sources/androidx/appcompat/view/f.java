package androidx.appcompat.view;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.view.b;
import androidx.appcompat.view.menu.j;
import androidx.appcompat.view.menu.o;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class f extends ActionMode {

    /* renamed from: a  reason: collision with root package name */
    final Context f1728a;

    /* renamed from: b  reason: collision with root package name */
    final b f1729b;

    /* loaded from: classes.dex */
    public static class a implements b.a {

        /* renamed from: a  reason: collision with root package name */
        final ActionMode.Callback f1730a;

        /* renamed from: b  reason: collision with root package name */
        final Context f1731b;

        /* renamed from: c  reason: collision with root package name */
        final ArrayList f1732c = new ArrayList();

        /* renamed from: d  reason: collision with root package name */
        final b1.g f1733d = new b1.g();

        public a(Context context, ActionMode.Callback callback) {
            this.f1731b = context;
            this.f1730a = callback;
        }

        private Menu f(Menu menu) {
            Menu menu2 = (Menu) this.f1733d.get(menu);
            if (menu2 == null) {
                o oVar = new o(this.f1731b, (d1.a) menu);
                this.f1733d.put(menu, oVar);
                return oVar;
            }
            return menu2;
        }

        @Override // androidx.appcompat.view.b.a
        public boolean a(b bVar, Menu menu) {
            return this.f1730a.onPrepareActionMode(e(bVar), f(menu));
        }

        @Override // androidx.appcompat.view.b.a
        public void b(b bVar) {
            this.f1730a.onDestroyActionMode(e(bVar));
        }

        @Override // androidx.appcompat.view.b.a
        public boolean c(b bVar, MenuItem menuItem) {
            return this.f1730a.onActionItemClicked(e(bVar), new j(this.f1731b, (d1.b) menuItem));
        }

        @Override // androidx.appcompat.view.b.a
        public boolean d(b bVar, Menu menu) {
            return this.f1730a.onCreateActionMode(e(bVar), f(menu));
        }

        public ActionMode e(b bVar) {
            int size = this.f1732c.size();
            for (int i8 = 0; i8 < size; i8++) {
                f fVar = (f) this.f1732c.get(i8);
                if (fVar != null && fVar.f1729b == bVar) {
                    return fVar;
                }
            }
            f fVar2 = new f(this.f1731b, bVar);
            this.f1732c.add(fVar2);
            return fVar2;
        }
    }

    public f(Context context, b bVar) {
        this.f1728a = context;
        this.f1729b = bVar;
    }

    @Override // android.view.ActionMode
    public void finish() {
        this.f1729b.c();
    }

    @Override // android.view.ActionMode
    public View getCustomView() {
        return this.f1729b.d();
    }

    @Override // android.view.ActionMode
    public Menu getMenu() {
        return new o(this.f1728a, (d1.a) this.f1729b.e());
    }

    @Override // android.view.ActionMode
    public MenuInflater getMenuInflater() {
        return this.f1729b.f();
    }

    @Override // android.view.ActionMode
    public CharSequence getSubtitle() {
        return this.f1729b.g();
    }

    @Override // android.view.ActionMode
    public Object getTag() {
        return this.f1729b.h();
    }

    @Override // android.view.ActionMode
    public CharSequence getTitle() {
        return this.f1729b.i();
    }

    @Override // android.view.ActionMode
    public boolean getTitleOptionalHint() {
        return this.f1729b.j();
    }

    @Override // android.view.ActionMode
    public void invalidate() {
        this.f1729b.k();
    }

    @Override // android.view.ActionMode
    public boolean isTitleOptional() {
        return this.f1729b.l();
    }

    @Override // android.view.ActionMode
    public void setCustomView(View view) {
        this.f1729b.m(view);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(int i8) {
        this.f1729b.n(i8);
    }

    @Override // android.view.ActionMode
    public void setTag(Object obj) {
        this.f1729b.p(obj);
    }

    @Override // android.view.ActionMode
    public void setTitle(int i8) {
        this.f1729b.q(i8);
    }

    @Override // android.view.ActionMode
    public void setTitleOptionalHint(boolean z7) {
        this.f1729b.s(z7);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(CharSequence charSequence) {
        this.f1729b.o(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTitle(CharSequence charSequence) {
        this.f1729b.r(charSequence);
    }
}
