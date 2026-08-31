package androidx.webkit.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Bundle;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public abstract class w0 {

    /* renamed from: c  reason: collision with root package name */
    private static final Set f4087c = new HashSet();

    /* renamed from: a  reason: collision with root package name */
    private final String f4088a;

    /* renamed from: b  reason: collision with root package name */
    private final String f4089b;

    /* loaded from: classes.dex */
    public static class a extends w0 {
        /* JADX INFO: Access modifiers changed from: package-private */
        public a(String str, String str2) {
            super(str, str2);
        }

        @Override // androidx.webkit.internal.w0
        public final boolean e() {
            return false;
        }
    }

    /* loaded from: classes.dex */
    public static class b extends w0 {
        /* JADX INFO: Access modifiers changed from: package-private */
        public b(String str, String str2) {
            super(str, str2);
        }

        @Override // androidx.webkit.internal.w0
        public final boolean e() {
            return Build.VERSION.SDK_INT >= 28;
        }
    }

    w0(String str, String str2) {
        this.f4088a = str;
        this.f4089b = str2;
        f4087c.add(this);
    }

    private static Bundle a(Context context) {
        PackageInfo f8 = c2.s.f(context);
        if (f8 == null) {
            return null;
        }
        ComponentName componentName = new ComponentName(f8.packageName, "org.chromium.android_webview.services.StartupFeatureMetadataHolder");
        if (Build.VERSION.SDK_INT < 33) {
            try {
                return c(context, componentName, 640).metaData;
            } catch (PackageManager.NameNotFoundException unused) {
                return null;
            }
        }
        try {
            return i0.a(context.getPackageManager(), componentName, i0.b(640L)).metaData;
        } catch (PackageManager.NameNotFoundException unused2) {
            return null;
        }
    }

    private static ServiceInfo c(Context context, ComponentName componentName, int i8) {
        return context.getPackageManager().getServiceInfo(componentName, i8);
    }

    public static Set g() {
        return Collections.unmodifiableSet(f4087c);
    }

    public String b() {
        return this.f4088a;
    }

    public boolean d(Context context) {
        return e() || f(context);
    }

    public abstract boolean e();

    public boolean f(Context context) {
        Bundle a8 = a(context);
        if (a8 == null) {
            return false;
        }
        return a8.containsKey(this.f4089b);
    }
}
