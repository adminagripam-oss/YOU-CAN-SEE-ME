package x3;

import java.util.NoSuchElementException;
/* loaded from: classes.dex */
abstract class r0 extends z0 {

    /* renamed from: e  reason: collision with root package name */
    private final int f12694e;

    /* renamed from: f  reason: collision with root package name */
    private int f12695f;

    /* JADX INFO: Access modifiers changed from: protected */
    public r0(int i8, int i9) {
        p0.b(i9, i8, "index");
        this.f12694e = i8;
        this.f12695f = i9;
    }

    protected abstract Object b(int i8);

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        return this.f12695f < this.f12694e;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.f12695f > 0;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final Object next() {
        if (hasNext()) {
            int i8 = this.f12695f;
            this.f12695f = i8 + 1;
            return b(i8);
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.f12695f;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (hasPrevious()) {
            int i8 = this.f12695f - 1;
            this.f12695f = i8;
            return b(i8);
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.f12695f - 1;
    }
}
