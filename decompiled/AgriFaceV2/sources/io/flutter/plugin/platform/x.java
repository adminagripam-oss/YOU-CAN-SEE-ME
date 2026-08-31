package io.flutter.plugin.platform;

import android.content.Context;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class x extends ViewGroup {

    /* renamed from: a  reason: collision with root package name */
    private final Rect f8029a;

    /* renamed from: b  reason: collision with root package name */
    private final Rect f8030b;

    public x(Context context) {
        super(context);
        this.f8029a = new Rect();
        this.f8030b = new Rect();
    }

    private static int a(int i8) {
        return View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i8), Integer.MIN_VALUE);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        for (int i12 = 0; i12 < getChildCount(); i12++) {
            View childAt = getChildAt(i12);
            WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) childAt.getLayoutParams();
            this.f8029a.set(i8, i9, i10, i11);
            Gravity.apply(layoutParams.gravity, childAt.getMeasuredWidth(), childAt.getMeasuredHeight(), this.f8029a, layoutParams.x, layoutParams.y, this.f8030b);
            Rect rect = this.f8030b;
            childAt.layout(rect.left, rect.top, rect.right, rect.bottom);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i8, int i9) {
        for (int i10 = 0; i10 < getChildCount(); i10++) {
            getChildAt(i10).measure(a(i8), a(i9));
        }
        super.onMeasure(i8, i9);
    }
}
