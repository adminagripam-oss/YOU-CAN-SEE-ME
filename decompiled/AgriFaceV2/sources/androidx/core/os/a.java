package androidx.core.os;

import android.os.Build;
import android.os.ext.SdkExtensions;
import java.util.Locale;
/* loaded from: classes.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public static final a f3038a = new a();

    /* renamed from: b  reason: collision with root package name */
    public static final int f3039b;

    /* renamed from: c  reason: collision with root package name */
    public static final int f3040c;

    /* renamed from: d  reason: collision with root package name */
    public static final int f3041d;

    /* renamed from: e  reason: collision with root package name */
    public static final int f3042e;

    /* renamed from: androidx.core.os.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private static final class C0028a {

        /* renamed from: a  reason: collision with root package name */
        public static final C0028a f3043a = new C0028a();

        private C0028a() {
        }

        public final int a(int i8) {
            return SdkExtensions.getExtensionVersion(i8);
        }
    }

    static {
        int i8 = Build.VERSION.SDK_INT;
        f3039b = i8 >= 30 ? C0028a.f3043a.a(30) : 0;
        f3040c = i8 >= 30 ? C0028a.f3043a.a(31) : 0;
        f3041d = i8 >= 30 ? C0028a.f3043a.a(33) : 0;
        f3042e = i8 >= 30 ? C0028a.f3043a.a(1000000) : 0;
    }

    private a() {
    }

    public static final boolean a(String str, String str2) {
        d7.k.e(str, "codename");
        d7.k.e(str2, "buildCodename");
        if (d7.k.a("REL", str2)) {
            return false;
        }
        Locale locale = Locale.ROOT;
        String upperCase = str2.toUpperCase(locale);
        d7.k.d(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
        String upperCase2 = str.toUpperCase(locale);
        d7.k.d(upperCase2, "this as java.lang.String).toUpperCase(Locale.ROOT)");
        return upperCase.compareTo(upperCase2) >= 0;
    }

    public static final boolean b() {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 < 33) {
            if (i8 >= 32) {
                String str = Build.VERSION.CODENAME;
                d7.k.d(str, "CODENAME");
                if (a("Tiramisu", str)) {
                }
            }
            return false;
        }
        return true;
    }
}
