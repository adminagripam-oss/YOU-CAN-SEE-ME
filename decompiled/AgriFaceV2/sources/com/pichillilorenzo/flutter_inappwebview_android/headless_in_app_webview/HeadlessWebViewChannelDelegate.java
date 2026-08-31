package com.pichillilorenzo.flutter_inappwebview_android.headless_in_app_webview;

import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import com.pichillilorenzo.flutter_inappwebview_android.types.Size2D;
import i6.i;
import i6.j;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class HeadlessWebViewChannelDelegate extends ChannelDelegateImpl {
    private HeadlessInAppWebView headlessWebView;

    public HeadlessWebViewChannelDelegate(HeadlessInAppWebView headlessInAppWebView, j jVar) {
        super(jVar);
        this.headlessWebView = headlessInAppWebView;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        this.headlessWebView = null;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.IChannelDelegate, i6.j.c
    public void onMethodCall(i iVar, j.d dVar) {
        Size2D size;
        Boolean bool;
        String str = iVar.f7535a;
        str.hashCode();
        char c8 = 65535;
        switch (str.hashCode()) {
            case -75151241:
                if (str.equals("getSize")) {
                    c8 = 0;
                    break;
                }
                break;
            case 1671767583:
                if (str.equals("dispose")) {
                    c8 = 1;
                    break;
                }
                break;
            case 1984958339:
                if (str.equals("setSize")) {
                    c8 = 2;
                    break;
                }
                break;
        }
        switch (c8) {
            case 0:
                HeadlessInAppWebView headlessInAppWebView = this.headlessWebView;
                Map<String, Object> map = null;
                if (headlessInAppWebView != null && (size = headlessInAppWebView.getSize()) != null) {
                    map = size.toMap();
                }
                dVar.success(map);
                return;
            case 1:
                HeadlessInAppWebView headlessInAppWebView2 = this.headlessWebView;
                if (headlessInAppWebView2 != null) {
                    headlessInAppWebView2.dispose();
                    bool = Boolean.TRUE;
                    dVar.success(bool);
                    return;
                }
                bool = Boolean.FALSE;
                dVar.success(bool);
                return;
            case 2:
                if (this.headlessWebView != null) {
                    Size2D fromMap = Size2D.fromMap((Map) iVar.a("size"));
                    if (fromMap != null) {
                        this.headlessWebView.setSize(fromMap);
                    }
                    bool = Boolean.TRUE;
                    dVar.success(bool);
                    return;
                }
                bool = Boolean.FALSE;
                dVar.success(bool);
                return;
            default:
                dVar.notImplemented();
                return;
        }
    }

    public void onWebViewCreated() {
        j channel = getChannel();
        if (channel == null) {
            return;
        }
        channel.c("onWebViewCreated", new HashMap());
    }
}
