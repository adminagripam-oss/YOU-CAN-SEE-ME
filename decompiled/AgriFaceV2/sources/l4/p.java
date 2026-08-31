package l4;

import java.security.GeneralSecurityException;
import k4.u;
import t4.f;
import t4.g;
import t4.w;
import t4.x;
import y4.y;
/* loaded from: classes.dex */
public abstract class p {

    /* renamed from: a  reason: collision with root package name */
    private static final a5.a f9018a;

    /* renamed from: b  reason: collision with root package name */
    private static final t4.x f9019b;

    /* renamed from: c  reason: collision with root package name */
    private static final t4.w f9020c;

    /* renamed from: d  reason: collision with root package name */
    private static final t4.g f9021d;

    /* renamed from: e  reason: collision with root package name */
    private static final t4.f f9022e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9023a;

        static {
            int[] iArr = new int[y4.i0.values().length];
            f9023a = iArr;
            try {
                iArr[y4.i0.TINK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9023a[y4.i0.CRUNCHY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9023a[y4.i0.LEGACY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9023a[y4.i0.RAW.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    static {
        a5.a h8 = t4.k0.h("type.googleapis.com/google.crypto.tink.AesGcmKey");
        f9018a = h8;
        f9019b = t4.x.a(new x.b() { // from class: l4.l
            @Override // t4.x.b
            public final t4.h0 a(j4.w wVar) {
                t4.e0 j8;
                j8 = p.j((k4.u) wVar);
                return j8;
            }
        }, k4.u.class, t4.e0.class);
        f9020c = t4.w.a(new w.b() { // from class: l4.m
            @Override // t4.w.b
            public final j4.w a(t4.h0 h0Var) {
                k4.u f8;
                f8 = p.f((t4.e0) h0Var);
                return f8;
            }
        }, h8, t4.e0.class);
        f9021d = t4.g.a(new g.b() { // from class: l4.n
            @Override // t4.g.b
            public final t4.h0 a(j4.h hVar, j4.y yVar) {
                t4.d0 i8;
                i8 = p.i((k4.q) hVar, yVar);
                return i8;
            }
        }, k4.q.class, t4.d0.class);
        f9022e = t4.f.a(new f.b() { // from class: l4.o
            @Override // t4.f.b
            public final j4.h a(t4.h0 h0Var, j4.y yVar) {
                k4.q e8;
                e8 = p.e((t4.d0) h0Var, yVar);
                return e8;
            }
        }, h8, t4.d0.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static k4.q e(t4.d0 d0Var, j4.y yVar) {
        if (d0Var.f().equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
            try {
                y4.l Z = y4.l.Z(d0Var.g(), com.google.crypto.tink.shaded.protobuf.p.b());
                if (Z.X() == 0) {
                    return k4.q.d().e(k4.u.b().c(Z.W().size()).b(12).d(16).e(l(d0Var.e())).a()).d(a5.b.a(Z.W().w(), j4.y.b(yVar))).c(d0Var.c()).a();
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (com.google.crypto.tink.shaded.protobuf.a0 unused) {
                throw new GeneralSecurityException("Parsing AesGcmKey failed");
            }
        }
        throw new IllegalArgumentException("Wrong type URL in call to AesGcmProtoSerialization.parseKey");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static k4.u f(t4.e0 e0Var) {
        if (!e0Var.d().a0().equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to AesGcmProtoSerialization.parseParameters: " + e0Var.d().a0());
        }
        try {
            y4.m Z = y4.m.Z(e0Var.d().b0(), com.google.crypto.tink.shaded.protobuf.p.b());
            if (Z.X() == 0) {
                return k4.u.b().c(Z.W()).b(12).d(16).e(l(e0Var.d().Z())).a();
            }
            throw new GeneralSecurityException("Only version 0 parameters are accepted");
        } catch (com.google.crypto.tink.shaded.protobuf.a0 e8) {
            throw new GeneralSecurityException("Parsing AesGcmParameters failed: ", e8);
        }
    }

    public static void g() {
        h(t4.u.c());
    }

    public static void h(t4.u uVar) {
        uVar.m(f9019b);
        uVar.l(f9020c);
        uVar.k(f9021d);
        uVar.j(f9022e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static t4.d0 i(k4.q qVar, j4.y yVar) {
        m(qVar.b());
        return t4.d0.b("type.googleapis.com/google.crypto.tink.AesGcmKey", ((y4.l) y4.l.Y().q(com.google.crypto.tink.shaded.protobuf.h.l(qVar.e().d(j4.y.b(yVar)))).h()).j(), y.c.SYMMETRIC, k(qVar.b().f()), qVar.a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static t4.e0 j(k4.u uVar) {
        m(uVar);
        return t4.e0.c((y4.a0) y4.a0.c0().r("type.googleapis.com/google.crypto.tink.AesGcmKey").s(((y4.m) y4.m.Y().q(uVar.d()).h()).j()).q(k(uVar.f())).h());
    }

    private static y4.i0 k(u.c cVar) {
        if (u.c.f8661b.equals(cVar)) {
            return y4.i0.TINK;
        }
        if (u.c.f8662c.equals(cVar)) {
            return y4.i0.CRUNCHY;
        }
        if (u.c.f8663d.equals(cVar)) {
            return y4.i0.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + cVar);
    }

    private static u.c l(y4.i0 i0Var) {
        int i8 = a.f9023a[i0Var.ordinal()];
        if (i8 != 1) {
            if (i8 == 2 || i8 == 3) {
                return u.c.f8662c;
            }
            if (i8 == 4) {
                return u.c.f8663d;
            }
            throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + i0Var.f());
        }
        return u.c.f8661b;
    }

    private static void m(k4.u uVar) {
        if (uVar.e() != 16) {
            throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d. Currently Tink only supports serialization of AES GCM keys with tag size equal to 16 bytes.", Integer.valueOf(uVar.e())));
        }
        if (uVar.c() != 12) {
            throw new GeneralSecurityException(String.format("Invalid IV size in bytes %d. Currently Tink only supports serialization of AES GCM keys with IV size equal to 12 bytes.", Integer.valueOf(uVar.c())));
        }
    }
}
