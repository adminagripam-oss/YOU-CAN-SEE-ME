package k4;

import java.security.GeneralSecurityException;
import o4.b;
import t4.z;
import y4.y;
/* loaded from: classes.dex */
public abstract class l0 {

    /* renamed from: a  reason: collision with root package name */
    private static final j4.i f8586a = t4.h.e(d(), j4.a.class, y.c.SYMMETRIC, y4.g0.a0());

    /* renamed from: b  reason: collision with root package name */
    private static final t4.d f8587b = new t4.d() { // from class: k4.j0
        @Override // t4.d
        public final j4.h a(j4.w wVar, Integer num) {
            t0 e8;
            e8 = l0.e((u0) wVar, num);
            return e8;
        }
    };

    /* renamed from: c  reason: collision with root package name */
    private static final t4.z f8588c = t4.z.b(new z.b() { // from class: k4.k0
        @Override // t4.z.b
        public final Object a(j4.h hVar) {
            j4.a c8;
            c8 = l0.c((t0) hVar);
            return c8;
        }
    }, t0.class, j4.a.class);

    /* JADX INFO: Access modifiers changed from: private */
    public static j4.a c(t0 t0Var) {
        String d8 = t0Var.b().d();
        return l4.k0.c(i0.d(t0Var.b().c(), j4.t.a(d8).a(d8)), t0Var.c());
    }

    static String d() {
        return "type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static t0 e(u0 u0Var, Integer num) {
        return t0.d(u0Var, num);
    }

    public static void f(boolean z7) {
        if (!b.EnumC0135b.f9848e.e()) {
            throw new GeneralSecurityException("Registering KMS Envelope AEAD is not supported in FIPS mode");
        }
        z0.h();
        t4.o.f().b(f8587b, u0.class);
        t4.r.b().c(f8588c);
        t4.e.d().g(f8586a, z7);
    }
}
