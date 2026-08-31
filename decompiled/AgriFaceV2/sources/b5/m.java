package b5;

import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes.dex */
public class m extends b5.a implements c5.a {

    /* renamed from: g  reason: collision with root package name */
    private static final h5.b f4656g = l.a();

    /* renamed from: a  reason: collision with root package name */
    private final Map f4657a;

    /* renamed from: b  reason: collision with root package name */
    private final Map f4658b;

    /* renamed from: c  reason: collision with root package name */
    private final Map f4659c;

    /* renamed from: d  reason: collision with root package name */
    private final List f4660d;

    /* renamed from: e  reason: collision with root package name */
    private final s f4661e;

    /* renamed from: f  reason: collision with root package name */
    private final AtomicReference f4662f;

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private final Executor f4663a;

        /* renamed from: b  reason: collision with root package name */
        private final List f4664b = new ArrayList();

        /* renamed from: c  reason: collision with root package name */
        private final List f4665c = new ArrayList();

        b(Executor executor) {
            this.f4663a = executor;
        }

        public b a(c cVar) {
            this.f4665c.add(cVar);
            return this;
        }

        public b b(Collection collection) {
            this.f4664b.addAll(collection);
            return this;
        }

        public m c() {
            return new m(this.f4663a, this.f4664b, this.f4665c);
        }
    }

    private m(Executor executor, Iterable iterable, Collection collection) {
        this.f4657a = new HashMap();
        this.f4658b = new HashMap();
        this.f4659c = new HashMap();
        this.f4662f = new AtomicReference();
        s sVar = new s(executor);
        this.f4661e = sVar;
        ArrayList arrayList = new ArrayList();
        arrayList.add(c.l(sVar, s.class, g5.c.class, g5.b.class));
        arrayList.add(c.l(this, c5.a.class, new Class[0]));
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            c cVar = (c) it.next();
            if (cVar != null) {
                arrayList.add(cVar);
            }
        }
        this.f4660d = i(iterable);
        f(arrayList);
    }

    public static b e(Executor executor) {
        return new b(executor);
    }

    private void f(List list) {
        ArrayList<Runnable> arrayList = new ArrayList();
        synchronized (this) {
            Iterator it = this.f4660d.iterator();
            while (it.hasNext()) {
                try {
                    h hVar = (h) ((h5.b) it.next()).get();
                    if (hVar != null) {
                        list.addAll(hVar.a());
                        it.remove();
                    }
                } catch (t e8) {
                    it.remove();
                    Log.w("ComponentDiscovery", "Invalid component registrar.", e8);
                }
            }
            if (this.f4657a.isEmpty()) {
                n.a(list);
            } else {
                ArrayList arrayList2 = new ArrayList(this.f4657a.keySet());
                arrayList2.addAll(list);
                n.a(arrayList2);
            }
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                c cVar = (c) it2.next();
                this.f4657a.put(cVar, new u(i.a(this, cVar)));
            }
            arrayList.addAll(o(list));
            arrayList.addAll(p());
            n();
        }
        for (Runnable runnable : arrayList) {
            runnable.run();
        }
        m();
    }

    private void g(Map map, boolean z7) {
        for (Map.Entry entry : map.entrySet()) {
            c cVar = (c) entry.getKey();
            h5.b bVar = (h5.b) entry.getValue();
            if (cVar.h() || (cVar.i() && z7)) {
                bVar.get();
            }
        }
        this.f4661e.a();
    }

    private static List i(Iterable iterable) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : iterable) {
            arrayList.add(obj);
        }
        return arrayList;
    }

    private void m() {
        Boolean bool = (Boolean) this.f4662f.get();
        if (bool != null) {
            g(this.f4657a, bool.booleanValue());
        }
    }

    private void n() {
        Map map;
        Class b8;
        h5.b a8;
        for (c cVar : this.f4657a.keySet()) {
            for (o oVar : cVar.c()) {
                if (oVar.f() && !this.f4659c.containsKey(oVar.b())) {
                    map = this.f4659c;
                    b8 = oVar.b();
                    a8 = v.b(Collections.emptySet());
                } else if (this.f4658b.containsKey(oVar.b())) {
                    continue;
                } else if (oVar.e()) {
                    throw new w(String.format("Unsatisfied dependency for component %s: %s", cVar, oVar.b()));
                } else {
                    if (!oVar.f()) {
                        map = this.f4658b;
                        b8 = oVar.b();
                        a8 = z.a();
                    }
                }
                map.put(b8, a8);
            }
        }
    }

    private List o(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            c cVar = (c) it.next();
            if (cVar.j()) {
                h5.b bVar = (h5.b) this.f4657a.get(cVar);
                for (Class cls : cVar.e()) {
                    if (this.f4658b.containsKey(cls)) {
                        arrayList.add(j.a((z) ((h5.b) this.f4658b.get(cls)), bVar));
                    } else {
                        this.f4658b.put(cls, bVar);
                    }
                }
            }
        }
        return arrayList;
    }

    private List p() {
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        for (Map.Entry entry : this.f4657a.entrySet()) {
            c cVar = (c) entry.getKey();
            if (!cVar.j()) {
                h5.b bVar = (h5.b) entry.getValue();
                for (Class cls : cVar.e()) {
                    if (!hashMap.containsKey(cls)) {
                        hashMap.put(cls, new HashSet());
                    }
                    ((Set) hashMap.get(cls)).add(bVar);
                }
            }
        }
        for (Map.Entry entry2 : hashMap.entrySet()) {
            if (this.f4659c.containsKey(entry2.getKey())) {
                v vVar = (v) this.f4659c.get(entry2.getKey());
                for (h5.b bVar2 : (Set) entry2.getValue()) {
                    arrayList.add(k.a(vVar, bVar2));
                }
            } else {
                this.f4659c.put((Class) entry2.getKey(), v.b((Collection) entry2.getValue()));
            }
        }
        return arrayList;
    }

    @Override // b5.a, b5.d
    public /* bridge */ /* synthetic */ Object a(Class cls) {
        return super.a(cls);
    }

    @Override // b5.a, b5.d
    public /* bridge */ /* synthetic */ Set b(Class cls) {
        return super.b(cls);
    }

    @Override // b5.d
    public synchronized h5.b c(Class cls) {
        a0.c(cls, "Null interface requested.");
        return (h5.b) this.f4658b.get(cls);
    }

    @Override // b5.d
    public synchronized h5.b d(Class cls) {
        v vVar = (v) this.f4659c.get(cls);
        if (vVar != null) {
            return vVar;
        }
        return f4656g;
    }

    public void h(boolean z7) {
        HashMap hashMap;
        if (c2.c.a(this.f4662f, null, Boolean.valueOf(z7))) {
            synchronized (this) {
                hashMap = new HashMap(this.f4657a);
            }
            g(hashMap, z7);
        }
    }
}
