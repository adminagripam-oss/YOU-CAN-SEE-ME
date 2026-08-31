package l4;

import java.security.GeneralSecurityException;
import java.util.Objects;
import k4.k1;
import k4.o1;
import t4.f;
import t4.g;
import t4.w;
import t4.x;
import y4.y;
/* loaded from: classes.dex */
public abstract class s0 {

    /* renamed from: a  reason: collision with root package name */
    private static final a5.a f9032a;

    /* renamed from: b  reason: collision with root package name */
    private static final t4.x f9033b;

    /* renamed from: c  reason: collision with root package name */
    private static final t4.w f9034c;

    /* renamed from: d  reason: collision with root package name */
    private static final t4.g f9035d;

    /* renamed from: e  reason: collision with root package name */
    private static final t4.f f9036e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9037a;

        static {
            int[] iArr = new int[y4.i0.values().length];
            f9037a = iArr;
            try {
                iArr[y4.i0.TINK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9037a[y4.i0.RAW.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    static {
        a5.a h8 = t4.k0.h("type.googleapis.com/google.crypto.tink.XAesGcmKey");
        f9032a = h8;
        f9033b = t4.x.a(new x.b() { // from class: l4.o0
            @Override // t4.x.b
            public final t4.h0 a(j4.w wVar) {
                t4.e0 j8;
                j8 = s0.j((o1) wVar);
                return j8;
            }
        }, o1.class, t4.e0.class);
        f9034c = t4.w.a(new w.b() { // from class: l4.p0
            @Override // t4.w.b
            public final j4.w a(t4.h0 h0Var) {
                o1 f8;
                f8 = s0.f((t4.e0) h0Var);
                return f8;
            }
        }, h8, t4.e0.class);
        f9035d = t4.g.a(new g.b() { // from class: l4.q0
            @Override // t4.g.b
            public final t4.h0 a(j4.h hVar, j4.y yVar) {
                t4.d0 i8;
                i8 = s0.i((k1) hVar, yVar);
                return i8;
            }
        }, k1.class, t4.d0.class);
        f9036e = t4.f.a(new f.b() { // from class: l4.r0
            @Override // t4.f.b
            public final j4.h a(t4.h0 h0Var, j4.y yVar) {
                k1 e8;
                e8 = s0.e((t4.d0) h0Var, yVar);
                return e8;
            }
        }, h8, t4.d0.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static k1 e(t4.d0 d0Var, j4.y yVar) {
        if (d0Var.f().equals("type.googleapis.com/google.crypto.tink.XAesGcmKey")) {
            try {
                y4.k0 b02 = y4.k0.b0(d0Var.g(), com.google.crypto.tink.shaded.protobuf.p.b());
                if (b02.Z() == 0) {
                    if (b02.X().size() == 32) {
                        return k1.d(o1.b(l(d0Var.e()), b02.Y().X()), a5.b.a(b02.X().w(), j4.y.b(yVar)), d0Var.c());
                    }
                    throw new GeneralSecurityException("Only 32 byte key size is accepted");
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (com.google.crypto.tink.shaded.protobuf.a0 unused) {
                throw new GeneralSecurityException("Parsing XAesGcmKey failed");
            }
        }
        throw new IllegalArgumentException("Wrong type URL in call to XAesGcmProtoSerialization.parseKey");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static o1 f(t4.e0 e0Var) {
        if (!e0Var.d().a0().equals("type.googleapis.com/google.crypto.tink.XAesGcmKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to XAesGcmProtoSerialization.parseParameters: " + e0Var.d().a0());
        }
        try {
            y4.l0 Z = y4.l0.Z(e0Var.d().b0(), com.google.crypto.tink.shaded.protobuf.p.b());
            if (Z.X() == 0) {
                return o1.b(l(e0Var.d().Z()), Z.W().X());
            }
            throw new GeneralSecurityException("Only version 0 parameters are accepted");
        } catch (com.google.crypto.tink.shaded.protobuf.a0 e8) {
            throw new GeneralSecurityException("Parsing XAesGcmParameters failed: ", e8);
        }
    }

    public static void g() {
        h(t4.u.c());
    }

    public static void h(t4.u uVar) {
        uVar.m(f9033b);
        uVar.l(f9034c);
        uVar.k(f9035d);
        uVar.j(f9036e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static t4.d0 i(k1 k1Var, j4.y yVar) {
        return t4.d0.b("type.googleapis.com/google.crypto.tink.XAesGcmKey", ((y4.k0) y4.k0.a0().q(com.google.crypto.tink.shaded.protobuf.h.l(k1Var.e().d(j4.y.b(yVar)))).r((y4.m0) y4.m0.Y().q(k1Var.b().c()).h()).h()).j(), y.c.SYMMETRIC, k(k1Var.b().d()), k1Var.a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static t4.e0 j(o1 o1Var) {
        return t4.e0.c((y4.a0) y4.a0.c0().r("type.googleapis.com/google.crypto.tink.XAesGcmKey").s(((y4.l0) y4.l0.Y().q((y4.m0) y4.m0.Y().q(o1Var.c()).h()).h()).j()).q(k(o1Var.d())).h());
    }

    private static y4.i0 k(o1.a aVar) {
        if (Objects.equals(aVar, o1.a.f8604b)) {
            return y4.i0.TINK;
        }
        if (Objects.equals(aVar, o1.a.f8605c)) {
            return y4.i0.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + aVar);
    }

    private static o1.a l(y4.i0 i0Var) {
        int i8 = a.f9037a[i0Var.ordinal()];
        if (i8 != 1) {
            if (i8 == 2) {
                return o1.a.f8605c;
            }
            throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + i0Var.f());
        }
        return o1.a.f8604b;
    }
}
