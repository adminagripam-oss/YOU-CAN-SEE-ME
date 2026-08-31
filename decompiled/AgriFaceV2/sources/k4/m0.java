package k4;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import k4.n0;
/* loaded from: classes.dex */
public final class m0 extends b {

    /* renamed from: a  reason: collision with root package name */
    private final n0 f8589a;

    /* renamed from: b  reason: collision with root package name */
    private final a5.a f8590b;

    /* renamed from: c  reason: collision with root package name */
    private final Integer f8591c;

    private m0(n0 n0Var, a5.a aVar, Integer num) {
        this.f8589a = n0Var;
        this.f8590b = aVar;
        this.f8591c = num;
    }

    public static m0 d(n0 n0Var, Integer num) {
        a5.a a8;
        if (n0Var.d() == n0.a.f8594b) {
            if (num == null) {
                throw new GeneralSecurityException("For given Variant TINK the value of idRequirement must be non-null");
            }
            a8 = a5.a.a(ByteBuffer.allocate(5).put((byte) 1).putInt(num.intValue()).array());
        } else if (n0Var.d() != n0.a.f8595c) {
            throw new GeneralSecurityException("Unknown Variant: " + n0Var.d());
        } else if (num != null) {
            throw new GeneralSecurityException("For given Variant NO_PREFIX the value of idRequirement must be null");
        } else {
            a8 = a5.a.a(new byte[0]);
        }
        return new m0(n0Var, a8, num);
    }

    @Override // j4.h
    public Integer a() {
        return this.f8591c;
    }

    @Override // k4.b
    public a5.a c() {
        return this.f8590b;
    }

    @Override // j4.h
    /* renamed from: e */
    public n0 b() {
        return this.f8589a;
    }
}
