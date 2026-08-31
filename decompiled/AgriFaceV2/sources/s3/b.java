package s3;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    protected final Context f11559a;

    public b(Context context) {
        this.f11559a = context;
    }

    public ApplicationInfo a(String str, int i8) {
        return this.f11559a.getPackageManager().getApplicationInfo(str, i8);
    }

    public CharSequence b(String str) {
        Context context = this.f11559a;
        return context.getPackageManager().getApplicationLabel(context.getPackageManager().getApplicationInfo(str, 0));
    }

    public PackageInfo c(String str, int i8) {
        return this.f11559a.getPackageManager().getPackageInfo(str, i8);
    }
}
