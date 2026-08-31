package androidx.camera.video.internal.compat.quirk;

import android.graphics.Rect;
import android.os.Build;
import android.util.Size;
import b0.h2;
import d0.r;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import y0.q1;
/* loaded from: classes.dex */
public class SizeCannotEncodeVideoQuirk implements h2 {
    private static Set g() {
        return h() ? new HashSet(Collections.singletonList(new Size(720, 1280))) : Collections.emptySet();
    }

    private static boolean h() {
        return "motorola".equalsIgnoreCase(Build.BRAND) && "moto c".equalsIgnoreCase(Build.MODEL);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean j() {
        return h();
    }

    public Rect f(Rect rect, int i8, q1 q1Var) {
        Size o8 = r.o(r.l(rect), i8);
        if (i(o8)) {
            int f8 = q1Var != null ? q1Var.f() / 2 : 8;
            Rect rect2 = new Rect(rect);
            if (rect.width() == o8.getHeight()) {
                rect2.left += f8;
                rect2.right -= f8;
            } else {
                rect2.top += f8;
                rect2.bottom -= f8;
            }
            return rect2;
        }
        return rect;
    }

    public boolean i(Size size) {
        return g().contains(size);
    }
}
