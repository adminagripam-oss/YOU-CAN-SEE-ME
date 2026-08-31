package p4;

import java.security.GeneralSecurityException;
import p4.e;
import t4.v;
/* loaded from: classes.dex */
public final class a extends g {

    /* renamed from: a  reason: collision with root package name */
    private final e f10091a;

    /* renamed from: b  reason: collision with root package name */
    private final a5.b f10092b;

    /* renamed from: c  reason: collision with root package name */
    private final a5.a f10093c;

    /* renamed from: d  reason: collision with root package name */
    private final Integer f10094d;

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private e f10095a;

        /* renamed from: b  reason: collision with root package name */
        private a5.b f10096b;

        /* renamed from: c  reason: collision with root package name */
        private Integer f10097c;

        private b() {
            this.f10095a = null;
            this.f10096b = null;
            this.f10097c = null;
        }

        private a5.a b() {
            if (this.f10095a.d() == e.c.f10108d) {
                return v.f11883a;
            }
            if (this.f10095a.d() == e.c.f10107c) {
                return v.a(this.f10097c.intValue());
            }
            if (this.f10095a.d() == e.c.f10106b) {
                return v.b(this.f10097c.intValue());
            }
            throw new IllegalStateException("Unknown AesSivParameters.Variant: " + this.f10095a.d());
        }

        public a a() {
            e eVar = this.f10095a;
            if (eVar == null || this.f10096b == null) {
                throw new IllegalArgumentException("Cannot build without parameters and/or key material");
            }
            if (eVar.c() == this.f10096b.c()) {
                if (this.f10095a.a() && this.f10097c == null) {
                    throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
                }
                if (this.f10095a.a() || this.f10097c == null) {
                    return new a(this.f10095a, this.f10096b, b(), this.f10097c);
                }
                throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
            }
            throw new GeneralSecurityException("Key size mismatch");
        }

        public b c(Integer num) {
            this.f10097c = num;
            return this;
        }

        public b d(a5.b bVar) {
            this.f10096b = bVar;
            return this;
        }

        public b e(e eVar) {
            this.f10095a = eVar;
            return this;
        }
    }

    private a(e eVar, a5.b bVar, a5.a aVar, Integer num) {
        this.f10091a = eVar;
        this.f10092b = bVar;
        this.f10093c = aVar;
        this.f10094d = num;
    }

    public static b d() {
        return new b();
    }

    @Override // j4.h
    public Integer a() {
        return this.f10094d;
    }

    @Override // p4.g
    public a5.a c() {
        return this.f10093c;
    }

    public a5.b e() {
        return this.f10092b;
    }

    @Override // j4.h
    /* renamed from: f */
    public e b() {
        return this.f10091a;
    }
}
