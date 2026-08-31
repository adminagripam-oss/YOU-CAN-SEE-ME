package a4;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
final class g extends w0 {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ i f575e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(i iVar) {
        this.f575e = iVar;
    }

    @Override // a4.w0
    final Map b() {
        return this.f575e;
    }

    @Override // a4.w0, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        Set entrySet = this.f575e.f658g.entrySet();
        entrySet.getClass();
        try {
            return entrySet.contains(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new h(this.f575e);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        if (contains(obj)) {
            Map.Entry entry = (Map.Entry) obj;
            entry.getClass();
            q.p(this.f575e.f659h, entry.getKey());
            return true;
        }
        return false;
    }
}
