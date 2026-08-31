package a4;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;
/* loaded from: classes.dex */
public abstract class q extends s implements Serializable {

    /* renamed from: g */
    private transient Map f957g;

    /* renamed from: h */
    private transient int f958h;

    public q(Map map) {
        if (!map.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.f957g = map;
    }

    public static /* synthetic */ int i(q qVar) {
        int i8 = qVar.f958h;
        qVar.f958h = i8 + 1;
        return i8;
    }

    public static /* synthetic */ int j(q qVar) {
        int i8 = qVar.f958h;
        qVar.f958h = i8 - 1;
        return i8;
    }

    public static /* synthetic */ int k(q qVar, int i8) {
        int i9 = qVar.f958h + i8;
        qVar.f958h = i9;
        return i9;
    }

    public static /* synthetic */ int l(q qVar, int i8) {
        int i9 = qVar.f958h - i8;
        qVar.f958h = i9;
        return i9;
    }

    public static /* synthetic */ Map o(q qVar) {
        return qVar.f957g;
    }

    public static /* synthetic */ void p(q qVar, Object obj) {
        Object obj2;
        Map map = qVar.f957g;
        map.getClass();
        try {
            obj2 = map.remove(obj);
        } catch (ClassCastException | NullPointerException unused) {
            obj2 = null;
        }
        Collection collection = (Collection) obj2;
        if (collection != null) {
            int size = collection.size();
            collection.clear();
            qVar.f958h -= size;
        }
    }

    @Override // a4.c1
    public final boolean d(Object obj, Object obj2) {
        Collection collection = (Collection) this.f957g.get(obj);
        if (collection != null) {
            if (collection.add(obj2)) {
                this.f958h++;
                return true;
            }
            return false;
        }
        Collection g8 = g();
        if (g8.add(obj2)) {
            this.f958h++;
            this.f957g.put(obj, g8);
            return true;
        }
        throw new AssertionError("New Collection violated the Collection spec");
    }

    @Override // a4.s
    final Map e() {
        return new i(this, this.f957g);
    }

    @Override // a4.s
    final Set f() {
        return new k(this, this.f957g);
    }

    public abstract Collection g();

    public abstract Collection h(Object obj, Collection collection);

    public final Collection m(Object obj) {
        Collection collection = (Collection) this.f957g.get(obj);
        if (collection == null) {
            collection = g();
        }
        return h(obj, collection);
    }

    public final List n(Object obj, List list, n nVar) {
        return list instanceof RandomAccess ? new l(this, obj, list, nVar) : new p(this, obj, list, nVar);
    }

    public final void q() {
        for (Collection collection : this.f957g.values()) {
            collection.clear();
        }
        this.f957g.clear();
        this.f958h = 0;
    }
}
