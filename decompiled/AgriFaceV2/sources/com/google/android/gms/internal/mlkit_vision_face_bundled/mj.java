package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Locale;
/* loaded from: classes.dex */
public abstract class mj implements Iterable, Serializable {

    /* renamed from: f  reason: collision with root package name */
    public static final mj f5439f = new lj(uk.f5674b);

    /* renamed from: e  reason: collision with root package name */
    private int f5440e = 0;

    static {
        int i8 = aj.f5130a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int n(int i8, int i9, int i10) {
        int i11 = i9 - i8;
        if ((i8 | i9 | i11 | (i10 - i9)) < 0) {
            if (i8 < 0) {
                throw new IndexOutOfBoundsException("Beginning index: " + i8 + " < 0");
            } else if (i9 < i8) {
                throw new IndexOutOfBoundsException("Beginning index larger than ending index: " + i8 + ", " + i9);
            } else {
                throw new IndexOutOfBoundsException("End index: " + i9 + " >= " + i10);
            }
        }
        return i11;
    }

    public static mj p(byte[] bArr, int i8, int i9) {
        n(i8, i8 + i9, bArr.length);
        byte[] bArr2 = new byte[i9];
        System.arraycopy(bArr, i8, bArr2, 0, i9);
        return new lj(bArr2);
    }

    public abstract byte b(int i8);

    public abstract boolean equals(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte f(int i8);

    public final int hashCode() {
        int i8 = this.f5440e;
        if (i8 == 0) {
            int j8 = j();
            i8 = k(j8, 0, j8);
            if (i8 == 0) {
                i8 = 1;
            }
            this.f5440e = i8;
        }
        return i8;
    }

    @Override // java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return new fj(this);
    }

    public abstract int j();

    protected abstract int k(int i8, int i9, int i10);

    public abstract mj l(int i8, int i9);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void m(ej ejVar);

    /* JADX INFO: Access modifiers changed from: protected */
    public final int o() {
        return this.f5440e;
    }

    public final String toString() {
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.toHexString(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(j());
        objArr[2] = j() <= 50 ? hm.a(this) : hm.a(l(0, 47)).concat("...");
        return String.format(locale, "<ByteString@%s size=%d contents=\"%s\">", objArr);
    }
}
