package u4;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Objects;
/* loaded from: classes.dex */
public final class n extends q {

    /* renamed from: a  reason: collision with root package name */
    private final int f12136a;

    /* renamed from: b  reason: collision with root package name */
    private final int f12137b;

    /* renamed from: c  reason: collision with root package name */
    private final d f12138c;

    /* renamed from: d  reason: collision with root package name */
    private final c f12139d;

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private Integer f12140a;

        /* renamed from: b  reason: collision with root package name */
        private Integer f12141b;

        /* renamed from: c  reason: collision with root package name */
        private c f12142c;

        /* renamed from: d  reason: collision with root package name */
        private d f12143d;

        private b() {
            this.f12140a = null;
            this.f12141b = null;
            this.f12142c = null;
            this.f12143d = d.f12153e;
        }

        private static void f(int i8, c cVar) {
            if (i8 < 10) {
                throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; must be at least 10 bytes", Integer.valueOf(i8)));
            }
            if (cVar == c.f12144b) {
                if (i8 > 20) {
                    throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 20 bytes for SHA1", Integer.valueOf(i8)));
                }
            } else if (cVar == c.f12145c) {
                if (i8 > 28) {
                    throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 28 bytes for SHA224", Integer.valueOf(i8)));
                }
            } else if (cVar == c.f12146d) {
                if (i8 > 32) {
                    throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 32 bytes for SHA256", Integer.valueOf(i8)));
                }
            } else if (cVar == c.f12147e) {
                if (i8 > 48) {
                    throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 48 bytes for SHA384", Integer.valueOf(i8)));
                }
            } else if (cVar != c.f12148f) {
                throw new GeneralSecurityException("unknown hash type; must be SHA256, SHA384 or SHA512");
            } else {
                if (i8 > 64) {
                    throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 64 bytes for SHA512", Integer.valueOf(i8)));
                }
            }
        }

        public n a() {
            Integer num = this.f12140a;
            if (num != null) {
                if (this.f12141b != null) {
                    if (this.f12142c != null) {
                        if (this.f12143d != null) {
                            if (num.intValue() >= 16) {
                                f(this.f12141b.intValue(), this.f12142c);
                                return new n(this.f12140a.intValue(), this.f12141b.intValue(), this.f12143d, this.f12142c);
                            }
                            throw new InvalidAlgorithmParameterException(String.format("Invalid key size in bytes %d; must be at least 16 bytes", this.f12140a));
                        }
                        throw new GeneralSecurityException("variant is not set");
                    }
                    throw new GeneralSecurityException("hash type is not set");
                }
                throw new GeneralSecurityException("tag size is not set");
            }
            throw new GeneralSecurityException("key size is not set");
        }

        public b b(c cVar) {
            this.f12142c = cVar;
            return this;
        }

        public b c(int i8) {
            this.f12140a = Integer.valueOf(i8);
            return this;
        }

        public b d(int i8) {
            this.f12141b = Integer.valueOf(i8);
            return this;
        }

        public b e(d dVar) {
            this.f12143d = dVar;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static final class c {

        /* renamed from: b  reason: collision with root package name */
        public static final c f12144b = new c("SHA1");

        /* renamed from: c  reason: collision with root package name */
        public static final c f12145c = new c("SHA224");

        /* renamed from: d  reason: collision with root package name */
        public static final c f12146d = new c("SHA256");

        /* renamed from: e  reason: collision with root package name */
        public static final c f12147e = new c("SHA384");

        /* renamed from: f  reason: collision with root package name */
        public static final c f12148f = new c("SHA512");

        /* renamed from: a  reason: collision with root package name */
        private final String f12149a;

        private c(String str) {
            this.f12149a = str;
        }

        public String toString() {
            return this.f12149a;
        }
    }

    /* loaded from: classes.dex */
    public static final class d {

        /* renamed from: b  reason: collision with root package name */
        public static final d f12150b = new d("TINK");

        /* renamed from: c  reason: collision with root package name */
        public static final d f12151c = new d("CRUNCHY");

        /* renamed from: d  reason: collision with root package name */
        public static final d f12152d = new d("LEGACY");

        /* renamed from: e  reason: collision with root package name */
        public static final d f12153e = new d("NO_PREFIX");

        /* renamed from: a  reason: collision with root package name */
        private final String f12154a;

        private d(String str) {
            this.f12154a = str;
        }

        public String toString() {
            return this.f12154a;
        }
    }

    private n(int i8, int i9, d dVar, c cVar) {
        this.f12136a = i8;
        this.f12137b = i9;
        this.f12138c = dVar;
        this.f12139d = cVar;
    }

    public static b b() {
        return new b();
    }

    @Override // j4.w
    public boolean a() {
        return this.f12138c != d.f12153e;
    }

    public int c() {
        return this.f12137b;
    }

    public c d() {
        return this.f12139d;
    }

    public int e() {
        return this.f12136a;
    }

    public boolean equals(Object obj) {
        if (obj instanceof n) {
            n nVar = (n) obj;
            return nVar.e() == e() && nVar.f() == f() && nVar.g() == g() && nVar.d() == d();
        }
        return false;
    }

    public int f() {
        d dVar = this.f12138c;
        if (dVar == d.f12153e) {
            return c();
        }
        if (dVar == d.f12150b || dVar == d.f12151c || dVar == d.f12152d) {
            return c() + 5;
        }
        throw new IllegalStateException("Unknown variant");
    }

    public d g() {
        return this.f12138c;
    }

    public int hashCode() {
        return Objects.hash(n.class, Integer.valueOf(this.f12136a), Integer.valueOf(this.f12137b), this.f12138c, this.f12139d);
    }

    public String toString() {
        return "HMAC Parameters (variant: " + this.f12138c + ", hashType: " + this.f12139d + ", " + this.f12137b + "-byte tags, and " + this.f12136a + "-byte key)";
    }
}
