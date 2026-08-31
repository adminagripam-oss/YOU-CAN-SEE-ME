package m6;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import androidx.lifecycle.i;
/* loaded from: classes.dex */
public class z5 implements Application.ActivityLifecycleCallbacks, androidx.lifecycle.m {

    /* renamed from: a  reason: collision with root package name */
    public androidx.lifecycle.n f9636a = new androidx.lifecycle.n(this);

    /* renamed from: b  reason: collision with root package name */
    private final int f9637b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public z5(Activity activity) {
        this.f9637b = activity.hashCode();
        activity.getApplication().registerActivityLifecycleCallbacks(this);
    }

    @Override // androidx.lifecycle.m
    public androidx.lifecycle.i getLifecycle() {
        return this.f9636a;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (activity.hashCode() != this.f9637b) {
            return;
        }
        this.f9636a.h(i.a.ON_CREATE);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        if (activity.hashCode() != this.f9637b) {
            return;
        }
        activity.getApplication().unregisterActivityLifecycleCallbacks(this);
        this.f9636a.h(i.a.ON_DESTROY);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        if (activity.hashCode() != this.f9637b) {
            return;
        }
        this.f9636a.h(i.a.ON_PAUSE);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        if (activity.hashCode() != this.f9637b) {
            return;
        }
        this.f9636a.h(i.a.ON_RESUME);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        if (activity.hashCode() != this.f9637b) {
            return;
        }
        this.f9636a.h(i.a.ON_START);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        if (activity.hashCode() != this.f9637b) {
            return;
        }
        this.f9636a.h(i.a.ON_STOP);
    }
}
