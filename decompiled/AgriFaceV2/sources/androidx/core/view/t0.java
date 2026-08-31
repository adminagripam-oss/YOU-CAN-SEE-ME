package androidx.core.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.view.InputDevice;
import android.view.ViewConfiguration;
import java.util.Objects;
/* loaded from: classes.dex */
public abstract class t0 {

    /* loaded from: classes.dex */
    static class a {
        static int a(ViewConfiguration viewConfiguration) {
            return viewConfiguration.getScaledHoverSlop();
        }

        static boolean b(ViewConfiguration viewConfiguration) {
            return viewConfiguration.shouldShowMenuShortcutsWhenKeyboardPresent();
        }
    }

    /* loaded from: classes.dex */
    static class b {
        static int a(ViewConfiguration viewConfiguration, int i8, int i9, int i10) {
            return viewConfiguration.getScaledMaximumFlingVelocity(i8, i9, i10);
        }

        static int b(ViewConfiguration viewConfiguration, int i8, int i9, int i10) {
            return viewConfiguration.getScaledMinimumFlingVelocity(i8, i9, i10);
        }
    }

    private static int a(Resources resources, int i8, g1.f fVar, int i9) {
        int dimensionPixelSize;
        return i8 != -1 ? (i8 == 0 || (dimensionPixelSize = resources.getDimensionPixelSize(i8)) < 0) ? i9 : dimensionPixelSize : ((Integer) fVar.get()).intValue();
    }

    private static int b(Resources resources, String str, String str2) {
        return resources.getIdentifier(str, str2, "android");
    }

    private static int c(Resources resources, int i8, int i9) {
        if (i8 == 4194304 && i9 == 26) {
            return b(resources, "config_viewMaxRotaryEncoderFlingVelocity", "dimen");
        }
        return -1;
    }

    private static int d(Resources resources, int i8, int i9) {
        if (i8 == 4194304 && i9 == 26) {
            return b(resources, "config_viewMinRotaryEncoderFlingVelocity", "dimen");
        }
        return -1;
    }

    public static int e(Context context, final ViewConfiguration viewConfiguration, int i8, int i9, int i10) {
        if (Build.VERSION.SDK_INT >= 34) {
            return b.a(viewConfiguration, i8, i9, i10);
        }
        if (g(i8, i9, i10)) {
            Resources resources = context.getResources();
            int c8 = c(resources, i10, i9);
            Objects.requireNonNull(viewConfiguration);
            return a(resources, c8, new g1.f() { // from class: androidx.core.view.s0
                @Override // g1.f
                public final Object get() {
                    return Integer.valueOf(viewConfiguration.getScaledMaximumFlingVelocity());
                }
            }, Integer.MIN_VALUE);
        }
        return Integer.MIN_VALUE;
    }

    public static int f(Context context, final ViewConfiguration viewConfiguration, int i8, int i9, int i10) {
        if (Build.VERSION.SDK_INT >= 34) {
            return b.b(viewConfiguration, i8, i9, i10);
        }
        if (g(i8, i9, i10)) {
            Resources resources = context.getResources();
            int d8 = d(resources, i10, i9);
            Objects.requireNonNull(viewConfiguration);
            return a(resources, d8, new g1.f() { // from class: androidx.core.view.r0
                @Override // g1.f
                public final Object get() {
                    return Integer.valueOf(viewConfiguration.getScaledMinimumFlingVelocity());
                }
            }, Integer.MAX_VALUE);
        }
        return Integer.MAX_VALUE;
    }

    private static boolean g(int i8, int i9, int i10) {
        InputDevice device = InputDevice.getDevice(i8);
        return (device == null || device.getMotionRange(i9, i10) == null) ? false : true;
    }

    public static boolean h(ViewConfiguration viewConfiguration, Context context) {
        if (Build.VERSION.SDK_INT >= 28) {
            return a.b(viewConfiguration);
        }
        Resources resources = context.getResources();
        int b8 = b(resources, "config_showMenuShortcutsWhenKeyboardPresent", "bool");
        return b8 != 0 && resources.getBoolean(b8);
    }
}
