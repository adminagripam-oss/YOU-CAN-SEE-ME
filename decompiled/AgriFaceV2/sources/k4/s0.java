package k4;

import java.security.GeneralSecurityException;
import k4.n0;
import t4.f;
import t4.g;
import t4.w;
import t4.x;
import y4.y;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class s0 {

    /* renamed from: a  reason: collision with root package name */
    private static final a5.a f8630a;

    /* renamed from: b  reason: collision with root package name */
    private static final t4.x f8631b;

    /* renamed from: c  reason: collision with root package name */
    private static final t4.w f8632c;

    /* renamed from: d  reason: collision with root package name */
    private static final t4.g f8633d;

    /* renamed from: e  reason: collision with root package name */
    private static final t4.f f8634e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f8635a;

        static {
            int[] iArr = new int[y4.i0.values().length];
            f8635a = iArr;
            try {
                iArr[y4.i0.TINK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8635a[y4.i0.RAW.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    static {
        a5.a h8 = t4.k0.h("type.googleapis.com/google.crypto.tink.KmsAeadKey");
        f8630a = h8;
        f8631b = t4.x.a(new x.b() { // from class: k4.o0
            @Override // t4.x.b
            public final t4.h0 a(j4.w wVar) {
                t4.e0 j8;
                j8 = s0.j((n0) wVar);
                return j8;
            }
        }, n0.class, t4.e0.class);
        f8632c = t4.w.a(new w.b() { // from class: k4.p0
            @Override // t4.w.b
            public final j4.w a(t4.h0 h0Var) {
                n0 f8;
                f8 = s0.f((t4.e0) h0Var);
                return f8;
            }
        }, h8, t4.e0.class);
        f8633d = t4.g.a(new g.b() { // from class: k4.q0
            @Override // t4.g.b
            public final t4.h0 a(j4.h hVar, j4.y yVar) {
                t4.d0 i8;
                i8 = s0.i((m0) hVar, yVar);
                return i8;
            }
        }, m0.class, t4.d0.class);
        f8634e = t4.f.a(new f.b() { // from class: k4.r0
            @Override // t4.f.b
            public final j4.h a(t4.h0 h0Var, j4.y yVar) {
                m0 e8;
                e8 = s0.e((t4.d0) h0Var, yVar);
                return e8;
            }
        }, h8, t4.d0.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static m0 e(t4.d0 d0Var, j4.y yVar) {
        if (d0Var.f().equals("type.googleapis.com/google.crypto.tink.KmsAeadKey")) {
            try {
                y4.e0 Z = y4.e0.Z(d0Var.g(), com.google.crypto.tink.shaded.protobuf.p.b());
                if (Z.X() == 0) {
                    return m0.d(n0.b(Z.W().X(), l(d0Var.e())), d0Var.c());
                }
                throw new GeneralSecurityException("KmsAeadKey are only accepted with version 0, got " + Z);
            } catch (com.google.crypto.tink.shaded.protobuf.a0 e8) {
                throw new GeneralSecurityException("Parsing KmsAeadKey failed: ", e8);
            }
        }
        throw new IllegalArgumentException("Wrong type URL in call to LegacyKmsAeadProtoSerialization.parseKey");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static n0 f(t4.e0 e0Var) {
        if (e0Var.d().a0().equals("type.googleapis.com/google.crypto.tink.KmsAeadKey")) {
            try {
                return n0.b(y4.f0.Z(e0Var.d().b0(), com.google.crypto.tink.shaded.protobuf.p.b()).X(), l(e0Var.d().Z()));
            } catch (com.google.crypto.tink.shaded.protobuf.a0 e8) {
                throw new GeneralSecurityException("Parsing KmsAeadKeyFormat failed: ", e8);
            }
        }
        throw new IllegalArgumentException("Wrong type URL in call to LegacyKmsAeadProtoSerialization.parseParameters: " + e0Var.d().a0());
    }

    public static void g() {
        h(t4.u.c());
    }

    public static void h(t4.u uVar) {
        uVar.m(f8631b);
        uVar.l(f8632c);
        uVar.k(f8633d);
        uVar.j(f8634e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static t4.d0 i(m0 m0Var, j4.y yVar) {
        return t4.d0.b("type.googleapis.com/google.crypto.tink.KmsAeadKey", ((y4.e0) y4.e0.Y().q((y4.f0) y4.f0.Y().q(m0Var.b().c()).h()).h()).j(), y.c.REMOTE, k(m0Var.b().d()), m0Var.a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static t4.e0 j(n0 n0Var) {
        return t4.e0.c((y4.a0) y4.a0.c0().r("type.googleapis.com/google.crypto.tink.KmsAeadKey").s(((y4.f0) y4.f0.Y().q(n0Var.c()).h()).j()).q(k(n0Var.d())).h());
    }

    private static y4.i0 k(n0.a aVar) {
        if (n0.a.f8594b.equals(aVar)) {
            return y4.i0.TINK;
        }
        if (n0.a.f8595c.equals(aVar)) {
            return y4.i0.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + aVar);
    }

    private static n0.a l(y4.i0 i0Var) {
        int i8 = a.f8635a[i0Var.ordinal()];
        if (i8 != 1) {
            if (i8 == 2) {
                return n0.a.f8595c;
            }
            throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + i0Var.f());
        }
        return n0.a.f8594b;
    }
}
