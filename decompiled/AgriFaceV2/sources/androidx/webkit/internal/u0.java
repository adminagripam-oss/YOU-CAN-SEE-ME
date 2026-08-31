package androidx.webkit.internal;

import android.webkit.ServiceWorkerController;
import androidx.webkit.internal.a;
import org.chromium.support_lib_boundary.ServiceWorkerControllerBoundaryInterface;
/* loaded from: classes.dex */
public class u0 extends c2.i {

    /* renamed from: a  reason: collision with root package name */
    private ServiceWorkerController f4082a;

    /* renamed from: b  reason: collision with root package name */
    private ServiceWorkerControllerBoundaryInterface f4083b;

    /* renamed from: c  reason: collision with root package name */
    private final c2.j f4084c;

    public u0() {
        a.c cVar = g1.f4033k;
        if (cVar.c()) {
            this.f4082a = c.g();
            this.f4083b = null;
            this.f4084c = c.i(e());
        } else if (!cVar.d()) {
            throw g1.a();
        } else {
            this.f4082a = null;
            ServiceWorkerControllerBoundaryInterface serviceWorkerController = h1.d().getServiceWorkerController();
            this.f4083b = serviceWorkerController;
            this.f4084c = new v0(serviceWorkerController.getServiceWorkerWebSettings());
        }
    }

    private ServiceWorkerControllerBoundaryInterface d() {
        if (this.f4083b == null) {
            this.f4083b = h1.d().getServiceWorkerController();
        }
        return this.f4083b;
    }

    private ServiceWorkerController e() {
        if (this.f4082a == null) {
            this.f4082a = c.g();
        }
        return this.f4082a;
    }

    @Override // c2.i
    public c2.j b() {
        return this.f4084c;
    }

    @Override // c2.i
    public void c(c2.h hVar) {
        a.c cVar = g1.f4033k;
        if (cVar.c()) {
            if (hVar == null) {
                c.p(e(), null);
            } else {
                c.q(e(), hVar);
            }
        } else if (!cVar.d()) {
            throw g1.a();
        } else {
            if (hVar == null) {
                d().setServiceWorkerClient(null);
            } else {
                d().setServiceWorkerClient(t7.a.c(new t0(hVar)));
            }
        }
    }
}
