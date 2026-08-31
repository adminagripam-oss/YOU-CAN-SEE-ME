package com.pichillilorenzo.flutter_inappwebview_android.service_worker;

import androidx.browser.customtabs.b;
import c2.t;
import com.pichillilorenzo.flutter_inappwebview_android.Util;
import com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl;
import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import com.pichillilorenzo.flutter_inappwebview_android.types.SyncBaseCallbackResultImpl;
import com.pichillilorenzo.flutter_inappwebview_android.types.WebResourceRequestExt;
import com.pichillilorenzo.flutter_inappwebview_android.types.WebResourceResponseExt;
import i6.i;
import i6.j;
import java.util.Map;
/* loaded from: classes.dex */
public class ServiceWorkerChannelDelegate extends ChannelDelegateImpl {
    private ServiceWorkerManager serviceWorkerManager;

    /* loaded from: classes.dex */
    public static class ShouldInterceptRequestCallback extends BaseCallbackResultImpl<WebResourceResponseExt> {
        @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
        public WebResourceResponseExt decodeResult(Object obj) {
            return WebResourceResponseExt.fromMap((Map) obj);
        }
    }

    /* loaded from: classes.dex */
    public static class SyncShouldInterceptRequestCallback extends SyncBaseCallbackResultImpl<WebResourceResponseExt> {
        @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
        public WebResourceResponseExt decodeResult(Object obj) {
            return new ShouldInterceptRequestCallback().decodeResult(obj);
        }
    }

    public ServiceWorkerChannelDelegate(ServiceWorkerManager serviceWorkerManager, j jVar) {
        super(jVar);
        this.serviceWorkerManager = serviceWorkerManager;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        this.serviceWorkerManager = null;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.IChannelDelegate, i6.j.c
    public void onMethodCall(i iVar, j.d dVar) {
        boolean a8;
        Object valueOf;
        ServiceWorkerManager.init();
        c2.i iVar2 = ServiceWorkerManager.serviceWorkerController;
        c2.j b8 = iVar2 != null ? iVar2.b() : null;
        String str = iVar.f7535a;
        str.hashCode();
        char c8 = 65535;
        switch (str.hashCode()) {
            case -1332730774:
                if (str.equals("getAllowContentAccess")) {
                    c8 = 0;
                    break;
                }
                break;
            case -1165005700:
                if (str.equals("setServiceWorkerClient")) {
                    c8 = 1;
                    break;
                }
                break;
            case -563397233:
                if (str.equals("getCacheMode")) {
                    c8 = 2;
                    break;
                }
                break;
            case 674894835:
                if (str.equals("getAllowFileAccess")) {
                    c8 = 3;
                    break;
                }
                break;
            case 985595395:
                if (str.equals("setCacheMode")) {
                    c8 = 4;
                    break;
                }
                break;
            case 1083898794:
                if (str.equals("setBlockNetworkLoads")) {
                    c8 = 5;
                    break;
                }
                break;
            case 1203480182:
                if (str.equals("setAllowContentAccess")) {
                    c8 = 6;
                    break;
                }
                break;
            case 1594928487:
                if (str.equals("setAllowFileAccess")) {
                    c8 = 7;
                    break;
                }
                break;
            case 1694822198:
                if (str.equals("getBlockNetworkLoads")) {
                    c8 = '\b';
                    break;
                }
                break;
        }
        switch (c8) {
            case 0:
                if (b8 != null && t.a("SERVICE_WORKER_CONTENT_ACCESS")) {
                    a8 = b8.a();
                    valueOf = Boolean.valueOf(a8);
                    break;
                }
                valueOf = Boolean.FALSE;
                break;
            case 1:
                if (this.serviceWorkerManager != null) {
                    this.serviceWorkerManager.setServiceWorkerClient((Boolean) iVar.a("isNull"));
                    valueOf = Boolean.TRUE;
                    break;
                }
                valueOf = Boolean.FALSE;
                break;
            case 2:
                if (b8 != null && t.a("SERVICE_WORKER_CACHE_MODE")) {
                    valueOf = Integer.valueOf(b8.d());
                    break;
                } else {
                    dVar.success(null);
                    return;
                }
            case 3:
                if (b8 != null && t.a("SERVICE_WORKER_FILE_ACCESS")) {
                    a8 = b8.b();
                    valueOf = Boolean.valueOf(a8);
                    break;
                }
                valueOf = Boolean.FALSE;
                break;
            case 4:
                if (b8 != null && t.a("SERVICE_WORKER_CACHE_MODE")) {
                    b8.h(((Integer) iVar.a("mode")).intValue());
                }
                valueOf = Boolean.TRUE;
                break;
            case 5:
                if (b8 != null && t.a("SERVICE_WORKER_BLOCK_NETWORK_LOADS")) {
                    b8.g(((Boolean) iVar.a("flag")).booleanValue());
                }
                valueOf = Boolean.TRUE;
                break;
            case b.TAB_HIDDEN /* 6 */:
                if (b8 != null && t.a("SERVICE_WORKER_CONTENT_ACCESS")) {
                    b8.e(((Boolean) iVar.a("allow")).booleanValue());
                }
                valueOf = Boolean.TRUE;
                break;
            case 7:
                if (b8 != null && t.a("SERVICE_WORKER_FILE_ACCESS")) {
                    b8.f(((Boolean) iVar.a("allow")).booleanValue());
                }
                valueOf = Boolean.TRUE;
                break;
            case '\b':
                if (b8 != null && t.a("SERVICE_WORKER_BLOCK_NETWORK_LOADS")) {
                    a8 = b8.c();
                    valueOf = Boolean.valueOf(a8);
                    break;
                }
                valueOf = Boolean.FALSE;
                break;
            default:
                dVar.notImplemented();
                return;
        }
        dVar.success(valueOf);
    }

    public WebResourceResponseExt shouldInterceptRequest(WebResourceRequestExt webResourceRequestExt) {
        j channel = getChannel();
        if (channel == null) {
            return null;
        }
        return (WebResourceResponseExt) Util.invokeMethodAndWaitResult(channel, "shouldInterceptRequest", webResourceRequestExt.toMap(), new SyncShouldInterceptRequestCallback());
    }

    public void shouldInterceptRequest(WebResourceRequestExt webResourceRequestExt, ShouldInterceptRequestCallback shouldInterceptRequestCallback) {
        j channel = getChannel();
        if (channel == null) {
            return;
        }
        channel.d("shouldInterceptRequest", webResourceRequestExt.toMap(), shouldInterceptRequestCallback);
    }
}
