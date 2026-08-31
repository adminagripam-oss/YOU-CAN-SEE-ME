package androidx.webkit.internal;

import android.webkit.WebView;
import android.webkit.WebViewRenderProcess;
import android.webkit.WebViewRenderProcessClient;
/* loaded from: classes.dex */
public class m1 extends WebViewRenderProcessClient {

    /* renamed from: a  reason: collision with root package name */
    private c2.v f4068a;

    public m1(c2.v vVar) {
        this.f4068a = vVar;
    }

    public void onRenderProcessResponsive(WebView webView, WebViewRenderProcess webViewRenderProcess) {
        this.f4068a.onRenderProcessResponsive(webView, o1.b(webViewRenderProcess));
    }

    public void onRenderProcessUnresponsive(WebView webView, WebViewRenderProcess webViewRenderProcess) {
        this.f4068a.onRenderProcessUnresponsive(webView, o1.b(webViewRenderProcess));
    }
}
