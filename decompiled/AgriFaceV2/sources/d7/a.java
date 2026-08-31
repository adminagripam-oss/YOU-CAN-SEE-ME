package d7;

import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: classes.dex */
final class a implements Iterator {

    /* renamed from: e  reason: collision with root package name */
    private final Object[] f6459e;

    /* renamed from: f  reason: collision with root package name */
    private int f6460f;

    public a(Object[] objArr) {
        k.e(objArr, "array");
        this.f6459e = objArr;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f6460f < this.f6459e.length;
    }

    @Override // java.util.Iterator
    public Object next() {
        try {
            Object[] objArr = this.f6459e;
            int i8 = this.f6460f;
            this.f6460f = i8 + 1;
            return objArr[i8];
        } catch (ArrayIndexOutOfBoundsException e8) {
            this.f6460f--;
            throw new NoSuchElementException(e8.getMessage());
        }
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
