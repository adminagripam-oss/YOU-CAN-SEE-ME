package com.pichillilorenzo.flutter_inappwebview_android;

import c2.t;
import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import i6.i;
import i6.j;
/* loaded from: classes.dex */
public class WebViewFeatureManager extends ChannelDelegateImpl {
    protected static final String LOG_TAG = "WebViewFeatureManager";
    public static final String METHOD_CHANNEL_NAME = "com.pichillilorenzo/flutter_inappwebview_webviewfeature";
    public InAppWebViewFlutterPlugin plugin;

    public WebViewFeatureManager(InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin) {
        super(new j(inAppWebViewFlutterPlugin.messenger, METHOD_CHANNEL_NAME));
        this.plugin = inAppWebViewFlutterPlugin;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        this.plugin = null;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.IChannelDelegate, i6.j.c
    public void onMethodCall(i iVar, j.d dVar) {
        boolean b8;
        String str = iVar.f7535a;
        str.hashCode();
        if (str.equals("isStartupFeatureSupported")) {
            InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin = this.plugin;
            if (inAppWebViewFlutterPlugin == null || inAppWebViewFlutterPlugin.activity == null) {
                return;
            }
            b8 = t.b(this.plugin.activity, (String) iVar.a("startupFeature"));
        } else if (!str.equals("isFeatureSupported")) {
            dVar.notImplemented();
            return;
        } else {
            b8 = t.a((String) iVar.a("feature"));
        }
        dVar.success(Boolean.valueOf(b8));
    }
}
