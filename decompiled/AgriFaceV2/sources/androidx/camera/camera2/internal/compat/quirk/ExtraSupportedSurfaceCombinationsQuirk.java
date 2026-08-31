package androidx.camera.camera2.internal.compat.quirk;

import android.os.Build;
import b0.h2;
import b0.v2;
import b0.w2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
/* loaded from: classes.dex */
public class ExtraSupportedSurfaceCombinationsQuirk implements h2 {

    /* renamed from: a  reason: collision with root package name */
    private static final v2 f2633a = f();

    /* renamed from: b  reason: collision with root package name */
    private static final v2 f2634b = g();

    /* renamed from: c  reason: collision with root package name */
    private static final Set f2635c = new HashSet(Arrays.asList("PIXEL 6", "PIXEL 6 PRO", "PIXEL 7", "PIXEL 7 PRO", "PIXEL 8", "PIXEL 8 PRO"));

    /* renamed from: d  reason: collision with root package name */
    private static final Set f2636d = new HashSet(Arrays.asList("SM-S921", "SC-51E", "SCG25", "SM-S926", "SM-S928", "SC-52E", "SCG26"));

    private static v2 f() {
        v2 v2Var = new v2();
        w2.b bVar = w2.b.YUV;
        v2Var.a(w2.a(bVar, w2.a.VGA));
        v2Var.a(w2.a(w2.b.PRIV, w2.a.PREVIEW));
        v2Var.a(w2.a(bVar, w2.a.MAXIMUM));
        return v2Var;
    }

    private static v2 g() {
        v2 v2Var = new v2();
        w2.b bVar = w2.b.PRIV;
        v2Var.a(w2.a(bVar, w2.a.PREVIEW));
        v2Var.a(w2.a(bVar, w2.a.VGA));
        v2Var.a(w2.a(w2.b.YUV, w2.a.MAXIMUM));
        return v2Var;
    }

    private List i(String str) {
        ArrayList arrayList = new ArrayList();
        if (str.equals("1")) {
            arrayList.add(f2633a);
        }
        return arrayList;
    }

    private static boolean j() {
        String str = Build.DEVICE;
        return "heroqltevzw".equalsIgnoreCase(str) || "heroqltetmo".equalsIgnoreCase(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean k() {
        return j() || l() || m();
    }

    private static boolean l() {
        if ("google".equalsIgnoreCase(Build.BRAND)) {
            return f2635c.contains(Build.MODEL.toUpperCase(Locale.US));
        }
        return false;
    }

    private static boolean m() {
        if ("samsung".equalsIgnoreCase(Build.BRAND)) {
            String upperCase = Build.MODEL.toUpperCase(Locale.US);
            for (String str : f2636d) {
                if (upperCase.startsWith(str)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public List h(String str) {
        return j() ? i(str) : (l() || m()) ? Collections.singletonList(f2634b) : Collections.emptyList();
    }
}
