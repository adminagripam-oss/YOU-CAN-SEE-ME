package s6;

import java.lang.reflect.Array;
/* loaded from: classes.dex */
abstract class g {
    public static final Object[] a(Object[] objArr, int i8) {
        d7.k.e(objArr, "reference");
        Object newInstance = Array.newInstance(objArr.getClass().getComponentType(), i8);
        d7.k.c(newInstance, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.arrayOfNulls>");
        return (Object[]) newInstance;
    }
}
