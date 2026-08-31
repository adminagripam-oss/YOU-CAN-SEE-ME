package u5;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;
/* loaded from: classes.dex */
public class y {

    /* renamed from: a  reason: collision with root package name */
    private final SharedPreferences f12271a;

    /* renamed from: b  reason: collision with root package name */
    private final SharedPreferences f12272b;

    public y(Context context, String str) {
        this.f12271a = context.getSharedPreferences(e(str), 0);
        this.f12272b = context.getSharedPreferences("FlutterSecureStorageConfiguration", 0);
    }

    private static String e(String str) {
        return "FlutterSecureStorageConfiguration:" + str;
    }

    public boolean a(String str) {
        return this.f12271a.contains(str) || this.f12272b.contains(str);
    }

    public SharedPreferences.Editor b() {
        return this.f12271a.edit();
    }

    public Map c() {
        return this.f12271a.getAll();
    }

    public boolean d(String str, boolean z7) {
        return (this.f12271a.contains(str) ? this.f12271a : this.f12272b).getBoolean(str, z7);
    }

    public String f(String str, String str2) {
        String string = this.f12271a.getString(str, null);
        return string != null ? string : this.f12272b.getString(str, str2);
    }
}
