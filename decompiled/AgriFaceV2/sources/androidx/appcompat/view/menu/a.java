package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
/* loaded from: classes.dex */
public class a implements d1.b {

    /* renamed from: a  reason: collision with root package name */
    private final int f1811a;

    /* renamed from: b  reason: collision with root package name */
    private final int f1812b;

    /* renamed from: c  reason: collision with root package name */
    private final int f1813c;

    /* renamed from: d  reason: collision with root package name */
    private CharSequence f1814d;

    /* renamed from: e  reason: collision with root package name */
    private CharSequence f1815e;

    /* renamed from: f  reason: collision with root package name */
    private Intent f1816f;

    /* renamed from: g  reason: collision with root package name */
    private char f1817g;

    /* renamed from: i  reason: collision with root package name */
    private char f1819i;

    /* renamed from: k  reason: collision with root package name */
    private Drawable f1821k;

    /* renamed from: l  reason: collision with root package name */
    private Context f1822l;

    /* renamed from: m  reason: collision with root package name */
    private MenuItem.OnMenuItemClickListener f1823m;

    /* renamed from: n  reason: collision with root package name */
    private CharSequence f1824n;

    /* renamed from: o  reason: collision with root package name */
    private CharSequence f1825o;

    /* renamed from: h  reason: collision with root package name */
    private int f1818h = 4096;

    /* renamed from: j  reason: collision with root package name */
    private int f1820j = 4096;

    /* renamed from: p  reason: collision with root package name */
    private ColorStateList f1826p = null;

    /* renamed from: q  reason: collision with root package name */
    private PorterDuff.Mode f1827q = null;

    /* renamed from: r  reason: collision with root package name */
    private boolean f1828r = false;

    /* renamed from: s  reason: collision with root package name */
    private boolean f1829s = false;

    /* renamed from: t  reason: collision with root package name */
    private int f1830t = 16;

    public a(Context context, int i8, int i9, int i10, int i11, CharSequence charSequence) {
        this.f1822l = context;
        this.f1811a = i9;
        this.f1812b = i8;
        this.f1813c = i11;
        this.f1814d = charSequence;
    }

    private void c() {
        Drawable drawable = this.f1821k;
        if (drawable != null) {
            if (this.f1828r || this.f1829s) {
                Drawable i8 = androidx.core.graphics.drawable.a.i(drawable);
                this.f1821k = i8;
                Drawable mutate = i8.mutate();
                this.f1821k = mutate;
                if (this.f1828r) {
                    androidx.core.graphics.drawable.a.g(mutate, this.f1826p);
                }
                if (this.f1829s) {
                    androidx.core.graphics.drawable.a.h(this.f1821k, this.f1827q);
                }
            }
        }
    }

    @Override // d1.b
    public d1.b a(androidx.core.view.b bVar) {
        throw new UnsupportedOperationException();
    }

    @Override // d1.b
    public androidx.core.view.b b() {
        return null;
    }

    @Override // d1.b, android.view.MenuItem
    public boolean collapseActionView() {
        return false;
    }

    @Override // d1.b, android.view.MenuItem
    /* renamed from: d */
    public d1.b setActionView(int i8) {
        throw new UnsupportedOperationException();
    }

    @Override // d1.b, android.view.MenuItem
    /* renamed from: e */
    public d1.b setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    @Override // d1.b, android.view.MenuItem
    public boolean expandActionView() {
        return false;
    }

    @Override // d1.b, android.view.MenuItem
    /* renamed from: f */
    public d1.b setShowAsActionFlags(int i8) {
        setShowAsAction(i8);
        return this;
    }

    @Override // android.view.MenuItem
    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    @Override // d1.b, android.view.MenuItem
    public View getActionView() {
        return null;
    }

    @Override // d1.b, android.view.MenuItem
    public int getAlphabeticModifiers() {
        return this.f1820j;
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.f1819i;
    }

    @Override // d1.b, android.view.MenuItem
    public CharSequence getContentDescription() {
        return this.f1824n;
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return this.f1812b;
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        return this.f1821k;
    }

    @Override // d1.b, android.view.MenuItem
    public ColorStateList getIconTintList() {
        return this.f1826p;
    }

    @Override // d1.b, android.view.MenuItem
    public PorterDuff.Mode getIconTintMode() {
        return this.f1827q;
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return this.f1816f;
    }

    @Override // android.view.MenuItem
    public int getItemId() {
        return this.f1811a;
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override // d1.b, android.view.MenuItem
    public int getNumericModifiers() {
        return this.f1818h;
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return this.f1817g;
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return this.f1813c;
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return null;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitle() {
        return this.f1814d;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f1815e;
        return charSequence != null ? charSequence : this.f1814d;
    }

    @Override // d1.b, android.view.MenuItem
    public CharSequence getTooltipText() {
        return this.f1825o;
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return false;
    }

    @Override // d1.b, android.view.MenuItem
    public boolean isActionViewExpanded() {
        return false;
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return (this.f1830t & 1) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return (this.f1830t & 2) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return (this.f1830t & 16) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        return (this.f1830t & 8) == 0;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c8) {
        this.f1819i = Character.toLowerCase(c8);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z7) {
        this.f1830t = (z7 ? 1 : 0) | (this.f1830t & (-2));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z7) {
        this.f1830t = (z7 ? 2 : 0) | (this.f1830t & (-3));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z7) {
        this.f1830t = (z7 ? 16 : 0) | (this.f1830t & (-17));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i8) {
        this.f1821k = androidx.core.content.a.d(this.f1822l, i8);
        c();
        return this;
    }

    @Override // d1.b, android.view.MenuItem
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f1826p = colorStateList;
        this.f1828r = true;
        c();
        return this;
    }

    @Override // d1.b, android.view.MenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f1827q = mode;
        this.f1829s = true;
        c();
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        this.f1816f = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c8) {
        this.f1817g = c8;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f1823m = onMenuItemClickListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c8, char c9) {
        this.f1817g = c8;
        this.f1819i = Character.toLowerCase(c9);
        return this;
    }

    @Override // d1.b, android.view.MenuItem
    public void setShowAsAction(int i8) {
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i8) {
        this.f1814d = this.f1822l.getResources().getString(i8);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f1815e = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z7) {
        this.f1830t = (this.f1830t & 8) | (z7 ? 0 : 8);
        return this;
    }

    @Override // d1.b, android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c8, int i8) {
        this.f1819i = Character.toLowerCase(c8);
        this.f1820j = KeyEvent.normalizeMetaState(i8);
        return this;
    }

    @Override // android.view.MenuItem
    public d1.b setContentDescription(CharSequence charSequence) {
        this.f1824n = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.f1821k = drawable;
        c();
        return this;
    }

    @Override // d1.b, android.view.MenuItem
    public MenuItem setNumericShortcut(char c8, int i8) {
        this.f1817g = c8;
        this.f1818h = KeyEvent.normalizeMetaState(i8);
        return this;
    }

    @Override // d1.b, android.view.MenuItem
    public MenuItem setShortcut(char c8, char c9, int i8, int i9) {
        this.f1817g = c8;
        this.f1818h = KeyEvent.normalizeMetaState(i8);
        this.f1819i = Character.toLowerCase(c9);
        this.f1820j = KeyEvent.normalizeMetaState(i9);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.f1814d = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public d1.b setTooltipText(CharSequence charSequence) {
        this.f1825o = charSequence;
        return this;
    }
}
