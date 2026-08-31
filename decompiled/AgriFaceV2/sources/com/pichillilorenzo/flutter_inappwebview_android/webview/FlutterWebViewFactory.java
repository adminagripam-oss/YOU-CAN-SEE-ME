package com.pichillilorenzo.flutter_inappwebview_android.webview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.pichillilorenzo.flutter_inappwebview_android.InAppWebViewFlutterPlugin;
import com.pichillilorenzo.flutter_inappwebview_android.headless_in_app_webview.HeadlessInAppWebView;
import com.pichillilorenzo.flutter_inappwebview_android.headless_in_app_webview.HeadlessInAppWebViewManager;
import com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.FlutterWebView;
import i6.n;
import io.flutter.plugin.platform.k;
import io.flutter.plugin.platform.l;
import java.util.HashMap;
/* loaded from: classes.dex */
public class FlutterWebViewFactory extends l {
    public static final String VIEW_TYPE_ID = "com.pichillilorenzo/flutter_inappwebview";
    private final InAppWebViewFlutterPlugin plugin;

    public FlutterWebViewFactory(InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin) {
        super(n.f7547a);
        this.plugin = inAppWebViewFlutterPlugin;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.flutter.plugin.platform.l
    public k create(Context context, int i8, Object obj) {
        FlutterWebView flutterWebView;
        View view;
        ViewGroup viewGroup;
        HeadlessInAppWebView headlessInAppWebView;
        HashMap<String, Object> hashMap = (HashMap) obj;
        Integer valueOf = Integer.valueOf(i8);
        String str = (String) hashMap.get("keepAliveId");
        String str2 = (String) hashMap.get("headlessWebViewId");
        HeadlessInAppWebViewManager headlessInAppWebViewManager = this.plugin.headlessInAppWebViewManager;
        if (str2 == null || headlessInAppWebViewManager == null || (headlessInAppWebView = headlessInAppWebViewManager.webViews.get(str2)) == null) {
            flutterWebView = null;
        } else {
            flutterWebView = headlessInAppWebView.disposeAndGetFlutterWebView();
            if (flutterWebView != null) {
                flutterWebView.keepAliveId = str;
            }
        }
        InAppWebViewManager inAppWebViewManager = this.plugin.inAppWebViewManager;
        if (str != null && flutterWebView == null && inAppWebViewManager != null && (flutterWebView = inAppWebViewManager.keepAliveWebViews.get(str)) != null && (view = flutterWebView.getView()) != null && (viewGroup = (ViewGroup) view.getParent()) != null) {
            viewGroup.removeView(view);
        }
        boolean z7 = flutterWebView == null;
        if (flutterWebView == null) {
            if (str != null) {
                valueOf = str;
            }
            flutterWebView = new FlutterWebView(this.plugin, context, valueOf, hashMap);
        }
        if (str != null && inAppWebViewManager != null) {
            inAppWebViewManager.keepAliveWebViews.put(str, flutterWebView);
        }
        if (z7) {
            flutterWebView.makeInitialLoad(hashMap);
        }
        return flutterWebView;
    }
}
