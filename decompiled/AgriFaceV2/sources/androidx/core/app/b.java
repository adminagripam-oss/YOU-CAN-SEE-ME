package androidx.core.app;

import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
/* loaded from: classes.dex */
public abstract class b extends androidx.core.content.a {

    /* loaded from: classes.dex */
    static class a {
        static void a(Activity activity) {
            activity.finishAfterTransition();
        }

        static void b(Activity activity) {
            activity.postponeEnterTransition();
        }

        static void c(Activity activity, SharedElementCallback sharedElementCallback) {
            activity.setEnterSharedElementCallback(sharedElementCallback);
        }

        static void d(Activity activity, SharedElementCallback sharedElementCallback) {
            activity.setExitSharedElementCallback(sharedElementCallback);
        }

        static void e(Activity activity) {
            activity.startPostponedEnterTransition();
        }
    }

    /* renamed from: androidx.core.app.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static class C0019b {
        static void a(Object obj) {
            ((SharedElementCallback.OnSharedElementsReadyListener) obj).onSharedElementsReady();
        }

        static void b(Activity activity, String[] strArr, int i8) {
            activity.requestPermissions(strArr, i8);
        }

        static boolean c(Activity activity, String str) {
            return activity.shouldShowRequestPermissionRationale(str);
        }
    }

    /* loaded from: classes.dex */
    static class c {
        static boolean a(Activity activity) {
            return activity.isLaunchedFromBubble();
        }

        static boolean b(Activity activity, String str) {
            try {
                return ((Boolean) PackageManager.class.getMethod("shouldShowRequestPermissionRationale", String.class).invoke(activity.getApplication().getPackageManager(), str)).booleanValue();
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                return activity.shouldShowRequestPermissionRationale(str);
            }
        }
    }

    /* loaded from: classes.dex */
    static class d {
        static boolean a(Activity activity, String str) {
            return activity.shouldShowRequestPermissionRationale(str);
        }
    }

    /* loaded from: classes.dex */
    public interface e {
        void validateRequestPermissionsRequestCode(int i8);
    }

    public static void n(Activity activity) {
        activity.finishAffinity();
    }

    public static void o(Activity activity) {
        a.a(activity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void p(Activity activity) {
        if (activity.isFinishing() || androidx.core.app.d.i(activity)) {
            return;
        }
        activity.recreate();
    }

    public static void q(Activity activity) {
        a.b(activity);
    }

    public static void r(final Activity activity) {
        if (Build.VERSION.SDK_INT >= 28) {
            activity.recreate();
        } else {
            new Handler(activity.getMainLooper()).post(new Runnable() { // from class: androidx.core.app.a
                @Override // java.lang.Runnable
                public final void run() {
                    b.p(activity);
                }
            });
        }
    }

    public static void s(Activity activity, String[] strArr, int i8) {
        HashSet hashSet = new HashSet();
        for (int i9 = 0; i9 < strArr.length; i9++) {
            if (TextUtils.isEmpty(strArr[i9])) {
                throw new IllegalArgumentException("Permission request for permissions " + Arrays.toString(strArr) + " must not contain null or empty values");
            }
            if (Build.VERSION.SDK_INT < 33 && TextUtils.equals(strArr[i9], "android.permission.POST_NOTIFICATIONS")) {
                hashSet.add(Integer.valueOf(i9));
            }
        }
        int size = hashSet.size();
        String[] strArr2 = size > 0 ? new String[strArr.length - size] : strArr;
        if (size > 0) {
            if (size == strArr.length) {
                return;
            }
            int i10 = 0;
            for (int i11 = 0; i11 < strArr.length; i11++) {
                if (!hashSet.contains(Integer.valueOf(i11))) {
                    strArr2[i10] = strArr[i11];
                    i10++;
                }
            }
        }
        if (activity instanceof e) {
            ((e) activity).validateRequestPermissionsRequestCode(i8);
        }
        C0019b.b(activity, strArr, i8);
    }

    public static void t(Activity activity, r rVar) {
        a.c(activity, null);
    }

    public static void u(Activity activity, r rVar) {
        a.d(activity, null);
    }

    public static boolean v(Activity activity, String str) {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 33 || !TextUtils.equals("android.permission.POST_NOTIFICATIONS", str)) {
            return i8 >= 32 ? d.a(activity, str) : i8 == 31 ? c.b(activity, str) : C0019b.c(activity, str);
        }
        return false;
    }

    public static void w(Activity activity, Intent intent, int i8, Bundle bundle) {
        activity.startActivityForResult(intent, i8, bundle);
    }

    public static void x(Activity activity, IntentSender intentSender, int i8, Intent intent, int i9, int i10, int i11, Bundle bundle) {
        activity.startIntentSenderForResult(intentSender, i8, intent, i9, i10, i11, bundle);
    }

    public static void y(Activity activity) {
        a.e(activity);
    }
}
