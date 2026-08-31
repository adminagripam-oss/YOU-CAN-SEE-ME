package k3;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.StrictMode;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
final class g1 implements ServiceConnection, k1 {

    /* renamed from: a  reason: collision with root package name */
    private final Map f8411a = new HashMap();

    /* renamed from: b  reason: collision with root package name */
    private int f8412b = 2;

    /* renamed from: c  reason: collision with root package name */
    private boolean f8413c;

    /* renamed from: d  reason: collision with root package name */
    private IBinder f8414d;

    /* renamed from: e  reason: collision with root package name */
    private final e1 f8415e;

    /* renamed from: f  reason: collision with root package name */
    private ComponentName f8416f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ j1 f8417g;

    public g1(j1 j1Var, e1 e1Var) {
        this.f8417g = j1Var;
        this.f8415e = e1Var;
    }

    public final int a() {
        return this.f8412b;
    }

    public final ComponentName b() {
        return this.f8416f;
    }

    public final IBinder c() {
        return this.f8414d;
    }

    public final void d(ServiceConnection serviceConnection, ServiceConnection serviceConnection2, String str) {
        this.f8411a.put(serviceConnection, serviceConnection2);
    }

    public final void e(String str, Executor executor) {
        p3.b bVar;
        Context context;
        Context context2;
        p3.b bVar2;
        Context context3;
        Handler handler;
        Handler handler2;
        long j8;
        StrictMode.VmPolicy.Builder permitUnsafeIntentLaunch;
        this.f8412b = 3;
        StrictMode.VmPolicy vmPolicy = StrictMode.getVmPolicy();
        if (q3.g.k()) {
            permitUnsafeIntentLaunch = new StrictMode.VmPolicy.Builder(vmPolicy).permitUnsafeIntentLaunch();
            StrictMode.setVmPolicy(permitUnsafeIntentLaunch.build());
        }
        try {
            j1 j1Var = this.f8417g;
            bVar = j1Var.f8436j;
            context = j1Var.f8433g;
            e1 e1Var = this.f8415e;
            context2 = j1Var.f8433g;
            boolean c8 = bVar.c(context, str, e1Var.b(context2), this, 4225, executor);
            this.f8413c = c8;
            if (c8) {
                handler = this.f8417g.f8434h;
                Message obtainMessage = handler.obtainMessage(1, this.f8415e);
                handler2 = this.f8417g.f8434h;
                j8 = this.f8417g.f8438l;
                handler2.sendMessageDelayed(obtainMessage, j8);
            } else {
                this.f8412b = 2;
                try {
                    j1 j1Var2 = this.f8417g;
                    bVar2 = j1Var2.f8436j;
                    context3 = j1Var2.f8433g;
                    bVar2.b(context3, this);
                } catch (IllegalArgumentException unused) {
                }
            }
        } finally {
            StrictMode.setVmPolicy(vmPolicy);
        }
    }

    public final void f(ServiceConnection serviceConnection, String str) {
        this.f8411a.remove(serviceConnection);
    }

    public final void g(String str) {
        Handler handler;
        p3.b bVar;
        Context context;
        e1 e1Var = this.f8415e;
        handler = this.f8417g.f8434h;
        handler.removeMessages(1, e1Var);
        j1 j1Var = this.f8417g;
        bVar = j1Var.f8436j;
        context = j1Var.f8433g;
        bVar.b(context, this);
        this.f8413c = false;
        this.f8412b = 2;
    }

    public final boolean h(ServiceConnection serviceConnection) {
        return this.f8411a.containsKey(serviceConnection);
    }

    public final boolean i() {
        return this.f8411a.isEmpty();
    }

    public final boolean j() {
        return this.f8413c;
    }

    @Override // android.content.ServiceConnection
    public final void onBindingDied(ComponentName componentName) {
        onServiceDisconnected(componentName);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        HashMap hashMap;
        Handler handler;
        hashMap = this.f8417g.f8432f;
        synchronized (hashMap) {
            handler = this.f8417g.f8434h;
            handler.removeMessages(1, this.f8415e);
            this.f8414d = iBinder;
            this.f8416f = componentName;
            for (ServiceConnection serviceConnection : this.f8411a.values()) {
                serviceConnection.onServiceConnected(componentName, iBinder);
            }
            this.f8412b = 1;
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        HashMap hashMap;
        Handler handler;
        hashMap = this.f8417g.f8432f;
        synchronized (hashMap) {
            handler = this.f8417g.f8434h;
            handler.removeMessages(1, this.f8415e);
            this.f8414d = null;
            this.f8416f = componentName;
            for (ServiceConnection serviceConnection : this.f8411a.values()) {
                serviceConnection.onServiceDisconnected(componentName);
            }
            this.f8412b = 2;
        }
    }
}
