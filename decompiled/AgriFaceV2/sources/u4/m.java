package u4;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import o4.b;
import t4.p;
import t4.z;
import u4.n;
import y4.y;
/* loaded from: classes.dex */
public abstract class m {

    /* renamed from: a  reason: collision with root package name */
    private static final z f12130a = z.b(new z.b() { // from class: u4.j
        @Override // t4.z.b
        public final Object a(j4.h hVar) {
            return new v4.i((i) hVar);
        }
    }, i.class, g.class);

    /* renamed from: b  reason: collision with root package name */
    private static final z f12131b = z.b(new z.b() { // from class: u4.k
        @Override // t4.z.b
        public final Object a(j4.h hVar) {
            return z4.o.d((i) hVar);
        }
    }, i.class, j4.v.class);

    /* renamed from: c  reason: collision with root package name */
    private static final j4.i f12132c = t4.h.e("type.googleapis.com/google.crypto.tink.HmacKey", j4.v.class, y.c.SYMMETRIC, y4.v.d0());

    /* renamed from: d  reason: collision with root package name */
    private static final p.a f12133d = new k4.h();

    /* renamed from: e  reason: collision with root package name */
    private static final t4.d f12134e = new t4.d() { // from class: u4.l
        @Override // t4.d
        public final j4.h a(j4.w wVar, Integer num) {
            return m.a((n) wVar, num);
        }
    };

    /* renamed from: f  reason: collision with root package name */
    private static final b.EnumC0135b f12135f = b.EnumC0135b.f9849f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static i a(n nVar, Integer num) {
        return i.d().e(nVar).d(a5.b.b(nVar.e())).c(num).a();
    }

    private static Map b() {
        HashMap hashMap = new HashMap();
        hashMap.put("HMAC_SHA256_128BITTAG", y.f12161a);
        n.b d8 = n.b().c(32).d(16);
        n.d dVar = n.d.f12153e;
        n.b e8 = d8.e(dVar);
        n.c cVar = n.c.f12146d;
        hashMap.put("HMAC_SHA256_128BITTAG_RAW", e8.b(cVar).a());
        n.b d9 = n.b().c(32).d(32);
        n.d dVar2 = n.d.f12150b;
        hashMap.put("HMAC_SHA256_256BITTAG", d9.e(dVar2).b(cVar).a());
        hashMap.put("HMAC_SHA256_256BITTAG_RAW", n.b().c(32).d(32).e(dVar).b(cVar).a());
        n.b e9 = n.b().c(64).d(16).e(dVar2);
        n.c cVar2 = n.c.f12148f;
        hashMap.put("HMAC_SHA512_128BITTAG", e9.b(cVar2).a());
        hashMap.put("HMAC_SHA512_128BITTAG_RAW", n.b().c(64).d(16).e(dVar).b(cVar2).a());
        hashMap.put("HMAC_SHA512_256BITTAG", n.b().c(64).d(32).e(dVar2).b(cVar2).a());
        hashMap.put("HMAC_SHA512_256BITTAG_RAW", n.b().c(64).d(32).e(dVar).b(cVar2).a());
        hashMap.put("HMAC_SHA512_512BITTAG", y.f12164d);
        hashMap.put("HMAC_SHA512_512BITTAG_RAW", n.b().c(64).d(64).e(dVar).b(cVar2).a());
        return Collections.unmodifiableMap(hashMap);
    }

    public static void c(boolean z7) {
        b.EnumC0135b enumC0135b = f12135f;
        if (!enumC0135b.e()) {
            throw new GeneralSecurityException("Can not use HMAC in FIPS-mode, as BoringCrypto module is not available.");
        }
        v4.n.h();
        t4.r.b().c(f12130a);
        t4.r.b().c(f12131b);
        t4.q.b().d(b());
        t4.o.f().b(f12134e, n.class);
        t4.p.b().a(f12133d, n.class);
        t4.e.d().h(f12132c, enumC0135b, z7);
    }
}
