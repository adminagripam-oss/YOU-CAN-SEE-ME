package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class cj {

    /* renamed from: a  reason: collision with root package name */
    private static volatile int f5184a = 100;

    /* renamed from: b  reason: collision with root package name */
    public static final /* synthetic */ int f5185b = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(byte[] bArr, int i8, bj bjVar) {
        int j8 = j(bArr, i8, bjVar);
        int i9 = bjVar.f5147a;
        if (i9 >= 0) {
            if (i9 <= bArr.length - j8) {
                if (i9 == 0) {
                    bjVar.f5149c = mj.f5439f;
                    return j8;
                }
                bjVar.f5149c = mj.p(bArr, j8, i9);
                return j8 + i9;
            }
            throw new wk("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        throw new wk("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b(int i8, byte[] bArr, int i9, int i10, lk lkVar, nk nkVar, jm jmVar, bj bjVar) {
        int i11;
        long j8;
        int i12;
        int d8;
        fk fkVar = lkVar.zzb;
        vm vmVar = nkVar.f5493d.f5442f;
        Object obj = null;
        if (vmVar == vm.f5866s) {
            j(bArr, i9, bjVar);
            throw null;
        }
        switch (vmVar.ordinal()) {
            case 0:
                i11 = i9 + 8;
                obj = Double.valueOf(Double.longBitsToDouble(q(bArr, i9)));
                i9 = i11;
                fkVar.g(nkVar.f5493d, obj);
                return i9;
            case 1:
                i11 = i9 + 4;
                obj = Float.valueOf(Float.intBitsToFloat(c(bArr, i9)));
                i9 = i11;
                fkVar.g(nkVar.f5493d, obj);
                return i9;
            case 2:
            case 3:
                i9 = m(bArr, i9, bjVar);
                j8 = bjVar.f5148b;
                obj = Long.valueOf(j8);
                fkVar.g(nkVar.f5493d, obj);
                return i9;
            case 4:
            case 12:
                i9 = j(bArr, i9, bjVar);
                i12 = bjVar.f5147a;
                obj = Integer.valueOf(i12);
                fkVar.g(nkVar.f5493d, obj);
                return i9;
            case 5:
            case 15:
                i11 = i9 + 8;
                obj = Long.valueOf(q(bArr, i9));
                i9 = i11;
                fkVar.g(nkVar.f5493d, obj);
                return i9;
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
            case 14:
                i11 = i9 + 4;
                obj = Integer.valueOf(c(bArr, i9));
                i9 = i11;
                fkVar.g(nkVar.f5493d, obj);
                return i9;
            case 7:
                i9 = m(bArr, i9, bjVar);
                obj = Boolean.valueOf(bjVar.f5148b != 0);
                fkVar.g(nkVar.f5493d, obj);
                return i9;
            case 8:
                i9 = h(bArr, i9, bjVar);
                obj = bjVar.f5149c;
                fkVar.g(nkVar.f5493d, obj);
                return i9;
            case 9:
                d8 = d(tl.a().b(nkVar.f5492c.getClass()), bArr, i9, i10, ((i8 >>> 3) << 3) | 4, bjVar);
                fkVar.g(nkVar.f5493d, bjVar.f5149c);
                return d8;
            case 10:
                d8 = e(tl.a().b(nkVar.f5492c.getClass()), bArr, i9, i10, bjVar);
                fkVar.g(nkVar.f5493d, bjVar.f5149c);
                return d8;
            case 11:
                i9 = a(bArr, i9, bjVar);
                obj = bjVar.f5149c;
                fkVar.g(nkVar.f5493d, obj);
                return i9;
            case 13:
                throw new IllegalStateException("Shouldn't reach here.");
            case 16:
                i9 = j(bArr, i9, bjVar);
                i12 = qj.a(bjVar.f5147a);
                obj = Integer.valueOf(i12);
                fkVar.g(nkVar.f5493d, obj);
                return i9;
            case 17:
                i9 = m(bArr, i9, bjVar);
                j8 = qj.b(bjVar.f5148b);
                obj = Long.valueOf(j8);
                fkVar.g(nkVar.f5493d, obj);
                return i9;
            default:
                fkVar.g(nkVar.f5493d, obj);
                return i9;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int c(byte[] bArr, int i8) {
        int i9 = (bArr[i8 + 1] & 255) << 8;
        return ((bArr[i8 + 3] & 255) << 24) | i9 | (bArr[i8] & 255) | ((bArr[i8 + 2] & 255) << 16);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int d(wl wlVar, byte[] bArr, int i8, int i9, int i10, bj bjVar) {
        Object c8 = wlVar.c();
        int n8 = n(c8, wlVar, bArr, i8, i9, i10, bjVar);
        wlVar.a(c8);
        bjVar.f5149c = c8;
        return n8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int e(wl wlVar, byte[] bArr, int i8, int i9, bj bjVar) {
        Object c8 = wlVar.c();
        int o8 = o(c8, wlVar, bArr, i8, i9, bjVar);
        wlVar.a(c8);
        bjVar.f5149c = c8;
        return o8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int f(wl wlVar, int i8, byte[] bArr, int i9, int i10, tk tkVar, bj bjVar) {
        int e8 = e(wlVar, bArr, i9, i10, bjVar);
        while (true) {
            tkVar.add(bjVar.f5149c);
            if (e8 >= i10) {
                break;
            }
            int j8 = j(bArr, e8, bjVar);
            if (i8 != bjVar.f5147a) {
                break;
            }
            e8 = e(wlVar, bArr, j8, i10, bjVar);
        }
        return e8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int g(byte[] bArr, int i8, tk tkVar, bj bjVar) {
        androidx.appcompat.app.w.a(tkVar);
        int j8 = j(bArr, i8, bjVar);
        int i9 = bjVar.f5147a + j8;
        if (j8 < i9) {
            j(bArr, j8, bjVar);
            throw null;
        } else if (j8 == i9) {
            return j8;
        } else {
            throw new wk("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int h(byte[] bArr, int i8, bj bjVar) {
        int j8 = j(bArr, i8, bjVar);
        int i9 = bjVar.f5147a;
        if (i9 >= 0) {
            if (i9 == 0) {
                bjVar.f5149c = "";
                return j8;
            }
            bjVar.f5149c = new String(bArr, j8, i9, uk.f5673a);
            return j8 + i9;
        }
        throw new wk("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int i(int i8, byte[] bArr, int i9, int i10, km kmVar, bj bjVar) {
        if ((i8 >>> 3) != 0) {
            int i11 = i8 & 7;
            if (i11 == 0) {
                int m8 = m(bArr, i9, bjVar);
                kmVar.j(i8, Long.valueOf(bjVar.f5148b));
                return m8;
            } else if (i11 == 1) {
                kmVar.j(i8, Long.valueOf(q(bArr, i9)));
                return i9 + 8;
            } else if (i11 == 2) {
                int j8 = j(bArr, i9, bjVar);
                int i12 = bjVar.f5147a;
                if (i12 >= 0) {
                    if (i12 <= bArr.length - j8) {
                        kmVar.j(i8, i12 == 0 ? mj.f5439f : mj.p(bArr, j8, i12));
                        return j8 + i12;
                    }
                    throw new wk("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                }
                throw new wk("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            } else if (i11 != 3) {
                if (i11 == 5) {
                    kmVar.j(i8, Integer.valueOf(c(bArr, i9)));
                    return i9 + 4;
                }
                throw new wk("Protocol message contained an invalid tag (zero).");
            } else {
                int i13 = (i8 & (-8)) | 4;
                km f8 = km.f();
                int i14 = bjVar.f5151e + 1;
                bjVar.f5151e = i14;
                r(i14);
                int i15 = 0;
                while (true) {
                    if (i9 >= i10) {
                        break;
                    }
                    int j9 = j(bArr, i9, bjVar);
                    i15 = bjVar.f5147a;
                    if (i15 == i13) {
                        i9 = j9;
                        break;
                    }
                    i9 = i(i15, bArr, j9, i10, f8, bjVar);
                }
                bjVar.f5151e--;
                if (i9 > i10 || i15 != i13) {
                    throw new wk("Failed to parse the message.");
                }
                kmVar.j(i8, f8);
                return i9;
            }
        }
        throw new wk("Protocol message contained an invalid tag (zero).");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int j(byte[] bArr, int i8, bj bjVar) {
        int i9 = i8 + 1;
        byte b8 = bArr[i8];
        if (b8 >= 0) {
            bjVar.f5147a = b8;
            return i9;
        }
        return k(b8, bArr, i9, bjVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int k(int i8, byte[] bArr, int i9, bj bjVar) {
        int i10;
        int i11;
        byte b8 = bArr[i9];
        int i12 = i9 + 1;
        int i13 = i8 & 127;
        if (b8 < 0) {
            int i14 = i13 | ((b8 & Byte.MAX_VALUE) << 7);
            int i15 = i12 + 1;
            byte b9 = bArr[i12];
            if (b9 >= 0) {
                i10 = b9 << 14;
            } else {
                i13 = i14 | ((b9 & Byte.MAX_VALUE) << 14);
                i12 = i15 + 1;
                byte b10 = bArr[i15];
                if (b10 >= 0) {
                    i11 = b10 << 21;
                } else {
                    i14 = i13 | ((b10 & Byte.MAX_VALUE) << 21);
                    i15 = i12 + 1;
                    byte b11 = bArr[i12];
                    if (b11 >= 0) {
                        i10 = b11 << 28;
                    } else {
                        int i16 = i14 | ((b11 & Byte.MAX_VALUE) << 28);
                        while (true) {
                            int i17 = i15 + 1;
                            if (bArr[i15] >= 0) {
                                bjVar.f5147a = i16;
                                return i17;
                            }
                            i15 = i17;
                        }
                    }
                }
            }
            bjVar.f5147a = i14 | i10;
            return i15;
        }
        i11 = b8 << 7;
        bjVar.f5147a = i13 | i11;
        return i12;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int l(int i8, byte[] bArr, int i9, int i10, tk tkVar, bj bjVar) {
        androidx.appcompat.app.w.a(tkVar);
        j(bArr, i9, bjVar);
        int i11 = bjVar.f5147a;
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int m(byte[] bArr, int i8, bj bjVar) {
        long j8 = bArr[i8];
        int i9 = i8 + 1;
        if (j8 >= 0) {
            bjVar.f5148b = j8;
            return i9;
        }
        int i10 = i9 + 1;
        byte b8 = bArr[i9];
        long j9 = (j8 & 127) | ((b8 & Byte.MAX_VALUE) << 7);
        int i11 = 7;
        while (b8 < 0) {
            int i12 = i10 + 1;
            byte b9 = bArr[i10];
            i11 += 7;
            j9 |= (b9 & Byte.MAX_VALUE) << i11;
            i10 = i12;
            b8 = b9;
        }
        bjVar.f5148b = j9;
        return i10;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int n(Object obj, wl wlVar, byte[] bArr, int i8, int i9, int i10, bj bjVar) {
        ol olVar = (ol) wlVar;
        int i11 = bjVar.f5151e + 1;
        bjVar.f5151e = i11;
        r(i11);
        int y7 = olVar.y(obj, bArr, i8, i9, i10, bjVar);
        bjVar.f5151e--;
        bjVar.f5149c = obj;
        return y7;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int o(Object obj, wl wlVar, byte[] bArr, int i8, int i9, bj bjVar) {
        int i10 = i8 + 1;
        int i11 = bArr[i8];
        if (i11 < 0) {
            i10 = k(i11, bArr, i10, bjVar);
            i11 = bjVar.f5147a;
        }
        int i12 = i10;
        if (i11 < 0 || i11 > i9 - i12) {
            throw new wk("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        int i13 = bjVar.f5151e + 1;
        bjVar.f5151e = i13;
        r(i13);
        int i14 = i11 + i12;
        wlVar.f(obj, bArr, i12, i14, bjVar);
        bjVar.f5151e--;
        bjVar.f5149c = obj;
        return i14;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int p(int i8, byte[] bArr, int i9, int i10, bj bjVar) {
        if ((i8 >>> 3) != 0) {
            int i11 = i8 & 7;
            if (i11 != 0) {
                if (i11 != 1) {
                    if (i11 != 2) {
                        if (i11 != 3) {
                            if (i11 == 5) {
                                return i9 + 4;
                            }
                            throw new wk("Protocol message contained an invalid tag (zero).");
                        }
                        int i12 = (i8 & (-8)) | 4;
                        int i13 = 0;
                        while (i9 < i10) {
                            i9 = j(bArr, i9, bjVar);
                            i13 = bjVar.f5147a;
                            if (i13 == i12) {
                                break;
                            }
                            i9 = p(i13, bArr, i9, i10, bjVar);
                        }
                        if (i9 > i10 || i13 != i12) {
                            throw new wk("Failed to parse the message.");
                        }
                        return i9;
                    }
                    return j(bArr, i9, bjVar) + bjVar.f5147a;
                }
                return i9 + 8;
            }
            return m(bArr, i9, bjVar);
        }
        throw new wk("Protocol message contained an invalid tag (zero).");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long q(byte[] bArr, int i8) {
        return (bArr[i8] & 255) | ((bArr[i8 + 1] & 255) << 8) | ((bArr[i8 + 2] & 255) << 16) | ((bArr[i8 + 3] & 255) << 24) | ((bArr[i8 + 4] & 255) << 32) | ((bArr[i8 + 5] & 255) << 40) | ((bArr[i8 + 6] & 255) << 48) | ((bArr[i8 + 7] & 255) << 56);
    }

    private static void r(int i8) {
        if (i8 >= f5184a) {
            throw new wk("Protocol message had too many levels of nesting.  May be malicious.  Use setRecursionLimit() to increase the recursion depth limit.");
        }
    }
}
