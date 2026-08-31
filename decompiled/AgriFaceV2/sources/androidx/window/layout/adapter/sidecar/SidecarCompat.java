package androidx.window.layout.adapter.sidecar;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.window.layout.adapter.sidecar.SidecarCompat;
import androidx.window.layout.adapter.sidecar.a;
import androidx.window.sidecar.SidecarDeviceState;
import androidx.window.sidecar.SidecarDisplayFeature;
import androidx.window.sidecar.SidecarInterface;
import androidx.window.sidecar.SidecarProvider;
import androidx.window.sidecar.SidecarWindowLayoutInfo;
import d7.g;
import d7.k;
import h2.j;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantLock;
import r6.r;
import s6.n;
/* loaded from: classes.dex */
public final class SidecarCompat implements androidx.window.layout.adapter.sidecar.a {

    /* renamed from: f  reason: collision with root package name */
    public static final a f4100f = new a(null);

    /* renamed from: a  reason: collision with root package name */
    private final SidecarInterface f4101a;

    /* renamed from: b  reason: collision with root package name */
    private final k2.a f4102b;

    /* renamed from: c  reason: collision with root package name */
    private final Map f4103c;

    /* renamed from: d  reason: collision with root package name */
    private final Map f4104d;

    /* renamed from: e  reason: collision with root package name */
    private b f4105e;

    /* loaded from: classes.dex */
    public final class TranslatingCallback implements SidecarInterface.SidecarCallback {
        public TranslatingCallback() {
        }

        public void onDeviceStateChanged(SidecarDeviceState sidecarDeviceState) {
            SidecarInterface h8;
            k.e(sidecarDeviceState, "newDeviceState");
            SidecarCompat sidecarCompat = SidecarCompat.this;
            for (Activity activity : SidecarCompat.this.f4103c.values()) {
                IBinder a8 = SidecarCompat.f4100f.a(activity);
                SidecarWindowLayoutInfo sidecarWindowLayoutInfo = null;
                if (a8 != null && (h8 = sidecarCompat.h()) != null) {
                    sidecarWindowLayoutInfo = h8.getWindowLayoutInfo(a8);
                }
                b bVar = sidecarCompat.f4105e;
                if (bVar != null) {
                    bVar.a(activity, sidecarCompat.f4102b.e(sidecarWindowLayoutInfo, sidecarDeviceState));
                }
            }
        }

        public void onWindowLayoutChanged(IBinder iBinder, SidecarWindowLayoutInfo sidecarWindowLayoutInfo) {
            SidecarDeviceState sidecarDeviceState;
            k.e(iBinder, "windowToken");
            k.e(sidecarWindowLayoutInfo, "newLayout");
            Activity activity = (Activity) SidecarCompat.this.f4103c.get(iBinder);
            if (activity == null) {
                Log.w("SidecarCompat", "Unable to resolve activity from window token. Missing a call to #onWindowLayoutChangeListenerAdded()?");
                return;
            }
            k2.a aVar = SidecarCompat.this.f4102b;
            SidecarInterface h8 = SidecarCompat.this.h();
            if (h8 == null || (sidecarDeviceState = h8.getDeviceState()) == null) {
                sidecarDeviceState = new SidecarDeviceState();
            }
            j e8 = aVar.e(sidecarWindowLayoutInfo, sidecarDeviceState);
            b bVar = SidecarCompat.this.f4105e;
            if (bVar != null) {
                bVar.a(activity, e8);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(g gVar) {
            this();
        }

        public final IBinder a(Activity activity) {
            Window window;
            WindowManager.LayoutParams attributes;
            if (activity == null || (window = activity.getWindow()) == null || (attributes = window.getAttributes()) == null) {
                return null;
            }
            return attributes.token;
        }

        public final SidecarInterface b(Context context) {
            k.e(context, "context");
            return SidecarProvider.getSidecarImpl(context.getApplicationContext());
        }

        public final e2.k c() {
            try {
                String apiVersion = SidecarProvider.getApiVersion();
                if (TextUtils.isEmpty(apiVersion)) {
                    return null;
                }
                return e2.k.f6590j.b(apiVersion);
            } catch (NoClassDefFoundError | UnsupportedOperationException unused) {
                return null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class b implements a.InterfaceC0054a {

        /* renamed from: a  reason: collision with root package name */
        private final a.InterfaceC0054a f4107a;

        /* renamed from: b  reason: collision with root package name */
        private final ReentrantLock f4108b;

        /* renamed from: c  reason: collision with root package name */
        private final WeakHashMap f4109c;

        public b(a.InterfaceC0054a interfaceC0054a) {
            k.e(interfaceC0054a, "callbackInterface");
            this.f4107a = interfaceC0054a;
            this.f4108b = new ReentrantLock();
            this.f4109c = new WeakHashMap();
        }

        @Override // androidx.window.layout.adapter.sidecar.a.InterfaceC0054a
        public void a(Activity activity, j jVar) {
            k.e(activity, "activity");
            k.e(jVar, "newLayout");
            ReentrantLock reentrantLock = this.f4108b;
            reentrantLock.lock();
            try {
                if (k.a(jVar, (j) this.f4109c.get(activity))) {
                    return;
                }
                j jVar2 = (j) this.f4109c.put(activity, jVar);
                reentrantLock.unlock();
                this.f4107a.a(activity, jVar);
            } finally {
                reentrantLock.unlock();
            }
        }

        public final void b(Activity activity) {
            k.e(activity, "activity");
            ReentrantLock reentrantLock = this.f4108b;
            reentrantLock.lock();
            try {
                this.f4109c.put(activity, null);
                r rVar = r.f11372a;
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    /* loaded from: classes.dex */
    private static final class c implements View.OnAttachStateChangeListener {

        /* renamed from: a  reason: collision with root package name */
        private final SidecarCompat f4110a;

        /* renamed from: b  reason: collision with root package name */
        private final WeakReference f4111b;

        public c(SidecarCompat sidecarCompat, Activity activity) {
            k.e(sidecarCompat, "sidecarCompat");
            k.e(activity, "activity");
            this.f4110a = sidecarCompat;
            this.f4111b = new WeakReference(activity);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            k.e(view, "view");
            view.removeOnAttachStateChangeListener(this);
            Activity activity = (Activity) this.f4111b.get();
            IBinder a8 = SidecarCompat.f4100f.a(activity);
            if (activity == null || a8 == null) {
                return;
            }
            this.f4110a.j(a8, activity);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            k.e(view, "view");
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SidecarCompat(Context context) {
        this(f4100f.b(context), new k2.a(null, 1, null));
        k.e(context, "context");
    }

    private final void k(final Activity activity) {
        if (this.f4104d.get(activity) == null && (activity instanceof androidx.core.content.c)) {
            g1.a aVar = new g1.a() { // from class: k2.b
                @Override // g1.a
                public final void accept(Object obj) {
                    SidecarCompat.l(SidecarCompat.this, activity, (Configuration) obj);
                }
            };
            this.f4104d.put(activity, aVar);
            ((androidx.core.content.c) activity).addOnConfigurationChangedListener(aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void l(SidecarCompat sidecarCompat, Activity activity, Configuration configuration) {
        k.e(sidecarCompat, "this$0");
        k.e(activity, "$activity");
        b bVar = sidecarCompat.f4105e;
        if (bVar != null) {
            bVar.a(activity, sidecarCompat.i(activity));
        }
    }

    private final void m(Activity activity) {
        g1.a aVar = (g1.a) this.f4104d.get(activity);
        if (aVar == null) {
            return;
        }
        if (activity instanceof androidx.core.content.c) {
            ((androidx.core.content.c) activity).removeOnConfigurationChangedListener(aVar);
        }
        this.f4104d.remove(activity);
    }

    @Override // androidx.window.layout.adapter.sidecar.a
    public void a(a.InterfaceC0054a interfaceC0054a) {
        k.e(interfaceC0054a, "extensionCallback");
        this.f4105e = new b(interfaceC0054a);
        SidecarInterface sidecarInterface = this.f4101a;
        if (sidecarInterface != null) {
            sidecarInterface.setSidecarCallback(new DistinctElementSidecarCallback(this.f4102b, new TranslatingCallback()));
        }
    }

    @Override // androidx.window.layout.adapter.sidecar.a
    public void b(Activity activity) {
        k.e(activity, "activity");
        IBinder a8 = f4100f.a(activity);
        if (a8 != null) {
            j(a8, activity);
            return;
        }
        activity.getWindow().getDecorView().addOnAttachStateChangeListener(new c(this, activity));
    }

    @Override // androidx.window.layout.adapter.sidecar.a
    public void c(Activity activity) {
        SidecarInterface sidecarInterface;
        k.e(activity, "activity");
        IBinder a8 = f4100f.a(activity);
        if (a8 == null) {
            return;
        }
        SidecarInterface sidecarInterface2 = this.f4101a;
        if (sidecarInterface2 != null) {
            sidecarInterface2.onWindowLayoutChangeListenerRemoved(a8);
        }
        m(activity);
        b bVar = this.f4105e;
        if (bVar != null) {
            bVar.b(activity);
        }
        boolean z7 = this.f4103c.size() == 1;
        this.f4103c.remove(a8);
        if (!z7 || (sidecarInterface = this.f4101a) == null) {
            return;
        }
        sidecarInterface.onDeviceStateListenersChanged(true);
    }

    public final SidecarInterface h() {
        return this.f4101a;
    }

    public final j i(Activity activity) {
        SidecarDeviceState sidecarDeviceState;
        List f8;
        k.e(activity, "activity");
        IBinder a8 = f4100f.a(activity);
        if (a8 == null) {
            f8 = n.f();
            return new j(f8);
        }
        SidecarInterface sidecarInterface = this.f4101a;
        SidecarWindowLayoutInfo windowLayoutInfo = sidecarInterface != null ? sidecarInterface.getWindowLayoutInfo(a8) : null;
        k2.a aVar = this.f4102b;
        SidecarInterface sidecarInterface2 = this.f4101a;
        if (sidecarInterface2 == null || (sidecarDeviceState = sidecarInterface2.getDeviceState()) == null) {
            sidecarDeviceState = new SidecarDeviceState();
        }
        return aVar.e(windowLayoutInfo, sidecarDeviceState);
    }

    public final void j(IBinder iBinder, Activity activity) {
        SidecarInterface sidecarInterface;
        k.e(iBinder, "windowToken");
        k.e(activity, "activity");
        this.f4103c.put(iBinder, activity);
        SidecarInterface sidecarInterface2 = this.f4101a;
        if (sidecarInterface2 != null) {
            sidecarInterface2.onWindowLayoutChangeListenerAdded(iBinder);
        }
        if (this.f4103c.size() == 1 && (sidecarInterface = this.f4101a) != null) {
            sidecarInterface.onDeviceStateListenersChanged(false);
        }
        b bVar = this.f4105e;
        if (bVar != null) {
            bVar.a(activity, i(activity));
        }
        k(activity);
    }

    public boolean n() {
        Class<?> cls;
        Class<?> cls2;
        Class<?> cls3;
        Class<?> cls4;
        try {
            SidecarInterface sidecarInterface = this.f4101a;
            Method method = (sidecarInterface == null || (cls4 = sidecarInterface.getClass()) == null) ? null : cls4.getMethod("setSidecarCallback", SidecarInterface.SidecarCallback.class);
            Class<?> returnType = method != null ? method.getReturnType() : null;
            if (!k.a(returnType, Void.TYPE)) {
                throw new NoSuchMethodException("Illegal return type for 'setSidecarCallback': " + returnType);
            }
            SidecarInterface sidecarInterface2 = this.f4101a;
            if (sidecarInterface2 != null) {
                sidecarInterface2.getDeviceState();
            }
            SidecarInterface sidecarInterface3 = this.f4101a;
            if (sidecarInterface3 != null) {
                sidecarInterface3.onDeviceStateListenersChanged(true);
            }
            SidecarInterface sidecarInterface4 = this.f4101a;
            Method method2 = (sidecarInterface4 == null || (cls3 = sidecarInterface4.getClass()) == null) ? null : cls3.getMethod("getWindowLayoutInfo", IBinder.class);
            Class<?> returnType2 = method2 != null ? method2.getReturnType() : null;
            if (!k.a(returnType2, SidecarWindowLayoutInfo.class)) {
                throw new NoSuchMethodException("Illegal return type for 'getWindowLayoutInfo': " + returnType2);
            }
            SidecarInterface sidecarInterface5 = this.f4101a;
            Method method3 = (sidecarInterface5 == null || (cls2 = sidecarInterface5.getClass()) == null) ? null : cls2.getMethod("onWindowLayoutChangeListenerAdded", IBinder.class);
            Class<?> returnType3 = method3 != null ? method3.getReturnType() : null;
            if (!k.a(returnType3, Void.TYPE)) {
                throw new NoSuchMethodException("Illegal return type for 'onWindowLayoutChangeListenerAdded': " + returnType3);
            }
            SidecarInterface sidecarInterface6 = this.f4101a;
            Method method4 = (sidecarInterface6 == null || (cls = sidecarInterface6.getClass()) == null) ? null : cls.getMethod("onWindowLayoutChangeListenerRemoved", IBinder.class);
            Class<?> returnType4 = method4 != null ? method4.getReturnType() : null;
            if (!k.a(returnType4, Void.TYPE)) {
                throw new NoSuchMethodException("Illegal return type for 'onWindowLayoutChangeListenerRemoved': " + returnType4);
            }
            SidecarDeviceState sidecarDeviceState = new SidecarDeviceState();
            try {
                sidecarDeviceState.posture = 3;
            } catch (NoSuchFieldError unused) {
                SidecarDeviceState.class.getMethod("setPosture", Integer.TYPE).invoke(sidecarDeviceState, 3);
                Object invoke = SidecarDeviceState.class.getMethod("getPosture", new Class[0]).invoke(sidecarDeviceState, new Object[0]);
                k.c(invoke, "null cannot be cast to non-null type kotlin.Int");
                if (((Integer) invoke).intValue() != 3) {
                    throw new Exception("Invalid device posture getter/setter");
                }
            }
            SidecarDisplayFeature sidecarDisplayFeature = new SidecarDisplayFeature();
            Rect rect = sidecarDisplayFeature.getRect();
            k.d(rect, "displayFeature.rect");
            sidecarDisplayFeature.setRect(rect);
            sidecarDisplayFeature.getType();
            sidecarDisplayFeature.setType(1);
            SidecarWindowLayoutInfo sidecarWindowLayoutInfo = new SidecarWindowLayoutInfo();
            try {
                List list = sidecarWindowLayoutInfo.displayFeatures;
            } catch (NoSuchFieldError unused2) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(sidecarDisplayFeature);
                SidecarWindowLayoutInfo.class.getMethod("setDisplayFeatures", List.class).invoke(sidecarWindowLayoutInfo, arrayList);
                Object invoke2 = SidecarWindowLayoutInfo.class.getMethod("getDisplayFeatures", new Class[0]).invoke(sidecarWindowLayoutInfo, new Object[0]);
                k.c(invoke2, "null cannot be cast to non-null type kotlin.collections.List<androidx.window.sidecar.SidecarDisplayFeature>");
                if (!k.a(arrayList, (List) invoke2)) {
                    throw new Exception("Invalid display feature getter/setter");
                }
            }
            return true;
        } catch (Throwable unused3) {
            return false;
        }
    }

    public SidecarCompat(SidecarInterface sidecarInterface, k2.a aVar) {
        k.e(aVar, "sidecarAdapter");
        this.f4101a = sidecarInterface;
        this.f4102b = aVar;
        this.f4103c = new LinkedHashMap();
        this.f4104d = new LinkedHashMap();
    }
}
