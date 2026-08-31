package io.flutter.embedding.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.window.BackEvent;
import android.window.OnBackAnimationCallback;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.lifecycle.i;
import io.flutter.embedding.android.f;
import java.util.List;
/* loaded from: classes.dex */
public abstract class e extends Activity implements f.c, androidx.lifecycle.m {

    /* renamed from: e  reason: collision with root package name */
    public static final int f7592e = View.generateViewId();

    /* renamed from: a  reason: collision with root package name */
    private boolean f7593a = false;

    /* renamed from: b  reason: collision with root package name */
    protected f f7594b;

    /* renamed from: c  reason: collision with root package name */
    private androidx.lifecycle.n f7595c;

    /* renamed from: d  reason: collision with root package name */
    private final OnBackInvokedCallback f7596d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements OnBackAnimationCallback {
        a() {
        }

        public void onBackCancelled() {
            e.this.B();
        }

        public void onBackInvoked() {
            e.this.C();
        }

        public void onBackProgressed(BackEvent backEvent) {
            e.this.R(backEvent);
        }

        public void onBackStarted(BackEvent backEvent) {
            e.this.N(backEvent);
        }
    }

    public e() {
        this.f7596d = Build.VERSION.SDK_INT < 33 ? null : G();
        this.f7595c = new androidx.lifecycle.n(this);
    }

    private void D() {
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(1073741824);
        window.getDecorView().setSystemUiVisibility(1280);
    }

    private void E() {
        if (H() == g.transparent) {
            getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
    }

    private View F() {
        return this.f7594b.u(null, null, null, f7592e, v() == d0.surface);
    }

    private OnBackInvokedCallback G() {
        return Build.VERSION.SDK_INT >= 34 ? new a() : new OnBackInvokedCallback() { // from class: io.flutter.embedding.android.d
            public final void onBackInvoked() {
                e.this.onBackPressed();
            }
        };
    }

    private boolean K() {
        return (getApplicationInfo().flags & 2) != 0;
    }

    private boolean O(String str) {
        StringBuilder sb;
        String str2;
        f fVar = this.f7594b;
        if (fVar == null) {
            sb = new StringBuilder();
            sb.append("FlutterActivity ");
            sb.append(hashCode());
            sb.append(" ");
            sb.append(str);
            str2 = " called after release.";
        } else if (fVar.o()) {
            return true;
        } else {
            sb = new StringBuilder();
            sb.append("FlutterActivity ");
            sb.append(hashCode());
            sb.append(" ");
            sb.append(str);
            str2 = " called after detach.";
        }
        sb.append(str2);
        z5.b.g("FlutterActivity", sb.toString());
        return false;
    }

    private void P() {
        try {
            Bundle J = J();
            if (J != null) {
                int i8 = J.getInt("io.flutter.embedding.android.NormalTheme", -1);
                if (i8 != -1) {
                    setTheme(i8);
                }
            } else {
                z5.b.f("FlutterActivity", "Using the launch theme as normal theme.");
            }
        } catch (PackageManager.NameNotFoundException unused) {
            z5.b.b("FlutterActivity", "Could not read meta-data for FlutterActivity. Using the launch theme as normal theme.");
        }
    }

    @Override // io.flutter.embedding.android.f.c
    public void A(io.flutter.embedding.engine.a aVar) {
        if (this.f7594b.p()) {
            return;
        }
        g6.a.a(aVar);
    }

    public void B() {
        if (O("cancelBackGesture")) {
            this.f7594b.h();
        }
    }

    public void C() {
        if (O("commitBackGesture")) {
            this.f7594b.i();
        }
    }

    protected g H() {
        return getIntent().hasExtra("background_mode") ? g.valueOf(getIntent().getStringExtra("background_mode")) : g.opaque;
    }

    protected io.flutter.embedding.engine.a I() {
        return this.f7594b.n();
    }

    protected Bundle J() {
        return getPackageManager().getActivityInfo(getComponentName(), 128).metaData;
    }

    public void L() {
        OnBackInvokedDispatcher onBackInvokedDispatcher;
        if (Build.VERSION.SDK_INT >= 33) {
            onBackInvokedDispatcher = getOnBackInvokedDispatcher();
            onBackInvokedDispatcher.registerOnBackInvokedCallback(0, this.f7596d);
            this.f7593a = true;
        }
    }

    public void M() {
        Q();
        f fVar = this.f7594b;
        if (fVar != null) {
            fVar.J();
            this.f7594b = null;
        }
    }

    public void N(BackEvent backEvent) {
        if (O("startBackGesture")) {
            this.f7594b.L(backEvent);
        }
    }

    public void Q() {
        OnBackInvokedDispatcher onBackInvokedDispatcher;
        if (Build.VERSION.SDK_INT >= 33) {
            onBackInvokedDispatcher = getOnBackInvokedDispatcher();
            onBackInvokedDispatcher.unregisterOnBackInvokedCallback(this.f7596d);
            this.f7593a = false;
        }
    }

    public void R(BackEvent backEvent) {
        if (O("updateBackGestureProgress")) {
            this.f7594b.M(backEvent);
        }
    }

    @Override // io.flutter.plugin.platform.i.d
    public boolean a() {
        return false;
    }

    @Override // io.flutter.embedding.android.f.c
    public void b() {
    }

    @Override // io.flutter.embedding.android.f.c
    public void c() {
        z5.b.g("FlutterActivity", "FlutterActivity " + this + " connection to the engine " + I() + " evicted by another attaching activity");
        f fVar = this.f7594b;
        if (fVar != null) {
            fVar.v();
            this.f7594b.w();
        }
    }

    @Override // io.flutter.embedding.android.f.c
    public void d() {
        if (Build.VERSION.SDK_INT >= 29) {
            reportFullyDrawn();
        }
    }

    @Override // io.flutter.plugin.platform.i.d
    public void e(boolean z7) {
        if (z7 && !this.f7593a) {
            L();
        } else if (z7 || !this.f7593a) {
        } else {
            Q();
        }
    }

    @Override // io.flutter.embedding.android.f.c
    public String f() {
        return getIntent().getStringExtra("cached_engine_group_id");
    }

    @Override // io.flutter.embedding.android.f.c
    public String g() {
        if (getIntent().hasExtra("route")) {
            return getIntent().getStringExtra("route");
        }
        try {
            Bundle J = J();
            if (J != null) {
                return J.getString("io.flutter.InitialRoute");
            }
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    @Override // io.flutter.embedding.android.f.c
    public Activity getActivity() {
        return this;
    }

    @Override // io.flutter.embedding.android.f.c
    public Context getContext() {
        return this;
    }

    @Override // io.flutter.embedding.android.f.c, androidx.lifecycle.m
    public androidx.lifecycle.i getLifecycle() {
        return this.f7595c;
    }

    @Override // io.flutter.embedding.android.f.c
    public List h() {
        return (List) getIntent().getSerializableExtra("dart_entrypoint_args");
    }

    @Override // io.flutter.embedding.android.f.c
    public boolean i() {
        return true;
    }

    @Override // io.flutter.embedding.android.f.c
    public boolean j() {
        boolean booleanExtra = getIntent().getBooleanExtra("destroy_engine_with_activity", false);
        return (l() != null || this.f7594b.p()) ? booleanExtra : getIntent().getBooleanExtra("destroy_engine_with_activity", true);
    }

    @Override // io.flutter.embedding.android.f.c
    public boolean k() {
        return true;
    }

    @Override // io.flutter.embedding.android.f.c
    public String l() {
        return getIntent().getStringExtra("cached_engine_id");
    }

    @Override // io.flutter.embedding.android.f.c
    public boolean m() {
        return getIntent().hasExtra("enable_state_restoration") ? getIntent().getBooleanExtra("enable_state_restoration", false) : l() == null;
    }

    @Override // io.flutter.embedding.android.f.c
    public String n() {
        if (getIntent().hasExtra("dart_entrypoint")) {
            return getIntent().getStringExtra("dart_entrypoint");
        }
        try {
            Bundle J = J();
            String string = J != null ? J.getString("io.flutter.Entrypoint") : null;
            return string != null ? string : "main";
        } catch (PackageManager.NameNotFoundException unused) {
            return "main";
        }
    }

    @Override // io.flutter.embedding.android.f.c
    public void o(io.flutter.embedding.engine.a aVar) {
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i8, int i9, Intent intent) {
        if (O("onActivityResult")) {
            this.f7594b.r(i8, i9, intent);
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (O("onBackPressed")) {
            this.f7594b.t();
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        P();
        super.onCreate(bundle);
        f fVar = new f(this);
        this.f7594b = fVar;
        fVar.s(this);
        this.f7594b.B(bundle);
        this.f7595c.h(i.a.ON_CREATE);
        E();
        setContentView(F());
        D();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (O("onDestroy")) {
            this.f7594b.v();
            this.f7594b.w();
        }
        M();
        this.f7595c.h(i.a.ON_DESTROY);
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (O("onNewIntent")) {
            this.f7594b.x(intent);
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        if (O("onPause")) {
            this.f7594b.y();
        }
        this.f7595c.h(i.a.ON_PAUSE);
    }

    @Override // android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        if (O("onPostResume")) {
            this.f7594b.z();
        }
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i8, String[] strArr, int[] iArr) {
        if (O("onRequestPermissionsResult")) {
            this.f7594b.A(i8, strArr, iArr);
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.f7595c.h(i.a.ON_RESUME);
        if (O("onResume")) {
            this.f7594b.C();
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (O("onSaveInstanceState")) {
            this.f7594b.D(bundle);
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        this.f7595c.h(i.a.ON_START);
        if (O("onStart")) {
            this.f7594b.E();
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        if (O("onStop")) {
            this.f7594b.F();
        }
        this.f7595c.h(i.a.ON_STOP);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks2
    public void onTrimMemory(int i8) {
        super.onTrimMemory(i8);
        if (O("onTrimMemory")) {
            this.f7594b.G(i8);
        }
    }

    @Override // android.app.Activity
    public void onUserLeaveHint() {
        if (O("onUserLeaveHint")) {
            this.f7594b.H();
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z7) {
        super.onWindowFocusChanged(z7);
        if (O("onWindowFocusChanged")) {
            this.f7594b.I(z7);
        }
    }

    @Override // io.flutter.embedding.android.f.c
    public String p() {
        try {
            Bundle J = J();
            if (J != null) {
                return J.getString("io.flutter.EntrypointUri");
            }
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    @Override // io.flutter.embedding.android.f.c
    public io.flutter.plugin.platform.i q(Activity activity, io.flutter.embedding.engine.a aVar) {
        return new io.flutter.plugin.platform.i(getActivity(), aVar.p(), this);
    }

    @Override // io.flutter.embedding.android.f.c
    public void r(l lVar) {
    }

    @Override // io.flutter.embedding.android.f.c
    public String s() {
        String dataString;
        if (K() && "android.intent.action.RUN".equals(getIntent().getAction()) && (dataString = getIntent().getDataString()) != null) {
            return dataString;
        }
        return null;
    }

    @Override // io.flutter.embedding.android.f.c
    public boolean t() {
        try {
            Bundle J = J();
            if (J != null) {
                return J.getBoolean("flutter_deeplinking_enabled");
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    @Override // io.flutter.embedding.android.f.c
    public io.flutter.embedding.engine.l u() {
        return io.flutter.embedding.engine.l.a(getIntent());
    }

    @Override // io.flutter.embedding.android.f.c
    public d0 v() {
        return H() == g.opaque ? d0.surface : d0.texture;
    }

    @Override // io.flutter.embedding.android.f.c
    public boolean w() {
        return true;
    }

    @Override // io.flutter.embedding.android.f.c
    public io.flutter.embedding.engine.a x(Context context) {
        return null;
    }

    @Override // io.flutter.embedding.android.f.c
    public e0 y() {
        return H() == g.opaque ? e0.opaque : e0.transparent;
    }

    @Override // io.flutter.embedding.android.f.c
    public void z(m mVar) {
    }
}
