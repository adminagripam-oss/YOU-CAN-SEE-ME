package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.e;
import com.google.crypto.tink.shaded.protobuf.i0;
import com.google.crypto.tink.shaded.protobuf.p1;
import com.google.crypto.tink.shaded.protobuf.z;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class s0 implements d1 {

    /* renamed from: q  reason: collision with root package name */
    private static final int[] f6212q = new int[0];

    /* renamed from: r  reason: collision with root package name */
    private static final Unsafe f6213r = m1.D();

    /* renamed from: a  reason: collision with root package name */
    private final int[] f6214a;

    /* renamed from: b  reason: collision with root package name */
    private final Object[] f6215b;

    /* renamed from: c  reason: collision with root package name */
    private final int f6216c;

    /* renamed from: d  reason: collision with root package name */
    private final int f6217d;

    /* renamed from: e  reason: collision with root package name */
    private final p0 f6218e;

    /* renamed from: f  reason: collision with root package name */
    private final boolean f6219f;

    /* renamed from: g  reason: collision with root package name */
    private final boolean f6220g;

    /* renamed from: h  reason: collision with root package name */
    private final boolean f6221h;

    /* renamed from: i  reason: collision with root package name */
    private final int[] f6222i;

    /* renamed from: j  reason: collision with root package name */
    private final int f6223j;

    /* renamed from: k  reason: collision with root package name */
    private final int f6224k;

    /* renamed from: l  reason: collision with root package name */
    private final u0 f6225l;

    /* renamed from: m  reason: collision with root package name */
    private final d0 f6226m;

    /* renamed from: n  reason: collision with root package name */
    private final j1 f6227n;

    /* renamed from: o  reason: collision with root package name */
    private final q f6228o;

    /* renamed from: p  reason: collision with root package name */
    private final k0 f6229p;

    private s0(int[] iArr, Object[] objArr, int i8, int i9, p0 p0Var, boolean z7, int[] iArr2, int i10, int i11, u0 u0Var, d0 d0Var, j1 j1Var, q qVar, k0 k0Var) {
        this.f6214a = iArr;
        this.f6215b = objArr;
        this.f6216c = i8;
        this.f6217d = i9;
        this.f6220g = p0Var instanceof x;
        this.f6219f = qVar != null && qVar.e(p0Var);
        this.f6221h = z7;
        this.f6222i = iArr2;
        this.f6223j = i10;
        this.f6224k = i11;
        this.f6225l = u0Var;
        this.f6226m = d0Var;
        this.f6227n = j1Var;
        this.f6228o = qVar;
        this.f6218e = p0Var;
        this.f6229p = k0Var;
    }

    private boolean A(Object obj, int i8, int i9, int i10, int i11) {
        return i9 == 1048575 ? z(obj, i8) : (i10 & i11) != 0;
    }

    private static boolean B(Object obj, int i8, d1 d1Var) {
        return d1Var.f(m1.C(obj, V(i8)));
    }

    private static boolean C(int i8) {
        return (i8 & Integer.MIN_VALUE) != 0;
    }

    private boolean D(Object obj, int i8, int i9) {
        List list = (List) m1.C(obj, V(i8));
        if (list.isEmpty()) {
            return true;
        }
        d1 u7 = u(i9);
        for (int i10 = 0; i10 < list.size(); i10++) {
            if (!u7.f(list.get(i10))) {
                return false;
            }
        }
        return true;
    }

    private boolean E(Object obj, int i8, int i9) {
        if (this.f6229p.g(m1.C(obj, V(i8))).isEmpty()) {
            return true;
        }
        this.f6229p.f(t(i9));
        throw null;
    }

    private static boolean F(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof x) {
            return ((x) obj).D();
        }
        return true;
    }

    private boolean G(Object obj, Object obj2, int i8) {
        long h02 = h0(i8) & 1048575;
        return m1.z(obj, h02) == m1.z(obj2, h02);
    }

    private boolean H(Object obj, int i8, int i9) {
        return m1.z(obj, (long) (h0(i9) & 1048575)) == i8;
    }

    private static boolean I(int i8) {
        return (i8 & 268435456) != 0;
    }

    private static long J(Object obj, long j8) {
        return m1.A(obj, j8);
    }

    /* JADX WARN: Code restructure failed: missing block: B:249:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0092, code lost:
        r0 = r19.f6223j;
        r5 = r18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0098, code lost:
        if (r0 >= r19.f6224k) goto L211;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x009a, code lost:
        r5 = q(r22, r19.f6222i[r0], r5, r20, r22);
        r0 = r0 + 1;
        r4 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00b0, code lost:
        r11 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00b1, code lost:
        if (r5 == null) goto L216;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00b3, code lost:
        r7.o(r11, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00b6, code lost:
        return;
     */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0560 A[Catch: all -> 0x05bd, TRY_LEAVE, TryCatch #8 {all -> 0x05bd, blocks: (B:61:0x00d7, B:172:0x055a, B:174:0x0560, B:184:0x0589, B:90:0x0224, B:91:0x0229, B:92:0x0235, B:93:0x023a, B:94:0x0246, B:95:0x024b, B:96:0x0257, B:97:0x025c, B:98:0x0268, B:99:0x026d, B:100:0x0280, B:101:0x028e, B:102:0x029a, B:103:0x029f, B:104:0x02ab, B:105:0x02b0, B:106:0x02bc, B:107:0x02c1, B:108:0x02cd, B:109:0x02d2, B:110:0x02de, B:111:0x02e3, B:112:0x02ef, B:113:0x02f4, B:114:0x0300, B:115:0x0305, B:116:0x0311, B:117:0x0316, B:118:0x0322, B:119:0x0327, B:120:0x0335, B:121:0x0343, B:122:0x0351, B:123:0x035f, B:124:0x0374, B:125:0x0382, B:126:0x0393, B:127:0x03a7, B:128:0x03ae, B:129:0x03bc, B:130:0x03ca, B:131:0x03d8, B:132:0x03e6, B:133:0x03f4, B:134:0x0402, B:135:0x0410, B:136:0x041e, B:137:0x042d, B:138:0x0432, B:139:0x043f, B:140:0x0444, B:141:0x0452, B:142:0x0460, B:143:0x046e, B:144:0x047f, B:145:0x048d, B:146:0x049b, B:147:0x04ab, B:148:0x04b1, B:149:0x04bf, B:150:0x04ce, B:151:0x04dd, B:152:0x04ec, B:153:0x04fb, B:154:0x050a, B:155:0x0519), top: B:223:0x00d7 }] */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0587  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x05ce A[LOOP:2: B:205:0x05ca->B:207:0x05ce, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:209:0x05e3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void K(com.google.crypto.tink.shaded.protobuf.j1 r20, com.google.crypto.tink.shaded.protobuf.q r21, java.lang.Object r22, com.google.crypto.tink.shaded.protobuf.c1 r23, com.google.crypto.tink.shaded.protobuf.p r24) {
        /*
            Method dump skipped, instructions count: 1654
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.s0.K(com.google.crypto.tink.shaded.protobuf.j1, com.google.crypto.tink.shaded.protobuf.q, java.lang.Object, com.google.crypto.tink.shaded.protobuf.c1, com.google.crypto.tink.shaded.protobuf.p):void");
    }

    private final void L(Object obj, int i8, Object obj2, p pVar, c1 c1Var) {
        long V = V(t0(i8));
        Object C = m1.C(obj, V);
        if (C == null) {
            C = this.f6229p.b(obj2);
            m1.R(obj, V, C);
        } else if (this.f6229p.d(C)) {
            Object b8 = this.f6229p.b(obj2);
            this.f6229p.a(b8, C);
            m1.R(obj, V, b8);
            C = b8;
        }
        Map h8 = this.f6229p.h(C);
        this.f6229p.f(obj2);
        c1Var.E(h8, null, pVar);
    }

    private void M(Object obj, Object obj2, int i8) {
        if (z(obj2, i8)) {
            long V = V(t0(i8));
            Unsafe unsafe = f6213r;
            Object object = unsafe.getObject(obj2, V);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + U(i8) + " is present but null: " + obj2);
            }
            d1 u7 = u(i8);
            if (!z(obj, i8)) {
                if (F(object)) {
                    Object i9 = u7.i();
                    u7.a(i9, object);
                    unsafe.putObject(obj, V, i9);
                } else {
                    unsafe.putObject(obj, V, object);
                }
                n0(obj, i8);
                return;
            }
            Object object2 = unsafe.getObject(obj, V);
            if (!F(object2)) {
                Object i10 = u7.i();
                u7.a(i10, object2);
                unsafe.putObject(obj, V, i10);
                object2 = i10;
            }
            u7.a(object2, object);
        }
    }

    private void N(Object obj, Object obj2, int i8) {
        int U = U(i8);
        if (H(obj2, U, i8)) {
            long V = V(t0(i8));
            Unsafe unsafe = f6213r;
            Object object = unsafe.getObject(obj2, V);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + U(i8) + " is present but null: " + obj2);
            }
            d1 u7 = u(i8);
            if (!H(obj, U, i8)) {
                if (F(object)) {
                    Object i9 = u7.i();
                    u7.a(i9, object);
                    unsafe.putObject(obj, V, i9);
                } else {
                    unsafe.putObject(obj, V, object);
                }
                o0(obj, U, i8);
                return;
            }
            Object object2 = unsafe.getObject(obj, V);
            if (!F(object2)) {
                Object i10 = u7.i();
                u7.a(i10, object2);
                unsafe.putObject(obj, V, i10);
                object2 = i10;
            }
            u7.a(object2, object);
        }
    }

    private void O(Object obj, Object obj2, int i8) {
        int t02 = t0(i8);
        long V = V(t02);
        int U = U(i8);
        switch (s0(t02)) {
            case 0:
                if (z(obj2, i8)) {
                    m1.N(obj, V, m1.x(obj2, V));
                    n0(obj, i8);
                    return;
                }
                return;
            case 1:
                if (z(obj2, i8)) {
                    m1.O(obj, V, m1.y(obj2, V));
                    n0(obj, i8);
                    return;
                }
                return;
            case 2:
                if (!z(obj2, i8)) {
                    return;
                }
                m1.Q(obj, V, m1.A(obj2, V));
                n0(obj, i8);
                return;
            case 3:
                if (!z(obj2, i8)) {
                    return;
                }
                m1.Q(obj, V, m1.A(obj2, V));
                n0(obj, i8);
                return;
            case 4:
                if (!z(obj2, i8)) {
                    return;
                }
                m1.P(obj, V, m1.z(obj2, V));
                n0(obj, i8);
                return;
            case 5:
                if (!z(obj2, i8)) {
                    return;
                }
                m1.Q(obj, V, m1.A(obj2, V));
                n0(obj, i8);
                return;
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                if (!z(obj2, i8)) {
                    return;
                }
                m1.P(obj, V, m1.z(obj2, V));
                n0(obj, i8);
                return;
            case 7:
                if (z(obj2, i8)) {
                    m1.H(obj, V, m1.r(obj2, V));
                    n0(obj, i8);
                    return;
                }
                return;
            case 8:
                if (!z(obj2, i8)) {
                    return;
                }
                m1.R(obj, V, m1.C(obj2, V));
                n0(obj, i8);
                return;
            case 9:
            case 17:
                M(obj, obj2, i8);
                return;
            case 10:
                if (!z(obj2, i8)) {
                    return;
                }
                m1.R(obj, V, m1.C(obj2, V));
                n0(obj, i8);
                return;
            case 11:
                if (!z(obj2, i8)) {
                    return;
                }
                m1.P(obj, V, m1.z(obj2, V));
                n0(obj, i8);
                return;
            case 12:
                if (!z(obj2, i8)) {
                    return;
                }
                m1.P(obj, V, m1.z(obj2, V));
                n0(obj, i8);
                return;
            case 13:
                if (!z(obj2, i8)) {
                    return;
                }
                m1.P(obj, V, m1.z(obj2, V));
                n0(obj, i8);
                return;
            case 14:
                if (!z(obj2, i8)) {
                    return;
                }
                m1.Q(obj, V, m1.A(obj2, V));
                n0(obj, i8);
                return;
            case 15:
                if (!z(obj2, i8)) {
                    return;
                }
                m1.P(obj, V, m1.z(obj2, V));
                n0(obj, i8);
                return;
            case 16:
                if (!z(obj2, i8)) {
                    return;
                }
                m1.Q(obj, V, m1.A(obj2, V));
                n0(obj, i8);
                return;
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
                this.f6226m.c(obj, obj2, V);
                return;
            case 50:
                f1.G(this.f6229p, obj, obj2, V);
                return;
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
                if (!H(obj2, U, i8)) {
                    return;
                }
                m1.R(obj, V, m1.C(obj2, V));
                o0(obj, U, i8);
                return;
            case 60:
            case 68:
                N(obj, obj2, i8);
                return;
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
                if (!H(obj2, U, i8)) {
                    return;
                }
                m1.R(obj, V, m1.C(obj2, V));
                o0(obj, U, i8);
                return;
            default:
                return;
        }
    }

    private Object P(Object obj, int i8) {
        d1 u7 = u(i8);
        long V = V(t0(i8));
        if (z(obj, i8)) {
            Object object = f6213r.getObject(obj, V);
            if (F(object)) {
                return object;
            }
            Object i9 = u7.i();
            if (object != null) {
                u7.a(i9, object);
            }
            return i9;
        }
        return u7.i();
    }

    private Object Q(Object obj, int i8, int i9) {
        d1 u7 = u(i9);
        if (H(obj, i8, i9)) {
            Object object = f6213r.getObject(obj, V(t0(i9)));
            if (F(object)) {
                return object;
            }
            Object i10 = u7.i();
            if (object != null) {
                u7.a(i10, object);
            }
            return i10;
        }
        return u7.i();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static s0 R(Class cls, n0 n0Var, u0 u0Var, d0 d0Var, j1 j1Var, q qVar, k0 k0Var) {
        if (n0Var instanceof b1) {
            return T((b1) n0Var, u0Var, d0Var, j1Var, qVar, k0Var);
        }
        androidx.appcompat.app.w.a(n0Var);
        return S(null, u0Var, d0Var, j1Var, qVar, k0Var);
    }

    static s0 S(g1 g1Var, u0 u0Var, d0 d0Var, j1 j1Var, q qVar, k0 k0Var) {
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:121:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x026e  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0271  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x032d  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x032f  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0332  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x038a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.google.crypto.tink.shaded.protobuf.s0 T(com.google.crypto.tink.shaded.protobuf.b1 r34, com.google.crypto.tink.shaded.protobuf.u0 r35, com.google.crypto.tink.shaded.protobuf.d0 r36, com.google.crypto.tink.shaded.protobuf.j1 r37, com.google.crypto.tink.shaded.protobuf.q r38, com.google.crypto.tink.shaded.protobuf.k0 r39) {
        /*
            Method dump skipped, instructions count: 1024
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.s0.T(com.google.crypto.tink.shaded.protobuf.b1, com.google.crypto.tink.shaded.protobuf.u0, com.google.crypto.tink.shaded.protobuf.d0, com.google.crypto.tink.shaded.protobuf.j1, com.google.crypto.tink.shaded.protobuf.q, com.google.crypto.tink.shaded.protobuf.k0):com.google.crypto.tink.shaded.protobuf.s0");
    }

    private int U(int i8) {
        return this.f6214a[i8];
    }

    private static long V(int i8) {
        return i8 & 1048575;
    }

    private static boolean W(Object obj, long j8) {
        return ((Boolean) m1.C(obj, j8)).booleanValue();
    }

    private static double X(Object obj, long j8) {
        return ((Double) m1.C(obj, j8)).doubleValue();
    }

    private static float Y(Object obj, long j8) {
        return ((Float) m1.C(obj, j8)).floatValue();
    }

    private static int Z(Object obj, long j8) {
        return ((Integer) m1.C(obj, j8)).intValue();
    }

    private static long a0(Object obj, long j8) {
        return ((Long) m1.C(obj, j8)).longValue();
    }

    private int b0(Object obj, byte[] bArr, int i8, int i9, int i10, long j8, e.a aVar) {
        Unsafe unsafe = f6213r;
        Object t8 = t(i10);
        Object object = unsafe.getObject(obj, j8);
        if (this.f6229p.d(object)) {
            Object b8 = this.f6229p.b(t8);
            this.f6229p.a(b8, object);
            unsafe.putObject(obj, j8, b8);
            object = b8;
        }
        this.f6229p.f(t8);
        return n(bArr, i8, i9, null, this.f6229p.h(object), aVar);
    }

    private int d0(Object obj, byte[] bArr, int i8, int i9, int i10, int i11, int i12, int i13, int i14, long j8, int i15, e.a aVar) {
        Object valueOf;
        Object valueOf2;
        int L;
        long j9;
        int i16;
        Object valueOf3;
        Object Q;
        int O;
        Unsafe unsafe = f6213r;
        long j10 = this.f6214a[i15 + 2] & 1048575;
        switch (i14) {
            case 51:
                if (i12 == 1) {
                    valueOf = Double.valueOf(e.e(bArr, i8));
                    unsafe.putObject(obj, j8, valueOf);
                    L = i8 + 8;
                    unsafe.putInt(obj, j10, i11);
                    return L;
                }
                return i8;
            case 52:
                if (i12 == 5) {
                    valueOf2 = Float.valueOf(e.l(bArr, i8));
                    unsafe.putObject(obj, j8, valueOf2);
                    L = i8 + 4;
                    unsafe.putInt(obj, j10, i11);
                    return L;
                }
                return i8;
            case 53:
            case 54:
                if (i12 == 0) {
                    L = e.L(bArr, i8, aVar);
                    j9 = aVar.f6063b;
                    valueOf3 = Long.valueOf(j9);
                    unsafe.putObject(obj, j8, valueOf3);
                    unsafe.putInt(obj, j10, i11);
                    return L;
                }
                return i8;
            case 55:
            case 62:
                if (i12 == 0) {
                    L = e.I(bArr, i8, aVar);
                    i16 = aVar.f6062a;
                    valueOf3 = Integer.valueOf(i16);
                    unsafe.putObject(obj, j8, valueOf3);
                    unsafe.putInt(obj, j10, i11);
                    return L;
                }
                return i8;
            case 56:
            case 65:
                if (i12 == 1) {
                    valueOf = Long.valueOf(e.j(bArr, i8));
                    unsafe.putObject(obj, j8, valueOf);
                    L = i8 + 8;
                    unsafe.putInt(obj, j10, i11);
                    return L;
                }
                return i8;
            case 57:
            case 64:
                if (i12 == 5) {
                    valueOf2 = Integer.valueOf(e.h(bArr, i8));
                    unsafe.putObject(obj, j8, valueOf2);
                    L = i8 + 4;
                    unsafe.putInt(obj, j10, i11);
                    return L;
                }
                return i8;
            case 58:
                if (i12 == 0) {
                    L = e.L(bArr, i8, aVar);
                    valueOf3 = Boolean.valueOf(aVar.f6063b != 0);
                    unsafe.putObject(obj, j8, valueOf3);
                    unsafe.putInt(obj, j10, i11);
                    return L;
                }
                return i8;
            case 59:
                if (i12 == 2) {
                    L = e.I(bArr, i8, aVar);
                    int i17 = aVar.f6062a;
                    if (i17 == 0) {
                        valueOf3 = "";
                        unsafe.putObject(obj, j8, valueOf3);
                        unsafe.putInt(obj, j10, i11);
                        return L;
                    } else if ((i13 & 536870912) == 0 || n1.m(bArr, L, L + i17)) {
                        unsafe.putObject(obj, j8, new String(bArr, L, i17, z.f6293b));
                        L += i17;
                        unsafe.putInt(obj, j10, i11);
                        return L;
                    } else {
                        throw a0.d();
                    }
                }
                return i8;
            case 60:
                if (i12 == 2) {
                    Q = Q(obj, i11, i15);
                    O = e.O(Q, u(i15), bArr, i8, i9, aVar);
                    r0(obj, i11, i15, Q);
                    return O;
                }
                return i8;
            case 61:
                if (i12 == 2) {
                    L = e.c(bArr, i8, aVar);
                    valueOf3 = aVar.f6064c;
                    unsafe.putObject(obj, j8, valueOf3);
                    unsafe.putInt(obj, j10, i11);
                    return L;
                }
                return i8;
            case 63:
                if (i12 == 0) {
                    L = e.I(bArr, i8, aVar);
                    i16 = aVar.f6062a;
                    s(i15);
                    valueOf3 = Integer.valueOf(i16);
                    unsafe.putObject(obj, j8, valueOf3);
                    unsafe.putInt(obj, j10, i11);
                    return L;
                }
                return i8;
            case 66:
                if (i12 == 0) {
                    L = e.I(bArr, i8, aVar);
                    i16 = i.d(aVar.f6062a);
                    valueOf3 = Integer.valueOf(i16);
                    unsafe.putObject(obj, j8, valueOf3);
                    unsafe.putInt(obj, j10, i11);
                    return L;
                }
                return i8;
            case 67:
                if (i12 == 0) {
                    L = e.L(bArr, i8, aVar);
                    j9 = i.e(aVar.f6063b);
                    valueOf3 = Long.valueOf(j9);
                    unsafe.putObject(obj, j8, valueOf3);
                    unsafe.putInt(obj, j10, i11);
                    return L;
                }
                return i8;
            case 68:
                if (i12 == 3) {
                    Q = Q(obj, i11, i15);
                    O = e.N(Q, u(i15), bArr, i8, i9, (i10 & (-8)) | 4, aVar);
                    r0(obj, i11, i15, Q);
                    return O;
                }
                return i8;
            default:
                return i8;
        }
    }

    private int e0(Object obj, byte[] bArr, int i8, int i9, int i10, int i11, int i12, int i13, long j8, int i14, long j9, e.a aVar) {
        int J;
        Unsafe unsafe = f6213r;
        z.d dVar = (z.d) unsafe.getObject(obj, j9);
        if (!dVar.i()) {
            dVar = dVar.e(dVar.size() * 2);
            unsafe.putObject(obj, j9, dVar);
        }
        switch (i14) {
            case 18:
            case 35:
                if (i12 == 2) {
                    return e.s(bArr, i8, dVar, aVar);
                }
                if (i12 == 1) {
                    return e.f(i10, bArr, i8, i9, dVar, aVar);
                }
                break;
            case 19:
            case 36:
                if (i12 == 2) {
                    return e.v(bArr, i8, dVar, aVar);
                }
                if (i12 == 5) {
                    return e.m(i10, bArr, i8, i9, dVar, aVar);
                }
                break;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i12 == 2) {
                    return e.z(bArr, i8, dVar, aVar);
                }
                if (i12 == 0) {
                    return e.M(i10, bArr, i8, i9, dVar, aVar);
                }
                break;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i12 == 2) {
                    return e.y(bArr, i8, dVar, aVar);
                }
                if (i12 == 0) {
                    return e.J(i10, bArr, i8, i9, dVar, aVar);
                }
                break;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i12 == 2) {
                    return e.u(bArr, i8, dVar, aVar);
                }
                if (i12 == 1) {
                    return e.k(i10, bArr, i8, i9, dVar, aVar);
                }
                break;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i12 == 2) {
                    return e.t(bArr, i8, dVar, aVar);
                }
                if (i12 == 5) {
                    return e.i(i10, bArr, i8, i9, dVar, aVar);
                }
                break;
            case 25:
            case 42:
                if (i12 == 2) {
                    return e.r(bArr, i8, dVar, aVar);
                }
                if (i12 == 0) {
                    return e.b(i10, bArr, i8, i9, dVar, aVar);
                }
                break;
            case 26:
                if (i12 == 2) {
                    int i15 = ((j8 & 536870912) > 0L ? 1 : ((j8 & 536870912) == 0L ? 0 : -1));
                    z.d dVar2 = dVar;
                    return i15 == 0 ? e.D(i10, bArr, i8, i9, dVar2, aVar) : e.E(i10, bArr, i8, i9, dVar2, aVar);
                }
                break;
            case 27:
                if (i12 == 2) {
                    return e.q(u(i13), i10, bArr, i8, i9, dVar, aVar);
                }
                break;
            case 28:
                if (i12 == 2) {
                    return e.d(i10, bArr, i8, i9, dVar, aVar);
                }
                break;
            case 30:
            case 44:
                if (i12 == 2) {
                    J = e.y(bArr, i8, dVar, aVar);
                } else if (i12 == 0) {
                    J = e.J(i10, bArr, i8, i9, dVar, aVar);
                }
                s(i13);
                f1.B(obj, i11, dVar, null, null, this.f6227n);
                return J;
            case 33:
            case 47:
                if (i12 == 2) {
                    return e.w(bArr, i8, dVar, aVar);
                }
                if (i12 == 0) {
                    return e.A(i10, bArr, i8, i9, dVar, aVar);
                }
                break;
            case 34:
            case 48:
                if (i12 == 2) {
                    return e.x(bArr, i8, dVar, aVar);
                }
                if (i12 == 0) {
                    return e.B(i10, bArr, i8, i9, dVar, aVar);
                }
                break;
            case 49:
                if (i12 == 3) {
                    return e.o(u(i13), i10, bArr, i8, i9, dVar, aVar);
                }
                break;
        }
        return i8;
    }

    private int f0(int i8) {
        if (i8 < this.f6216c || i8 > this.f6217d) {
            return -1;
        }
        return p0(i8, 0);
    }

    private int g0(int i8, int i9) {
        if (i8 < this.f6216c || i8 > this.f6217d) {
            return -1;
        }
        return p0(i8, i9);
    }

    private int h0(int i8) {
        return this.f6214a[i8 + 2];
    }

    private void i0(Object obj, long j8, c1 c1Var, d1 d1Var, p pVar) {
        c1Var.I(this.f6226m.b(obj, j8), d1Var, pVar);
    }

    private void j0(Object obj, int i8, c1 c1Var, d1 d1Var, p pVar) {
        c1Var.o(this.f6226m.b(obj, V(i8)), d1Var, pVar);
    }

    private boolean k(Object obj, Object obj2, int i8) {
        return z(obj, i8) == z(obj2, i8);
    }

    private void k0(Object obj, int i8, c1 c1Var) {
        long V;
        Object x7;
        if (y(i8)) {
            V = V(i8);
            x7 = c1Var.K();
        } else if (this.f6220g) {
            V = V(i8);
            x7 = c1Var.n();
        } else {
            V = V(i8);
            x7 = c1Var.x();
        }
        m1.R(obj, V, x7);
    }

    private static boolean l(Object obj, long j8) {
        return m1.r(obj, j8);
    }

    private void l0(Object obj, int i8, c1 c1Var) {
        if (y(i8)) {
            c1Var.w(this.f6226m.b(obj, V(i8)));
        } else {
            c1Var.t(this.f6226m.b(obj, V(i8)));
        }
    }

    private static void m(Object obj) {
        if (F(obj)) {
            return;
        }
        throw new IllegalArgumentException("Mutating immutable message: " + obj);
    }

    private static Field m0(Class cls, String str) {
        Field[] declaredFields;
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException e8) {
            for (Field field : cls.getDeclaredFields()) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields), e8);
        }
    }

    private int n(byte[] bArr, int i8, int i9, i0.a aVar, Map map, e.a aVar2) {
        int I = e.I(bArr, i8, aVar2);
        int i10 = aVar2.f6062a;
        if (i10 < 0 || i10 > i9 - I) {
            throw a0.m();
        }
        throw null;
    }

    private void n0(Object obj, int i8) {
        int h02 = h0(i8);
        long j8 = 1048575 & h02;
        if (j8 == 1048575) {
            return;
        }
        m1.P(obj, j8, (1 << (h02 >>> 20)) | m1.z(obj, j8));
    }

    private static double o(Object obj, long j8) {
        return m1.x(obj, j8);
    }

    private void o0(Object obj, int i8, int i9) {
        m1.P(obj, h0(i9) & 1048575, i8);
    }

    private boolean p(Object obj, Object obj2, int i8) {
        int t02 = t0(i8);
        long V = V(t02);
        switch (s0(t02)) {
            case 0:
                return k(obj, obj2, i8) && Double.doubleToLongBits(m1.x(obj, V)) == Double.doubleToLongBits(m1.x(obj2, V));
            case 1:
                return k(obj, obj2, i8) && Float.floatToIntBits(m1.y(obj, V)) == Float.floatToIntBits(m1.y(obj2, V));
            case 2:
                return k(obj, obj2, i8) && m1.A(obj, V) == m1.A(obj2, V);
            case 3:
                return k(obj, obj2, i8) && m1.A(obj, V) == m1.A(obj2, V);
            case 4:
                return k(obj, obj2, i8) && m1.z(obj, V) == m1.z(obj2, V);
            case 5:
                return k(obj, obj2, i8) && m1.A(obj, V) == m1.A(obj2, V);
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                return k(obj, obj2, i8) && m1.z(obj, V) == m1.z(obj2, V);
            case 7:
                return k(obj, obj2, i8) && m1.r(obj, V) == m1.r(obj2, V);
            case 8:
                return k(obj, obj2, i8) && f1.J(m1.C(obj, V), m1.C(obj2, V));
            case 9:
                return k(obj, obj2, i8) && f1.J(m1.C(obj, V), m1.C(obj2, V));
            case 10:
                return k(obj, obj2, i8) && f1.J(m1.C(obj, V), m1.C(obj2, V));
            case 11:
                return k(obj, obj2, i8) && m1.z(obj, V) == m1.z(obj2, V);
            case 12:
                return k(obj, obj2, i8) && m1.z(obj, V) == m1.z(obj2, V);
            case 13:
                return k(obj, obj2, i8) && m1.z(obj, V) == m1.z(obj2, V);
            case 14:
                return k(obj, obj2, i8) && m1.A(obj, V) == m1.A(obj2, V);
            case 15:
                return k(obj, obj2, i8) && m1.z(obj, V) == m1.z(obj2, V);
            case 16:
                return k(obj, obj2, i8) && m1.A(obj, V) == m1.A(obj2, V);
            case 17:
                return k(obj, obj2, i8) && f1.J(m1.C(obj, V), m1.C(obj2, V));
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
                return f1.J(m1.C(obj, V), m1.C(obj2, V));
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
                return G(obj, obj2, i8) && f1.J(m1.C(obj, V), m1.C(obj2, V));
            default:
                return true;
        }
    }

    private int p0(int i8, int i9) {
        int length = (this.f6214a.length / 3) - 1;
        while (i9 <= length) {
            int i10 = (length + i9) >>> 1;
            int i11 = i10 * 3;
            int U = U(i11);
            if (i8 == U) {
                return i11;
            }
            if (i8 < U) {
                length = i10 - 1;
            } else {
                i9 = i10 + 1;
            }
        }
        return -1;
    }

    private Object q(Object obj, int i8, Object obj2, j1 j1Var, Object obj3) {
        U(i8);
        if (m1.C(obj, V(t0(i8))) == null) {
            return obj2;
        }
        s(i8);
        return obj2;
    }

    private void q0(Object obj, int i8, Object obj2) {
        f6213r.putObject(obj, V(t0(i8)), obj2);
        n0(obj, i8);
    }

    private static float r(Object obj, long j8) {
        return m1.y(obj, j8);
    }

    private void r0(Object obj, int i8, int i9, Object obj2) {
        f6213r.putObject(obj, V(t0(i9)), obj2);
        o0(obj, i8, i9);
    }

    private z.c s(int i8) {
        androidx.appcompat.app.w.a(this.f6215b[((i8 / 3) * 2) + 1]);
        return null;
    }

    private static int s0(int i8) {
        return (i8 & 267386880) >>> 20;
    }

    private Object t(int i8) {
        return this.f6215b[(i8 / 3) * 2];
    }

    private int t0(int i8) {
        return this.f6214a[i8 + 1];
    }

    private d1 u(int i8) {
        int i9 = (i8 / 3) * 2;
        d1 d1Var = (d1) this.f6215b[i9];
        if (d1Var != null) {
            return d1Var;
        }
        d1 c8 = z0.a().c((Class) this.f6215b[i9 + 1]);
        this.f6215b[i9] = c8;
        return c8;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0621  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void u0(java.lang.Object r22, com.google.crypto.tink.shaded.protobuf.p1 r23) {
        /*
            Method dump skipped, instructions count: 1738
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.s0.u0(java.lang.Object, com.google.crypto.tink.shaded.protobuf.p1):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static k1 v(Object obj) {
        x xVar = (x) obj;
        k1 k1Var = xVar.unknownFields;
        if (k1Var == k1.c()) {
            k1 k8 = k1.k();
            xVar.unknownFields = k8;
            return k8;
        }
        return k1Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x052e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void v0(java.lang.Object r11, com.google.crypto.tink.shaded.protobuf.p1 r12) {
        /*
            Method dump skipped, instructions count: 1490
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.s0.v0(java.lang.Object, com.google.crypto.tink.shaded.protobuf.p1):void");
    }

    private int w(j1 j1Var, Object obj) {
        return j1Var.h(j1Var.g(obj));
    }

    private void w0(p1 p1Var, int i8, Object obj, int i9) {
        if (obj != null) {
            this.f6229p.f(t(i9));
            p1Var.x(i8, null, this.f6229p.g(obj));
        }
    }

    private static int x(Object obj, long j8) {
        return m1.z(obj, j8);
    }

    private void x0(int i8, Object obj, p1 p1Var) {
        if (obj instanceof String) {
            p1Var.F(i8, (String) obj);
        } else {
            p1Var.v(i8, (h) obj);
        }
    }

    private static boolean y(int i8) {
        return (i8 & 536870912) != 0;
    }

    private void y0(j1 j1Var, Object obj, p1 p1Var) {
        j1Var.t(j1Var.g(obj), p1Var);
    }

    private boolean z(Object obj, int i8) {
        int h02 = h0(i8);
        long j8 = 1048575 & h02;
        if (j8 != 1048575) {
            return (m1.z(obj, j8) & (1 << (h02 >>> 20))) != 0;
        }
        int t02 = t0(i8);
        long V = V(t02);
        switch (s0(t02)) {
            case 0:
                return Double.doubleToRawLongBits(m1.x(obj, V)) != 0;
            case 1:
                return Float.floatToRawIntBits(m1.y(obj, V)) != 0;
            case 2:
                return m1.A(obj, V) != 0;
            case 3:
                return m1.A(obj, V) != 0;
            case 4:
                return m1.z(obj, V) != 0;
            case 5:
                return m1.A(obj, V) != 0;
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                return m1.z(obj, V) != 0;
            case 7:
                return m1.r(obj, V);
            case 8:
                Object C = m1.C(obj, V);
                if (C instanceof String) {
                    return !((String) C).isEmpty();
                }
                if (C instanceof h) {
                    return !h.f6072f.equals(C);
                }
                throw new IllegalArgumentException();
            case 9:
                return m1.C(obj, V) != null;
            case 10:
                return !h.f6072f.equals(m1.C(obj, V));
            case 11:
                return m1.z(obj, V) != 0;
            case 12:
                return m1.z(obj, V) != 0;
            case 13:
                return m1.z(obj, V) != 0;
            case 14:
                return m1.A(obj, V) != 0;
            case 15:
                return m1.z(obj, V) != 0;
            case 16:
                return m1.A(obj, V) != 0;
            case 17:
                return m1.C(obj, V) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.d1
    public void a(Object obj, Object obj2) {
        m(obj);
        obj2.getClass();
        for (int i8 = 0; i8 < this.f6214a.length; i8 += 3) {
            O(obj, obj2, i8);
        }
        f1.H(this.f6227n, obj, obj2);
        if (this.f6219f) {
            f1.F(this.f6228o, obj, obj2);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.d1
    public void b(Object obj, byte[] bArr, int i8, int i9, e.a aVar) {
        c0(obj, bArr, i8, i9, 0, aVar);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.d1
    public void c(Object obj, p1 p1Var) {
        if (p1Var.A() == p1.a.DESCENDING) {
            v0(obj, p1Var);
        } else {
            u0(obj, p1Var);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int c0(Object obj, byte[] bArr, int i8, int i9, int i10, e.a aVar) {
        Unsafe unsafe;
        int i11;
        s0 s0Var;
        int i12;
        int i13;
        int i14;
        int i15;
        Object obj2;
        byte b8;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        char c8;
        byte[] bArr2;
        byte[] bArr3;
        long j8;
        int L;
        Unsafe unsafe2;
        Object obj3;
        long j9;
        int i23;
        long j10;
        long j11;
        int i24;
        s0 s0Var2 = this;
        Object obj4 = obj;
        byte[] bArr4 = bArr;
        int i25 = i9;
        int i26 = i10;
        e.a aVar2 = aVar;
        m(obj);
        Unsafe unsafe3 = f6213r;
        int i27 = i8;
        int i28 = 0;
        int i29 = 0;
        int i30 = 0;
        int i31 = -1;
        int i32 = 1048575;
        while (true) {
            if (i27 < i25) {
                int i33 = i27 + 1;
                byte b9 = bArr4[i27];
                if (b9 < 0) {
                    int H = e.H(b9, bArr4, i33, aVar2);
                    b8 = aVar2.f6062a;
                    i33 = H;
                } else {
                    b8 = b9;
                }
                int i34 = b8 >>> 3;
                int i35 = b8 & 7;
                int g02 = i34 > i31 ? s0Var2.g0(i34, i28 / 3) : s0Var2.f0(i34);
                if (g02 == -1) {
                    i16 = i34;
                    i17 = i33;
                    i13 = b8;
                    i18 = i30;
                    i19 = i32;
                    unsafe = unsafe3;
                    i11 = i26;
                    i20 = 0;
                } else {
                    int i36 = s0Var2.f6214a[g02 + 1];
                    int s02 = s0(i36);
                    long V = V(i36);
                    int i37 = b8;
                    if (s02 <= 17) {
                        int i38 = s0Var2.f6214a[g02 + 2];
                        int i39 = 1 << (i38 >>> 20);
                        int i40 = 1048575;
                        int i41 = i38 & 1048575;
                        if (i41 != i32) {
                            if (i32 != 1048575) {
                                unsafe3.putInt(obj4, i32, i30);
                                i40 = 1048575;
                            }
                            i19 = i41;
                            i21 = i41 == i40 ? 0 : unsafe3.getInt(obj4, i41);
                        } else {
                            i21 = i30;
                            i19 = i32;
                        }
                        switch (s02) {
                            case 0:
                                bArr2 = bArr;
                                i16 = i34;
                                i22 = g02;
                                c8 = 65535;
                                if (i35 == 1) {
                                    m1.N(obj4, V, e.e(bArr2, i33));
                                    i27 = i33 + 8;
                                    i30 = i21 | i39;
                                    i26 = i10;
                                    i28 = i22;
                                    bArr4 = bArr2;
                                    i29 = i37;
                                    i32 = i19;
                                    i31 = i16;
                                    break;
                                } else {
                                    i11 = i10;
                                    i17 = i33;
                                    unsafe = unsafe3;
                                    i18 = i21;
                                    i13 = i37;
                                    i20 = i22;
                                    break;
                                }
                            case 1:
                                bArr2 = bArr;
                                i16 = i34;
                                i22 = g02;
                                c8 = 65535;
                                if (i35 == 5) {
                                    m1.O(obj4, V, e.l(bArr2, i33));
                                    i27 = i33 + 4;
                                    i30 = i21 | i39;
                                    i26 = i10;
                                    i28 = i22;
                                    bArr4 = bArr2;
                                    i29 = i37;
                                    i32 = i19;
                                    i31 = i16;
                                    break;
                                } else {
                                    i11 = i10;
                                    i17 = i33;
                                    unsafe = unsafe3;
                                    i18 = i21;
                                    i13 = i37;
                                    i20 = i22;
                                    break;
                                }
                            case 2:
                            case 3:
                                bArr3 = bArr;
                                i16 = i34;
                                i22 = g02;
                                j8 = V;
                                c8 = 65535;
                                if (i35 == 0) {
                                    L = e.L(bArr3, i33, aVar2);
                                    unsafe2 = unsafe3;
                                    obj3 = obj;
                                    j9 = aVar2.f6063b;
                                    unsafe2.putLong(obj3, j8, j9);
                                    i30 = i21 | i39;
                                    i26 = i10;
                                    i28 = i22;
                                    bArr4 = bArr3;
                                    i27 = L;
                                    i29 = i37;
                                    i32 = i19;
                                    i31 = i16;
                                    break;
                                } else {
                                    i11 = i10;
                                    i17 = i33;
                                    unsafe = unsafe3;
                                    i18 = i21;
                                    i13 = i37;
                                    i20 = i22;
                                    break;
                                }
                            case 4:
                            case 11:
                                bArr2 = bArr;
                                i16 = i34;
                                i22 = g02;
                                c8 = 65535;
                                if (i35 == 0) {
                                    i27 = e.I(bArr2, i33, aVar2);
                                    i23 = aVar2.f6062a;
                                    j10 = V;
                                    unsafe3.putInt(obj4, j10, i23);
                                    i30 = i21 | i39;
                                    i26 = i10;
                                    i28 = i22;
                                    bArr4 = bArr2;
                                    i29 = i37;
                                    i32 = i19;
                                    i31 = i16;
                                    break;
                                } else {
                                    i11 = i10;
                                    i17 = i33;
                                    unsafe = unsafe3;
                                    i18 = i21;
                                    i13 = i37;
                                    i20 = i22;
                                    break;
                                }
                            case 5:
                            case 14:
                                bArr2 = bArr;
                                i16 = i34;
                                i22 = g02;
                                c8 = 65535;
                                if (i35 == 1) {
                                    unsafe3.putLong(obj, V, e.j(bArr2, i33));
                                    i27 = i33 + 8;
                                    i30 = i21 | i39;
                                    i26 = i10;
                                    i28 = i22;
                                    bArr4 = bArr2;
                                    i29 = i37;
                                    i32 = i19;
                                    i31 = i16;
                                    break;
                                } else {
                                    i11 = i10;
                                    i17 = i33;
                                    unsafe = unsafe3;
                                    i18 = i21;
                                    i13 = i37;
                                    i20 = i22;
                                    break;
                                }
                            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                            case 13:
                                bArr2 = bArr;
                                i16 = i34;
                                i22 = g02;
                                c8 = 65535;
                                if (i35 == 5) {
                                    unsafe3.putInt(obj4, V, e.h(bArr2, i33));
                                    i27 = i33 + 4;
                                    i30 = i21 | i39;
                                    i26 = i10;
                                    i28 = i22;
                                    bArr4 = bArr2;
                                    i29 = i37;
                                    i32 = i19;
                                    i31 = i16;
                                    break;
                                } else {
                                    i11 = i10;
                                    i17 = i33;
                                    unsafe = unsafe3;
                                    i18 = i21;
                                    i13 = i37;
                                    i20 = i22;
                                    break;
                                }
                            case 7:
                                bArr2 = bArr;
                                i16 = i34;
                                i22 = g02;
                                c8 = 65535;
                                if (i35 == 0) {
                                    i27 = e.L(bArr2, i33, aVar2);
                                    m1.H(obj4, V, aVar2.f6063b != 0);
                                    i30 = i21 | i39;
                                    i26 = i10;
                                    i28 = i22;
                                    bArr4 = bArr2;
                                    i29 = i37;
                                    i32 = i19;
                                    i31 = i16;
                                    break;
                                } else {
                                    i11 = i10;
                                    i17 = i33;
                                    unsafe = unsafe3;
                                    i18 = i21;
                                    i13 = i37;
                                    i20 = i22;
                                    break;
                                }
                            case 8:
                                bArr2 = bArr;
                                i16 = i34;
                                i22 = g02;
                                j11 = V;
                                c8 = 65535;
                                if (i35 == 2) {
                                    i27 = y(i36) ? e.F(bArr2, i33, aVar2) : e.C(bArr2, i33, aVar2);
                                    unsafe3.putObject(obj4, j11, aVar2.f6064c);
                                    i30 = i21 | i39;
                                    i26 = i10;
                                    i28 = i22;
                                    bArr4 = bArr2;
                                    i29 = i37;
                                    i32 = i19;
                                    i31 = i16;
                                    break;
                                } else {
                                    i11 = i10;
                                    i17 = i33;
                                    unsafe = unsafe3;
                                    i18 = i21;
                                    i13 = i37;
                                    i20 = i22;
                                    break;
                                }
                            case 9:
                                bArr2 = bArr;
                                i16 = i34;
                                i22 = g02;
                                c8 = 65535;
                                if (i35 == 2) {
                                    Object P = s0Var2.P(obj4, i22);
                                    i27 = e.O(P, s0Var2.u(i22), bArr, i33, i9, aVar);
                                    s0Var2.q0(obj4, i22, P);
                                    i30 = i21 | i39;
                                    i26 = i10;
                                    i28 = i22;
                                    bArr4 = bArr2;
                                    i29 = i37;
                                    i32 = i19;
                                    i31 = i16;
                                    break;
                                } else {
                                    i11 = i10;
                                    i17 = i33;
                                    unsafe = unsafe3;
                                    i18 = i21;
                                    i13 = i37;
                                    i20 = i22;
                                    break;
                                }
                            case 10:
                                bArr2 = bArr;
                                i16 = i34;
                                i22 = g02;
                                j11 = V;
                                c8 = 65535;
                                if (i35 == 2) {
                                    i27 = e.c(bArr2, i33, aVar2);
                                    unsafe3.putObject(obj4, j11, aVar2.f6064c);
                                    i30 = i21 | i39;
                                    i26 = i10;
                                    i28 = i22;
                                    bArr4 = bArr2;
                                    i29 = i37;
                                    i32 = i19;
                                    i31 = i16;
                                    break;
                                } else {
                                    i11 = i10;
                                    i17 = i33;
                                    unsafe = unsafe3;
                                    i18 = i21;
                                    i13 = i37;
                                    i20 = i22;
                                    break;
                                }
                            case 12:
                                bArr2 = bArr;
                                i16 = i34;
                                i22 = g02;
                                c8 = 65535;
                                if (i35 == 0) {
                                    i27 = e.I(bArr2, i33, aVar2);
                                    int i42 = aVar2.f6062a;
                                    s0Var2.s(i22);
                                    C(i36);
                                    unsafe3.putInt(obj4, V, i42);
                                    i30 = i21 | i39;
                                    i26 = i10;
                                    i28 = i22;
                                    bArr4 = bArr2;
                                    i29 = i37;
                                    i32 = i19;
                                    i31 = i16;
                                    break;
                                } else {
                                    i11 = i10;
                                    i17 = i33;
                                    unsafe = unsafe3;
                                    i18 = i21;
                                    i13 = i37;
                                    i20 = i22;
                                    break;
                                }
                            case 15:
                                bArr2 = bArr;
                                i16 = i34;
                                i22 = g02;
                                j10 = V;
                                c8 = 65535;
                                if (i35 == 0) {
                                    i27 = e.I(bArr2, i33, aVar2);
                                    i23 = i.d(aVar2.f6062a);
                                    unsafe3.putInt(obj4, j10, i23);
                                    i30 = i21 | i39;
                                    i26 = i10;
                                    i28 = i22;
                                    bArr4 = bArr2;
                                    i29 = i37;
                                    i32 = i19;
                                    i31 = i16;
                                    break;
                                } else {
                                    i11 = i10;
                                    i17 = i33;
                                    unsafe = unsafe3;
                                    i18 = i21;
                                    i13 = i37;
                                    i20 = i22;
                                    break;
                                }
                            case 16:
                                i16 = i34;
                                i22 = g02;
                                c8 = 65535;
                                bArr3 = bArr;
                                if (i35 == 0) {
                                    j8 = V;
                                    L = e.L(bArr3, i33, aVar2);
                                    j9 = i.e(aVar2.f6063b);
                                    unsafe2 = unsafe3;
                                    obj3 = obj;
                                    unsafe2.putLong(obj3, j8, j9);
                                    i30 = i21 | i39;
                                    i26 = i10;
                                    i28 = i22;
                                    bArr4 = bArr3;
                                    i27 = L;
                                    i29 = i37;
                                    i32 = i19;
                                    i31 = i16;
                                    break;
                                } else {
                                    i11 = i10;
                                    i17 = i33;
                                    unsafe = unsafe3;
                                    i18 = i21;
                                    i13 = i37;
                                    i20 = i22;
                                    break;
                                }
                            case 17:
                                if (i35 == 3) {
                                    Object P2 = s0Var2.P(obj4, g02);
                                    i16 = i34;
                                    i27 = e.N(P2, s0Var2.u(g02), bArr, i33, i9, (i34 << 3) | 4, aVar);
                                    s0Var2.q0(obj4, g02, P2);
                                    i30 = i21 | i39;
                                    bArr4 = bArr;
                                    i26 = i10;
                                    i28 = g02;
                                    i29 = i37;
                                    i32 = i19;
                                    i31 = i16;
                                    break;
                                } else {
                                    i16 = i34;
                                    c8 = 65535;
                                    i22 = g02;
                                    i11 = i10;
                                    i17 = i33;
                                    unsafe = unsafe3;
                                    i18 = i21;
                                    i13 = i37;
                                    i20 = i22;
                                    break;
                                }
                            default:
                                i16 = i34;
                                i22 = g02;
                                c8 = 65535;
                                i11 = i10;
                                i17 = i33;
                                unsafe = unsafe3;
                                i18 = i21;
                                i13 = i37;
                                i20 = i22;
                                break;
                        }
                    } else {
                        i16 = i34;
                        int i43 = i32;
                        i18 = i30;
                        if (s02 != 27) {
                            i19 = i43;
                            if (s02 <= 49) {
                                int i44 = i33;
                                unsafe = unsafe3;
                                i20 = g02;
                                i27 = e0(obj, bArr, i33, i9, i37, i16, i35, g02, i36, s02, V, aVar);
                                if (i27 != i44) {
                                    s0Var2 = this;
                                    obj4 = obj;
                                    bArr4 = bArr;
                                    i25 = i9;
                                    i26 = i10;
                                    aVar2 = aVar;
                                    i28 = i20;
                                    i29 = i37;
                                    i30 = i18;
                                    i32 = i19;
                                    i31 = i16;
                                    unsafe3 = unsafe;
                                } else {
                                    i11 = i10;
                                    i17 = i27;
                                }
                            } else {
                                i24 = i33;
                                unsafe = unsafe3;
                                i20 = g02;
                                if (s02 != 50) {
                                    i27 = d0(obj, bArr, i24, i9, i37, i16, i35, i36, s02, V, i20, aVar);
                                    if (i27 != i24) {
                                        s0Var2 = this;
                                        obj4 = obj;
                                        bArr4 = bArr;
                                        i25 = i9;
                                        i26 = i10;
                                        aVar2 = aVar;
                                        i28 = i20;
                                        i29 = i37;
                                        i30 = i18;
                                        i32 = i19;
                                        i31 = i16;
                                        unsafe3 = unsafe;
                                    } else {
                                        i11 = i10;
                                        i17 = i27;
                                    }
                                } else if (i35 == 2) {
                                    i27 = b0(obj, bArr, i24, i9, i20, V, aVar);
                                    if (i27 != i24) {
                                        s0Var2 = this;
                                        obj4 = obj;
                                        bArr4 = bArr;
                                        i25 = i9;
                                        i26 = i10;
                                        aVar2 = aVar;
                                        i28 = i20;
                                        i29 = i37;
                                        i30 = i18;
                                        i32 = i19;
                                        i31 = i16;
                                        unsafe3 = unsafe;
                                    } else {
                                        i11 = i10;
                                        i17 = i27;
                                    }
                                } else {
                                    i11 = i10;
                                    i17 = i24;
                                }
                            }
                        } else if (i35 == 2) {
                            z.d dVar = (z.d) unsafe3.getObject(obj4, V);
                            if (!dVar.i()) {
                                int size = dVar.size();
                                dVar = dVar.e(size == 0 ? 10 : size * 2);
                                unsafe3.putObject(obj4, V, dVar);
                            }
                            i27 = e.q(s0Var2.u(g02), i37, bArr, i33, i9, dVar, aVar);
                            i26 = i10;
                            i28 = g02;
                            i29 = i37;
                            i30 = i18;
                            i32 = i43;
                            i31 = i16;
                            bArr4 = bArr;
                        } else {
                            i19 = i43;
                            i24 = i33;
                            unsafe = unsafe3;
                            i20 = g02;
                            i11 = i10;
                            i17 = i24;
                        }
                        i13 = i37;
                    }
                }
                if (i13 != i11 || i11 == 0) {
                    i27 = (!this.f6219f || aVar.f6065d == p.b()) ? e.G(i13, bArr, i17, i9, v(obj), aVar) : e.g(i13, bArr, i17, i9, obj, this.f6218e, this.f6227n, aVar);
                    obj4 = obj;
                    bArr4 = bArr;
                    i25 = i9;
                    i29 = i13;
                    s0Var2 = this;
                    aVar2 = aVar;
                    i28 = i20;
                    i30 = i18;
                    i32 = i19;
                    i31 = i16;
                    unsafe3 = unsafe;
                    i26 = i11;
                } else {
                    i15 = 1048575;
                    s0Var = this;
                    i12 = i17;
                    i30 = i18;
                    i14 = i19;
                }
            } else {
                int i45 = i32;
                unsafe = unsafe3;
                i11 = i26;
                s0Var = s0Var2;
                i12 = i27;
                i13 = i29;
                i14 = i45;
                i15 = 1048575;
            }
        }
        if (i14 != i15) {
            obj2 = obj;
            unsafe.putInt(obj2, i14, i30);
        } else {
            obj2 = obj;
        }
        k1 k1Var = null;
        for (int i46 = s0Var.f6223j; i46 < s0Var.f6224k; i46++) {
            k1Var = (k1) q(obj, s0Var.f6222i[i46], k1Var, s0Var.f6227n, obj);
        }
        if (k1Var != null) {
            s0Var.f6227n.o(obj2, k1Var);
        }
        if (i11 == 0) {
            if (i12 != i9) {
                throw a0.h();
            }
        } else if (i12 > i9 || i13 != i11) {
            throw a0.h();
        }
        return i12;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.d1
    public void d(Object obj, c1 c1Var, p pVar) {
        pVar.getClass();
        m(obj);
        K(this.f6227n, this.f6228o, obj, c1Var, pVar);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.d1
    public void e(Object obj) {
        int i8;
        if (F(obj)) {
            if (obj instanceof x) {
                x xVar = (x) obj;
                xVar.n();
                xVar.m();
                xVar.F();
            }
            int length = this.f6214a.length;
            while (i8 < length) {
                int t02 = t0(i8);
                long V = V(t02);
                int s02 = s0(t02);
                if (s02 != 9) {
                    if (s02 != 60 && s02 != 68) {
                        switch (s02) {
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
                                this.f6226m.a(obj, V);
                                break;
                            case 50:
                                Unsafe unsafe = f6213r;
                                Object object = unsafe.getObject(obj, V);
                                if (object == null) {
                                    break;
                                } else {
                                    unsafe.putObject(obj, V, this.f6229p.e(object));
                                    break;
                                }
                        }
                    } else {
                        if (!H(obj, U(i8), i8)) {
                        }
                        u(i8).e(f6213r.getObject(obj, V));
                    }
                }
                i8 = z(obj, i8) ? 0 : i8 + 3;
                u(i8).e(f6213r.getObject(obj, V));
            }
            this.f6227n.j(obj);
            if (this.f6219f) {
                this.f6228o.f(obj);
            }
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.d1
    public final boolean f(Object obj) {
        int i8;
        int i9;
        int i10 = 1048575;
        int i11 = 0;
        int i12 = 0;
        while (i12 < this.f6223j) {
            int i13 = this.f6222i[i12];
            int U = U(i13);
            int t02 = t0(i13);
            int i14 = this.f6214a[i13 + 2];
            int i15 = i14 & 1048575;
            int i16 = 1 << (i14 >>> 20);
            if (i15 != i10) {
                if (i15 != 1048575) {
                    i11 = f6213r.getInt(obj, i15);
                }
                i9 = i11;
                i8 = i15;
            } else {
                i8 = i10;
                i9 = i11;
            }
            if (I(t02) && !A(obj, i13, i8, i9, i16)) {
                return false;
            }
            int s02 = s0(t02);
            if (s02 != 9 && s02 != 17) {
                if (s02 != 27) {
                    if (s02 == 60 || s02 == 68) {
                        if (H(obj, U, i13) && !B(obj, t02, u(i13))) {
                            return false;
                        }
                    } else if (s02 != 49) {
                        if (s02 == 50 && !E(obj, t02, i13)) {
                            return false;
                        }
                    }
                }
                if (!D(obj, t02, i13)) {
                    return false;
                }
            } else if (A(obj, i13, i8, i9, i16) && !B(obj, t02, u(i13))) {
                return false;
            }
            i12++;
            i10 = i8;
            i11 = i9;
        }
        return !this.f6219f || this.f6228o.c(obj).e();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.d1
    public boolean g(Object obj, Object obj2) {
        int length = this.f6214a.length;
        for (int i8 = 0; i8 < length; i8 += 3) {
            if (!p(obj, obj2, i8)) {
                return false;
            }
        }
        if (this.f6227n.g(obj).equals(this.f6227n.g(obj2))) {
            if (this.f6219f) {
                return this.f6228o.c(obj).equals(this.f6228o.c(obj2));
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x01eb, code lost:
        if (r18.f6221h != false) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x01fd, code lost:
        if (r18.f6221h != false) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x020f, code lost:
        if (r18.f6221h != false) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0221, code lost:
        if (r18.f6221h != false) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x0233, code lost:
        if (r18.f6221h != false) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x0244, code lost:
        if (r18.f6221h != false) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x0255, code lost:
        if (r18.f6221h != false) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x0266, code lost:
        if (r18.f6221h != false) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x0277, code lost:
        if (r18.f6221h != false) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x0288, code lost:
        if (r18.f6221h != false) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x0299, code lost:
        if (r18.f6221h != false) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x029b, code lost:
        r8.putInt(r19, r0, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x029f, code lost:
        r0 = (com.google.crypto.tink.shaded.protobuf.k.J(r13) + com.google.crypto.tink.shaded.protobuf.k.L(r1)) + r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x01b5, code lost:
        if (r18.f6221h != false) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x01c7, code lost:
        if (r18.f6221h != false) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01d9, code lost:
        if (r18.f6221h != false) goto L83;
     */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r9v16 */
    @Override // com.google.crypto.tink.shaded.protobuf.d1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int h(java.lang.Object r19) {
        /*
            Method dump skipped, instructions count: 1502
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.s0.h(java.lang.Object):int");
    }

    @Override // com.google.crypto.tink.shaded.protobuf.d1
    public Object i() {
        return this.f6225l.a(this.f6218e);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00cc, code lost:
        if (r3 != null) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00de, code lost:
        if (r3 != null) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00e0, code lost:
        r7 = r3.hashCode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00e4, code lost:
        r2 = (r2 * 53) + r7;
     */
    @Override // com.google.crypto.tink.shaded.protobuf.d1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int j(java.lang.Object r9) {
        /*
            Method dump skipped, instructions count: 474
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.s0.j(java.lang.Object):int");
    }
}
