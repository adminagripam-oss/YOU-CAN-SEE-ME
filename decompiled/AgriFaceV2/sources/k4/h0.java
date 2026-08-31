package k4;

import java.security.GeneralSecurityException;
import o4.b;
import t4.z;
import y4.y;
/* loaded from: classes.dex */
public abstract class h0 {

    /* renamed from: a  reason: collision with root package name */
    private static final t4.z f8528a = t4.z.b(new z.b() { // from class: k4.f0
        @Override // t4.z.b
        public final Object a(j4.h hVar) {
            j4.a c8;
            c8 = h0.c((m0) hVar);
            return c8;
        }
    }, m0.class, j4.a.class);

    /* renamed from: b  reason: collision with root package name */
    private static final j4.i f8529b = t4.h.e(d(), j4.a.class, y.c.REMOTE, y4.e0.a0());

    /* renamed from: c  reason: collision with root package name */
    private static final t4.d f8530c = new t4.d() { // from class: k4.g0
        @Override // t4.d
        public final j4.h a(j4.w wVar, Integer num) {
            m0 e8;
            e8 = h0.e((n0) wVar, num);
            return e8;
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static j4.a c(m0 m0Var) {
        return l4.k0.c(j4.t.a(m0Var.b().c()).a(m0Var.b().c()), m0Var.c());
    }

    static String d() {
        return "type.googleapis.com/google.crypto.tink.KmsAeadKey";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static m0 e(n0 n0Var, Integer num) {
        return m0.d(n0Var, num);
    }

    public static void f(boolean z7) {
        if (!b.EnumC0135b.f9848e.e()) {
            throw new GeneralSecurityException("Registering KMS AEAD is not supported in FIPS mode");
        }
        s0.g();
        t4.r.b().c(f8528a);
        t4.o.f().b(f8530c, n0.class);
        t4.e.d().g(f8529b, z7);
    }
}
