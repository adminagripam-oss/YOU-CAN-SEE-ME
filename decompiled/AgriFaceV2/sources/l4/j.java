package l4;

import java.security.GeneralSecurityException;
import k4.p;
import t4.f;
import t4.g;
import t4.w;
import t4.x;
import y4.y;
/* loaded from: classes.dex */
public abstract class j {

    /* renamed from: a  reason: collision with root package name */
    private static final a5.a f8999a;

    /* renamed from: b  reason: collision with root package name */
    private static final t4.x f9000b;

    /* renamed from: c  reason: collision with root package name */
    private static final t4.w f9001c;

    /* renamed from: d  reason: collision with root package name */
    private static final t4.g f9002d;

    /* renamed from: e  reason: collision with root package name */
    private static final t4.f f9003e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9004a;

        static {
            int[] iArr = new int[y4.i0.values().length];
            f9004a = iArr;
            try {
                iArr[y4.i0.TINK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9004a[y4.i0.CRUNCHY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9004a[y4.i0.LEGACY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9004a[y4.i0.RAW.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    static {
        a5.a h8 = t4.k0.h("type.googleapis.com/google.crypto.tink.AesEaxKey");
        f8999a = h8;
        f9000b = t4.x.a(new x.b() { // from class: l4.f
            @Override // t4.x.b
            public final t4.h0 a(j4.w wVar) {
                t4.e0 k8;
                k8 = j.k((k4.p) wVar);
                return k8;
            }
        }, k4.p.class, t4.e0.class);
        f9001c = t4.w.a(new w.b() { // from class: l4.g
            @Override // t4.w.b
            public final j4.w a(t4.h0 h0Var) {
                k4.p g8;
                g8 = j.g((t4.e0) h0Var);
                return g8;
            }
        }, h8, t4.e0.class);
        f9002d = t4.g.a(new g.b() { // from class: l4.h
            @Override // t4.g.b
            public final t4.h0 a(j4.h hVar, j4.y yVar) {
                t4.d0 j8;
                j8 = j.j((k4.l) hVar, yVar);
                return j8;
            }
        }, k4.l.class, t4.d0.class);
        f9003e = t4.f.a(new f.b() { // from class: l4.i
            @Override // t4.f.b
            public final j4.h a(t4.h0 h0Var, j4.y yVar) {
                k4.l f8;
                f8 = j.f((t4.d0) h0Var, yVar);
                return f8;
            }
        }, h8, t4.d0.class);
    }

    private static y4.k e(k4.p pVar) {
        if (pVar.e() == 16) {
            return (y4.k) y4.k.Y().q(pVar.c()).h();
        }
        throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d. Currently Tink only supports aes eax keys with tag size equal to 16 bytes.", Integer.valueOf(pVar.e())));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static k4.l f(t4.d0 d0Var, j4.y yVar) {
        if (d0Var.f().equals("type.googleapis.com/google.crypto.tink.AesEaxKey")) {
            try {
                y4.i b02 = y4.i.b0(d0Var.g(), com.google.crypto.tink.shaded.protobuf.p.b());
                if (b02.Z() == 0) {
                    return k4.l.d().e(k4.p.b().c(b02.X().size()).b(b02.Y().X()).d(16).e(m(d0Var.e())).a()).d(a5.b.a(b02.X().w(), j4.y.b(yVar))).c(d0Var.c()).a();
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (com.google.crypto.tink.shaded.protobuf.a0 unused) {
                throw new GeneralSecurityException("Parsing AesEaxKey failed");
            }
        }
        throw new IllegalArgumentException("Wrong type URL in call to AesEaxProtoSerialization.parseKey");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static k4.p g(t4.e0 e0Var) {
        if (e0Var.d().a0().equals("type.googleapis.com/google.crypto.tink.AesEaxKey")) {
            try {
                y4.j a02 = y4.j.a0(e0Var.d().b0(), com.google.crypto.tink.shaded.protobuf.p.b());
                return k4.p.b().c(a02.X()).b(a02.Y().X()).d(16).e(m(e0Var.d().Z())).a();
            } catch (com.google.crypto.tink.shaded.protobuf.a0 e8) {
                throw new GeneralSecurityException("Parsing AesEaxParameters failed: ", e8);
            }
        }
        throw new IllegalArgumentException("Wrong type URL in call to AesEaxProtoSerialization.parseParameters: " + e0Var.d().a0());
    }

    public static void h() {
        i(t4.u.c());
    }

    public static void i(t4.u uVar) {
        uVar.m(f9000b);
        uVar.l(f9001c);
        uVar.k(f9002d);
        uVar.j(f9003e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static t4.d0 j(k4.l lVar, j4.y yVar) {
        return t4.d0.b("type.googleapis.com/google.crypto.tink.AesEaxKey", ((y4.i) y4.i.a0().r(e(lVar.b())).q(com.google.crypto.tink.shaded.protobuf.h.l(lVar.e().d(j4.y.b(yVar)))).h()).j(), y.c.SYMMETRIC, l(lVar.b().f()), lVar.a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static t4.e0 k(k4.p pVar) {
        return t4.e0.c((y4.a0) y4.a0.c0().r("type.googleapis.com/google.crypto.tink.AesEaxKey").s(((y4.j) y4.j.Z().r(e(pVar)).q(pVar.d()).h()).j()).q(l(pVar.f())).h());
    }

    private static y4.i0 l(p.c cVar) {
        if (p.c.f8615b.equals(cVar)) {
            return y4.i0.TINK;
        }
        if (p.c.f8616c.equals(cVar)) {
            return y4.i0.CRUNCHY;
        }
        if (p.c.f8617d.equals(cVar)) {
            return y4.i0.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + cVar);
    }

    private static p.c m(y4.i0 i0Var) {
        int i8 = a.f9004a[i0Var.ordinal()];
        if (i8 != 1) {
            if (i8 == 2 || i8 == 3) {
                return p.c.f8616c;
            }
            if (i8 == 4) {
                return p.c.f8617d;
            }
            throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + i0Var.f());
        }
        return p.c.f8615b;
    }
}
