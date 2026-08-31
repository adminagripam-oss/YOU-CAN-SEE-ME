package io.flutter.plugin.platform;

import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowMetrics;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
/* loaded from: classes.dex */
abstract class c0 implements WindowManager {

    /* renamed from: a  reason: collision with root package name */
    final WindowManager f7941a;

    /* renamed from: b  reason: collision with root package name */
    x f7942b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c0(WindowManager windowManager, x xVar) {
        this.f7941a = windowManager;
        this.f7942b = xVar;
    }

    @Override // android.view.WindowManager
    public void addCrossWindowBlurEnabledListener(Executor executor, Consumer consumer) {
        this.f7941a.addCrossWindowBlurEnabledListener(executor, consumer);
    }

    @Override // android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        x xVar = this.f7942b;
        if (xVar == null) {
            z5.b.g("PlatformViewsController", "Embedded view called addView while detached from presentation");
        } else {
            xVar.addView(view, layoutParams);
        }
    }

    @Override // android.view.WindowManager
    public WindowMetrics getCurrentWindowMetrics() {
        WindowMetrics currentWindowMetrics;
        currentWindowMetrics = this.f7941a.getCurrentWindowMetrics();
        return currentWindowMetrics;
    }

    @Override // android.view.WindowManager
    public Display getDefaultDisplay() {
        return this.f7941a.getDefaultDisplay();
    }

    @Override // android.view.WindowManager
    public WindowMetrics getMaximumWindowMetrics() {
        WindowMetrics maximumWindowMetrics;
        maximumWindowMetrics = this.f7941a.getMaximumWindowMetrics();
        return maximumWindowMetrics;
    }

    @Override // android.view.WindowManager
    public boolean isCrossWindowBlurEnabled() {
        boolean isCrossWindowBlurEnabled;
        isCrossWindowBlurEnabled = this.f7941a.isCrossWindowBlurEnabled();
        return isCrossWindowBlurEnabled;
    }

    @Override // android.view.WindowManager
    public void removeCrossWindowBlurEnabledListener(Consumer consumer) {
        this.f7941a.removeCrossWindowBlurEnabledListener(consumer);
    }

    @Override // android.view.ViewManager
    public void removeView(View view) {
        x xVar = this.f7942b;
        if (xVar == null) {
            z5.b.g("PlatformViewsController", "Embedded view called removeView while detached from presentation");
        } else {
            xVar.removeView(view);
        }
    }

    @Override // android.view.WindowManager
    public void removeViewImmediate(View view) {
        if (this.f7942b == null) {
            z5.b.g("PlatformViewsController", "Embedded view called removeViewImmediate while detached from presentation");
            return;
        }
        view.clearAnimation();
        this.f7942b.removeView(view);
    }

    @Override // android.view.ViewManager
    public void updateViewLayout(View view, ViewGroup.LayoutParams layoutParams) {
        x xVar = this.f7942b;
        if (xVar == null) {
            z5.b.g("PlatformViewsController", "Embedded view called updateViewLayout while detached from presentation");
        } else {
            xVar.updateViewLayout(view, layoutParams);
        }
    }

    @Override // android.view.WindowManager
    public void addCrossWindowBlurEnabledListener(Consumer consumer) {
        this.f7941a.addCrossWindowBlurEnabledListener(consumer);
    }
}
