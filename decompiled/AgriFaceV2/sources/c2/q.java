package c2;

import android.webkit.WebSettings;
import androidx.webkit.internal.a;
import androidx.webkit.internal.f0;
import androidx.webkit.internal.f1;
import androidx.webkit.internal.g1;
import androidx.webkit.internal.h1;
import java.util.Set;
/* loaded from: classes.dex */
public abstract class q {
    private static f1 a(WebSettings webSettings) {
        return h1.c().f(webSettings);
    }

    public static int b(WebSettings webSettings) {
        a.c cVar = g1.f4023d;
        if (cVar.c()) {
            return androidx.webkit.internal.c.f(webSettings);
        }
        if (cVar.d()) {
            return a(webSettings).a();
        }
        throw g1.a();
    }

    public static boolean c(WebSettings webSettings) {
        if (g1.Y.d()) {
            return a(webSettings).b();
        }
        throw g1.a();
    }

    public static int d(WebSettings webSettings) {
        a.h hVar = g1.S;
        if (hVar.c()) {
            return f0.a(webSettings);
        }
        if (hVar.d()) {
            return a(webSettings).c();
        }
        throw g1.a();
    }

    public static int e(WebSettings webSettings) {
        if (g1.T.d()) {
            return a(webSettings).c();
        }
        throw g1.a();
    }

    public static boolean f(WebSettings webSettings) {
        a.b bVar = g1.f4019b;
        if (bVar.c()) {
            return androidx.webkit.internal.b.g(webSettings);
        }
        if (bVar.d()) {
            return a(webSettings).d();
        }
        throw g1.a();
    }

    public static Set g(WebSettings webSettings) {
        if (g1.f4018a0.d()) {
            return a(webSettings).e();
        }
        throw g1.a();
    }

    public static boolean h(WebSettings webSettings) {
        a.e eVar = g1.f4021c;
        if (eVar.c()) {
            return androidx.webkit.internal.d.b(webSettings);
        }
        if (eVar.d()) {
            return a(webSettings).f();
        }
        throw g1.a();
    }

    public static boolean i(WebSettings webSettings) {
        if (g1.P.d()) {
            return a(webSettings).g();
        }
        throw g1.a();
    }

    public static void j(WebSettings webSettings, boolean z7) {
        if (!g1.P.d()) {
            throw g1.a();
        }
        a(webSettings).h(z7);
    }

    public static void k(WebSettings webSettings, int i8) {
        a.c cVar = g1.f4023d;
        if (cVar.c()) {
            androidx.webkit.internal.c.o(webSettings, i8);
        } else if (!cVar.d()) {
            throw g1.a();
        } else {
            a(webSettings).i(i8);
        }
    }

    public static void l(WebSettings webSettings, boolean z7) {
        if (!g1.Y.d()) {
            throw g1.a();
        }
        a(webSettings).j(z7);
    }

    public static void m(WebSettings webSettings, int i8) {
        a.h hVar = g1.S;
        if (hVar.c()) {
            f0.d(webSettings, i8);
        } else if (!hVar.d()) {
            throw g1.a();
        } else {
            a(webSettings).k(i8);
        }
    }

    public static void n(WebSettings webSettings, int i8) {
        if (!g1.T.d()) {
            throw g1.a();
        }
        a(webSettings).l(i8);
    }

    public static void o(WebSettings webSettings, boolean z7) {
        a.b bVar = g1.f4019b;
        if (bVar.c()) {
            androidx.webkit.internal.b.k(webSettings, z7);
        } else if (!bVar.d()) {
            throw g1.a();
        } else {
            a(webSettings).m(z7);
        }
    }

    public static void p(WebSettings webSettings, Set set) {
        if (!g1.f4018a0.d()) {
            throw g1.a();
        }
        a(webSettings).n(set);
    }

    public static void q(WebSettings webSettings, boolean z7) {
        a.e eVar = g1.f4021c;
        if (eVar.c()) {
            androidx.webkit.internal.d.e(webSettings, z7);
        } else if (!eVar.d()) {
            throw g1.a();
        } else {
            a(webSettings).o(z7);
        }
    }
}
