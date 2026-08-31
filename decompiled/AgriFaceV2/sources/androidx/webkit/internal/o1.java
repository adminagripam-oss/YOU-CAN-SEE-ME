package androidx.webkit.internal;

import android.webkit.WebViewRenderProcess;
import androidx.webkit.internal.a;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.util.WeakHashMap;
import java.util.concurrent.Callable;
import org.chromium.support_lib_boundary.WebViewRendererBoundaryInterface;
/* loaded from: classes.dex */
public class o1 extends c2.u {

    /* renamed from: c  reason: collision with root package name */
    private static final WeakHashMap f4072c = new WeakHashMap();

    /* renamed from: a  reason: collision with root package name */
    private WebViewRendererBoundaryInterface f4073a;

    /* renamed from: b  reason: collision with root package name */
    private WeakReference f4074b;

    /* loaded from: classes.dex */
    class a implements Callable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ WebViewRendererBoundaryInterface f4075a;

        a(WebViewRendererBoundaryInterface webViewRendererBoundaryInterface) {
            this.f4075a = webViewRendererBoundaryInterface;
        }

        @Override // java.util.concurrent.Callable
        public Object call() {
            return new o1(this.f4075a);
        }
    }

    public o1(WebViewRenderProcess webViewRenderProcess) {
        this.f4074b = new WeakReference(webViewRenderProcess);
    }

    public static o1 b(WebViewRenderProcess webViewRenderProcess) {
        WeakHashMap weakHashMap = f4072c;
        o1 o1Var = (o1) weakHashMap.get(webViewRenderProcess);
        if (o1Var != null) {
            return o1Var;
        }
        o1 o1Var2 = new o1(webViewRenderProcess);
        weakHashMap.put(webViewRenderProcess, o1Var2);
        return o1Var2;
    }

    public static o1 c(InvocationHandler invocationHandler) {
        WebViewRendererBoundaryInterface webViewRendererBoundaryInterface = (WebViewRendererBoundaryInterface) t7.a.a(WebViewRendererBoundaryInterface.class, invocationHandler);
        return (o1) webViewRendererBoundaryInterface.getOrCreatePeer(new a(webViewRendererBoundaryInterface));
    }

    @Override // c2.u
    public boolean a() {
        a.h hVar = g1.K;
        if (hVar.c()) {
            WebViewRenderProcess a8 = n1.a(this.f4074b.get());
            return a8 != null && f0.g(a8);
        } else if (hVar.d()) {
            return this.f4073a.terminate();
        } else {
            throw g1.a();
        }
    }

    public o1(WebViewRendererBoundaryInterface webViewRendererBoundaryInterface) {
        this.f4073a = webViewRendererBoundaryInterface;
    }
}
