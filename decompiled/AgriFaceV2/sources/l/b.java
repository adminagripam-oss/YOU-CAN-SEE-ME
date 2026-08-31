package l;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
/* loaded from: classes.dex */
public class b implements Iterable {

    /* renamed from: e  reason: collision with root package name */
    c f8807e;

    /* renamed from: f  reason: collision with root package name */
    private c f8808f;

    /* renamed from: g  reason: collision with root package name */
    private final WeakHashMap f8809g = new WeakHashMap();

    /* renamed from: h  reason: collision with root package name */
    private int f8810h = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a extends e {
        a(c cVar, c cVar2) {
            super(cVar, cVar2);
        }

        @Override // l.b.e
        c c(c cVar) {
            return cVar.f8814h;
        }

        @Override // l.b.e
        c d(c cVar) {
            return cVar.f8813g;
        }
    }

    /* renamed from: l.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private static class C0119b extends e {
        C0119b(c cVar, c cVar2) {
            super(cVar, cVar2);
        }

        @Override // l.b.e
        c c(c cVar) {
            return cVar.f8813g;
        }

        @Override // l.b.e
        c d(c cVar) {
            return cVar.f8814h;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class c implements Map.Entry {

        /* renamed from: e  reason: collision with root package name */
        final Object f8811e;

        /* renamed from: f  reason: collision with root package name */
        final Object f8812f;

        /* renamed from: g  reason: collision with root package name */
        c f8813g;

        /* renamed from: h  reason: collision with root package name */
        c f8814h;

        c(Object obj, Object obj2) {
            this.f8811e = obj;
            this.f8812f = obj2;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof c) {
                c cVar = (c) obj;
                return this.f8811e.equals(cVar.f8811e) && this.f8812f.equals(cVar.f8812f);
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public Object getKey() {
            return this.f8811e;
        }

        @Override // java.util.Map.Entry
        public Object getValue() {
            return this.f8812f;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.f8811e.hashCode() ^ this.f8812f.hashCode();
        }

        @Override // java.util.Map.Entry
        public Object setValue(Object obj) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.f8811e + "=" + this.f8812f;
        }
    }

    /* loaded from: classes.dex */
    public class d extends f implements Iterator {

        /* renamed from: e  reason: collision with root package name */
        private c f8815e;

        /* renamed from: f  reason: collision with root package name */
        private boolean f8816f = true;

        d() {
        }

        @Override // l.b.f
        void b(c cVar) {
            c cVar2 = this.f8815e;
            if (cVar == cVar2) {
                c cVar3 = cVar2.f8814h;
                this.f8815e = cVar3;
                this.f8816f = cVar3 == null;
            }
        }

        @Override // java.util.Iterator
        /* renamed from: c */
        public Map.Entry next() {
            c cVar;
            if (this.f8816f) {
                this.f8816f = false;
                cVar = b.this.f8807e;
            } else {
                c cVar2 = this.f8815e;
                cVar = cVar2 != null ? cVar2.f8813g : null;
            }
            this.f8815e = cVar;
            return this.f8815e;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f8816f) {
                return b.this.f8807e != null;
            }
            c cVar = this.f8815e;
            return (cVar == null || cVar.f8813g == null) ? false : true;
        }
    }

    /* loaded from: classes.dex */
    private static abstract class e extends f implements Iterator {

        /* renamed from: e  reason: collision with root package name */
        c f8818e;

        /* renamed from: f  reason: collision with root package name */
        c f8819f;

        e(c cVar, c cVar2) {
            this.f8818e = cVar2;
            this.f8819f = cVar;
        }

        private c f() {
            c cVar = this.f8819f;
            c cVar2 = this.f8818e;
            if (cVar == cVar2 || cVar2 == null) {
                return null;
            }
            return d(cVar);
        }

        @Override // l.b.f
        public void b(c cVar) {
            if (this.f8818e == cVar && cVar == this.f8819f) {
                this.f8819f = null;
                this.f8818e = null;
            }
            c cVar2 = this.f8818e;
            if (cVar2 == cVar) {
                this.f8818e = c(cVar2);
            }
            if (this.f8819f == cVar) {
                this.f8819f = f();
            }
        }

        abstract c c(c cVar);

        abstract c d(c cVar);

        @Override // java.util.Iterator
        /* renamed from: e */
        public Map.Entry next() {
            c cVar = this.f8819f;
            this.f8819f = f();
            return cVar;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f8819f != null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class f {
        abstract void b(c cVar);
    }

    public Iterator b() {
        C0119b c0119b = new C0119b(this.f8808f, this.f8807e);
        this.f8809g.put(c0119b, Boolean.FALSE);
        return c0119b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof b) {
            b bVar = (b) obj;
            if (size() != bVar.size()) {
                return false;
            }
            Iterator it = iterator();
            Iterator it2 = bVar.iterator();
            while (it.hasNext() && it2.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                Object next = it2.next();
                if ((entry == null && next != null) || (entry != null && !entry.equals(next))) {
                    return false;
                }
            }
            return (it.hasNext() || it2.hasNext()) ? false : true;
        }
        return false;
    }

    public Map.Entry f() {
        return this.f8807e;
    }

    public int hashCode() {
        Iterator it = iterator();
        int i8 = 0;
        while (it.hasNext()) {
            i8 += ((Map.Entry) it.next()).hashCode();
        }
        return i8;
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        a aVar = new a(this.f8807e, this.f8808f);
        this.f8809g.put(aVar, Boolean.FALSE);
        return aVar;
    }

    protected c j(Object obj) {
        c cVar = this.f8807e;
        while (cVar != null && !cVar.f8811e.equals(obj)) {
            cVar = cVar.f8813g;
        }
        return cVar;
    }

    public d k() {
        d dVar = new d();
        this.f8809g.put(dVar, Boolean.FALSE);
        return dVar;
    }

    public Map.Entry l() {
        return this.f8808f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c m(Object obj, Object obj2) {
        c cVar = new c(obj, obj2);
        this.f8810h++;
        c cVar2 = this.f8808f;
        if (cVar2 == null) {
            this.f8807e = cVar;
        } else {
            cVar2.f8813g = cVar;
            cVar.f8814h = cVar2;
        }
        this.f8808f = cVar;
        return cVar;
    }

    public Object n(Object obj, Object obj2) {
        c j8 = j(obj);
        if (j8 != null) {
            return j8.f8812f;
        }
        m(obj, obj2);
        return null;
    }

    public Object o(Object obj) {
        c j8 = j(obj);
        if (j8 == null) {
            return null;
        }
        this.f8810h--;
        if (!this.f8809g.isEmpty()) {
            for (f fVar : this.f8809g.keySet()) {
                fVar.b(j8);
            }
        }
        c cVar = j8.f8814h;
        c cVar2 = j8.f8813g;
        if (cVar != null) {
            cVar.f8813g = cVar2;
        } else {
            this.f8807e = cVar2;
        }
        c cVar3 = j8.f8813g;
        if (cVar3 != null) {
            cVar3.f8814h = cVar;
        } else {
            this.f8808f = cVar;
        }
        j8.f8813g = null;
        j8.f8814h = null;
        return j8.f8812f;
    }

    public int size() {
        return this.f8810h;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator it = iterator();
        while (it.hasNext()) {
            sb.append(((Map.Entry) it.next()).toString());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
