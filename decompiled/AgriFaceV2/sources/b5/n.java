package b5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class n {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private final b5.c f4666a;

        /* renamed from: b  reason: collision with root package name */
        private final Set f4667b = new HashSet();

        /* renamed from: c  reason: collision with root package name */
        private final Set f4668c = new HashSet();

        b(b5.c cVar) {
            this.f4666a = cVar;
        }

        void a(b bVar) {
            this.f4667b.add(bVar);
        }

        void b(b bVar) {
            this.f4668c.add(bVar);
        }

        b5.c c() {
            return this.f4666a;
        }

        Set d() {
            return this.f4667b;
        }

        boolean e() {
            return this.f4667b.isEmpty();
        }

        boolean f() {
            return this.f4668c.isEmpty();
        }

        void g(b bVar) {
            this.f4668c.remove(bVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        private final Class f4669a;

        /* renamed from: b  reason: collision with root package name */
        private final boolean f4670b;

        private c(Class cls, boolean z7) {
            this.f4669a = cls;
            this.f4670b = z7;
        }

        public boolean equals(Object obj) {
            if (obj instanceof c) {
                c cVar = (c) obj;
                return cVar.f4669a.equals(this.f4669a) && cVar.f4670b == this.f4670b;
            }
            return false;
        }

        public int hashCode() {
            return ((this.f4669a.hashCode() ^ 1000003) * 1000003) ^ Boolean.valueOf(this.f4670b).hashCode();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(List list) {
        Set<b> c8 = c(list);
        Set b8 = b(c8);
        int i8 = 0;
        while (!b8.isEmpty()) {
            b bVar = (b) b8.iterator().next();
            b8.remove(bVar);
            i8++;
            for (b bVar2 : bVar.d()) {
                bVar2.g(bVar);
                if (bVar2.f()) {
                    b8.add(bVar2);
                }
            }
        }
        if (i8 == list.size()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (b bVar3 : c8) {
            if (!bVar3.f() && !bVar3.e()) {
                arrayList.add(bVar3.c());
            }
        }
        throw new p(arrayList);
    }

    private static Set b(Set set) {
        HashSet hashSet = new HashSet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            if (bVar.f()) {
                hashSet.add(bVar);
            }
        }
        return hashSet;
    }

    private static Set c(List list) {
        Set<b> set;
        HashMap hashMap = new HashMap(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            b5.c cVar = (b5.c) it.next();
            b bVar = new b(cVar);
            for (Class cls : cVar.e()) {
                c cVar2 = new c(cls, !cVar.j());
                if (!hashMap.containsKey(cVar2)) {
                    hashMap.put(cVar2, new HashSet());
                }
                Set set2 = (Set) hashMap.get(cVar2);
                if (!set2.isEmpty() && !cVar2.f4670b) {
                    throw new IllegalArgumentException(String.format("Multiple components provide %s.", cls));
                }
                set2.add(bVar);
            }
        }
        for (Set<b> set3 : hashMap.values()) {
            for (b bVar2 : set3) {
                for (o oVar : bVar2.c().c()) {
                    if (oVar.d() && (set = (Set) hashMap.get(new c(oVar.b(), oVar.f()))) != null) {
                        for (b bVar3 : set) {
                            bVar2.a(bVar3);
                            bVar3.b(bVar2);
                        }
                    }
                }
            }
        }
        HashSet hashSet = new HashSet();
        for (Set set4 : hashMap.values()) {
            hashSet.addAll(set4);
        }
        return hashSet;
    }
}
