package com.google.crypto.tink.shaded.protobuf;

import java.lang.reflect.Type;
/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum j uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:444)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:368)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:333)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:318)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:258)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:289)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes.dex */
public final class u {
    public static final u A;
    public static final u B;
    public static final u C;
    public static final u D;
    public static final u E;
    public static final u F;
    public static final u G;
    public static final u H;
    public static final u I;
    public static final u J;
    public static final u K;
    public static final u L;
    public static final u M;
    public static final u N;
    public static final u O;
    public static final u P;
    public static final u Q;
    public static final u R;
    public static final u S;
    public static final u T;
    public static final u U;
    public static final u V;
    public static final u W;
    public static final u X;
    public static final u Y;
    public static final u Z;

    /* renamed from: a0  reason: collision with root package name */
    public static final u f6234a0;

    /* renamed from: b0  reason: collision with root package name */
    public static final u f6235b0;

    /* renamed from: c0  reason: collision with root package name */
    public static final u f6236c0;

    /* renamed from: d0  reason: collision with root package name */
    public static final u f6237d0;

    /* renamed from: e0  reason: collision with root package name */
    public static final u f6238e0;

    /* renamed from: f0  reason: collision with root package name */
    public static final u f6239f0;

    /* renamed from: g0  reason: collision with root package name */
    public static final u f6240g0;

    /* renamed from: h0  reason: collision with root package name */
    public static final u f6241h0;

    /* renamed from: i0  reason: collision with root package name */
    private static final u[] f6242i0;

    /* renamed from: j  reason: collision with root package name */
    public static final u f6243j;

    /* renamed from: j0  reason: collision with root package name */
    private static final Type[] f6244j0;

    /* renamed from: k  reason: collision with root package name */
    public static final u f6245k;

    /* renamed from: k0  reason: collision with root package name */
    private static final /* synthetic */ u[] f6246k0;

    /* renamed from: l  reason: collision with root package name */
    public static final u f6247l;

    /* renamed from: m  reason: collision with root package name */
    public static final u f6248m;

    /* renamed from: n  reason: collision with root package name */
    public static final u f6249n;

    /* renamed from: o  reason: collision with root package name */
    public static final u f6250o;

    /* renamed from: p  reason: collision with root package name */
    public static final u f6251p;

    /* renamed from: q  reason: collision with root package name */
    public static final u f6252q;

    /* renamed from: r  reason: collision with root package name */
    public static final u f6253r;

    /* renamed from: s  reason: collision with root package name */
    public static final u f6254s;

    /* renamed from: t  reason: collision with root package name */
    public static final u f6255t;

    /* renamed from: u  reason: collision with root package name */
    public static final u f6256u;

    /* renamed from: v  reason: collision with root package name */
    public static final u f6257v;

    /* renamed from: w  reason: collision with root package name */
    public static final u f6258w;

    /* renamed from: x  reason: collision with root package name */
    public static final u f6259x;

    /* renamed from: y  reason: collision with root package name */
    public static final u f6260y;

    /* renamed from: z  reason: collision with root package name */
    public static final u f6261z;

    /* renamed from: e  reason: collision with root package name */
    private final b0 f6262e;

    /* renamed from: f  reason: collision with root package name */
    private final int f6263f;

    /* renamed from: g  reason: collision with root package name */
    private final b f6264g;

    /* renamed from: h  reason: collision with root package name */
    private final Class f6265h;

    /* renamed from: i  reason: collision with root package name */
    private final boolean f6266i;

    /* loaded from: classes.dex */
    static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f6267a;

        static {
            int[] iArr = new int[b0.values().length];
            f6267a = iArr;
            try {
                iArr[b0.f6046o.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f6267a[b0.f6048q.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f6267a[b0.f6045n.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes.dex */
    enum b {
        SCALAR(false),
        VECTOR(true),
        PACKED_VECTOR(true),
        MAP(false);
        

        /* renamed from: e  reason: collision with root package name */
        private final boolean f6273e;

        b(boolean z7) {
            this.f6273e = z7;
        }
    }

    static {
        b bVar = b.SCALAR;
        b0 b0Var = b0.f6043l;
        f6243j = new u("DOUBLE", 0, 0, bVar, b0Var);
        b0 b0Var2 = b0.f6042k;
        f6245k = new u("FLOAT", 1, 1, bVar, b0Var2);
        b0 b0Var3 = b0.f6041j;
        f6247l = new u("INT64", 2, 2, bVar, b0Var3);
        f6248m = new u("UINT64", 3, 3, bVar, b0Var3);
        b0 b0Var4 = b0.f6040i;
        f6249n = new u("INT32", 4, 4, bVar, b0Var4);
        f6250o = new u("FIXED64", 5, 5, bVar, b0Var3);
        f6251p = new u("FIXED32", 6, 6, bVar, b0Var4);
        b0 b0Var5 = b0.f6044m;
        f6252q = new u("BOOL", 7, 7, bVar, b0Var5);
        b0 b0Var6 = b0.f6045n;
        f6253r = new u("STRING", 8, 8, bVar, b0Var6);
        b0 b0Var7 = b0.f6048q;
        f6254s = new u("MESSAGE", 9, 9, bVar, b0Var7);
        b0 b0Var8 = b0.f6046o;
        f6255t = new u("BYTES", 10, 10, bVar, b0Var8);
        f6256u = new u("UINT32", 11, 11, bVar, b0Var4);
        b0 b0Var9 = b0.f6047p;
        f6257v = new u("ENUM", 12, 12, bVar, b0Var9);
        f6258w = new u("SFIXED32", 13, 13, bVar, b0Var4);
        f6259x = new u("SFIXED64", 14, 14, bVar, b0Var3);
        f6260y = new u("SINT32", 15, 15, bVar, b0Var4);
        f6261z = new u("SINT64", 16, 16, bVar, b0Var3);
        A = new u("GROUP", 17, 17, bVar, b0Var7);
        b bVar2 = b.VECTOR;
        B = new u("DOUBLE_LIST", 18, 18, bVar2, b0Var);
        C = new u("FLOAT_LIST", 19, 19, bVar2, b0Var2);
        D = new u("INT64_LIST", 20, 20, bVar2, b0Var3);
        E = new u("UINT64_LIST", 21, 21, bVar2, b0Var3);
        F = new u("INT32_LIST", 22, 22, bVar2, b0Var4);
        G = new u("FIXED64_LIST", 23, 23, bVar2, b0Var3);
        H = new u("FIXED32_LIST", 24, 24, bVar2, b0Var4);
        I = new u("BOOL_LIST", 25, 25, bVar2, b0Var5);
        J = new u("STRING_LIST", 26, 26, bVar2, b0Var6);
        K = new u("MESSAGE_LIST", 27, 27, bVar2, b0Var7);
        L = new u("BYTES_LIST", 28, 28, bVar2, b0Var8);
        M = new u("UINT32_LIST", 29, 29, bVar2, b0Var4);
        N = new u("ENUM_LIST", 30, 30, bVar2, b0Var9);
        O = new u("SFIXED32_LIST", 31, 31, bVar2, b0Var4);
        P = new u("SFIXED64_LIST", 32, 32, bVar2, b0Var3);
        Q = new u("SINT32_LIST", 33, 33, bVar2, b0Var4);
        R = new u("SINT64_LIST", 34, 34, bVar2, b0Var3);
        b bVar3 = b.PACKED_VECTOR;
        S = new u("DOUBLE_LIST_PACKED", 35, 35, bVar3, b0Var);
        T = new u("FLOAT_LIST_PACKED", 36, 36, bVar3, b0Var2);
        U = new u("INT64_LIST_PACKED", 37, 37, bVar3, b0Var3);
        V = new u("UINT64_LIST_PACKED", 38, 38, bVar3, b0Var3);
        W = new u("INT32_LIST_PACKED", 39, 39, bVar3, b0Var4);
        X = new u("FIXED64_LIST_PACKED", 40, 40, bVar3, b0Var3);
        Y = new u("FIXED32_LIST_PACKED", 41, 41, bVar3, b0Var4);
        Z = new u("BOOL_LIST_PACKED", 42, 42, bVar3, b0Var5);
        f6234a0 = new u("UINT32_LIST_PACKED", 43, 43, bVar3, b0Var4);
        f6235b0 = new u("ENUM_LIST_PACKED", 44, 44, bVar3, b0Var9);
        f6236c0 = new u("SFIXED32_LIST_PACKED", 45, 45, bVar3, b0Var4);
        f6237d0 = new u("SFIXED64_LIST_PACKED", 46, 46, bVar3, b0Var3);
        f6238e0 = new u("SINT32_LIST_PACKED", 47, 47, bVar3, b0Var4);
        f6239f0 = new u("SINT64_LIST_PACKED", 48, 48, bVar3, b0Var3);
        f6240g0 = new u("GROUP_LIST", 49, 49, bVar2, b0Var7);
        f6241h0 = new u("MAP", 50, 50, b.MAP, b0.f6039h);
        f6246k0 = d();
        f6244j0 = new Type[0];
        u[] values = values();
        f6242i0 = new u[values.length];
        for (u uVar : values) {
            f6242i0[uVar.f6263f] = uVar;
        }
    }

    private u(String str, int i8, int i9, b bVar, b0 b0Var) {
        int i10;
        this.f6263f = i9;
        this.f6264g = bVar;
        this.f6262e = b0Var;
        int ordinal = bVar.ordinal();
        boolean z7 = true;
        this.f6265h = (ordinal == 1 || ordinal == 3) ? b0Var.e() : null;
        this.f6266i = (bVar != b.SCALAR || (i10 = a.f6267a[b0Var.ordinal()]) == 1 || i10 == 2 || i10 == 3) ? false : false;
    }

    private static /* synthetic */ u[] d() {
        return new u[]{f6243j, f6245k, f6247l, f6248m, f6249n, f6250o, f6251p, f6252q, f6253r, f6254s, f6255t, f6256u, f6257v, f6258w, f6259x, f6260y, f6261z, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, f6234a0, f6235b0, f6236c0, f6237d0, f6238e0, f6239f0, f6240g0, f6241h0};
    }

    public static u valueOf(String str) {
        return (u) Enum.valueOf(u.class, str);
    }

    public static u[] values() {
        return (u[]) f6246k0.clone();
    }

    public int e() {
        return this.f6263f;
    }
}
