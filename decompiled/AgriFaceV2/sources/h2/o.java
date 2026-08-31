package h2;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.inputmethodservice.InputMethodService;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.DisplayCutout;
import android.view.WindowManager;
import androidx.core.view.b1;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
/* loaded from: classes.dex */
public final class o implements l {

    /* renamed from: b  reason: collision with root package name */
    public static final o f7217b = new o();

    /* renamed from: c  reason: collision with root package name */
    private static final String f7218c;

    /* renamed from: d  reason: collision with root package name */
    private static final ArrayList f7219d;

    static {
        ArrayList d8;
        String simpleName = o.class.getSimpleName();
        d7.k.d(simpleName, "WindowMetricsCalculatorC…at::class.java.simpleName");
        f7218c = simpleName;
        d8 = s6.n.d(Integer.valueOf(b1.m.g()), Integer.valueOf(b1.m.f()), Integer.valueOf(b1.m.a()), Integer.valueOf(b1.m.c()), Integer.valueOf(b1.m.h()), Integer.valueOf(b1.m.e()), Integer.valueOf(b1.m.i()), Integer.valueOf(b1.m.b()));
        f7219d = d8;
    }

    private o() {
    }

    private final DisplayCutout j(Display display) {
        try {
            Constructor<?> constructor = Class.forName("android.view.DisplayInfo").getConstructor(new Class[0]);
            constructor.setAccessible(true);
            Object newInstance = constructor.newInstance(new Object[0]);
            Method declaredMethod = display.getClass().getDeclaredMethod("getDisplayInfo", newInstance.getClass());
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(display, newInstance);
            Field declaredField = newInstance.getClass().getDeclaredField("displayCutout");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(newInstance);
            if (m.a(obj)) {
                return n.a(obj);
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException e8) {
            Log.w(f7218c, e8);
        }
        return null;
    }

    private final int k(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    private final void m(Activity activity, Rect rect) {
        activity.getWindowManager().getDefaultDisplay().getRectSize(rect);
    }

    @Override // h2.l
    public k a(Activity activity) {
        d7.k.e(activity, "activity");
        return e(activity);
    }

    public k c(Activity activity) {
        b1 a8;
        d7.k.e(activity, "activity");
        int i8 = Build.VERSION.SDK_INT;
        Rect a9 = i8 >= 30 ? l2.g.f8975a.a(activity) : i8 >= 29 ? h(activity) : i8 >= 28 ? g(activity) : f(activity);
        if (i8 >= 30) {
            a8 = i(activity);
        } else {
            a8 = new b1.b().a();
            d7.k.d(a8, "{\n            WindowInse…ilder().build()\n        }");
        }
        return new k(new e2.b(a9), a8);
    }

    public k d(Context context) {
        d7.k.e(context, "context");
        if (Build.VERSION.SDK_INT >= 30) {
            return l2.g.f8975a.c(context);
        }
        Context a8 = l2.b.f8974a.a(context);
        if (a8 instanceof Activity) {
            return c((Activity) context);
        }
        if (!(a8 instanceof InputMethodService)) {
            throw new IllegalArgumentException(context + " is not a UiContext");
        }
        Object systemService = context.getSystemService("window");
        d7.k.c(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        Display defaultDisplay = ((WindowManager) systemService).getDefaultDisplay();
        d7.k.d(defaultDisplay, "wm.defaultDisplay");
        Point l8 = l(defaultDisplay);
        return new k(new Rect(0, 0, l8.x, l8.y), null, 2, null);
    }

    public k e(Context context) {
        Rect rect;
        b1 a8;
        d7.k.e(context, "context");
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 30) {
            rect = l2.g.f8975a.d(context);
        } else {
            Object systemService = context.getSystemService("window");
            d7.k.c(systemService, "null cannot be cast to non-null type android.view.WindowManager");
            Display defaultDisplay = ((WindowManager) systemService).getDefaultDisplay();
            d7.k.d(defaultDisplay, "display");
            Point l8 = l(defaultDisplay);
            rect = new Rect(0, 0, l8.x, l8.y);
        }
        if (i8 >= 30) {
            a8 = i(context);
        } else {
            a8 = new b1.b().a();
            d7.k.d(a8, "{\n            WindowInse…ilder().build()\n        }");
        }
        return new k(new e2.b(rect), a8);
    }

    public final Rect f(Activity activity) {
        d7.k.e(activity, "activity");
        Rect rect = new Rect();
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        defaultDisplay.getRectSize(rect);
        if (!l2.a.f8973a.a(activity)) {
            d7.k.d(defaultDisplay, "defaultDisplay");
            Point l8 = l(defaultDisplay);
            int k8 = k(activity);
            int i8 = rect.bottom;
            if (i8 + k8 == l8.y) {
                rect.bottom = i8 + k8;
            } else {
                int i9 = rect.right;
                if (i9 + k8 == l8.x) {
                    rect.right = i9 + k8;
                }
            }
        }
        return rect;
    }

    public final Rect g(Activity activity) {
        DisplayCutout j8;
        Rect rect;
        d7.k.e(activity, "activity");
        Rect rect2 = new Rect();
        Configuration configuration = activity.getResources().getConfiguration();
        try {
            Field declaredField = Configuration.class.getDeclaredField("windowConfiguration");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(configuration);
            if (l2.a.f8973a.a(activity)) {
                Object invoke = obj.getClass().getDeclaredMethod("getBounds", new Class[0]).invoke(obj, new Object[0]);
                d7.k.c(invoke, "null cannot be cast to non-null type android.graphics.Rect");
                rect = (Rect) invoke;
            } else {
                Object invoke2 = obj.getClass().getDeclaredMethod("getAppBounds", new Class[0]).invoke(obj, new Object[0]);
                d7.k.c(invoke2, "null cannot be cast to non-null type android.graphics.Rect");
                rect = (Rect) invoke2;
            }
            rect2.set(rect);
        } catch (IllegalAccessException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException e8) {
            Log.w(f7218c, e8);
            m(activity, rect2);
        }
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        l2.h hVar = l2.h.f8976a;
        d7.k.d(defaultDisplay, "currentDisplay");
        hVar.a(defaultDisplay, point);
        l2.a aVar = l2.a.f8973a;
        if (!aVar.a(activity)) {
            int k8 = k(activity);
            int i8 = rect2.bottom;
            if (i8 + k8 == point.y) {
                rect2.bottom = i8 + k8;
            } else {
                int i9 = rect2.right;
                if (i9 + k8 == point.x) {
                    rect2.right = i9 + k8;
                } else if (rect2.left == k8) {
                    rect2.left = 0;
                }
            }
        }
        if ((rect2.width() < point.x || rect2.height() < point.y) && !aVar.a(activity) && (j8 = j(defaultDisplay)) != null) {
            int i10 = rect2.left;
            l2.m mVar = l2.m.f8977a;
            if (i10 == mVar.b(j8)) {
                rect2.left = 0;
            }
            if (point.x - rect2.right == mVar.c(j8)) {
                rect2.right += mVar.c(j8);
            }
            if (rect2.top == mVar.d(j8)) {
                rect2.top = 0;
            }
            if (point.y - rect2.bottom == mVar.a(j8)) {
                rect2.bottom += mVar.a(j8);
            }
        }
        return rect2;
    }

    public final Rect h(Activity activity) {
        d7.k.e(activity, "activity");
        Configuration configuration = activity.getResources().getConfiguration();
        try {
            Field declaredField = Configuration.class.getDeclaredField("windowConfiguration");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(configuration);
            Object invoke = obj.getClass().getDeclaredMethod("getBounds", new Class[0]).invoke(obj, new Object[0]);
            d7.k.c(invoke, "null cannot be cast to non-null type android.graphics.Rect");
            return new Rect((Rect) invoke);
        } catch (IllegalAccessException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException e8) {
            Log.w(f7218c, e8);
            return g(activity);
        }
    }

    public final b1 i(Context context) {
        d7.k.e(context, "context");
        if (Build.VERSION.SDK_INT >= 30) {
            return l2.g.f8975a.b(context);
        }
        throw new Exception("Incompatible SDK version");
    }

    public final Point l(Display display) {
        d7.k.e(display, "display");
        Point point = new Point();
        l2.h.f8976a.a(display, point);
        return point;
    }
}
