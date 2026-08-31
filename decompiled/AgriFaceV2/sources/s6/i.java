package s6;

import java.util.Arrays;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class i extends h {
    public static final List b(Object[] objArr) {
        d7.k.e(objArr, "<this>");
        List a8 = k.a(objArr);
        d7.k.d(a8, "asList(...)");
        return a8;
    }

    public static final Object[] c(Object[] objArr, Object[] objArr2, int i8, int i9, int i10) {
        d7.k.e(objArr, "<this>");
        d7.k.e(objArr2, "destination");
        System.arraycopy(objArr, i9, objArr2, i8, i10 - i9);
        return objArr2;
    }

    public static /* synthetic */ Object[] d(Object[] objArr, Object[] objArr2, int i8, int i9, int i10, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i8 = 0;
        }
        if ((i11 & 4) != 0) {
            i9 = 0;
        }
        if ((i11 & 8) != 0) {
            i10 = objArr.length;
        }
        return c(objArr, objArr2, i8, i9, i10);
    }

    public static final void e(Object[] objArr, Object obj, int i8, int i9) {
        d7.k.e(objArr, "<this>");
        Arrays.fill(objArr, i8, i9, obj);
    }
}
