package p4;

import j4.w;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import o4.b;
import p4.e;
import t4.o;
import t4.p;
import t4.q;
import t4.r;
import t4.z;
import y4.p;
import y4.y;
/* loaded from: classes.dex */
public abstract class d {

    /* renamed from: a  reason: collision with root package name */
    private static final z f10098a = z.b(new z.b() { // from class: p4.b
        @Override // t4.z.b
        public final Object a(j4.h hVar) {
            j4.f b8;
            b8 = d.b((a) hVar);
            return b8;
        }
    }, a.class, j4.f.class);

    /* renamed from: b  reason: collision with root package name */
    private static final j4.i f10099b = t4.h.e(c(), j4.f.class, y.c.SYMMETRIC, p.a0());

    /* renamed from: c  reason: collision with root package name */
    private static final p.a f10100c = new k4.h();

    /* renamed from: d  reason: collision with root package name */
    private static final t4.d f10101d = new t4.d() { // from class: p4.c
        @Override // t4.d
        public final j4.h a(w wVar, Integer num) {
            return d.e((e) wVar, num);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static j4.f b(a aVar) {
        g(aVar.b());
        return z4.d.c(aVar);
    }

    static String c() {
        return "type.googleapis.com/google.crypto.tink.AesSivKey";
    }

    private static Map d() {
        HashMap hashMap = new HashMap();
        hashMap.put("AES256_SIV", l.f10115a);
        hashMap.put("AES256_SIV_RAW", e.b().b(64).c(e.c.f10108d).a());
        return Collections.unmodifiableMap(hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static a e(e eVar, Integer num) {
        g(eVar);
        return a.d().e(eVar).c(num).d(a5.b.b(eVar.c())).a();
    }

    public static void f(boolean z7) {
        if (!b.EnumC0135b.f9848e.e()) {
            throw new GeneralSecurityException("Registering AES SIV is not supported in FIPS mode");
        }
        q4.e.i();
        r.b().c(f10098a);
        q.b().d(d());
        t4.p.b().a(f10100c, e.class);
        o.f().b(f10101d, e.class);
        t4.e.d().g(f10099b, z7);
    }

    private static void g(e eVar) {
        if (eVar.c() == 64) {
            return;
        }
        throw new InvalidAlgorithmParameterException("invalid key size: " + eVar.c() + ". Valid keys must have 64 bytes.");
    }
}
