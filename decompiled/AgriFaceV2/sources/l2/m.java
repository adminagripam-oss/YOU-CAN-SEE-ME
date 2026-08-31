package l2;

import android.view.DisplayCutout;
/* loaded from: classes.dex */
public final class m {

    /* renamed from: a  reason: collision with root package name */
    public static final m f8977a = new m();

    private m() {
    }

    public final int a(DisplayCutout displayCutout) {
        int safeInsetBottom;
        d7.k.e(displayCutout, "displayCutout");
        safeInsetBottom = displayCutout.getSafeInsetBottom();
        return safeInsetBottom;
    }

    public final int b(DisplayCutout displayCutout) {
        int safeInsetLeft;
        d7.k.e(displayCutout, "displayCutout");
        safeInsetLeft = displayCutout.getSafeInsetLeft();
        return safeInsetLeft;
    }

    public final int c(DisplayCutout displayCutout) {
        int safeInsetRight;
        d7.k.e(displayCutout, "displayCutout");
        safeInsetRight = displayCutout.getSafeInsetRight();
        return safeInsetRight;
    }

    public final int d(DisplayCutout displayCutout) {
        int safeInsetTop;
        d7.k.e(displayCutout, "displayCutout");
        safeInsetTop = displayCutout.getSafeInsetTop();
        return safeInsetTop;
    }
}
