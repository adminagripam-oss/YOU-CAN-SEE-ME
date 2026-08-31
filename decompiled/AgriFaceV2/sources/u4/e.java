package u4;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import o4.b;
import t4.z;
import u4.f;
import y4.y;
/* loaded from: classes.dex */
public abstract class e {

    /* renamed from: a  reason: collision with root package name */
    private static final t4.d f12107a = new t4.d() { // from class: u4.b
        @Override // t4.d
        public final j4.h a(j4.w wVar, Integer num) {
            a d8;
            d8 = e.d((f) wVar, num);
            return d8;
        }
    };

    /* renamed from: b  reason: collision with root package name */
    private static final z f12108b = z.b(new z.b() { // from class: u4.c
        @Override // t4.z.b
        public final Object a(j4.h hVar) {
            g e8;
            e8 = e.e((a) hVar);
            return e8;
        }
    }, a.class, g.class);

    /* renamed from: c  reason: collision with root package name */
    private static final z f12109c = z.b(new z.b() { // from class: u4.d
        @Override // t4.z.b
        public final Object a(j4.h hVar) {
            j4.v f8;
            f8 = e.f((a) hVar);
            return f8;
        }
    }, a.class, j4.v.class);

    /* renamed from: d  reason: collision with root package name */
    private static final j4.i f12110d = t4.h.e("type.googleapis.com/google.crypto.tink.AesCmacKey", j4.v.class, y.c.SYMMETRIC, y4.a.c0());

    /* JADX INFO: Access modifiers changed from: private */
    public static a d(f fVar, Integer num) {
        i(fVar);
        return a.d().e(fVar).c(a5.b.b(fVar.d())).d(num).a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static g e(a aVar) {
        i(aVar.b());
        return v4.h.a(aVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static j4.v f(a aVar) {
        i(aVar.b());
        return z4.o.c(aVar);
    }

    private static Map g() {
        HashMap hashMap = new HashMap();
        f fVar = y.f12165e;
        hashMap.put("AES_CMAC", fVar);
        hashMap.put("AES256_CMAC", fVar);
        hashMap.put("AES256_CMAC_RAW", f.b().b(32).c(16).d(f.c.f12120e).a());
        return Collections.unmodifiableMap(hashMap);
    }

    public static void h(boolean z7) {
        if (!b.EnumC0135b.f9848e.e()) {
            throw new GeneralSecurityException("Registering AES CMAC is not supported in FIPS mode");
        }
        v4.e.h();
        t4.o.f().b(f12107a, f.class);
        t4.r.b().c(f12108b);
        t4.r.b().c(f12109c);
        t4.q.b().d(g());
        t4.e.d().g(f12110d, z7);
    }

    private static void i(f fVar) {
        if (fVar.d() != 32) {
            throw new GeneralSecurityException("AesCmacKey size wrong, must be 32 bytes");
        }
    }
}
