package a4;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class i extends z0 {

    /* renamed from: g  reason: collision with root package name */
    final transient Map f658g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ q f659h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(q qVar, Map map) {
        this.f659h = qVar;
        this.f658g = map;
    }

    @Override // a4.z0
    protected final Set a() {
        return new g(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        Map map;
        Map map2 = this.f658g;
        q qVar = this.f659h;
        map = qVar.f957g;
        if (map2 == map) {
            qVar.q();
        } else {
            t0.a(new h(this));
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        return a1.b(this.f658g, obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        return this == obj || this.f658g.equals(obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final /* bridge */ /* synthetic */ Object get(Object obj) {
        Collection collection = (Collection) a1.a(this.f658g, obj);
        if (collection == null) {
            return null;
        }
        return this.f659h.h(obj, collection);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        return this.f658g.hashCode();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        return this.f659h.c();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final /* bridge */ /* synthetic */ Object remove(Object obj) {
        Collection collection = (Collection) this.f658g.remove(obj);
        if (collection == null) {
            return null;
        }
        Collection g8 = this.f659h.g();
        g8.addAll(collection);
        q.l(this.f659h, collection.size());
        collection.clear();
        return g8;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.f658g.size();
    }

    @Override // java.util.AbstractMap
    public final String toString() {
        return this.f658g.toString();
    }
}
