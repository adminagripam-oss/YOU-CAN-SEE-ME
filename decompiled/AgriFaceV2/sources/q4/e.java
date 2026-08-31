package q4;

import com.google.crypto.tink.shaded.protobuf.a0;
import j4.h;
import j4.y;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import p4.e;
import t4.d0;
import t4.e0;
import t4.f;
import t4.g;
import t4.h0;
import t4.k0;
import t4.u;
import t4.w;
import t4.x;
import y4.i0;
import y4.p;
import y4.q;
import y4.y;
/* loaded from: classes.dex */
public abstract class e {

    /* renamed from: a  reason: collision with root package name */
    private static final a5.a f10336a;

    /* renamed from: b  reason: collision with root package name */
    private static final x f10337b;

    /* renamed from: c  reason: collision with root package name */
    private static final w f10338c;

    /* renamed from: d  reason: collision with root package name */
    private static final t4.g f10339d;

    /* renamed from: e  reason: collision with root package name */
    private static final t4.f f10340e;

    /* renamed from: f  reason: collision with root package name */
    private static final Map f10341f;

    /* renamed from: g  reason: collision with root package name */
    private static final Map f10342g;

    static {
        a5.a h8 = k0.h("type.googleapis.com/google.crypto.tink.AesSivKey");
        f10336a = h8;
        f10337b = x.a(new x.b() { // from class: q4.a
            @Override // t4.x.b
            public final h0 a(j4.w wVar) {
                e0 l8;
                l8 = e.l((p4.e) wVar);
                return l8;
            }
        }, p4.e.class, e0.class);
        f10338c = w.a(new w.b() { // from class: q4.b
            @Override // t4.w.b
            public final j4.w a(h0 h0Var) {
                p4.e h9;
                h9 = e.h((e0) h0Var);
                return h9;
            }
        }, h8, e0.class);
        f10339d = t4.g.a(new g.b() { // from class: q4.c
            @Override // t4.g.b
            public final h0 a(h hVar, y yVar) {
                d0 k8;
                k8 = e.k((p4.a) hVar, yVar);
                return k8;
            }
        }, p4.a.class, d0.class);
        f10340e = t4.f.a(new f.b() { // from class: q4.d
            @Override // t4.f.b
            public final h a(h0 h0Var, y yVar) {
                p4.a g8;
                g8 = e.g((d0) h0Var, yVar);
                return g8;
            }
        }, h8, d0.class);
        f10341f = f();
        f10342g = e();
    }

    private static Map e() {
        EnumMap enumMap = new EnumMap(i0.class);
        enumMap.put((EnumMap) i0.RAW, (i0) e.c.f10108d);
        enumMap.put((EnumMap) i0.TINK, (i0) e.c.f10106b);
        i0 i0Var = i0.CRUNCHY;
        e.c cVar = e.c.f10107c;
        enumMap.put((EnumMap) i0Var, (i0) cVar);
        enumMap.put((EnumMap) i0.LEGACY, (i0) cVar);
        return Collections.unmodifiableMap(enumMap);
    }

    private static Map f() {
        HashMap hashMap = new HashMap();
        hashMap.put(e.c.f10108d, i0.RAW);
        hashMap.put(e.c.f10106b, i0.TINK);
        hashMap.put(e.c.f10107c, i0.CRUNCHY);
        return Collections.unmodifiableMap(hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static p4.a g(d0 d0Var, y yVar) {
        if (d0Var.f().equals("type.googleapis.com/google.crypto.tink.AesSivKey")) {
            try {
                p Z = p.Z(d0Var.g(), com.google.crypto.tink.shaded.protobuf.p.b());
                if (Z.X() == 0) {
                    return p4.a.d().e(p4.e.b().b(Z.W().size()).c(n(d0Var.e())).a()).d(a5.b.a(Z.W().w(), y.b(yVar))).c(d0Var.c()).a();
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (a0 unused) {
                throw new GeneralSecurityException("Parsing AesSivKey failed");
            }
        }
        throw new IllegalArgumentException("Wrong type URL in call to AesSivParameters.parseParameters");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static p4.e h(e0 e0Var) {
        if (!e0Var.d().a0().equals("type.googleapis.com/google.crypto.tink.AesSivKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to AesSivParameters.parseParameters: " + e0Var.d().a0());
        }
        try {
            q Z = q.Z(e0Var.d().b0(), com.google.crypto.tink.shaded.protobuf.p.b());
            if (Z.X() == 0) {
                return p4.e.b().b(Z.W()).c(n(e0Var.d().Z())).a();
            }
            throw new GeneralSecurityException("Only version 0 keys are accepted");
        } catch (a0 e8) {
            throw new GeneralSecurityException("Parsing AesSivParameters failed: ", e8);
        }
    }

    public static void i() {
        j(u.c());
    }

    public static void j(u uVar) {
        uVar.m(f10337b);
        uVar.l(f10338c);
        uVar.k(f10339d);
        uVar.j(f10340e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static d0 k(p4.a aVar, y yVar) {
        return d0.b("type.googleapis.com/google.crypto.tink.AesSivKey", ((p) p.Y().q(com.google.crypto.tink.shaded.protobuf.h.l(aVar.e().d(y.b(yVar)))).h()).j(), y.c.SYMMETRIC, m(aVar.b().d()), aVar.a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static e0 l(p4.e eVar) {
        return e0.c((y4.a0) y4.a0.c0().r("type.googleapis.com/google.crypto.tink.AesSivKey").s(((q) q.Y().q(eVar.c()).h()).j()).q(m(eVar.d())).h());
    }

    private static i0 m(e.c cVar) {
        Map map = f10341f;
        if (map.containsKey(cVar)) {
            return (i0) map.get(cVar);
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + cVar);
    }

    private static e.c n(i0 i0Var) {
        Map map = f10342g;
        if (map.containsKey(i0Var)) {
            return (e.c) map.get(i0Var);
        }
        throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + i0Var.f());
    }
}
