package androidx.webkit.internal;

import android.webkit.WebView;
import java.lang.reflect.InvocationHandler;
import java.util.concurrent.Executor;
import org.chromium.support_lib_boundary.WebViewRendererClientBoundaryInterface;
/* loaded from: classes.dex */
public class l1 implements WebViewRendererClientBoundaryInterface {

    /* renamed from: c  reason: collision with root package name */
    private static final String[] f4056c = {"WEB_VIEW_RENDERER_CLIENT_BASIC_USAGE"};

    /* renamed from: a  reason: collision with root package name */
    private final Executor f4057a;

    /* renamed from: b  reason: collision with root package name */
    private final c2.v f4058b;

    /* loaded from: classes.dex */
    class a implements Runnable {

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ c2.v f4059e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ WebView f4060f;

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ c2.u f4061g;

        a(c2.v vVar, WebView webView, c2.u uVar) {
            this.f4059e = vVar;
            this.f4060f = webView;
            this.f4061g = uVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f4059e.onRenderProcessUnresponsive(this.f4060f, this.f4061g);
        }
    }

    /* loaded from: classes.dex */
    class b implements Runnable {

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ c2.v f4063e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ WebView f4064f;

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ c2.u f4065g;

        b(c2.v vVar, WebView webView, c2.u uVar) {
            this.f4063e = vVar;
            this.f4064f = webView;
            this.f4065g = uVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f4063e.onRenderProcessResponsive(this.f4064f, this.f4065g);
        }
    }

    public l1(Executor executor, c2.v vVar) {
        this.f4057a = executor;
        this.f4058b = vVar;
    }

    @Override // org.chromium.support_lib_boundary.FeatureFlagHolderBoundaryInterface
    public final String[] getSupportedFeatures() {
        return f4056c;
    }

    @Override // org.chromium.support_lib_boundary.WebViewRendererClientBoundaryInterface
    public final void onRendererResponsive(WebView webView, InvocationHandler invocationHandler) {
        o1 c8 = o1.c(invocationHandler);
        c2.v vVar = this.f4058b;
        Executor executor = this.f4057a;
        if (executor == null) {
            vVar.onRenderProcessResponsive(webView, c8);
        } else {
            executor.execute(new b(vVar, webView, c8));
        }
    }

    @Override // org.chromium.support_lib_boundary.WebViewRendererClientBoundaryInterface
    public final void onRendererUnresponsive(WebView webView, InvocationHandler invocationHandler) {
        o1 c8 = o1.c(invocationHandler);
        c2.v vVar = this.f4058b;
        Executor executor = this.f4057a;
        if (executor == null) {
            vVar.onRenderProcessUnresponsive(webView, c8);
        } else {
            executor.execute(new a(vVar, webView, c8));
        }
    }
}
