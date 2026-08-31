package s6;

import java.util.Collection;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class o extends n {
    public static int k(Iterable iterable, int i8) {
        d7.k.e(iterable, "<this>");
        return iterable instanceof Collection ? ((Collection) iterable).size() : i8;
    }
}
