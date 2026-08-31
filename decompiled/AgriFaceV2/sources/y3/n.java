package y3;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes.dex */
final class n extends j {

    /* renamed from: g  reason: collision with root package name */
    private final transient i f13251g;

    /* renamed from: h  reason: collision with root package name */
    private final transient Object[] f13252h;

    /* renamed from: i  reason: collision with root package name */
    private final transient int f13253i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(i iVar, Object[] objArr, int i8, int i9) {
        this.f13251g = iVar;
        this.f13252h = objArr;
        this.f13253i = i9;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // y3.c
    public final int b(Object[] objArr, int i8) {
        return l().b(objArr, 0);
    }

    @Override // y3.c, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value != null && value.equals(this.f13251g.get(key))) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return l().listIterator(0);
    }

    @Override // y3.j
    final f m() {
        return new m(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f13253i;
    }
}
