package d0;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import java.util.Objects;
/* loaded from: classes.dex */
public abstract class f {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a {
        static Context a(Context context, String str) {
            return context.createAttributionContext(str);
        }

        static String b(Context context) {
            return context.getAttributionTag();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b {
        static Context a(Context context, int i8) {
            return context.createDeviceContext(i8);
        }

        static int b(Context context) {
            return context.getDeviceId();
        }
    }

    public static Context a(Context context) {
        int b8;
        Context applicationContext = context.getApplicationContext();
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 34 && (b8 = b.b(context)) != b.b(applicationContext)) {
            applicationContext = b.a(applicationContext, b8);
        }
        if (i8 >= 30) {
            String b9 = a.b(context);
            return !Objects.equals(b9, a.b(applicationContext)) ? a.a(applicationContext, b9) : applicationContext;
        }
        return applicationContext;
    }

    public static Application b(Context context) {
        for (Context a8 = a(context); a8 instanceof ContextWrapper; a8 = ((ContextWrapper) a8).getBaseContext()) {
            if (a8 instanceof Application) {
                return (Application) a8;
            }
        }
        return null;
    }
}
