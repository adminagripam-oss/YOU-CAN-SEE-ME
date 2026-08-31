package q0;

import androidx.lifecycle.i;
import androidx.lifecycle.l;
import androidx.lifecycle.m;
import androidx.lifecycle.u;
import b0.o2;
import g0.f;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import y.i2;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    private final Object f10214a = new Object();

    /* renamed from: b  reason: collision with root package name */
    private final Map f10215b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    private final Map f10216c = new HashMap();

    /* renamed from: d  reason: collision with root package name */
    private final ArrayDeque f10217d = new ArrayDeque();

    /* renamed from: e  reason: collision with root package name */
    z.a f10218e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class a {
        static a a(m mVar, f.b bVar) {
            return new q0.a(mVar, bVar);
        }

        public abstract f.b b();

        public abstract m c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b implements l {

        /* renamed from: e  reason: collision with root package name */
        private final c f10219e;

        /* renamed from: f  reason: collision with root package name */
        private final m f10220f;

        b(m mVar, c cVar) {
            this.f10220f = mVar;
            this.f10219e = cVar;
        }

        m a() {
            return this.f10220f;
        }

        @u(i.a.ON_DESTROY)
        public void onDestroy(m mVar) {
            this.f10219e.m(mVar);
        }

        @u(i.a.ON_START)
        public void onStart(m mVar) {
            this.f10219e.h(mVar);
        }

        @u(i.a.ON_STOP)
        public void onStop(m mVar) {
            this.f10219e.i(mVar);
        }
    }

    private b d(m mVar) {
        synchronized (this.f10214a) {
            for (b bVar : this.f10216c.keySet()) {
                if (mVar.equals(bVar.a())) {
                    return bVar;
                }
            }
            return null;
        }
    }

    private boolean f(m mVar) {
        synchronized (this.f10214a) {
            b d8 = d(mVar);
            if (d8 == null) {
                return false;
            }
            for (a aVar : (Set) this.f10216c.get(d8)) {
                if (!((q0.b) g1.e.j((q0.b) this.f10215b.get(aVar))).t().isEmpty()) {
                    return true;
                }
            }
            return false;
        }
    }

    private void g(q0.b bVar) {
        synchronized (this.f10214a) {
            m r8 = bVar.r();
            a a8 = a.a(r8, g0.f.A((o2) bVar.b(), (o2) bVar.s()));
            b d8 = d(r8);
            Set hashSet = d8 != null ? (Set) this.f10216c.get(d8) : new HashSet();
            hashSet.add(a8);
            this.f10215b.put(a8, bVar);
            if (d8 == null) {
                b bVar2 = new b(r8, this);
                this.f10216c.put(bVar2, hashSet);
                r8.getLifecycle().a(bVar2);
            }
        }
    }

    private void j(m mVar) {
        synchronized (this.f10214a) {
            b d8 = d(mVar);
            if (d8 == null) {
                return;
            }
            for (a aVar : (Set) this.f10216c.get(d8)) {
                ((q0.b) g1.e.j((q0.b) this.f10215b.get(aVar))).v();
            }
        }
    }

    private void n(m mVar) {
        synchronized (this.f10214a) {
            for (a aVar : (Set) this.f10216c.get(d(mVar))) {
                q0.b bVar = (q0.b) this.f10215b.get(aVar);
                if (!((q0.b) g1.e.j(bVar)).t().isEmpty()) {
                    bVar.y();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(q0.b bVar, i2 i2Var, List list, Collection collection, z.a aVar) {
        synchronized (this.f10214a) {
            g1.e.a(!collection.isEmpty());
            this.f10218e = aVar;
            m r8 = bVar.r();
            b d8 = d(r8);
            if (d8 == null) {
                return;
            }
            Set<a> set = (Set) this.f10216c.get(d8);
            z.a aVar2 = this.f10218e;
            if (aVar2 == null || aVar2.a() != 2) {
                for (a aVar3 : set) {
                    q0.b bVar2 = (q0.b) g1.e.j((q0.b) this.f10215b.get(aVar3));
                    if (!bVar2.equals(bVar) && !bVar2.t().isEmpty()) {
                        throw new IllegalArgumentException("Multiple LifecycleCameras with use cases are registered to the same LifecycleOwner.");
                    }
                }
            }
            try {
                bVar.n().c0(i2Var);
                bVar.n().a0(list);
                bVar.m(collection);
                if (r8.getLifecycle().b().e(i.b.STARTED)) {
                    h(r8);
                }
            } catch (f.a e8) {
                throw new IllegalArgumentException(e8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public q0.b b(m mVar, g0.f fVar) {
        synchronized (this.f10214a) {
            g1.e.b(this.f10215b.get(a.a(mVar, fVar.C())) == null, "LifecycleCamera already exists for the given LifecycleOwner and set of cameras");
            q0.b bVar = new q0.b(mVar, fVar);
            if (fVar.I().isEmpty()) {
                bVar.v();
            }
            if (mVar.getLifecycle().b() == i.b.DESTROYED) {
                return bVar;
            }
            g(bVar);
            return bVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public q0.b c(m mVar, f.b bVar) {
        q0.b bVar2;
        synchronized (this.f10214a) {
            bVar2 = (q0.b) this.f10215b.get(a.a(mVar, bVar));
        }
        return bVar2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Collection e() {
        Collection unmodifiableCollection;
        synchronized (this.f10214a) {
            unmodifiableCollection = Collections.unmodifiableCollection(this.f10215b.values());
        }
        return unmodifiableCollection;
    }

    void h(m mVar) {
        ArrayDeque arrayDeque;
        synchronized (this.f10214a) {
            if (f(mVar)) {
                if (!this.f10217d.isEmpty()) {
                    z.a aVar = this.f10218e;
                    if (aVar == null || aVar.a() != 2) {
                        m mVar2 = (m) this.f10217d.peek();
                        if (!mVar.equals(mVar2)) {
                            j(mVar2);
                            this.f10217d.remove(mVar);
                            arrayDeque = this.f10217d;
                        }
                    }
                    n(mVar);
                }
                arrayDeque = this.f10217d;
                arrayDeque.push(mVar);
                n(mVar);
            }
        }
    }

    void i(m mVar) {
        synchronized (this.f10214a) {
            this.f10217d.remove(mVar);
            j(mVar);
            if (!this.f10217d.isEmpty()) {
                n((m) this.f10217d.peek());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k(Collection collection) {
        synchronized (this.f10214a) {
            for (a aVar : this.f10215b.keySet()) {
                q0.b bVar = (q0.b) this.f10215b.get(aVar);
                boolean z7 = !bVar.t().isEmpty();
                bVar.w(collection);
                if (z7 && bVar.t().isEmpty()) {
                    i(bVar.r());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l() {
        synchronized (this.f10214a) {
            for (a aVar : this.f10215b.keySet()) {
                q0.b bVar = (q0.b) this.f10215b.get(aVar);
                bVar.x();
                i(bVar.r());
            }
        }
    }

    void m(m mVar) {
        synchronized (this.f10214a) {
            b d8 = d(mVar);
            if (d8 == null) {
                return;
            }
            i(mVar);
            for (a aVar : (Set) this.f10216c.get(d8)) {
                this.f10215b.remove(aVar);
            }
            this.f10216c.remove(d8);
            d8.a().getLifecycle().c(d8);
        }
    }
}
