package androidx.core.os;

import android.content.res.Configuration;
import android.os.LocaleList;
/* loaded from: classes.dex */
public abstract class e {

    /* loaded from: classes.dex */
    static class a {
        static LocaleList a(Configuration configuration) {
            return configuration.getLocales();
        }

        static void b(Configuration configuration, h hVar) {
            configuration.setLocales((LocaleList) hVar.h());
        }
    }

    public static h a(Configuration configuration) {
        return h.i(a.a(configuration));
    }
}
