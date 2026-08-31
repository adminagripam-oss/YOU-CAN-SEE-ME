package androidx.webkit.internal;

import android.webkit.WebView;
import org.chromium.support_lib_boundary.ProxyControllerBoundaryInterface;
import org.chromium.support_lib_boundary.ServiceWorkerControllerBoundaryInterface;
import org.chromium.support_lib_boundary.StaticsBoundaryInterface;
import org.chromium.support_lib_boundary.TracingControllerBoundaryInterface;
import org.chromium.support_lib_boundary.WebViewProviderBoundaryInterface;
import org.chromium.support_lib_boundary.WebViewProviderFactoryBoundaryInterface;
import org.chromium.support_lib_boundary.WebkitToCompatConverterBoundaryInterface;
/* loaded from: classes.dex */
public class k1 implements j1 {

    /* renamed from: a  reason: collision with root package name */
    final WebViewProviderFactoryBoundaryInterface f4054a;

    public k1(WebViewProviderFactoryBoundaryInterface webViewProviderFactoryBoundaryInterface) {
        this.f4054a = webViewProviderFactoryBoundaryInterface;
    }

    @Override // androidx.webkit.internal.j1
    public String[] a() {
        return this.f4054a.getSupportedFeatures();
    }

    @Override // androidx.webkit.internal.j1
    public WebViewProviderBoundaryInterface createWebView(WebView webView) {
        return (WebViewProviderBoundaryInterface) t7.a.a(WebViewProviderBoundaryInterface.class, this.f4054a.createWebView(webView));
    }

    @Override // androidx.webkit.internal.j1
    public ProxyControllerBoundaryInterface getProxyController() {
        return (ProxyControllerBoundaryInterface) t7.a.a(ProxyControllerBoundaryInterface.class, this.f4054a.getProxyController());
    }

    @Override // androidx.webkit.internal.j1
    public ServiceWorkerControllerBoundaryInterface getServiceWorkerController() {
        return (ServiceWorkerControllerBoundaryInterface) t7.a.a(ServiceWorkerControllerBoundaryInterface.class, this.f4054a.getServiceWorkerController());
    }

    @Override // androidx.webkit.internal.j1
    public StaticsBoundaryInterface getStatics() {
        return (StaticsBoundaryInterface) t7.a.a(StaticsBoundaryInterface.class, this.f4054a.getStatics());
    }

    @Override // androidx.webkit.internal.j1
    public TracingControllerBoundaryInterface getTracingController() {
        return (TracingControllerBoundaryInterface) t7.a.a(TracingControllerBoundaryInterface.class, this.f4054a.getTracingController());
    }

    @Override // androidx.webkit.internal.j1
    public WebkitToCompatConverterBoundaryInterface getWebkitToCompatConverter() {
        return (WebkitToCompatConverterBoundaryInterface) t7.a.a(WebkitToCompatConverterBoundaryInterface.class, this.f4054a.getWebkitToCompatConverter());
    }
}
