package androidx.webkit.internal;

import android.webkit.ServiceWorkerClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
/* loaded from: classes.dex */
public class m0 extends ServiceWorkerClient {

    /* renamed from: a  reason: collision with root package name */
    private final c2.h f4067a;

    public m0(c2.h hVar) {
        this.f4067a = hVar;
    }

    @Override // android.webkit.ServiceWorkerClient
    public WebResourceResponse shouldInterceptRequest(WebResourceRequest webResourceRequest) {
        return this.f4067a.shouldInterceptRequest(webResourceRequest);
    }
}
