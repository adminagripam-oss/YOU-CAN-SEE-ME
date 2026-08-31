package androidx.webkit.internal;

import android.webkit.CookieManager;
import android.webkit.SafeBrowsingResponse;
import android.webkit.ServiceWorkerWebSettings;
import android.webkit.WebMessagePort;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import java.lang.reflect.InvocationHandler;
import org.chromium.support_lib_boundary.WebResourceRequestBoundaryInterface;
import org.chromium.support_lib_boundary.WebSettingsBoundaryInterface;
import org.chromium.support_lib_boundary.WebViewCookieManagerBoundaryInterface;
import org.chromium.support_lib_boundary.WebkitToCompatConverterBoundaryInterface;
/* loaded from: classes.dex */
public class q1 {

    /* renamed from: a  reason: collision with root package name */
    private final WebkitToCompatConverterBoundaryInterface f4077a;

    public q1(WebkitToCompatConverterBoundaryInterface webkitToCompatConverterBoundaryInterface) {
        this.f4077a = webkitToCompatConverterBoundaryInterface;
    }

    public l0 a(CookieManager cookieManager) {
        return new l0((WebViewCookieManagerBoundaryInterface) t7.a.a(WebViewCookieManagerBoundaryInterface.class, this.f4077a.convertCookieManager(cookieManager)));
    }

    public SafeBrowsingResponse b(InvocationHandler invocationHandler) {
        return p1.a(this.f4077a.convertSafeBrowsingResponse(invocationHandler));
    }

    public InvocationHandler c(SafeBrowsingResponse safeBrowsingResponse) {
        return this.f4077a.convertSafeBrowsingResponse(safeBrowsingResponse);
    }

    public ServiceWorkerWebSettings d(InvocationHandler invocationHandler) {
        return (ServiceWorkerWebSettings) this.f4077a.convertServiceWorkerSettings(invocationHandler);
    }

    public InvocationHandler e(ServiceWorkerWebSettings serviceWorkerWebSettings) {
        return this.f4077a.convertServiceWorkerSettings(serviceWorkerWebSettings);
    }

    public f1 f(WebSettings webSettings) {
        return new f1((WebSettingsBoundaryInterface) t7.a.a(WebSettingsBoundaryInterface.class, this.f4077a.convertSettings(webSettings)));
    }

    public WebMessagePort g(InvocationHandler invocationHandler) {
        return (WebMessagePort) this.f4077a.convertWebMessagePort(invocationHandler);
    }

    public InvocationHandler h(WebMessagePort webMessagePort) {
        return this.f4077a.convertWebMessagePort(webMessagePort);
    }

    public WebResourceError i(InvocationHandler invocationHandler) {
        return (WebResourceError) this.f4077a.convertWebResourceError(invocationHandler);
    }

    public InvocationHandler j(WebResourceError webResourceError) {
        return this.f4077a.convertWebResourceError(webResourceError);
    }

    public e1 k(WebResourceRequest webResourceRequest) {
        return new e1((WebResourceRequestBoundaryInterface) t7.a.a(WebResourceRequestBoundaryInterface.class, this.f4077a.convertWebResourceRequest(webResourceRequest)));
    }
}
