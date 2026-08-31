package s6;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class e0 extends d0 {
    public static Map d() {
        y yVar = y.f11602e;
        d7.k.c(yVar, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.emptyMap, V of kotlin.collections.MapsKt__MapsKt.emptyMap>");
        return yVar;
    }

    public static final Map e(Map map) {
        d7.k.e(map, "<this>");
        int size = map.size();
        return size != 0 ? size != 1 ? map : d0.c(map) : b0.d();
    }

    public static final void f(Map map, Iterable iterable) {
        d7.k.e(map, "<this>");
        d7.k.e(iterable, "pairs");
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            r6.j jVar = (r6.j) it.next();
            map.put(jVar.a(), jVar.b());
        }
    }

    public static Map g(Iterable iterable) {
        d7.k.e(iterable, "<this>");
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            int size = collection.size();
            if (size != 0) {
                if (size != 1) {
                    return h(iterable, new LinkedHashMap(b0.a(collection.size())));
                }
                return d0.b((r6.j) (iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next()));
            }
            return b0.d();
        }
        return e(h(iterable, new LinkedHashMap()));
    }

    public static final Map h(Iterable iterable, Map map) {
        d7.k.e(iterable, "<this>");
        d7.k.e(map, "destination");
        f(map, iterable);
        return map;
    }

    public static Map i(Map map) {
        d7.k.e(map, "<this>");
        int size = map.size();
        return size != 0 ? size != 1 ? j(map) : d0.c(map) : b0.d();
    }

    public static final Map j(Map map) {
        d7.k.e(map, "<this>");
        return new LinkedHashMap(map);
    }
}
