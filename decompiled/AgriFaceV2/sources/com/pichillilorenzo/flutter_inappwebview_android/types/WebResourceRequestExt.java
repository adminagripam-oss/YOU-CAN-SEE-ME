package com.pichillilorenzo.flutter_inappwebview_android.types;

import android.webkit.WebResourceRequest;
import c2.p;
import c2.t;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class WebResourceRequestExt {
    private boolean hasGesture;
    private Map<String, String> headers;
    private boolean isForMainFrame;
    private boolean isRedirect;
    private String method;
    private String url;

    public WebResourceRequestExt(String str, Map<String, String> map, boolean z7, boolean z8, boolean z9, String str2) {
        this.url = str;
        this.headers = map;
        this.isRedirect = z7;
        this.hasGesture = z8;
        this.isForMainFrame = z9;
        this.method = str2;
    }

    public static WebResourceRequestExt fromWebResourceRequest(WebResourceRequest webResourceRequest) {
        return new WebResourceRequestExt(webResourceRequest.getUrl().toString(), webResourceRequest.getRequestHeaders(), t.a("WEB_RESOURCE_REQUEST_IS_REDIRECT") ? p.b(webResourceRequest) : webResourceRequest.isRedirect(), webResourceRequest.hasGesture(), webResourceRequest.isForMainFrame(), webResourceRequest.getMethod());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WebResourceRequestExt webResourceRequestExt = (WebResourceRequestExt) obj;
        if (this.isRedirect == webResourceRequestExt.isRedirect && this.hasGesture == webResourceRequestExt.hasGesture && this.isForMainFrame == webResourceRequestExt.isForMainFrame && this.url.equals(webResourceRequestExt.url)) {
            Map<String, String> map = this.headers;
            if (map == null ? webResourceRequestExt.headers == null : map.equals(webResourceRequestExt.headers)) {
                String str = this.method;
                String str2 = webResourceRequestExt.method;
                return str != null ? str.equals(str2) : str2 == null;
            }
            return false;
        }
        return false;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public String getMethod() {
        return this.method;
    }

    public String getUrl() {
        return this.url;
    }

    public int hashCode() {
        int hashCode = this.url.hashCode() * 31;
        Map<String, String> map = this.headers;
        int hashCode2 = (((((((hashCode + (map != null ? map.hashCode() : 0)) * 31) + (this.isRedirect ? 1 : 0)) * 31) + (this.hasGesture ? 1 : 0)) * 31) + (this.isForMainFrame ? 1 : 0)) * 31;
        String str = this.method;
        return hashCode2 + (str != null ? str.hashCode() : 0);
    }

    public boolean isForMainFrame() {
        return this.isForMainFrame;
    }

    public boolean isHasGesture() {
        return this.hasGesture;
    }

    public boolean isRedirect() {
        return this.isRedirect;
    }

    public void setForMainFrame(boolean z7) {
        this.isForMainFrame = z7;
    }

    public void setHasGesture(boolean z7) {
        this.hasGesture = z7;
    }

    public void setHeaders(Map<String, String> map) {
        this.headers = map;
    }

    public void setMethod(String str) {
        this.method = str;
    }

    public void setRedirect(boolean z7) {
        this.isRedirect = z7;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("url", this.url);
        hashMap.put("headers", this.headers);
        hashMap.put("isRedirect", Boolean.valueOf(this.isRedirect));
        hashMap.put("hasGesture", Boolean.valueOf(this.hasGesture));
        hashMap.put("isForMainFrame", Boolean.valueOf(this.isForMainFrame));
        hashMap.put("method", this.method);
        return hashMap;
    }

    public String toString() {
        return "WebResourceRequestExt{url=" + this.url + ", headers=" + this.headers + ", isRedirect=" + this.isRedirect + ", hasGesture=" + this.hasGesture + ", isForMainFrame=" + this.isForMainFrame + ", method='" + this.method + "'}";
    }
}
