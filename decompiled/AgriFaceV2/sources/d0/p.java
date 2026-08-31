package d0;

import b0.s2;
import java.util.ArrayList;
import y.d0;
/* loaded from: classes.dex */
public abstract class p {
    public static d0 a(s2 s2Var, d0 d0Var) {
        boolean z7;
        if (s2Var == null) {
            return d0Var;
        }
        d0.a aVar = new d0.a(d0Var);
        boolean z8 = true;
        if (d0Var.c().isEmpty() || b(s2Var, 1, 2)) {
            z7 = false;
        } else {
            aVar.e(1);
            z7 = true;
        }
        if (!d0Var.b().isEmpty() && !b(s2Var, 3)) {
            aVar.e(2);
            z7 = true;
        }
        if (d0Var.d().isEmpty() || b(s2Var, 4)) {
            z8 = z7;
        } else {
            aVar.e(4);
        }
        if (z8) {
            d0 c8 = aVar.c();
            if (c8.c().isEmpty() && c8.b().isEmpty() && c8.d().isEmpty()) {
                return null;
            }
            return aVar.c();
        }
        return d0Var;
    }

    public static boolean b(s2 s2Var, int... iArr) {
        if (s2Var == null) {
            return true;
        }
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int i8 : iArr) {
            arrayList.add(Integer.valueOf(i8));
        }
        return s2Var.a().containsAll(arrayList);
    }
}
