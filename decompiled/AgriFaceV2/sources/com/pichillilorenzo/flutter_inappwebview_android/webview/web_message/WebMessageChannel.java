package com.pichillilorenzo.flutter_inappwebview_android.webview.web_message;

import android.webkit.ValueCallback;
import c2.m;
import c2.n;
import c2.s;
import c2.t;
import com.pichillilorenzo.flutter_inappwebview_android.types.Disposable;
import com.pichillilorenzo.flutter_inappwebview_android.types.WebMessageCompatExt;
import com.pichillilorenzo.flutter_inappwebview_android.types.WebMessagePort;
import com.pichillilorenzo.flutter_inappwebview_android.types.WebMessagePortCompatExt;
import com.pichillilorenzo.flutter_inappwebview_android.webview.InAppWebViewInterface;
import com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebView;
import i6.b;
import i6.j;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class WebMessageChannel implements Disposable {
    protected static final String LOG_TAG = "WebMessageChannel";
    public static final String METHOD_CHANNEL_NAME_PREFIX = "com.pichillilorenzo/flutter_inappwebview_web_message_channel_";
    public WebMessageChannelChannelDelegate channelDelegate;
    public final List<n> compatPorts;
    public String id;
    public final List<WebMessagePort> ports;
    public InAppWebViewInterface webView;

    public WebMessageChannel(String str, InAppWebViewInterface inAppWebViewInterface) {
        this.id = str;
        b bVar = inAppWebViewInterface.getPlugin().messenger;
        this.channelDelegate = new WebMessageChannelChannelDelegate(this, new j(bVar, METHOD_CHANNEL_NAME_PREFIX + str));
        if (inAppWebViewInterface instanceof InAppWebView) {
            this.compatPorts = new ArrayList(Arrays.asList(s.d((InAppWebView) inAppWebViewInterface)));
            this.ports = new ArrayList();
        } else {
            this.ports = Arrays.asList(new WebMessagePort("port1", this), new WebMessagePort("port2", this));
            this.compatPorts = new ArrayList();
        }
        this.webView = inAppWebViewInterface;
    }

    public void closeForInAppWebView(Integer num, j.d dVar) {
        if (this.webView == null || this.compatPorts.size() <= 0 || !t.a("WEB_MESSAGE_PORT_CLOSE")) {
            dVar.success(Boolean.TRUE);
            return;
        }
        try {
            this.compatPorts.get(num.intValue()).a();
            dVar.success(Boolean.TRUE);
        } catch (Exception e8) {
            dVar.error(LOG_TAG, e8.getMessage(), null);
        }
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        if (t.a("WEB_MESSAGE_PORT_CLOSE")) {
            for (n nVar : this.compatPorts) {
                try {
                    nVar.a();
                } catch (Exception unused) {
                }
            }
        }
        WebMessageChannelChannelDelegate webMessageChannelChannelDelegate = this.channelDelegate;
        if (webMessageChannelChannelDelegate != null) {
            webMessageChannelChannelDelegate.dispose();
            this.channelDelegate = null;
        }
        this.compatPorts.clear();
        this.webView = null;
    }

    public void initJsInstance(InAppWebViewInterface inAppWebViewInterface, final ValueCallback<WebMessageChannel> valueCallback) {
        if (inAppWebViewInterface == null) {
            valueCallback.onReceiveValue(this);
            return;
        }
        inAppWebViewInterface.evaluateJavascript("(function() {window.flutter_inappwebview._webMessageChannels['" + this.id + "'] = new MessageChannel();})();", null, new ValueCallback<String>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.web_message.WebMessageChannel.1
            @Override // android.webkit.ValueCallback
            public void onReceiveValue(String str) {
                valueCallback.onReceiveValue(this);
            }
        });
    }

    public void onMessage(int i8, WebMessageCompatExt webMessageCompatExt) {
        WebMessageChannelChannelDelegate webMessageChannelChannelDelegate = this.channelDelegate;
        if (webMessageChannelChannelDelegate != null) {
            webMessageChannelChannelDelegate.onMessage(i8, webMessageCompatExt);
        }
    }

    public void postMessageForInAppWebView(Integer num, WebMessageCompatExt webMessageCompatExt, j.d dVar) {
        if (this.webView == null || this.compatPorts.size() <= 0 || !t.a("WEB_MESSAGE_PORT_POST_MESSAGE")) {
            dVar.success(Boolean.TRUE);
            return;
        }
        n nVar = this.compatPorts.get(num.intValue());
        ArrayList arrayList = new ArrayList();
        List<WebMessagePortCompatExt> ports = webMessageCompatExt.getPorts();
        if (ports != null) {
            for (WebMessagePortCompatExt webMessagePortCompatExt : ports) {
                WebMessageChannel webMessageChannel = this.webView.getWebMessageChannels().get(webMessagePortCompatExt.getWebMessageChannelId());
                if (webMessageChannel != null) {
                    arrayList.add(webMessageChannel.compatPorts.get(webMessagePortCompatExt.getIndex()));
                }
            }
        }
        Object data = webMessageCompatExt.getData();
        try {
            nVar.d((t.a("WEB_MESSAGE_ARRAY_BUFFER") && data != null && webMessageCompatExt.getType() == 1) ? new m((byte[]) data, (n[]) arrayList.toArray(new n[0])) : new m(data != null ? data.toString() : null, (n[]) arrayList.toArray(new n[0])));
            dVar.success(Boolean.TRUE);
        } catch (Exception e8) {
            dVar.error(LOG_TAG, e8.getMessage(), null);
        }
    }

    public void setWebMessageCallbackForInAppWebView(final int i8, j.d dVar) {
        if (this.webView == null || this.compatPorts.size() <= 0 || !t.a("WEB_MESSAGE_PORT_SET_MESSAGE_CALLBACK")) {
            dVar.success(Boolean.TRUE);
            return;
        }
        try {
            this.compatPorts.get(i8).e(new n.a() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.web_message.WebMessageChannel.2
                @Override // c2.n.a
                public void onMessage(n nVar, m mVar) {
                    super.onMessage(nVar, mVar);
                    this.onMessage(i8, mVar != null ? WebMessageCompatExt.fromMapWebMessageCompat(mVar) : null);
                }
            });
            dVar.success(Boolean.TRUE);
        } catch (Exception e8) {
            dVar.error(LOG_TAG, e8.getMessage(), null);
        }
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("id", this.id);
        return hashMap;
    }
}
