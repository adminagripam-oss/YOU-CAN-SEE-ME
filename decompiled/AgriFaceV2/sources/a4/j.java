package a4;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes.dex */
final class j implements Iterator {

    /* renamed from: e  reason: collision with root package name */
    Map.Entry f689e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ Iterator f690f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ k f691g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(k kVar, Iterator it) {
        this.f691g = kVar;
        this.f690f = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f690f.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        Map.Entry entry = (Map.Entry) this.f690f.next();
        this.f689e = entry;
        return entry.getKey();
    }

    @Override // java.util.Iterator
    public final void remove() {
        c.d(this.f689e != null, "no calls to next() since the last call to remove()");
        Collection collection = (Collection) this.f689e.getValue();
        this.f690f.remove();
        q.l(this.f691g.f732f, collection.size());
        collection.clear();
        this.f689e = null;
    }
}
