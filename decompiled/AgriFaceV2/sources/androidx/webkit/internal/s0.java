package androidx.webkit.internal;

import java.lang.reflect.InvocationHandler;
import org.chromium.support_lib_boundary.ScriptHandlerBoundaryInterface;
/* loaded from: classes.dex */
public class s0 implements c2.g {

    /* renamed from: a  reason: collision with root package name */
    private final ScriptHandlerBoundaryInterface f4080a;

    private s0(ScriptHandlerBoundaryInterface scriptHandlerBoundaryInterface) {
        this.f4080a = scriptHandlerBoundaryInterface;
    }

    public static s0 a(InvocationHandler invocationHandler) {
        return new s0((ScriptHandlerBoundaryInterface) t7.a.a(ScriptHandlerBoundaryInterface.class, invocationHandler));
    }

    @Override // c2.g
    public void remove() {
        this.f4080a.remove();
    }
}
