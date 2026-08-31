package androidx.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.window.OnBackInvokedDispatcher;
import androidx.activity.h;
import androidx.core.view.b0;
import androidx.core.view.w;
import androidx.core.view.z;
import androidx.lifecycle.e0;
import androidx.lifecycle.h0;
import androidx.lifecycle.i;
import androidx.lifecycle.l0;
import androidx.lifecycle.n0;
import androidx.lifecycle.o0;
import androidx.lifecycle.p0;
import androidx.lifecycle.q0;
import androidx.lifecycle.y;
import f.a;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import w1.d;
/* loaded from: classes.dex */
public abstract class h extends androidx.core.app.f implements o0, androidx.lifecycle.g, w1.f, s, e.e, androidx.core.content.c, androidx.core.content.d, androidx.core.app.n, androidx.core.app.o, w, o {
    private static final String ACTIVITY_RESULT_TAG = "android:support:activity-result";
    private final e.d mActivityResultRegistry;
    private int mContentLayoutId;
    private l0.b mDefaultFactory;
    private boolean mDispatchingOnMultiWindowModeChanged;
    private boolean mDispatchingOnPictureInPictureModeChanged;
    final n mFullyDrawnReporter;
    private final AtomicInteger mNextLocalRequestCode;
    private q mOnBackPressedDispatcher;
    private final CopyOnWriteArrayList<g1.a> mOnConfigurationChangedListeners;
    private final CopyOnWriteArrayList<g1.a> mOnMultiWindowModeChangedListeners;
    private final CopyOnWriteArrayList<g1.a> mOnNewIntentListeners;
    private final CopyOnWriteArrayList<g1.a> mOnPictureInPictureModeChangedListeners;
    private final CopyOnWriteArrayList<g1.a> mOnTrimMemoryListeners;
    final j mReportFullyDrawnExecutor;
    final w1.e mSavedStateRegistryController;
    private n0 mViewModelStore;
    final d.a mContextAwareHelper = new d.a();
    private final z mMenuHostHelper = new z(new Runnable() { // from class: androidx.activity.d
        @Override // java.lang.Runnable
        public final void run() {
            h.this.invalidateMenu();
        }
    });
    private final androidx.lifecycle.n mLifecycleRegistry = new androidx.lifecycle.n(this);

    /* loaded from: classes.dex */
    class a extends e.d {

        /* renamed from: androidx.activity.h$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        class RunnableC0003a implements Runnable {

            /* renamed from: e  reason: collision with root package name */
            final /* synthetic */ int f1409e;

            /* renamed from: f  reason: collision with root package name */
            final /* synthetic */ a.C0084a f1410f;

            RunnableC0003a(int i8, a.C0084a c0084a) {
                this.f1409e = i8;
                this.f1410f = c0084a;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.c(this.f1409e, this.f1410f.a());
            }
        }

        /* loaded from: classes.dex */
        class b implements Runnable {

            /* renamed from: e  reason: collision with root package name */
            final /* synthetic */ int f1412e;

            /* renamed from: f  reason: collision with root package name */
            final /* synthetic */ IntentSender.SendIntentException f1413f;

            b(int i8, IntentSender.SendIntentException sendIntentException) {
                this.f1412e = i8;
                this.f1413f = sendIntentException;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.b(this.f1412e, 0, new Intent().setAction("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST").putExtra("androidx.activity.result.contract.extra.SEND_INTENT_EXCEPTION", this.f1413f));
            }
        }

        a() {
        }

        @Override // e.d
        public void f(int i8, f.a aVar, Object obj, androidx.core.app.c cVar) {
            Bundle b8;
            h hVar = h.this;
            a.C0084a b9 = aVar.b(hVar, obj);
            if (b9 != null) {
                new Handler(Looper.getMainLooper()).post(new RunnableC0003a(i8, b9));
                return;
            }
            Intent a8 = aVar.a(hVar, obj);
            if (a8.getExtras() != null && a8.getExtras().getClassLoader() == null) {
                a8.setExtrasClassLoader(hVar.getClassLoader());
            }
            if (a8.hasExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE")) {
                Bundle bundleExtra = a8.getBundleExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
                a8.removeExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
                b8 = bundleExtra;
            } else {
                b8 = cVar != null ? cVar.b() : null;
            }
            if ("androidx.activity.result.contract.action.REQUEST_PERMISSIONS".equals(a8.getAction())) {
                String[] stringArrayExtra = a8.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
                if (stringArrayExtra == null) {
                    stringArrayExtra = new String[0];
                }
                androidx.core.app.b.s(hVar, stringArrayExtra, i8);
            } else if (!"androidx.activity.result.contract.action.INTENT_SENDER_REQUEST".equals(a8.getAction())) {
                androidx.core.app.b.w(hVar, a8, i8, b8);
            } else {
                e.f fVar = (e.f) a8.getParcelableExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST");
                try {
                    androidx.core.app.b.x(hVar, fVar.d(), i8, fVar.a(), fVar.b(), fVar.c(), 0, b8);
                } catch (IntentSender.SendIntentException e8) {
                    new Handler(Looper.getMainLooper()).post(new b(i8, e8));
                }
            }
        }
    }

    /* loaded from: classes.dex */
    class b implements androidx.lifecycle.k {
        b() {
        }

        @Override // androidx.lifecycle.k
        public void f(androidx.lifecycle.m mVar, i.a aVar) {
            if (aVar == i.a.ON_STOP) {
                Window window = h.this.getWindow();
                View peekDecorView = window != null ? window.peekDecorView() : null;
                if (peekDecorView != null) {
                    g.a(peekDecorView);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    class c implements androidx.lifecycle.k {
        c() {
        }

        @Override // androidx.lifecycle.k
        public void f(androidx.lifecycle.m mVar, i.a aVar) {
            if (aVar == i.a.ON_DESTROY) {
                h.this.mContextAwareHelper.b();
                if (!h.this.isChangingConfigurations()) {
                    h.this.getViewModelStore().a();
                }
                h.this.mReportFullyDrawnExecutor.f();
            }
        }
    }

    /* loaded from: classes.dex */
    class d implements androidx.lifecycle.k {
        d() {
        }

        @Override // androidx.lifecycle.k
        public void f(androidx.lifecycle.m mVar, i.a aVar) {
            h.this.ensureViewModelStore();
            h.this.getLifecycle().c(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                h.super.onBackPressed();
            } catch (IllegalStateException e8) {
                if (!TextUtils.equals(e8.getMessage(), "Can not perform this action after onSaveInstanceState")) {
                    throw e8;
                }
            } catch (NullPointerException e9) {
                if (!TextUtils.equals(e9.getMessage(), "Attempt to invoke virtual method 'android.os.Handler android.app.FragmentHostCallback.getHandler()' on a null object reference")) {
                    throw e9;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class f implements androidx.lifecycle.k {
        f() {
        }

        @Override // androidx.lifecycle.k
        public void f(androidx.lifecycle.m mVar, i.a aVar) {
            if (aVar != i.a.ON_CREATE || Build.VERSION.SDK_INT < 33) {
                return;
            }
            h.this.mOnBackPressedDispatcher.n(C0004h.a((h) mVar));
        }
    }

    /* loaded from: classes.dex */
    static class g {
        static void a(View view) {
            view.cancelPendingInputEvents();
        }
    }

    /* renamed from: androidx.activity.h$h  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static class C0004h {
        static OnBackInvokedDispatcher a(Activity activity) {
            return activity.getOnBackInvokedDispatcher();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class i {

        /* renamed from: a  reason: collision with root package name */
        Object f1420a;

        /* renamed from: b  reason: collision with root package name */
        n0 f1421b;

        i() {
        }
    }

    /* loaded from: classes.dex */
    private interface j extends Executor {
        void f();

        void v(View view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class k implements j, ViewTreeObserver.OnDrawListener, Runnable {

        /* renamed from: f  reason: collision with root package name */
        Runnable f1423f;

        /* renamed from: e  reason: collision with root package name */
        final long f1422e = SystemClock.uptimeMillis() + 10000;

        /* renamed from: g  reason: collision with root package name */
        boolean f1424g = false;

        k() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b() {
            Runnable runnable = this.f1423f;
            if (runnable != null) {
                runnable.run();
                this.f1423f = null;
            }
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.f1423f = runnable;
            View decorView = h.this.getWindow().getDecorView();
            if (!this.f1424g) {
                decorView.postOnAnimation(new Runnable() { // from class: androidx.activity.i
                    @Override // java.lang.Runnable
                    public final void run() {
                        h.k.this.b();
                    }
                });
            } else if (Looper.myLooper() == Looper.getMainLooper()) {
                decorView.invalidate();
            } else {
                decorView.postInvalidate();
            }
        }

        @Override // androidx.activity.h.j
        public void f() {
            h.this.getWindow().getDecorView().removeCallbacks(this);
            h.this.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnDrawListener
        public void onDraw() {
            Runnable runnable = this.f1423f;
            if (runnable != null) {
                runnable.run();
                this.f1423f = null;
                if (!h.this.mFullyDrawnReporter.c()) {
                    return;
                }
            } else if (SystemClock.uptimeMillis() <= this.f1422e) {
                return;
            }
            this.f1424g = false;
            h.this.getWindow().getDecorView().post(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            h.this.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(this);
        }

        @Override // androidx.activity.h.j
        public void v(View view) {
            if (this.f1424g) {
                return;
            }
            this.f1424g = true;
            view.getViewTreeObserver().addOnDrawListener(this);
        }
    }

    public h() {
        w1.e a8 = w1.e.a(this);
        this.mSavedStateRegistryController = a8;
        this.mOnBackPressedDispatcher = null;
        j e8 = e();
        this.mReportFullyDrawnExecutor = e8;
        this.mFullyDrawnReporter = new n(e8, new c7.a() { // from class: androidx.activity.e
            @Override // c7.a
            public final Object a() {
                r6.r f8;
                f8 = h.this.f();
                return f8;
            }
        });
        this.mNextLocalRequestCode = new AtomicInteger();
        this.mActivityResultRegistry = new a();
        this.mOnConfigurationChangedListeners = new CopyOnWriteArrayList<>();
        this.mOnTrimMemoryListeners = new CopyOnWriteArrayList<>();
        this.mOnNewIntentListeners = new CopyOnWriteArrayList<>();
        this.mOnMultiWindowModeChangedListeners = new CopyOnWriteArrayList<>();
        this.mOnPictureInPictureModeChangedListeners = new CopyOnWriteArrayList<>();
        this.mDispatchingOnMultiWindowModeChanged = false;
        this.mDispatchingOnPictureInPictureModeChanged = false;
        if (getLifecycle() == null) {
            throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
        }
        getLifecycle().a(new b());
        getLifecycle().a(new c());
        getLifecycle().a(new d());
        a8.c();
        e0.c(this);
        getSavedStateRegistry().h(ACTIVITY_RESULT_TAG, new d.c() { // from class: androidx.activity.f
            @Override // w1.d.c
            public final Bundle a() {
                Bundle g8;
                g8 = h.this.g();
                return g8;
            }
        });
        addOnContextAvailableListener(new d.b() { // from class: androidx.activity.g
            @Override // d.b
            public final void a(Context context) {
                h.this.h(context);
            }
        });
    }

    private j e() {
        return new k();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ r6.r f() {
        reportFullyDrawn();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Bundle g() {
        Bundle bundle = new Bundle();
        this.mActivityResultRegistry.h(bundle);
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(Context context) {
        Bundle b8 = getSavedStateRegistry().b(ACTIVITY_RESULT_TAG);
        if (b8 != null) {
            this.mActivityResultRegistry.g(b8);
        }
    }

    @Override // androidx.core.view.w
    public void addMenuProvider(b0 b0Var) {
        this.mMenuHostHelper.c(b0Var);
    }

    @Override // androidx.core.content.c
    public final void addOnConfigurationChangedListener(g1.a aVar) {
        this.mOnConfigurationChangedListeners.add(aVar);
    }

    public final void addOnContextAvailableListener(d.b bVar) {
        this.mContextAwareHelper.a(bVar);
    }

    @Override // androidx.core.app.n
    public final void addOnMultiWindowModeChangedListener(g1.a aVar) {
        this.mOnMultiWindowModeChangedListeners.add(aVar);
    }

    public final void addOnNewIntentListener(g1.a aVar) {
        this.mOnNewIntentListeners.add(aVar);
    }

    @Override // androidx.core.app.o
    public final void addOnPictureInPictureModeChangedListener(g1.a aVar) {
        this.mOnPictureInPictureModeChangedListeners.add(aVar);
    }

    @Override // androidx.core.content.d
    public final void addOnTrimMemoryListener(g1.a aVar) {
        this.mOnTrimMemoryListeners.add(aVar);
    }

    void ensureViewModelStore() {
        if (this.mViewModelStore == null) {
            i iVar = (i) getLastNonConfigurationInstance();
            if (iVar != null) {
                this.mViewModelStore = iVar.f1421b;
            }
            if (this.mViewModelStore == null) {
                this.mViewModelStore = new n0();
            }
        }
    }

    @Override // e.e
    public final e.d getActivityResultRegistry() {
        return this.mActivityResultRegistry;
    }

    @Override // androidx.lifecycle.g
    public v1.a getDefaultViewModelCreationExtras() {
        v1.b bVar = new v1.b();
        if (getApplication() != null) {
            bVar.c(l0.a.f3793h, getApplication());
        }
        bVar.c(e0.f3755a, this);
        bVar.c(e0.f3756b, this);
        if (getIntent() != null && getIntent().getExtras() != null) {
            bVar.c(e0.f3757c, getIntent().getExtras());
        }
        return bVar;
    }

    public l0.b getDefaultViewModelProviderFactory() {
        if (this.mDefaultFactory == null) {
            this.mDefaultFactory = new h0(getApplication(), this, getIntent() != null ? getIntent().getExtras() : null);
        }
        return this.mDefaultFactory;
    }

    public n getFullyDrawnReporter() {
        return this.mFullyDrawnReporter;
    }

    @Deprecated
    public Object getLastCustomNonConfigurationInstance() {
        i iVar = (i) getLastNonConfigurationInstance();
        if (iVar != null) {
            return iVar.f1420a;
        }
        return null;
    }

    @Override // androidx.lifecycle.m
    public androidx.lifecycle.i getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Override // androidx.activity.s
    public final q getOnBackPressedDispatcher() {
        if (this.mOnBackPressedDispatcher == null) {
            this.mOnBackPressedDispatcher = new q(new e());
            getLifecycle().a(new f());
        }
        return this.mOnBackPressedDispatcher;
    }

    @Override // w1.f
    public final w1.d getSavedStateRegistry() {
        return this.mSavedStateRegistryController.b();
    }

    @Override // androidx.lifecycle.o0
    public n0 getViewModelStore() {
        if (getApplication() != null) {
            ensureViewModelStore();
            return this.mViewModelStore;
        }
        throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
    }

    public void initializeViewTreeOwners() {
        p0.a(getWindow().getDecorView(), this);
        q0.a(getWindow().getDecorView(), this);
        w1.g.a(getWindow().getDecorView(), this);
        v.a(getWindow().getDecorView(), this);
        u.a(getWindow().getDecorView(), this);
    }

    public void invalidateMenu() {
        invalidateOptionsMenu();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onActivityResult(int i8, int i9, Intent intent) {
        if (this.mActivityResultRegistry.b(i8, i9, intent)) {
            return;
        }
        super.onActivityResult(i8, i9, intent);
    }

    @Override // android.app.Activity
    @Deprecated
    public void onBackPressed() {
        getOnBackPressedDispatcher().k();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Iterator<g1.a> it = this.mOnConfigurationChangedListeners.iterator();
        while (it.hasNext()) {
            it.next().accept(configuration);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.core.app.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.mSavedStateRegistryController.d(bundle);
        this.mContextAwareHelper.c(this);
        super.onCreate(bundle);
        y.e(this);
        int i8 = this.mContentLayoutId;
        if (i8 != 0) {
            setContentView(i8);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onCreatePanelMenu(int i8, Menu menu) {
        if (i8 == 0) {
            super.onCreatePanelMenu(i8, menu);
            this.mMenuHostHelper.h(menu, getMenuInflater());
            return true;
        }
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i8, MenuItem menuItem) {
        if (super.onMenuItemSelected(i8, menuItem)) {
            return true;
        }
        if (i8 == 0) {
            return this.mMenuHostHelper.j(menuItem);
        }
        return false;
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z7) {
        if (this.mDispatchingOnMultiWindowModeChanged) {
            return;
        }
        Iterator<g1.a> it = this.mOnMultiWindowModeChangedListeners.iterator();
        while (it.hasNext()) {
            it.next().accept(new androidx.core.app.g(z7));
        }
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Iterator<g1.a> it = this.mOnNewIntentListeners.iterator();
        while (it.hasNext()) {
            it.next().accept(intent);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i8, Menu menu) {
        this.mMenuHostHelper.i(menu);
        super.onPanelClosed(i8, menu);
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z7) {
        if (this.mDispatchingOnPictureInPictureModeChanged) {
            return;
        }
        Iterator<g1.a> it = this.mOnPictureInPictureModeChangedListeners.iterator();
        while (it.hasNext()) {
            it.next().accept(new androidx.core.app.p(z7));
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onPreparePanel(int i8, View view, Menu menu) {
        if (i8 == 0) {
            super.onPreparePanel(i8, view, menu);
            this.mMenuHostHelper.k(menu);
            return true;
        }
        return true;
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i8, String[] strArr, int[] iArr) {
        if (this.mActivityResultRegistry.b(i8, -1, new Intent().putExtra("androidx.activity.result.contract.extra.PERMISSIONS", strArr).putExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS", iArr))) {
            return;
        }
        super.onRequestPermissionsResult(i8, strArr, iArr);
    }

    @Deprecated
    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        i iVar;
        Object onRetainCustomNonConfigurationInstance = onRetainCustomNonConfigurationInstance();
        n0 n0Var = this.mViewModelStore;
        if (n0Var == null && (iVar = (i) getLastNonConfigurationInstance()) != null) {
            n0Var = iVar.f1421b;
        }
        if (n0Var == null && onRetainCustomNonConfigurationInstance == null) {
            return null;
        }
        i iVar2 = new i();
        iVar2.f1420a = onRetainCustomNonConfigurationInstance;
        iVar2.f1421b = n0Var;
        return iVar2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.core.app.f, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        androidx.lifecycle.i lifecycle = getLifecycle();
        if (lifecycle instanceof androidx.lifecycle.n) {
            ((androidx.lifecycle.n) lifecycle).m(i.b.CREATED);
        }
        super.onSaveInstanceState(bundle);
        this.mSavedStateRegistryController.e(bundle);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks2
    public void onTrimMemory(int i8) {
        super.onTrimMemory(i8);
        Iterator<g1.a> it = this.mOnTrimMemoryListeners.iterator();
        while (it.hasNext()) {
            it.next().accept(Integer.valueOf(i8));
        }
    }

    public Context peekAvailableContext() {
        return this.mContextAwareHelper.d();
    }

    public final <I, O> e.c registerForActivityResult(f.a aVar, e.b bVar) {
        return registerForActivityResult(aVar, this.mActivityResultRegistry, bVar);
    }

    @Override // androidx.core.view.w
    public void removeMenuProvider(b0 b0Var) {
        this.mMenuHostHelper.l(b0Var);
    }

    @Override // androidx.core.content.c
    public final void removeOnConfigurationChangedListener(g1.a aVar) {
        this.mOnConfigurationChangedListeners.remove(aVar);
    }

    public final void removeOnContextAvailableListener(d.b bVar) {
        this.mContextAwareHelper.e(bVar);
    }

    @Override // androidx.core.app.n
    public final void removeOnMultiWindowModeChangedListener(g1.a aVar) {
        this.mOnMultiWindowModeChangedListeners.remove(aVar);
    }

    public final void removeOnNewIntentListener(g1.a aVar) {
        this.mOnNewIntentListeners.remove(aVar);
    }

    @Override // androidx.core.app.o
    public final void removeOnPictureInPictureModeChangedListener(g1.a aVar) {
        this.mOnPictureInPictureModeChangedListeners.remove(aVar);
    }

    @Override // androidx.core.content.d
    public final void removeOnTrimMemoryListener(g1.a aVar) {
        this.mOnTrimMemoryListeners.remove(aVar);
    }

    @Override // android.app.Activity
    public void reportFullyDrawn() {
        try {
            if (z1.a.h()) {
                z1.a.c("reportFullyDrawn() for ComponentActivity");
            }
            super.reportFullyDrawn();
            this.mFullyDrawnReporter.b();
        } finally {
            z1.a.f();
        }
    }

    @Override // android.app.Activity
    public abstract void setContentView(int i8);

    @Override // android.app.Activity
    public void setContentView(View view) {
        initializeViewTreeOwners();
        this.mReportFullyDrawnExecutor.v(getWindow().getDecorView());
        super.setContentView(view);
    }

    @Override // android.app.Activity
    @Deprecated
    public void startActivityForResult(Intent intent, int i8) {
        super.startActivityForResult(intent, i8);
    }

    @Override // android.app.Activity
    @Deprecated
    public void startIntentSenderForResult(IntentSender intentSender, int i8, Intent intent, int i9, int i10, int i11) {
        super.startIntentSenderForResult(intentSender, i8, intent, i9, i10, i11);
    }

    public void addMenuProvider(b0 b0Var, androidx.lifecycle.m mVar) {
        this.mMenuHostHelper.d(b0Var, mVar);
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z7, Configuration configuration) {
        this.mDispatchingOnMultiWindowModeChanged = true;
        try {
            super.onMultiWindowModeChanged(z7, configuration);
            this.mDispatchingOnMultiWindowModeChanged = false;
            Iterator<g1.a> it = this.mOnMultiWindowModeChangedListeners.iterator();
            while (it.hasNext()) {
                it.next().accept(new androidx.core.app.g(z7, configuration));
            }
        } catch (Throwable th) {
            this.mDispatchingOnMultiWindowModeChanged = false;
            throw th;
        }
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z7, Configuration configuration) {
        this.mDispatchingOnPictureInPictureModeChanged = true;
        try {
            super.onPictureInPictureModeChanged(z7, configuration);
            this.mDispatchingOnPictureInPictureModeChanged = false;
            Iterator<g1.a> it = this.mOnPictureInPictureModeChangedListeners.iterator();
            while (it.hasNext()) {
                it.next().accept(new androidx.core.app.p(z7, configuration));
            }
        } catch (Throwable th) {
            this.mDispatchingOnPictureInPictureModeChanged = false;
            throw th;
        }
    }

    public final <I, O> e.c registerForActivityResult(f.a aVar, e.d dVar, e.b bVar) {
        return dVar.i("activity_rq#" + this.mNextLocalRequestCode.getAndIncrement(), this, aVar, bVar);
    }

    @Override // android.app.Activity
    @Deprecated
    public void startActivityForResult(Intent intent, int i8, Bundle bundle) {
        super.startActivityForResult(intent, i8, bundle);
    }

    @Override // android.app.Activity
    @Deprecated
    public void startIntentSenderForResult(IntentSender intentSender, int i8, Intent intent, int i9, int i10, int i11, Bundle bundle) {
        super.startIntentSenderForResult(intentSender, i8, intent, i9, i10, i11, bundle);
    }

    public void addMenuProvider(b0 b0Var, androidx.lifecycle.m mVar, i.b bVar) {
        this.mMenuHostHelper.e(b0Var, mVar, bVar);
    }
}
