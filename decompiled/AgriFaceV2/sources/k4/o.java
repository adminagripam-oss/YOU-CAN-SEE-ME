package k4;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import k4.p;
import o4.b;
import t4.z;
import y4.y;
/* loaded from: classes.dex */
public abstract class o {

    /* renamed from: a  reason: collision with root package name */
    private static final t4.z f8599a = t4.z.b(new z.b() { // from class: k4.m
        @Override // t4.z.b
        public final Object a(j4.h hVar) {
            return z4.b.c((l) hVar);
        }
    }, l.class, j4.a.class);

    /* renamed from: b  reason: collision with root package name */
    private static final j4.i f8600b = t4.h.e(c(), j4.a.class, y.c.SYMMETRIC, y4.i.c0());

    /* renamed from: c  reason: collision with root package name */
    private static final t4.d f8601c = new t4.d() { // from class: k4.n
        @Override // t4.d
        public final j4.h a(j4.w wVar, Integer num) {
            l b8;
            b8 = o.b((p) wVar, num);
            return b8;
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static l b(p pVar, Integer num) {
        f(pVar);
        return l.d().e(pVar).c(num).d(a5.b.b(pVar.d())).a();
    }

    static String c() {
        return "type.googleapis.com/google.crypto.tink.AesEaxKey";
    }

    private static Map d() {
        HashMap hashMap = new HashMap();
        hashMap.put("AES128_EAX", j1.f8543c);
        p.b d8 = p.b().b(16).c(16).d(16);
        p.c cVar = p.c.f8617d;
        hashMap.put("AES128_EAX_RAW", d8.e(cVar).a());
        hashMap.put("AES256_EAX", j1.f8544d);
        hashMap.put("AES256_EAX_RAW", p.b().b(16).c(32).d(16).e(cVar).a());
        return Collections.unmodifiableMap(hashMap);
    }

    public static void e(boolean z7) {
        if (!b.EnumC0135b.f9848e.e()) {
            throw new GeneralSecurityException("Registering AES EAX is not supported in FIPS mode");
        }
        l4.j.h();
        t4.r.b().c(f8599a);
        t4.q.b().d(d());
        t4.o.f().b(f8601c, p.class);
        t4.e.d().g(f8600b, z7);
    }

    private static final void f(p pVar) {
        if (pVar.d() == 24) {
            throw new GeneralSecurityException("192 bit AES EAX Parameters are not valid");
        }
    }
}
