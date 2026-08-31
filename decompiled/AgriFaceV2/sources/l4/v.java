package l4;

import java.security.GeneralSecurityException;
import k4.z;
import t4.f;
import t4.g;
import t4.w;
import t4.x;
import y4.y;
/* loaded from: classes.dex */
public abstract class v {

    /* renamed from: a  reason: collision with root package name */
    private static final a5.a f9042a;

    /* renamed from: b  reason: collision with root package name */
    private static final t4.x f9043b;

    /* renamed from: c  reason: collision with root package name */
    private static final t4.w f9044c;

    /* renamed from: d  reason: collision with root package name */
    private static final t4.g f9045d;

    /* renamed from: e  reason: collision with root package name */
    private static final t4.f f9046e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9047a;

        static {
            int[] iArr = new int[y4.i0.values().length];
            f9047a = iArr;
            try {
                iArr[y4.i0.TINK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9047a[y4.i0.CRUNCHY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9047a[y4.i0.LEGACY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9047a[y4.i0.RAW.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    static {
        a5.a h8 = t4.k0.h("type.googleapis.com/google.crypto.tink.AesGcmSivKey");
        f9042a = h8;
        f9043b = t4.x.a(new x.b() { // from class: l4.r
            @Override // t4.x.b
            public final t4.h0 a(j4.w wVar) {
                t4.e0 j8;
                j8 = v.j((k4.z) wVar);
                return j8;
            }
        }, k4.z.class, t4.e0.class);
        f9044c = t4.w.a(new w.b() { // from class: l4.s
            @Override // t4.w.b
            public final j4.w a(t4.h0 h0Var) {
                k4.z f8;
                f8 = v.f((t4.e0) h0Var);
                return f8;
            }
        }, h8, t4.e0.class);
        f9045d = t4.g.a(new g.b() { // from class: l4.t
            @Override // t4.g.b
            public final t4.h0 a(j4.h hVar, j4.y yVar) {
                t4.d0 i8;
                i8 = v.i((k4.v) hVar, yVar);
                return i8;
            }
        }, k4.v.class, t4.d0.class);
        f9046e = t4.f.a(new f.b() { // from class: l4.u
            @Override // t4.f.b
            public final j4.h a(t4.h0 h0Var, j4.y yVar) {
                k4.v e8;
                e8 = v.e((t4.d0) h0Var, yVar);
                return e8;
            }
        }, h8, t4.d0.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static k4.v e(t4.d0 d0Var, j4.y yVar) {
        if (d0Var.f().equals("type.googleapis.com/google.crypto.tink.AesGcmSivKey")) {
            try {
                y4.n Z = y4.n.Z(d0Var.g(), com.google.crypto.tink.shaded.protobuf.p.b());
                if (Z.X() == 0) {
                    return k4.v.d().e(k4.z.b().b(Z.W().size()).c(l(d0Var.e())).a()).d(a5.b.a(Z.W().w(), j4.y.b(yVar))).c(d0Var.c()).a();
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (com.google.crypto.tink.shaded.protobuf.a0 unused) {
                throw new GeneralSecurityException("Parsing AesGcmSivKey failed");
            }
        }
        throw new IllegalArgumentException("Wrong type URL in call to AesGcmSivProtoSerialization.parseKey");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static k4.z f(t4.e0 e0Var) {
        if (!e0Var.d().a0().equals("type.googleapis.com/google.crypto.tink.AesGcmSivKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to AesGcmSivProtoSerialization.parseParameters: " + e0Var.d().a0());
        }
        try {
            y4.o Z = y4.o.Z(e0Var.d().b0(), com.google.crypto.tink.shaded.protobuf.p.b());
            if (Z.X() == 0) {
                return k4.z.b().b(Z.W()).c(l(e0Var.d().Z())).a();
            }
            throw new GeneralSecurityException("Only version 0 parameters are accepted");
        } catch (com.google.crypto.tink.shaded.protobuf.a0 e8) {
            throw new GeneralSecurityException("Parsing AesGcmSivParameters failed: ", e8);
        }
    }

    public static void g() {
        h(t4.u.c());
    }

    public static void h(t4.u uVar) {
        uVar.m(f9043b);
        uVar.l(f9044c);
        uVar.k(f9045d);
        uVar.j(f9046e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static t4.d0 i(k4.v vVar, j4.y yVar) {
        return t4.d0.b("type.googleapis.com/google.crypto.tink.AesGcmSivKey", ((y4.n) y4.n.Y().q(com.google.crypto.tink.shaded.protobuf.h.l(vVar.e().d(j4.y.b(yVar)))).h()).j(), y.c.SYMMETRIC, k(vVar.b().d()), vVar.a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static t4.e0 j(k4.z zVar) {
        return t4.e0.c((y4.a0) y4.a0.c0().r("type.googleapis.com/google.crypto.tink.AesGcmSivKey").s(((y4.o) y4.o.Y().q(zVar.c()).h()).j()).q(k(zVar.d())).h());
    }

    private static y4.i0 k(z.c cVar) {
        if (z.c.f8698b.equals(cVar)) {
            return y4.i0.TINK;
        }
        if (z.c.f8699c.equals(cVar)) {
            return y4.i0.CRUNCHY;
        }
        if (z.c.f8700d.equals(cVar)) {
            return y4.i0.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + cVar);
    }

    private static z.c l(y4.i0 i0Var) {
        int i8 = a.f9047a[i0Var.ordinal()];
        if (i8 != 1) {
            if (i8 == 2 || i8 == 3) {
                return z.c.f8699c;
            }
            if (i8 == 4) {
                return z.c.f8700d;
            }
            throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + i0Var.f());
        }
        return z.c.f8698b;
    }
}
