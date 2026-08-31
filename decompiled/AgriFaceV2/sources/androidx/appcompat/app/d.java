package androidx.appcompat.app;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.view.b;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.h1;
import androidx.core.app.s;
import androidx.lifecycle.p0;
import androidx.lifecycle.q0;
import w1.d;
/* loaded from: classes.dex */
public abstract class d extends androidx.fragment.app.u implements e, s.a {
    private static final String DELEGATE_TAG = "androidx:appcompat";
    private g mDelegate;
    private Resources mResources;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements d.c {
        a() {
        }

        @Override // w1.d.c
        public Bundle a() {
            Bundle bundle = new Bundle();
            d.this.getDelegate().C(bundle);
            return bundle;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements d.b {
        b() {
        }

        @Override // d.b
        public void a(Context context) {
            g delegate = d.this.getDelegate();
            delegate.t();
            delegate.y(d.this.getSavedStateRegistry().b(d.DELEGATE_TAG));
        }
    }

    public d() {
        s();
    }

    private void s() {
        getSavedStateRegistry().h(DELEGATE_TAG, new a());
        addOnContextAvailableListener(new b());
    }

    private void t() {
        p0.a(getWindow().getDecorView(), this);
        q0.a(getWindow().getDecorView(), this);
        w1.g.a(getWindow().getDecorView(), this);
        androidx.activity.v.a(getWindow().getDecorView(), this);
    }

    private boolean u(KeyEvent keyEvent) {
        return false;
    }

    @Override // android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        t();
        getDelegate().e(view, layoutParams);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(getDelegate().g(context));
    }

    @Override // android.app.Activity
    public void closeOptionsMenu() {
        androidx.appcompat.app.a supportActionBar = getSupportActionBar();
        if (getWindow().hasFeature(0)) {
            if (supportActionBar == null || !supportActionBar.g()) {
                super.closeOptionsMenu();
            }
        }
    }

    @Override // androidx.core.app.f, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        androidx.appcompat.app.a supportActionBar = getSupportActionBar();
        if (keyCode == 82 && supportActionBar != null && supportActionBar.r(keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity
    public <T extends View> T findViewById(int i8) {
        return (T) getDelegate().j(i8);
    }

    public g getDelegate() {
        if (this.mDelegate == null) {
            this.mDelegate = g.h(this, this);
        }
        return this.mDelegate;
    }

    public androidx.appcompat.app.b getDrawerToggleDelegate() {
        return getDelegate().n();
    }

    @Override // android.app.Activity
    public MenuInflater getMenuInflater() {
        return getDelegate().q();
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        if (this.mResources == null && h1.c()) {
            this.mResources = new h1(this, super.getResources());
        }
        Resources resources = this.mResources;
        return resources == null ? super.getResources() : resources;
    }

    public androidx.appcompat.app.a getSupportActionBar() {
        return getDelegate().s();
    }

    @Override // androidx.core.app.s.a
    public Intent getSupportParentActivityIntent() {
        return androidx.core.app.h.a(this);
    }

    @Override // android.app.Activity
    public void invalidateOptionsMenu() {
        getDelegate().u();
    }

    @Override // androidx.activity.h, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        getDelegate().x(configuration);
        if (this.mResources != null) {
            this.mResources.updateConfiguration(super.getResources().getConfiguration(), super.getResources().getDisplayMetrics());
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onContentChanged() {
        onSupportContentChanged();
    }

    public void onCreateSupportNavigateUpTaskStack(androidx.core.app.s sVar) {
        sVar.f(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.u, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        getDelegate().z();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i8, KeyEvent keyEvent) {
        if (u(keyEvent)) {
            return true;
        }
        return super.onKeyDown(i8, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onLocalesChanged(androidx.core.os.h hVar) {
    }

    @Override // androidx.fragment.app.u, androidx.activity.h, android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i8, MenuItem menuItem) {
        if (super.onMenuItemSelected(i8, menuItem)) {
            return true;
        }
        androidx.appcompat.app.a supportActionBar = getSupportActionBar();
        if (menuItem.getItemId() != 16908332 || supportActionBar == null || (supportActionBar.j() & 4) == 0) {
            return false;
        }
        return onSupportNavigateUp();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuOpened(int i8, Menu menu) {
        return super.onMenuOpened(i8, menu);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onNightModeChanged(int i8) {
    }

    @Override // androidx.activity.h, android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i8, Menu menu) {
        super.onPanelClosed(i8, menu);
    }

    @Override // android.app.Activity
    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        getDelegate().A(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.u, android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        getDelegate().B();
    }

    public void onPrepareSupportNavigateUpTaskStack(androidx.core.app.s sVar) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.u, android.app.Activity
    public void onStart() {
        super.onStart();
        getDelegate().D();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.u, android.app.Activity
    public void onStop() {
        super.onStop();
        getDelegate().E();
    }

    @Override // androidx.appcompat.app.e
    public void onSupportActionModeFinished(androidx.appcompat.view.b bVar) {
    }

    @Override // androidx.appcompat.app.e
    public void onSupportActionModeStarted(androidx.appcompat.view.b bVar) {
    }

    @Deprecated
    public void onSupportContentChanged() {
    }

    public boolean onSupportNavigateUp() {
        Intent supportParentActivityIntent = getSupportParentActivityIntent();
        if (supportParentActivityIntent != null) {
            if (!supportShouldUpRecreateTask(supportParentActivityIntent)) {
                supportNavigateUpTo(supportParentActivityIntent);
                return true;
            }
            androidx.core.app.s k8 = androidx.core.app.s.k(this);
            onCreateSupportNavigateUpTaskStack(k8);
            onPrepareSupportNavigateUpTaskStack(k8);
            k8.l();
            try {
                androidx.core.app.b.n(this);
                return true;
            } catch (IllegalStateException unused) {
                finish();
                return true;
            }
        }
        return false;
    }

    @Override // android.app.Activity
    protected void onTitleChanged(CharSequence charSequence, int i8) {
        super.onTitleChanged(charSequence, i8);
        getDelegate().O(charSequence);
    }

    @Override // androidx.appcompat.app.e
    public androidx.appcompat.view.b onWindowStartingSupportActionMode(b.a aVar) {
        return null;
    }

    @Override // android.app.Activity
    public void openOptionsMenu() {
        androidx.appcompat.app.a supportActionBar = getSupportActionBar();
        if (getWindow().hasFeature(0)) {
            if (supportActionBar == null || !supportActionBar.s()) {
                super.openOptionsMenu();
            }
        }
    }

    @Override // androidx.activity.h, android.app.Activity
    public void setContentView(int i8) {
        t();
        getDelegate().I(i8);
    }

    public void setSupportActionBar(Toolbar toolbar) {
        getDelegate().M(toolbar);
    }

    @Deprecated
    public void setSupportProgress(int i8) {
    }

    @Deprecated
    public void setSupportProgressBarIndeterminate(boolean z7) {
    }

    @Deprecated
    public void setSupportProgressBarIndeterminateVisibility(boolean z7) {
    }

    @Deprecated
    public void setSupportProgressBarVisibility(boolean z7) {
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int i8) {
        super.setTheme(i8);
        getDelegate().N(i8);
    }

    public androidx.appcompat.view.b startSupportActionMode(b.a aVar) {
        return getDelegate().P(aVar);
    }

    public void supportInvalidateOptionsMenu() {
        getDelegate().u();
    }

    public void supportNavigateUpTo(Intent intent) {
        androidx.core.app.h.e(this, intent);
    }

    public boolean supportRequestWindowFeature(int i8) {
        return getDelegate().H(i8);
    }

    public boolean supportShouldUpRecreateTask(Intent intent) {
        return androidx.core.app.h.f(this, intent);
    }

    @Override // androidx.activity.h, android.app.Activity
    public void setContentView(View view) {
        t();
        getDelegate().J(view);
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        t();
        getDelegate().K(view, layoutParams);
    }
}
