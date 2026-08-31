package p7;

import java.util.concurrent.atomic.AtomicReferenceArray;
/* loaded from: classes.dex */
public final class c0 {
    private volatile AtomicReferenceArray<Object> array;

    public c0(int i8) {
        this.array = new AtomicReferenceArray<>(i8);
    }

    public final int a() {
        return this.array.length();
    }

    public final Object b(int i8) {
        AtomicReferenceArray<Object> atomicReferenceArray = this.array;
        if (i8 < atomicReferenceArray.length()) {
            return atomicReferenceArray.get(i8);
        }
        return null;
    }

    public final void c(int i8, Object obj) {
        int a8;
        AtomicReferenceArray<Object> atomicReferenceArray = this.array;
        int length = atomicReferenceArray.length();
        if (i8 < length) {
            atomicReferenceArray.set(i8, obj);
            return;
        }
        a8 = g7.f.a(i8 + 1, length * 2);
        AtomicReferenceArray<Object> atomicReferenceArray2 = new AtomicReferenceArray<>(a8);
        for (int i9 = 0; i9 < length; i9++) {
            atomicReferenceArray2.set(i9, atomicReferenceArray.get(i9));
        }
        atomicReferenceArray2.set(i8, obj);
        this.array = atomicReferenceArray2;
    }
}
