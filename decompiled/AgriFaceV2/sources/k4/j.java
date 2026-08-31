package k4;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import k4.k;
import o4.b;
import t4.p;
import t4.z;
import y4.y;
/* loaded from: classes.dex */
public abstract class j {

    /* renamed from: a  reason: collision with root package name */
    private static final t4.z f8536a = t4.z.b(new z.b() { // from class: k4.g
        @Override // t4.z.b
        public final Object a(j4.h hVar) {
            return z4.h.c((f) hVar);
        }
    }, f.class, j4.a.class);

    /* renamed from: b  reason: collision with root package name */
    private static final j4.i f8537b = t4.h.e(b(), j4.a.class, y.c.SYMMETRIC, y4.d.c0());

    /* renamed from: c  reason: collision with root package name */
    private static final p.a f8538c = new h();

    /* renamed from: d  reason: collision with root package name */
    private static final t4.d f8539d = new t4.d() { // from class: k4.i
        @Override // t4.d
        public final j4.h a(j4.w wVar, Integer num) {
            return j.a((k) wVar, num);
        }
    };

    /* renamed from: e  reason: collision with root package name */
    private static final b.EnumC0135b f8540e = b.EnumC0135b.f9849f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static f a(k kVar, Integer num) {
        e(kVar);
        return f.d().f(kVar).e(num).c(a5.b.b(kVar.c())).d(a5.b.b(kVar.e())).a();
    }

    static String b() {
        return "type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey";
    }

    private static Map c() {
        HashMap hashMap = new HashMap();
        hashMap.put("AES128_CTR_HMAC_SHA256", j1.f8545e);
        k.b e8 = k.b().b(16).d(32).f(16).e(16);
        k.c cVar = k.c.f8567d;
        k.b c8 = e8.c(cVar);
        k.d dVar = k.d.f8573d;
        hashMap.put("AES128_CTR_HMAC_SHA256_RAW", c8.g(dVar).a());
        hashMap.put("AES256_CTR_HMAC_SHA256", j1.f8546f);
        hashMap.put("AES256_CTR_HMAC_SHA256_RAW", k.b().b(32).d(32).f(32).e(16).c(cVar).g(dVar).a());
        return Collections.unmodifiableMap(hashMap);
    }

    public static void d(boolean z7) {
        b.EnumC0135b enumC0135b = f8540e;
        if (!enumC0135b.e()) {
            throw new GeneralSecurityException("Can not use AES-CTR-HMAC in FIPS-mode, as BoringCrypto module is not available.");
        }
        l4.e.h();
        t4.r.b().c(f8536a);
        t4.q.b().d(c());
        t4.p.b().a(f8538c, k.class);
        t4.o.f().b(f8539d, k.class);
        t4.e.d().h(f8537b, enumC0135b, z7);
    }

    private static void e(k kVar) {
        if (kVar.c() != 16 && kVar.c() != 32) {
            throw new GeneralSecurityException("AES key size must be 16 or 32 bytes");
        }
    }
}
