package com.pichillilorenzo.flutter_inappwebview_android.webview.web_message;

import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import com.pichillilorenzo.flutter_inappwebview_android.types.WebMessageCompatExt;
import com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebView;
import i6.i;
import i6.j;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class WebMessageListenerChannelDelegate extends ChannelDelegateImpl {
    private WebMessageListener webMessageListener;

    public WebMessageListenerChannelDelegate(WebMessageListener webMessageListener, j jVar) {
        super(jVar);
        this.webMessageListener = webMessageListener;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        this.webMessageListener = null;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.IChannelDelegate, i6.j.c
    public void onMethodCall(i iVar, j.d dVar) {
        String str = iVar.f7535a;
        str.hashCode();
        if (!str.equals("postMessage")) {
            dVar.notImplemented();
            return;
        }
        WebMessageListener webMessageListener = this.webMessageListener;
        if (webMessageListener == null || !(webMessageListener.webView instanceof InAppWebView)) {
            dVar.success(Boolean.FALSE);
            return;
        }
        this.webMessageListener.postMessageForInAppWebView(WebMessageCompatExt.fromMap((Map) iVar.a("message")), dVar);
    }

    public void onPostMessage(WebMessageCompatExt webMessageCompatExt, String str, boolean z7) {
        j channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("message", webMessageCompatExt != null ? webMessageCompatExt.toMap() : null);
        hashMap.put("sourceOrigin", str);
        hashMap.put("isMainFrame", Boolean.valueOf(z7));
        channel.c("onPostMessage", hashMap);
    }
}
