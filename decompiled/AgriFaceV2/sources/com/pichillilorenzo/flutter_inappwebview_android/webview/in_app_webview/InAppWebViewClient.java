package com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.CookieManager;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SafeBrowsingResponse;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import c2.p;
import c2.t;
import com.pichillilorenzo.flutter_inappwebview_android.Util;
import com.pichillilorenzo.flutter_inappwebview_android.in_app_browser.InAppBrowserDelegate;
import com.pichillilorenzo.flutter_inappwebview_android.plugin_scripts_js.JavaScriptBridgeJS;
import com.pichillilorenzo.flutter_inappwebview_android.types.CustomSchemeResponse;
import com.pichillilorenzo.flutter_inappwebview_android.types.NavigationAction;
import com.pichillilorenzo.flutter_inappwebview_android.types.NavigationActionPolicy;
import com.pichillilorenzo.flutter_inappwebview_android.types.URLCredential;
import com.pichillilorenzo.flutter_inappwebview_android.types.URLRequest;
import com.pichillilorenzo.flutter_inappwebview_android.types.WebResourceErrorExt;
import com.pichillilorenzo.flutter_inappwebview_android.types.WebResourceRequestExt;
import com.pichillilorenzo.flutter_inappwebview_android.types.WebResourceResponseExt;
import com.pichillilorenzo.flutter_inappwebview_android.types.WebViewAssetLoaderExt;
import com.pichillilorenzo.flutter_inappwebview_android.webview.WebViewChannelDelegate;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class InAppWebViewClient extends WebViewClient {
    protected static final String LOG_TAG = "IAWebViewClient";
    private static List<URLCredential> credentialsProposed;
    private static int previousAuthRequestFailureCount;
    private InAppBrowserDelegate inAppBrowserDelegate;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewClient$7  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$types$NavigationActionPolicy;

        static {
            int[] iArr = new int[NavigationActionPolicy.values().length];
            $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$types$NavigationActionPolicy = iArr;
            try {
                iArr[NavigationActionPolicy.ALLOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$types$NavigationActionPolicy[NavigationActionPolicy.CANCEL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public InAppWebViewClient(InAppBrowserDelegate inAppBrowserDelegate) {
        this.inAppBrowserDelegate = inAppBrowserDelegate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void allowShouldOverrideUrlLoading(WebView webView, String str, Map<String, String> map, boolean z7) {
        if (z7) {
            webView.loadUrl(str, map);
        }
    }

    public void dispose() {
        if (this.inAppBrowserDelegate != null) {
            this.inAppBrowserDelegate = null;
        }
    }

    @Override // android.webkit.WebViewClient
    public void doUpdateVisitedHistory(WebView webView, String str, boolean z7) {
        super.doUpdateVisitedHistory(webView, str, z7);
        String url = webView.getUrl();
        InAppBrowserDelegate inAppBrowserDelegate = this.inAppBrowserDelegate;
        if (inAppBrowserDelegate != null) {
            inAppBrowserDelegate.didUpdateVisitedHistory(url);
        }
        WebViewChannelDelegate webViewChannelDelegate = ((InAppWebView) webView).channelDelegate;
        if (webViewChannelDelegate != null) {
            webViewChannelDelegate.onUpdateVisitedHistory(url, z7);
        }
    }

    public void loadCustomJavaScriptOnPageFinished(WebView webView) {
        InAppWebView inAppWebView = (InAppWebView) webView;
        inAppWebView.evaluateJavascript(inAppWebView.userContentController.generateWrappedCodeForDocumentEnd(), null);
    }

    public void loadCustomJavaScriptOnPageStarted(WebView webView) {
        InAppWebView inAppWebView = (InAppWebView) webView;
        if (t.a("DOCUMENT_START_SCRIPT")) {
            return;
        }
        inAppWebView.evaluateJavascript(inAppWebView.userContentController.generateWrappedCodeForDocumentStart(), null);
    }

    @Override // android.webkit.WebViewClient
    public void onFormResubmission(final WebView webView, final Message message, final Message message2) {
        InAppWebView inAppWebView = (InAppWebView) webView;
        WebViewChannelDelegate.FormResubmissionCallback formResubmissionCallback = new WebViewChannelDelegate.FormResubmissionCallback() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewClient.6
            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public void defaultBehaviour(Integer num) {
                InAppWebViewClient.super.onFormResubmission(webView, message, message2);
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult, i6.j.d
            public void error(String str, String str2, Object obj) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(", ");
                if (str2 == null) {
                    str2 = "";
                }
                sb.append(str2);
                Log.e(InAppWebViewClient.LOG_TAG, sb.toString());
                defaultBehaviour((Integer) null);
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public boolean nonNullSuccess(Integer num) {
                (num.intValue() != 0 ? message : message2).sendToTarget();
                return false;
            }
        };
        WebViewChannelDelegate webViewChannelDelegate = inAppWebView.channelDelegate;
        if (webViewChannelDelegate != null) {
            webViewChannelDelegate.onFormResubmission(inAppWebView.getUrl(), formResubmissionCallback);
        } else {
            formResubmissionCallback.defaultBehaviour(null);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onPageCommitVisible(WebView webView, String str) {
        super.onPageCommitVisible(webView, str);
        WebViewChannelDelegate webViewChannelDelegate = ((InAppWebView) webView).channelDelegate;
        if (webViewChannelDelegate != null) {
            webViewChannelDelegate.onPageCommitVisible(str);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        InAppWebView inAppWebView = (InAppWebView) webView;
        inAppWebView.isLoading = false;
        loadCustomJavaScriptOnPageFinished(inAppWebView);
        previousAuthRequestFailureCount = 0;
        credentialsProposed = null;
        super.onPageFinished(webView, str);
        InAppBrowserDelegate inAppBrowserDelegate = this.inAppBrowserDelegate;
        if (inAppBrowserDelegate != null) {
            inAppBrowserDelegate.didFinishNavigation(str);
        }
        CookieManager.getInstance().flush();
        inAppWebView.evaluateJavascript(JavaScriptBridgeJS.PLATFORM_READY_JS_SOURCE, null);
        WebViewChannelDelegate webViewChannelDelegate = inAppWebView.channelDelegate;
        if (webViewChannelDelegate != null) {
            webViewChannelDelegate.onLoadStop(str);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        InAppWebView inAppWebView = (InAppWebView) webView;
        inAppWebView.isLoading = true;
        inAppWebView.disposeWebMessageChannels();
        inAppWebView.userContentController.resetContentWorlds();
        loadCustomJavaScriptOnPageStarted(inAppWebView);
        super.onPageStarted(webView, str, bitmap);
        InAppBrowserDelegate inAppBrowserDelegate = this.inAppBrowserDelegate;
        if (inAppBrowserDelegate != null) {
            inAppBrowserDelegate.didStartNavigation(str);
        }
        WebViewChannelDelegate webViewChannelDelegate = inAppWebView.channelDelegate;
        if (webViewChannelDelegate != null) {
            webViewChannelDelegate.onLoadStart(str);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x004c  */
    @Override // android.webkit.WebViewClient
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onReceivedClientCertRequest(final android.webkit.WebView r9, final android.webkit.ClientCertRequest r10) {
        /*
            r8 = this;
            java.lang.String r0 = r9.getUrl()
            java.lang.String r2 = r10.getHost()
            int r5 = r10.getPort()
            if (r0 == 0) goto L20
            java.net.URI r1 = new java.net.URI     // Catch: java.net.URISyntaxException -> L18
            r1.<init>(r0)     // Catch: java.net.URISyntaxException -> L18
            java.lang.String r0 = r1.getScheme()     // Catch: java.net.URISyntaxException -> L18
            goto L22
        L18:
            r0 = move-exception
            java.lang.String r1 = "IAWebViewClient"
            java.lang.String r3 = ""
            android.util.Log.e(r1, r3, r0)
        L20:
            java.lang.String r0 = "https"
        L22:
            r3 = r0
            com.pichillilorenzo.flutter_inappwebview_android.types.URLProtectionSpace r0 = new com.pichillilorenzo.flutter_inappwebview_android.types.URLProtectionSpace
            r4 = 0
            android.net.http.SslCertificate r6 = r9.getCertificate()
            r7 = 0
            r1 = r0
            r1.<init>(r2, r3, r4, r5, r6, r7)
            com.pichillilorenzo.flutter_inappwebview_android.types.ClientCertChallenge r1 = new com.pichillilorenzo.flutter_inappwebview_android.types.ClientCertChallenge
            java.security.Principal[] r2 = r10.getPrincipals()
            java.lang.String[] r3 = r10.getKeyTypes()
            r1.<init>(r0, r2, r3)
            r0 = r9
            com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebView r0 = (com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebView) r0
            com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewClient$4 r2 = new com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewClient$4
            r2.<init>()
            com.pichillilorenzo.flutter_inappwebview_android.webview.WebViewChannelDelegate r9 = r0.channelDelegate
            if (r9 == 0) goto L4c
            r9.onReceivedClientCertRequest(r1, r2)
            goto L50
        L4c:
            r9 = 0
            r2.defaultBehaviour(r9)
        L50:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewClient.onReceivedClientCertRequest(android.webkit.WebView, android.webkit.ClientCertRequest):void");
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i8, String str, String str2) {
        InAppWebView inAppWebView = (InAppWebView) webView;
        if (inAppWebView.customSettings.disableDefaultErrorPage.booleanValue()) {
            inAppWebView.stopLoading();
            inAppWebView.loadUrl("about:blank");
        }
        inAppWebView.isLoading = false;
        previousAuthRequestFailureCount = 0;
        credentialsProposed = null;
        InAppBrowserDelegate inAppBrowserDelegate = this.inAppBrowserDelegate;
        if (inAppBrowserDelegate != null) {
            inAppBrowserDelegate.didFailNavigation(str2, i8, str);
        }
        WebResourceRequestExt webResourceRequestExt = new WebResourceRequestExt(str2, null, false, false, true, "GET");
        WebResourceErrorExt webResourceErrorExt = new WebResourceErrorExt(i8, str);
        WebViewChannelDelegate webViewChannelDelegate = inAppWebView.channelDelegate;
        if (webViewChannelDelegate != null) {
            webViewChannelDelegate.onReceivedError(webResourceRequestExt, webResourceErrorExt);
        }
        super.onReceivedError(webView, i8, str, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x008f  */
    @Override // android.webkit.WebViewClient
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onReceivedHttpAuthRequest(final android.webkit.WebView r16, final android.webkit.HttpAuthHandler r17, final java.lang.String r18, final java.lang.String r19) {
        /*
            r15 = this;
            java.lang.String r0 = r16.getUrl()
            java.lang.String r1 = "https"
            r2 = 0
            if (r0 == 0) goto L1f
            java.net.URI r3 = new java.net.URI     // Catch: java.net.URISyntaxException -> L17
            r3.<init>(r0)     // Catch: java.net.URISyntaxException -> L17
            java.lang.String r1 = r3.getScheme()     // Catch: java.net.URISyntaxException -> L17
            int r0 = r3.getPort()     // Catch: java.net.URISyntaxException -> L17
            goto L20
        L17:
            r0 = move-exception
            java.lang.String r3 = "IAWebViewClient"
            java.lang.String r4 = ""
            android.util.Log.e(r3, r4, r0)
        L1f:
            r0 = r2
        L20:
            int r3 = com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewClient.previousAuthRequestFailureCount
            int r3 = r3 + 1
            com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewClient.previousAuthRequestFailureCount = r3
            java.util.List<com.pichillilorenzo.flutter_inappwebview_android.types.URLCredential> r3 = com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewClient.credentialsProposed
            if (r3 != 0) goto L41
            android.content.Context r3 = r16.getContext()
            com.pichillilorenzo.flutter_inappwebview_android.credential_database.CredentialDatabase r3 = com.pichillilorenzo.flutter_inappwebview_android.credential_database.CredentialDatabase.getInstance(r3)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r0)
            r12 = r18
            r13 = r19
            java.util.List r3 = r3.getHttpAuthCredentials(r12, r1, r13, r4)
            com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewClient.credentialsProposed = r3
            goto L45
        L41:
            r12 = r18
            r13 = r19
        L45:
            java.util.List<com.pichillilorenzo.flutter_inappwebview_android.types.URLCredential> r3 = com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewClient.credentialsProposed
            r4 = 0
            if (r3 == 0) goto L59
            int r3 = r3.size()
            if (r3 <= 0) goto L59
            java.util.List<com.pichillilorenzo.flutter_inappwebview_android.types.URLCredential> r3 = com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewClient.credentialsProposed
            java.lang.Object r2 = r3.get(r2)
            com.pichillilorenzo.flutter_inappwebview_android.types.URLCredential r2 = (com.pichillilorenzo.flutter_inappwebview_android.types.URLCredential) r2
            goto L5a
        L59:
            r2 = r4
        L5a:
            com.pichillilorenzo.flutter_inappwebview_android.types.URLProtectionSpace r3 = new com.pichillilorenzo.flutter_inappwebview_android.types.URLProtectionSpace
            android.net.http.SslCertificate r10 = r16.getCertificate()
            r11 = 0
            r5 = r3
            r6 = r18
            r7 = r1
            r8 = r19
            r9 = r0
            r5.<init>(r6, r7, r8, r9, r10, r11)
            com.pichillilorenzo.flutter_inappwebview_android.types.HttpAuthenticationChallenge r14 = new com.pichillilorenzo.flutter_inappwebview_android.types.HttpAuthenticationChallenge
            int r5 = com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewClient.previousAuthRequestFailureCount
            r14.<init>(r3, r5, r2)
            r2 = r16
            com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebView r2 = (com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebView) r2
            com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewClient$2 r3 = new com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewClient$2
            r5 = r3
            r6 = r15
            r7 = r16
            r8 = r18
            r9 = r1
            r10 = r19
            r11 = r0
            r12 = r17
            r5.<init>()
            com.pichillilorenzo.flutter_inappwebview_android.webview.WebViewChannelDelegate r0 = r2.channelDelegate
            if (r0 == 0) goto L8f
            r0.onReceivedHttpAuthRequest(r14, r3)
            goto L92
        L8f:
            r3.defaultBehaviour(r4)
        L92:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewClient.onReceivedHttpAuthRequest(android.webkit.WebView, android.webkit.HttpAuthHandler, java.lang.String, java.lang.String):void");
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
        super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
        WebViewChannelDelegate webViewChannelDelegate = ((InAppWebView) webView).channelDelegate;
        if (webViewChannelDelegate != null) {
            webViewChannelDelegate.onReceivedHttpError(WebResourceRequestExt.fromWebResourceRequest(webResourceRequest), WebResourceResponseExt.fromWebResourceResponse(webResourceResponse));
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
        WebViewChannelDelegate webViewChannelDelegate = ((InAppWebView) webView).channelDelegate;
        if (webViewChannelDelegate != null) {
            webViewChannelDelegate.onReceivedLoginRequest(str, str2, str3);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0048  */
    @Override // android.webkit.WebViewClient
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onReceivedSslError(final android.webkit.WebView r11, final android.webkit.SslErrorHandler r12, final android.net.http.SslError r13) {
        /*
            r10 = this;
            java.lang.String r0 = r13.getUrl()
            java.lang.String r1 = ""
            java.lang.String r2 = "https"
            java.net.URI r3 = new java.net.URI     // Catch: java.net.URISyntaxException -> L1c
            r3.<init>(r0)     // Catch: java.net.URISyntaxException -> L1c
            java.lang.String r0 = r3.getHost()     // Catch: java.net.URISyntaxException -> L1c
            java.lang.String r2 = r3.getScheme()     // Catch: java.net.URISyntaxException -> L1a
            int r1 = r3.getPort()     // Catch: java.net.URISyntaxException -> L1a
            goto L24
        L1a:
            r3 = move-exception
            goto L1e
        L1c:
            r3 = move-exception
            r0 = r1
        L1e:
            java.lang.String r4 = "IAWebViewClient"
            android.util.Log.e(r4, r1, r3)
            r1 = 0
        L24:
            r4 = r0
            r7 = r1
            r5 = r2
            com.pichillilorenzo.flutter_inappwebview_android.types.URLProtectionSpace r0 = new com.pichillilorenzo.flutter_inappwebview_android.types.URLProtectionSpace
            r6 = 0
            android.net.http.SslCertificate r8 = r13.getCertificate()
            r3 = r0
            r9 = r13
            r3.<init>(r4, r5, r6, r7, r8, r9)
            com.pichillilorenzo.flutter_inappwebview_android.types.ServerTrustChallenge r1 = new com.pichillilorenzo.flutter_inappwebview_android.types.ServerTrustChallenge
            r1.<init>(r0)
            r0 = r11
            com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebView r0 = (com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebView) r0
            com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewClient$3 r2 = new com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewClient$3
            r2.<init>()
            com.pichillilorenzo.flutter_inappwebview_android.webview.WebViewChannelDelegate r11 = r0.channelDelegate
            if (r11 == 0) goto L48
            r11.onReceivedServerTrustAuthRequest(r1, r2)
            goto L4c
        L48:
            r11 = 0
            r2.defaultBehaviour(r11)
        L4c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewClient.onReceivedSslError(android.webkit.WebView, android.webkit.SslErrorHandler, android.net.http.SslError):void");
    }

    @Override // android.webkit.WebViewClient
    public boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
        InAppWebView inAppWebView = (InAppWebView) webView;
        if (!inAppWebView.customSettings.useOnRenderProcessGone.booleanValue() || inAppWebView.channelDelegate == null) {
            return super.onRenderProcessGone(webView, renderProcessGoneDetail);
        }
        inAppWebView.channelDelegate.onRenderProcessGone(renderProcessGoneDetail.didCrash(), renderProcessGoneDetail.rendererPriorityAtExit());
        return true;
    }

    @Override // android.webkit.WebViewClient
    public void onSafeBrowsingHit(final WebView webView, final WebResourceRequest webResourceRequest, final int i8, final SafeBrowsingResponse safeBrowsingResponse) {
        WebViewChannelDelegate.SafeBrowsingHitCallback safeBrowsingHitCallback = new WebViewChannelDelegate.SafeBrowsingHitCallback() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewClient.5
            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public void defaultBehaviour(com.pichillilorenzo.flutter_inappwebview_android.types.SafeBrowsingResponse safeBrowsingResponse2) {
                InAppWebViewClient.super.onSafeBrowsingHit(webView, webResourceRequest, i8, safeBrowsingResponse);
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult, i6.j.d
            public void error(String str, String str2, Object obj) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(", ");
                if (str2 == null) {
                    str2 = "";
                }
                sb.append(str2);
                Log.e(InAppWebViewClient.LOG_TAG, sb.toString());
                defaultBehaviour((com.pichillilorenzo.flutter_inappwebview_android.types.SafeBrowsingResponse) null);
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public boolean nonNullSuccess(com.pichillilorenzo.flutter_inappwebview_android.types.SafeBrowsingResponse safeBrowsingResponse2) {
                Integer action = safeBrowsingResponse2.getAction();
                if (action != null) {
                    boolean isReport = safeBrowsingResponse2.isReport();
                    int intValue = action.intValue();
                    if (intValue == 0) {
                        safeBrowsingResponse.backToSafety(isReport);
                        return false;
                    } else if (intValue != 1) {
                        safeBrowsingResponse.showInterstitial(isReport);
                        return false;
                    } else {
                        safeBrowsingResponse.proceed(isReport);
                        return false;
                    }
                }
                return true;
            }
        };
        WebViewChannelDelegate webViewChannelDelegate = ((InAppWebView) webView).channelDelegate;
        if (webViewChannelDelegate != null) {
            webViewChannelDelegate.onSafeBrowsingHit(webResourceRequest.getUrl().toString(), i8, safeBrowsingHitCallback);
        } else {
            safeBrowsingHitCallback.defaultBehaviour(null);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onScaleChanged(WebView webView, float f8, float f9) {
        super.onScaleChanged(webView, f8, f9);
        InAppWebView inAppWebView = (InAppWebView) webView;
        inAppWebView.zoomScale = f9 / Util.getPixelDensity(inAppWebView.getContext());
        WebViewChannelDelegate webViewChannelDelegate = inAppWebView.channelDelegate;
        if (webViewChannelDelegate != null) {
            webViewChannelDelegate.onZoomScaleChanged(f8, f9);
        }
    }

    public void onShouldOverrideUrlLoading(final InAppWebView inAppWebView, final String str, String str2, final Map<String, String> map, final boolean z7, boolean z8, boolean z9) {
        NavigationAction navigationAction = new NavigationAction(new URLRequest(str, str2, null, map), z7, z8, z9);
        WebViewChannelDelegate.ShouldOverrideUrlLoadingCallback shouldOverrideUrlLoadingCallback = new WebViewChannelDelegate.ShouldOverrideUrlLoadingCallback() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewClient.1
            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public void defaultBehaviour(NavigationActionPolicy navigationActionPolicy) {
                InAppWebViewClient.this.allowShouldOverrideUrlLoading(inAppWebView, str, map, z7);
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult, i6.j.d
            public void error(String str3, String str4, Object obj) {
                StringBuilder sb = new StringBuilder();
                sb.append(str3);
                sb.append(", ");
                if (str4 == null) {
                    str4 = "";
                }
                sb.append(str4);
                Log.e(InAppWebViewClient.LOG_TAG, sb.toString());
                defaultBehaviour((NavigationActionPolicy) null);
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public boolean nonNullSuccess(NavigationActionPolicy navigationActionPolicy) {
                if (AnonymousClass7.$SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$types$NavigationActionPolicy[navigationActionPolicy.ordinal()] != 1) {
                    return false;
                }
                InAppWebViewClient.this.allowShouldOverrideUrlLoading(inAppWebView, str, map, z7);
                return false;
            }
        };
        WebViewChannelDelegate webViewChannelDelegate = inAppWebView.channelDelegate;
        if (webViewChannelDelegate != null) {
            webViewChannelDelegate.shouldOverrideUrlLoading(navigationAction, shouldOverrideUrlLoadingCallback);
        } else {
            shouldOverrideUrlLoadingCallback.defaultBehaviour(null);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onUnhandledKeyEvent(WebView webView, KeyEvent keyEvent) {
    }

    @Override // android.webkit.WebViewClient
    public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        return shouldInterceptRequest(webView, WebResourceRequestExt.fromWebResourceRequest(webResourceRequest));
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        InAppWebView inAppWebView = (InAppWebView) webView;
        if (inAppWebView.customSettings.useShouldOverrideUrlLoading.booleanValue()) {
            onShouldOverrideUrlLoading(inAppWebView, webResourceRequest.getUrl().toString(), webResourceRequest.getMethod(), webResourceRequest.getRequestHeaders(), webResourceRequest.isForMainFrame(), webResourceRequest.hasGesture(), t.a("WEB_RESOURCE_REQUEST_IS_REDIRECT") ? p.b(webResourceRequest) : webResourceRequest.isRedirect());
            if (inAppWebView.regexToCancelSubFramesLoadingCompiled != null) {
                if (webResourceRequest.isForMainFrame()) {
                    return true;
                }
                return inAppWebView.regexToCancelSubFramesLoadingCompiled.matcher(webResourceRequest.getUrl().toString()).matches();
            }
            return webResourceRequest.isForMainFrame();
        }
        return false;
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        InAppWebView inAppWebView = (InAppWebView) webView;
        if (webResourceRequest.isForMainFrame()) {
            if (inAppWebView.customSettings.disableDefaultErrorPage.booleanValue()) {
                inAppWebView.stopLoading();
                inAppWebView.loadUrl("about:blank");
            }
            inAppWebView.isLoading = false;
            previousAuthRequestFailureCount = 0;
            credentialsProposed = null;
            InAppBrowserDelegate inAppBrowserDelegate = this.inAppBrowserDelegate;
            if (inAppBrowserDelegate != null) {
                inAppBrowserDelegate.didFailNavigation(webResourceRequest.getUrl().toString(), webResourceError.getErrorCode(), webResourceError.getDescription().toString());
            }
        }
        WebViewChannelDelegate webViewChannelDelegate = inAppWebView.channelDelegate;
        if (webViewChannelDelegate != null) {
            webViewChannelDelegate.onReceivedError(WebResourceRequestExt.fromWebResourceRequest(webResourceRequest), WebResourceErrorExt.fromWebResourceError(webResourceError));
        }
    }

    public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequestExt webResourceRequestExt) {
        CustomSchemeResponse onLoadResourceWithCustomScheme;
        WebResourceResponseExt shouldInterceptRequest;
        InAppWebView inAppWebView = (InAppWebView) webView;
        WebViewAssetLoaderExt webViewAssetLoaderExt = inAppWebView.webViewAssetLoaderExt;
        if (webViewAssetLoaderExt != null && webViewAssetLoaderExt.loader != null) {
            try {
                WebResourceResponse a8 = inAppWebView.webViewAssetLoaderExt.loader.a(Uri.parse(webResourceRequestExt.getUrl()));
                if (a8 != null) {
                    return a8;
                }
            } catch (Exception e8) {
                Log.e(LOG_TAG, "", e8);
            }
        }
        WebResourceResponse webResourceResponse = null;
        if (inAppWebView.customSettings.useShouldInterceptRequest.booleanValue()) {
            WebViewChannelDelegate webViewChannelDelegate = inAppWebView.channelDelegate;
            if (webViewChannelDelegate != null) {
                try {
                    shouldInterceptRequest = webViewChannelDelegate.shouldInterceptRequest(webResourceRequestExt);
                } catch (InterruptedException e9) {
                    Log.e(LOG_TAG, "", e9);
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
        String lowerCase = webResourceRequestExt.getUrl().split(":")[0].toLowerCase();
        try {
            lowerCase = Uri.parse(webResourceRequestExt.getUrl()).getScheme();
        } catch (Exception unused) {
        }
        List<String> list = inAppWebView.customSettings.resourceCustomSchemes;
        if (list != null && list.contains(lowerCase)) {
            WebViewChannelDelegate webViewChannelDelegate2 = inAppWebView.channelDelegate;
            if (webViewChannelDelegate2 != null) {
                try {
                    onLoadResourceWithCustomScheme = webViewChannelDelegate2.onLoadResourceWithCustomScheme(webResourceRequestExt);
                } catch (InterruptedException e10) {
                    Log.e(LOG_TAG, "", e10);
                    return null;
                }
            } else {
                onLoadResourceWithCustomScheme = null;
            }
            if (onLoadResourceWithCustomScheme != null) {
                try {
                    webResourceResponse = inAppWebView.contentBlockerHandler.checkUrl(inAppWebView, webResourceRequestExt, onLoadResourceWithCustomScheme.getContentType());
                } catch (Exception e11) {
                    Log.e(LOG_TAG, "", e11);
                }
                return webResourceResponse != null ? webResourceResponse : new WebResourceResponse(onLoadResourceWithCustomScheme.getContentType(), onLoadResourceWithCustomScheme.getContentType(), new ByteArrayInputStream(onLoadResourceWithCustomScheme.getData()));
            }
        }
        if (inAppWebView.contentBlockerHandler.getRuleList().size() > 0) {
            try {
                return inAppWebView.contentBlockerHandler.checkUrl(inAppWebView, webResourceRequestExt);
            } catch (Exception e12) {
                Log.e(LOG_TAG, "", e12);
                return null;
            }
        }
        return null;
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        InAppWebView inAppWebView = (InAppWebView) webView;
        if (inAppWebView.customSettings.useShouldOverrideUrlLoading.booleanValue()) {
            onShouldOverrideUrlLoading(inAppWebView, str, "GET", null, true, false, false);
            return true;
        }
        return false;
    }

    @Override // android.webkit.WebViewClient
    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return shouldInterceptRequest(webView, new WebResourceRequestExt(str, null, false, false, true, "GET"));
    }
}
