package l4;

import java.security.GeneralSecurityException;
import k4.k;
import t4.f;
import t4.g;
import t4.w;
import t4.x;
import y4.y;
/* loaded from: classes.dex */
public abstract class e {

    /* renamed from: a  reason: collision with root package name */
    private static final a5.a f8987a;

    /* renamed from: b  reason: collision with root package name */
    private static final t4.x f8988b;

    /* renamed from: c  reason: collision with root package name */
    private static final t4.w f8989c;

    /* renamed from: d  reason: collision with root package name */
    private static final t4.g f8990d;

    /* renamed from: e  reason: collision with root package name */
    private static final t4.f f8991e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f8992a;

        /* renamed from: b  reason: collision with root package name */
        static final /* synthetic */ int[] f8993b;

        static {
            int[] iArr = new int[y4.u.values().length];
            f8993b = iArr;
            try {
                iArr[y4.u.SHA1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8993b[y4.u.SHA224.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8993b[y4.u.SHA256.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8993b[y4.u.SHA384.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f8993b[y4.u.SHA512.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[y4.i0.values().length];
            f8992a = iArr2;
            try {
                iArr2[y4.i0.TINK.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f8992a[y4.i0.CRUNCHY.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f8992a[y4.i0.LEGACY.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f8992a[y4.i0.RAW.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    static {
        a5.a h8 = t4.k0.h("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey");
        f8987a = h8;
        f8988b = t4.x.a(new x.b() { // from class: l4.a
            @Override // t4.x.b
            public final t4.h0 a(j4.w wVar) {
                t4.e0 k8;
                k8 = e.k((k4.k) wVar);
                return k8;
            }
        }, k4.k.class, t4.e0.class);
        f8989c = t4.w.a(new w.b() { // from class: l4.b
            @Override // t4.w.b
            public final j4.w a(t4.h0 h0Var) {
                k4.k g8;
                g8 = e.g((t4.e0) h0Var);
                return g8;
            }
        }, h8, t4.e0.class);
        f8990d = t4.g.a(new g.b() { // from class: l4.c
            @Override // t4.g.b
            public final t4.h0 a(j4.h hVar, j4.y yVar) {
                t4.d0 j8;
                j8 = e.j((k4.f) hVar, yVar);
                return j8;
            }
        }, k4.f.class, t4.d0.class);
        f8991e = t4.f.a(new f.b() { // from class: l4.d
            @Override // t4.f.b
            public final j4.h a(t4.h0 h0Var, j4.y yVar) {
                k4.f f8;
                f8 = e.f((t4.d0) h0Var, yVar);
                return f8;
            }
        }, h8, t4.d0.class);
    }

    private static y4.x e(k4.k kVar) {
        return (y4.x) y4.x.a0().r(kVar.g()).q(m(kVar.d())).h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static k4.f f(t4.d0 d0Var, j4.y yVar) {
        if (d0Var.f().equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
            try {
                y4.d b02 = y4.d.b0(d0Var.g(), com.google.crypto.tink.shaded.protobuf.p.b());
                if (b02.Z() == 0) {
                    if (b02.X().a0() == 0) {
                        if (b02.Y().a0() == 0) {
                            return k4.f.d().f(k4.k.b().b(b02.X().Y().size()).d(b02.Y().Y().size()).e(b02.X().Z().X()).f(b02.Y().Z().Z()).c(l(b02.Y().Z().Y())).g(o(d0Var.e())).a()).c(a5.b.a(b02.X().Y().w(), j4.y.b(yVar))).d(a5.b.a(b02.Y().Y().w(), j4.y.b(yVar))).e(d0Var.c()).a();
                        }
                        throw new GeneralSecurityException("Only version 0 keys inner HMAC keys are accepted");
                    }
                    throw new GeneralSecurityException("Only version 0 keys inner AES CTR keys are accepted");
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (com.google.crypto.tink.shaded.protobuf.a0 unused) {
                throw new GeneralSecurityException("Parsing AesCtrHmacAeadKey failed");
            }
        }
        throw new IllegalArgumentException("Wrong type URL in call to AesCtrHmacAeadProtoSerialization.parseKey");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static k4.k g(t4.e0 e0Var) {
        if (!e0Var.d().a0().equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to AesCtrHmacAeadProtoSerialization.parseParameters: " + e0Var.d().a0());
        }
        try {
            y4.e a02 = y4.e.a0(e0Var.d().b0(), com.google.crypto.tink.shaded.protobuf.p.b());
            if (a02.Y().a0() == 0) {
                return k4.k.b().b(a02.X().Y()).d(a02.Y().Y()).e(a02.X().Z().X()).f(a02.Y().Z().Z()).c(l(a02.Y().Z().Y())).g(o(e0Var.d().Z())).a();
            }
            throw new GeneralSecurityException("Only version 0 keys are accepted");
        } catch (com.google.crypto.tink.shaded.protobuf.a0 e8) {
            throw new GeneralSecurityException("Parsing AesCtrHmacAeadParameters failed: ", e8);
        }
    }

    public static void h() {
        i(t4.u.c());
    }

    public static void i(t4.u uVar) {
        uVar.m(f8988b);
        uVar.l(f8989c);
        uVar.k(f8990d);
        uVar.j(f8991e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static t4.d0 j(k4.f fVar, j4.y yVar) {
        return t4.d0.b("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey", ((y4.d) y4.d.a0().q((y4.f) y4.f.b0().r((y4.h) y4.h.Y().q(fVar.b().f()).h()).q(com.google.crypto.tink.shaded.protobuf.h.l(fVar.e().d(j4.y.b(yVar)))).h()).r((y4.v) y4.v.b0().r(e(fVar.b())).q(com.google.crypto.tink.shaded.protobuf.h.l(fVar.f().d(j4.y.b(yVar)))).h()).h()).j(), y.c.SYMMETRIC, n(fVar.b().h()), fVar.a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static t4.e0 k(k4.k kVar) {
        return t4.e0.c((y4.a0) y4.a0.c0().r("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey").s(((y4.e) y4.e.Z().q((y4.g) y4.g.a0().r((y4.h) y4.h.Y().q(kVar.f()).h()).q(kVar.c()).h()).r((y4.w) y4.w.b0().r(e(kVar)).q(kVar.e()).h()).h()).j()).q(n(kVar.h())).h());
    }

    private static k.c l(y4.u uVar) {
        int i8 = a.f8993b[uVar.ordinal()];
        if (i8 != 1) {
            if (i8 != 2) {
                if (i8 != 3) {
                    if (i8 != 4) {
                        if (i8 == 5) {
                            return k.c.f8569f;
                        }
                        throw new GeneralSecurityException("Unable to parse HashType: " + uVar.f());
                    }
                    return k.c.f8568e;
                }
                return k.c.f8567d;
            }
            return k.c.f8566c;
        }
        return k.c.f8565b;
    }

    private static y4.u m(k.c cVar) {
        if (k.c.f8565b.equals(cVar)) {
            return y4.u.SHA1;
        }
        if (k.c.f8566c.equals(cVar)) {
            return y4.u.SHA224;
        }
        if (k.c.f8567d.equals(cVar)) {
            return y4.u.SHA256;
        }
        if (k.c.f8568e.equals(cVar)) {
            return y4.u.SHA384;
        }
        if (k.c.f8569f.equals(cVar)) {
            return y4.u.SHA512;
        }
        throw new GeneralSecurityException("Unable to serialize HashType " + cVar);
    }

    private static y4.i0 n(k.d dVar) {
        if (k.d.f8571b.equals(dVar)) {
            return y4.i0.TINK;
        }
        if (k.d.f8572c.equals(dVar)) {
            return y4.i0.CRUNCHY;
        }
        if (k.d.f8573d.equals(dVar)) {
            return y4.i0.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + dVar);
    }

    private static k.d o(y4.i0 i0Var) {
        int i8 = a.f8992a[i0Var.ordinal()];
        if (i8 != 1) {
            if (i8 == 2 || i8 == 3) {
                return k.d.f8572c;
            }
            if (i8 == 4) {
                return k.d.f8573d;
            }
            throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + i0Var.f());
        }
        return k.d.f8571b;
    }
}
