package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import org.xmlpull.v1.XmlPullParser;
/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: androidx.core.graphics.drawable.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static class C0025a {
        static void a(Drawable drawable, Resources.Theme theme) {
            drawable.applyTheme(theme);
        }

        static boolean b(Drawable drawable) {
            return drawable.canApplyTheme();
        }

        static ColorFilter c(Drawable drawable) {
            return drawable.getColorFilter();
        }

        static void d(Drawable drawable, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            drawable.inflate(resources, xmlPullParser, attributeSet, theme);
        }

        static void e(Drawable drawable, float f8, float f9) {
            drawable.setHotspot(f8, f9);
        }

        static void f(Drawable drawable, int i8, int i9, int i10, int i11) {
            drawable.setHotspotBounds(i8, i9, i10, i11);
        }

        static void g(Drawable drawable, int i8) {
            drawable.setTint(i8);
        }

        static void h(Drawable drawable, ColorStateList colorStateList) {
            drawable.setTintList(colorStateList);
        }

        static void i(Drawable drawable, PorterDuff.Mode mode) {
            drawable.setTintMode(mode);
        }
    }

    /* loaded from: classes.dex */
    static class b {
        static int a(Drawable drawable) {
            return drawable.getLayoutDirection();
        }

        static boolean b(Drawable drawable, int i8) {
            return drawable.setLayoutDirection(i8);
        }
    }

    public static boolean a(Drawable drawable) {
        return drawable.isAutoMirrored();
    }

    public static void b(Drawable drawable, boolean z7) {
        drawable.setAutoMirrored(z7);
    }

    public static void c(Drawable drawable, float f8, float f9) {
        C0025a.e(drawable, f8, f9);
    }

    public static void d(Drawable drawable, int i8, int i9, int i10, int i11) {
        C0025a.f(drawable, i8, i9, i10, i11);
    }

    public static boolean e(Drawable drawable, int i8) {
        return b.b(drawable, i8);
    }

    public static void f(Drawable drawable, int i8) {
        C0025a.g(drawable, i8);
    }

    public static void g(Drawable drawable, ColorStateList colorStateList) {
        C0025a.h(drawable, colorStateList);
    }

    public static void h(Drawable drawable, PorterDuff.Mode mode) {
        C0025a.i(drawable, mode);
    }

    public static Drawable i(Drawable drawable) {
        return drawable;
    }
}
