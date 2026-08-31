package k3;

import android.util.Log;
/* loaded from: classes.dex */
public final class j {

    /* renamed from: a  reason: collision with root package name */
    private final String f8426a;

    /* renamed from: b  reason: collision with root package name */
    private final String f8427b;

    public j(String str, String str2) {
        q.h(str, "log tag cannot be null");
        q.c(str.length() <= 23, "tag \"%s\" is longer than the %d character maximum", str, 23);
        this.f8426a = str;
        this.f8427b = (str2 == null || str2.length() <= 0) ? null : str2;
    }

    private final String g(String str) {
        String str2 = this.f8427b;
        return str2 == null ? str : str2.concat(str);
    }

    public boolean a(int i8) {
        return Log.isLoggable(this.f8426a, i8);
    }

    public void b(String str, String str2) {
        if (a(3)) {
            Log.d(str, g(str2));
        }
    }

    public void c(String str, String str2) {
        if (a(6)) {
            Log.e(str, g(str2));
        }
    }

    public void d(String str, String str2, Throwable th) {
        if (a(6)) {
            Log.e(str, g(str2), th);
        }
    }

    public void e(String str, String str2) {
        if (a(2)) {
            Log.v(str, g(str2));
        }
    }

    public void f(String str, String str2) {
        if (a(5)) {
            Log.w(str, g(str2));
        }
    }
}
