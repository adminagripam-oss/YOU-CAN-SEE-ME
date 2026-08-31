package com.google.crypto.tink.shaded.protobuf;

import java.nio.charset.Charset;
import java.util.Arrays;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class n1 {

    /* renamed from: a  reason: collision with root package name */
    private static final b f6163a;

    /* loaded from: classes.dex */
    private static class a {
        /* JADX INFO: Access modifiers changed from: private */
        public static void h(byte b8, byte b9, byte b10, byte b11, char[] cArr, int i8) {
            if (m(b9) || (((b8 << 28) + (b9 + 112)) >> 30) != 0 || m(b10) || m(b11)) {
                throw a0.d();
            }
            int r8 = ((b8 & 7) << 18) | (r(b9) << 12) | (r(b10) << 6) | r(b11);
            cArr[i8] = l(r8);
            cArr[i8 + 1] = q(r8);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void i(byte b8, char[] cArr, int i8) {
            cArr[i8] = (char) b8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void j(byte b8, byte b9, byte b10, char[] cArr, int i8) {
            if (m(b9) || ((b8 == -32 && b9 < -96) || ((b8 == -19 && b9 >= -96) || m(b10)))) {
                throw a0.d();
            }
            cArr[i8] = (char) (((b8 & 15) << 12) | (r(b9) << 6) | r(b10));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void k(byte b8, byte b9, char[] cArr, int i8) {
            if (b8 < -62 || m(b9)) {
                throw a0.d();
            }
            cArr[i8] = (char) (((b8 & 31) << 6) | r(b9));
        }

        private static char l(int i8) {
            return (char) ((i8 >>> 10) + 55232);
        }

        private static boolean m(byte b8) {
            return b8 > -65;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean n(byte b8) {
            return b8 >= 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean o(byte b8) {
            return b8 < -16;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean p(byte b8) {
            return b8 < -32;
        }

        private static char q(int i8) {
            return (char) ((i8 & 1023) + 56320);
        }

        private static int r(byte b8) {
            return b8 & 63;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class b {
        b() {
        }

        abstract String a(byte[] bArr, int i8, int i9);

        abstract int b(String str, byte[] bArr, int i8, int i9);

        final boolean c(byte[] bArr, int i8, int i9) {
            return d(0, bArr, i8, i9) == 0;
        }

        abstract int d(int i8, byte[] bArr, int i9, int i10);
    }

    /* loaded from: classes.dex */
    static final class c extends b {
        c() {
        }

        private static int e(byte[] bArr, int i8, int i9) {
            while (i8 < i9 && bArr[i8] >= 0) {
                i8++;
            }
            if (i8 >= i9) {
                return 0;
            }
            return f(bArr, i8, i9);
        }

        private static int f(byte[] bArr, int i8, int i9) {
            while (i8 < i9) {
                int i10 = i8 + 1;
                byte b8 = bArr[i8];
                if (b8 < 0) {
                    if (b8 < -32) {
                        if (i10 >= i9) {
                            return b8;
                        }
                        if (b8 >= -62) {
                            i8 = i10 + 1;
                            if (bArr[i10] > -65) {
                            }
                        }
                        return -1;
                    } else if (b8 >= -16) {
                        if (i10 >= i9 - 2) {
                            return n1.l(bArr, i10, i9);
                        }
                        int i11 = i10 + 1;
                        byte b9 = bArr[i10];
                        if (b9 <= -65 && (((b8 << 28) + (b9 + 112)) >> 30) == 0) {
                            int i12 = i11 + 1;
                            if (bArr[i11] <= -65) {
                                i10 = i12 + 1;
                                if (bArr[i12] > -65) {
                                }
                            }
                        }
                        return -1;
                    } else if (i10 >= i9 - 1) {
                        return n1.l(bArr, i10, i9);
                    } else {
                        int i13 = i10 + 1;
                        byte b10 = bArr[i10];
                        if (b10 <= -65 && ((b8 != -32 || b10 >= -96) && (b8 != -19 || b10 < -96))) {
                            i8 = i13 + 1;
                            if (bArr[i13] > -65) {
                            }
                        }
                        return -1;
                    }
                }
                i8 = i10;
            }
            return 0;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.n1.b
        String a(byte[] bArr, int i8, int i9) {
            if ((i8 | i9 | ((bArr.length - i8) - i9)) >= 0) {
                int i10 = i8 + i9;
                char[] cArr = new char[i9];
                int i11 = 0;
                while (i8 < i10) {
                    byte b8 = bArr[i8];
                    if (!a.n(b8)) {
                        break;
                    }
                    i8++;
                    a.i(b8, cArr, i11);
                    i11++;
                }
                int i12 = i11;
                while (i8 < i10) {
                    int i13 = i8 + 1;
                    byte b9 = bArr[i8];
                    if (a.n(b9)) {
                        int i14 = i12 + 1;
                        a.i(b9, cArr, i12);
                        while (i13 < i10) {
                            byte b10 = bArr[i13];
                            if (!a.n(b10)) {
                                break;
                            }
                            i13++;
                            a.i(b10, cArr, i14);
                            i14++;
                        }
                        i8 = i13;
                        i12 = i14;
                    } else if (a.p(b9)) {
                        if (i13 >= i10) {
                            throw a0.d();
                        }
                        a.k(b9, bArr[i13], cArr, i12);
                        i8 = i13 + 1;
                        i12++;
                    } else if (a.o(b9)) {
                        if (i13 >= i10 - 1) {
                            throw a0.d();
                        }
                        int i15 = i13 + 1;
                        a.j(b9, bArr[i13], bArr[i15], cArr, i12);
                        i8 = i15 + 1;
                        i12++;
                    } else if (i13 >= i10 - 2) {
                        throw a0.d();
                    } else {
                        int i16 = i13 + 1;
                        byte b11 = bArr[i13];
                        int i17 = i16 + 1;
                        a.h(b9, b11, bArr[i16], bArr[i17], cArr, i12);
                        i8 = i17 + 1;
                        i12 = i12 + 1 + 1;
                    }
                }
                return new String(cArr, 0, i12);
            }
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArr.length), Integer.valueOf(i8), Integer.valueOf(i9)));
        }

        /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
            return r10 + r0;
         */
        @Override // com.google.crypto.tink.shaded.protobuf.n1.b
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        int b(java.lang.String r8, byte[] r9, int r10, int r11) {
            /*
                Method dump skipped, instructions count: 254
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.n1.c.b(java.lang.String, byte[], int, int):int");
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0015, code lost:
            if (r8[r9] > (-65)) goto L12;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x0046, code lost:
            if (r8[r9] > (-65)) goto L31;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x0083, code lost:
            if (r8[r7] > (-65)) goto L51;
         */
        @Override // com.google.crypto.tink.shaded.protobuf.n1.b
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        int d(int r7, byte[] r8, int r9, int r10) {
            /*
                r6 = this;
                if (r7 == 0) goto L86
                if (r9 < r10) goto L5
                return r7
            L5:
                byte r0 = (byte) r7
                r1 = -32
                r2 = -1
                r3 = -65
                if (r0 >= r1) goto L1c
                r7 = -62
                if (r0 < r7) goto L1b
                int r7 = r9 + 1
                r9 = r8[r9]
                if (r9 <= r3) goto L18
                goto L1b
            L18:
                r9 = r7
                goto L86
            L1b:
                return r2
            L1c:
                r4 = -16
                if (r0 >= r4) goto L49
                int r7 = r7 >> 8
                int r7 = ~r7
                byte r7 = (byte) r7
                if (r7 != 0) goto L34
                int r7 = r9 + 1
                r9 = r8[r9]
                if (r7 < r10) goto L31
                int r7 = com.google.crypto.tink.shaded.protobuf.n1.a(r0, r9)
                return r7
            L31:
                r5 = r9
                r9 = r7
                r7 = r5
            L34:
                if (r7 > r3) goto L48
                r4 = -96
                if (r0 != r1) goto L3c
                if (r7 < r4) goto L48
            L3c:
                r1 = -19
                if (r0 != r1) goto L42
                if (r7 >= r4) goto L48
            L42:
                int r7 = r9 + 1
                r9 = r8[r9]
                if (r9 <= r3) goto L18
            L48:
                return r2
            L49:
                int r1 = r7 >> 8
                int r1 = ~r1
                byte r1 = (byte) r1
                if (r1 != 0) goto L5c
                int r7 = r9 + 1
                r1 = r8[r9]
                if (r7 < r10) goto L5a
                int r7 = com.google.crypto.tink.shaded.protobuf.n1.a(r0, r1)
                return r7
            L5a:
                r9 = 0
                goto L62
            L5c:
                int r7 = r7 >> 16
                byte r7 = (byte) r7
                r5 = r9
                r9 = r7
                r7 = r5
            L62:
                if (r9 != 0) goto L72
                int r9 = r7 + 1
                r7 = r8[r7]
                if (r9 < r10) goto L6f
                int r7 = com.google.crypto.tink.shaded.protobuf.n1.b(r0, r1, r7)
                return r7
            L6f:
                r5 = r9
                r9 = r7
                r7 = r5
            L72:
                if (r1 > r3) goto L85
                int r0 = r0 << 28
                int r1 = r1 + 112
                int r0 = r0 + r1
                int r0 = r0 >> 30
                if (r0 != 0) goto L85
                if (r9 > r3) goto L85
                int r9 = r7 + 1
                r7 = r8[r7]
                if (r7 <= r3) goto L86
            L85:
                return r2
            L86:
                int r7 = e(r8, r9, r10)
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.n1.c.d(int, byte[], int, int):int");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class d extends IllegalArgumentException {
        d(int i8, int i9) {
            super("Unpaired surrogate at index " + i8 + " of " + i9);
        }
    }

    /* loaded from: classes.dex */
    static final class e extends b {
        e() {
        }

        static boolean e() {
            return m1.E() && m1.F();
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x0039, code lost:
            return -1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x008e, code lost:
            return -1;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private static int f(byte[] r8, long r9, int r11) {
            /*
                int r0 = g(r8, r9, r11)
                int r11 = r11 - r0
                long r0 = (long) r0
                long r9 = r9 + r0
            L7:
                r0 = 0
                r1 = r0
            L9:
                r2 = 1
                if (r11 <= 0) goto L1a
                long r4 = r9 + r2
                byte r1 = com.google.crypto.tink.shaded.protobuf.m1.u(r8, r9)
                if (r1 < 0) goto L19
                int r11 = r11 + (-1)
                r9 = r4
                goto L9
            L19:
                r9 = r4
            L1a:
                if (r11 != 0) goto L1d
                return r0
            L1d:
                int r11 = r11 + (-1)
                r0 = -32
                r4 = -65
                r5 = -1
                if (r1 >= r0) goto L3a
                if (r11 != 0) goto L29
                return r1
            L29:
                int r11 = r11 + (-1)
                r0 = -62
                if (r1 < r0) goto L39
                long r2 = r2 + r9
                byte r9 = com.google.crypto.tink.shaded.protobuf.m1.u(r8, r9)
                if (r9 <= r4) goto L37
                goto L39
            L37:
                r9 = r2
                goto L7
            L39:
                return r5
            L3a:
                r6 = -16
                if (r1 >= r6) goto L64
                r6 = 2
                if (r11 >= r6) goto L46
                int r8 = h(r8, r1, r9, r11)
                return r8
            L46:
                int r11 = r11 + (-2)
                long r6 = r9 + r2
                byte r9 = com.google.crypto.tink.shaded.protobuf.m1.u(r8, r9)
                if (r9 > r4) goto L63
                r10 = -96
                if (r1 != r0) goto L56
                if (r9 < r10) goto L63
            L56:
                r0 = -19
                if (r1 != r0) goto L5c
                if (r9 >= r10) goto L63
            L5c:
                long r2 = r2 + r6
                byte r9 = com.google.crypto.tink.shaded.protobuf.m1.u(r8, r6)
                if (r9 <= r4) goto L37
            L63:
                return r5
            L64:
                r0 = 3
                if (r11 >= r0) goto L6c
                int r8 = h(r8, r1, r9, r11)
                return r8
            L6c:
                int r11 = r11 + (-3)
                long r6 = r9 + r2
                byte r9 = com.google.crypto.tink.shaded.protobuf.m1.u(r8, r9)
                if (r9 > r4) goto L8e
                int r10 = r1 << 28
                int r9 = r9 + 112
                int r10 = r10 + r9
                int r9 = r10 >> 30
                if (r9 != 0) goto L8e
                long r9 = r6 + r2
                byte r0 = com.google.crypto.tink.shaded.protobuf.m1.u(r8, r6)
                if (r0 > r4) goto L8e
                long r2 = r2 + r9
                byte r9 = com.google.crypto.tink.shaded.protobuf.m1.u(r8, r9)
                if (r9 <= r4) goto L37
            L8e:
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.n1.e.f(byte[], long, int):int");
        }

        private static int g(byte[] bArr, long j8, int i8) {
            int i9 = 0;
            if (i8 < 16) {
                return 0;
            }
            int i10 = 8 - (((int) j8) & 7);
            while (i9 < i10) {
                long j9 = 1 + j8;
                if (m1.u(bArr, j8) < 0) {
                    return i9;
                }
                i9++;
                j8 = j9;
            }
            while (true) {
                int i11 = i9 + 8;
                if (i11 > i8 || (m1.A(bArr, m1.f6146h + j8) & (-9187201950435737472L)) != 0) {
                    break;
                }
                j8 += 8;
                i9 = i11;
            }
            while (i9 < i8) {
                long j10 = j8 + 1;
                if (m1.u(bArr, j8) < 0) {
                    return i9;
                }
                i9++;
                j8 = j10;
            }
            return i8;
        }

        private static int h(byte[] bArr, int i8, long j8, int i9) {
            if (i9 != 0) {
                if (i9 != 1) {
                    if (i9 == 2) {
                        return n1.k(i8, m1.u(bArr, j8), m1.u(bArr, j8 + 1));
                    }
                    throw new AssertionError();
                }
                return n1.j(i8, m1.u(bArr, j8));
            }
            return n1.i(i8);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.n1.b
        String a(byte[] bArr, int i8, int i9) {
            Charset charset = z.f6293b;
            String str = new String(bArr, i8, i9, charset);
            if (str.indexOf(65533) >= 0 && !Arrays.equals(str.getBytes(charset), Arrays.copyOfRange(bArr, i8, i9 + i8))) {
                throw a0.d();
            }
            return str;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.n1.b
        int b(String str, byte[] bArr, int i8, int i9) {
            char c8;
            long j8;
            long j9;
            long j10;
            char c9;
            int i10;
            char charAt;
            long j11 = i8;
            long j12 = i9 + j11;
            int length = str.length();
            if (length > i9 || bArr.length - i9 < i8) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + str.charAt(length - 1) + " at index " + (i8 + i9));
            }
            int i11 = 0;
            while (true) {
                c8 = 128;
                j8 = 1;
                if (i11 >= length || (charAt = str.charAt(i11)) >= 128) {
                    break;
                }
                m1.K(bArr, j11, (byte) charAt);
                i11++;
                j11 = 1 + j11;
            }
            if (i11 == length) {
                return (int) j11;
            }
            while (i11 < length) {
                char charAt2 = str.charAt(i11);
                if (charAt2 < c8 && j11 < j12) {
                    long j13 = j11 + j8;
                    m1.K(bArr, j11, (byte) charAt2);
                    j10 = j8;
                    j9 = j13;
                    c9 = c8;
                } else if (charAt2 < 2048 && j11 <= j12 - 2) {
                    long j14 = j11 + j8;
                    m1.K(bArr, j11, (byte) ((charAt2 >>> 6) | 960));
                    long j15 = j14 + j8;
                    m1.K(bArr, j14, (byte) ((charAt2 & '?') | 128));
                    long j16 = j8;
                    c9 = 128;
                    j9 = j15;
                    j10 = j16;
                } else if ((charAt2 >= 55296 && 57343 >= charAt2) || j11 > j12 - 3) {
                    if (j11 > j12 - 4) {
                        if (55296 > charAt2 || charAt2 > 57343 || ((i10 = i11 + 1) != length && Character.isSurrogatePair(charAt2, str.charAt(i10)))) {
                            throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + j11);
                        }
                        throw new d(i11, length);
                    }
                    int i12 = i11 + 1;
                    if (i12 != length) {
                        char charAt3 = str.charAt(i12);
                        if (Character.isSurrogatePair(charAt2, charAt3)) {
                            int codePoint = Character.toCodePoint(charAt2, charAt3);
                            long j17 = j11 + 1;
                            m1.K(bArr, j11, (byte) ((codePoint >>> 18) | 240));
                            long j18 = j17 + 1;
                            c9 = 128;
                            m1.K(bArr, j17, (byte) (((codePoint >>> 12) & 63) | 128));
                            long j19 = j18 + 1;
                            m1.K(bArr, j18, (byte) (((codePoint >>> 6) & 63) | 128));
                            j10 = 1;
                            j9 = j19 + 1;
                            m1.K(bArr, j19, (byte) ((codePoint & 63) | 128));
                            i11 = i12;
                        } else {
                            i11 = i12;
                        }
                    }
                    throw new d(i11 - 1, length);
                } else {
                    long j20 = j11 + j8;
                    m1.K(bArr, j11, (byte) ((charAt2 >>> '\f') | 480));
                    long j21 = j20 + j8;
                    m1.K(bArr, j20, (byte) (((charAt2 >>> 6) & 63) | 128));
                    m1.K(bArr, j21, (byte) ((charAt2 & '?') | 128));
                    j9 = j21 + 1;
                    j10 = 1;
                    c9 = 128;
                }
                i11++;
                c8 = c9;
                long j22 = j10;
                j11 = j9;
                j8 = j22;
            }
            return (int) j11;
        }

        /* JADX WARN: Code restructure failed: missing block: B:35:0x0058, code lost:
            if (com.google.crypto.tink.shaded.protobuf.m1.u(r12, r0) > (-65)) goto L36;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x009e, code lost:
            if (com.google.crypto.tink.shaded.protobuf.m1.u(r12, r0) > (-65)) goto L56;
         */
        @Override // com.google.crypto.tink.shaded.protobuf.n1.b
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        int d(int r11, byte[] r12, int r13, int r14) {
            /*
                Method dump skipped, instructions count: 197
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.n1.e.d(int, byte[], int, int):int");
        }
    }

    static {
        f6163a = (!e.e() || com.google.crypto.tink.shaded.protobuf.d.c()) ? new c() : new e();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String e(byte[] bArr, int i8, int i9) {
        return f6163a.a(bArr, i8, i9);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int f(String str, byte[] bArr, int i8, int i9) {
        return f6163a.b(str, bArr, i8, i9);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int g(String str) {
        int length = str.length();
        int i8 = 0;
        while (i8 < length && str.charAt(i8) < 128) {
            i8++;
        }
        int i9 = length;
        while (true) {
            if (i8 < length) {
                char charAt = str.charAt(i8);
                if (charAt >= 2048) {
                    i9 += h(str, i8);
                    break;
                }
                i9 += (127 - charAt) >>> 31;
                i8++;
            } else {
                break;
            }
        }
        if (i9 >= length) {
            return i9;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (i9 + 4294967296L));
    }

    private static int h(String str, int i8) {
        int length = str.length();
        int i9 = 0;
        while (i8 < length) {
            char charAt = str.charAt(i8);
            if (charAt < 2048) {
                i9 += (127 - charAt) >>> 31;
            } else {
                i9 += 2;
                if (55296 <= charAt && charAt <= 57343) {
                    if (Character.codePointAt(str, i8) < 65536) {
                        throw new d(i8, length);
                    }
                    i8++;
                }
            }
            i8++;
        }
        return i9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int i(int i8) {
        if (i8 > -12) {
            return -1;
        }
        return i8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int j(int i8, int i9) {
        if (i8 > -12 || i9 > -65) {
            return -1;
        }
        return i8 ^ (i9 << 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int k(int i8, int i9, int i10) {
        if (i8 > -12 || i9 > -65 || i10 > -65) {
            return -1;
        }
        return (i8 ^ (i9 << 8)) ^ (i10 << 16);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int l(byte[] bArr, int i8, int i9) {
        byte b8 = bArr[i8 - 1];
        int i10 = i9 - i8;
        if (i10 != 0) {
            if (i10 != 1) {
                if (i10 == 2) {
                    return k(b8, bArr[i8], bArr[i8 + 1]);
                }
                throw new AssertionError();
            }
            return j(b8, bArr[i8]);
        }
        return i(b8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean m(byte[] bArr, int i8, int i9) {
        return f6163a.c(bArr, i8, i9);
    }
}
