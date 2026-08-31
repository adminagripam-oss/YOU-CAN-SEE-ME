package k4;

import java.security.GeneralSecurityException;
import k4.o1;
/* loaded from: classes.dex */
public final class k1 extends b {

    /* renamed from: a  reason: collision with root package name */
    private final o1 f8575a;

    /* renamed from: b  reason: collision with root package name */
    private final a5.b f8576b;

    /* renamed from: c  reason: collision with root package name */
    private final a5.a f8577c;

    /* renamed from: d  reason: collision with root package name */
    private final Integer f8578d;

    private k1(o1 o1Var, a5.b bVar, a5.a aVar, Integer num) {
        this.f8575a = o1Var;
        this.f8576b = bVar;
        this.f8577c = aVar;
        this.f8578d = num;
    }

    public static k1 d(o1 o1Var, a5.b bVar, Integer num) {
        o1.a d8 = o1Var.d();
        o1.a aVar = o1.a.f8605c;
        if (d8 != aVar && num == null) {
            throw new GeneralSecurityException("For given Variant " + o1Var.d() + " the value of idRequirement must be non-null");
        } else if (o1Var.d() != aVar || num == null) {
            if (bVar.c() == 32) {
                return new k1(o1Var, bVar, f(o1Var, num), num);
            }
            throw new GeneralSecurityException("XAesGcmKey key must be constructed with key of length 32 bytes, not " + bVar.c());
        } else {
            throw new GeneralSecurityException("For given Variant NO_PREFIX the value of idRequirement must be null");
        }
    }

    private static a5.a f(o1 o1Var, Integer num) {
        if (o1Var.d() == o1.a.f8605c) {
            return t4.v.f11883a;
        }
        if (o1Var.d() == o1.a.f8604b) {
            return t4.v.b(num.intValue());
        }
        throw new IllegalStateException("Unknown Variant: " + o1Var.d());
    }

    @Override // j4.h
    public Integer a() {
        return this.f8578d;
    }

    @Override // k4.b
    public a5.a c() {
        return this.f8577c;
    }

    public a5.b e() {
        return this.f8576b;
    }

    @Override // j4.h
    /* renamed from: g */
    public o1 b() {
        return this.f8575a;
    }
}
