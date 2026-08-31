package s6;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class n extends m {
    public static ArrayList d(Object... objArr) {
        d7.k.e(objArr, "elements");
        return objArr.length == 0 ? new ArrayList() : new ArrayList(new d(objArr, true));
    }

    public static final Collection e(Object[] objArr) {
        d7.k.e(objArr, "<this>");
        return new d(objArr, false);
    }

    public static List f() {
        return x.f11601e;
    }

    public static final int g(List list) {
        d7.k.e(list, "<this>");
        return list.size() - 1;
    }

    public static List h(Object... objArr) {
        List f8;
        d7.k.e(objArr, "elements");
        if (objArr.length > 0) {
            return i.b(objArr);
        }
        f8 = f();
        return f8;
    }

    public static final List i(List list) {
        List f8;
        List b8;
        d7.k.e(list, "<this>");
        int size = list.size();
        if (size == 0) {
            f8 = f();
            return f8;
        } else if (size != 1) {
            return list;
        } else {
            b8 = m.b(list.get(0));
            return b8;
        }
    }

    public static void j() {
        throw new ArithmeticException("Index overflow has happened.");
    }
}
