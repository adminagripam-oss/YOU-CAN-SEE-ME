package t4;

import java.security.GeneralSecurityException;
import y4.y;
/* loaded from: classes.dex */
public final class d0 implements h0 {

    /* renamed from: a  reason: collision with root package name */
    private final String f11827a;

    /* renamed from: b  reason: collision with root package name */
    private final a5.a f11828b;

    /* renamed from: c  reason: collision with root package name */
    private final com.google.crypto.tink.shaded.protobuf.h f11829c;

    /* renamed from: d  reason: collision with root package name */
    private final y.c f11830d;

    /* renamed from: e  reason: collision with root package name */
    private final y4.i0 f11831e;

    /* renamed from: f  reason: collision with root package name */
    private final Integer f11832f;

    private d0(String str, a5.a aVar, com.google.crypto.tink.shaded.protobuf.h hVar, y.c cVar, y4.i0 i0Var, Integer num) {
        this.f11827a = str;
        this.f11828b = aVar;
        this.f11829c = hVar;
        this.f11830d = cVar;
        this.f11831e = i0Var;
        this.f11832f = num;
    }

    public static d0 b(String str, com.google.crypto.tink.shaded.protobuf.h hVar, y.c cVar, y4.i0 i0Var, Integer num) {
        if (i0Var == y4.i0.RAW) {
            if (num != null) {
                throw new GeneralSecurityException("Keys with output prefix type raw should not have an id requirement.");
            }
        } else if (num == null) {
            throw new GeneralSecurityException("Keys with output prefix type different from raw should have an id requirement.");
        }
        return new d0(str, k0.b(str), hVar, cVar, i0Var, num);
    }

    @Override // t4.h0
    public a5.a a() {
        return this.f11828b;
    }

    public Integer c() {
        return this.f11832f;
    }

    public y.c d() {
        return this.f11830d;
    }

    public y4.i0 e() {
        return this.f11831e;
    }

    public String f() {
        return this.f11827a;
    }

    public com.google.crypto.tink.shaded.protobuf.h g() {
        return this.f11829c;
    }
}
