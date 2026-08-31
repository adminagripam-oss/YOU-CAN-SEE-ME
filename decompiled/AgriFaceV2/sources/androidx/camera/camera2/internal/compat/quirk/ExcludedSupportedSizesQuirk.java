package androidx.camera.camera2.internal.compat.quirk;

import android.os.Build;
import android.util.Size;
import b0.h2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import y.f1;
/* loaded from: classes.dex */
public class ExcludedSupportedSizesQuirk implements h2 {
    private List g(String str, int i8, Class cls) {
        ArrayList arrayList = new ArrayList();
        if (str.equals("0") && (i8 == 34 || i8 == 35 || cls != null)) {
            arrayList.add(new Size(720, 720));
            arrayList.add(new Size(400, 400));
        }
        return arrayList;
    }

    private List h(String str, int i8) {
        ArrayList arrayList = new ArrayList();
        if (str.equals("0") && i8 == 256) {
            arrayList.add(new Size(4160, 3120));
            arrayList.add(new Size(4000, 3000));
        }
        return arrayList;
    }

    private List i(String str, int i8) {
        ArrayList arrayList = new ArrayList();
        if (str.equals("0") && i8 == 256) {
            arrayList.add(new Size(4160, 3120));
            arrayList.add(new Size(4000, 3000));
        }
        return arrayList;
    }

    private List j(String str, int i8) {
        ArrayList arrayList = new ArrayList();
        if (str.equals("0") && i8 == 256) {
            arrayList.add(new Size(9280, 6944));
        }
        return arrayList;
    }

    private List k(String str, int i8, Class cls) {
        Size size;
        ArrayList arrayList = new ArrayList();
        if (str.equals("0")) {
            if (i8 == 34 || cls != null) {
                arrayList.add(new Size(4128, 3096));
                arrayList.add(new Size(4128, 2322));
                arrayList.add(new Size(3088, 3088));
                arrayList.add(new Size(3264, 2448));
                arrayList.add(new Size(3264, 1836));
                arrayList.add(new Size(2048, 1536));
                arrayList.add(new Size(2048, 1152));
                size = new Size(1920, 1080);
            } else if (i8 == 35) {
                arrayList.add(new Size(2048, 1536));
                arrayList.add(new Size(2048, 1152));
                size = new Size(1920, 1080);
            }
            arrayList.add(size);
        } else if (str.equals("1") && (i8 == 34 || i8 == 35 || cls != null)) {
            arrayList.add(new Size(2576, 1932));
            arrayList.add(new Size(2560, 1440));
            arrayList.add(new Size(1920, 1920));
            arrayList.add(new Size(2048, 1536));
            arrayList.add(new Size(2048, 1152));
            size = new Size(1920, 1080);
            arrayList.add(size);
        }
        return arrayList;
    }

    private List l(String str, int i8, Class cls) {
        Size size;
        ArrayList arrayList = new ArrayList();
        if (str.equals("0")) {
            if (i8 == 34 || cls != null) {
                arrayList.add(new Size(4128, 3096));
                arrayList.add(new Size(4128, 2322));
                arrayList.add(new Size(3088, 3088));
                arrayList.add(new Size(3264, 2448));
                arrayList.add(new Size(3264, 1836));
                arrayList.add(new Size(2048, 1536));
                arrayList.add(new Size(2048, 1152));
                size = new Size(1920, 1080);
            } else if (i8 == 35) {
                arrayList.add(new Size(4128, 2322));
                arrayList.add(new Size(3088, 3088));
                arrayList.add(new Size(3264, 2448));
                arrayList.add(new Size(3264, 1836));
                arrayList.add(new Size(2048, 1536));
                arrayList.add(new Size(2048, 1152));
                size = new Size(1920, 1080);
            }
            arrayList.add(size);
        } else if (str.equals("1") && (i8 == 34 || i8 == 35 || cls != null)) {
            arrayList.add(new Size(3264, 2448));
            arrayList.add(new Size(3264, 1836));
            arrayList.add(new Size(2448, 2448));
            arrayList.add(new Size(1920, 1920));
            arrayList.add(new Size(2048, 1536));
            arrayList.add(new Size(2048, 1152));
            size = new Size(1920, 1080);
            arrayList.add(size);
        }
        return arrayList;
    }

    private static boolean m() {
        return "HUAWEI".equalsIgnoreCase(Build.BRAND) && "HWANE".equalsIgnoreCase(Build.DEVICE);
    }

    private static boolean n() {
        return "OnePlus".equalsIgnoreCase(Build.BRAND) && "OnePlus6".equalsIgnoreCase(Build.DEVICE);
    }

    private static boolean o() {
        return "OnePlus".equalsIgnoreCase(Build.BRAND) && "OnePlus6T".equalsIgnoreCase(Build.DEVICE);
    }

    private static boolean p() {
        return "REDMI".equalsIgnoreCase(Build.BRAND) && "joyeuse".equalsIgnoreCase(Build.DEVICE);
    }

    private static boolean q() {
        return "SAMSUNG".equalsIgnoreCase(Build.BRAND) && "J7XELTE".equalsIgnoreCase(Build.DEVICE) && Build.VERSION.SDK_INT >= 27;
    }

    private static boolean r() {
        return "SAMSUNG".equalsIgnoreCase(Build.BRAND) && "ON7XELTE".equalsIgnoreCase(Build.DEVICE) && Build.VERSION.SDK_INT >= 27;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean s() {
        return n() || o() || m() || r() || q() || p();
    }

    public List f(String str, int i8) {
        if (n()) {
            return h(str, i8);
        }
        if (o()) {
            return i(str, i8);
        }
        if (m()) {
            return g(str, i8, null);
        }
        if (r()) {
            return l(str, i8, null);
        }
        if (q()) {
            return k(str, i8, null);
        }
        if (p()) {
            return j(str, i8);
        }
        f1.l("ExcludedSupportedSizesQuirk", "Cannot retrieve list of supported sizes to exclude on this device.");
        return Collections.emptyList();
    }
}
