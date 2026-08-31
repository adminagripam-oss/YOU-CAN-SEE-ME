package l4;

import java.security.GeneralSecurityException;
import k4.e0;
import t4.f;
import t4.g;
import t4.w;
import t4.x;
import y4.y;
/* loaded from: classes.dex */
public abstract class b0 {

    /* renamed from: a  reason: collision with root package name */
    private static final a5.a f8978a;

    /* renamed from: b  reason: collision with root package name */
    private static final t4.x f8979b;

    /* renamed from: c  reason: collision with root package name */
    private static final t4.w f8980c;

    /* renamed from: d  reason: collision with root package name */
    private static final t4.g f8981d;

    /* renamed from: e  reason: collision with root package name */
    private static final t4.f f8982e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f8983a;

        static {
            int[] iArr = new int[y4.i0.values().length];
            f8983a = iArr;
            try {
                iArr[y4.i0.TINK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8983a[y4.i0.CRUNCHY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8983a[y4.i0.LEGACY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8983a[y4.i0.RAW.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    static {
        a5.a h8 = t4.k0.h("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key");
        f8978a = h8;
        f8979b = t4.x.a(new x.b() { // from class: l4.x
            @Override // t4.x.b
            public final t4.h0 a(j4.w wVar) {
                t4.e0 j8;
                j8 = b0.j((k4.e0) wVar);
                return j8;
            }
        }, k4.e0.class, t4.e0.class);
        f8980c = t4.w.a(new w.b() { // from class: l4.y
            @Override // t4.w.b
            public final j4.w a(t4.h0 h0Var) {
                k4.e0 f8;
                f8 = b0.f((t4.e0) h0Var);
                return f8;
            }
        }, h8, t4.e0.class);
        f8981d = t4.g.a(new g.b() { // from class: l4.z
            @Override // t4.g.b
            public final t4.h0 a(j4.h hVar, j4.y yVar) {
                t4.d0 i8;
                i8 = b0.i((k4.a0) hVar, yVar);
                return i8;
            }
        }, k4.a0.class, t4.d0.class);
        f8982e = t4.f.a(new f.b() { // from class: l4.a0
            @Override // t4.f.b
            public final j4.h a(t4.h0 h0Var, j4.y yVar) {
                k4.a0 e8;
                e8 = b0.e((t4.d0) h0Var, yVar);
                return e8;
            }
        }, h8, t4.d0.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static k4.a0 e(t4.d0 d0Var, j4.y yVar) {
        if (d0Var.f().equals("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key")) {
            try {
                y4.r Z = y4.r.Z(d0Var.g(), com.google.crypto.tink.shaded.protobuf.p.b());
                if (Z.X() == 0) {
                    return k4.a0.d(l(d0Var.e()), a5.b.a(Z.W().w(), j4.y.b(yVar)), d0Var.c());
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (com.google.crypto.tink.shaded.protobuf.a0 unused) {
                throw new GeneralSecurityException("Parsing ChaCha20Poly1305Key failed");
            }
        }
        throw new IllegalArgumentException("Wrong type URL in call to ChaCha20Poly1305ProtoSerialization.parseKey");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static k4.e0 f(t4.e0 e0Var) {
        if (e0Var.d().a0().equals("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key")) {
            try {
                y4.s.W(e0Var.d().b0(), com.google.crypto.tink.shaded.protobuf.p.b());
                return k4.e0.b(l(e0Var.d().Z()));
            } catch (com.google.crypto.tink.shaded.protobuf.a0 e8) {
                throw new GeneralSecurityException("Parsing ChaCha20Poly1305Parameters failed: ", e8);
            }
        }
        throw new IllegalArgumentException("Wrong type URL in call to ChaCha20Poly1305ProtoSerialization.parseParameters: " + e0Var.d().a0());
    }

    public static void g() {
        h(t4.u.c());
    }

    public static void h(t4.u uVar) {
        uVar.m(f8979b);
        uVar.l(f8980c);
        uVar.k(f8981d);
        uVar.j(f8982e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static t4.d0 i(k4.a0 a0Var, j4.y yVar) {
        return t4.d0.b("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key", ((y4.r) y4.r.Y().q(com.google.crypto.tink.shaded.protobuf.h.l(a0Var.e().d(j4.y.b(yVar)))).h()).j(), y.c.SYMMETRIC, k(a0Var.b().c()), a0Var.a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static t4.e0 j(k4.e0 e0Var) {
        return t4.e0.c((y4.a0) y4.a0.c0().r("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key").s(y4.s.V().j()).q(k(e0Var.c())).h());
    }

    private static y4.i0 k(e0.a aVar) {
        if (e0.a.f8515b.equals(aVar)) {
            return y4.i0.TINK;
        }
        if (e0.a.f8516c.equals(aVar)) {
            return y4.i0.CRUNCHY;
        }
        if (e0.a.f8517d.equals(aVar)) {
            return y4.i0.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + aVar);
    }

    private static e0.a l(y4.i0 i0Var) {
        int i8 = a.f8983a[i0Var.ordinal()];
        if (i8 != 1) {
            if (i8 == 2 || i8 == 3) {
                return e0.a.f8516c;
            }
            if (i8 == 4) {
                return e0.a.f8517d;
            }
            throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + i0Var.f());
        }
        return e0.a.f8515b;
    }
}
