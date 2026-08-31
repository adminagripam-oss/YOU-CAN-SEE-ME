package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.RandomAccess;
/* loaded from: classes.dex */
final class ul extends zi implements RandomAccess {

    /* renamed from: h  reason: collision with root package name */
    private static final ul f5675h = new ul(new Object[0], 0, false);

    /* renamed from: f  reason: collision with root package name */
    private Object[] f5676f;

    /* renamed from: g  reason: collision with root package name */
    private int f5677g;

    private ul(Object[] objArr, int i8, boolean z7) {
        super(z7);
        this.f5676f = objArr;
        this.f5677g = i8;
    }

    public static ul f() {
        return f5675h;
    }

    private final String j(int i8) {
        int i9 = this.f5677g;
        return "Index:" + i8 + ", Size:" + i9;
    }

    private final void k(int i8) {
        if (i8 < 0 || i8 >= this.f5677g) {
            throw new IndexOutOfBoundsException(j(i8));
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i8, Object obj) {
        int i9;
        b();
        if (i8 < 0 || i8 > (i9 = this.f5677g)) {
            throw new IndexOutOfBoundsException(j(i8));
        }
        int i10 = i8 + 1;
        Object[] objArr = this.f5676f;
        if (i9 < objArr.length) {
            System.arraycopy(objArr, i8, objArr, i10, i9 - i8);
        } else {
            Object[] objArr2 = new Object[((i9 * 3) / 2) + 1];
            System.arraycopy(objArr, 0, objArr2, 0, i8);
            System.arraycopy(this.f5676f, i8, objArr2, i10, this.f5677g - i8);
            this.f5676f = objArr2;
        }
        this.f5676f[i8] = obj;
        this.f5677g++;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.tk
    public final /* bridge */ /* synthetic */ tk c(int i8) {
        if (i8 >= this.f5677g) {
            return new ul(Arrays.copyOf(this.f5676f, i8), this.f5677g, true);
        }
        throw new IllegalArgumentException();
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i8) {
        k(i8);
        return this.f5676f[i8];
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zi, java.util.AbstractList, java.util.List
    public final Object remove(int i8) {
        int i9;
        b();
        k(i8);
        Object[] objArr = this.f5676f;
        Object obj = objArr[i8];
        if (i8 < this.f5677g - 1) {
            System.arraycopy(objArr, i8 + 1, objArr, i8, (i9 - i8) - 1);
        }
        this.f5677g--;
        ((AbstractList) this).modCount++;
        return obj;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i8, Object obj) {
        b();
        k(i8);
        Object[] objArr = this.f5676f;
        Object obj2 = objArr[i8];
        objArr[i8] = obj;
        ((AbstractList) this).modCount++;
        return obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f5677g;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        b();
        int i8 = this.f5677g;
        Object[] objArr = this.f5676f;
        if (i8 == objArr.length) {
            this.f5676f = Arrays.copyOf(objArr, ((i8 * 3) / 2) + 1);
        }
        Object[] objArr2 = this.f5676f;
        int i9 = this.f5677g;
        this.f5677g = i9 + 1;
        objArr2[i9] = obj;
        ((AbstractList) this).modCount++;
        return true;
    }
}
