package k3;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
/* loaded from: classes.dex */
public final class x0 implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    private final int f8489a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ c f8490b;

    public x0(c cVar, int i8) {
        this.f8490b = cVar;
        this.f8489a = i8;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Object obj;
        c cVar = this.f8490b;
        if (iBinder == null) {
            c.b0(cVar, 16);
            return;
        }
        obj = cVar.f8345n;
        synchronized (obj) {
            c cVar2 = this.f8490b;
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
            cVar2.f8346o = (queryLocalInterface == null || !(queryLocalInterface instanceof m)) ? new q0(iBinder) : (m) queryLocalInterface;
        }
        this.f8490b.c0(0, null, this.f8489a);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        Object obj;
        obj = this.f8490b.f8345n;
        synchronized (obj) {
            this.f8490b.f8346o = null;
        }
        c cVar = this.f8490b;
        int i8 = this.f8489a;
        Handler handler = cVar.f8343l;
        handler.sendMessage(handler.obtainMessage(6, i8, 1));
    }
}
