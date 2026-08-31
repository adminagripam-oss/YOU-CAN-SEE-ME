package k4;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import k4.z;
import o4.b;
import t4.p;
import t4.z;
import y4.y;
/* loaded from: classes.dex */
public abstract class y {

    /* renamed from: a  reason: collision with root package name */
    private static final t4.z f8690a = t4.z.b(new z.b() { // from class: k4.w
        @Override // t4.z.b
        public final Object a(j4.h hVar) {
            return m4.b.e((v) hVar);
        }
    }, v.class, j4.a.class);

    /* renamed from: b  reason: collision with root package name */
    private static final t4.d f8691b = new t4.d() { // from class: k4.x
        @Override // t4.d
        public final j4.h a(j4.w wVar, Integer num) {
            v b8;
            b8 = y.b((z) wVar, num);
            return b8;
        }
    };

    /* renamed from: c  reason: collision with root package name */
    private static final p.a f8692c = new h();

    /* renamed from: d  reason: collision with root package name */
    private static final j4.i f8693d = t4.h.e("type.googleapis.com/google.crypto.tink.AesGcmSivKey", j4.a.class, y.c.SYMMETRIC, y4.n.a0());

    /* JADX INFO: Access modifiers changed from: private */
    public static v b(z zVar, Integer num) {
        return v.d().e(zVar).c(num).d(a5.b.b(zVar.c())).a();
    }

    private static Map c() {
        HashMap hashMap = new HashMap();
        z.b b8 = z.b().b(16);
        z.c cVar = z.c.f8698b;
        hashMap.put("AES128_GCM_SIV", b8.c(cVar).a());
        z.b b9 = z.b().b(16);
        z.c cVar2 = z.c.f8700d;
        hashMap.put("AES128_GCM_SIV_RAW", b9.c(cVar2).a());
        hashMap.put("AES256_GCM_SIV", z.b().b(32).c(cVar).a());
        hashMap.put("AES256_GCM_SIV_RAW", z.b().b(32).c(cVar2).a());
        return Collections.unmodifiableMap(hashMap);
    }

    public static void d(boolean z7) {
        if (!b.EnumC0135b.f9848e.e()) {
            throw new GeneralSecurityException("Registering AES GCM SIV is not supported in FIPS mode");
        }
        l4.v.g();
        t4.q.b().d(c());
        t4.p.b().a(f8692c, z.class);
        t4.o.f().b(f8691b, z.class);
        t4.r.b().c(f8690a);
        t4.e.d().g(f8693d, z7);
    }
}
