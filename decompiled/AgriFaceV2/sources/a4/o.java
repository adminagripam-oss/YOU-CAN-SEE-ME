package a4;

import java.util.List;
import java.util.ListIterator;
/* loaded from: classes.dex */
final class o extends m implements ListIterator {

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ p f877h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(p pVar) {
        super(pVar);
        this.f877h = pVar;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        boolean isEmpty = this.f877h.isEmpty();
        b();
        ((ListIterator) this.f795e).add(obj);
        q.i(this.f877h.f911j);
        if (isEmpty) {
            this.f877h.b();
        }
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        b();
        return ((ListIterator) this.f795e).hasPrevious();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        b();
        return ((ListIterator) this.f795e).nextIndex();
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        b();
        return ((ListIterator) this.f795e).previous();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        b();
        return ((ListIterator) this.f795e).previousIndex();
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        b();
        ((ListIterator) this.f795e).set(obj);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(p pVar, int i8) {
        super(pVar, ((List) pVar.f837f).listIterator(i8));
        this.f877h = pVar;
    }
}
