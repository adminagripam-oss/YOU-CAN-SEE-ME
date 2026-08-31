package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.p1;
import java.util.Arrays;
/* loaded from: classes.dex */
public final class k1 {

    /* renamed from: f  reason: collision with root package name */
    private static final k1 f6129f = new k1(0, new int[0], new Object[0], false);

    /* renamed from: a  reason: collision with root package name */
    private int f6130a;

    /* renamed from: b  reason: collision with root package name */
    private int[] f6131b;

    /* renamed from: c  reason: collision with root package name */
    private Object[] f6132c;

    /* renamed from: d  reason: collision with root package name */
    private int f6133d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f6134e;

    private k1() {
        this(0, new int[8], new Object[8], true);
    }

    private void b(int i8) {
        int[] iArr = this.f6131b;
        if (i8 > iArr.length) {
            int i9 = this.f6130a;
            int i10 = i9 + (i9 / 2);
            if (i10 >= i8) {
                i8 = i10;
            }
            if (i8 < 8) {
                i8 = 8;
            }
            this.f6131b = Arrays.copyOf(iArr, i8);
            this.f6132c = Arrays.copyOf(this.f6132c, i8);
        }
    }

    public static k1 c() {
        return f6129f;
    }

    private static int f(int[] iArr, int i8) {
        int i9 = 17;
        for (int i10 = 0; i10 < i8; i10++) {
            i9 = (i9 * 31) + iArr[i10];
        }
        return i9;
    }

    private static int g(Object[] objArr, int i8) {
        int i9 = 17;
        for (int i10 = 0; i10 < i8; i10++) {
            i9 = (i9 * 31) + objArr[i10].hashCode();
        }
        return i9;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static k1 j(k1 k1Var, k1 k1Var2) {
        int i8 = k1Var.f6130a + k1Var2.f6130a;
        int[] copyOf = Arrays.copyOf(k1Var.f6131b, i8);
        System.arraycopy(k1Var2.f6131b, 0, copyOf, k1Var.f6130a, k1Var2.f6130a);
        Object[] copyOf2 = Arrays.copyOf(k1Var.f6132c, i8);
        System.arraycopy(k1Var2.f6132c, 0, copyOf2, k1Var.f6130a, k1Var2.f6130a);
        return new k1(i8, copyOf, copyOf2, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static k1 k() {
        return new k1();
    }

    private static boolean l(Object[] objArr, Object[] objArr2, int i8) {
        for (int i9 = 0; i9 < i8; i9++) {
            if (!objArr[i9].equals(objArr2[i9])) {
                return false;
            }
        }
        return true;
    }

    private static boolean o(int[] iArr, int[] iArr2, int i8) {
        for (int i9 = 0; i9 < i8; i9++) {
            if (iArr[i9] != iArr2[i9]) {
                return false;
            }
        }
        return true;
    }

    private static void q(int i8, Object obj, p1 p1Var) {
        int a8 = o1.a(i8);
        int b8 = o1.b(i8);
        if (b8 == 0) {
            p1Var.d(a8, ((Long) obj).longValue());
        } else if (b8 == 1) {
            p1Var.z(a8, ((Long) obj).longValue());
        } else if (b8 == 2) {
            p1Var.v(a8, (h) obj);
        } else if (b8 != 3) {
            if (b8 != 5) {
                throw new RuntimeException(a0.e());
            }
            p1Var.n(a8, ((Integer) obj).intValue());
        } else if (p1Var.A() == p1.a.ASCENDING) {
            p1Var.j(a8);
            ((k1) obj).r(p1Var);
            p1Var.E(a8);
        } else {
            p1Var.E(a8);
            ((k1) obj).r(p1Var);
            p1Var.j(a8);
        }
    }

    void a() {
        if (!this.f6134e) {
            throw new UnsupportedOperationException();
        }
    }

    public int d() {
        int M;
        int i8 = this.f6133d;
        if (i8 != -1) {
            return i8;
        }
        int i9 = 0;
        for (int i10 = 0; i10 < this.f6130a; i10++) {
            int i11 = this.f6131b[i10];
            int a8 = o1.a(i11);
            int b8 = o1.b(i11);
            if (b8 == 0) {
                M = k.M(a8, ((Long) this.f6132c[i10]).longValue());
            } else if (b8 == 1) {
                M = k.n(a8, ((Long) this.f6132c[i10]).longValue());
            } else if (b8 == 2) {
                M = k.f(a8, (h) this.f6132c[i10]);
            } else if (b8 == 3) {
                M = (k.J(a8) * 2) + ((k1) this.f6132c[i10]).d();
            } else if (b8 != 5) {
                throw new IllegalStateException(a0.e());
            } else {
                M = k.l(a8, ((Integer) this.f6132c[i10]).intValue());
            }
            i9 += M;
        }
        this.f6133d = i9;
        return i9;
    }

    public int e() {
        int i8 = this.f6133d;
        if (i8 != -1) {
            return i8;
        }
        int i9 = 0;
        for (int i10 = 0; i10 < this.f6130a; i10++) {
            i9 += k.y(o1.a(this.f6131b[i10]), (h) this.f6132c[i10]);
        }
        this.f6133d = i9;
        return i9;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof k1)) {
            k1 k1Var = (k1) obj;
            int i8 = this.f6130a;
            return i8 == k1Var.f6130a && o(this.f6131b, k1Var.f6131b, i8) && l(this.f6132c, k1Var.f6132c, this.f6130a);
        }
        return false;
    }

    public void h() {
        if (this.f6134e) {
            this.f6134e = false;
        }
    }

    public int hashCode() {
        int i8 = this.f6130a;
        return ((((527 + i8) * 31) + f(this.f6131b, i8)) * 31) + g(this.f6132c, this.f6130a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public k1 i(k1 k1Var) {
        if (k1Var.equals(c())) {
            return this;
        }
        a();
        int i8 = this.f6130a + k1Var.f6130a;
        b(i8);
        System.arraycopy(k1Var.f6131b, 0, this.f6131b, this.f6130a, k1Var.f6130a);
        System.arraycopy(k1Var.f6132c, 0, this.f6132c, this.f6130a, k1Var.f6130a);
        this.f6130a = i8;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void m(StringBuilder sb, int i8) {
        for (int i9 = 0; i9 < this.f6130a; i9++) {
            r0.d(sb, i8, String.valueOf(o1.a(this.f6131b[i9])), this.f6132c[i9]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n(int i8, Object obj) {
        a();
        b(this.f6130a + 1);
        int[] iArr = this.f6131b;
        int i9 = this.f6130a;
        iArr[i9] = i8;
        this.f6132c[i9] = obj;
        this.f6130a = i9 + 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void p(p1 p1Var) {
        if (p1Var.A() == p1.a.DESCENDING) {
            for (int i8 = this.f6130a - 1; i8 >= 0; i8--) {
                p1Var.h(o1.a(this.f6131b[i8]), this.f6132c[i8]);
            }
            return;
        }
        for (int i9 = 0; i9 < this.f6130a; i9++) {
            p1Var.h(o1.a(this.f6131b[i9]), this.f6132c[i9]);
        }
    }

    public void r(p1 p1Var) {
        if (this.f6130a == 0) {
            return;
        }
        if (p1Var.A() == p1.a.ASCENDING) {
            for (int i8 = 0; i8 < this.f6130a; i8++) {
                q(this.f6131b[i8], this.f6132c[i8], p1Var);
            }
            return;
        }
        for (int i9 = this.f6130a - 1; i9 >= 0; i9--) {
            q(this.f6131b[i9], this.f6132c[i9], p1Var);
        }
    }

    private k1(int i8, int[] iArr, Object[] objArr, boolean z7) {
        this.f6133d = -1;
        this.f6130a = i8;
        this.f6131b = iArr;
        this.f6132c = objArr;
        this.f6134e = z7;
    }
}
