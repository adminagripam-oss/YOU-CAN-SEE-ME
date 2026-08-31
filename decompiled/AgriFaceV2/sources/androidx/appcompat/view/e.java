package androidx.appcompat.view;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.view.b;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.widget.ActionBarContextView;
import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
public class e extends b implements g.a {

    /* renamed from: c  reason: collision with root package name */
    private Context f1721c;

    /* renamed from: d  reason: collision with root package name */
    private ActionBarContextView f1722d;

    /* renamed from: e  reason: collision with root package name */
    private b.a f1723e;

    /* renamed from: f  reason: collision with root package name */
    private WeakReference f1724f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f1725g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f1726h;

    /* renamed from: i  reason: collision with root package name */
    private androidx.appcompat.view.menu.g f1727i;

    public e(Context context, ActionBarContextView actionBarContextView, b.a aVar, boolean z7) {
        this.f1721c = context;
        this.f1722d = actionBarContextView;
        this.f1723e = aVar;
        androidx.appcompat.view.menu.g S = new androidx.appcompat.view.menu.g(actionBarContextView.getContext()).S(1);
        this.f1727i = S;
        S.R(this);
        this.f1726h = z7;
    }

    @Override // androidx.appcompat.view.menu.g.a
    public boolean a(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
        return this.f1723e.c(this, menuItem);
    }

    @Override // androidx.appcompat.view.menu.g.a
    public void b(androidx.appcompat.view.menu.g gVar) {
        k();
        this.f1722d.l();
    }

    @Override // androidx.appcompat.view.b
    public void c() {
        if (this.f1725g) {
            return;
        }
        this.f1725g = true;
        this.f1723e.b(this);
    }

    @Override // androidx.appcompat.view.b
    public View d() {
        WeakReference weakReference = this.f1724f;
        if (weakReference != null) {
            return (View) weakReference.get();
        }
        return null;
    }

    @Override // androidx.appcompat.view.b
    public Menu e() {
        return this.f1727i;
    }

    @Override // androidx.appcompat.view.b
    public MenuInflater f() {
        return new g(this.f1722d.getContext());
    }

    @Override // androidx.appcompat.view.b
    public CharSequence g() {
        return this.f1722d.getSubtitle();
    }

    @Override // androidx.appcompat.view.b
    public CharSequence i() {
        return this.f1722d.getTitle();
    }

    @Override // androidx.appcompat.view.b
    public void k() {
        this.f1723e.a(this, this.f1727i);
    }

    @Override // androidx.appcompat.view.b
    public boolean l() {
        return this.f1722d.j();
    }

    @Override // androidx.appcompat.view.b
    public void m(View view) {
        this.f1722d.setCustomView(view);
        this.f1724f = view != null ? new WeakReference(view) : null;
    }

    @Override // androidx.appcompat.view.b
    public void n(int i8) {
        o(this.f1721c.getString(i8));
    }

    @Override // androidx.appcompat.view.b
    public void o(CharSequence charSequence) {
        this.f1722d.setSubtitle(charSequence);
    }

    @Override // androidx.appcompat.view.b
    public void q(int i8) {
        r(this.f1721c.getString(i8));
    }

    @Override // androidx.appcompat.view.b
    public void r(CharSequence charSequence) {
        this.f1722d.setTitle(charSequence);
    }

    @Override // androidx.appcompat.view.b
    public void s(boolean z7) {
        super.s(z7);
        this.f1722d.setTitleOptional(z7);
    }
}
