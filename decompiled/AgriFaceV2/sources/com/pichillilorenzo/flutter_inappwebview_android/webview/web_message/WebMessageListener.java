package com.pichillilorenzo.flutter_inappwebview_android.webview.web_message;

import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebView;
import c2.b;
import c2.m;
import c2.s;
import c2.t;
import com.pichillilorenzo.flutter_inappwebview_android.Util;
import com.pichillilorenzo.flutter_inappwebview_android.plugin_scripts_js.JavaScriptBridgeJS;
import com.pichillilorenzo.flutter_inappwebview_android.types.Disposable;
import com.pichillilorenzo.flutter_inappwebview_android.types.PluginScript;
import com.pichillilorenzo.flutter_inappwebview_android.types.UserScriptInjectionTime;
import com.pichillilorenzo.flutter_inappwebview_android.types.WebMessageCompatExt;
import com.pichillilorenzo.flutter_inappwebview_android.webview.InAppWebViewInterface;
import com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebView;
import i6.j;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public class WebMessageListener implements Disposable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    protected static final String LOG_TAG = "WebMessageListener";
    public static final String METHOD_CHANNEL_NAME_PREFIX = "com.pichillilorenzo/flutter_inappwebview_web_message_listener_";
    public Set<String> allowedOriginRules;
    public WebMessageListenerChannelDelegate channelDelegate;
    public String id;
    public String jsObjectName;
    public s.b listener;
    public b replyProxy;
    public InAppWebViewInterface webView;

    public WebMessageListener(String str, InAppWebViewInterface inAppWebViewInterface, i6.b bVar, String str2, Set<String> set) {
        this.id = str;
        this.webView = inAppWebViewInterface;
        this.jsObjectName = str2;
        this.allowedOriginRules = set;
        this.channelDelegate = new WebMessageListenerChannelDelegate(this, new j(bVar, METHOD_CHANNEL_NAME_PREFIX + this.id + "_" + this.jsObjectName));
        if (this.webView instanceof InAppWebView) {
            this.listener = new s.b() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.web_message.WebMessageListener.1
                @Override // c2.s.b
                public void onPostMessage(WebView webView, m mVar, Uri uri, boolean z7, b bVar2) {
                    WebMessageListener webMessageListener = WebMessageListener.this;
                    webMessageListener.replyProxy = bVar2;
                    WebMessageListenerChannelDelegate webMessageListenerChannelDelegate = webMessageListener.channelDelegate;
                    if (webMessageListenerChannelDelegate != null) {
                        webMessageListenerChannelDelegate.onPostMessage(WebMessageCompatExt.fromMapWebMessageCompat(mVar), uri.toString().equals("null") ? null : uri.toString(), z7);
                    }
                }
            };
        }
    }

    public static WebMessageListener fromMap(InAppWebViewInterface inAppWebViewInterface, i6.b bVar, Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        return new WebMessageListener((String) map.get("id"), inAppWebViewInterface, bVar, (String) map.get("jsObjectName"), new HashSet((List) map.get("allowedOriginRules")));
    }

    public void assertOriginRulesValid() {
        int i8 = 0;
        for (String str : this.allowedOriginRules) {
            if (str == null) {
                throw new Exception("allowedOriginRules[" + i8 + "] is null");
            } else if (str.isEmpty()) {
                throw new Exception("allowedOriginRules[" + i8 + "] is empty");
            } else if (!"*".equals(str)) {
                Uri parse = Uri.parse(str);
                String scheme = parse.getScheme();
                String host = parse.getHost();
                String path = parse.getPath();
                int port = parse.getPort();
                if (scheme == null) {
                    throw new Exception("allowedOriginRules " + str + " is invalid");
                } else if (("http".equals(scheme) || "https".equals(scheme)) && (host == null || host.isEmpty())) {
                    throw new Exception("allowedOriginRules " + str + " is invalid");
                } else if (!"http".equals(scheme) && !"https".equals(scheme) && (host != null || port != -1)) {
                    throw new Exception("allowedOriginRules " + str + " is invalid");
                } else if ((host == null || host.isEmpty()) && port != -1) {
                    throw new Exception("allowedOriginRules " + str + " is invalid");
                } else if (!path.isEmpty()) {
                    throw new Exception("allowedOriginRules " + str + " is invalid");
                } else {
                    if (host != null) {
                        int indexOf = host.indexOf("*");
                        if (indexOf != 0 || (indexOf == 0 && !host.startsWith("*."))) {
                            throw new Exception("allowedOriginRules " + str + " is invalid");
                        } else if (host.startsWith("[")) {
                            if (!host.endsWith("]")) {
                                throw new Exception("allowedOriginRules " + str + " is invalid");
                            } else if (!Util.isIPv6(host.substring(1, host.length() - 1))) {
                                throw new Exception("allowedOriginRules " + str + " is invalid");
                            }
                        }
                    }
                    i8++;
                }
            }
        }
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        WebMessageListenerChannelDelegate webMessageListenerChannelDelegate = this.channelDelegate;
        if (webMessageListenerChannelDelegate != null) {
            webMessageListenerChannelDelegate.dispose();
            this.channelDelegate = null;
        }
        this.listener = null;
        this.replyProxy = null;
        this.webView = null;
    }

    public void initJsInstance() {
        String sb;
        if (this.webView != null) {
            String replaceAll = Util.replaceAll(this.jsObjectName, "'", "\\'");
            ArrayList arrayList = new ArrayList();
            for (String str : this.allowedOriginRules) {
                if ("*".equals(str)) {
                    sb = "'*'";
                } else {
                    Uri parse = Uri.parse(str);
                    String str2 = parse.getHost() != null ? "'" + Util.replaceAll(parse.getHost(), "'", "\\'") + "'" : "null";
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("{scheme: '");
                    sb2.append(parse.getScheme());
                    sb2.append("', host: ");
                    sb2.append(str2);
                    sb2.append(", port: ");
                    sb2.append(parse.getPort() != -1 ? Integer.valueOf(parse.getPort()) : "null");
                    sb2.append("}");
                    sb = sb2.toString();
                }
                arrayList.add(sb);
            }
            this.webView.getUserContentController().addPluginScript(new PluginScript("WebMessageListener-" + this.jsObjectName, "(function() {  var allowedOriginRules = [" + TextUtils.join(", ", arrayList) + "];  var isPageBlank = window.location.href === 'about:blank';  var scheme = !isPageBlank ? window.location.protocol.replace(':', '') : null;  var host = !isPageBlank ? window.location.hostname : null;  var port = !isPageBlank ? window.location.port : null;  if (window." + JavaScriptBridgeJS.JAVASCRIPT_BRIDGE_NAME + "._isOriginAllowed(allowedOriginRules, scheme, host, port)) {      window['" + replaceAll + "'] = new FlutterInAppWebViewWebMessageListener('" + replaceAll + "');  }})();", UserScriptInjectionTime.AT_DOCUMENT_START, null, $assertionsDisabled, null));
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:17|(2:72|(1:74)(1:75))(1:21)|(2:69|(1:71))(1:24)|25|(8:29|30|31|32|33|(1:63)(1:48)|(1:50)|(2:56|57))|68|31|32|33|(1:35)|63|(0)|(1:62)(4:52|54|56|57)) */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00f0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean isOriginAllowed(java.lang.String r12, java.lang.String r13, int r14) {
        /*
            Method dump skipped, instructions count: 249
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.pichillilorenzo.flutter_inappwebview_android.webview.web_message.WebMessageListener.isOriginAllowed(java.lang.String, java.lang.String, int):boolean");
    }

    public void postMessageForInAppWebView(WebMessageCompatExt webMessageCompatExt, j.d dVar) {
        Object data;
        if (this.replyProxy != null && t.a("WEB_MESSAGE_LISTENER") && (data = webMessageCompatExt.getData()) != null) {
            if (t.a("WEB_MESSAGE_ARRAY_BUFFER") && webMessageCompatExt.getType() == 1) {
                this.replyProxy.b((byte[]) data);
            } else {
                this.replyProxy.a(data.toString());
            }
        }
        dVar.success(Boolean.TRUE);
    }
}
