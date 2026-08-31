package com.google.crypto.tink.shaded.protobuf;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.RandomAccess;
/* loaded from: classes.dex */
final class a1 extends c implements RandomAccess {

    /* renamed from: h  reason: collision with root package name */
    private static final Object[] f6034h;

    /* renamed from: i  reason: collision with root package name */
    private static final a1 f6035i;

    /* renamed from: f  reason: collision with root package name */
    private Object[] f6036f;

    /* renamed from: g  reason: collision with root package name */
    private int f6037g;

    static {
        Object[] objArr = new Object[0];
        f6034h = objArr;
        f6035i = new a1(objArr, 0, false);
    }

    private a1(Object[] objArr, int i8, boolean z7) {
        super(z7);
        this.f6036f = objArr;
        this.f6037g = i8;
    }

    private static Object[] f(int i8) {
        return new Object[i8];
    }

    public static a1 j() {
        return f6035i;
    }

    private void k(int i8) {
        if (i8 < 0 || i8 >= this.f6037g) {
            throw new IndexOutOfBoundsException(m(i8));
        }
    }

    private static int l(int i8) {
        return Math.max(((i8 * 3) / 2) + 1, 10);
    }

    private String m(int i8) {
        return "Index:" + i8 + ", Size:" + this.f6037g;
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i8, Object obj) {
        int i9;
        b();
        if (i8 < 0 || i8 > (i9 = this.f6037g)) {
            throw new IndexOutOfBoundsException(m(i8));
        }
        Object[] objArr = this.f6036f;
        if (i9 < objArr.length) {
            System.arraycopy(objArr, i8, objArr, i8 + 1, i9 - i8);
        } else {
            Object[] f8 = f(l(objArr.length));
            System.arraycopy(this.f6036f, 0, f8, 0, i8);
            System.arraycopy(this.f6036f, i8, f8, i8 + 1, this.f6037g - i8);
            this.f6036f = f8;
        }
        this.f6036f[i8] = obj;
        this.f6037g++;
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public Object get(int i8) {
        k(i8);
        return this.f6036f[i8];
    }

    @Override // com.google.crypto.tink.shaded.protobuf.z.d
    /* renamed from: n */
    public a1 e(int i8) {
        if (i8 >= this.f6037g) {
            return new a1(i8 == 0 ? f6034h : Arrays.copyOf(this.f6036f, i8), this.f6037g, true);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c, java.util.AbstractList, java.util.List
    public Object remove(int i8) {
        int i9;
        b();
        k(i8);
        Object[] objArr = this.f6036f;
        Object obj = objArr[i8];
        if (i8 < this.f6037g - 1) {
            System.arraycopy(objArr, i8 + 1, objArr, i8, (i9 - i8) - 1);
        }
        this.f6037g--;
        ((AbstractList) this).modCount++;
        return obj;
    }

    @Override // java.util.AbstractList, java.util.List
    public Object set(int i8, Object obj) {
        b();
        k(i8);
        Object[] objArr = this.f6036f;
        Object obj2 = objArr[i8];
        objArr[i8] = obj;
        ((AbstractList) this).modCount++;
        return obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.f6037g;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(Object obj) {
        b();
        int i8 = this.f6037g;
        Object[] objArr = this.f6036f;
        if (i8 == objArr.length) {
            this.f6036f = Arrays.copyOf(this.f6036f, l(objArr.length));
        }
        Object[] objArr2 = this.f6036f;
        int i9 = this.f6037g;
        this.f6037g = i9 + 1;
        objArr2[i9] = obj;
        ((AbstractList) this).modCount++;
        return true;
    }
}
