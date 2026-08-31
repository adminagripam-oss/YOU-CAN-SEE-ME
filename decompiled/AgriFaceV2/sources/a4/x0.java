package a4;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes.dex */
abstract class x0 extends k1 {

    /* renamed from: e  reason: collision with root package name */
    final Map f1235e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public x0(Map map) {
        map.getClass();
        this.f1235e = map;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.f1235e.containsKey(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return this.f1235e.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public abstract Iterator iterator();

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f1235e.size();
    }
}
