package p3;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.util.Log;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import k3.k1;
import k3.q;
import q3.g;
import s3.c;
/* loaded from: classes.dex */
public class b {

    /* renamed from: b  reason: collision with root package name */
    private static final Object f10088b = new Object();

    /* renamed from: c  reason: collision with root package name */
    private static volatile b f10089c;

    /* renamed from: a  reason: collision with root package name */
    public final ConcurrentHashMap f10090a = new ConcurrentHashMap();

    private b() {
    }

    public static b a() {
        if (f10089c == null) {
            synchronized (f10088b) {
                if (f10089c == null) {
                    f10089c = new b();
                }
            }
        }
        b bVar = f10089c;
        q.g(bVar);
        return bVar;
    }

    private static void d(Context context, ServiceConnection serviceConnection) {
        try {
            context.unbindService(serviceConnection);
        } catch (IllegalArgumentException | IllegalStateException | NoSuchElementException unused) {
        }
    }

    private final boolean e(Context context, String str, Intent intent, ServiceConnection serviceConnection, int i8, boolean z7, Executor executor) {
        ComponentName component = intent.getComponent();
        if (component != null) {
            String packageName = component.getPackageName();
            "com.google.android.gms".equals(packageName);
            try {
                if ((c.a(context).a(packageName, 0).flags & 2097152) != 0) {
                    Log.w("ConnectionTracker", "Attempted to bind to a service in a STOPPED package.");
                    return false;
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        if (f(serviceConnection)) {
            ServiceConnection serviceConnection2 = (ServiceConnection) this.f10090a.putIfAbsent(serviceConnection, serviceConnection);
            if (serviceConnection2 != null && serviceConnection != serviceConnection2) {
                Log.w("ConnectionTracker", String.format("Duplicate binding with the same ServiceConnection: %s, %s, %s.", serviceConnection, str, intent.getAction()));
            }
            try {
                boolean g8 = g(context, intent, serviceConnection, i8, executor);
                if (g8) {
                    return g8;
                }
                return false;
            } finally {
                this.f10090a.remove(serviceConnection, serviceConnection);
            }
        }
        return g(context, intent, serviceConnection, i8, executor);
    }

    private static boolean f(ServiceConnection serviceConnection) {
        return !(serviceConnection instanceof k1);
    }

    private static final boolean g(Context context, Intent intent, ServiceConnection serviceConnection, int i8, Executor executor) {
        boolean bindService;
        if (executor == null) {
            executor = null;
        }
        if (!g.i() || executor == null) {
            return context.bindService(intent, serviceConnection, i8);
        }
        bindService = context.bindService(intent, i8, executor, serviceConnection);
        return bindService;
    }

    public void b(Context context, ServiceConnection serviceConnection) {
        if (!f(serviceConnection) || !this.f10090a.containsKey(serviceConnection)) {
            d(context, serviceConnection);
            return;
        }
        try {
            d(context, (ServiceConnection) this.f10090a.get(serviceConnection));
        } finally {
            this.f10090a.remove(serviceConnection);
        }
    }

    public final boolean c(Context context, String str, Intent intent, ServiceConnection serviceConnection, int i8, Executor executor) {
        return e(context, str, intent, serviceConnection, 4225, true, executor);
    }
}
