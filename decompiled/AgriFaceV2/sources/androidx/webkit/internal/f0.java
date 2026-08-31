package androidx.webkit.internal;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewRenderProcess;
import android.webkit.WebViewRenderProcessClient;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public abstract class f0 {
    @Deprecated
    public static int a(WebSettings webSettings) {
        int forceDark;
        forceDark = webSettings.getForceDark();
        return forceDark;
    }

    public static WebViewRenderProcess b(WebView webView) {
        WebViewRenderProcess webViewRenderProcess;
        webViewRenderProcess = webView.getWebViewRenderProcess();
        return webViewRenderProcess;
    }

    public static WebViewRenderProcessClient c(WebView webView) {
        WebViewRenderProcessClient webViewRenderProcessClient;
        webViewRenderProcessClient = webView.getWebViewRenderProcessClient();
        return webViewRenderProcessClient;
    }

    @Deprecated
    public static void d(WebSettings webSettings, int i8) {
        webSettings.setForceDark(i8);
    }

    public static void e(WebView webView, c2.v vVar) {
        webView.setWebViewRenderProcessClient(vVar != null ? new m1(vVar) : null);
    }

    public static void f(WebView webView, Executor executor, c2.v vVar) {
        webView.setWebViewRenderProcessClient(executor, vVar != null ? new m1(vVar) : null);
    }

    public static boolean g(WebViewRenderProcess webViewRenderProcess) {
        boolean terminate;
        terminate = webViewRenderProcess.terminate();
        return terminate;
    }
}
