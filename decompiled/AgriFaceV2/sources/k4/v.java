package k4;

import java.security.GeneralSecurityException;
import k4.z;
/* loaded from: classes.dex */
public final class v extends k4.b {

    /* renamed from: a  reason: collision with root package name */
    private final z f8683a;

    /* renamed from: b  reason: collision with root package name */
    private final a5.b f8684b;

    /* renamed from: c  reason: collision with root package name */
    private final a5.a f8685c;

    /* renamed from: d  reason: collision with root package name */
    private final Integer f8686d;

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private z f8687a;

        /* renamed from: b  reason: collision with root package name */
        private a5.b f8688b;

        /* renamed from: c  reason: collision with root package name */
        private Integer f8689c;

        private b() {
            this.f8687a = null;
            this.f8688b = null;
            this.f8689c = null;
        }

        private a5.a b() {
            if (this.f8687a.d() == z.c.f8700d) {
                return t4.v.f11883a;
            }
            if (this.f8687a.d() == z.c.f8699c) {
                return t4.v.a(this.f8689c.intValue());
            }
            if (this.f8687a.d() == z.c.f8698b) {
                return t4.v.b(this.f8689c.intValue());
            }
            throw new IllegalStateException("Unknown AesGcmSivParameters.Variant: " + this.f8687a.d());
        }

        public v a() {
            z zVar = this.f8687a;
            if (zVar == null || this.f8688b == null) {
                throw new GeneralSecurityException("Cannot build without parameters and/or key material");
            }
            if (zVar.c() == this.f8688b.c()) {
                if (this.f8687a.a() && this.f8689c == null) {
                    throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
                }
                if (this.f8687a.a() || this.f8689c == null) {
                    return new v(this.f8687a, this.f8688b, b(), this.f8689c);
                }
                throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
            }
            throw new GeneralSecurityException("Key size mismatch");
        }

        public b c(Integer num) {
            this.f8689c = num;
            return this;
        }

        public b d(a5.b bVar) {
            this.f8688b = bVar;
            return this;
        }

        public b e(z zVar) {
            this.f8687a = zVar;
            return this;
        }
    }

    private v(z zVar, a5.b bVar, a5.a aVar, Integer num) {
        this.f8683a = zVar;
        this.f8684b = bVar;
        this.f8685c = aVar;
        this.f8686d = num;
    }

    public static b d() {
        return new b();
    }

    @Override // j4.h
    public Integer a() {
        return this.f8686d;
    }

    @Override // k4.b
    public a5.a c() {
        return this.f8685c;
    }

    public a5.b e() {
        return this.f8684b;
    }

    @Override // j4.h
    /* renamed from: f */
    public z b() {
        return this.f8683a;
    }
}
