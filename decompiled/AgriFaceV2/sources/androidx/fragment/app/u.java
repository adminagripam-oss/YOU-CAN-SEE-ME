package androidx.fragment.app;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.core.app.b;
import androidx.lifecycle.i;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import w1.d;
/* loaded from: classes.dex */
public abstract class u extends androidx.activity.h implements b.e {
    static final String LIFECYCLE_TAG = "android:support:lifecycle";
    boolean mCreated;
    boolean mResumed;
    final y mFragments = y.b(new a());
    final androidx.lifecycle.n mFragmentLifecycleRegistry = new androidx.lifecycle.n(this);
    boolean mStopped = true;

    /* loaded from: classes.dex */
    class a extends a0 implements androidx.core.content.c, androidx.core.content.d, androidx.core.app.n, androidx.core.app.o, androidx.lifecycle.o0, androidx.activity.s, e.e, w1.f, m0, androidx.core.view.w {
        public a() {
            super(u.this);
        }

        @Override // androidx.fragment.app.m0
        public void a(i0 i0Var, p pVar) {
            u.this.onAttachFragment(pVar);
        }

        @Override // androidx.core.view.w
        public void addMenuProvider(androidx.core.view.b0 b0Var) {
            u.this.addMenuProvider(b0Var);
        }

        @Override // androidx.core.content.c
        public void addOnConfigurationChangedListener(g1.a aVar) {
            u.this.addOnConfigurationChangedListener(aVar);
        }

        @Override // androidx.core.app.n
        public void addOnMultiWindowModeChangedListener(g1.a aVar) {
            u.this.addOnMultiWindowModeChangedListener(aVar);
        }

        @Override // androidx.core.app.o
        public void addOnPictureInPictureModeChangedListener(g1.a aVar) {
            u.this.addOnPictureInPictureModeChangedListener(aVar);
        }

        @Override // androidx.core.content.d
        public void addOnTrimMemoryListener(g1.a aVar) {
            u.this.addOnTrimMemoryListener(aVar);
        }

        @Override // androidx.fragment.app.w
        public View c(int i8) {
            return u.this.findViewById(i8);
        }

        @Override // androidx.fragment.app.w
        public boolean d() {
            Window window = u.this.getWindow();
            return (window == null || window.peekDecorView() == null) ? false : true;
        }

        @Override // e.e
        public e.d getActivityResultRegistry() {
            return u.this.getActivityResultRegistry();
        }

        @Override // androidx.lifecycle.m
        public androidx.lifecycle.i getLifecycle() {
            return u.this.mFragmentLifecycleRegistry;
        }

        @Override // androidx.activity.s
        public androidx.activity.q getOnBackPressedDispatcher() {
            return u.this.getOnBackPressedDispatcher();
        }

        @Override // w1.f
        public w1.d getSavedStateRegistry() {
            return u.this.getSavedStateRegistry();
        }

        @Override // androidx.lifecycle.o0
        public androidx.lifecycle.n0 getViewModelStore() {
            return u.this.getViewModelStore();
        }

        @Override // androidx.fragment.app.a0
        public void i(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            u.this.dump(str, fileDescriptor, printWriter, strArr);
        }

        @Override // androidx.fragment.app.a0
        public LayoutInflater k() {
            return u.this.getLayoutInflater().cloneInContext(u.this);
        }

        @Override // androidx.fragment.app.a0
        public void n() {
            o();
        }

        public void o() {
            u.this.invalidateMenu();
        }

        @Override // androidx.fragment.app.a0
        /* renamed from: p */
        public u j() {
            return u.this;
        }

        @Override // androidx.core.view.w
        public void removeMenuProvider(androidx.core.view.b0 b0Var) {
            u.this.removeMenuProvider(b0Var);
        }

        @Override // androidx.core.content.c
        public void removeOnConfigurationChangedListener(g1.a aVar) {
            u.this.removeOnConfigurationChangedListener(aVar);
        }

        @Override // androidx.core.app.n
        public void removeOnMultiWindowModeChangedListener(g1.a aVar) {
            u.this.removeOnMultiWindowModeChangedListener(aVar);
        }

        @Override // androidx.core.app.o
        public void removeOnPictureInPictureModeChangedListener(g1.a aVar) {
            u.this.removeOnPictureInPictureModeChangedListener(aVar);
        }

        @Override // androidx.core.content.d
        public void removeOnTrimMemoryListener(g1.a aVar) {
            u.this.removeOnTrimMemoryListener(aVar);
        }
    }

    public u() {
        m();
    }

    private void m() {
        getSavedStateRegistry().h(LIFECYCLE_TAG, new d.c() { // from class: androidx.fragment.app.q
            @Override // w1.d.c
            public final Bundle a() {
                Bundle n8;
                n8 = u.this.n();
                return n8;
            }
        });
        addOnConfigurationChangedListener(new g1.a() { // from class: androidx.fragment.app.r
            @Override // g1.a
            public final void accept(Object obj) {
                u.this.o((Configuration) obj);
            }
        });
        addOnNewIntentListener(new g1.a() { // from class: androidx.fragment.app.s
            @Override // g1.a
            public final void accept(Object obj) {
                u.this.p((Intent) obj);
            }
        });
        addOnContextAvailableListener(new d.b() { // from class: androidx.fragment.app.t
            @Override // d.b
            public final void a(Context context) {
                u.this.q(context);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Bundle n() {
        markFragmentsCreated();
        this.mFragmentLifecycleRegistry.h(i.a.ON_STOP);
        return new Bundle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o(Configuration configuration) {
        this.mFragments.m();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void p(Intent intent) {
        this.mFragments.m();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void q(Context context) {
        this.mFragments.a(null);
    }

    private static boolean r(i0 i0Var, i.b bVar) {
        boolean z7 = false;
        for (p pVar : i0Var.u0()) {
            if (pVar != null) {
                if (pVar.x() != null) {
                    z7 |= r(pVar.o(), bVar);
                }
                u0 u0Var = pVar.V;
                if (u0Var != null && u0Var.getLifecycle().b().e(i.b.STARTED)) {
                    pVar.V.f(bVar);
                    z7 = true;
                }
                if (pVar.U.b().e(i.b.STARTED)) {
                    pVar.U.m(bVar);
                    z7 = true;
                }
            }
        }
        return z7;
    }

    final View dispatchFragmentsOnCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return this.mFragments.n(view, str, context, attributeSet);
    }

    @Override // android.app.Activity
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        if (shouldDumpInternalState(strArr)) {
            printWriter.print(str);
            printWriter.print("Local FragmentActivity ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(" State:");
            String str2 = str + "  ";
            printWriter.print(str2);
            printWriter.print("mCreated=");
            printWriter.print(this.mCreated);
            printWriter.print(" mResumed=");
            printWriter.print(this.mResumed);
            printWriter.print(" mStopped=");
            printWriter.print(this.mStopped);
            if (getApplication() != null) {
                androidx.loader.app.a.b(this).a(str2, fileDescriptor, printWriter, strArr);
            }
            this.mFragments.l().W(str, fileDescriptor, printWriter, strArr);
        }
    }

    public i0 getSupportFragmentManager() {
        return this.mFragments.l();
    }

    @Deprecated
    public androidx.loader.app.a getSupportLoaderManager() {
        return androidx.loader.app.a.b(this);
    }

    void markFragmentsCreated() {
        do {
        } while (r(getSupportFragmentManager(), i.b.CREATED));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.h, android.app.Activity
    public void onActivityResult(int i8, int i9, Intent intent) {
        this.mFragments.m();
        super.onActivityResult(i8, i9, intent);
    }

    @Deprecated
    public void onAttachFragment(p pVar) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.h, androidx.core.app.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFragmentLifecycleRegistry.h(i.a.ON_CREATE);
        this.mFragments.e();
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View dispatchFragmentsOnCreateView = dispatchFragmentsOnCreateView(view, str, context, attributeSet);
        return dispatchFragmentsOnCreateView == null ? super.onCreateView(view, str, context, attributeSet) : dispatchFragmentsOnCreateView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.mFragments.f();
        this.mFragmentLifecycleRegistry.h(i.a.ON_DESTROY);
    }

    @Override // androidx.activity.h, android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i8, MenuItem menuItem) {
        if (super.onMenuItemSelected(i8, menuItem)) {
            return true;
        }
        if (i8 == 6) {
            return this.mFragments.d(menuItem);
        }
        return false;
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.mResumed = false;
        this.mFragments.g();
        this.mFragmentLifecycleRegistry.h(i.a.ON_PAUSE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        onResumeFragments();
    }

    @Override // androidx.activity.h, android.app.Activity
    public void onRequestPermissionsResult(int i8, String[] strArr, int[] iArr) {
        this.mFragments.m();
        super.onRequestPermissionsResult(i8, strArr, iArr);
    }

    @Override // android.app.Activity
    protected void onResume() {
        this.mFragments.m();
        super.onResume();
        this.mResumed = true;
        this.mFragments.k();
    }

    protected void onResumeFragments() {
        this.mFragmentLifecycleRegistry.h(i.a.ON_RESUME);
        this.mFragments.h();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onStart() {
        this.mFragments.m();
        super.onStart();
        this.mStopped = false;
        if (!this.mCreated) {
            this.mCreated = true;
            this.mFragments.c();
        }
        this.mFragments.k();
        this.mFragmentLifecycleRegistry.h(i.a.ON_START);
        this.mFragments.i();
    }

    @Override // android.app.Activity
    public void onStateNotSaved() {
        this.mFragments.m();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        this.mStopped = true;
        markFragmentsCreated();
        this.mFragments.j();
        this.mFragmentLifecycleRegistry.h(i.a.ON_STOP);
    }

    public void setEnterSharedElementCallback(androidx.core.app.r rVar) {
        androidx.core.app.b.t(this, rVar);
    }

    public void setExitSharedElementCallback(androidx.core.app.r rVar) {
        androidx.core.app.b.u(this, rVar);
    }

    public void startActivityFromFragment(p pVar, Intent intent, int i8) {
        startActivityFromFragment(pVar, intent, i8, (Bundle) null);
    }

    @Deprecated
    public void startIntentSenderFromFragment(p pVar, IntentSender intentSender, int i8, Intent intent, int i9, int i10, int i11, Bundle bundle) {
        if (i8 == -1) {
            androidx.core.app.b.x(this, intentSender, i8, intent, i9, i10, i11, bundle);
        } else {
            pVar.x1(intentSender, i8, intent, i9, i10, i11, bundle);
        }
    }

    public void supportFinishAfterTransition() {
        androidx.core.app.b.o(this);
    }

    public void supportPostponeEnterTransition() {
        androidx.core.app.b.q(this);
    }

    public void supportStartPostponedEnterTransition() {
        androidx.core.app.b.y(this);
    }

    @Override // androidx.core.app.b.e
    @Deprecated
    public final void validateRequestPermissionsRequestCode(int i8) {
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View dispatchFragmentsOnCreateView = dispatchFragmentsOnCreateView(null, str, context, attributeSet);
        return dispatchFragmentsOnCreateView == null ? super.onCreateView(str, context, attributeSet) : dispatchFragmentsOnCreateView;
    }

    public void startActivityFromFragment(p pVar, Intent intent, int i8, Bundle bundle) {
        if (i8 == -1) {
            androidx.core.app.b.w(this, intent, -1, bundle);
        } else {
            pVar.w1(intent, i8, bundle);
        }
    }
}
