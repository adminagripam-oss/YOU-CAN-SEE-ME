package androidx.core.app;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class d {

    /* renamed from: a  reason: collision with root package name */
    protected static final Class f2820a;

    /* renamed from: b  reason: collision with root package name */
    protected static final Field f2821b;

    /* renamed from: c  reason: collision with root package name */
    protected static final Field f2822c;

    /* renamed from: d  reason: collision with root package name */
    protected static final Method f2823d;

    /* renamed from: e  reason: collision with root package name */
    protected static final Method f2824e;

    /* renamed from: f  reason: collision with root package name */
    protected static final Method f2825f;

    /* renamed from: g  reason: collision with root package name */
    private static final Handler f2826g = new Handler(Looper.getMainLooper());

    /* loaded from: classes.dex */
    class a implements Runnable {

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ C0020d f2827e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ Object f2828f;

        a(C0020d c0020d, Object obj) {
            this.f2827e = c0020d;
            this.f2828f = obj;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f2827e.f2833a = this.f2828f;
        }
    }

    /* loaded from: classes.dex */
    class b implements Runnable {

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ Application f2829e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ C0020d f2830f;

        b(Application application, C0020d c0020d) {
            this.f2829e = application;
            this.f2830f = c0020d;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f2829e.unregisterActivityLifecycleCallbacks(this.f2830f);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c implements Runnable {

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ Object f2831e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ Object f2832f;

        c(Object obj, Object obj2) {
            this.f2831e = obj;
            this.f2832f = obj2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Method method = d.f2823d;
                if (method != null) {
                    method.invoke(this.f2831e, this.f2832f, Boolean.FALSE, "AppCompat recreation");
                } else {
                    d.f2824e.invoke(this.f2831e, this.f2832f, Boolean.FALSE);
                }
            } catch (RuntimeException e8) {
                if (e8.getClass() == RuntimeException.class && e8.getMessage() != null && e8.getMessage().startsWith("Unable to stop")) {
                    throw e8;
                }
            } catch (Throwable th) {
                Log.e("ActivityRecreator", "Exception while invoking performStopActivity", th);
            }
        }
    }

    /* renamed from: androidx.core.app.d$d  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private static final class C0020d implements Application.ActivityLifecycleCallbacks {

        /* renamed from: a  reason: collision with root package name */
        Object f2833a;

        /* renamed from: b  reason: collision with root package name */
        private Activity f2834b;

        /* renamed from: c  reason: collision with root package name */
        private final int f2835c;

        /* renamed from: d  reason: collision with root package name */
        private boolean f2836d = false;

        /* renamed from: e  reason: collision with root package name */
        private boolean f2837e = false;

        /* renamed from: f  reason: collision with root package name */
        private boolean f2838f = false;

        C0020d(Activity activity) {
            this.f2834b = activity;
            this.f2835c = activity.hashCode();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            if (this.f2834b == activity) {
                this.f2834b = null;
                this.f2837e = true;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            if (!this.f2837e || this.f2838f || this.f2836d || !d.h(this.f2833a, this.f2835c, activity)) {
                return;
            }
            this.f2838f = true;
            this.f2833a = null;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            if (this.f2834b == activity) {
                this.f2836d = true;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }
    }

    static {
        Class a8 = a();
        f2820a = a8;
        f2821b = b();
        f2822c = f();
        f2823d = d(a8);
        f2824e = c(a8);
        f2825f = e(a8);
    }

    private static Class a() {
        try {
            return Class.forName("android.app.ActivityThread");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Field b() {
        try {
            Field declaredField = Activity.class.getDeclaredField("mMainThread");
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Method c(Class cls) {
        if (cls == null) {
            return null;
        }
        try {
            Method declaredMethod = cls.getDeclaredMethod("performStopActivity", IBinder.class, Boolean.TYPE);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Method d(Class cls) {
        if (cls == null) {
            return null;
        }
        try {
            Method declaredMethod = cls.getDeclaredMethod("performStopActivity", IBinder.class, Boolean.TYPE, String.class);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Method e(Class cls) {
        if (g() && cls != null) {
            try {
                Class<?> cls2 = Boolean.TYPE;
                Method declaredMethod = cls.getDeclaredMethod("requestRelaunchActivity", IBinder.class, List.class, List.class, Integer.TYPE, cls2, Configuration.class, Configuration.class, cls2, cls2);
                declaredMethod.setAccessible(true);
                return declaredMethod;
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    private static Field f() {
        try {
            Field declaredField = Activity.class.getDeclaredField("mToken");
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean g() {
        int i8 = Build.VERSION.SDK_INT;
        return i8 == 26 || i8 == 27;
    }

    protected static boolean h(Object obj, int i8, Activity activity) {
        try {
            Object obj2 = f2822c.get(activity);
            if (obj2 == obj && activity.hashCode() == i8) {
                f2826g.postAtFrontOfQueue(new c(f2821b.get(activity), obj2));
                return true;
            }
            return false;
        } catch (Throwable th) {
            Log.e("ActivityRecreator", "Exception while fetching field values", th);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean i(Activity activity) {
        Object obj;
        if (Build.VERSION.SDK_INT >= 28) {
            activity.recreate();
            return true;
        } else if (g() && f2825f == null) {
            return false;
        } else {
            if (f2824e == null && f2823d == null) {
                return false;
            }
            try {
                Object obj2 = f2822c.get(activity);
                if (obj2 == null || (obj = f2821b.get(activity)) == null) {
                    return false;
                }
                Application application = activity.getApplication();
                C0020d c0020d = new C0020d(activity);
                application.registerActivityLifecycleCallbacks(c0020d);
                Handler handler = f2826g;
                handler.post(new a(c0020d, obj2));
                if (g()) {
                    Method method = f2825f;
                    Boolean bool = Boolean.FALSE;
                    method.invoke(obj, obj2, null, null, 0, bool, null, null, bool, bool);
                } else {
                    activity.recreate();
                }
                handler.post(new b(application, c0020d));
                return true;
            } catch (Throwable unused) {
                return false;
            }
        }
    }
}
