package androidx.appcompat.view;

import android.content.Context;
import android.content.res.Configuration;
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private Context f1712a;

    private a(Context context) {
        this.f1712a = context;
    }

    public static a b(Context context) {
        return new a(context);
    }

    public boolean a() {
        return this.f1712a.getApplicationInfo().targetSdkVersion < 14;
    }

    public int c() {
        return this.f1712a.getResources().getDisplayMetrics().widthPixels / 2;
    }

    public int d() {
        Configuration configuration = this.f1712a.getResources().getConfiguration();
        int i8 = configuration.screenWidthDp;
        int i9 = configuration.screenHeightDp;
        if (configuration.smallestScreenWidthDp > 600 || i8 > 600) {
            return 5;
        }
        if (i8 <= 960 || i9 <= 720) {
            if (i8 <= 720 || i9 <= 960) {
                if (i8 < 500) {
                    if (i8 <= 640 || i9 <= 480) {
                        if (i8 <= 480 || i9 <= 640) {
                            return i8 >= 360 ? 3 : 2;
                        }
                        return 4;
                    }
                    return 4;
                }
                return 4;
            }
            return 5;
        }
        return 5;
    }

    public boolean e() {
        return this.f1712a.getResources().getBoolean(g.b.f6818a);
    }

    public boolean f() {
        return true;
    }
}
