package a4;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
/* loaded from: classes.dex */
public abstract class l1 {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(Set set) {
        Iterator it = set.iterator();
        int i8 = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i8 += next != null ? next.hashCode() : 0;
        }
        return i8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean b(Set set, Collection collection) {
        collection.getClass();
        if (collection instanceof d1) {
            collection = ((d1) collection).zza();
        }
        if (!(collection instanceof Set) || collection.size() <= set.size()) {
            return c(set, collection.iterator());
        }
        Iterator it = set.iterator();
        boolean z7 = false;
        while (it.hasNext()) {
            if (collection.contains(it.next())) {
                it.remove();
                z7 = true;
            }
        }
        return z7;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean c(Set set, Iterator it) {
        boolean z7 = false;
        while (it.hasNext()) {
            z7 |= set.remove(it.next());
        }
        return z7;
    }
}
