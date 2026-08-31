package androidx.webkit.internal;

import c2.n;
import java.lang.reflect.InvocationHandler;
import org.chromium.support_lib_boundary.WebMessageBoundaryInterface;
import org.chromium.support_lib_boundary.WebMessageCallbackBoundaryInterface;
/* loaded from: classes.dex */
public class z0 implements WebMessageCallbackBoundaryInterface {

    /* renamed from: a  reason: collision with root package name */
    private final n.a f4094a;

    public z0(n.a aVar) {
        this.f4094a = aVar;
    }

    @Override // org.chromium.support_lib_boundary.FeatureFlagHolderBoundaryInterface
    public String[] getSupportedFeatures() {
        return new String[]{"WEB_MESSAGE_CALLBACK_ON_MESSAGE"};
    }

    @Override // org.chromium.support_lib_boundary.WebMessageCallbackBoundaryInterface
    public void onMessage(InvocationHandler invocationHandler, InvocationHandler invocationHandler2) {
        c2.m c8 = y0.c((WebMessageBoundaryInterface) t7.a.a(WebMessageBoundaryInterface.class, invocationHandler2));
        if (c8 != null) {
            this.f4094a.onMessage(new c1(invocationHandler), c8);
        }
    }
}
