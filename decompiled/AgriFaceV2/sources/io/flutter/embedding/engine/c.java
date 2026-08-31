package io.flutter.embedding.engine;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.w;
import e6.a;
import i6.m;
import io.flutter.embedding.engine.plugins.lifecycle.HiddenLifecycleReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class c implements e6.b, f6.b {

    /* renamed from: b  reason: collision with root package name */
    private final io.flutter.embedding.engine.a f7740b;

    /* renamed from: c  reason: collision with root package name */
    private final a.b f7741c;

    /* renamed from: e  reason: collision with root package name */
    private io.flutter.embedding.android.b f7743e;

    /* renamed from: f  reason: collision with root package name */
    private C0107c f7744f;

    /* renamed from: i  reason: collision with root package name */
    private Service f7747i;

    /* renamed from: k  reason: collision with root package name */
    private BroadcastReceiver f7749k;

    /* renamed from: m  reason: collision with root package name */
    private ContentProvider f7751m;

    /* renamed from: a  reason: collision with root package name */
    private final Map f7739a = new HashMap();

    /* renamed from: d  reason: collision with root package name */
    private final Map f7742d = new HashMap();

    /* renamed from: g  reason: collision with root package name */
    private boolean f7745g = false;

    /* renamed from: h  reason: collision with root package name */
    private final Map f7746h = new HashMap();

    /* renamed from: j  reason: collision with root package name */
    private final Map f7748j = new HashMap();

    /* renamed from: l  reason: collision with root package name */
    private final Map f7750l = new HashMap();

    /* loaded from: classes.dex */
    private static class b implements a.InterfaceC0083a {

        /* renamed from: a  reason: collision with root package name */
        final c6.d f7752a;

        private b(c6.d dVar) {
            this.f7752a = dVar;
        }

        @Override // e6.a.InterfaceC0083a
        public String a(String str) {
            return this.f7752a.i(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: io.flutter.embedding.engine.c$c  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0107c implements f6.c {

        /* renamed from: a  reason: collision with root package name */
        private final Activity f7753a;

        /* renamed from: b  reason: collision with root package name */
        private final HiddenLifecycleReference f7754b;

        /* renamed from: c  reason: collision with root package name */
        private final Set f7755c = new HashSet();

        /* renamed from: d  reason: collision with root package name */
        private final Set f7756d = new HashSet();

        /* renamed from: e  reason: collision with root package name */
        private final Set f7757e = new HashSet();

        /* renamed from: f  reason: collision with root package name */
        private final Set f7758f = new HashSet();

        /* renamed from: g  reason: collision with root package name */
        private final Set f7759g = new HashSet();

        /* renamed from: h  reason: collision with root package name */
        private final Set f7760h = new HashSet();

        public C0107c(Activity activity, androidx.lifecycle.i iVar) {
            this.f7753a = activity;
            this.f7754b = new HiddenLifecycleReference(iVar);
        }

        @Override // f6.c
        public void a(i6.l lVar) {
            this.f7756d.add(lVar);
        }

        @Override // f6.c
        public void b(m mVar) {
            this.f7755c.add(mVar);
        }

        @Override // f6.c
        public void c(i6.l lVar) {
            this.f7756d.remove(lVar);
        }

        @Override // f6.c
        public void d(m mVar) {
            this.f7755c.remove(mVar);
        }

        boolean e(int i8, int i9, Intent intent) {
            Iterator it = new HashSet(this.f7756d).iterator();
            while (true) {
                boolean z7 = false;
                while (it.hasNext()) {
                    if (((i6.l) it.next()).onActivityResult(i8, i9, intent) || z7) {
                        z7 = true;
                    }
                }
                return z7;
            }
        }

        void f(Intent intent) {
            Iterator it = this.f7757e.iterator();
            if (it.hasNext()) {
                w.a(it.next());
                throw null;
            }
        }

        boolean g(int i8, String[] strArr, int[] iArr) {
            boolean z7;
            while (true) {
                for (m mVar : this.f7755c) {
                    z7 = mVar.a(i8, strArr, iArr) || z7;
                }
                return z7;
            }
        }

        @Override // f6.c
        public Activity getActivity() {
            return this.f7753a;
        }

        void h(Bundle bundle) {
            Iterator it = this.f7760h.iterator();
            if (it.hasNext()) {
                w.a(it.next());
                throw null;
            }
        }

        void i(Bundle bundle) {
            Iterator it = this.f7760h.iterator();
            if (it.hasNext()) {
                w.a(it.next());
                throw null;
            }
        }

        void j() {
            Iterator it = this.f7758f.iterator();
            if (it.hasNext()) {
                w.a(it.next());
                throw null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(Context context, io.flutter.embedding.engine.a aVar, c6.d dVar, d dVar2) {
        this.f7740b = aVar;
        this.f7741c = new a.b(context, aVar, aVar.k(), aVar.t(), aVar.q().W(), new b(dVar), dVar2);
    }

    private void j(Activity activity, androidx.lifecycle.i iVar) {
        this.f7744f = new C0107c(activity, iVar);
        this.f7740b.q().u0(activity.getIntent() != null ? activity.getIntent().getBooleanExtra("enable-software-rendering", false) : false);
        this.f7740b.q().C(activity, this.f7740b.t(), this.f7740b.k());
        for (f6.a aVar : this.f7742d.values()) {
            if (this.f7745g) {
                aVar.onReattachedToActivityForConfigChanges(this.f7744f);
            } else {
                aVar.onAttachedToActivity(this.f7744f);
            }
        }
        this.f7745g = false;
    }

    private void l() {
        this.f7740b.q().O();
        this.f7743e = null;
        this.f7744f = null;
    }

    private void m() {
        if (r()) {
            g();
        } else if (u()) {
            p();
        } else if (s()) {
            n();
        } else if (t()) {
            o();
        }
    }

    private boolean r() {
        return this.f7743e != null;
    }

    private boolean s() {
        return this.f7749k != null;
    }

    private boolean t() {
        return this.f7751m != null;
    }

    private boolean u() {
        return this.f7747i != null;
    }

    @Override // f6.b
    public boolean a(int i8, String[] strArr, int[] iArr) {
        if (!r()) {
            z5.b.b("FlutterEngineCxnRegstry", "Attempted to notify ActivityAware plugins of onRequestPermissionsResult, but no Activity was attached.");
            return false;
        }
        p6.e j8 = p6.e.j("FlutterEngineConnectionRegistry#onRequestPermissionsResult");
        try {
            boolean g8 = this.f7744f.g(i8, strArr, iArr);
            if (j8 != null) {
                j8.close();
            }
            return g8;
        } catch (Throwable th) {
            if (j8 != null) {
                try {
                    j8.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @Override // f6.b
    public void b(io.flutter.embedding.android.b bVar, androidx.lifecycle.i iVar) {
        p6.e j8 = p6.e.j("FlutterEngineConnectionRegistry#attachToActivity");
        try {
            io.flutter.embedding.android.b bVar2 = this.f7743e;
            if (bVar2 != null) {
                bVar2.c();
            }
            m();
            this.f7743e = bVar;
            j((Activity) bVar.d(), iVar);
            if (j8 != null) {
                j8.close();
            }
        } catch (Throwable th) {
            if (j8 != null) {
                try {
                    j8.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @Override // f6.b
    public void c() {
        if (!r()) {
            z5.b.b("FlutterEngineCxnRegstry", "Attempted to detach plugins from an Activity when no Activity was attached.");
            return;
        }
        p6.e j8 = p6.e.j("FlutterEngineConnectionRegistry#detachFromActivityForConfigChanges");
        try {
            this.f7745g = true;
            for (f6.a aVar : this.f7742d.values()) {
                aVar.onDetachedFromActivityForConfigChanges();
            }
            l();
            if (j8 != null) {
                j8.close();
            }
        } catch (Throwable th) {
            if (j8 != null) {
                try {
                    j8.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @Override // f6.b
    public void d(Intent intent) {
        if (!r()) {
            z5.b.b("FlutterEngineCxnRegstry", "Attempted to notify ActivityAware plugins of onNewIntent, but no Activity was attached.");
            return;
        }
        p6.e j8 = p6.e.j("FlutterEngineConnectionRegistry#onNewIntent");
        try {
            this.f7744f.f(intent);
            if (j8 != null) {
                j8.close();
            }
        } catch (Throwable th) {
            if (j8 != null) {
                try {
                    j8.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @Override // e6.b
    public void e(e6.a aVar) {
        p6.e j8 = p6.e.j("FlutterEngineConnectionRegistry#add " + aVar.getClass().getSimpleName());
        try {
            if (q(aVar.getClass())) {
                z5.b.g("FlutterEngineCxnRegstry", "Attempted to register plugin (" + aVar + ") but it was already registered with this FlutterEngine (" + this.f7740b + ").");
                if (j8 != null) {
                    j8.close();
                    return;
                }
                return;
            }
            z5.b.f("FlutterEngineCxnRegstry", "Adding plugin: " + aVar);
            this.f7739a.put(aVar.getClass(), aVar);
            aVar.onAttachedToEngine(this.f7741c);
            if (aVar instanceof f6.a) {
                f6.a aVar2 = (f6.a) aVar;
                this.f7742d.put(aVar.getClass(), aVar2);
                if (r()) {
                    aVar2.onAttachedToActivity(this.f7744f);
                }
            }
            if (j8 != null) {
                j8.close();
            }
        } catch (Throwable th) {
            if (j8 != null) {
                try {
                    j8.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @Override // f6.b
    public void f(Bundle bundle) {
        if (!r()) {
            z5.b.b("FlutterEngineCxnRegstry", "Attempted to notify ActivityAware plugins of onRestoreInstanceState, but no Activity was attached.");
            return;
        }
        p6.e j8 = p6.e.j("FlutterEngineConnectionRegistry#onRestoreInstanceState");
        try {
            this.f7744f.h(bundle);
            if (j8 != null) {
                j8.close();
            }
        } catch (Throwable th) {
            if (j8 != null) {
                try {
                    j8.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @Override // f6.b
    public void g() {
        if (!r()) {
            z5.b.b("FlutterEngineCxnRegstry", "Attempted to detach plugins from an Activity when no Activity was attached.");
            return;
        }
        p6.e j8 = p6.e.j("FlutterEngineConnectionRegistry#detachFromActivity");
        try {
            for (f6.a aVar : this.f7742d.values()) {
                aVar.onDetachedFromActivity();
            }
            l();
            if (j8 != null) {
                j8.close();
            }
        } catch (Throwable th) {
            if (j8 != null) {
                try {
                    j8.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @Override // f6.b
    public void h(Bundle bundle) {
        if (!r()) {
            z5.b.b("FlutterEngineCxnRegstry", "Attempted to notify ActivityAware plugins of onSaveInstanceState, but no Activity was attached.");
            return;
        }
        p6.e j8 = p6.e.j("FlutterEngineConnectionRegistry#onSaveInstanceState");
        try {
            this.f7744f.i(bundle);
            if (j8 != null) {
                j8.close();
            }
        } catch (Throwable th) {
            if (j8 != null) {
                try {
                    j8.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @Override // f6.b
    public void i() {
        if (!r()) {
            z5.b.b("FlutterEngineCxnRegstry", "Attempted to notify ActivityAware plugins of onUserLeaveHint, but no Activity was attached.");
            return;
        }
        p6.e j8 = p6.e.j("FlutterEngineConnectionRegistry#onUserLeaveHint");
        try {
            this.f7744f.j();
            if (j8 != null) {
                j8.close();
            }
        } catch (Throwable th) {
            if (j8 != null) {
                try {
                    j8.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public void k() {
        z5.b.f("FlutterEngineCxnRegstry", "Destroying.");
        m();
        x();
    }

    public void n() {
        if (!s()) {
            z5.b.b("FlutterEngineCxnRegstry", "Attempted to detach plugins from a BroadcastReceiver when no BroadcastReceiver was attached.");
            return;
        }
        p6.e j8 = p6.e.j("FlutterEngineConnectionRegistry#detachFromBroadcastReceiver");
        try {
            Iterator it = this.f7748j.values().iterator();
            if (it.hasNext()) {
                w.a(it.next());
                throw null;
            } else if (j8 != null) {
                j8.close();
            }
        } catch (Throwable th) {
            if (j8 != null) {
                try {
                    j8.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public void o() {
        if (!t()) {
            z5.b.b("FlutterEngineCxnRegstry", "Attempted to detach plugins from a ContentProvider when no ContentProvider was attached.");
            return;
        }
        p6.e j8 = p6.e.j("FlutterEngineConnectionRegistry#detachFromContentProvider");
        try {
            Iterator it = this.f7750l.values().iterator();
            if (it.hasNext()) {
                w.a(it.next());
                throw null;
            } else if (j8 != null) {
                j8.close();
            }
        } catch (Throwable th) {
            if (j8 != null) {
                try {
                    j8.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @Override // f6.b
    public boolean onActivityResult(int i8, int i9, Intent intent) {
        if (!r()) {
            z5.b.b("FlutterEngineCxnRegstry", "Attempted to notify ActivityAware plugins of onActivityResult, but no Activity was attached.");
            return false;
        }
        p6.e j8 = p6.e.j("FlutterEngineConnectionRegistry#onActivityResult");
        try {
            boolean e8 = this.f7744f.e(i8, i9, intent);
            if (j8 != null) {
                j8.close();
            }
            return e8;
        } catch (Throwable th) {
            if (j8 != null) {
                try {
                    j8.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public void p() {
        if (!u()) {
            z5.b.b("FlutterEngineCxnRegstry", "Attempted to detach plugins from a Service when no Service was attached.");
            return;
        }
        p6.e j8 = p6.e.j("FlutterEngineConnectionRegistry#detachFromService");
        try {
            Iterator it = this.f7746h.values().iterator();
            if (it.hasNext()) {
                w.a(it.next());
                throw null;
            }
            this.f7747i = null;
            if (j8 != null) {
                j8.close();
            }
        } catch (Throwable th) {
            if (j8 != null) {
                try {
                    j8.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public boolean q(Class cls) {
        return this.f7739a.containsKey(cls);
    }

    public void v(Class cls) {
        e6.a aVar = (e6.a) this.f7739a.get(cls);
        if (aVar == null) {
            return;
        }
        p6.e j8 = p6.e.j("FlutterEngineConnectionRegistry#remove " + cls.getSimpleName());
        try {
            if (aVar instanceof f6.a) {
                if (r()) {
                    ((f6.a) aVar).onDetachedFromActivity();
                }
                this.f7742d.remove(cls);
            }
            aVar.onDetachedFromEngine(this.f7741c);
            this.f7739a.remove(cls);
            if (j8 != null) {
                j8.close();
            }
        } catch (Throwable th) {
            if (j8 != null) {
                try {
                    j8.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public void w(Set set) {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            v((Class) it.next());
        }
    }

    public void x() {
        w(new HashSet(this.f7739a.keySet()));
        this.f7739a.clear();
    }
}
