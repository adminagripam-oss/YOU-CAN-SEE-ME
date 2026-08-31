package k4;

import java.security.GeneralSecurityException;
import k4.e0;
/* loaded from: classes.dex */
public final class a0 extends b {

    /* renamed from: a  reason: collision with root package name */
    private final e0 f8505a;

    /* renamed from: b  reason: collision with root package name */
    private final a5.b f8506b;

    /* renamed from: c  reason: collision with root package name */
    private final a5.a f8507c;

    /* renamed from: d  reason: collision with root package name */
    private final Integer f8508d;

    private a0(e0 e0Var, a5.b bVar, a5.a aVar, Integer num) {
        this.f8505a = e0Var;
        this.f8506b = bVar;
        this.f8507c = aVar;
        this.f8508d = num;
    }

    public static a0 d(e0.a aVar, a5.b bVar, Integer num) {
        e0.a aVar2 = e0.a.f8517d;
        if (aVar != aVar2 && num == null) {
            throw new GeneralSecurityException("For given Variant " + aVar + " the value of idRequirement must be non-null");
        } else if (aVar != aVar2 || num == null) {
            if (bVar.c() == 32) {
                e0 b8 = e0.b(aVar);
                return new a0(b8, bVar, f(b8, num), num);
            }
            throw new GeneralSecurityException("ChaCha20Poly1305 key must be constructed with key of length 32 bytes, not " + bVar.c());
        } else {
            throw new GeneralSecurityException("For given Variant NO_PREFIX the value of idRequirement must be null");
        }
    }

    private static a5.a f(e0 e0Var, Integer num) {
        if (e0Var.c() == e0.a.f8517d) {
            return t4.v.f11883a;
        }
        if (e0Var.c() == e0.a.f8516c) {
            return t4.v.a(num.intValue());
        }
        if (e0Var.c() == e0.a.f8515b) {
            return t4.v.b(num.intValue());
        }
        throw new IllegalStateException("Unknown Variant: " + e0Var.c());
    }

    @Override // j4.h
    public Integer a() {
        return this.f8508d;
    }

    @Override // k4.b
    public a5.a c() {
        return this.f8507c;
    }

    public a5.b e() {
        return this.f8506b;
    }

    @Override // j4.h
    /* renamed from: g */
    public e0 b() {
        return this.f8505a;
    }
}
