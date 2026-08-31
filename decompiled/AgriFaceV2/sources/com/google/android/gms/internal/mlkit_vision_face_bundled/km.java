package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.Arrays;
/* loaded from: classes.dex */
public final class km {

    /* renamed from: f  reason: collision with root package name */
    private static final km f5401f = new km(0, new int[0], new Object[0], false);

    /* renamed from: a  reason: collision with root package name */
    private int f5402a;

    /* renamed from: b  reason: collision with root package name */
    private int[] f5403b;

    /* renamed from: c  reason: collision with root package name */
    private Object[] f5404c;

    /* renamed from: d  reason: collision with root package name */
    private int f5405d = -1;

    /* renamed from: e  reason: collision with root package name */
    private boolean f5406e;

    private km(int i8, int[] iArr, Object[] objArr, boolean z7) {
        this.f5402a = i8;
        this.f5403b = iArr;
        this.f5404c = objArr;
        this.f5406e = z7;
    }

    public static km c() {
        return f5401f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static km e(km kmVar, km kmVar2) {
        int i8 = kmVar.f5402a + kmVar2.f5402a;
        int[] copyOf = Arrays.copyOf(kmVar.f5403b, i8);
        System.arraycopy(kmVar2.f5403b, 0, copyOf, kmVar.f5402a, kmVar2.f5402a);
        Object[] copyOf2 = Arrays.copyOf(kmVar.f5404c, i8);
        System.arraycopy(kmVar2.f5404c, 0, copyOf2, kmVar.f5402a, kmVar2.f5402a);
        return new km(i8, copyOf, copyOf2, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static km f() {
        return new km(0, new int[8], new Object[8], true);
    }

    private final void m(int i8) {
        int[] iArr = this.f5403b;
        if (i8 > iArr.length) {
            int i9 = this.f5402a;
            int i10 = i9 + (i9 / 2);
            if (i10 >= i8) {
                i8 = i10;
            }
            if (i8 < 8) {
                i8 = 8;
            }
            this.f5403b = Arrays.copyOf(iArr, i8);
            this.f5404c = Arrays.copyOf(this.f5404c, i8);
        }
    }

    public final int a() {
        int B;
        int a8;
        int i8;
        int i9 = this.f5405d;
        if (i9 == -1) {
            int i10 = 0;
            for (int i11 = 0; i11 < this.f5402a; i11++) {
                int i12 = this.f5403b[i11];
                int i13 = i12 >>> 3;
                int i14 = i12 & 7;
                if (i14 != 0) {
                    if (i14 == 1) {
                        ((Long) this.f5404c[i11]).longValue();
                        i8 = vj.B(i13 << 3) + 8;
                    } else if (i14 == 2) {
                        int B2 = vj.B(i13 << 3);
                        int j8 = ((mj) this.f5404c[i11]).j();
                        i8 = B2 + vj.B(j8) + j8;
                    } else if (i14 == 3) {
                        int B3 = vj.B(i13 << 3);
                        B = B3 + B3;
                        a8 = ((km) this.f5404c[i11]).a();
                    } else if (i14 != 5) {
                        throw new IllegalStateException(new vk("Protocol message tag had invalid wire type."));
                    } else {
                        ((Integer) this.f5404c[i11]).intValue();
                        i8 = vj.B(i13 << 3) + 4;
                    }
                    i10 += i8;
                } else {
                    int i15 = i13 << 3;
                    long longValue = ((Long) this.f5404c[i11]).longValue();
                    B = vj.B(i15);
                    a8 = vj.a(longValue);
                }
                i8 = B + a8;
                i10 += i8;
            }
            this.f5405d = i10;
            return i10;
        }
        return i9;
    }

    public final int b() {
        int i8 = this.f5405d;
        if (i8 == -1) {
            int i9 = 0;
            for (int i10 = 0; i10 < this.f5402a; i10++) {
                int B = vj.B(8);
                int B2 = vj.B(16) + vj.B(this.f5403b[i10] >>> 3);
                int B3 = vj.B(24);
                int j8 = ((mj) this.f5404c[i10]).j();
                i9 += B + B + B2 + B3 + vj.B(j8) + j8;
            }
            this.f5405d = i9;
            return i9;
        }
        return i8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final km d(km kmVar) {
        if (kmVar.equals(f5401f)) {
            return this;
        }
        g();
        int i8 = this.f5402a + kmVar.f5402a;
        m(i8);
        System.arraycopy(kmVar.f5403b, 0, this.f5403b, this.f5402a, kmVar.f5402a);
        System.arraycopy(kmVar.f5404c, 0, this.f5404c, this.f5402a, kmVar.f5402a);
        this.f5402a = i8;
        return this;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof km)) {
            km kmVar = (km) obj;
            int i8 = this.f5402a;
            if (i8 == kmVar.f5402a) {
                int[] iArr = this.f5403b;
                int[] iArr2 = kmVar.f5403b;
                int i9 = 0;
                while (true) {
                    if (i9 >= i8) {
                        Object[] objArr = this.f5404c;
                        Object[] objArr2 = kmVar.f5404c;
                        int i10 = this.f5402a;
                        for (int i11 = 0; i11 < i10; i11++) {
                            if (objArr[i11].equals(objArr2[i11])) {
                            }
                        }
                        return true;
                    } else if (iArr[i9] != iArr2[i9]) {
                        break;
                    } else {
                        i9++;
                    }
                }
            }
            return false;
        }
        return false;
    }

    final void g() {
        if (!this.f5406e) {
            throw new UnsupportedOperationException();
        }
    }

    public final void h() {
        if (this.f5406e) {
            this.f5406e = false;
        }
    }

    public final int hashCode() {
        int i8 = this.f5402a;
        int i9 = i8 + 527;
        int[] iArr = this.f5403b;
        int i10 = 17;
        int i11 = 17;
        for (int i12 = 0; i12 < i8; i12++) {
            i11 = (i11 * 31) + iArr[i12];
        }
        int i13 = ((i9 * 31) + i11) * 31;
        Object[] objArr = this.f5404c;
        int i14 = this.f5402a;
        for (int i15 = 0; i15 < i14; i15++) {
            i10 = (i10 * 31) + objArr[i15].hashCode();
        }
        return i13 + i10;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void i(StringBuilder sb, int i8) {
        for (int i9 = 0; i9 < this.f5402a; i9++) {
            nl.b(sb, i8, String.valueOf(this.f5403b[i9] >>> 3), this.f5404c[i9]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void j(int i8, Object obj) {
        g();
        m(this.f5402a + 1);
        int[] iArr = this.f5403b;
        int i9 = this.f5402a;
        iArr[i9] = i8;
        this.f5404c[i9] = obj;
        this.f5402a = i9 + 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void k(xm xmVar) {
        for (int i8 = 0; i8 < this.f5402a; i8++) {
            xmVar.m(this.f5403b[i8] >>> 3, this.f5404c[i8]);
        }
    }

    public final void l(xm xmVar) {
        if (this.f5402a != 0) {
            for (int i8 = 0; i8 < this.f5402a; i8++) {
                int i9 = this.f5403b[i8];
                Object obj = this.f5404c[i8];
                int i10 = i9 & 7;
                int i11 = i9 >>> 3;
                if (i10 == 0) {
                    xmVar.q(i11, ((Long) obj).longValue());
                } else if (i10 == 1) {
                    xmVar.B(i11, ((Long) obj).longValue());
                } else if (i10 == 2) {
                    xmVar.H(i11, (mj) obj);
                } else if (i10 == 3) {
                    xmVar.J(i11);
                    ((km) obj).l(xmVar);
                    xmVar.I(i11);
                } else if (i10 != 5) {
                    throw new RuntimeException(new vk("Protocol message tag had invalid wire type."));
                } else {
                    xmVar.l(i11, ((Integer) obj).intValue());
                }
            }
        }
    }
}
