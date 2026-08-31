package z3;

import java.util.NoSuchElementException;
/* loaded from: classes.dex */
abstract class h6 extends d {

    /* renamed from: e  reason: collision with root package name */
    private final int f13725e;

    /* renamed from: f  reason: collision with root package name */
    private int f13726f;

    /* JADX INFO: Access modifiers changed from: protected */
    public h6(int i8, int i9) {
        f4.b(i9, i8, "index");
        this.f13725e = i8;
        this.f13726f = i9;
    }

    protected abstract Object b(int i8);

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        return this.f13726f < this.f13725e;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.f13726f > 0;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final Object next() {
        if (hasNext()) {
            int i8 = this.f13726f;
            this.f13726f = i8 + 1;
            return b(i8);
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.f13726f;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (hasPrevious()) {
            int i8 = this.f13726f - 1;
            this.f13726f = i8;
            return b(i8);
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.f13726f - 1;
    }
}
