package androidx.lifecycle;

import androidx.lifecycle.i;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import l.b;
/* loaded from: classes.dex */
public class n extends i {

    /* renamed from: k  reason: collision with root package name */
    public static final a f3802k = new a(null);

    /* renamed from: b  reason: collision with root package name */
    private final boolean f3803b;

    /* renamed from: c  reason: collision with root package name */
    private l.a f3804c;

    /* renamed from: d  reason: collision with root package name */
    private i.b f3805d;

    /* renamed from: e  reason: collision with root package name */
    private final WeakReference f3806e;

    /* renamed from: f  reason: collision with root package name */
    private int f3807f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f3808g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f3809h;

    /* renamed from: i  reason: collision with root package name */
    private ArrayList f3810i;

    /* renamed from: j  reason: collision with root package name */
    private final n7.j f3811j;

    /* loaded from: classes.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(d7.g gVar) {
            this();
        }

        public final i.b a(i.b bVar, i.b bVar2) {
            d7.k.e(bVar, "state1");
            return (bVar2 == null || bVar2.compareTo(bVar) >= 0) ? bVar : bVar2;
        }
    }

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private i.b f3812a;

        /* renamed from: b  reason: collision with root package name */
        private k f3813b;

        public b(l lVar, i.b bVar) {
            d7.k.e(bVar, "initialState");
            d7.k.b(lVar);
            this.f3813b = o.f(lVar);
            this.f3812a = bVar;
        }

        public final void a(m mVar, i.a aVar) {
            d7.k.e(aVar, "event");
            i.b f8 = aVar.f();
            this.f3812a = n.f3802k.a(this.f3812a, f8);
            k kVar = this.f3813b;
            d7.k.b(mVar);
            kVar.f(mVar, aVar);
            this.f3812a = f8;
        }

        public final i.b b() {
            return this.f3812a;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public n(m mVar) {
        this(mVar, true);
        d7.k.e(mVar, "provider");
    }

    private final void d(m mVar) {
        Iterator b8 = this.f3804c.b();
        d7.k.d(b8, "observerMap.descendingIterator()");
        while (b8.hasNext() && !this.f3809h) {
            Map.Entry entry = (Map.Entry) b8.next();
            d7.k.d(entry, "next()");
            l lVar = (l) entry.getKey();
            b bVar = (b) entry.getValue();
            while (bVar.b().compareTo(this.f3805d) > 0 && !this.f3809h && this.f3804c.contains(lVar)) {
                i.a a8 = i.a.Companion.a(bVar.b());
                if (a8 == null) {
                    throw new IllegalStateException("no event down from " + bVar.b());
                }
                l(a8.f());
                bVar.a(mVar, a8);
                k();
            }
        }
    }

    private final i.b e(l lVar) {
        b bVar;
        Map.Entry p8 = this.f3804c.p(lVar);
        i.b bVar2 = null;
        i.b b8 = (p8 == null || (bVar = (b) p8.getValue()) == null) ? null : bVar.b();
        if (!this.f3810i.isEmpty()) {
            ArrayList arrayList = this.f3810i;
            bVar2 = (i.b) arrayList.get(arrayList.size() - 1);
        }
        a aVar = f3802k;
        return aVar.a(aVar.a(this.f3805d, b8), bVar2);
    }

    private final void f(String str) {
        if (!this.f3803b || k.c.f().b()) {
            return;
        }
        throw new IllegalStateException(("Method " + str + " must be called on the main thread").toString());
    }

    private final void g(m mVar) {
        b.d k8 = this.f3804c.k();
        d7.k.d(k8, "observerMap.iteratorWithAdditions()");
        while (k8.hasNext() && !this.f3809h) {
            Map.Entry entry = (Map.Entry) k8.next();
            l lVar = (l) entry.getKey();
            b bVar = (b) entry.getValue();
            while (bVar.b().compareTo(this.f3805d) < 0 && !this.f3809h && this.f3804c.contains(lVar)) {
                l(bVar.b());
                i.a b8 = i.a.Companion.b(bVar.b());
                if (b8 == null) {
                    throw new IllegalStateException("no event up from " + bVar.b());
                }
                bVar.a(mVar, b8);
                k();
            }
        }
    }

    private final boolean i() {
        if (this.f3804c.size() == 0) {
            return true;
        }
        Map.Entry f8 = this.f3804c.f();
        d7.k.b(f8);
        i.b b8 = ((b) f8.getValue()).b();
        Map.Entry l8 = this.f3804c.l();
        d7.k.b(l8);
        i.b b9 = ((b) l8.getValue()).b();
        return b8 == b9 && this.f3805d == b9;
    }

    private final void j(i.b bVar) {
        i.b bVar2 = this.f3805d;
        if (bVar2 == bVar) {
            return;
        }
        if (!((bVar2 == i.b.INITIALIZED && bVar == i.b.DESTROYED) ? false : true)) {
            throw new IllegalStateException(("no event down from " + this.f3805d + " in component " + this.f3806e.get()).toString());
        }
        this.f3805d = bVar;
        if (this.f3808g || this.f3807f != 0) {
            this.f3809h = true;
            return;
        }
        this.f3808g = true;
        n();
        this.f3808g = false;
        if (this.f3805d == i.b.DESTROYED) {
            this.f3804c = new l.a();
        }
    }

    private final void k() {
        ArrayList arrayList = this.f3810i;
        arrayList.remove(arrayList.size() - 1);
    }

    private final void l(i.b bVar) {
        this.f3810i.add(bVar);
    }

    private final void n() {
        m mVar = (m) this.f3806e.get();
        if (mVar == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is already garbage collected. It is too late to change lifecycle state.");
        }
        while (true) {
            boolean i8 = i();
            this.f3809h = false;
            if (i8) {
                this.f3811j.setValue(b());
                return;
            }
            i.b bVar = this.f3805d;
            Map.Entry f8 = this.f3804c.f();
            d7.k.b(f8);
            if (bVar.compareTo(((b) f8.getValue()).b()) < 0) {
                d(mVar);
            }
            Map.Entry l8 = this.f3804c.l();
            if (!this.f3809h && l8 != null && this.f3805d.compareTo(((b) l8.getValue()).b()) > 0) {
                g(mVar);
            }
        }
    }

    @Override // androidx.lifecycle.i
    public void a(l lVar) {
        m mVar;
        d7.k.e(lVar, "observer");
        f("addObserver");
        i.b bVar = this.f3805d;
        i.b bVar2 = i.b.DESTROYED;
        if (bVar != bVar2) {
            bVar2 = i.b.INITIALIZED;
        }
        b bVar3 = new b(lVar, bVar2);
        if (((b) this.f3804c.n(lVar, bVar3)) == null && (mVar = (m) this.f3806e.get()) != null) {
            boolean z7 = this.f3807f != 0 || this.f3808g;
            i.b e8 = e(lVar);
            this.f3807f++;
            while (bVar3.b().compareTo(e8) < 0 && this.f3804c.contains(lVar)) {
                l(bVar3.b());
                i.a b8 = i.a.Companion.b(bVar3.b());
                if (b8 == null) {
                    throw new IllegalStateException("no event up from " + bVar3.b());
                }
                bVar3.a(mVar, b8);
                k();
                e8 = e(lVar);
            }
            if (!z7) {
                n();
            }
            this.f3807f--;
        }
    }

    @Override // androidx.lifecycle.i
    public i.b b() {
        return this.f3805d;
    }

    @Override // androidx.lifecycle.i
    public void c(l lVar) {
        d7.k.e(lVar, "observer");
        f("removeObserver");
        this.f3804c.o(lVar);
    }

    public void h(i.a aVar) {
        d7.k.e(aVar, "event");
        f("handleLifecycleEvent");
        j(aVar.f());
    }

    public void m(i.b bVar) {
        d7.k.e(bVar, "state");
        f("setCurrentState");
        j(bVar);
    }

    private n(m mVar, boolean z7) {
        this.f3803b = z7;
        this.f3804c = new l.a();
        i.b bVar = i.b.INITIALIZED;
        this.f3805d = bVar;
        this.f3810i = new ArrayList();
        this.f3806e = new WeakReference(mVar);
        this.f3811j = n7.o.a(bVar);
    }
}
