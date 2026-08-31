package v4;

import com.google.crypto.tink.shaded.protobuf.a0;
import j4.y;
import java.security.GeneralSecurityException;
import t4.d0;
import t4.e0;
import t4.f;
import t4.g;
import t4.h0;
import t4.k0;
import t4.u;
import t4.w;
import t4.x;
import u4.f;
import y4.i0;
import y4.y;
/* loaded from: classes.dex */
public abstract class e {

    /* renamed from: a  reason: collision with root package name */
    private static final a5.a f12360a;

    /* renamed from: b  reason: collision with root package name */
    private static final x f12361b;

    /* renamed from: c  reason: collision with root package name */
    private static final w f12362c;

    /* renamed from: d  reason: collision with root package name */
    private static final t4.g f12363d;

    /* renamed from: e  reason: collision with root package name */
    private static final t4.f f12364e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f12365a;

        static {
            int[] iArr = new int[i0.values().length];
            f12365a = iArr;
            try {
                iArr[i0.TINK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f12365a[i0.CRUNCHY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f12365a[i0.LEGACY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f12365a[i0.RAW.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    static {
        a5.a h8 = k0.h("type.googleapis.com/google.crypto.tink.AesCmacKey");
        f12360a = h8;
        f12361b = x.a(new x.b() { // from class: v4.a
            @Override // t4.x.b
            public final h0 a(j4.w wVar) {
                e0 k8;
                k8 = e.k((u4.f) wVar);
                return k8;
            }
        }, u4.f.class, e0.class);
        f12362c = w.a(new w.b() { // from class: v4.b
            @Override // t4.w.b
            public final j4.w a(h0 h0Var) {
                u4.f g8;
                g8 = e.g((e0) h0Var);
                return g8;
            }
        }, h8, e0.class);
        f12363d = t4.g.a(new g.b() { // from class: v4.c
            @Override // t4.g.b
            public final h0 a(j4.h hVar, y yVar) {
                d0 j8;
                j8 = e.j((u4.a) hVar, yVar);
                return j8;
            }
        }, u4.a.class, d0.class);
        f12364e = t4.f.a(new f.b() { // from class: v4.d
            @Override // t4.f.b
            public final j4.h a(h0 h0Var, y yVar) {
                u4.a f8;
                f8 = e.f((d0) h0Var, yVar);
                return f8;
            }
        }, h8, d0.class);
    }

    private static y4.c e(u4.f fVar) {
        return (y4.c) y4.c.Y().q(fVar.c()).h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static u4.a f(d0 d0Var, y yVar) {
        if (d0Var.f().equals("type.googleapis.com/google.crypto.tink.AesCmacKey")) {
            try {
                y4.a b02 = y4.a.b0(d0Var.g(), com.google.crypto.tink.shaded.protobuf.p.b());
                if (b02.Z() == 0) {
                    return u4.a.d().e(u4.f.b().b(b02.X().size()).c(b02.Y().X()).d(m(d0Var.e())).a()).c(a5.b.a(b02.X().w(), y.b(yVar))).d(d0Var.c()).a();
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (a0 | IllegalArgumentException unused) {
                throw new GeneralSecurityException("Parsing AesCmacKey failed");
            }
        }
        throw new IllegalArgumentException("Wrong type URL in call to AesCmacProtoSerialization.parseKey");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static u4.f g(e0 e0Var) {
        if (e0Var.d().a0().equals("type.googleapis.com/google.crypto.tink.AesCmacKey")) {
            try {
                y4.b a02 = y4.b.a0(e0Var.d().b0(), com.google.crypto.tink.shaded.protobuf.p.b());
                return u4.f.b().b(a02.X()).c(a02.Y().X()).d(m(e0Var.d().Z())).a();
            } catch (a0 e8) {
                throw new GeneralSecurityException("Parsing AesCmacParameters failed: ", e8);
            }
        }
        throw new IllegalArgumentException("Wrong type URL in call to AesCmacProtoSerialization.parseParameters: " + e0Var.d().a0());
    }

    public static void h() {
        i(u.c());
    }

    public static void i(u uVar) {
        uVar.m(f12361b);
        uVar.l(f12362c);
        uVar.k(f12363d);
        uVar.j(f12364e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static d0 j(u4.a aVar, y yVar) {
        return d0.b("type.googleapis.com/google.crypto.tink.AesCmacKey", ((y4.a) y4.a.a0().r(e(aVar.b())).q(com.google.crypto.tink.shaded.protobuf.h.l(aVar.e().d(y.b(yVar)))).h()).j(), y.c.SYMMETRIC, l(aVar.b().f()), aVar.a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static e0 k(u4.f fVar) {
        return e0.c((y4.a0) y4.a0.c0().r("type.googleapis.com/google.crypto.tink.AesCmacKey").s(((y4.b) y4.b.Z().r(e(fVar)).q(fVar.d()).h()).j()).q(l(fVar.f())).h());
    }

    private static i0 l(f.c cVar) {
        if (f.c.f12117b.equals(cVar)) {
            return i0.TINK;
        }
        if (f.c.f12118c.equals(cVar)) {
            return i0.CRUNCHY;
        }
        if (f.c.f12120e.equals(cVar)) {
            return i0.RAW;
        }
        if (f.c.f12119d.equals(cVar)) {
            return i0.LEGACY;
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + cVar);
    }

    private static f.c m(i0 i0Var) {
        int i8 = a.f12365a[i0Var.ordinal()];
        if (i8 != 1) {
            if (i8 != 2) {
                if (i8 != 3) {
                    if (i8 == 4) {
                        return f.c.f12120e;
                    }
                    throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + i0Var.f());
                }
                return f.c.f12119d;
            }
            return f.c.f12118c;
        }
        return f.c.f12117b;
    }
}
