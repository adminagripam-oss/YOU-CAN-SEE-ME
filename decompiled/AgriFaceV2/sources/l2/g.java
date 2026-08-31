package l2;

import android.content.Context;
import android.graphics.Rect;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;
import androidx.core.view.b1;
/* loaded from: classes.dex */
public final class g {

    /* renamed from: a  reason: collision with root package name */
    public static final g f8975a = new g();

    private g() {
    }

    public final Rect a(Context context) {
        WindowMetrics currentWindowMetrics;
        Rect bounds;
        d7.k.e(context, "context");
        currentWindowMetrics = ((WindowManager) context.getSystemService(WindowManager.class)).getCurrentWindowMetrics();
        bounds = currentWindowMetrics.getBounds();
        d7.k.d(bounds, "wm.currentWindowMetrics.bounds");
        return bounds;
    }

    public final b1 b(Context context) {
        WindowMetrics currentWindowMetrics;
        WindowInsets windowInsets;
        d7.k.e(context, "context");
        currentWindowMetrics = ((WindowManager) context.getSystemService(WindowManager.class)).getCurrentWindowMetrics();
        windowInsets = currentWindowMetrics.getWindowInsets();
        d7.k.d(windowInsets, "context.getSystemService…indowMetrics.windowInsets");
        b1 u7 = b1.u(windowInsets);
        d7.k.d(u7, "toWindowInsetsCompat(platformInsets)");
        return u7;
    }

    public final h2.k c(Context context) {
        WindowMetrics currentWindowMetrics;
        WindowInsets windowInsets;
        WindowMetrics currentWindowMetrics2;
        Rect bounds;
        d7.k.e(context, "context");
        WindowManager windowManager = (WindowManager) context.getSystemService(WindowManager.class);
        currentWindowMetrics = windowManager.getCurrentWindowMetrics();
        windowInsets = currentWindowMetrics.getWindowInsets();
        b1 u7 = b1.u(windowInsets);
        d7.k.d(u7, "toWindowInsetsCompat(wm.…ndowMetrics.windowInsets)");
        currentWindowMetrics2 = windowManager.getCurrentWindowMetrics();
        bounds = currentWindowMetrics2.getBounds();
        d7.k.d(bounds, "wm.currentWindowMetrics.bounds");
        return new h2.k(bounds, u7);
    }

    public final Rect d(Context context) {
        WindowMetrics maximumWindowMetrics;
        Rect bounds;
        d7.k.e(context, "context");
        maximumWindowMetrics = ((WindowManager) context.getSystemService(WindowManager.class)).getMaximumWindowMetrics();
        bounds = maximumWindowMetrics.getBounds();
        d7.k.d(bounds, "wm.maximumWindowMetrics.bounds");
        return bounds;
    }
}
