package a4;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes.dex */
final class h implements Iterator {

    /* renamed from: e  reason: collision with root package name */
    final Iterator f611e;

    /* renamed from: f  reason: collision with root package name */
    Collection f612f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ i f613g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(i iVar) {
        this.f613g = iVar;
        this.f611e = iVar.f658g.entrySet().iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f611e.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        Map.Entry entry = (Map.Entry) this.f611e.next();
        this.f612f = (Collection) entry.getValue();
        i iVar = this.f613g;
        Object key = entry.getKey();
        return new l0(key, iVar.f659h.h(key, (Collection) entry.getValue()));
    }

    @Override // java.util.Iterator
    public final void remove() {
        c.d(this.f612f != null, "no calls to next() since the last call to remove()");
        this.f611e.remove();
        q.l(this.f613g.f659h, this.f612f.size());
        this.f612f.clear();
        this.f612f = null;
    }
}
