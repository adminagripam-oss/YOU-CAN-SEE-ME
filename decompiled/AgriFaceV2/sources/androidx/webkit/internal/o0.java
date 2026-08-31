package androidx.webkit.internal;

import java.lang.reflect.InvocationHandler;
import java.util.Objects;
import java.util.concurrent.Callable;
import org.chromium.support_lib_boundary.JsReplyProxyBoundaryInterface;
/* loaded from: classes.dex */
public class o0 extends c2.b {

    /* renamed from: a  reason: collision with root package name */
    private JsReplyProxyBoundaryInterface f4070a;

    /* loaded from: classes.dex */
    class a implements Callable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ JsReplyProxyBoundaryInterface f4071a;

        a(JsReplyProxyBoundaryInterface jsReplyProxyBoundaryInterface) {
            this.f4071a = jsReplyProxyBoundaryInterface;
        }

        @Override // java.util.concurrent.Callable
        public Object call() {
            return new o0(this.f4071a);
        }
    }

    public o0(JsReplyProxyBoundaryInterface jsReplyProxyBoundaryInterface) {
        this.f4070a = jsReplyProxyBoundaryInterface;
    }

    public static o0 c(InvocationHandler invocationHandler) {
        JsReplyProxyBoundaryInterface jsReplyProxyBoundaryInterface = (JsReplyProxyBoundaryInterface) t7.a.a(JsReplyProxyBoundaryInterface.class, invocationHandler);
        return (o0) jsReplyProxyBoundaryInterface.getOrCreatePeer(new a(jsReplyProxyBoundaryInterface));
    }

    @Override // c2.b
    public void a(String str) {
        if (!g1.U.d()) {
            throw g1.a();
        }
        this.f4070a.postMessage(str);
    }

    @Override // c2.b
    public void b(byte[] bArr) {
        Objects.requireNonNull(bArr, "ArrayBuffer must be non-null");
        if (!g1.C.d()) {
            throw g1.a();
        }
        this.f4070a.postMessageWithPayload(t7.a.c(new b1(bArr)));
    }
}
