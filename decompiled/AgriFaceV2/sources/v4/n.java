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
import t4.w;
import t4.x;
import u4.n;
import y4.i0;
import y4.u;
import y4.v;
import y4.y;
/* loaded from: classes.dex */
public abstract class n {

    /* renamed from: a  reason: collision with root package name */
    private static final a5.a f12375a;

    /* renamed from: b  reason: collision with root package name */
    private static final t4.c f12376b;

    /* renamed from: c  reason: collision with root package name */
    private static final t4.c f12377c;

    /* renamed from: d  reason: collision with root package name */
    private static final x f12378d;

    /* renamed from: e  reason: collision with root package name */
    private static final w f12379e;

    /* renamed from: f  reason: collision with root package name */
    private static final t4.g f12380f;

    /* renamed from: g  reason: collision with root package name */
    private static final t4.f f12381g;

    static {
        a5.a h8 = k0.h("type.googleapis.com/google.crypto.tink.HmacKey");
        f12375a = h8;
        f12376b = t4.c.a().a(i0.RAW, n.d.f12153e).a(i0.TINK, n.d.f12150b).a(i0.LEGACY, n.d.f12152d).a(i0.CRUNCHY, n.d.f12151c).b();
        f12377c = t4.c.a().a(u.SHA1, n.c.f12144b).a(u.SHA224, n.c.f12145c).a(u.SHA256, n.c.f12146d).a(u.SHA384, n.c.f12147e).a(u.SHA512, n.c.f12148f).b();
        f12378d = x.a(new x.b() { // from class: v4.j
            @Override // t4.x.b
            public final h0 a(j4.w wVar) {
                e0 k8;
                k8 = n.k((u4.n) wVar);
                return k8;
            }
        }, u4.n.class, e0.class);
        f12379e = w.a(new w.b() { // from class: v4.k
            @Override // t4.w.b
            public final j4.w a(h0 h0Var) {
                u4.n g8;
                g8 = n.g((e0) h0Var);
                return g8;
            }
        }, h8, e0.class);
        f12380f = t4.g.a(new g.b() { // from class: v4.l
            @Override // t4.g.b
            public final h0 a(j4.h hVar, y yVar) {
                d0 j8;
                j8 = n.j((u4.i) hVar, yVar);
                return j8;
            }
        }, u4.i.class, d0.class);
        f12381g = t4.f.a(new f.b() { // from class: v4.m
            @Override // t4.f.b
            public final j4.h a(h0 h0Var, y yVar) {
                u4.i f8;
                f8 = n.f((d0) h0Var, yVar);
                return f8;
            }
        }, h8, d0.class);
    }

    private static y4.x e(u4.n nVar) {
        return (y4.x) y4.x.a0().r(nVar.c()).q((u) f12377c.c(nVar.d())).h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static u4.i f(d0 d0Var, y yVar) {
        if (d0Var.f().equals("type.googleapis.com/google.crypto.tink.HmacKey")) {
            try {
                v c02 = v.c0(d0Var.g(), com.google.crypto.tink.shaded.protobuf.p.b());
                if (c02.a0() == 0) {
                    return u4.i.d().e(u4.n.b().c(c02.Y().size()).d(c02.Z().Z()).b((n.c) f12377c.b(c02.Z().Y())).e((n.d) f12376b.b(d0Var.e())).a()).d(a5.b.a(c02.Y().w(), y.b(yVar))).c(d0Var.c()).a();
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (a0 | IllegalArgumentException unused) {
                throw new GeneralSecurityException("Parsing HmacKey failed");
            }
        }
        throw new IllegalArgumentException("Wrong type URL in call to HmacProtoSerialization.parseKey");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static u4.n g(e0 e0Var) {
        if (!e0Var.d().a0().equals("type.googleapis.com/google.crypto.tink.HmacKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to HmacProtoSerialization.parseParameters: " + e0Var.d().a0());
        }
        try {
            y4.w c02 = y4.w.c0(e0Var.d().b0(), com.google.crypto.tink.shaded.protobuf.p.b());
            if (c02.a0() == 0) {
                return u4.n.b().c(c02.Y()).d(c02.Z().Z()).b((n.c) f12377c.b(c02.Z().Y())).e((n.d) f12376b.b(e0Var.d().Z())).a();
            }
            throw new GeneralSecurityException("Parsing HmacParameters failed: unknown Version " + c02.a0());
        } catch (a0 e8) {
            throw new GeneralSecurityException("Parsing HmacParameters failed: ", e8);
        }
    }

    public static void h() {
        i(t4.u.c());
    }

    public static void i(t4.u uVar) {
        uVar.m(f12378d);
        uVar.l(f12379e);
        uVar.k(f12380f);
        uVar.j(f12381g);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static d0 j(u4.i iVar, y yVar) {
        return d0.b("type.googleapis.com/google.crypto.tink.HmacKey", ((v) v.b0().r(e(iVar.b())).q(com.google.crypto.tink.shaded.protobuf.h.l(iVar.e().d(y.b(yVar)))).h()).j(), y.c.SYMMETRIC, (i0) f12376b.c(iVar.b().g()), iVar.a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static e0 k(u4.n nVar) {
        return e0.c((y4.a0) y4.a0.c0().r("type.googleapis.com/google.crypto.tink.HmacKey").s(((y4.w) y4.w.b0().r(e(nVar)).q(nVar.e()).h()).j()).q((i0) f12376b.c(nVar.g())).h());
    }
}
