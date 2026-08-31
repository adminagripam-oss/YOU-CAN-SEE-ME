package b0;

import android.util.ArrayMap;
import b0.u0;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
/* loaded from: classes.dex */
public final class a2 extends f2 implements z1 {
    private static final u0.c L = u0.c.OPTIONAL;

    private a2(TreeMap treeMap) {
        super(treeMap);
    }

    public static a2 d0() {
        return new a2(new TreeMap(f2.J));
    }

    public static a2 e0(u0 u0Var) {
        TreeMap treeMap = new TreeMap(f2.J);
        for (u0.a aVar : u0Var.d()) {
            Set<u0.c> g8 = u0Var.g(aVar);
            ArrayMap arrayMap = new ArrayMap();
            for (u0.c cVar : g8) {
                arrayMap.put(cVar, u0Var.e(aVar, cVar));
            }
            treeMap.put(aVar, arrayMap);
        }
        return new a2(treeMap);
    }

    @Override // b0.z1
    public void F(u0.a aVar, u0.c cVar, Object obj) {
        Map map = (Map) this.I.get(aVar);
        if (map == null) {
            ArrayMap arrayMap = new ArrayMap();
            this.I.put(aVar, arrayMap);
            arrayMap.put(cVar, obj);
            return;
        }
        u0.c cVar2 = (u0.c) Collections.min(map.keySet());
        if (Objects.equals(map.get(cVar2), obj) || !u0.J(cVar2, cVar)) {
            map.put(cVar, obj);
            return;
        }
        throw new IllegalArgumentException("Option values conflicts: " + aVar.c() + ", existing value (" + cVar2 + ")=" + map.get(cVar2) + ", conflicting (" + cVar + ")=" + obj);
    }

    @Override // b0.z1
    public void Y(u0.a aVar, Object obj) {
        F(aVar, L, obj);
    }

    public Object f0(u0.a aVar) {
        return this.I.remove(aVar);
    }
}
