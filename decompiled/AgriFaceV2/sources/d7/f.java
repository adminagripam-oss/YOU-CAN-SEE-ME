package d7;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
/* loaded from: classes.dex */
public abstract class f {

    /* renamed from: a  reason: collision with root package name */
    private static final Object[] f6476a = new Object[0];

    public static final Object[] a(Collection collection) {
        k.e(collection, "collection");
        int size = collection.size();
        if (size != 0) {
            Iterator it = collection.iterator();
            if (it.hasNext()) {
                Object[] objArr = new Object[size];
                int i8 = 0;
                while (true) {
                    int i9 = i8 + 1;
                    objArr[i8] = it.next();
                    if (i9 >= objArr.length) {
                        if (!it.hasNext()) {
                            return objArr;
                        }
                        int i10 = ((i9 * 3) + 1) >>> 1;
                        if (i10 <= i9) {
                            i10 = 2147483645;
                            if (i9 >= 2147483645) {
                                throw new OutOfMemoryError();
                            }
                        }
                        objArr = Arrays.copyOf(objArr, i10);
                        k.d(objArr, "copyOf(...)");
                    } else if (!it.hasNext()) {
                        Object[] copyOf = Arrays.copyOf(objArr, i9);
                        k.d(copyOf, "copyOf(...)");
                        return copyOf;
                    }
                    i8 = i9;
                }
            }
        }
        return f6476a;
    }

    public static final Object[] b(Collection collection, Object[] objArr) {
        Object[] objArr2;
        k.e(collection, "collection");
        objArr.getClass();
        int size = collection.size();
        int i8 = 0;
        if (size == 0) {
            if (objArr.length > 0) {
                objArr[0] = null;
                return objArr;
            }
            return objArr;
        }
        Iterator it = collection.iterator();
        if (!it.hasNext()) {
            if (objArr.length > 0) {
                objArr[0] = null;
                return objArr;
            }
            return objArr;
        }
        if (size <= objArr.length) {
            objArr2 = objArr;
        } else {
            Object newInstance = Array.newInstance(objArr.getClass().getComponentType(), size);
            k.c(newInstance, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            objArr2 = (Object[]) newInstance;
        }
        while (true) {
            int i9 = i8 + 1;
            objArr2[i8] = it.next();
            if (i9 >= objArr2.length) {
                if (!it.hasNext()) {
                    return objArr2;
                }
                int i10 = ((i9 * 3) + 1) >>> 1;
                if (i10 <= i9) {
                    i10 = 2147483645;
                    if (i9 >= 2147483645) {
                        throw new OutOfMemoryError();
                    }
                }
                objArr2 = Arrays.copyOf(objArr2, i10);
                k.d(objArr2, "copyOf(...)");
            } else if (!it.hasNext()) {
                if (objArr2 == objArr) {
                    objArr[i9] = null;
                    return objArr;
                }
                Object[] copyOf = Arrays.copyOf(objArr2, i9);
                k.d(copyOf, "copyOf(...)");
                return copyOf;
            }
            i8 = i9;
        }
    }
}
