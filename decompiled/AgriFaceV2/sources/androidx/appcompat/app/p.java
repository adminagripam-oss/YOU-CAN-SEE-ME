package androidx.appcompat.app;

import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.view.b;
import androidx.core.view.t;
/* loaded from: classes.dex */
public abstract class p extends androidx.activity.l implements e {

    /* renamed from: d  reason: collision with root package name */
    private g f1666d;

    /* renamed from: e  reason: collision with root package name */
    private final t.a f1667e;

    public p(Context context, int i8) {
        super(context, f(context, i8));
        this.f1667e = new t.a() { // from class: androidx.appcompat.app.o
            @Override // androidx.core.view.t.a
            public final boolean superDispatchKeyEvent(KeyEvent keyEvent) {
                return p.this.g(keyEvent);
            }
        };
        g e8 = e();
        e8.N(f(context, i8));
        e8.y(null);
    }

    private static int f(Context context, int i8) {
        if (i8 == 0) {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(g.a.f6814w, typedValue, true);
            return typedValue.resourceId;
        }
        return i8;
    }

    @Override // androidx.activity.l, android.app.Dialog
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        e().e(view, layoutParams);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        e().z();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return androidx.core.view.t.e(this.f1667e, getWindow().getDecorView(), this, keyEvent);
    }

    public g e() {
        if (this.f1666d == null) {
            this.f1666d = g.i(this, this);
        }
        return this.f1666d;
    }

    @Override // android.app.Dialog
    public View findViewById(int i8) {
        return e().j(i8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean g(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean h(int i8) {
        return e().H(i8);
    }

    @Override // android.app.Dialog
    public void invalidateOptionsMenu() {
        e().u();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.l, android.app.Dialog
    public void onCreate(Bundle bundle) {
        e().t();
        super.onCreate(bundle);
        e().y(bundle);
    }

    @Override // androidx.activity.l, android.app.Dialog
    protected void onStop() {
        super.onStop();
        e().E();
    }

    @Override // androidx.appcompat.app.e
    public void onSupportActionModeFinished(androidx.appcompat.view.b bVar) {
    }

    @Override // androidx.appcompat.app.e
    public void onSupportActionModeStarted(androidx.appcompat.view.b bVar) {
    }

    @Override // androidx.appcompat.app.e
    public androidx.appcompat.view.b onWindowStartingSupportActionMode(b.a aVar) {
        return null;
    }

    @Override // androidx.activity.l, android.app.Dialog
    public void setContentView(int i8) {
        e().I(i8);
    }

    @Override // android.app.Dialog
    public void setTitle(int i8) {
        super.setTitle(i8);
        e().O(getContext().getString(i8));
    }

    @Override // androidx.activity.l, android.app.Dialog
    public void setContentView(View view) {
        e().J(view);
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        e().O(charSequence);
    }

    @Override // androidx.activity.l, android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        e().K(view, layoutParams);
    }
}
