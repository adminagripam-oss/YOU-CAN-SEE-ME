package c2;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import androidx.webkit.internal.a;
import androidx.webkit.internal.c1;
import androidx.webkit.internal.f0;
import androidx.webkit.internal.g1;
import androidx.webkit.internal.h1;
import androidx.webkit.internal.i1;
import androidx.webkit.internal.j1;
import androidx.webkit.internal.y0;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.chromium.support_lib_boundary.WebViewProviderBoundaryInterface;
/* loaded from: classes.dex */
public abstract class s {

    /* renamed from: a  reason: collision with root package name */
    private static final Uri f4780a = Uri.parse("*");

    /* renamed from: b  reason: collision with root package name */
    private static final Uri f4781b = Uri.parse("");

    /* loaded from: classes.dex */
    public interface a {
    }

    /* loaded from: classes.dex */
    public interface b {
        void onPostMessage(WebView webView, m mVar, Uri uri, boolean z7, c2.b bVar);
    }

    public static g a(WebView webView, String str, Set set) {
        if (g1.V.d()) {
            return i(webView).a(str, (String[]) set.toArray(new String[0]));
        }
        throw g1.a();
    }

    public static void b(WebView webView, String str, Set set, b bVar) {
        if (!g1.U.d()) {
            throw g1.a();
        }
        i(webView).b(str, (String[]) set.toArray(new String[0]), bVar);
    }

    private static WebViewProviderBoundaryInterface c(WebView webView) {
        return g().createWebView(webView);
    }

    public static n[] d(WebView webView) {
        a.b bVar = g1.E;
        if (bVar.c()) {
            return c1.k(androidx.webkit.internal.b.c(webView));
        }
        if (bVar.d()) {
            return i(webView).c();
        }
        throw g1.a();
    }

    public static PackageInfo e() {
        return androidx.webkit.internal.d.a();
    }

    public static PackageInfo f(Context context) {
        PackageInfo e8 = e();
        return e8 != null ? e8 : h(context);
    }

    private static j1 g() {
        return h1.d();
    }

    private static PackageInfo h(Context context) {
        try {
            String str = (String) Class.forName("android.webkit.WebViewUpdateService").getMethod("getCurrentWebViewPackageName", new Class[0]).invoke(null, new Object[0]);
            if (str == null) {
                return null;
            }
            return context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException | ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return null;
        }
    }

    private static i1 i(WebView webView) {
        return new i1(c(webView));
    }

    public static Uri j() {
        a.f fVar = g1.f4032j;
        if (fVar.c()) {
            return androidx.webkit.internal.k.b();
        }
        if (fVar.d()) {
            return g().getStatics().getSafeBrowsingPrivacyPolicyUrl();
        }
        throw g1.a();
    }

    public static String k() {
        if (g1.X.d()) {
            return g().getStatics().getVariationsHeader();
        }
        throw g1.a();
    }

    public static boolean l() {
        if (g1.R.d()) {
            return g().getStatics().isMultiProcessEnabled();
        }
        throw g1.a();
    }

    public static void m(WebView webView, m mVar, Uri uri) {
        if (f4780a.equals(uri)) {
            uri = f4781b;
        }
        a.b bVar = g1.F;
        if (bVar.c() && mVar.e() == 0) {
            androidx.webkit.internal.b.j(webView, c1.f(mVar), uri);
        } else if (!bVar.d() || !y0.a(mVar.e())) {
            throw g1.a();
        } else {
            i(webView).d(mVar, uri);
        }
    }

    public static void n(Set set, ValueCallback valueCallback) {
        a.f fVar = g1.f4031i;
        a.f fVar2 = g1.f4030h;
        if (fVar.d()) {
            g().getStatics().setSafeBrowsingAllowlist(set, valueCallback);
            return;
        }
        ArrayList arrayList = new ArrayList(set);
        if (fVar2.c()) {
            androidx.webkit.internal.k.d(arrayList, valueCallback);
        } else if (!fVar2.d()) {
            throw g1.a();
        } else {
            g().getStatics().setSafeBrowsingWhitelist(arrayList, valueCallback);
        }
    }

    public static void o(List list, ValueCallback valueCallback) {
        n(new HashSet(list), valueCallback);
    }

    public static void p(WebView webView, v vVar) {
        a.h hVar = g1.O;
        if (hVar.c()) {
            f0.e(webView, vVar);
        } else if (!hVar.d()) {
            throw g1.a();
        } else {
            i(webView).e(null, vVar);
        }
    }

    public static void q(Context context, ValueCallback valueCallback) {
        a.f fVar = g1.f4025e;
        if (fVar.c()) {
            androidx.webkit.internal.k.f(context, valueCallback);
        } else if (!fVar.d()) {
            throw g1.a();
        } else {
            g().getStatics().initSafeBrowsing(context, valueCallback);
        }
    }
}
