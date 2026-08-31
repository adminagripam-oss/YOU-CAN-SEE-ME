package androidx.core.content;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.text.TextUtils;
import androidx.core.app.m;
import androidx.core.content.res.f;
import java.io.File;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f2922a = new Object();

    /* renamed from: androidx.core.content.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static class C0021a {
        static File a(Context context) {
            return context.getCodeCacheDir();
        }

        static Drawable b(Context context, int i8) {
            return context.getDrawable(i8);
        }

        static File c(Context context) {
            return context.getNoBackupFilesDir();
        }
    }

    /* loaded from: classes.dex */
    static class b {
        static int a(Context context, int i8) {
            return context.getColor(i8);
        }

        static <T> T b(Context context, Class<T> cls) {
            return (T) context.getSystemService(cls);
        }

        static String c(Context context, Class<?> cls) {
            return context.getSystemServiceName(cls);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class c {
        static Intent a(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str, Handler handler, int i8) {
            return ((i8 & 4) == 0 || str != null) ? context.registerReceiver(broadcastReceiver, intentFilter, str, handler, i8 & 1) : context.registerReceiver(broadcastReceiver, intentFilter, a.h(context), handler);
        }

        static ComponentName b(Context context, Intent intent) {
            return context.startForegroundService(intent);
        }
    }

    /* loaded from: classes.dex */
    static class d {
        static Executor a(Context context) {
            return context.getMainExecutor();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class e {
        static Intent a(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str, Handler handler, int i8) {
            return context.registerReceiver(broadcastReceiver, intentFilter, str, handler, i8);
        }
    }

    public static int a(Context context, String str) {
        g1.c.d(str, "permission must be non-null");
        return (Build.VERSION.SDK_INT >= 33 || !TextUtils.equals("android.permission.POST_NOTIFICATIONS", str)) ? context.checkPermission(str, Process.myPid(), Process.myUid()) : m.c(context).a() ? 0 : -1;
    }

    public static int b(Context context, int i8) {
        return b.a(context, i8);
    }

    public static ColorStateList c(Context context, int i8) {
        return f.c(context.getResources(), i8, context.getTheme());
    }

    public static Drawable d(Context context, int i8) {
        return C0021a.b(context, i8);
    }

    public static File[] e(Context context) {
        return context.getExternalCacheDirs();
    }

    public static File[] f(Context context, String str) {
        return context.getExternalFilesDirs(str);
    }

    public static Executor g(Context context) {
        return Build.VERSION.SDK_INT >= 28 ? d.a(context) : androidx.core.os.f.a(new Handler(context.getMainLooper()));
    }

    static String h(Context context) {
        String str = context.getPackageName() + ".DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION";
        if (androidx.core.content.e.b(context, str) == 0) {
            return str;
        }
        throw new RuntimeException("Permission " + str + " is required by your application to receive broadcasts, please add it to your manifest");
    }

    public static Intent i(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, int i8) {
        return j(context, broadcastReceiver, intentFilter, null, null, i8);
    }

    public static Intent j(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str, Handler handler, int i8) {
        int i9 = i8 & 1;
        if (i9 == 0 || (i8 & 4) == 0) {
            if (i9 != 0) {
                i8 |= 2;
            }
            int i10 = i8;
            int i11 = i10 & 2;
            if (i11 == 0 && (i10 & 4) == 0) {
                throw new IllegalArgumentException("One of either RECEIVER_EXPORTED or RECEIVER_NOT_EXPORTED is required");
            }
            if (i11 == 0 || (i10 & 4) == 0) {
                return Build.VERSION.SDK_INT >= 33 ? e.a(context, broadcastReceiver, intentFilter, str, handler, i10) : c.a(context, broadcastReceiver, intentFilter, str, handler, i10);
            }
            throw new IllegalArgumentException("Cannot specify both RECEIVER_EXPORTED and RECEIVER_NOT_EXPORTED");
        }
        throw new IllegalArgumentException("Cannot specify both RECEIVER_VISIBLE_TO_INSTANT_APPS and RECEIVER_NOT_EXPORTED");
    }

    public static boolean k(Context context, Intent[] intentArr, Bundle bundle) {
        context.startActivities(intentArr, bundle);
        return true;
    }

    public static void l(Context context, Intent intent, Bundle bundle) {
        context.startActivity(intent, bundle);
    }
}
