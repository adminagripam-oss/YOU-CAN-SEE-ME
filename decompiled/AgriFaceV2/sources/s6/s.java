package s6;

import java.util.Collection;
import java.util.Iterator;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class s extends r {
    public static boolean l(Collection collection, Iterable iterable) {
        d7.k.e(collection, "<this>");
        d7.k.e(iterable, "elements");
        if (iterable instanceof Collection) {
            return collection.addAll((Collection) iterable);
        }
        boolean z7 = false;
        for (Object obj : iterable) {
            if (collection.add(obj)) {
                z7 = true;
            }
        }
        return z7;
    }

    private static final boolean m(Iterable iterable, c7.l lVar, boolean z7) {
        Iterator it = iterable.iterator();
        boolean z8 = false;
        while (it.hasNext()) {
            if (((Boolean) lVar.g(it.next())).booleanValue() == z7) {
                it.remove();
                z8 = true;
            }
        }
        return z8;
    }

    public static boolean n(Iterable iterable, c7.l lVar) {
        d7.k.e(iterable, "<this>");
        d7.k.e(lVar, "predicate");
        return m(iterable, lVar, false);
    }
}
