package androidx.webkit.internal;

import java.lang.reflect.InvocationHandler;
import java.util.Objects;
import org.chromium.support_lib_boundary.WebMessageBoundaryInterface;
import org.chromium.support_lib_boundary.WebMessagePayloadBoundaryInterface;
/* loaded from: classes.dex */
public class y0 implements WebMessageBoundaryInterface {

    /* renamed from: b  reason: collision with root package name */
    private static final String[] f4092b = {"WEB_MESSAGE_ARRAY_BUFFER"};

    /* renamed from: a  reason: collision with root package name */
    private c2.m f4093a;

    public y0(c2.m mVar) {
        this.f4093a = mVar;
    }

    public static boolean a(int i8) {
        if (i8 != 0) {
            return i8 == 1 && g1.C.d();
        }
        return true;
    }

    private static c2.n[] b(InvocationHandler[] invocationHandlerArr) {
        c2.n[] nVarArr = new c2.n[invocationHandlerArr.length];
        for (int i8 = 0; i8 < invocationHandlerArr.length; i8++) {
            nVarArr[i8] = new c1(invocationHandlerArr[i8]);
        }
        return nVarArr;
    }

    public static c2.m c(WebMessageBoundaryInterface webMessageBoundaryInterface) {
        c2.n[] b8 = b(webMessageBoundaryInterface.getPorts());
        if (g1.C.d()) {
            WebMessagePayloadBoundaryInterface webMessagePayloadBoundaryInterface = (WebMessagePayloadBoundaryInterface) t7.a.a(WebMessagePayloadBoundaryInterface.class, webMessageBoundaryInterface.getMessagePayload());
            int type = webMessagePayloadBoundaryInterface.getType();
            if (type != 0) {
                if (type != 1) {
                    return null;
                }
                return new c2.m(webMessagePayloadBoundaryInterface.getAsArrayBuffer(), b8);
            }
            return new c2.m(webMessagePayloadBoundaryInterface.getAsString(), b8);
        }
        return new c2.m(webMessageBoundaryInterface.getData(), b8);
    }

    @Override // org.chromium.support_lib_boundary.WebMessageBoundaryInterface
    public String getData() {
        return this.f4093a.c();
    }

    @Override // org.chromium.support_lib_boundary.WebMessageBoundaryInterface
    public InvocationHandler getMessagePayload() {
        b1 b1Var;
        int e8 = this.f4093a.e();
        if (e8 == 0) {
            b1Var = new b1(this.f4093a.c());
        } else if (e8 != 1) {
            throw new IllegalStateException("Unknown web message payload type: " + this.f4093a.e());
        } else {
            byte[] b8 = this.f4093a.b();
            Objects.requireNonNull(b8);
            b1Var = new b1(b8);
        }
        return t7.a.c(b1Var);
    }

    @Override // org.chromium.support_lib_boundary.WebMessageBoundaryInterface
    public InvocationHandler[] getPorts() {
        c2.n[] d8 = this.f4093a.d();
        if (d8 == null) {
            return null;
        }
        InvocationHandler[] invocationHandlerArr = new InvocationHandler[d8.length];
        for (int i8 = 0; i8 < d8.length; i8++) {
            invocationHandlerArr[i8] = d8[i8].c();
        }
        return invocationHandlerArr;
    }

    @Override // org.chromium.support_lib_boundary.FeatureFlagHolderBoundaryInterface
    public String[] getSupportedFeatures() {
        return f4092b;
    }
}
