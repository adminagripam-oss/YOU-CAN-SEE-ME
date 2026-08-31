package androidx.webkit.internal;

import android.webkit.WebResourceError;
import androidx.webkit.internal.a;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import org.chromium.support_lib_boundary.WebResourceErrorBoundaryInterface;
/* loaded from: classes.dex */
public class d1 extends c2.o {

    /* renamed from: a  reason: collision with root package name */
    private WebResourceError f4013a;

    /* renamed from: b  reason: collision with root package name */
    private WebResourceErrorBoundaryInterface f4014b;

    public d1(WebResourceError webResourceError) {
        this.f4013a = webResourceError;
    }

    private WebResourceErrorBoundaryInterface c() {
        if (this.f4014b == null) {
            this.f4014b = (WebResourceErrorBoundaryInterface) t7.a.a(WebResourceErrorBoundaryInterface.class, h1.c().j(this.f4013a));
        }
        return this.f4014b;
    }

    private WebResourceError d() {
        if (this.f4013a == null) {
            this.f4013a = h1.c().i(Proxy.getInvocationHandler(this.f4014b));
        }
        return this.f4013a;
    }

    @Override // c2.o
    public CharSequence a() {
        a.b bVar = g1.f4044v;
        if (bVar.c()) {
            return b.e(d());
        }
        if (bVar.d()) {
            return c().getDescription();
        }
        throw g1.a();
    }

    @Override // c2.o
    public int b() {
        a.b bVar = g1.f4045w;
        if (bVar.c()) {
            return b.f(d());
        }
        if (bVar.d()) {
            return c().getErrorCode();
        }
        throw g1.a();
    }

    public d1(InvocationHandler invocationHandler) {
        this.f4014b = (WebResourceErrorBoundaryInterface) t7.a.a(WebResourceErrorBoundaryInterface.class, invocationHandler);
    }
}
