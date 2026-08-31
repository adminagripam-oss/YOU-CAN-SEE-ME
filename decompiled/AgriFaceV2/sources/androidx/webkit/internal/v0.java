package androidx.webkit.internal;

import android.webkit.ServiceWorkerWebSettings;
import androidx.webkit.internal.a;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import org.chromium.support_lib_boundary.ServiceWorkerWebSettingsBoundaryInterface;
/* loaded from: classes.dex */
public class v0 extends c2.j {

    /* renamed from: a  reason: collision with root package name */
    private ServiceWorkerWebSettings f4085a;

    /* renamed from: b  reason: collision with root package name */
    private ServiceWorkerWebSettingsBoundaryInterface f4086b;

    public v0(ServiceWorkerWebSettings serviceWorkerWebSettings) {
        this.f4085a = serviceWorkerWebSettings;
    }

    private ServiceWorkerWebSettingsBoundaryInterface i() {
        if (this.f4086b == null) {
            this.f4086b = (ServiceWorkerWebSettingsBoundaryInterface) t7.a.a(ServiceWorkerWebSettingsBoundaryInterface.class, h1.c().e(this.f4085a));
        }
        return this.f4086b;
    }

    private ServiceWorkerWebSettings j() {
        if (this.f4085a == null) {
            this.f4085a = h1.c().d(Proxy.getInvocationHandler(this.f4086b));
        }
        return this.f4085a;
    }

    @Override // c2.j
    public boolean a() {
        a.c cVar = g1.f4035m;
        if (cVar.c()) {
            return c.a(j());
        }
        if (cVar.d()) {
            return i().getAllowContentAccess();
        }
        throw g1.a();
    }

    @Override // c2.j
    public boolean b() {
        a.c cVar = g1.f4036n;
        if (cVar.c()) {
            return c.b(j());
        }
        if (cVar.d()) {
            return i().getAllowFileAccess();
        }
        throw g1.a();
    }

    @Override // c2.j
    public boolean c() {
        a.c cVar = g1.f4037o;
        if (cVar.c()) {
            return c.c(j());
        }
        if (cVar.d()) {
            return i().getBlockNetworkLoads();
        }
        throw g1.a();
    }

    @Override // c2.j
    public int d() {
        a.c cVar = g1.f4034l;
        if (cVar.c()) {
            return c.d(j());
        }
        if (cVar.d()) {
            return i().getCacheMode();
        }
        throw g1.a();
    }

    @Override // c2.j
    public void e(boolean z7) {
        a.c cVar = g1.f4035m;
        if (cVar.c()) {
            c.k(j(), z7);
        } else if (!cVar.d()) {
            throw g1.a();
        } else {
            i().setAllowContentAccess(z7);
        }
    }

    @Override // c2.j
    public void f(boolean z7) {
        a.c cVar = g1.f4036n;
        if (cVar.c()) {
            c.l(j(), z7);
        } else if (!cVar.d()) {
            throw g1.a();
        } else {
            i().setAllowFileAccess(z7);
        }
    }

    @Override // c2.j
    public void g(boolean z7) {
        a.c cVar = g1.f4037o;
        if (cVar.c()) {
            c.m(j(), z7);
        } else if (!cVar.d()) {
            throw g1.a();
        } else {
            i().setBlockNetworkLoads(z7);
        }
    }

    @Override // c2.j
    public void h(int i8) {
        a.c cVar = g1.f4034l;
        if (cVar.c()) {
            c.n(j(), i8);
        } else if (!cVar.d()) {
            throw g1.a();
        } else {
            i().setCacheMode(i8);
        }
    }

    public v0(InvocationHandler invocationHandler) {
        this.f4086b = (ServiceWorkerWebSettingsBoundaryInterface) t7.a.a(ServiceWorkerWebSettingsBoundaryInterface.class, invocationHandler);
    }
}
