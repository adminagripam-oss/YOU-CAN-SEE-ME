package io.flutter.embedding.android;

import a6.a;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.window.BackEvent;
import io.flutter.embedding.engine.d;
import io.flutter.plugin.platform.i;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes.dex */
class f implements io.flutter.embedding.android.b {

    /* renamed from: a  reason: collision with root package name */
    private c f7601a;

    /* renamed from: b  reason: collision with root package name */
    private io.flutter.embedding.engine.a f7602b;

    /* renamed from: c  reason: collision with root package name */
    s f7603c;

    /* renamed from: d  reason: collision with root package name */
    private io.flutter.plugin.platform.i f7604d;

    /* renamed from: e  reason: collision with root package name */
    ViewTreeObserver.OnPreDrawListener f7605e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f7606f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f7607g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f7608h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f7609i;

    /* renamed from: j  reason: collision with root package name */
    private Integer f7610j;

    /* renamed from: k  reason: collision with root package name */
    private io.flutter.embedding.engine.d f7611k;

    /* renamed from: l  reason: collision with root package name */
    private final io.flutter.embedding.engine.renderer.l f7612l;

    /* loaded from: classes.dex */
    class a implements io.flutter.embedding.engine.renderer.l {
        a() {
        }

        @Override // io.flutter.embedding.engine.renderer.l
        public void b() {
            f.this.f7601a.b();
            f.this.f7607g = false;
        }

        @Override // io.flutter.embedding.engine.renderer.l
        public void d() {
            f.this.f7601a.d();
            f.this.f7607g = true;
            f.this.f7608h = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements ViewTreeObserver.OnPreDrawListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ s f7614a;

        b(s sVar) {
            this.f7614a = sVar;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            if (f.this.f7607g && f.this.f7605e != null) {
                this.f7614a.getViewTreeObserver().removeOnPreDrawListener(this);
                f.this.f7605e = null;
            }
            return f.this.f7607g;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface c extends i.d {
        void A(io.flutter.embedding.engine.a aVar);

        void b();

        void c();

        void d();

        String f();

        String g();

        Activity getActivity();

        Context getContext();

        androidx.lifecycle.i getLifecycle();

        List h();

        boolean i();

        boolean j();

        boolean k();

        String l();

        boolean m();

        String n();

        void o(io.flutter.embedding.engine.a aVar);

        String p();

        io.flutter.plugin.platform.i q(Activity activity, io.flutter.embedding.engine.a aVar);

        void r(l lVar);

        String s();

        boolean t();

        io.flutter.embedding.engine.l u();

        d0 v();

        boolean w();

        io.flutter.embedding.engine.a x(Context context);

        e0 y();

        void z(m mVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(c cVar) {
        this(cVar, null);
    }

    private d.b g(d.b bVar) {
        String s8 = this.f7601a.s();
        if (s8 == null || s8.isEmpty()) {
            s8 = z5.a.e().c().g();
        }
        a.b bVar2 = new a.b(s8, this.f7601a.n());
        String g8 = this.f7601a.g();
        if (g8 == null && (g8 = q(this.f7601a.getActivity().getIntent())) == null) {
            g8 = "/";
        }
        return bVar.i(bVar2).k(g8).j(this.f7601a.h());
    }

    private void j(s sVar) {
        if (this.f7601a.v() != d0.surface) {
            throw new IllegalArgumentException("Cannot delay the first Android view draw when the render mode is not set to `RenderMode.surface`.");
        }
        if (this.f7605e != null) {
            sVar.getViewTreeObserver().removeOnPreDrawListener(this.f7605e);
        }
        this.f7605e = new b(sVar);
        sVar.getViewTreeObserver().addOnPreDrawListener(this.f7605e);
    }

    private void k() {
        String str;
        if (this.f7601a.l() == null && !this.f7602b.k().j()) {
            String g8 = this.f7601a.g();
            if (g8 == null && (g8 = q(this.f7601a.getActivity().getIntent())) == null) {
                g8 = "/";
            }
            String p8 = this.f7601a.p();
            if (("Executing Dart entrypoint: " + this.f7601a.n() + ", library uri: " + p8) == null) {
                str = "\"\"";
            } else {
                str = p8 + ", and sending initial route: " + g8;
            }
            z5.b.f("FlutterActivityAndFragmentDelegate", str);
            this.f7602b.o().c(g8);
            String s8 = this.f7601a.s();
            if (s8 == null || s8.isEmpty()) {
                s8 = z5.a.e().c().g();
            }
            this.f7602b.k().i(p8 == null ? new a.b(s8, this.f7601a.n()) : new a.b(s8, p8, this.f7601a.n()), this.f7601a.h());
        }
    }

    private void l() {
        if (this.f7601a == null) {
            throw new IllegalStateException("Cannot execute method on a destroyed FlutterActivityAndFragmentDelegate.");
        }
    }

    private String q(Intent intent) {
        Uri data;
        if (!this.f7601a.t() || (data = intent.getData()) == null) {
            return null;
        }
        return data.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void A(int i8, String[] strArr, int[] iArr) {
        l();
        if (this.f7602b == null) {
            z5.b.g("FlutterActivityAndFragmentDelegate", "onRequestPermissionResult() invoked before FlutterFragment was attached to an Activity.");
            return;
        }
        z5.b.f("FlutterActivityAndFragmentDelegate", "Forwarding onRequestPermissionsResult() to FlutterEngine:\nrequestCode: " + i8 + "\npermissions: " + Arrays.toString(strArr) + "\ngrantResults: " + Arrays.toString(iArr));
        this.f7602b.i().a(i8, strArr, iArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void B(Bundle bundle) {
        Bundle bundle2;
        byte[] bArr;
        z5.b.f("FlutterActivityAndFragmentDelegate", "onRestoreInstanceState. Giving framework and plugins an opportunity to restore state.");
        l();
        if (bundle != null) {
            bundle2 = bundle.getBundle("plugins");
            bArr = bundle.getByteArray("framework");
        } else {
            bundle2 = null;
            bArr = null;
        }
        if (this.f7601a.m()) {
            this.f7602b.u().j(bArr);
        }
        if (this.f7601a.i()) {
            this.f7602b.i().f(bundle2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void C() {
        io.flutter.embedding.engine.a aVar;
        z5.b.f("FlutterActivityAndFragmentDelegate", "onResume()");
        l();
        if (!this.f7601a.k() || (aVar = this.f7602b) == null) {
            return;
        }
        aVar.l().e();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void D(Bundle bundle) {
        z5.b.f("FlutterActivityAndFragmentDelegate", "onSaveInstanceState. Giving framework and plugins an opportunity to save state.");
        l();
        if (this.f7601a.m()) {
            bundle.putByteArray("framework", this.f7602b.u().h());
        }
        if (this.f7601a.i()) {
            Bundle bundle2 = new Bundle();
            this.f7602b.i().h(bundle2);
            bundle.putBundle("plugins", bundle2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void E() {
        z5.b.f("FlutterActivityAndFragmentDelegate", "onStart()");
        l();
        k();
        Integer num = this.f7610j;
        if (num != null) {
            this.f7603c.setVisibility(num.intValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void F() {
        io.flutter.embedding.engine.a aVar;
        z5.b.f("FlutterActivityAndFragmentDelegate", "onStop()");
        l();
        if (this.f7601a.k() && (aVar = this.f7602b) != null) {
            aVar.l().d();
        }
        this.f7610j = Integer.valueOf(this.f7603c.getVisibility());
        this.f7603c.setVisibility(8);
        io.flutter.embedding.engine.a aVar2 = this.f7602b;
        if (aVar2 != null) {
            aVar2.t().p(40);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void G(int i8) {
        l();
        io.flutter.embedding.engine.a aVar = this.f7602b;
        if (aVar != null) {
            if (this.f7608h && i8 >= 10) {
                aVar.k().k();
                this.f7602b.x().a();
            }
            this.f7602b.t().p(i8);
            this.f7602b.q().o0(i8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void H() {
        l();
        if (this.f7602b == null) {
            z5.b.g("FlutterActivityAndFragmentDelegate", "onUserLeaveHint() invoked before FlutterFragment was attached to an Activity.");
            return;
        }
        z5.b.f("FlutterActivityAndFragmentDelegate", "Forwarding onUserLeaveHint() to FlutterEngine.");
        this.f7602b.i().i();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void I(boolean z7) {
        io.flutter.embedding.engine.a aVar;
        l();
        StringBuilder sb = new StringBuilder();
        sb.append("Received onWindowFocusChanged: ");
        sb.append(z7 ? "true" : "false");
        z5.b.f("FlutterActivityAndFragmentDelegate", sb.toString());
        if (!this.f7601a.k() || (aVar = this.f7602b) == null) {
            return;
        }
        if (z7) {
            aVar.l().a();
        } else {
            aVar.l().f();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void J() {
        this.f7601a = null;
        this.f7602b = null;
        this.f7603c = null;
        this.f7604d = null;
    }

    void K() {
        io.flutter.embedding.engine.a a8;
        z5.b.f("FlutterActivityAndFragmentDelegate", "Setting up FlutterEngine.");
        String l8 = this.f7601a.l();
        if (l8 != null) {
            io.flutter.embedding.engine.a a9 = io.flutter.embedding.engine.b.b().a(l8);
            this.f7602b = a9;
            this.f7606f = true;
            if (a9 != null) {
                return;
            }
            throw new IllegalStateException("The requested cached FlutterEngine did not exist in the FlutterEngineCache: '" + l8 + "'");
        }
        c cVar = this.f7601a;
        io.flutter.embedding.engine.a x7 = cVar.x(cVar.getContext());
        this.f7602b = x7;
        if (x7 != null) {
            this.f7606f = true;
            return;
        }
        String f8 = this.f7601a.f();
        if (f8 != null) {
            io.flutter.embedding.engine.d a10 = io.flutter.embedding.engine.e.b().a(f8);
            if (a10 == null) {
                throw new IllegalStateException("The requested cached FlutterEngineGroup did not exist in the FlutterEngineGroupCache: '" + f8 + "'");
            }
            a8 = a10.a(g(new d.b(this.f7601a.getContext())));
        } else {
            z5.b.f("FlutterActivityAndFragmentDelegate", "No preferred FlutterEngine was provided. Creating a new FlutterEngine for this FlutterFragment.");
            io.flutter.embedding.engine.d dVar = this.f7611k;
            if (dVar == null) {
                dVar = new io.flutter.embedding.engine.d(this.f7601a.getContext(), this.f7601a.u().b());
            }
            a8 = dVar.a(g(new d.b(this.f7601a.getContext()).h(false).l(this.f7601a.m())));
        }
        this.f7602b = a8;
        this.f7606f = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void L(BackEvent backEvent) {
        l();
        if (this.f7602b == null) {
            z5.b.g("FlutterActivityAndFragmentDelegate", "Invoked startBackGesture() before FlutterFragment was attached to an Activity.");
            return;
        }
        z5.b.f("FlutterActivityAndFragmentDelegate", "Forwarding startBackGesture() to FlutterEngine.");
        this.f7602b.j().d(backEvent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void M(BackEvent backEvent) {
        l();
        if (this.f7602b == null) {
            z5.b.g("FlutterActivityAndFragmentDelegate", "Invoked updateBackGestureProgress() before FlutterFragment was attached to an Activity.");
            return;
        }
        z5.b.f("FlutterActivityAndFragmentDelegate", "Forwarding updateBackGestureProgress() to FlutterEngine.");
        this.f7602b.j().e(backEvent);
    }

    void N() {
        io.flutter.plugin.platform.i iVar = this.f7604d;
        if (iVar != null) {
            iVar.E();
        }
    }

    @Override // io.flutter.embedding.android.b
    public void c() {
        if (!this.f7601a.j()) {
            this.f7601a.c();
            return;
        }
        throw new AssertionError("The internal FlutterEngine created by " + this.f7601a + " has been attached to by another activity. To persist a FlutterEngine beyond the ownership of this activity, explicitly create a FlutterEngine");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h() {
        l();
        if (this.f7602b == null) {
            z5.b.g("FlutterActivityAndFragmentDelegate", "Invoked cancelBackGesture() before FlutterFragment was attached to an Activity.");
            return;
        }
        z5.b.f("FlutterActivityAndFragmentDelegate", "Forwarding cancelBackGesture() to FlutterEngine.");
        this.f7602b.j().b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i() {
        l();
        if (this.f7602b == null) {
            z5.b.g("FlutterActivityAndFragmentDelegate", "Invoked commitBackGesture() before FlutterFragment was attached to an Activity.");
            return;
        }
        z5.b.f("FlutterActivityAndFragmentDelegate", "Forwarding commitBackGesture() to FlutterEngine.");
        this.f7602b.j().c();
    }

    @Override // io.flutter.embedding.android.b
    /* renamed from: m */
    public Activity d() {
        Activity activity = this.f7601a.getActivity();
        if (activity != null) {
            return activity;
        }
        throw new AssertionError("FlutterActivityAndFragmentDelegate's getAppComponent should only be queried after onAttach, when the host's activity should always be non-null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public io.flutter.embedding.engine.a n() {
        return this.f7602b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean o() {
        return this.f7609i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean p() {
        return this.f7606f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void r(int i8, int i9, Intent intent) {
        l();
        if (this.f7602b == null) {
            z5.b.g("FlutterActivityAndFragmentDelegate", "onActivityResult() invoked before FlutterFragment was attached to an Activity.");
            return;
        }
        z5.b.f("FlutterActivityAndFragmentDelegate", "Forwarding onActivityResult() to FlutterEngine:\nrequestCode: " + i8 + "\nresultCode: " + i9 + "\ndata: " + intent);
        this.f7602b.i().onActivityResult(i8, i9, intent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void s(Context context) {
        l();
        if (this.f7602b == null) {
            K();
        }
        if (this.f7601a.i()) {
            z5.b.f("FlutterActivityAndFragmentDelegate", "Attaching FlutterEngine to the Activity that owns this delegate.");
            this.f7602b.i().b(this, this.f7601a.getLifecycle());
        }
        c cVar = this.f7601a;
        this.f7604d = cVar.q(cVar.getActivity(), this.f7602b);
        this.f7601a.A(this.f7602b);
        this.f7609i = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void t() {
        l();
        if (this.f7602b == null) {
            z5.b.g("FlutterActivityAndFragmentDelegate", "Invoked onBackPressed() before FlutterFragment was attached to an Activity.");
            return;
        }
        z5.b.f("FlutterActivityAndFragmentDelegate", "Forwarding onBackPressed() to FlutterEngine.");
        this.f7602b.o().a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View u(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle, int i8, boolean z7) {
        s sVar;
        z5.b.f("FlutterActivityAndFragmentDelegate", "Creating FlutterView.");
        l();
        if (this.f7601a.v() == d0.surface) {
            l lVar = new l(this.f7601a.getContext(), this.f7601a.y() == e0.transparent);
            this.f7601a.r(lVar);
            sVar = new s(this.f7601a.getContext(), lVar);
        } else {
            m mVar = new m(this.f7601a.getContext());
            mVar.setOpaque(this.f7601a.y() == e0.opaque);
            this.f7601a.z(mVar);
            sVar = new s(this.f7601a.getContext(), mVar);
        }
        this.f7603c = sVar;
        this.f7603c.l(this.f7612l);
        if (this.f7601a.w()) {
            z5.b.f("FlutterActivityAndFragmentDelegate", "Attaching FlutterEngine to FlutterView.");
            this.f7603c.n(this.f7602b);
        }
        this.f7603c.setId(i8);
        if (z7) {
            j(this.f7603c);
        }
        return this.f7603c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void v() {
        z5.b.f("FlutterActivityAndFragmentDelegate", "onDestroyView()");
        l();
        if (this.f7605e != null) {
            this.f7603c.getViewTreeObserver().removeOnPreDrawListener(this.f7605e);
            this.f7605e = null;
        }
        s sVar = this.f7603c;
        if (sVar != null) {
            sVar.s();
            this.f7603c.y(this.f7612l);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void w() {
        io.flutter.embedding.engine.a aVar;
        if (this.f7609i) {
            z5.b.f("FlutterActivityAndFragmentDelegate", "onDetach()");
            l();
            this.f7601a.o(this.f7602b);
            if (this.f7601a.i()) {
                z5.b.f("FlutterActivityAndFragmentDelegate", "Detaching FlutterEngine from the Activity that owns this Fragment.");
                if (this.f7601a.getActivity().isChangingConfigurations()) {
                    this.f7602b.i().c();
                } else {
                    this.f7602b.i().g();
                }
            }
            io.flutter.plugin.platform.i iVar = this.f7604d;
            if (iVar != null) {
                iVar.q();
                this.f7604d = null;
            }
            if (this.f7601a.k() && (aVar = this.f7602b) != null) {
                aVar.l().b();
            }
            if (this.f7601a.j()) {
                this.f7602b.g();
                if (this.f7601a.l() != null) {
                    io.flutter.embedding.engine.b.b().d(this.f7601a.l());
                }
                this.f7602b = null;
            }
            this.f7609i = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void x(Intent intent) {
        l();
        if (this.f7602b == null) {
            z5.b.g("FlutterActivityAndFragmentDelegate", "onNewIntent() invoked before FlutterFragment was attached to an Activity.");
            return;
        }
        z5.b.f("FlutterActivityAndFragmentDelegate", "Forwarding onNewIntent() to FlutterEngine and sending pushRouteInformation message.");
        this.f7602b.i().d(intent);
        String q8 = q(intent);
        if (q8 == null || q8.isEmpty()) {
            return;
        }
        this.f7602b.o().b(q8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void y() {
        io.flutter.embedding.engine.a aVar;
        z5.b.f("FlutterActivityAndFragmentDelegate", "onPause()");
        l();
        if (!this.f7601a.k() || (aVar = this.f7602b) == null) {
            return;
        }
        aVar.l().c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void z() {
        z5.b.f("FlutterActivityAndFragmentDelegate", "onPostResume()");
        l();
        if (this.f7602b == null) {
            z5.b.g("FlutterActivityAndFragmentDelegate", "onPostResume() invoked before FlutterFragment was attached to an Activity.");
            return;
        }
        N();
        this.f7602b.q().n0();
    }

    f(c cVar, io.flutter.embedding.engine.d dVar) {
        this.f7612l = new a();
        this.f7601a = cVar;
        this.f7608h = false;
        this.f7611k = dVar;
    }
}
