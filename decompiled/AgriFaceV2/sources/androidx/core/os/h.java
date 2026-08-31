package androidx.core.os;

import android.os.LocaleList;
import java.util.Locale;
/* loaded from: classes.dex */
public final class h {

    /* renamed from: b  reason: collision with root package name */
    private static final h f3049b = a(new Locale[0]);

    /* renamed from: a  reason: collision with root package name */
    private final i f3050a;

    /* loaded from: classes.dex */
    static class a {

        /* renamed from: a  reason: collision with root package name */
        private static final Locale[] f3051a = {new Locale("en", "XA"), new Locale("ar", "XB")};

        static Locale a(String str) {
            return Locale.forLanguageTag(str);
        }

        private static boolean b(Locale locale) {
            for (Locale locale2 : f3051a) {
                if (locale2.equals(locale)) {
                    return true;
                }
            }
            return false;
        }

        static boolean c(Locale locale, Locale locale2) {
            if (locale.equals(locale2)) {
                return true;
            }
            if (!locale.getLanguage().equals(locale2.getLanguage()) || b(locale) || b(locale2)) {
                return false;
            }
            String a8 = androidx.core.text.a.a(locale);
            if (a8.isEmpty()) {
                String country = locale.getCountry();
                return country.isEmpty() || country.equals(locale2.getCountry());
            }
            return a8.equals(androidx.core.text.a.a(locale2));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b {
        static LocaleList a(Locale... localeArr) {
            return new LocaleList(localeArr);
        }

        static LocaleList b() {
            return LocaleList.getAdjustedDefault();
        }

        static LocaleList c() {
            return LocaleList.getDefault();
        }
    }

    private h(i iVar) {
        this.f3050a = iVar;
    }

    public static h a(Locale... localeArr) {
        return i(b.a(localeArr));
    }

    public static h b(String str) {
        if (str == null || str.isEmpty()) {
            return d();
        }
        String[] split = str.split(",", -1);
        int length = split.length;
        Locale[] localeArr = new Locale[length];
        for (int i8 = 0; i8 < length; i8++) {
            localeArr[i8] = a.a(split[i8]);
        }
        return a(localeArr);
    }

    public static h d() {
        return f3049b;
    }

    public static h i(LocaleList localeList) {
        return new h(new j(localeList));
    }

    public Locale c(int i8) {
        return this.f3050a.get(i8);
    }

    public boolean e() {
        return this.f3050a.isEmpty();
    }

    public boolean equals(Object obj) {
        return (obj instanceof h) && this.f3050a.equals(((h) obj).f3050a);
    }

    public int f() {
        return this.f3050a.size();
    }

    public String g() {
        return this.f3050a.a();
    }

    public Object h() {
        return this.f3050a.b();
    }

    public int hashCode() {
        return this.f3050a.hashCode();
    }

    public String toString() {
        return this.f3050a.toString();
    }
}
