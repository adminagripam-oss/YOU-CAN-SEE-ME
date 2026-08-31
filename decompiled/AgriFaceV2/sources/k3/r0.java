package k3;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
/* loaded from: classes.dex */
public abstract class r0 {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f8469a = new Object();

    /* renamed from: b  reason: collision with root package name */
    private static boolean f8470b;

    /* renamed from: c  reason: collision with root package name */
    private static String f8471c;

    /* renamed from: d  reason: collision with root package name */
    private static int f8472d;

    public static int a(Context context) {
        b(context);
        return f8472d;
    }

    private static void b(Context context) {
        Bundle bundle;
        synchronized (f8469a) {
            if (f8470b) {
                return;
            }
            f8470b = true;
            try {
                bundle = s3.c.a(context).a(context.getPackageName(), 128).metaData;
            } catch (PackageManager.NameNotFoundException e8) {
                Log.wtf("MetadataValueReader", "This should never happen.", e8);
            }
            if (bundle == null) {
                return;
            }
            f8471c = bundle.getString("com.google.app.id");
            f8472d = bundle.getInt("com.google.android.gms.version");
        }
    }
}
