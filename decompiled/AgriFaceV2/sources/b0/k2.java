package b0;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import b0.i2;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public class k2 implements m.a {

    /* loaded from: classes.dex */
    public static class a extends Service {
    }

    private static i2 b(Context context, Bundle bundle) {
        boolean z7 = bundle.getBoolean("androidx.camera.core.quirks.DEFAULT_QUIRK_ENABLED", true);
        String[] c8 = c(context, bundle, "androidx.camera.core.quirks.FORCE_ENABLED");
        String[] c9 = c(context, bundle, "androidx.camera.core.quirks.FORCE_DISABLED");
        y.f1.a("QuirkSettingsLoader", "Loaded quirk settings from metadata:");
        y.f1.a("QuirkSettingsLoader", "  KEY_DEFAULT_QUIRK_ENABLED = " + z7);
        y.f1.a("QuirkSettingsLoader", "  KEY_QUIRK_FORCE_ENABLED = " + Arrays.toString(c8));
        y.f1.a("QuirkSettingsLoader", "  KEY_QUIRK_FORCE_DISABLED = " + Arrays.toString(c9));
        return new i2.b().d(z7).c(e(c8)).b(e(c9)).a();
    }

    private static String[] c(Context context, Bundle bundle, String str) {
        if (bundle.containsKey(str)) {
            int i8 = bundle.getInt(str, -1);
            if (i8 == -1) {
                y.f1.l("QuirkSettingsLoader", "Resource ID not found for key: " + str);
            } else {
                try {
                    return context.getResources().getStringArray(i8);
                } catch (Resources.NotFoundException e8) {
                    y.f1.m("QuirkSettingsLoader", "Quirk class names resource not found: " + i8, e8);
                }
            }
            return new String[0];
        }
        return new String[0];
    }

    private static Class d(String str) {
        try {
            Class<?> cls = Class.forName(str);
            if (h2.class.isAssignableFrom(cls)) {
                return cls;
            }
            y.f1.l("QuirkSettingsLoader", str + " does not implement the Quirk interface.");
            return null;
        } catch (ClassNotFoundException e8) {
            y.f1.m("QuirkSettingsLoader", "Class not found: " + str, e8);
            return null;
        }
    }

    private static Set e(String[] strArr) {
        HashSet hashSet = new HashSet();
        for (String str : strArr) {
            Class d8 = d(str);
            if (d8 != null) {
                hashSet.add(d8);
            }
        }
        return hashSet;
    }

    @Override // m.a
    /* renamed from: a */
    public i2 apply(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getServiceInfo(new ComponentName(context, a.class), 640).metaData;
            if (bundle == null) {
                y.f1.l("QuirkSettingsLoader", "No metadata in MetadataHolderService.");
                return null;
            }
            return b(context, bundle);
        } catch (PackageManager.NameNotFoundException unused) {
            y.f1.a("QuirkSettingsLoader", "QuirkSettings$MetadataHolderService is not found.");
            return null;
        }
    }
}
