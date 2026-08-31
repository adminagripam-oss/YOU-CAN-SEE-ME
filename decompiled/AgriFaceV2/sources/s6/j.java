package s6;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class j extends i {
    public static final boolean f(Object[] objArr, Object obj) {
        d7.k.e(objArr, "<this>");
        return l(objArr, obj) >= 0;
    }

    public static List g(Object[] objArr, int i8) {
        int a8;
        d7.k.e(objArr, "<this>");
        if (i8 >= 0) {
            a8 = g7.f.a(objArr.length - i8, 0);
            return o(objArr, a8);
        }
        throw new IllegalArgumentException(("Requested element count " + i8 + " is less than zero.").toString());
    }

    public static List h(Object[] objArr) {
        d7.k.e(objArr, "<this>");
        return (List) i(objArr, new ArrayList());
    }

    public static final Collection i(Object[] objArr, Collection collection) {
        d7.k.e(objArr, "<this>");
        d7.k.e(collection, "destination");
        for (Object obj : objArr) {
            if (obj != null) {
                collection.add(obj);
            }
        }
        return collection;
    }

    public static final int j(Object[] objArr) {
        d7.k.e(objArr, "<this>");
        return objArr.length - 1;
    }

    public static Object k(Object[] objArr, int i8) {
        d7.k.e(objArr, "<this>");
        if (i8 < 0 || i8 > j(objArr)) {
            return null;
        }
        return objArr[i8];
    }

    public static final int l(Object[] objArr, Object obj) {
        d7.k.e(objArr, "<this>");
        int i8 = 0;
        if (obj == null) {
            int length = objArr.length;
            while (i8 < length) {
                if (objArr[i8] == null) {
                    return i8;
                }
                i8++;
            }
            return -1;
        }
        int length2 = objArr.length;
        while (i8 < length2) {
            if (d7.k.a(obj, objArr[i8])) {
                return i8;
            }
            i8++;
        }
        return -1;
    }

    public static char m(char[] cArr) {
        d7.k.e(cArr, "<this>");
        int length = cArr.length;
        if (length != 0) {
            if (length == 1) {
                return cArr[0];
            }
            throw new IllegalArgumentException("Array has more than one element.");
        }
        throw new NoSuchElementException("Array is empty.");
    }

    public static Object n(Object[] objArr) {
        d7.k.e(objArr, "<this>");
        if (objArr.length == 1) {
            return objArr[0];
        }
        return null;
    }

    public static final List o(Object[] objArr, int i8) {
        List b8;
        List p8;
        List f8;
        d7.k.e(objArr, "<this>");
        if (!(i8 >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i8 + " is less than zero.").toString());
        } else if (i8 == 0) {
            f8 = n.f();
            return f8;
        } else {
            int length = objArr.length;
            if (i8 >= length) {
                p8 = p(objArr);
                return p8;
            } else if (i8 == 1) {
                b8 = m.b(objArr[length - 1]);
                return b8;
            } else {
                ArrayList arrayList = new ArrayList(i8);
                for (int i9 = length - i8; i9 < length; i9++) {
                    arrayList.add(objArr[i9]);
                }
                return arrayList;
            }
        }
    }

    public static List p(Object[] objArr) {
        List f8;
        List b8;
        d7.k.e(objArr, "<this>");
        int length = objArr.length;
        if (length == 0) {
            f8 = n.f();
            return f8;
        } else if (length != 1) {
            return q(objArr);
        } else {
            b8 = m.b(objArr[0]);
            return b8;
        }
    }

    public static final List q(Object[] objArr) {
        d7.k.e(objArr, "<this>");
        return new ArrayList(n.e(objArr));
    }
}
