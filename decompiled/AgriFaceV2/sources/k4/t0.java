package k4;

import java.security.GeneralSecurityException;
import k4.u0;
/* loaded from: classes.dex */
public final class t0 extends b {

    /* renamed from: a  reason: collision with root package name */
    private final u0 f8645a;

    /* renamed from: b  reason: collision with root package name */
    private final a5.a f8646b;

    /* renamed from: c  reason: collision with root package name */
    private final Integer f8647c;

    private t0(u0 u0Var, a5.a aVar, Integer num) {
        this.f8645a = u0Var;
        this.f8646b = aVar;
        this.f8647c = num;
    }

    public static t0 d(u0 u0Var, Integer num) {
        a5.a b8;
        if (u0Var.e() == u0.d.f8681c) {
            if (num != null) {
                throw new GeneralSecurityException("For given Variant NO_PREFIX the value of idRequirement must be null");
            }
            b8 = t4.v.f11883a;
        } else if (u0Var.e() != u0.d.f8680b) {
            throw new GeneralSecurityException("Unknown Variant: " + u0Var.e());
        } else if (num == null) {
            throw new GeneralSecurityException("For given Variant TINK the value of idRequirement must be non-null");
        } else {
            b8 = t4.v.b(num.intValue());
        }
        return new t0(u0Var, b8, num);
    }

    @Override // j4.h
    public Integer a() {
        return this.f8647c;
    }

    @Override // k4.b
    public a5.a c() {
        return this.f8646b;
    }

    @Override // j4.h
    /* renamed from: e */
    public u0 b() {
        return this.f8645a;
    }
}
