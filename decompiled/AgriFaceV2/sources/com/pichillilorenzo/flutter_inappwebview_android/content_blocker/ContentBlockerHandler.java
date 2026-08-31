package com.pichillilorenzo.flutter_inappwebview_android.content_blocker;

import android.util.Log;
import android.webkit.WebResourceResponse;
import com.pichillilorenzo.flutter_inappwebview_android.Util;
import com.pichillilorenzo.flutter_inappwebview_android.types.WebResourceRequestExt;
import com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebView;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class ContentBlockerHandler {
    protected static final String LOG_TAG = "ContentBlockerHandler";
    protected List<ContentBlocker> ruleList;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.pichillilorenzo.flutter_inappwebview_android.content_blocker.ContentBlockerHandler$3  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$content_blocker$ContentBlockerActionType;

        static {
            int[] iArr = new int[ContentBlockerActionType.values().length];
            $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$content_blocker$ContentBlockerActionType = iArr;
            try {
                iArr[ContentBlockerActionType.BLOCK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$content_blocker$ContentBlockerActionType[ContentBlockerActionType.CSS_DISPLAY_NONE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$content_blocker$ContentBlockerActionType[ContentBlockerActionType.MAKE_HTTPS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public ContentBlockerHandler() {
        this.ruleList = new ArrayList();
    }

    public WebResourceResponse checkUrl(InAppWebView inAppWebView, WebResourceRequestExt webResourceRequestExt) {
        return checkUrl(inAppWebView, webResourceRequestExt, getResourceTypeFromUrl(webResourceRequestExt));
    }

    public ContentBlockerTriggerResourceType getResourceTypeFromContentType(String str) {
        return str.equals("text/css") ? ContentBlockerTriggerResourceType.STYLE_SHEET : str.equals("image/svg+xml") ? ContentBlockerTriggerResourceType.SVG_DOCUMENT : str.startsWith("image/") ? ContentBlockerTriggerResourceType.IMAGE : str.startsWith("font/") ? ContentBlockerTriggerResourceType.FONT : (str.startsWith("audio/") || str.startsWith("video/") || str.equals("application/ogg")) ? ContentBlockerTriggerResourceType.MEDIA : str.endsWith("javascript") ? ContentBlockerTriggerResourceType.SCRIPT : str.startsWith("text/") ? ContentBlockerTriggerResourceType.DOCUMENT : ContentBlockerTriggerResourceType.RAW;
    }

    public ContentBlockerTriggerResourceType getResourceTypeFromUrl(WebResourceRequestExt webResourceRequestExt) {
        HttpURLConnection makeHttpRequest;
        ContentBlockerTriggerResourceType contentBlockerTriggerResourceType = ContentBlockerTriggerResourceType.RAW;
        String url = webResourceRequestExt.getUrl();
        if ((url.startsWith("http://") || url.startsWith("https://")) && (makeHttpRequest = Util.makeHttpRequest(url, "HEAD", webResourceRequestExt.getHeaders())) != null) {
            try {
                try {
                    String contentType = makeHttpRequest.getContentType();
                    if (contentType != null) {
                        contentBlockerTriggerResourceType = getResourceTypeFromContentType(contentType.split(";")[0].trim());
                    }
                } catch (Exception e8) {
                    Log.e(LOG_TAG, "", e8);
                }
            } finally {
                makeHttpRequest.disconnect();
            }
        }
        return contentBlockerTriggerResourceType;
    }

    public List<ContentBlocker> getRuleList() {
        return this.ruleList;
    }

    public void setRuleList(List<ContentBlocker> list) {
        this.ruleList = list;
    }

    public ContentBlockerHandler(List<ContentBlocker> list) {
        new ArrayList();
        this.ruleList = list;
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x0107  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.webkit.WebResourceResponse checkUrl(final com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebView r27, com.pichillilorenzo.flutter_inappwebview_android.types.WebResourceRequestExt r28, com.pichillilorenzo.flutter_inappwebview_android.content_blocker.ContentBlockerTriggerResourceType r29) {
        /*
            Method dump skipped, instructions count: 874
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.pichillilorenzo.flutter_inappwebview_android.content_blocker.ContentBlockerHandler.checkUrl(com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebView, com.pichillilorenzo.flutter_inappwebview_android.types.WebResourceRequestExt, com.pichillilorenzo.flutter_inappwebview_android.content_blocker.ContentBlockerTriggerResourceType):android.webkit.WebResourceResponse");
    }

    public WebResourceResponse checkUrl(InAppWebView inAppWebView, WebResourceRequestExt webResourceRequestExt, String str) {
        return checkUrl(inAppWebView, webResourceRequestExt, getResourceTypeFromContentType(str));
    }
}
