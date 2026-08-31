package l2;

import android.graphics.Point;
import android.view.Display;
/* loaded from: classes.dex */
public final class h {

    /* renamed from: a  reason: collision with root package name */
    public static final h f8976a = new h();

    private h() {
    }

    public final void a(Display display, Point point) {
        d7.k.e(display, "display");
        d7.k.e(point, "point");
        display.getRealSize(point);
    }
}
