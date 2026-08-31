package androidx.webkit.internal;

import android.webkit.TracingController;
import androidx.webkit.internal.a;
import java.io.OutputStream;
import java.util.concurrent.Executor;
import org.chromium.support_lib_boundary.TracingControllerBoundaryInterface;
/* loaded from: classes.dex */
public class x0 extends c2.l {

    /* renamed from: a  reason: collision with root package name */
    private TracingController f4090a;

    /* renamed from: b  reason: collision with root package name */
    private TracingControllerBoundaryInterface f4091b;

    public x0() {
        a.g gVar = g1.L;
        if (gVar.c()) {
            this.f4090a = x.a();
            this.f4091b = null;
        } else if (!gVar.d()) {
            throw g1.a();
        } else {
            this.f4090a = null;
            this.f4091b = h1.d().getTracingController();
        }
    }

    private TracingControllerBoundaryInterface e() {
        if (this.f4091b == null) {
            this.f4091b = h1.d().getTracingController();
        }
        return this.f4091b;
    }

    private TracingController f() {
        if (this.f4090a == null) {
            this.f4090a = x.a();
        }
        return this.f4090a;
    }

    @Override // c2.l
    public boolean b() {
        a.g gVar = g1.L;
        if (gVar.c()) {
            return x.d(f());
        }
        if (gVar.d()) {
            return e().isTracing();
        }
        throw g1.a();
    }

    @Override // c2.l
    public void c(c2.k kVar) {
        if (kVar == null) {
            throw new IllegalArgumentException("Tracing config must be non null");
        }
        a.g gVar = g1.L;
        if (gVar.c()) {
            x.f(f(), kVar);
        } else if (!gVar.d()) {
            throw g1.a();
        } else {
            e().start(kVar.b(), kVar.a(), kVar.c());
        }
    }

    @Override // c2.l
    public boolean d(OutputStream outputStream, Executor executor) {
        a.g gVar = g1.L;
        if (gVar.c()) {
            return x.g(f(), outputStream, executor);
        }
        if (gVar.d()) {
            return e().stop(outputStream, executor);
        }
        throw g1.a();
    }
}
