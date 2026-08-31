package k4;

import java.security.GeneralSecurityException;
import k4.p;
/* loaded from: classes.dex */
public final class l extends k4.b {

    /* renamed from: a  reason: collision with root package name */
    private final p f8579a;

    /* renamed from: b  reason: collision with root package name */
    private final a5.b f8580b;

    /* renamed from: c  reason: collision with root package name */
    private final a5.a f8581c;

    /* renamed from: d  reason: collision with root package name */
    private final Integer f8582d;

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private p f8583a;

        /* renamed from: b  reason: collision with root package name */
        private a5.b f8584b;

        /* renamed from: c  reason: collision with root package name */
        private Integer f8585c;

        private b() {
            this.f8583a = null;
            this.f8584b = null;
            this.f8585c = null;
        }

        private a5.a b() {
            if (this.f8583a.f() == p.c.f8617d) {
                return t4.v.f11883a;
            }
            if (this.f8583a.f() == p.c.f8616c) {
                return t4.v.a(this.f8585c.intValue());
            }
            if (this.f8583a.f() == p.c.f8615b) {
                return t4.v.b(this.f8585c.intValue());
            }
            throw new IllegalStateException("Unknown AesEaxParameters.Variant: " + this.f8583a.f());
        }

        public l a() {
            p pVar = this.f8583a;
            if (pVar == null || this.f8584b == null) {
                throw new GeneralSecurityException("Cannot build without parameters and/or key material");
            }
            if (pVar.d() == this.f8584b.c()) {
                if (this.f8583a.a() && this.f8585c == null) {
                    throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
                }
                if (this.f8583a.a() || this.f8585c == null) {
                    return new l(this.f8583a, this.f8584b, b(), this.f8585c);
                }
                throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
            }
            throw new GeneralSecurityException("Key size mismatch");
        }

        public b c(Integer num) {
            this.f8585c = num;
            return this;
        }

        public b d(a5.b bVar) {
            this.f8584b = bVar;
            return this;
        }

        public b e(p pVar) {
            this.f8583a = pVar;
            return this;
        }
    }

    private l(p pVar, a5.b bVar, a5.a aVar, Integer num) {
        this.f8579a = pVar;
        this.f8580b = bVar;
        this.f8581c = aVar;
        this.f8582d = num;
    }

    public static b d() {
        return new b();
    }

    @Override // j4.h
    public Integer a() {
        return this.f8582d;
    }

    @Override // k4.b
    public a5.a c() {
        return this.f8581c;
    }

    public a5.b e() {
        return this.f8580b;
    }

    @Override // j4.h
    /* renamed from: f */
    public p b() {
        return this.f8579a;
    }
}
