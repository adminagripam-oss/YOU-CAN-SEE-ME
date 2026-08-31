package k4;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import k4.u;
import o4.b;
import t4.p;
import t4.z;
import y4.y;
/* loaded from: classes.dex */
public abstract class t {

    /* renamed from: a  reason: collision with root package name */
    private static final t4.z f8640a = t4.z.b(new z.b() { // from class: k4.r
        @Override // t4.z.b
        public final Object a(j4.h hVar) {
            return z4.c.c((q) hVar);
        }
    }, q.class, j4.a.class);

    /* renamed from: b  reason: collision with root package name */
    private static final j4.i f8641b = t4.h.e(c(), j4.a.class, y.c.SYMMETRIC, y4.l.a0());

    /* renamed from: c  reason: collision with root package name */
    private static final p.a f8642c = new h();

    /* renamed from: d  reason: collision with root package name */
    private static final t4.d f8643d = new t4.d() { // from class: k4.s
        @Override // t4.d
        public final j4.h a(j4.w wVar, Integer num) {
            q b8;
            b8 = t.b((u) wVar, num);
            return b8;
        }
    };

    /* renamed from: e  reason: collision with root package name */
    private static final b.EnumC0135b f8644e = b.EnumC0135b.f9849f;

    /* JADX INFO: Access modifiers changed from: private */
    public static q b(u uVar, Integer num) {
        f(uVar);
        return q.d().e(uVar).c(num).d(a5.b.b(uVar.d())).a();
    }

    static String c() {
        return "type.googleapis.com/google.crypto.tink.AesGcmKey";
    }

    private static Map d() {
        HashMap hashMap = new HashMap();
        hashMap.put("AES128_GCM", j1.f8541a);
        u.b d8 = u.b().b(12).c(16).d(16);
        u.c cVar = u.c.f8663d;
        hashMap.put("AES128_GCM_RAW", d8.e(cVar).a());
        hashMap.put("AES256_GCM", j1.f8542b);
        hashMap.put("AES256_GCM_RAW", u.b().b(12).c(32).d(16).e(cVar).a());
        return Collections.unmodifiableMap(hashMap);
    }

    public static void e(boolean z7) {
        b.EnumC0135b enumC0135b = f8644e;
        if (!enumC0135b.e()) {
            throw new GeneralSecurityException("Can not use AES-GCM in FIPS-mode, as BoringCrypto module is not available.");
        }
        l4.p.g();
        t4.r.b().c(f8640a);
        t4.q.b().d(d());
        t4.p.b().a(f8642c, u.class);
        t4.o.f().b(f8643d, u.class);
        t4.e.d().h(f8641b, enumC0135b, z7);
    }

    private static final void f(u uVar) {
        if (uVar.d() == 24) {
            throw new GeneralSecurityException("192 bit AES GCM Parameters are not valid");
        }
    }
}
