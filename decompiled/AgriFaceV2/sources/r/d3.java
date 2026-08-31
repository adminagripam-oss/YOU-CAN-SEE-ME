package r;

import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.util.Size;
import android.view.Display;
/* loaded from: classes.dex */
public class d3 {

    /* renamed from: e  reason: collision with root package name */
    private static final Size f10430e = new Size(1920, 1080);

    /* renamed from: f  reason: collision with root package name */
    private static final Size f10431f = new Size(320, 240);

    /* renamed from: g  reason: collision with root package name */
    private static final Size f10432g = new Size(640, 480);

    /* renamed from: h  reason: collision with root package name */
    private static final Object f10433h = new Object();

    /* renamed from: i  reason: collision with root package name */
    private static volatile d3 f10434i;

    /* renamed from: a  reason: collision with root package name */
    private final DisplayManager f10435a;

    /* renamed from: b  reason: collision with root package name */
    private volatile Size f10436b = null;

    /* renamed from: c  reason: collision with root package name */
    private final v.k f10437c = new v.k();

    /* renamed from: d  reason: collision with root package name */
    private final v.d f10438d = new v.d();

    private d3(Context context) {
        this.f10435a = (DisplayManager) context.getSystemService("display");
    }

    private Size a() {
        Size b8 = b();
        int width = b8.getWidth() * b8.getHeight();
        Size size = f10430e;
        if (width > size.getWidth() * size.getHeight()) {
            b8 = size;
        }
        return this.f10437c.a(b8);
    }

    private Size b() {
        Point point = new Point();
        d(false).getRealSize(point);
        Size size = new Size(point.x, point.y);
        if (k0.d.d(size, f10431f) && (size = this.f10438d.a()) == null) {
            size = f10432g;
        }
        return size.getHeight() > size.getWidth() ? new Size(size.getHeight(), size.getWidth()) : size;
    }

    public static d3 c(Context context) {
        if (f10434i == null) {
            synchronized (f10433h) {
                if (f10434i == null) {
                    f10434i = new d3(context);
                }
            }
        }
        return f10434i;
    }

    private Display e(Display[] displayArr, boolean z7) {
        Display display = null;
        int i8 = -1;
        for (Display display2 : displayArr) {
            if (!z7 || display2.getState() != 1) {
                Point point = new Point();
                display2.getRealSize(point);
                int i9 = point.x;
                int i10 = point.y;
                if (i9 * i10 > i8) {
                    display = display2;
                    i8 = i9 * i10;
                }
            }
        }
        return display;
    }

    public Display d(boolean z7) {
        Display[] displays = this.f10435a.getDisplays();
        if (displays.length == 1) {
            return displays[0];
        }
        Display e8 = e(displays, z7);
        if (e8 == null && z7) {
            e8 = e(displays, false);
        }
        if (e8 != null) {
            return e8;
        }
        throw new IllegalArgumentException("No display can be found from the input display manager!");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Size f() {
        if (this.f10436b != null) {
            return this.f10436b;
        }
        this.f10436b = a();
        return this.f10436b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g() {
        this.f10436b = a();
    }
}
