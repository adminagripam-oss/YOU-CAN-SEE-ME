package y3;

import java.util.NoSuchElementException;
/* loaded from: classes.dex */
abstract class v0 extends t {

    /* renamed from: e  reason: collision with root package name */
    private final int f13269e;

    /* renamed from: f  reason: collision with root package name */
    private int f13270f;

    /* JADX INFO: Access modifiers changed from: protected */
    public v0(int i8, int i9) {
        t0.b(i9, i8, "index");
        this.f13269e = i8;
        this.f13270f = i9;
    }

    protected abstract Object b(int i8);

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        return this.f13270f < this.f13269e;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.f13270f > 0;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final Object next() {
        if (hasNext()) {
            int i8 = this.f13270f;
            this.f13270f = i8 + 1;
            return b(i8);
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.f13270f;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (hasPrevious()) {
            int i8 = this.f13270f - 1;
            this.f13270f = i8;
            return b(i8);
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.f13270f - 1;
    }
}
