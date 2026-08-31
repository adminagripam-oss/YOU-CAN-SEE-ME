package h3;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.UserManager;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesIncorrectManifestValueException;
import com.google.android.gms.common.GooglePlayServicesMissingManifestValueException;
import java.util.concurrent.atomic.AtomicBoolean;
import k3.r0;
/* loaded from: classes.dex */
public abstract class g {

    /* renamed from: a  reason: collision with root package name */
    public static final int f7238a = 12451000;

    /* renamed from: b  reason: collision with root package name */
    static final AtomicBoolean f7239b = new AtomicBoolean();

    /* renamed from: c  reason: collision with root package name */
    private static final AtomicBoolean f7240c = new AtomicBoolean();

    public static int a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 0;
        }
    }

    public static String b(int i8) {
        return a.f(i8);
    }

    public static Resources c(Context context) {
        try {
            return context.getPackageManager().getResourcesForApplication("com.google.android.gms");
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public static int d(Context context, int i8) {
        String valueOf;
        String str;
        PackageInfo packageInfo;
        try {
            context.getResources().getString(i.f7243a);
        } catch (Throwable unused) {
            Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
        }
        if (!"com.google.android.gms".equals(context.getPackageName()) && !f7240c.get()) {
            int a8 = r0.a(context);
            if (a8 == 0) {
                throw new GooglePlayServicesMissingManifestValueException();
            }
            if (a8 != f7238a) {
                throw new GooglePlayServicesIncorrectManifestValueException(a8);
            }
        }
        boolean z7 = (q3.e.c(context) || q3.e.e(context)) ? false : true;
        k3.q.a(i8 >= 0);
        String packageName = context.getPackageName();
        PackageManager packageManager = context.getPackageManager();
        if (z7) {
            try {
                packageInfo = packageManager.getPackageInfo("com.android.vending", 8256);
            } catch (PackageManager.NameNotFoundException unused2) {
                valueOf = String.valueOf(packageName);
                str = " requires the Google Play Store, but it is missing.";
            }
        } else {
            packageInfo = null;
        }
        try {
            PackageInfo packageInfo2 = packageManager.getPackageInfo("com.google.android.gms", 64);
            h.a(context);
            if (h.c(packageInfo2, true)) {
                if (z7) {
                    k3.q.g(packageInfo);
                    if (!h.c(packageInfo, true)) {
                        valueOf = String.valueOf(packageName);
                        str = " requires Google Play Store, but its signature is invalid.";
                    }
                }
                if (!z7 || packageInfo == null || packageInfo.signatures[0].equals(packageInfo2.signatures[0])) {
                    if (q3.i.a(packageInfo2.versionCode) >= q3.i.a(i8)) {
                        ApplicationInfo applicationInfo = packageInfo2.applicationInfo;
                        if (applicationInfo == null) {
                            try {
                                applicationInfo = packageManager.getApplicationInfo("com.google.android.gms", 0);
                            } catch (PackageManager.NameNotFoundException e8) {
                                Log.wtf("GooglePlayServicesUtil", String.valueOf(packageName).concat(" requires Google Play services, but they're missing when getting application info."), e8);
                                return 1;
                            }
                        }
                        return !applicationInfo.enabled ? 3 : 0;
                    }
                    int i9 = packageInfo2.versionCode;
                    Log.w("GooglePlayServicesUtil", "Google Play services out of date for " + packageName + ".  Requires " + i8 + " but found " + i9);
                    return 2;
                }
                valueOf = String.valueOf(packageName);
                str = " requires Google Play Store, but its signature doesn't match that of Google Play services.";
            } else {
                valueOf = String.valueOf(packageName);
                str = " requires Google Play services, but their signature is invalid.";
            }
            Log.w("GooglePlayServicesUtil", valueOf.concat(str));
            return 9;
        } catch (PackageManager.NameNotFoundException unused3) {
            Log.w("GooglePlayServicesUtil", String.valueOf(packageName).concat(" requires Google Play services, but they are missing."));
            return 1;
        }
    }

    public static boolean e(Context context, int i8) {
        if (i8 == 18) {
            return true;
        }
        if (i8 == 1) {
            return h(context, "com.google.android.gms");
        }
        return false;
    }

    public static boolean f(Context context) {
        if (q3.g.c()) {
            Object systemService = context.getSystemService("user");
            k3.q.g(systemService);
            Bundle applicationRestrictions = ((UserManager) systemService).getApplicationRestrictions(context.getPackageName());
            return applicationRestrictions != null && "true".equals(applicationRestrictions.getString("restricted_profile"));
        }
        return false;
    }

    public static boolean g(int i8) {
        return i8 == 1 || i8 == 2 || i8 == 3 || i8 == 9;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean h(Context context, String str) {
        ApplicationInfo applicationInfo;
        boolean equals = str.equals("com.google.android.gms");
        if (q3.g.e()) {
            try {
                for (PackageInstaller.SessionInfo sessionInfo : context.getPackageManager().getPackageInstaller().getAllSessions()) {
                    if (str.equals(sessionInfo.getAppPackageName())) {
                        return true;
                    }
                }
            } catch (Exception unused) {
                return false;
            }
        }
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(str, 8192);
        } catch (PackageManager.NameNotFoundException unused2) {
        }
        return equals ? applicationInfo.enabled : applicationInfo.enabled && !f(context);
    }
}
