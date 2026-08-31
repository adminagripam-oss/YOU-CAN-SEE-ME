package r;

import android.hardware.camera2.CameraCharacteristics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes.dex */
abstract class l2 {
    private static String a(s.o0 o0Var, Integer num, List list) {
        if (num != null && list.contains("0") && list.contains("1")) {
            if (num.intValue() == 1) {
                if (((Integer) o0Var.c("0").a(CameraCharacteristics.LENS_FACING)).intValue() == 1) {
                    return "1";
                }
                return null;
            } else if (num.intValue() == 0 && ((Integer) o0Var.c("1").a(CameraCharacteristics.LENS_FACING)).intValue() == 0) {
                return "0";
            } else {
                return null;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List b(v vVar, y.q qVar) {
        String str;
        try {
            ArrayList arrayList = new ArrayList();
            List<String> asList = Arrays.asList(vVar.b().d());
            if (qVar == null) {
                for (String str2 : asList) {
                    arrayList.add(str2);
                }
                return arrayList;
            }
            try {
                str = a(vVar.b(), qVar.d(), asList);
            } catch (IllegalStateException unused) {
                str = null;
            }
            ArrayList arrayList2 = new ArrayList();
            for (String str3 : asList) {
                if (!str3.equals(str)) {
                    arrayList2.add(vVar.f(str3));
                }
            }
            for (y.p pVar : qVar.b(arrayList2)) {
                arrayList.add(((b0.g0) pVar).g());
            }
            return arrayList;
        } catch (s.g e8) {
            throw new y.d1(n2.a(e8));
        } catch (y.s e9) {
            throw new y.d1(e9);
        }
    }
}
