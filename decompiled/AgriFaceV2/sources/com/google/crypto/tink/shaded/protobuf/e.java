package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.z;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class e {

    /* renamed from: a  reason: collision with root package name */
    private static volatile int f6061a = 100;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public int f6062a;

        /* renamed from: b  reason: collision with root package name */
        public long f6063b;

        /* renamed from: c  reason: collision with root package name */
        public Object f6064c;

        /* renamed from: d  reason: collision with root package name */
        public final p f6065d;

        /* renamed from: e  reason: collision with root package name */
        public int f6066e;

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(p pVar) {
            pVar.getClass();
            this.f6065d = pVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int A(int i8, byte[] bArr, int i9, int i10, z.d dVar, a aVar) {
        y yVar = (y) dVar;
        int I = I(bArr, i9, aVar);
        while (true) {
            yVar.f(i.d(aVar.f6062a));
            if (I >= i10) {
                break;
            }
            int I2 = I(bArr, I, aVar);
            if (i8 != aVar.f6062a) {
                break;
            }
            I = I(bArr, I2, aVar);
        }
        return I;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int B(int i8, byte[] bArr, int i9, int i10, z.d dVar, a aVar) {
        g0 g0Var = (g0) dVar;
        int L = L(bArr, i9, aVar);
        while (true) {
            g0Var.f(i.e(aVar.f6063b));
            if (L >= i10) {
                break;
            }
            int I = I(bArr, L, aVar);
            if (i8 != aVar.f6062a) {
                break;
            }
            L = L(bArr, I, aVar);
        }
        return L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int C(byte[] bArr, int i8, a aVar) {
        int I = I(bArr, i8, aVar);
        int i9 = aVar.f6062a;
        if (i9 >= 0) {
            if (i9 == 0) {
                aVar.f6064c = "";
                return I;
            }
            aVar.f6064c = new String(bArr, I, i9, z.f6293b);
            return I + i9;
        }
        throw a0.g();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x001d  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x002e -> B:6:0x000c). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:8:0x0017 -> B:9:0x001b). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int D(int r4, byte[] r5, int r6, int r7, com.google.crypto.tink.shaded.protobuf.z.d r8, com.google.crypto.tink.shaded.protobuf.e.a r9) {
        /*
            int r6 = I(r5, r6, r9)
            int r0 = r9.f6062a
            if (r0 < 0) goto L3f
            java.lang.String r1 = ""
            if (r0 != 0) goto L10
        Lc:
            r8.add(r1)
            goto L1b
        L10:
            java.lang.String r2 = new java.lang.String
            java.nio.charset.Charset r3 = com.google.crypto.tink.shaded.protobuf.z.f6293b
            r2.<init>(r5, r6, r0, r3)
        L17:
            r8.add(r2)
            int r6 = r6 + r0
        L1b:
            if (r6 >= r7) goto L3e
            int r0 = I(r5, r6, r9)
            int r2 = r9.f6062a
            if (r4 == r2) goto L26
            goto L3e
        L26:
            int r6 = I(r5, r0, r9)
            int r0 = r9.f6062a
            if (r0 < 0) goto L39
            if (r0 != 0) goto L31
            goto Lc
        L31:
            java.lang.String r2 = new java.lang.String
            java.nio.charset.Charset r3 = com.google.crypto.tink.shaded.protobuf.z.f6293b
            r2.<init>(r5, r6, r0, r3)
            goto L17
        L39:
            com.google.crypto.tink.shaded.protobuf.a0 r4 = com.google.crypto.tink.shaded.protobuf.a0.g()
            throw r4
        L3e:
            return r6
        L3f:
            com.google.crypto.tink.shaded.protobuf.a0 r4 = com.google.crypto.tink.shaded.protobuf.a0.g()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.e.D(int, byte[], int, int, com.google.crypto.tink.shaded.protobuf.z$d, com.google.crypto.tink.shaded.protobuf.e$a):int");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0025  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x001f -> B:11:0x0023). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0036 -> B:6:0x000c). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int E(int r5, byte[] r6, int r7, int r8, com.google.crypto.tink.shaded.protobuf.z.d r9, com.google.crypto.tink.shaded.protobuf.e.a r10) {
        /*
            int r7 = I(r6, r7, r10)
            int r0 = r10.f6062a
            if (r0 < 0) goto L59
            java.lang.String r1 = ""
            if (r0 != 0) goto L10
        Lc:
            r9.add(r1)
            goto L23
        L10:
            int r2 = r7 + r0
            boolean r3 = com.google.crypto.tink.shaded.protobuf.n1.m(r6, r7, r2)
            if (r3 == 0) goto L54
            java.lang.String r3 = new java.lang.String
            java.nio.charset.Charset r4 = com.google.crypto.tink.shaded.protobuf.z.f6293b
            r3.<init>(r6, r7, r0, r4)
        L1f:
            r9.add(r3)
            r7 = r2
        L23:
            if (r7 >= r8) goto L53
            int r0 = I(r6, r7, r10)
            int r2 = r10.f6062a
            if (r5 == r2) goto L2e
            goto L53
        L2e:
            int r7 = I(r6, r0, r10)
            int r0 = r10.f6062a
            if (r0 < 0) goto L4e
            if (r0 != 0) goto L39
            goto Lc
        L39:
            int r2 = r7 + r0
            boolean r3 = com.google.crypto.tink.shaded.protobuf.n1.m(r6, r7, r2)
            if (r3 == 0) goto L49
            java.lang.String r3 = new java.lang.String
            java.nio.charset.Charset r4 = com.google.crypto.tink.shaded.protobuf.z.f6293b
            r3.<init>(r6, r7, r0, r4)
            goto L1f
        L49:
            com.google.crypto.tink.shaded.protobuf.a0 r5 = com.google.crypto.tink.shaded.protobuf.a0.d()
            throw r5
        L4e:
            com.google.crypto.tink.shaded.protobuf.a0 r5 = com.google.crypto.tink.shaded.protobuf.a0.g()
            throw r5
        L53:
            return r7
        L54:
            com.google.crypto.tink.shaded.protobuf.a0 r5 = com.google.crypto.tink.shaded.protobuf.a0.d()
            throw r5
        L59:
            com.google.crypto.tink.shaded.protobuf.a0 r5 = com.google.crypto.tink.shaded.protobuf.a0.g()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.e.E(int, byte[], int, int, com.google.crypto.tink.shaded.protobuf.z$d, com.google.crypto.tink.shaded.protobuf.e$a):int");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int F(byte[] bArr, int i8, a aVar) {
        int I = I(bArr, i8, aVar);
        int i9 = aVar.f6062a;
        if (i9 >= 0) {
            if (i9 == 0) {
                aVar.f6064c = "";
                return I;
            }
            aVar.f6064c = n1.e(bArr, I, i9);
            return I + i9;
        }
        throw a0.g();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int G(int i8, byte[] bArr, int i9, int i10, k1 k1Var, a aVar) {
        if (o1.a(i8) != 0) {
            int b8 = o1.b(i8);
            if (b8 == 0) {
                int L = L(bArr, i9, aVar);
                k1Var.n(i8, Long.valueOf(aVar.f6063b));
                return L;
            } else if (b8 == 1) {
                k1Var.n(i8, Long.valueOf(j(bArr, i9)));
                return i9 + 8;
            } else if (b8 == 2) {
                int I = I(bArr, i9, aVar);
                int i11 = aVar.f6062a;
                if (i11 >= 0) {
                    if (i11 <= bArr.length - I) {
                        k1Var.n(i8, i11 == 0 ? h.f6072f : h.m(bArr, I, i11));
                        return I + i11;
                    }
                    throw a0.m();
                }
                throw a0.g();
            } else if (b8 != 3) {
                if (b8 == 5) {
                    k1Var.n(i8, Integer.valueOf(h(bArr, i9)));
                    return i9 + 4;
                }
                throw a0.c();
            } else {
                k1 k8 = k1.k();
                int i12 = (i8 & (-8)) | 4;
                int i13 = aVar.f6066e + 1;
                aVar.f6066e = i13;
                a(i13);
                int i14 = 0;
                while (true) {
                    if (i9 >= i10) {
                        break;
                    }
                    int I2 = I(bArr, i9, aVar);
                    int i15 = aVar.f6062a;
                    i14 = i15;
                    if (i15 == i12) {
                        i9 = I2;
                        break;
                    }
                    int G = G(i14, bArr, I2, i10, k8, aVar);
                    i14 = i15;
                    i9 = G;
                }
                aVar.f6066e--;
                if (i9 > i10 || i14 != i12) {
                    throw a0.h();
                }
                k1Var.n(i8, k8);
                return i9;
            }
        }
        throw a0.c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int H(int i8, byte[] bArr, int i9, a aVar) {
        int i10;
        int i11;
        int i12 = i8 & 127;
        int i13 = i9 + 1;
        byte b8 = bArr[i9];
        if (b8 < 0) {
            int i14 = i12 | ((b8 & Byte.MAX_VALUE) << 7);
            int i15 = i13 + 1;
            byte b9 = bArr[i13];
            if (b9 >= 0) {
                i10 = b9 << 14;
            } else {
                i12 = i14 | ((b9 & Byte.MAX_VALUE) << 14);
                i13 = i15 + 1;
                byte b10 = bArr[i15];
                if (b10 >= 0) {
                    i11 = b10 << 21;
                } else {
                    i14 = i12 | ((b10 & Byte.MAX_VALUE) << 21);
                    i15 = i13 + 1;
                    byte b11 = bArr[i13];
                    if (b11 >= 0) {
                        i10 = b11 << 28;
                    } else {
                        int i16 = i14 | ((b11 & Byte.MAX_VALUE) << 28);
                        while (true) {
                            int i17 = i15 + 1;
                            if (bArr[i15] >= 0) {
                                aVar.f6062a = i16;
                                return i17;
                            }
                            i15 = i17;
                        }
                    }
                }
            }
            aVar.f6062a = i14 | i10;
            return i15;
        }
        i11 = b8 << 7;
        aVar.f6062a = i12 | i11;
        return i13;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int I(byte[] bArr, int i8, a aVar) {
        int i9 = i8 + 1;
        byte b8 = bArr[i8];
        if (b8 >= 0) {
            aVar.f6062a = b8;
            return i9;
        }
        return H(b8, bArr, i9, aVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int J(int i8, byte[] bArr, int i9, int i10, z.d dVar, a aVar) {
        y yVar = (y) dVar;
        int I = I(bArr, i9, aVar);
        while (true) {
            yVar.f(aVar.f6062a);
            if (I >= i10) {
                break;
            }
            int I2 = I(bArr, I, aVar);
            if (i8 != aVar.f6062a) {
                break;
            }
            I = I(bArr, I2, aVar);
        }
        return I;
    }

    static int K(long j8, byte[] bArr, int i8, a aVar) {
        int i9 = i8 + 1;
        byte b8 = bArr[i8];
        long j9 = (j8 & 127) | ((b8 & Byte.MAX_VALUE) << 7);
        int i10 = 7;
        while (b8 < 0) {
            int i11 = i9 + 1;
            byte b9 = bArr[i9];
            i10 += 7;
            j9 |= (b9 & Byte.MAX_VALUE) << i10;
            i9 = i11;
            b8 = b9;
        }
        aVar.f6063b = j9;
        return i9;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int L(byte[] bArr, int i8, a aVar) {
        int i9 = i8 + 1;
        long j8 = bArr[i8];
        if (j8 >= 0) {
            aVar.f6063b = j8;
            return i9;
        }
        return K(j8, bArr, i9, aVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int M(int i8, byte[] bArr, int i9, int i10, z.d dVar, a aVar) {
        g0 g0Var = (g0) dVar;
        int L = L(bArr, i9, aVar);
        while (true) {
            g0Var.f(aVar.f6063b);
            if (L >= i10) {
                break;
            }
            int I = I(bArr, L, aVar);
            if (i8 != aVar.f6062a) {
                break;
            }
            L = L(bArr, I, aVar);
        }
        return L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int N(Object obj, d1 d1Var, byte[] bArr, int i8, int i9, int i10, a aVar) {
        s0 s0Var = (s0) d1Var;
        int i11 = aVar.f6066e + 1;
        aVar.f6066e = i11;
        a(i11);
        int c02 = s0Var.c0(obj, bArr, i8, i9, i10, aVar);
        aVar.f6066e--;
        aVar.f6064c = obj;
        return c02;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int O(Object obj, d1 d1Var, byte[] bArr, int i8, int i9, a aVar) {
        int i10 = i8 + 1;
        int i11 = bArr[i8];
        if (i11 < 0) {
            i10 = H(i11, bArr, i10, aVar);
            i11 = aVar.f6062a;
        }
        int i12 = i10;
        if (i11 < 0 || i11 > i9 - i12) {
            throw a0.m();
        }
        int i13 = aVar.f6066e + 1;
        aVar.f6066e = i13;
        a(i13);
        int i14 = i11 + i12;
        d1Var.b(obj, bArr, i12, i14, aVar);
        aVar.f6066e--;
        aVar.f6064c = obj;
        return i14;
    }

    private static void a(int i8) {
        if (i8 >= f6061a) {
            throw a0.i();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0021, code lost:
        r9 = L(r8, r0, r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0029, code lost:
        if (r12.f6063b == 0) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002c, code lost:
        return r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x000e, code lost:
        if (r12.f6063b != 0) goto L3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0010, code lost:
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0012, code lost:
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0013, code lost:
        r11.f(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0016, code lost:
        if (r9 >= r10) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0018, code lost:
        r0 = I(r8, r9, r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001e, code lost:
        if (r7 == r12.f6062a) goto L8;
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x0029 -> B:4:0x0010). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:5:0x0012 -> B:6:0x0013). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int b(int r7, byte[] r8, int r9, int r10, com.google.crypto.tink.shaded.protobuf.z.d r11, com.google.crypto.tink.shaded.protobuf.e.a r12) {
        /*
            com.google.crypto.tink.shaded.protobuf.f r11 = (com.google.crypto.tink.shaded.protobuf.f) r11
            int r9 = L(r8, r9, r12)
            long r0 = r12.f6063b
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 1
            r4 = 0
            if (r0 == 0) goto L12
        L10:
            r0 = r1
            goto L13
        L12:
            r0 = r4
        L13:
            r11.f(r0)
            if (r9 >= r10) goto L2c
            int r0 = I(r8, r9, r12)
            int r5 = r12.f6062a
            if (r7 == r5) goto L21
            goto L2c
        L21:
            int r9 = L(r8, r0, r12)
            long r5 = r12.f6063b
            int r0 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r0 == 0) goto L12
            goto L10
        L2c:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.e.b(int, byte[], int, int, com.google.crypto.tink.shaded.protobuf.z$d, com.google.crypto.tink.shaded.protobuf.e$a):int");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int c(byte[] bArr, int i8, a aVar) {
        int I = I(bArr, i8, aVar);
        int i9 = aVar.f6062a;
        if (i9 >= 0) {
            if (i9 <= bArr.length - I) {
                if (i9 == 0) {
                    aVar.f6064c = h.f6072f;
                    return I;
                }
                aVar.f6064c = h.m(bArr, I, i9);
                return I + i9;
            }
            throw a0.m();
        }
        throw a0.g();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x001e, code lost:
        r0 = I(r3, r4, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0024, code lost:
        if (r2 == r7.f6062a) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0027, code lost:
        r4 = I(r3, r0, r7);
        r0 = r7.f6062a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002d, code lost:
        if (r0 < 0) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0031, code lost:
        if (r0 > (r3.length - r4)) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0033, code lost:
        if (r0 != 0) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003a, code lost:
        throw com.google.crypto.tink.shaded.protobuf.a0.m();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x003f, code lost:
        throw com.google.crypto.tink.shaded.protobuf.a0.g();
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0040, code lost:
        return r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x000c, code lost:
        if (r0 == 0) goto L6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x000e, code lost:
        r6.add(com.google.crypto.tink.shaded.protobuf.h.f6072f);
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0014, code lost:
        r6.add(com.google.crypto.tink.shaded.protobuf.h.m(r3, r4, r0));
        r4 = r4 + r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r4 >= r5) goto L20;
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0033 -> B:7:0x000e). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:8:0x0014 -> B:9:0x001c). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int d(int r2, byte[] r3, int r4, int r5, com.google.crypto.tink.shaded.protobuf.z.d r6, com.google.crypto.tink.shaded.protobuf.e.a r7) {
        /*
            int r4 = I(r3, r4, r7)
            int r0 = r7.f6062a
            if (r0 < 0) goto L46
            int r1 = r3.length
            int r1 = r1 - r4
            if (r0 > r1) goto L41
            if (r0 != 0) goto L14
        Le:
            com.google.crypto.tink.shaded.protobuf.h r0 = com.google.crypto.tink.shaded.protobuf.h.f6072f
            r6.add(r0)
            goto L1c
        L14:
            com.google.crypto.tink.shaded.protobuf.h r1 = com.google.crypto.tink.shaded.protobuf.h.m(r3, r4, r0)
            r6.add(r1)
            int r4 = r4 + r0
        L1c:
            if (r4 >= r5) goto L40
            int r0 = I(r3, r4, r7)
            int r1 = r7.f6062a
            if (r2 == r1) goto L27
            goto L40
        L27:
            int r4 = I(r3, r0, r7)
            int r0 = r7.f6062a
            if (r0 < 0) goto L3b
            int r1 = r3.length
            int r1 = r1 - r4
            if (r0 > r1) goto L36
            if (r0 != 0) goto L14
            goto Le
        L36:
            com.google.crypto.tink.shaded.protobuf.a0 r2 = com.google.crypto.tink.shaded.protobuf.a0.m()
            throw r2
        L3b:
            com.google.crypto.tink.shaded.protobuf.a0 r2 = com.google.crypto.tink.shaded.protobuf.a0.g()
            throw r2
        L40:
            return r4
        L41:
            com.google.crypto.tink.shaded.protobuf.a0 r2 = com.google.crypto.tink.shaded.protobuf.a0.m()
            throw r2
        L46:
            com.google.crypto.tink.shaded.protobuf.a0 r2 = com.google.crypto.tink.shaded.protobuf.a0.g()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.e.d(int, byte[], int, int, com.google.crypto.tink.shaded.protobuf.z$d, com.google.crypto.tink.shaded.protobuf.e$a):int");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double e(byte[] bArr, int i8) {
        return Double.longBitsToDouble(j(bArr, i8));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int f(int i8, byte[] bArr, int i9, int i10, z.d dVar, a aVar) {
        m mVar = (m) dVar;
        mVar.f(e(bArr, i9));
        int i11 = i9 + 8;
        while (i11 < i10) {
            int I = I(bArr, i11, aVar);
            if (i8 != aVar.f6062a) {
                break;
            }
            mVar.f(e(bArr, I));
            i11 = I + 8;
        }
        return i11;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int g(int i8, byte[] bArr, int i9, int i10, Object obj, p0 p0Var, j1 j1Var, a aVar) {
        aVar.f6065d.a(p0Var, i8 >>> 3);
        return G(i8, bArr, i9, i10, s0.v(obj), aVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int h(byte[] bArr, int i8) {
        return ((bArr[i8 + 3] & 255) << 24) | (bArr[i8] & 255) | ((bArr[i8 + 1] & 255) << 8) | ((bArr[i8 + 2] & 255) << 16);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int i(int i8, byte[] bArr, int i9, int i10, z.d dVar, a aVar) {
        y yVar = (y) dVar;
        yVar.f(h(bArr, i9));
        int i11 = i9 + 4;
        while (i11 < i10) {
            int I = I(bArr, i11, aVar);
            if (i8 != aVar.f6062a) {
                break;
            }
            yVar.f(h(bArr, I));
            i11 = I + 4;
        }
        return i11;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long j(byte[] bArr, int i8) {
        return ((bArr[i8 + 7] & 255) << 56) | (bArr[i8] & 255) | ((bArr[i8 + 1] & 255) << 8) | ((bArr[i8 + 2] & 255) << 16) | ((bArr[i8 + 3] & 255) << 24) | ((bArr[i8 + 4] & 255) << 32) | ((bArr[i8 + 5] & 255) << 40) | ((bArr[i8 + 6] & 255) << 48);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int k(int i8, byte[] bArr, int i9, int i10, z.d dVar, a aVar) {
        g0 g0Var = (g0) dVar;
        g0Var.f(j(bArr, i9));
        int i11 = i9 + 8;
        while (i11 < i10) {
            int I = I(bArr, i11, aVar);
            if (i8 != aVar.f6062a) {
                break;
            }
            g0Var.f(j(bArr, I));
            i11 = I + 8;
        }
        return i11;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float l(byte[] bArr, int i8) {
        return Float.intBitsToFloat(h(bArr, i8));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int m(int i8, byte[] bArr, int i9, int i10, z.d dVar, a aVar) {
        v vVar = (v) dVar;
        vVar.f(l(bArr, i9));
        int i11 = i9 + 4;
        while (i11 < i10) {
            int I = I(bArr, i11, aVar);
            if (i8 != aVar.f6062a) {
                break;
            }
            vVar.f(l(bArr, I));
            i11 = I + 4;
        }
        return i11;
    }

    static int n(d1 d1Var, byte[] bArr, int i8, int i9, int i10, a aVar) {
        Object i11 = d1Var.i();
        int N = N(i11, d1Var, bArr, i8, i9, i10, aVar);
        d1Var.e(i11);
        aVar.f6064c = i11;
        return N;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int o(d1 d1Var, int i8, byte[] bArr, int i9, int i10, z.d dVar, a aVar) {
        int i11 = (i8 & (-8)) | 4;
        int n8 = n(d1Var, bArr, i9, i10, i11, aVar);
        while (true) {
            dVar.add(aVar.f6064c);
            if (n8 >= i10) {
                break;
            }
            int I = I(bArr, n8, aVar);
            if (i8 != aVar.f6062a) {
                break;
            }
            n8 = n(d1Var, bArr, I, i10, i11, aVar);
        }
        return n8;
    }

    static int p(d1 d1Var, byte[] bArr, int i8, int i9, a aVar) {
        Object i10 = d1Var.i();
        int O = O(i10, d1Var, bArr, i8, i9, aVar);
        d1Var.e(i10);
        aVar.f6064c = i10;
        return O;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int q(d1 d1Var, int i8, byte[] bArr, int i9, int i10, z.d dVar, a aVar) {
        int p8 = p(d1Var, bArr, i9, i10, aVar);
        while (true) {
            dVar.add(aVar.f6064c);
            if (p8 >= i10) {
                break;
            }
            int I = I(bArr, p8, aVar);
            if (i8 != aVar.f6062a) {
                break;
            }
            p8 = p(d1Var, bArr, I, i10, aVar);
        }
        return p8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int r(byte[] bArr, int i8, z.d dVar, a aVar) {
        f fVar = (f) dVar;
        int I = I(bArr, i8, aVar);
        int i9 = aVar.f6062a + I;
        while (I < i9) {
            I = L(bArr, I, aVar);
            fVar.f(aVar.f6063b != 0);
        }
        if (I == i9) {
            return I;
        }
        throw a0.m();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int s(byte[] bArr, int i8, z.d dVar, a aVar) {
        m mVar = (m) dVar;
        int I = I(bArr, i8, aVar);
        int i9 = aVar.f6062a;
        int i10 = I + i9;
        if (i10 <= bArr.length) {
            mVar.j(mVar.size() + (i9 / 8));
            while (I < i10) {
                mVar.f(e(bArr, I));
                I += 8;
            }
            if (I == i10) {
                return I;
            }
            throw a0.m();
        }
        throw a0.m();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int t(byte[] bArr, int i8, z.d dVar, a aVar) {
        y yVar = (y) dVar;
        int I = I(bArr, i8, aVar);
        int i9 = aVar.f6062a;
        int i10 = I + i9;
        if (i10 <= bArr.length) {
            yVar.j(yVar.size() + (i9 / 4));
            while (I < i10) {
                yVar.f(h(bArr, I));
                I += 4;
            }
            if (I == i10) {
                return I;
            }
            throw a0.m();
        }
        throw a0.m();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int u(byte[] bArr, int i8, z.d dVar, a aVar) {
        g0 g0Var = (g0) dVar;
        int I = I(bArr, i8, aVar);
        int i9 = aVar.f6062a;
        int i10 = I + i9;
        if (i10 <= bArr.length) {
            g0Var.j(g0Var.size() + (i9 / 8));
            while (I < i10) {
                g0Var.f(j(bArr, I));
                I += 8;
            }
            if (I == i10) {
                return I;
            }
            throw a0.m();
        }
        throw a0.m();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int v(byte[] bArr, int i8, z.d dVar, a aVar) {
        v vVar = (v) dVar;
        int I = I(bArr, i8, aVar);
        int i9 = aVar.f6062a;
        int i10 = I + i9;
        if (i10 <= bArr.length) {
            vVar.j(vVar.size() + (i9 / 4));
            while (I < i10) {
                vVar.f(l(bArr, I));
                I += 4;
            }
            if (I == i10) {
                return I;
            }
            throw a0.m();
        }
        throw a0.m();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int w(byte[] bArr, int i8, z.d dVar, a aVar) {
        y yVar = (y) dVar;
        int I = I(bArr, i8, aVar);
        int i9 = aVar.f6062a + I;
        while (I < i9) {
            I = I(bArr, I, aVar);
            yVar.f(i.d(aVar.f6062a));
        }
        if (I == i9) {
            return I;
        }
        throw a0.m();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int x(byte[] bArr, int i8, z.d dVar, a aVar) {
        g0 g0Var = (g0) dVar;
        int I = I(bArr, i8, aVar);
        int i9 = aVar.f6062a + I;
        while (I < i9) {
            I = L(bArr, I, aVar);
            g0Var.f(i.e(aVar.f6063b));
        }
        if (I == i9) {
            return I;
        }
        throw a0.m();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int y(byte[] bArr, int i8, z.d dVar, a aVar) {
        y yVar = (y) dVar;
        int I = I(bArr, i8, aVar);
        int i9 = aVar.f6062a + I;
        while (I < i9) {
            I = I(bArr, I, aVar);
            yVar.f(aVar.f6062a);
        }
        if (I == i9) {
            return I;
        }
        throw a0.m();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int z(byte[] bArr, int i8, z.d dVar, a aVar) {
        g0 g0Var = (g0) dVar;
        int I = I(bArr, i8, aVar);
        int i9 = aVar.f6062a + I;
        while (I < i9) {
            I = L(bArr, I, aVar);
            g0Var.f(aVar.f6063b);
        }
        if (I == i9) {
            return I;
        }
        throw a0.m();
    }
}
