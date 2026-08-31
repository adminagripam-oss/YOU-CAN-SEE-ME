package s6;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class m {
    public static final Object[] a(Object[] objArr, boolean z7) {
        d7.k.e(objArr, "<this>");
        if (z7 && d7.k.a(objArr.getClass(), Object[].class)) {
            return objArr;
        }
        Object[] copyOf = Arrays.copyOf(objArr, objArr.length, Object[].class);
        d7.k.d(copyOf, "copyOf(...)");
        return copyOf;
    }

    public static List b(Object obj) {
        List singletonList = Collections.singletonList(obj);
        d7.k.d(singletonList, "singletonList(...)");
        return singletonList;
    }

    public static final Object[] c(int i8, Object[] objArr) {
        d7.k.e(objArr, "array");
        if (i8 < objArr.length) {
            objArr[i8] = null;
        }
        return objArr;
    }
}
