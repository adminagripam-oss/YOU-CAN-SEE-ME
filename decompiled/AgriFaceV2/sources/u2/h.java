package u2;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import u2.a;
/* loaded from: classes.dex */
public abstract class h {

    /* loaded from: classes.dex */
    public static abstract class a {
        public final a a(String str, int i8) {
            e().put(str, String.valueOf(i8));
            return this;
        }

        public final a b(String str, long j8) {
            e().put(str, String.valueOf(j8));
            return this;
        }

        public final a c(String str, String str2) {
            e().put(str, str2);
            return this;
        }

        public abstract h d();

        protected abstract Map e();

        protected abstract a f(Map map);

        public abstract a g(Integer num);

        public abstract a h(g gVar);

        public abstract a i(long j8);

        public abstract a j(String str);

        public abstract a k(long j8);
    }

    public static a a() {
        return new a.b().f(new HashMap());
    }

    public final String b(String str) {
        String str2 = (String) c().get(str);
        return str2 == null ? "" : str2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Map c();

    public abstract Integer d();

    public abstract g e();

    public abstract long f();

    public final int g(String str) {
        String str2 = (String) c().get(str);
        if (str2 == null) {
            return 0;
        }
        return Integer.valueOf(str2).intValue();
    }

    public final long h(String str) {
        String str2 = (String) c().get(str);
        if (str2 == null) {
            return 0L;
        }
        return Long.valueOf(str2).longValue();
    }

    public final Map i() {
        return Collections.unmodifiableMap(c());
    }

    public abstract String j();

    public abstract long k();

    public a l() {
        return new a.b().j(j()).g(d()).h(e()).i(f()).k(k()).f(new HashMap(c()));
    }
}
