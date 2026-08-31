package com.pichillilorenzo.flutter_inappwebview_android.headless_in_app_webview;

import android.content.Context;
import com.pichillilorenzo.flutter_inappwebview_android.InAppWebViewFlutterPlugin;
import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.FlutterWebView;
import i6.i;
import i6.j;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class HeadlessInAppWebViewManager extends ChannelDelegateImpl {
    protected static final String LOG_TAG = "HeadlessInAppWebViewManager";
    public static final String METHOD_CHANNEL_NAME = "com.pichillilorenzo/flutter_headless_inappwebview";
    public InAppWebViewFlutterPlugin plugin;
    public final Map<String, HeadlessInAppWebView> webViews;

    public HeadlessInAppWebViewManager(InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin) {
        super(new j(inAppWebViewFlutterPlugin.messenger, METHOD_CHANNEL_NAME));
        this.webViews = new HashMap();
        this.plugin = inAppWebViewFlutterPlugin;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        for (HeadlessInAppWebView headlessInAppWebView : this.webViews.values()) {
            if (headlessInAppWebView != null) {
                headlessInAppWebView.dispose();
            }
        }
        this.webViews.clear();
        this.plugin = null;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.IChannelDelegate, i6.j.c
    public void onMethodCall(i iVar, j.d dVar) {
        String str = (String) iVar.a("id");
        String str2 = iVar.f7535a;
        str2.hashCode();
        if (!str2.equals("run")) {
            dVar.notImplemented();
            return;
        }
        run(str, (HashMap) iVar.a("params"));
        dVar.success(Boolean.TRUE);
    }

    public void run(String str, HashMap<String, Object> hashMap) {
        InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin = this.plugin;
        if (inAppWebViewFlutterPlugin != null) {
            Context context = inAppWebViewFlutterPlugin.activity;
            if (context == null && inAppWebViewFlutterPlugin.applicationContext == null) {
                return;
            }
            if (context == null) {
                context = inAppWebViewFlutterPlugin.applicationContext;
            }
            FlutterWebView flutterWebView = new FlutterWebView(inAppWebViewFlutterPlugin, context, str, hashMap);
            HeadlessInAppWebView headlessInAppWebView = new HeadlessInAppWebView(this.plugin, str, flutterWebView);
            this.webViews.put(str, headlessInAppWebView);
            headlessInAppWebView.prepare(hashMap);
            headlessInAppWebView.onWebViewCreated();
            flutterWebView.makeInitialLoad(hashMap);
        }
    }
}
