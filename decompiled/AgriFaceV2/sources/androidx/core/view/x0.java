package androidx.core.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
public final class x0 {

    /* renamed from: a  reason: collision with root package name */
    private final WeakReference f3188a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends AnimatorListenerAdapter {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ y0 f3189a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ View f3190b;

        a(y0 y0Var, View view) {
            this.f3189a = y0Var;
            this.f3190b = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f3189a.a(this.f3190b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f3189a.b(this.f3190b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f3189a.c(this.f3190b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public x0(View view) {
        this.f3188a = new WeakReference(view);
    }

    private void i(View view, y0 y0Var) {
        if (y0Var != null) {
            view.animate().setListener(new a(y0Var, view));
        } else {
            view.animate().setListener(null);
        }
    }

    public x0 b(float f8) {
        View view = (View) this.f3188a.get();
        if (view != null) {
            view.animate().alpha(f8);
        }
        return this;
    }

    public void c() {
        View view = (View) this.f3188a.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public long d() {
        View view = (View) this.f3188a.get();
        if (view != null) {
            return view.animate().getDuration();
        }
        return 0L;
    }

    public x0 f(long j8) {
        View view = (View) this.f3188a.get();
        if (view != null) {
            view.animate().setDuration(j8);
        }
        return this;
    }

    public x0 g(Interpolator interpolator) {
        View view = (View) this.f3188a.get();
        if (view != null) {
            view.animate().setInterpolator(interpolator);
        }
        return this;
    }

    public x0 h(y0 y0Var) {
        View view = (View) this.f3188a.get();
        if (view != null) {
            i(view, y0Var);
        }
        return this;
    }

    public x0 j(long j8) {
        View view = (View) this.f3188a.get();
        if (view != null) {
            view.animate().setStartDelay(j8);
        }
        return this;
    }

    public x0 k(final a1 a1Var) {
        final View view = (View) this.f3188a.get();
        if (view != null) {
            view.animate().setUpdateListener(a1Var != null ? new ValueAnimator.AnimatorUpdateListener() { // from class: androidx.core.view.w0
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    a1.this.a(view);
                }
            } : null);
        }
        return this;
    }

    public void l() {
        View view = (View) this.f3188a.get();
        if (view != null) {
            view.animate().start();
        }
    }

    public x0 m(float f8) {
        View view = (View) this.f3188a.get();
        if (view != null) {
            view.animate().translationY(f8);
        }
        return this;
    }
}
