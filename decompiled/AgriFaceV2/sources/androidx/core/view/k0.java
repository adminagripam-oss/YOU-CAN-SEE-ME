package androidx.core.view;

import android.view.View;
import android.view.ViewTreeObserver;
/* loaded from: classes.dex */
public final class k0 implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

    /* renamed from: a  reason: collision with root package name */
    private final View f3136a;

    /* renamed from: b  reason: collision with root package name */
    private ViewTreeObserver f3137b;

    /* renamed from: c  reason: collision with root package name */
    private final Runnable f3138c;

    private k0(View view, Runnable runnable) {
        this.f3136a = view;
        this.f3137b = view.getViewTreeObserver();
        this.f3138c = runnable;
    }

    public static k0 a(View view, Runnable runnable) {
        if (view != null) {
            if (runnable != null) {
                k0 k0Var = new k0(view, runnable);
                view.getViewTreeObserver().addOnPreDrawListener(k0Var);
                view.addOnAttachStateChangeListener(k0Var);
                return k0Var;
            }
            throw new NullPointerException("runnable == null");
        }
        throw new NullPointerException("view == null");
    }

    public void b() {
        (this.f3137b.isAlive() ? this.f3137b : this.f3136a.getViewTreeObserver()).removeOnPreDrawListener(this);
        this.f3136a.removeOnAttachStateChangeListener(this);
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        b();
        this.f3138c.run();
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
        this.f3137b = view.getViewTreeObserver();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        b();
    }
}
