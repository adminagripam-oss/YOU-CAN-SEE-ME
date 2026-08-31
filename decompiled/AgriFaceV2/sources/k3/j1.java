package k3;

import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Looper;
import java.util.HashMap;
import java.util.concurrent.Executor;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class j1 extends i {

    /* renamed from: f  reason: collision with root package name */
    private final HashMap f8432f = new HashMap();

    /* renamed from: g  reason: collision with root package name */
    private final Context f8433g;

    /* renamed from: h  reason: collision with root package name */
    private volatile Handler f8434h;

    /* renamed from: i  reason: collision with root package name */
    private final i1 f8435i;

    /* renamed from: j  reason: collision with root package name */
    private final p3.b f8436j;

    /* renamed from: k  reason: collision with root package name */
    private final long f8437k;

    /* renamed from: l  reason: collision with root package name */
    private final long f8438l;

    /* renamed from: m  reason: collision with root package name */
    private volatile Executor f8439m;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j1(Context context, Looper looper, Executor executor) {
        i1 i1Var = new i1(this, null);
        this.f8435i = i1Var;
        this.f8433g = context.getApplicationContext();
        this.f8434h = new w3.e(looper, i1Var);
        this.f8436j = p3.b.a();
        this.f8437k = 5000L;
        this.f8438l = 300000L;
        this.f8439m = executor;
    }

    @Override // k3.i
    protected final void c(e1 e1Var, ServiceConnection serviceConnection, String str) {
        q.h(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.f8432f) {
            g1 g1Var = (g1) this.f8432f.get(e1Var);
            if (g1Var == null) {
                String obj = e1Var.toString();
                throw new IllegalStateException("Nonexistent connection status for service config: " + obj);
            } else if (!g1Var.h(serviceConnection)) {
                String obj2 = e1Var.toString();
                throw new IllegalStateException("Trying to unbind a GmsServiceConnection  that was not bound before.  config=" + obj2);
            } else {
                g1Var.f(serviceConnection, str);
                if (g1Var.i()) {
                    this.f8434h.sendMessageDelayed(this.f8434h.obtainMessage(0, e1Var), this.f8437k);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // k3.i
    public final boolean e(e1 e1Var, ServiceConnection serviceConnection, String str, Executor executor) {
        boolean j8;
        q.h(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.f8432f) {
            g1 g1Var = (g1) this.f8432f.get(e1Var);
            if (executor == null) {
                executor = this.f8439m;
            }
            if (g1Var == null) {
                g1Var = new g1(this, e1Var);
                g1Var.d(serviceConnection, serviceConnection, str);
                g1Var.e(str, executor);
                this.f8432f.put(e1Var, g1Var);
            } else {
                this.f8434h.removeMessages(0, e1Var);
                if (g1Var.h(serviceConnection)) {
                    String obj = e1Var.toString();
                    throw new IllegalStateException("Trying to bind a GmsServiceConnection that was already connected before.  config=" + obj);
                }
                g1Var.d(serviceConnection, serviceConnection, str);
                int a8 = g1Var.a();
                if (a8 == 1) {
                    serviceConnection.onServiceConnected(g1Var.b(), g1Var.c());
                } else if (a8 == 2) {
                    g1Var.e(str, executor);
                }
            }
            j8 = g1Var.j();
        }
        return j8;
    }
}
