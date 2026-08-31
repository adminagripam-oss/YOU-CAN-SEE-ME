package androidx.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.window.OnBackInvokedDispatcher;
import androidx.lifecycle.i;
import androidx.lifecycle.p0;
/* loaded from: classes.dex */
public class l extends Dialog implements androidx.lifecycle.m, s, w1.f {

    /* renamed from: a  reason: collision with root package name */
    private androidx.lifecycle.n f1428a;

    /* renamed from: b  reason: collision with root package name */
    private final w1.e f1429b;

    /* renamed from: c  reason: collision with root package name */
    private final q f1430c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(Context context, int i8) {
        super(context, i8);
        d7.k.e(context, "context");
        this.f1429b = w1.e.f12460d.a(this);
        this.f1430c = new q(new Runnable() { // from class: androidx.activity.k
            @Override // java.lang.Runnable
            public final void run() {
                l.d(l.this);
            }
        });
    }

    private final androidx.lifecycle.n b() {
        androidx.lifecycle.n nVar = this.f1428a;
        if (nVar == null) {
            androidx.lifecycle.n nVar2 = new androidx.lifecycle.n(this);
            this.f1428a = nVar2;
            return nVar2;
        }
        return nVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void d(l lVar) {
        d7.k.e(lVar, "this$0");
        super.onBackPressed();
    }

    @Override // android.app.Dialog
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        d7.k.e(view, "view");
        c();
        super.addContentView(view, layoutParams);
    }

    public void c() {
        Window window = getWindow();
        d7.k.b(window);
        View decorView = window.getDecorView();
        d7.k.d(decorView, "window!!.decorView");
        p0.a(decorView, this);
        Window window2 = getWindow();
        d7.k.b(window2);
        View decorView2 = window2.getDecorView();
        d7.k.d(decorView2, "window!!.decorView");
        v.a(decorView2, this);
        Window window3 = getWindow();
        d7.k.b(window3);
        View decorView3 = window3.getDecorView();
        d7.k.d(decorView3, "window!!.decorView");
        w1.g.a(decorView3, this);
    }

    @Override // androidx.lifecycle.m
    public androidx.lifecycle.i getLifecycle() {
        return b();
    }

    @Override // androidx.activity.s
    public final q getOnBackPressedDispatcher() {
        return this.f1430c;
    }

    @Override // w1.f
    public w1.d getSavedStateRegistry() {
        return this.f1429b.b();
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        this.f1430c.k();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        OnBackInvokedDispatcher onBackInvokedDispatcher;
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 33) {
            q qVar = this.f1430c;
            onBackInvokedDispatcher = getOnBackInvokedDispatcher();
            d7.k.d(onBackInvokedDispatcher, "onBackInvokedDispatcher");
            qVar.n(onBackInvokedDispatcher);
        }
        this.f1429b.d(bundle);
        b().h(i.a.ON_CREATE);
    }

    @Override // android.app.Dialog
    public Bundle onSaveInstanceState() {
        Bundle onSaveInstanceState = super.onSaveInstanceState();
        d7.k.d(onSaveInstanceState, "super.onSaveInstanceState()");
        this.f1429b.e(onSaveInstanceState);
        return onSaveInstanceState;
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        b().h(i.a.ON_RESUME);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Dialog
    public void onStop() {
        b().h(i.a.ON_DESTROY);
        this.f1428a = null;
        super.onStop();
    }

    @Override // android.app.Dialog
    public void setContentView(int i8) {
        c();
        super.setContentView(i8);
    }

    @Override // android.app.Dialog
    public void setContentView(View view) {
        d7.k.e(view, "view");
        c();
        super.setContentView(view);
    }

    @Override // android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        d7.k.e(view, "view");
        c();
        super.setContentView(view, layoutParams);
    }
}
