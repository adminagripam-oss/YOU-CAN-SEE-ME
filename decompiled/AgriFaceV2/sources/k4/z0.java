package k4;

import java.security.GeneralSecurityException;
import k4.u0;
import t4.f;
import t4.g;
import t4.w;
import t4.x;
import y4.y;
/* loaded from: classes.dex */
public abstract class z0 {

    /* renamed from: a  reason: collision with root package name */
    private static final a5.a f8702a;

    /* renamed from: b  reason: collision with root package name */
    private static final t4.x f8703b;

    /* renamed from: c  reason: collision with root package name */
    private static final t4.w f8704c;

    /* renamed from: d  reason: collision with root package name */
    private static final t4.g f8705d;

    /* renamed from: e  reason: collision with root package name */
    private static final t4.f f8706e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f8707a;

        static {
            int[] iArr = new int[y4.i0.values().length];
            f8707a = iArr;
            try {
                iArr[y4.i0.TINK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8707a[y4.i0.RAW.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    static {
        a5.a h8 = t4.k0.h("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey");
        f8702a = h8;
        f8703b = t4.x.a(new x.b() { // from class: k4.v0
            @Override // t4.x.b
            public final t4.h0 a(j4.w wVar) {
                t4.e0 k8;
                k8 = z0.k((u0) wVar);
                return k8;
            }
        }, u0.class, t4.e0.class);
        f8704c = t4.w.a(new w.b() { // from class: k4.w0
            @Override // t4.w.b
            public final j4.w a(t4.h0 h0Var) {
                u0 f8;
                f8 = z0.f((t4.e0) h0Var);
                return f8;
            }
        }, h8, t4.e0.class);
        f8705d = t4.g.a(new g.b() { // from class: k4.x0
            @Override // t4.g.b
            public final t4.h0 a(j4.h hVar, j4.y yVar) {
                t4.d0 j8;
                j8 = z0.j((t0) hVar, yVar);
                return j8;
            }
        }, t0.class, t4.d0.class);
        f8706e = t4.f.a(new f.b() { // from class: k4.y0
            @Override // t4.f.b
            public final j4.h a(t4.h0 h0Var, j4.y yVar) {
                t0 e8;
                e8 = z0.e((t4.d0) h0Var, yVar);
                return e8;
            }
        }, h8, t4.d0.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static t0 e(t4.d0 d0Var, j4.y yVar) {
        if (d0Var.f().equals("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey")) {
            try {
                y4.g0 Z = y4.g0.Z(d0Var.g(), com.google.crypto.tink.shaded.protobuf.p.b());
                if (Z.X() == 0) {
                    return t0.d(g(Z.W(), d0Var.e()), d0Var.c());
                }
                throw new GeneralSecurityException("KmsEnvelopeAeadKeys are only accepted with version 0, got " + Z);
            } catch (com.google.crypto.tink.shaded.protobuf.a0 e8) {
                throw new GeneralSecurityException("Parsing KmsEnvelopeAeadKey failed: ", e8);
            }
        }
        throw new IllegalArgumentException("Wrong type URL in call to LegacyKmsEnvelopeAeadProtoSerialization.parseKey");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static u0 f(t4.e0 e0Var) {
        if (e0Var.d().a0().equals("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey")) {
            try {
                return g(y4.h0.b0(e0Var.d().b0(), com.google.crypto.tink.shaded.protobuf.p.b()), e0Var.d().Z());
            } catch (com.google.crypto.tink.shaded.protobuf.a0 e8) {
                throw new GeneralSecurityException("Parsing KmsEnvelopeAeadKeyFormat failed: ", e8);
            }
        }
        throw new IllegalArgumentException("Wrong type URL in call to LegacyKmsEnvelopeAeadProtoSerialization.parseParameters: " + e0Var.d().a0());
    }

    private static u0 g(y4.h0 h0Var, y4.i0 i0Var) {
        u0.c cVar;
        j4.w a8 = j4.z.a(((y4.a0) y4.a0.c0().r(h0Var.Y().a0()).s(h0Var.Y().b0()).q(y4.i0.RAW).h()).i());
        if (a8 instanceof u) {
            cVar = u0.c.f8673b;
        } else if (a8 instanceof e0) {
            cVar = u0.c.f8675d;
        } else if (a8 instanceof t1) {
            cVar = u0.c.f8674c;
        } else if (a8 instanceof k) {
            cVar = u0.c.f8676e;
        } else if (a8 instanceof p) {
            cVar = u0.c.f8677f;
        } else if (!(a8 instanceof z)) {
            throw new GeneralSecurityException("Unsupported DEK parameters when parsing " + a8);
        } else {
            cVar = u0.c.f8678g;
        }
        return u0.b().f(n(i0Var)).e(h0Var.Z()).c((c) a8).d(cVar).a();
    }

    public static void h() {
        i(t4.u.c());
    }

    public static void i(t4.u uVar) {
        uVar.m(f8703b);
        uVar.l(f8704c);
        uVar.k(f8705d);
        uVar.j(f8706e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static t4.d0 j(t0 t0Var, j4.y yVar) {
        return t4.d0.b("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey", ((y4.g0) y4.g0.Y().q(l(t0Var.b())).h()).j(), y.c.REMOTE, m(t0Var.b().e()), t0Var.a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static t4.e0 k(u0 u0Var) {
        return t4.e0.c((y4.a0) y4.a0.c0().r("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey").s(l(u0Var).j()).q(m(u0Var.e())).h());
    }

    private static y4.h0 l(u0 u0Var) {
        try {
            return (y4.h0) y4.h0.a0().r(u0Var.d()).q(y4.a0.e0(j4.z.b(u0Var.c()), com.google.crypto.tink.shaded.protobuf.p.b())).h();
        } catch (com.google.crypto.tink.shaded.protobuf.a0 e8) {
            throw new GeneralSecurityException("Parsing KmsEnvelopeAeadKeyFormat failed: ", e8);
        }
    }

    private static y4.i0 m(u0.d dVar) {
        if (u0.d.f8680b.equals(dVar)) {
            return y4.i0.TINK;
        }
        if (u0.d.f8681c.equals(dVar)) {
            return y4.i0.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + dVar);
    }

    private static u0.d n(y4.i0 i0Var) {
        int i8 = a.f8707a[i0Var.ordinal()];
        if (i8 != 1) {
            if (i8 == 2) {
                return u0.d.f8681c;
            }
            throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + i0Var.f());
        }
        return u0.d.f8680b;
    }
}
