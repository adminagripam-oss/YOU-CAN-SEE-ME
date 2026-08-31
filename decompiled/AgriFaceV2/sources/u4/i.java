package u4;

import java.security.GeneralSecurityException;
import u4.n;
/* loaded from: classes.dex */
public final class i extends p {

    /* renamed from: a  reason: collision with root package name */
    private final n f12123a;

    /* renamed from: b  reason: collision with root package name */
    private final a5.b f12124b;

    /* renamed from: c  reason: collision with root package name */
    private final a5.a f12125c;

    /* renamed from: d  reason: collision with root package name */
    private final Integer f12126d;

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private n f12127a;

        /* renamed from: b  reason: collision with root package name */
        private a5.b f12128b;

        /* renamed from: c  reason: collision with root package name */
        private Integer f12129c;

        private b() {
            this.f12127a = null;
            this.f12128b = null;
            this.f12129c = null;
        }

        private a5.a b() {
            if (this.f12127a.g() == n.d.f12153e) {
                return t4.v.f11883a;
            }
            if (this.f12127a.g() == n.d.f12152d || this.f12127a.g() == n.d.f12151c) {
                return t4.v.a(this.f12129c.intValue());
            }
            if (this.f12127a.g() == n.d.f12150b) {
                return t4.v.b(this.f12129c.intValue());
            }
            throw new IllegalStateException("Unknown HmacParameters.Variant: " + this.f12127a.g());
        }

        public i a() {
            n nVar = this.f12127a;
            if (nVar == null || this.f12128b == null) {
                throw new GeneralSecurityException("Cannot build without parameters and/or key material");
            }
            if (nVar.e() == this.f12128b.c()) {
                if (this.f12127a.a() && this.f12129c == null) {
                    throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
                }
                if (this.f12127a.a() || this.f12129c == null) {
                    return new i(this.f12127a, this.f12128b, b(), this.f12129c);
                }
                throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
            }
            throw new GeneralSecurityException("Key size mismatch");
        }

        public b c(Integer num) {
            this.f12129c = num;
            return this;
        }

        public b d(a5.b bVar) {
            this.f12128b = bVar;
            return this;
        }

        public b e(n nVar) {
            this.f12127a = nVar;
            return this;
        }
    }

    private i(n nVar, a5.b bVar, a5.a aVar, Integer num) {
        this.f12123a = nVar;
        this.f12124b = bVar;
        this.f12125c = aVar;
        this.f12126d = num;
    }

    public static b d() {
        return new b();
    }

    @Override // j4.h
    public Integer a() {
        return this.f12126d;
    }

    @Override // u4.p
    public a5.a c() {
        return this.f12125c;
    }

    public a5.b e() {
        return this.f12124b;
    }

    @Override // j4.h
    /* renamed from: f */
    public n b() {
        return this.f12123a;
    }
}
