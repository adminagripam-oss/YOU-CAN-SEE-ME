package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public final class a implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {

    /* renamed from: e  reason: collision with root package name */
    private static final a f4972e = new a();

    /* renamed from: a  reason: collision with root package name */
    private final AtomicBoolean f4973a = new AtomicBoolean();

    /* renamed from: b  reason: collision with root package name */
    private final AtomicBoolean f4974b = new AtomicBoolean();

    /* renamed from: c  reason: collision with root package name */
    private final ArrayList f4975c = new ArrayList();

    /* renamed from: d  reason: collision with root package name */
    private boolean f4976d = false;

    /* renamed from: com.google.android.gms.common.api.internal.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0064a {
        void a(boolean z7);
    }

    private a() {
    }

    public static a b() {
        return f4972e;
    }

    public static void c(Application application) {
        a aVar = f4972e;
        synchronized (aVar) {
            if (!aVar.f4976d) {
                application.registerActivityLifecycleCallbacks(aVar);
                application.registerComponentCallbacks(aVar);
                aVar.f4976d = true;
            }
        }
    }

    private final void f(boolean z7) {
        synchronized (f4972e) {
            Iterator it = this.f4975c.iterator();
            while (it.hasNext()) {
                ((InterfaceC0064a) it.next()).a(z7);
            }
        }
    }

    public void a(InterfaceC0064a interfaceC0064a) {
        synchronized (f4972e) {
            this.f4975c.add(interfaceC0064a);
        }
    }

    public boolean d() {
        return this.f4973a.get();
    }

    public boolean e(boolean z7) {
        if (!this.f4974b.get()) {
            if (!q3.g.b()) {
                return z7;
            }
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            if (!this.f4974b.getAndSet(true) && runningAppProcessInfo.importance > 100) {
                this.f4973a.set(true);
            }
        }
        return d();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        AtomicBoolean atomicBoolean = this.f4974b;
        boolean compareAndSet = this.f4973a.compareAndSet(true, false);
        atomicBoolean.set(true);
        if (compareAndSet) {
            f(false);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        AtomicBoolean atomicBoolean = this.f4974b;
        boolean compareAndSet = this.f4973a.compareAndSet(true, false);
        atomicBoolean.set(true);
        if (compareAndSet) {
            f(false);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }

    @Override // android.content.ComponentCallbacks2
    public final void onTrimMemory(int i8) {
        if (i8 == 20 && this.f4973a.compareAndSet(false, true)) {
            this.f4974b.set(true);
            f(true);
        }
    }
}
