package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import sun.misc.Unsafe;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ol implements wl {

    /* renamed from: l  reason: collision with root package name */
    private static final int[] f5506l = new int[0];

    /* renamed from: m  reason: collision with root package name */
    private static final Unsafe f5507m = rm.l();

    /* renamed from: a  reason: collision with root package name */
    private final int[] f5508a;

    /* renamed from: b  reason: collision with root package name */
    private final Object[] f5509b;

    /* renamed from: c  reason: collision with root package name */
    private final int f5510c;

    /* renamed from: d  reason: collision with root package name */
    private final int f5511d;

    /* renamed from: e  reason: collision with root package name */
    private final ll f5512e;

    /* renamed from: f  reason: collision with root package name */
    private final boolean f5513f;

    /* renamed from: g  reason: collision with root package name */
    private final int[] f5514g;

    /* renamed from: h  reason: collision with root package name */
    private final int f5515h;

    /* renamed from: i  reason: collision with root package name */
    private final int f5516i;

    /* renamed from: j  reason: collision with root package name */
    private final jm f5517j;

    /* renamed from: k  reason: collision with root package name */
    private final bk f5518k;

    private ol(int[] iArr, Object[] objArr, int i8, int i9, ll llVar, boolean z7, int[] iArr2, int i10, int i11, rl rlVar, zk zkVar, jm jmVar, bk bkVar, gl glVar) {
        this.f5508a = iArr;
        this.f5509b = objArr;
        this.f5510c = i8;
        this.f5511d = i9;
        boolean z8 = false;
        if (bkVar != null && (llVar instanceof lk)) {
            z8 = true;
        }
        this.f5513f = z8;
        this.f5514g = iArr2;
        this.f5515h = i10;
        this.f5516i = i11;
        this.f5517j = jmVar;
        this.f5518k = bkVar;
        this.f5512e = llVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0265  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0282  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.google.android.gms.internal.mlkit_vision_face_bundled.ol A(java.lang.Class r34, com.google.android.gms.internal.mlkit_vision_face_bundled.il r35, com.google.android.gms.internal.mlkit_vision_face_bundled.rl r36, com.google.android.gms.internal.mlkit_vision_face_bundled.zk r37, com.google.android.gms.internal.mlkit_vision_face_bundled.jm r38, com.google.android.gms.internal.mlkit_vision_face_bundled.bk r39, com.google.android.gms.internal.mlkit_vision_face_bundled.gl r40) {
        /*
            Method dump skipped, instructions count: 1045
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_face_bundled.ol.A(java.lang.Class, com.google.android.gms.internal.mlkit_vision_face_bundled.il, com.google.android.gms.internal.mlkit_vision_face_bundled.rl, com.google.android.gms.internal.mlkit_vision_face_bundled.zk, com.google.android.gms.internal.mlkit_vision_face_bundled.jm, com.google.android.gms.internal.mlkit_vision_face_bundled.bk, com.google.android.gms.internal.mlkit_vision_face_bundled.gl):com.google.android.gms.internal.mlkit_vision_face_bundled.ol");
    }

    private static double B(Object obj, long j8) {
        return ((Double) rm.k(obj, j8)).doubleValue();
    }

    private static float C(Object obj, long j8) {
        return ((Float) rm.k(obj, j8)).floatValue();
    }

    private static int D(Object obj, long j8) {
        return ((Integer) rm.k(obj, j8)).intValue();
    }

    private final int E(int i8) {
        return this.f5508a[i8 + 2];
    }

    private final int F(int i8, int i9) {
        int length = (this.f5508a.length / 3) - 1;
        while (i9 <= length) {
            int i10 = (length + i9) >>> 1;
            int i11 = i10 * 3;
            int i12 = this.f5508a[i11];
            if (i8 == i12) {
                return i11;
            }
            if (i8 < i12) {
                length = i10 - 1;
            } else {
                i9 = i10 + 1;
            }
        }
        return -1;
    }

    private static int G(int i8) {
        return (i8 >>> 20) & 255;
    }

    private final int H(int i8) {
        return this.f5508a[i8 + 1];
    }

    private static long I(Object obj, long j8) {
        return ((Long) rm.k(obj, j8)).longValue();
    }

    private final sk J(int i8) {
        int i9 = i8 / 3;
        return (sk) this.f5509b[i9 + i9 + 1];
    }

    private final wl K(int i8) {
        Object[] objArr = this.f5509b;
        int i9 = i8 / 3;
        int i10 = i9 + i9;
        wl wlVar = (wl) objArr[i10];
        if (wlVar != null) {
            return wlVar;
        }
        wl b8 = tl.a().b((Class) objArr[i10 + 1]);
        this.f5509b[i10] = b8;
        return b8;
    }

    private final Object L(int i8) {
        int i9 = i8 / 3;
        return this.f5509b[i9 + i9];
    }

    private final Object M(Object obj, int i8) {
        wl K = K(i8);
        int H = H(i8) & 1048575;
        if (r(obj, i8)) {
            Object object = f5507m.getObject(obj, H);
            if (u(object)) {
                return object;
            }
            Object c8 = K.c();
            if (object != null) {
                K.e(c8, object);
            }
            return c8;
        }
        return K.c();
    }

    private final Object N(Object obj, int i8, int i9) {
        wl K = K(i9);
        if (v(obj, i8, i9)) {
            Object object = f5507m.getObject(obj, H(i9) & 1048575);
            if (u(object)) {
                return object;
            }
            Object c8 = K.c();
            if (object != null) {
                K.e(c8, object);
            }
            return c8;
        }
        return K.c();
    }

    private static Field O(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    private static void j(Object obj) {
        if (!u(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(obj)));
        }
    }

    private final void k(Object obj, Object obj2, int i8) {
        if (r(obj2, i8)) {
            Unsafe unsafe = f5507m;
            long H = H(i8) & 1048575;
            Object object = unsafe.getObject(obj2, H);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.f5508a[i8] + " is present but null: " + obj2.toString());
            }
            wl K = K(i8);
            if (!r(obj, i8)) {
                if (u(object)) {
                    Object c8 = K.c();
                    K.e(c8, object);
                    unsafe.putObject(obj, H, c8);
                } else {
                    unsafe.putObject(obj, H, object);
                }
                m(obj, i8);
                return;
            }
            Object object2 = unsafe.getObject(obj, H);
            if (!u(object2)) {
                Object c9 = K.c();
                K.e(c9, object2);
                unsafe.putObject(obj, H, c9);
                object2 = c9;
            }
            K.e(object2, object);
        }
    }

    private final void l(Object obj, Object obj2, int i8) {
        int i9 = this.f5508a[i8];
        if (v(obj2, i9, i8)) {
            Unsafe unsafe = f5507m;
            long H = H(i8) & 1048575;
            Object object = unsafe.getObject(obj2, H);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.f5508a[i8] + " is present but null: " + obj2.toString());
            }
            wl K = K(i8);
            if (!v(obj, i9, i8)) {
                if (u(object)) {
                    Object c8 = K.c();
                    K.e(c8, object);
                    unsafe.putObject(obj, H, c8);
                } else {
                    unsafe.putObject(obj, H, object);
                }
                n(obj, i9, i8);
                return;
            }
            Object object2 = unsafe.getObject(obj, H);
            if (!u(object2)) {
                Object c9 = K.c();
                K.e(c9, object2);
                unsafe.putObject(obj, H, c9);
                object2 = c9;
            }
            K.e(object2, object);
        }
    }

    private final void m(Object obj, int i8) {
        int E = E(i8);
        long j8 = 1048575 & E;
        if (j8 == 1048575) {
            return;
        }
        rm.v(obj, j8, (1 << (E >>> 20)) | rm.h(obj, j8));
    }

    private final void n(Object obj, int i8, int i9) {
        rm.v(obj, E(i9) & 1048575, i8);
    }

    private final void o(Object obj, int i8, Object obj2) {
        f5507m.putObject(obj, H(i8) & 1048575, obj2);
        m(obj, i8);
    }

    private final void p(Object obj, int i8, int i9, Object obj2) {
        f5507m.putObject(obj, H(i9) & 1048575, obj2);
        n(obj, i8, i9);
    }

    private final boolean q(Object obj, Object obj2, int i8) {
        return r(obj, i8) == r(obj2, i8);
    }

    private final boolean r(Object obj, int i8) {
        int E = E(i8);
        long j8 = E & 1048575;
        if (j8 != 1048575) {
            return (rm.h(obj, j8) & (1 << (E >>> 20))) != 0;
        }
        int H = H(i8);
        long j9 = H & 1048575;
        switch (G(H)) {
            case 0:
                return Double.doubleToRawLongBits(rm.f(obj, j9)) != 0;
            case 1:
                return Float.floatToRawIntBits(rm.g(obj, j9)) != 0;
            case 2:
                return rm.i(obj, j9) != 0;
            case 3:
                return rm.i(obj, j9) != 0;
            case 4:
                return rm.h(obj, j9) != 0;
            case 5:
                return rm.i(obj, j9) != 0;
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                return rm.h(obj, j9) != 0;
            case 7:
                return rm.B(obj, j9);
            case 8:
                Object k8 = rm.k(obj, j9);
                if (k8 instanceof String) {
                    return !((String) k8).isEmpty();
                } else if (k8 instanceof mj) {
                    return !mj.f5439f.equals(k8);
                } else {
                    throw new IllegalArgumentException();
                }
            case 9:
                return rm.k(obj, j9) != null;
            case 10:
                return !mj.f5439f.equals(rm.k(obj, j9));
            case 11:
                return rm.h(obj, j9) != 0;
            case 12:
                return rm.h(obj, j9) != 0;
            case 13:
                return rm.h(obj, j9) != 0;
            case 14:
                return rm.i(obj, j9) != 0;
            case 15:
                return rm.h(obj, j9) != 0;
            case 16:
                return rm.i(obj, j9) != 0;
            case 17:
                return rm.k(obj, j9) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean s(Object obj, int i8, int i9, int i10, int i11) {
        return i9 == 1048575 ? r(obj, i8) : (i10 & i11) != 0;
    }

    private static boolean t(Object obj, int i8, wl wlVar) {
        return wlVar.h(rm.k(obj, i8 & 1048575));
    }

    private static boolean u(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof ok) {
            return ((ok) obj).q();
        }
        return true;
    }

    private final boolean v(Object obj, int i8, int i9) {
        return rm.h(obj, (long) (E(i9) & 1048575)) == i8;
    }

    private static boolean w(Object obj, long j8) {
        return ((Boolean) rm.k(obj, j8)).booleanValue();
    }

    private static final void x(int i8, Object obj, xm xmVar) {
        if (obj instanceof String) {
            xmVar.g(i8, (String) obj);
        } else {
            xmVar.H(i8, (mj) obj);
        }
    }

    static km z(Object obj) {
        ok okVar = (ok) obj;
        km kmVar = okVar.zzc;
        if (kmVar == km.c()) {
            km f8 = km.f();
            okVar.zzc = f8;
            return f8;
        }
        return kmVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.wl
    public final void a(Object obj) {
        int i8;
        if (u(obj)) {
            if (obj instanceof ok) {
                ok okVar = (ok) obj;
                okVar.o(Integer.MAX_VALUE);
                okVar.zza = 0;
                okVar.m();
            }
            int[] iArr = this.f5508a;
            while (i8 < iArr.length) {
                int H = H(i8);
                int i9 = 1048575 & H;
                int G = G(H);
                long j8 = i9;
                if (G != 9) {
                    if (G != 60 && G != 68) {
                        switch (G) {
                            case 18:
                            case 19:
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                            case 26:
                            case 27:
                            case 28:
                            case 29:
                            case 30:
                            case 31:
                            case 32:
                            case 33:
                            case 34:
                            case 35:
                            case 36:
                            case 37:
                            case 38:
                            case 39:
                            case 40:
                            case 41:
                            case 42:
                            case 43:
                            case 44:
                            case 45:
                            case 46:
                            case 47:
                            case 48:
                            case 49:
                                ((tk) rm.k(obj, j8)).zzb();
                                break;
                            case 50:
                                Unsafe unsafe = f5507m;
                                Object object = unsafe.getObject(obj, j8);
                                if (object == null) {
                                    break;
                                } else {
                                    ((fl) object).d();
                                    unsafe.putObject(obj, j8, object);
                                    break;
                                }
                        }
                    } else {
                        if (!v(obj, this.f5508a[i8], i8)) {
                        }
                        K(i8).a(f5507m.getObject(obj, j8));
                    }
                }
                i8 = r(obj, i8) ? 0 : i8 + 3;
                K(i8).a(f5507m.getObject(obj, j8));
            }
            this.f5517j.a(obj);
            if (this.f5513f) {
                this.f5518k.a(obj);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:240:0x04f3, code lost:
        if ((r1 instanceof com.google.android.gms.internal.mlkit_vision_face_bundled.mj) != false) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:242:0x04f6, code lost:
        r0 = com.google.android.gms.internal.mlkit_vision_face_bundled.vj.B(r0);
        r1 = com.google.android.gms.internal.mlkit_vision_face_bundled.vj.A((java.lang.String) r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00d0, code lost:
        if ((r1 instanceof com.google.android.gms.internal.mlkit_vision_face_bundled.mj) != false) goto L60;
     */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.wl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int b(java.lang.Object r20) {
        /*
            Method dump skipped, instructions count: 1710
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_face_bundled.ol.b(java.lang.Object):int");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.wl
    public final Object c() {
        return ((ok) this.f5512e).y();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x02e6  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x02f7  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0308  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0319  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x032a  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x033e  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x034e  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0362  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0377  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0389  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x039a  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x03ab  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x03bc  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x03cd  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x03de  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x03ef  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0407  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x042e  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0450  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0472  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0494  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x04b6  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x04d8  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x04fa  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x051e  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0544  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0566  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0588  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x05aa  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x05cc  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x05ee  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0610  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0631  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0652  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0685  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0233  */
    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.wl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void d(java.lang.Object r24, com.google.android.gms.internal.mlkit_vision_face_bundled.xm r25) {
        /*
            Method dump skipped, instructions count: 1842
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_face_bundled.ol.d(java.lang.Object, com.google.android.gms.internal.mlkit_vision_face_bundled.xm):void");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.wl
    public final void e(Object obj, Object obj2) {
        j(obj);
        obj2.getClass();
        for (int i8 = 0; i8 < this.f5508a.length; i8 += 3) {
            int H = H(i8);
            int i9 = 1048575 & H;
            int[] iArr = this.f5508a;
            int G = G(H);
            int i10 = iArr[i8];
            long j8 = i9;
            switch (G) {
                case 0:
                    if (r(obj2, i8)) {
                        rm.t(obj, j8, rm.f(obj2, j8));
                        m(obj, i8);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (r(obj2, i8)) {
                        rm.u(obj, j8, rm.g(obj2, j8));
                        m(obj, i8);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (!r(obj2, i8)) {
                        break;
                    }
                    rm.w(obj, j8, rm.i(obj2, j8));
                    m(obj, i8);
                    break;
                case 3:
                    if (!r(obj2, i8)) {
                        break;
                    }
                    rm.w(obj, j8, rm.i(obj2, j8));
                    m(obj, i8);
                    break;
                case 4:
                    if (!r(obj2, i8)) {
                        break;
                    }
                    rm.v(obj, j8, rm.h(obj2, j8));
                    m(obj, i8);
                    break;
                case 5:
                    if (!r(obj2, i8)) {
                        break;
                    }
                    rm.w(obj, j8, rm.i(obj2, j8));
                    m(obj, i8);
                    break;
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                    if (!r(obj2, i8)) {
                        break;
                    }
                    rm.v(obj, j8, rm.h(obj2, j8));
                    m(obj, i8);
                    break;
                case 7:
                    if (r(obj2, i8)) {
                        rm.r(obj, j8, rm.B(obj2, j8));
                        m(obj, i8);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (!r(obj2, i8)) {
                        break;
                    }
                    rm.x(obj, j8, rm.k(obj2, j8));
                    m(obj, i8);
                    break;
                case 9:
                case 17:
                    k(obj, obj2, i8);
                    break;
                case 10:
                    if (!r(obj2, i8)) {
                        break;
                    }
                    rm.x(obj, j8, rm.k(obj2, j8));
                    m(obj, i8);
                    break;
                case 11:
                    if (!r(obj2, i8)) {
                        break;
                    }
                    rm.v(obj, j8, rm.h(obj2, j8));
                    m(obj, i8);
                    break;
                case 12:
                    if (!r(obj2, i8)) {
                        break;
                    }
                    rm.v(obj, j8, rm.h(obj2, j8));
                    m(obj, i8);
                    break;
                case 13:
                    if (!r(obj2, i8)) {
                        break;
                    }
                    rm.v(obj, j8, rm.h(obj2, j8));
                    m(obj, i8);
                    break;
                case 14:
                    if (!r(obj2, i8)) {
                        break;
                    }
                    rm.w(obj, j8, rm.i(obj2, j8));
                    m(obj, i8);
                    break;
                case 15:
                    if (!r(obj2, i8)) {
                        break;
                    }
                    rm.v(obj, j8, rm.h(obj2, j8));
                    m(obj, i8);
                    break;
                case 16:
                    if (!r(obj2, i8)) {
                        break;
                    }
                    rm.w(obj, j8, rm.i(obj2, j8));
                    m(obj, i8);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    tk tkVar = (tk) rm.k(obj, j8);
                    tk tkVar2 = (tk) rm.k(obj2, j8);
                    int size = tkVar.size();
                    int size2 = tkVar2.size();
                    if (size > 0 && size2 > 0) {
                        if (!tkVar.a()) {
                            tkVar = tkVar.c(size2 + size);
                        }
                        tkVar.addAll(tkVar2);
                    }
                    if (size > 0) {
                        tkVar2 = tkVar;
                    }
                    rm.x(obj, j8, tkVar2);
                    break;
                case 50:
                    int i11 = yl.f5963b;
                    rm.x(obj, j8, gl.a(rm.k(obj, j8), rm.k(obj2, j8)));
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (!v(obj2, i10, i8)) {
                        break;
                    }
                    rm.x(obj, j8, rm.k(obj2, j8));
                    n(obj, i10, i8);
                    break;
                case 60:
                case 68:
                    l(obj, obj2, i8);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (!v(obj2, i10, i8)) {
                        break;
                    }
                    rm.x(obj, j8, rm.k(obj2, j8));
                    n(obj, i10, i8);
                    break;
            }
        }
        yl.y(this.f5517j, obj, obj2);
        if (this.f5513f) {
            yl.x(this.f5518k, obj, obj2);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.wl
    public final void f(Object obj, byte[] bArr, int i8, int i9, bj bjVar) {
        y(obj, bArr, i8, i9, 0, bjVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.wl
    public final boolean g(Object obj, Object obj2) {
        for (int i8 = 0; i8 < this.f5508a.length; i8 += 3) {
            int H = H(i8);
            long j8 = H & 1048575;
            switch (G(H)) {
                case 0:
                    if (q(obj, obj2, i8) && Double.doubleToLongBits(rm.f(obj, j8)) == Double.doubleToLongBits(rm.f(obj2, j8))) {
                        break;
                    }
                    return false;
                case 1:
                    if (q(obj, obj2, i8) && Float.floatToIntBits(rm.g(obj, j8)) == Float.floatToIntBits(rm.g(obj2, j8))) {
                        break;
                    }
                    return false;
                case 2:
                    if (q(obj, obj2, i8) && rm.i(obj, j8) == rm.i(obj2, j8)) {
                        break;
                    }
                    return false;
                case 3:
                    if (q(obj, obj2, i8) && rm.i(obj, j8) == rm.i(obj2, j8)) {
                        break;
                    }
                    return false;
                case 4:
                    if (q(obj, obj2, i8) && rm.h(obj, j8) == rm.h(obj2, j8)) {
                        break;
                    }
                    return false;
                case 5:
                    if (q(obj, obj2, i8) && rm.i(obj, j8) == rm.i(obj2, j8)) {
                        break;
                    }
                    return false;
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                    if (q(obj, obj2, i8) && rm.h(obj, j8) == rm.h(obj2, j8)) {
                        break;
                    }
                    return false;
                case 7:
                    if (q(obj, obj2, i8) && rm.B(obj, j8) == rm.B(obj2, j8)) {
                        break;
                    }
                    return false;
                case 8:
                    if (q(obj, obj2, i8) && yl.i(rm.k(obj, j8), rm.k(obj2, j8))) {
                        break;
                    }
                    return false;
                case 9:
                    if (q(obj, obj2, i8) && yl.i(rm.k(obj, j8), rm.k(obj2, j8))) {
                        break;
                    }
                    return false;
                case 10:
                    if (q(obj, obj2, i8) && yl.i(rm.k(obj, j8), rm.k(obj2, j8))) {
                        break;
                    }
                    return false;
                case 11:
                    if (q(obj, obj2, i8) && rm.h(obj, j8) == rm.h(obj2, j8)) {
                        break;
                    }
                    return false;
                case 12:
                    if (q(obj, obj2, i8) && rm.h(obj, j8) == rm.h(obj2, j8)) {
                        break;
                    }
                    return false;
                case 13:
                    if (q(obj, obj2, i8) && rm.h(obj, j8) == rm.h(obj2, j8)) {
                        break;
                    }
                    return false;
                case 14:
                    if (q(obj, obj2, i8) && rm.i(obj, j8) == rm.i(obj2, j8)) {
                        break;
                    }
                    return false;
                case 15:
                    if (q(obj, obj2, i8) && rm.h(obj, j8) == rm.h(obj2, j8)) {
                        break;
                    }
                    return false;
                case 16:
                    if (q(obj, obj2, i8) && rm.i(obj, j8) == rm.i(obj2, j8)) {
                        break;
                    }
                    return false;
                case 17:
                    if (q(obj, obj2, i8) && yl.i(rm.k(obj, j8), rm.k(obj2, j8))) {
                        break;
                    }
                    return false;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                case 50:
                    if (yl.i(rm.k(obj, j8), rm.k(obj2, j8))) {
                        break;
                    } else {
                        return false;
                    }
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                    long E = E(i8) & 1048575;
                    if (rm.h(obj, E) == rm.h(obj2, E) && yl.i(rm.k(obj, j8), rm.k(obj2, j8))) {
                        break;
                    }
                    return false;
            }
        }
        if (((ok) obj).zzc.equals(((ok) obj2).zzc)) {
            if (this.f5513f) {
                return ((lk) obj).zzb.equals(((lk) obj2).zzb);
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.wl
    public final boolean h(Object obj) {
        int i8;
        int i9;
        int i10 = 0;
        int i11 = 0;
        int i12 = 1048575;
        while (i11 < this.f5515h) {
            int[] iArr = this.f5514g;
            int[] iArr2 = this.f5508a;
            int i13 = iArr[i11];
            int i14 = iArr2[i13];
            int H = H(i13);
            int i15 = this.f5508a[i13 + 2];
            int i16 = i15 & 1048575;
            int i17 = 1 << (i15 >>> 20);
            if (i16 != i12) {
                if (i16 != 1048575) {
                    i10 = f5507m.getInt(obj, i16);
                }
                i9 = i10;
                i8 = i16;
            } else {
                i8 = i12;
                i9 = i10;
            }
            if ((268435456 & H) != 0 && !s(obj, i13, i8, i9, i17)) {
                return false;
            }
            int G = G(H);
            if (G != 9 && G != 17) {
                if (G != 27) {
                    if (G == 60 || G == 68) {
                        if (v(obj, i14, i13) && !t(obj, H, K(i13))) {
                            return false;
                        }
                    } else if (G != 49) {
                        if (G == 50 && !((fl) rm.k(obj, H & 1048575)).isEmpty()) {
                            androidx.appcompat.app.w.a(L(i13));
                            throw null;
                        }
                    }
                }
                List list = (List) rm.k(obj, H & 1048575);
                if (list.isEmpty()) {
                    continue;
                } else {
                    wl K = K(i13);
                    for (int i18 = 0; i18 < list.size(); i18++) {
                        if (!K.h(list.get(i18))) {
                            return false;
                        }
                    }
                    continue;
                }
            } else if (s(obj, i13, i8, i9, i17) && !t(obj, H, K(i13))) {
                return false;
            }
            i11++;
            i12 = i8;
            i10 = i9;
        }
        return !this.f5513f || ((lk) obj).zzb.l();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00cc, code lost:
        if (r2 != null) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00e0, code lost:
        if (r2 != null) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00e2, code lost:
        r6 = r2.hashCode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00e6, code lost:
        r1 = r1 + r6;
     */
    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.wl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int i(java.lang.Object r9) {
        /*
            Method dump skipped, instructions count: 474
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_face_bundled.ol.i(java.lang.Object):int");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:265:0x063c, code lost:
        r7 = r6;
        r12 = r26;
        r9 = r25;
        r35 = r8;
        r13 = r1;
        r10 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:539:0x0c73, code lost:
        if (r6 == 1048575) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:540:0x0c75, code lost:
        r10.putInt(r7, r6, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:541:0x0c79, code lost:
        r3 = r9.f5515h;
     */
    /* JADX WARN: Code restructure failed: missing block: B:543:0x0c7d, code lost:
        if (r3 >= r9.f5516i) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:544:0x0c7f, code lost:
        r5 = r9.f5514g;
        r6 = r9.f5508a;
        r5 = r5[r3];
        r6 = r6[r5];
        r6 = com.google.android.gms.internal.mlkit_vision_face_bundled.rm.k(r7, r9.H(r5) & 1048575);
     */
    /* JADX WARN: Code restructure failed: missing block: B:545:0x0c91, code lost:
        if (r6 != null) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:548:0x0c98, code lost:
        if (r9.J(r5) != null) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:549:0x0c9a, code lost:
        r3 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:550:0x0c9d, code lost:
        r6 = (com.google.android.gms.internal.mlkit_vision_face_bundled.fl) r6;
        androidx.appcompat.app.w.a(r9.L(r5));
     */
    /* JADX WARN: Code restructure failed: missing block: B:551:0x0ca6, code lost:
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:553:0x0ca9, code lost:
        if (r0 != 0) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:554:0x0cab, code lost:
        if (r1 != r8) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:557:0x0cb3, code lost:
        throw new com.google.android.gms.internal.mlkit_vision_face_bundled.wk("Failed to parse the message.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:558:0x0cb4, code lost:
        if (r1 > r8) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:559:0x0cb6, code lost:
        if (r4 != r0) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:560:0x0cb8, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:562:0x0cbe, code lost:
        throw new com.google.android.gms.internal.mlkit_vision_face_bundled.wk("Failed to parse the message.");
     */
    /* JADX WARN: Removed duplicated region for block: B:26:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x06c5  */
    /* JADX WARN: Removed duplicated region for block: B:529:0x0be2  */
    /* JADX WARN: Removed duplicated region for block: B:620:0x0059 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x020c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:285:0x06bf -> B:286:0x06c3). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:291:0x06d5 -> B:283:0x06b4). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int y(java.lang.Object r38, byte[] r39, int r40, int r41, int r42, com.google.android.gms.internal.mlkit_vision_face_bundled.bj r43) {
        /*
            Method dump skipped, instructions count: 3408
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_face_bundled.ol.y(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.mlkit_vision_face_bundled.bj):int");
    }
}
