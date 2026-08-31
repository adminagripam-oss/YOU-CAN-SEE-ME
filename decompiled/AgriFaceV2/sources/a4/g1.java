package a4;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes.dex */
final class g1 extends s0 {

    /* renamed from: g  reason: collision with root package name */
    private final transient r0 f576g;

    /* renamed from: h  reason: collision with root package name */
    private final transient Object[] f577h;

    /* renamed from: i  reason: collision with root package name */
    private final transient int f578i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g1(r0 r0Var, Object[] objArr, int i8, int i9) {
        this.f576g = r0Var;
        this.f577h = objArr;
        this.f578i = i9;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // a4.k0
    public final int b(Object[] objArr, int i8) {
        return l().b(objArr, 0);
    }

    @Override // a4.k0, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value != null && value.equals(this.f576g.get(key))) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return l().listIterator(0);
    }

    @Override // a4.s0
    final p0 m() {
        return new f1(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f578i;
    }
}
