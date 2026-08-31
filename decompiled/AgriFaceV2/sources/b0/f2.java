package b0;

import android.util.ArrayMap;
import b0.u0;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
/* loaded from: classes.dex */
public class f2 implements u0 {
    protected static final Comparator J;
    private static final f2 K;
    protected final TreeMap I;

    static {
        Comparator comparator = new Comparator() { // from class: b0.e2
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int c02;
                c02 = f2.c0((u0.a) obj, (u0.a) obj2);
                return c02;
            }
        };
        J = comparator;
        K = new f2(new TreeMap(comparator));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public f2(TreeMap treeMap) {
        this.I = treeMap;
    }

    public static f2 a0() {
        return K;
    }

    public static f2 b0(u0 u0Var) {
        if (f2.class.equals(u0Var.getClass())) {
            return (f2) u0Var;
        }
        TreeMap treeMap = new TreeMap(J);
        for (u0.a aVar : u0Var.d()) {
            Set<u0.c> g8 = u0Var.g(aVar);
            ArrayMap arrayMap = new ArrayMap();
            for (u0.c cVar : g8) {
                arrayMap.put(cVar, u0Var.e(aVar, cVar));
            }
            treeMap.put(aVar, arrayMap);
        }
        return new f2(treeMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int c0(u0.a aVar, u0.a aVar2) {
        return aVar.c().compareTo(aVar2.c());
    }

    @Override // b0.u0
    public boolean a(u0.a aVar) {
        return this.I.containsKey(aVar);
    }

    @Override // b0.u0
    public Object b(u0.a aVar) {
        Map map = (Map) this.I.get(aVar);
        if (map != null) {
            return map.get((u0.c) Collections.min(map.keySet()));
        }
        throw new IllegalArgumentException("Option does not exist: " + aVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x001a  */
    @Override // b0.u0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void c(java.lang.String r4, b0.u0.b r5) {
        /*
            r3 = this;
            java.lang.Class<java.lang.Void> r0 = java.lang.Void.class
            b0.u0$a r0 = b0.u0.a.a(r4, r0)
            java.util.TreeMap r1 = r3.I
            java.util.SortedMap r0 = r1.tailMap(r0)
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L14:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L3d
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getKey()
            b0.u0$a r2 = (b0.u0.a) r2
            java.lang.String r2 = r2.c()
            boolean r2 = r2.startsWith(r4)
            if (r2 != 0) goto L31
            goto L3d
        L31:
            java.lang.Object r1 = r1.getKey()
            b0.u0$a r1 = (b0.u0.a) r1
            boolean r1 = r5.a(r1)
            if (r1 != 0) goto L14
        L3d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: b0.f2.c(java.lang.String, b0.u0$b):void");
    }

    @Override // b0.u0
    public Set d() {
        return Collections.unmodifiableSet(this.I.keySet());
    }

    @Override // b0.u0
    public Object e(u0.a aVar, u0.c cVar) {
        Map map = (Map) this.I.get(aVar);
        if (map == null) {
            throw new IllegalArgumentException("Option does not exist: " + aVar);
        } else if (map.containsKey(cVar)) {
            return map.get(cVar);
        } else {
            throw new IllegalArgumentException("Option does not exist: " + aVar + " with priority=" + cVar);
        }
    }

    @Override // b0.u0
    public Object f(u0.a aVar, Object obj) {
        try {
            return b(aVar);
        } catch (IllegalArgumentException unused) {
            return obj;
        }
    }

    @Override // b0.u0
    public Set g(u0.a aVar) {
        Map map = (Map) this.I.get(aVar);
        return map == null ? Collections.emptySet() : Collections.unmodifiableSet(map.keySet());
    }

    @Override // b0.u0
    public u0.c h(u0.a aVar) {
        Map map = (Map) this.I.get(aVar);
        if (map != null) {
            return (u0.c) Collections.min(map.keySet());
        }
        throw new IllegalArgumentException("Option does not exist: " + aVar);
    }
}
