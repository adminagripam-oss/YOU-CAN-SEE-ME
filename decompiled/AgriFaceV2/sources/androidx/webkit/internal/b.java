package androidx.webkit.internal;

import android.net.Uri;
import android.os.Handler;
import android.webkit.WebMessage;
import android.webkit.WebMessagePort;
import android.webkit.WebResourceError;
import android.webkit.WebSettings;
import android.webkit.WebView;
import c2.n;
import c2.s;
/* loaded from: classes.dex */
public abstract class b {

    /* loaded from: classes.dex */
    class a extends WebMessagePort.WebMessageCallback {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ n.a f4006a;

        a(n.a aVar) {
            this.f4006a = aVar;
        }

        @Override // android.webkit.WebMessagePort.WebMessageCallback
        public void onMessage(WebMessagePort webMessagePort, WebMessage webMessage) {
            this.f4006a.onMessage(new c1(webMessagePort), c1.h(webMessage));
        }
    }

    /* renamed from: androidx.webkit.internal.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0053b extends WebMessagePort.WebMessageCallback {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ n.a f4007a;

        C0053b(n.a aVar) {
            this.f4007a = aVar;
        }

        @Override // android.webkit.WebMessagePort.WebMessageCallback
        public void onMessage(WebMessagePort webMessagePort, WebMessage webMessage) {
            this.f4007a.onMessage(new c1(webMessagePort), c1.h(webMessage));
        }
    }

    /* loaded from: classes.dex */
    class c extends WebView.VisualStateCallback {
        c(s.a aVar) {
        }

        @Override // android.webkit.WebView.VisualStateCallback
        public void onComplete(long j8) {
            throw null;
        }
    }

    public static void a(WebMessagePort webMessagePort) {
        webMessagePort.close();
    }

    public static WebMessage b(c2.m mVar) {
        return new WebMessage(mVar.c(), c1.g(mVar.d()));
    }

    public static WebMessagePort[] c(WebView webView) {
        return webView.createWebMessageChannel();
    }

    public static c2.m d(WebMessage webMessage) {
        return new c2.m(webMessage.getData(), c1.k(webMessage.getPorts()));
    }

    public static CharSequence e(WebResourceError webResourceError) {
        return webResourceError.getDescription();
    }

    public static int f(WebResourceError webResourceError) {
        return webResourceError.getErrorCode();
    }

    public static boolean g(WebSettings webSettings) {
        return webSettings.getOffscreenPreRaster();
    }

    public static void h(WebMessagePort webMessagePort, WebMessage webMessage) {
        webMessagePort.postMessage(webMessage);
    }

    public static void i(WebView webView, long j8, s.a aVar) {
        webView.postVisualStateCallback(j8, new c(aVar));
    }

    public static void j(WebView webView, WebMessage webMessage, Uri uri) {
        webView.postWebMessage(webMessage, uri);
    }

    public static void k(WebSettings webSettings, boolean z7) {
        webSettings.setOffscreenPreRaster(z7);
    }

    public static void l(WebMessagePort webMessagePort, n.a aVar) {
        webMessagePort.setWebMessageCallback(new a(aVar));
    }

    public static void m(WebMessagePort webMessagePort, n.a aVar, Handler handler) {
        webMessagePort.setWebMessageCallback(new C0053b(aVar), handler);
    }
}
