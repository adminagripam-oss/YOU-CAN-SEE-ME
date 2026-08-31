package k4;

import java.security.GeneralSecurityException;
import k4.k;
/* loaded from: classes.dex */
public final class f extends k4.b {

    /* renamed from: a  reason: collision with root package name */
    private final k f8519a;

    /* renamed from: b  reason: collision with root package name */
    private final a5.b f8520b;

    /* renamed from: c  reason: collision with root package name */
    private final a5.b f8521c;

    /* renamed from: d  reason: collision with root package name */
    private final a5.a f8522d;

    /* renamed from: e  reason: collision with root package name */
    private final Integer f8523e;

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private k f8524a;

        /* renamed from: b  reason: collision with root package name */
        private a5.b f8525b;

        /* renamed from: c  reason: collision with root package name */
        private a5.b f8526c;

        /* renamed from: d  reason: collision with root package name */
        private Integer f8527d;

        private b() {
            this.f8524a = null;
            this.f8525b = null;
            this.f8526c = null;
            this.f8527d = null;
        }

        private a5.a b() {
            if (this.f8524a.h() == k.d.f8573d) {
                return t4.v.f11883a;
            }
            if (this.f8524a.h() == k.d.f8572c) {
                return t4.v.a(this.f8527d.intValue());
            }
            if (this.f8524a.h() == k.d.f8571b) {
                return t4.v.b(this.f8527d.intValue());
            }
            throw new IllegalStateException("Unknown AesCtrHmacAeadParameters.Variant: " + this.f8524a.h());
        }

        public f a() {
            k kVar = this.f8524a;
            if (kVar != null) {
                if (this.f8525b == null || this.f8526c == null) {
                    throw new GeneralSecurityException("Cannot build without key material");
                }
                if (kVar.c() == this.f8525b.c()) {
                    if (this.f8524a.e() == this.f8526c.c()) {
                        if (this.f8524a.a() && this.f8527d == null) {
                            throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
                        }
                        if (this.f8524a.a() || this.f8527d == null) {
                            return new f(this.f8524a, this.f8525b, this.f8526c, b(), this.f8527d);
                        }
                        throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
                    }
                    throw new GeneralSecurityException("HMAC key size mismatch");
                }
                throw new GeneralSecurityException("AES key size mismatch");
            }
            throw new GeneralSecurityException("Cannot build without parameters");
        }

        public b c(a5.b bVar) {
            this.f8525b = bVar;
            return this;
        }

        public b d(a5.b bVar) {
            this.f8526c = bVar;
            return this;
        }

        public b e(Integer num) {
            this.f8527d = num;
            return this;
        }

        public b f(k kVar) {
            this.f8524a = kVar;
            return this;
        }
    }

    private f(k kVar, a5.b bVar, a5.b bVar2, a5.a aVar, Integer num) {
        this.f8519a = kVar;
        this.f8520b = bVar;
        this.f8521c = bVar2;
        this.f8522d = aVar;
        this.f8523e = num;
    }

    public static b d() {
        return new b();
    }

    @Override // j4.h
    public Integer a() {
        return this.f8523e;
    }

    @Override // k4.b
    public a5.a c() {
        return this.f8522d;
    }

    public a5.b e() {
        return this.f8520b;
    }

    public a5.b f() {
        return this.f8521c;
    }

    @Override // j4.h
    /* renamed from: g */
    public k b() {
        return this.f8519a;
    }
}
