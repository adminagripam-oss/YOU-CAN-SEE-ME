package j3;

import android.app.Activity;
/* loaded from: classes.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private final Object f8222a;

    public e(Activity activity) {
        k3.q.h(activity, "Activity must not be null");
        this.f8222a = activity;
    }

    public final Activity a() {
        return (Activity) this.f8222a;
    }

    public final androidx.fragment.app.u b() {
        return (androidx.fragment.app.u) this.f8222a;
    }

    public final boolean c() {
        return this.f8222a instanceof Activity;
    }

    public final boolean d() {
        return this.f8222a instanceof androidx.fragment.app.u;
    }
}
