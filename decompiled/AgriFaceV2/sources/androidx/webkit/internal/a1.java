package androidx.webkit.internal;

import android.net.Uri;
import android.webkit.WebView;
import c2.s;
import java.lang.reflect.InvocationHandler;
import org.chromium.support_lib_boundary.WebMessageBoundaryInterface;
import org.chromium.support_lib_boundary.WebMessageListenerBoundaryInterface;
/* loaded from: classes.dex */
public class a1 implements WebMessageListenerBoundaryInterface {

    /* renamed from: a  reason: collision with root package name */
    private s.b f4005a;

    public a1(s.b bVar) {
        this.f4005a = bVar;
    }

    @Override // org.chromium.support_lib_boundary.FeatureFlagHolderBoundaryInterface
    public String[] getSupportedFeatures() {
        return new String[]{"WEB_MESSAGE_LISTENER", "WEB_MESSAGE_ARRAY_BUFFER"};
    }

    @Override // org.chromium.support_lib_boundary.WebMessageListenerBoundaryInterface
    public void onPostMessage(WebView webView, InvocationHandler invocationHandler, Uri uri, boolean z7, InvocationHandler invocationHandler2) {
        c2.m c8 = y0.c((WebMessageBoundaryInterface) t7.a.a(WebMessageBoundaryInterface.class, invocationHandler));
        if (c8 != null) {
            this.f4005a.onPostMessage(webView, c8, uri, z7, o0.c(invocationHandler2));
        }
    }
}
