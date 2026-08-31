package com.google.crypto.tink.shaded.protobuf;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes.dex */
public abstract class i {

    /* renamed from: g  reason: collision with root package name */
    private static volatile int f6089g = 100;

    /* renamed from: a  reason: collision with root package name */
    int f6090a;

    /* renamed from: b  reason: collision with root package name */
    int f6091b;

    /* renamed from: c  reason: collision with root package name */
    int f6092c;

    /* renamed from: d  reason: collision with root package name */
    int f6093d;

    /* renamed from: e  reason: collision with root package name */
    j f6094e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f6095f;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class b extends i {

        /* renamed from: h  reason: collision with root package name */
        private final byte[] f6096h;

        /* renamed from: i  reason: collision with root package name */
        private final boolean f6097i;

        /* renamed from: j  reason: collision with root package name */
        private int f6098j;

        /* renamed from: k  reason: collision with root package name */
        private int f6099k;

        /* renamed from: l  reason: collision with root package name */
        private int f6100l;

        /* renamed from: m  reason: collision with root package name */
        private int f6101m;

        /* renamed from: n  reason: collision with root package name */
        private int f6102n;

        /* renamed from: o  reason: collision with root package name */
        private boolean f6103o;

        /* renamed from: p  reason: collision with root package name */
        private int f6104p;

        private b(byte[] bArr, int i8, int i9, boolean z7) {
            super();
            this.f6104p = Integer.MAX_VALUE;
            this.f6096h = bArr;
            this.f6098j = i9 + i8;
            this.f6100l = i8;
            this.f6101m = i8;
            this.f6097i = z7;
        }

        private void P() {
            int i8 = this.f6098j + this.f6099k;
            this.f6098j = i8;
            int i9 = i8 - this.f6101m;
            int i10 = this.f6104p;
            if (i9 <= i10) {
                this.f6099k = 0;
                return;
            }
            int i11 = i9 - i10;
            this.f6099k = i11;
            this.f6098j = i8 - i11;
        }

        private void R() {
            if (this.f6098j - this.f6100l >= 10) {
                S();
            } else {
                T();
            }
        }

        private void S() {
            for (int i8 = 0; i8 < 10; i8++) {
                byte[] bArr = this.f6096h;
                int i9 = this.f6100l;
                this.f6100l = i9 + 1;
                if (bArr[i9] >= 0) {
                    return;
                }
            }
            throw a0.f();
        }

        private void T() {
            for (int i8 = 0; i8 < 10; i8++) {
                if (I() >= 0) {
                    return;
                }
            }
            throw a0.f();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public long A() {
            return i.e(N());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public String B() {
            int M = M();
            if (M > 0) {
                int i8 = this.f6098j;
                int i9 = this.f6100l;
                if (M <= i8 - i9) {
                    String str = new String(this.f6096h, i9, M, z.f6293b);
                    this.f6100l += M;
                    return str;
                }
            }
            if (M == 0) {
                return "";
            }
            if (M < 0) {
                throw a0.g();
            }
            throw a0.m();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public String C() {
            int M = M();
            if (M > 0) {
                int i8 = this.f6098j;
                int i9 = this.f6100l;
                if (M <= i8 - i9) {
                    String e8 = n1.e(this.f6096h, i9, M);
                    this.f6100l += M;
                    return e8;
                }
            }
            if (M == 0) {
                return "";
            }
            if (M <= 0) {
                throw a0.g();
            }
            throw a0.m();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public int D() {
            if (g()) {
                this.f6102n = 0;
                return 0;
            }
            int M = M();
            this.f6102n = M;
            if (o1.a(M) != 0) {
                return this.f6102n;
            }
            throw a0.c();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public int E() {
            return M();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public long F() {
            return N();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public boolean G(int i8) {
            int b8 = o1.b(i8);
            if (b8 == 0) {
                R();
                return true;
            } else if (b8 == 1) {
                Q(8);
                return true;
            } else if (b8 == 2) {
                Q(M());
                return true;
            } else if (b8 == 3) {
                H();
                a(o1.c(o1.a(i8), 4));
                return true;
            } else if (b8 == 4) {
                c();
                return false;
            } else if (b8 == 5) {
                Q(4);
                return true;
            } else {
                throw a0.e();
            }
        }

        public byte I() {
            int i8 = this.f6100l;
            if (i8 != this.f6098j) {
                byte[] bArr = this.f6096h;
                this.f6100l = i8 + 1;
                return bArr[i8];
            }
            throw a0.m();
        }

        public byte[] J(int i8) {
            if (i8 > 0) {
                int i9 = this.f6098j;
                int i10 = this.f6100l;
                if (i8 <= i9 - i10) {
                    int i11 = i8 + i10;
                    this.f6100l = i11;
                    return Arrays.copyOfRange(this.f6096h, i10, i11);
                }
            }
            if (i8 <= 0) {
                if (i8 == 0) {
                    return z.f6295d;
                }
                throw a0.g();
            }
            throw a0.m();
        }

        public int K() {
            int i8 = this.f6100l;
            if (this.f6098j - i8 >= 4) {
                byte[] bArr = this.f6096h;
                this.f6100l = i8 + 4;
                return ((bArr[i8 + 3] & 255) << 24) | (bArr[i8] & 255) | ((bArr[i8 + 1] & 255) << 8) | ((bArr[i8 + 2] & 255) << 16);
            }
            throw a0.m();
        }

        public long L() {
            int i8 = this.f6100l;
            if (this.f6098j - i8 >= 8) {
                byte[] bArr = this.f6096h;
                this.f6100l = i8 + 8;
                return ((bArr[i8 + 7] & 255) << 56) | (bArr[i8] & 255) | ((bArr[i8 + 1] & 255) << 8) | ((bArr[i8 + 2] & 255) << 16) | ((bArr[i8 + 3] & 255) << 24) | ((bArr[i8 + 4] & 255) << 32) | ((bArr[i8 + 5] & 255) << 40) | ((bArr[i8 + 6] & 255) << 48);
            }
            throw a0.m();
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x0068, code lost:
            if (r2[r3] < 0) goto L34;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int M() {
            /*
                r5 = this;
                int r0 = r5.f6100l
                int r1 = r5.f6098j
                if (r1 != r0) goto L7
                goto L6a
            L7:
                byte[] r2 = r5.f6096h
                int r3 = r0 + 1
                r0 = r2[r0]
                if (r0 < 0) goto L12
                r5.f6100l = r3
                return r0
            L12:
                int r1 = r1 - r3
                r4 = 9
                if (r1 >= r4) goto L18
                goto L6a
            L18:
                int r1 = r3 + 1
                r3 = r2[r3]
                int r3 = r3 << 7
                r0 = r0 ^ r3
                if (r0 >= 0) goto L24
                r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
                goto L70
            L24:
                int r3 = r1 + 1
                r1 = r2[r1]
                int r1 = r1 << 14
                r0 = r0 ^ r1
                if (r0 < 0) goto L31
                r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            L2f:
                r1 = r3
                goto L70
            L31:
                int r1 = r3 + 1
                r3 = r2[r3]
                int r3 = r3 << 21
                r0 = r0 ^ r3
                if (r0 >= 0) goto L3f
                r2 = -2080896(0xffffffffffe03f80, float:NaN)
                r0 = r0 ^ r2
                goto L70
            L3f:
                int r3 = r1 + 1
                r1 = r2[r1]
                int r4 = r1 << 28
                r0 = r0 ^ r4
                r4 = 266354560(0xfe03f80, float:2.2112565E-29)
                r0 = r0 ^ r4
                if (r1 >= 0) goto L2f
                int r1 = r3 + 1
                r3 = r2[r3]
                if (r3 >= 0) goto L70
                int r3 = r1 + 1
                r1 = r2[r1]
                if (r1 >= 0) goto L2f
                int r1 = r3 + 1
                r3 = r2[r3]
                if (r3 >= 0) goto L70
                int r3 = r1 + 1
                r1 = r2[r1]
                if (r1 >= 0) goto L2f
                int r1 = r3 + 1
                r2 = r2[r3]
                if (r2 >= 0) goto L70
            L6a:
                long r0 = r5.O()
                int r0 = (int) r0
                return r0
            L70:
                r5.f6100l = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.i.b.M():int");
        }

        /* JADX WARN: Code restructure failed: missing block: B:39:0x00b4, code lost:
            if (r2[r0] < 0) goto L42;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public long N() {
            /*
                Method dump skipped, instructions count: 192
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.i.b.N():long");
        }

        long O() {
            long j8 = 0;
            for (int i8 = 0; i8 < 64; i8 += 7) {
                byte I = I();
                j8 |= (I & Byte.MAX_VALUE) << i8;
                if ((I & 128) == 0) {
                    return j8;
                }
            }
            throw a0.f();
        }

        public void Q(int i8) {
            if (i8 >= 0) {
                int i9 = this.f6098j;
                int i10 = this.f6100l;
                if (i8 <= i9 - i10) {
                    this.f6100l = i10 + i8;
                    return;
                }
            }
            if (i8 >= 0) {
                throw a0.m();
            }
            throw a0.g();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public void a(int i8) {
            if (this.f6102n != i8) {
                throw a0.b();
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public int f() {
            return this.f6100l - this.f6101m;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public boolean g() {
            return this.f6100l == this.f6098j;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public void m(int i8) {
            this.f6104p = i8;
            P();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public int n(int i8) {
            if (i8 >= 0) {
                int f8 = i8 + f();
                if (f8 >= 0) {
                    int i9 = this.f6104p;
                    if (f8 <= i9) {
                        this.f6104p = f8;
                        P();
                        return i9;
                    }
                    throw a0.m();
                }
                throw a0.l();
            }
            throw a0.g();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public boolean o() {
            return N() != 0;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public h p() {
            int M = M();
            if (M > 0) {
                int i8 = this.f6098j;
                int i9 = this.f6100l;
                if (M <= i8 - i9) {
                    h A = (this.f6097i && this.f6103o) ? h.A(this.f6096h, i9, M) : h.m(this.f6096h, i9, M);
                    this.f6100l += M;
                    return A;
                }
            }
            return M == 0 ? h.f6072f : h.z(J(M));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public double q() {
            return Double.longBitsToDouble(L());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public int r() {
            return M();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public int s() {
            return K();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public long t() {
            return L();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public float u() {
            return Float.intBitsToFloat(K());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public int v() {
            return M();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public long w() {
            return N();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public int x() {
            return K();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public long y() {
            return L();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public int z() {
            return i.d(M());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class c extends i {

        /* renamed from: h  reason: collision with root package name */
        private final InputStream f6105h;

        /* renamed from: i  reason: collision with root package name */
        private final byte[] f6106i;

        /* renamed from: j  reason: collision with root package name */
        private int f6107j;

        /* renamed from: k  reason: collision with root package name */
        private int f6108k;

        /* renamed from: l  reason: collision with root package name */
        private int f6109l;

        /* renamed from: m  reason: collision with root package name */
        private int f6110m;

        /* renamed from: n  reason: collision with root package name */
        private int f6111n;

        /* renamed from: o  reason: collision with root package name */
        private int f6112o;

        private c(InputStream inputStream, int i8) {
            super();
            this.f6112o = Integer.MAX_VALUE;
            z.b(inputStream, "input");
            this.f6105h = inputStream;
            this.f6106i = new byte[i8];
            this.f6107j = 0;
            this.f6109l = 0;
            this.f6111n = 0;
        }

        private static int I(InputStream inputStream) {
            try {
                return inputStream.available();
            } catch (a0 e8) {
                e8.j();
                throw e8;
            }
        }

        private static int J(InputStream inputStream, byte[] bArr, int i8, int i9) {
            try {
                return inputStream.read(bArr, i8, i9);
            } catch (a0 e8) {
                e8.j();
                throw e8;
            }
        }

        private h K(int i8) {
            byte[] N = N(i8);
            if (N != null) {
                return h.l(N);
            }
            int i9 = this.f6109l;
            int i10 = this.f6107j;
            int i11 = i10 - i9;
            this.f6111n += i10;
            this.f6109l = 0;
            this.f6107j = 0;
            List<byte[]> O = O(i8 - i11);
            byte[] bArr = new byte[i8];
            System.arraycopy(this.f6106i, i9, bArr, 0, i11);
            for (byte[] bArr2 : O) {
                System.arraycopy(bArr2, 0, bArr, i11, bArr2.length);
                i11 += bArr2.length;
            }
            return h.z(bArr);
        }

        private byte[] M(int i8, boolean z7) {
            byte[] N = N(i8);
            if (N != null) {
                return z7 ? (byte[]) N.clone() : N;
            }
            int i9 = this.f6109l;
            int i10 = this.f6107j;
            int i11 = i10 - i9;
            this.f6111n += i10;
            this.f6109l = 0;
            this.f6107j = 0;
            List<byte[]> O = O(i8 - i11);
            byte[] bArr = new byte[i8];
            System.arraycopy(this.f6106i, i9, bArr, 0, i11);
            for (byte[] bArr2 : O) {
                System.arraycopy(bArr2, 0, bArr, i11, bArr2.length);
                i11 += bArr2.length;
            }
            return bArr;
        }

        private byte[] N(int i8) {
            if (i8 == 0) {
                return z.f6295d;
            }
            if (i8 >= 0) {
                int i9 = this.f6111n;
                int i10 = this.f6109l;
                int i11 = i9 + i10 + i8;
                if (i11 - this.f6093d <= 0) {
                    int i12 = this.f6112o;
                    if (i11 > i12) {
                        X((i12 - i9) - i10);
                        throw a0.m();
                    }
                    int i13 = this.f6107j - i10;
                    int i14 = i8 - i13;
                    if (i14 < 4096 || i14 <= I(this.f6105h)) {
                        byte[] bArr = new byte[i8];
                        System.arraycopy(this.f6106i, this.f6109l, bArr, 0, i13);
                        this.f6111n += this.f6107j;
                        this.f6109l = 0;
                        this.f6107j = 0;
                        while (i13 < i8) {
                            int J = J(this.f6105h, bArr, i13, i8 - i13);
                            if (J == -1) {
                                throw a0.m();
                            }
                            this.f6111n += J;
                            i13 += J;
                        }
                        return bArr;
                    }
                    return null;
                }
                throw a0.l();
            }
            throw a0.g();
        }

        private List O(int i8) {
            ArrayList arrayList = new ArrayList();
            while (i8 > 0) {
                int min = Math.min(i8, 4096);
                byte[] bArr = new byte[min];
                int i9 = 0;
                while (i9 < min) {
                    int read = this.f6105h.read(bArr, i9, min - i9);
                    if (read == -1) {
                        throw a0.m();
                    }
                    this.f6111n += read;
                    i9 += read;
                }
                i8 -= min;
                arrayList.add(bArr);
            }
            return arrayList;
        }

        private void U() {
            int i8 = this.f6107j + this.f6108k;
            this.f6107j = i8;
            int i9 = this.f6111n + i8;
            int i10 = this.f6112o;
            if (i9 <= i10) {
                this.f6108k = 0;
                return;
            }
            int i11 = i9 - i10;
            this.f6108k = i11;
            this.f6107j = i8 - i11;
        }

        private void V(int i8) {
            if (c0(i8)) {
                return;
            }
            if (i8 <= (this.f6093d - this.f6111n) - this.f6109l) {
                throw a0.m();
            }
            throw a0.l();
        }

        private static long W(InputStream inputStream, long j8) {
            try {
                return inputStream.skip(j8);
            } catch (a0 e8) {
                e8.j();
                throw e8;
            }
        }

        private void Y(int i8) {
            if (i8 < 0) {
                throw a0.g();
            }
            int i9 = this.f6111n;
            int i10 = this.f6109l;
            int i11 = i9 + i10 + i8;
            int i12 = this.f6112o;
            if (i11 > i12) {
                X((i12 - i9) - i10);
                throw a0.m();
            }
            this.f6111n = i9 + i10;
            int i13 = this.f6107j - i10;
            this.f6107j = 0;
            this.f6109l = 0;
            while (i13 < i8) {
                try {
                    long j8 = i8 - i13;
                    long W = W(this.f6105h, j8);
                    int i14 = (W > 0L ? 1 : (W == 0L ? 0 : -1));
                    if (i14 < 0 || W > j8) {
                        throw new IllegalStateException(this.f6105h.getClass() + "#skip returned invalid result: " + W + "\nThe InputStream implementation is buggy.");
                    } else if (i14 == 0) {
                        break;
                    } else {
                        i13 += (int) W;
                    }
                } finally {
                    this.f6111n += i13;
                    U();
                }
            }
            if (i13 >= i8) {
                return;
            }
            int i15 = this.f6107j;
            int i16 = i15 - this.f6109l;
            this.f6109l = i15;
            while (true) {
                V(1);
                int i17 = i8 - i16;
                int i18 = this.f6107j;
                if (i17 <= i18) {
                    this.f6109l = i17;
                    return;
                } else {
                    i16 += i18;
                    this.f6109l = i18;
                }
            }
        }

        private void Z() {
            if (this.f6107j - this.f6109l >= 10) {
                a0();
            } else {
                b0();
            }
        }

        private void a0() {
            for (int i8 = 0; i8 < 10; i8++) {
                byte[] bArr = this.f6106i;
                int i9 = this.f6109l;
                this.f6109l = i9 + 1;
                if (bArr[i9] >= 0) {
                    return;
                }
            }
            throw a0.f();
        }

        private void b0() {
            for (int i8 = 0; i8 < 10; i8++) {
                if (L() >= 0) {
                    return;
                }
            }
            throw a0.f();
        }

        private boolean c0(int i8) {
            int i9 = this.f6109l;
            int i10 = i9 + i8;
            int i11 = this.f6107j;
            if (i10 <= i11) {
                throw new IllegalStateException("refillBuffer() called when " + i8 + " bytes were already available in buffer");
            }
            int i12 = this.f6093d;
            int i13 = this.f6111n;
            if (i8 <= (i12 - i13) - i9 && i13 + i9 + i8 <= this.f6112o) {
                if (i9 > 0) {
                    if (i11 > i9) {
                        byte[] bArr = this.f6106i;
                        System.arraycopy(bArr, i9, bArr, 0, i11 - i9);
                    }
                    this.f6111n += i9;
                    this.f6107j -= i9;
                    this.f6109l = 0;
                }
                InputStream inputStream = this.f6105h;
                byte[] bArr2 = this.f6106i;
                int i14 = this.f6107j;
                int J = J(inputStream, bArr2, i14, Math.min(bArr2.length - i14, (this.f6093d - this.f6111n) - i14));
                if (J != 0 && J >= -1 && J <= this.f6106i.length) {
                    if (J > 0) {
                        this.f6107j += J;
                        U();
                        return this.f6107j >= i8 || c0(i8);
                    }
                    return false;
                }
                throw new IllegalStateException(this.f6105h.getClass() + "#read(byte[]) returned invalid result: " + J + "\nThe InputStream implementation is buggy.");
            }
            return false;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public long A() {
            return i.e(S());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public String B() {
            int R = R();
            if (R > 0) {
                int i8 = this.f6107j;
                int i9 = this.f6109l;
                if (R <= i8 - i9) {
                    String str = new String(this.f6106i, i9, R, z.f6293b);
                    this.f6109l += R;
                    return str;
                }
            }
            if (R == 0) {
                return "";
            }
            if (R >= 0) {
                if (R <= this.f6107j) {
                    V(R);
                    String str2 = new String(this.f6106i, this.f6109l, R, z.f6293b);
                    this.f6109l += R;
                    return str2;
                }
                return new String(M(R, false), z.f6293b);
            }
            throw a0.g();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public String C() {
            byte[] M;
            int i8;
            int R = R();
            int i9 = this.f6109l;
            int i10 = this.f6107j;
            if (R <= i10 - i9 && R > 0) {
                M = this.f6106i;
                i8 = i9 + R;
            } else if (R == 0) {
                return "";
            } else {
                if (R < 0) {
                    throw a0.g();
                }
                i9 = 0;
                if (R > i10) {
                    M = M(R, false);
                    return n1.e(M, i9, R);
                }
                V(R);
                M = this.f6106i;
                i8 = R + 0;
            }
            this.f6109l = i8;
            return n1.e(M, i9, R);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public int D() {
            if (g()) {
                this.f6110m = 0;
                return 0;
            }
            int R = R();
            this.f6110m = R;
            if (o1.a(R) != 0) {
                return this.f6110m;
            }
            throw a0.c();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public int E() {
            return R();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public long F() {
            return S();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public boolean G(int i8) {
            int b8 = o1.b(i8);
            if (b8 == 0) {
                Z();
                return true;
            } else if (b8 == 1) {
                X(8);
                return true;
            } else if (b8 == 2) {
                X(R());
                return true;
            } else if (b8 == 3) {
                H();
                a(o1.c(o1.a(i8), 4));
                return true;
            } else if (b8 == 4) {
                c();
                return false;
            } else if (b8 == 5) {
                X(4);
                return true;
            } else {
                throw a0.e();
            }
        }

        public byte L() {
            if (this.f6109l == this.f6107j) {
                V(1);
            }
            byte[] bArr = this.f6106i;
            int i8 = this.f6109l;
            this.f6109l = i8 + 1;
            return bArr[i8];
        }

        public int P() {
            int i8 = this.f6109l;
            if (this.f6107j - i8 < 4) {
                V(4);
                i8 = this.f6109l;
            }
            byte[] bArr = this.f6106i;
            this.f6109l = i8 + 4;
            return ((bArr[i8 + 3] & 255) << 24) | (bArr[i8] & 255) | ((bArr[i8 + 1] & 255) << 8) | ((bArr[i8 + 2] & 255) << 16);
        }

        public long Q() {
            int i8 = this.f6109l;
            if (this.f6107j - i8 < 8) {
                V(8);
                i8 = this.f6109l;
            }
            byte[] bArr = this.f6106i;
            this.f6109l = i8 + 8;
            return ((bArr[i8 + 7] & 255) << 56) | (bArr[i8] & 255) | ((bArr[i8 + 1] & 255) << 8) | ((bArr[i8 + 2] & 255) << 16) | ((bArr[i8 + 3] & 255) << 24) | ((bArr[i8 + 4] & 255) << 32) | ((bArr[i8 + 5] & 255) << 40) | ((bArr[i8 + 6] & 255) << 48);
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x0068, code lost:
            if (r2[r3] < 0) goto L34;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int R() {
            /*
                r5 = this;
                int r0 = r5.f6109l
                int r1 = r5.f6107j
                if (r1 != r0) goto L7
                goto L6a
            L7:
                byte[] r2 = r5.f6106i
                int r3 = r0 + 1
                r0 = r2[r0]
                if (r0 < 0) goto L12
                r5.f6109l = r3
                return r0
            L12:
                int r1 = r1 - r3
                r4 = 9
                if (r1 >= r4) goto L18
                goto L6a
            L18:
                int r1 = r3 + 1
                r3 = r2[r3]
                int r3 = r3 << 7
                r0 = r0 ^ r3
                if (r0 >= 0) goto L24
                r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
                goto L70
            L24:
                int r3 = r1 + 1
                r1 = r2[r1]
                int r1 = r1 << 14
                r0 = r0 ^ r1
                if (r0 < 0) goto L31
                r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            L2f:
                r1 = r3
                goto L70
            L31:
                int r1 = r3 + 1
                r3 = r2[r3]
                int r3 = r3 << 21
                r0 = r0 ^ r3
                if (r0 >= 0) goto L3f
                r2 = -2080896(0xffffffffffe03f80, float:NaN)
                r0 = r0 ^ r2
                goto L70
            L3f:
                int r3 = r1 + 1
                r1 = r2[r1]
                int r4 = r1 << 28
                r0 = r0 ^ r4
                r4 = 266354560(0xfe03f80, float:2.2112565E-29)
                r0 = r0 ^ r4
                if (r1 >= 0) goto L2f
                int r1 = r3 + 1
                r3 = r2[r3]
                if (r3 >= 0) goto L70
                int r3 = r1 + 1
                r1 = r2[r1]
                if (r1 >= 0) goto L2f
                int r1 = r3 + 1
                r3 = r2[r3]
                if (r3 >= 0) goto L70
                int r3 = r1 + 1
                r1 = r2[r1]
                if (r1 >= 0) goto L2f
                int r1 = r3 + 1
                r2 = r2[r3]
                if (r2 >= 0) goto L70
            L6a:
                long r0 = r5.T()
                int r0 = (int) r0
                return r0
            L70:
                r5.f6109l = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.i.c.R():int");
        }

        /* JADX WARN: Code restructure failed: missing block: B:39:0x00b4, code lost:
            if (r2[r0] < 0) goto L42;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public long S() {
            /*
                Method dump skipped, instructions count: 192
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.i.c.S():long");
        }

        long T() {
            long j8 = 0;
            for (int i8 = 0; i8 < 64; i8 += 7) {
                byte L = L();
                j8 |= (L & Byte.MAX_VALUE) << i8;
                if ((L & 128) == 0) {
                    return j8;
                }
            }
            throw a0.f();
        }

        public void X(int i8) {
            int i9 = this.f6107j;
            int i10 = this.f6109l;
            if (i8 > i9 - i10 || i8 < 0) {
                Y(i8);
            } else {
                this.f6109l = i10 + i8;
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public void a(int i8) {
            if (this.f6110m != i8) {
                throw a0.b();
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public int f() {
            return this.f6111n + this.f6109l;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public boolean g() {
            return this.f6109l == this.f6107j && !c0(1);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public void m(int i8) {
            this.f6112o = i8;
            U();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public int n(int i8) {
            if (i8 >= 0) {
                int i9 = i8 + this.f6111n + this.f6109l;
                if (i9 >= 0) {
                    int i10 = this.f6112o;
                    if (i9 <= i10) {
                        this.f6112o = i9;
                        U();
                        return i10;
                    }
                    throw a0.m();
                }
                throw a0.l();
            }
            throw a0.g();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public boolean o() {
            return S() != 0;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public h p() {
            int R = R();
            int i8 = this.f6107j;
            int i9 = this.f6109l;
            if (R <= i8 - i9 && R > 0) {
                h m8 = h.m(this.f6106i, i9, R);
                this.f6109l += R;
                return m8;
            } else if (R == 0) {
                return h.f6072f;
            } else {
                if (R >= 0) {
                    return K(R);
                }
                throw a0.g();
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public double q() {
            return Double.longBitsToDouble(Q());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public int r() {
            return R();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public int s() {
            return P();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public long t() {
            return Q();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public float u() {
            return Float.intBitsToFloat(P());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public int v() {
            return R();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public long w() {
            return S();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public int x() {
            return P();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public long y() {
            return Q();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.i
        public int z() {
            return i.d(R());
        }
    }

    private i() {
        this.f6092c = f6089g;
        this.f6093d = Integer.MAX_VALUE;
        this.f6095f = false;
    }

    public static int d(int i8) {
        return (-(i8 & 1)) ^ (i8 >>> 1);
    }

    public static long e(long j8) {
        return (-(j8 & 1)) ^ (j8 >>> 1);
    }

    public static i h(InputStream inputStream) {
        return i(inputStream, 4096);
    }

    public static i i(InputStream inputStream, int i8) {
        if (i8 > 0) {
            return inputStream == null ? j(z.f6295d) : new c(inputStream, i8);
        }
        throw new IllegalArgumentException("bufferSize must be > 0");
    }

    public static i j(byte[] bArr) {
        return k(bArr, 0, bArr.length);
    }

    public static i k(byte[] bArr, int i8, int i9) {
        return l(bArr, i8, i9, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static i l(byte[] bArr, int i8, int i9, boolean z7) {
        b bVar = new b(bArr, i8, i9, z7);
        try {
            bVar.n(i9);
            return bVar;
        } catch (a0 e8) {
            throw new IllegalArgumentException(e8);
        }
    }

    public abstract long A();

    public abstract String B();

    public abstract String C();

    public abstract int D();

    public abstract int E();

    public abstract long F();

    public abstract boolean G(int i8);

    public void H() {
        int D;
        do {
            D = D();
            if (D == 0) {
                return;
            }
            b();
            this.f6091b++;
            this.f6091b--;
        } while (G(D));
    }

    public abstract void a(int i8);

    public void b() {
        if (this.f6090a + this.f6091b >= this.f6092c) {
            throw a0.i();
        }
    }

    public void c() {
        if (this.f6091b == 0) {
            a(0);
        }
    }

    public abstract int f();

    public abstract boolean g();

    public abstract void m(int i8);

    public abstract int n(int i8);

    public abstract boolean o();

    public abstract h p();

    public abstract double q();

    public abstract int r();

    public abstract int s();

    public abstract long t();

    public abstract float u();

    public abstract int v();

    public abstract long w();

    public abstract int x();

    public abstract long y();

    public abstract int z();
}
