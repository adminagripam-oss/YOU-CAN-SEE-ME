package k4;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import k4.t1;
import o4.b;
import t4.p;
import t4.z;
import y4.y;
/* loaded from: classes.dex */
public abstract class s1 {

    /* renamed from: a  reason: collision with root package name */
    private static final t4.z f8636a = t4.z.b(new z.b() { // from class: k4.q1
        @Override // t4.z.b
        public final Object a(j4.h hVar) {
            j4.a b8;
            b8 = s1.b((p1) hVar);
            return b8;
        }
    }, p1.class, j4.a.class);

    /* renamed from: b  reason: collision with root package name */
    private static final j4.i f8637b = t4.h.e(d(), j4.a.class, y.c.SYMMETRIC, y4.n0.a0());

    /* renamed from: c  reason: collision with root package name */
    private static final p.a f8638c = new h();

    /* renamed from: d  reason: collision with root package name */
    private static final t4.d f8639d = new t4.d() { // from class: k4.r1
        @Override // t4.d
        public final j4.h a(j4.w wVar, Integer num) {
            return s1.c((t1) wVar, num);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static j4.a b(p1 p1Var) {
        return l4.t0.e() ? l4.t0.c(p1Var) : z4.s.c(p1Var);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static p1 c(t1 t1Var, Integer num) {
        return p1.d(t1Var.c(), a5.b.b(32), num);
    }

    static String d() {
        return "type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key";
    }

    private static Map e() {
        HashMap hashMap = new HashMap();
        hashMap.put("XCHACHA20_POLY1305", t1.b(t1.a.f8649b));
        hashMap.put("XCHACHA20_POLY1305_RAW", t1.b(t1.a.f8651d));
        return Collections.unmodifiableMap(hashMap);
    }

    public static void f(boolean z7) {
        if (!b.EnumC0135b.f9848e.e()) {
            throw new GeneralSecurityException("Registering XChaCha20Poly1305 is not supported in FIPS mode");
        }
        l4.y0.g();
        t4.r.b().c(f8636a);
        t4.q.b().d(e());
        t4.o.f().b(f8639d, t1.class);
        t4.p.b().a(f8638c, t1.class);
        t4.e.d().g(f8637b, z7);
    }
}
