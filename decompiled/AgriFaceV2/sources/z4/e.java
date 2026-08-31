package z4;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
/* loaded from: classes.dex */
public abstract class e {

    /* renamed from: a  reason: collision with root package name */
    private static final Charset f14404a = Charset.forName("UTF-8");

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class a {

        /* renamed from: a  reason: collision with root package name */
        public byte[] f14405a;

        /* renamed from: b  reason: collision with root package name */
        public int f14406b;

        a() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b extends a {

        /* renamed from: f  reason: collision with root package name */
        private static final int[] f14407f = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

        /* renamed from: g  reason: collision with root package name */
        private static final int[] f14408g = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

        /* renamed from: c  reason: collision with root package name */
        private int f14409c;

        /* renamed from: d  reason: collision with root package name */
        private int f14410d;

        /* renamed from: e  reason: collision with root package name */
        private final int[] f14411e;

        public b(int i8, byte[] bArr) {
            this.f14405a = bArr;
            this.f14411e = (i8 & 8) == 0 ? f14407f : f14408g;
            this.f14409c = 0;
            this.f14410d = 0;
        }

        public boolean a(byte[] bArr, int i8, int i9, boolean z7) {
            int i10 = this.f14409c;
            if (i10 == 6) {
                return false;
            }
            int i11 = i9 + i8;
            int i12 = this.f14410d;
            byte[] bArr2 = this.f14405a;
            int[] iArr = this.f14411e;
            int i13 = 0;
            int i14 = i12;
            int i15 = i10;
            int i16 = i8;
            while (i16 < i11) {
                if (i15 == 0) {
                    while (true) {
                        int i17 = i16 + 4;
                        if (i17 > i11 || (i14 = (iArr[bArr[i16] & 255] << 18) | (iArr[bArr[i16 + 1] & 255] << 12) | (iArr[bArr[i16 + 2] & 255] << 6) | iArr[bArr[i16 + 3] & 255]) < 0) {
                            break;
                        }
                        bArr2[i13 + 2] = (byte) i14;
                        bArr2[i13 + 1] = (byte) (i14 >> 8);
                        bArr2[i13] = (byte) (i14 >> 16);
                        i13 += 3;
                        i16 = i17;
                    }
                    if (i16 >= i11) {
                        break;
                    }
                }
                int i18 = i16 + 1;
                int i19 = iArr[bArr[i16] & 255];
                if (i15 != 0) {
                    if (i15 == 1) {
                        if (i19 < 0) {
                            if (i19 != -1) {
                                this.f14409c = 6;
                                return false;
                            }
                        }
                        i19 |= i14 << 6;
                    } else if (i15 == 2) {
                        if (i19 < 0) {
                            if (i19 == -2) {
                                bArr2[i13] = (byte) (i14 >> 4);
                                i13++;
                                i15 = 4;
                            } else if (i19 != -1) {
                                this.f14409c = 6;
                                return false;
                            }
                        }
                        i19 |= i14 << 6;
                    } else if (i15 != 3) {
                        if (i15 != 4) {
                            if (i15 == 5 && i19 != -1) {
                                this.f14409c = 6;
                                return false;
                            }
                        } else if (i19 == -2) {
                            i15++;
                        } else if (i19 != -1) {
                            this.f14409c = 6;
                            return false;
                        }
                    } else if (i19 >= 0) {
                        int i20 = i19 | (i14 << 6);
                        bArr2[i13 + 2] = (byte) i20;
                        bArr2[i13 + 1] = (byte) (i20 >> 8);
                        bArr2[i13] = (byte) (i20 >> 16);
                        i13 += 3;
                        i14 = i20;
                        i15 = 0;
                    } else if (i19 == -2) {
                        bArr2[i13 + 1] = (byte) (i14 >> 2);
                        bArr2[i13] = (byte) (i14 >> 10);
                        i13 += 2;
                        i15 = 5;
                    } else if (i19 != -1) {
                        this.f14409c = 6;
                        return false;
                    }
                    i15++;
                    i14 = i19;
                } else {
                    if (i19 < 0) {
                        if (i19 != -1) {
                            this.f14409c = 6;
                            return false;
                        }
                    }
                    i15++;
                    i14 = i19;
                }
                i16 = i18;
            }
            if (!z7) {
                this.f14409c = i15;
                this.f14410d = i14;
            } else if (i15 == 1) {
                this.f14409c = 6;
                return false;
            } else {
                if (i15 == 2) {
                    bArr2[i13] = (byte) (i14 >> 4);
                    i13++;
                } else if (i15 == 3) {
                    int i21 = i13 + 1;
                    bArr2[i13] = (byte) (i14 >> 10);
                    i13 = i21 + 1;
                    bArr2[i21] = (byte) (i14 >> 2);
                } else if (i15 == 4) {
                    this.f14409c = 6;
                    return false;
                }
                this.f14409c = i15;
            }
            this.f14406b = i13;
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class c extends a {

        /* renamed from: j  reason: collision with root package name */
        private static final byte[] f14412j = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};

        /* renamed from: k  reason: collision with root package name */
        private static final byte[] f14413k = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};

        /* renamed from: c  reason: collision with root package name */
        private final byte[] f14414c;

        /* renamed from: d  reason: collision with root package name */
        int f14415d;

        /* renamed from: e  reason: collision with root package name */
        private int f14416e;

        /* renamed from: f  reason: collision with root package name */
        public final boolean f14417f;

        /* renamed from: g  reason: collision with root package name */
        public final boolean f14418g;

        /* renamed from: h  reason: collision with root package name */
        public final boolean f14419h;

        /* renamed from: i  reason: collision with root package name */
        private final byte[] f14420i;

        public c(int i8, byte[] bArr) {
            this.f14405a = bArr;
            this.f14417f = (i8 & 1) == 0;
            boolean z7 = (i8 & 2) == 0;
            this.f14418g = z7;
            this.f14419h = (i8 & 4) != 0;
            this.f14420i = (i8 & 8) == 0 ? f14412j : f14413k;
            this.f14414c = new byte[2];
            this.f14415d = 0;
            this.f14416e = z7 ? 19 : -1;
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
            */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0094  */
        /* JADX WARN: Removed duplicated region for block: B:87:0x00e6 A[SYNTHETIC] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x00dd -> B:22:0x008a). Please submit an issue!!! */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean a(byte[] r18, int r19, int r20, boolean r21) {
            /*
                Method dump skipped, instructions count: 478
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: z4.e.c.a(byte[], int, int, boolean):boolean");
        }
    }

    public static byte[] a(String str, int i8) {
        return b(str.getBytes(f14404a), i8);
    }

    public static byte[] b(byte[] bArr, int i8) {
        return c(bArr, 0, bArr.length, i8);
    }

    public static byte[] c(byte[] bArr, int i8, int i9, int i10) {
        b bVar = new b(i10, new byte[(i9 * 3) / 4]);
        if (bVar.a(bArr, i8, i9, true)) {
            int i11 = bVar.f14406b;
            byte[] bArr2 = bVar.f14405a;
            if (i11 == bArr2.length) {
                return bArr2;
            }
            byte[] bArr3 = new byte[i11];
            System.arraycopy(bArr2, 0, bArr3, 0, i11);
            return bArr3;
        }
        throw new IllegalArgumentException("bad base-64");
    }

    public static String d(byte[] bArr) {
        return g(bArr, 2);
    }

    public static byte[] e(byte[] bArr, int i8) {
        return f(bArr, 0, bArr.length, i8);
    }

    public static byte[] f(byte[] bArr, int i8, int i9, int i10) {
        c cVar = new c(i10, null);
        int i11 = (i9 / 3) * 4;
        if (!cVar.f14417f) {
            int i12 = i9 % 3;
            if (i12 == 1) {
                i11 += 2;
            } else if (i12 == 2) {
                i11 += 3;
            }
        } else if (i9 % 3 > 0) {
            i11 += 4;
        }
        if (cVar.f14418g && i9 > 0) {
            i11 += (((i9 - 1) / 57) + 1) * (cVar.f14419h ? 2 : 1);
        }
        cVar.f14405a = new byte[i11];
        cVar.a(bArr, i8, i9, true);
        return cVar.f14405a;
    }

    public static String g(byte[] bArr, int i8) {
        return new String(e(bArr, i8), StandardCharsets.US_ASCII);
    }
}
