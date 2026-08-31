package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.n1;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes.dex */
public abstract class k extends g {

    /* renamed from: c  reason: collision with root package name */
    private static final Logger f6121c = Logger.getLogger(k.class.getName());

    /* renamed from: d  reason: collision with root package name */
    private static final boolean f6122d = m1.E();

    /* renamed from: a  reason: collision with root package name */
    l f6123a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f6124b;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b extends k {

        /* renamed from: e  reason: collision with root package name */
        private final byte[] f6125e;

        /* renamed from: f  reason: collision with root package name */
        private final int f6126f;

        /* renamed from: g  reason: collision with root package name */
        private final int f6127g;

        /* renamed from: h  reason: collision with root package name */
        private int f6128h;

        b(byte[] bArr, int i8, int i9) {
            super();
            if (bArr == null) {
                throw new NullPointerException("buffer");
            }
            int i10 = i8 + i9;
            if ((i8 | i9 | (bArr.length - i10)) < 0) {
                throw new IllegalArgumentException(String.format(Locale.US, "Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(bArr.length), Integer.valueOf(i8), Integer.valueOf(i9)));
            }
            this.f6125e = bArr;
            this.f6126f = i8;
            this.f6128h = i8;
            this.f6127g = i10;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.k
        public final void A0(int i8, int i9) {
            C0(o1.c(i8, i9));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.k
        public final void B0(int i8, int i9) {
            A0(i8, 0);
            C0(i9);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.k
        public final void C0(int i8) {
            int i9;
            int i10 = this.f6128h;
            while ((i8 & (-128)) != 0) {
                try {
                    i9 = i10 + 1;
                } catch (IndexOutOfBoundsException e8) {
                    e = e8;
                    throw new c(i10, this.f6127g, 1, (Throwable) e);
                }
                try {
                    this.f6125e[i10] = (byte) (i8 | 128);
                    i8 >>>= 7;
                    i10 = i9;
                } catch (IndexOutOfBoundsException e9) {
                    e = e9;
                    i10 = i9;
                    throw new c(i10, this.f6127g, 1, (Throwable) e);
                }
            }
            i9 = i10 + 1;
            this.f6125e[i10] = (byte) i8;
            this.f6128h = i9;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.k
        public final void D0(int i8, long j8) {
            A0(i8, 0);
            E0(j8);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.k
        public final void E0(long j8) {
            int i8;
            int i9;
            int i10 = this.f6128h;
            if (!k.f6122d || U() < 10) {
                while ((j8 & (-128)) != 0) {
                    try {
                        i9 = i10 + 1;
                    } catch (IndexOutOfBoundsException e8) {
                        e = e8;
                    }
                    try {
                        this.f6125e[i10] = (byte) (((int) j8) | 128);
                        j8 >>>= 7;
                        i10 = i9;
                    } catch (IndexOutOfBoundsException e9) {
                        e = e9;
                        i10 = i9;
                        throw new c(i10, this.f6127g, 1, (Throwable) e);
                    }
                }
                i8 = i10 + 1;
                try {
                    this.f6125e[i10] = (byte) j8;
                } catch (IndexOutOfBoundsException e10) {
                    e = e10;
                    i10 = i8;
                    throw new c(i10, this.f6127g, 1, (Throwable) e);
                }
            } else {
                while ((j8 & (-128)) != 0) {
                    m1.K(this.f6125e, i10, (byte) (((int) j8) | 128));
                    j8 >>>= 7;
                    i10++;
                }
                i8 = i10 + 1;
                m1.K(this.f6125e, i10, (byte) j8);
            }
            this.f6128h = i8;
        }

        public final void F0(byte[] bArr, int i8, int i9) {
            try {
                System.arraycopy(bArr, i8, this.f6125e, this.f6128h, i9);
                this.f6128h += i9;
            } catch (IndexOutOfBoundsException e8) {
                throw new c(this.f6128h, this.f6127g, i9, (Throwable) e8);
            }
        }

        public final void G0(h hVar) {
            C0(hVar.size());
            hVar.B(this);
        }

        public final void H0(int i8, p0 p0Var) {
            A0(i8, 2);
            I0(p0Var);
        }

        public final void I0(p0 p0Var) {
            C0(p0Var.a());
            p0Var.d(this);
        }

        public final void J0(String str) {
            int f8;
            int i8 = this.f6128h;
            try {
                int L = k.L(str.length() * 3);
                int L2 = k.L(str.length());
                if (L2 == L) {
                    int i9 = i8 + L2;
                    this.f6128h = i9;
                    f8 = n1.f(str, this.f6125e, i9, U());
                    this.f6128h = i8;
                    C0((f8 - i8) - L2);
                } else {
                    C0(n1.g(str));
                    f8 = n1.f(str, this.f6125e, this.f6128h, U());
                }
                this.f6128h = f8;
            } catch (n1.d e8) {
                this.f6128h = i8;
                Q(str, e8);
            } catch (IndexOutOfBoundsException e9) {
                throw new c(e9);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.k
        public final int U() {
            return this.f6127g - this.f6128h;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.k
        public final void V(byte b8) {
            int i8 = this.f6128h;
            try {
                int i9 = i8 + 1;
                try {
                    this.f6125e[i8] = b8;
                    this.f6128h = i9;
                } catch (IndexOutOfBoundsException e8) {
                    e = e8;
                    i8 = i9;
                    throw new c(i8, this.f6127g, 1, (Throwable) e);
                }
            } catch (IndexOutOfBoundsException e9) {
                e = e9;
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.k
        public final void W(int i8, boolean z7) {
            A0(i8, 0);
            V(z7 ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.k
        public final void Y(int i8, h hVar) {
            A0(i8, 2);
            G0(hVar);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.k, com.google.crypto.tink.shaded.protobuf.g
        public final void a(byte[] bArr, int i8, int i9) {
            F0(bArr, i8, i9);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.k
        public final void d0(int i8, int i9) {
            A0(i8, 5);
            e0(i9);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.k
        public final void e0(int i8) {
            int i9 = this.f6128h;
            try {
                byte[] bArr = this.f6125e;
                bArr[i9] = (byte) i8;
                bArr[i9 + 1] = (byte) (i8 >> 8);
                bArr[i9 + 2] = (byte) (i8 >> 16);
                bArr[i9 + 3] = (byte) (i8 >> 24);
                this.f6128h = i9 + 4;
            } catch (IndexOutOfBoundsException e8) {
                throw new c(i9, this.f6127g, 4, (Throwable) e8);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.k
        public final void f0(int i8, long j8) {
            A0(i8, 1);
            g0(j8);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.k
        public final void g0(long j8) {
            int i8 = this.f6128h;
            try {
                byte[] bArr = this.f6125e;
                bArr[i8] = (byte) j8;
                bArr[i8 + 1] = (byte) (j8 >> 8);
                bArr[i8 + 2] = (byte) (j8 >> 16);
                bArr[i8 + 3] = (byte) (j8 >> 24);
                bArr[i8 + 4] = (byte) (j8 >> 32);
                bArr[i8 + 5] = (byte) (j8 >> 40);
                bArr[i8 + 6] = (byte) (j8 >> 48);
                bArr[i8 + 7] = (byte) (j8 >> 56);
                this.f6128h = i8 + 8;
            } catch (IndexOutOfBoundsException e8) {
                throw new c(i8, this.f6127g, 8, (Throwable) e8);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.k
        public final void l0(int i8, int i9) {
            A0(i8, 0);
            m0(i9);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.k
        public final void m0(int i8) {
            if (i8 >= 0) {
                C0(i8);
            } else {
                E0(i8);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.k
        public final void p0(int i8, p0 p0Var) {
            A0(1, 3);
            B0(2, i8);
            H0(3, p0Var);
            A0(1, 4);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.k
        public final void q0(int i8, h hVar) {
            A0(1, 3);
            B0(2, i8);
            Y(3, hVar);
            A0(1, 4);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.k
        public final void z0(int i8, String str) {
            A0(i8, 2);
            J0(str);
        }
    }

    /* loaded from: classes.dex */
    public static class c extends IOException {
        c(int i8, int i9, int i10, Throwable th) {
            this(i8, i9, i10, th);
        }

        c(long j8, long j9, int i8, Throwable th) {
            this(String.format(Locale.US, "Pos: %d, limit: %d, len: %d", Long.valueOf(j8), Long.valueOf(j9), Integer.valueOf(i8)), th);
        }

        c(String str, Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.: " + str, th);
        }

        c(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }
    }

    private k() {
    }

    public static int A(int i8) {
        return 4;
    }

    public static int B(int i8, long j8) {
        return J(i8) + C(j8);
    }

    public static int C(long j8) {
        return 8;
    }

    public static int D(int i8, int i9) {
        return J(i8) + E(i9);
    }

    public static int E(int i8) {
        return L(O(i8));
    }

    public static int F(int i8, long j8) {
        return J(i8) + G(j8);
    }

    public static int G(long j8) {
        return N(P(j8));
    }

    public static int H(int i8, String str) {
        return J(i8) + I(str);
    }

    public static int I(String str) {
        int length;
        try {
            length = n1.g(str);
        } catch (n1.d unused) {
            length = str.getBytes(z.f6293b).length;
        }
        return x(length);
    }

    public static int J(int i8) {
        return L(o1.c(i8, 0));
    }

    public static int K(int i8, int i9) {
        return J(i8) + L(i9);
    }

    public static int L(int i8) {
        return (352 - (Integer.numberOfLeadingZeros(i8) * 9)) >>> 6;
    }

    public static int M(int i8, long j8) {
        return J(i8) + N(j8);
    }

    public static int N(long j8) {
        return (640 - (Long.numberOfLeadingZeros(j8) * 9)) >>> 6;
    }

    public static int O(int i8) {
        return (i8 >> 31) ^ (i8 << 1);
    }

    public static long P(long j8) {
        return (j8 >> 63) ^ (j8 << 1);
    }

    public static k S(byte[] bArr) {
        return T(bArr, 0, bArr.length);
    }

    public static k T(byte[] bArr, int i8, int i9) {
        return new b(bArr, i8, i9);
    }

    public static int d(int i8, boolean z7) {
        return J(i8) + e(z7);
    }

    public static int e(boolean z7) {
        return 1;
    }

    public static int f(int i8, h hVar) {
        return J(i8) + g(hVar);
    }

    public static int g(h hVar) {
        return x(hVar.size());
    }

    public static int h(int i8, double d8) {
        return J(i8) + i(d8);
    }

    public static int i(double d8) {
        return 8;
    }

    public static int j(int i8, int i9) {
        return J(i8) + k(i9);
    }

    public static int k(int i8) {
        return u(i8);
    }

    public static int l(int i8, int i9) {
        return J(i8) + m(i9);
    }

    public static int m(int i8) {
        return 4;
    }

    public static int n(int i8, long j8) {
        return J(i8) + o(j8);
    }

    public static int o(long j8) {
        return 8;
    }

    public static int p(int i8, float f8) {
        return J(i8) + q(f8);
    }

    public static int q(float f8) {
        return 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int r(int i8, p0 p0Var, d1 d1Var) {
        return (J(i8) * 2) + s(p0Var, d1Var);
    }

    static int s(p0 p0Var, d1 d1Var) {
        return ((com.google.crypto.tink.shaded.protobuf.a) p0Var).b(d1Var);
    }

    public static int t(int i8, int i9) {
        return J(i8) + u(i9);
    }

    public static int u(int i8) {
        return N(i8);
    }

    public static int v(int i8, long j8) {
        return J(i8) + w(j8);
    }

    public static int w(long j8) {
        return N(j8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int x(int i8) {
        return L(i8) + i8;
    }

    public static int y(int i8, h hVar) {
        return (J(1) * 2) + K(2, i8) + f(3, hVar);
    }

    public static int z(int i8, int i9) {
        return J(i8) + A(i9);
    }

    public abstract void A0(int i8, int i9);

    public abstract void B0(int i8, int i9);

    public abstract void C0(int i8);

    public abstract void D0(int i8, long j8);

    public abstract void E0(long j8);

    final void Q(String str, n1.d dVar) {
        f6121c.log(Level.WARNING, "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) dVar);
        byte[] bytes = str.getBytes(z.f6293b);
        try {
            C0(bytes.length);
            a(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e8) {
            throw new c(e8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean R() {
        return this.f6124b;
    }

    public abstract int U();

    public abstract void V(byte b8);

    public abstract void W(int i8, boolean z7);

    public final void X(boolean z7) {
        V(z7 ? (byte) 1 : (byte) 0);
    }

    public abstract void Y(int i8, h hVar);

    public final void Z(int i8, double d8) {
        f0(i8, Double.doubleToRawLongBits(d8));
    }

    @Override // com.google.crypto.tink.shaded.protobuf.g
    public abstract void a(byte[] bArr, int i8, int i9);

    public final void a0(double d8) {
        g0(Double.doubleToRawLongBits(d8));
    }

    public final void b0(int i8, int i9) {
        l0(i8, i9);
    }

    public final void c() {
        if (U() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public final void c0(int i8) {
        m0(i8);
    }

    public abstract void d0(int i8, int i9);

    public abstract void e0(int i8);

    public abstract void f0(int i8, long j8);

    public abstract void g0(long j8);

    public final void h0(int i8, float f8) {
        d0(i8, Float.floatToRawIntBits(f8));
    }

    public final void i0(float f8) {
        e0(Float.floatToRawIntBits(f8));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void j0(int i8, p0 p0Var, d1 d1Var) {
        A0(i8, 3);
        k0(p0Var, d1Var);
        A0(i8, 4);
    }

    final void k0(p0 p0Var, d1 d1Var) {
        d1Var.c(p0Var, this.f6123a);
    }

    public abstract void l0(int i8, int i9);

    public abstract void m0(int i8);

    public final void n0(int i8, long j8) {
        D0(i8, j8);
    }

    public final void o0(long j8) {
        E0(j8);
    }

    public abstract void p0(int i8, p0 p0Var);

    public abstract void q0(int i8, h hVar);

    public final void r0(int i8, int i9) {
        d0(i8, i9);
    }

    public final void s0(int i8) {
        e0(i8);
    }

    public final void t0(int i8, long j8) {
        f0(i8, j8);
    }

    public final void u0(long j8) {
        g0(j8);
    }

    public final void v0(int i8, int i9) {
        B0(i8, O(i9));
    }

    public final void w0(int i8) {
        C0(O(i8));
    }

    public final void x0(int i8, long j8) {
        D0(i8, P(j8));
    }

    public final void y0(long j8) {
        E0(P(j8));
    }

    public abstract void z0(int i8, String str);
}
