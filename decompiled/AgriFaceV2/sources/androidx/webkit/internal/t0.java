package androidx.webkit.internal;

import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import org.chromium.support_lib_boundary.ServiceWorkerClientBoundaryInterface;
/* loaded from: classes.dex */
public class t0 implements ServiceWorkerClientBoundaryInterface {

    /* renamed from: a  reason: collision with root package name */
    private final c2.h f4081a;

    public t0(c2.h hVar) {
        this.f4081a = hVar;
    }

    @Override // org.chromium.support_lib_boundary.FeatureFlagHolderBoundaryInterface
    public String[] getSupportedFeatures() {
        return new String[]{"SERVICE_WORKER_SHOULD_INTERCEPT_REQUEST"};
    }

    @Override // org.chromium.support_lib_boundary.ServiceWorkerClientBoundaryInterface
    public WebResourceResponse shouldInterceptRequest(WebResourceRequest webResourceRequest) {
        return this.f4081a.shouldInterceptRequest(webResourceRequest);
    }
}
