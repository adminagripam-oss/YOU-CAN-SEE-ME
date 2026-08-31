package com.pichillilorenzo.flutter_inappwebview_android.headless_in_app_webview;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.pichillilorenzo.flutter_inappwebview_android.InAppWebViewFlutterPlugin;
import com.pichillilorenzo.flutter_inappwebview_android.Util;
import com.pichillilorenzo.flutter_inappwebview_android.types.Disposable;
import com.pichillilorenzo.flutter_inappwebview_android.types.Size2D;
import com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.FlutterWebView;
import i6.b;
import i6.j;
import java.util.Map;
/* loaded from: classes.dex */
public class HeadlessInAppWebView implements Disposable {
    protected static final String LOG_TAG = "HeadlessInAppWebView";
    public static final String METHOD_CHANNEL_NAME_PREFIX = "com.pichillilorenzo/flutter_headless_inappwebview_";
    public HeadlessWebViewChannelDelegate channelDelegate;
    public FlutterWebView flutterWebView;
    public final String id;
    public InAppWebViewFlutterPlugin plugin;

    public HeadlessInAppWebView(InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin, String str, FlutterWebView flutterWebView) {
        this.id = str;
        this.plugin = inAppWebViewFlutterPlugin;
        this.flutterWebView = flutterWebView;
        b bVar = inAppWebViewFlutterPlugin.messenger;
        this.channelDelegate = new HeadlessWebViewChannelDelegate(this, new j(bVar, METHOD_CHANNEL_NAME_PREFIX + str));
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        ViewGroup viewGroup;
        ViewGroup viewGroup2;
        FlutterWebView flutterWebView;
        HeadlessWebViewChannelDelegate headlessWebViewChannelDelegate = this.channelDelegate;
        if (headlessWebViewChannelDelegate != null) {
            headlessWebViewChannelDelegate.dispose();
            this.channelDelegate = null;
        }
        InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin = this.plugin;
        if (inAppWebViewFlutterPlugin != null) {
            HeadlessInAppWebViewManager headlessInAppWebViewManager = inAppWebViewFlutterPlugin.headlessInAppWebViewManager;
            if (headlessInAppWebViewManager != null && headlessInAppWebViewManager.webViews.containsKey(this.id)) {
                headlessInAppWebViewManager.webViews.put(this.id, null);
            }
            Activity activity = this.plugin.activity;
            if (activity != null && (viewGroup = (ViewGroup) activity.findViewById(16908290)) != null && (viewGroup2 = (ViewGroup) viewGroup.getChildAt(0)) != null && (flutterWebView = this.flutterWebView) != null && flutterWebView.getView() != null) {
                viewGroup2.removeView(this.flutterWebView.getView());
            }
        }
        FlutterWebView flutterWebView2 = this.flutterWebView;
        if (flutterWebView2 != null) {
            flutterWebView2.dispose();
        }
        this.flutterWebView = null;
        this.plugin = null;
    }

    public FlutterWebView disposeAndGetFlutterWebView() {
        FlutterWebView flutterWebView = this.flutterWebView;
        if (flutterWebView != null) {
            View view = flutterWebView.getView();
            if (view != null) {
                view.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                view.setVisibility(0);
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                if (viewGroup != null) {
                    viewGroup.removeView(view);
                }
            }
            this.flutterWebView = null;
            dispose();
        }
        return flutterWebView;
    }

    public Size2D getSize() {
        View view;
        FlutterWebView flutterWebView = this.flutterWebView;
        if (flutterWebView == null || flutterWebView.webView == null || (view = flutterWebView.getView()) == null) {
            return null;
        }
        float pixelDensity = Util.getPixelDensity(view.getContext());
        Size2D fullscreenSize = Util.getFullscreenSize(view.getContext());
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        double width = fullscreenSize.getWidth();
        int i8 = layoutParams.width;
        double d8 = width == ((double) i8) ? i8 : i8 / pixelDensity;
        double height = fullscreenSize.getHeight();
        int i9 = layoutParams.height;
        return new Size2D(d8, height == ((double) i9) ? i9 : i9 / pixelDensity);
    }

    public void onWebViewCreated() {
        HeadlessWebViewChannelDelegate headlessWebViewChannelDelegate = this.channelDelegate;
        if (headlessWebViewChannelDelegate != null) {
            headlessWebViewChannelDelegate.onWebViewCreated();
        }
    }

    public void prepare(Map<String, Object> map) {
        Activity activity;
        ViewGroup viewGroup;
        ViewGroup viewGroup2;
        FlutterWebView flutterWebView;
        View view;
        View view2;
        FlutterWebView flutterWebView2 = this.flutterWebView;
        if (flutterWebView2 != null && (view2 = flutterWebView2.getView()) != null) {
            Size2D fromMap = Size2D.fromMap((Map) map.get("initialSize"));
            if (fromMap == null) {
                fromMap = new Size2D(-1.0d, -1.0d);
            }
            setSize(fromMap);
            view2.setVisibility(4);
        }
        InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin = this.plugin;
        if (inAppWebViewFlutterPlugin == null || (activity = inAppWebViewFlutterPlugin.activity) == null || (viewGroup = (ViewGroup) activity.findViewById(16908290)) == null || (viewGroup2 = (ViewGroup) viewGroup.getChildAt(0)) == null || (flutterWebView = this.flutterWebView) == null || (view = flutterWebView.getView()) == null) {
            return;
        }
        viewGroup2.addView(view, 0);
    }

    public void setSize(Size2D size2D) {
        View view;
        FlutterWebView flutterWebView = this.flutterWebView;
        if (flutterWebView == null || flutterWebView.webView == null || (view = flutterWebView.getView()) == null) {
            return;
        }
        float pixelDensity = Util.getPixelDensity(view.getContext());
        Size2D fullscreenSize = Util.getFullscreenSize(view.getContext());
        view.setLayoutParams(new FrameLayout.LayoutParams((int) (size2D.getWidth() == -1.0d ? fullscreenSize.getWidth() : size2D.getWidth() * pixelDensity), (int) (size2D.getWidth() == -1.0d ? fullscreenSize.getHeight() : pixelDensity * size2D.getHeight())));
    }
}
