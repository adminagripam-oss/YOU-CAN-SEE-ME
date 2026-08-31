package p0;

import b0.g3;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import y.a0;
/* loaded from: classes.dex */
public abstract class a {
    private static a0 a(List list) {
        if (list.isEmpty()) {
            return null;
        }
        a0 a0Var = (a0) list.get(0);
        Integer valueOf = Integer.valueOf(a0Var.b());
        Integer valueOf2 = Integer.valueOf(a0Var.a());
        for (int i8 = 1; i8 < list.size(); i8++) {
            a0 a0Var2 = (a0) list.get(i8);
            valueOf = c(valueOf, Integer.valueOf(a0Var2.b()));
            valueOf2 = b(valueOf2, Integer.valueOf(a0Var2.a()));
            if (valueOf == null || valueOf2 == null) {
                return null;
            }
        }
        return new a0(valueOf.intValue(), valueOf2.intValue());
    }

    private static Integer b(Integer num, Integer num2) {
        if (num.equals(0)) {
            return num2;
        }
        if (num2.equals(0) || num.equals(num2)) {
            return num;
        }
        return null;
    }

    private static Integer c(Integer num, Integer num2) {
        if (num.equals(0)) {
            return num2;
        }
        if (num2.equals(0)) {
            return num;
        }
        if (!num.equals(2) || num2.equals(1)) {
            if ((!num2.equals(2) || num.equals(1)) && !num.equals(num2)) {
                return null;
            }
            return num;
        }
        return num2;
    }

    public static a0 d(Set set) {
        ArrayList arrayList = new ArrayList();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            arrayList.add(((g3) it.next()).o());
        }
        return a(arrayList);
    }
}
