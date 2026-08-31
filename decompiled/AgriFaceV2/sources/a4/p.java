package a4;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class p extends n implements List {

    /* renamed from: j  reason: collision with root package name */
    final /* synthetic */ q f911j;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(q qVar, Object obj, List list, n nVar) {
        super(qVar, obj, list, nVar);
        this.f911j = qVar;
    }

    @Override // java.util.List
    public final void add(int i8, Object obj) {
        zzb();
        boolean isEmpty = this.f837f.isEmpty();
        ((List) this.f837f).add(i8, obj);
        q.i(this.f911j);
        if (isEmpty) {
            b();
        }
    }

    @Override // java.util.List
    public final boolean addAll(int i8, Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean addAll = ((List) this.f837f).addAll(i8, collection);
        if (addAll) {
            q.k(this.f911j, this.f837f.size() - size);
            if (size == 0) {
                b();
                return true;
            }
            return addAll;
        }
        return addAll;
    }

    @Override // java.util.List
    public final Object get(int i8) {
        zzb();
        return ((List) this.f837f).get(i8);
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        zzb();
        return ((List) this.f837f).indexOf(obj);
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        zzb();
        return ((List) this.f837f).lastIndexOf(obj);
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        zzb();
        return new o(this);
    }

    @Override // java.util.List
    public final Object remove(int i8) {
        zzb();
        Object remove = ((List) this.f837f).remove(i8);
        q.j(this.f911j);
        f();
        return remove;
    }

    @Override // java.util.List
    public final Object set(int i8, Object obj) {
        zzb();
        return ((List) this.f837f).set(i8, obj);
    }

    @Override // java.util.List
    public final List subList(int i8, int i9) {
        zzb();
        q qVar = this.f911j;
        Object obj = this.f836e;
        List subList = ((List) this.f837f).subList(i8, i9);
        n nVar = this.f838g;
        if (nVar == null) {
            nVar = this;
        }
        return qVar.n(obj, subList, nVar);
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i8) {
        zzb();
        return new o(this, i8);
    }
}
