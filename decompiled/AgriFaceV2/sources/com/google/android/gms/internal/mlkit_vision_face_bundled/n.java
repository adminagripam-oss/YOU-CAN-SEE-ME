package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.NoSuchElementException;
/* loaded from: classes.dex */
abstract class n extends i0 {

    /* renamed from: e  reason: collision with root package name */
    private final int f5444e;

    /* renamed from: f  reason: collision with root package name */
    private int f5445f;

    /* JADX INFO: Access modifiers changed from: protected */
    public n(int i8, int i9) {
        l.b(i9, i8, "index");
        this.f5444e = i8;
        this.f5445f = i9;
    }

    protected abstract Object b(int i8);

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        return this.f5445f < this.f5444e;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.f5445f > 0;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final Object next() {
        if (hasNext()) {
            int i8 = this.f5445f;
            this.f5445f = i8 + 1;
            return b(i8);
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.f5445f;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (hasPrevious()) {
            int i8 = this.f5445f - 1;
            this.f5445f = i8;
            return b(i8);
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.f5445f - 1;
    }
}
