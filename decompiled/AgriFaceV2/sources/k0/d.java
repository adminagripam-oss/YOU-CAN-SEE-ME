package k0;

import android.util.Size;
import java.util.Map;
import java.util.TreeMap;
/* loaded from: classes.dex */
public abstract class d {

    /* renamed from: a  reason: collision with root package name */
    public static final Size f8303a = new Size(0, 0);

    /* renamed from: b  reason: collision with root package name */
    public static final Size f8304b = new Size(320, 240);

    /* renamed from: c  reason: collision with root package name */
    public static final Size f8305c = new Size(640, 480);

    /* renamed from: d  reason: collision with root package name */
    public static final Size f8306d = new Size(720, 480);

    /* renamed from: e  reason: collision with root package name */
    public static final Size f8307e = new Size(1280, 720);

    /* renamed from: f  reason: collision with root package name */
    public static final Size f8308f = new Size(1920, 1080);

    /* renamed from: g  reason: collision with root package name */
    public static final Size f8309g = new Size(1920, 1440);

    public static Object a(Size size, TreeMap treeMap) {
        Map.Entry ceilingEntry = treeMap.ceilingEntry(size);
        if (ceilingEntry != null) {
            return ceilingEntry.getValue();
        }
        Map.Entry floorEntry = treeMap.floorEntry(size);
        if (floorEntry != null) {
            return floorEntry.getValue();
        }
        return null;
    }

    public static int b(int i8, int i9) {
        return i8 * i9;
    }

    public static int c(Size size) {
        return b(size.getWidth(), size.getHeight());
    }

    public static boolean d(Size size, Size size2) {
        return c(size) < c(size2);
    }
}
