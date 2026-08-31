package androidx.appcompat.view.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.view.menu.n;
import androidx.core.view.b;
/* loaded from: classes.dex */
public final class i implements d1.b {
    private View A;
    private androidx.core.view.b B;
    private MenuItem.OnActionExpandListener C;
    private ContextMenu.ContextMenuInfo E;

    /* renamed from: a  reason: collision with root package name */
    private final int f1926a;

    /* renamed from: b  reason: collision with root package name */
    private final int f1927b;

    /* renamed from: c  reason: collision with root package name */
    private final int f1928c;

    /* renamed from: d  reason: collision with root package name */
    private final int f1929d;

    /* renamed from: e  reason: collision with root package name */
    private CharSequence f1930e;

    /* renamed from: f  reason: collision with root package name */
    private CharSequence f1931f;

    /* renamed from: g  reason: collision with root package name */
    private Intent f1932g;

    /* renamed from: h  reason: collision with root package name */
    private char f1933h;

    /* renamed from: j  reason: collision with root package name */
    private char f1935j;

    /* renamed from: l  reason: collision with root package name */
    private Drawable f1937l;

    /* renamed from: n  reason: collision with root package name */
    g f1939n;

    /* renamed from: o  reason: collision with root package name */
    private r f1940o;

    /* renamed from: p  reason: collision with root package name */
    private Runnable f1941p;

    /* renamed from: q  reason: collision with root package name */
    private MenuItem.OnMenuItemClickListener f1942q;

    /* renamed from: r  reason: collision with root package name */
    private CharSequence f1943r;

    /* renamed from: s  reason: collision with root package name */
    private CharSequence f1944s;

    /* renamed from: z  reason: collision with root package name */
    private int f1951z;

    /* renamed from: i  reason: collision with root package name */
    private int f1934i = 4096;

    /* renamed from: k  reason: collision with root package name */
    private int f1936k = 4096;

    /* renamed from: m  reason: collision with root package name */
    private int f1938m = 0;

    /* renamed from: t  reason: collision with root package name */
    private ColorStateList f1945t = null;

    /* renamed from: u  reason: collision with root package name */
    private PorterDuff.Mode f1946u = null;

    /* renamed from: v  reason: collision with root package name */
    private boolean f1947v = false;

    /* renamed from: w  reason: collision with root package name */
    private boolean f1948w = false;

    /* renamed from: x  reason: collision with root package name */
    private boolean f1949x = false;

    /* renamed from: y  reason: collision with root package name */
    private int f1950y = 16;
    private boolean D = false;

    /* loaded from: classes.dex */
    class a implements b.InterfaceC0032b {
        a() {
        }

        @Override // androidx.core.view.b.InterfaceC0032b
        public void onActionProviderVisibilityChanged(boolean z7) {
            i iVar = i.this;
            iVar.f1939n.J(iVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(g gVar, int i8, int i9, int i10, int i11, CharSequence charSequence, int i12) {
        this.f1939n = gVar;
        this.f1926a = i9;
        this.f1927b = i8;
        this.f1928c = i10;
        this.f1929d = i11;
        this.f1930e = charSequence;
        this.f1951z = i12;
    }

    private static void d(StringBuilder sb, int i8, int i9, String str) {
        if ((i8 & i9) == i9) {
            sb.append(str);
        }
    }

    private Drawable e(Drawable drawable) {
        if (drawable != null && this.f1949x && (this.f1947v || this.f1948w)) {
            drawable = androidx.core.graphics.drawable.a.i(drawable).mutate();
            if (this.f1947v) {
                androidx.core.graphics.drawable.a.g(drawable, this.f1945t);
            }
            if (this.f1948w) {
                androidx.core.graphics.drawable.a.h(drawable, this.f1946u);
            }
            this.f1949x = false;
        }
        return drawable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean A() {
        return this.f1939n.H() && g() != 0;
    }

    public boolean B() {
        return (this.f1951z & 4) == 4;
    }

    @Override // d1.b
    public d1.b a(androidx.core.view.b bVar) {
        androidx.core.view.b bVar2 = this.B;
        if (bVar2 != null) {
            bVar2.g();
        }
        this.A = null;
        this.B = bVar;
        this.f1939n.K(true);
        androidx.core.view.b bVar3 = this.B;
        if (bVar3 != null) {
            bVar3.i(new a());
        }
        return this;
    }

    @Override // d1.b
    public androidx.core.view.b b() {
        return this.B;
    }

    public void c() {
        this.f1939n.I(this);
    }

    @Override // d1.b, android.view.MenuItem
    public boolean collapseActionView() {
        if ((this.f1951z & 8) == 0) {
            return false;
        }
        if (this.A == null) {
            return true;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.C;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionCollapse(this)) {
            return this.f1939n.f(this);
        }
        return false;
    }

    @Override // d1.b, android.view.MenuItem
    public boolean expandActionView() {
        if (j()) {
            MenuItem.OnActionExpandListener onActionExpandListener = this.C;
            if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionExpand(this)) {
                return this.f1939n.k(this);
            }
            return false;
        }
        return false;
    }

    public int f() {
        return this.f1929d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public char g() {
        return this.f1939n.G() ? this.f1935j : this.f1933h;
    }

    @Override // android.view.MenuItem
    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    @Override // d1.b, android.view.MenuItem
    public View getActionView() {
        View view = this.A;
        if (view != null) {
            return view;
        }
        androidx.core.view.b bVar = this.B;
        if (bVar != null) {
            View c8 = bVar.c(this);
            this.A = c8;
            return c8;
        }
        return null;
    }

    @Override // d1.b, android.view.MenuItem
    public int getAlphabeticModifiers() {
        return this.f1936k;
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.f1935j;
    }

    @Override // d1.b, android.view.MenuItem
    public CharSequence getContentDescription() {
        return this.f1943r;
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return this.f1927b;
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        Drawable drawable = this.f1937l;
        if (drawable != null) {
            return e(drawable);
        }
        if (this.f1938m != 0) {
            Drawable b8 = h.a.b(this.f1939n.u(), this.f1938m);
            this.f1938m = 0;
            this.f1937l = b8;
            return e(b8);
        }
        return null;
    }

    @Override // d1.b, android.view.MenuItem
    public ColorStateList getIconTintList() {
        return this.f1945t;
    }

    @Override // d1.b, android.view.MenuItem
    public PorterDuff.Mode getIconTintMode() {
        return this.f1946u;
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return this.f1932g;
    }

    @Override // android.view.MenuItem
    public int getItemId() {
        return this.f1926a;
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.E;
    }

    @Override // d1.b, android.view.MenuItem
    public int getNumericModifiers() {
        return this.f1934i;
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return this.f1933h;
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return this.f1928c;
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return this.f1940o;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitle() {
        return this.f1930e;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f1931f;
        return charSequence != null ? charSequence : this.f1930e;
    }

    @Override // d1.b, android.view.MenuItem
    public CharSequence getTooltipText() {
        return this.f1944s;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String h() {
        int i8;
        char g8 = g();
        if (g8 == 0) {
            return "";
        }
        Resources resources = this.f1939n.u().getResources();
        StringBuilder sb = new StringBuilder();
        if (ViewConfiguration.get(this.f1939n.u()).hasPermanentMenuKey()) {
            sb.append(resources.getString(g.h.f6917k));
        }
        int i9 = this.f1939n.G() ? this.f1936k : this.f1934i;
        d(sb, i9, 65536, resources.getString(g.h.f6913g));
        d(sb, i9, 4096, resources.getString(g.h.f6909c));
        d(sb, i9, 2, resources.getString(g.h.f6908b));
        d(sb, i9, 1, resources.getString(g.h.f6914h));
        d(sb, i9, 4, resources.getString(g.h.f6916j));
        d(sb, i9, 8, resources.getString(g.h.f6912f));
        if (g8 == '\b') {
            i8 = g.h.f6910d;
        } else if (g8 == '\n') {
            i8 = g.h.f6911e;
        } else if (g8 != ' ') {
            sb.append(g8);
            return sb.toString();
        } else {
            i8 = g.h.f6915i;
        }
        sb.append(resources.getString(i8));
        return sb.toString();
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return this.f1940o != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CharSequence i(n.a aVar) {
        return (aVar == null || !aVar.a()) ? getTitle() : getTitleCondensed();
    }

    @Override // d1.b, android.view.MenuItem
    public boolean isActionViewExpanded() {
        return this.D;
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return (this.f1950y & 1) == 1;
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return (this.f1950y & 2) == 2;
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return (this.f1950y & 16) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        androidx.core.view.b bVar = this.B;
        return (bVar == null || !bVar.f()) ? (this.f1950y & 8) == 0 : (this.f1950y & 8) == 0 && this.B.b();
    }

    public boolean j() {
        androidx.core.view.b bVar;
        if ((this.f1951z & 8) != 0) {
            if (this.A == null && (bVar = this.B) != null) {
                this.A = bVar.c(this);
            }
            return this.A != null;
        }
        return false;
    }

    public boolean k() {
        MenuItem.OnMenuItemClickListener onMenuItemClickListener = this.f1942q;
        if (onMenuItemClickListener == null || !onMenuItemClickListener.onMenuItemClick(this)) {
            g gVar = this.f1939n;
            if (gVar.h(gVar, this)) {
                return true;
            }
            Runnable runnable = this.f1941p;
            if (runnable != null) {
                runnable.run();
                return true;
            }
            if (this.f1932g != null) {
                try {
                    this.f1939n.u().startActivity(this.f1932g);
                    return true;
                } catch (ActivityNotFoundException e8) {
                    Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", e8);
                }
            }
            androidx.core.view.b bVar = this.B;
            return bVar != null && bVar.d();
        }
        return true;
    }

    public boolean l() {
        return (this.f1950y & 32) == 32;
    }

    public boolean m() {
        return (this.f1950y & 4) != 0;
    }

    public boolean n() {
        return (this.f1951z & 1) == 1;
    }

    public boolean o() {
        return (this.f1951z & 2) == 2;
    }

    @Override // d1.b, android.view.MenuItem
    /* renamed from: p */
    public d1.b setActionView(int i8) {
        Context u7 = this.f1939n.u();
        setActionView(LayoutInflater.from(u7).inflate(i8, (ViewGroup) new LinearLayout(u7), false));
        return this;
    }

    @Override // d1.b, android.view.MenuItem
    /* renamed from: q */
    public d1.b setActionView(View view) {
        int i8;
        this.A = view;
        this.B = null;
        if (view != null && view.getId() == -1 && (i8 = this.f1926a) > 0) {
            view.setId(i8);
        }
        this.f1939n.I(this);
        return this;
    }

    public void r(boolean z7) {
        this.D = z7;
        this.f1939n.K(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void s(boolean z7) {
        int i8 = this.f1950y;
        int i9 = (z7 ? 2 : 0) | (i8 & (-3));
        this.f1950y = i9;
        if (i8 != i9) {
            this.f1939n.K(false);
        }
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c8) {
        if (this.f1935j == c8) {
            return this;
        }
        this.f1935j = Character.toLowerCase(c8);
        this.f1939n.K(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z7) {
        int i8 = this.f1950y;
        int i9 = (z7 ? 1 : 0) | (i8 & (-2));
        this.f1950y = i9;
        if (i8 != i9) {
            this.f1939n.K(false);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z7) {
        if ((this.f1950y & 4) != 0) {
            this.f1939n.T(this);
        } else {
            s(z7);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z7) {
        this.f1950y = z7 ? this.f1950y | 16 : this.f1950y & (-17);
        this.f1939n.K(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i8) {
        this.f1937l = null;
        this.f1938m = i8;
        this.f1949x = true;
        this.f1939n.K(false);
        return this;
    }

    @Override // d1.b, android.view.MenuItem
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f1945t = colorStateList;
        this.f1947v = true;
        this.f1949x = true;
        this.f1939n.K(false);
        return this;
    }

    @Override // d1.b, android.view.MenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f1946u = mode;
        this.f1948w = true;
        this.f1949x = true;
        this.f1939n.K(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        this.f1932g = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c8) {
        if (this.f1933h == c8) {
            return this;
        }
        this.f1933h = c8;
        this.f1939n.K(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.C = onActionExpandListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f1942q = onMenuItemClickListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c8, char c9) {
        this.f1933h = c8;
        this.f1935j = Character.toLowerCase(c9);
        this.f1939n.K(false);
        return this;
    }

    @Override // d1.b, android.view.MenuItem
    public void setShowAsAction(int i8) {
        int i9 = i8 & 3;
        if (i9 != 0 && i9 != 1 && i9 != 2) {
            throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
        }
        this.f1951z = i8;
        this.f1939n.I(this);
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i8) {
        return setTitle(this.f1939n.u().getString(i8));
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f1931f = charSequence;
        this.f1939n.K(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z7) {
        if (y(z7)) {
            this.f1939n.J(this);
        }
        return this;
    }

    public void t(boolean z7) {
        this.f1950y = (z7 ? 4 : 0) | (this.f1950y & (-5));
    }

    public String toString() {
        CharSequence charSequence = this.f1930e;
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    public void u(boolean z7) {
        this.f1950y = z7 ? this.f1950y | 32 : this.f1950y & (-33);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void v(ContextMenu.ContextMenuInfo contextMenuInfo) {
        this.E = contextMenuInfo;
    }

    @Override // d1.b, android.view.MenuItem
    /* renamed from: w */
    public d1.b setShowAsActionFlags(int i8) {
        setShowAsAction(i8);
        return this;
    }

    public void x(r rVar) {
        this.f1940o = rVar;
        rVar.setHeaderTitle(getTitle());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean y(boolean z7) {
        int i8 = this.f1950y;
        int i9 = (z7 ? 0 : 8) | (i8 & (-9));
        this.f1950y = i9;
        return i8 != i9;
    }

    public boolean z() {
        return this.f1939n.A();
    }

    @Override // d1.b, android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c8, int i8) {
        if (this.f1935j == c8 && this.f1936k == i8) {
            return this;
        }
        this.f1935j = Character.toLowerCase(c8);
        this.f1936k = KeyEvent.normalizeMetaState(i8);
        this.f1939n.K(false);
        return this;
    }

    @Override // android.view.MenuItem
    public d1.b setContentDescription(CharSequence charSequence) {
        this.f1943r = charSequence;
        this.f1939n.K(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.f1938m = 0;
        this.f1937l = drawable;
        this.f1949x = true;
        this.f1939n.K(false);
        return this;
    }

    @Override // d1.b, android.view.MenuItem
    public MenuItem setNumericShortcut(char c8, int i8) {
        if (this.f1933h == c8 && this.f1934i == i8) {
            return this;
        }
        this.f1933h = c8;
        this.f1934i = KeyEvent.normalizeMetaState(i8);
        this.f1939n.K(false);
        return this;
    }

    @Override // d1.b, android.view.MenuItem
    public MenuItem setShortcut(char c8, char c9, int i8, int i9) {
        this.f1933h = c8;
        this.f1934i = KeyEvent.normalizeMetaState(i8);
        this.f1935j = Character.toLowerCase(c9);
        this.f1936k = KeyEvent.normalizeMetaState(i9);
        this.f1939n.K(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.f1930e = charSequence;
        this.f1939n.K(false);
        r rVar = this.f1940o;
        if (rVar != null) {
            rVar.setHeaderTitle(charSequence);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public d1.b setTooltipText(CharSequence charSequence) {
        this.f1944s = charSequence;
        this.f1939n.K(false);
        return this;
    }
}
