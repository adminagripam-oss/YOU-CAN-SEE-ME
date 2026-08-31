package z3;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes.dex */
final class tc extends pc {

    /* renamed from: g  reason: collision with root package name */
    private final transient oc f14149g;

    /* renamed from: h  reason: collision with root package name */
    private final transient Object[] f14150h;

    /* renamed from: i  reason: collision with root package name */
    private final transient int f14151i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public tc(oc ocVar, Object[] objArr, int i8, int i9) {
        this.f14149g = ocVar;
        this.f14150h = objArr;
        this.f14151i = i9;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // z3.la
    public final int b(Object[] objArr, int i8) {
        return l().b(objArr, 0);
    }

    @Override // z3.la, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value != null && value.equals(this.f14149g.get(key))) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return l().listIterator(0);
    }

    @Override // z3.pc
    final mc m() {
        return new sc(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f14151i;
    }
}
