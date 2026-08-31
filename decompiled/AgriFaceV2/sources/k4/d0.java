package k4;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import k4.e0;
import o4.b;
import t4.z;
import y4.y;
/* loaded from: classes.dex */
public abstract class d0 {

    /* renamed from: a  reason: collision with root package name */
    private static final t4.z f8509a = t4.z.b(new z.b() { // from class: k4.b0
        @Override // t4.z.b
        public final Object a(j4.h hVar) {
            j4.a b8;
            b8 = d0.b((a0) hVar);
            return b8;
        }
    }, a0.class, j4.a.class);

    /* renamed from: b  reason: collision with root package name */
    private static final t4.d f8510b = new t4.d() { // from class: k4.c0
        @Override // t4.d
        public final j4.h a(j4.w wVar, Integer num) {
            return d0.c((e0) wVar, num);
        }
    };

    /* renamed from: c  reason: collision with root package name */
    private static final j4.i f8511c = t4.h.e(d(), j4.a.class, y.c.SYMMETRIC, y4.r.a0());

    /* JADX INFO: Access modifiers changed from: private */
    public static j4.a b(a0 a0Var) {
        return l4.w.f() ? l4.w.c(a0Var) : z4.g.c(a0Var);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static a0 c(e0 e0Var, Integer num) {
        return a0.d(e0Var.c(), a5.b.b(32), num);
    }

    static String d() {
        return "type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key";
    }

    private static Map e() {
        HashMap hashMap = new HashMap();
        hashMap.put("CHACHA20_POLY1305", e0.b(e0.a.f8515b));
        hashMap.put("CHACHA20_POLY1305_RAW", e0.b(e0.a.f8517d));
        return Collections.unmodifiableMap(hashMap);
    }

    public static void f(boolean z7) {
        if (!b.EnumC0135b.f9848e.e()) {
            throw new GeneralSecurityException("Registering ChaCha20Poly1305 is not supported in FIPS mode");
        }
        l4.b0.g();
        t4.r.b().c(f8509a);
        t4.o.f().b(f8510b, e0.class);
        t4.q.b().d(e());
        t4.e.d().g(f8511c, z7);
    }
}
