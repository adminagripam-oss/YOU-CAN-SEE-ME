package androidx.webkit.internal;

import android.webkit.SafeBrowsingResponse;
import androidx.webkit.internal.a;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import org.chromium.support_lib_boundary.SafeBrowsingResponseBoundaryInterface;
/* loaded from: classes.dex */
public class r0 extends c2.f {

    /* renamed from: a  reason: collision with root package name */
    private SafeBrowsingResponse f4078a;

    /* renamed from: b  reason: collision with root package name */
    private SafeBrowsingResponseBoundaryInterface f4079b;

    public r0(SafeBrowsingResponse safeBrowsingResponse) {
        this.f4078a = safeBrowsingResponse;
    }

    private SafeBrowsingResponseBoundaryInterface d() {
        if (this.f4079b == null) {
            this.f4079b = (SafeBrowsingResponseBoundaryInterface) t7.a.a(SafeBrowsingResponseBoundaryInterface.class, h1.c().c(this.f4078a));
        }
        return this.f4079b;
    }

    private SafeBrowsingResponse e() {
        if (this.f4078a == null) {
            this.f4078a = h1.c().b(Proxy.getInvocationHandler(this.f4079b));
        }
        return this.f4078a;
    }

    @Override // c2.f
    public void a(boolean z7) {
        a.f fVar = g1.f4046x;
        if (fVar.c()) {
            k.a(e(), z7);
        } else if (!fVar.d()) {
            throw g1.a();
        } else {
            d().backToSafety(z7);
        }
    }

    @Override // c2.f
    public void b(boolean z7) {
        a.f fVar = g1.f4047y;
        if (fVar.c()) {
            k.c(e(), z7);
        } else if (!fVar.d()) {
            throw g1.a();
        } else {
            d().proceed(z7);
        }
    }

    @Override // c2.f
    public void c(boolean z7) {
        a.f fVar = g1.f4048z;
        if (fVar.c()) {
            k.e(e(), z7);
        } else if (!fVar.d()) {
            throw g1.a();
        } else {
            d().showInterstitial(z7);
        }
    }

    public r0(InvocationHandler invocationHandler) {
        this.f4079b = (SafeBrowsingResponseBoundaryInterface) t7.a.a(SafeBrowsingResponseBoundaryInterface.class, invocationHandler);
    }
}
