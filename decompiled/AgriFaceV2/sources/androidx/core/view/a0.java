package androidx.core.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.MenuItem;
/* loaded from: classes.dex */
public abstract class a0 {

    /* loaded from: classes.dex */
    static class a {
        static int a(MenuItem menuItem) {
            return menuItem.getAlphabeticModifiers();
        }

        static CharSequence b(MenuItem menuItem) {
            return menuItem.getContentDescription();
        }

        static ColorStateList c(MenuItem menuItem) {
            return menuItem.getIconTintList();
        }

        static PorterDuff.Mode d(MenuItem menuItem) {
            return menuItem.getIconTintMode();
        }

        static int e(MenuItem menuItem) {
            return menuItem.getNumericModifiers();
        }

        static CharSequence f(MenuItem menuItem) {
            return menuItem.getTooltipText();
        }

        static MenuItem g(MenuItem menuItem, char c8, int i8) {
            return menuItem.setAlphabeticShortcut(c8, i8);
        }

        static MenuItem h(MenuItem menuItem, CharSequence charSequence) {
            return menuItem.setContentDescription(charSequence);
        }

        static MenuItem i(MenuItem menuItem, ColorStateList colorStateList) {
            return menuItem.setIconTintList(colorStateList);
        }

        static MenuItem j(MenuItem menuItem, PorterDuff.Mode mode) {
            return menuItem.setIconTintMode(mode);
        }

        static MenuItem k(MenuItem menuItem, char c8, int i8) {
            return menuItem.setNumericShortcut(c8, i8);
        }

        static MenuItem l(MenuItem menuItem, char c8, char c9, int i8, int i9) {
            return menuItem.setShortcut(c8, c9, i8, i9);
        }

        static MenuItem m(MenuItem menuItem, CharSequence charSequence) {
            return menuItem.setTooltipText(charSequence);
        }
    }

    public static MenuItem a(MenuItem menuItem, b bVar) {
        if (menuItem instanceof d1.b) {
            return ((d1.b) menuItem).a(bVar);
        }
        Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
        return menuItem;
    }

    public static void b(MenuItem menuItem, char c8, int i8) {
        if (menuItem instanceof d1.b) {
            ((d1.b) menuItem).setAlphabeticShortcut(c8, i8);
        } else {
            a.g(menuItem, c8, i8);
        }
    }

    public static void c(MenuItem menuItem, CharSequence charSequence) {
        if (menuItem instanceof d1.b) {
            ((d1.b) menuItem).setContentDescription(charSequence);
        } else {
            a.h(menuItem, charSequence);
        }
    }

    public static void d(MenuItem menuItem, ColorStateList colorStateList) {
        if (menuItem instanceof d1.b) {
            ((d1.b) menuItem).setIconTintList(colorStateList);
        } else {
            a.i(menuItem, colorStateList);
        }
    }

    public static void e(MenuItem menuItem, PorterDuff.Mode mode) {
        if (menuItem instanceof d1.b) {
            ((d1.b) menuItem).setIconTintMode(mode);
        } else {
            a.j(menuItem, mode);
        }
    }

    public static void f(MenuItem menuItem, char c8, int i8) {
        if (menuItem instanceof d1.b) {
            ((d1.b) menuItem).setNumericShortcut(c8, i8);
        } else {
            a.k(menuItem, c8, i8);
        }
    }

    public static void g(MenuItem menuItem, CharSequence charSequence) {
        if (menuItem instanceof d1.b) {
            ((d1.b) menuItem).setTooltipText(charSequence);
        } else {
            a.m(menuItem, charSequence);
        }
    }
}
