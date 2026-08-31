package androidx.webkit.internal;

import android.webkit.WebMessage;
import android.webkit.WebMessagePort;
import androidx.webkit.internal.a;
import c2.n;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import org.chromium.support_lib_boundary.WebMessagePortBoundaryInterface;
/* loaded from: classes.dex */
public class c1 extends c2.n {

    /* renamed from: a  reason: collision with root package name */
    private WebMessagePort f4011a;

    /* renamed from: b  reason: collision with root package name */
    private WebMessagePortBoundaryInterface f4012b;

    public c1(WebMessagePort webMessagePort) {
        this.f4011a = webMessagePort;
    }

    public static WebMessage f(c2.m mVar) {
        return b.b(mVar);
    }

    public static WebMessagePort[] g(c2.n[] nVarArr) {
        if (nVarArr == null) {
            return null;
        }
        int length = nVarArr.length;
        WebMessagePort[] webMessagePortArr = new WebMessagePort[length];
        for (int i8 = 0; i8 < length; i8++) {
            webMessagePortArr[i8] = nVarArr[i8].b();
        }
        return webMessagePortArr;
    }

    public static c2.m h(WebMessage webMessage) {
        return b.d(webMessage);
    }

    private WebMessagePortBoundaryInterface i() {
        if (this.f4012b == null) {
            this.f4012b = (WebMessagePortBoundaryInterface) t7.a.a(WebMessagePortBoundaryInterface.class, h1.c().h(this.f4011a));
        }
        return this.f4012b;
    }

    private WebMessagePort j() {
        if (this.f4011a == null) {
            this.f4011a = h1.c().g(Proxy.getInvocationHandler(this.f4012b));
        }
        return this.f4011a;
    }

    public static c2.n[] k(WebMessagePort[] webMessagePortArr) {
        if (webMessagePortArr == null) {
            return null;
        }
        c2.n[] nVarArr = new c2.n[webMessagePortArr.length];
        for (int i8 = 0; i8 < webMessagePortArr.length; i8++) {
            nVarArr[i8] = new c1(webMessagePortArr[i8]);
        }
        return nVarArr;
    }

    @Override // c2.n
    public void a() {
        a.b bVar = g1.B;
        if (bVar.c()) {
            b.a(j());
        } else if (!bVar.d()) {
            throw g1.a();
        } else {
            i().close();
        }
    }

    @Override // c2.n
    public WebMessagePort b() {
        return j();
    }

    @Override // c2.n
    public InvocationHandler c() {
        return Proxy.getInvocationHandler(i());
    }

    @Override // c2.n
    public void d(c2.m mVar) {
        a.b bVar = g1.A;
        if (bVar.c() && mVar.e() == 0) {
            b.h(j(), f(mVar));
        } else if (!bVar.d() || !y0.a(mVar.e())) {
            throw g1.a();
        } else {
            i().postMessage(t7.a.c(new y0(mVar)));
        }
    }

    @Override // c2.n
    public void e(n.a aVar) {
        a.b bVar = g1.D;
        if (bVar.d()) {
            i().setWebMessageCallback(t7.a.c(new z0(aVar)));
        } else if (!bVar.c()) {
            throw g1.a();
        } else {
            b.l(j(), aVar);
        }
    }

    public c1(InvocationHandler invocationHandler) {
        this.f4012b = (WebMessagePortBoundaryInterface) t7.a.a(WebMessagePortBoundaryInterface.class, invocationHandler);
    }
}
