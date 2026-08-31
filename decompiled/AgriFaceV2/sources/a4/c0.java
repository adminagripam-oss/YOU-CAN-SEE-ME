package a4;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes.dex */
final class c0 extends AbstractSet {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ f0 f299e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c0(f0 f0Var) {
        this.f299e = f0Var;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.f299e.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.f299e.containsKey(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        f0 f0Var = this.f299e;
        Map n8 = f0Var.n();
        return n8 != null ? n8.keySet().iterator() : new w(f0Var);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        Object z7;
        Object obj2;
        Map n8 = this.f299e.n();
        if (n8 != null) {
            return n8.keySet().remove(obj);
        }
        z7 = this.f299e.z(obj);
        obj2 = f0.f537n;
        return z7 != obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f299e.size();
    }
}
