package com.pichillilorenzo.flutter_inappwebview_android.service_worker;

import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import c2.h;
import c2.i;
import c2.t;
import com.pichillilorenzo.flutter_inappwebview_android.InAppWebViewFlutterPlugin;
import com.pichillilorenzo.flutter_inappwebview_android.types.Disposable;
import com.pichillilorenzo.flutter_inappwebview_android.types.WebResourceRequestExt;
import com.pichillilorenzo.flutter_inappwebview_android.types.WebResourceResponseExt;
import i6.j;
import java.io.ByteArrayInputStream;
import java.util.Map;
/* loaded from: classes.dex */
public class ServiceWorkerManager implements Disposable {
    protected static final String LOG_TAG = "ServiceWorkerManager";
    public static final String METHOD_CHANNEL_NAME = "com.pichillilorenzo/flutter_inappwebview_serviceworkercontroller";
    public static i serviceWorkerController;
    public ServiceWorkerChannelDelegate channelDelegate;
    public InAppWebViewFlutterPlugin plugin;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class DummyServiceWorkerClientCompat extends h {
        static final h INSTANCE = new DummyServiceWorkerClientCompat();

        private DummyServiceWorkerClientCompat() {
        }

        @Override // c2.h
        public WebResourceResponse shouldInterceptRequest(WebResourceRequest webResourceRequest) {
            return null;
        }
    }

    public ServiceWorkerManager(InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin) {
        this.plugin = inAppWebViewFlutterPlugin;
        this.channelDelegate = new ServiceWorkerChannelDelegate(this, new j(inAppWebViewFlutterPlugin.messenger, METHOD_CHANNEL_NAME));
    }

    private h dummyServiceWorkerClientCompat() {
        return DummyServiceWorkerClientCompat.INSTANCE;
    }

    public static void init() {
        if (serviceWorkerController == null && t.a("SERVICE_WORKER_BASIC_USAGE")) {
            serviceWorkerController = i.a();
        }
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        ServiceWorkerChannelDelegate serviceWorkerChannelDelegate = this.channelDelegate;
        if (serviceWorkerChannelDelegate != null) {
            serviceWorkerChannelDelegate.dispose();
            this.channelDelegate = null;
        }
        this.plugin = null;
    }

    public void setServiceWorkerClient(Boolean bool) {
        i iVar = serviceWorkerController;
        if (iVar != null) {
            iVar.c(bool.booleanValue() ? dummyServiceWorkerClientCompat() : new h() { // from class: com.pichillilorenzo.flutter_inappwebview_android.service_worker.ServiceWorkerManager.1
                @Override // c2.h
                public WebResourceResponse shouldInterceptRequest(WebResourceRequest webResourceRequest) {
                    WebResourceResponseExt shouldInterceptRequest;
                    WebResourceRequestExt fromWebResourceRequest = WebResourceRequestExt.fromWebResourceRequest(webResourceRequest);
                    ServiceWorkerChannelDelegate serviceWorkerChannelDelegate = ServiceWorkerManager.this.channelDelegate;
                    if (serviceWorkerChannelDelegate != null) {
                        try {
                            shouldInterceptRequest = serviceWorkerChannelDelegate.shouldInterceptRequest(fromWebResourceRequest);
                        } catch (InterruptedException e8) {
                            Log.e(ServiceWorkerManager.LOG_TAG, "", e8);
                            return null;
                        }
                    } else {
                        shouldInterceptRequest = null;
                    }
                    if (shouldInterceptRequest != null) {
                        String contentType = shouldInterceptRequest.getContentType();
                        String contentEncoding = shouldInterceptRequest.getContentEncoding();
                        byte[] data = shouldInterceptRequest.getData();
                        Map<String, String> headers = shouldInterceptRequest.getHeaders();
                        Integer statusCode = shouldInterceptRequest.getStatusCode();
                        String reasonPhrase = shouldInterceptRequest.getReasonPhrase();
                        ByteArrayInputStream byteArrayInputStream = data != null ? new ByteArrayInputStream(data) : null;
                        return (statusCode == null || reasonPhrase == null) ? new WebResourceResponse(contentType, contentEncoding, byteArrayInputStream) : new WebResourceResponse(contentType, contentEncoding, statusCode.intValue(), reasonPhrase, headers, byteArrayInputStream);
                    }
                    return null;
                }
            });
        }
    }
}
