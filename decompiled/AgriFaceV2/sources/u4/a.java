package u4;

import java.security.GeneralSecurityException;
import u4.f;
/* loaded from: classes.dex */
public final class a extends p {

    /* renamed from: a  reason: collision with root package name */
    private final f f12100a;

    /* renamed from: b  reason: collision with root package name */
    private final a5.b f12101b;

    /* renamed from: c  reason: collision with root package name */
    private final a5.a f12102c;

    /* renamed from: d  reason: collision with root package name */
    private final Integer f12103d;

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private f f12104a;

        /* renamed from: b  reason: collision with root package name */
        private a5.b f12105b;

        /* renamed from: c  reason: collision with root package name */
        private Integer f12106c;

        private b() {
            this.f12104a = null;
            this.f12105b = null;
            this.f12106c = null;
        }

        private a5.a b() {
            if (this.f12104a.f() == f.c.f12120e) {
                return t4.v.f11883a;
            }
            if (this.f12104a.f() == f.c.f12119d || this.f12104a.f() == f.c.f12118c) {
                return t4.v.a(this.f12106c.intValue());
            }
            if (this.f12104a.f() == f.c.f12117b) {
                return t4.v.b(this.f12106c.intValue());
            }
            throw new IllegalStateException("Unknown AesCmacParametersParameters.Variant: " + this.f12104a.f());
        }

        public a a() {
            f fVar = this.f12104a;
            if (fVar == null || this.f12105b == null) {
                throw new GeneralSecurityException("Cannot build without parameters and/or key material");
            }
            if (fVar.d() == this.f12105b.c()) {
                if (this.f12104a.a() && this.f12106c == null) {
                    throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
                }
                if (this.f12104a.a() || this.f12106c == null) {
                    return new a(this.f12104a, this.f12105b, b(), this.f12106c);
                }
                throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
            }
            throw new GeneralSecurityException("Key size mismatch");
        }

        public b c(a5.b bVar) {
            this.f12105b = bVar;
            return this;
        }

        public b d(Integer num) {
            this.f12106c = num;
            return this;
        }

        public b e(f fVar) {
            this.f12104a = fVar;
            return this;
        }
    }

    private a(f fVar, a5.b bVar, a5.a aVar, Integer num) {
        this.f12100a = fVar;
        this.f12101b = bVar;
        this.f12102c = aVar;
        this.f12103d = num;
    }

    public static b d() {
        return new b();
    }

    @Override // j4.h
    public Integer a() {
        return this.f12103d;
    }

    @Override // u4.p
    public a5.a c() {
        return this.f12102c;
    }

    public a5.b e() {
        return this.f12101b;
    }

    @Override // j4.h
    /* renamed from: f */
    public f b() {
        return this.f12100a;
    }
}
