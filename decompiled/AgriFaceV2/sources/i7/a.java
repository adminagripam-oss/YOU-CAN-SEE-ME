package i7;

import d7.k;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes.dex */
public final class a implements b {

    /* renamed from: a  reason: collision with root package name */
    private final AtomicReference f7554a;

    public a(b bVar) {
        k.e(bVar, "sequence");
        this.f7554a = new AtomicReference(bVar);
    }

    @Override // i7.b
    public Iterator iterator() {
        b bVar = (b) this.f7554a.getAndSet(null);
        if (bVar != null) {
            return bVar.iterator();
        }
        throw new IllegalStateException("This sequence can be consumed only once.");
    }
}
