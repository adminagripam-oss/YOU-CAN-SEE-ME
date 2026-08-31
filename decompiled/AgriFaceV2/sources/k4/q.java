package k4;

import java.security.GeneralSecurityException;
import k4.u;
/* loaded from: classes.dex */
public final class q extends k4.b {

    /* renamed from: a  reason: collision with root package name */
    private final u f8623a;

    /* renamed from: b  reason: collision with root package name */
    private final a5.b f8624b;

    /* renamed from: c  reason: collision with root package name */
    private final a5.a f8625c;

    /* renamed from: d  reason: collision with root package name */
    private final Integer f8626d;

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private u f8627a;

        /* renamed from: b  reason: collision with root package name */
        private a5.b f8628b;

        /* renamed from: c  reason: collision with root package name */
        private Integer f8629c;

        private b() {
            this.f8627a = null;
            this.f8628b = null;
            this.f8629c = null;
        }

        private a5.a b() {
            if (this.f8627a.f() == u.c.f8663d) {
                return t4.v.f11883a;
            }
            if (this.f8627a.f() == u.c.f8662c) {
                return t4.v.a(this.f8629c.intValue());
            }
            if (this.f8627a.f() == u.c.f8661b) {
                return t4.v.b(this.f8629c.intValue());
            }
            throw new IllegalStateException("Unknown AesGcmParameters.Variant: " + this.f8627a.f());
        }

        public q a() {
            u uVar = this.f8627a;
            if (uVar == null || this.f8628b == null) {
                throw new GeneralSecurityException("Cannot build without parameters and/or key material");
            }
            if (uVar.d() == this.f8628b.c()) {
                if (this.f8627a.a() && this.f8629c == null) {
                    throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
                }
                if (this.f8627a.a() || this.f8629c == null) {
                    return new q(this.f8627a, this.f8628b, b(), this.f8629c);
                }
                throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
            }
            throw new GeneralSecurityException("Key size mismatch");
        }

        public b c(Integer num) {
            this.f8629c = num;
            return this;
        }

        public b d(a5.b bVar) {
            this.f8628b = bVar;
            return this;
        }

        public b e(u uVar) {
            this.f8627a = uVar;
            return this;
        }
    }

    private q(u uVar, a5.b bVar, a5.a aVar, Integer num) {
        this.f8623a = uVar;
        this.f8624b = bVar;
        this.f8625c = aVar;
        this.f8626d = num;
    }

    public static b d() {
        return new b();
    }

    @Override // j4.h
    public Integer a() {
        return this.f8626d;
    }

    @Override // k4.b
    public a5.a c() {
        return this.f8625c;
    }

    public a5.b e() {
        return this.f8624b;
    }

    @Override // j4.h
    /* renamed from: f */
    public u b() {
        return this.f8623a;
    }
}
