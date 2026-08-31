package b1;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public class a extends g implements Map {

    /* renamed from: l  reason: collision with root package name */
    f f4498l;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b1.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0059a extends f {
        C0059a() {
        }

        @Override // b1.f
        protected void a() {
            a.this.clear();
        }

        @Override // b1.f
        protected Object b(int i8, int i9) {
            return a.this.f4547f[(i8 << 1) + i9];
        }

        @Override // b1.f
        protected Map c() {
            return a.this;
        }

        @Override // b1.f
        protected int d() {
            return a.this.f4548g;
        }

        @Override // b1.f
        protected int e(Object obj) {
            return a.this.f(obj);
        }

        @Override // b1.f
        protected int f(Object obj) {
            return a.this.h(obj);
        }

        @Override // b1.f
        protected void g(Object obj, Object obj2) {
            a.this.put(obj, obj2);
        }

        @Override // b1.f
        protected void h(int i8) {
            a.this.j(i8);
        }

        @Override // b1.f
        protected Object i(int i8, Object obj) {
            return a.this.k(i8, obj);
        }
    }

    public a() {
    }

    public a(int i8) {
        super(i8);
    }

    private f m() {
        if (this.f4498l == null) {
            this.f4498l = new C0059a();
        }
        return this.f4498l;
    }

    @Override // java.util.Map
    public Set entrySet() {
        return m().l();
    }

    @Override // java.util.Map
    public Set keySet() {
        return m().m();
    }

    public boolean n(Collection collection) {
        return f.p(this, collection);
    }

    @Override // java.util.Map
    public void putAll(Map map) {
        c(this.f4548g + map.size());
        for (Map.Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public Collection values() {
        return m().n();
    }
}
