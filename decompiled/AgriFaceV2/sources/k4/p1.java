package k4;

import java.security.GeneralSecurityException;
import k4.t1;
/* loaded from: classes.dex */
public final class p1 extends b {

    /* renamed from: a  reason: collision with root package name */
    private final t1 f8619a;

    /* renamed from: b  reason: collision with root package name */
    private final a5.b f8620b;

    /* renamed from: c  reason: collision with root package name */
    private final a5.a f8621c;

    /* renamed from: d  reason: collision with root package name */
    private final Integer f8622d;

    private p1(t1 t1Var, a5.b bVar, a5.a aVar, Integer num) {
        this.f8619a = t1Var;
        this.f8620b = bVar;
        this.f8621c = aVar;
        this.f8622d = num;
    }

    public static p1 d(t1.a aVar, a5.b bVar, Integer num) {
        t1.a aVar2 = t1.a.f8651d;
        if (aVar != aVar2 && num == null) {
            throw new GeneralSecurityException("For given Variant " + aVar + " the value of idRequirement must be non-null");
        } else if (aVar != aVar2 || num == null) {
            if (bVar.c() == 32) {
                t1 b8 = t1.b(aVar);
                return new p1(b8, bVar, f(b8, num), num);
            }
            throw new GeneralSecurityException("XChaCha20Poly1305 key must be constructed with key of length 32 bytes, not " + bVar.c());
        } else {
            throw new GeneralSecurityException("For given Variant NO_PREFIX the value of idRequirement must be null");
        }
    }

    private static a5.a f(t1 t1Var, Integer num) {
        if (t1Var.c() == t1.a.f8651d) {
            return t4.v.f11883a;
        }
        if (t1Var.c() == t1.a.f8650c) {
            return t4.v.a(num.intValue());
        }
        if (t1Var.c() == t1.a.f8649b) {
            return t4.v.b(num.intValue());
        }
        throw new IllegalStateException("Unknown Variant: " + t1Var.c());
    }

    @Override // j4.h
    public Integer a() {
        return this.f8622d;
    }

    @Override // k4.b
    public a5.a c() {
        return this.f8621c;
    }

    public a5.b e() {
        return this.f8620b;
    }

    @Override // j4.h
    /* renamed from: g */
    public t1 b() {
        return this.f8619a;
    }
}
