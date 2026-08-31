package k3;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import k3.c;
/* loaded from: classes.dex */
public final class y0 extends n0 {

    /* renamed from: g  reason: collision with root package name */
    public final IBinder f8491g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ c f8492h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y0(c cVar, int i8, IBinder iBinder, Bundle bundle) {
        super(cVar, i8, bundle);
        this.f8492h = cVar;
        this.f8491g = iBinder;
    }

    @Override // k3.n0
    protected final void f(h3.a aVar) {
        if (this.f8492h.f8353v != null) {
            this.f8492h.f8353v.e(aVar);
        }
        this.f8492h.K(aVar);
    }

    @Override // k3.n0
    protected final boolean g() {
        String str;
        String interfaceDescriptor;
        c.a aVar;
        c.a aVar2;
        try {
            IBinder iBinder = this.f8491g;
            q.g(iBinder);
            interfaceDescriptor = iBinder.getInterfaceDescriptor();
        } catch (RemoteException unused) {
            str = "service probably died";
        }
        if (!this.f8492h.D().equals(interfaceDescriptor)) {
            str = "service descriptor mismatch: " + this.f8492h.D() + " vs. " + interfaceDescriptor;
            Log.w("GmsClient", str);
            return false;
        }
        IInterface r8 = this.f8492h.r(this.f8491g);
        if (r8 == null || !(c.e0(this.f8492h, 2, 4, r8) || c.e0(this.f8492h, 3, 4, r8))) {
            return false;
        }
        this.f8492h.f8357z = null;
        c cVar = this.f8492h;
        Bundle w7 = cVar.w();
        aVar = cVar.f8352u;
        if (aVar != null) {
            aVar2 = this.f8492h.f8352u;
            aVar2.f(w7);
            return true;
        }
        return true;
    }
}
