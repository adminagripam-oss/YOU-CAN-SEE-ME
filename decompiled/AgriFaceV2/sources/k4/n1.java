package k4;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import t4.z;
/* loaded from: classes.dex */
public abstract class n1 {

    /* renamed from: a  reason: collision with root package name */
    private static final t4.d f8597a = new t4.d() { // from class: k4.l1
        @Override // t4.d
        public final j4.h a(j4.w wVar, Integer num) {
            k1 b8;
            b8 = n1.b((o1) wVar, num);
            return b8;
        }
    };

    /* renamed from: b  reason: collision with root package name */
    private static final t4.z f8598b = t4.z.b(new z.b() { // from class: k4.m1
        @Override // t4.z.b
        public final Object a(j4.h hVar) {
            return l4.n0.c((k1) hVar);
        }
    }, k1.class, j4.a.class);

    /* JADX INFO: Access modifiers changed from: private */
    public static k1 b(o1 o1Var, Integer num) {
        return k1.d(o1Var, a5.b.b(32), num);
    }

    private static Map c() {
        HashMap hashMap = new HashMap();
        hashMap.put("XAES_256_GCM_192_BIT_NONCE", j1.f8549i);
        hashMap.put("XAES_256_GCM_192_BIT_NONCE_NO_PREFIX", j1.f8550j);
        hashMap.put("XAES_256_GCM_160_BIT_NONCE_NO_PREFIX", j1.f8551k);
        hashMap.put("X_AES_GCM_8_BYTE_SALT_NO_PREFIX", j1.f8552l);
        return Collections.unmodifiableMap(hashMap);
    }

    public static void d(boolean z7) {
        l4.s0.g();
        t4.q.b().d(c());
        t4.r.b().c(f8598b);
        t4.o.f().b(f8597a, o1.class);
    }
}
