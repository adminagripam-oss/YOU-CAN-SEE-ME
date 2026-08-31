package androidx.webkit.internal;

import android.content.Context;
import android.webkit.ServiceWorkerClient;
import android.webkit.ServiceWorkerController;
import android.webkit.ServiceWorkerWebSettings;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import java.io.File;
/* loaded from: classes.dex */
public abstract class c {
    public static boolean a(ServiceWorkerWebSettings serviceWorkerWebSettings) {
        return serviceWorkerWebSettings.getAllowContentAccess();
    }

    public static boolean b(ServiceWorkerWebSettings serviceWorkerWebSettings) {
        return serviceWorkerWebSettings.getAllowFileAccess();
    }

    public static boolean c(ServiceWorkerWebSettings serviceWorkerWebSettings) {
        return serviceWorkerWebSettings.getBlockNetworkLoads();
    }

    public static int d(ServiceWorkerWebSettings serviceWorkerWebSettings) {
        return serviceWorkerWebSettings.getCacheMode();
    }

    public static File e(Context context) {
        return context.getDataDir();
    }

    public static int f(WebSettings webSettings) {
        return webSettings.getDisabledActionModeMenuItems();
    }

    public static ServiceWorkerController g() {
        return ServiceWorkerController.getInstance();
    }

    public static ServiceWorkerWebSettings h(ServiceWorkerController serviceWorkerController) {
        return serviceWorkerController.getServiceWorkerWebSettings();
    }

    public static v0 i(ServiceWorkerController serviceWorkerController) {
        return new v0(h(serviceWorkerController));
    }

    public static boolean j(WebResourceRequest webResourceRequest) {
        return webResourceRequest.isRedirect();
    }

    public static void k(ServiceWorkerWebSettings serviceWorkerWebSettings, boolean z7) {
        serviceWorkerWebSettings.setAllowContentAccess(z7);
    }

    public static void l(ServiceWorkerWebSettings serviceWorkerWebSettings, boolean z7) {
        serviceWorkerWebSettings.setAllowFileAccess(z7);
    }

    public static void m(ServiceWorkerWebSettings serviceWorkerWebSettings, boolean z7) {
        serviceWorkerWebSettings.setBlockNetworkLoads(z7);
    }

    public static void n(ServiceWorkerWebSettings serviceWorkerWebSettings, int i8) {
        serviceWorkerWebSettings.setCacheMode(i8);
    }

    public static void o(WebSettings webSettings, int i8) {
        webSettings.setDisabledActionModeMenuItems(i8);
    }

    public static void p(ServiceWorkerController serviceWorkerController, ServiceWorkerClient serviceWorkerClient) {
        serviceWorkerController.setServiceWorkerClient(serviceWorkerClient);
    }

    public static void q(ServiceWorkerController serviceWorkerController, c2.h hVar) {
        serviceWorkerController.setServiceWorkerClient(new m0(hVar));
    }
}
