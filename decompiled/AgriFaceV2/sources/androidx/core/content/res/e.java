package androidx.core.content.res;

import java.lang.reflect.Array;
/* loaded from: classes.dex */
abstract class e {
    public static int[] a(int[] iArr, int i8, int i9) {
        if (i8 + 1 > iArr.length) {
            int[] iArr2 = new int[c(i8)];
            System.arraycopy(iArr, 0, iArr2, 0, i8);
            iArr = iArr2;
        }
        iArr[i8] = i9;
        return iArr;
    }

    public static Object[] b(Object[] objArr, int i8, Object obj) {
        if (i8 + 1 > objArr.length) {
            Object[] objArr2 = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), c(i8));
            System.arraycopy(objArr, 0, objArr2, 0, i8);
            objArr = objArr2;
        }
        objArr[i8] = obj;
        return objArr;
    }

    public static int c(int i8) {
        if (i8 <= 4) {
            return 8;
        }
        return i8 * 2;
    }
}
