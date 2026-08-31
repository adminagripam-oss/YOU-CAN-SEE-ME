package a5;

import java.util.Arrays;
import z4.k;
/* loaded from: classes.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f1352a;

    private a(byte[] bArr, int i8, int i9) {
        byte[] bArr2 = new byte[i9];
        this.f1352a = bArr2;
        System.arraycopy(bArr, i8, bArr2, 0, i9);
    }

    public static a a(byte[] bArr) {
        if (bArr != null) {
            return b(bArr, 0, bArr.length);
        }
        throw new NullPointerException("data must be non-null");
    }

    public static a b(byte[] bArr, int i8, int i9) {
        if (bArr != null) {
            if (i8 + i9 > bArr.length) {
                i9 = bArr.length - i8;
            }
            return new a(bArr, i8, i9);
        }
        throw new NullPointerException("data must be non-null");
    }

    public int c() {
        return this.f1352a.length;
    }

    public byte[] d() {
        byte[] bArr = this.f1352a;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof a) {
            return Arrays.equals(((a) obj).f1352a, this.f1352a);
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(this.f1352a);
    }

    public String toString() {
        return "Bytes(" + k.b(this.f1352a) + ")";
    }
}
