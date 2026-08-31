package j6;

import android.content.Context;
import android.content.res.Configuration;
import android.os.LocaleList;
import h6.l;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private final l f8280a;

    /* renamed from: b  reason: collision with root package name */
    private final Context f8281b;

    /* renamed from: c  reason: collision with root package name */
    final l.b f8282c;

    /* renamed from: j6.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0111a implements l.b {
        C0111a() {
        }

        @Override // h6.l.b
        public String a(String str, String str2) {
            Context context = a.this.f8281b;
            if (str2 != null) {
                Locale b8 = a.b(str2);
                Configuration configuration = new Configuration(a.this.f8281b.getResources().getConfiguration());
                configuration.setLocale(b8);
                context = a.this.f8281b.createConfigurationContext(configuration);
            }
            int identifier = context.getResources().getIdentifier(str, "string", a.this.f8281b.getPackageName());
            if (identifier != 0) {
                return context.getResources().getString(identifier);
            }
            return null;
        }
    }

    public a(Context context, l lVar) {
        C0111a c0111a = new C0111a();
        this.f8282c = c0111a;
        this.f8281b = context;
        this.f8280a = lVar;
        lVar.c(c0111a);
    }

    public static Locale b(String str) {
        String str2;
        String[] split = str.replace('_', '-').split("-", -1);
        String str3 = split[0];
        String str4 = "";
        int i8 = 1;
        if (split.length <= 1 || split[1].length() != 4) {
            str2 = "";
        } else {
            str2 = split[1];
            i8 = 2;
        }
        if (split.length > i8 && split[i8].length() >= 2 && split[i8].length() <= 3) {
            str4 = split[i8];
        }
        return new Locale(str3, str4, str2);
    }

    public Locale c(List list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        LocaleList locales = this.f8281b.getResources().getConfiguration().getLocales();
        int size = locales.size();
        for (int i8 = 0; i8 < size; i8++) {
            Locale locale = locales.get(i8);
            String language = locale.getLanguage();
            if (!locale.getScript().isEmpty()) {
                language = language + "-" + locale.getScript();
            }
            if (!locale.getCountry().isEmpty()) {
                language = language + "-" + locale.getCountry();
            }
            arrayList.add(new Locale.LanguageRange(language));
            arrayList.add(new Locale.LanguageRange(locale.getLanguage()));
            arrayList.add(new Locale.LanguageRange(locale.getLanguage() + "-*"));
        }
        Locale lookup = Locale.lookup(arrayList, list);
        return lookup != null ? lookup : (Locale) list.get(0);
    }

    public void d(Configuration configuration) {
        ArrayList arrayList = new ArrayList();
        LocaleList locales = configuration.getLocales();
        int size = locales.size();
        for (int i8 = 0; i8 < size; i8++) {
            arrayList.add(locales.get(i8));
        }
        this.f8280a.b(arrayList);
    }
}
