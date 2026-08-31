package androidx.webkit.internal;

import android.net.Uri;
import c2.s;
import java.lang.reflect.InvocationHandler;
import java.util.concurrent.Executor;
import org.chromium.support_lib_boundary.WebViewProviderBoundaryInterface;
/* loaded from: classes.dex */
public class i1 {

    /* renamed from: a  reason: collision with root package name */
    WebViewProviderBoundaryInterface f4052a;

    public i1(WebViewProviderBoundaryInterface webViewProviderBoundaryInterface) {
        this.f4052a = webViewProviderBoundaryInterface;
    }

    public s0 a(String str, String[] strArr) {
        return s0.a(this.f4052a.addDocumentStartJavaScript(str, strArr));
    }

    public void b(String str, String[] strArr, s.b bVar) {
        this.f4052a.addWebMessageListener(str, strArr, t7.a.c(new a1(bVar)));
    }

    public c2.n[] c() {
        InvocationHandler[] createWebMessageChannel = this.f4052a.createWebMessageChannel();
        c2.n[] nVarArr = new c2.n[createWebMessageChannel.length];
        for (int i8 = 0; i8 < createWebMessageChannel.length; i8++) {
            nVarArr[i8] = new c1(createWebMessageChannel[i8]);
        }
        return nVarArr;
    }

    public void d(c2.m mVar, Uri uri) {
        this.f4052a.postMessageToMainFrame(t7.a.c(new y0(mVar)), uri);
    }

    public void e(Executor executor, c2.v vVar) {
        this.f4052a.setWebViewRendererClient(vVar != null ? t7.a.c(new l1(executor, vVar)) : null);
    }
}
