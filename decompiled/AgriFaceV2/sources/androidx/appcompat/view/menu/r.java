package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.appcompat.view.menu.g;
/* loaded from: classes.dex */
public class r extends g implements SubMenu {
    private g B;
    private i C;

    public r(Context context, g gVar, i iVar) {
        super(context);
        this.B = gVar;
        this.C = iVar;
    }

    @Override // androidx.appcompat.view.menu.g
    public g D() {
        return this.B.D();
    }

    @Override // androidx.appcompat.view.menu.g
    public boolean F() {
        return this.B.F();
    }

    @Override // androidx.appcompat.view.menu.g
    public boolean G() {
        return this.B.G();
    }

    @Override // androidx.appcompat.view.menu.g
    public boolean H() {
        return this.B.H();
    }

    @Override // androidx.appcompat.view.menu.g
    public void R(g.a aVar) {
        this.B.R(aVar);
    }

    @Override // androidx.appcompat.view.menu.g
    public boolean f(i iVar) {
        return this.B.f(iVar);
    }

    public Menu f0() {
        return this.B;
    }

    @Override // android.view.SubMenu
    public MenuItem getItem() {
        return this.C;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.appcompat.view.menu.g
    public boolean h(g gVar, MenuItem menuItem) {
        return super.h(gVar, menuItem) || this.B.h(gVar, menuItem);
    }

    @Override // androidx.appcompat.view.menu.g
    public boolean k(i iVar) {
        return this.B.k(iVar);
    }

    @Override // androidx.appcompat.view.menu.g, android.view.Menu
    public void setGroupDividerEnabled(boolean z7) {
        this.B.setGroupDividerEnabled(z7);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(int i8) {
        return (SubMenu) super.U(i8);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(int i8) {
        return (SubMenu) super.X(i8);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderView(View view) {
        return (SubMenu) super.Z(view);
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(int i8) {
        this.C.setIcon(i8);
        return this;
    }

    @Override // androidx.appcompat.view.menu.g, android.view.Menu
    public void setQwertyMode(boolean z7) {
        this.B.setQwertyMode(z7);
    }

    @Override // androidx.appcompat.view.menu.g
    public String t() {
        i iVar = this.C;
        int itemId = iVar != null ? iVar.getItemId() : 0;
        if (itemId == 0) {
            return null;
        }
        return super.t() + ":" + itemId;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(Drawable drawable) {
        return (SubMenu) super.V(drawable);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(CharSequence charSequence) {
        return (SubMenu) super.Y(charSequence);
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(Drawable drawable) {
        this.C.setIcon(drawable);
        return this;
    }
}
