package l4;

import java.security.GeneralSecurityException;
import k4.p1;
import k4.t1;
import t4.f;
import t4.g;
import t4.w;
import t4.x;
import y4.y;
/* loaded from: classes.dex */
public abstract class y0 {

    /* renamed from: a  reason: collision with root package name */
    private static final a5.a f9055a;

    /* renamed from: b  reason: collision with root package name */
    private static final t4.x f9056b;

    /* renamed from: c  reason: collision with root package name */
    private static final t4.w f9057c;

    /* renamed from: d  reason: collision with root package name */
    private static final t4.g f9058d;

    /* renamed from: e  reason: collision with root package name */
    private static final t4.f f9059e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9060a;

        static {
            int[] iArr = new int[y4.i0.values().length];
            f9060a = iArr;
            try {
                iArr[y4.i0.TINK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9060a[y4.i0.CRUNCHY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9060a[y4.i0.LEGACY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9060a[y4.i0.RAW.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    static {
        a5.a h8 = t4.k0.h("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key");
        f9055a = h8;
        f9056b = t4.x.a(new x.b() { // from class: l4.u0
            @Override // t4.x.b
            public final t4.h0 a(j4.w wVar) {
                t4.e0 j8;
                j8 = y0.j((t1) wVar);
                return j8;
            }
        }, t1.class, t4.e0.class);
        f9057c = t4.w.a(new w.b() { // from class: l4.v0
            @Override // t4.w.b
            public final j4.w a(t4.h0 h0Var) {
                t1 f8;
                f8 = y0.f((t4.e0) h0Var);
                return f8;
            }
        }, h8, t4.e0.class);
        f9058d = t4.g.a(new g.b() { // from class: l4.w0
            @Override // t4.g.b
            public final t4.h0 a(j4.h hVar, j4.y yVar) {
                t4.d0 i8;
                i8 = y0.i((p1) hVar, yVar);
                return i8;
            }
        }, p1.class, t4.d0.class);
        f9059e = t4.f.a(new f.b() { // from class: l4.x0
            @Override // t4.f.b
            public final j4.h a(t4.h0 h0Var, j4.y yVar) {
                p1 e8;
                e8 = y0.e((t4.d0) h0Var, yVar);
                return e8;
            }
        }, h8, t4.d0.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static p1 e(t4.d0 d0Var, j4.y yVar) {
        if (d0Var.f().equals("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key")) {
            try {
                y4.n0 Z = y4.n0.Z(d0Var.g(), com.google.crypto.tink.shaded.protobuf.p.b());
                if (Z.X() == 0) {
                    return p1.d(l(d0Var.e()), a5.b.a(Z.W().w(), j4.y.b(yVar)), d0Var.c());
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (com.google.crypto.tink.shaded.protobuf.a0 unused) {
                throw new GeneralSecurityException("Parsing XChaCha20Poly1305Key failed");
            }
        }
        throw new IllegalArgumentException("Wrong type URL in call to XChaCha20Poly1305ProtoSerialization.parseKey");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static t1 f(t4.e0 e0Var) {
        if (!e0Var.d().a0().equals("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key")) {
            throw new IllegalArgumentException("Wrong type URL in call to XChaCha20Poly1305ProtoSerialization.parseParameters: " + e0Var.d().a0());
        }
        try {
            if (y4.o0.X(e0Var.d().b0(), com.google.crypto.tink.shaded.protobuf.p.b()).W() == 0) {
                return t1.b(l(e0Var.d().Z()));
            }
            throw new GeneralSecurityException("Only version 0 parameters are accepted");
        } catch (com.google.crypto.tink.shaded.protobuf.a0 e8) {
            throw new GeneralSecurityException("Parsing XChaCha20Poly1305Parameters failed: ", e8);
        }
    }

    public static void g() {
        h(t4.u.c());
    }

    public static void h(t4.u uVar) {
        uVar.m(f9056b);
        uVar.l(f9057c);
        uVar.k(f9058d);
        uVar.j(f9059e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static t4.d0 i(p1 p1Var, j4.y yVar) {
        return t4.d0.b("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key", ((y4.n0) y4.n0.Y().q(com.google.crypto.tink.shaded.protobuf.h.l(p1Var.e().d(j4.y.b(yVar)))).h()).j(), y.c.SYMMETRIC, k(p1Var.b().c()), p1Var.a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static t4.e0 j(t1 t1Var) {
        return t4.e0.c((y4.a0) y4.a0.c0().r("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key").s(y4.o0.V().j()).q(k(t1Var.c())).h());
    }

    private static y4.i0 k(t1.a aVar) {
        if (t1.a.f8649b.equals(aVar)) {
            return y4.i0.TINK;
        }
        if (t1.a.f8650c.equals(aVar)) {
            return y4.i0.CRUNCHY;
        }
        if (t1.a.f8651d.equals(aVar)) {
            return y4.i0.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + aVar);
    }

    private static t1.a l(y4.i0 i0Var) {
        int i8 = a.f9060a[i0Var.ordinal()];
        if (i8 != 1) {
            if (i8 == 2 || i8 == 3) {
                return t1.a.f8650c;
            }
            if (i8 == 4) {
                return t1.a.f8651d;
            }
            throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + i0Var.f());
        }
        return t1.a.f8649b;
    }
}
