package a4;

import java.util.NoSuchElementException;
/* loaded from: classes.dex */
abstract class e extends o1 {

    /* renamed from: e  reason: collision with root package name */
    private final int f488e;

    /* renamed from: f  reason: collision with root package name */
    private int f489f;

    /* JADX INFO: Access modifiers changed from: protected */
    public e(int i8, int i9) {
        c.b(i9, i8, "index");
        this.f488e = i8;
        this.f489f = i9;
    }

    protected abstract Object b(int i8);

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        return this.f489f < this.f488e;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.f489f > 0;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final Object next() {
        if (hasNext()) {
            int i8 = this.f489f;
            this.f489f = i8 + 1;
            return b(i8);
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.f489f;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (hasPrevious()) {
            int i8 = this.f489f - 1;
            this.f489f = i8;
            return b(i8);
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.f489f - 1;
    }
}
