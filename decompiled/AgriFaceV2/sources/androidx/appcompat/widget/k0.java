package androidx.appcompat.widget;

import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
/* loaded from: classes.dex */
public abstract class k0 {

    /* renamed from: a  reason: collision with root package name */
    private static final int[] f2362a = {16842912};

    /* renamed from: b  reason: collision with root package name */
    private static final int[] f2363b = new int[0];

    /* renamed from: c  reason: collision with root package name */
    public static final Rect f2364c = new Rect();

    public static boolean a(Drawable drawable) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(Drawable drawable) {
        String name = drawable.getClass().getName();
        int i8 = Build.VERSION.SDK_INT;
        if (i8 < 29 || i8 >= 31 || !"android.graphics.drawable.ColorStateListDrawable".equals(name)) {
            return;
        }
        c(drawable);
    }

    private static void c(Drawable drawable) {
        int[] state = drawable.getState();
        if (state == null || state.length == 0) {
            drawable.setState(f2362a);
        } else {
            drawable.setState(f2363b);
        }
        drawable.setState(state);
    }

    public static PorterDuff.Mode d(int i8, PorterDuff.Mode mode) {
        if (i8 != 3) {
            if (i8 != 5) {
                if (i8 != 9) {
                    switch (i8) {
                        case 14:
                            return PorterDuff.Mode.MULTIPLY;
                        case 15:
                            return PorterDuff.Mode.SCREEN;
                        case 16:
                            return PorterDuff.Mode.ADD;
                        default:
                            return mode;
                    }
                }
                return PorterDuff.Mode.SRC_ATOP;
            }
            return PorterDuff.Mode.SRC_IN;
        }
        return PorterDuff.Mode.SRC_OVER;
    }
}
