package androidx.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.lifecycle.i;
import androidx.lifecycle.y;
/* loaded from: classes.dex */
public final class w implements m {

    /* renamed from: i  reason: collision with root package name */
    public static final b f3843i = new b(null);

    /* renamed from: j  reason: collision with root package name */
    private static final w f3844j = new w();

    /* renamed from: a  reason: collision with root package name */
    private int f3845a;

    /* renamed from: b  reason: collision with root package name */
    private int f3846b;

    /* renamed from: e  reason: collision with root package name */
    private Handler f3849e;

    /* renamed from: c  reason: collision with root package name */
    private boolean f3847c = true;

    /* renamed from: d  reason: collision with root package name */
    private boolean f3848d = true;

    /* renamed from: f  reason: collision with root package name */
    private final n f3850f = new n(this);

    /* renamed from: g  reason: collision with root package name */
    private final Runnable f3851g = new Runnable() { // from class: androidx.lifecycle.v
        @Override // java.lang.Runnable
        public final void run() {
            w.i(w.this);
        }
    };

    /* renamed from: h  reason: collision with root package name */
    private final y.a f3852h = new d();

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public static final a f3853a = new a();

        private a() {
        }

        public static final void a(Activity activity, Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
            d7.k.e(activity, "activity");
            d7.k.e(activityLifecycleCallbacks, "callback");
            activity.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
        }
    }

    /* loaded from: classes.dex */
    public static final class b {
        private b() {
        }

        public /* synthetic */ b(d7.g gVar) {
            this();
        }

        public final m a() {
            return w.f3844j;
        }

        public final void b(Context context) {
            d7.k.e(context, "context");
            w.f3844j.h(context);
        }
    }

    /* loaded from: classes.dex */
    public static final class c extends e {

        /* loaded from: classes.dex */
        public static final class a extends e {
            final /* synthetic */ w this$0;

            a(w wVar) {
                this.this$0 = wVar;
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPostResumed(Activity activity) {
                d7.k.e(activity, "activity");
                this.this$0.e();
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPostStarted(Activity activity) {
                d7.k.e(activity, "activity");
                this.this$0.f();
            }
        }

        c() {
        }

        @Override // androidx.lifecycle.e, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            d7.k.e(activity, "activity");
            if (Build.VERSION.SDK_INT < 29) {
                y.f3857b.b(activity).f(w.this.f3852h);
            }
        }

        @Override // androidx.lifecycle.e, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            d7.k.e(activity, "activity");
            w.this.d();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPreCreated(Activity activity, Bundle bundle) {
            d7.k.e(activity, "activity");
            a.a(activity, new a(w.this));
        }

        @Override // androidx.lifecycle.e, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            d7.k.e(activity, "activity");
            w.this.g();
        }
    }

    /* loaded from: classes.dex */
    public static final class d implements y.a {
        d() {
        }

        @Override // androidx.lifecycle.y.a
        public void a() {
            w.this.f();
        }

        @Override // androidx.lifecycle.y.a
        public void onCreate() {
        }

        @Override // androidx.lifecycle.y.a
        public void onResume() {
            w.this.e();
        }
    }

    private w() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void i(w wVar) {
        d7.k.e(wVar, "this$0");
        wVar.j();
        wVar.k();
    }

    public static final m l() {
        return f3843i.a();
    }

    public final void d() {
        int i8 = this.f3846b - 1;
        this.f3846b = i8;
        if (i8 == 0) {
            Handler handler = this.f3849e;
            d7.k.b(handler);
            handler.postDelayed(this.f3851g, 700L);
        }
    }

    public final void e() {
        int i8 = this.f3846b + 1;
        this.f3846b = i8;
        if (i8 == 1) {
            if (this.f3847c) {
                this.f3850f.h(i.a.ON_RESUME);
                this.f3847c = false;
                return;
            }
            Handler handler = this.f3849e;
            d7.k.b(handler);
            handler.removeCallbacks(this.f3851g);
        }
    }

    public final void f() {
        int i8 = this.f3845a + 1;
        this.f3845a = i8;
        if (i8 == 1 && this.f3848d) {
            this.f3850f.h(i.a.ON_START);
            this.f3848d = false;
        }
    }

    public final void g() {
        this.f3845a--;
        k();
    }

    @Override // androidx.lifecycle.m
    public i getLifecycle() {
        return this.f3850f;
    }

    public final void h(Context context) {
        d7.k.e(context, "context");
        this.f3849e = new Handler();
        this.f3850f.h(i.a.ON_CREATE);
        Context applicationContext = context.getApplicationContext();
        d7.k.c(applicationContext, "null cannot be cast to non-null type android.app.Application");
        ((Application) applicationContext).registerActivityLifecycleCallbacks(new c());
    }

    public final void j() {
        if (this.f3846b == 0) {
            this.f3847c = true;
            this.f3850f.h(i.a.ON_PAUSE);
        }
    }

    public final void k() {
        if (this.f3845a == 0 && this.f3847c) {
            this.f3850f.h(i.a.ON_STOP);
            this.f3848d = true;
        }
    }
}
