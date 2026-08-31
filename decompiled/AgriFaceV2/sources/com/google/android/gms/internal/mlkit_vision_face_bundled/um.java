package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class um {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int f5678a = 0;

    static {
        if (rm.C() && rm.D()) {
            int i8 = aj.f5130a;
        }
    }

    static /* bridge */ /* synthetic */ int a(byte[] bArr, int i8, int i9) {
        int i10 = i9 - i8;
        byte b8 = bArr[i8 - 1];
        if (i10 == 0) {
            if (b8 <= -12) {
                return b8;
            }
            return -1;
        } else if (i10 == 1) {
            byte b9 = bArr[i8];
            if (b8 > -12 || b9 > -65) {
                return -1;
            }
            return (b9 << 8) ^ b8;
        } else if (i10 == 2) {
            byte b10 = bArr[i8];
            byte b11 = bArr[i8 + 1];
            if (b8 > -12 || b10 > -65 || b11 > -65) {
                return -1;
            }
            return (b11 << 16) ^ ((b10 << 8) ^ b8);
        } else {
            throw new AssertionError();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0101, code lost:
        return r10 + r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int b(java.lang.String r8, byte[] r9, int r10, int r11) {
        /*
            Method dump skipped, instructions count: 258
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_face_bundled.um.b(java.lang.String, byte[], int, int):int");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int c(String str) {
        int length = str.length();
        int i8 = 0;
        int i9 = 0;
        while (i9 < length && str.charAt(i9) < 128) {
            i9++;
        }
        int i10 = length;
        while (true) {
            if (i9 >= length) {
                break;
            }
            char charAt = str.charAt(i9);
            if (charAt < 2048) {
                i10 += (127 - charAt) >>> 31;
                i9++;
            } else {
                int length2 = str.length();
                while (i9 < length2) {
                    char charAt2 = str.charAt(i9);
                    if (charAt2 < 2048) {
                        i8 += (127 - charAt2) >>> 31;
                    } else {
                        i8 += 2;
                        if (charAt2 >= 55296 && charAt2 <= 57343) {
                            if (Character.codePointAt(str, i9) < 65536) {
                                throw new tm(i9, length2);
                            }
                            i9++;
                        }
                    }
                    i9++;
                }
                i10 += i8;
            }
        }
        if (i10 >= length) {
            return i10;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (i10 + 4294967296L));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0072 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0076 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean d(byte[] r5, int r6, int r7) {
        /*
        L0:
            if (r6 >= r7) goto L9
            r0 = r5[r6]
            if (r0 < 0) goto L9
            int r6 = r6 + 1
            goto L0
        L9:
            if (r6 < r7) goto Ld
            goto L76
        Ld:
            if (r6 >= r7) goto L76
            int r0 = r6 + 1
            r6 = r5[r6]
            if (r6 >= 0) goto L74
            r1 = -32
            r2 = -65
            if (r6 >= r1) goto L29
            if (r0 < r7) goto L1e
            goto L53
        L1e:
            r1 = -62
            if (r6 < r1) goto L72
            int r6 = r0 + 1
            r0 = r5[r0]
            if (r0 <= r2) goto Ld
            goto L72
        L29:
            r3 = -16
            if (r6 >= r3) goto L4b
            int r3 = r7 + (-1)
            if (r0 < r3) goto L32
            goto L4f
        L32:
            int r3 = r0 + 1
            r0 = r5[r0]
            if (r0 > r2) goto L72
            r4 = -96
            if (r6 != r1) goto L3e
            if (r0 < r4) goto L72
        L3e:
            r1 = -19
            if (r6 != r1) goto L44
            if (r0 >= r4) goto L72
        L44:
            int r6 = r3 + 1
            r0 = r5[r3]
            if (r0 <= r2) goto Ld
            goto L72
        L4b:
            int r1 = r7 + (-2)
            if (r0 < r1) goto L56
        L4f:
            int r6 = a(r5, r0, r7)
        L53:
            if (r6 == 0) goto L76
            goto L72
        L56:
            int r1 = r0 + 1
            r0 = r5[r0]
            if (r0 > r2) goto L72
            int r6 = r6 << 28
            int r0 = r0 + 112
            int r6 = r6 + r0
            int r6 = r6 >> 30
            if (r6 != 0) goto L72
            int r6 = r1 + 1
            r0 = r5[r1]
            if (r0 > r2) goto L72
            int r0 = r6 + 1
            r6 = r5[r6]
            if (r6 > r2) goto L72
            goto L74
        L72:
            r5 = 0
            return r5
        L74:
            r6 = r0
            goto Ld
        L76:
            r5 = 1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_face_bundled.um.d(byte[], int, int):boolean");
    }
}
