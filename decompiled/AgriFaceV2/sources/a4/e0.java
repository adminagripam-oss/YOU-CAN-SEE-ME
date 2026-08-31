package a4;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes.dex */
final class e0 extends AbstractCollection {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ f0 f490e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e0(f0 f0Var) {
        this.f490e = f0Var;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.f490e.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        f0 f0Var = this.f490e;
        Map n8 = f0Var.n();
        return n8 != null ? n8.values().iterator() : new y(f0Var);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        return this.f490e.size();
    }
}
